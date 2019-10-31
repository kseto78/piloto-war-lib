package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblUsuarios;

public interface ViewUsuariosManager {

	/**
	 * obtiene la lista de la vista by query
	 * 
	 * @param query
	 * @return List
	 */
	List<TblUsuarios> getViewUsuariosPaginado(int start, int size, String order, String column, String nombre,
	Long aplicacionId, Integer rolId, boolean count);

	
	
}
