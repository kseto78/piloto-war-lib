package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.ViewLotesEnviosDetalladaManager;
import es.minhap.sim.dao.ViewLotesEnviosDetalladaDAO;
import es.minhap.sim.model.ViewLotesEnviosDetallada;
import es.minhap.sim.query.ViewLotesEnviosDetalladaQuery;

/**
 * 
 * @author everis
 *
 */
@Service("ViewLotesEnviosDetalladaManagerImpl")
public class ViewLotesEnviosDetalladaManagerImpl implements ViewLotesEnviosDetalladaManager {

	@Resource
	private ViewLotesEnviosDetalladaDAO viewLotesDAO;

	/**
	 * @see es.minhap.ViewLotesEnviosDetalladaManager.countViewLoteDetallado
	 */
	@Override
	public Integer countViewLoteDetallado(ViewLotesEnviosDetalladaQuery query) {
		return getViewLotesDAO().count(query);
	}

	@Override
	public ViewLotesEnviosDetallada getViewLoteDetallado(ViewLotesEnviosDetalladaQuery query) {
		return getViewLotesDAO().searchUnique(query);
	}

	/**
	 * @return the viewLotesDAO
	 */
	public ViewLotesEnviosDetalladaDAO getViewLotesDAO() {
		return viewLotesDAO;
	}

	/**
	 * @param viewLotesDAO the viewLotesDAO to set
	 */
	public void setViewLotesDAO(ViewLotesEnviosDetalladaDAO viewLotesDAO) {
		this.viewLotesDAO = viewLotesDAO;
	}

}
