/**
 * 
 */
package es.minhap.misim.bus.webapp.restserviceprovider;

import java.io.StringReader;
import java.nio.charset.Charset;
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
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.misc.BASE64Decoder;

import com.google.gson.Gson;

import es.minhap.misim.bus.core.pojo.PeticionPayload;

/**
 * @author everis
 * 
 */
@Service("getAvisosUsuario")
public class GetAvisosUsuarioImpl implements GetAvisosUsuario {

	public static String ERROR_AUTENTIFICACION = "Error en Autentificacion - La clave no se corresponde con ninguna aplicacion";
	public static String ERROR_REQUESTTIMEOUT = "Error en Peticion - La peticion se ha caducado";
	public static String ERROR_PARAMETROS = "Error en Parametros de entrada";

	public static final String RECEPT_QUEUE = "vm://get-avisos-usuario-push";
	public static final String SOAP_ACTION = "get-avisos-usuario-push";

	private StringTokenizer tokenizer = null;
	private String username = null;
	private String password = null;

	@Context
	private HttpServletRequest request;

	@Context
	private ServletContext servletContext;
	

	@Override
	public String getAvisosUsuario(String idServicio, String idPlataforma, String idDispositivo, 
			String idUsuario, String numPagina, String tamPagina ) {
		RespuestaGetAvisosUsuario respuesta = new RespuestaGetAvisosUsuario();
		AvisosResponseStatusType response = new AvisosResponseStatusType();
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

			System.out.println(decoded);
			if (null != decoded) {
				tokenizer = new StringTokenizer(decoded, ":");
				username = tokenizer.nextToken();
				password = tokenizer.nextToken();
			}

			if ((null != username && !("").equals(username)) && (null != password && !("").equals(password)) && (null != idDispositivo && !("").equals(idDispositivo))
					&& (null != idServicio && !("").equals(idServicio)) && (null != idPlataforma && !("").equals(idPlataforma))) {

				try {

					PeticionGetAvisosUsuario peticion = new PeticionGetAvisosUsuario();
					peticion.setUsuario(username);
					peticion.setPassword(password);
					peticion.setIdDispositivo(idDispositivo);
					peticion.setIdServicio(idServicio);
					peticion.setIdPlataforma(idPlataforma);
					peticion.setIdUsuario(idUsuario);
					peticion.setTamPagina(tamPagina);
					peticion.setNumPagina(numPagina);
										
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

						JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaGetAvisosUsuario.class);
						Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

						StringReader reader = new StringReader(xmlRespuesta);
						respuesta = (RespuestaGetAvisosUsuario) unmarshaller.unmarshal(reader);

					} catch (final MuleException e) {
						throw new RuntimeException("Error in mule client", e);
					} catch (final Exception e) {
						throw new RuntimeException("Error in provider endpoint", e);
					}

				} catch (Exception e) {
					response.setStatusCode("0999");
					response.setStatusText("Error indeterminado");
					response.setDetails("Error indeterminado");

					respuesta.setStatus(response);
					e.printStackTrace();
				}

			} else {
				response.setStatusCode("0020");
				response.setStatusText("La peticion no incluye todos los parametros obligatorios");
				response.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: servicioId, dispositivoId, plataformaId");

				respuesta.setStatus(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode("0020");
			response.setStatusText("Autentificiacion no valida o enviada.");
			response.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: Usuario, Password");
			respuesta.setStatus(response);
		}
		String respuestaJson = this.getJsonResponse(respuesta);
        String respuestaFormateada = respuestaJson.replace("\\\\\\", "\\");
		return respuestaFormateada;
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
