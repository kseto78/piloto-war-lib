package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TblDestinatariosMensHist generated by hbm2java
 */
@Entity
@Table(name = "TBL_DESTINATARIOS_MENS_HIST")
public class TblDestinatariosMensHist implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4204463392501198534L;

	@Id
	@Column(name = "DESTINATARIOSMENSAJES", unique = true, nullable = false, precision = 22, scale = 0)
	private Long destinatariosmensajes;

	@Column(name = "MENSAJEID", nullable = false, precision = 22, scale = 0)
	private Long mensajeid;

	@Column(name = "DESTINATARIO", nullable = false, length = 200)
	private String destinatario;

	@Column(name = "ESTADO", nullable = false, length = 100)
	private String estado;

	@Column(name = "FECHACREACION", nullable = false, length = 7)
	private Date fechacreacion;

	@Column(name = "CREADOPOR", nullable = false, length = 100)
	private String creadopor;

	@Column(name = "FECHAMODIFICACION", length = 7)
	private Date fechamodificacion;

	@Column(name = "MODIFICADOPOR", length = 100)
	private String modificadopor;

	@Column(name = "CODIGOEXTERNO", length = 100)
	private String codigoexterno;

	@Column(name = "UIM", length = 50)
	private String uim;

	@Column(name = "NODO", precision = 22, scale = 0)
	private Integer nodo;

	@Column(name = "FECHAHISTORIFICACION", length = 7)
	private Date fechahistorificacion;

	@Column(name = "ULTIMOENVIO", length = 7)
	private Date ultimoenvio;

	public TblDestinatariosMensHist() {
	}

	/**
	 * @return the destinatariosmensajes
	 */
	public Long getDestinatariosmensajes() {
		return destinatariosmensajes;
	}

	/**
	 * @param destinatariosmensajes
	 *            the destinatariosmensajes to set
	 */
	public void setDestinatariosmensajes(Long destinatariosmensajes) {
		this.destinatariosmensajes = destinatariosmensajes;
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
	 * @return the destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/**
	 * @param destinatario
	 *            the destinatario to set
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the fechacreacion
	 */
	public Date getFechacreacion() {
		return fechacreacion;
	}

	/**
	 * @param fechacreacion
	 *            the fechacreacion to set
	 */
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	/**
	 * @return the creadopor
	 */
	public String getCreadopor() {
		return creadopor;
	}

	/**
	 * @param creadopor
	 *            the creadopor to set
	 */
	public void setCreadopor(String creadopor) {
		this.creadopor = creadopor;
	}

	/**
	 * @return the fechamodificacion
	 */
	public Date getFechamodificacion() {
		return fechamodificacion;
	}

	/**
	 * @param fechamodificacion
	 *            the fechamodificacion to set
	 */
	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	/**
	 * @return the modificadopor
	 */
	public String getModificadopor() {
		return modificadopor;
	}

	/**
	 * @param modificadopor
	 *            the modificadopor to set
	 */
	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}

	/**
	 * @return the codigoexterno
	 */
	public String getCodigoexterno() {
		return codigoexterno;
	}

	/**
	 * @param codigoexterno
	 *            the codigoexterno to set
	 */
	public void setCodigoexterno(String codigoexterno) {
		this.codigoexterno = codigoexterno;
	}

	/**
	 * @return the uim
	 */
	public String getUim() {
		return uim;
	}

	/**
	 * @param uim
	 *            the uim to set
	 */
	public void setUim(String uim) {
		this.uim = uim;
	}

	/**
	 * @return the nodo
	 */
	public Integer getNodo() {
		return nodo;
	}

	/**
	 * @param nodo
	 *            the nodo to set
	 */
	public void setNodo(Integer nodo) {
		this.nodo = nodo;
	}

	/**
	 * @return the fechahistorificacion
	 */
	public Date getFechahistorificacion() {
		return fechahistorificacion;
	}

	/**
	 * @param fechahistorificacion
	 *            the fechahistorificacion to set
	 */
	public void setFechahistorificacion(Date fechahistorificacion) {
		this.fechahistorificacion = fechahistorificacion;
	}

	/**
	 * @return the ultimoenvio
	 */
	public Date getUltimoenvio() {
		return ultimoenvio;
	}

	/**
	 * @param ultimoenvio
	 *            the ultimoenvio to set
	 */
	public void setUltimoenvio(Date ultimoenvio) {
		this.ultimoenvio = ultimoenvio;
	}

}
