package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.TblAdjuntos;


public interface TblAdjuntosManager {

	/**
	 * Inserta una entrada en la tabla adjuntos
	 * 
	 * @param mensajeId
	 * @param nombre
	 * @param contenido
	 * @param usuario
	 * @param password
	 * @return idAdjunto
	 */
	public Integer insertarAdjunto(Long mensajeId, String nombre, byte[] contenido, String usuario, String password);

	/**
	 * asocia un adjunto
	 * 
	 * @param mensajeId
	 * @param idAdjunto
	 * @param usuario
	 * @param password
	 * @return idAdjunto
	 */
	public Integer asociarAnexo(Long mensajeId, Long idAdjunto, String usuario, String password);

	/**
	 * Obtiene un adjunto por Id
	 * 
	 * @param adjuntoid
	 * @return TblAdjuntos
	 */
	public TblAdjuntos getAdjuntoById(Long adjuntoid);

	
	/**
	 * Actualiza adjunto
	 * 
	 * @param adjunto
	 */
	public void update(TblAdjuntos adjunto);

}
