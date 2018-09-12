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
import es.minhap.plataformamensajeria.iop.beans.lotes.PeticionXMLBean;
import es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensajes;
import es.minhap.plataformamensajeria.iop.business.sendmail.ISendMessageService;
//import es.minhap.plataformamensajeria.iop.business.thread.HiloEnviarEmailPremium;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.services.envioLotes.IEnvioLotesMensajesService;
import es.minhap.sim.model.TblDestinatariosMensajes;
import es.minhap.sim.model.TblServicios;
import es.redsara.intermediacion.scsp.esquemas.v3.respuesta.Respuesta;

/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarEnvioEmail implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(InvocarEnvioEmail.class);
	
	@Resource
	private IEnvioLotesMensajesService envioLotesMensajesImpl;
	
	@Resource(name="sendMessageService")
	private ISendMessageService sendMessageService;
	
	@Resource(name="TblMensajesManagerImpl")
	private TblMensajesManager tblMensajesManager;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	@Resource(name="TblDestinatariosMensajesManagerImpl")
	private TblDestinatariosMensajesManager tblDestinatariosMensajes;
	
	@Resource
	private TblServiciosManager serviciosManager;

	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {

		LOG.debug("Empezando el proceso de invocación del enviador...");
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		
		try{

			final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
			if(LOG.isInfoEnabled()) {
				LOG.info("REQUEST: " + XMLUtils.dom2xml(docOriginal));
			}

			NodeList peticion = docOriginal.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/peticion", "Peticion");
			String xmlPeticion = XMLUtils.nodeToString(peticion.item(0));
			
			PeticionXMLBean peticionXML = new PeticionXMLBean();
			peticionXML.loadObjectFromXML(xmlPeticion);
			
			//Recuperamos el servicio para combrobar si es premuium
			Long idServicio = Long.parseLong(peticionXML.getServicio());
			TblServicios tblServicios = serviciosManager.getServicio(idServicio);
			boolean esPremium = ((tblServicios != null && tblServicios.getPremium())?true:false);
			
			ps = new PropertiesServices(reloadableResourceBundleMessageSource);
			String respuesta = envioLotesMensajesImpl.enviarLotesEmail(peticionXML, ps);
			
			//Recogemos la respuesta y la pasamos a object
			es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Respuesta respuestaEnvio = new es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Respuesta();
			respuestaEnvio = respuestaEnvio.loadObjectFromXMLWithList(respuesta);
			
			Document doc = XMLUtils.xml2doc(respuesta, Charset.forName("UTF-8"));
			String respuestaCompleta = XMLUtils.createSOAPFaultString((Node) doc.getDocumentElement());
			
			String utilizarActiveMqEmail = ps.getMessage("constantes.ENVIO_ACTIVEMQEMAIL", null,"S");
			
			//Utilizamos el hilo según si es Premium y según configuracion properties
			if(esPremium && !"S".equals(utilizarActiveMqEmail)){
				levantarHilo(respuestaEnvio);
			}
			
			NodeList nodoLoteId = doc.getElementsByTagName("idLote");
			
			if(nodoLoteId!=null && nodoLoteId.item(0)!=null) {
				String idLote=nodoLoteId.item(0).getTextContent();
				eventContext.getMessage().setOutboundProperty("idLote", idLote);
			}	
			
			NodeList nodoMensajeId = doc.getElementsByTagName("idMensaje");
			// Se agrega el idMensaje para los casos donde es unico
			if(nodoMensajeId!=null && nodoMensajeId.item(0)!=null && nodoMensajeId.getLength() == 1) {
				String idMensaje=nodoMensajeId.item(0).getTextContent();
				eventContext.getMessage().setOutboundProperty("idMensaje", idMensaje);
			}
			
			SOAPMessage responseMessage=getSoapMessageFromString(respuestaCompleta);
			
	        try{
				
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
			    
			    if(LOG.isInfoEnabled()) {
					LOG.info("RESPONSE: " + XMLUtils.dom2xml(XMLUtils.soap2dom(responseMessage)));
				}
			    
				soapPayload.setSoapAction(initPayload.getSoapAction());
				soapPayload.setSoapMessage(XMLUtils.soap2dom(responseMessage));
		
				eventContext.getMessage().setPayload(soapPayload);
				
			}catch(Exception e){
				//Lanzar error
				LOG.error("Error en la transmisión: Error al obtener la respuesta del servicio Web especificado", e);
				throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
			}

		}catch (ModelException e){
			
			throw new ModelException(e.getMensaje(), e.getCodigo());
			
		}catch(Exception e){
			//Lanzar error
			LOG.error("Error en la transmisión: Error de sistema Invocar Emisor", e);
			throw new ModelException("Error de sistema Invocar Emisor", 502);
		}

		LOG.debug("Proceso de creación de invocación al emisor terminado.");

		return eventContext.getMessage();
	}

	private SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
		return message;
	}
	
	private void levantarHilo(es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Respuesta respuesta) {
		
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String estadoPendiente = ps.getMessage("constantes.ESTADO_PENDIENTE", null);
		String estadoAnulado = ps.getMessage("constantes.ESTADO_ANULADO", null);
		String estadoIncidencia = ps.getMessage("constantes.ESTADO_INCIDENCIA", null);
		Long idLote = null;
		
		if(null!=respuesta){
			if(respuesta.getLote()!=null && respuesta.getLote().getIdLote()!=null){
				idLote = Long.parseLong(respuesta.getLote().getIdLote());
			
				if(idLote!=null && respuesta.getMensajes()!=null && !respuesta.getMensajes().isEmpty()){
					for(Mensajes mensajes : respuesta.getMensajes()){
						if(mensajes != null && mensajes.getMensaje()!=null && mensajes.getMensaje().getIdMensaje()!=null){
							String estadoActual = tblMensajesManager.getMensaje(Long.parseLong(mensajes.getMensaje().getIdMensaje())).getEstadoactual();
							List<TblDestinatariosMensajes> listaDestinatarios = tblDestinatariosMensajes.getDestinatarioMensajes(Long.parseLong(mensajes.getMensaje().getIdMensaje()));
							for (TblDestinatariosMensajes destinatario : listaDestinatarios) {
								if (estadoActual.equals(estadoIncidencia) || estadoActual.equals(estadoAnulado) || estadoActual.equals(estadoPendiente)){
//									HiloEnviarEmailPremium hilo1 = new HiloEnviarEmailPremium(sendMessageService, tblMensajesManager, Long.parseLong(mensajes.getMensaje().getIdMensaje()), idLote, destinatario.getDestinatariosmensajes(), true, reloadableResourceBundleMessageSource);
//									hilo1.start();
								}
							}
						}		
					}
				}
			}
		}
	}
}