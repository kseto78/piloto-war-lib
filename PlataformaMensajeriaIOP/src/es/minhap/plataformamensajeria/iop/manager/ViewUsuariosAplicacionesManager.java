package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.ViewUsuariosAplicaciones;

public interface ViewUsuariosAplicacionesManager {

	/**
	 * obtiene la lista de la vista by query
	 * 
	 * @param ViewUsuariosPushQuery
	 * @return List
	 */
	public List<ViewUsuariosAplicaciones> getViewUsuariosAplicacionesBy(Long usuarioId, Long aplicacionId);

	
	
}
