package es.mpr.plataformamensajeria.beans;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Clase que representa un misim para la capa de presentaci&oacute;n.
 *
 * @author everis
 */
public class PeticionBean implements Audit {

	

	/**
	 * Constructor de peticion bean.
	 */
	public PeticionBean() {
		super();
		this.idPeticion = null;
		this.proveedor = null;
		this.estado = null;
		this.aplicacion = null;
		this.producto = null;
		this.idMensaje = null;
		this.mensajePeticion = null;
		this.mensajeRespuesta = null;
	}

	
	/**  id peticion. */
	protected Long idPeticion = null;
	
	/**  proveedor. */
	protected ProveedorMisimBean proveedor = null;
	
	/**  estado. */
	protected EstadoBean estado = null;
	
	/**  aplicacion. */
	protected AplicacionBean aplicacion = null;
	
	/**  producto. */
	protected ProductoBean producto = null;
	
	/**  id mensaje. */
	protected Long idMensaje = null;
	
	/**  mensaje peticion. */
	protected String mensajePeticion = null;
	
	/**  mensaje respuesta. */
	protected String mensajeRespuesta = null;


	/**
	 * Obtener id peticion.
	 *
	 * @return id peticion
	 */
	public Long getIdPeticion() {
		return idPeticion;
	}


	/**
	 * Modificar id peticion.
	 *
	 * @param idPeticion new id peticion
	 */
	public void setIdPeticion(Long idPeticion) {
		this.idPeticion = idPeticion;
	}


	/**
	 * Obtener proveedor.
	 *
	 * @return proveedor
	 */
	public ProveedorMisimBean getProveedor() {
		return proveedor;
	}


	/**
	 * Modificar proveedor.
	 *
	 * @param proveedor new proveedor
	 */
	public void setProveedor(ProveedorMisimBean proveedor) {
		this.proveedor = proveedor;
	}


	/**
	 * Obtener estado.
	 *
	 * @return estado
	 */
	public EstadoBean getEstado() {
		return estado;
	}


	/**
	 * Modificar estado.
	 *
	 * @param estado new estado
	 */
	public void setEstado(EstadoBean estado) {
		this.estado = estado;
	}


	/**
	 * Obtener aplicacion.
	 *
	 * @return aplicacion
	 */
	public AplicacionBean getAplicacion() {
		return aplicacion;
	}


	/**
	 * Modificar aplicacion.
	 *
	 * @param aplicacion new aplicacion
	 */
	public void setAplicacion(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}


	/**
	 * Obtener producto.
	 *
	 * @return producto
	 */
	public ProductoBean getProducto() {
		return producto;
	}


	/**
	 * Modificar producto.
	 *
	 * @param producto new producto
	 */
	public void setProducto(ProductoBean producto) {
		this.producto = producto;
	}


	/**
	 * Obtener id mensaje.
	 *
	 * @return id mensaje
	 */
	public Long getIdMensaje() {
		return idMensaje;
	}


	/**
	 * Modificar id mensaje.
	 *
	 * @param idMensaje new id mensaje
	 */
	public void setIdMensaje(Long idMensaje) {
		this.idMensaje = idMensaje;
	}


	/**
	 * Obtener mensaje peticion.
	 *
	 * @return mensaje peticion
	 */
	public String getMensajePeticion() {
		return mensajePeticion;
	}


	/**
	 * Modificar mensaje peticion.
	 *
	 * @param mensajePeticion new mensaje peticion
	 */
	public void setMensajePeticion(String mensajePeticion) {
		this.mensajePeticion = mensajePeticion;
	}


	/**
	 * Obtener mensaje respuesta.
	 *
	 * @return mensaje respuesta
	 */
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}


	/**
	 * Modificar mensaje respuesta.
	 *
	 * @param mensajeRespuesta new mensaje respuesta
	 */
	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
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
