package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblMensajesAdjuntos;

public interface TblMensajesAdjuntosManager {

	/**
	 * Inserta en tabla Mensajes Adjuntos
	 * 
	 * @param TblMensajesAdjuntos
	 * @return id
	 */
	Long insertarMensajesAdjuntos(TblMensajesAdjuntos menAd);
	
	/**
	 * obtiene el listado de los idAdjuntos de un mensaje
	 * 
	 * @param mensajeId
	 * @return List<TblMensajesAdjuntos>
	 */
	List<TblMensajesAdjuntos> listaAdjuntosByMensaje(Long mensajeId);


	/**
	 * Elimina en tabla Mensajes Adjuntos
	 * 
	 * @param TblMensajesAdjuntos
	 * @return id
	 */
	void delete(Long idMensajeAdjunto);

	/**
	 * obtiene el listado de los idAdjuntos de un mensaje
	 * 
	 * @param mensajeId
	 * @return Integer
	 */
	Integer countAdjuntosByMensaje(Long mensajeId);
	

}
