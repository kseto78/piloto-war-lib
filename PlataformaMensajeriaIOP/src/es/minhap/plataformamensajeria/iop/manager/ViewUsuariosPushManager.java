package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.query.ViewUsuariosPushQuery;

public interface ViewUsuariosPushManager {

	/**
	 * Se comprueba si hay algún usuario push con ese nombre de usuario
	 * 
	 * @param ViewUsuariosPushQuery
	 * @return Integer
	 */
	public Integer countViewUsuariosPush(ViewUsuariosPushQuery query);

}
