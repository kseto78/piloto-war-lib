package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
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
import es.minhap.plataformamensajeria.iop.beans.OrganismosServicioBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorOrganismos;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblOrganismosManager;
import es.minhap.plataformamensajeria.iop.manager.TblOrganismosServicioManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresOrganismosManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosAplicacionesManager;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblUsuariosAplicaciones;
import es.minhap.sim.query.TblAplicacionesQuery;
import es.minhap.sim.query.TblOrganismosQuery;
import es.minhap.sim.query.TblOrganismosServicioQuery;
import es.minhap.sim.query.TblServiciosQuery;
import es.minhap.sim.query.TblServidoresOrganismosQuery;
import es.minhap.sim.query.TblUsuariosAplicacionesQuery;
import es.minhap.sim.query.TblUsuariosQuery;
import es.mpr.plataformamensajeria.beans.OrganismoBean;
import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Maneja la persistencia y b&uacute;squeda de aplicaciones a traves de JPA.
 * 
 * @author
 * 
 */
@Service("servicioOrganismoImpl")
public class ServicioOrganismoImpl implements ServicioOrganismo {

	protected static final String NOMBRE = "nombre";

	protected static final String SERVICIOORGANIS = "ServicioOrganismoImpl - getOrganismoBean:";

	protected static final String SERVICIOORGANIS0 = "ServicioOrganismoImpl - getOrganismos:";

	protected static final String ERRORSDOTORGANI = "errors.organismo.updateOrganismo";

	protected static final String SERVICIOORGANIS1 = "ServicioOrganismoImpl - updateOrganismo:";

	protected static final String DIR3 = "dir3";

	protected static final String R_CONST_REF = "1";

	protected static final String R_CONST_0 = "2";

	protected static final String ALTA_MASIVA_APL = "ALTA_MASIVA_APLICACION";

	protected static final String SERVICIOORGANIS2 = "ServicioOrganismoImpl - autocomplete organismos:";

	/** Constante ERRORS_ORGANISMO_GET_ORGANISMOS. */
	private static final String ERRORS_ORGANISMO_GET_ORGANISMOS = "errors.organismo.getOrganismos";

	/**  logger. */
	private static Logger logger = Logger
			.getLogger(ServicioOrganismoImpl.class);

	/**  tbl organismos manager. */
	@Resource(name = "TblOrganismosManagerImpl")
	private TblOrganismosManager tblOrganismosManager;

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
	@Resource(name = "queryExecutorOrganismosImpl")
	private QueryExecutorOrganismos queryExecutorOrganismosImpl;
	
	/**  tbl organismos servicio manager. */
	@Resource(name = "TblServidoresManagerImpl")
	private TblServidoresManager tblServidoresManager;	

