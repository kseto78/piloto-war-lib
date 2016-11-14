package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

/**
 * 
 * 
 * @author everis
 *
 */
public interface QueryExecutorPlanificaciones {
	
	/**
	 * Obtiene la lista servicios con planificación activa
	 * @return Lista
	 */
	public List<Long> obtenerServiciosPlanificacion();
	
	/**
	 * Obtiene la lista servicios con planificacion por defecto
	 * @param List<Long>
	 * @return Lista
	 */
	public List<Long> obtenerServiciosSinPlanificacion();
	

	
}
