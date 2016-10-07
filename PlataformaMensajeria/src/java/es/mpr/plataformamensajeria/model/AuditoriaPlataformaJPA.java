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
 *  Representa la vista canales de la base de datos
 *  
 *  @author Altran
 */
@Entity
@Table(name = "TBL_LOG")
@NamedQueries({         
	@NamedQuery(name = "selectAuditoriaPlataformaJPA", query = "SELECT e FROM AuditoriaPlataformaJPA e")})

public class AuditoriaPlataformaJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuditoriaPlataformaJPA() {
		super();
		this.logId = null;
		this.sourceName = null;
		this.sourceId = null;
		this.logAccion = null;
		this.logDescripcion = null;
		this.adtUsuario = null;
		this.adtFecha = null;
		this.sourceDescription=null;
	}

	
	@Id
	@SequenceGenerator(name="log", sequenceName="LOGID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="log")
	protected Integer logId;

	
	@Column(name="SOURCENAME")
	protected String sourceName;
	
	@Column(name="SOURCEID")
	protected Integer sourceId;
	
	@Column(name = "LOGACCION")
	protected Integer logAccion = null;
	
	@Column(name = "LOGDESCRIPCION")
	protected String logDescripcion = null;
	
	@Column(name = "ADTUSUARIO")
	protected String adtUsuario = null;
	
	@Column (name = "ADTFECHA")
	protected Date adtFecha = null;
	
	@Column (name = "SOURCEDESCRIPTION")
	protected String sourceDescription = null;
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.logId;
	}
	public void setId(Object id){
		this.logId =(Integer)id;
	}
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	public Integer getLogAccion() {
		return logAccion;
	}
	public void setLogAccion(Integer logAccion) {
		this.logAccion = logAccion;
	}
	public String getLogDescripcion() {
		return logDescripcion;
	}
	public void setLogDescripcion(String logDescripcion) {
		this.logDescripcion = logDescripcion;
	}
	public String getAdtUsuario() {
		return adtUsuario;
	}
	public void setAdtUsuario(String adtUsuario) {
		this.adtUsuario = adtUsuario;
	}
	public Date getAdtFecha() {
		return adtFecha;
	}
	public void setAdtFecha(Date adtFecha) {
		this.adtFecha = adtFecha;
	}
	public String getSourceDescription() {
		return sourceDescription;
	}
	public void setSourceDescription(String sourceDescription) {
		this.sourceDescription = sourceDescription;
	}

	
}
