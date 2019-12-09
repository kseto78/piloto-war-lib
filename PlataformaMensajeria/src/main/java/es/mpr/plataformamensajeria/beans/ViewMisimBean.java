package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Representa los envios de tipo email
 * <p>.
 *
 * @author everis
 */
public class ViewMisimBean implements Audit {

	/**  fecha creacion. */
	private Date fechaCreacion;

	/**  fecha creacion str. */
	private String fechaCreacionStr;

	/**  id lote. */
	private Long idLote;

	/**  proveedor producto. */
	private String proveedorProducto;

	/**  id peticion. */
	private Long idPeticion;


	/**
	 * Constructor de view misim bean.
	 */
	public ViewMisimBean() {
		this.fechaCreacion = null;
		this.idLote = null;
		this.proveedorProducto = null;
		this.idPeticion = null;
		
	}

	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		return null;
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
	 * Obtener id lote.
	 *
	 * @return id lote
	 */
	public Long getIdLote() {
		return idLote;
	}


	/**
	 * Modificar id lote.
	 *
	 * @param idLote new id lote
	 */
	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}


	/**
	 * Obtener proveedor producto.
	 *
	 * @return proveedor producto
	 */
	public String getProveedorProducto() {
		return proveedorProducto;
	}


	/**
	 * Modificar proveedor producto.
	 *
	 * @param proveedorProducto new proveedor producto
	 */
	public void setProveedorProducto(String proveedorProducto) {
		this.proveedorProducto = proveedorProducto;
	}


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
	 * Obtener fecha creacion str.
	 *
	 * @return fecha creacion str
	 */
	public String getFechaCreacionStr() {
		return fechaCreacionStr;
	}


	/**
	 * Modificar fecha creacion str.
	 *
	 * @param fechaCreacionStr new fecha creacion str
	 */
	public void setFechaCreacionStr(String fechaCreacionStr) {
		this.fechaCreacionStr = fechaCreacionStr;
	}
	
}
