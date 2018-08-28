package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;


/**
 * <p>
 * Clase que representa un servidor Push para la capa de presentaci&oacute;n.
 *
 * @author jgonzvil
 */
public class ServidorWebPushBean implements Audit, Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de servidor web push bean.
	 */
	public ServidorWebPushBean() {
		super();
		this.servidorWebPushId = null;
		this.nombre = null;
		this.descripcion = null;
		this.pordefecto = null;
		this.activo = null;
		this.fechacreacion = null;
		this.creadopor = null;
		this.fechamodificacion = null;
		this.modificadopor = null;
		this.tipo = null;
		this.eliminado = null;
	}

	/**  servidor web push id. */
	protected Long servidorWebPushId;
	
	/**  nombre. */
	protected String nombre;
	
	/**  descripcion. */
	protected String descripcion;
	
	/**  pordefecto. */
	protected Boolean pordefecto;
	
	/**  activo. */
	protected Boolean activo;
	
	/**  fechacreacion. */
	protected Date fechacreacion;
	
	/**  creadopor. */
	protected String creadopor;
	
	/**  fechamodificacion. */
	protected Date fechamodificacion;
	
	/**  modificadopor. */
	protected String modificadopor;
	
	/**  tipo. */
	protected Integer tipo = null;
	
	/**  eliminado. */
	protected String eliminado;
	
	/**
	 * Modificar defecto.
	 *
	 * @param defecto new defecto
	 */
	public void setDefecto(String defecto) {
		if (defecto != null && defecto.equals("true")) {
			this.pordefecto = true;
		} else {
			this.pordefecto = false;
		}
	}

	/**
	 * Obtener defecto.
	 *
	 * @return defecto
	 */
	public String getDefecto() {
		if (pordefecto != null && pordefecto) {
			return "true";
		} else if (!pordefecto) {
			return "false";
		} else {
			return null;
		}

	}

	/**
	 * Modificar activado.
	 *
	 * @param activado new activado
	 */
	public void setActivado(String activado) {
		if (activado != null && activado.equals("true")) {
			this.activo = true;
		} else {
			this.activo = false;
		}
	}

	/**
	 * Obtener activado.
	 *
	 * @return activado
	 */
	public String getActivado() {
		if (activo != null && activo) {
			return "true";
		} else if (!activo) {
			return "false";
		} else {
			return null;
		}

	}

	/**
	 * Obtener checks if is defecto.
	 *
	 * @return checks if is defecto
	 */
	public String getIsDefecto() {
		if (pordefecto != null && pordefecto) {
			return "<span class='activo'></span>";
		} else if (!pordefecto) {
			return "<span class='inactivo'></span>";
		} else {
			return null;
		}

	}

	/**
	 * Modificar checks if is defecto.
	 *
	 * @param isDefecto new checks if is defecto
	 */
	public void setIsDefecto(String isDefecto) {
		if (isDefecto != null && isDefecto.equals("true")) {
			this.pordefecto = true;
		} else {
			this.pordefecto = false;
		}

	}

	/**
	 * Obtener checks if is activo.
	 *
	 * @return checks if is activo
	 */
	public String getIsActivo() {
		if (activo != null && activo) {
			return "<span class='activo'></span>";
		} else if (!activo) {
			return "<span class='inactivo'></span>";
		} else {
			return null;
		}

	}

	/**
	 * Modificar checks if is activo.
	 *
	 * @param isActivo new checks if is activo
	 */
	public void setIsActivo(String isActivo) {
		if (isActivo != null && isActivo.equals("true")) {
			this.activo = true;
		} else {
			this.activo = false;
		}

	}

	/**
	 * Obtener nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Modificar nombre.
	 *
	 * @param nombre            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtener descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Modificar descripcion.
	 *
	 * @param descripcion            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtener pordefecto.
	 *
	 * @return the pordefecto
	 */
	public Boolean getPordefecto() {
		return pordefecto;
	}

	/**
	 * Modificar pordefecto.
	 *
	 * @param pordefecto            the pordefecto to set
	 */
	public void setPordefecto(Boolean pordefecto) {
		this.pordefecto = pordefecto;
	}

	/**
	 * Obtener activo.
	 *
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * Modificar activo.
	 *
	 * @param activo            the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * Obtener fechacreacion.
	 *
	 * @return the fechacreacion
	 */
	public Date getFechacreacion() {
		return fechacreacion;
	}

	/**
	 * Modificar fechacreacion.
	 *
	 * @param fechacreacion            the fechacreacion to set
	 */
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	/**
	 * Obtener creadopor.
	 *
	 * @return the creadopor
	 */
	public String getCreadopor() {
		return creadopor;
	}

	/**
	 * Modificar creadopor.
	 *
	 * @param creadopor            the creadopor to set
	 */
	public void setCreadopor(String creadopor) {
		this.creadopor = creadopor;
	}

	/**
	 * Obtener fechamodificacion.
	 *
	 * @return the fechamodificacion
	 */
	public Date getFechamodificacion() {
		return fechamodificacion;
	}

	/**
	 * Modificar fechamodificacion.
	 *
	 * @param fechamodificacion            the fechamodificacion to set
	 */
	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	
	/**
	 * Obtener modificadopor.
	 *
	 * @return the modificadopor
	 */
	public String getModificadopor() {
		return modificadopor;
	}

	/**
	 * Modificar modificadopor.
	 *
	 * @param modificadopor            the modificadopor to set
	 */
	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}


	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		return null;
	}

	/**
	 * Obtener servidor web push id.
	 *
	 * @return the servidorWebPushId
	 */
	public Long getServidorWebPushId() {
		return servidorWebPushId;
	}

	/**
	 * Modificar servidor web push id.
	 *
	 * @param servidorWebPushId the servidorWebPushId to set
	 */
	public void setServidorWebPushId(Long servidorWebPushId) {
		this.servidorWebPushId = servidorWebPushId;
	}

	/**
	 * Obtener eliminado.
	 *
	 * @return the eliminado
	 */
	public String getEliminado() {
		return eliminado;
	}

	/**
	 * Modificar eliminado.
	 *
	 * @param eliminado the eliminado to set
	 */
	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}

	/**
	 * Obtener tipo.
	 *
	 * @return the tipo
	 */
	public Integer getTipo() {
		return tipo;
	}

	/**
	 * Modificar tipo.
	 *
	 * @param tipo the tipo to set
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
}
