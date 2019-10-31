package es.minhap.plataformamensajeria.iop.dao;

/**
 * 
 * @author everis
 * 
 */
public interface QueryExecutorAplicaciones {

	/**
	 * Obtiene el nombre de la aplicacion a partir de un mensaje
	 *  
	 * @param mensajeId
	 * @return
	 */
	Long  findAplicacionByMessageId(Long mensajeId);

}
