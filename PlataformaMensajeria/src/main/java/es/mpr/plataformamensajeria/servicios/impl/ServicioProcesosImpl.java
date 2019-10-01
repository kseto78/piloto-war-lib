package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.minhap.common.entity.OrderType;
import es.minhap.common.entity.TextComparator;
import es.minhap.plataformamensajeria.iop.beans.PdpDiputacionesBean;
import es.minhap.plataformamensajeria.iop.beans.OrganismosServicioBean;
import es.minhap.plataformamensajeria.iop.beans.ProcesosBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorPdpDiputaciones;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorProcesos;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblPdpDiputacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblOrganismosServicioManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblProcesosManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosAplicacionesManager;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblPdpDiputaciones;
import es.minhap.sim.model.TblProcesos;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblUsuariosAplicaciones;
import es.minhap.sim.query.TblAplicacionesQuery;
import es.minhap.sim.query.TblPdpDiputacionesQuery;
import es.minhap.sim.query.TblOrganismosServicioQuery;
import es.minhap.sim.query.TblProcesosQuery;
import es.minhap.sim.query.TblServiciosQuery;
import es.minhap.sim.query.TblUsuariosAplicacionesQuery;
import es.minhap.sim.query.TblUsuariosQuery;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPdpDiputaciones;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesos;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Maneja la persistencia y b&uacute;squeda de aplicaciones a traves de JPA.
 * 
 * @author
 * 
 */
@Service("servicioProcesosImpl")
public class ServicioProcesosImpl implements ServicioProcesos {

	/** Constante ERRORS_ORGANISMO_GET_ORGANISMOS. */
	private static final String ERRORS_EJECUCIONJOBS_GET_PROCESOS = "errors.ejecucionjobs.getProcesos";

	/**  logger. */
	private static Logger logger = Logger
			.getLogger(ServicioProcesosImpl.class);

	/**  tbl organismos manager. */
	@Resource(name = "TblPdpDiputacionesManagerImpl")
	private TblPdpDiputacionesManager tblPdpDiputacionesManager;
	
	/**  tbl procesos manager. */
	@Resource(name = "TblProcesosManagerImpl")
	private TblProcesosManager tblProcesosManager;

	/**  tbl usuarios planificaciones manager. */
	@Resource(name = "TblUsuariosAplicacionesManagerImpl")
	private TblUsuariosAplicacionesManager tblUsuariosPlanificacionesManager;

	/**  tbl aplicaciones manager. */
	@Resource(name = "TblAplicacionesManagerImpl")
	private TblAplicacionesManager tblAplicacionesManager;

	/**  tbl servicios manager. */
	@Resource(name = "TblServiciosManagerImpl")
	private TblServiciosManager tblServiciosManager;

	/**  tbl organismos servicio manager. */
	@Resource(name = "tblOrganismosServicioManagerImpl")
	private TblOrganismosServicioManager tblOrganismosServicioManager;

	/**  query executor organismos impl. */
	@Resource(name = "queryExecutorPdpDiputacionesImpl")
	private QueryExecutorPdpDiputaciones queryExecutorOrganismosPdpImpl;
	
