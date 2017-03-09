package es.minhap.misim.components;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.w3c.dom.Document;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.model.Auditoria;
import es.minhap.misim.bus.model.Estado;
import es.minhap.misim.bus.model.Peticion;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.servicios.AuditoriaManager;
import es.minhap.misim.bus.model.servicios.PeticionManager;

/**
 * Component to call the Auditor�a Service SRV-ADT-02
 * 
 * @author ludarcos
 * 
 */
public class ActualizarAuditoria implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(ActualizarAuditoria.class);
	
	@Resource
	private AuditoriaManager auditoriaManager;
	
	@Resource
	private PeticionManager peticionManager;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Inicio actualización de auditoría");
		final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
		
		try{
			
			PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
			
			// Obtenemos las identificaciones para poder actualizar la peticion y la auditoria
			String idPeticion=eventContext.getMessage().getOutboundProperty("idPeticion");
			String idAuditoria=eventContext.getMessage().getOutboundProperty("idAuditoria");
			
			// Obtenemos el mensaje de la respuesta
			//String xmlRespuesta = XMLUtils.dom2xml(docOriginal);
			String xmlRespuesta = eventContext.getMessage().getOutboundProperty("xmlRespuestaDirectaOperador");
//			LOG.info("xmlRespuestaDirectaOperador--->" + xmlRespuesta);
			if (null == xmlRespuesta || xmlRespuesta.length() <= 0){
				xmlRespuesta = XMLUtils.dom2xml(docOriginal);
			}
			
//			LOG.info("respuestaTransformada--->" +XMLUtils.dom2xml(docOriginal));
	
			// Obtenemos la petición a actualizar

			Peticion peticion = peticionManager.getPeticionById(Long.valueOf(idPeticion));
			
			if(peticion==null){
				// Lanzar error
				LOG.error("Actualizar Auditoría: Error al recuperar el estado de la petición");
				throw new ModelException("Error al recuperar el estado de la petición", 207);
			}
			
			boolean soapFault = Boolean.parseBoolean(String.valueOf(eventContext.getMessage().getOutboundProperty("SOAPFault")));
			Boolean peticionAEAT = Boolean.parseBoolean(String.valueOf(eventContext.getMessage().getOutboundProperty("peticionAEAT")));
			Boolean errorAEAT = Boolean.parseBoolean(String.valueOf(eventContext.getMessage().getOutboundProperty("errorAEAT")));
			
			// Actualizamos la petición
			Estado estado = new Estado();
			if(soapFault){
				estado.setIdEstado(Long.valueOf(ps.getMessage("estado.fracasado", null)));
			}else{
				estado.setIdEstado(Long.valueOf(ps.getMessage("estado.exito", null)));
			}
			if(null != peticionAEAT && errorAEAT){
				estado.setIdEstado(Long.valueOf(ps.getMessage("estado.fracasado", null)));
				eventContext.getMessage().setOutboundProperty("errorAEAT", "false");
			}else{
				estado.setIdEstado(Long.valueOf(ps.getMessage("estado.exito", null)));
			}
			
			peticion.setEstado(estado);
			peticion.setMensajeRespuesta(xmlRespuesta);
			
			if(!peticionManager.updatePeticion(peticion)){
				// Lanzar error
				LOG.error("Actualizar Auditoría: Error al actualizar el estado");
				throw new ModelException("Error al actualizar el estado", 203);
			}
			
			// Obtenemos la auditoria a actualizar
			Auditoria auditoria = auditoriaManager.getAuditoriaById(Long.valueOf(idAuditoria));
			
			if(auditoria==null){
				// Lanzar error
				LOG.error("Actualizar auditoría: Error de Base de Datos");
				throw new ModelException("Error de Base de Datos", 501);
			}
			
			// Actualizamos la petición
			Calendar calendar = Calendar.getInstance();
			Date fecha = calendar.getTime();
			auditoria.setFechaActualizacion(fecha);
			if(!auditoriaManager.updateAuditoria(auditoria)){
				// Lanzar error
				LOG.error("Actualizar auditoría: Error de Base de Datos");
				throw new ModelException("Error de Base de Datos", 501);
			}
			
			String xmlPeticionOriginal = eventContext.getMessage().getOutboundProperty("xmlRespuesta"); 
			Document documentOriginal = XMLUtils.xml2doc(xmlPeticionOriginal, Charset.forName("UTF-8"));
			
			SoapPayload.class.cast(eventContext.getMessage().getPayload()).setSoapMessage(documentOriginal);

		}catch(ModelException e){
			//Lanzar error
			throw new ModelException(e.getMensaje(), e.getCodigo());
		}catch(Exception e){
			//Lanzar error
			LOG.error("Actualizar auditoría: Error de sistema Identificar Auditar", e);
			throw new ModelException("Error de sistema Identificar Auditar", 502);
		}

		LOG.debug("Fin actualización de auditoría");
		return eventContext.getMessage();
	}
}
