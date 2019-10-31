package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.sim.model.TblLotesEnviosHist;
import es.minhap.sim.model.TblMensajesAdjuntosHist;

/**
 * 
 * @author everis
 * 
 */
public interface QueryExecutorMensajesAdjuntosHist {

	/**
	 * Obtiene lista de TblMensajesAdjuntosHist a partir de la lista de mensajes en TblMensajesAdjuntos
	 * @param subList
	 * @param loteHistorico
	 * @param firstResult 
	 * @param max 
	 * @return 
	 */
	List<TblMensajesAdjuntosHist> convertAdjuntosTOAdjuntosHist(List<Long> subList, TblLotesEnviosHist loteHistorico, Integer max, Integer firstResult);

	/**
	 * Obtiene lista de TblMensajesAdjuntosHist a partir de la lista de mensajes
	 * @param listaMensajes
	 * @return 
	 */
	List<Long> getIdMensajesAdjuntosCons(List<Long> listaMensajes);

	/**
	 * Cuenta el total lista de TblMensajesAdjuntosHist a partir de la lista de mensajes en TblMensajesAdjuntos
	 * @param subList
	 * @param loteHistorico
	 * @return 
	 */
	Integer countConvertAdjuntosTOAdjuntosHist(List<Long> subList, TblLotesEnviosHist loteHistorico);

}
