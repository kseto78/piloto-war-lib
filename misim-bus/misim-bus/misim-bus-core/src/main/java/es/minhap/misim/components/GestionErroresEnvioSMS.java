package es.minhap.misim.components;

import java.nio.charset.Charset;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.minhap.misim.bus.model.exception.ModelException;
import es.redsara.misim.misim_bus_webapp.respuesta.ResponseStatusType;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;

/**
 * Component to call the Auditor�a Service SRV-ADT-02
 * 
 * @author ludarcos
 * 
 */
public class GestionErroresEnvioSMS implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(GestionErroresEnvioSMS.class);

	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Inicio del proceso de gestión de errores");
		
		//Recuperamos el codigo de Error y el Literal Error de la Excepcion
		Integer codigoError=0;
		String literalError="";
		String detailsError="";

		if(eventContext.getMessage().getExceptionPayload().getException().getCause().getClass().equals(ModelException.class)){
			ModelException modelExcep = (ModelException)eventContext.getMessage().getExceptionPayload().getException().getCause();
			codigoError=modelExcep.getCodigo();
			literalError=modelExcep.getMensaje();
			detailsError=modelExcep.getDetails();
		}else{
			codigoError=901;
			literalError="Servicio no disponible";
		}
		
		// Generamos las clases de la respuesta
		ResponseStatusType response = new ResponseStatusType();
		response.setStatusCode(String.valueOf(codigoError));
		response.setStatusText(literalError);
		response.setDetails(detailsError);

		Respuesta respuesta = new Respuesta();
		respuesta.setStatus(response);
		
		String idMensaje = String.class.cast(eventContext.getMessage().getOutboundProperty("idMensaje"));	
		
		if (idMensaje !=null && !("").equals(idMensaje)){
			respuesta.setMessageId(idMensaje);
		}
		
		
		SoapPayload.class.cast(eventContext.getMessage().getPayload()).setSoapMessage(XMLUtils.setPayloadFromObject(respuesta, Charset.forName("UTF-8"), Respuesta.class));
		
		LOG.debug("Fin del proceso de gestión de errores");
		return eventContext.getMessage();
	}

}