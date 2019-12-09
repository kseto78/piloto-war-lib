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
public class UsuariosPushBean implements Audit, Serializable{
							  
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  usuario id. */
	protected Integer usuarioId;

	/**  nombre usuario. */
	protected String nombreUsuario;

	/**  servicio id. */
	protected Integer servicioId;

	/**  plataforma id. */
	protected Integer plataformaId;

	/**  token usuario. */
	protected String tokenUsuario;

	/**  fecha creacion. */
	protected Date fechaCreacion;

	/**  fecha modificacion. */
	protected Date fechaModificacion;

	/**  dispositivo id. */
	protected String dispositivoId;

	/**  aplicacion id. */
	protected Integer aplicacionId;

	/**  plataforma. */
	protected String plataforma;

	/**  fecha desde. */
	protected Date fechaDesde;

	/**  fecha hasta. */
	protected Date fechaHasta;

	/**  fecha. */
	protected Date fecha;

	/**  aplicacion. */
	protected String aplicacion;

	/**  servicio. */
	protected String servicio;

	/**  nombre. */
	protected String nombre;

	/**  apellido 1. */
	protected String apellido1;

	/**  apellido 2. */
	protected String apellido2;

	/**
	 * Constructor de usuarios push bean.
	 */
	public UsuariosPushBean() {
		super();
		this.usuarioId = null;
		this.nombreUsuario = null;
		this.servicioId = null;
		this.plataformaId = null;
		this.tokenUsuario = null;
		this.fechaCreacion = null;
		this.fechaModificacion = null;
		
		this.aplicacionId = null;
		this.plataforma = null;
		this.fechaDesde = null;
		this.fechaHasta = null;
		this.fecha = null;
		
		this.aplicacion = null;
		this.servicio = null;
		this.nombre = null;
		this.apellido1 = null;
		this.apellido2 = null;
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
	 * Obtener plataforma id.
	 *
	 * @return plataforma id
	 */
	public Integer getPlataformaId() {
		return plataformaId;
	}


	/**
	 * Modificar plataforma id.
	 *
	 * @param plataformaId new plataforma id
	 */
	public void setPlataformaId(Integer plataformaId) {
		this.plataformaId = plataformaId;
	}


	/**
	 * Obtener token usuario.
	 *
	 * @return token usuario
	 */
	public String getTokenUsuario() {
		return tokenUsuario;
	}


	/**
	 * Modificar token usuario.
	 *
	 * @param tokenUsuario new token usuario
	 */
	public void setTokenUsuario(String tokenUsuario) {
		this.tokenUsuario = tokenUsuario;
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
	
	
	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
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
	 * Obtener plataforma.
	 *
	 * @return plataforma
	 */
	public String getPlataforma() {
		return plataforma;
	}


	/**
	 * Modificar plataforma.
	 *
	 * @param plataforma new plataforma
	 */
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}


	/**
	 * Obtener fecha.
	 *
	 * @return fecha
	 */
	public Date getFecha() {
		return fecha;
	}


	/**
	 * Modificar fecha.
	 *
	 * @param fecha new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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


	/**
	 * Obtener dispositivo id.
	 *
	 * @return dispositivo id
	 */
	public String getDispositivoId() {
		return dispositivoId;
	}


	/**
	 * Modificar dispositivo id.
	 *
	 * @param dispositivoId new dispositivo id
	 */
	public void setDispositivoId(String dispositivoId) {
		this.dispositivoId = dispositivoId;
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
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * Obtener apellido 1.
	 *
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}


	/**
	 * Modificar apellido 1.
	 *
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}


	/**
	 * Obtener apellido 2.
	 *
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}


	/**
	 * Modificar apellido 2.
	 *
	 * @param apellido2 the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}



}
