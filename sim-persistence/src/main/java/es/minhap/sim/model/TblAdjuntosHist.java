package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TblAdjuntosHist generated by hbm2java
 */
@Entity
@Table(name = "TBL_ADJUNTOS_HIST")
public class TblAdjuntosHist implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -703943786986441300L;

	@Id
	@Column(name = "ADJUNTOID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long adjuntoid;

	@Column(name = "NOMBRE", length = 100)
	private String nombre;

	@Column(name = "FECHACREACION", length = 7)
	private Date fechacreacion;

	@Column(name = "CREADOPOR", length = 100)
	private String creadopor;

	@Column(name = "FECHAMODIFICACION", length = 7)
	private Date fechamodificacion;

	@Column(name = "MODIFICADOPOR", length = 100)
	private String modificadopor;

	@Column(name = "CONTENIDO")
	private byte[] contenido;

	@Column(name = "IMAGEN", precision = 22, scale = 0)
	private Long imagen;

	@Column(name = "FECHAHISTORIFICACION", length = 7)
	private Date fechahistorificacion;

	public TblAdjuntosHist() {
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

}
