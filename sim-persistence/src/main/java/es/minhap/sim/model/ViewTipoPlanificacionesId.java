package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ViewTipoPlanificacionesId generated by hbm2java
 */
@Embeddable
public class ViewTipoPlanificacionesId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -740294697895601971L;

	@Column(name = "TIPOPLANIFICACIONID", nullable = false, precision = 22, scale = 0)
	private Long tipoplanificacionid;

	@Column(name = "NOMBRE", length = 100)
	private String nombre;

	@Column(name = "DESCRIPCION", length = 500)
	private String descripcion;

	@Column(name = "ACTIVO", precision = 1, scale = 0)
	private Boolean activo;

	@Column(name = "FECHACREACION", length = 7)
	private Date fechacreacion;

	@Column(name = "CREADOPOR", length = 100)
	private String creadopor;

	@Column(name = "FECHAMODIFICACION", length = 7)
	private Date fechamodificacion;

	@Column(name = "MODIFICADOPOR", length = 100)
	private String modificadopor;

	public ViewTipoPlanificacionesId() {
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ViewTipoPlanificacionesId))
			return false;
		ViewTipoPlanificacionesId castOther = (ViewTipoPlanificacionesId) other;

		return ((this.getTipoplanificacionid() == castOther
				.getTipoplanificacionid()) || (this.getTipoplanificacionid() != null
				&& castOther.getTipoplanificacionid() != null && this
				.getTipoplanificacionid().equals(
						castOther.getTipoplanificacionid())))
				&& ((this.getNombre() == castOther.getNombre()) || (this
						.getNombre() != null && castOther.getNombre() != null && this
						.getNombre().equals(castOther.getNombre())))
				&& ((this.getDescripcion() == castOther.getDescripcion()) || (this
						.getDescripcion() != null
						&& castOther.getDescripcion() != null && this
						.getDescripcion().equals(castOther.getDescripcion())))
				&& ((this.getActivo() == castOther.getActivo()) || (this
						.getActivo() != null && castOther.getActivo() != null && this
						.getActivo().equals(castOther.getActivo())))
				&& ((this.getFechacreacion() == castOther.getFechacreacion()) || (this
						.getFechacreacion() != null
						&& castOther.getFechacreacion() != null && this
						.getFechacreacion()
						.equals(castOther.getFechacreacion())))
				&& ((this.getCreadopor() == castOther.getCreadopor()) || (this
						.getCreadopor() != null
						&& castOther.getCreadopor() != null && this
						.getCreadopor().equals(castOther.getCreadopor())))
				&& ((this.getFechamodificacion() == castOther
						.getFechamodificacion()) || (this
						.getFechamodificacion() != null
						&& castOther.getFechamodificacion() != null && this
						.getFechamodificacion().equals(
								castOther.getFechamodificacion())))
				&& ((this.getModificadopor() == castOther.getModificadopor()) || (this
						.getModificadopor() != null
						&& castOther.getModificadopor() != null && this
						.getModificadopor()
						.equals(castOther.getModificadopor())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getTipoplanificacionid() == null ? 0 : this
						.getTipoplanificacionid().hashCode());
		result = 37 * result
				+ (getNombre() == null ? 0 : this.getNombre().hashCode());
		result = 37
				* result
				+ (getDescripcion() == null ? 0 : this.getDescripcion()
						.hashCode());
		result = 37 * result
				+ (getActivo() == null ? 0 : this.getActivo().hashCode());
		result = 37
				* result
				+ (getFechacreacion() == null ? 0 : this.getFechacreacion()
						.hashCode());
		result = 37 * result
				+ (getCreadopor() == null ? 0 : this.getCreadopor().hashCode());
		result = 37
				* result
				+ (getFechamodificacion() == null ? 0 : this
						.getFechamodificacion().hashCode());
		result = 37
				* result
				+ (getModificadopor() == null ? 0 : this.getModificadopor()
						.hashCode());
		return result;
	}

	/**
	 * @return the tipoplanificacionid
	 */
	public Long getTipoplanificacionid() {
		return tipoplanificacionid;
	}

	/**
	 * @param tipoplanificacionid
	 *            the tipoplanificacionid to set
	 */
	public void setTipoplanificacionid(Long tipoplanificacionid) {
		this.tipoplanificacionid = tipoplanificacionid;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * @param activo
	 *            the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
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

}
