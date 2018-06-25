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

/**
 * Clase que implementa la logica del servicio WS_ClaveGetAuth
 * @author everis
 * 
 */
@Service("WS_ClaveGetAuth")
public class WS_ClaveGetAuthImpl implements WS_ClaveGetAuth {

	private static final Logger LOG = LoggerFactory.getLogger(WS_ClaveGetAuthImpl.class);
	
	public static String ERROR_AUTENTIFICACION = "Error en Autentificacion - La clave no se corresponde con ninguna aplicacion";
	public static String ERROR_REQUESTTIMEOUT = "Error en Peticion - La peticion se ha caducado";
	public static String ERROR_PARAMETROS = "Error en Parametros de entrada";

	public static final String RECEPT_QUEUE = "vm://get-auth";
	public static final String SOAP_ACTION = "get-auth";

	private StringTokenizer tokenizer = null;
	private String username = null;
	private String password = null;

	@Context
	private HttpServletRequest request;

	@Context
	private ServletContext servletContext;
	
	/**
	 * Metodo que implementa la logica de la peticion de autenticacion
	 * @param idServicio
	 * @param idPlataforma
	 * @param idDispositivo
	 * @return
	 */
	@Override
	public String getAuth(String idServicio, String idPlataforma, String idDispositivo) {
		RespuestaSAMLResponse respuesta = new RespuestaSAMLResponse();
		ResponseSAMLStatusType response = new ResponseSAMLStatusType();
		String decoded;

		try {
			// Get the Authorisation Header from Request
			String header = request.getHeader("authorization");

			// Header is in the format "Basic 3nc0dedDat4"
			// We need to extract data before decoding it back to original
			// string
			String data = header.substring(header.indexOf(" ") + 1);

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
					&& (null != idServicio && !("").equals(idServicio)) && (null != idPlataforma && !("").equals(idPlataforma))) {

				try {
					PeticionClaveAuthRequest peticion = new PeticionClaveAuthRequest();
					peticion.setUsuario(username);
					peticion.setPassword(password);
					peticion.setIdDispositivo(idDispositivo);
					peticion.setIdServicio(idServicio);
					peticion.setIdPlataforma(idPlataforma);
					
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

						JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaSAMLResponse.class);
						Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

						xmlRespuesta = eliminarSimbolos(xmlRespuesta);
						StringReader reader = new StringReader(xmlRespuesta);
						respuesta = (RespuestaSAMLResponse) unmarshaller.unmarshal(reader);

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
				response.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: servicioId, dispositivoId, plataformaId");

				respuesta.setStatus(response);
			}
		} catch (Exception e) {
			 LOG.error("Error en WS_ClaveGetAuthImpl", e);
			 response.setStatusCode("0020");
			 response.setStatusText("Autentificiacion no valida o enviada.");
			 response.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: Usuario, Password");
			 respuesta.setStatus(response);
		}

		String respuestaJson = "";

		try {
			Properties p = new Properties();
			InputStream is = WS_ClaveGetAuthImpl.class.getResourceAsStream("/properties/sp.properties");

			p.load(is);
			
			String parametro1 = p.getProperty("sp.clave.parametro1");
			String parametro2 = p.getProperty("sp.clave.parametro2");
			String parametro3 = p.getProperty("sp.clave.parametro3");
			String respuestaSinFormatear = this.getJsonResponse(respuesta);
			respuestaJson = respuestaSinFormatear.replace("parametro1", parametro1).replace("parametro2", parametro2).replace("parametro3", parametro3);
			
		} catch (Exception e) {
			respuesta = new RespuestaSAMLResponse();
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
	
	/**
	 * Metodo que elimina tildes y ene
	 * @param xmlRespuesta
	 * @return
	 */
	private String eliminarSimbolos(String xmlRespuesta) {
		return xmlRespuesta.replace("Ñ", "N").replace("ñ", "n").replace("Á", "A").replace("á", "a").replace("É", "E").replace("é", "e").replace("Í", "I").replace("í", "i")
				.replace("Ó", "O").replace("ó", "o").replace("Ú", "U").replace("ú", "u").replace("Ü", "U").replace("ü", "u");
	}
}
