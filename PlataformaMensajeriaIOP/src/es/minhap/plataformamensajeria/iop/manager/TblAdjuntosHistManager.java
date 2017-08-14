package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.TblAdjuntosHist;


public interface TblAdjuntosHistManager {

	/**
	 * Inserta una entrada en la tabla adjuntos
	 * 
	 * @param adjunto
	 * @return idAdjunto
	 */
	public Long insertarAdjunto(TblAdjuntosHist adjunto);

	/**
	 * Obtiene un adjunto por Id
	 * 
	 * @param adjuntoid
	 * @return TblAdjuntosHist
	 */
	public TblAdjuntosHist getAdjuntoById(Long adjuntoid);

	
	/**
	 * Actualiza adjunto
	 * 
	 * @param adjunto
	 */
	public void update(TblAdjuntosHist adjunto);

}
