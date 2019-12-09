package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorPdpDiputaciones;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblPdpDiputacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblOrganismosServicioManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosAplicacionesManager;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblPdpDiputaciones;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblUsuariosAplicaciones;
import es.minhap.sim.query.TblAplicacionesQuery;
import es.minhap.sim.query.TblPdpDiputacionesQuery;
import es.minhap.sim.query.TblOrganismosServicioQuery;
import es.minhap.sim.query.TblServiciosQuery;
import es.minhap.sim.query.TblUsuariosAplicacionesQuery;
import es.minhap.sim.query.TblUsuariosQuery;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPdpDiputaciones;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Maneja la persistencia y b&uacute;squeda de aplicaciones a traves de JPA.
 * 
 * @author
 * 
 */
@Service("servicioPdpDiputacionesImpl")
public class ServicioPdpDiputacionesImpl implements ServicioPdpDiputaciones {

	protected static final String NOMBRE = "nombre";

	protected static final String SERVICIOORGANIS = "ServicioOrganismoImpl - getOrganismoBean:";

	protected static final String SERVICIOORGANIS0 = "ServicioOrganismoImpl - getOrganismos:";

	protected static final String SERVICIOORGANIS1 = "ServicioOrganismoImpl - autocomplete organismos:";

	/** Constante ERRORS_ORGANISMO_GET_ORGANISMOS. */
	private static final String ERRORS_ORGANISMO_GET_ORGANISMOSPDP = "errors.organismo.getOrganismosPdp";

	/**  logger. */
	private static Logger logger = Logger
			.getLogger(ServicioPdpDiputacionesImpl.class);

