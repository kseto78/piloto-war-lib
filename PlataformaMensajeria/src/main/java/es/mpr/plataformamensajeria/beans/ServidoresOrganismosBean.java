package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

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
 * Representa la vista canales de la base de datos
 * 
 * @author Altran
 */
public class ServidoresOrganismosBean implements Audit, Serializable {

	private static final long serialVersionUID = 3312760048196008475L;

	public ServidoresOrganismosBean() {
		super();
		this.servidorOrganismoId = null;
		this.servidorId = null;
		this.organismoId = null;
		this.numIntentos = null;
		this.headerSMS = null;
		this.nombreServidor = null;
		this.nombreOrganismo = null;
		this.proveedorUsuarioSMS = null;
		this.proveedorPasswordSMS = null;
		this.fechaCreacion = null;
		this.fechaModificacion = null;
		this.creadoPor = null;
		this.modificadoPor = null;
		this.tipo = null;
	}

	protected Long servidorOrganismoId;
	protected Long servidorId;
	protected Long organismoId;
	protected Integer numIntentos = null;
	protected String headerSMS = null;
	protected String nombreServidor;
	protected String nombreOrganismo;
	protected String proveedorUsuarioSMS;
	protected String proveedorPasswordSMS;
	protected Date fechaCreacion = null;
	protected String creadoPor = null;
	protected Date fechaModificacion = null;
	protected String modificadoPor = null;
	protected Integer tipo = null;

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
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

	public String getNombreServidor() {
		return nombreServidor;
	}

	public void setNombreServidor(String nombreServidor) {
		this.nombreServidor = nombreServidor;
	}

	public String getNombreOrganismo() {
		return nombreOrganismo;
	}

	public void setNombreOrganismo(String nombreOrganismo) {
		this.nombreOrganismo = nombreOrganismo;
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

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Long getServidorOrganismoId() {
		return servidorOrganismoId;
	}

	public void setServidorOrganismoId(Long servidorOrganismoId) {
		this.servidorOrganismoId = servidorOrganismoId;
	}

	public Long getServidorId() {
		return servidorId;
	}

	public void setServidorId(Long servidorId) {
		this.servidorId = servidorId;
	}

	public Long getOrganismoId() {
		return organismoId;
	}

	public void setOrganismoId(Long organismoId) {
		this.organismoId = organismoId;
	}

}
