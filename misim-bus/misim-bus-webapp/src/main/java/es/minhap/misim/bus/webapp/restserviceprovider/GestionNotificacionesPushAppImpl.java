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
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.misc.BASE64Decoder;

import com.google.gson.Gson;

import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.ResponseNotificacionPushStatusType;
/**
 * Clase que implementa la logica de los metodos del sevicio gestionNotificacionesPushApp
 * @author ralberoc
 *
 */
@Service("gestionNotificacionesPushApp")
public class GestionNotificacionesPushAppImpl implements GestionNotificacionesPushApp {
	
	private static final Logger LOG = LoggerFactory.getLogger(GestionNotificacionesPushAppImpl.class);

	public static final String ERROR_AUTENTIFICACION = "Error en Autentificacion - La clave no se corresponde con ninguna aplicacion";
	public static final String ERROR_REQUESTTIMEOUT = "Error en Peticion - La peticion se ha caducado";
	public static final String ERROR_PARAMETROS = "Error en Parametros de entrada";

	public static final String RECEPT_QUEUE = "vm://gestion-notificaciones-push";
	public static final String SOAP_ACTION = "notificacionEstado";

	private String username = null;
	private String password = null;

	@Context
	private HttpServletRequest request;

	@Context
	private ServletContext servletContext;

	/**
	 * Metodo que implementa la logica de la peticion de notificacion de estado
	 * @param map
	 * @return
	 */
	public String notificacionEstado(MultivaluedMap<String,String>map) {

		RespuestaNotificacionesPush respuesta = new RespuestaNotificacionesPush();
		String decoded;
		try {
			
			StringTokenizer tokenizer = null;
			
			// Get the Authorisation Header from Request
			String header = request.getHeader("authorization");

			String idUsuario = (null != map.getFirst("IdDispositivo")) ? map.getFirst("IdDispositivo") : null;
			String status = (null != map.getFirst("Status")) ? map.getFirst("Status") : null;
			String notificacionId = (null != map.getFirst("NotificacionId")) ? map.getFirst("NotificacionId") : null;
			String uidDispositivo = (null != map.getFirst("UidDispositivo")) ? map.getFirst("UidDispositivo") : null;
			String tokenSession = (null != map.getFirst("TokenSession")) ? map.getFirst("TokenSession") : null;

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

			if ((null != username && !("").equals(username)) && (null != password && !("").equals(password))
					&& (null != idUsuario && !("").equals(idUsuario))
					&& (null != uidDispositivo && !("").equals(uidDispositivo))
					&& (null != tokenSession && !("").equals(tokenSession))) {

				try {

					PeticionNotificacionEstado peticion = new PeticionNotificacionEstado();
					peticion.setUsuario(username);
					peticion.setPassword(password);
					peticion.setNotificacionId(notificacionId);
					peticion.setStatus(status);
					peticion.setIdUsuario(idUsuario);
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

						NodeList nodoRespuesta = respuestaSOAP.getElementsByTagName("RespuestaNotificacionesPush");
						String xmlRespuesta = XMLUtils.nodeToString(nodoRespuesta.item(0));

						JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaNotificacionesPush.class);
						Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

						StringReader reader = new StringReader(xmlRespuesta);
						respuesta = (RespuestaNotificacionesPush) unmarshaller.unmarshal(reader);

					} catch (final MuleException e) {
						throw new RuntimeException("Error in mule client", e);
					} catch (final Exception e) {
						throw new RuntimeException("Error in provider endpoint", e);
					}

				} catch (Exception e) {

					ResponseNotificacionPushStatusType response = new ResponseNotificacionPushStatusType();

					response.setStatusCode("0999");
					response.setStatusText("Error indeterminado");
					response.setDetails("Error indeterminado");

					respuesta.setStatus(response);
				}

			} else {

				ResponseNotificacionPushStatusType response = new ResponseNotificacionPushStatusType();

				response.setStatusCode("3001");
				response.setStatusText("La peticion no incluye todos los parametros obligatorios");
				response.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: Usuario, Password, IdDispositivo, UidDispositivo o TokenUsuario");

				respuesta.setStatus(response);
			}
		} catch (Exception e) {
			LOG.error("Error en GestionNotificacionesPushAppImpl", e);
			ResponseNotificacionPushStatusType response = new ResponseNotificacionPushStatusType();
			response.setStatusCode("3002");
			response.setStatusText("Autentificiacion no valida o enviada.");
			response.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: Usuario, Password");
			respuesta.setStatus(response);
		}

		return this.getJsonResponse(respuesta);

	}

	/**
	 * Metodo que obtiene la cadena de un json almacenado en un objeto
	 * @param conResponse
	 * @return
	 */
	public String getJsonResponse(Object conResponse) {
		Gson gson = new Gson();

		return gson.toJson(conResponse);
		
	}
}