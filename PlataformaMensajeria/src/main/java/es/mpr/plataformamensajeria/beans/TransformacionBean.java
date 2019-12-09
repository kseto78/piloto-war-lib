package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Clase que representa un misim para la capa de presentaci&oacute;n.
 *
 * @author everis
 */
public class TransformacionBean implements Audit {

	

	/**  id transformacion. */
	protected Long idTransformacion = null;

	/**  nombre. */
	protected String nombre = null;

	/**  fecha creacion. */
	protected Date fechaCreacion = null;

	/**  fecha actualizacion. */
	protected Date fechaActualizacion = null;

	/**  xsl peticion. */
	protected String xslPeticion = null;

	/**  xsl respuesta. */
	protected String xslRespuesta = null;

	/**  xsl fault. */
	protected String xslFault = null;


	/**
	 * Constructor de transformacion bean.
	 */
	public TransformacionBean() {
		super();
		this.idTransformacion = null;
		this.nombre = null;
		this.xslPeticion = null;
		this.xslRespuesta = null;
		this.xslFault = null;
		this.fechaCreacion = null;
		this.fechaActualizacion = null;

	}


	/**
	 * Obtener id transformacion.
	 *
	 * @return id transformacion
	 */
	public Long getIdTransformacion() {
		return idTransformacion;
	}


	/**
	 * Modificar id transformacion.
	 *
	 * @param idTransformacion new id transformacion
	 */
	public void setIdTransformacion(Long idTransformacion) {
		this.idTransformacion = idTransformacion;
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
	 * Obtener xsl peticion.
	 *
	 * @return xsl peticion
	 */
	public String getXslPeticion() {
		return xslPeticion;
	}


	/**
	 * Modificar xsl peticion.
	 *
	 * @param xslPeticion new xsl peticion
	 */
	public void setXslPeticion(String xslPeticion) {
		this.xslPeticion = xslPeticion;
	}


	/**
	 * Obtener xsl respuesta.
	 *
	 * @return xsl respuesta
	 */
	public String getXslRespuesta() {
		return xslRespuesta;
	}


	/**
	 * Modificar xsl respuesta.
	 *
	 * @param xslRespuesta new xsl respuesta
	 */
	public void setXslRespuesta(String xslRespuesta) {
		this.xslRespuesta = xslRespuesta;
	}


	/**
	 * Obtener xsl fault.
	 *
	 * @return xsl fault
	 */
	public String getXslFault() {
		return xslFault;
	}


	/**
	 * Modificar xsl fault.
	 *
	 * @param xslFault new xsl fault
	 */
	public void setXslFault(String xslFault) {
		this.xslFault = xslFault;
	}


	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
