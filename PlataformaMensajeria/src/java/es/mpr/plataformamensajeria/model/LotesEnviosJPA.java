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
 *  @author jgonzvil
 */
@Entity
@Table(name = "TBL_LOTESENVIOS")
@NamedQueries({         
	@NamedQuery(name = "selectAllLotesEnviosJPA", query = "SELECT e FROM LotesEnviosJPA e")})

public class LotesEnviosJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LotesEnviosJPA() {
		this.loteEnvioId=null;
		this.nombre=null;
		this.descripcion=null;
		this.servicioId=null;
		this.estadoEnvioId=null;
		this.fechaCreacion=null;
		this.creadorPor=null;
		this.fechaModificacion=null;
		this.modificadoPor=null;
		
	}
	
	@Id
	@SequenceGenerator(name="loteEnvio", sequenceName="LOTEENVIOID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="loteEnvio")
	protected Integer loteEnvioId;
	
	@Column(name="NOMBRE")
	protected String nombre;
	
	@Column(name="DESCRIPCION")
	protected String descripcion;
	
	@Column(name="SERVICIOID")
	protected Integer servicioId;
	
	@Column(name="ESTADOENVIOID")
	protected Integer estadoEnvioId;
	
	@Column(name="FECHACREACION")
	protected Date fechaCreacion;
	
	@Column(name="CREADOPOR")
	protected String creadorPor;
	
	@Column(name="FECHAMODIFICACION")
	protected Date fechaModificacion;
	
	@Column(name="MODIFICADOPOR")
	protected String modificadoPor;
	

	@Override
	public Object getId() {
		return this.loteEnvioId;
	}
	public void setId(Object id){
		this.loteEnvioId = (Integer)id;
	}
	public Integer getLoteEnvioId() {
		return loteEnvioId;
	}
	public void setLoteEnvioId(Integer loteEnvioId) {
		this.loteEnvioId = loteEnvioId;
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
	public Integer getServicioId() {
		return servicioId;
	}
	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}
	public Integer getEstadoEnvioId() {
		return estadoEnvioId;
	}
	public void setEstadoEnvioId(Integer estadoEnvioId) {
		this.estadoEnvioId = estadoEnvioId;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getCreadorPor() {
		return creadorPor;
	}
	public void setCreadorPor(String creadorPor) {
		this.creadorPor = creadorPor;
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
