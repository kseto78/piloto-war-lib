package es.mpr.plataformamensajeria.beans;

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
public class ServidoresServiciosBean implements Audit {



	/**
	 * Constructor de servidores servicios bean.
	 */
	public ServidoresServiciosBean() {
		super();
		this.servidorServicioId = null;
		this.servidorId = null;
		this.servicioId = null;
		this.numIntentos = null;
		this.headerSMS = null;
		this.nombreServidor = null;
		this.nombreServicio = null;
		this.proveedorUsuarioSMS = null;
		this.proveedorPasswordSMS = null;
		this.organismoId = null;
		this.DIR3Organismo = null;
		this.fechaCreacion = null;
		this.fechaModificacion = null;
		this.creadoPor = null;
		this.modificadoPor = null;
		this.prefijoSMS = null;
	}

	/**  servidor servicio id. */
	protected Integer servidorServicioId;
	
	/**  servidor id. */
	protected Integer servidorId;
	
	/**  servicio id. */
	protected Integer servicioId;
	
	/**  num intentos. */
	protected Integer numIntentos = null;
	
	/**  header SMS. */
	protected String headerSMS = null;
	
	/**  nombre servidor. */
	protected String nombreServidor;
	
	/**  nombre servicio. */
	protected String nombreServicio;
	
	/**  proveedor usuario SMS. */
	protected String proveedorUsuarioSMS;
	
	/**  proveedor password SMS. */
	protected String proveedorPasswordSMS;
	
	/**  organismo id. */
	protected Integer organismoId;
	
	/**  DIR 3 organismo. */
	protected String DIR3Organismo;
	
	/**  fecha creacion. */
	protected Date fechaCreacion = null;
	
	/**  fecha modificacion. */
	protected Date fechaModificacion = null;
	
	/**  creado por. */
	protected String creadoPor = null;
	
	/**  modificado por. */
	protected String modificadoPor = null;
	
	/**  prefijo SMS. */
	protected String prefijoSMS = null;

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
	 * Obtener nombre servicio.
	 *
	 * @return nombre servicio
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * Modificar nombre servicio.
	 *
	 * @param nombreServicio new nombre servicio
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	/**
	 * Obtener id.
	 *
	 * @return id
	 */
	public Object getId() {
		return servidorServicioId;
	}

	/**
	 * Modificar id.
	 *
	 * @param id new id
	 */
	public void setId(Object id) {
		this.servidorServicioId = (Integer) id;
	}

	/**
	 * Obtener servidor servicio id.
	 *
	 * @return servidor servicio id
	 */
	public Integer getServidorServicioId() {
		return servidorServicioId;
	}

	/**
	 * Modificar servidor servicio id.
	 *
	 * @param servidorServicioId new servidor servicio id
	 */
	public void setServidorServicioId(Integer servidorServicioId) {
		this.servidorServicioId = servidorServicioId;
	}

	/**
	 * Obtener servidor id.
	 *
	 * @return servidor id
	 */
	public Integer getServidorId() {
		return servidorId;
	}

	/**
	 * Modificar servidor id.
	 *
	 * @param servidorId new servidor id
	 */
	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}

	/**
	 * Obtener servicio id.
	 *
	 * @return servicio id
	 */
	public Integer getServicioId() {
		return servicioId;
	}

	/**
	 * Modificar servicio id.
	 *
	 * @param servicioId new servicio id
	 */
	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
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
	 * Obtener DIR 3 organismo.
	 *
	 * @return DIR 3 organismo
	 */
	public String getDIR3Organismo() {
		return DIR3Organismo;
	}

	/**
	 * Modificar DIR 3 organismo.
	 *
	 * @param dIR3Organismo new DIR 3 organismo
	 */
	public void setDIR3Organismo(String dIR3Organismo) {
		DIR3Organismo = dIR3Organismo;
	}

	/**
	 * Obtener organismo id.
	 *
	 * @return organismo id
	 */
	public Integer getOrganismoId() {
		return organismoId;
	}

	/**
	 * Modificar organismo id.
	 *
	 * @param organismoId new organismo id
	 */
	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
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
	 * Obtener prefijo SMS.
	 *
	 * @return prefijo SMS
	 */
	public String getPrefijoSMS() {
		return prefijoSMS;
	}

	/**
	 * Modificar prefijo SMS.
	 *
	 * @param prefijoSMS new prefijo SMS
	 */
	public void setPrefijoSMS(String prefijoSMS) {
		this.prefijoSMS = prefijoSMS;
	}
	
}
