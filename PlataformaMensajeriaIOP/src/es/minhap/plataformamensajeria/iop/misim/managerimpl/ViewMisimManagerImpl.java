package es.minhap.plataformamensajeria.iop.misim.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.misim.bus.dao.ViewMisimDAO;
import es.minhap.misim.bus.model.ViewMisim;
import es.minhap.misim.bus.query.ViewMisimQuery;
import es.minhap.plataformamensajeria.iop.misim.manager.ViewMisimManager;

/**
 * 
 * @author everis
 *
 */
@Service("ViewMisimManagerImpl")
public class ViewMisimManagerImpl implements ViewMisimManager {

	@Resource 
	private ViewMisimDAO viewMisimDAO;

	
	@Override
	@Transactional
	public ViewMisim getViewMisim(ViewMisimQuery query) {
		
		return  getViewMisimDAO().searchUnique(query);

	}

	@Override
	@Transactional
	public List<ViewMisim> getIntercambiosMisimByQuery(ViewMisimQuery query, int start, int size) {
		
		query.setPrimerResultado(start);
		query.setMaxResultados(size);
		query.addOrder("idPeticion", OrderType.DESC);
		
		List<ViewMisim> intercambios = getViewMisimDAO().search(query).getResults();
		
		return intercambios;
	}
	
	/**
	 * @return the viewMisimDAO
	 */
	public ViewMisimDAO getViewMisimDAO() {
		return viewMisimDAO;
	}

	/**
	 * @param viewMisimDAO the viewMisimDAO to set
	 */
	public void setViewMisimDAO(ViewMisimDAO viewMisimDAO) {
		this.viewMisimDAO = viewMisimDAO;
	}

	
	

}
