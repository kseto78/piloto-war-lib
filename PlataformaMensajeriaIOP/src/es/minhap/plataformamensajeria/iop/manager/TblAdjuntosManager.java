package es.minhap.plataformamensajeria.iop.manager;

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
	public Integer asociarAnexo(Long mensajeId, Long idAdjunto,  String usuario, String password);

}
