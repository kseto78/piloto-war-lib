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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.plataformamensajeria.iop.beans.RegistroUsuarioXMLBean;
import es.minhap.plataformamensajeria.iop.beans.estadoUsuarios.EstadoUsuarioRequest;
import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.RespuestaServiciosRegistrarUsuario;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosMovilesManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.services.gestionServiciosPush.IGestionServiciosPushService;
import es.minhap.sim.model.TblServiciosMoviles;

/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarRegistroUsuarioEnServicio implements Callable {
	
	public static final String RECEPT_QUEUE = "vm://suscripcion-servicio-movil";
	
	protected static final String SOAP_ACTION = "SUSCRIPCION_USUARIO_PUSH";
	
	private static final String HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"> <soapenv:Header/> <soapenv:Body>";    
	
	private static final String FOOTER = "</soapenv:Body></soapenv:Envelope>";
	
	private static final Logger LOG = LoggerFactory.getLogger(InvocarRegistroUsuarioEnServicio.class);

	@Resource
	IGestionServiciosPushService gestionServiciosPushImpl;
	
	@Resource
	TblServiciosMovilesManager serviciosMovilesManager;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	PropertiesServices ps = null;

	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {

		LOG.debug("Empezando el proceso de invocación del enviador...");

		try {

			final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload())
					.getSoapMessage();
			
			if(LOG.isInfoEnabled()){
	        	LOG.info("REQUEST: "+ XMLUtils.dom2xml(docOriginal));
	        }
			NodeList peticion = docOriginal.getElementsByTagNameNS(
					"http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio",
					"PeticionRegistroUsuario");
			String xmlPeticion = XMLUtils.nodeToString(peticion.item(0));

			RegistroUsuarioXMLBean registroUsuarioXML = new RegistroUsuarioXMLBean();
			registroUsuarioXML.loadObjectFromXML(xmlPeticion);

			String respuesta = gestionServiciosPushImpl.registroUsuarioEnServicio(registroUsuarioXML);

			if(respuesta.contains("OK")){
				String idServicioMovil = registroUsuarioXML.getIdServicioMovil();
				
				if(registroUsuarioXML.getAccion()!=null && ("A".equals(registroUsuarioXML.getAccion()) || "B".equals(registroUsuarioXML.getAccion()))) {
					TblServiciosMoviles tblServiciosMoviles = serviciosMovilesManager.getServicioMovilById(Long.valueOf(idServicioMovil));
					Integer indSuscripcion = tblServiciosMoviles.getInd_Suscripcion();
					String usuarioServicio = tblServiciosMoviles.getEndpoint_User();
					String passwordServicio = tblServiciosMoviles.getEndpoint_Pass();
					
					if(indSuscripcion == 1){
						//Consultar BBDD y realizar la llamada si el campo IND_SUSCRIPCION=1.
						//Adicionalmente recupera la URL para invocar el servicio
						String idUsuario = registroUsuarioXML.getIdUsuario();
						String accion = registroUsuarioXML.getAccion();
						
						String respuestaUsuarioServicioMovil = envioEstadoUsuarioServicioMovil(eventContext, idServicioMovil, idUsuario, accion, usuarioServicio, passwordServicio);
						
						//Pendiente: tratar respuesta
												
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
					soapPayload = new SoapPayload<RespuestaServiciosRegistrarUsuario>();
					eventContext.getMessage().setOutboundProperty("SOAPFault", false);
				}

				if(LOG.isInfoEnabled()){
		        	LOG.info("RESPONSE: " + XMLUtils.dom2xml(XMLUtils.soap2dom(responseMessage)));
		        }
				
				soapPayload.setSoapAction(initPayload.getSoapAction());
				soapPayload.setSoapMessage(XMLUtils.soap2dom(responseMessage));

				eventContext.getMessage().setPayload(soapPayload);

			} catch (Exception e) {
				// Lanzar error
				LOG.error("Error en la transmisión: Error al obtener la respuesta del servicio Web especificado", e);
				throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
			}

		} catch (ModelException e) {

			throw new ModelException(e.getMensaje(), e.getCodigo());

		} catch (Exception e) {
			// Lanzar error
			LOG.error("Error en la transmisión: Error de sistema Invocar Emisor", e);
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
	
	private String envioEstadoUsuarioServicioMovil(final MuleEventContext eventContext,String idServicioMovil,String idUsuario,String accion, String usuarioServicio, String passwordServicio){
		
		LOG.debug("Empezando el proceso de invocación al emisor...");
		String respuestaIncompleta ="";
		String xml = null;
		try{
			
			ps = new PropertiesServices(reloadableResourceBundleMessageSource);
			String usuarioServicioMovil = new String(ps.getMessage("usuarioMISIM", null, null, null));
			String passwordServicioMovil = new String(ps.getMessage("passwordMISIM", null, null, null));
			
			if(usuarioServicio!=null && !"".equals(usuarioServicio)) {
				usuarioServicioMovil = usuarioServicio;
			}
			
			if(passwordServicio!=null && !"".equals(passwordServicio)){
				passwordServicioMovil = passwordServicio;
			}
			String valorAccion = "0";

			if(accion.equals("A")){
				valorAccion = "1";
			}
			
			EstadoUsuarioRequest estadoUsuarioRequest = new EstadoUsuarioRequest();
			estadoUsuarioRequest.setIdServicioMovil(idServicioMovil);
			estadoUsuarioRequest.setIdUsuario(idUsuario);
			estadoUsuarioRequest.setPassword(passwordServicioMovil);
			estadoUsuarioRequest.setUsuario(usuarioServicioMovil);
			estadoUsuarioRequest.setEstadoServicio(valorAccion);

			SOAPMessage soapMessage = sendMessage(eventContext.getMuleContext(),estadoUsuarioRequest);
			xml = pharseMessageToString(soapMessage);
			
		}catch(IllegalArgumentException e){
			LOG.error("Error al generar el cliente: Endpoint no encontrado en el WSDL", e);
			LOG.error("La peticion que se envia es :" + respuestaIncompleta);
		}catch (SOAPFaultException e) {			
			LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
			LOG.error("La peticion que se envia es :" + respuestaIncompleta);
		}catch(WebServiceException e){
			
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(SocketTimeoutException.class)){
					LOG.error("Error en la transmisión: Comunicación sin respuesta",e);
					LOG.error("La peticion que se envia es :" + respuestaIncompleta);
				}else{
					LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
					LOG.error("La peticion que se envia es :" + respuestaIncompleta);
				}
			}else{
				LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
				LOG.error("La peticion que se envia es :" + respuestaIncompleta);
			}
		}catch(Exception e){
			LOG.error("Error en la transmisión: Error de sistema Invocar Emisor", e);
			LOG.error("La peticion que se envia es :" + respuestaIncompleta);
		}
		
		LOG.debug("Proceso de creación de invocación al emisor terminado.");
	return xml;
	}
	private String pharseMessageToString(SOAPMessage soapMessage) throws SOAPException, IOException {
		String xml;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		soapMessage.writeTo(out);
		xml = new String(out.toByteArray());
		return xml;
	}
	
	private SOAPMessage sendMessage(MuleContext muleContext, EstadoUsuarioRequest estadoUsuarioRequest) {
		SOAPMessage res = null;
		try {
			SoapPayload<?> payload = new PeticionPayload();
			payload.setSoapAction(SOAP_ACTION);
			String xml = mountSoapRequest(estadoUsuarioRequest);
			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = null;
			db = dbf.newDocumentBuilder();
			Document soapDOM = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("UTF-8"))));
			payload.setSoapMessage(soapDOM);
			// llamamos al flujo suscripcion-servicio-movil
			final MuleMessage muleResponse = muleContext.getClient().send(RECEPT_QUEUE,payload, null, 10000);
			
			res = XMLUtils.dom2soap(muleResponse.getPayload(SoapPayload.class).getSoapMessage());
			
		} catch (Exception e) {
			LOG.error("InvocarRegistroUsuarioEnServicio.sendMessage: Se ha producido un error enviando estado de usuario push ", e);
		}
		return res;
	}
	
	private String mountSoapRequest(EstadoUsuarioRequest estadoUsuarioRequest) throws PlataformaBusinessException {
		StringBuilder soapRequest = new StringBuilder();
		soapRequest.append(HEADER);
		String xml = estadoUsuarioRequest.toXML();
		soapRequest.append(xml.substring(xml.indexOf(">")+1,xml.length()));
		soapRequest.append(FOOTER);
		return soapRequest.toString();
	}

}