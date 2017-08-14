package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Clase que representa un proveedor para la capa de presentaci&oacute;n
 * 
 * @author Altran
 * 
 */
public class ProveedorSMSBean implements Audit {


	public ProveedorSMSBean() {
		super();
		this.proveedorSMSId = null;
		this.nombre = null;
		this.descripcion = null;
		this.pordefecto = null;
		this.activo = null;
		this.fechacreacion = null;
		this.creadopor = null;
		this.fechamodificacion = null;
		this.modificadopor = null;
		this.urldestino = null;
		this.tipo = null;
		this.externalid = null;
		this.metodoconsulta = null;
	}

	protected Long proveedorSMSId;
	protected String nombre = null;
	protected String descripcion = null;
	protected Boolean pordefecto = null;
	protected Boolean activo = null;
	protected Date fechacreacion = null;
	protected String creadopor = null;
	protected Date fechamodificacion = null;
	protected String modificadopor = null;
	protected String urldestino = null;
	protected Integer tipo = null;
	protected String externalid = null;
	protected String metodoconsulta = null;

	public void setDefecto(String defecto) {
		if (defecto != null && defecto.equals("true")) {
			this.pordefecto = true;
		} else {
			this.pordefecto = false;
		}
	}

	public String getDefecto() {
		if (pordefecto != null && pordefecto) {
			return "true";
		} else if (pordefecto != null && !pordefecto) {
			return "false";
		} else {
			return null;
		}

	}

	public void setActivado(String activado) {
		if (activado != null && activado.equals("true")) {
			this.activo = true;
		} else {
			this.activo = false;
		}
	}

	public String getActivado() {
		if (activo != null && activo) {
			return "true";
		} else if (activo != null && !activo) {
			return "false";
		} else {
			return null;
		}

	}

	public String getIsDefecto() {
		if (pordefecto != null && pordefecto) {
			return "<span class='activo'></span>";
		} else if (pordefecto != null && !pordefecto) {
			return "<span class='inactivo'></span>";
		} else {
			return null;
		}

	}

	public void setIsDefecto(String isDefecto) {
		if (isDefecto != null && isDefecto.equals("true")) {
			this.pordefecto = true;
		} else {
			this.pordefecto = false;
		}

	}

	public String getIsActivo() {
		if (activo != null && activo) {
			return "<span class='activo'></span>";
		} else if (activo != null && !activo) {
			return "<span class='inactivo'></span>";
		} else {
			return null;
		}

	}

	public void setIsActivo(String isActivo) {
		if (isActivo != null && isActivo.equals("true")) {
			this.activo = true;
		} else {
			this.activo = false;
		}

	}

	public Long getId() {
		return proveedorSMSId;
	}

	public void setId(Long proveedorSMSId) {
		this.proveedorSMSId = proveedorSMSId;
	}

	/**
	 * @return the proveedorSMSId
	 */
	public Long getProveedorSMSId() {
		return proveedorSMSId;
	}

	/**
	 * @param proveedorSMSId
	 *            the proveedorSMSId to set
	 */
	public void setProveedorSMSId(Long proveedorSMSId) {
		this.proveedorSMSId = proveedorSMSId;
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
	 * @return the pordefecto
	 */
	public Boolean getPordefecto() {
		return pordefecto;
	}

	/**
	 * @param pordefecto
	 *            the pordefecto to set
	 */
	public void setPordefecto(Boolean pordefecto) {
		this.pordefecto = pordefecto;
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
	 * @return the urldestino
	 */
	public String getUrldestino() {
		return urldestino;
	}

	/**
	 * @param urldestino
	 *            the urldestino to set
	 */
	public void setUrldestino(String urldestino) {
		this.urldestino = urldestino;
	}

	/**
	 * @return the tipo
	 */
	public Integer getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the externalid
	 */
	public String getExternalid() {
		return externalid;
	}

	/**
	 * @param externalid
	 *            the externalid to set
	 */
	public void setExternalid(String externalid) {
		this.externalid = externalid;
	}

	/**
	 * @return the metodoconsulta
	 */
	public String getMetodoconsulta() {
		return metodoconsulta;
	}

	/**
	 * @param metodoconsulta
	 *            the metodoconsulta to set
	 */
	public void setMetodoconsulta(String metodoconsulta) {
		this.metodoconsulta = metodoconsulta;
	}

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
}
