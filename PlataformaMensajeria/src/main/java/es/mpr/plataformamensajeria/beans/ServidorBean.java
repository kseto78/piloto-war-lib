package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Clase que representa un servidor para la capa de presentaci&oacute;n
 * 
 * @author Altran
 * 
 */
public class ServidorBean implements Audit, Serializable {

	private static final long serialVersionUID = 97918980091434705L;

	public ServidorBean() {
		super();
		this.servidorid = null;
		this.nombre = null;
		this.descripcion = null;
		this.fechacreacion = null;
		this.creadopor = null;
		this.fechamodificacion = null;
		this.modificadopor = null;
		this.urldestino = null;
		this.tipo = null;
		this.externalid = null;
		this.eliminado = null;
		this.plataforma = null;
		this.urlfeedback = null;
		this.usuario = null;
		this.password = null;
		this.metodoconsulta = null;
	}

	protected Long servidorid;
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
	protected String eliminado;
	protected Integer plataforma;
	protected String urlfeedback;
	protected String usuario;
	protected String password;
	protected Boolean metodoconsulta;
	protected String isActivo = null;
	protected String isDefecto = null;

	public void setDefecto(String defecto) {
		if (defecto != null && defecto.equals("true")) {
			this.pordefecto = true;
		} else {
			this.pordefecto = false;
		}
	}

	public void setActivado(String activado) {
		if (activado != null && activado.equals("true")) {
			this.activo = true;
		} else {
			this.activo = false;
		}
	}

	public String getDefecto() {
		if (pordefecto != null && pordefecto) {
			return "true";
		} else {
			return "false";
		}
	}

	public String getActivado() {
		if (activo != null && activo) {
			return "true";
		} else {
			return "false";
		}

	}

	public String getIsActivo() {
		if (activo != null && activo) {
			return "<span class='activo'></span>";
		} else {
			return "<span class='inactivo'></span>";
		}

	}

	public String getIsDefecto() {
		if (pordefecto != null && pordefecto) {
			return "<span class='activo'></span>";
		} else {
			return "<span class='inactivo'></span>";
		}

	}

	public void setIsDefecto(String isDefecto) {
		if (isDefecto != null && isDefecto.equals("true")) {
			this.pordefecto = true;
		} else {
			this.pordefecto = false;
		}
		this.isDefecto = isDefecto;
	}

	public void setIsActivo(String isActivo) {
		if (isActivo != null && isActivo.equals("true")) {
			this.activo = true;
		} else {
			this.activo = false;
		}
		this.isActivo = isActivo;
	}

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getId() {
		return servidorid;
	}


	public void setId(Long servidorId) {
		this.servidorid = servidorId;
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
	 * @return the eliminado
	 */
	public String getEliminado() {
		return eliminado;
	}

	/**
	 * @param eliminado the eliminado to set
	 */
	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}

	/**
	 * @return the plataforma
	 */
	public Integer getPlataforma() {
		return plataforma;
	}

	/**
	 * @param plataforma the plataforma to set
	 */
	public void setPlataforma(Integer plataforma) {
		this.plataforma = plataforma;
	}

	/**
	 * @return the urlfeedback
	 */
	public String getUrlfeedback() {
		return urlfeedback;
	}

	/**
	 * @param urlfeedback the urlfeedback to set
	 */
	public void setUrlfeedback(String urlfeedback) {
		this.urlfeedback = urlfeedback;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
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
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the metodoconsulta
	 */
	public Boolean getMetodoconsulta() {
		return metodoconsulta;
	}

	/**
	 * @param metodoconsulta the metodoconsulta to set
	 */
	public void setMetodoconsulta(Boolean metodoconsulta) {
		this.metodoconsulta = metodoconsulta;
	}

}
