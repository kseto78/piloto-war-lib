package es.minhap.plataformamensajeria.iop.misim.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.misim.bus.dao.ProductoDAO;
import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.query.ProductoQuery;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.misim.manager.ProductosManager;
import es.minhap.sim.model.TblLog;

/**
 * 
 * @author everis
 *
 */
@Service("ProductosManagerImpl")
public class ProductosManagerImpl implements ProductosManager {

	@Resource 
	private ProductoDAO productoDAO;
	
	@Resource 
	private TblLogManager tblLogManager;
	

	/**
	 * @see es.minhap.TblLotesEnviosManager.insertarLote
	 */
	@Override
	@Transactional
	public List<Producto> getProductos(ProductoQuery query) {
		List<Producto> productos = getProductoDAO().search(query).getResults();
		
		return productos;
	}

	@Transactional
	@Override
	public Producto getProducto(Long idProducto){
		return productoDAO.get(idProducto);
	}

	@Override
	public List<Producto> getProductosOrdenados() {
		ProductoQuery query = new ProductoQuery();
		query.addOrder("nombre", OrderType.ASC);
		
		return productoDAO.search(query).getResults();
	}
	
	
	@Override
	@Transactional
	public Long insert(Producto producto, String source, String accion, Long accionId) {
		Long id = productoDAO.insert(producto);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(producto.getNombre());
		log.setSourceid(id);
		log.setSourcename(source);
		tblLogManager.insertLog(log);

		return id;
	}
	
	@Override
	@Transactional
	public void update(Producto producto, String source, String accion, Long accionId) {
		productoDAO.update(producto);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(producto.getNombre());
		log.setSourceid(producto.getIdProducto());
		log.setSourcename(source);
		tblLogManager.insertLog(log);
		
	}
	
	/**
	 * @return the productoDAO
	 */
	public ProductoDAO getProductoDAO() {
		return productoDAO;
	}

	/**
	 * @param productoDAO the productoDAO to set
	 */
	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}
}
