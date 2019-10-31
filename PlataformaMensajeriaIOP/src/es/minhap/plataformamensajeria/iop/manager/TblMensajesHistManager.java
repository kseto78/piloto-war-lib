package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblMensajesHist;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.query.TblMensajesHistQuery;

public interface TblMensajesHistManager {


	/**
	 * Inserta el mensaje historicos
	 * 
	 * @param m
	 * @return Long
	 */
	Long insert(TblMensajesHist m);

	/**
	 * Elimina el mensaje historicos
	 * 
	 * @param mensajeId
	 */
	void delete(Long mensajeId);


	/**
	 * REcupera el mensaje por ID
	 * 
	 * @param mensajeId
	 */
	TblMensajesHist getMensaje(Long mensajeid);

	/**
	 * Obtiene una Lista de  TblMensajesHist que pertenecen a un lote con límites
	 * 
	 * @param idLote
	 * @param max
	 * @param firstResult
	 * @return
	 */
	List<TblMensajesHist> getMensajesByLote(Long loteId, int max, int firstResult);

/**
	 * Obtiene Los mensajes a partir de query
	 * 
	 * @param query
	 * @return
	 */
	List<TblMensajesHist> getMensajesByQuery(TblMensajesHistQuery query);

	
	/**
	 * actualiza el mensaje 
	 * 
	 * @param mensaje
	 */
	void update(TblMensajesHist mensaje);


	/**
	 * cuenta los mensajes by query
	 * 
	 * @param createQueryMensaje
	 * @return Integer
	 */
	Integer countMensajesHistoricosByQuery(TblMensajesHistQuery createQueryMensaje);

	/**
	 * Se obtiene el id de servicio a partir del id mensaje
	 * 
	 * @param idMensaje
	 * @return TblServicios
	 */
	TblServicios getServicioByIdMensaje(Long idMensaje);
	
}