	/**  query executor organismos impl. */
	@Resource(name = "queryExecutorProcesosImpl")
	private QueryExecutorProcesos queryExecutorProcesosImpl;

	

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#getOrganismos(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.OrganismoBean)
	 */
	// //MIGRADO
	@Override
	public PaginatedList<ProcesosBean> getProcesos(int size,
			String order, String columnSort, ProcesosBean criterio)
			throws BusinessException {

		try {
			// Columna para ordenar
			HashMap<String, String> columns = new HashMap<>();
			columns.put("1", "nombre");
			columns.put("2", "inicioUltimaEjecucion");
			columns.put("3", "finUltimaEjecucion");
			columns.put("4", "estado");
			columns.put("5", "proximaEjecucion");
			columns.put("6", "parametro1");
			columns.put("7", "activo");

			String column = columns.get(columnSort);
			if (column == null) {
				column = "nombre";
			}

			es.minhap.plataformamensajeria.iop.beans.ProcesosBean ob = new es.minhap.plataformamensajeria.iop.beans.ProcesosBean();
			ob = createProcesosBean(criterio, ob);
			List<TblProcesos> lista = queryExecutorProcesosImpl
					.getProcesosPaginado(size, order, column, ob);
			
			for(TblProcesos proceso : lista){	
				if(proceso.getProximaEjecucion() != null ){
					if(proceso.getActivo() == false) proceso.setProximaEjecucion("");
						else{
							CronExpression cronExp = new CronExpression(proceso.getProximaEjecucion());
							Calendar fecha_ejecucion = Calendar.getInstance();
							Date fecha_siguiente_ejecucion = fecha_ejecucion.getTime();
							cronExp.getNextValidTimeAfter(fecha_siguiente_ejecucion);
							SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							proceso.setProximaEjecucion(format.format(cronExp.getNextValidTimeAfter(fecha_siguiente_ejecucion)));
						}
					
				}
			}
			List<ProcesosBean> pageList = getListProcesosBean(lista);

			// Total de organismos
			Integer rowcount = queryExecutorProcesosImpl
					.countProcesosPaginado(ob);

			PaginatedList<ProcesosBean> result = new PaginatedList<>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			logger.error("ServicioProcesosImpl - getProcesos:" + e);
			throw new BusinessException(e,
					ServicioProcesosImpl.ERRORS_EJECUCIONJOBS_GET_PROCESOS);

		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#newOrganismo(es.mpr.plataformamensajeria.beans.OrganismoBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	// //MIGRADO
//	@Override
//	@Transactional
//	public Integer newOrganismoPdpDiputacion(PdpDiputacionesBean organismoPdp, String source,
//			String accion, Long accionId) throws BusinessException {
//		try {
//			TblPdpDiputaciones organismoTO = getOrganismoPdpTO(organismoPdp);
//
//			organismoTO.setFechacreacion(new Date());
//			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado()
//					.getNombreCompleto();
//			organismoTO.setCreadopor(modificador);
//
//			Long idOrganismo = tblPdpDiputacionesManager.insert(organismoTO, source,
//					accion, accionId);		
//			organismoPdp.setFechacreacion(organismoTO.getFechacreacion());
//			organismoPdp.setCreadopor(organismoTO.getCreadopor());
//			organismoPdp.setPdpDiputacionesId(idOrganismo.intValue());
//			return organismoPdp.getPdpDiputacionesId();
//		} catch (Exception e) {
//			logger.error("ServicioOrganismoImpl - newOrganismo:" + e);
//			BusinessException exc = new BusinessException(e,
//					"errors.organismo.newOrganismo");
//			exc.mRechargeInput();
//			throw exc;
//		}
//	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#updateOrganismo(es.mpr.plataformamensajeria.beans.OrganismoBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	// //MIGRADO
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateProceso(ProcesosBean proceso, String source, String accion, Long accionId) throws BusinessException {
		
		try {
			TblProcesos procesosTO = getProcesosTO(proceso);
			procesosTO.setFechaModificacion(new Date());
			
			procesosTO.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			tblProcesosManager.update(procesosTO, source, accion, accionId);
			
		}
		catch (Exception e){
			logger.error("ServicioProcesosImpl - updateProceso:" + e);
			throw new BusinessException(e,"errors.ejecucionjobs.updateProceso");		
		} 
		
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProceso#loadProceso(es.mpr.plataformamensajeria.beans.ProcesosBean)
	 */
	// //MIGRADO
	@Override
	@Transactional
	public ProcesosBean loadProceso(ProcesosBean proceso)
			throws BusinessException {
		try {
			TblProcesos procesoTO = tblProcesosManager.getProcesoById(proceso.getProcesosId().longValue());
			return getProcesosBean(procesoTO);
		} catch (Exception e) {
			logger.error("ServicioProcesosImpl - loadProceso:" + e);
			throw new BusinessException(e, "errors.action.ejecucionjobs.loadProcesoServicio");
		}
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProceso#loadProceso(es.mpr.plataformamensajeria.beans.ProcesosBean)
	 */
	// //MIGRADO
	@Override
	@Transactional
	public ProcesosBean loadProcesoNombreClase(ProcesosBean proceso)
			throws BusinessException {
		try {
			TblProcesosQuery query = new TblProcesosQuery();
			query.setNombreClase(proceso.getNombreClase());
			List<TblProcesos> procesoTO = tblProcesosManager.getProcesosByQuery(query);
			return getProcesosBean(procesoTO.get(0));
		} catch (Exception e) {
			logger.error("ServicioProcesosImpl - loadProceso:" + e);
			throw new BusinessException(e, "errors.action.ejecucionjobs.loadProcesoServicio");
		}
	}
	

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#listAutocomplete(java.lang.String)
	 */
	@Override
	public List<String> listAutocomplete(String term) {

		try {
			return queryExecutorOrganismosPdpImpl.getListAutocomplete(term);

		} catch (Exception e) {
			logger.error("ServicioOrganismoImpl - autocomplete organismos:" + e);
			return new ArrayList<>();
		}

	}

	
	/**
	 * <p>
	 * Obtenemos un objeto TblOrganismos a partir de un objeto OrganismoBean
	 * </p>.
	 *
	 * @param organismoPdp the organismo
	 * @return objeto TblOrganismos
	 */
	// ///MIGRADO
	protected TblProcesos getProcesosTO(ProcesosBean proceso) {

		TblProcesos procesoTO = new TblProcesos();

		try {
			Date defaultValue = null;
			DateConverter converter = new DateConverter(defaultValue);
			Long defaultLongValue = null;
			LongConverter longConverter = new LongConverter(defaultLongValue);
			Integer defaultIntegerValue = null;
			IntegerConverter integerConverter = new IntegerConverter(
					defaultIntegerValue);
			ConvertUtils.register(longConverter, java.lang.Long.class);
			ConvertUtils.register(integerConverter, java.lang.Integer.class);
			ConvertUtils.register(converter, java.util.Date.class);
			BeanUtils.copyProperties(procesoTO, proceso);
			procesoTO
					.setProcesosid((null != proceso.getProcesosId()) ? proceso
							.getProcesosId().longValue() : null);
			procesoTO.setInicioUltimaEjecucion(proceso.getInicioUltimaEjecucion());
			procesoTO.setFinUltimaEjecucion(proceso.getFinUltimaEjecucion());
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioProcesosImpl - getProcesosTO:" + e);
		}

		return procesoTO;
	}

	/**
	 * <p>
	 * Convertirmos una lista de AplicacionJPA a una lista de ServidoresBean
	 * </p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos OrganismoBean
	 * @throws BusinessException the business exception
	 */
	// //MIGRADO
	protected List<ProcesosBean> getListProcesosBean(List<TblProcesos> lista)
			throws BusinessException {
		List<ProcesosBean> result = null;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();

			for (TblProcesos o : lista) {
				ProcesosBean proceso = new ProcesosBean();

				try {
					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					Long defaultLongValue = null;
					LongConverter longConverter = new LongConverter(
							defaultLongValue);
					Integer defaultIntegerValue = null;
					IntegerConverter integerConverter = new IntegerConverter(
							defaultIntegerValue);
					ConvertUtils.register(longConverter, java.lang.Long.class);
					ConvertUtils.register(integerConverter,
							java.lang.Integer.class);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(proceso, o);
					proceso.setProcesosId(o.getProcesosid().intValue());		
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(proceso);
			}
		}

		return result;
	}

	/**
	 * <p>
	 * Obtenemos un objeto OrganismoBean a partir de un objeto TblOrganismos
	 * </p>.
	 *
	 * @param o the o
	 * @return objeto OrganismoBean
	 * @throws BusinessException the business exception
	 * @throws ParseException 
	 */
	// /MIGRADO
	protected ProcesosBean getProcesosBean(TblProcesos o)
			throws BusinessException, ParseException {
		ProcesosBean proceso = new ProcesosBean();

		try {
			Date defaultValue = null;
			DateConverter converter = new DateConverter(defaultValue);
			Long defaultLongValue = null;
			LongConverter longConverter = new LongConverter(defaultLongValue);
			Integer defaultIntegerValue = null;
			IntegerConverter integerConverter = new IntegerConverter(
					defaultIntegerValue);
			ConvertUtils.register(longConverter, java.lang.Long.class);
			ConvertUtils.register(integerConverter, java.lang.Integer.class);
			ConvertUtils.register(converter, java.util.Date.class);
			BeanUtils.copyProperties(proceso, o);
			proceso.setProcesosId(o.getProcesosid().intValue());

		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioProcesosImpl - getProcesosBean:" + e);
		}
		return proceso;
	}

	/**
	 * Creates the organismo bean.
	 *
	 * @param criterio the criterio
	 * @param ob the ob
	 * @return the es.minhap.plataformamensajeria.iop.beans. organismo bean
	 */
	private es.minhap.plataformamensajeria.iop.beans.ProcesosBean createProcesosBean(
			ProcesosBean criterio,
			ProcesosBean ob) {
		try {
			if (null != criterio) {
				Date defaultValue = null;
				DateConverter converter = new DateConverter(defaultValue);
				Long defaultLongValue = null;
				LongConverter longConverter = new LongConverter(
						defaultLongValue);
				Integer defaultIntegerValue = null;
				IntegerConverter integerConverter = new IntegerConverter(
						defaultIntegerValue);
				ConvertUtils.register(longConverter, java.lang.Long.class);
				ConvertUtils
						.register(integerConverter, java.lang.Integer.class);
				ConvertUtils.register(converter, java.util.Date.class);
				BeanUtils.copyProperties(ob, criterio);				
			}

		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioProcesosImpl - createProcesosBean:" + e);
		}
		return ob;
	}


	@Override
	public List<ProcesosBean> getAllProcesos()
			throws BusinessException {
		try {
			TblProcesosQuery query = new TblProcesosQuery();
			
			
			List<TblProcesos> lista = tblProcesosManager
					.getProcesosByQuery(query);

			return getListProcesosBean(lista);
		} catch (Exception e) {
			logger.error("ServicioProcesosImpl - getAllProcesos:" + e);
			throw new BusinessException(e,
					ServicioProcesosImpl.ERRORS_EJECUCIONJOBS_GET_PROCESOS);
		}
	}
	
	


}
