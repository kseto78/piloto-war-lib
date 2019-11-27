package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidor;

/**
 * 
 * 
 * @author everis
 *
 */
public interface QueryExecutorViewServidoresPrioridad {
	
	/**
	 * Obtiene los parametros del servidor a partir del identificador del mensaje
	 * 
	 * @param mensajeId
	 * @return
	 */
	List<ParametrosServidor> getServidores(Long mensajeId);
	
	
}
