package es.minhap.misim.components;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

import javax.annotation.Resource;
import javax.wsdl.WSDLException;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.ws.soap.SOAPFaultException;

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
import es.minhap.plataformamensajeria.iop.services.envioPremium.IEnvioPremiumService;
import es.minhap.plataformamensajeria.iop.util.FactoryServiceSim;
import es.redsara.intermediacion.scsp.esquemas.v3.respuesta.Respuesta;
//import es.minhap.misim.components.envio.EnvioEmailXMLBean;
/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarEnvio001ConsultaOP implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(InvocarEnvio001.class);

	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	PropertiesServices ps = null;

	
	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {

		LOG.debug("Empezando el proceso de invocación al emisor...");
		String COMPROBACION_URL = " | ";
		String request ="";
		try{
			String temp = eventContext.getMessage().getInboundProperty("recepcion_mensaje_premium_001");
			String temp1 = eventContext.getMessage().getOutboundProperty("recepcion_mensaje_premium_001");
			
			ps = new PropertiesServices(reloadableResourceBundleMessageSource);
			String targetName = ps.getMessage("aeat.tagetName", null, null, null);
			String serviceName = ps.getMessage("aeat.serviceName", null, null, null);
			String portName = ps.getMessage("aeat.portName", null, null, null);
			String operationName = ps.getMessage("aeat.operationName", null, null, null);
			final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
			 if (LOG.isDebugEnabled()) {
				 LOG.info("REQUEST: " + XMLUtils.dom2xml(docOriginal));
			 }
			NodeList respuesta = docOriginal.getElementsByTagName("Respuesta");

			String xmlRespuesta = XMLUtils.nodeToString(respuesta.item(0));
			Document doc = XMLUtils.xml2doc(xmlRespuesta, Charset.forName("UTF-8"));
			String respuestaCompleta = XMLUtils.createSOAPFaultString((Node)doc.getDocumentElement());
			
		    System.out.println( getClass() +"Envio de respuesta a la URL");
			String endpointUrl = "";

			
			if (respuestaCompleta.contains(COMPROBACION_URL)){
				String requestFirst = respuestaCompleta.substring(0,respuestaCompleta.indexOf("|", 0));
				String requestLast = respuestaCompleta.substring(respuestaCompleta.indexOf("</Details>"),respuestaCompleta.length());
				endpointUrl = respuestaCompleta.substring(respuestaCompleta.indexOf("|", 0)+2, respuestaCompleta.indexOf("</Details>"));
				request = requestFirst + requestLast;
				
			}

			int timeout=0;
			
			QName serviceQName = new QName(targetName, serviceName);
	        QName portQName = new QName(targetName, portName);
	        SOAPMessage responseMessage =this.invoke(serviceQName, portQName, endpointUrl, operationName, request, timeout);
	        
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

			    if (LOG.isDebugEnabled()) {
			    	LOG.debug("RESPONSE: " + XMLUtils.dom2xml(XMLUtils.soap2dom(responseMessage)));
			    }
				soapPayload.setSoapAction(initPayload.getSoapAction());
				soapPayload.setSoapMessage(XMLUtils.soap2dom(responseMessage));
				
				eventContext.getMessage().setPayload(soapPayload);
				
			}catch(Exception e){
				//Lanzar error
				LOG.error("Error en la transmisión: Error al obtener la respuesta del servicio Web especificado", e);
				LOG.error("La peticion que se envia es :" + request);
				throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
			}
			
		}catch(WSDLException e){
			//Lanzar error
			LOG.error("Error al generar el cliente: WSDL no generado correctamente", e);
			LOG.error("La peticion que se envia es :" + request);
			throw new ModelException("Error al generar el cliente del servicio Web especificado", 103);
		}catch(IllegalArgumentException e){
			//Lanzar error
			LOG.error("Error al generar el cliente: Endpoint no encontrado en el WSDL", e);
			LOG.error("La peticion que se envia es :" + request);
			throw new ModelException("Error al generar el cliente del servicio Web especificado", 103);
		}catch(ModelException e){
			//Lanzar error
			throw new ModelException(e.getMensaje(), e.getCodigo());
		}catch (SOAPFaultException e) {			
			//Lanzar error
			LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
			LOG.error("La peticion que se envia es :" + request);
			throw new ModelException("Error al contactar con el servicio Web especificado", 101);
		}catch(WebServiceException e){
			
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(SocketTimeoutException.class)){
					//Lanzar error
					LOG.error("Error en la transmisión: Comunicación sin respuesta",e);
					LOG.error("La peticion que se envia es :" + request);
					throw new ModelException("Comunicación sin respuesta", 102);
				}else{
					//Lanzar error
					LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
					LOG.error("La peticion que se envia es :" + request);
					throw new ModelException("Error al contactar con el servicio Web especificado", 101);
				}
			}else{
				//Lanzar error
				LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
				LOG.error("La peticion que se envia es :" + request);
				throw new ModelException("Error al contactar con el servicio Web especificado", 101);
			}
		}catch(Exception e){
			//Lanzar error
			LOG.error("Error en la transmisión: Error de sistema Invocar Emisor", e);
			LOG.error("La peticion que se envia es :" + request);
			throw new ModelException("Error de sistema Invocar Emisor", 502);
		}
		
		LOG.debug("Proceso de creación de invocación al emisor terminado.");

		return eventContext.getMessage();
	}
	
	private SOAPMessage invoke(QName serviceName, QName portName, String endpointUrl, 
            String soapActionUri, String data, int timeout) throws Exception {
        
		Service service = Service.create(serviceName);
        service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointUrl);

        Dispatch<SOAPMessage> dispatch = service.createDispatch(portName, SOAPMessage.class, Service.Mode.MESSAGE);
        
        if(timeout>0){
            dispatch.getRequestContext().put("javax.xml.ws.client.connectionTimeout", timeout);
            dispatch.getRequestContext().put("javax.xml.ws.client.receiveTimeout", timeout);
        }
        
        dispatch.getRequestContext().put(Dispatch.SOAPACTION_USE_PROPERTY, false);
        dispatch.getRequestContext().put(Dispatch.SOAPACTION_URI_PROPERTY, soapActionUri);
        
        SOAPMessage response = null;
        
        try{
        	
        	MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage message = messageFactory.createMessage();

            StreamSource preppedMsgSrc = new StreamSource(new StringReader(data));
            SOAPPart soapPart = message.getSOAPPart();
            soapPart.setContent(preppedMsgSrc);
            response = dispatch.invoke(message);
            
        }catch( SOAPFaultException e ){
        	
            // Processing for acquiring the SOAP fault
            SOAPFault fault = e.getFault();
            Node node = fault.cloneNode(true);
            Document doc = XMLUtils.createSOAPFaultDocument(node);
            response = XMLUtils.dom2soap(doc);
        }

        return response;
    }
}