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
public class IdentificarPropiedadesMensaje implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(IdentificarPropiedadesMensaje.class);
	
	@Resource
	private AplicacionManager aplicacionManager;
	
	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Empezando la obtención ID MENSAJE.");
		
		// Se recupera el documento de la petición
		final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
			
		try{
			
			
			NodeList SMS_ID = docOriginal.getElementsByTagName("MensajeId");
			
			String idMensaje=SMS_ID.item(0).getTextContent();
			eventContext.getMessage().setOutboundProperty("smsID", idMensaje);
	
			
			
		}catch (Exception e){
			
			LOG.error("Identificación: Error de sistema al obtener ID MENSAJE", e);
			throw new ModelException("Error de sistema al obtener ID MENSAJE", 502);
		}
		
		LOG.debug("Fin de la obtención ID MENSAJE.");

		return eventContext.getMessage();
	}
}
