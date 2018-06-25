package es.minhap.misim.components;

import java.io.StringReader;
import java.net.SocketTimeoutException;

import javax.wsdl.WSDLException;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
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
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import es.minhap.misim.bus.model.exception.ModelException;
import es.redsara.intermediacion.scsp.esquemas.v3.respuesta.Respuesta;
/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarEmisor implements Callable {
	
	private static final Logger LOG = LoggerFactory.getLogger(InvocarEmisor.class);

	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {

		LOG.debug("Empezando el proceso de invocación al emisor...");

		try{
			
			// Obtiene la información del endpoint y la request
			String endpointUrl = String.class.cast(eventContext.getMessage().getOutboundProperty("endpointUrl"));
			
			if (eventContext.getMessage().getOutboundProperty("urlEndpointSIM")!=null && !("").equals(eventContext.getMessage().getOutboundProperty("urlEndpointSIM"))){
				endpointUrl = String.class.cast(eventContext.getMessage().getOutboundProperty("urlEndpointSIM"));
			}
			
			String targetName = String.class.cast(eventContext.getMessage().getOutboundProperty("targetName"));
			String serviceName = String.class.cast(eventContext.getMessage().getOutboundProperty("serviceName"));
			String portName = String.class.cast(eventContext.getMessage().getOutboundProperty("portName"));
			String operationName = String.class.cast(eventContext.getMessage().getOutboundProperty("operation"));
//			String idSms=String.class.cast(eventContext.getMessage().getOutboundProperty("smsID"));
			String request = XMLUtils.dom2xml(SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage());
			int timeout=0;
			if(eventContext.getMessage().getOutboundProperty("timeout")!=null 
					&& !("").equals(eventContext.getMessage().getOutboundProperty("timeout"))
					&& Integer.parseInt(eventContext.getMessage().getOutboundProperty("timeout").toString())>0){
				timeout=Integer.parseInt(eventContext.getMessage().getOutboundProperty("timeout").toString());
			}
		
			
			QName serviceQName = new QName(targetName, serviceName);
	        QName portQName = new QName(targetName, portName);
	        
	        if(LOG.isInfoEnabled()){
	        	LOG.info("REQUEST: "+request);
	        }
//	        SOAPMessage responseMessage =this.invoke(serviceQName, portQName, endpointUrl, operationName, request, timeout);
	        
	        try{
	        	SOAPMessage responseMessage =this.invoke(serviceQName, portQName, endpointUrl, operationName, request, timeout);
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
			   // idDelMensaje
//			    String xmlResponse=XMLUtils.dom2xml(XMLUtils.soap2dom(responseMessage));
//			    responseMessage=XMLUtils.dom2soap(XMLUtils.xml2doc(xmlResponse.replace("idDelMensaje", idSms), Charset.forName("UTF-8")));
			    if(LOG.isInfoEnabled()){
			    	LOG.info("RESPONSE: " + XMLUtils.dom2xml(XMLUtils.soap2dom(responseMessage)));
			    }
			    soapPayload.setSoapAction(initPayload.getSoapAction());
				soapPayload.setSoapMessage(XMLUtils.soap2dom(responseMessage));
				
								
				eventContext.getMessage().setOutboundProperty("xmlRespuestaDirectaOperador", XMLUtils.dom2xml(soapPayload.getSoapMessage()));
				
				eventContext.getMessage().setPayload(soapPayload);
				
			}catch(Exception e){
				//Lanzar error
				LOG.error("Error en la transmisión: Error al obtener la respuesta del servicio Web especificado:" +endpointUrl);
				throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
			}
			
		}catch(WSDLException e){
			//Lanzar error
			LOG.error("Error al generar el cliente: WSDL no generado correctamente", e);
			throw new ModelException("Error al generar el cliente del servicio Web especificado", 103);
		}catch(IllegalArgumentException e){
			//Lanzar error
			LOG.error("Error al generar el cliente: Endpoint no encontrado en el WSDL", e);
			throw new ModelException("Error al generar el cliente del servicio Web especificado", 103);
		}catch(ModelException e){
			//Lanzar error
			throw new ModelException(e.getMensaje(), e.getCodigo());
		}catch (SOAPFaultException e) {			
			//Lanzar error
			LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
			throw new ModelException("Error al contactar con el servicio Web especificado", 101);
		}catch(WebServiceException e){
			
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(SocketTimeoutException.class)){
					//Lanzar error
					LOG.error("Error en la transmisión: Comunicación sin respuesta",e);
					throw new ModelException("Comunicación sin respuesta", 102);
				}else{
					//Lanzar error
					LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
					throw new ModelException("Error al contactar con el servicio Web especificado", 101);
				}
			}else{
				//Lanzar error
				LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
				throw new ModelException("Error al contactar con el servicio Web especificado", 101);
			}
		}catch(Exception e){
			//Lanzar error
			LOG.error("Error en la transmisión: Error de sistema Invocar Emisor", e);
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