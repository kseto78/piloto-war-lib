package es.minhap.misim.components;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Component to call the Auditor�a Service SRV-ADT-02
 * 
 * @author ludarcos
 * 
 */
public class ValidarXSDRespuesta implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(ValidarXSDRespuesta.class);

	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Empezando el proceso de validacion XSD...");

//		String xsdEspecificos = eventContext.getMessage().getOutboundProperty("xsdEspecificosProductor");
//		final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
//		String xmlOriginal = XMLUtils.dom2xml(docOriginal);
//		
//		try{
//			
//			if(xsdEspecificos!=null && !("").equals(xsdEspecificos)){
//				if(!XSDValidator.process(xmlOriginal, xsdEspecificos)){
//					//Lanzar error
//					LOG.error("Validar XSD: La estructura del fichero recibido no corresponde con el esquema");
//					throw new ModelException("La estructura del fichero recibido no corresponde con el esquema", 401);
//				}
//			}
//			
//		}catch(ModelException e){
//			//Lanzar error
//			throw new ModelException(e.getMensaje(), e.getCodigo());
//		}catch(Exception e){
//			//Lanzar error
//			LOG.error("Validar XSD: Error de sistema Validar XSD", e);
//			throw new ModelException("Error de sistema Validar XSD", 502);
//		}

		LOG.debug("Proceso de validación de XSD terminado.");
		return eventContext.getMessage();
	}
}
