package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

public class OrganismoBean implements Audit {


	public OrganismoBean() {
		super();
		this.organismoId = null;
		this.dir3 = null;
		this.nombre = null;
		this.descripcion = null;
		this.activo = null;
		this.fechacreacion = null;
		this.fechamodificacion = null;
		this.creadopor = null;
		this.modificadopor = null;
		this.externalid = null;
		this.nombrecuentaenvio = null;
		this.historificacion = null;
		this.motivohistorificacion = null;
		this.conservacion = null;
		this.motivoconservacion = null;
		this.isActivo = null;

	}

	protected Integer organismoId;
	protected String dir3;
	protected String nombre = null;
	protected String descripcion = null;
	protected Boolean activo = null;
	protected Date fechacreacion = null;
	protected Date fechamodificacion = null;
	protected String creadopor = null;
	protected String modificadopor = null;
	protected Long externalid;
	protected String nombrecuentaenvio;
	protected Integer historificacion;
	protected String motivohistorificacion;
	protected Integer conservacion;
	protected String motivoconservacion;
	protected String isActivo = null;

	@Override
	public String obtenerXML() {
		return null;
	}

	public Integer getOrganismoId() {
		return organismoId;
	}

	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}

	public void setActivado(String activado) {
		if (activado != null && activado.equals("true")) {
			this.activo = true;
		} else {
			this.activo = false;
		}
	}

	public String getIsActivo() {
		if (activo != null && activo) {
			return "<span class='activo'></span>";
		} else {
			return "<span class='inactivo'></span>";
		}

	}

	public void setIsActivo(String isActivo) {
		if (isActivo != null && isActivo.equals("true")) {
			this.activo = true;
		} else {
			this.activo = false;
		}
		this.isActivo = isActivo;
	}

	public String getActivado() {
		if (activo != null && activo) {
			return "true";
		} else {
			return "false";
		}
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

}
