package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

// TODO: Auto-generated Javadoc
/*
 * Diferentes implementaciones de la JPA pueden requerir diferencias en las NamedQuerys.
 * Por ejemplo la siguiente Namedquery debe ser expresada diferente si nuestra implementaci�n es openjpa o hibernate:
 * Con Open JPA -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like :nombre")
 * Con Hibernate -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like ?")
 */

/**
 * <p>
 * Clase de entidad con las anotaciones JPA necesarias.
 * 
 * <p>
 * Representa la vista canales de la base de datos
 * 
 * @author Altran
 */
public class UsuariosServiciosMovilesBean implements Audit {


	/**
	 * Constructor de usuarios servicios moviles bean.
	 */
	public UsuariosServiciosMovilesBean() {
		super();
		this.usuarioServicioMovilId = null;
		this.usuarioId = null;
		this.servicioMovilId = null;
		this.estadoSuscripcion = null;
		this.fechaCreacion = null;
		this.fechaModificacion = null;
		this.creadoPor = null;
		this.modificadoPor = null;

	}

	/**  usuario servicio movil id. */
	protected Integer usuarioServicioMovilId;
	
	/**  usuario id. */
	protected Integer usuarioId;
	
	/**  servicio movil id. */
	protected Integer servicioMovilId;
	
	/**  estado suscripcion. */
	protected Integer estadoSuscripcion = null;
	
	/**  fecha creacion. */
	protected Date fechaCreacion = null;
	
	/**  creado por. */
	protected String creadoPor = null;
	
	/**  fecha modificacion. */
	protected Date fechaModificacion = null;
	
	/**  modificado por. */
	protected String modificadoPor = null;

	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtener usuario servicio movil id.
	 *
	 * @return usuario servicio movil id
	 */
	public Integer getUsuarioServicioMovilId() {
		return usuarioServicioMovilId;
	}

	/**
	 * Modificar usuario servicio movil id.
	 *
	 * @param usuarioServicioMovilId new usuario servicio movil id
	 */
	public void setUsuarioServicioMovilId(Integer usuarioServicioMovilId) {
		this.usuarioServicioMovilId = usuarioServicioMovilId;
	}

	/**
	 * Obtener usuario id.
	 *
	 * @return usuario id
	 */
	public Integer getUsuarioId() {
		return usuarioId;
	}

	/**
	 * Modificar usuario id.
	 *
	 * @param usuarioId new usuario id
	 */
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	/**
	 * Obtener servicio movil id.
	 *
	 * @return servicio movil id
	 */
	public Integer getServicioMovilId() {
		return servicioMovilId;
	}

	/**
	 * Modificar servicio movil id.
	 *
	 * @param servicioMovilId new servicio movil id
	 */
	public void setServicioMovilId(Integer servicioMovilId) {
		this.servicioMovilId = servicioMovilId;
	}

	/**
	 * Obtener estado suscripcion.
	 *
	 * @return estado suscripcion
	 */
	public Integer getEstadoSuscripcion() {
		return estadoSuscripcion;
	}

	/**
	 * Modificar estado suscripcion.
	 *
	 * @param estadoSuscripcion new estado suscripcion
	 */
	public void setEstadoSuscripcion(Integer estadoSuscripcion) {
		this.estadoSuscripcion = estadoSuscripcion;
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
	 * @param fechaCreacion new fecha creacion
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
	 * @param creadoPor new creado por
	 */
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
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
	 * @param fechaModificacion new fecha modificacion
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
	 * @param modificadoPor new modificado por
	 */
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

}