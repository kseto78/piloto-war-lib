package es.minhap.misim.bus.model.servicios;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import es.minhap.misim.bus.dao.ProductoDAO;
import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.query.ProductoQuery;

@Service("productoManager")
public class ProductoManagerImpl implements ProductoManager{

	private static final Logger logger = Logger.getLogger(ProductoManagerImpl.class);
		
	@Resource
	ProductoDAO ProductoDAO;
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ProductoManager#guardarProducto(micc.bus.persistence.entities.Producto)
	 */
	public Long guardarProducto(Producto producto){
		logger.info("guardarProducto - start");
		return ProductoDAO.insert(producto);
	}	
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ProductoManager#insertProducto(micc.bus.persistence.entities.Producto)
	 */
	public Long insertProducto(Producto producto) {
		Long id = null;
		if(producto!=null){
			id = ProductoDAO.insert(producto);
		}
		return id;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ProductoManager#getProductoById(Long)
	 */
	public Producto getProductoById(Long idProducto) {
		if(idProducto==null)
			return null;
		
		return ProductoDAO.get(idProducto);
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ProductoManager#updateProducto(micc.bus.persistence.entities.Producto)
	 */
	public boolean updateProducto(Producto producto) {
		boolean update = false;
		if(producto!=null && producto.getIdProducto()!=null && !("").equals(producto.getIdProducto())){
			ProductoDAO.update(producto);
			update = true;
		}
		return update;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ProductoManager#eliminarProducto(Long)
	 */
	public void eliminarProducto(Long id) {
		logger.info("eliminarProducto - start");
		ProductoDAO.delete(id);
		logger.info("eliminarProducto - end");
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ProductoManager#getProducto(es.minhap.micc.bus.model.query.ProductoQuery)
	 */	
	public List<Producto> getProducto(ProductoQuery query) {
		return ProductoDAO.search(query).getResults();
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ProductoManager#getProductoByCodigoServicio(java.lang.String)
	 */
	public Producto getProductoByCodigo(String codigo){
		ProductoQuery query = new ProductoQuery();
		query.setCodigo(codigo);
		return ProductoDAO.searchUnique(query);
	}
	
}
