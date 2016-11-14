package es.minhap.plataformamensajeria.iop.services.recepcion;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.map.sim.jms.sender.SIMMessageSender;
import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.RecepcionSMSBean;
import es.minhap.plataformamensajeria.iop.beans.RecibirSMSRequest;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblEstadosManager;
import es.minhap.plataformamensajeria.iop.manager.TblHistoricosManager;
import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.plataformamensajeria.iop.util.WSPlataformaErrors;
import es.minhap.sim.model.TblServicios;


/**
 * 
 * @author EVERIS
 *
 */
@Service("recepcionMensajesImpl")                 
public class RecepcionMensajesServiceImpl implements IRecepcionMensajesService {
	
	private static final  Logger LOG = Logger.getLogger(RecepcionMensajesServiceImpl.class);
	
	@Resource
	private TblLotesEnviosManager lotesManager;
	
	@Resource
	private TblMensajesManager mensajesManager;
	
	@Resource
	private TblDestinatariosMensajesManager destinatariosMensajesManager;
	
	@Resource
	private TblHistoricosManager historicosManager;
	
	@Resource
	private TblServiciosManager serviciosManager;
	
	@Resource
	private TblEstadosManager estadosManager;
	
	@Resource(name = "messageSender")
	private SIMMessageSender sender;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	
		/**
	 * @param RecibirSMSRequest
	 * 
	 * @return RecibirSMSResponse
	 */
	public RecibirSMSResponse recibirSMS(RecibirSMSRequest recibirSMSRequest) {
        
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

        RecibirSMSResponse retorno = new RecibirSMSResponse();
        retorno.setStatus(new ResponseStatusType());
        
        Integer idLote=null;
        
        RecepcionSMSBean envioSMS = new RecepcionSMSBean();
        String statusTextKO = ps.getMessage("plataformaErrores.generales.STATUSTEXT_KO",null);
        String errorRecMensaje = ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_RECEPCION_MENSAJE",null);
    
        
        try {
        	
        	boolean peticionCorrecta = evaluarPeticion(recibirSMSRequest);
        	        	
        	if(peticionCorrecta){
        		
        		envioSMS = lotesManager.buscarInfoLote(recibirSMSRequest.getRecipient(), recibirSMSRequest.getUser(), recibirSMSRequest.getPassword());
            	//Se valida que se haya recuperado correctamente la informacion de base de datos
        		if (!ps.getMessage("constantes.errores.devolucion.error1",null).equals(envioSMS.getServicio()) &&
        			!ps.getMessage("constantes.errores.devolucion.error2",null).equals(envioSMS.getServicio()) &&
        			!ps.getMessage("constantes.errores.devolucion.error4",null).equals(envioSMS.getServicio()) &&
        			!ps.getMessage("constantes.errores.devolucion.error5",null).equals(envioSMS.getServicio())){
        		
            		idLote = lotesManager.insertarLote(Long.parseLong(envioSMS.getServicio()), envioSMS.getNombreLote(), envioSMS.getUserAplicacion(), envioSMS.getPasswordAplicacion());
        			String errorCrearLote = WSPlataformaErrors.getErrorCrearLote(idLote);
        			if(errorCrearLote!=null){
        				Integer value = Integer.parseInt(errorCrearLote);
        				switch (value) {
        				case -1:
        					retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_AUTENTICACION_LOTE",null));
        					break;
        				case -2:
        					retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_SERVICIO_INCORRECTO",null));
        					break;
        				case -3:
        					retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_SERVICIO_INACTIVO",null));
        					break;
        				case -10:
        					retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_LOTE",null));
        					break;
        				default:
        					retorno.getStatus().setStatusCode(errorRecMensaje);
        					break;
        				}
        				retorno.getStatus().setStatusText(statusTextKO);
        				retorno.getStatus().setDetails(WSPlataformaErrors.getErrorCrearLote(idLote));
        				return retorno;
        			}
        			
        			Integer idMensaje = mensajesManager.insertarMensajeRecepcionSMS(Long.valueOf(idLote), recibirSMSRequest.getSMSText(), recibirSMSRequest.getMessageId(), recibirSMSRequest.getSender(), 
        					envioSMS.getUserAplicacion(), envioSMS.getPasswordAplicacion());
        			
        			String errorCrearSMS = WSPlataformaErrors.getErrorCrearSMS(idMensaje);
        			if(errorCrearSMS!=null){

        				switch (idMensaje) {
        				case -1:
        					retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_AUTENTICACION_SMS",null));
        					break;
        				case -2:
        					retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_CANAL_SMS",null));
        					break;
        				case -3:
        					retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_DESTINATARIO_SMS",null));
        					break;
        				case -10:
        					retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_SMS",null));
        					break;
        				default:
        					retorno.getStatus().setStatusCode(errorRecMensaje);
        					break;
        				}
        				retorno.getStatus().setStatusText(statusTextKO);
        				retorno.getStatus().setDetails(errorCrearSMS);
        			}else{
        				Long desMensaje =  destinatariosMensajesManager.insertarDestinatarioMensaje(idMensaje.toString(),
        						recibirSMSRequest.getSender(), recibirSMSRequest.getMessageId(), envioSMS.getUserAplicacion());
        				Long estadoId = estadosManager.getEstadoByName(ps.getMessage("constantes.ESTADO_PENDIENTE", null)).getEstadoid();
        				historicosManager.creaHistorico(idMensaje.longValue(), desMensaje, estadoId, null, null, null, envioSMS.getUserAplicacion());
        				retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.generales.STATUS_OK",null));
            			retorno.getStatus().setStatusText(ps.getMessage("plataformaErrores.generales.STATUSTEXT_OK",null));
            			retorno.getStatus().setDetails(ps.getMessage("plataformaErrores.generales.DETAILS_OK",null));
            			
            			MensajeJMS mensajeJms = new MensajeJMS();
						mensajeJms.setIdMensaje(idMensaje.toString());
						mensajeJms.setIdExterno(recibirSMSRequest.getMessageId());
						mensajeJms.setIdCanal(ps.getMessage("constantes.CANAL_RECEPCION_SMS", null));
						mensajeJms.setDestinatarioMensajeId(desMensaje.toString());
						Long maxRetries = null;
						TblServicios servicio = serviciosManager.getServicio(Long.parseLong(envioSMS.getServicio()));
						if (servicio.getNumeroMaxReenvios() != null && servicio.getNumeroMaxReenvios() > 0) {
							maxRetries = servicio.getNumeroMaxReenvios().longValue();
						} else {
							maxRetries = Long.parseLong(ps.getMessage("constantes.servicio.numMaxReenvios", null));
						}
						sender.send(mensajeJms, maxRetries, servicio.getNombre(), false);
        			}
    	        	
            	} else if(ps.getMessage("constantes.errores.devolucion.error1",null).equals(envioSMS.getServicio())) {
            		retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_NO_SERVICIO",null));
        			retorno.getStatus().setStatusText(statusTextKO);
        			retorno.getStatus().setDetails(ps.getMessage("plataformaErrores.recepcionSMS.TAG_MENSAJE_KO_NO_SERVICIO",null));
            	} else if(ps.getMessage("constantes.errores.devolucion.error2",null).equals(envioSMS.getServicio())){
            		retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_SERVICIO_DUPLICADO",null));
        			retorno.getStatus().setStatusText(statusTextKO);
        			retorno.getStatus().setDetails(ps.getMessage("plataformaErrores.recepcionSMS.TAG_MENSAJE_KO_SERVICIO_DUPLICADO",null));
            	} else if(ps.getMessage("constantes.errores.devolucion.error4",null).equals(envioSMS.getServicio())){
            		retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_VALIDACION_USER_PASSWORD",null));
            		retorno.getStatus().setStatusText(statusTextKO);
        			retorno.getStatus().setDetails(ps.getMessage("plataformaErrores.recepcionSMS.TAG_MENSAJE_KO_VALIDACION_USER_PASSWORD",null));
            	} else if(ps.getMessage("constantes.errores.devolucion.error5",null).equals(envioSMS.getServicio())){
            		retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_VALIDACION_USER_PASSWORD_VARIOS_SERVIDORES",null));
            		retorno.getStatus().setStatusText(statusTextKO);
        			retorno.getStatus().setDetails(ps.getMessage("plataformaErrores.recepcionSMS.TAG_MENSAJE_KO_VALIDACION_USER_PASSWORD_VARIOS_SERVIDORES",null));
            	}   

        	} else {
        		retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_PARAMETROS_PETICION",null));
    			retorno.getStatus().setStatusText(statusTextKO);
    			retorno.getStatus().setDetails(ps.getMessage("plataformaErrores.recepcionSMS.TAG_MENSAJE_KO_PARAMETROS_PETICION",null));
        	}
        	
		} catch (Exception e) {
			LOG.error("IRecepcionMensajesServiceImpl.recibirSMS", e);
			retorno.getStatus().setStatusCode(errorRecMensaje);
			retorno.getStatus().setStatusText(statusTextKO);
			retorno.getStatus().setDetails(ps.getMessage("plataformaErrores.recepcionSMS.TAG_MENSAJE_KO_GENERAL",null));
		}
		return retorno;
    }

	

