package es.minhap.misim.components;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

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
import es.minhap.plataformamensajeria.iop.business.sendmail.ISendMessageService;
import es.minhap.plataformamensajeria.iop.business.thread.HiloEnviarMensajesPremium;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.services.envioPremium.IEnvioPremiumService;
import es.minhap.sim.model.TblDestinatariosMensajes;
import es.redsara.intermediacion.scsp.esquemas.v3.respuesta.Respuesta;

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
	
	@Resource(name="sendMessageService")
	private ISendMessageService sendMessageService;
	
	@Resource(name="TblMensajesManagerImpl")
	private TblMensajesManager tblMensajesManager;
	
	@Resource(name="TblDestinatariosMensajesManagerImpl")
	private TblDestinatariosMensajesManager tblDestinatariosMensajes;

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
			es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta resp = new es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta();
			resp.loadObjectFromXML(respuesta);
			
			Long idMensaje = (null != resp.getIdMensaje() && resp.getIdMensaje().length()>0)? Long.parseLong(resp.getIdMensaje()) : null;
			Long idLote = null;
			
			if (null != idMensaje){
				idLote = tblMensajesManager.getIdLoteByIdMensaje(idMensaje);
				eventContext.getMessage().setOutboundProperty("idLote", idLote);
//				levantarHilo(estadoPendiente, estadoAnulado, estadoIncidencia, resp, idMensaje, idLote);
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
				LOG.error("Error en la transmision: Error al obtener la respuesta del servicio Web especificado", e);
				throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
			}


		} catch (ModelException e) {
			LOG.error(e.getMensaje());
			throw new ModelException(e.getMensaje(), e.getCodigo());

		} catch (Exception e) {
			// Lanzar error
			LOG.error("Error en la transmision: Error de sistema Invocar Emisor", e);
			throw new ModelException("Error de sistema Invocar Emisor", 502);
		}

		LOG.debug("Proceso de creación de invocación al emisor terminado.");

		return eventContext.getMessage();
	}

	private void levantarHilo(String estadoPendiente, String estadoAnulado, String estadoIncidencia,
			es.minhap.plataformamensaferia.iop.beans.envioPremium.Respuesta resp, Long idMensaje, Long idLote) {
		String estadoActual = tblMensajesManager.getMensaje(Long.parseLong(resp.getIdMensaje())).getEstadoactual();
		List<TblDestinatariosMensajes> listaDestinatarios = tblDestinatariosMensajes.getDestinatarioMensajes(idMensaje);
		
		for (TblDestinatariosMensajes d : listaDestinatarios) {
			if (estadoActual.equals(estadoIncidencia) || estadoActual.equals(estadoAnulado) || estadoActual.equals(estadoPendiente)){
				HiloEnviarMensajesPremium hilo1 = new HiloEnviarMensajesPremium(sendMessageService, tblMensajesManager, idMensaje, idLote, d.getDestinatariosmensajes(), true, reloadableResourceBundleMessageSource);
				hilo1.start();
			}
		}
	}

	
	private SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage(new MimeHeaders(),
				new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
		return message;
	}
}