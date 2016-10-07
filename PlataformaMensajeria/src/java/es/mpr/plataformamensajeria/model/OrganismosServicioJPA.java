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
 *  Representa la tabla servidores servicios de la base de datos
 *  
 *  @author Selered
 */
@Entity
@Table(name = "TBL_ORGANISMOS_SERVICIO")
@NamedQueries({
	@NamedQuery(name = "selectOrganismosServicioJPA", query = "SELECT e FROM OrganismosServicioJPA e WHERE to_char(e.organismoId) = ? "),
	@NamedQuery(name = "selectServicioOrganismosJPA", query = "SELECT e FROM OrganismosServicioJPA e WHERE to_char(e.servicioId) = ? ")
})

public class OrganismosServicioJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrganismosServicioJPA() {
		super();
		this.servicioOrganismoId = null;
		this.servicioId = null;
		this.organismoId = null;
		this.creadoPor = null;
		this.fechaCreacion = null;
		this.modificadoPor = null;
		this.fechaModificacion = null;
	
	}
	
	
	@Id
	@SequenceGenerator(name="organismosServicio", sequenceName="ORGANISMOSSERVICIOID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="organismosServicio")
	protected Integer servicioOrganismoId;

	
	@Column(name="SERVICIOID")
	protected Integer servicioId;
	
	@Column(name="ORGANISMOID")
	protected Integer organismoId;
	
	@Column(name = "CREADOPOR")
	protected String creadoPor = null;
	
	@Column(name = "FECHACREACION")
	protected Date fechaCreacion;
	
	@Column(name = "MODIFICADOPOR")
	protected String modificadoPor;
	
	@Column(name = "FECHAMODIFICACION")
	protected Date fechaModificacion;
	


	@Override
	public Object getId() {
		return this.servicioOrganismoId;
	}


	public Integer getServicioOrganismoId() {
		return servicioOrganismoId;
	}



	public void setServicioOrganismoId(Integer servicioOrganismoId) {
		this.servicioOrganismoId = servicioOrganismoId;
	}



	public Integer getServicioId() {
		return servicioId;
	}



	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}



	public Integer getOrganismoId() {
		return organismoId;
	}



	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}



	public String getCreadoPor() {
		return creadoPor;
	}



	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}



	public Date getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public String getModificadoPor() {
		return modificadoPor;
	}



	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}



	public Date getFechaModificacion() {
		return fechaModificacion;
	}



	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	
}