	@Override
	public String recibirSMSXML(RecibirSMSRequest recepcionMensajes) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		RecibirSMSResponse respuesta = this.recibirSMS(recepcionMensajes);
		String res = "";
		try {
			res = Utils.convertToUTF8(respuesta.toXML());
		} catch (PlataformaBusinessException e) {
			LOG.error("IRecepcionMensajesServiceImpl.recibirSMSXML", e);
			res = Utils.convertToUTF8(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_GENERANDO_RESPUESTA_XML",null));
		}
		return res;
	}
    
    private boolean evaluarPeticion(RecibirSMSRequest recibirSMSRequest){
    	
    	if(evaluarParametro(recibirSMSRequest.getUser()) && evaluarParametro(recibirSMSRequest.getPassword())
    			&& evaluarParametro(recibirSMSRequest.getSender()) && evaluarParametro(recibirSMSRequest.getRecipient())
    			&& evaluarParametro(recibirSMSRequest.getMessageId()) && evaluarParametro(recibirSMSRequest.getMessageId())){
    		recibirSMSRequest.setRecipient(Utils.eliminarPrefijo(recibirSMSRequest.getRecipient()));
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    
    private boolean evaluarParametro(String parametro){
    	
    	if(null!=parametro && !parametro.isEmpty()){
    		return true;
    	} 
    	return false;
  	
    }



	/**
	 * @return the lotesManager
	 */
	public TblLotesEnviosManager getLotesManager() {
		return lotesManager;
	}



	/**
	 * @param lotesManager the lotesManager to set
	 */
	public void setLotesManager(TblLotesEnviosManager lotesManager) {
		this.lotesManager = lotesManager;
	}



	/**
	 * @return the mensajesManager
	 */
	public TblMensajesManager getMensajesManager() {
		return mensajesManager;
	}



	/**
	 * @param mensajesManager the mensajesManager to set
	 */
	public void setMensajesManager(TblMensajesManager mensajesManager) {
		this.mensajesManager = mensajesManager;
	}



	/**
	 * @return the destinatariosMensajesManager
	 */
	public TblDestinatariosMensajesManager getDestinatariosMensajesManager() {
		return destinatariosMensajesManager;
	}



	/**
	 * @param destinatariosMensajesManager the destinatariosMensajesManager to set
	 */
	public void setDestinatariosMensajesManager(TblDestinatariosMensajesManager destinatariosMensajesManager) {
		this.destinatariosMensajesManager = destinatariosMensajesManager;
	}



	/**
	 * @return the historicosManager
	 */
	public TblHistoricosManager getHistoricosManager() {
		return historicosManager;
	}



	/**
	 * @param historicosManager the historicosManager to set
	 */
	public void setHistoricosManager(TblHistoricosManager historicosManager) {
		this.historicosManager = historicosManager;
	}



	/**
	 * @return the serviciosManager
	 */
	public TblServiciosManager getServiciosManager() {
		return serviciosManager;
	}



	/**
	 * @param serviciosManager the serviciosManager to set
	 */
	public void setServiciosManager(TblServiciosManager serviciosManager) {
		this.serviciosManager = serviciosManager;
	}



	/**
	 * @return the estadosManager
	 */
	public TblEstadosManager getEstadosManager() {
		return estadosManager;
	}



	/**
	 * @param estadosManager the estadosManager to set
	 */
	public void setEstadosManager(TblEstadosManager estadosManager) {
		this.estadosManager = estadosManager;
	}



	/**
	 * @return the sender
	 */
	public SIMMessageSender getSender() {
		return sender;
	}



	/**
	 * @param sender the sender to set
	 */
	public void setSender(SIMMessageSender sender) {
		this.sender = sender;
	}



	/**
	 * @return the reloadableResourceBundleMessageSource
	 */
	public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		return reloadableResourceBundleMessageSource;
	}



	/**
	 * @param reloadableResourceBundleMessageSource the reloadableResourceBundleMessageSource to set
	 */
	public void setReloadableResourceBundleMessageSource(
			ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}

}
