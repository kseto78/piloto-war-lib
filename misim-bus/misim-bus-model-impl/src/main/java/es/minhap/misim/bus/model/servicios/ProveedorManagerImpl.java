package es.minhap.misim.bus.model.servicios;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import es.minhap.misim.bus.dao.ProveedorDAO;
import es.minhap.misim.bus.model.Proveedor;
import es.minhap.misim.bus.query.ProveedorQuery;

@Service("proveedorManager")
public class ProveedorManagerImpl implements ProveedorManager{

	private static final Logger logger = Logger.getLogger(ProveedorManagerImpl.class);
		
	@Resource
	ProveedorDAO ProveedorDAO;
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ProveedorManager#guardarProveedor(es.minhap.misim.bus.model.Proveedor)
	 */
	public Long guardarProveedor(Proveedor proveedor){
		logger.info("guardarProveedor - start");
		return ProveedorDAO.insert(proveedor);
	}	
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ProveedorManager#insertProveedor(es.minhap.misim.bus.model.Proveedor)
	 */
	public Long insertProveedor(Proveedor proveedor) {
		Long id = null;
		if(proveedor!=null){
			id = ProveedorDAO.insert(proveedor);
		}
		return id;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ProveedorManager#getProveedorById(Long)
	 */
	public Proveedor getProveedorById(Long idProveedor) {
		if(idProveedor==null)
			return null;
		
		return ProveedorDAO.get(idProveedor);
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ProveedorManager#updateProveedor(es.minhap.misim.bus.model.Proveedor)
	 */
	public boolean updateProveedor(Proveedor proveedor) {
		boolean update = false;
		if(proveedor!=null && proveedor.getIdProveedor()>0){
			ProveedorDAO.update(proveedor);
			update = true;
		}
		return update;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ProveedorManager#eliminarProveedor(Long)
	 */
	public void eliminarProveedor(Long id) {
		logger.info("eliminarProveedor - start");
		ProveedorDAO.delete(id);
		logger.info("eliminarProveedor - end");
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ProveedorManager#getProveedor(es.minhap.misim.bus.query.ProveedorQuery)
	 */	
	public List<Proveedor> getProveedor(ProveedorQuery query) {
		return ProveedorDAO.search(query).getResults();
	}
	
}
