/**
 * 
 */
package es.minhap.misim.bus.webapp.restserviceprovider;

import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPMessage;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.misc.BASE64Decoder;

import com.google.gson.Gson;

import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.minhap.misim.components.InicializarAEAT;

/**
 * @author everis
 * 
 */
@Service("WS_ClaveAuthRequest")
public class WS_ClaveAuthRequestImpl implements WS_ClaveAuthRequest {
	
	private static final Logger LOG = LoggerFactory.getLogger(WS_ClaveAuthRequestImpl.class);

	public static String ERROR_AUTENTIFICACION = "Error en Autentificacion - La clave no se corresponde con ninguna aplicacion";
	public static String ERROR_REQUESTTIMEOUT = "Error en Peticion - La peticion se ha caducado";
	public static String ERROR_PARAMETROS = "Error en Parametros de entrada";

	public static final String RECEPT_QUEUE = "vm://generar-SAML";
	public static final String SOAP_ACTION = "generar-SAML";

	private StringTokenizer tokenizer = null;
	private String username = null;
	private String password = null;

	@Context
	private HttpServletRequest request;

	@Context
	private ServletContext servletContext;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.minhap.misim.bus.webapp.restserviceprovider.GestionServiciosDisponibles
	 * #getServiciosDispoonibles(java.lang.String)
	 */
	

