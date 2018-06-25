package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblPlanificaciones;
import es.minhap.sim.query.TblPlanificacionesQuery;

public interface TblPlanificacionesManager {

	/**
	 * recupera la lista con los servicios que estan en planificacion
	 *
	 * @return List<Long>
	 */
	public List<Long> getServiciosPlanificacion();
	
	
	/**
	 * recupera la lista con las planificaciones por servidor
	 * @param servidorId
	 * @return List<Long>
	 */
	public List<TblPlanificaciones> getPlanificacionesByServidor(Long servidorId);

	
	/**
	 * actualizamos la planificacion
	 *
	 * @param p
	 * @param source
	 * @param accion
	 * @param accionId
	 * @param descripcion
	 */
	public void updatePlanificacion(TblPlanificaciones p, String source, String accion, Long accionId, String descripcion);


	/**
	 * recupera la planificacion a partir del id
	 * @param planificacionId
	 * @return
	 */
	public TblPlanificaciones getPlanificacionById(Long planificacionId);


	/**
	 * Inserta la planificacion
	 * @param planificacion
	 * @param source
	 * @param accion
	 * @param accionId
	 * @param descripcion
	 * @return
	 */
	public Long insert(TblPlanificaciones planificacion, String source, String accion, Long accionId, String descripcion);

	/**
	 * Obtiene la lista de planificaciones a partir del criterio
	 * @param query
	 * @return
	 */
	public List<TblPlanificaciones> getPlanificacionesByQuery(TblPlanificacionesQuery query);

	/**
	 * recupera la lista con las planificaciones por servidor
	 * @param servidorId
	 * @return List<Long>
	 */
	public List<TblPlanificaciones> getPlanificacionesByServicio(Long servicioId);
}
