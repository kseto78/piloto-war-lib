package es.minhap.misim.bus.model.servicios;

import java.util.List;

import es.minhap.misim.bus.model.Proveedor;
import es.minhap.misim.bus.query.ProveedorQuery;



public interface ProveedorManager {
		
	/**
	 * Guarda un elemento proveedor. Devuelve su identificador en caso de exito o null si hay error.
	 * @param proveedor
	 * @return
	 */
	Long guardarProveedor(Proveedor proveedor);
	
	
	/**
	 * Inserta un elemento proveedor. Devuelve su identificador en caso de exito o null si hay error.
	 * @param proveedor
	 * @return
	 */	
	Long insertProveedor(Proveedor proveedor);
	
	/**
	 * Actualiza un elemento proveedor. Devuelve true en caso de exito y false en caso de error.
	 * @param proveedor
	 * @return
	 */
	boolean updateProveedor(Proveedor proveedor);
	
	/**
	 * Obtiene un elemento proveedor
	 * @param idProveedor
	 * @return
	 */
	Proveedor getProveedorById(Long idProveedor);
	
	/**
	 * Elimina un elemento proveedor
	 * @param id
	 * @return
	 */
	void eliminarProveedor(Long id);
	
	/**
	 * Obtiene una lista de proveedor.
	 * @param query
	 * @return
	 */	
	List<Proveedor> getProveedor(ProveedorQuery query);
	
}
