package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.TblAdjuntosHist;

public interface TblAdjuntosHistManager {

	/**
	 * Inserta una entrada 
	 * 
	 * @param adjunto
	* @return idAdjunto
	 */
	Long insertar(TblAdjuntosHist adjunto);

	/**
	 * Elimina una entrada 
	 * 
	 * @param adjunto
	 */
	void eliminar(Long adjunto);

	/**
	 * Recupera el adjunto por id
	 * 
	 * @param idAdjunto
	 * @return TblAdjuntos
	 */
	TblAdjuntosHist getById(Long idAdjunto);
	
	/**
	 * Inserta una entrada en la tabla adjuntos
	 * 
	 * @param adjunto
	 * @return idAdjunto
	 */
	Long insertarAdjunto(TblAdjuntosHist adjunto);

	/**
	 * Obtiene un adjunto por Id
	 * 
	 * @param adjuntoid
	 * @return TblAdjuntosHist
	 */
	TblAdjuntosHist getAdjuntoById(Long adjuntoid);

	
	/**
	 * Actualiza adjunto
	 * 
	 * @param adjunto
	 */
	void update(TblAdjuntosHist adjunto);
	

}
