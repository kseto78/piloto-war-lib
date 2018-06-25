package es.minhap.plataformamensajeria.iop.misim.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.misim.bus.dao.ParametrosProveedorDAO;
import es.minhap.misim.bus.model.ParametrosProveedor;
import es.minhap.misim.bus.query.ParametrosProveedorQuery;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.misim.manager.ParametrosProveedorManager;
import es.minhap.plataformamensajeria.iop.misim.manager.ProveedoresMisimManager;
import es.minhap.sim.model.TblLog;

/**
 * 
 * @author everis
 *
 */
@Service("ParametrosProveedorManagerImpl")
public class ParametrosProveedorManagerImpl implements ParametrosProveedorManager {

	@Resource 
	private ParametrosProveedorDAO parametrosProveedorDAO;
	
	@Resource 
	private TblLogManager tblLogManager;
	
	@Resource 
	private ProveedoresMisimManager proveedoresMisimManager;
	
	@Override
	
	public List<ParametrosProveedor> getParametrosPorProveedorMisim(Long idProveedor) {
		ParametrosProveedorQuery query = new ParametrosProveedorQuery();
		query.setIdProveedor(idProveedor);
		
		return parametrosProveedorDAO.search(query).getResults();
	}

	public ParametrosProveedor getParametroPorIdParametrosProveedor(Long idParametrosProveedor) {
		ParametrosProveedorQuery query = new ParametrosProveedorQuery();
		query.setIdParametrosProveedor(idParametrosProveedor);
		
		return parametrosProveedorDAO.searchUnique(query);
	}
	
	@Override
	@Transactional
	public Long insert(ParametrosProveedor parametroProveedor, String source, String accion, Long accionId, String descripcion) {
		
		Long id = parametrosProveedorDAO.insert(parametroProveedor);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(descripcion + " " + parametroProveedor.getParametro());
		log.setSourceid(id);
		log.setSourcename(source);
		tblLogManager.insertLog(log);
		
		return id;
	}
	
	@Override
	public void delete(Long idParametrosProveedor, String source, String accion, Long accionId, String descripcion) {
		ParametrosProveedor pp = parametrosProveedorDAO.get(idParametrosProveedor);
		parametrosProveedorDAO.delete(idParametrosProveedor);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(descripcion + " " + pp.getIdParametrosProveedor());
		log.setSourceid(pp.getIdProveedor());
		log.setSourcename(source);
		tblLogManager.insertLog(log);		
		
	}

	public ParametrosProveedorDAO getParametrosProveedorDAO() {
		return parametrosProveedorDAO;
	}


	public void setParametrosProveedorDAO(ParametrosProveedorDAO parametrosProveedorDAO) {
		this.parametrosProveedorDAO = parametrosProveedorDAO;
	}
	
}
