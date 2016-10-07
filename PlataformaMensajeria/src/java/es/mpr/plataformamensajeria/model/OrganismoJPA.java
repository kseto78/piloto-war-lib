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
 * Representa la tabla aplicaciones de la base de datos
 * 
 * @author Altran
 */
@Entity
@Table(name = "TBL_ORGANISMOS")
@NamedQueries({
	@NamedQuery(name = "selectOrganismoByName_orderbyNombre_ASC", query = "SELECT e FROM OrganismoJPA e WHERE upper (e.nombre) like upper(concat(concat('%',:nombre),'%'))" +
			" and (e.eliminado is null or e.eliminado = 'N') order by e.nombre ASC"),
@NamedQuery(name = "selectOrganismoByName_orderbyNombre_DESC", query = "SELECT e FROM OrganismoJPA e WHERE upper (e.nombre) like upper(concat(concat('%',:nombre),'%'))" +
		" and (e.eliminado is null or e.eliminado = 'N') order by e.nombre DESC"),
@NamedQuery(name = "selectOrganismo_count", query = "SELECT count(e) FROM OrganismoJPA e WHERE upper (e.nombre) like upper(concat(concat('%',:nombre),'%'))" +
		" and (e.eliminado is null or e.eliminado = 'N')"),
@NamedQuery(name="selectOrganismoJPA",
query="SELECT e FROM OrganismoJPA e WHERE (e.eliminado is null or e.eliminado = 'N') order by e.nombre ASC")})



public class OrganismoJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrganismoJPA() {
		super();
		this.organismoId = null;
		this.DIR3 = null;
		this.nombre = null;
		this.descripcion = null;
		this.activo = null;
		this.fechaCreacion = null;
		this.fechaModificacion = null;
		this.creadoPor = null;
		this.modificadoPor = null;
		this.externalId = null;
		this.nombreCuentaEnvio = null;
		this.historificacion = null;
		this.motivoHistorificacion = null;
		this.conservacion = null;
		this.motivoConservacion = null;

	}

	@Id
	@SequenceGenerator(name = "organismo", sequenceName = "ORGANISMOID_SEC", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organismo")
	protected Integer organismoId;

	@Column(name = "DIR3")
	protected String DIR3 = null;

	@Column(name = "NOMBRE")
	protected String nombre = null;

	@Column(name = "DESCRIPCION")
	protected String descripcion = null;

	@Column(name = "ACTIVO")
	protected Integer activo = null;

	@Column(name = "FECHACREACION")
	protected Date fechaCreacion = null;

	@Column(name = "CREADOPOR")
	protected String creadoPor = null;

	@Column(name = "FECHAMODIFICACION")
	protected Date fechaModificacion = null;

	@Column(name = "MODIFICADOPOR")
	protected String modificadoPor = null;

	@Column(name = "EXTERNALID")
	protected Integer externalId = null;

	@Column(name = "NOMBRECUENTAENVIO")
	protected String nombreCuentaEnvio = null;

	@Column(name = "HISTORIFICACION")
	protected Integer historificacion = null;

	@Column(name = "MOTIVOHISTORIFICACION")
	protected String motivoHistorificacion = null;

	@Column(name = "CONSERVACION")
	protected Integer conservacion = null;

	@Column(name = "MOTIVOCONSERVACION")
	protected String motivoConservacion = null;

	@Column(name = "ELIMINADO")
	protected String eliminado = null;

	public Integer getOrganismoId() {
		return organismoId;
	}

	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}

	public String getDIR3() {
		return DIR3;
	}

	public void setDIR3(String dIR3) {
		DIR3 = dIR3;
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

	public Integer getExternalId() {
		return externalId;
	}

	public void setExternalId(Integer externalId) {
		this.externalId = externalId;
	}

	public String getNombreCuentaEnvio() {
		return nombreCuentaEnvio;
	}

	public void setNombreCuentaEnvio(String nombreCuentaEnvio) {
		this.nombreCuentaEnvio = nombreCuentaEnvio;
	}

	public Integer getHistorificacion() {
		return historificacion;
	}

	public void setHistorificacion(Integer historificacion) {
		this.historificacion = historificacion;
	}

	public String getMotivoHistorificacion() {
		return motivoHistorificacion;
	}

	public void setMotivoHistorificacion(String motivoHistorificacion) {
		this.motivoHistorificacion = motivoHistorificacion;
	}

	public Integer getConservacion() {
		return conservacion;
	}

	public void setConservacion(Integer conservacion) {
		this.conservacion = conservacion;
	}

	public String getMotivoConservacion() {
		return motivoConservacion;
	}

	public void setMotivoConservacion(String motivoConservacion) {
		this.motivoConservacion = motivoConservacion;
	}

	public String getEliminado() {
		return eliminado;
	}

	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}

	@Override
	public Object getId() {
		return this.organismoId;
	}

}
