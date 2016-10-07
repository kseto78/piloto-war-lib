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
@Table(name = "VIEW_USUARIOS_APLICACIONES")
@NamedQueries({         
	@NamedQuery(name = "selectViewUsuarioAplicacionJPA_orderbyUsuario_ASC", 
			query = "SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e order by e.nombreUsuario ASC"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPA_orderbyUsuario_DESC", 
	query = "SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e order by e.nombreUsuario DESC"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByNombre_orderbyUsuario_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE upper (e.nombreUsuario) like upper(concat(concat('%',:nombreUsuario),'%')) order by e.nombreUsuario ASC"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByNombre_orderbyUsuario_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE upper (e.nombreUsuario) like upper(concat(concat('%',:nombreUsuario),'%')) order by e.nombreUsuario DESC"),
	
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByNombreAplicacion_orderbyUsuario_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE upper (e.nombreUsuario) like upper(concat(concat('%',:nombreUsuario),'%')) " +
			"and to_char(e.aplicacionId)=:aplicacionId order by e.nombreUsuario ASC"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByNombreAplicacion_orderbyUsuario_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE upper (e.nombreUsuario) like upper(concat(concat('%',:nombreUsuario),'%')) " +
			"and to_char(e.aplicacionId)=:aplicacionId order by e.nombreUsuario DESC"),

	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByNombreRol_orderbyUsuario_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE upper (e.nombreUsuario) like upper(concat(concat('%',:nombreUsuario),'%')) " +
			"and to_char(e.rolId)=:rolId order by e.nombreUsuario ASC"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByNombreRol_orderbyUsuario_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE upper (e.nombreUsuario) like upper(concat(concat('%',:nombreUsuario),'%')) " +
			"and to_char(e.rolId)=:rolId order by e.nombreUsuario DESC"),

	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByNombreAplicacionRol_orderbyUsuario_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE upper (e.nombreUsuario) like upper(concat(concat('%',:nombreUsuario),'%')) " +
			" and to_char(e.aplicacionId)=:aplicacionId and to_char(e.rolId)=:rolId order by e.nombreUsuario ASC"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByNombreAplicacionRol_orderbyUsuario_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE upper (e.nombreUsuario) like upper(concat(concat('%',:nombreUsuario),'%'))  " +
			"and to_char(e.aplicacionId)=:aplicacionId and to_char(e.rolId)=:rolId order by e.nombreUsuario DESC"),
	
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByAplicacion_orderbyUsuario_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE to_char(e.aplicacionId)=:aplicacionId order by e.nombreUsuario ASC"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByAplicacion_orderbyUsuario_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE to_char(e.aplicacionId)=:aplicacionId order by e.nombreUsuario DESC"),

	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByAplicacionRol_orderbyUsuario_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE to_char(e.aplicacionId)=:aplicacionId and to_char(e.rolId)=:rolId order by e.nombreUsuario ASC"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByAplicacionRol_orderbyUsuario_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE to_char(e.aplicacionId)=:aplicacionId and to_char(e.rolId)=:rolId order by e.nombreUsuario DESC"),
	
	
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByRol_orderbyUsuario_ASC", 
	query="SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE to_char(e.rolId)=:rolId order by e.nombreUsuario ASC"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByRol_orderbyUsuario_DESC", 
	query="SELECT distinct(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE to_char(e.rolId)=:rolId order by e.nombreUsuario DESC"),

	@NamedQuery(name = "selectViewUsuarioAplicacionJPA_count", 
	query = "SELECT count(e) FROM ViewUsuarioAplicacionJPA e"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByNombre_count", 
	query="SELECT count(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE upper (e.nombreUsuario) like upper(concat(concat('%',:nombreUsuario),'%'))"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByNombreAplicacion_count", 
	query="SELECT count(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE upper (e.nombreUsuario) like upper(concat(concat('%',:nombreUsuario),'%')) " +
			"and to_char(e.aplicacionId)=:aplicacionId"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByNombreRol_count", 
	query="SELECT count(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE upper (e.nombreUsuario) like upper(concat(concat('%',:nombreUsuario),'%')) " +
			"and to_char(e.rolId)=:rolId"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByNombreAplicacionRol_count", 
	query="SELECT count(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE upper (e.nombreUsuario) like upper(concat(concat('%',:nombreUsuario),'%'))  " +
			"and to_char(e.aplicacionId)=:aplicacionId and to_char(e.rolId)=:rolId"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByAplicacion_count", 
	query="SELECT count(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE to_char(e.aplicacionId)=:aplicacionId"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByAplicacionRol_count", 
	query="SELECT count(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE to_char(e.aplicacionId)=:aplicacionId and to_char(e.rolId)=:rolId"),
	@NamedQuery(name = "selectViewUsuarioAplicacionJPAByRol_count", 
	query="SELECT count(e) FROM ViewUsuarioAplicacionJPA e " +
			"WHERE to_char(e.rolId)=:rolId"),
	@NamedQuery(name = "selectViewUsuarioAplicacionByUsuarioId", 
	query="SELECT e FROM ViewUsuarioAplicacionJPA e " +
			"WHERE to_char(e.usuarioId)=? order by e.nombreAplicacion ASC"),
	@NamedQuery(name = "selectViewUsuarioAplicacionByAplicacionId", 
	query="SELECT e FROM ViewUsuarioAplicacionJPA e " +
			"WHERE to_char(e.aplicacionId)=? order by e.nombreAplicacion ASC")})

	

public class ViewUsuarioAplicacionJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewUsuarioAplicacionJPA() {
		super();
		this.usuarioId = null;
		this.fechaCreacion = null;
		this.creadoPor = null;
		this.usuarioAplicacionId = null;
		this.aplicacionId=null;
		this.modo = null;
		this.fechaCreacion = null;
		this.creadoPor = null;
		this.nombreUsuario = null;
		this.rolUsuario = null;

	}

	
	public Integer getUsuarioAplicacionId() {
		return usuarioAplicacionId;
	}
	public void setUsuarioAplicacionId(Integer usuarioAplicacionId) {
		this.usuarioAplicacionId = usuarioAplicacionId;
	}
	public Integer getAplicacionId() {
		return aplicacionId;
	}
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}
	public Integer getModo() {
		return modo;
	}
	public void setModo(Integer modo) {
		this.modo = modo;
	}
	public String getNombreAplicacion() {
		return nombreAplicacion;
	}
	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getRolUsuario() {
		return rolUsuario;
	}
	public void setRolUsuario(String rolUsuario) {
		this.rolUsuario = rolUsuario;
	}


	@Id
	@SequenceGenerator(name="usuario", sequenceName="USUARIOAPLICACIONID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuario")
	protected Integer usuarioAplicacionId;

	@Column(name="USUARIOID")
	protected Integer usuarioId;
	
	@Column(name="APLICACIONID")
	protected Integer aplicacionId;
	
	@Column(name= "ROLID")
	protected Integer rolId;
	
	@Column(name="MODO")
	protected Integer modo;
	
	
	@Column(name = "FECHACREACION")
	protected Date fechaCreacion = null;
	
	@Column(name = "CREADOPOR")
	protected String creadoPor = null;
	
	@Column(name = "NOMBREAPLICACION")
	protected String nombreAplicacion;
	
	@Column(name="NOMBREUSUARIO")
	protected String nombreUsuario;
	
	@Column(name="ROLUSUARIO")
	protected String rolUsuario;
	
	public Object getId() {
		// TODO Auto-generated method stub
		return this.usuarioId;
	}
	public void setId(Object id){
		this.usuarioId =(Integer)id;
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


	public Integer getRolId() {
		return rolId;
	}


	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}
}
