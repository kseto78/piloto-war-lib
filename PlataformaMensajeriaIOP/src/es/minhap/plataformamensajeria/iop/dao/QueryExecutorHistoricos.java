package es.minhap.plataformamensajeria.iop.dao;

/**
 * 
 * @author everis
 * 
 */
public interface QueryExecutorHistoricos {

	/**
	 * Obtiene el último servidor de la tabla de historicos del mensaje
	 * 
	 * @param mensajeId
	 * @return
	 */
	public Long getServidorByMensaje(Long mensajeId);

}
