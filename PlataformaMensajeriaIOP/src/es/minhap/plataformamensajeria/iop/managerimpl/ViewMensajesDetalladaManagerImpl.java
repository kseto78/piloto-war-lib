package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.ViewMensajesDetalladaManager;
import es.minhap.sim.dao.ViewMensajesDetalladaDAO;
import es.minhap.sim.query.ViewMensajesDetalladaQuery;

/**
 * 
 * @author everis
 *
 */
@Service("ViewMensajesDetalladaManagerImpl")
public class ViewMensajesDetalladaManagerImpl implements ViewMensajesDetalladaManager {

	@Resource
	private ViewMensajesDetalladaDAO viewMensajesDetalladaDAO;

	/**
	 * @see es.minhap.ViewMensajesDetalladaManagerImpl.countViewMensajeDetallada
	 */
	@Override
	public Integer countViewMensajeDetallada(ViewMensajesDetalladaQuery query) {

		return getViewMensajesDetalladaDAO().count(query);
	}

	/**
	 * @return the viewMensajesDetalladaDAO
	 */
	public ViewMensajesDetalladaDAO getViewMensajesDetalladaDAO() {
		return viewMensajesDetalladaDAO;
	}

	/**
	 * @param viewMensajesDetalladaDAO the viewMensajesDetalladaDAO to set
	 */
	public void setViewMensajesDetalladaDAO(ViewMensajesDetalladaDAO viewMensajesDetalladaDAO) {
		this.viewMensajesDetalladaDAO = viewMensajesDetalladaDAO;
	}

}
