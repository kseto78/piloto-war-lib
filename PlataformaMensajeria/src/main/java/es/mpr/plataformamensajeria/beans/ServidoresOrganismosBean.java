package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

// TODO: Auto-generated Javadoc
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

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3312760048196008475L;

	/**  servidor organismo id. */
	protected Long servidorOrganismoId;

	/**  servidor id. */
	protected Long servidorId;

	/**  organismo id. */
	protected Long organismoId;

	/**  num intentos. */
	protected Integer numIntentos = null;

	/**  header SMS. */
	protected String headerSMS = null;

	/**  nombre servidor. */
	protected String nombreServidor;

	/**  nombre organismo. */
	protected String nombreOrganismo;

	/**  proveedor usuario SMS. */
	protected String proveedorUsuarioSMS;

	/**  proveedor password SMS. */
	protected String proveedorPasswordSMS;

	/**  fecha creacion. */
	protected Date fechaCreacion = null;

	/**  creado por. */
	protected String creadoPor = null;

	/**  fecha modificacion. */
	protected Date fechaModificacion = null;

	/**  modificado por. */
	protected String modificadoPor = null;

	/**  tipo. */
	protected Integer tipo = null;

	/**
	 * Constructor de servidores organismos bean.
	 */
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

	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtener proveedor usuario SMS.
	 *
	 * @return proveedor usuario SMS
	 */
	public String getProveedorUsuarioSMS() {
		return proveedorUsuarioSMS;
	}

	/**
	 * Modificar proveedor usuario SMS.
	 *
	 * @param proveedorUsuarioSMS new proveedor usuario SMS
	 */
	public void setProveedorUsuarioSMS(String proveedorUsuarioSMS) {
		this.proveedorUsuarioSMS = proveedorUsuarioSMS;
	}

	/**
	 * Obtener proveedor password SMS.
	 *
	 * @return proveedor password SMS
	 */
	public String getProveedorPasswordSMS() {
		return proveedorPasswordSMS;
	}

	/**
	 * Modificar proveedor password SMS.
	 *
	 * @param proveedorPasswordSMS new proveedor password SMS
	 */
	public void setProveedorPasswordSMS(String proveedorPasswordSMS) {
		this.proveedorPasswordSMS = proveedorPasswordSMS;
	}

	/**
	 * Obtener num intentos.
	 *
	 * @return num intentos
	 */
	public Integer getNumIntentos() {
		return numIntentos;
	}

	/**
	 * Modificar num intentos.
	 *
	 * @param numIntentos new num intentos
	 */
	public void setNumIntentos(Integer numIntentos) {
		this.numIntentos = numIntentos;
	}

	/**
	 * Obtener header SMS.
	 *
	 * @return header SMS
	 */
	public String getHeaderSMS() {
		return headerSMS;
	}

	/**
	 * Modificar header SMS.
	 *
	 * @param headerSMS new header SMS
	 */
	public void setHeaderSMS(String headerSMS) {
		this.headerSMS = headerSMS;
	}

	/**
	 * Obtener nombre servidor.
	 *
	 * @return nombre servidor
	 */
	public String getNombreServidor() {
		return nombreServidor;
	}

	/**
	 * Modificar nombre servidor.
	 *
	 * @param nombreServidor new nombre servidor
	 */
	public void setNombreServidor(String nombreServidor) {
		this.nombreServidor = nombreServidor;
	}

	/**
	 * Obtener nombre organismo.
	 *
	 * @return nombre organismo
	 */
	public String getNombreOrganismo() {
		return nombreOrganismo;
	}

	/**
	 * Modificar nombre organismo.
	 *
	 * @param nombreOrganismo new nombre organismo
	 */
	public void setNombreOrganismo(String nombreOrganismo) {
		this.nombreOrganismo = nombreOrganismo;
	}

	/**
	 * Obtener fecha creacion.
	 *
	 * @return fecha creacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Modificar fecha creacion.
	 *
	 * @param fechaCreacion new fecha creacion
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Obtener creado por.
	 *
	 * @return creado por
	 */
	public String getCreadoPor() {
		return creadoPor;
	}

	/**
	 * Modificar creado por.
	 *
	 * @param creadoPor new creado por
	 */
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	/**
	 * Obtener fecha modificacion.
	 *
	 * @return fecha modificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * Modificar fecha modificacion.
	 *
	 * @param fechaModificacion new fecha modificacion
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * Obtener modificado por.
	 *
	 * @return modificado por
	 */
	public String getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * Modificar modificado por.
	 *
	 * @param modificadoPor new modificado por
	 */
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * Obtener tipo.
	 *
	 * @return tipo
	 */
	public Integer getTipo() {
		return tipo;
	}

	/**
	 * Modificar tipo.
	 *
	 * @param tipo new tipo
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	/**
	 * Obtener servidor organismo id.
	 *
	 * @return servidor organismo id
	 */
	public Long getServidorOrganismoId() {
		return servidorOrganismoId;
	}

	/**
	 * Modificar servidor organismo id.
	 *
	 * @param servidorOrganismoId new servidor organismo id
	 */
	public void setServidorOrganismoId(Long servidorOrganismoId) {
		this.servidorOrganismoId = servidorOrganismoId;
	}

	/**
	 * Obtener servidor id.
	 *
	 * @return servidor id
	 */
	public Long getServidorId() {
		return servidorId;
	}

	/**
	 * Modificar servidor id.
	 *
	 * @param servidorId new servidor id
	 */
	public void setServidorId(Long servidorId) {
		this.servidorId = servidorId;
	}

	/**
	 * Obtener organismo id.
	 *
	 * @return organismo id
	 */
	public Long getOrganismoId() {
		return organismoId;
	}

	/**
	 * Modificar organismo id.
	 *
	 * @param organismoId new organismo id
	 */
	public void setOrganismoId(Long organismoId) {
		this.organismoId = organismoId;
	}

}
