package es.minhap.misim.components;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
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

import org.mule.api.MuleContext;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Callable;
import org.mule.client.DefaultLocalMuleClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.plataformamensajeria.iop.beans.estadoUsuarios.EstadoUsuarioRequest;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosMovilesManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.sim.model.TblServiciosMoviles;
import es.redsara.intermediacion.scsp.esquemas.v3.respuesta.Respuesta;
//import es.minhap.misim.components.envio.EnvioEmailXMLBean;
/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarEnvioSuscripcionServicioMovil implements Callable, MuleContextAware {

	private static final Logger LOG = LoggerFactory.getLogger(InvocarEnvioSuscripcionServicioMovil.class);
	
	private MuleContext muleContext;
	
	private String innerUrl;
	
	@Resource
	TblServiciosMovilesManager serviciosMovilesManager;

	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	PropertiesServices ps = null;
	
	public MuleContext getMuleContext() {
		return muleContext;
	}

	@Override
	public void setMuleContext(MuleContext context) {
		this.muleContext = context;
	}
	
	public String getInnerUrl() {
		return innerUrl;
	}

	public void setInnerUrl(String innerUrl) {
		this.innerUrl = innerUrl;
	}
   	
	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {

		LOG.debug("Empezando el proceso de invocación actualizacion estado Servicio Movil...");
		
		try{
			
			String targetName = eventContext.getMessage().getOutboundProperty("targetName");
			String serviceName = eventContext.getMessage().getOutboundProperty("serviceName");
			String portName = eventContext.getMessage().getOutboundProperty("portName");
			String operationName = eventContext.getMessage().getOutboundProperty("operationName");
			String endpointUrl = eventContext.getMessage().getOutboundProperty("endpointUrl");
			Long timeout = eventContext.getMessage().getOutboundProperty("timeout");
			if (null ==operationName)
				operationName = "";
			
			final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
			if(LOG.isInfoEnabled()){
			LOG.info("REQUEST: " + XMLUtils.dom2xml(docOriginal));}
//			NodeList peticion = docOriginal.getElementsByTagName("estadoUsuarioRequest");
//
//			String xmlPeticion = XMLUtils.nodeToString(peticion.item(0));
//			Document doc = XMLUtils.xml2doc(xmlPeticion, Charset.forName("UTF-8"));
//			String respuestaCompleta = XMLUtils.createSOAPFaultString((Node)doc.getDocumentElement());
			
			String respuestaCompleta= XMLUtils.dom2xml(docOriginal);

			QName serviceQName = new QName(targetName, serviceName);
	        QName portQName = new QName(targetName, portName);
	        
			String idServicioMovil = docOriginal.getElementsByTagName("pet:idServicioMovil").item(0).getTextContent();
			
			TblServiciosMoviles tblServiciosMoviles = serviciosMovilesManager.getServicioMovilById(Long.valueOf(idServicioMovil));
	        if(tblServiciosMoviles!=null && tblServiciosMoviles.getUrl_AvisoSuscripcion()!=null && 
	        tblServiciosMoviles.getEndpoint_User()!=null && tblServiciosMoviles.getEndpoint_Pass()!=null) {
	        	endpointUrl = tblServiciosMoviles.getUrl_AvisoSuscripcion();
	        }
	        if(LOG.isInfoEnabled()){
	        LOG.info("ENDPOINT: " + endpointUrl);
	        LOG.info("REQUEST: " + respuestaCompleta);}
	        
	        try{
	        	SOAPMessage responseMessage =this.invoke(serviceQName, portQName, endpointUrl, operationName, respuestaCompleta, timeout);
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
			
		}catch(IllegalArgumentException e){
			establecerPropertyError(eventContext, true);
			LOG.error("Error al obtener la respuesta del servicio Web especificado", e);
		}catch(ModelException e){
			establecerPropertyError(eventContext, true);
			LOG.error("Error al generar el cliente: Endpoint no encontrado en el WSDL", e);
		}catch (SOAPFaultException e) {	
			establecerPropertyError(eventContext, true);
			LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
		}catch(WebServiceException e){
			establecerPropertyError(eventContext, true);
			if(e.getCause()!=null){
				if(e.getCause().getClass().equals(SocketTimeoutException.class)){
					LOG.error("Error en la transmisión: Comunicación sin respuesta",e);
				}else{
					LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
				}
			}else{
				LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
			}
		}catch(Exception e){
			establecerPropertyError(eventContext, true);
			LOG.error("Error en la transmisión: Error de sistema Invocar Emisor", e);
		}
		
		LOG.debug("Proceso de creación de invocación al emisor terminado.");
	
		return eventContext.getMessage();
	}


	private void establecerPropertyError(MuleEventContext eventContext, boolean b) {
		eventContext.getMessage().setOutboundProperty("errorAEAT", b);
	}


//	private Document getSoapDOM(String operationName, String endpointUrl, Long timeout, String respuestaCompleta, QName serviceQName, QName portQName,MuleEventContext eventContext)
//			throws ParserConfigurationException, SAXException, IOException, UnsupportedEncodingException, ModelException {
//		String xml =this.invoke(serviceQName, portQName, endpointUrl, operationName, respuestaCompleta, timeout);
//		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		dbf.setNamespaceAware(true);
//		DocumentBuilder db = null;
//		db = dbf.newDocumentBuilder();
//		Document soapDOM = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("UTF-8"))));
//		return soapDOM;
//	}

	private SOAPMessage invoke(QName serviceName, QName portName, String endpointUrl, 
            String soapActionUri, String data, Long timeout) throws Exception {
        
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
	
//	private String invoke(QName serviceName, QName portName, String endpointUrl, 
//	        String soapActionUri, String data, Long timeout,MuleEventContext eventContext) throws ModelException{
//	     
//		String xmlText = null;
//		 
//	    SOAPMessage response = null;
//	    
//	    try{
//	    	
//	        xmlText = sendMessageServicioMovil(data,endpointUrl);
//	        
//	    }catch( SOAPFaultException e ){
//	    	establecerPropertyError(eventContext, true);
//	    	LOG.error("SOAPFaultException", e);
//	        // Processing for acquiring the SOAP fault
//	        SOAPFault fault = e.getFault();
//	        Node node = fault.cloneNode(true);
//	        Document doc;
//			try {
//				doc = XMLUtils.createSOAPFaultDocument(node);
//		        response = XMLUtils.dom2soap(doc);
//			} catch (Exception e1) {
//				LOG.error("Error procesando SOAPFaultException", e1);
//			}
//	    }catch( SOAPException e ){
//	    	establecerPropertyError(eventContext, true);
//	    	LOG.error("SOAPException", e);
//	    	throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
//	    } catch (IOException e) {
//	    	establecerPropertyError(eventContext, true);
//	    	LOG.error("IOException", e);
//	    	throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
//		} catch (MuleException e) {
//			establecerPropertyError(eventContext, true);
//	    	LOG.error("MuleException", e);
//		} catch (Throwable e) {
//			establecerPropertyError(eventContext, true);
//	    	LOG.error("Throwable", e);
//		}
//	
//	    return xmlText;
//	}
	
	//método usado para pharsear la respuesta cuando se prueba en local
	private String mountSoapRequest(EstadoUsuarioRequest respuesta) throws PlataformaBusinessException {
		StringBuilder soapRequest = new StringBuilder();
		soapRequest.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"> <soapenv:Header/> <soapenv:Body>");
		String xml = respuesta.toXML();
		soapRequest.append(xml.substring(xml.indexOf(">")+1,xml.length()));
		soapRequest.append("</soapenv:Body></soapenv:Envelope>");
		return soapRequest.toString();
	}
	
	private String pharseMessageToString(SOAPMessage soapMessage) throws SOAPException, IOException {
		String xml;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		soapMessage.writeTo(out);
		xml = new String(out.toByteArray());
		return xml;
	}
	
	private String sendMessageServicioMovil(String message, String url) throws Throwable {
        MuleClient client = new DefaultLocalMuleClient(muleContext);
        Map<String, Object> props = new HashMap<String, Object>();
        props.put("url", url.substring(7));
        MuleMessage retVal = client.send(innerUrl, message, props);
        if (retVal.getExceptionPayload() != null) {
        	throw retVal.getExceptionPayload().getException();
        }
        return retVal.getPayloadAsString();
	}

}