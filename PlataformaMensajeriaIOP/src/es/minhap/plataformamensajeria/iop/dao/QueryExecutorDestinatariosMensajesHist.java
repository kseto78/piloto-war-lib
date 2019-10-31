package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.sim.model.TblDestinatariosMensHist;

/**
 * 
 * @author everis
 * 
 */
public interface QueryExecutorDestinatariosMensajesHist {

	/**
	 * Obtiene lista de TblDestinatariosMensajesHist a partir de la lista de mensajes en TblDestinatariosMensajes
	 * @param subList
	 * @param firstResult 
	 * @param max 
	 * @return 
	 */
	List<TblDestinatariosMensHist> convertDestinatarioMensTODestinatarioMensHist(List<Long> subList, Integer max, Integer firstResult);

	/**
	 * Obtiene lista de TblDestinatariosMensHist a partir de la lista de mensajes
	 * @param listaMensajes
	 * @return 
	 */
	List<Long> getIdDestinatariosMensajesCons(List<Long> listaMensajes);

	
	/**
	 * Obtiene el total de la lista de TblDestinatariosMensajesHist a partir de la lista de mensajes en TblDestinatariosMensajes
	 * @param subList
	 * @return 
	 */
	Integer countConvertDestinatarioMensTODestinatarioMensHist(List<Long> subList);

	/**
	 * Cuenta los destinatarios a partir del identificador de mensaje
	 * 
	 * @param mensajeId
	 * @return
	 */
	Integer countDestinatariosByMensaje(Long idMensaje);


	/**
	 * Recupera los destinatarios paginados
	 * 
	 * @param mensajeId
	 * @param size
	 * @param start
	 * @return
	 */
	List<TblDestinatariosMensHist> getDestinatarioMensHist(Long idMensaje, int size, int start);

}
