package es.minhap.misim.bus.webapp.restserviceprovider;

import java.io.StringReader;
import java.nio.charset.Charset;
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

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.misc.BASE64Decoder;

import com.google.gson.Gson;

import es.minhap.misim.bus.core.pojo.PeticionPayload;

/**
 * Clase que implementa la logica del servicio de registroUsuarioEnServicio
 * @author ralberoc
 *
 */
public class GestionServiciosPushImpl implements GestionServiciosPush {
	
	private static final Logger LOG = LoggerFactory.getLogger(GestionServiciosPushImpl.class);

	public static String ERROR_AUTENTIFICACION = "Error en Autentificacion - La clave no se corresponde con ninguna aplicacion";
	public static String ERROR_REQUESTTIMEOUT = "Error en Peticion - La peticion se ha caducado";
	public static String ERROR_PARAMETROS = "Error en Parametros de entrada";

	public static final String RECEPT_QUEUE = "vm://registro-usuario-en-servicio";
	public static final String SOAP_ACTION = "registro-usuario-en-servicio";

	private StringTokenizer tokenizer = null;
	private String username = null;
	private String password = null;

	@Context
	private HttpServletRequest request;

	@Context
	private ServletContext servletContext;

	/**
	 * Metodo que implementa la logica de la peticion de registro de usuarios en servicio
	 * @param map
	 * @return
	 */
	public String registroUsuarioEnServicio(MultivaluedMap<String,String>map) {

		RespuestaServiciosRegistrarUsuario respuesta = new RespuestaServiciosRegistrarUsuario();
		String decoded;
		try {
			// Get the Authorisation Header from Request
			String header = request.getHeader("authorization");

			// Header is in the format "Basic 3nc0dedDat4"
			// We need to extract data before decoding it back to original
			// string
			String data = header.substring(header.indexOf(" ") + 1);
			
			String idUsuario = (null != map.getFirst("IdUsuario")) ? map.getFirst("IdUsuario") : null;
			String idServicioMovil = (null != map.getFirst("IdServicioMovil")) ? map.getFirst("IdServicioMovil") : null;
			String accion = (null != map.getFirst("Accion")) ? map.getFirst("Accion") : null;
			String idDispositivo = (null != map.getFirst("IdDispositivo")) ? map.getFirst("IdDispositivo") : null;
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

			if ((null != username && !("").equals(username)) && (null != password && !("").equals(password)) && (null != idUsuario && !("").equals(idUsuario))
					&& (null != idServicioMovil && !("").equals(idServicioMovil)) && (null != idDispositivo && !("").equals(idDispositivo)) 
					&& (null != accion && !("").equals(accion))
					&& (null != uidDispositivo && !("").equals(uidDispositivo))
					&& (null != tokenSession && !("").equals(tokenSession))) {

				try {

					PeticionRegistroUsuarioEnServicio peticion = new PeticionRegistroUsuarioEnServicio();
					peticion.setUsuario(username);
					peticion.setPassword(password);
					peticion.setIdUsuario(idUsuario);
					peticion.setIdServicioMovil(idServicioMovil);
					peticion.setAccion(accion);
					peticion.setIdDispositivo(idDispositivo);
					peticion.setUidDispositivo(uidDispositivo);
					peticion.setTokenSession(tokenSession);

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

						JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaServiciosRegistrarUsuario.class);
						Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

						StringReader reader = new StringReader(xmlRespuesta);
						respuesta = (RespuestaServiciosRegistrarUsuario) unmarshaller.unmarshal(reader);

					} catch (final MuleException e) {
						throw new RuntimeException("Error in mule client", e);
					} catch (final Exception e) {
						throw new RuntimeException("Error in provider endpoint", e);
					}

				} catch (Exception e) {

					ResponseStatusType response = new ResponseStatusType();
					response.setStatusCode("999");
					response.setStatusText("Error indeterminado");
					response.setDetails("Error indeterminado");
				}

			} else {

				ResponseServRegUsuarioStatusType status = new ResponseServRegUsuarioStatusType();
				status.setStatusCode("2000");
				status.setStatusText("La petici&oacute;n no incluye todos los parametros obligatorios");
				status.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: Usuario, Password, IdUsuario, IdServicioMovil, Accion, IdDispositivo, UidDispositivo o TokenSession");
				respuesta.setStatus(status);
			}
		} catch (Exception e) {
			LOG.error("Error en GestionServiciosPushImpl", e);
			ResponseServRegUsuarioStatusType status = new ResponseServRegUsuarioStatusType();
			status.setStatusCode("3000");
			status.setStatusText("Autentificiaci&oacute;n no v&aacute;lida o enviada.");
			status.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: Usuario, Password");
			respuesta.setStatus(status);
		}

		String respuestaJson = this.getJsonResponse(respuesta);

		return respuestaJson;
	}
	
	/**
	 * Metodo que obtiene la cadena de un json almacenado en un objeto
	 * @param conResponse
	 * @return
	 */
	public String getJsonResponse(Object conResponse) {
		Gson gson = new Gson();

		String result = gson.toJson(conResponse);
		return result;
	}
}