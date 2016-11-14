package es.minhap.misim.components;

import java.nio.charset.Charset;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.minhap.misim.bus.model.exception.ModelException;
import es.telefonica.mi.interfazsimplificado.schemas.DeliverRes;
import es.telefonica.mi.interfazsimplificado.schemas.DeliveryReportRes;
import es.telefonica.mi.interfazsimplificado.schemas.ResponseStatusType;

/**
 * Component to call the Auditor�a Service SRV-ADT-02
 * 
 * @author ludarcos
 * 
 */
public class GestionErroresTelefonica implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(GestionErroresTelefonica.class);

	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Empezando el proceso de gestión de errores...");
		
		String soapAction=SoapPayload.class.cast(eventContext.getMessage().getOriginalPayload()).getSoapAction();
		
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
		
		if(("recepcionSMS").equals(soapAction)){
			
			DeliverRes respuesta = new DeliverRes();
			
			ResponseStatusType response = new ResponseStatusType();
			response.setStatusCode(String.valueOf(codigoError));
			response.setStatusText(literalError);
			response.setDetails(detailsError);

			respuesta.setStatus(response);
			respuesta.setVersion("1.0");
			
			SoapPayload.class.cast(eventContext.getMessage().getPayload()).setSoapMessage(XMLUtils.setPayloadFromObject(respuesta, Charset.forName("UTF-8"), DeliverRes.class));
			
		}else if (("notificacionSMS").equals(soapAction)){
			
			DeliveryReportRes respuesta = new DeliveryReportRes();
			
			ResponseStatusType response = new ResponseStatusType();
			response.setStatusCode(String.valueOf(codigoError));
			response.setStatusText(literalError);
			response.setDetails(detailsError);
			
			respuesta.setStatus(response);
			respuesta.setVersion("1.0");
			
			SoapPayload.class.cast(eventContext.getMessage().getPayload()).setSoapMessage(XMLUtils.setPayloadFromObject(respuesta, Charset.forName("UTF-8"), DeliveryReportRes.class));
		}
		
		
		LOG.debug("Proceso de gestión de errores terminado.");
		return eventContext.getMessage();
	}

}