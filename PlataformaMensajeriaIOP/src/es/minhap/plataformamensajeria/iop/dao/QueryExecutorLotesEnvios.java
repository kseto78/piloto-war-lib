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
	public Integer updateStatusLoteByIdMensaje(String estado, Long idMensaje);
	
	/**
	 * Comprueba si para un identificador de mensaje es multidestinatario
	 * 
	 * @param mensajeId
	 * @return
	 */
	public Boolean esMultidestinatario(Long mensajeId);
	
}
