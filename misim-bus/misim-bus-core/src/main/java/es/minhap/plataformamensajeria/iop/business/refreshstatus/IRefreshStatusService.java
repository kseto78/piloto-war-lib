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
	 * @param loteId 
	 * @param aplicacionPremium 
	 * @param usuarioAplicacion 
	 */
    public void refreshStatus(Long mensajeId, Long destinatarioMensajeId, Long loteId, String aplicacionPremium, String usuarioAplicacion) throws Exception;
	

}
