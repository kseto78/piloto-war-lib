package es.minhap.plataformamensajeria.iop.business;

import javax.annotation.Resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.plataformamensajeria.iop.business.refreshstatus.IRefreshStatusService;
/**
 * 
 * @author everis
 *
 */
public class SIMRefreshStatusDispatcher {
	
	private static final Logger LOG = LoggerFactory.getLogger(SIMRefreshStatusDispatcher.class);
	
	@Resource(name = "refreshStatusServiceImpl")
	private IRefreshStatusService refreshStatusService;
	
	/**
	 * Consumidor de la cola de consulta de estado (RefreshStatus)
	 * @param msg
	 */
	public void dispatchMessage(MensajeJMS msg){
		LOG.info("Procesando mensaje de Refresh Status: " + msg.getIdMensaje());
		Long mensajeId = Long.parseLong(msg.getIdMensaje());
		Long destinatarioMensajeId = null;
		try {
			destinatarioMensajeId = Long.parseLong(msg.getDestinatarioMensajeId());
		} catch (NumberFormatException e) {
			destinatarioMensajeId = null;
		}
		try {
			getRefreshStatusService().refreshStatus(mensajeId, destinatarioMensajeId);
		} catch (Exception e) {
			//Se lanza una excepción para volver a encolar el mensaje.
			LOG.error("[SIMRefreshStatusDispatcher] Error procesando el mensaje de Refresh Status", e);
			throw new RuntimeException("Exception");
		}
	}

	/**
	 * @return the refreshStatusService
	 */
	public IRefreshStatusService getRefreshStatusService() {
		return refreshStatusService;
	}

	/**
	 * @param refreshStatusService the refreshStatusService to set
	 */
	public void setRefreshStatusService(IRefreshStatusService refreshStatusService) {
		this.refreshStatusService = refreshStatusService;
	}
	
}
