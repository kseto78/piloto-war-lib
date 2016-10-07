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

import com.map.j2ee.auditoria.ifaces.Audit;
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
@Table(name = "TBL_SERVIDORES_SERVICIOS")
@NamedQueries({
	@NamedQuery(name = "selectServidoresServiciosByServicioIdJPA", query = "SELECT e FROM ServidoresServiciosJPA e WHERE e.servicioId= ? ")})

public class ServidoresServiciosJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServidoresServiciosJPA() {
		super();
		this.servidorServicioId = null;
		this.servidorId = null;
		this.servicioId = null;
		this.numIntentos = null;
		this.headerSMS = null;
		this.proveedorUsuarioSMS = null;
		this.proveedorPasswordSMS = null;
	}

	
	@Id
	@SequenceGenerator(name="servidorServicio", sequenceName="SERVIDORSERVICIOID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="servidorServicio")
	protected Integer servidorServicioId;

	
	@Column(name="SERVIDORID")
	protected Integer servidorId;
	
	@Column(name="SERVICIOID")
	protected String servicioId;
	
	@Column(name = "NUMINTENTOS")
	protected Integer numIntentos = null;
	
	@Column(name = "HEADERSMS")
	protected String headerSMS = null;
	
	@Column(name = "PROVEEDORUSUARIOSMS")
	protected String proveedorUsuarioSMS;
	
	@Column(name = "PROVEEDORPASSWORDSMS")
	protected String proveedorPasswordSMS;
	
	public Integer getServidorServicioId() {
		return servidorServicioId;
	}



	public void setServidorServicioId(Integer servidorServicioId) {
		this.servidorServicioId = servidorServicioId;
	}



	public Integer getServidorId() {
		return servidorId;
	}



	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}



	public String getServicioId() {
		return servicioId;
	}



	public void setServicioId(String servicioId) {
		this.servicioId = servicioId;
	}



	public Integer getNumIntentos() {
		return numIntentos;
	}



	public void setNumIntentos(Integer numIntentos) {
		this.numIntentos = numIntentos;
	}



	public String getHeaderSMS() {
		return headerSMS;
	}



	public void setHeaderSMS(String headerSMS) {
		this.headerSMS = headerSMS;
	}



	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.servidorServicioId;
	}



	public String getProveedorUsuarioSMS() {
		return proveedorUsuarioSMS;
	}



	public void setProveedorUsuarioSMS(String proveedorUsuarioSMS) {
		this.proveedorUsuarioSMS = proveedorUsuarioSMS;
	}



	public String getProveedorPasswordSMS() {
		return proveedorPasswordSMS;
	}



	public void setProveedorPasswordSMS(String proveedorPasswordSMS) {
		this.proveedorPasswordSMS = proveedorPasswordSMS;
	}


 	
	
}
