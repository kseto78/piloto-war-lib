package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.ViewMensajeAdjuntosDetalladaManager;
import es.minhap.sim.dao.ViewMensajeAdjuntosDetalladaDAO;
import es.minhap.sim.query.ViewMensajeAdjuntosDetalladaQuery;

/**
 * 
 * @author everis
 *
 */
@Service("ViewMensajeAdjuntosDetalladaManagerImpl")
public class ViewMensajeAdjuntosDetalladaManagerImpl implements ViewMensajeAdjuntosDetalladaManager {

	@Resource
	private ViewMensajeAdjuntosDetalladaDAO viewMensajeAdjuntosDetalladaDAO;

	/**
	 * @see es.minhap.ViewMensajesDetalladaManagerImpl.countViewMensajeAdjuntosDetallada
	 */
	@Override
	public Integer countViewMensajeAdjuntosDetallada(ViewMensajeAdjuntosDetalladaQuery query) {

		return getViewMensajeAdjuntosDetalladaDAO().count(query);
	}

	/**
	 * @return the viewMensajeAdjuntosDetalladaDAO
	 */
	public ViewMensajeAdjuntosDetalladaDAO getViewMensajeAdjuntosDetalladaDAO() {
		return viewMensajeAdjuntosDetalladaDAO;
	}

	/**
	 * @param viewMensajeAdjuntosDetalladaDAO the viewMensajeAdjuntosDetalladaDAO to set
	 */
	public void setViewMensajeAdjuntosDetalladaDAO(ViewMensajeAdjuntosDetalladaDAO viewMensajeAdjuntosDetalladaDAO) {
		this.viewMensajeAdjuntosDetalladaDAO = viewMensajeAdjuntosDetalladaDAO;
	}

}
