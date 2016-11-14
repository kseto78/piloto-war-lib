package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ViewHistoricosSMSHistId generated by hbm2java
 */
@Embeddable
public class ViewHistoricosSMSHistId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6448399312301908853L;

	@Column(name = "IDENVIOSSMS", nullable = false, precision = 22, scale = 0)
	private Long idenviossms;

	@Column(name = "UIM", length = 50)
	private String uim;

	@Column(name = "IDESTADO", precision = 22, scale = 0)
	private Long idestado;

	@Column(name = "FECHA", length = 7)
	private Date fecha;

	@Column(name = "ESTADO", nullable = false, length = 100)
	private String estado;

	@Column(name = "IDGRUPO", precision = 22, scale = 0)
	private Long idgrupo;

	@Column(name = "DESCRIPCION", length = 5)
	private String descripcion;

	@Column(name = "SERVIDOR", length = 100)
	private String servidor;

	@Column(name = "IDVENTANA", precision = 22, scale = 0)
	private Long idventana;

	@Column(name = "PLANIFICACIONID", precision = 22, scale = 0)
	private Long planificacionid;

	@Column(name = "SERVIDORID", precision = 22, scale = 0)
	private Long servidorid;

	@Column(name = "USUARIO", length = 50)
	private String usuario;

	@Column(name = "PASSWORD", length = 50)
	private String password;

	@Column(name = "FECHAHISTORIFICACION", length = 7)
	private Date fechahistorificacion;

	public ViewHistoricosSMSHistId() {
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ViewHistoricosSMSHistId))
			return false;
		ViewHistoricosSMSHistId castOther = (ViewHistoricosSMSHistId) other;

		return ((this.getIdenviossms() == castOther.getIdenviossms()) || (this
				.getIdenviossms() != null && castOther.getIdenviossms() != null && this
				.getIdenviossms().equals(castOther.getIdenviossms())))
				&& ((this.getUim() == castOther.getUim()) || (this.getUim() != null
						&& castOther.getUim() != null && this.getUim().equals(
						castOther.getUim())))
				&& ((this.getIdestado() == castOther.getIdestado()) || (this
						.getIdestado() != null
						&& castOther.getIdestado() != null && this
						.getIdestado().equals(castOther.getIdestado())))
				&& ((this.getFecha() == castOther.getFecha()) || (this
						.getFecha() != null && castOther.getFecha() != null && this
						.getFecha().equals(castOther.getFecha())))
				&& ((this.getEstado() == castOther.getEstado()) || (this
						.getEstado() != null && castOther.getEstado() != null && this
						.getEstado().equals(castOther.getEstado())))
				&& ((this.getIdgrupo() == castOther.getIdgrupo()) || (this
						.getIdgrupo() != null && castOther.getIdgrupo() != null && this
						.getIdgrupo().equals(castOther.getIdgrupo())))
				&& ((this.getDescripcion() == castOther.getDescripcion()) || (this
						.getDescripcion() != null
						&& castOther.getDescripcion() != null && this
						.getDescripcion().equals(castOther.getDescripcion())))
				&& ((this.getServidor() == castOther.getServidor()) || (this
						.getServidor() != null
						&& castOther.getServidor() != null && this
						.getServidor().equals(castOther.getServidor())))
				&& ((this.getIdventana() == castOther.getIdventana()) || (this
						.getIdventana() != null
						&& castOther.getIdventana() != null && this
						.getIdventana().equals(castOther.getIdventana())))
				&& ((this.getPlanificacionid() == castOther
						.getPlanificacionid()) || (this.getPlanificacionid() != null
						&& castOther.getPlanificacionid() != null && this
						.getPlanificacionid().equals(
								castOther.getPlanificacionid())))
				&& ((this.getServidorid() == castOther.getServidorid()) || (this
						.getServidorid() != null
						&& castOther.getServidorid() != null && this
						.getServidorid().equals(castOther.getServidorid())))
				&& ((this.getUsuario() == castOther.getUsuario()) || (this
						.getUsuario() != null && castOther.getUsuario() != null && this
						.getUsuario().equals(castOther.getUsuario())))
				&& ((this.getPassword() == castOther.getPassword()) || (this
						.getPassword() != null
						&& castOther.getPassword() != null && this
						.getPassword().equals(castOther.getPassword())))
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
				+ (getIdenviossms() == null ? 0 : this.getIdenviossms()
						.hashCode());
		result = 37 * result
				+ (getUim() == null ? 0 : this.getUim().hashCode());
		result = 37 * result
				+ (getIdestado() == null ? 0 : this.getIdestado().hashCode());
		result = 37 * result
				+ (getFecha() == null ? 0 : this.getFecha().hashCode());
		result = 37 * result
				+ (getEstado() == null ? 0 : this.getEstado().hashCode());
		result = 37 * result
				+ (getIdgrupo() == null ? 0 : this.getIdgrupo().hashCode());
		result = 37
				* result
				+ (getDescripcion() == null ? 0 : this.getDescripcion()
						.hashCode());
		result = 37 * result
				+ (getServidor() == null ? 0 : this.getServidor().hashCode());
		result = 37 * result
				+ (getIdventana() == null ? 0 : this.getIdventana().hashCode());
		result = 37
				* result
				+ (getPlanificacionid() == null ? 0 : this.getPlanificacionid()
						.hashCode());
		result = 37
				* result
				+ (getServidorid() == null ? 0 : this.getServidorid()
						.hashCode());
		result = 37 * result
				+ (getUsuario() == null ? 0 : this.getUsuario().hashCode());
		result = 37 * result
				+ (getPassword() == null ? 0 : this.getPassword().hashCode());
		result = 37
				* result
				+ (getFechahistorificacion() == null ? 0 : this
						.getFechahistorificacion().hashCode());
		return result;
	}

	/**
	 * @return the idenviossms
	 */
	public Long getIdenviossms() {
		return idenviossms;
	}

	/**
	 * @param idenviossms
	 *            the idenviossms to set
	 */
	public void setIdenviossms(Long idenviossms) {
		this.idenviossms = idenviossms;
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
	 * @return the idestado
	 */
	public Long getIdestado() {
		return idestado;
	}

	/**
	 * @param idestado
	 *            the idestado to set
	 */
	public void setIdestado(Long idestado) {
		this.idestado = idestado;
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
	 * @return the idgrupo
	 */
	public Long getIdgrupo() {
		return idgrupo;
	}

	/**
	 * @param idgrupo
	 *            the idgrupo to set
	 */
	public void setIdgrupo(Long idgrupo) {
		this.idgrupo = idgrupo;
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
	 * @return the idventana
	 */
	public Long getIdventana() {
		return idventana;
	}

	/**
	 * @param idventana
	 *            the idventana to set
	 */
	public void setIdventana(Long idventana) {
		this.idventana = idventana;
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
