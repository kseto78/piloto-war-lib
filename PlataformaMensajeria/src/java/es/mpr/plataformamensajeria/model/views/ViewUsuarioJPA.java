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
 *  Representa la vista canales de la base de datos
 *  
 *  @author Altran
 */
@Entity
@Table(name = "VIEW_USUARIOS")
@NamedQueries({         
	@NamedQuery(name = "selectViewUsuarioJPA_orderbyUsuario_ASC", 
			query = "SELECT distinct(e) FROM ViewUsuarioJPA e order by e.nombre ASC"),
	@NamedQuery(name = "selectViewUsuarioJPA_orderbyUsuario_DESC", 
	query = "SELECT distinct(e) FROM ViewUsuarioJPA e order by e.nombre DESC"),
	@NamedQuery(name = "selectViewUsuarioJPAByNombre_orderbyUsuario_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario) order by e.nombre ASC"),
	@NamedQuery(name = "selectViewUsuarioJPAByNombre_orderbyUsuario_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario) order by e.nombre DESC"),
	
	@NamedQuery(name = "selectViewUsuarioJPAByNombreAplicacion_orderbyUsuario_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario) and " +
			"to_char(e.aplicacionId)=:aplicacionId order by e.nombre ASC"),
	@NamedQuery(name = "selectViewUsuarioJPAByNombreAplicacion_orderbyUsuario_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario) and " +
			"to_char(e.aplicacionId)=:aplicacionId order by e.nombre DESC"),

	@NamedQuery(name = "selectViewUsuarioJPAByNombreRol_orderbyUsuario_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario) and " +
			"to_char(e.rolId)=:rolId order by e.nombre ASC"),
	@NamedQuery(name = "selectViewUsuarioJPAByNombreRol_orderbyUsuario_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario) and " +
			"to_char(e.rolId)=:rolId order by e.nombre DESC"),

	@NamedQuery(name = "selectViewUsuarioJPAByNombreAplicacionRol_orderbyUsuario_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario)  and " +
			"to_char(e.aplicacionId)=:aplicacionId and " +
			"to_char(e.rolId)=:rolId order by e.nombre ASC"),
	@NamedQuery(name = "selectViewUsuarioJPAByNombreAplicacionRol_orderbyUsuario_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario)  and " +
			"to_char(e.aplicacionId)=:aplicacionId and " +
			"to_char(e.rolId)=:rolId order by e.nombre DESC"),
	
	@NamedQuery(name = "selectViewUsuarioJPAByAplicacion_orderbyUsuario_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.aplicacionId)=:aplicacionId order by e.nombre ASC"),
	@NamedQuery(name = "selectViewUsuarioJPAByAplicacion_orderbyUsuario_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.aplicacionId)=:aplicacionId order by e.nombre DESC"),

	@NamedQuery(name = "selectViewUsuarioJPAByAplicacionRol_orderbyUsuario_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.aplicacionId)=:aplicacionId and " +
			"to_char(e.rolId)=:rolId order by e.nombre ASC"),
	@NamedQuery(name = "selectViewUsuarioJPAByAplicacionRol_orderbyUsuario_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.aplicacionId)=:aplicacionId and " +
			"to_char(e.rolId)=:rolId order by e.nombre DESC"),
	
	
	@NamedQuery(name = "selectViewUsuarioJPAByRol_orderbyUsuario_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.rolId)=:rolId order by e.nombre ASC"),
	@NamedQuery(name = "selectViewUsuarioJPAByRol_orderbyUsuario_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.rolId)=:rolId order by e.nombre DESC"),

	@NamedQuery(name = "selectViewUsuarioJPA_count", 
	query = "SELECT count(e) FROM ViewUsuarioJPA e"),
	@NamedQuery(name = "selectViewUsuarioJPAByNombre_count", 
	query="SELECT count(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) " +
			"like upper(:nombreUsuario)"),
	@NamedQuery(name = "selectViewUsuarioJPAByNombreAplicacion_count", 
	query="SELECT count(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) " +
			"like upper(:nombreUsuario) " +
			"and to_char(e.aplicacionId)=:aplicacionId"),
	@NamedQuery(name = "selectViewUsuarioJPAByNombreRol_count", 
	query="SELECT count(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) " +
			"like upper(:nombreUsuario) " +
			"and to_char(e.rolId)=:rolId"),
	@NamedQuery(name = "selectViewUsuarioJPAByNombreAplicacionRol_count", 
	query="SELECT count(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) " +
			"like upper(:nombreUsuario)  and " +
			"to_char(e.aplicacionId)=:aplicacionId and to_char(e.rolId)=:rolId"),
	@NamedQuery(name = "selectViewUsuarioJPAByAplicacion_count", 
	query="SELECT count(e) FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.aplicacionId)=:aplicacionId"),
	@NamedQuery(name = "selectViewUsuarioJPAByAplicacionRol_count", 
	query="SELECT count(e) FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.aplicacionId)=:aplicacionId and " +
			"to_char(e.rolId)=:rolId"),
	@NamedQuery(name = "selectViewUsuarioJPAByRol_count", 
	query="SELECT count(e) FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.rolId)=:rolId"),
	@NamedQuery(name = "selectViewUsuarioByUsuarioId", 
	query="SELECT e FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.usuarioId)=? order by e.nombreAplicacion ASC"),
	@NamedQuery(name = "selectViewUsuarioByAplicacionId", 
	query="SELECT e FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.aplicacionId)=? order by e.nombreAplicacion ASC"),
			



			@NamedQuery(name = "selectViewUsuarioJPA_orderbyRol_ASC", 
			query = "SELECT distinct(e) FROM ViewUsuarioJPA e order by e.rolId ASC"),
	@NamedQuery(name = "selectViewUsuarioJPA_orderbyRol_DESC", 
	query = "SELECT distinct(e) FROM ViewUsuarioJPA e order by e.rolId DESC"),
	@NamedQuery(name = "selectViewUsuarioJPAByNombre_orderbyRol_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario) order by e.rolId ASC"),
	@NamedQuery(name = "selectViewUsuarioJPAByNombre_orderbyRol_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario) order by e.rolId DESC"),
	
	@NamedQuery(name = "selectViewUsuarioJPAByNombreAplicacion_orderbyRol_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario) and " +
			"to_char(e.aplicacionId)=:aplicacionId order by e.rolId ASC"),
	@NamedQuery(name = "selectViewUsuarioJPAByNombreAplicacion_orderbyRol_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario) and " +
			"to_char(e.aplicacionId)=:aplicacionId order by e.rolId DESC"),

	@NamedQuery(name = "selectViewUsuarioJPAByNombreRol_orderbyRol_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario) and " +
			"to_char(e.rolId)=:rolId order by e.rolId ASC"),
	@NamedQuery(name = "selectViewUsuarioJPAByNombreRol_orderbyRol_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario) and " +
			"to_char(e.rolId)=:rolId order by e.rolId DESC"),

	@NamedQuery(name = "selectViewUsuarioJPAByNombreAplicacionRol_orderbyRol_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario)  and " +
			"to_char(e.aplicacionId)=:aplicacionId and " +
			"to_char(e.rolId)=:rolId order by e.rolId ASC"),
	@NamedQuery(name = "selectViewUsuarioJPAByNombreAplicacionRol_orderbyRol_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE upper (e.nombre) like " +
			"upper(:nombreUsuario)  and " +
			"to_char(e.aplicacionId)=:aplicacionId and " +
			"to_char(e.rolId)=:rolId order by e.rolId DESC"),
	
	@NamedQuery(name = "selectViewUsuarioJPAByAplicacion_orderbyRol_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.aplicacionId)=:aplicacionId order by e.rolId ASC"),
	@NamedQuery(name = "selectViewUsuarioJPAByAplicacion_orderbyRol_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.aplicacionId)=:aplicacionId order by e.rolId DESC"),

	@NamedQuery(name = "selectViewUsuarioJPAByAplicacionRol_orderbyRol_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.aplicacionId)=:aplicacionId and " +
			"to_char(e.rolId)=:rolId order by e.rolId ASC"),
	@NamedQuery(name = "selectViewUsuarioJPAByAplicacionRol_orderbyRol_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.aplicacionId)=:aplicacionId and " +
			"to_char(e.rolId)=:rolId order by e.rolId DESC"),
	
	
	@NamedQuery(name = "selectViewUsuarioJPAByRol_orderbyRol_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.rolId)=:rolId order by e.rolId ASC"),
	@NamedQuery(name = "selectViewUsuarioJPAByRol_orderbyRol_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioJPA e WHERE " +
			"to_char(e.rolId)=:rolId order by e.rolId DESC")})



