package es.minhap.plataformamensajeria.iop.dao;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author everis
 * 
 */
public interface QueryExecutorLotesEnviosHist {

	/**
	 * Obtiene los lotes segun datos
	 * @param servicioId
	 * @param fecha
	 * @return 
	 */
	public Integer countLotesByServicioYFecha(Long servicioId, Date fecha);

	/**
	 * Obtiene los idlotes segun datos
	 * @param servicioId
	 * @param fecha 
	 * @param max
	 * @param firstResult
	 * @return 
	 */
	public List<Long> getIdLotesByServicio(Long servicioId, Date fecha, Integer max, Integer firstResult);

}
