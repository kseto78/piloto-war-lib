package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.ViewHistoricoHistManager;
import es.minhap.sim.dao.ViewHistoricoHistDAO;
import es.minhap.sim.dao.ViewHistoricoHistMultidestDAO;
import es.minhap.sim.model.ViewHistoricoHist;
import es.minhap.sim.model.ViewHistoricoHistMultidest;
import es.minhap.sim.query.ViewHistoricoHistMultidestQuery;
import es.minhap.sim.query.ViewHistoricoHistQuery;

/**
 * 
 * @author ereris
 *
 */
@Service("ViewHistoricoHistManagerImpl")
public class ViewHistoricoHistManagerImpl implements ViewHistoricoHistManager {

	@Resource
	private ViewHistoricoHistMultidestDAO viewHistoricoHistMultidestDAO;

	@Resource
	private ViewHistoricoHistDAO viewHistoricoHistDAO;
	
	/**
	 * @see es.minhap.ViewHistoricoManagerImpl.getHistoricoMultidest
	 */
	@Override
	public List<ViewHistoricoHistMultidest> getHistoricoMultidest(ViewHistoricoHistMultidestQuery query) {
		return viewHistoricoHistMultidestDAO.search(query).getResults();
	}


	@Override
	public List<ViewHistoricoHist> getHistorico(ViewHistoricoHistQuery query) {
		return viewHistoricoHistDAO.search(query).getResults();
	
	}
	
}
