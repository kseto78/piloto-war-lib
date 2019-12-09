package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorViewPlanificaciones;
import es.minhap.plataformamensajeria.iop.manager.TblOrganismosManager;
import es.minhap.plataformamensajeria.iop.manager.TblPlanificacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.plataformamensajeria.iop.manager.TblTipoPlanificacionesManager;
import es.minhap.plataformamensajeria.iop.manager.ViewPlanificacionesManager;
import es.minhap.sim.model.TblPlanificaciones;
import es.minhap.sim.model.ViewPlanificaciones;
import es.minhap.sim.query.TblPlanificacionesQuery;
import es.minhap.sim.query.TblServiciosQuery;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de planificaciones a traves de JPA.
 * 
 * @author 
 * 
 */

@Service("servicioPlanificacionImpl")
public class ServicioPlanificacionImpl implements ServicioPlanificacion{

	protected static final String SERVICIOPLANIFI = "ServicioPlanificacionImpl - deletePlanificacion:";

	protected static final String SERVICIOPLANIFI0 = "ServicioPlanificacionImpl - validaPlanificacionServidor: ";

	protected static final String NOMBREAPLICACIO = "nombreAplicacion";

	protected static final String D = "d";

	protected static final String SERVICIOPLANIFI1 = "ServicioPlanificacionImpl - newPlanificacion: ";

	protected static final String J = "j";

	protected static final String L = "l";

	protected static final String M = "m";

	protected static final String ERRORSDOTORGANI = "errors.organismo.getOrganismos";

	protected static final String R_CONST_REF = "2";

	protected static final String S = "s";

	protected static final String V = "v";

	protected static final String TRUE = "true";

	protected static final String X = "x";

	protected static final String ERRORSDOTORGANI0 = "errors.organismo.deleteOrganismo";

	/**  logger. */
	private static Logger logger = Logger.getLogger(ServicioParametroServidorImpl.class);
	
	/**  view planificaciones manager. */
	@Resource
	private ViewPlanificacionesManager viewPlanificacionesManager;
	
	/**  tbl planificaciones manager. */
	@Resource
	private TblPlanificacionesManager tblPlanificacionesManager;
	
	/**  query executor view planificaciones. */
	@Resource
	private QueryExecutorViewPlanificaciones queryExecutorViewPlanificaciones;
	
	/**  tbl servicios manager. */
	@Resource
	private TblServiciosManager tblServiciosManager;
	
	/**  tbl servidores manager. */
	@Resource
	private TblServidoresManager tblServidoresManager;
	
	/**  tbl tipo planificaciones manager. */
	@Resource
	private TblTipoPlanificacionesManager tblTipoPlanificacionesManager;
	
	/**  tbl organismos manager. */
	@Resource(name="TblOrganismosManagerImpl")
	private TblOrganismosManager tblOrganismosManager;
	
		
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion#getPlanificaciones(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.PlanificacionBean)
 */
////MIGRADO
	@Override
	public PaginatedList<PlanificacionBean> getPlanificaciones(int start, int size,
			String order, String columnSort, PlanificacionBean criterio)
			throws BusinessException {
		String nombre = null;
	
		try {
			
			//Columna para ordenar
			HashMap<String, String> columns = new HashMap<>();
			columns.put(R_CONST_REF,NOMBREAPLICACIO);
			columns.put("3","nombreServicio");
			
			if (columnSort==null) {
				columnSort = R_CONST_REF;
			} 
				//Id
			
			String column = columns.get(columnSort);
			if (column==null) {
				column = NOMBREAPLICACIO;
			}
			
			es.minhap.plataformamensajeria.iop.beans.PlanificacionBean pb = new es.minhap.plataformamensajeria.iop.beans.PlanificacionBean();
			if (null != criterio){
				pb = createPlanificacionBean(pb, criterio);
			}
			
			List<ViewPlanificaciones> lista = viewPlanificacionesManager.getPlanificacionesPaginadas(start, size, order, column, nombre, pb, false);
			List<PlanificacionBean> pageList = getListViewPlanificacionBean(lista);
			
			// Total de organismos
			Integer rowcount = viewPlanificacionesManager.getPlanificacionesPaginadas(start, size, order, column, nombre,
					pb, true).size();
			
			PaginatedList<PlanificacionBean> result = new PaginatedList<>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			logger.error("ServicioPlanificacionImpl - getPlanificaciones:" + e);
			throw new BusinessException(e, ERRORSDOTORGANI);

		}
	}

