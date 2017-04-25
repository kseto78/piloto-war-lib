package es.minhap.misim.components;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleContext;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.plataformamensaferia.iop.beans.envioPremium.PeticionNotificacionEstadoSMS;
import es.minhap.plataformamensajeria.iop.beans.RecepcionEstadoSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.RecibirSMSRequest;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.services.recepcion.IRecepcionMensajesService;
import es.minhap.plataformamensajeria.iop.services.recepcionEstadoSMS.IRecepcionEstadoSMSService;
import es.minhap.plataformamensajeria.iop.services.seguimiento.ISeguimientoMensajesService;
import es.redsara.intermediacion.scsp.esquemas.v3.respuesta.Respuesta;
import es.redsara.misim.misim_bus_webapp.respuesta.ResponseStatusType;
//import es.minhap.misim.components.envio.EnvioEmailXMLBean;
/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarRecepcion implements Callable {

	public static final String RECEPT_QUEUE = "vm://recepcion-AEAT";
	
	protected static final String SOAP_ACTION = "ACUSE_AEAT";
	
	private static final String HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"> <soapenv:Header/> <soapenv:Body>";    
	
	private static final String FOOTER = "</soapenv:Body></soapenv:Envelope>";
	
	
	private static final String APLICACION = "AEAT";
	private static final String ESCLAVE = "esClave";
	
	
	private static final Logger LOG = LoggerFactory.getLogger(InvocarRecepcion.class);
	
	@Resource
	IRecepcionMensajesService recepcionMensajesImpl;
	
	@Resource
	IRecepcionEstadoSMSService recepcionEstadoSMSImpl;
	
	@Resource
	ISeguimientoMensajesService seguimientoMensajesImpl;
	
    @Resource
    TblMensajesManager mensajesManager;
    
    @Resource
    TblDestinatariosMensajesManager destinatariosMensajesManager;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	PropertiesServices ps = null;

	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {

		LOG.debug("Empezando el proceso de invocación del enviador...");
		
		ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String errorClave = ps.getMessage("clave.ERRORCLAVE.AEAT", null, "[ERROR-CL@VE]:");
		String ok = ps.getMessage("clave.respuestaOK.AEAT", null, "OK");

		String respuesta="";
		String respuestaFinal = null;
		try{

			String soapAction=SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapAction();
			final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
			
			if(("recepcionSMS").equals(soapAction)){
			
				LOG.info("REQUEST RECEPCION: " + XMLUtils.dom2xml(docOriginal));
				
		        try{
		        	
					NodeList recibirSMSRequest = docOriginal.getElementsByTagName("recibirSMSRequest");
					String xmlPeticion2 = XMLUtils.nodeToString(recibirSMSRequest.item(0));
					
					RecibirSMSRequest recepcionBean = new RecibirSMSRequest();
					recepcionBean.loadObjectFromXML(xmlPeticion2);
		
					respuesta=recepcionMensajesImpl.recibirSMSXML(recepcionBean);
					
					Document doc = XMLUtils.xml2doc(respuesta, Charset.forName("UTF-8"));
					
					NodeList nodoLoteId = doc.getElementsByTagName("idLote");
					
					if(nodoLoteId!=null && nodoLoteId.item(0)!=null) {
						String idLote=nodoLoteId.item(0).getTextContent();
						eventContext.getMessage().setOutboundProperty("idLote", idLote);
					}
					
					SOAPMessage responseMessage=getSoapMessageFromString(respuesta);
		        	
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
				    
				   
					soapPayload.setSoapAction(initPayload.getSoapAction());
					soapPayload.setSoapMessage(XMLUtils.soap2dom(responseMessage));
			
					eventContext.getMessage().setPayload(soapPayload);
					
					eventContext.getMessage().setOutboundProperty("xmlRespuestaDirectaOperador", XMLUtils.dom2xml(soapPayload.getSoapMessage()));
					
					if(LOG.isInfoEnabled()){
			        	LOG.info("RESPONSE: " + respuesta);
			        }	
					
				}catch(Exception e){
					//Lanzar error
					LOG.error("Error en la transmisión: Error al obtener la respuesta del servicio Web especificado", e);
					throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
				}
		        
			}else if (("notificacionSMS").equals(soapAction)){
				
				LOG.info("REQUEST NOTIFICACION: " + XMLUtils.dom2xml(docOriginal));
				
				try{
					
					NodeList recibirSMSRequest = docOriginal.getElementsByTagName("recepcionEstadoSMSXMLBean");
					
					respuestaFinal = null;
					
					if(recibirSMSRequest!=null){
						for (int i=0; i< recibirSMSRequest.getLength();i++){
							
							
							String xmlPeticion2 = XMLUtils.nodeToString(recibirSMSRequest.item(i));
							
							RecepcionEstadoSMSXMLBean recepcionBean = new RecepcionEstadoSMSXMLBean();
							recepcionBean.loadObjectFromXML(xmlPeticion2);
							
							respuesta=recepcionEstadoSMSImpl.recibirEstadoSMSXML(recepcionBean);
							
							Document doc = XMLUtils.xml2doc(respuesta, Charset.forName("UTF-8"));
							
							NodeList nodoLoteId = doc.getElementsByTagName("idLote");
							
							if(nodoLoteId!=null && nodoLoteId.item(0)!=null) {
								String idLote=nodoLoteId.item(0).getTextContent();
								eventContext.getMessage().setOutboundProperty("idLote", idLote);
							}
							
							//messageId = recepcionBean.getMensajeId();
							if(respuesta.contains(",")){ ////es AEAT
								String sender = recepcionBean.getSender();
								String recipient = recepcionBean.getRecipient();
								String endpoint = respuesta.substring(respuesta.indexOf(",")+1,respuesta.indexOf(" | "));
								String messageId = respuesta.substring(respuesta.indexOf(" | ")+3,respuesta.indexOf("</Details>"));

								respuesta = quitarURLRespuesta(respuesta);
							//	String messageStatus = respuesta.substring(respuesta.indexOf("<Details>"), respuesta.indexOf("</Details>"));
								String statusText = respuesta.substring(respuesta.indexOf("<StatusText>")+12, respuesta.indexOf("</StatusText>"));
								//Tenemos la respuesta de AEAT por si hay que utilizarla para algún reintento de la recepcion de estado.
//								String respuestaAEAT = envioAEAT(eventContext,messageId,endpoint,sender,recipient, statusText);
								SOAPMessage soapMessage = envioAEAT(eventContext,messageId,endpoint,sender,recipient, statusText);
								
								String respuestaAEAT = "";
								if (null != soapMessage){
								 respuestaAEAT = pharseMessageToString(soapMessage);
								 if (!respuestaAEAT.contains(ok)){//error si son al invocar, auditar, .... Si es en InvocarEnvioRecepcionAEAT se lanza desde esa clase
										Document document = soapMessage.getSOAPBody().extractContentAsDocument();
										NodeList nodoRespuesta = document.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/respuesta", "StatusText");//("ns2:Respuesta");
										NodeList nodoRespuesta2 = document.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/respuesta", "Details");
										String xmlRespuesta = nodoRespuesta.item(0).getFirstChild().getNodeValue();
										String xmlRespuesta2 = (null != nodoRespuesta2.item(0) )? nodoRespuesta2.item(0).getFirstChild().getNodeValue() : "";
										
										LOG.error(errorClave + xmlRespuesta + " " +xmlRespuesta2);
									}
								}else{
									LOG.error(errorClave + "Error notificar estado AEAT " );
								}
								
							
								LOG.info("RESPONSE ACC_RECIBO AEAT: " + respuestaAEAT);
								if (respuestaFinal==null){
									respuestaFinal=respuesta;
								}else if (!respuesta.contains(ok)){
									respuestaFinal=respuesta;
								}
//								//si hay error al comunicar con AEAT y queremos modificar la respuesta
//								if (respuestaAEAT.contains(ETIQUETA_FIN_STATUSTEXT) && respuestaAEAT.contains(CADENA_AEAT_CORRECTA)){
//									respuestaFinal = respuesta;
//								}else{
//									respuestaFinal = formatResponseErrorAEAT(respuesta);				
//								}
										
							}else{
							
								if (respuestaFinal==null){
									respuestaFinal=respuesta;
								}else if (!respuesta.contains("<StatusCode>1000</StatusCode>")){
									respuestaFinal=respuesta;
								}
							}

						}
					}
					
					SOAPMessage responseMessage=getSoapMessageFromString(respuestaFinal);
		        	
					SoapPayload<?> initPayload = eventContext.getMessage().getPayload(SoapPayload.class);
					SoapPayload<?> soapPayload = null;
				    if (responseMessage.getSOAPBody().hasFault()) {
				    	// La respuesta es un SOAP Fault
						soapPayload = new SoapPayload<Object>();
						eventContext.getMessage().setOutboundProperty("SOAPFault", true);
			        } else {
			        	// La respuesta no es un SOAP Fault
			        	soapPayload = new SoapPayload<Object>();
						eventContext.getMessage().setOutboundProperty("SOAPFault", false);
			        }
				    
				   
					soapPayload.setSoapAction(initPayload.getSoapAction());
					soapPayload.setSoapMessage(XMLUtils.soap2dom(responseMessage));
			
					eventContext.getMessage().setPayload(soapPayload);
					
					eventContext.getMessage().setOutboundProperty("xmlRespuestaDirectaOperador", XMLUtils.dom2xml(soapPayload.getSoapMessage()));
					
					if(LOG.isInfoEnabled()){
			        	LOG.info("RESPONSE: " + respuestaFinal);
			        }	
				}catch(Exception e){
					//Lanzar error
					if(eventContext.getMessage().getOutboundProperty(ESCLAVE)){
						LOG.error(errorClave +"Error en la transmisión: Error al obtener la respuesta del servicio Web especificado");
					}
					LOG.error("Error en la transmisión: Error al obtener la respuesta del servicio Web especificado", e);
					throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
				}
			}
	
		}catch (ModelException e){
			if(eventContext.getMessage().getOutboundProperty(ESCLAVE)){
				LOG.error(errorClave +e.getMensaje());
			}
			throw new ModelException(e.getMensaje(), e.getCodigo());
			
		}catch(Exception e){
			//Lanzar error
			if(eventContext.getMessage().getOutboundProperty(ESCLAVE)){
				LOG.error(errorClave +"Error en la transmisión: Error de sistema Invocar Emisor");
			}
			LOG.error("Error en la transmisión: Error de sistema Invocar Emisor", e);
			throw new ModelException("Error de sistema Invocar Emisor", 502);
		}

		LOG.debug("Proceso de creación de invocación al emisor terminado.");

		return eventContext.getMessage();
	}
	
	private String quitarURLRespuesta(String respuesta) {
		if (respuesta.contains(",")){
			respuesta = respuesta.substring(0, respuesta.indexOf(",")).concat(respuesta.substring(respuesta.indexOf("</Details>"),respuesta.length()));
		}
		return respuesta;
	}

	private SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
		return message;
	}

	public SOAPMessage envioAEAT(final MuleEventContext eventContext,String idMensaje,String endpointUrl,String sender, String recipient, String statusText){
		LOG.debug("Empezando el proceso de invocación al emisor...");
		String respuestaIncompleta ="";
		SOAPMessage soapMessage = null;
//		String xml = null;
		try{
			String usuarioAEAT = new String(ps.getMessage("aeat.usuario.sms", null, null, null));
			String passwordAEAT = new String(ps.getMessage("aeat.contrasena.sms", null, null, null));
			Integer idServicioAEAT = new Integer(ps.getMessage("aeat.servicio.sms.premium", null, null, null));
			respuestaIncompleta=seguimientoMensajesImpl.consultarEstadoAEAT(idServicioAEAT, new Integer(idMensaje),null, usuarioAEAT, passwordAEAT,sender,recipient, statusText);
			
			PeticionNotificacionEstadoSMS petNotAEAT = new PeticionNotificacionEstadoSMS();
			petNotAEAT.loadObjectFromXML(respuestaIncompleta);

			soapMessage = sendMessage(eventContext.getMuleContext(),petNotAEAT);
//			xml = pharseMessageToString(soapMessage);
			
		}catch(IllegalArgumentException e){
			soapMessage = generateSOAPFaultEnvio(soapMessage, "Error al generar el cliente: Endpoint no encontrado en el WSDL");
			LOG.error("Error al generar el cliente: Endpoint no encontrado en el WSDL",e);
			LOG.error("La peticion que se envia es :" + respuestaIncompleta);
		}catch (SOAPFaultException e) {		
			soapMessage = generateSOAPFaultEnvio(soapMessage, "Error en la transmisión: Error al contactar con el servicio Web especificado");
			LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado",e);
			LOG.error("La peticion que se envia es :" + respuestaIncompleta);
		}catch(WebServiceException e){
			
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(SocketTimeoutException.class)){
					soapMessage = generateSOAPFaultEnvio(soapMessage, "Error en la transmisión: Comunicación sin respuesta");
					LOG.error("Error en la transmisión: Comunicación sin respuesta",e);
					LOG.error("La peticion que se envia es :" + respuestaIncompleta);
				}else{
					soapMessage = generateSOAPFaultEnvio(soapMessage, "Error en la transmisión: Error al contactar con el servicio Web especificado");
					LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
					LOG.error("La peticion que se envia es :" + respuestaIncompleta);
				}
			}else{
				soapMessage = generateSOAPFaultEnvio(soapMessage, "Error en la transmisión: Error al contactar con el servicio Web especificado");
				LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
				LOG.error("La peticion que se envia es :" + respuestaIncompleta);
			}
		}catch(Exception e){
			soapMessage = generateSOAPFaultEnvio(soapMessage, "Error en la transmisión: Error de sistema Invocar Emisor");
			LOG.error("Error en la transmisión: Error de sistema Invocar Emisor", e);
			LOG.error("La peticion que se envia es :" + respuestaIncompleta);
		}
		
		LOG.debug("Proceso de creación de invocación al emisor terminado.");
	return soapMessage;
	}
	
	
	/**
	 * Genera el SOAP Fault Message
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected static final SOAPMessage generateSOAPFaultEnvio(SOAPMessage request, String descripcion) {
		try{
			ResponseStatusType response = new ResponseStatusType();
			
			response.setStatusCode("0403");
			response.setStatusText("KO");
			response.setDetails(descripcion);
	
			
			return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(response, Charset.forName("UTF-8"), ResponseStatusType.class));
		}catch (Exception e){
			LOG.error("Error generando Respuesta",e);
			return null;
		}
		
	}
	
	
	
	private String pharseMessageToString(SOAPMessage soapMessage) throws SOAPException, IOException {
		String xml;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		soapMessage.writeTo(out);
		xml = new String(out.toByteArray());
		return xml;
	}
	
	private SOAPMessage sendMessage(MuleContext muleContext, PeticionNotificacionEstadoSMS envio) {
		SOAPMessage res = null;
		try {
			SoapPayload<?> payload = new PeticionPayload();
			payload.setSoapAction(SOAP_ACTION);
			String xml = mountSoapRequest(envio);
			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = null;
			db = dbf.newDocumentBuilder();
			Document soapDOM = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("UTF-8"))));
			payload.setSoapMessage(soapDOM);
			payload.setSoapAplication(APLICACION);
			// llamamos al flujo recepcion-AEAT
			final MuleMessage muleResponse = muleContext.getClient().send(RECEPT_QUEUE,payload, null, 10000);
			
			res = XMLUtils.dom2soap(muleResponse.getPayload(SoapPayload.class).getSoapMessage());
			
		} catch (Exception e) {
			LOG.error("ReintentoGISSJob.execute: Se ha producido un error en el reenvio ", e);
		}
		return res;
	}
	
	private String mountSoapRequest(PeticionNotificacionEstadoSMS envio) throws PlataformaBusinessException {
		StringBuilder soapRequest = new StringBuilder();
		soapRequest.append(HEADER);
		String xml = envio.toXML();
		soapRequest.append(xml.substring(xml.indexOf(">")+1,xml.length()));
		soapRequest.append(FOOTER);
		return soapRequest.toString();
	}
}