package es.minhap.plataformamensajeria.iop.beans.entity;

/**
 * Bean para destinatarios mensajes utilizado en los QueryExecutors
 * 
 * 
 * @author everis
 *
 */
public class DestinatariosMensajesBean {
	
	private String uim;
	
	private String estadoActualLote;
	
	private String estadoFinalMensaje;
	
	private Long estadoFinalMensajeId;
	
	private Long ultimoHistoricoId;

	/**
	 * @return the uim
	 */
	public String getUim() {
		return uim;
	}

	/**
	 * @param uim the uim to set
	 */
	public void setUim(String uim) {
		this.uim = uim;
	}

	/**
	 * @return the estadoActualLote
	 */
	public String getEstadoActualLote() {
		return estadoActualLote;
	}

	/**
	 * @param estadoActualLote the estadoActualLote to set
	 */
	public void setEstadoActualLote(String estadoActualLote) {
		this.estadoActualLote = estadoActualLote;
	}

	/**
	 * @return the estadoFinalMensaje
	 */
	public String getEstadoFinalMensaje() {
		return estadoFinalMensaje;
	}

	/**
	 * @param estadoFinalMensaje the estadoFinalMensaje to set
	 */
	public void setEstadoFinalMensaje(String estadoFinalMensaje) {
		this.estadoFinalMensaje = estadoFinalMensaje;
	}

	/**
	 * @return the estadoFinalMensajeId
	 */
	public Long getEstadoFinalMensajeId() {
		return estadoFinalMensajeId;
	}

	/**
	 * @param estadoFinalMensajeId the estadoFinalMensajeId to set
	 */
	public void setEstadoFinalMensajeId(Long estadoFinalMensajeId) {
		this.estadoFinalMensajeId = estadoFinalMensajeId;
	}

	/**
	 * @return the ultimoHistoricoId
	 */
	public Long getUltimoHistoricoId() {
		return ultimoHistoricoId;
	}

	/**
	 * @param ultimoHistoricoId the ultimoHistoricoId to set
	 */
	public void setUltimoHistoricoId(Long ultimoHistoricoId) {
		this.ultimoHistoricoId = ultimoHistoricoId;
	}

}
