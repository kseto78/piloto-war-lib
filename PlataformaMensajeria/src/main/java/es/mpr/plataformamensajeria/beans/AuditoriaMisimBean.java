package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Clase que representa un misim para la capa de presentaci&oacute;n
 * 
 * @author everis
 * 
 */
public class AuditoriaMisimBean implements Audit {

	protected Long idAuditoria = null;
	protected ProveedorMisimBean proveedor = null;
	protected AplicacionMisimBean aplicacion = null;
	protected ProductoBean producto = null;
	protected PeticionBean peticion = null;
	protected Long idMensaje = null;
	protected Date fechaCreacion = null;
	protected Date fechaActualizacion = null;
	protected Long idLote = null;

	/**
	 * Constructor por defecto
	 */
	public AuditoriaMisimBean() {
		super();
		this.idAuditoria = null;
		this.proveedor = null;
		this.aplicacion = null;
		this.producto = null;
		this.peticion = null;
		this.idMensaje = null;
		this.fechaCreacion = null;
		this.fechaActualizacion = null;
		this.idLote = null;
	}

	/**
	 * Obtener idAuditoria
	 * @return
	 */
	public Long getIdAuditoria() {
		return idAuditoria;
	}

	/**
	 * Modificar idAuditoria
	 * @param idAuditoria
	 */
	public void setIdAuditoria(Long idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	/**
	 * Obtener proveedor
	 * @return
	 */
	public ProveedorMisimBean getProveedor() {
		return proveedor;
	}

	/**
	 * Modificar proveedor
	 * @param proveedor
	 */
	public void setProveedor(ProveedorMisimBean proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * Obtener aplicacion
	 * @return
	 */
	public AplicacionMisimBean getAplicacion() {
		return aplicacion;
	}

	/**
	 * Modificar aplicacion
	 * @param aplicacionMisimBean
	 */
	public void setAplicacion(AplicacionMisimBean aplicacionMisimBean) {
		this.aplicacion = aplicacionMisimBean;
	}

	/**
	 * obtener producto
	 * @return
	 */
	public ProductoBean getProducto() {
		return producto;
	}

	/**
	 * Modificar producto
	 * @param producto
	 */
	public void setProducto(ProductoBean producto) {
		this.producto = producto;
	}

	/**
	 * Obtener peticion
	 * @return
	 */
	public PeticionBean getPeticion() {
		return peticion;
	}

	/**
	 * Modificar peticion
	 * @param peticion
	 */
	public void setPeticion(PeticionBean peticion) {
		this.peticion = peticion;
	}

	/**
	 * Obtener idMensaje
	 * @return
	 */
	public Long getIdMensaje() {
		return idMensaje;
	}

	/**
	 * Modificar idMensaje
	 * @param idMensaje
	 */
	public void setIdMensaje(Long idMensaje) {
		this.idMensaje = idMensaje;
	}

	/**
	 * Obtener fecha de creacion
	 * @return
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Modificar fecha de creacion
	 * @param fechaCreacion
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Obtener fecha de actualizacion
	 * @return
	 */
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	/**
	 * Modificar fecha de actualziacion
	 * @param fechaActualizacion
	 */
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	/**
	 * Obtener idLote
	 * @return
	 */
	public Long getIdLote() {
		return idLote;
	}

	/**
	 * Modificar idLote
	 * @param idLote
	 */
	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}

	/**
	 * Obtener XML
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
