package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblPlataformas;


public interface TblPlataformasManager {

	/**
	 * Comprueba si existe la plataforma
	 * 
	 * @param plataformaID
	 * @return Boolean
	 */
	public Boolean existPlataforma(Long plataformaID );
	
	/**
	 * Obtiene todas las plataformas activas
	 * 
	 * @return 
	 */
	public List<TblPlataformas> getPlataformasActivas();

}
