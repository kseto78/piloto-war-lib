package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.sim.model.ViewUsuariosAplicaciones;

/**
 * 
 * @author everis
 * 
 */
public interface QueryExecutorViewUsuariosAplicaciones {

	/**
	 * Obtiene el nombre de la aplicacion a partir de un mensaje
	 *  
	 * @param usuarioId
	 * @param aplicacionId
	 * @return
	 */
	public List<ViewUsuariosAplicaciones>  findViewUsuarioAplicacion(Long usuarioId, Long aplicacionId);

}
