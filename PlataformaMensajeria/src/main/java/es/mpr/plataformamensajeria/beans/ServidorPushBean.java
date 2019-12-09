package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Clase que representa un servidor Push para la capa de presentaci&oacute;n.
 *
 * @author jgonzvil
 */
public class ServidorPushBean implements Audit {

	protected static final String SPAN_CLASSINACT = "<span class='inactivo'></span>";

	protected static final String TRUE = "true";

	protected static final String FALSE = "false";

	protected static final String SPAN_CLASSACTIV = "<span class='activo'></span>";

	/**  servidor push id. */
	protected Long servidorPushId;

	/**  nombre. */
	protected String nombre = null;

	/**  descripcion. */
	protected String descripcion = null;

	/**  pordefecto. */
	protected Boolean pordefecto = null;

	/**  activo. */
	protected Boolean activo = null;

	/**  fechacreacion. */
	protected Date fechacreacion = null;

	/**  creadopor. */
	protected String creadopor = null;

	/**  fechamodificacion. */
	protected Date fechamodificacion = null;

	/**  modificadopor. */
	protected String modificadopor = null;

	/**  urldestino. */
	protected String urldestino = null;

	/**  tipo. */
	protected Integer tipo = null;

	/**  externalid. */
	protected String externalid = null;

	/**  plataformaid. */
	protected Integer plataformaid = null;

	/**  urlfeedback. */
	protected String urlfeedback = null;

	/**
	 * Constructor de servidor push bean.
	 */
	public ServidorPushBean() {
		super();
		this.servidorPushId = null;
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
		this.plataformaid = null;
		this.urlfeedback = null;
	}

	/**
	 * Modificar defecto.
	 *
	 * @param defecto new defecto
	 */
	public void setDefecto(String defecto) {
		if (defecto != null && TRUE.equals(defecto)) {
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
			return TRUE;
		} else if (pordefecto != null && !pordefecto) {
			return FALSE;
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
		if (activado != null && TRUE.equals(activado)) {
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
			return TRUE;
		} else if (activo != null && !activo) {
			return FALSE;
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
			return SPAN_CLASSACTIV;
		} else if (pordefecto != null && !pordefecto) {
			return SPAN_CLASSINACT;
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
		if (isDefecto != null && TRUE.equals(isDefecto)) {
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
			return SPAN_CLASSACTIV;
		} else if (activo != null && !activo) {
			return SPAN_CLASSINACT;
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
		if (isActivo != null && TRUE.equals(isActivo)) {
			this.activo = true;
		} else {
			this.activo = false;
		}

	}

	/**
	 * Obtener id.
	 *
	 * @return id
	 */
	public Long getId() {
		return servidorPushId;
	}

	/**
	 * Modificar id.
	 *
	 * @param servidorPushId new id
	 */
	public void setId(Long servidorPushId) {
		this.servidorPushId = servidorPushId;
	}

	/**
	 * Obtener servidor push id.
	 *
	 * @return the servidorPushId
	 */
	public Long getServidorPushId() {
		return servidorPushId;
	}

	/**
	 * Modificar servidor push id.
	 *
	 * @param servidorPushId            the servidorPushId to set
	 */
	public void setServidorPushId(Long servidorPushId) {
		this.servidorPushId = servidorPushId;
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
	 * Obtener urldestino.
	 *
	 * @return the urldestino
	 */
	public String getUrldestino() {
		return urldestino;
	}

	/**
	 * Modificar urldestino.
	 *
	 * @param urldestino            the urldestino to set
	 */
	public void setUrldestino(String urldestino) {
		this.urldestino = urldestino;
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
	 * @param tipo            the tipo to set
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	/**
	 * Obtener externalid.
	 *
	 * @return the externalid
	 */
	public String getExternalid() {
		return externalid;
	}

	/**
	 * Modificar externalid.
	 *
	 * @param externalid            the externalid to set
	 */
	public void setExternalid(String externalid) {
		this.externalid = externalid;
	}

	/**
	 * Obtener plataformaid.
	 *
	 * @return the plataformaid
	 */
	public Integer getPlataformaid() {
		return plataformaid;
	}

	/**
	 * Modificar plataformaid.
	 *
	 * @param plataformaid            the plataformaid to set
	 */
	public void setPlataformaid(Integer plataformaid) {
		this.plataformaid = plataformaid;
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

	/**
	 * Obtener urlfeedback.
	 *
	 * @return the urlfeedback
	 */
	public String getUrlfeedback() {
		return urlfeedback;
	}

	/**
	 * Modificar urlfeedback.
	 *
	 * @param urlfeedback            the urlfeedback to set
	 */
	public void setUrlfeedback(String urlfeedback) {
		this.urlfeedback = urlfeedback;
	}

	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		return null;
	}
}
