package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.ViewServiciosManager;
import es.minhap.sim.dao.ViewServiciosDAO;
import es.minhap.sim.model.ViewServicios;
import es.minhap.sim.query.ViewServiciosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("ViewServiciosManagerImpl")
public class ViewServiciosManagerImpl implements ViewServiciosManager {

	@Resource
	private ViewServiciosDAO viewServiciosDAO;

	/**
	 * @see es.minhap.ViewMensajesDetalladaManagerImpl.countViewMensajeAdjuntosDetallada
	 */
	@Override
	public ViewServicios getAplicacionId(ViewServiciosQuery query) {
		return getViewServiciosDAO().searchUnique(query);
	}

	/**
	 * @return the viewServiciosDAO
	 */
	public ViewServiciosDAO getViewServiciosDAO() {
		return viewServiciosDAO;
	}

	/**
	 * @param viewServiciosDAO the viewServiciosDAO to set
	 */
	public void setViewServiciosDAO(ViewServiciosDAO viewServiciosDAO) {
		this.viewServiciosDAO = viewServiciosDAO;
	}


}
