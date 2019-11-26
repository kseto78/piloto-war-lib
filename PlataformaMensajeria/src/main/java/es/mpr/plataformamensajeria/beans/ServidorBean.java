package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Clase que representa un servidor para la capa de presentaci&oacute;n.
 *
 * @author Altran
 */
public class ServidorBean implements Audit, Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 97918980091434705L;

	/**
	 * Constructor de servidor bean.
	 */
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
		this.cuotadiaria = null;
	}

	/**  servidorid. */
	protected Long servidorid;
	
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
	
	/**  eliminado. */
	protected String eliminado;
	
	/**  plataforma. */
	protected Integer plataforma;
	
	/**  urlfeedback. */
	protected String urlfeedback;
	
	/**  usuario. */
	protected String usuario;
	
	/**  password. */
	protected String password;
	
	/**  metodoconsulta. */
	protected Boolean metodoconsulta;
	
	/**  is activo. */
	protected String isActivo = null;
	
	/**  is defecto. */
	protected String isDefecto = null;
	
	/**  cuota diaria. */
	protected String cuotadiaria;

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
	 * Obtener defecto.
	 *
	 * @return defecto
	 */
	public String getDefecto() {
		if (pordefecto != null && pordefecto) {
			return "true";
		} else {
			return "false";
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
		} else {
			return "false";
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
		} else {
			return "<span class='inactivo'></span>";
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
		} else {
			return "<span class='inactivo'></span>";
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
		this.isDefecto = isDefecto;
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
		this.isActivo = isActivo;
	}

	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtener id.
	 *
	 * @return id
	 */
	public Long getId() {
		return servidorid;
	}


	/**
	 * Modificar id.
	 *
	 * @param servidorId new id
	 */
	public void setId(Long servidorId) {
		this.servidorid = servidorId;
	}
	
	/**
	 * Obtener servidorid.
	 *
	 * @return the servidorid
	 */
	public Long getServidorid() {
		return servidorid;
	}

	/**
	 * Modificar servidorid.
	 *
	 * @param servidorid            the servidorid to set
	 */
	public void setServidorid(Long servidorid) {
		this.servidorid = servidorid;
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
	 * Obtener plataforma.
	 *
	 * @return the plataforma
	 */
	public Integer getPlataforma() {
		return plataforma;
	}

	/**
	 * Modificar plataforma.
	 *
	 * @param plataforma the plataforma to set
	 */
	public void setPlataforma(Integer plataforma) {
		this.plataforma = plataforma;
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
	 * @param urlfeedback the urlfeedback to set
	 */
	public void setUrlfeedback(String urlfeedback) {
		this.urlfeedback = urlfeedback;
	}

	/**
	 * Obtener usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Modificar usuario.
	 *
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Obtener password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Modificar password.
	 *
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Obtener metodoconsulta.
	 *
	 * @return the metodoconsulta
	 */
	public Boolean getMetodoconsulta() {
		return metodoconsulta;
	}

	/**
	 * Modificar metodoconsulta.
	 *
	 * @param metodoconsulta the metodoconsulta to set
	 */
	public void setMetodoconsulta(Boolean metodoconsulta) {
		this.metodoconsulta = metodoconsulta;
	}

	public String getCuotadiaria() {
		return cuotadiaria;
	}

	public void setCuotadiaria(String cuotadiaria) {
		this.cuotadiaria = cuotadiaria;
	}

}
