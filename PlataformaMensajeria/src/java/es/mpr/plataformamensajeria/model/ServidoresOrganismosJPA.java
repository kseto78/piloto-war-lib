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
 * <p>
 * Clase de entidad con las anotaciones JPA necesarias.
 * 
 * <p>
 * Representa la tabla servidores servicios de la base de datos
 * 
 * @author Selered
 */
@Entity
@Table(name = "TBL_SERVIDORES_ORGANISMOS")
@NamedQueries({ @NamedQuery(name = "selectServidoresOrganismoByServidorIdJPA", query = "SELECT e FROM ServidoresOrganismosJPA e WHERE e.servidorId= ? "),
		@NamedQuery(name = "selectServidoresOrganismoByIdOrganismoIdJPA", query = "SELECT e FROM ServidoresOrganismosJPA e WHERE e.organismoId = ?") })
public class ServidoresOrganismosJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServidoresOrganismosJPA() {
		super();
		this.servidorOrganismoId = null;
		this.servidorId = null;
		this.organismoId = null;
		this.numIntentos = null;
		this.headerSMS = null;
		this.proveedorUsuarioSMS = null;
		this.proveedorPasswordSMS = null;
		this.fechaCreacion = null;
		this.fechaModificacion = null;
		this.creadoPor = null;
		this.modificadoPor = null;
	}

	@Id
	@SequenceGenerator(name = "servidorOrganismo", sequenceName = "ORGANISMOSSERVIDORID_SEC", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servidorOrganismo")
	protected Integer servidorOrganismoId;

	@Column(name = "SERVIDORID")
	protected Integer servidorId;

	@Column(name = "ORGANISMOID")
	protected String organismoId;

	@Column(name = "NUMINTENTOS")
	protected Integer numIntentos = null;

	@Column(name = "HEADERSMS")
	protected String headerSMS = null;

	@Column(name = "PROVEEDORUSUARIOSMS")
	protected String proveedorUsuarioSMS;

	@Column(name = "PROVEEDORPASSWORDSMS")
	protected String proveedorPasswordSMS;

	@Column(name = "FECHACREACION")
	protected Date fechaCreacion = null;

	@Column(name = "CREADOPOR")
	protected String creadoPor = null;

	@Column(name = "FECHAMODIFICACION")
	protected Date fechaModificacion = null;

	@Column(name = "MODIFICADOPOR")
	protected String modificadoPor = null;

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.servidorOrganismoId;
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

	public Integer getServidorOrganismoId() {
		return servidorOrganismoId;
	}

	public void setServidorOrganismoId(Integer servidorOrganismoId) {
		this.servidorOrganismoId = servidorOrganismoId;
	}

	public Integer getServidorId() {
		return servidorId;
	}

	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}

	public String getOrganismoId() {
		return organismoId;
	}

	public void setOrganismoId(String organismoId) {
		this.organismoId = organismoId;
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

}
