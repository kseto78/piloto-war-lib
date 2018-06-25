package es.minhap.plataformamensajeria.iop.beans.entity;

/**
 * Bean para destinatarios mensajes utilizado en los QueryExecutors
 * 
 * 
 * @author everis
 *
 */
public class DetallesMensajesBean {
	
	private String cabecera;
	
	private String cuerpo;
	
	private String tipoCuerpo;
	
	private String tipoCodificacion;
	
	private String destinatario;
	
	private Long destinatariosMensajes;

	/**
	 * @return the cabecera
	 */
	public String getCabecera() {
		return cabecera;
	}

	/**
	 * @param cabecera the cabecera to set
	 */
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * @return the cuerpo
	 */
	public String getCuerpo() {
		return cuerpo;
	}

	/**
	 * @param cuerpo the cuerpo to set
	 */
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	/**
	 * @return the tipoCuerpo
	 */
	public String getTipoCuerpo() {
		return tipoCuerpo;
	}

	/**
	 * @param tipoCuerpo the tipoCuerpo to set
	 */
	public void setTipoCuerpo(String tipoCuerpo) {
		this.tipoCuerpo = tipoCuerpo;
	}

	/**
	 * @return the tipoCodificacion
	 */
	public String getTipoCodificacion() {
		return tipoCodificacion;
	}

	/**
	 * @param tipoCodificacion the tipoCodificacion to set
	 */
	public void setTipoCodificacion(String tipoCodificacion) {
		this.tipoCodificacion = tipoCodificacion;
	}

	/**
	 * @return the destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/**
	 * @param destinatario the destinatario to set
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * @return the destinatariosMensajes
	 */
	public Long getDestinatariosMensajes() {
		return destinatariosMensajes;
	}

	/**
	 * @param destinatariosMensajes the destinatariosMensajes to set
	 */
	public void setDestinatariosMensajes(Long destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}
	
}
