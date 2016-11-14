package es.minhap.plataformamensajeria.iop.business;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.Transactional;

import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.business.common.ICommonUtilitiesService;
import es.minhap.plataformamensajeria.iop.business.sendmail.ISendMessageService;
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


	private static Logger LOG = Logger.getLogger(SIMMessageDispatcher.class);

	@Resource
	private ICommonUtilitiesService commonUtilitiesService;

	@Resource
	private ISendMessageService sendMessageService;

	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Transactional(noRollbackFor=RuntimeException.class)
	public void dispatchMessage(MensajeJMS msg) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		LOG.info("Procesando mensaje: " + msg.getIdMensaje());
		Long mensajeId = Long.parseLong(msg.getIdMensaje());
		Long destinatarioMensajeId = null;
		Integer canal = Integer.parseInt(msg.getIdCanal());
		try {
			destinatarioMensajeId = Long.parseLong(msg.getDestinatarioMensajeId());
		} catch (NumberFormatException e) {
			destinatarioMensajeId = null;
		}

		try {
			
			//Se comprueba que el mensaje no este anulado entonces se procesa
			TblMensajes mensaje = getCommonUtilitiesService().getMensaje(mensajeId);
			if (mensaje.getEstadoactual() != null && !mensaje.getEstadoactual().equals(ps.getMessage("constantes.ESTADO_ANULADO", null))) {
				switch (canal) {
				case 1:
					getSendMessageService().postMail(mensajeId, msg.getDestinatarioMensajeId());
					break;
				case 2:
					getSendMessageService().postSMS(mensajeId, destinatarioMensajeId, msg.getCodOrganismo(),
							msg.getUsuarioAplicacion(), msg.getPasswordAplicacion());
					break;
				case 4:
					getSendMessageService().postNotificacionPush(mensajeId, destinatarioMensajeId);
					break;
				case 3:
					getSendMessageService().postRecepcionSMS(mensajeId, destinatarioMensajeId);
					break;
				default:
					LOG.error(" [SIMMessageDispatcher] Error procesando el mensaje: canal indefinido");
					throw new RuntimeException("");
				}
			}
		} catch (Exception e) {
			LOG.error("[SIMMessageDispatcher] Error enviando el mensaje ----->" + mensajeId);
			throw new RuntimeException("");
		}
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
