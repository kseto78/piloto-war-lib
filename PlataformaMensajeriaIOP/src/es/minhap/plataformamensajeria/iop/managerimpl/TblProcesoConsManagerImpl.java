package es.minhap.plataformamensajeria.iop.managerimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblProcesoConsManager;
import es.minhap.sim.dao.TblProcesoConsDAO;
import es.minhap.sim.model.TblProcesoCons;

/**
 * 
 * @author everis
 *
 */
@Service("TblProcesoConsManagerImpl")
public class TblProcesoConsManagerImpl implements TblProcesoConsManager {

	@Autowired
	private TblProcesoConsDAO procesoConsDAO;



	/**
	 * @see es.minhap.TblLotesEnviosManager.inserta
	 */
	@Override
	@Transactional
	public Long insertar(TblProcesoCons procesoCons) {
		return procesoConsDAO.insert(procesoCons);
	}

}
