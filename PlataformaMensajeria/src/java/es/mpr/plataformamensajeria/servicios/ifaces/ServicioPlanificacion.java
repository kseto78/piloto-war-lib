package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.model.PlanificacionJPA;
import es.mpr.plataformamensajeria.model.views.ViewPlanificacionJPA;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de planificaciones</p>
 * 
 * @author Selered
 *
 */
@Service
public interface ServicioPlanificacion {
	
	List<PlanificacionBean> getPlanificaciones(PlanificacionBean criterio) throws BusinessException;
	
	List<PlanificacionBean> getPlanificacionesByServidorId(Integer servidorId) throws BusinessException;
	
	List<PlanificacionBean> getPlanificacionesByServicioID(Integer servicioId) throws BusinessException;
	
	List<PlanificacionBean> getPlanificacionesByProveedorSMSId(Integer proveedorSMSId) throws BusinessException;
	
	List<PlanificacionBean> getPlanificacionesByReceptorSMSId(Integer receptorSMSId) throws BusinessException;
	
	List<PlanificacionBean> getPlanificacionesByServidorPushId(Integer servidorPushId) throws BusinessException;
	
	PaginatedList<PlanificacionBean> getPlanificaciones(int start, int size, String order, String columnSort,PlanificacionBean criterio) 
		throws BusinessException;
	
	public Integer getTotalPlanificaciones(PlanificacionBean criterio, EntityManager em,String query,boolean aplicacionId, boolean servicioId,
			boolean lunes, boolean martes, boolean miercoles, boolean jueves,
			boolean viernes, boolean sabado, boolean domingo,
			boolean inicioDesde, boolean inicioHasta, boolean finDesde,
			boolean finHasta, boolean canalId, boolean servidorId) throws BusinessException;
	
	Integer newPlanificacion(PlanificacionBean planificacion)throws BusinessException;
	
	void updatePlanificacion(PlanificacionBean planificacion)throws BusinessException;
	
	PlanificacionBean loadPlanificacion(PlanificacionBean planificacion)throws BusinessException;
	
	boolean deletePlanificacion(PlanificacionBean planificacion)throws BusinessException;
	
	boolean deletePlanificacionOrganismo(PlanificacionBean planificacion, Integer idOrganismo)throws BusinessException;
	
	public ViewPlanificacionJPA loadViewPlanificacionJPA(PlanificacionBean planificacion)	throws BusinessException;

	PlanificacionJPA loadPlanificacionJPA(PlanificacionBean planificacion) throws BusinessException;
/*
 * paramTipo number,paramServidor number, paramServicio number,

    paramL varchar2,paramM varchar2,paramX varchar2, paramJ varchar2, paramV varchar2,

    paramS varchar2,paramD varchar2, paramHoraHasta varchar2, paramHoraDesde varchar2,

    response out number
 */
	int validaPlanificacionOptima(String planificacionId,Integer tipo,Integer servidorId,Integer servicioId, String lunes, String martes,
			String miercoles,String jueves, String viernes, String sabado, String domingo, String horaHasta, String horaDesde) throws BusinessException;
	
	int validaPlanificacionOptimaOrganismo(String planificacionId,Integer tipo,Integer servidorId,Integer servicioId, String lunes, String martes,
			String miercoles,String jueves, String viernes, String sabado, String domingo, String horaHasta, String horaDesde, Integer organismoId) throws BusinessException;
	
	int validaPlanificacionServidor(String planificacionId, Integer servidorId, String lunes, String martes,
			String miercoles,String jueves, String viernes, String sabado, String domingo, String horaHasta, String horaDesde) throws BusinessException;
	
	boolean crearJobPorPlanificacion(Integer idPlanificacion) throws BusinessException;
	
	boolean modificarJobPorPlanificacion(Integer idPlanificacion) throws BusinessException;
	
	boolean eliminarJobPorPlanificacion(Integer idPlanificacion) throws BusinessException;
	
	public List<PlanificacionBean> getPlanificacionesByOrganismoID(Integer organismoId) throws BusinessException;
}
