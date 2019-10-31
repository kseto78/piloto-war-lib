package es.minhap.plataformamensajeria.iop.misim.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.common.entity.TextComparator;
import es.minhap.misim.bus.dao.ProveedorDAO;
import es.minhap.misim.bus.model.Proveedor;
import es.minhap.misim.bus.query.ProveedorQuery;
import es.minhap.plataformamensajeria.iop.beans.FiltroProveedorMisimBean;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.misim.manager.ProveedoresMisimManager;
import es.minhap.sim.model.TblLog;

/**
 * 
 * @author everis
 *
 */
@Service("ProveedoresMisimManagerImpl")
public class ProveedoresMisimManagerImpl implements ProveedoresMisimManager {

	protected static final String R_CONST_1 = "%";

	@Resource 
	private ProveedorDAO proveedorDAO;
	
	@Resource 
	private TblLogManager tblLogManager;
	
	
	@Override
	@Transactional
	public List<Proveedor> getProveedoresMisimPaginado(int start, int size, String order, String columnSort,
			FiltroProveedorMisimBean criterio, boolean count) {
		
		ProveedorQuery query = new ProveedorQuery();
		
		OrderType ord = null;
		// Orden ascendente o descendente
		if (order == null || "1".equals(order)) {
			ord = OrderType.ASC;
		} else {
			ord = OrderType.DESC;
		}


		if (null != criterio.getNombre()) {
			query.setNombreComparator(TextComparator.ILIKE);
			query.setNombre(R_CONST_1+criterio.getNombre()+R_CONST_1);
		}
		
		query.addOrder(columnSort, ord);
//		if (!count){
//			query.setFirstResult(start);
//			query.setMaxResults(size);
//		}
		
		return proveedorDAO.search(query).getResults();
	}
	
	@Transactional
	@Override
	public Proveedor getProveedor(Long idProveedor){
		return proveedorDAO.get(idProveedor);
	}
	
	@Override
	@Transactional
	public Long insert(Proveedor proveedor, String source, String accion, Long accionId) {
		Long id = proveedorDAO.insert(proveedor);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(proveedor.getNombre());
		log.setSourceid(id);
		log.setSourcename(source);
		tblLogManager.insertLog(log);

		return id;
	}
	
	@Override
	@Transactional
	public void update(Proveedor proveedor, String source, String accion, Long accionId) {
		proveedorDAO.update(proveedor);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(proveedor.getNombre());
		log.setSourceid(proveedor.getIdProveedor());
		log.setSourcename(source);
		tblLogManager.insertLog(log);
		
	}
	
	@Override
	@Transactional
	public void delete(Long idProveedor, String source, String accion, Long accionId) {
		Proveedor proveedor  = proveedorDAO.get(idProveedor);
		proveedorDAO.delete(idProveedor);
	
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(proveedor.getNombre());
		log.setSourceid(proveedor.getIdProveedor());
		log.setSourcename(source);
		tblLogManager.insertLog(log);		
	}


	public ProveedorDAO getProveedorDAO() {
		return proveedorDAO;
	}


	public void setProveedorDAO(ProveedorDAO proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
	}
	
}
