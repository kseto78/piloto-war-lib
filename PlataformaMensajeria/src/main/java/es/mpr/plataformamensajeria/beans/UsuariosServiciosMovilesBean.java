package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

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
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	protected Integer usuarioServicioMovilId;
	protected Integer usuarioId;
	protected Integer servicioMovilId;
	protected Integer estadoSuscripcion = null;
	protected Date fechaCreacion = null;
	protected String creadoPor = null;
	protected Date fechaModificacion = null;
	protected String modificadoPor = null;

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getUsuarioServicioMovilId() {
		return usuarioServicioMovilId;
	}

	public void setUsuarioServicioMovilId(Integer usuarioServicioMovilId) {
		this.usuarioServicioMovilId = usuarioServicioMovilId;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Integer getServicioMovilId() {
		return servicioMovilId;
	}

	public void setServicioMovilId(Integer servicioMovilId) {
		this.servicioMovilId = servicioMovilId;
	}

	public Integer getEstadoSuscripcion() {
		return estadoSuscripcion;
	}

	public void setEstadoSuscripcion(Integer estadoSuscripcion) {
		this.estadoSuscripcion = estadoSuscripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

}