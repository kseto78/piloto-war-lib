package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.sim.model.TblHistoricosHist;

/**
 * 
 * @author everis
 * 
 */
public interface QueryExecutorHistoricosHist {

	/**
	 * Obtiene lista de TblHistoricosHist a partir de la lista de mensajes en TblHistoricos
	 * @param subList
	 * @param firstResult 
	 * @param max 
	 * @return 
	 */
	public List<TblHistoricosHist> convertHistoricoTOHistoricoHist(List<Long> subList, Integer max, Integer firstResult);

	/**
	 * Obtiene lista de TblHistoricost a partir de la lista de mensajes
	 * @param listaMensajesHistoricosCons
	 * @return 
	 */
	public List<Long> getIdHistoricosCons(List<Long> listaMensajesHistoricosCons);

	/**
	 * Obtiene total TblHistoricosHist a partir de la lista de mensajes en TblHistoricos
	 * @param subList
	 * @param i 
	 * @param max 
	 * @return 
	 */
	public Integer countConvertHistoricoTOHistoricoHist(List<Long> subList);

}
