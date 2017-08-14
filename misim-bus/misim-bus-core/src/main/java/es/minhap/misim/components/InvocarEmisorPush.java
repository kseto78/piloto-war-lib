package es.minhap.misim.components;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import javapns.communication.ProxyManager;
import javapns.notification.PushedNotification;
import javapns.notification.PushedNotifications;
import javapns.test.NotificationTest;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.json.JSONObject;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.model.Proveedor;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.servicios.ProveedorManager;
import es.minhap.misim.tranformers.GMCSendMessage;
import es.minhap.misim.tranformers.NotificacionDataRequest;
import es.minhap.misim.tranformers.PushNotificationSender;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServicios;
import es.minhap.plataformamensajeria.iop.services.usuariosplataformas.IRegistroUsuarioPushService;
import es.redsara.misim.misim_bus_webapp.respuesta.ResponseStatusType;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;

/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarEmisorPush implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(InvocarEmisorPush.class);

	@Resource
	IRegistroUsuarioPushService registroUsuarioPushImpl;
	
	@Resource(name="proveedorManager")
	private ProveedorManager proveedorManager; 

	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	
	@Autowired
	private QueryExecutorServicios servicios;


	private static final String OK_RESPONSE = "OK";

	private static final String KO_RESPONSE = "KO";

	private static final String DESC_ERROR = "ERROR ";
	
	private static final String COMPANY_GOOGLE = "Google";
	
	private static final String COMPANY_APPLE = "Apple";

	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {
		String company ="";
		LOG.debug("Empezando el proceso de invocación al emisor...");
		final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();

		String idMensaje = String.class.cast(eventContext.getMessage().getOutboundProperty("idMensaje"));
		Long proveedorId = eventContext.getMessage().getOutboundProperty("idProveedor");
				
		ResponseStatusType response = new ResponseStatusType();

		try {

			NotificacionDataRequest parametros = leerParametros(docOriginal);

			Proveedor p = proveedorManager.getProveedorById(proveedorId);
			// Obtiene la información del endpoint y la request
			PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

			String keystore = ps.getMessage("ruta.certificados", null, null, null);
			List<String> services = servicios.findServicioByMessageId(Long.parseLong(idMensaje));
			if (null != services && !services.isEmpty()) {
				String[] servicio = services.get(0).split("~");
				if (servicio.length > 0) {
					keystore = keystore + servicio[0] + "/";
				}
			}
			
			if (p.getNombre().startsWith(COMPANY_GOOGLE)){
				company = COMPANY_GOOGLE;
			}
			if (p.getNombre().startsWith(COMPANY_APPLE)){
				company = COMPANY_APPLE;
			}
			

			response = sendPushNotification(idMensaje, parametros, keystore, company);

		} catch (ModelException modelException) {
			LOG.error("Error leyendo los parámetros de la llamada: ", modelException);
			response.setStatusCode(KO_RESPONSE);
			response.setStatusText(KO_RESPONSE);
			response.setDetails(DESC_ERROR + modelException.getMensaje());

		} catch (Exception e) {
			LOG.error("Error leyendo los parámetros de la llamada: ", e);
			response.setStatusCode(KO_RESPONSE);
			response.setStatusText(KO_RESPONSE);
			response.setDetails(DESC_ERROR + e.toString());
		}

		try {
			Respuesta respuestaWS = new Respuesta();
			respuestaWS.setStatus(response);
			respuestaWS.setMessageId(idMensaje);

			SoapPayload.class.cast(eventContext.getMessage().getPayload()).setSoapMessage(
					XMLUtils.setPayloadFromObject(respuestaWS, Charset.forName("UTF-8"), Respuesta.class));

			eventContext.getMessage().setOutboundProperty("xmlRespuestaDirectaOperador", 
					XMLUtils.dom2xml(SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage()));
			
		} catch (Exception e) {
			LOG.error("Error al generar la respuesta: ", e);
			throw new ModelException(e.toString());
		}
		LOG.debug("Proceso de creación de invocación al emisor terminado.");

		return eventContext.getMessage();
	}

	private ResponseStatusType sendPushNotification(String idMensaje, NotificacionDataRequest parametros,
			String keystore, String company) {
		ResponseStatusType response = new ResponseStatusType();
		try {
			response = sendPUSH(company, keystore, parametros, idMensaje);
		} catch (ModelException e) {
			LOG.error("Error al realizar la llamada: ", e);
			response.setStatusCode(KO_RESPONSE);
			response.setStatusText(KO_RESPONSE);
			response.setDetails(DESC_ERROR + e.getMensaje());

		} catch (Exception e) {
			LOG.error("Error al realizar la llamada: ", e);
			response.setStatusCode(KO_RESPONSE);
			response.setStatusText(KO_RESPONSE);
			response.setDetails(DESC_ERROR + e.toString());
		}
		return response;
	}

	private NotificacionDataRequest leerParametros(Document docOriginal) throws Exception {

		NodeList nodeList = docOriginal.getElementsByTagName("NotificacionDataRequest");

		JAXBContext jc = JAXBContext.newInstance(NotificacionDataRequest.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		return (NotificacionDataRequest) unmarshaller.unmarshal((Node) nodeList.item(0));

	}

	private ResponseStatusType sendPUSH(String company, String keystore, NotificacionDataRequest notificacion,
			String idMensaje) throws ModelException, Exception {

		ResponseStatusType response = new ResponseStatusType();

		String respuesta = "";
		String statusCode = "";

		try {

			if ("Google".equals(company)) {

				GMCSendMessage sendToGoogle = new GMCSendMessage();
				respuesta = sendToGoogle.enviarGoogle(notificacion.getgCMApiKey(), notificacion.getToken(),
						notificacion.getUrl(), notificacion.getBadge(), notificacion.getCabecera(),
						notificacion.getCuerpo(), notificacion.getSound(), notificacion.getIcon(), idMensaje);
				LOG.info("respuesta de google ->" + respuesta);
				Integer mensajesfallidos = Integer.parseInt(respuesta.substring(respuesta.indexOf("\"failure\":") + 10,
						respuesta.indexOf("\"canonical_ids\":") - 1));
				JSONObject jsonObject = new JSONObject(respuesta);
				String canonicalIds = (!jsonObject.isNull("canonical_ids")) ? jsonObject.getString("canonical_ids")
						: null;
				String results = (!jsonObject.isNull("results")) ? jsonObject.getString("results") : null;
				if (mensajesfallidos > 0) {
					respuesta = "ERROR,Token no valido";
					statusCode = KO_RESPONSE;
				} else {
					statusCode = OK_RESPONSE;
				}

				if ((null != canonicalIds && "1".equals(canonicalIds))
						|| (null != results && results.contains("error"))) {
					respuesta = "ERROR,Token no valido";
					statusCode = KO_RESPONSE;

					registroUsuarioPushImpl.eliminarUsuario(notificacion.getToken().get(0));
				} else {
					statusCode = OK_RESPONSE;
				}

			} else if ("Apple".equals(company)) {
				boolean produccion = true;

				if (notificacion.getUrl().contains("sandbox")) {
					produccion = false;
				}

				// Probar a pasar directamente el keystore de sun sin necesidad
				// de la libreria
				File file = new File(keystore + notificacion.getRutaCertificadoAPNS());
				PushNotificationSender pushSender = new PushNotificationSender(file,
						notificacion.getPasswordCertificadoAPNS(), produccion);

				javapns.notification.PushNotificationPayload payload = null;

				if (notificacion.getCuerpo().contains("{")) {

					String data = notificacion.getCuerpo();
					data = data.replaceAll("\\\\\"", "\"");

					Integer badge = 0;

					if (notificacion.getBadge() != null) {
						badge = Integer.parseInt(notificacion.getBadge());
					}

					String sound = "default";
					if (notificacion.getSound() != null) {
						sound = notificacion.getSound();
					}

					data = data.replaceAll("}", ", \"idMensaje\":\"" + idMensaje + "\"}");
					LOG.info("InvocarEmisorPush: data: " + data);

					JSONObject jsonObject = new JSONObject(data);

					String title = (!jsonObject.isNull("title")) ? jsonObject.getString("title") : notificacion
							.getCabecera();
					String body = (!jsonObject.isNull("bodyMessage")) ? jsonObject.getString("bodyMessage") : "";

					String alert = "{\"aps\": {\"alert\":{\"title\":\"" + title + "\",\"body\":\"" + body
							+ "\",\"action-loc-key\":\"PLAY\"}" + ", \"badge\": \"" + badge + "\", \"sound\": \""
							+ sound + "\"},\"content\":" + data + "}";

					payload = new javapns.notification.PushNotificationPayload(alert);
				} else {
					String alert = "{\"aps\":{\"alert\":{\"title\":\"" + notificacion.getCabecera() + "\", \"body\":\""
							+ notificacion.getCuerpo() + "\", \"action-loc-key\":\"PLAY\"}}}";

					payload = new javapns.notification.PushNotificationPayload(alert);

					if (notificacion.getIcon() != null) {
						payload.addCustomDictionary("launch-image", notificacion.getIcon());
					}
					if (notificacion.getBadge() != null) {
						payload.addBadge(Integer.valueOf(notificacion.getBadge()));
					}
					if (notificacion.getSound() != null) {
						payload.addSound(notificacion.getSound());
					}
				}

				String[] token = new String[notificacion.getToken().size()];
				for (int i = 0; i < notificacion.getToken().size(); i++) {
					token[i] = notificacion.getToken().get(i);
				}

				if (System.getProperty("https.proxyHost") != null && System.getProperty("https.proxyPort") != null) {
					ProxyManager.setProxy(System.getProperty("https.proxyHost"), System.getProperty("https.proxyPort"));
				}
				PushedNotifications notifications = pushSender.sendPayload(payload, token);
				NotificationTest.printPushedNotifications(notifications);

				if (notifications != null && !notifications.isEmpty()) {

					List<PushedNotification> failedNotifications = PushedNotification
							.findFailedNotifications(notifications);
					List<PushedNotification> successfulNotifications = PushedNotification
							.findSuccessfulNotifications(notifications);
					int failed = failedNotifications.size();
					int successful = successfulNotifications.size();

					if (successful > 0 && failed == 0) {
						statusCode = OK_RESPONSE;
						respuesta = "All notifications pushed successfully (" + successfulNotifications.size() + ")";
					} else if (successful == 0 && failed > 0) {
						statusCode = KO_RESPONSE;
						respuesta = "All notifications failed (" + failedNotifications.size() + "):";
					} else if (successful == 0 && failed == 0) {
						statusCode = KO_RESPONSE;
						respuesta = "No notifications could be sent, probably because of a critical error";
					} else {
						statusCode = KO_RESPONSE;
						respuesta = "Some notifications failed (" + failedNotifications.size() + ")";
						respuesta = respuesta + " - Others succeeded (" + successfulNotifications.size() + "):";
					}

					for (int i = 0; i < notifications.size(); i++) {

						if (notifications.get(i).getException() != null) {
							respuesta = respuesta + " " + notifications.get(i).getException().getMessage();
						}
					}

				} else {

					statusCode = "KO";
					respuesta = "No notifications could be sent, probably because of a critical error";
				}
			}

			response.setStatusCode(statusCode);
			response.setStatusText(statusCode);
			response.setDetails(respuesta);
		} catch (Exception e) {
			LOG.error("Error al enviar un PUSH: ", e);
			throw e;
		}

		return response;
	}

	public QueryExecutorServicios getServicios() {
		return servicios;
	}

	public void setServicios(QueryExecutorServicios servicios) {
		this.servicios = servicios;
	}

}