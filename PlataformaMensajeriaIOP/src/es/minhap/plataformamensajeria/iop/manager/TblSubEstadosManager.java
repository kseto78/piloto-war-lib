package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.TblSubestados;
import es.minhap.sim.query.TblSubestadosQuery;

/**
 * Manager encargado de las operaciones de la tabla TBL_SUBESTADOS
 * 
 * 
 * @author everis
 *
 */
public interface TblSubEstadosManager {

	/**
	 * Recupera el sub estado  a partir del objeto subEstadoQuery
	 * 
	 * @param TblSubestadosQuery
	 * @return
	 */
	TblSubestados getSubEstadoByName(TblSubestadosQuery subEstadoQuery);
	
	/**
	 * Recupera el sub estado a partir del id de sub estado
	 * 
	 * @param idSubEstado
	 * @return
	 */
	TblSubestados getSubEstadoById(Long idSubEstado);

}
