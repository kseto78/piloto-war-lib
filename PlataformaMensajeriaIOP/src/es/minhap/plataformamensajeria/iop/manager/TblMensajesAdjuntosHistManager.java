package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblMensajesAdjuntosHist;

public interface TblMensajesAdjuntosHistManager {

	/**
	 * Inserta en tabla Mensajes Adjuntos
	 * 
	 * @param menAd
	 * @return id
	 */
	public Long insertarMensajesAdjuntos(TblMensajesAdjuntosHist menAd);

	/**
	 * obtiene el listado de los idAdjuntos de un mensaje
	 * 
	 * @param mensajeId
	 * @return List<TblMensajesAdjuntosHist>
	 */
	public List<TblMensajesAdjuntosHist> listaAdjuntosByMensaje(Long mensajeId);

	/**
	 * obtiene el listado de los idAdjuntos de un mensaje
	 * 
	 * @param mensajeId
	 * @return Integer
	 */
	public Integer countAdjuntosByMensaje(Long mensajeId);

}