	@Override
	public String getSAMLRequest(MultivaluedMap<String,String>map) {
		RespuestaSAMLRequest respuesta = new RespuestaSAMLRequest();
		ResponseSAMLStatusType response = new ResponseSAMLStatusType();
		String decoded;

		try {
			// Get the Authorisation Header from Request
			String header = request.getHeader("authorization");

			// Header is in the format "Basic 3nc0dedDat4"
			// We need to extract data before decoding it back to original
			// string
			String data = header.substring(header.indexOf(" ") + 1);

			String idServicio = (null != map.getFirst("Servicio")) ? map.getFirst("Servicio") : null;
			String idPlataforma = (null != map.getFirst("Plataforma")) ? map.getFirst("Plataforma") : null;
			String idDispositivo = (null != map.getFirst("IdDispositivo")) ? map.getFirst("IdDispositivo") : null;
			String APILevel = (null != map.getFirst("APILevel")) ? map.getFirst("APILevel") : null;
			String uidDispositivo = (null != map.getFirst("UidDispositivo")) ? map.getFirst("UidDispositivo") : null;
			String tokenSession = (null != map.getFirst("TokenSession")) ? map.getFirst("TokenSession") : null;
				
			// Decode the data back to original string
			byte[] bytes = new BASE64Decoder().decodeBuffer(data);
			decoded = new String(bytes);

			LOG.info(decoded);
			if (null != decoded) {
				tokenizer = new StringTokenizer(decoded, ":");
				username = tokenizer.nextToken();
				password = tokenizer.nextToken();
			}

			if ((null != username && !("").equals(username)) && (null != password && !("").equals(password)) && (null != idDispositivo && !("").equals(idDispositivo))
					&& (null != idServicio && !("").equals(idServicio)) && (null != idPlataforma && !("").equals(idPlataforma))
					&& (null != uidDispositivo && !("").equals(uidDispositivo))
					&& (null != tokenSession && !("").equals(tokenSession))) {

				try {

					PeticionClaveAuthRequest peticion = new PeticionClaveAuthRequest();
					peticion.setUsuario(username);
					peticion.setPassword(password);
					peticion.setIdDispositivo(idDispositivo);
					peticion.setIdServicio(idServicio);
					peticion.setIdPlataforma(idPlataforma);
					peticion.setUidDispositivo(uidDispositivo);
					peticion.setTokenSession(tokenSession);
					
					if (null != APILevel){
						Integer.parseInt(APILevel);
						peticion.setAPILevel(APILevel);
					}

					DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					Document document = docBuilder.newDocument();

					JAXBContext context = JAXBContext.newInstance(peticion.getClass());
					Marshaller marshaller = context.createMarshaller();
					marshaller.marshal(peticion, document);

					String respuestaCompleta = XMLUtils.createSOAPFaultString((Node) document.getDocumentElement());

					try {

						MuleContext muleContext = (MuleContext) servletContext.getAttribute("mule.context");
						MuleClient muleClient = muleContext.getClient();
						SoapPayload<?> payload = new PeticionPayload();
						payload.setSoapAction(SOAP_ACTION);
						payload.setSoapMessage(XMLUtils.xml2doc(respuestaCompleta, Charset.forName("UTF-8")));

						final MuleMessage muleResponse = muleClient.send(RECEPT_QUEUE, payload, null, 10000);

						Document respuestaSOAP = muleResponse.getPayload(SoapPayload.class).getSoapMessage();

						NodeList nodoRespuesta = respuestaSOAP.getElementsByTagName("Respuesta");
						String xmlRespuesta = XMLUtils.nodeToString(nodoRespuesta.item(0));

						JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaSAMLRequest.class);
						Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

						StringReader reader = new StringReader(xmlRespuesta);
						respuesta = (RespuestaSAMLRequest) unmarshaller.unmarshal(reader);

					} catch (final MuleException e) {
						throw new RuntimeException("Error in mule client", e);
					} catch (final Exception e) {
						throw new RuntimeException("Error in provider endpoint", e);
					}

				}catch (NumberFormatException e) {
					response.setStatusCode("0020");
					response.setStatusText("KO");
					response.setDetails("La peticion no esta construida correctamente. APILevel no es un valor numerico");

					respuesta.setStatus(response);
				}catch (Exception e) {
					response.setStatusCode("0999");
					response.setStatusText("Error indeterminado");
					response.setDetails("Error indeterminado");

					respuesta.setStatus(response);
				}

			} else {
				response.setStatusCode("0020");
				response.setStatusText("La peticion no incluye todos los parametros obligatorios");
				response.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: servicioId, dispositivoId, plataformaId, UidDispositivo, TokenSession");

				respuesta.setStatus(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode("0020");
			response.setStatusText("Autentificiacion no valida o enviada.");
			response.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: Usuario, Password");
			respuesta.setStatus(response);
		}

		String respuestaJson = "";

		try {
			Properties p = new Properties();
			InputStream is = WS_ClaveAuthRequestImpl.class.getResourceAsStream("/properties/sp.properties");

			p.load(is);
			
			String parametro1 = p.getProperty("sp.clave.parametro1");
			String parametro2 = p.getProperty("sp.clave.parametro2");
			String parametro3 = p.getProperty("sp.clave.parametro3");
			String respuestaSinFormatear = this.getJsonResponse(respuesta);
			respuestaJson = respuestaSinFormatear.replace("parametro1", parametro1).replace("parametro2", parametro2).replace("parametro3", parametro3);
			
		} catch (Exception e) {
			respuesta = new RespuestaSAMLRequest();
			response = new ResponseSAMLStatusType();
			response.setStatusCode("0999");
			response.setStatusText("Error indeterminado");
			response.setDetails("Error indeterminado");

			respuesta.setStatus(response);
			respuestaJson = this.getJsonResponse(respuesta);
		}

		return respuestaJson;
	}

	/**
	 * Genera el SOAP Fault Message
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected static final SOAPMessage generateSOAPFault() throws Exception {

		ResponseStatusType response = new ResponseStatusType();

		response.setStatusCode("0999");
		response.setStatusText("Error indeterminado");
		response.setDetails("Error indeterminado");

		RespuestaServiciosDisponibles respuesta = new RespuestaServiciosDisponibles();
		// respuesta.setStatus(response);

		return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(respuesta, Charset.forName("UTF-8"), RespuestaServiciosDisponibles.class));
	}

	public String getJsonResponse(Object conResponse) {
		Gson gson = new Gson();

		String result = gson.toJson(conResponse);
		return result;
	}

}
