package es.minhap.misim.components;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.tranformers.Tempos21Sender;
import es.redsara.misim.misim_bus_webapp.respuesta.ResponseStatusType;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;
/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarEmisorHTTP implements Callable {
	
	private static final Logger LOG = LoggerFactory.getLogger(InvocarEmisorHTTP.class);
	
	public static final String ACCION_SMS_ENVIO = "envio";
	public static final String ACCION_SMS_CONSULTA = "consulta";
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {

		LOG.debug("Inicio invocación emisor HTTP");
		
		final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
		String respuesta ="";
		String statusCode ="";
		String [] parametros = null;
		try{
			
			parametros = leerParametros(docOriginal, Long.class.cast(eventContext.getMessage().getOutboundProperty("idProducto"))==1);
			LOG.debug("Número de parámetros de la llamada: " + parametros.length);

			try{
				
				String user = String.class.cast(eventContext.getMessage().getOutboundProperty("usuario"));
				String password = String.class.cast(eventContext.getMessage().getOutboundProperty("password"));
				String company = String.class.cast(eventContext.getMessage().getOutboundProperty("company"));
				String url = String.class.cast(eventContext.getMessage().getOutboundProperty("endpointUrl"));
				
				if (eventContext.getMessage().getOutboundProperty("urlEndpointSIM")!=null && !("").equals(eventContext.getMessage().getOutboundProperty("urlEndpointSIM"))){
					url = String.class.cast(eventContext.getMessage().getOutboundProperty("urlEndpointSIM"));
				}
				
				String type = String.class.cast(eventContext.getMessage().getOutboundProperty("type"));
				String certificado = String.class.cast(eventContext.getMessage().getOutboundProperty("certificado"));
				String keystorePassword =  String.class.cast(eventContext.getMessage().getOutboundProperty("certificadoPass"));
				
				PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
				
				String keystore = ps.getMessage("ruta.certificados", null, null, null) + certificado;
				
				
				if (parametros.length > 3) {
					LOG.debug("Se va a proceder a enviar un SMS");
					respuesta = sendSMS(parametros, user, password, company, url, type, keystore, keystorePassword);
					
					if (respuesta.contains("OK")){
						statusCode="OK";
					}else{
						statusCode="KO";
					}
					
				} else {
					LOG.debug("Se va a proceder a consultar el estado de un SMS");
					respuesta = getStatus(parametros, user, password, company, url, type, keystore, keystorePassword);
					
					if (respuesta.contains("OK")){
						statusCode="OK";
					}else{
						statusCode="KO";
					}
				}
				
			}catch (ModelException e) {
				LOG.error("Error al realizar la llamada: " + e.getMensaje());
				respuesta = "ERROR " + e.getMensaje();
				statusCode="KO";
				
			}catch (Exception e) {
				LOG.error("Error leyendo los parámetros de la llamada: " + e.toString());
				respuesta = "ERROR " + e.toString();
				statusCode="KO";
			}
		
		}catch (ModelException e) {
			LOG.error("Error leyendo los parámetros de la llamada: " + e.getMensaje());
			respuesta = "ERROR " + e.getMensaje();
			statusCode="KO";
			
		}catch (Exception e) {
			LOG.error("Error leyendo los parámetros de la llamada: " + e.toString());
			respuesta = "ERROR " + e.toString();
			statusCode="KO";
		}
		
		try{
			
			ResponseStatusType response = new ResponseStatusType();
			response.setStatusCode(statusCode);
			response.setStatusText(respuesta);
			response.setDetails("");
			
			Respuesta respuestaWS = new Respuesta();
			respuestaWS.setStatus(response);
			respuestaWS.setMessageId(String.class.cast(eventContext.getMessage().getOutboundProperty("smsID")));
			if (null==respuestaWS.getMessageId())
				respuestaWS.setMessageId(parametros[0]);
			
			SoapPayload.class.cast(eventContext.getMessage().getPayload()).setSoapMessage(XMLUtils.setPayloadFromObject(respuestaWS, Charset.forName("UTF-8"), Respuesta.class));
		
		}catch (Exception e) {
			LOG.error("Error al generar la respuesta: " + e.getMessage());
			throw new ModelException(e.getMessage()); 
		}
		
		LOG.debug("Fin invocación emisor HTTP.");

		return eventContext.getMessage();
	}
	
	
	private String[] leerParametros(Document docOriginal, boolean envio) throws Exception{
    	
    	if (envio) {
    	
    		String[] parametros = new String[4];
    		
    		NodeList SMS_ID = docOriginal.getElementsByTagName("SMS_ID");
    		NodeList SMS_DESTINATARIO = docOriginal.getElementsByTagName("SMS_DESTINATARIO");
    		NodeList SMS_TEXTO = docOriginal.getElementsByTagName("SMS_TEXTO");
    		NodeList SMS_HEADER = docOriginal.getElementsByTagName("SMS_HEADER");
    		
    		parametros[0] = SMS_ID.item(0).getTextContent();
    		parametros[1] = SMS_DESTINATARIO.item(0).getTextContent();
    		parametros[2] = SMS_TEXTO.item(0).getTextContent();
    		parametros[3] = SMS_HEADER.item(0).getTextContent();
        	
        	LOG.debug("Pametros - ID:" + parametros[0] + "  telefono:" + parametros[1]);
        	LOG.debug("Pametros - texto:" + parametros[2] + "  header:" + parametros[3]);
        
        	return parametros;
    		
    	} else {
    		
    		String[] parametros = new String[3];
    		
    		NodeList SMS_ID = docOriginal.getElementsByTagName("SMS_ID");
    		NodeList SMS_UIM = docOriginal.getElementsByTagName("SMS_UIM");
    		NodeList SMS_HEADER = docOriginal.getElementsByTagName("SMS_HEADER");
    		
    		parametros[0] = SMS_ID.item(0).getTextContent();
    		parametros[1] = SMS_UIM.item(0).getTextContent();
    		parametros[2] = SMS_HEADER.item(0).getTextContent();
    		
    		LOG.debug("Pametros - ID:" + parametros[0] + "  uim:" + parametros[1]);
        	LOG.debug("Pametros - header:" + parametros[2]);

    		return parametros;
    	}
    } 
	
	 private String sendSMS(String[] parametros, String user, String password, String company, String url, String type, String keystore, String keystorePassword) throws ModelException{
		 
		String respuesta = "";
		
		try {    	
			
			Tempos21Sender mdirectSender = new Tempos21Sender(url, company, user, password, parametros[3], keystore, keystorePassword, ACCION_SMS_ENVIO);
		
			LOG.info("Realizando envío de SMS - EnviaSMS - sendSMS{\n " + "idEnvio: " + parametros[0] + "\nTelefono: " + parametros[1] + "\nTexto SMS:" +parametros[2] + "\n}");
		
			respuesta = mdirectSender.sendSMS(parametros[1], parametros[2]);
		
			LOG.info("Realizado envío de SMS - EnviaSMS - sendSMS{\n " + "idEnvio: " + parametros[0] + "\nRespuesta: " + respuesta + "\n}");
		
		} catch (ModelException e) {
			LOG.error("Error al enviar un SMS: " + e.getMensaje());
			throw new ModelException("Error al enviar un SMS:" + e.getMensaje()); 
		
		} catch (Exception e) {
			LOG.error("Error al enviar un SMS: " + e.toString());
			throw new ModelException("Error al enviar un SMS:" + e.toString()); 
		}
		
		return respuesta;
	 }

	 private String getStatus(String[] parametros, String user, String password, String company, String url, String type,  String keystore, String keystorePassword) throws ModelException {

	        String respuesta = "";

	        try {

	        	Tempos21Sender mdirectSender = new Tempos21Sender(url, company, user, password, parametros[2], keystore, keystorePassword, ACCION_SMS_CONSULTA);

	        	LOG.info("Realizando consulta de estado - EnviaSMS - getStatus{\n " + "idEnvio: " + parametros[0] + "\nUIM: " + parametros[1] + "\n}");
	        
	        	respuesta = mdirectSender.getTracking(parametros[1]);
	        
	        	LOG.info("Realizada consulta de estado - EnviaSMS - getStatus{\n " + "idEnvio: " + parametros[0] + "\nRespuesta: " + respuesta + "\n}");
	        
	        } catch (ModelException e) {
				LOG.error("Error al enviar un SMS: " + e.getMensaje());
				throw new ModelException("Error al enviar un SMS:" + e.getMensaje()); 
				
	        } catch (Exception e) {
				LOG.error("Error al consultar el estado de un SMS: " + e.toString());
	        	throw new ModelException("Error al consultar el estado de un SMS: " + e.toString()); 
	        }

	        return respuesta;
	    }
}