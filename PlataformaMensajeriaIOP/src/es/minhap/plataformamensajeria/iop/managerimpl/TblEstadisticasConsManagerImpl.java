package es.minhap.plataformamensajeria.iop.managerimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblEstadisticasConsManager;
import es.minhap.sim.dao.TblEstadistitcasConsDAO;
import es.minhap.sim.model.TblEstadistitcasCons;

/**
 * 
 * @author everis
 *
 */
@Service("TblEstadisticasConsManagerImpl")
public class TblEstadisticasConsManagerImpl implements TblEstadisticasConsManager {

	@Autowired
	private TblEstadistitcasConsDAO estadisticasConsDAO;



	/**
	 * @see es.minhap.TblLotesEnviosManager.inserta
	 */
	@Override
	@Transactional
	public Long insertar(TblEstadistitcasCons estadisticasCons) {
		return estadisticasConsDAO.insert(estadisticasCons);
	}

}
