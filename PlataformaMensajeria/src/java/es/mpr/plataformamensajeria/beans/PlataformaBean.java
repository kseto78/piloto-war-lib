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
 *  <p>Clase de entidad con las anotaciones JPA necesarias.
 *  
 *  <p>
 *  Representa la vista plataforma de la base de datos
 *  
 *  @author jgonzvil
 */
public class PlataformaBean implements Audit{

	public PlataformaBean() {
		super();
		this.nombre = null;
		this.descripcion = null;
		this.activo = null;
		this.fechaCreacion = null;
		this.creadoPor = null;
		this.fechaModificacion = null;
		this.modificadoPor = null;

	}

	
	protected Integer plataformaId;
	
	protected String nombre;
	
	protected String descripcion;
	
	protected Integer activo = null;
	
	protected Date fechaCreacion = null;
	
	protected String creadoPor = null;
	
	protected Date fechaModificacion = null;
	
	
	protected String modificadoPor = null;
	
	public Object getId() {
		return this.plataformaId;
	}
	public void setId(Object id){
		this.plataformaId =(Integer)id;
	}

	public Integer getPlataformaId() {
		return plataformaId;
	}
	public void setPlataformaId(Integer plataformaId) {
		this.plataformaId = plataformaId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getActivo() {
		return activo;
	}
	public void setActivo(Integer activo) {
		this.activo = activo;
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
	@Override
	public String obtenerXML() {
		return null;
	}
	
}
