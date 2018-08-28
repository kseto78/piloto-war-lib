package es.mpr.plataformamensajeria.beans;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Clase que representa un misim para la capa de presentaci&oacute;n.
 *
 * @author everis
 */
public class ProductoBean implements Audit {

	

	/**
	 * Constructor de producto bean.
	 */
	public ProductoBean() {
		super();
		this.idProducto = null;
		this.nombre = null;
		this.codigo = null;
	}


	/**  id producto. */
	protected Long idProducto = null;
	
	/**  nombre. */
	protected String nombre = null;
	
	/**  codigo. */
	protected String codigo = null;

	
	/**
	 * Obtener id producto.
	 *
	 * @return id producto
	 */
	public Long getIdProducto() {
		return idProducto;
	}


	/**
	 * Modificar id producto.
	 *
	 * @param idProducto new id producto
	 */
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
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
	 * Obtener codigo.
	 *
	 * @return codigo
	 */
	public String getCodigo() {
		return codigo;
	}


	/**
	 * Modificar codigo.
	 *
	 * @param codigo new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
