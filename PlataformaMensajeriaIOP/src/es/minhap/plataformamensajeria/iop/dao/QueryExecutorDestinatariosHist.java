package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.sim.model.TblDestinatariosHist;

/**
 * 
 * @author everis
 * 
 */
public interface QueryExecutorDestinatariosHist {

	/**
	 * Obtiene lista de TblDestinatariosHist a partir de la lista de mensajes en TblDestinatarios
	 * @param subList
	 * @param max
	 * @param firstResult
	 * @return 
	 */
	List<TblDestinatariosHist> convertDestinatarioTODestinatarioHist(List<Long> subList, Integer max, Integer firstResult);

	/**
	 * Obtiene lista de TblDestinatariosHist a partir de la lista de mensajes
	 * @param listaMensajes
	 * @return 
	 */
	List<Long> getIdDestinatariosCons(List<Long> listaMensajes);

	/**
	 * Obtiene el total de TblDestinatariosHist a partir de la lista de mensajes en TblDestinatarios
	 * @param subList
	 * @return 
	 */
	Integer countConvertDestinatarioTODestinatarioHist(List<Long> subList);

}
