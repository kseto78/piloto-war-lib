package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.ViewLotesEnviosDetHistManager;
import es.minhap.sim.dao.ViewLotesEnviosDetHistDAO;
import es.minhap.sim.model.ViewLotesEnviosDetHist;
import es.minhap.sim.query.ViewLotesEnviosDetHistQuery;

/**
 * 
 * @author everis
 *
 */
@Service("ViewLotesEnviosDetHistManagerImpl")
public class ViewLotesEnviosDetHistManagerImpl implements ViewLotesEnviosDetHistManager {

	@Resource
	private ViewLotesEnviosDetHistDAO viewLotesDAO;

	
	@Override
	public ViewLotesEnviosDetHist getViewLoteDetallado(ViewLotesEnviosDetHistQuery query) {
		return viewLotesDAO.searchUnique(query);
	}


}
