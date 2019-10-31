package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.plataformamensajeria.sm.modelo.ParametrosReceptor;

/**
 * 
 * 
 * @author everis
 *
 */
public interface QueryExecutorViewReceptoresPrioridad {
	
	List<ParametrosReceptor> getReceptores(Long mensajeId);

}
