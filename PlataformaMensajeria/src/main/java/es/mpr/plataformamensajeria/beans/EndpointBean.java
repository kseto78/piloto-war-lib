package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Clase que representa un misim para la capa de presentaci&oacute;n.
 *
 * @author everis
 */
public class EndpointBean implements Audit {

	

	/**
	 * Constructor de endpoint bean.
	 */
	public EndpointBean() {
		super();
		this.idEndpoint = null;
		this.nombre = null;
		this.urlEndpoint = null;
		this.serviceName = null;
		this.portName = null;
		this.urlTargetName = null;
		this.urlOperation = null;
		this.timeout = null;
		this.fechaCreacion = null;
		this.fechaActualizacion = null;
		this.comunicacion = null;
	}

	/**  id endpoint. */
	protected Long idEndpoint = null;
	
	/**  nombre. */
	protected String nombre = null;
	
	/**  url endpoint. */
	protected String urlEndpoint = null;
	
	/**  service name. */
	protected String serviceName = null;	
	
	/**  port name. */
	protected String portName = null;
	
	/**  url target name. */
	protected String urlTargetName = null;
	
	/**  url operation. */
	protected String urlOperation = null;
	
	/**  timeout. */
	protected Long timeout = null;
	
	/**  fecha creacion. */
	protected Date fechaCreacion = null;
	
	/**  fecha actualizacion. */
	protected Date fechaActualizacion = null;
	
	/**  comunicacion. */
	protected ComunicacionBean comunicacion = null;


	/**
	 * Obtener id endpoint.
	 *
	 * @return id endpoint
	 */
	public Long getIdEndpoint() {
		return idEndpoint;
	}


	/**
	 * Modificar id endpoint.
	 *
	 * @param idEndpoint new id endpoint
	 */
	public void setIdEndpoint(Long idEndpoint) {
		this.idEndpoint = idEndpoint;
	}


	/**
	 * Obtener nombre.
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * Modificar nombre.
	 *
	 * @param nombre new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * Obtener url endpoint.
	 *
	 * @return url endpoint
	 */
	public String getUrlEndpoint() {
		return urlEndpoint;
	}


	/**
	 * Modificar url endpoint.
	 *
	 * @param urlEndpoint new url endpoint
	 */
	public void setUrlEndpoint(String urlEndpoint) {
		this.urlEndpoint = urlEndpoint;
	}


	/**
	 * Obtener service name.
	 *
	 * @return service name
	 */
	public String getServiceName() {
		return serviceName;
	}


	/**
	 * Modificar service name.
	 *
	 * @param serviceName new service name
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	/**
	 * Obtener port name.
	 *
	 * @return port name
	 */
	public String getPortName() {
		return portName;
	}


	/**
	 * Modificar port name.
	 *
	 * @param portName new port name
	 */
	public void setPortName(String portName) {
		this.portName = portName;
	}

	/**
	 * Obtener timeout.
	 *
	 * @return timeout
	 */
	public Long getTimeout() {
		return timeout;
	}


	/**
	 * Modificar timeout.
	 *
	 * @param timeout new timeout
	 */
	public void setTimeout(Long timeout) {
		this.timeout = timeout;
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
	 * Obtener fecha actualizacion.
	 *
	 * @return fecha actualizacion
	 */
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}


	/**
	 * Modificar fecha actualizacion.
	 *
	 * @param fechaActualizacion new fecha actualizacion
	 */
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}


	/**
	 * Obtener comunicacion.
	 *
	 * @return comunicacion
	 */
	public ComunicacionBean getComunicacion() {
		return comunicacion;
	}


	/**
	 * Modificar comunicacion.
	 *
	 * @param comunicacion new comunicacion
	 */
	public void setComunicacion(ComunicacionBean comunicacion) {
		this.comunicacion = comunicacion;
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
	 * Obtener url operation.
	 *
	 * @return the urlOperation
	 */
	public String getUrlOperation() {
		return urlOperation;
	}


	/**
	 * Modificar url operation.
	 *
	 * @param urlOperation the urlOperation to set
	 */
	public void setUrlOperation(String urlOperation) {
		this.urlOperation = urlOperation;
	}


	/**
	 * Obtener url target name.
	 *
	 * @return the urlTargetName
	 */
	public String getUrlTargetName() {
		return urlTargetName;
	}


	/**
	 * Modificar url target name.
	 *
	 * @param urlTargetName the urlTargetName to set
	 */
	public void setUrlTargetName(String urlTargetName) {
		this.urlTargetName = urlTargetName;
	}

	
}
