package es.minhap.plataformamensajeria.iop.misim.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.misim.bus.dao.TransformacionDAO;
import es.minhap.misim.bus.model.Transformacion;
import es.minhap.misim.bus.query.TransformacionQuery;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.misim.manager.TransformacionesManager;
import es.minhap.sim.model.TblLog;

/**
 * 
 * @author everis
 *
 */
@Service("TransformacionesManagerImpl")
public class TransformacionesManagerImpl implements TransformacionesManager {

	@Resource 
	private TransformacionDAO transformacionDAO;
	
	@Resource 
	private TblLogManager tblLogManager;
	

	/**
	 * @see es.minhap.TblLotesEnviosManager.insertarLote
	 */
	@Override
	@Transactional
	public List<Transformacion> getTransformaciones(TransformacionQuery query) {
		return getTransformacionDAO().search(query).getResults();
	}
	
	@Transactional
	@Override
	public Transformacion getTransformacion(Long idTransformacion){
		return transformacionDAO.get(idTransformacion);
	}

	@Override
	public List<Transformacion> getTransformacionesOrdenados() {
		TransformacionQuery query = new TransformacionQuery();
		query.addOrder("nombre", OrderType.ASC);
		
		return transformacionDAO.search(query).getResults();
	}

	@Override
	@Transactional
	public Long insert(Transformacion transformacion, String source, String accion, Long accionId) {
		Long id = transformacionDAO.insert(transformacion);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(transformacion.getNombre());
		log.setSourceid(id);
		log.setSourcename(source);
		tblLogManager.insertLog(log);

		return id;
	}

	@Override
	@Transactional
	public void update(Transformacion transformacion, String source, String accion, Long accionId) {
		transformacionDAO.update(transformacion);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(transformacion.getNombre());
		log.setSourceid(transformacion.getIdTransformacion());
		log.setSourcename(source);
		tblLogManager.insertLog(log);
		
	}
	
	public TransformacionDAO getTransformacionDAO() {
		return transformacionDAO;
	}


	public void setTransformacionDAO(TransformacionDAO transformacionDAO) {
		this.transformacionDAO = transformacionDAO;
	}

}