	/**
	 * Creates the planificacion bean.
	 *
	 * @param pb the pb
	 * @param criterio the criterio
	 * @return the es.minhap.plataformamensajeria.iop.beans. planificacion bean
	 */
	////MIGRADO
	private es.minhap.plataformamensajeria.iop.beans.PlanificacionBean createPlanificacionBean(
			es.minhap.plataformamensajeria.iop.beans.PlanificacionBean pb, PlanificacionBean criterio) {
		try{
			BeanUtils.copyProperties(pb, criterio);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.error("ServicioPlanificacionImpl - createPlanificacionBean:" + e);
			} 
		return pb;
	}


	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion#newPlanificacion(es.mpr.plataformamensajeria.beans.PlanificacionBean, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	//////MIGRADO
	@Override 
	@Transactional
	public Integer newPlanificacion(PlanificacionBean planificacion, String source, String accion, Long accionId, String descripcion) throws BusinessException {
		try{
			TblPlanificaciones planificacionTO = getPlanificacionTO(planificacion);
			planificacionTO.parseDias();
						
			planificacionTO.setPlanificacionid(null);
			
			planificacionTO.setFechacreacion(new Date());
			
			planificacionTO.setCreadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			Long idPlanificacion = tblPlanificacionesManager.insert(planificacionTO, source, accion, accionId, descripcion);
			
			planificacion.setPlanificacionId(idPlanificacion.intValue());
			planificacion.setFechaCreacion(planificacionTO.getFechacreacion());
			planificacion.setCreadoPor(planificacionTO.getCreadopor());
			return idPlanificacion.intValue();
		}catch (Exception e){
			logger.error(SERVICIOPLANIFI1, e);
			throw new BusinessException(e,ERRORSDOTORGANI);
		}
	}


	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion#updatePlanificacion(es.mpr.plataformamensajeria.beans.PlanificacionBean, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	//////MIGRADO
	@Override
	@Transactional
	public void updatePlanificacion(PlanificacionBean planificacion, String source, String accion, Long accionId, String descripcion) throws BusinessException {
		
		try {
			TblPlanificaciones planificacionTO = getPlanificacionTO(planificacion);
			planificacionTO.parseDias();
			planificacionTO.setFechacreacion(planificacion.getFechaCreacion());
			planificacionTO.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			planificacionTO.setFechamodificacion(new Date());
			tblPlanificacionesManager.updatePlanificacion(planificacionTO, source, accion, accionId, descripcion);
			
		} catch (Exception e){
			logger.error(SERVICIOPLANIFI1, e);
			throw new BusinessException(e,ERRORSDOTORGANI);
		}
	}

