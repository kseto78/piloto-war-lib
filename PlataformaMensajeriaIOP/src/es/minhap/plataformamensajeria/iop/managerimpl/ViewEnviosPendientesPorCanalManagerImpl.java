package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.ViewEnviosPendientesPorCanalManager;
import es.minhap.sim.dao.ViewEnviosPendientesPorCanalDAO;
import es.minhap.sim.model.ViewEnviosPendientesPorCanal;
import es.minhap.sim.query.ViewEnviosPendientesPorCanalQuery;

/**
 * 
 * @author ereris
 *
 */
@Service("viewEnviosPendientesPorCanalManagerImpl")
public class ViewEnviosPendientesPorCanalManagerImpl implements ViewEnviosPendientesPorCanalManager {

	@Resource
	private ViewEnviosPendientesPorCanalDAO enviosPendientesPorCanalDAO;
	
	@Override
	public List<ViewEnviosPendientesPorCanal> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
