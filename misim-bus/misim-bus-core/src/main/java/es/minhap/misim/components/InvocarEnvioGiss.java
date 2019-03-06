package es.minhap.misim.components;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
import es.minhap.plataformamensajeria.iop.beans.EnvioGISSXMLBean;
import es.minhap.plataformamensajeria.iop.business.sendmail.ISendMessageService;
import es.minhap.plataformamensajeria.iop.business.thread.HiloEnviarMensajesExclusivo;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.services.envioPremium.IEnvioPremiumGISSService;
import es.minhap.sim.model.TblDestinatariosMensajes;
import es.redsara.intermediacion.scsp.esquemas.v3.respuesta.Respuesta;

//import es.minhap.misim.components.envio.EnvioEmailXMLBean;
/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarEnvioGiss implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(InvocarEnvioGiss.class);

	@Resource
	IEnvioPremiumGISSService envioPremiumGISSServiceImpl;
	
	@Resource(name = "TblMensajesManagerImpl")
	private TblMensajesManager tblMensajesManager;

	@Resource(name = "TblDestinatariosMensajesManagerImpl")
	private TblDestinatariosMensajesManager tblDestinatariosMensajes;

	@Resource(name = "sendMessageService")
	private ISendMessageService sendMessageService;

	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
		
	PropertiesServices ps = null;

	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {

		LOG.debug("Empezando el proceso de invocación del enviador...");
		ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String usuarioGISS = ps.getMessage("giss.usuario.sms", null, null, null);
		String passwordGISS = ps.getMessage("giss.contrasena.sms", null, null, null);
		Integer idServicioGISS = new Integer(ps.getMessage("giss.servicio.sms.premium", null, null, null));
		String usuarioMISIM = ps.getMessage("misim.aplicacion.giss.usuario.sms", null, null, null);
		String passwordMISIM = ps.getMessage("misim.aplicacion.giss.contrasena.sms", null, null, null);
		String codCorrecto =  ps.getMessage("clave.respuestaOK.GISS", null, null, null); 

		String estadoPendiente = ps.getMessage("constantes.ESTADO_PENDIENTE", null);
		
		String utilizarActiveMq = ps.getMessage("constantes.ENVIO_ACTIVEMQ", null,"S");

		try {
			final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload())
					.getSoapMessage();
			
			if(LOG.isInfoEnabled()){
				LOG.info("REQUEST: " + XMLUtils.dom2xml(docOriginal));}

			NodeList peticion = docOriginal.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/peticion",
					"Peticion");

			String xmlPeticion = XMLUtils.nodeToString(peticion.item(0));
			EnvioGISSXMLBean envioGISSXML = new EnvioGISSXMLBean();
			envioGISSXML.loadObjectFromXML(xmlPeticion);
			String respuesta = envioPremiumGISSServiceImpl.enviarSMSGISS(envioGISSXML, usuarioGISS, passwordGISS,
					idServicioGISS, usuarioMISIM, passwordMISIM);
			if(respuesta.contains(codCorrecto)){
				List<Long> listaMensajes = new ArrayList<>();
				if (null != envioGISSXML.getIdExterno()){
					listaMensajes = tblDestinatariosMensajes.getIdMensajeByIdExterno(envioGISSXML.getIdExterno());
				}
				
				for (Long idMensaje : listaMensajes) {
					Long idLote = tblMensajesManager.getIdLoteByIdMensaje(idMensaje);
					eventContext.getMessage().setOutboundProperty("idLote", idLote);
					
					//utilizamos el hilo según configuracion properties
					if (!"S".equals(utilizarActiveMq)){
						levantarHilo(estadoPendiente, idMensaje, idLote);
					}
				}

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
				
				if(LOG.isInfoEnabled()){
					LOG.info("RESPONSE: " + XMLUtils.dom2xml(XMLUtils.soap2dom(responseMessage)));}
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

	private SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage(new MimeHeaders(),
				new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
		return message;
	}

	private void levantarHilo(String estadoPendiente, Long idMensaje, Long idLote) {
		String estadoActual = tblMensajesManager.getMensaje(idMensaje).getEstadoactual();
		List<TblDestinatariosMensajes> listaDestinatarios = tblDestinatariosMensajes.getDestinatarioMensajes(idMensaje);

		for (TblDestinatariosMensajes d : listaDestinatarios) {
			if (estadoActual.equals(estadoPendiente)) {
				HiloEnviarMensajesExclusivo hilo1 = new HiloEnviarMensajesExclusivo(sendMessageService, tblMensajesManager,
						idMensaje, idLote, d.getDestinatariosmensajes(), false, reloadableResourceBundleMessageSource);
				hilo1.start();
			}
		}
	}


}