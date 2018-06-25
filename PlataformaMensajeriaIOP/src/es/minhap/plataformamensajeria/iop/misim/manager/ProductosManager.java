package es.minhap.plataformamensajeria.iop.misim.manager;

import java.util.List;

import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.query.ProductoQuery;

public interface ProductosManager {

	/**
	 * recupera el producto segun datos pasados
	 * 
	 * @param ProductoQuery
	 * @return
	 */
	public List<Producto> getProductos(ProductoQuery producto);



	List<Producto> getProductosOrdenados();
	
	/**
	 * recupera el Producto por el id
	 * 
	 * @param idProducto
	 * @return Producto
	 */
	Producto getProducto(Long idProducto);

	/**
	 * Insertamos un nuevo producto
	 *
	 * @param producto
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return
	 */
	public Long insert(Producto producto, String source, String accion, Long accionId);

	/**
	 * Actualizamos el producto
	 *
	 * @param producto
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return
	 */
	
	void update(Producto producto, String source, String accion, Long accionId);
	
}
