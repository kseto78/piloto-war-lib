package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ViewHistoricoHistId generated by hbm2java
 */
@Embeddable
public class ViewHistoricoHistId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 236974121764757762L;

	@Column(name = "HISTORICOID", nullable = false, precision = 22, scale = 0)
	private Long historicoid;

	@Column(name = "MENSAJEID", precision = 22, scale = 0)
	private Long mensajeid;

	@Column(name = "DESTINATARIOSMENSAJES", precision = 22, scale = 0)
	private Long destinatariosmensajes;

	@Column(name = "ESTADOID", precision = 22, scale = 0)
	private Long estadoid;

	@Column(name = "SERVIDORID", precision = 22, scale = 0)
	private Long servidorid;

	@Column(name = "PLANIFICACIONID", precision = 22, scale = 0)
	private Long planificacionid;

	@Column(name = "ESTADO", nullable = false, length = 100)
	private String estado;

	@Column(name = "FECHA", length = 7)
	private Date fecha;

	@Column(name = "SERVIDOR", length = 100)
	private String servidor;

	@Column(name = "CODIGOEXTERNO", length = 100)
	private String codigoexterno;

	@Column(name = "USUARIO", length = 50)
	private String usuario;

	@Column(name = "PASSWORD", length = 50)
	private String password;

	@Column(name = "DESCRIPCION", length = 1000)
	private String descripcion;

	@Column(name = "FECHAHISTORIFICACION", length = 7)
	private Date fechahistorificacion;

	public ViewHistoricoHistId() {
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ViewHistoricoHistId))
			return false;
		ViewHistoricoHistId castOther = (ViewHistoricoHistId) other;

		return ((this.getHistoricoid() == castOther.getHistoricoid()) || (this
				.getHistoricoid() != null && castOther.getHistoricoid() != null && this
				.getHistoricoid().equals(castOther.getHistoricoid())))
				&& ((this.getMensajeid() == castOther.getMensajeid()) || (this
						.getMensajeid() != null
						&& castOther.getMensajeid() != null && this
						.getMensajeid().equals(castOther.getMensajeid())))
				&& ((this.getDestinatariosmensajes() == castOther
						.getDestinatariosmensajes()) || (this
						.getDestinatariosmensajes() != null
						&& castOther.getDestinatariosmensajes() != null && this
						.getDestinatariosmensajes().equals(
								castOther.getDestinatariosmensajes())))
				&& ((this.getEstadoid() == castOther.getEstadoid()) || (this
						.getEstadoid() != null
						&& castOther.getEstadoid() != null && this
						.getEstadoid().equals(castOther.getEstadoid())))
				&& ((this.getServidorid() == castOther.getServidorid()) || (this
						.getServidorid() != null
						&& castOther.getServidorid() != null && this
						.getServidorid().equals(castOther.getServidorid())))
				&& ((this.getPlanificacionid() == castOther
						.getPlanificacionid()) || (this.getPlanificacionid() != null
						&& castOther.getPlanificacionid() != null && this
						.getPlanificacionid().equals(
								castOther.getPlanificacionid())))
				&& ((this.getEstado() == castOther.getEstado()) || (this
						.getEstado() != null && castOther.getEstado() != null && this
						.getEstado().equals(castOther.getEstado())))
				&& ((this.getFecha() == castOther.getFecha()) || (this
						.getFecha() != null && castOther.getFecha() != null && this
						.getFecha().equals(castOther.getFecha())))
				&& ((this.getServidor() == castOther.getServidor()) || (this
						.getServidor() != null
						&& castOther.getServidor() != null && this
						.getServidor().equals(castOther.getServidor())))
				&& ((this.getCodigoexterno() == castOther.getCodigoexterno()) || (this
						.getCodigoexterno() != null
						&& castOther.getCodigoexterno() != null && this
						.getCodigoexterno()
						.equals(castOther.getCodigoexterno())))
				&& ((this.getUsuario() == castOther.getUsuario()) || (this
						.getUsuario() != null && castOther.getUsuario() != null && this
						.getUsuario().equals(castOther.getUsuario())))
				&& ((this.getPassword() == castOther.getPassword()) || (this
						.getPassword() != null
						&& castOther.getPassword() != null && this
						.getPassword().equals(castOther.getPassword())))
				&& ((this.getDescripcion() == castOther.getDescripcion()) || (this
						.getDescripcion() != null
						&& castOther.getDescripcion() != null && this
						.getDescripcion().equals(castOther.getDescripcion())))
				&& ((this.getFechahistorificacion() == castOther
						.getFechahistorificacion()) || (this
						.getFechahistorificacion() != null
						&& castOther.getFechahistorificacion() != null && this
						.getFechahistorificacion().equals(
								castOther.getFechahistorificacion())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getHistoricoid() == null ? 0 : this.getHistoricoid()
						.hashCode());
		result = 37 * result
				+ (getMensajeid() == null ? 0 : this.getMensajeid().hashCode());
		result = 37
				* result
				+ (getDestinatariosmensajes() == null ? 0 : this
						.getDestinatariosmensajes().hashCode());
		result = 37 * result
				+ (getEstadoid() == null ? 0 : this.getEstadoid().hashCode());
		result = 37
				* result
				+ (getServidorid() == null ? 0 : this.getServidorid()
						.hashCode());
		result = 37
				* result
				+ (getPlanificacionid() == null ? 0 : this.getPlanificacionid()
						.hashCode());
		result = 37 * result
				+ (getEstado() == null ? 0 : this.getEstado().hashCode());
		result = 37 * result
				+ (getFecha() == null ? 0 : this.getFecha().hashCode());
		result = 37 * result
				+ (getServidor() == null ? 0 : this.getServidor().hashCode());
		result = 37
				* result
				+ (getCodigoexterno() == null ? 0 : this.getCodigoexterno()
						.hashCode());
		result = 37 * result
				+ (getUsuario() == null ? 0 : this.getUsuario().hashCode());
		result = 37 * result
				+ (getPassword() == null ? 0 : this.getPassword().hashCode());
		result = 37
				* result
				+ (getDescripcion() == null ? 0 : this.getDescripcion()
						.hashCode());
		result = 37
				* result
				+ (getFechahistorificacion() == null ? 0 : this
						.getFechahistorificacion().hashCode());
		return result;
	}

	/**
	 * @return the historicoid
	 */
	public Long getHistoricoid() {
		return historicoid;
	}

	/**
	 * @param historicoid
	 *            the historicoid to set
	 */
	public void setHistoricoid(Long historicoid) {
		this.historicoid = historicoid;
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
	 * @return the estadoid
	 */
	public Long getEstadoid() {
		return estadoid;
	}

	/**
	 * @param estadoid
	 *            the estadoid to set
	 */
	public void setEstadoid(Long estadoid) {
		this.estadoid = estadoid;
	}

	/**
	 * @return the servidorid
	 */
	public Long getServidorid() {
		return servidorid;
	}

	/**
	 * @param servidorid
	 *            the servidorid to set
	 */
	public void setServidorid(Long servidorid) {
		this.servidorid = servidorid;
	}

	/**
	 * @return the planificacionid
	 */
	public Long getPlanificacionid() {
		return planificacionid;
	}

	/**
	 * @param planificacionid
	 *            the planificacionid to set
	 */
	public void setPlanificacionid(Long planificacionid) {
		this.planificacionid = planificacionid;
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
	 * @return the servidor
	 */
	public String getServidor() {
		return servidor;
	}

	/**
	 * @param servidor
	 *            the servidor to set
	 */
	public void setServidor(String servidor) {
		this.servidor = servidor;
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

}
