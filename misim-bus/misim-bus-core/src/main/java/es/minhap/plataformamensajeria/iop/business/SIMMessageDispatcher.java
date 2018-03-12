package es.minhap.plataformamensajeria.iop.business;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.Transactional;

import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.business.common.ICommonUtilitiesService;
import es.minhap.plataformamensajeria.iop.business.sendmail.ISendMessageService;
import es.minhap.plataformamensajeria.iop.manager.TblHistoricosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.sim.model.TblMensajes;

/**
 * Clase encargada de procesar los mensaje pertenecientes de la cola ActiveMQ y
 * por tanto capaz de decidir que tipo es:
 * <ul>
 * <li>Email</li>
 * <li>SMS</li>
 * <li>Notificacion Push</li>
 * <li>Recepcion SMS</li>
 * </ul>
 * 
 * Para lanzarlo al servidor correspondiente
 * 
 * @author everis
 * 
 */
public class SIMMessageDispatcher {


	private static Logger LOG = LoggerFactory.getLogger(SIMMessageDispatcher.class);

	@Resource
	private ICommonUtilitiesService commonUtilitiesService;

	@Resource
	private ISendMessageService sendMessageService;
	
	@Resource(name = "TblHistoricosManagerImpl")
	private TblHistoricosManager tblHistoricosManager;
	
	@Resource(name = "TblMensajesManagerImpl")
	private TblMensajesManager mensajesManager;

	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Transactional(noRollbackFor=RuntimeException.class)
	public void dispatchMessage(MensajeJMS msg) {
		
		Long mensajeId = null;
		String aplicacionPremium = null;
		String errorClave = "";
		try {
			PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
			String mensajeAEAT= ps.getMessage("aeat.usuario.sms", null, "aeat");
			String mensajeGISS= ps.getMessage("giss.usuario.sms", null, "giss");
			String aplicacionAEAT= ps.getMessage("aeat.aplicacion", null, "AEAT");
			String aplicacionGISS= ps.getMessage("giss.aplicacion", null, "GISS");
			errorClave = ps.getMessage("clave.ERRORCLAVE.AEAT", null, "[ERROR-CL@VE]:");
			
			Long estadoId = Long.parseLong(ps.getMessage("constantes.ESTADOID_ENVIADO", null,  "1"));
			LOG.info("Procesando mensaje: " + msg.getIdMensaje());
			mensajeId = Long.parseLong(msg.getIdMensaje());
			Long idLote = Long.parseLong(msg.getIdLote());
			Long destinatarioMensajeId = null;
			Integer canal = Integer.parseInt(msg.getIdCanal());
			try {
				destinatarioMensajeId = Long.parseLong(msg.getDestinatarioMensajeId());
			} catch (NumberFormatException e) {
				destinatarioMensajeId = null;
			}
			
			if (null != msg.getUsuarioAplicacion()){
				if (msg.getUsuarioAplicacion().contains(mensajeAEAT) ){
					aplicacionPremium = aplicacionAEAT;
				}else if (msg.getUsuarioAplicacion().contains(mensajeGISS)){
					aplicacionPremium = aplicacionGISS;
				}
			}
			//Se comprueba que el mensaje no este anulado entonces se procesa
			TblMensajes mensaje = getCommonUtilitiesService().getMensaje(mensajeId);
			
			//comprobamos si el mensaje en su historico ya tiene un estado enviado.
			Boolean yaEnviado = tblHistoricosManager.checkMensajeYaEnviado(mensajeId, destinatarioMensajeId, estadoId);
			if (yaEnviado){
				LOG.info("El mensaje " + msg.getIdMensaje() + " con destinatario: " + msg.getDestinatarioMensajeId() + " ya ha sido enviado anteriormente");
			}

			//comprobamos si el mensaje esta vigente y no caducado.
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(mensaje.getFechacreacion());
			
			if(msg.getCaducidad()!= null){
				calendar.add(Calendar.MINUTE, Integer.valueOf(msg.getCaducidad()));
			}
			
			Date fechaCaducidad = calendar.getTime();
			Boolean vigente = true;
			
			if(msg.getCaducidad() != null && Integer.valueOf(msg.getCaducidad()) > 0 && fechaCaducidad.compareTo(new Date()) < 0){
					mensajesManager.setEstadoMensaje(Long.valueOf(msg.getIdMensaje()), 
							ps.getMessage("constantes.ESTADO_ANULADO", null), "SMS_ID: " + msg.getIdMensaje()
									+ ". Error: El mensaje ha caducado", false, Long.valueOf(msg.getDestinatarioMensajeId()), null, ps.getMessage("constantes.usuarioActiveMQ", null), null);
					vigente = false;
			}
			
			if (mensaje.getEstadoactual() != null && !mensaje.getEstadoactual().equals(ps.getMessage("constantes.ESTADO_ANULADO", null)) 
					&& !yaEnviado && vigente) {
				switch (canal) {
				case 1:
					getSendMessageService().postMail(mensajeId, msg.getDestinatarioMensajeId());
					break;
				case 2:
					getSendMessageService().postSMS(mensajeId, idLote, destinatarioMensajeId, msg.getCodOrganismo(),
							msg.getUsuarioAplicacion(), msg.getPasswordAplicacion(), aplicacionPremium);
					break;
				case 4:
					getSendMessageService().postNotificacionPush(mensajeId,idLote, destinatarioMensajeId);
					break;
				case 3:
					getSendMessageService().postRecepcionSMS(mensajeId, destinatarioMensajeId);
					break;
				case 5:
					getSendMessageService().postNotificacionWebPush(mensajeId,idLote, destinatarioMensajeId);
					break;
				default:
					LOG.error((null != aplicacionPremium)? errorClave : "" + " [SIMMessageDispatcher] Error procesando el mensaje: canal indefinido");
					throw new RuntimeException("");
				}
			}
		} catch (Exception e) {
			if (null != aplicacionPremium){
				LOG.error(errorClave + "[SIMMessageDispatcher] Error enviando el mensaje ----->" + mensajeId, e);
			} else {
				LOG.error("[SIMMessageDispatcher] Error enviando el mensaje ----->" + mensajeId, e);
			}
			throw new RuntimeException("");
		}
		
//		LOG.info("Mensaje procesado: " + msg.getIdMensaje());
	}

	/**
	 * @return the commonUtilitiesService
	 */
	public ICommonUtilitiesService getCommonUtilitiesService() {
		return commonUtilitiesService;
	}

	/**
	 * @param commonUtilitiesService the commonUtilitiesService to set
	 */
	public void setCommonUtilitiesService(ICommonUtilitiesService commonUtilitiesService) {
		this.commonUtilitiesService = commonUtilitiesService;
	}

	/**
	 * @return the sendMessageService
	 */
	public ISendMessageService getSendMessageService() {
		return sendMessageService;
	}

	/**
	 * @param sendMessageService the sendMessageService to set
	 */
	public void setSendMessageService(ISendMessageService sendMessageService) {
		this.sendMessageService = sendMessageService;
	}
}
