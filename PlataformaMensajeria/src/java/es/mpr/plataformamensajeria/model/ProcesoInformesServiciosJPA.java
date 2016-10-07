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
@Table(name = "TBL_PROCESO_INFORMES_SERVICIOS")
@NamedQueries({         
	@NamedQuery(name = "selectAllProcesoInformesServiciosJPA", query = "SELECT e FROM ProcesoInformesServiciosJPA e")})

public class ProcesoInformesServiciosJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProcesoInformesServiciosJPA() {
		this.procesoInformesServiciosId=null;
		this.codigoEstado=null;
		this.descripcionEstado=null;
		this.fechaCreacion=null;
		this.fechaInicio=null;
		this.fechaFin=null;
	}

	
	@Id
	@SequenceGenerator(name="procesoInformesServicios", sequenceName="PROCESOINFORMESSERVICIOSID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="procesoInformesServicios")
	protected Long procesoInformesServiciosId;
	
	@Column(name="CODIGOESTADO")
	protected String codigoEstado;

	@Column(name="DESCRIPCIONESTADO")
	protected String descripcionEstado;
	
	@Column(name="FECHACREACION")
	protected Date fechaCreacion;
	
	@Column(name="FECHAINICIO")
	protected Date fechaInicio;
	
	@Column(name="FECHAFIN")
	protected Date fechaFin;
	
	@Override
	public Long getId() {
		return this.procesoInformesServiciosId;
	}
	
	public void setId(Long id){
		this.procesoInformesServiciosId =(Long)id;
	}

	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	
	
}
