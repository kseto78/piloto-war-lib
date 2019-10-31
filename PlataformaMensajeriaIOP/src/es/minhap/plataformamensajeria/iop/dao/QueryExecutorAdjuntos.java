package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.sim.model.TblAdjuntos;

/**
 * 
 * @author everis
 * 
 */
public interface QueryExecutorAdjuntos {

	/**
	 * Obtiene lista de TblAdjuntos de un mensaje
	 * @param mensajeId
	 * @return 
	 */
	List<TblAdjuntos> getAdjuntosByMensaje(Long mensajeId);

}
