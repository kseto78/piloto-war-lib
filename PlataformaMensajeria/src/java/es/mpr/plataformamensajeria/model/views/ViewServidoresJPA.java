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
 *  Representa la vista VIEW_SERVIDORES de la base de datos
 *  
 *  @author Altran
 */
@Entity
@Table(name = "VIEW_SERVIDORES")
/*@NamedNativeQueries({
	@NamedNativeQuery(
			name="selectViewServidor", 
			query= "{ call PKG_SERVIDOR.searchServidor(:name,:tipo,?) }",
			resultClass=ServidoresJPA.class,
			hints={
				@QueryHint(name = "org.hibernate.callable", value = "true"),
				@QueryHint(name = "org.hibernate.readOnly",value = "true")
			}
		)
})
@SqlResultSetMapping(name="param_cur", columns=@ColumnResult(name="param_cur"))*/

@NamedQueries({
	@NamedQuery(name = "selectViewServidorJPA", query = "SELECT e FROM ViewServidoresJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N')"),
	@NamedQuery(name= "selectViewServidorByName_orderbyNombre_ASC", query="SELECT e FROM ViewServidoresJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N') order by e.nombre ASC"),
	@NamedQuery(name= "selectViewServidorByName_orderbyNombre_DESC", query="SELECT e FROM ViewServidoresJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N')  order by e.nombre DESC"),
	@NamedQuery(name= "selectViewServidorByName_orderbyDescripcion_ASC", query="SELECT e FROM ViewServidoresJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N')  order by e.descripcion ASC"),
	@NamedQuery(name= "selectViewServidorByName_orderbyDescripcion_DESC", query="SELECT e FROM ViewServidoresJPA e WHERE upper (e.nombre) like upper(:nombre)" +
			" and e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N')  order by e.descripcion DESC"),
	@NamedQuery(name = "selectViewServidor_orderbyId_ASC", query = "SELECT e FROM ViewServidoresJPA e WHERE upper (e.nombre) like upper(:nombre) and" +
			" e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N')" +
			" order by e.id ASC"),
	@NamedQuery(name = "selectViewServidor_orderbyNombre_ASC", query = "SELECT e FROM ViewServidoresJPA e WHERE upper (e.nombre) like upper(:nombre) and" +
			" e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N')" +
			"  order by e.nombre ASC"),
	@NamedQuery(name = "selectViewServidor_orderbyDescripcion_ASC", query = "SELECT e FROM ViewServidoresJPA e WHERE upper (e.nombre) like upper(:nombre) and" +
			" e.tipo = :tipo and (e.eliminado is null or e.eliminado = 'N')" +
			"  order by e.descripcion ASC"),
	@NamedQuery(name = "selectViewServidor_count", query = "SELECT count(e) FROM ViewServidoresJPA e WHERE upper (e.nombre) like upper(:nombre) and" +
			" e.tipo = :tipo  and (e.eliminado is null or e.eliminado = 'N')"),
	@NamedQuery(name = "selectViewServidorByName_count", query = "SELECT count(e) FROM ViewServidoresJPA e WHERE upper (e.nombre) like upper(:nombre) and" +
			" e.tipo = :tipo  and (e.eliminado is null or e.eliminado = 'N')"),
	@NamedQuery(name = "selectAllServidores", query = "SELECT e FROM ViewServidoresJPA e WHERE (e.eliminado is null or e.eliminado = 'N') order by e.nombre ASC"),
	@NamedQuery(name= "selectServidoresByTipo", query="SELECT e FROM ViewServidoresJPA e WHERE e.tipo=:tipo and (e.eliminado is null or e.eliminado = 'N')"),
	@NamedQuery(name= "selectServidoresByTipo2", query="SELECT e FROM ViewServidoresJPA e WHERE to_char(e.tipo) = ? and e.activo = '1' and (e.eliminado is null or e.eliminado = 'N')"),
	@NamedQuery(name= "selectServidores", query="SELECT e FROM ViewServidoresJPA e WHERE e.tipo= 2 and (e.eliminado is null or e.eliminado = 'N')"),
	@NamedQuery(name = "selectAllServidoresBBDD", query = "SELECT e FROM ViewServidoresJPA e order by e.nombre ASC")})
public class ViewServidoresJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	
	public ViewServidoresJPA() {
		super();
		this.servidorId = null;
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
	protected Long servidorId;

	
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
		return this.servidorId;
	}


	public Long getServidorId() {
		return servidorId;
	}

    public void setId(Long servidorId){
    	this.servidorId = servidorId;
    }
	public void setServidorId(Long servidorId) {
		
		this.servidorId = servidorId;
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
		if(this.tipo!=null&&this.tipo.intValue()==3){
			return null;
		}
		return new Integer(2);
	}


	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	
}
