package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
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
 *  
 *  Representa la tabla de usuarios movil
 *  
 *  @author jgonzvil
 */
public class UsuariosWebPushBean implements Audit, Serializable{
							  
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de usuarios web push bean.
	 */
	public UsuariosWebPushBean() {
		super();
		this.usuarioId = null;
		this.nombreUsuario = null;
		this.servicioId = null;
		this.fechaCreacion = null;
		this.fechaModificacion = null;
		
		this.aplicacionId = null;
		this.fechaDesde = null;
		this.fechaHasta = null;
				
		this.aplicacion = null;
		this.servicio = null;
	}
	
	
	/**  usuario id. */
	protected Integer usuarioId;
	
	/**  nombre usuario. */
	protected String nombreUsuario;
	
	/**  servicio id. */
	protected Integer servicioId;
	
	/**  fecha creacion. */
	protected Date fechaCreacion;
	
	/**  fecha modificacion. */
	protected Date fechaModificacion;
		
	/**  aplicacion id. */
	protected Integer aplicacionId;
	
	/**  fecha desde. */
	protected Date fechaDesde;
	
	/**  fecha hasta. */
	protected Date fechaHasta;
	
	/**  aplicacion. */
	protected String aplicacion;
	
	/**  servicio. */
	protected String servicio;
	
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
	 * @param nombreUsuario new nombre usuario
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	/**
	 * Obtener servicio id.
	 *
	 * @return servicio id
	 */
	public Integer getServicioId() {
		return servicioId;
	}


	/**
	 * Modificar servicio id.
	 *
	 * @param servicioId new servicio id
	 */
	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
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
	 * Obtener aplicacion id.
	 *
	 * @return aplicacion id
	 */
	public Integer getAplicacionId() {
		return aplicacionId;
	}


	/**
	 * Modificar aplicacion id.
	 *
	 * @param aplicacionId new aplicacion id
	 */
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}


	/**
	 * Obtener fecha desde.
	 *
	 * @return fecha desde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}


	/**
	 * Modificar fecha desde.
	 *
	 * @param fechaDesde new fecha desde
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	/**
	 * Obtener fecha hasta.
	 *
	 * @return fecha hasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}


	/**
	 * Modificar fecha hasta.
	 *
	 * @param fechaHasta new fecha hasta
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Obtener aplicacion.
	 *
	 * @return aplicacion
	 */
	public String getAplicacion() {
		return aplicacion;
	}


	/**
	 * Modificar aplicacion.
	 *
	 * @param aplicacion new aplicacion
	 */
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}


	/**
	 * Obtener servicio.
	 *
	 * @return servicio
	 */
	public String getServicio() {
		return servicio;
	}


	/**
	 * Modificar servicio.
	 *
	 * @param servicio new servicio
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}


	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		return null;
	}
}
