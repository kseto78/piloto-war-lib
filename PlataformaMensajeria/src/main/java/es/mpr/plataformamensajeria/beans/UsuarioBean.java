package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Clase que representa un usuario para la capa de presentaci&oacute;n.
 *
 * @author Altran
 */
public class UsuarioBean implements Audit {

	/**
	 * Constructor de usuario bean.
	 */
	public UsuarioBean() {
		super();
		this.usuarioId = null;
		this.nombre = null;
		this.login = null;
		this.email = null;
		this.fechaCreacion = null;
		this.creadoPor = null;
		this.activo = null;
		this.rolId = null;
		this.rolUsuario = null;
		this.nombreAplicacion = null;
		this.nombreUsuario = null;
		this.aplicacionId = null;

	}

	/** usuario id. */
	protected Long usuarioId;

	/** nombre. */
	protected String nombre = null;

	/** activo. */
	protected Boolean activo = null;

	/** fecha creacion. */
	protected Date fechaCreacion = null;

	/** creado por. */
	protected String creadoPor = null;

	/** login. */
	protected String login = null;

	/** email. */
	protected String email = null;

	/** rol id. */
	protected Integer rolId = null;

	/** rol usuario. */
	protected String rolUsuario = null;

	/** nombre aplicacion. */
	protected String nombreAplicacion = null;

	/** nombre usuario. */
	protected String nombreUsuario = null;

	/** aplicacion id. */
	protected Long aplicacionId = null;

	/** fecha modificacion. */
	protected Date fechaModificacion = null;

	/** modificado por. */
	protected String modificadoPor = null;

	/** is activo. */
	protected String isActivo = null;

	/**
	 * Modificar activado.
	 *
	 * @param activado
	 *            new activado
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
		} else if (activo != null && !activo) {
			return "false";
		} else {
			return null;
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
		} else if (activo != null && !activo) {
			return "<span class='inactivo'></span>";
		} else {
			return null;
		}

	}

	/**
	 * Modificar checks if is activo.
	 *
	 * @param isActivo
	 *            new checks if is activo
	 */
	public void setIsActivo(String isActivo) {
		if (isActivo != null && isActivo.equals("true")) {
			this.activo = true;
		} else {
			this.activo = false;
		}

	}

	/**
	 * Modificar id.
	 *
	 * @param id
	 *            new id
	 */
	public void setId(Object id) {
		this.usuarioId = (Long) id;
	}

	/**
	 * Obtener id.
	 *
	 * @return id
	 */
	public Long getId() {
		return this.usuarioId;
	}

	/**
	 * Obtener activo.
	 *
	 * @return activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * Modificar activo.
	 *
	 * @param activo
	 *            new activo
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * Obtener fecha creacion.
	 *
	 * @return fecha creacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Modificar fecha creacion.
	 *
	 * @param fechaCreacion
	 *            new fecha creacion
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Obtener creado por.
	 *
	 * @return creado por
	 */
	public String getCreadoPor() {
		return creadoPor;
	}

	/**
	 * Modificar creado por.
	 *
	 * @param creadoPor
	 *            new creado por
	 */
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtener usuario id.
	 *
	 * @return usuario id
	 */
	public Long getUsuarioId() {
		return usuarioId;
	}

	/**
	 * Modificar usuario id.
	 *
	 * @param usuarioId
	 *            new usuario id
	 */
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	/**
	 * Obtener nombre.
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Modificar nombre.
	 *
	 * @param nombre
	 *            new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtener login.
	 *
	 * @return login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Modificar login.
	 *
	 * @param login
	 *            new login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Obtener email.
	 *
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Modificar email.
	 *
	 * @param email
	 *            new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtener rol id.
	 *
	 * @return rol id
	 */
	public Integer getRolId() {
		return rolId;
	}

	/**
	 * Modificar rol id.
	 *
	 * @param rolId
	 *            new rol id
	 */
	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	/**
	 * Obtener rol usuario.
	 *
	 * @return rol usuario
	 */
	public String getRolUsuario() {
		if (rolId != null && rolId.intValue() == 1) {
			return "Administrador";
		} else if (rolId != null && rolId.intValue() == 2) {
			return "Propietario Aplicacion";
		} else if (rolId != null && rolId.intValue() == 3) {
			return "Caid";
		} else {
			return "<Rol no definido>";
		}
	}

	/**
	 * Modificar rol usuario.
	 *
	 * @param rolUsuario
	 *            new rol usuario
	 */
	public void setRolUsuario(String rolUsuario) {
		this.rolUsuario = rolUsuario;
	}

	/**
	 * Obtener nombre aplicacion.
	 *
	 * @return nombre aplicacion
	 */
	public String getNombreAplicacion() {
		return nombreAplicacion;
	}

	/**
	 * Modificar nombre aplicacion.
	 *
	 * @param nombreAplicacion
	 *            new nombre aplicacion
	 */
	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}

	/**
	 * Obtener nombre usuario.
	 *
	 * @return nombre usuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * Modificar nombre usuario.
	 *
	 * @param nombreUsuario
	 *            new nombre usuario
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * Obtener aplicacion id.
	 *
	 * @return aplicacion id
	 */
	public Long getAplicacionId() {
		return aplicacionId;
	}

	/**
	 * Modificar aplicacion id.
	 *
	 * @param aplicacionId
	 *            new aplicacion id
	 */
	public void setAplicacionId(Long aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

	/**
	 * Obtener fecha modificacion.
	 *
	 * @return fecha modificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * Modificar fecha modificacion.
	 *
	 * @param fechaModificacion
	 *            new fecha modificacion
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * Obtener modificado por.
	 *
	 * @return modificado por
	 */
	public String getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * Modificar modificado por.
	 *
	 * @param modificadoPor
	 *            new modificado por
	 */
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
}
