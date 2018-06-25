package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.TblTipoPlanificacionesManager;
import es.minhap.sim.dao.TblTipoPlanificacionesDAO;
import es.minhap.sim.model.TblTipoPlanificaciones;

/**
 * 
 * @author everis
 *
 */
@Service("tblTipoPlanificacionesManagerImpl")
public class TblTipoPlanificacionesManagerImpl implements TblTipoPlanificacionesManager {

	@Resource 
	private TblTipoPlanificacionesDAO tipoPlanificacionesDAO;

	@Override
	public TblTipoPlanificaciones getTipoPlanificacionById(Long tipoPlanificacionId) {
		return tipoPlanificacionesDAO.get(tipoPlanificacionId);
	}


}
