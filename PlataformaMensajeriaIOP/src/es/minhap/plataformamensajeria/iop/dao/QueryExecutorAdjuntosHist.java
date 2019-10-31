package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.sim.model.TblAdjuntosHist;

/**
 * 
 * @author everis
 * 
 */
public interface QueryExecutorAdjuntosHist {

	/**
	 * Obtiene lista de TblAdjuntosHist a partir de la lista de mensajes
	 * @param listaMensajes
	 * @return 
	 */
	List<Long> getIdAdjuntosCons(List<Long> listaMensajes);

	/**
	 * Obtiene lista de TblAdjuntosHist de un mensaje
	 * @param mensajeId
	 * @return 
	 */
	List<TblAdjuntosHist> getAdjuntosByMensaje(Long mensajeId);

}
