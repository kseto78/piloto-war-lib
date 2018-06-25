package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.plataformamensajeria.iop.beans.EstadisticasBean;

/**
 * 
 * 
 * @author everis
 *
 */
public interface QueryExecutorEstadisticas {
	
	/**
	 * Obtiene el estado de los lotes de envíos
	 * 
	 * @return
	 */
	public List<Object[]> getEstadisticas(EstadisticasBean estadistica, StringBuffer aplicaciones);
	
	
}
