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
 *  
 *  
 *  @author i-nercya
 */
@Entity
@Table(name = "TBL_ADJUNTOS")
@NamedQueries({         
	@NamedQuery(name = "selectAllAdjuntoEmailJPA", query = "SELECT e FROM AdjuntoEmailJPA e")})

public class AdjuntoEmailJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdjuntoEmailJPA() {
		this.adjuntoId = null;
		this.nombre = null;
		this.fechaCreacion=null;
		this.creadoPor = null;
		this.fechaModificacion = null;
		this.modificadoPor = null;
		this.contenido = null;
		this.imagen = null;

	}

	
	@Id
	@SequenceGenerator(name="adjunto", sequenceName="ADJUNTOID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="adjunto")
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
	
	/*@Column (name="COLUMNA_TEST")
	@Lob
	protected byte[] columnaTest;
	public byte[] getColumnaTest() {
		return columnaTest;
	}
	public void setColumnaTest(byte[] columnaTest) {
		this.columnaTest = columnaTest;
	}*/
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
	public byte[] getContenido() {
		return contenido;
	}
	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}
	public void setImagen(Integer imagen) {
		this.imagen = imagen;
	}
	public Integer getImagen() {
		return imagen;
	}

	
}
