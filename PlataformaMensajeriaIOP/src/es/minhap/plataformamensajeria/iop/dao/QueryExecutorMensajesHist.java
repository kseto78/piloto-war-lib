package es.minhap.plataformamensajeria.iop.dao;

import java.util.Date;
import java.util.List;

import es.minhap.sim.model.TblLotesEnviosHist;
import es.minhap.sim.model.TblMensajesHist;

/**
 * 
 * @author everis
 * 
 */
public interface QueryExecutorMensajesHist {

	/***
	 * Numero de mensajes enviados/anulados por lote y fecha 
	 * 
	 * @param loteId
	 * @param fecha
	 * @return
	 */
	public Integer countMensajesHistorificacion(Long loteId, Date fecha);

	/***
	 * Recupera una lista con los Id mensajes 
	 * 
	 * @param loteEnvioID
	 * @param maxResult
	 * @param firstResult
	 * @return
	 */
	List<Long> getIdMensajesByLote(Long loteEnvioID, Integer maxResult, Integer firstResult);

	/***
	 * Recupera una lista con los mensajes transformados en mensajesHist
	 * 
	 * @param subList
	 * @param loteHistorico
	 * @return
	 */
	public List<TblMensajesHist> convertMensajeTOMensajeHist(List<Long> subList, TblLotesEnviosHist loteHistorico);

	/***
	 * Numero de mensajes enviados/anulados por lote 
	 * 
	 * @param loteId
	 * @return
	 */
	public Integer countMensajesByLote(Long loteId);

	
}
