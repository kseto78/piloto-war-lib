package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.plataformamensajeria.iop.beans.PlanificacionBean;
import es.minhap.sim.model.ViewPlanificaciones;

public interface ViewPlanificacionesManager {

	/**
	 * Se obtienen todos las planificaicones de un servicio
	 * 
	 * @param servidorId
	 * @return List
	 */
	List<ViewPlanificaciones> getPlanificacionesServidor(Long servidorId);

	/**
	 * Se obtienen todos las planificaicones paginadas
	 * 
	 * @param start
	 * @param size
	 * @param order
	 * @param column
	 * @param nombre
	 * @param criterio
	 * @param count
	 * @return List
	 */
	List<ViewPlanificaciones> getPlanificacionesPaginadas(int start, int size, String order, String column,
			String nombre, PlanificacionBean criterio, boolean count);

	/**
	 * Se obtienen la planificacion por Id
	 * 
	 * @param planificacionId
	 * @return ViewPlanificaciones
	 */
	ViewPlanificaciones getPlanificacionById(long planificacionId);

	
}
