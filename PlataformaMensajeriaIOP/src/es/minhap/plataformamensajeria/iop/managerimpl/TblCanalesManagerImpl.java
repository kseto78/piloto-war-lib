package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.common.entity.OrderType;
import es.minhap.plataformamensajeria.iop.manager.TblCanalesManager;
import es.minhap.sim.dao.TblCanalesDAO;
import es.minhap.sim.model.TblCanales;
import es.minhap.sim.query.TblCanalesQuery;

/**
 * 
 * @author everis
 *
 */
@Service("tblCanalesManagerImpl")
public class TblCanalesManagerImpl implements TblCanalesManager {

	@Resource 
	private TblCanalesDAO tblCanalesDAO;


	/**
	 * @see es.minhap.TblCanalesManagerImpl.getCanalById
	 */
	@Override
	public TblCanales getCanalById(Long canalId) {
		return tblCanalesDAO.get(canalId);
	}

	/**
	 * @see es.minhap.TblCanalesManagerImpl.getCanalesOrdenados
	 */
	@Override
	public List<TblCanales> getCanalesOrdenados() {
		TblCanalesQuery query = new TblCanalesQuery();
		query.setActivo(true);
		query.addOrder("nombre", OrderType.ASC);
		
		return tblCanalesDAO.search(query).getResults();
	}
	
	
}
