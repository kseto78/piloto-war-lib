package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.minhap.plataformamensajeria.iop.beans.ProcesosManualesBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorProcesosManuales;
import es.minhap.plataformamensajeria.iop.manager.TblProcesosManualesManager;
import es.minhap.sim.model.TblProcesosManuales;
import es.minhap.sim.model.ViewProcesosManuales;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesosManuales;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Maneja la persistencia y b&uacute;squeda de aplicaciones a traves de JPA.
 * 
 * @author
 * 
 */
@Service("servicioProcesosManualesImpl")
public class ServicioProcesosManualesImpl implements ServicioProcesosManuales {

	protected static final String NOMBRE = "nombre";

	/** Constante ERRORS_ORGANISMO_GET_ORGANISMOS. */
	private static final String ERRORS_EJECUCIONJOBS_GET_PROCESOS = "errors.ejecucionjobs.getProcesos";

	/**  logger. */
	private static Logger logger = Logger
			.getLogger(ServicioProcesosManualesImpl.class);


	/**  tbl organismos manager. */
	@Resource(name = "TblProcesosManualesManagerImpl")
	private TblProcesosManualesManager tblProcesosManualesManager;	
	
	/**  query executor organismos impl. */
	@Resource(name = "queryExecutorProcesosManualesImpl")
	private QueryExecutorProcesosManuales queryExecutorProcesosManualesImpl;



	

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#getOrganismos(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.OrganismoBean)
	 */
	// //MIGRADO
	@Override
	public PaginatedList<ProcesosManualesBean> getProcesosManuales(int inicio, int size,
			String order, String columnSort, ProcesosManualesBean criterio)
			throws BusinessException {

		try {
			// Columna para ordenar
			HashMap<String, String> columns = new HashMap<>();
			columns.put("1", NOMBRE);
			columns.put("2", "job");
			

			String column = columns.get(columnSort);
			if (column == null) {
				column = NOMBRE;
			}

			es.minhap.plataformamensajeria.iop.beans.ProcesosManualesBean ob = new es.minhap.plataformamensajeria.iop.beans.ProcesosManualesBean();
			ob = createProcesosManualesBean(criterio, ob);
			List<ViewProcesosManuales> lista = queryExecutorProcesosManualesImpl
					.getProcesosManualesPaginado(inicio, size, order, column, ob);
//			
			List<ProcesosManualesBean> pageList = getListProcesosManualesBean(lista);

			// Total de organismos
			Integer rowcount = queryExecutorProcesosManualesImpl
					.countProcesosManualesPaginado(ob);

			PaginatedList<ProcesosManualesBean> result = new PaginatedList<>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			logger.error("ServicioProcesosManualesImpl - getProcesosManuales:" + e);
			throw new BusinessException(e,
					ServicioProcesosManualesImpl.ERRORS_EJECUCIONJOBS_GET_PROCESOS);

		}
	}
	
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesosManuales#loadProcesoManual(es.mpr.plataformamensajeria.beans.ProcesosBean)
	 */
	// //MIGRADO
	@Override
	@Transactional
	public ProcesosManualesBean loadProcesoManual(ProcesosManualesBean procesoManual)
			throws BusinessException {
		try {
			TblProcesosManuales procesoManualTO = tblProcesosManualesManager.getProcesoManualById(procesoManual.getProcesosManualesId().longValue());
			return getProcesosManualesBean(procesoManualTO);
		} catch (Exception e) {
			logger.error("ServicioProcesosImpl - loadProceso:" + e);
			throw new BusinessException(e, "errors.action.ejecucionjobs.loadProcesoServicio");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#deleteOrganismo(java.lang.Long, java.lang.String, java.lang.String, java.lang.Long)
	 */
	// /MIGRADO
	@Override
	@Transactional
	public void deleteProcesoManual(Long procesoManualId, String source, String accion,
			Long accionId) throws BusinessException {
		try {
			TblProcesosManuales o = tblProcesosManualesManager.getProcesoManualById(procesoManualId);

			o.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado()
					.getNombreCompleto());
			o.setFechaModificacion(new Date());
			o.setEliminado("S");	
			tblProcesosManualesManager.update(o, source, accion, accionId);			
		} catch (Exception e) {
			logger.error("ServicioOrganismoImpl - deleteOrganismo:" + e);
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateProcesoManual(ProcesosManualesBean procesoManual, String source, String accion, Long accionId) throws BusinessException {
		
		try {
			TblProcesosManuales procesosManualesTO = getProcesosManualesTO(procesoManual);
			procesosManualesTO.setFechaModificacion(new Date());
			
			procesosManualesTO.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			tblProcesosManualesManager.update(procesosManualesTO, source, accion, accionId);
			
		} catch (Exception e){
			logger.error("ServicioProcesosImpl - updateProceso:" + e);
			throw new BusinessException(e,"errors.ejecucionjobs.updateProceso");		
		} 
		
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProceso#loadProceso(es.mpr.plataformamensajeria.beans.ProcesosBean)
	 */
	// //MIGRADO
//	@Override
//	@Transactional
//	public ProcesosBean loadProceso(ProcesosBean proceso)

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#deleteOrganismo(java.lang.Long, java.lang.String, java.lang.String, java.lang.Long)
	 */
	// /MIGRADO
//	@Override
//	@Transactional
//	public void deleteOrganismoPdp(Long organismoPdpId, String source, String accion,
//			TblPdpDiputaciones o = tblPdpDiputacionesManager
//
////			o.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado()


	
	/**
	 * <p>
	 * Obtenemos un objeto TblOrganismos a partir de un objeto OrganismoBean
	 * </p>.
	 *
	 * @param organismoPdp the organismo
	 * @return objeto TblOrganismos
	 */
	// ///MIGRADO
	protected TblProcesosManuales getProcesosManualesTO(ProcesosManualesBean procesoManual) {

		TblProcesosManuales procesoManualTO = new TblProcesosManuales();

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
			BeanUtils.copyProperties(procesoManualTO, procesoManual);
			procesoManualTO
					.setProcesosManualesId((null != procesoManual.getProcesosManualesId()) ? procesoManual
							.getProcesosManualesId().longValue() : null);
			procesoManualTO.setFechaCreacion(procesoManual.getFechaCreacion());
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioProcesosManualesImpl - getProcesosManualesTO:" + e);
		}

		return procesoManualTO;
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
	protected List<ProcesosManualesBean> getListProcesosManualesBean(List<ViewProcesosManuales> lista)
			throws BusinessException {
		List<ProcesosManualesBean> result = null;
		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();

			for (ViewProcesosManuales o : lista) {
				ProcesosManualesBean proceso = new ProcesosManualesBean();

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
					proceso.setProcesosManualesId(o.getProcesosManualesId().intValue());		
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
	protected ProcesosManualesBean getProcesosManualesBean(TblProcesosManuales o)
			throws BusinessException, ParseException {
		ProcesosManualesBean procesoManual = new ProcesosManualesBean();

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
			BeanUtils.copyProperties(procesoManual, o);
			procesoManual.setProcesosManualesId(o.getProcesosManualesId().intValue());			
			
			
			

		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioProcesosManualesImpl - getProcesosManualesBean:" + e);
		}
		return procesoManual;
	}

	/**
	 * Creates the organismo bean.
	 *
	 * @param criterio the criterio
	 * @param ob the ob
	 * @return the es.minhap.plataformamensajeria.iop.beans. organismo bean
	 */
	private es.minhap.plataformamensajeria.iop.beans.ProcesosManualesBean createProcesosManualesBean(
			ProcesosManualesBean criterio,
			ProcesosManualesBean ob) {
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
			logger.error("ServicioProcesoManualesImpl - createProcesosManualesBean:" + e);
		}
		return ob;
	}


	@Override
	public Integer newProcesoManual(ProcesosManualesBean procesoManual,
			String source, String accion, Long accionId)
			throws BusinessException {
			
		try {
			TblProcesosManuales procesoManualTo = getProcesosManualesTO(procesoManual);

			procesoManualTo.setFechaCreacion(new Date());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado()
					.getNombreCompleto();
			procesoManualTo.setCreadoPor(modificador);

			Long idOrganismo = tblProcesosManualesManager.insert(procesoManualTo, source,
					accion, accionId);			
						
			return idOrganismo.intValue();
		} catch (Exception e) {
			logger.error("ServicioProceosManualesImpl - newProcesoManual:" + e);
			BusinessException exc = new BusinessException(e,
					"errors.action.ejecucionjobs.newProcesoManual");
			exc.mRechargeInput();
			throw exc;
		}
	}


	
	


}
