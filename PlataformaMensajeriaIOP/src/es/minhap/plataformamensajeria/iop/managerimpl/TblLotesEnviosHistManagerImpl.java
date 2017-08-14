package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosHistManager;
import es.minhap.sim.dao.TblLotesEnviosHistDAO;
import es.minhap.sim.model.TblLotesEnviosHist;

/**
 * 
 * @author everis
 * 
 */
@Service("TblLotesEnviosHistManagerImpl")
public class TblLotesEnviosHistManagerImpl implements TblLotesEnviosHistManager {

	@Resource(name="TblLotesEnviosHistDAOImpl")
	private TblLotesEnviosHistDAO lotesDAO;

	@Override
	public TblLotesEnviosHist getLoteHistoricoById(Long loteHistId) {
		return lotesDAO.get(loteHistId);
	}

	

}
