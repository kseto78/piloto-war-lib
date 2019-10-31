package es.minhap.plataformamensajeria.iop.services.recepcion;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

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
import es.minhap.plataformamensajeria.iop.misim.manager.ErroresManager;
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
	
	protected static final String R_CONST_1 = "plataformaErrores.recepcionSMS.TAG_ERROR_NO_SERVICIO";

	protected static final String R_CONST_2 = "plataformaErrores.generales.STATUS_OK";

	protected static final String R_CONST_3 = "plataformaErrores.generales.STATUSTEXT_OK";

	protected static final String R_CONST_4 = "plataformaErrores.generales.DETAILS_OK";

	protected static final String R_CONST_5 = "plataformaErrores.recepcionSMS.TAG_MENSAJE_KO_GENERAL";

	protected static final String R_CONST_6 = "constantes.errores.devolucion.error1";

	protected static final String R_CONST_7 = "plataformaErrores.recepcionSMS.TAG_MENSAJE_KO_NO_SERVICIO";

	protected static final String R_CONST_8 = "constantes.errores.devolucion.error4";

	protected static final String R_CONST_9 = "constantes.errores.devolucion.error5";

	protected static final String R_CONST_10 = "constantes.errores.devolucion.error2";

	private static final  Logger LOG = LoggerFactory.getLogger(RecepcionMensajesServiceImpl.class);
	
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
	
	@Autowired
	private ErroresManager erroresManager;
	
	
		/**
	 * @param RecibirSMSRequest
	 * 
	 * @return RecibirSMSResponse
	 */
	@Override
	public RecibirSMSResponse recibirSMS(RecibirSMSRequest recibirSMSRequest) {
        
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

        RecibirSMSResponse retorno = new RecibirSMSResponse();
        retorno.setStatus(new ResponseStatusType());
        
        Integer idLote=null;
        
        RecepcionSMSBean envioSMS = new RecepcionSMSBean();
        String errorActiveMq = ps.getMessage("conexion.ERRORACTIVEMQ", null, "[ERROR-ACTIVEMQ]");
        String statusTextKO = ps.getMessage("plataformaErrores.generales.STATUSTEXT_KO",null);
        String errorRecMensaje = ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_RECEPCION_MENSAJE",null);
        String estadoAnulado = ps.getMessage("constantes.ESTADO_ANULADO", null);
		String descripcionErrorActiveMq = ps.getMessage("plataformaErrores.envioPremiumAEAT.DESC_ERROR_ACTIVEMQ", null);
        String prefijoSMS= null;
        boolean premium = false;
        Integer idMensaje = null;
        
        int activeMQ = 2;
        
        try {
        	
        	boolean peticionCorrecta = evaluarPeticion(recibirSMSRequest);
        	if (null != recibirSMSRequest.getSMSText() && !recibirSMSRequest.getSMSText().isEmpty()){
        		String cuerpo = recibirSMSRequest.getSMSText();
        		if(cuerpo.indexOf(' ')!=-1) {
        			prefijoSMS = cuerpo.substring(0, cuerpo.indexOf(' '));
        		} else {
        			retorno.getStatus().setStatusCode(ps.getMessage(R_CONST_1,null));
        			retorno.getStatus().setStatusText(statusTextKO);
        			retorno.getStatus().setDetails(ps.getMessage(R_CONST_7,null));
        			return retorno;
        		}
        	}else{
        		peticionCorrecta = false;
        	}
        	        	
        	if(peticionCorrecta){
        		
        		envioSMS = lotesManager.buscarInfoLote(recibirSMSRequest.getRecipient(), recibirSMSRequest.getUser(), recibirSMSRequest.getPassword(), prefijoSMS);
            	//Se valida que se haya recuperado correctamente la informacion de base de datos
        		if (!ps.getMessage(R_CONST_6,null).equals(envioSMS.getServicio()) &&
        			!ps.getMessage(R_CONST_10,null).equals(envioSMS.getServicio()) &&
        			!ps.getMessage(R_CONST_8,null).equals(envioSMS.getServicio()) &&
        			!ps.getMessage(R_CONST_9,null).equals(envioSMS.getServicio())){
        		
            		idLote = lotesManager.insertarLote(Long.parseLong(envioSMS.getServicio()), envioSMS.getNombreLote(), envioSMS.getUserAplicacion(), envioSMS.getPasswordAplicacion(), envioSMS.getCodOrganismo());
        			retorno.setIdLote(idLote);
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
        			
        			idMensaje = mensajesManager.insertarMensajeRecepcionSMS(Long.valueOf(idLote), recibirSMSRequest.getSMSText(), recibirSMSRequest.getMessageId(), recibirSMSRequest.getSender(), 
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
        				retorno.getStatus().setStatusCode(ps.getMessage(R_CONST_2,null));
            			retorno.getStatus().setStatusText(ps.getMessage(R_CONST_3,null));
            			retorno.getStatus().setDetails(ps.getMessage(R_CONST_4,null));
            			
            			MensajeJMS mensajeJms = new MensajeJMS();
						mensajeJms.setIdMensaje(idMensaje.toString());
//						mensajeJms.setIdExterno(recibirSMSRequest.getMessageId());
						mensajeJms.setIdCanal(ps.getMessage("constantes.CANAL_RECEPCION_SMS", null));
						mensajeJms.setIdLote(idLote.toString());
						mensajeJms.setDestinatarioMensajeId(desMensaje.toString());
						Long maxRetries;
						TblServicios servicio = serviciosManager.getServicio(Long.parseLong(envioSMS.getServicio()));
						if(servicio.getPremium()!=null && servicio.getPremium()) {
							premium = true;
						}
						if (servicio.getNumeroMaxReenvios() != null && servicio.getNumeroMaxReenvios() >= 0) {
							maxRetries = servicio.getNumeroMaxReenvios().longValue();
						} else {
							maxRetries = Long.parseLong(ps.getMessage("constantes.servicio.numMaxReenvios", null));
						}
						sender.send(mensajeJms, maxRetries, servicio.getServicioid().toString(), premium);
						//Comprobamos que si ya se ha actualizado la tabla de errores a true
						activeMQ = 1;//true
						
        			}
    	        	
            	} else if(ps.getMessage(R_CONST_6,null).equals(envioSMS.getServicio())) {
            		retorno.getStatus().setStatusCode(ps.getMessage(R_CONST_1,null));
        			retorno.getStatus().setStatusText(statusTextKO);
        			retorno.getStatus().setDetails(ps.getMessage(R_CONST_7,null));
            	} else if(ps.getMessage(R_CONST_10,null).equals(envioSMS.getServicio())){
            		retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_SERVICIO_DUPLICADO",null));
        			retorno.getStatus().setStatusText(statusTextKO);
        			retorno.getStatus().setDetails(ps.getMessage("plataformaErrores.recepcionSMS.TAG_MENSAJE_KO_SERVICIO_DUPLICADO",null));
            	} else if(ps.getMessage(R_CONST_8,null).equals(envioSMS.getServicio())){
            		retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_VALIDACION_USER_PASSWORD",null));
            		retorno.getStatus().setStatusText(statusTextKO);
        			retorno.getStatus().setDetails(ps.getMessage("plataformaErrores.recepcionSMS.TAG_MENSAJE_KO_VALIDACION_USER_PASSWORD",null));
            	} else if(ps.getMessage(R_CONST_9,null).equals(envioSMS.getServicio())){
            		retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_VALIDACION_USER_PASSWORD_VARIOS_SERVIDORES",null));
            		retorno.getStatus().setStatusText(statusTextKO);
        			retorno.getStatus().setDetails(ps.getMessage("plataformaErrores.recepcionSMS.TAG_MENSAJE_KO_VALIDACION_USER_PASSWORD_VARIOS_SERVIDORES",null));
            	}   

        	} else {
        		retorno.getStatus().setStatusCode(ps.getMessage("plataformaErrores.recepcionSMS.TAG_ERROR_PARAMETROS_PETICION",null));
    			retorno.getStatus().setStatusText(statusTextKO);
    			retorno.getStatus().setDetails(ps.getMessage("plataformaErrores.recepcionSMS.TAG_MENSAJE_KO_PARAMETROS_PETICION",null));
        	}
        	
		}catch (CannotCreateTransactionException e) {
			//Comprobamos que si ya se ha actualizado la tabla de errores a false
			activeMQ = 0;//false
			LOG.error(errorActiveMq+" IRecepcionMensajesServiceImpl.recibirSMS --Error ActiveMq--", e);
			
			if (premium){
				mensajesManager.setEstadoMensaje(idMensaje.longValue(), estadoAnulado, descripcionErrorActiveMq, 
							false, null, null, envioSMS.getUserAplicacion(), null);
				retorno.getStatus().setStatusCode(errorRecMensaje);
				retorno.getStatus().setStatusText(statusTextKO);
				retorno.getStatus().setDetails(ps.getMessage(R_CONST_5,null));
			}else{
				retorno.getStatus().setStatusCode(ps.getMessage(R_CONST_2,null));
				retorno.getStatus().setStatusText(ps.getMessage(R_CONST_3,null));
				retorno.getStatus().setDetails(ps.getMessage(R_CONST_4,null));	
			}
			
		}catch (Exception e) {
			LOG.error("IRecepcionMensajesServiceImpl.recibirSMS", e);
			retorno.getStatus().setStatusCode(errorRecMensaje);
			retorno.getStatus().setStatusText(statusTextKO);
			retorno.getStatus().setDetails(ps.getMessage(R_CONST_5,null));
		}finally{
//			Comprobamos que si ya se ha actualizado la tabla de errores
			LOG.debug("Estamos en RecepcionMensajesServiceImpl-recibirSMS");					
			switch (activeMQ) {
			case 0:
				erroresManager.comprobarActiveMqActivo(false);
				break;
			case 1:
				erroresManager.comprobarActiveMqActivo(true);
				break;
			}
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
    	
    	PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String telefonoExcepcion = ps.getMessage("validarTelefono.TelefonoExcepcion", null, "");
    	
    	if(evaluarParametro(recibirSMSRequest.getUser()) && evaluarParametro(recibirSMSRequest.getPassword())
    			&& evaluarParametro(recibirSMSRequest.getSender()) && evaluarParametro(recibirSMSRequest.getRecipient())
    			&& evaluarParametro(recibirSMSRequest.getMessageId()) ){
    		recibirSMSRequest.setRecipient(Utils.eliminarPrefijo(recibirSMSRequest.getRecipient(), telefonoExcepcion));
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    
    private boolean evaluarParametro(String parametro){
    	
    	return null!=parametro && !parametro.isEmpty();
  	
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
