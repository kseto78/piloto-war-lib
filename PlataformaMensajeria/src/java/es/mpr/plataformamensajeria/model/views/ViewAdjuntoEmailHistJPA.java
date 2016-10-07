package es.mpr.plataformamensajeria.model.views;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.map.j2ee.base.jpa.AbstractBaseJPAEntity;

/**
 *  <p>Clase de entidad con las anotaciones JPA necesarias.
 *  
 *  <p>
 *  
 *  
 *  @author jgonzvil
 */
@Entity
@Table(name = "VIEW_ADJUNTOS_HIST")
@NamedQueries({         
	@NamedQuery(name = "selectAllViewAdjuntoEmailHistJPA", 
			query = "SELECT e FROM ViewAdjuntoEmailHistJPA e"),
	@NamedQuery(name = "selectViewAdjuntoEmailByEmailIdHistJPA", 
			query = "SELECT e FROM ViewAdjuntoEmailHistJPA e where e.emailId=:emailId and (e.imagen is null or e.imagen=0)"),
	@NamedQuery(name = "selectViewImagenEmailByEmailIdHistJPA", 
			query = "SELECT e FROM ViewAdjuntoEmailHistJPA e where e.emailId=:emailId and e.imagen=1"),			
	@NamedQuery(name = "selectViewAdjuntoEmailByAdjuntoIdEmailIdHistJPA", 
			query = "SELECT e FROM ViewAdjuntoEmailHistJPA e where " +
					"e.adjuntoId=to_number(?) and e.emailId=to_number(?) and (e.imagen is null or e.imagen=0)")})

public class ViewAdjuntoEmailHistJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewAdjuntoEmailHistJPA() {
		this.adjuntoId=null;
		this.nombre = null;
		this.contenido = null;
		this.emailId = null;
		this.fechaCreacion=null;
		this.creadoPor = null;
		this.fechaModificacion = null;
		this.modificadoPor = null;
		this.fechaHistorificacion = null;

	}

	
	@Id
	@Column (name="ADJUNTOID")
	protected Integer adjuntoId;
	@Column (name="IMAGEN")
	protected Integer imagen;
	@Column(name="NOMBRE")
	protected String nombre;
	@Column(name="CONTENIDO")
	protected Blob contenido;
	@Column(name="EMAILID")
	protected Integer emailId;
	@Column(name = "FECHACREACION")
	protected Date fechaCreacion = null;
	@Column(name = "CREADOPOR")
	protected String creadoPor = null;
	@Column (name = "FECHAMODIFICACION")
	protected Date fechaModificacion = null;
	@Column (name = "MODIFICADOPOR")
	protected String modificadoPor = null;
	@Column (name = "FECHAHISTORIFICACION")
	protected Date fechaHistorificacion = null;

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.adjuntoId;
	}
	public void setId(Object id){
		this.adjuntoId =(Integer)id;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getAdjuntoId() {
		return adjuntoId;
	}
	public void setAdjuntoId(Integer adjuntoId) {
		this.adjuntoId = adjuntoId;
	}
/*	public Blob getContenido() {
		if(this.contenido!=null){
			return contenido;
		}else{
			return null;
		}
	}
	public void setContenido(Blob contenido) {
			this.contenido = contenido;
	}*/
	public Integer getEmailId() {
		return emailId;
	}
	public void setEmailId(Integer emailId) {
		this.emailId = emailId;
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
	public void setImagen(Integer imagen) {
		this.imagen = imagen;
	}
	public Integer getImagen() {
		return imagen;
	}
	public Date getFechaHistorificacion() {
		return fechaHistorificacion;
	}
	public void setFechaHistorificacion(Date fechaHistorificacion) {
		this.fechaHistorificacion = fechaHistorificacion;
	}
	public Blob getContenido() {
		return contenido;
	}
	public void setContenido(Blob contenido) {
		this.contenido = contenido;
	}
	
}
