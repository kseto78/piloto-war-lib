package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.PlanificacionBean;

/**
 * <p>
 * Interface que define los m&eacute;todos para la gesti&oacute;n de
 * planificaciones
 * </p>.
 *
 * @author Selered
 */
public interface ServicioPlanificacion {

	/**
	 * Obtener planificaciones by servidor id.
	 *
	 * @param servidorId the servidor id
	 * @return planificaciones by servidor id
	 * @throws BusinessException the business exception
	 */
	List<PlanificacionBean> getPlanificacionesByServidorId(Integer servidorId) throws BusinessException;

	/**
	 * Obtener planificaciones by servicio ID.
	 *
	 * @param servicioId the servicio id
	 * @return planificaciones by servicio ID
	 * @throws BusinessException the business exception
	 */
	List<PlanificacionBean> getPlanificacionesByServicioID(Integer servicioId) throws BusinessException;

	/**
	 * Obtener planificaciones.
	 *
	 * @param start the start
	 * @param size the size
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @return planificaciones
	 * @throws BusinessException the business exception
	 */
	PaginatedList<PlanificacionBean> getPlanificaciones(int start, int size, String order, String columnSort,
			PlanificacionBean criterio) throws BusinessException;

	/**
	 * New planificacion.
	 *
	 * @param planificacion the planificacion
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcion the descripcion
	 * @return the integer
	 * @throws BusinessException the business exception
	 */
	Integer newPlanificacion(PlanificacionBean planificacion, String source, String accion, Long accionId, String descripcion) throws BusinessException;

	/**
	 * Update planificacion.
	 *
	 * @param planificacion the planificacion
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void updatePlanificacion(PlanificacionBean planificacion, String source, String accion, Long accionId, String descripcion) throws BusinessException;

	/**
	 * Load planificacion.
	 *
	 * @param planificacion the planificacion
	 * @return the planificacion bean
	 * @throws BusinessException the business exception
	 */
	PlanificacionBean loadPlanificacion(PlanificacionBean planificacion) throws BusinessException;

	/**
	 * Delete planificacion.
	 *
	 * @param planificacion the planificacion
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcion the descripcion
	 * @return true, if successful
	 * @throws BusinessException the business exception
	 */
	boolean deletePlanificacion(PlanificacionBean planificacion, String source, String accion, Long accionId, String descripcion) throws BusinessException;

	/**
	 * Valida planificacion optima.
	 *
	 * @param planificacionId the planificacion id
	 * @param tipo the tipo
	 * @param servidorId the servidor id
	 * @param servicioId the servicio id
	 * @param lunes the lunes
	 * @param martes the martes
	 * @param miercoles the miercoles
	 * @param jueves the jueves
	 * @param viernes the viernes
	 * @param sabado the sabado
	 * @param domingo the domingo
	 * @param horaHasta the hora hasta
	 * @param horaDesde the hora desde
	 * @return the int
	 * @throws BusinessException the business exception
	 */
	int validaPlanificacionOptima(String planificacionId, Integer tipo, Integer servidorId, Integer servicioId,
			String lunes, String martes, String miercoles, String jueves, String viernes, String sabado,
			String domingo, String horaHasta, String horaDesde) throws BusinessException;

	/**
	 * Valida planificacion optima organismo.
	 *
	 * @param planificacionId the planificacion id
	 * @param tipo the tipo
	 * @param servidorId the servidor id
	 * @param servicioId the servicio id
	 * @param lunes the lunes
	 * @param martes the martes
	 * @param miercoles the miercoles
	 * @param jueves the jueves
	 * @param viernes the viernes
	 * @param sabado the sabado
	 * @param domingo the domingo
	 * @param horaHasta the hora hasta
	 * @param horaDesde the hora desde
	 * @param organismoId the organismo id
	 * @return the int
	 * @throws BusinessException the business exception
	 */
	int validaPlanificacionOptimaOrganismo(String planificacionId, Integer tipo, Integer servidorId,
			Integer servicioId, String lunes, String martes, String miercoles, String jueves, String viernes,
			String sabado, String domingo, String horaHasta, String horaDesde, Integer organismoId)
			throws BusinessException;

	/**
	 * Valida planificacion servidor.
	 *
	 * @param planificacionId the planificacion id
	 * @param servidorId the servidor id
	 * @param lunes the lunes
	 * @param martes the martes
	 * @param miercoles the miercoles
	 * @param jueves the jueves
	 * @param viernes the viernes
	 * @param sabado the sabado
	 * @param domingo the domingo
	 * @param horaHasta the hora hasta
	 * @param horaDesde the hora desde
	 * @return the int
	 * @throws BusinessException the business exception
	 */
	int validaPlanificacionServidor(String planificacionId, Integer servidorId, String lunes, String martes,
			String miercoles, String jueves, String viernes, String sabado, String domingo, String horaHasta,
			String horaDesde) throws BusinessException;

	/**
	 * Obtener planificaciones by organismo ID.
	 *
	 * @param organismoId the organismo id
	 * @return planificaciones by organismo ID
	 * @throws BusinessException the business exception
	 */
	public List<PlanificacionBean> getPlanificacionesByOrganismoID(Integer organismoId) throws BusinessException;

}
