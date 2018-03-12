package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean;
import es.minhap.sim.model.ViewUsuariosPush;
import es.minhap.sim.query.ViewUsuariosPushQuery;

public interface ViewUsuariosPushManager {

	/**
	 * Se comprueba si hay algún usuario push con ese nombre de usuario
	 * 
	 * @param ViewUsuariosPushQuery
	 * @return Integer
	 */
	public Integer countViewUsuariosPush(ViewUsuariosPushQuery query);

	public List<ViewUsuariosPush> getUsuariosPushPaginado(int start, int size, String order, String column,
			UsuariosPushBean criterio, boolean b);

}
