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
	public Long insertarMensajesAdjuntos(TblMensajesAdjuntos menAd);
	
	/**
	 * obtiene el listado de los idAdjuntos de un mensaje
	 * 
	 * @param mensajeId
	 * @return List<TblMensajesAdjuntos>
	 */
	public List<TblMensajesAdjuntos> listaAdjuntosByMensaje(Long mensajeId);


	/**
	 * Elimina en tabla Mensajes Adjuntos
	 * 
	 * @param TblMensajesAdjuntos
	 * @return id
	 */
	public void delete(Long idMensajeAdjunto);

	/**
	 * obtiene el listado de los idAdjuntos de un mensaje
	 * 
	 * @param mensajeId
	 * @return Integer
	 */
	public Integer countAdjuntosByMensaje(Long mensajeId);
	

}
