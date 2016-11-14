package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TblOrganismos generated by hbm2java
 */
@Entity
@Table(name = "TBL_ORGANISMOS")
public class TblOrganismos implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6508061878633453602L;

	@Id
	@Column(name = "ORGANISMOID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long organismoid;

	@Column(name = "DIR3", nullable = false, length = 100)
	private String dir3;

	@Column(name = "NOMBRE", nullable = false, length = 100)
	private String nombre;

	@Column(name = "DESCRIPCION", length = 500)
	private String descripcion;

	@Column(name = "ACTIVO", precision = 1, scale = 0)
	private Boolean activo;

	@Column(name = "FECHACREACION", nullable = false, length = 7)
	private Date fechacreacion;

	@Column(name = "CREADOPOR", nullable = false, length = 100)
	private String creadopor;

	@Column(name = "FECHAMODIFICACION", length = 7)
	private Date fechamodificacion;

	@Column(name = "MODIFICADOPOR", length = 100)
	private String modificadopor;

	@Column(name = "EXTERNALID", precision = 22, scale = 0)
	private Long externalid;

	@Column(name = "NOMBRECUENTAENVIO", length = 256)
	private String nombrecuentaenvio;

	@Column(name = "HISTORIFICACION", precision = 22, scale = 0)
	private Integer historificacion;

	@Column(name = "MOTIVOHISTORIFICACION", length = 500)
	private String motivohistorificacion;

	@Column(name = "CONSERVACION", precision = 22, scale = 0)
	private Integer conservacion;

	@Column(name = "MOTIVOCONSERVACION", length = 500)
	private String motivoconservacion;

	@Column(name = "ELIMINADO", length = 1)
	private String eliminado;

	public TblOrganismos() {
	}

	/**
	 * @return the organismoid
	 */
	public Long getOrganismoid() {
		return organismoid;
	}

	/**
	 * @param organismoid
	 *            the organismoid to set
	 */
	public void setOrganismoid(Long organismoid) {
		this.organismoid = organismoid;
	}

	/**
	 * @return the dir3
	 */
	public String getDir3() {
		return dir3;
	}

	/**
	 * @param dir3
	 *            the dir3 to set
	 */
	public void setDir3(String dir3) {
		this.dir3 = dir3;
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

	/**
	 * @return the externalid
	 */
	public Long getExternalid() {
		return externalid;
	}

	/**
	 * @param externalid
	 *            the externalid to set
	 */
	public void setExternalid(Long externalid) {
		this.externalid = externalid;
	}

	/**
	 * @return the nombrecuentaenvio
	 */
	public String getNombrecuentaenvio() {
		return nombrecuentaenvio;
	}

	/**
	 * @param nombrecuentaenvio
	 *            the nombrecuentaenvio to set
	 */
	public void setNombrecuentaenvio(String nombrecuentaenvio) {
		this.nombrecuentaenvio = nombrecuentaenvio;
	}

	/**
	 * @return the historificacion
	 */
	public Integer getHistorificacion() {
		return historificacion;
	}

	/**
	 * @param historificacion
	 *            the historificacion to set
	 */
	public void setHistorificacion(Integer historificacion) {
		this.historificacion = historificacion;
	}

	/**
	 * @return the motivohistorificacion
	 */
	public String getMotivohistorificacion() {
		return motivohistorificacion;
	}

	/**
	 * @param motivohistorificacion
	 *            the motivohistorificacion to set
	 */
	public void setMotivohistorificacion(String motivohistorificacion) {
		this.motivohistorificacion = motivohistorificacion;
	}

	/**
	 * @return the conservacion
	 */
	public Integer getConservacion() {
		return conservacion;
	}

	/**
	 * @param conservacion
	 *            the conservacion to set
	 */
	public void setConservacion(Integer conservacion) {
		this.conservacion = conservacion;
	}

	/**
	 * @return the motivoconservacion
	 */
	public String getMotivoconservacion() {
		return motivoconservacion;
	}

	/**
	 * @param motivoconservacion
	 *            the motivoconservacion to set
	 */
	public void setMotivoconservacion(String motivoconservacion) {
		this.motivoconservacion = motivoconservacion;
	}

	/**
	 * @return the eliminado
	 */
	public String getEliminado() {
		return eliminado;
	}

	/**
	 * @param eliminado
	 *            the eliminado to set
	 */
	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}

}
