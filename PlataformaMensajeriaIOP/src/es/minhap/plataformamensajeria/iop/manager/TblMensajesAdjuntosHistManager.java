package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblMensajesAdjuntosHist;
import es.minhap.sim.model.TblMensajesHist;

public interface TblMensajesAdjuntosHistManager {

	/**
	 * Obtiene un mensaje historico a partir del id pasado
	 * 
	 * @param idMensaje
	 * @param mensajeHist 
	 * @param adjuntoHist
	 * @return TblMensajesHist
	 */
	List<TblMensajesAdjuntosHist> convertMensajeTOMensajeHist(Long idMensaje, TblMensajesHist mensajeHist);

	/**
	 * Inserta el mensaje historicos
	 * 
	 * @param m
	 * @return Long
	 */
	Long insert(TblMensajesAdjuntosHist m);

	
	/**
	 * Elimina el mensaje historicos
	 * 
	 * @param m
	 * @return Long
	 */
	void eliminar(Long id);

/**
	 * Inserta en tabla Mensajes Adjuntos
	 * 
	 * @param menAd
	 * @return id
	 */
	Long insertarMensajesAdjuntos(TblMensajesAdjuntosHist menAd);

	/**
	 * obtiene el listado de los idAdjuntos de un mensaje
	 * 
	 * @param mensajeId
	 * @return List<TblMensajesAdjuntosHist>
	 */
	List<TblMensajesAdjuntosHist> listaAdjuntosByMensaje(Long mensajeId);

	/**
	 * obtiene el listado de los idAdjuntos de un mensaje
	 * 
	 * @param mensajeId
	 * @return Integer
	 */
	Integer countAdjuntosByMensaje(Long mensajeId);

	TblMensajesAdjuntosHist getMensajesAdjuntosHistById(Long idMensajeAdjuntoHistId);
}
