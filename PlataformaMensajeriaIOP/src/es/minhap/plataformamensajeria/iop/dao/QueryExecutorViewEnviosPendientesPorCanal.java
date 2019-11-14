package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.sim.model.ViewEnviosPendientesPorCanal;

/**
 * 
 * 
 * @author everis
 *
 */
public interface QueryExecutorViewEnviosPendientesPorCanal {

	/**
	 * Se recupera la lista con todos los valores de la vista
	 * 
	 * @return
	 */
	public List<ViewEnviosPendientesPorCanal> getAll();
	
	
}