	/**  tbl servidores organismos manager. */
	@Resource(name = "tblServidoresOrganismosManagerImpl")
	private TblServidoresOrganismosManager tblServidoreOrganismosManager;
	

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#getOrganismos()
	 */
	// //MIGRADO
	@Override
	public List<OrganismoBean> getOrganismos() throws BusinessException {
		try {
			TblOrganismosQuery query = new TblOrganismosQuery();
			query.setEliminadoIsNull(true);
			query.addOrder(NOMBRE, OrderType.ASC);
			List<TblOrganismos> lista = tblOrganismosManager
					.getOrganismosByQuery(query);

			return getListOrganismoBean(lista);
		} catch (Exception e) {
			logger.error(SERVICIOORGANIS0 + e);
			throw new BusinessException(e,
					ServicioOrganismoImpl.ERRORS_ORGANISMO_GET_ORGANISMOS);
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#getOrganismos(java.lang.String, java.lang.Integer)
	 */
	// /MIGRADO
	@Override
	public List<Integer> getOrganismos(String rolUsuario, Integer idUsuario)
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
					ServicioOrganismoImpl.ERRORS_ORGANISMO_GET_ORGANISMOS);
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#getOrganismos(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.OrganismoBean)
	 */
	// //MIGRADO
	@Override
	public PaginatedList<OrganismoBean> getOrganismos(int start, int size,
			String order, String columnSort, OrganismoBean criterio)
			throws BusinessException {

		try {
			// Columna para ordenar
			HashMap<String, String> columns = new HashMap<>();
			columns.put(R_CONST_REF, DIR3);
			columns.put("3", NOMBRE);
			columns.put("4", "estado");
			columns.put("6", "codUnidadRaiz");

			String column = columns.get(columnSort);
			if (column == null) {
				column = DIR3;
			}

			es.minhap.plataformamensajeria.iop.beans.OrganismoBean ob = new es.minhap.plataformamensajeria.iop.beans.OrganismoBean();
			ob = createOrganismoBean(criterio, ob);
			List<TblOrganismos> lista = queryExecutorOrganismosImpl
					.getOrganismosPaginado(start, size, order, column, ob);

			List<OrganismoBean> pageList = getListOrganismoBean(lista);

			// Total de organismos
			Integer rowcount = queryExecutorOrganismosImpl
					.countOrganismosPaginado(ob);

			PaginatedList<OrganismoBean> result = new PaginatedList<>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			logger.error(SERVICIOORGANIS0 + e);
			throw new BusinessException(e,
					ServicioOrganismoImpl.ERRORS_ORGANISMO_GET_ORGANISMOS);

		}
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS#getProveedoresSMS(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.ProveedorSMSBean, int)
	 */
	////MIGRADO
	@Override
	public PaginatedList<OrganismoBean> getOrganismosProveedoresSMS(int start, int size,
			String order, String columnSort, ProveedorSMSBean criterio, int tipoServidor)
			throws BusinessException {
		String nombre = null;
		try {
			//Columna para ordenar
			Hashtable<String, String> columns = new Hashtable<>();
			columns.put(R_CONST_0,NOMBRE);
			if (columnSort==null) {
				columnSort = R_CONST_0;
			} 
				//Id
			
			String column = columns.get(columnSort);
			if (column==null) {
				column = NOMBRE;
			}
			
			TblServidoresOrganismosQuery queryServOrg = new TblServidoresOrganismosQuery();
			queryServOrg.setServidorid(criterio.getProveedorSMSId());

			List<Long> listaServOrganismos = tblServidoreOrganismosManager.getOrganismosServidorActivos(criterio.getProveedorSMSId());
			if(listaServOrganismos.isEmpty()){
				PaginatedList<OrganismoBean> result = new PaginatedList<>();
				result.setTotalList(0);
				List<OrganismoBean> pageList = new ArrayList<>();
				result.setPageList(pageList);
				return result;
			}
			TblOrganismosQuery queryOrg = new TblOrganismosQuery();

			for(Long servOrg: listaServOrganismos){
				queryOrg.addOrganismoidIn(servOrg);
			}
			
			queryOrg.setEliminadoIsNull(true);
			
			if(size!=-1){
				queryOrg.setMaxResults(size);
			}
			OrderType ord = null;
			if (order == null || R_CONST_REF.equals(order)){
				ord = OrderType.ASC;
			} else {
				ord = OrderType.DESC;
			}
				
			queryOrg.addOrder(column, ord);			
			queryOrg.setPrimerResultado(start);			

			List<TblOrganismos> lista = tblOrganismosManager.getOrganismosByQuery(queryOrg);
			List<OrganismoBean> pageList = getListOrganismoBean(lista);
			
			// Total de organismos

			Integer rowcount = queryExecutorOrganismosImpl.countServidoresPaginadoOrganismos(queryOrg);
			
			PaginatedList<OrganismoBean> result = new PaginatedList<>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);
			
			return result;
		} catch (Exception e){
			logger.error("ServicioProveedorSMS - getProveedoresSMS:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
			
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#newOrganismo(es.mpr.plataformamensajeria.beans.OrganismoBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	// //MIGRADO
	@Override
	@Transactional
	public Integer newOrganismo(OrganismoBean organismo, String source,
			String accion, Long accionId) throws BusinessException {
		try {
			TblOrganismos organismoTO = getOrganismoTO(organismo);

			organismoTO.setFechacreacion(new Date());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado()
					.getNombreCompleto();
			organismoTO.setCreadopor(modificador);

			Long idOrganismo = tblOrganismosManager.insert(organismoTO, source,
					accion, accionId);
			organismo.setActivo(organismoTO.getActivo());
			organismo.setFechacreacion(organismoTO.getFechacreacion());
			organismo.setCreadopor(organismoTO.getCreadopor());
			organismo.setOrganismoId(idOrganismo.intValue());
			return organismo.getOrganismoId();
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
	public void updateOrganismo(OrganismoBean organismo, String source, String accion, Long accionId) throws BusinessException {
		
		try {
			TblOrganismos organismoTO = getOrganismoTO(organismo);
			organismoTO.setFechamodificacion(new Date());
			if(organismo.getActivo()){
				organismoTO.setFechaActivo(new Date());
			}else{
				organismoTO.setFechaActivo(null);
			}
			organismoTO.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			if(ALTA_MASIVA_APL.equals(source)){
				organismoTO.setModificadopor(ALTA_MASIVA_APL);
			}
			tblOrganismosManager.update(organismoTO, source, accion, accionId);
			
		} catch (Exception e){
			logger.error(SERVICIOORGANIS1 + e);
			throw new BusinessException(e,ERRORSDOTORGANI);		
		} 
		
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#updateOrganismo(es.mpr.plataformamensajeria.beans.OrganismoBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	// //MIGRADO
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteOrganismoPdp(TblOrganismos org, String source, String accion, Long accionId) throws BusinessException {
		
		try {
			OrganismoBean organismo = getOrganismoBean(org);
			TblOrganismos organismoTO = getOrganismoTO(organismo);
			organismoTO.setFechamodificacion(new Date());
			if(organismo.getActivo()){
				organismoTO.setFechaActivo(new Date());
			}else{
				organismoTO.setFechaActivo(null);
			}
			organismoTO.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			if(ALTA_MASIVA_APL.equals(source)){
				organismoTO.setModificadopor(ALTA_MASIVA_APL);
			}
			organismoTO.setIdPdpDiputaciones(null);
			tblOrganismosManager.update(organismoTO, source, accion, accionId);
			
		} catch (Exception e){
			logger.error(SERVICIOORGANIS1 + e);
			throw new BusinessException(e,ERRORSDOTORGANI);		
		} 
		
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#loadOrganismo(es.mpr.plataformamensajeria.beans.OrganismoBean)
	 */
	// //MIGRADO
	@Override
	@Transactional
	public OrganismoBean loadOrganismo(OrganismoBean organismo)
			throws BusinessException {
		try {
			TblOrganismos organismoTO = tblOrganismosManager
					.getOrganismoById(organismo.getOrganismoId().longValue());
			return getOrganismoBean(organismoTO);
		} catch (Exception e) {
			logger.error("ServicioOrganismoImpl - loadOrganismo:" + e);
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#deleteOrganismo(java.lang.Long, java.lang.String, java.lang.String, java.lang.Long)
	 */
	// /MIGRADO
	@Override
	@Transactional
	public void deleteOrganismo(Long organismoId, String source, String accion,
			Long accionId) throws BusinessException {
		try {
			TblOrganismos o = tblOrganismosManager
					.getOrganismoById(organismoId);

			o.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado()
					.getNombreCompleto());
			o.setFechamodificacion(new Date());
			o.setEliminado("S");
			o.setActivo(false);
			tblOrganismosManager.update(o, source, accion, accionId);
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
			return queryExecutorOrganismosImpl.getListAutocomplete(term);

		} catch (Exception e) {
			logger.error(SERVICIOORGANIS2 + e);
			return new ArrayList<>();
		}

	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#getOrganismoIdByDir3(java.lang.String)
	 */
	@Override
	public Integer getOrganismoIdByDir3(String search) {
		try {
			return queryExecutorOrganismosImpl.getOrganismoIdByDir3(search);

		} catch (Exception e) {
			logger.error(SERVICIOORGANIS2 + e);
			return null;
		}
	}
	
	@Override
	public Integer getOrganismoIdByDir3SoloEliminado(String search) {
		try {
			return queryExecutorOrganismosImpl.getOrganismoIdByDir3SoloEliminado(search);

		} catch (Exception e) {
			logger.error(SERVICIOORGANIS2 + e);
			return null;
		}
	}
	
	@Override
	public List<TblOrganismos> getOrganismoById(String dir3) {
		try {
			TblOrganismosQuery query = new TblOrganismosQuery();
			query.setDir3(dir3);
			query.setDir3Comparator(TextComparator.UPPERCASE_TRANSLATE);
			query.setEliminadoIsNull(true);
			List<TblOrganismos> lista = tblOrganismosManager
					.getOrganismosByQuery(query);

			return (null != lista && !lista.isEmpty()) ? lista : null;

		} catch (Exception e) {
			logger.error(SERVICIOORGANIS2 + e);
			return null;
		}
	}
	
	@Override
	public List<TblOrganismos> getOrganismosHijos(String search) {
		try {
			return queryExecutorOrganismosImpl.getOrganismosHijos(search);

		} catch (Exception e) {
			logger.error(SERVICIOORGANIS2 + e);
			return null;
		}
	}
	
	@Override
	public List<TblOrganismos> getOrganismosByPdp(Long idOrganismosPdp) {
		try {
			return queryExecutorOrganismosImpl.getOrganismosByPdp(idOrganismosPdp);

		} catch (Exception e) {
			logger.error(SERVICIOORGANIS2 + e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo#existeOrganimo(es.mpr.plataformamensajeria.beans.OrganismoBean)
	 */
	@Override
	public Boolean existeOrganimo(OrganismoBean organismo) {
		try {
			TblOrganismosQuery query = new TblOrganismosQuery();
			query.setDir3(organismo.getDir3());
			query.setDir3Comparator(TextComparator.UPPERCASE_TRANSLATE);
			List<TblOrganismos> lista = tblOrganismosManager
					.getOrganismosByQuery(query);

			return null != lista && !lista.isEmpty();

		} catch (Exception e) {
			logger.error(SERVICIOORGANIS2 + e);
			return false;
		}
	}
	
	@Override
	public Boolean existeOrganismoServicio(Long idOrganismo,Long idServicio) {
		try {
			TblOrganismosServicioQuery query = new TblOrganismosServicioQuery();
			TblServiciosQuery servicio = new TblServiciosQuery();
			servicio.setServicioid(idServicio);
			TblOrganismosQuery organismo = new TblOrganismosQuery();
			organismo.setOrganismoid(idOrganismo);
			query.setTblOrganismos(organismo);
			query.setTblServicios(servicio);
			
			List<OrganismosServicioBean> lista = tblOrganismosServicioManager.getOrganismosServicioByQuery(query);
					

			return null != lista && !lista.isEmpty();

		} catch (Exception e) {
			logger.error(SERVICIOORGANIS2 + e);
			return false;
		}
	}

	/**
	 * <p>
	 * Obtenemos un objeto TblOrganismos a partir de un objeto OrganismoBean
	 * </p>.
	 *
	 * @param organismo the organismo
	 * @return objeto TblOrganismos
	 */
	// ///MIGRADO
	protected TblOrganismos getOrganismoTO(OrganismoBean organismo) {

		TblOrganismos organismoTO = new TblOrganismos();

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
			BeanUtils.copyProperties(organismoTO, organismo);
			organismoTO
					.setOrganismoid((null != organismo.getOrganismoId()) ? organismo
							.getOrganismoId().longValue() : null);
			organismoTO.setFechacreacion(organismo.getFechacreacion());
			organismoTO.setFechamodificacion(organismo.getFechamodificacion());
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioOrganismoImpl - getOrganismoTO:" + e);
		}

		return organismoTO;
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
	protected List<OrganismoBean> getListOrganismoBean(List<TblOrganismos> lista)
			throws BusinessException {
		List<OrganismoBean> result = null;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();

			for (TblOrganismos o : lista) {
				OrganismoBean organismo = new OrganismoBean();

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
					BeanUtils.copyProperties(organismo, o);
					organismo.setOrganismoId(o.getOrganismoid().intValue());
					organismo.setFechaEstadoStr((null != organismo
							.getFechaEstado()) ? df.format(organismo
							.getFechaEstado()) : "");
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(organismo);
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
	protected OrganismoBean getOrganismoBean(TblOrganismos o)
			throws BusinessException {
		OrganismoBean organismo = new OrganismoBean();

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
			organismo.setOrganismoId(o.getOrganismoid().intValue());
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
	private es.minhap.plataformamensajeria.iop.beans.OrganismoBean createOrganismoBean(
			OrganismoBean criterio,
			es.minhap.plataformamensajeria.iop.beans.OrganismoBean ob) {
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
				ob.setFechaCreacionDesde(criterio.getFechaCreacionDesde());
				ob.setFechaCreacionHasta(criterio.getFechaCreacionHasta());
			}

		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(SERVICIOORGANIS + e);
		}
		return ob;
	}

}
