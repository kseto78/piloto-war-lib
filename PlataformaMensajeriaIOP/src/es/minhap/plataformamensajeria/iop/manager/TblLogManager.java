package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblLog;

public interface TblLogManager {

	/**
	 * Se inserta en la tabla de log
	 * 
	 * @param log
	 */
	public void insertLog(TblLog log);

	/**
	 * Se obtienen todos las auditorias paginadas
	 * 
	 * @param start
	 * @param size
	 * @param order
	 * @param column
	 * @param nombre
	 * @param criterio
	 * @param count
	 * @param recursosFiltro
	 * @return List
	 */
	public List<TblLog> getAuditoriasPaginadas(int start, int size, String order, String column, String nombre,
			es.minhap.plataformamensajeria.iop.beans.AuditoriaPlataformaBean criterio, boolean b, String recursosFiltro);

	
}
