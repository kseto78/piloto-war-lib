
package es.minhap.misim.components;

import misim.bus.common.bean.SoapPayload;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;

import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.seguridad.FirmaService;

/**
 * Component to call the Firm Validation Service SRV-FRM-02
 * 
 * @author ludarcos
 * 
 */
public class ValidarFirma implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(ValidarFirma.class);

	@Autowired
	FirmaService firmaService;
	
	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		MuleMessage muleMessage = eventContext.getMessage();

		// Se recupera el documento de la petición
		final Document docFirmado = SoapPayload.class.cast(muleMessage.getPayload()).getSoapMessage();
		
		// Obtenemos el tipo de firma
		String tipoFirma= eventContext.getMessage().getOutboundProperty("tipoFirma");
		
		try{
			if(("WS-Security").equals(tipoFirma)){
				// Lanzamos la validación de firma
				if(!firmaService.validarFirmaWSSecurity(docFirmado)){
					//Lanzar error
					LOG.error("Validar Firma: Firma no válida");
					throw new ModelException("Firma no válida",305);
				}
				
			}else if(("XMLdSig").equals(tipoFirma)){
				// Lanzamos la validación de firma
				if(!firmaService.validarFirmaXMLDSign(docFirmado)){
					//Lanzar error
					LOG.error("Validar Firma: Firma no válida");
					throw new ModelException("Firma no válida",305);
				}
			}

		}catch(ModelException e){
			//Lanzar error
			throw new ModelException(e.getMensaje(), e.getCodigo());
		}catch(Exception e){
			//Lanzar error
			LOG.error("Validar Firma: Error de sistema Validar Firma", e);
			throw new ModelException("Error de sistema Validar Firma", 502);
		}

		LOG.debug("Validación de firma realizada.");
		return eventContext.getMessage();
	}

}