	/**
	 * Valida la planifciaci�n para ver si se garantizan los envios.
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
	/////MIGRADO
	public int validaPlanificacionOptima(String planificacionId,Integer tipo,Integer servidorId,Integer servicioId, String lunes, String martes,
			String miercoles,String jueves, String viernes, String sabado, String domingo, String horaHasta, String horaDesde) throws BusinessException {
		Long planId = null;
		Integer solapado = null;
		
		try{
			planId = Long.parseLong(planificacionId);
		}catch(Exception e)	{}
		
		try{
				if (null == solapado && TRUE.equals(lunes)){
					solapado = queryExecutorViewPlanificaciones.validaPlanificacionServicio(planId, tipo, L, servidorId, horaHasta, horaDesde, servicioId);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(martes)){
					solapado = queryExecutorViewPlanificaciones.validaPlanificacionServicio(planId, tipo, M, servidorId, horaHasta, horaDesde, servicioId);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(miercoles)){
					solapado = queryExecutorViewPlanificaciones.validaPlanificacionServicio(planId,tipo, X, servidorId, horaHasta, horaDesde, servicioId);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(jueves)){
					solapado = queryExecutorViewPlanificaciones.validaPlanificacionServicio(planId,tipo, J, servidorId, horaHasta, horaDesde, servicioId);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(viernes)){
					solapado = queryExecutorViewPlanificaciones.validaPlanificacionServicio(planId,tipo, V, servidorId, horaHasta, horaDesde, servicioId);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(sabado)){
					solapado = queryExecutorViewPlanificaciones.validaPlanificacionServicio(planId,tipo, S, servidorId, horaHasta, horaDesde, servicioId);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(domingo)){
					solapado = queryExecutorViewPlanificaciones.validaPlanificacionServicio(planId,tipo, D, servidorId, horaHasta, horaDesde, servicioId);
				}
		}catch (Exception e) {
			logger.error(SERVICIOPLANIFI0, e);
			throw new BusinessException(e,ERRORSDOTORGANI);
		}
		return solapado;
	}
	
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion#validaPlanificacionServidor(java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	///////MIGRADO
	public int validaPlanificacionServidor(String planificacionId, Integer servidorId, String lunes, String martes,
			String miercoles,String jueves, String viernes, String sabado, String domingo, String horaHasta, String horaDesde) throws BusinessException {
		Integer solapado = null;
		Long planId = null;
		try{
			planId = Long.parseLong(planificacionId);
		}catch(Exception e)	{}
		
		try{
			
			if (null != servidorId && servidorId > 0){
				if ((null == solapado || solapado == 1) && TRUE.equals(lunes)){
					solapado = queryExecutorViewPlanificaciones.validaPlanificacionServidor(planId, L, servidorId, horaHasta, horaDesde);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(martes)){
					solapado = queryExecutorViewPlanificaciones.validaPlanificacionServidor(planId, M, servidorId, horaHasta, horaDesde);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(miercoles)){
					solapado = queryExecutorViewPlanificaciones.validaPlanificacionServidor(planId, X, servidorId, horaHasta, horaDesde);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(jueves)){
					solapado = queryExecutorViewPlanificaciones.validaPlanificacionServidor(planId, J, servidorId, horaHasta, horaDesde);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(viernes)){
					solapado = queryExecutorViewPlanificaciones.validaPlanificacionServidor(planId, V, servidorId, horaHasta, horaDesde);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(sabado)){
					solapado = queryExecutorViewPlanificaciones.validaPlanificacionServidor(planId, S, servidorId, horaHasta, horaDesde);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(domingo)){
					solapado = queryExecutorViewPlanificaciones.validaPlanificacionServidor(planId, D, servidorId, horaHasta, horaDesde);
				}
				
			}
			
		}catch (Exception e) {
			logger.error(SERVICIOPLANIFI0, e);
			throw new BusinessException(e,ERRORSDOTORGANI);
		}
		return solapado;
	}
	

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion#loadPlanificacion(es.mpr.plataformamensajeria.beans.PlanificacionBean)
	 */
	/////MIGRADO
	@Override
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public PlanificacionBean loadPlanificacion(PlanificacionBean planificacion)	throws BusinessException {
		try {
			ViewPlanificaciones plan = viewPlanificacionesManager.getPlanificacionById(planificacion.getPlanificacionId().longValue());
			
			return getPlanificacionBean(plan);
		} catch (Exception e){
				throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}
		
		
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion#deletePlanificacion(es.mpr.plataformamensajeria.beans.PlanificacionBean, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	//////MIGRADO
	@Override
	@Transactional
	public boolean deletePlanificacion(PlanificacionBean planificacion, String source, String accion, Long accionId, String descripcion) throws BusinessException {
		boolean sw=true;
		try {
			try {
				TblPlanificaciones planificacionTO = tblPlanificacionesManager.getPlanificacionById(planificacion.getPlanificacionId().longValue()); 	 
				planificacionTO.setEliminado("S");
				planificacionTO.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				planificacionTO.setFechamodificacion(new Date());
				tblPlanificacionesManager.updatePlanificacion(planificacionTO, source, accion, accionId, descripcion);	
			} catch (Exception e){
				logger.error(SERVICIOPLANIFI + e);
				sw=false;
			}
			return sw;	
		} catch (Exception e){
			logger.error(SERVICIOPLANIFI + e);
			sw=false;
		}
		return sw;
	}
	

	
	/**
	 * <p>Obtenemos un objeto OrganismoTO a partir de un objeto OrganismoBean</p>.
	 *
	 * @param planificacion the planificacion
	 * @return objeto OrganismoJPA
	 * @throws BusinessException the business exception
	 */
	/////MIGRADO
	public TblPlanificaciones getPlanificacionTO(PlanificacionBean planificacion) throws BusinessException {
		TblPlanificaciones res = new TblPlanificaciones();
		try {
			res.setActivo(planificacion.getActivo());
			res.setCreadopor(planificacion.getCreadoPor());
			res.setD(planificacion.getDomingo());
			res.setExternalid((null != planificacion.getExternalId())? planificacion.getExternalId().longValue() : null);
			res.setFechacreacion(planificacion.getFechaCreacion());
			res.setFechamodificacion(planificacion.getFechaModificacion());
			res.setHoradesde(planificacion.getHoraDesde());
			res.setHorahasta(planificacion.getHoraHasta());
			res.setJ(planificacion.getJueves());
			res.setL(planificacion.getLunes());
			res.setM(planificacion.getMartes());
			res.setModificadopor(planificacion.getModificadoPor());
			res.setOrganismoid((null != planificacion.getOrganismoId())? planificacion.getOrganismoId().longValue() : null);
			res.setPlanificacionid((null != planificacion.getPlanificacionId())? planificacion.getPlanificacionId().longValue() : null);
			res.setS(planificacion.getSabado());
			res.setTblServicios((null != planificacion.getServicioId())? tblServiciosManager.getServicio(planificacion.getServicioId().longValue()) : null);
			res.setTblServidores((null != planificacion.getServidorId())? tblServidoresManager.getServidorById(planificacion.getServidorId().longValue()) : null);
			res.setTblTipoPlanificaciones((null != 
				planificacion.getTipoPlanificacionId())? tblTipoPlanificacionesManager.getTipoPlanificacionById(planificacion.getTipoPlanificacionId().longValue()) : null);
			res.setV(planificacion.getViernes());
			res.setX(planificacion.getMiercoles());
		
		} catch (Exception e) {
			logger.error("ServicioPlanificacionImpl - getPlanificacionJPA: ", e);
		} 
		
		return res;
	}
	
	/**
	 * <p>Obtenemos un objeto PlanificacionBean a partir de un objeto TblPlanificaciones</p>.
	 *
	 * @param planificacion the planificacion
	 * @return objeto PlanificacionBean
	 * @throws BusinessException the business exception
	 */
	/////MIGRADO
	public PlanificacionBean getPlanificacionBean(TblPlanificaciones planificacion) throws BusinessException {
		PlanificacionBean res = new PlanificacionBean();
		try {
			res.setActivo(planificacion.getActivo());
			res.setCreadoPor(planificacion.getCreadopor());
			res.setDomingo(planificacion.getD());
			res.setExternalId((null != planificacion.getExternalid())? planificacion.getExternalid().intValue() : null);
			res.setFechaCreacion(planificacion.getFechacreacion());
			res.setFechaModificacion(planificacion.getFechamodificacion());
			res.setHoraDesde(planificacion.getHoradesde());
			res.setHoraHasta(planificacion.getHorahasta());
			res.setJueves(planificacion.getJ());
			res.setLunes(planificacion.getL());
			res.setMartes(planificacion.getM());
			res.setModificadoPor(planificacion.getModificadopor());
			res.setOrganismoId((null != planificacion.getOrganismoid())? planificacion.getOrganismoid().intValue() : null);
			res.setDir3Organismo((null != planificacion.getOrganismoid())? tblOrganismosManager.getOrganismoById(planificacion.getOrganismoid()).getDir3() : null);
			res.setPlanificacionId((null != planificacion.getPlanificacionid())? planificacion.getPlanificacionid().intValue() : null);
			res.setSabado(planificacion.getS());
			res.setServicioId((null != planificacion.getTblServicios())? planificacion.getTblServicios().getServicioid().intValue() : null);
			res.setServidorId((null != planificacion.getTblServidores())? planificacion.getTblServidores().getServidorid().intValue() : null);
			res.setNombreServicio((null != planificacion.getTblServicios())? planificacion.getTblServicios().getNombre() : null);
			res.setNombreServidor((null != planificacion.getTblServidores())? planificacion.getTblServidores().getNombre() : null);
			res.setTipoPlanificacionId((null != 
				planificacion.getTblTipoPlanificaciones())? planificacion.getTblTipoPlanificaciones().getTipoplanificacionid().intValue() : null);
			res.setViernes(planificacion.getV());
			res.setMiercoles(planificacion.getX());
		
		} catch (Exception e) {
			logger.error("ServicioPlanificacionImpl - getPlanificacionBean: ", e);
		} 
		
		return res;
	}
	
	/**
	 * <p>Convertirmos una lista de Planificacion a una lista de PlanificacionBean</p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos PlanificacionBean
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	protected List<PlanificacionBean> getListPlanificacionBean(List<TblPlanificaciones> lista) throws BusinessException {
	
		List<PlanificacionBean> result = new ArrayList<>();
		
		if (lista!=null && !lista.isEmpty()) {
			for (TblPlanificaciones p : lista) {
				PlanificacionBean planificacion = null;
				planificacion = getPlanificacionBean(p);
				result.add(planificacion);
			}
		}
			
		return result;
	}
	
	
	////MIGRADO
	/**
	 * <p>Obtenemos un objeto PlanificacionBean a partir de un objeto PlanificacionJPA</p>.
	 *
	 * @param viewPlanificacion the view planificacion
	 * @return objeto PlanificacionBean
	 * @throws BusinessException the business exception
	 */
	protected PlanificacionBean getPlanificacionBean(ViewPlanificaciones viewPlanificacion) throws BusinessException {
		PlanificacionBean planificacion = new PlanificacionBean();
		
		try {
			viewPlanificacion.reverseParseDias();
			Integer defaultIntegerValue=null;
			IntegerConverter intergerConverter = new IntegerConverter(defaultIntegerValue);
			ConvertUtils.register(intergerConverter, java.lang.Integer.class);
			BeanUtils.copyProperties(planificacion, viewPlanificacion);
		} catch (IllegalAccessException |  InvocationTargetException e) {
			throw new BusinessException(e);
		}
		
		return planificacion;
	}
	
	/**
	 * <p>Convertirmos una lista de ViewPlanificacionJPA a una lista de PlanificacionBean</p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos PlanificacionBean
	 * @throws BusinessException the business exception
	 */
	
	//////MIGRADO
	protected List<PlanificacionBean> getListViewPlanificacionBean(List<ViewPlanificaciones> lista) throws BusinessException {
	
		List<PlanificacionBean> result = null;
		
		if (lista!=null && !lista.isEmpty()) {
			result = new ArrayList<>();
		
			for (ViewPlanificaciones vp : lista) {
				PlanificacionBean planificacion =  new PlanificacionBean();
				
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					vp.reverseParseDias();
					Integer defaultIntegerValue=null;
					IntegerConverter intergerConverter = new IntegerConverter(defaultIntegerValue);
					ConvertUtils.register(intergerConverter, java.lang.Integer.class);
					BeanUtils.copyProperties(planificacion, vp);
				} catch (IllegalAccessException | InvocationTargetException e) {
					logger.error("ServicioPlanificacionImpl - getListViewPlanificacionBean:" + e);
					throw new BusinessException(e);
				} 
			
				result.add(planificacion);
			}
		}
			
		return result;
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion#getPlanificacionesByServidorId(java.lang.Integer)
	 */
	///////MIGRADO
	@Override
	public List<PlanificacionBean> getPlanificacionesByServidorId(Integer servidorId) throws BusinessException {
		try {
			List<ViewPlanificaciones> lista = viewPlanificacionesManager.getPlanificacionesServidor(servidorId.longValue());
			return getListViewPlanificacionBean(lista);	
		} catch (Exception e) {
			logger.error("ServicioPlanificacionImpl - getPlanificacionesByServidorId:" + e);
			throw new BusinessException(e, ERRORSDOTORGANI0);
		}		
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion#getPlanificacionesByServicioID(java.lang.Integer)
	 */
	////MIGRADO
	@Override
	public List<PlanificacionBean> getPlanificacionesByServicioID(Integer servicioId) throws BusinessException {
		try {
			TblPlanificacionesQuery query = new TblPlanificacionesQuery();
			TblServiciosQuery queryServicio = new TblServiciosQuery();
			queryServicio.setServicioid(servicioId.longValue());
			query.setTblServicios(queryServicio);
			query.setEliminadoIsNull(true);
			
			List<TblPlanificaciones> lista = tblPlanificacionesManager.getPlanificacionesByQuery(query); 
			
			return getListPlanificacionBean(lista);	
		} catch (Exception e) {
			logger.error("ServicioPlanificacionImpl - getPlanificacionesByServicioID:" + e);
			throw new BusinessException(e, ERRORSDOTORGANI0);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion#getPlanificacionesByOrganismoID(java.lang.Integer)
	 */
	////MIGRADO
	@Override
	public List<PlanificacionBean> getPlanificacionesByOrganismoID(
			Integer organismoId) throws BusinessException {
		try{
			TblPlanificacionesQuery query = new TblPlanificacionesQuery();
			query.setOrganismoid(organismoId.longValue());
			query.setEliminadoIsNull(true);
			
			List<TblPlanificaciones> lista = tblPlanificacionesManager.getPlanificacionesByQuery(query); 
			
			return getListPlanificacionBean(lista);	
		} catch (Exception e) {
			logger.error("ServicioPlanificacionImpl - getPlanificacionesByOrganismoID:" + e);
			throw new BusinessException(e, ERRORSDOTORGANI0);
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion#validaPlanificacionOptimaOrganismo(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	////MIGRADO
	@Override
	public int validaPlanificacionOptimaOrganismo(String planificacionId, Integer tipo, Integer servidorId, Integer servicioId, String lunes, String martes, String miercoles, String jueves, String viernes, String sabado, String domingo, String horaHasta, String horaDesde,
			Integer organismoId) throws BusinessException {
		Integer solapado = null;
		Long planId = null;
		try{
			planId = Long.parseLong(planificacionId);
		}catch(Exception e)	{}
		
		try{
			if (null != servicioId && servicioId > 0){
				if ((null == solapado || solapado == 1) && TRUE.equals(lunes)){
					solapado = queryExecutorViewPlanificaciones.countPlanificacionPorHorasOrganismo(organismoId, L, planId, horaHasta, horaDesde, servicioId, servidorId, tipo);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(martes)){
					solapado = queryExecutorViewPlanificaciones.countPlanificacionPorHorasOrganismo(organismoId, M, planId, horaHasta, horaDesde, servicioId, servidorId, tipo);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(miercoles)){
					solapado = queryExecutorViewPlanificaciones.countPlanificacionPorHorasOrganismo(organismoId, X, planId, horaHasta, horaDesde, servicioId, servidorId, tipo);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(jueves)){
					solapado = queryExecutorViewPlanificaciones.countPlanificacionPorHorasOrganismo(organismoId, J, planId, horaHasta, horaDesde, servicioId, servidorId, tipo);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(viernes)){
					solapado = queryExecutorViewPlanificaciones.countPlanificacionPorHorasOrganismo(organismoId, V, planId, horaHasta, horaDesde, servicioId, servidorId, tipo);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(sabado)){
					solapado = queryExecutorViewPlanificaciones.countPlanificacionPorHorasOrganismo(organismoId, S, planId, horaHasta, horaDesde, servicioId, servidorId, tipo);
				}
				if ((null == solapado || solapado == 1) && TRUE.equals(domingo)){
					solapado = queryExecutorViewPlanificaciones.countPlanificacionPorHorasOrganismo(organismoId, D, planId, horaHasta, horaDesde, servicioId, servidorId, tipo);
				}
			}
			
			
		}catch (Exception e) {
			logger.error(SERVICIOPLANIFI0, e);
			throw new BusinessException(e,ERRORSDOTORGANI);
		}
		return solapado;
	}
	

}
