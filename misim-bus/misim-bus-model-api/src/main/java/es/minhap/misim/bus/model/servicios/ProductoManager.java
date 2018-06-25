package es.minhap.misim.bus.model.servicios;

import java.util.List;

import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.query.ProductoQuery;



public interface ProductoManager {
		
	/**
	 * Guarda un elemento producto. Devuelve su identificador en caso de exito o null si hay error.
	 * @param producto
	 * @return
	 */
	Long guardarProducto(Producto producto);
	
	
	/**
	 * Inserta un elemento producto. Devuelve su identificador en caso de exito o null si hay error.
	 * @param producto
	 * @return
	 */	
	Long insertProducto(Producto producto);
	
	/**
	 * Actualiza un elemento producto. Devuelve true en caso de exito y false en caso de error.
	 * @param producto
	 * @return
	 */
	boolean updateProducto(Producto producto);
	
	/**
	 * Obtiene un elemento producto
	 * @param idProducto
	 * @return
	 */
	Producto getProductoById(Long idProducto);
	
	/**
	 * Elimina un elemento producto
	 * @param id
	 * @return
	 */
	void eliminarProducto(Long id);
	
	/**
	 * Obtiene una lista de producto.
	 * @param query
	 * @return
	 */	
	List<Producto> getProducto(ProductoQuery query);
	
	/**
	 * Obtiene un producto dado su codigo. Devuelve null si no existe.
	 * @param codigoServicio
	 * @return
	 */
	Producto getProductoByCodigo(String codigo);
	
}
