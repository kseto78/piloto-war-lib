package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.PlanificacionBean;

/**
 * <p>
 * Interface que define los m&eacute;todos para la gesti&oacute;n de
 * planificaciones
 * </p>
 * 
 * @author Selered
 * 
 */
public interface ServicioPlanificacion {

	List<PlanificacionBean> getPlanificacionesByServidorId(Integer servidorId) throws BusinessException;

	List<PlanificacionBean> getPlanificacionesByServicioID(Integer servicioId) throws BusinessException;

	PaginatedList<PlanificacionBean> getPlanificaciones(int start, int size, String order, String columnSort,
			PlanificacionBean criterio) throws BusinessException;

	Integer newPlanificacion(PlanificacionBean planificacion, String source, String accion, Long accionId, String descripcion) throws BusinessException;

	void updatePlanificacion(PlanificacionBean planificacion, String source, String accion, Long accionId, String descripcion) throws BusinessException;

	PlanificacionBean loadPlanificacion(PlanificacionBean planificacion) throws BusinessException;

	boolean deletePlanificacion(PlanificacionBean planificacion, String source, String accion, Long accionId, String descripcion) throws BusinessException;

	int validaPlanificacionOptima(String planificacionId, Integer tipo, Integer servidorId, Integer servicioId,
			String lunes, String martes, String miercoles, String jueves, String viernes, String sabado,
			String domingo, String horaHasta, String horaDesde) throws BusinessException;

	int validaPlanificacionOptimaOrganismo(String planificacionId, Integer tipo, Integer servidorId,
			Integer servicioId, String lunes, String martes, String miercoles, String jueves, String viernes,
			String sabado, String domingo, String horaHasta, String horaDesde, Integer organismoId)
			throws BusinessException;

	int validaPlanificacionServidor(String planificacionId, Integer servidorId, String lunes, String martes,
			String miercoles, String jueves, String viernes, String sabado, String domingo, String horaHasta,
			String horaDesde) throws BusinessException;

	public List<PlanificacionBean> getPlanificacionesByOrganismoID(Integer organismoId) throws BusinessException;

}
