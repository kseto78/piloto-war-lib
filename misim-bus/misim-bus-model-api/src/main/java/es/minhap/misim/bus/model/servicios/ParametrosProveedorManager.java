package es.minhap.misim.bus.model.servicios;

import java.util.List;

import es.minhap.misim.bus.model.ParametrosProveedor;
import es.minhap.misim.bus.query.ParametrosProveedorQuery;



public interface ParametrosProveedorManager {
		
	
	
	/**
	 * Obtiene una lista de proveedor.
	 * @param query
	 * @return
	 */	
	List<ParametrosProveedor> getParametrosProveedor(ParametrosProveedorQuery query);
	
}
