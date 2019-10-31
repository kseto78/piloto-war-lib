package es.minhap.plataformamensajeria.iop.dao;

import es.minhap.plataformamensajeria.iop.beans.entity.EstadosBean;

/**
 * 
 * @author everis
 *
 */
public interface QueryExecutorSubEstados {
	
	/**
	 * Obtiene un Estados Bean a partir del codigo de subestado
	 * 
	 * @param code
	 * @return
	 */
	EstadosBean getEstadoByCode(String code);

	
}
