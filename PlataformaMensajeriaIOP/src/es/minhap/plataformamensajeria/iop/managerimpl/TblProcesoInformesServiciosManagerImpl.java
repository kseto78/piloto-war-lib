package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblProcesoInformesServiciosManager;
import es.minhap.sim.dao.TblProcesoInformesServiciosDAO;
import es.minhap.sim.model.TblProcesoInformesServicios;

/**
 * 
 * @author everis
 *
 */
@Service("TblProcesoInformesServiciosManagerImpl")
public class TblProcesoInformesServiciosManagerImpl implements TblProcesoInformesServiciosManager {

	@Resource
	private TblProcesoInformesServiciosDAO tblProcesoInformesServiciosDAO;

	/**
	 * @see es.minhap.TblProcesoInformesServiciosManagerImpl.insertar
	 */
	@Override
	@Transactional
	public Long insertar(TblProcesoInformesServicios pis) {
		return tblProcesoInformesServiciosDAO.insert(pis);
	}



}