	/**  tbl organismos manager. */
	@Resource(name = "TblPdpDiputacionesManagerImpl")
	private TblPdpDiputacionesManager tblPdpDiputacionesManager;

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

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#getOrganismos()
	 */
	// //MIGRADO
	@Override
	public List<PdpDiputacionesBean> getPdpDiputaciones() throws BusinessException {
		try {
			TblPdpDiputacionesQuery query = new TblPdpDiputacionesQuery();
			query.setEliminadoIsNull(true);
			query.addOrder(NOMBRE, OrderType.ASC);
			List<TblPdpDiputaciones> lista = tblPdpDiputacionesManager
					.getPdpDiputacionesByQuery(query);

			return getListOrganismoPdpBean(lista);
		} catch (Exception e) {
			logger.error(SERVICIOORGANIS0 + e);
			throw new BusinessException(e,
					ServicioPdpDiputacionesImpl.ERRORS_ORGANISMO_GET_ORGANISMOSPDP);
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#getOrganismos(java.lang.String, java.lang.Integer)
	 */
	// /MIGRADO
	@Override
	public List<Integer> getPdpDiputaciones(String rolUsuario, Integer idUsuario)
			throws BusinessException {
		List<Integer> res = new ArrayList<>();
		try {

			TblUsuariosAplicacionesQuery queryUsuariosAplicaciones = new TblUsuariosAplicacionesQuery();
			TblUsuariosQuery queryUsuarios = new TblUsuariosQuery();
			queryUsuarios.setUsuarioid(idUsuario.longValue());
			queryUsuariosAplicaciones.setTblUsuarios(queryUsuarios);
			List<TblUsuariosAplicaciones> listaUsuarioAplicaciones = tblUsuariosPlanificacionesManager
					.getUsuariosAplicacionesByQuery(queryUsuariosAplicaciones);

			if (null != listaUsuarioAplicaciones
					&& !listaUsuarioAplicaciones.isEmpty()) {

				TblAplicacionesQuery queryAplicacionesQuery = new TblAplicacionesQuery();
				for (TblUsuariosAplicaciones tblUsuariosAplicaciones : listaUsuarioAplicaciones) {
					queryAplicacionesQuery
							.addAplicacionidIn(tblUsuariosAplicaciones
									.getAplicacionid());
				}

				List<TblAplicaciones> listaAplicaciones = tblAplicacionesManager
						.getAplicaciones(queryAplicacionesQuery);

				TblServiciosQuery queryServicios = new TblServiciosQuery();
				for (TblAplicaciones aplicacion : listaAplicaciones) {
					queryServicios.addTblAplicacionesIdIn(aplicacion);
				}
				queryServicios.setEliminadoIsNull(true);
				List<TblServicios> listaServicios = tblServiciosManager
						.getServicios(queryServicios);

				if (null != listaServicios && !listaServicios.isEmpty()) {
					TblOrganismosServicioQuery queryOrganismoServicio = new TblOrganismosServicioQuery();

					for (TblServicios s : listaServicios) {
						queryOrganismoServicio.addTblServiciosIdIn(s);
					}
					List<OrganismosServicioBean> os = tblOrganismosServicioManager
							.getOrganismosServicioByQuery(queryOrganismoServicio);

					if (null != os && !os.isEmpty()) {
						for (OrganismosServicioBean organismosServicioBean : os) {
							res.add(organismosServicioBean.getOrganismoId());
						}
					}
					// eliminado posibles organismos duplicados
					Set<Integer> set = new HashSet<>();
					set.addAll(res);
					res = new ArrayList<>(set);
				}
			}

		} catch (Exception e) {
			logger.error("ServicioServicioImpl - getServicios:" + e);
			throw new BusinessException(e,
					ServicioPdpDiputacionesImpl.ERRORS_ORGANISMO_GET_ORGANISMOSPDP);
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#getOrganismos(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.OrganismoBean)
	 */
	// //MIGRADO
	@Override
	public PaginatedList<PdpDiputacionesBean> getOrganismosPdpDiputaciones(int start, int size,
			String order, String columnSort, PdpDiputacionesBean criterio)
			throws BusinessException {

		try {
			// Columna para ordenar
			HashMap<String, String> columns = new HashMap<>();
			columns.put("1", NOMBRE);
			columns.put("2", "descripcion");			

			String column = columns.get(columnSort);
			if (column == null) {
				column = NOMBRE;
			}

			es.minhap.plataformamensajeria.iop.beans.PdpDiputacionesBean ob = new es.minhap.plataformamensajeria.iop.beans.PdpDiputacionesBean();
			ob = createOrganismoPdpBean(criterio, ob);
			List<TblPdpDiputaciones> lista = queryExecutorOrganismosPdpImpl
					.getPdpDiputacionesPaginado(start, size, order, column, ob);

			List<PdpDiputacionesBean> pageList = getListOrganismoPdpBean(lista);

			// Total de organismos
			Integer rowcount = queryExecutorOrganismosPdpImpl
					.countPdpDiputacionesPaginado(ob);

			PaginatedList<PdpDiputacionesBean> result = new PaginatedList<>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			logger.error(SERVICIOORGANIS0 + e);
			throw new BusinessException(e,
					ServicioPdpDiputacionesImpl.ERRORS_ORGANISMO_GET_ORGANISMOSPDP);

		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#newOrganismo(es.mpr.plataformamensajeria.beans.OrganismoBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	// //MIGRADO
	@Override
	@Transactional
	public Integer newOrganismoPdpDiputacion(PdpDiputacionesBean organismoPdp, String source,
			String accion, Long accionId) throws BusinessException {
		try {
			TblPdpDiputaciones organismoTO = getOrganismoPdpTO(organismoPdp);

			organismoTO.setFechacreacion(new Date());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado()
					.getNombreCompleto();
			organismoTO.setCreadopor(modificador);

			Long idOrganismo = tblPdpDiputacionesManager.insert(organismoTO, source,
					accion, accionId);		
			organismoPdp.setFechacreacion(organismoTO.getFechacreacion());
			organismoPdp.setCreadopor(organismoTO.getCreadopor());
			organismoPdp.setPdpDiputacionesId(idOrganismo.intValue());
			return organismoPdp.getPdpDiputacionesId();
		} catch (Exception e) {
			logger.error("ServicioOrganismoImpl - newOrganismo:" + e);
			BusinessException exc = new BusinessException(e,
					"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#updateOrganismo(es.mpr.plataformamensajeria.beans.OrganismoBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	// //MIGRADO
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateOrganismoPdp(PdpDiputacionesBean organismoPdp, String source, String accion, Long accionId) throws BusinessException {
		
		try {
			TblPdpDiputaciones organismoPdpTO = getOrganismoPdpTO(organismoPdp);
			organismoPdpTO.setFechamodificacion(new Date());
			
			organismoPdpTO.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			tblPdpDiputacionesManager.update(organismoPdpTO, source, accion, accionId);
			
		} catch (Exception e){
			logger.error("ServicioOrganismoImpl - updateOrganismo:" + e);
			throw new BusinessException(e,"errors.organismo.updateOrganismo");		
		} 
		
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#loadOrganismo(es.mpr.plataformamensajeria.beans.OrganismoBean)
	 */
	// //MIGRADO
	@Override
	@Transactional
	public PdpDiputacionesBean loadOrganismoPdp(PdpDiputacionesBean organismoPdp)
			throws BusinessException {
		try {
			TblPdpDiputaciones organismoPdpTO = tblPdpDiputacionesManager.getPdpDiputacionesById(organismoPdp.getPdpDiputacionesId().longValue());
			return getOrganismoPdpBean(organismoPdpTO);
		} catch (Exception e) {
			logger.error("ServicioPdpDiputacionesImpl - loadOrganismoPdp:" + e);
			throw new BusinessException(e, "errors.organismo.loadOrganismoPdp");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#deleteOrganismo(java.lang.Long, java.lang.String, java.lang.String, java.lang.Long)
	 */
	// /MIGRADO
	@Override
	@Transactional
	public void deleteOrganismoPdp(Long organismoPdpId, String source, String accion,
			Long accionId) throws BusinessException {
		try {
			TblPdpDiputaciones o = tblPdpDiputacionesManager
					.getPdpDiputacionesById(organismoPdpId);

			o.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado()
					.getNombreCompleto());
			o.setFechamodificacion(new Date());
			o.setEliminado("S");			
			tblPdpDiputacionesManager.update(o, source, accion, accionId);
		} catch (Exception e) {
			logger.error("ServicioOrganismoImpl - deleteOrganismo:" + e);
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
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
			logger.error(SERVICIOORGANIS1 + e);
			return new ArrayList<>();
		}

	}

	
	@Override
	public List<String> getOrganismosHijos(String search) {
		try {
			return queryExecutorOrganismosPdpImpl.getOrganismosHijos(search);

		} catch (Exception e) {
			logger.error(SERVICIOORGANIS1 + e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#existeOrganimo(es.mpr.plataformamensajeria.beans.OrganismoBean)
	 */
	@Override
	public Boolean existeOrganimo(PdpDiputacionesBean organismoPdp) {
		try {
			TblPdpDiputacionesQuery query = new TblPdpDiputacionesQuery();
			query.setNombre(organismoPdp.getNombre());
			query.setNombreComparator(TextComparator.IEQUALS);			
			List<TblPdpDiputaciones> lista = tblPdpDiputacionesManager
					.getPdpDiputacionesByQuery(query);

			return null != lista && !lista.isEmpty();

		} catch (Exception e) {
			logger.error("ServicioOrganismoImpl - autocomplete organismosPdp:" + e);
			return false;
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
	protected TblPdpDiputaciones getOrganismoPdpTO(PdpDiputacionesBean organismoPdp) {

		TblPdpDiputaciones organismoPdpTO = new TblPdpDiputaciones();

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
			BeanUtils.copyProperties(organismoPdpTO, organismoPdp);
			organismoPdpTO
					.setPdpDiputacionesId((null != organismoPdp.getPdpDiputacionesId()) ? organismoPdp
							.getPdpDiputacionesId().longValue() : null);
			organismoPdpTO.setFechacreacion(organismoPdp.getFechacreacion());
			organismoPdpTO.setFechamodificacion(organismoPdp.getFechamodificacion());
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioOrganismoImpl - getOrganismoTO:" + e);
		}

		return organismoPdpTO;
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
	protected List<PdpDiputacionesBean> getListOrganismoPdpBean(List<TblPdpDiputaciones> lista)
			throws BusinessException {
		List<PdpDiputacionesBean> result = null;
		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();

			for (TblPdpDiputaciones o : lista) {
				PdpDiputacionesBean organismoPdp = new PdpDiputacionesBean();

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
					BeanUtils.copyProperties(organismoPdp, o);
					organismoPdp.setPdpDiputacionesId(o.getPdpDiputacionesId().intValue());		
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(organismoPdp);
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
	 */
	// /MIGRADO
	protected PdpDiputacionesBean getOrganismoPdpBean(TblPdpDiputaciones o)
			throws BusinessException {
		PdpDiputacionesBean organismo = new PdpDiputacionesBean();

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
			BeanUtils.copyProperties(organismo, o);
			organismo.setPdpDiputacionesId(o.getPdpDiputacionesId().intValue());
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(SERVICIOORGANIS + e);
		}
		return organismo;
	}

	/**
	 * Creates the organismo bean.
	 *
	 * @param criterio the criterio
	 * @param ob the ob
	 * @return the es.minhap.plataformamensajeria.iop.beans. organismo bean
	 */
	private es.minhap.plataformamensajeria.iop.beans.PdpDiputacionesBean createOrganismoPdpBean(
			PdpDiputacionesBean criterio,
			PdpDiputacionesBean ob) {
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
			logger.error(SERVICIOORGANIS + e);
		}
		return ob;
	}


}
