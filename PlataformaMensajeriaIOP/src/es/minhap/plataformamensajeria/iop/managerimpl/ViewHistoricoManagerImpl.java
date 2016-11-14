package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.ViewHistoricoManager;
import es.minhap.sim.dao.ViewHistoricoDAO;
import es.minhap.sim.dao.ViewHistoricoMultidestDAO;
import es.minhap.sim.model.ViewHistorico;
import es.minhap.sim.model.ViewHistoricoMultidest;
import es.minhap.sim.query.ViewHistoricoMultidestQuery;
import es.minhap.sim.query.ViewHistoricoQuery;

/**
 * 
 * @author ereris
 *
 */
@Service("ViewHistoricoManagerImpl")
public class ViewHistoricoManagerImpl implements ViewHistoricoManager {

	@Resource
	private ViewHistoricoDAO viewHistoricoDAO;
	
	@Resource
	private ViewHistoricoMultidestDAO viewHistoricoMultidestDAO;

	
	/**
	 * @see es.minhap.ViewHistoricoManagerImpl.getHistorico
	 */
	@Override
	public List<ViewHistorico> getHistorico(ViewHistoricoQuery query) {
		return viewHistoricoDAO.search(query).getResults();
	}
	
	/**
	 * @see es.minhap.ViewHistoricoManagerImpl.getHistoricoMultidest
	 */
	@Override
	public List<ViewHistoricoMultidest> getHistoricoMultidest(ViewHistoricoMultidestQuery query) {
		return viewHistoricoMultidestDAO.search(query).getResults();
	}

	/**
	 * @return the viewHistoricoDAO
	 */
	public ViewHistoricoDAO getViewHistoricoDAO() {
		return viewHistoricoDAO;
	}

	/**
	 * @param viewHistoricoDAO the viewHistoricoDAO to set
	 */
	public void setViewHistoricoDAO(ViewHistoricoDAO viewHistoricoDAO) {
		this.viewHistoricoDAO = viewHistoricoDAO;
	}

	/**
	 * @return the viewHistoricoMultidestDAO
	 */
	public ViewHistoricoMultidestDAO getViewHistoricoMultidestDAO() {
		return viewHistoricoMultidestDAO;
	}

	/**
	 * @param viewHistoricoMultidestDAO the viewHistoricoMultidestDAO to set
	 */
	public void setViewHistoricoMultidestDAO(ViewHistoricoMultidestDAO viewHistoricoMultidestDAO) {
		this.viewHistoricoMultidestDAO = viewHistoricoMultidestDAO;
	}

}
