package es.minhap.plataformamensajeria.iop.dao;

/**
 * 
 * 
 * @author everis
 * 
 */
public interface QueryExecutorViewPlanificaciones {

	/**
	 * Se obtiene si hay planificación para el día y la hora
	 * 
	 * @param planificacionId
	 * @param tipo
	 * @param dia
	 * @param servidorId
	 * @param paramHoraHasta
	 * @param paramHoraDesde
	 * @param servicioId
	 * @return
	 */
	Integer validaPlanificacionServicio(Long planificacionId, Integer tipo, String dia, Integer servidorId,
			String paramHoraHasta, String paramHoraDesde, Integer servicioId);
	
	/**
	 * Se obtiene si hay planificación para el día y la hora
	 * 
	 * @param planificacionId
	 * @param dia
	 * @param servidorId
	 * @param paramHoraHasta
	 * @param paramHoraDesde
	 * @return
	 */
	Integer validaPlanificacionServidor(Long planificacionId, String dia, Integer servidorId,
			String paramHoraHasta, String paramHoraDesde);
	

	/**
	 * Se obtiene si hay planificación para el día y la hora
	 * 
	 * @param organismoId
	 * @param dia
	 * @param planificacionId
	 * @param paramHoraHasta
	 * @param paramHoraDesde
	 * @param servicioId
	 * @param servidorId
	 * @return
	 */
	Integer countPlanificacionPorHorasOrganismo(Integer organismoId, String dia, Long planificacionId,
			String paramHoraHasta, String paramHoraDesde, Integer servicioId, Integer servidorId, Integer tipo);

}
