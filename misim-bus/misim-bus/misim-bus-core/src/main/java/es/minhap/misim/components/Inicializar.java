package es.minhap.misim.components;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Inicializar implements Callable {
	
	private static final Logger LOG = LoggerFactory.getLogger(Inicializar.class);

	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {
		
		LOG.debug("Inicio de la inicialización de variables");
		
		eventContext.getMessage().setOutboundProperty("xmlPeticion", "");
		eventContext.getMessage().setOutboundProperty("xmlRespuesta", "");
		eventContext.getMessage().setOutboundProperty("xmlFault", "");
		
//		eventContext.getMessage().setOutboundProperty("idAuditoria", "");
//		eventContext.getMessage().setOutboundProperty("endpointUrl", "");
		// eventContext.getMessage().setOutboundProperty("wsdlEmisor", "");
		// eventContext.getMessage().setOutboundProperty("bindingName", "");
//		eventContext.getMessage().setOutboundProperty("targetName", "");
//		eventContext.getMessage().setOutboundProperty("serviceName", "");
//		eventContext.getMessage().setOutboundProperty("portName", "");
		eventContext.getMessage().setOutboundProperty("operation", "");
//		eventContext.getMessage().setOutboundProperty("comunicacionProductor", Integer.valueOf(1));
//		eventContext.getMessage().setOutboundProperty("timeout", "");
		
		LOG.debug("Fin de la inicialización de variables");
		
		return eventContext.getMessage();
	}

}
