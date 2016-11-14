package es.minhap.misim.components;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.annotation.Resource;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.plataformamensajeria.iop.beans.EnvioAEATXMLBean;
import es.minhap.plataformamensajeria.iop.services.envioPremium.IEnvioPremiumService;
import es.redsara.intermediacion.scsp.esquemas.v3.respuesta.Respuesta;

//import es.minhap.misim.components.envio.EnvioEmailXMLBean;
/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarEnvio001 implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(InvocarEnvio001.class);

	@Resource
	IEnvioPremiumService envioPremiumAEATService;

	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	PropertiesServices ps = null;

	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {

		LOG.debug("Empezando el proceso de invocación del enviador...");
		ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String usuarioAEAT = ps.getMessage("aeat.usuario.sms", null, null, null);
		String passwordAEAT = ps.getMessage("aeat.contrasena.sms", null, null, null);
		Integer idServicioAEAT = new Integer(ps.getMessage("aeat.servicio.sms.premium", null, null, null));
		String usuarioMISIM = ps.getMessage("misim.aplicacion.aeat.usuario.sms", null, null, null);
		String passwordMISIM = ps.getMessage("misim.aplicacion.aeat.contrasena.sms", null, null, null);
		Integer reintentos = new Integer(ps.getMessage("aeat.reintentos.sms.premium", null, null, null));

		// String usuarioAEAT = "pruebasSIMdes";
		// String passwordAEAT = "pruebasSIMdes";
		// Integer idServicioAEAT = 1602;
		String COMPROBACION_ENVIO = "&lt;![CDATA[";
		String COMPROBACION_URL = " | ";

		try {
			final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload())
					.getSoapMessage();

			LOG.info("REQUEST PREMIUM: " + XMLUtils.dom2xml(docOriginal));

			NodeList peticion = docOriginal.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/peticion",
					"Peticion");

			String xmlPeticion = XMLUtils.nodeToString(peticion.item(0));
			EnvioAEATXMLBean envioAEATXML = new EnvioAEATXMLBean();
			envioAEATXML.loadObjectFromXML(xmlPeticion);
			String respuesta = envioPremiumAEATService.enviarSMSPremium(envioAEATXML, usuarioAEAT, passwordAEAT,
					idServicioAEAT, usuarioMISIM, passwordMISIM, reintentos);
			eventContext.getMessage().setOutboundProperty("mensaje_AEAT", "S");
			if (null != respuesta && respuesta.contains(COMPROBACION_ENVIO)) {
				respuesta = adaptarRespuestaEnvioSMS(respuesta);
				eventContext.getMessage().setOutboundProperty("enviar_mensaje_premium_001", "S");

			} else {
				eventContext.getMessage().setOutboundProperty("enviar_mensaje_premium_001", "N");
			}

			if (null != respuesta && respuesta.contains(COMPROBACION_URL)) {
				respuesta = quitarURLRespuesta(respuesta);
			}
			Document doc = XMLUtils.xml2doc(respuesta, Charset.forName("UTF-8"));
			String respuestaCompleta = XMLUtils.createSOAPFaultString((Node) doc.getDocumentElement());

			SOAPMessage responseMessage = getSoapMessageFromString(respuestaCompleta);
			try {

				SoapPayload<?> initPayload = eventContext.getMessage().getPayload(SoapPayload.class);
				SoapPayload<?> soapPayload = null;
				if (responseMessage.getSOAPBody().hasFault()) {
					// La respuesta es un SOAP Fault
					soapPayload = new SoapPayload<Object>();
					eventContext.getMessage().setOutboundProperty("SOAPFault", true);
				} else {
					// La respuesta no es un SOAP Fault
					soapPayload = new SoapPayload<Respuesta>();
					eventContext.getMessage().setOutboundProperty("SOAPFault", false);
				}

				LOG.info("RESPONSE PREMIUM: " + XMLUtils.dom2xml(XMLUtils.soap2dom(responseMessage)));
				soapPayload.setSoapAction(initPayload.getSoapAction());
				soapPayload.setSoapMessage(XMLUtils.soap2dom(responseMessage));

				eventContext.getMessage().setPayload(soapPayload);

			} catch (Exception e) {
				// Lanzar error
				LOG.error("Error en la transmision: Error al obtener la respuesta del servicio Web especificado", e);
				throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
			}
			// }

		} catch (ModelException e) {

			throw new ModelException(e.getMensaje(), e.getCodigo());

		} catch (Exception e) {
			// Lanzar error
			LOG.error("Error en la transmision: Error de sistema Invocar Emisor", e);
			throw new ModelException("Error de sistema Invocar Emisor", 502);
		}

		LOG.debug("Proceso de creación de invocación al emisor terminado.");

		return eventContext.getMessage();
	}

	/**
	 * @param usuarioMISIM
	 * @param passwordMISIM
	 * @param respuesta
	 * @return
	 */
	private String adaptarRespuestaEnvioSMS(String respuesta) {
		if (respuesta.contains("&lt;![CDATA[")) {
			respuesta = respuesta.substring(respuesta.indexOf("&lt;![CDATA[", 0) + 12, respuesta.indexOf("]]&gt;"));
		}
		respuesta = respuesta.replaceAll("&gt;", ">").replaceAll("&lt;", "<");
		// respuesta = respuesta.substring(0,
		// respuesta.indexOf(" | ")).concat(respuesta.substring(respuesta.indexOf("</Details>"),respuesta.length()));
		// respuesta.indexOf("</Details>");
		respuesta = respuesta.replaceAll("<Peticion>",
				"<Peticion xmlns=\"http://misim.redsara.es/misim-bus-webapp/peticion\">");

		// System.out.println("PETICION DE ENVIO SMS: " + respuesta);
		return respuesta;
	}

	private String quitarURLRespuesta(String respuesta) {
		respuesta = respuesta.substring(0, respuesta.indexOf(" | ")).concat(
				respuesta.substring(respuesta.indexOf("</Details>"), respuesta.length()));
		// System.out.println("PETICION DE ENVIO SMS: " + respuesta);
		return respuesta;
	}

	private SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage(new MimeHeaders(),
				new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
		return message;
	}
}