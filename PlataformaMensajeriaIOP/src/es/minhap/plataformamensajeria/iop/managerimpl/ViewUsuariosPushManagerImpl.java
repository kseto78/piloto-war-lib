package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.ViewUsuariosPushManager;
import es.minhap.sim.dao.ViewUsuariosPushDAO;
import es.minhap.sim.query.ViewUsuariosPushQuery;

/**
 * 
 * @author everis
 *
 */
@Service("ViewUsuariosPushManager")
public class ViewUsuariosPushManagerImpl implements ViewUsuariosPushManager {

	@Resource
	private ViewUsuariosPushDAO viewUsuariosDAO;

	/**
	 * @see es.minhap.ViewUsuariosPushManagerImpl.countViewUsuariosPush
	 */
	@Override
	public Integer countViewUsuariosPush(ViewUsuariosPushQuery query) {

		return getViewUsuariosDAO().count(query);
	}

	/**
	 * @return the viewUsuariosDAO
	 */
	public ViewUsuariosPushDAO getViewUsuariosDAO() {
		return viewUsuariosDAO;
	}

	/**
	 * @param viewUsuariosDAO the viewUsuariosDAO to set
	 */
	public void setViewUsuariosDAO(ViewUsuariosPushDAO viewUsuariosDAO) {
		this.viewUsuariosDAO = viewUsuariosDAO;
	}

}
