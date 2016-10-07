package es.mpr.plataformamensajeria.model;

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
 *  Representa la tabla SERVIDORES de la base de datos
 *  
 *  @author jgonzvil
 */
@Entity
@Table(name = "TBL_SERVIDORES")

@NamedQueries({
	@NamedQuery(name = "selectReceptorJPA", query = "SELECT e FROM ReceptoresSMSJPA e WHERE upper (e.nombre) like :nombre and" +
			" e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N')"),
	@NamedQuery(name= "selectReceptorByName_orderbyNombre_ASC", query="SELECT e FROM ReceptoresSMSJPA e WHERE upper (e.nombre) like :nombre and" +
			" e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N') order by e.nombre ASC"),
	@NamedQuery(name= "selectReceptorByName_orderbyNombre_DESC", query="SELECT e FROM ReceptoresSMSJPA e WHERE upper (e.nombre) like :nombre and" +
			" e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N') order by e.nombre DESC"),
	@NamedQuery(name= "selectReceptorByName_orderbyDescripcion_ASC", query="SELECT e FROM ReceptoresSMSJPA e WHERE upper (e.nombre) like :nombre and" +
			" e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N') order by e.descripcion ASC"),
	@NamedQuery(name= "selectReceptorByName_orderbyDescripcion_DESC", query="SELECT e FROM ReceptoresSMSJPA e WHERE upper (e.nombre) like :nombre and" +
			" e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N') order by e.descripcion DESC"),
	@NamedQuery(name = "selectReceptor_orderbyId_ASC", query = "SELECT e FROM ReceptoresSMSJPA e WHERE upper (e.nombre) like :nombre and" +
			" e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N') order by e.id ASC"),
	@NamedQuery(name = "selectReceptor_orderbyNombre_ASC", query = "SELECT e FROM ReceptoresSMSJPA e WHERE upper (e.nombre) like :nombre and" +
			" e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N') order by e.nombre ASC"),
	@NamedQuery(name = "selectReceptor_orderbyDescripcion_ASC", query = "SELECT e FROM ReceptoresSMSJPA e WHERE upper (e.nombre) like :nombre and" +
			" e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N') order by e.descripcion ASC"),
	@NamedQuery(name = "selectReceptor_count", query = "SELECT count(e) FROM ReceptoresSMSJPA e WHERE upper (e.nombre) like :nombre and" +
			" e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N')"),
	@NamedQuery(name = "selectReceptorByName_count", query = "SELECT count(e) FROM ReceptoresSMSJPA e WHERE upper (e.nombre) like :nombre and" +
			" e.tipo = :tipo")

})
public class ReceptoresSMSJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Los Receptores SMS son de tipo 3
	 */
	public static final Integer TIPO = Integer.valueOf(3);
	
	public ReceptoresSMSJPA() {
		super();
		this.receptorSMSId = null;
		this.nombre = null;
		this.descripcion = null;
		this.porDefecto = null;
		this.activo = null;
		this.fechaCreacion = null;
		this.creadoPor = null;
		this.fechaModificacion = null;
		this.modificadoPor = null;
		this.tipo = null;
		this.externalId = null;
		this.eliminado = null;

	}

	
	@Id
	@SequenceGenerator(name="servidor", sequenceName="SERVIDORID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="servidor")
	@Column(name="SERVIDORID")
	protected Long receptorSMSId;

	
	@Column(name = "NOMBRE")
	protected String nombre = null;
	
	@Column(name = "DESCRIPCION")
	protected String descripcion = null;
	

	@Column(name = "PORDEFECTO")
	protected Integer porDefecto = null;
	
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
	
	@Column (name = "TIPO")
	protected Integer tipo = null;
	
	@Column (name ="EXTERNALID")
	protected String  externalId = null;
	
	@Column (name="ELIMINADO")
	protected String eliminado = null;
	
	@Column (name="USUARIO")
	protected String usuario = null;
	
	@Column (name="PASSWORD")
	protected String password = null;
	
	
	public String getEliminado() {
		return eliminado;
	}


	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}


	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.receptorSMSId;
	}


	public Long getReceptorSMSId() {
		return receptorSMSId;
	}

    public void setId(Long receptorSMSId){
    	this.receptorSMSId = receptorSMSId;
    }
	public void setReceptorSMSId(Long receptorSMSId) {
		
		this.receptorSMSId = receptorSMSId;
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


	public Integer getPorDefecto() {
		return porDefecto;
	}


	public void setPorDefecto(Integer porDefecto) {
		this.porDefecto = porDefecto;
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

	public Integer getTipo() {
		return this.TIPO;
	}


	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}


	public String getExternalId() {
		return externalId;
	}


	public void setExternalId(String externalId) {
		this.externalId = externalId;
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
	
}
