package es.minhap.misim.components;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import es.minhap.misim.bus.model.Aplicacion;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.servicios.AplicacionManager;
import es.redsara.intermediacion.scsp.esquemas.v3.respuesta.Respuesta;

/**
 * Component to call the Identification Service SRV-ID- 01
 * 
 * @author ludarcos
 * 
 */
public class IncluirUsuario implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(IncluirUsuario.class);
	
	@Resource
	private AplicacionManager aplicacionManager;
	
	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Empezando la identificación del proveedor.");
		
		// Se recupera el documento de la petición
		final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
			
		try{

//			LOG.debug("Antes de modificar id MENSAJE: " + XMLUtils.dom2xml(docOriginal));
			
			Long idAplicacion = Long.class.cast(eventContext.getMessage().getOutboundProperty("idAplicacion"));
			Aplicacion aplicacion = aplicacionManager.getAplicacionById(idAplicacion);
			
			String xmlDocumento=XMLUtils.dom2xml(docOriginal);
			xmlDocumento=xmlDocumento.replace("idUsuario", aplicacion.getUsuario());
			xmlDocumento=xmlDocumento.replace("idPassword", aplicacion.getPassword());
			
			Document documentoMod=XMLUtils.xml2doc(xmlDocumento, Charset.forName("UTF-8"));
			
			SoapPayload<?> initPayload = eventContext.getMessage().getPayload(SoapPayload.class);
			SoapPayload<?> soapPayload = new SoapPayload<Respuesta>();
			soapPayload.setSoapAction(initPayload.getSoapAction());
			soapPayload.setSoapMessage(documentoMod);
			
			eventContext.getMessage().setPayload(soapPayload);
			
//			LOG.debug("Despues modificar id MENSAJE: " + XMLUtils.dom2xml(documentoMod));
		
		}catch (Exception e){
			
			LOG.error("Identificación: Error de sistema al identificar el mensaje", e);
			throw new ModelException("Error de sistema al identificar el mensaje", 502);
		}
		
		LOG.debug("Fin de la identificación del proveedor.");

		return eventContext.getMessage();
	}
}
