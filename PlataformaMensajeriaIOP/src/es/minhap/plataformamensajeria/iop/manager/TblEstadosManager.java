package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.common.entity.SearchResult;
import es.minhap.sim.model.TblEstados;

public interface TblEstadosManager {

	/**
	 * recupera el estado segun datos pasados
	 * 
	 * @param TblEstadosQuery
	 * @return TblEstados
	 */
	TblEstados getEstadoByName(String nombre);
	
	
	/**
	 * recupera el estado segun datos pasados
	 * 
	 * @param TblEstadosQuery
	 * @return TblEstados
	 */
	TblEstados getEstadoById(Long idEstado);


	SearchResult<TblEstados> getEstados();
	
	/**
	 * recupera el id del estado pasado en properties
	 * @param ps
	 * @return Long
	 */
//	public Long getEstado(PropertiesServices ps);
}
