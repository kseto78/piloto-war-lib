package es.mpr.plataformamensajeria.model.views;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.map.j2ee.base.jpa.AbstractBaseJPAEntity;

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
 *  Representa la tabla aplicaciones de la base de datos
 *  
 *  @author Altran
 */
@Entity
@Table(name = "VIEW_APLICACIONES")
@NamedQueries({
	@NamedQuery(name = "selectViewAplicacionByName_orderbyNombre_ASC", query = "SELECT e FROM ViewAplicacionJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and (e.eliminado is null or e.eliminado = 'N') order by e.nombre ASC"),
@NamedQuery(name = "selectViewAplicacionByName_orderbyNombre_DESC", query = "SELECT e FROM ViewAplicacionJPA e WHERE upper (e.nombre) like upper(:nombre)" +
		" and (e.eliminado is null or e.eliminado = 'N') order by e.nombre DESC"),
@NamedQuery(name = "selectViewAplicacion_count", query = "SELECT count(e) FROM ViewAplicacionJPA e WHERE upper (e.nombre) like upper(:nombre)" +
		" and (e.eliminado is null or e.eliminado = 'N')")})

public class ViewAplicacionJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewAplicacionJPA() {
		super();
		this.aplicacionId = null;
		this.nombre = null;
		this.descripcion = null;
		this.usuario = null;
		this.password = null;
		this.activo = null;
		this.fechaCreacion = null;
		this.creadoPor = null;
		this.fechaModificacion = null;
		this.modificadoPor = null;
		this.eliminado = null;
	}

	
	@Id
	@SequenceGenerator(name="aplicacion", sequenceName="APLICACIONID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="aplicacion")
	protected Integer aplicacionId;

	
	@Column(name = "NOMBRE")
	protected String nombre = null;
	
	@Column(name = "DESCRIPCION")
	protected String descripcion = null;
	
	@Column(name = "USUARIO")
	protected String usuario = null;
	
	@Column(name = "PASSWORD")
	protected String password = null;
	
	@Column(name = "ACTIVO")
	protected Integer activo = null;
	
	@Column(name = "FECHACREACION")
	protected Date fechaCreacion = null;
	
	@Column(name = "CREADOPOR")
	protected String creadoPor = null;
	
	@Column (name = "FECHAMODIFICACION")
	protected Date fechaModificacion = null;
	
	@Column (name = "MODIFICADOPOR")
	protected String modificadoPor = null;
	
	@Column (name= "ELIMINADO")
	protected String eliminado = null;
	
	public String getEliminado() {
		return eliminado;
	}
	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.aplicacionId;
	}
	public void setId(Object id){
		this.aplicacionId =(Integer)id;
	}

	public Integer getAplicacionId() {
		return aplicacionId;
	}
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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


	
	
}
