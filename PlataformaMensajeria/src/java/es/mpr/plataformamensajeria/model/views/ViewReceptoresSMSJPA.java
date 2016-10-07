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
 *  Representa la tabla SERVIDORES de la base de datos
 *  
 *  @author jgonzvil
 */
@Entity
@Table(name = "VIEW_SERVIDORES")

@NamedQueries({
	@NamedQuery(name = "selectViewReceptorSMSJPA", query = "SELECT e FROM ViewReceptoresSMSJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N')"),
	@NamedQuery(name= "selectViewReceptorSMSByName_orderbyNombre_ASC", query="SELECT e FROM ViewReceptoresSMSJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N') order by e.nombre ASC"),
	@NamedQuery(name= "selectViewReceptorSMSByName_orderbyNombre_DESC", query="SELECT e FROM ViewReceptoresSMSJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N') order by e.nombre DESC"),
	@NamedQuery(name= "selectViewReceptorSMSByName_orderbyDescripcion_ASC", query="SELECT e FROM ViewReceptoresSMSJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N') order by e.descripcion ASC"),
	@NamedQuery(name= "selectViewReceptorSMSByName_orderbyDescripcion_DESC", query="SELECT e FROM ViewReceptoresSMSJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N') order by e.descripcion DESC"),
	@NamedQuery(name = "selectViewReceptorSMS_orderbyId_ASC", query = "SELECT e FROM ViewReceptoresSMSJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N') order by e.id ASC"),
	@NamedQuery(name = "selectViewReceptorSMS_orderbyNombre_ASC", query = "SELECT e FROM ViewReceptoresSMSJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N') order by e.nombre ASC"),
	@NamedQuery(name = "selectViewReceptorSMS_orderbyDescripcion_ASC", query = "SELECT e FROM ViewReceptoresSMSJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N') order by e.descripcion ASC"),
	@NamedQuery(name = "selectViewReceptorSMS_count", query = "SELECT count(e) FROM ViewReceptoresSMSJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N')"),
	@NamedQuery(name = "selectViewReceptorSMSByName_count", query = "SELECT count(e) FROM ViewReceptoresSMSJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N')"),
			@NamedQuery(name = "selectViewReceptorSMS", query = "SELECT e FROM ViewReceptoresSMSJPA e WHERE" +
					" e.tipo = 3 and (e.eliminado is null or e.eliminado = 'N')")

})
public class ViewReceptoresSMSJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Los Receptores SMS son de tipo 3
	 */
	public static final Integer TIPO = Integer.valueOf(3);
	
	public ViewReceptoresSMSJPA() {
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
		this.urlDestino = null;
		this.tipo = null;
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
	
	@Column (name = "URLDESTINO")
	protected String urlDestino = null;
	
	@Column (name = "TIPO")
	protected Integer tipo = null;
	
	@Column (name = "ELIMINADO")
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


	public String getUrlDestino() {
		return urlDestino;
	}


	public void setUrlDestino(String urlDestino) {
		this.urlDestino = urlDestino;
	}


	public Integer getTipo() {
		return this.TIPO;
	}


	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}
