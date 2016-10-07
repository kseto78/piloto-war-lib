package es.minhap.misim.components;

import javax.annotation.Resource;

import misim.bus.common.bean.SoapPayload;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.servicios.AplicacionManager;

/**
 * Component to call the Identification Service SRV-ID- 01
 * 
 * @author ludarcos
 * 
 */
public class IdentificarTipoPeticion implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(IdentificarTipoPeticion.class);
	
	@Resource
	private AplicacionManager aplicacionManager;
	
	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Inicio de la identificación del tipo de peticion");
		
		// Se recupera el documento de la petición
		final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
		
		try{
			
			String operation=null;
			
			NodeList listSMS = docOriginal.getElementsByTagName("MensajeSMS");
			if(listSMS.item(0)!=null){
				operation = "enviarSMS";
			}
			
			NodeList listSMS2 = docOriginal.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/peticion","MensajeSMS");
			
			if(listSMS2.item(0)!=null){
				operation = "enviarSMS";
			}
			
			
			NodeList listEMAIL = docOriginal.getElementsByTagName("MensajeEmail");
			if(listEMAIL.item(0)!=null){
				operation = "enviarEmail";
			}
			
			NodeList listEMAIL2 = docOriginal.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/peticion","MensajeEmail");
			if(listEMAIL2.item(0)!=null){
				operation = "enviarEmail";
			}
			
			NodeList listPush = docOriginal.getElementsByTagName("MensajePush");
			if(listPush.item(0)!=null){
				operation = "enviarPush";
			}
			
			NodeList listPush2 = docOriginal.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/peticion","MensajePush");
			if(listPush2.item(0)!=null){
				operation = "enviarPush";
			}
			
			if(operation==null || "".equals(operation)){
				
				String soapAction=SoapPayload.class.cast(eventContext.getMessage().getOriginalPayload()).getSoapAction();
				
				if(!("enviarMensaje").equals(soapAction)){
					
					eventContext.getMessage().setOutboundProperty("tipoPeticion", soapAction);

				}else{
					throw new ModelException("No se ha enviado ningún mensaje", 502);
				}

			}else{
				
				eventContext.getMessage().setOutboundProperty("tipoPeticion", operation);
			}
			

			
		}catch (ModelException e){	
			
			LOG.error("Identificación: Error de sistema al identificar el tipo de peticion", e.getMensaje());
			throw new ModelException("Error de sistema al identificar el tipo de peticion", 502);
			
		}catch (Exception e){
			
			LOG.error("Identificación: Error de sistema al identificar el tipo de peticion", e.getMessage());
			throw new ModelException("Error de sistema al identificar el tipo de peticion", 502);
		}
		
		LOG.debug("Empezando la identificación del tipo de peticion");

		return eventContext.getMessage();
	}	

}
