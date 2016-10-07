package es.mpr.plataformamensajeria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
 *  
 *  
 *  @author jgonzvil
 */
@Entity
@Table(name = "TBL_ADJUNTOS_HIST")
@NamedQueries({         
	@NamedQuery(name = "selectAllAdjuntoEmailHistoricosJPA", query = "SELECT e FROM AdjuntoEmailHistoricosJPA e")})

public class AdjuntoEmailHistoricosJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdjuntoEmailHistoricosJPA() {
		this.adjuntoId=null;
		this.nombre = null;
		this.fechaCreacion=null;
		this.creadoPor = null;
		this.fechaModificacion = null;
		this.modificadoPor = null;
		this.contenido = null;
		this.imagen = null;
		this.fechaHistorificado = null;

	}

	@Id
	@Column(name="ADJUNTOID")
	protected Integer adjuntoId;
	
	@Column(name="NOMBRE")
	protected String nombre;
	
	@Column(name = "FECHACREACION")
	protected Date fechaCreacion = null;
	
	@Column(name = "CREADOPOR")
	protected String creadoPor = null;
	
	@Column (name = "FECHAMODIFICACION")
	protected Date fechaModificacion = null;
	
	@Column (name = "MODIFICADOPOR")
	protected String modificadoPor = null;
	
	@Column(name="CONTENIDO")
	protected byte[] contenido;
	
	@Column (name="IMAGEN")
	protected Integer imagen;
	
	@Column (name="FECHAHISTORIFICACION")
	protected Date fechaHistorificado = null;
	
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
	public byte[] getContenido() {
		return contenido;
	}
	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
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
	public Date getFechaHistorificado() {
		return fechaHistorificado;
	}
	public void setFechaHistorificado(Date fechaHistorificado) {
		this.fechaHistorificado = fechaHistorificado;
	}
	
}