public class ViewUsuarioJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewUsuarioJPA() {
		super();
		this.usuarioId = null;
		this.nombre = null;
		this.login = null;
		this.email = null;
		this.fechaCreacion = null;
		this.creadoPor = null;
		this.activo = null;
		this.rolId = null;

	}

	@Id
	@Column(name="USUARIOID", unique=false,nullable=true)
	protected Integer usuarioId;
	@Column(name="IDCOMPUESTO")
	protected String idCompuesto;
	
	@Column(name="NOMBRE")
	protected String nombre;
	
	@Column(name="LOGIN")
	protected String login;
	
	@Column(name = "ACTIVO")
	protected Integer activo = null;
	
	@Column(name = "FECHACREACION")
	protected Date fechaCreacion = null;
	
	@Column(name = "CREADOPOR")
	protected String creadoPor = null;
	
	@Column (name = "EMAIL")
	protected String email = null;
	@Column(name="ROLID")
	protected Integer rolId= null;
	
	@Column(name="APLICACIONID")
	protected Integer aplicacionId;
	
	@Column(name="NOMBREAPLICACION")
	protected String nombreAplicacion;
	public Integer getAplicacionId() {
		return aplicacionId;
	}
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}
	public String getNombreAplicacion() {
		return nombreAplicacion;
	}
	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.usuarioId;
	}
	public void setId(Object id){
		this.usuarioId =(Integer)id;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getRolId() {
		return rolId;
	}
	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}
	public String getIdCompuesto() {
		return idCompuesto;
	}
	public void setIdCompuesto(String idCompuesto) {
		this.idCompuesto = idCompuesto;
	}
	
}
