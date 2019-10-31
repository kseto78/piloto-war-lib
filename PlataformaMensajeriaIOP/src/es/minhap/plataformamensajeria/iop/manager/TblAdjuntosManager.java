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
	Integer insertarAdjunto(Long mensajeId, String nombre, byte[] contenido, String usuario, String password);

	/**
	 * asocia un adjunto
	 * 
	 * @param mensajeId
	 * @param idAdjunto
	 * @param usuario
	 * @param password
	 * @return idAdjunto
	 */
	Integer asociarAnexo(Long mensajeId, Long idAdjunto, String usuario, String password);


	/**
	 * Elimina un adjunto
	 * 
	 * @param adjuntoid
	 * @return idAdjunto
	 */
	void delete(Long adjuntoid);


	/**
	 * Recupera el adjunto por id
	 * 
	 * @param idAdjunto
	 * @return TblAdjuntos
	 */
	TblAdjuntos getById(Long idAdjunto);


	/**
	 * Obtiene un adjunto por Id
	 * 
	 * @param adjuntoid
	 * @return TblAdjuntos
	 */
	TblAdjuntos getAdjuntoById(Long adjuntoid);

	
	/**
	 * Actualiza adjunto
	 * 
	 * @param adjunto
	 */
	void update(TblAdjuntos adjunto);

}
