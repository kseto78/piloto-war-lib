package es.minhap.misim.components;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleContext;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleException;
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
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.plataformamensaferia.iop.beans.envioPremium.RespuestaNotificacionEstadoSMS;
import es.minhap.plataformamensajeria.iop.beans.enviosGISS.Fault;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.redsara.intermediacion.scsp.esquemas.v3.respuesta.Respuesta;
//import es.minhap.misim.components.envio.EnvioEmailXMLBean;
/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarEnvioRecepcionAEAT implements Callable, MuleContextAware {

	private static final Logger LOG = LoggerFactory.getLogger(InvocarEnvioRecepcionAEAT.class);
	
	private MuleContext muleContext;
	
	private String innerUrl;

	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	PropertiesServices ps = null;
	
	String errorClave = "";
	
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

		LOG.debug("Empezando el proceso de invocación actualización estado AEAT...");
		
		ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		errorClave = ps.getMessage("clave.ERRORCLAVE.AEAT", null, "[ERROR-CL@VE]:");
				
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
				LOG.info("REQUEST: " + XMLUtils.dom2xml(docOriginal));
			}           
			
			NodeList peticion = docOriginal.getElementsByTagNameNS("https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aduanas/es/aeat/dit/adu/itea/server/AcCLEV1Ent.xsd", "PeticionNotificacionEstadoSMS");


			String xmlPeticion = XMLUtils.nodeToString(peticion.item(0));
			Document doc = XMLUtils.xml2doc(xmlPeticion, Charset.forName("UTF-8"));
			String respuestaCompleta = XMLUtils.createSOAPFaultString((Node)doc.getDocumentElement());

			QName serviceQName = new QName(targetName, serviceName);
	        QName portQName = new QName(targetName, portName);
	        
	        LOG.info("ENDPOINT PREMIUM: " + endpointUrl);
	        LOG.info("REQUEST PREMIUM: " + respuestaCompleta);
	        
	        Document soapDOM = getSoapDOM(operationName, endpointUrl, timeout, respuestaCompleta, serviceQName, portQName,eventContext);
			
	        //LOG.info("RESPONSE PREMIUM: " + XMLUtils.dom2xml(XMLUtils.soap2dom(responseMessage)));
	        
	        try{
				
				SoapPayload<?> initPayload = eventContext.getMessage().getPayload(SoapPayload.class);
				SoapPayload<?> soapPayload = null;
		    	// La respuesta no es un SOAP Fault
	        	soapPayload = new SoapPayload<Respuesta>();
				soapPayload.setSoapAction(initPayload.getSoapAction());
				soapPayload.setSoapMessage(soapDOM);
				eventContext.getMessage().setPayload(soapPayload);
				
			}catch(Exception e){
				establecerPropertyError(eventContext, true, "Error en la transmisión: Error al obtener la respuesta del servicio Web especificado");
				LOG.error("Error en la transmisión: Error al obtener la respuesta del servicio Web especificado", e);
				LOG.error("La peticion que se envia es :" + respuestaCompleta);
				throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
			}
			
		}catch(IllegalArgumentException e){
			establecerPropertyError(eventContext, true, "Error al obtener la respuesta del servicio Web especificado");
			LOG.error("Error al obtener la respuesta del servicio Web especificado", e);
		}catch(ModelException e){
			establecerPropertyError(eventContext, true, "Error al generar el cliente: Endpoint no encontrado en el WSDL");
			LOG.error("Error al generar el cliente: Endpoint no encontrado en el WSDL", e);
		}catch (SOAPFaultException e) {	
			establecerPropertyError(eventContext, true,"Error en la transmisión: Error al contactar con el servicio Web especificado");
			LOG.error("Error en la transmisión: Error al contactar con el servicio Web especificado", e);
		}catch(WebServiceException e){
			establecerPropertyError(eventContext, true, "Error en la transmisión: Comunicación sin respuesta");
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
			establecerPropertyError(eventContext, true, "Error en la transmisión: Error de sistema Invocar Emisor");
			LOG.error("Error en la transmisión: Error de sistema Invocar Emisor", e);
		}
		
		LOG.debug("Proceso de creación de invocación al emisor terminado.");
	
		return eventContext.getMessage();
	}


	private void establecerPropertyError(MuleEventContext eventContext, boolean b, String error) {
		eventContext.getMessage().setOutboundProperty("errorAEAT", b);
		LOG.error(errorClave + error);
	}

	private void establecerPropertyError(MuleEventContext eventContext, boolean b) {
		eventContext.getMessage().setOutboundProperty("errorAEAT", b);
	}

	private Document getSoapDOM(String operationName, String endpointUrl, Long timeout, String respuestaCompleta, QName serviceQName, QName portQName,MuleEventContext eventContext)
			throws ParserConfigurationException, SAXException, IOException, UnsupportedEncodingException, ModelException {
		String xml =this.invoke(serviceQName, portQName, endpointUrl, operationName, respuestaCompleta, timeout,eventContext);

		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = null;
		db = dbf.newDocumentBuilder();
		Document soapDOM = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("UTF-8"))));
		return soapDOM;
	}

	private String invoke(QName serviceName, QName portName, String endpointUrl, 
	        String soapActionUri, String data, Long timeout,MuleEventContext eventContext) throws ModelException{
	     
		String xmlText = null;
		 
	    SOAPMessage response = null;
	    
	    try{
	    	
	        xmlText = sendMessageAEAT(data,endpointUrl, eventContext);
	        
	    }catch(SOAPFaultException e){
	    	establecerPropertyError(eventContext, true);
	    	LOG.error("SOAPFaultException", e);
	        // Processing for acquiring the SOAP fault
	        SOAPFault fault = e.getFault();
	        Node node = fault.cloneNode(true);
	        Document doc;
			try {
				doc = XMLUtils.createSOAPFaultDocument(node);
		        response = XMLUtils.dom2soap(doc);
			} catch (Exception e1) {
				LOG.error("Error procesando SOAPFaultException", e1);
			}
	    }catch(SOAPException e){
	    	establecerPropertyError(eventContext, true);
	    	LOG.error("SOAPException", e);
	    	throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
	    } catch (IOException e) {
	    	establecerPropertyError(eventContext, true);
	    	LOG.error("IOException", e);
	    	throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
		} catch (MuleException e) {
			establecerPropertyError(eventContext, true);
	    	LOG.error("MuleException", e);
		} catch (Throwable e) {
			establecerPropertyError(eventContext, true);
	    	LOG.error("Throwable", e);
		}
	
	    return xmlText;
	}
	
	//método usado para pharsear la respuesta cuando se prueba en local
	private String mountSoapRequest(RespuestaNotificacionEstadoSMS respuesta) throws PlataformaBusinessException {
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
		
	private SOAPMessage generateFaultAcuse(String url, MuleEventContext eventContext) throws Exception {
		SOAPMessage response = null;
		Fault respuestaFault = new Fault();
		respuestaFault.setFaultcode("0999");
		respuestaFault.setFaultstring("Se ha producido un error al invocar el endpoint: "+url);
		response = XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(respuestaFault, Charset.forName("UTF-8"), Fault.class));
		return response;
	}
	
	private String sendMessageAEAT(String message, String url, MuleEventContext eventContext) throws Throwable {
        MuleClient client = new DefaultLocalMuleClient(muleContext);
        Map<String, Object> props = new HashMap<String, Object>();
        props.put("url", url.substring(8));
        MuleMessage retVal = client.send(innerUrl, message, props);
        if (retVal.getExceptionPayload() != null &&  retVal.getExceptionPayload().getException()!=null) {
        	SOAPMessage soapMessage = generateFaultAcuse(url, eventContext);
        	String xmlFault = pharseMessageToString(soapMessage);
        	eventContext.getMessage().setOutboundProperty("SOAPFault", true);
        	return xmlFault;
        }
        
        eventContext.getMessage().setOutboundProperty("SOAPFault", false);
        return retVal.getPayloadAsString();
	}

}