package es.minhap.plataformamensajeria.iop.dao;

/**
 * 
 * 
 * @author everis
 *
 */
public interface QueryExecutorLotesEnvios {
	
	/**
	 * Actualiza el estado del lote a partir del id del mensaje
	 * 
	 * @param estado
	 * @param idMensaje
	 * @return
	 */
	Integer updateStatusLoteByIdMensaje(String estado, Long idMensaje);
	
	/**
	 * Comprueba si para un identificador de mensaje es multidestinatario
	 * 
	 * @param mensajeId
	 * @return
	 */
	Boolean esMultidestinatario(Long mensajeId);
	
	/**
	 * Se recupera el id lote a partir del mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	Long getIdLoteByIdMensaje(Long idMensaje);
}
