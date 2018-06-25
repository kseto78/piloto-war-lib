package es.minhap.plataformamensajeria.iop.managerimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblProcesoHistManager;
import es.minhap.sim.dao.TblProcesoHistDAO;
import es.minhap.sim.model.TblProcesoHist;

/**
 * 
 * @author everis
 *
 */
@Service("TblProcesoHistManagerImpl")
public class TblProcesoHistManagerImpl implements TblProcesoHistManager {

	@Autowired
	private TblProcesoHistDAO procesoHistDAO;



	/**
	 * @see es.minhap.TblLotesEnviosManager.inserta
	 */
	@Override
	@Transactional
	public Long insertar(TblProcesoHist procesoHist) {
		return procesoHistDAO.insert(procesoHist);
	}

}
