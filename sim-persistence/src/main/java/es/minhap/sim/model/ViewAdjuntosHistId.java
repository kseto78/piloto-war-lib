package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ViewAdjuntosHistId generated by hbm2java
 */
@Embeddable
public class ViewAdjuntosHistId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4261993651234699271L;

	@Column(name = "ADJUNTOID", nullable = false, precision = 22, scale = 0)
	private Long adjuntoid;

	@Column(name = "NOMBRE", length = 100)
	private String nombre;

	@Column(name = "CONTENIDO")
	private byte[] contenido;

	@Column(name = "EMAILID", precision = 22, scale = 0)
	private Long emailid;

	@Column(name = "IMAGEN", precision = 22, scale = 0)
	private Long imagen;

	@Column(name = "FECHACREACION", length = 7)
	private Date fechacreacion;

	@Column(name = "CREADOPOR", length = 100)
	private String creadopor;

	@Column(name = "FECHAMODIFICACION", length = 7)
	private Date fechamodificacion;

	@Column(name = "MODIFICADOPOR", length = 100)
	private String modificadopor;

	@Column(name = "FECHAHISTORIFICACION", length = 7)
	private Date fechahistorificacion;

	public ViewAdjuntosHistId() {
	}

	/**
	 * @return the adjuntoid
	 */
	public Long getAdjuntoid() {
		return adjuntoid;
	}

	/**
	 * @param adjuntoid
	 *            the adjuntoid to set
	 */
	public void setAdjuntoid(Long adjuntoid) {
		this.adjuntoid = adjuntoid;
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
	 * @return the contenido
	 */
	public byte[] getContenido() {
		return contenido;
	}

	/**
	 * @param contenido
	 *            the contenido to set
	 */
	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}

	/**
	 * @return the emailid
	 */
	public Long getEmailid() {
		return emailid;
	}

	/**
	 * @param emailid
	 *            the emailid to set
	 */
	public void setEmailid(Long emailid) {
		this.emailid = emailid;
	}

	/**
	 * @return the imagen
	 */
	public Long getImagen() {
		return imagen;
	}

	/**
	 * @param imagen
	 *            the imagen to set
	 */
	public void setImagen(Long imagen) {
		this.imagen = imagen;
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

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ViewAdjuntosHistId))
			return false;
		ViewAdjuntosHistId castOther = (ViewAdjuntosHistId) other;

		return ((this.getAdjuntoid() == castOther.getAdjuntoid()) || (this
				.getAdjuntoid() != null && castOther.getAdjuntoid() != null && this
				.getAdjuntoid().equals(castOther.getAdjuntoid())))
				&& ((this.getNombre() == castOther.getNombre()) || (this
						.getNombre() != null && castOther.getNombre() != null && this
						.getNombre().equals(castOther.getNombre())))
				&& ((this.getContenido() == castOther.getContenido()) || (this
						.getContenido() != null
						&& castOther.getContenido() != null && this
						.getContenido().equals(castOther.getContenido())))
				&& ((this.getEmailid() == castOther.getEmailid()) || (this
						.getEmailid() != null && castOther.getEmailid() != null && this
						.getEmailid().equals(castOther.getEmailid())))
				&& ((this.getImagen() == castOther.getImagen()) || (this
						.getImagen() != null && castOther.getImagen() != null && this
						.getImagen().equals(castOther.getImagen())))
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
						.equals(castOther.getModificadopor())))
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

		result = 37 * result
				+ (getAdjuntoid() == null ? 0 : this.getAdjuntoid().hashCode());
		result = 37 * result
				+ (getNombre() == null ? 0 : this.getNombre().hashCode());
		result = 37 * result
				+ (getContenido() == null ? 0 : this.getContenido().hashCode());
		result = 37 * result
				+ (getEmailid() == null ? 0 : this.getEmailid().hashCode());
		result = 37 * result
				+ (getImagen() == null ? 0 : this.getImagen().hashCode());
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
		result = 37
				* result
				+ (getFechahistorificacion() == null ? 0 : this
						.getFechahistorificacion().hashCode());
		return result;
	}

}
