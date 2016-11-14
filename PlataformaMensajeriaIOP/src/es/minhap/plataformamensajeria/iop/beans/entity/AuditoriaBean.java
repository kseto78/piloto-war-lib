package es.minhap.plataformamensajeria.iop.beans.entity;

import java.util.Date;

/**
 * 
 * Bean que contiene la información necearia para persistir en la TBL_AUDITORIA
 * 
 * @author everis
 *
 */
public class AuditoriaBean {

	private Long auditoriaid;
	private String operacion;
	private Date fecha;
	private Long loteenvioid;
	private Long mensajeid;
	private Long servicioid;
	private Long anexoid;
	private String usuario;
	private String password;
	private Long coderror;
	private String descripcion;

	public AuditoriaBean(String operacion, Date fecha, Long loteenvioId, Long mensajeId, Long servicioId,
			Long anexoId, String usuario, String password, Long codError, String descripcion){
		this.operacion = operacion;
		this.fecha = fecha;
		this.loteenvioid = loteenvioId;
		this.mensajeid = mensajeId;
		this.servicioid = servicioId;
		this.anexoid = anexoId;
		this.usuario = usuario;
		this.password = password;
		this.coderror = codError;
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the auditoriaid
	 */
	public Long getAuditoriaid() {
		return auditoriaid;
	}

	/**
	 * @param auditoriaid
	 *            the auditoriaid to set
	 */
	public void setAuditoriaid(Long auditoriaid) {
		this.auditoriaid = auditoriaid;
	}

	/**
	 * @return the operacion
	 */
	public String getOperacion() {
		return operacion;
	}

	/**
	 * @param operacion
	 *            the operacion to set
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the loteenvioid
	 */
	public Long getLoteenvioid() {
		return loteenvioid;
	}

	/**
	 * @param loteenvioid
	 *            the loteenvioid to set
	 */
	public void setLoteenvioid(Long loteenvioid) {
		this.loteenvioid = loteenvioid;
	}

	/**
	 * @return the mensajeid
	 */
	public Long getMensajeid() {
		return mensajeid;
	}

	/**
	 * @param mensajeid
	 *            the mensajeid to set
	 */
	public void setMensajeid(Long mensajeid) {
		this.mensajeid = mensajeid;
	}

	/**
	 * @return the servicioid
	 */
	public Long getServicioid() {
		return servicioid;
	}

	/**
	 * @param servicioid
	 *            the servicioid to set
	 */
	public void setServicioid(Long servicioid) {
		this.servicioid = servicioid;
	}

	/**
	 * @return the anexoid
	 */
	public Long getAnexoid() {
		return anexoid;
	}

	/**
	 * @param anexoid
	 *            the anexoid to set
	 */
	public void setAnexoid(Long anexoid) {
		this.anexoid = anexoid;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the coderror
	 */
	public Long getCoderror() {
		return coderror;
	}

	/**
	 * @param coderror
	 *            the coderror to set
	 */
	public void setCoderror(Long coderror) {
		this.coderror = coderror;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
