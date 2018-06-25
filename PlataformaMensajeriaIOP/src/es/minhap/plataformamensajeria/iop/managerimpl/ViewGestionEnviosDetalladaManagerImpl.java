package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.ViewGestionEnviosDetalladaManager;
import es.minhap.sim.dao.ViewGestionenviosDetalladaDAO;
import es.minhap.sim.model.ViewGestionenviosDetallada;
import es.minhap.sim.query.ViewGestionenviosDetalladaQuery;

/**
 * 
 * @author everis
 *
 */
@Service("ViewGestionEnviosManagerImpl")
public class ViewGestionEnviosDetalladaManagerImpl implements ViewGestionEnviosDetalladaManager {

	@Resource
	private ViewGestionenviosDetalladaDAO viewGestionEnviosDetalladaDAO;

	/**
	 * @see es.minhap.ViewGestionEnviosDetalladaManagerImpl.getEstadosFiltroMensaje
	 */
	@Override
	public List<ViewGestionenviosDetallada> getEstadosFiltroMensaje(ViewGestionenviosDetalladaQuery query) {
		return getViewGestionEnviosDetalladaDAO().search(query).getResults();
	}

	/**
	 * @return the viewGestionEnviosDetalladaDAO
	 */
	public ViewGestionenviosDetalladaDAO getViewGestionEnviosDetalladaDAO() {
		return viewGestionEnviosDetalladaDAO;
	}

	/**
	 * @param viewGestionEnviosDetalladaDAO the viewGestionEnviosDetalladaDAO to set
	 */
	public void setViewGestionEnviosDetalladaDAO(ViewGestionenviosDetalladaDAO viewGestionEnviosDetalladaDAO) {
		this.viewGestionEnviosDetalladaDAO = viewGestionEnviosDetalladaDAO;
	}

}
