package es.minhap.plataformamensajeria.iop.business.refreshstatus;



/**
 * 
 * @author everis
 *
 */
public interface IRefreshStatusService {

	/**
	 * Logica realización refresh status (consulta de estado)
	 * 
	 * @param mensajeId
	 * @param destinatarioMensajeId
	 */
    public void refreshStatus(Long mensajeId, Long destinatarioMensajeId) throws Exception;
	

}
