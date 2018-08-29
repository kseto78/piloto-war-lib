package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.beanutils.converters.DateConverter;
import com.map.j2ee.util.beanutils.converters.IntegerConverter;

import es.minhap.common.entity.OrderType;
import es.minhap.plataformamensajeria.iop.beans.OrganismosServicioBean;
import es.minhap.plataformamensajeria.iop.beans.TblServidoresServiciosBean;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblCanalesManager;
import es.minhap.plataformamensajeria.iop.manager.TblOrganismosManager;
import es.minhap.plataformamensajeria.iop.manager.TblOrganismosServicioManager;
import es.minhap.plataformamensajeria.iop.manager.TblPlanificacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosManager;
import es.minhap.plataformamensajeria.iop.manager.ViewServiciosManager;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblOrganismosServicio;
import es.minhap.sim.model.TblPlanificaciones;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblServidoresServicios;
import es.minhap.sim.model.TblUsuariosAplicaciones;
import es.minhap.sim.model.ViewServicios;
import es.minhap.sim.query.TblAplicacionesQuery;
import es.minhap.sim.query.TblOrganismosQuery;
import es.minhap.sim.query.TblOrganismosServicioQuery;
import es.minhap.sim.query.TblServiciosQuery;
import es.minhap.sim.query.TblServidoresServiciosQuery;
import es.minhap.sim.query.TblUsuariosAplicacionesQuery;
import es.minhap.sim.query.TblUsuariosQuery;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServicioOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Maneja la persistencia y b&uacute;squeda de servicios a traves de JPA.
 * 
 * @author
 * 
 */
@Service("servicioServicioImpl")
public class ServicioServicioImpl implements ServicioServicio {

	/**  logger. */
	private static Logger logger = Logger.getLogger(ServicioServicioImpl.class);

	/**  view servicios manager. */
	@Resource(name = "ViewServiciosManagerImpl")
	private ViewServiciosManager viewServiciosManager;

	/**  tbl organismo servicio manager. */
	@Resource(name = "tblOrganismosServicioManagerImpl")
	private TblOrganismosServicioManager tblOrganismoServicioManager;

	/**  tbl servidores servicios manager. */
	@Resource(name = "TblServidoresServiciosManagerImpl")
	private TblServidoresServiciosManager tblServidoresServiciosManager;

	/**  tbl servicios manager. */
	@Resource(name = "TblServiciosManagerImpl")
	private TblServiciosManager tblServiciosManager;

	/**  tbl servidores manager. */
	@Resource(name = "TblServidoresManagerImpl")
	private TblServidoresManager tblServidoresManager;

	/**  tbl aplicaciones manager. */
	@Resource(name = "TblAplicacionesManagerImpl")
	private TblAplicacionesManager tblAplicacionesManager;

	/**  tbl canales manager. */
	@Resource(name = "tblCanalesManagerImpl")
	private TblCanalesManager tblCanalesManager;

	/**  tbl organismos manager. */
	@Resource(name = "TblOrganismosManagerImpl")
	private TblOrganismosManager tblOrganismosManager;

	/**  tbl planificaciones manager. */
	@Resource(name = "TblPlanificacionesManagerImpl")
	private TblPlanificacionesManager tblPlanificacionesManager;
	
	/**  tbl usuarios planificaciones manager. */
	@Resource(name = "TblUsuariosAplicacionesManagerImpl")
	private TblUsuariosAplicacionesManager tblUsuariosPlanificacionesManager;
	
	/**  tbl usuarios manager. */
	@Resource(name = "tblUsuariosManagerImpl")
	private TblUsuariosManager tblUsuariosManager;


	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#getServiciosMultiorganismo()
	 */
	///MIGRADO
	@Override
	public List<ServicioBean> getServiciosMultiorganismo() throws BusinessException {
		try {
			TblServiciosQuery query = new TblServiciosQuery();
			query.setEliminadoIsNull(true);
			query.setActivo(true);
			query.setMultiorganismo(true);
			
			List<TblServicios> lista = tblServiciosManager.getServicios(query); 
			return getListServicioBean(lista);
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - getServiciosMultiorganismo:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#getServicios(java.lang.String, java.lang.Integer)
	 */
	///MIGRADO
	@Override
	public List<ServicioBean> getServicios(String rolUsuario, Integer idUsuario) throws BusinessException {
		List<ServicioBean> res = new ArrayList<>();
		try{
			if (rolUsuario != null && rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)) {
				TblServiciosQuery query = new TblServiciosQuery();
				query.setEliminadoIsNull(true);
				query.setTblAplicacionesIsNotNull(true);
				query.setTblCanalesIsNotNull(true);
				query.addOrder("nombre", OrderType.ASC);
				List<TblServicios> listaServicios = tblServiciosManager.getServicios(query);
				if (null != listaServicios){
					for (TblServicios s : listaServicios) {
						ServicioBean servicio = new ServicioBean();
						servicio.setServicioId(s.getServicioid().intValue());
						servicio.setNombre(s.getNombre());
						servicio.setHistorificacion(s.getHistorificacion());
						res.add(servicio);
					}
				}
			}else{
				TblUsuariosAplicacionesQuery queryUsuariosAplicaciones = new TblUsuariosAplicacionesQuery();
				TblUsuariosQuery queryUsuarios = new TblUsuariosQuery();
				queryUsuarios.setUsuarioid(idUsuario.longValue());
				queryUsuariosAplicaciones.setTblUsuarios(queryUsuarios);
				List<TblUsuariosAplicaciones> listaUsuarioAplicaciones = tblUsuariosPlanificacionesManager.getUsuariosAplicacionesByQuery(queryUsuariosAplicaciones);
				
				if (null != listaUsuarioAplicaciones && !listaUsuarioAplicaciones.isEmpty()){
				
					TblAplicacionesQuery queryAplicacionesQuery = new TblAplicacionesQuery();
					for (TblUsuariosAplicaciones tblUsuariosAplicaciones : listaUsuarioAplicaciones) {
						queryAplicacionesQuery.addAplicacionidIn(tblUsuariosAplicaciones.getAplicacionid());
					}
				
					List<TblAplicaciones> listaAplicaciones = tblAplicacionesManager.getAplicaciones(queryAplicacionesQuery);
				
					TblServiciosQuery queryServicios = new TblServiciosQuery();
					for (TblAplicaciones aplicacion : listaAplicaciones) {
						queryServicios.addTblAplicacionesIdIn(aplicacion);
					}
					queryServicios.setEliminadoIsNull(true);
					List<TblServicios> listaServicios = tblServiciosManager.getServicios(queryServicios);
					if (null != listaServicios){
						for (TblServicios s : listaServicios) {
							ServicioBean servicio = new ServicioBean();
							servicio.setServicioId(s.getServicioid().intValue());
							servicio.setNombre(s.getNombre());
							servicio.setHistorificacion(s.getHistorificacion());
							res.add(servicio);
						}
					}
				}
//				TblServidoresServiciosQuery query = new TblServidoresServiciosQuery();
//				for (TblServicios s : listaServicios) {
//					query.addTblServiciosIdIn(s);
//				}
				
//				List<TblServidoresServiciosBean> listaSS = tblServidoresServiciosManager.getServidoresServicioByQuery(query);
//				for (TblServidoresServiciosBean tblSS : listaSS) {
//					ServicioBean servicio = new ServicioBean();
//					servicio.setServicioId(tblSS.getServicioId());
//					servicio.setNombre(tblSS.getNombreServicio());
//					res.add(servicio);
//				}
				
			}
		}catch(Exception e){
			logger.error("ServicioServicioImpl - getServicios:", e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
		return res;
	}
		
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#getServicios(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.ServicioBean)
	 */
	///MIGRADO
	@Override
	public PaginatedList<ServicioBean> getServicios(int start, int size, String order, String columnSort,
			ServicioBean criterio) throws BusinessException {
		String nombre = null;
		Long aplicacionId = null;
		int servicioId = 0;

		try {
			// Columna para ordenar
			HashMap<String, String> columns = new HashMap<>();
			columns.put("2", "aplicacionnombre");
			columns.put("3", "nombre");
			if (columnSort == null)
				columnSort = "1"; // Id

			String column = columns.get(columnSort);
			if (column == null)
				column = "aplicacionnombre";

			
			List<ViewServicios> lista = null;
			
			
			if (null != criterio && null != criterio.getServicioId()) {
				
				servicioId = criterio.getServicioId();

			}
			if (null != criterio && null != criterio.getNombre()) {
				
				nombre = criterio.getNombre();

			}
			if (null != criterio && null != criterio.getAplicacionid()) {
				
				aplicacionId = criterio.getAplicacionid().longValue();

			}
				
			lista = viewServiciosManager.getServiciosPaginado(start, size, order, column, nombre, aplicacionId, servicioId, false);


//			lista = viewServiciosManager.getServiciosPaginado(start, size, order, column, nombre,
//					aplicacionId, false);
			List<ServicioBean> pageList = getListViewServicioBean(lista);
			// Total de organismos
//			Integer rowcount = viewServiciosManager.getServiciosPaginado(start, size, order, column, nombre, aplicacionId, true).size();
			Integer rowcount = viewServiciosManager.getServiciosPaginado(start, size, order, column, nombre, aplicacionId, servicioId, true).size();

			
			PaginatedList<ServicioBean> result = new PaginatedList<ServicioBean>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - getServicios:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#newServicio(es.mpr.plataformamensajeria.beans.ServicioBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	///MIGRADO
	@Override
	@Transactional
	public Integer newServicio(ServicioBean servicio, String source, String accion, Long accionId)
			throws BusinessException {
		try {
			TblServicios servicioTO = getServicioTO(servicio);
			servicioTO.setFechacreacion(new Date());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			servicioTO.setCreadopor(modificador);
			Long idServicio = tblServiciosManager.insert(servicioTO, source, accion, accionId);

			servicio.setServicioId(idServicio.intValue());
			servicio.setActivo(servicio.getActivo());
			servicio.setFechacreacion(servicioTO.getFechacreacion());
			servicio.setCreadopor(servicioTO.getCreadopor());
			servicio.setNumeroMaxReenvios(servicioTO.getNumeroMaxReenvios());
			return servicio.getServicioId();
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - newServicio:" + e);
			BusinessException exc = new BusinessException(e, "errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#updateServicio(es.mpr.plataformamensajeria.beans.ServicioBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	///MIGRADO
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateServicio(ServicioBean servicio, String source, String accion, Long accionId)
			throws BusinessException {
		try {
			TblServicios servicioTO = getServicioTO(servicio);
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			servicioTO.setModificadopor(modificador);
			servicioTO.setFechamodificacion(new Date());
			tblServiciosManager.update(servicioTO, source, accion, accionId);

		} catch (Exception e) {
			logger.error("ServicioServicioImpl - updateServicio:" + e);
			throw new BusinessException(e, "errors.organismo.updateOrganismo");
		}

	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#loadServicio(es.mpr.plataformamensajeria.beans.ServicioBean)
	 */
	///MIGRADO
	@Override
	@Transactional
	public ServicioBean loadServicio(ServicioBean servicio) throws BusinessException {
		try {
			TblServicios servicioTO = tblServiciosManager.getServicio(servicio.getServicioId().longValue());
			return getServicioBean(servicioTO);
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - loadServicio:" + e);
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#deleteServicio(es.mpr.plataformamensajeria.beans.ServicioBean, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
	 */
	///MIGRADO
	@Override
	@Transactional
	public void deleteServicio(ServicioBean servicio, String accionServicio, Long accionIdServicio, String source,
			String accionPlanificacion, Long accionIdPlanificacion, String descripcionPlanificacion,
			String descripcionServidorServicio) throws BusinessException {
		try {
			TblServicios servicioTO = tblServiciosManager.getServicio(servicio.getServicioId().longValue());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			List<TblPlanificaciones> listaPlanificaciones = tblPlanificacionesManager
					.getPlanificacionesByServicio(servicioTO.getServicioid());
			// /Eliminamos Planificaciones
			for (TblPlanificaciones p : listaPlanificaciones) {
				p.setModificadopor(modificador);
				p.setFechamodificacion(new Date());
				p.setEliminado("S");
				tblPlanificacionesManager.updatePlanificacion(p, source, accionPlanificacion, accionIdPlanificacion,
						descripcionPlanificacion);
			}
			// /Eliminamos ServidoresServicios
			TblServidoresServiciosQuery query = new TblServidoresServiciosQuery();
			TblServiciosQuery queryServicio = new TblServiciosQuery();
			queryServicio.setServicioid(servicioTO.getServicioid());
			query.setTblServicios(queryServicio);

			List<TblServidoresServiciosBean> listaSS = tblServidoresServiciosManager
					.getServidoresServicioByQuery(query);
			if (!listaSS.isEmpty()) {
				List<ServidoresServiciosBean> lista = getListServidoresServiciosBean(listaSS);
				for (ServidoresServiciosBean ssb : lista) {
					deleteServidoresServicios(ssb, source, accionServicio, accionIdServicio,
							descripcionServidorServicio);
				}
			}
			servicioTO.setEliminado("S");
			servicioTO.setModificadopor(modificador);
			servicioTO.setFechamodificacion(new Date());
			tblServiciosManager.update(servicioTO, source, accionServicio, accionIdServicio);
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - deleteServicio:" + e);
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
		}
	}

	/**
	 * <p>
	 * Obtenemos un objeto OrganismoJPA a partir de un objeto OrganismoBean
	 * </p>.
	 *
	 * @param servicio the servicio
	 * @return objeto OrganismoJPA
	 */
	///MIGRADO
	public TblServicios getServicioTO(ServicioBean servicio) {

		TblServicios sTO = new TblServicios();
		try {
			sTO.setActivo(servicio.getActivo());
			sTO.setAgrupacioncodorg(servicio.getAgrupacioncodorg());
			sTO.setAgrupacioncodorgpagador(servicio.getAgrupacioncodorgpagador());
			sTO.setAgrupacioncodsia(servicio.getAgrupacioncodsia());
			sTO.setAgrupacionestado(servicio.getAgrupacionestado());
			sTO.setAndroidplataforma(servicio.getAndroidplataforma());
			sTO.setApnspasswordcertificado(servicio.getApnspasswordcertificado());
			sTO.setApnsrutacertificado(servicio.getApnsrutacertificado());
			sTO.setBadge((null != servicio.getBadge())? servicio.getBadge().longValue() : null);
			sTO.setConservacion(servicio.getConservacion());
			sTO.setCreadopor(servicio.getCreadopor());
			sTO.setCuentaenvio(servicio.getFrommail());
			sTO.setDescripcion(servicio.getDescripcion());
			sTO.setEndpoint(servicio.getEndpoint());
			sTO.setExternalid((null != servicio.getExternalid())? Long.parseLong(servicio.getExternalid()) : null);
			sTO.setEliminado(servicio.getEliminado());
			sTO.setFechacreacion(servicio.getFechacreacion());
			sTO.setFechamodificacion(servicio.getFechamodificacion());
			sTO.setGcmprojectkey(servicio.getGcmprojectkey());
			sTO.setHistorificacion(servicio.getHistorificacion());
			sTO.setInformesactivo(servicio.getInformesactivo());
			sTO.setInformesdestinatarios(servicio.getInformesdestinatarios());
			sTO.setIosplataforma(servicio.getIosplataforma());
			sTO.setModificadopor(servicio.getModificadopor());
			sTO.setMotivoconservacion(servicio.getMotivoconservacion());
			sTO.setMotivohistorificacion(servicio.getMotivohistorificacion());
			sTO.setMultiorganismo(servicio.getMultiorganismo());
			sTO.setNmaxenvios(servicio.getNmaxenvios());
			sTO.setNombre(servicio.getNombre());
			sTO.setNombrecuentaenvio(servicio.getFrommailname());
			sTO.setNombreloteenvio(servicio.getNombreloteenvio());
			sTO.setNumeroMaxReenvios(servicio.getNumeroMaxReenvios());
			sTO.setPendienteaprobacion(servicio.getPendienteaprobacion());
			sTO.setPremium(servicio.getPremium());
			sTO.setRespFuncionalEmail(servicio.getResponsablefuncionalemail());
			sTO.setRespFuncionalNombre(servicio.getResponsablefuncionalnombre());
			sTO.setRespTecnicoEmail(servicio.getResponsabletecnicoemail());
			sTO.setRespTecnicoNombre(servicio.getResponsabletecniconombre());
			sTO.setServicioid((null != servicio.getServicioId()) ? servicio.getServicioId().longValue() : null);
			sTO.setTblAplicaciones((null != servicio.getAplicacionid()) ? tblAplicacionesManager
					.getAplicacion(servicio.getAplicacionid().longValue()) : null);
			sTO.setTblCanales((null != servicio.getCanalid()) ? tblCanalesManager.getCanalById(servicio
					.getCanalid().longValue()) : null);
			sTO.setVapidPrivateKey(servicio.getVapidPrivateKey());
			sTO.setVapidPublicKey(servicio.getVapidPublicKey());
			sTO.setCaducidadWebPush(servicio.getCaducidadWebPush());

		} catch (Exception e) {
			logger.error("ServicioServicioImpl - getServicioJPA:" + e);
		} 
		return sTO;
	}

	/**
	 * <p>
	 * Obtenemos un objeto OrganismoJPA a partir de un objeto OrganismoBean
	 * </p>.
	 *
	 * @param servidoresServicios the servidores servicios
	 * @return objeto OrganismoJPA
	 */
	///MIGRADO
	protected TblServidoresServicios getServidorServicioTO(ServidoresServiciosBean servidoresServicios) {

		TblServidoresServicios ss = new TblServidoresServicios();

		ss.setHeadersms(servidoresServicios.getHeaderSMS());
		ss.setPrefijosms(servidoresServicios.getPrefijoSMS());
		ss.setNumintentos(servidoresServicios.getNumIntentos());
		ss.setProveedorpasswordsms(servidoresServicios.getProveedorPasswordSMS());
		ss.setProveedorusuariosms(servidoresServicios.getProveedorUsuarioSMS());
		ss.setServidorservicioid((null != servidoresServicios.getServidorServicioId() ? servidoresServicios
				.getServidorServicioId().longValue() : null));
		ss.setTblServicios((null != servidoresServicios.getServicioId()) ? tblServiciosManager
				.getServicio(servidoresServicios.getServicioId().longValue()) : null);
		ss.setTblServidores((null != servidoresServicios.getServidorId()) ? tblServidoresManager
				.getServidorById(servidoresServicios.getServidorId().longValue()) : null);

		return ss;
	}

	/**
	 * Obtener servicio organismos TO.
	 *
	 * @param servicioOrganismo the servicio organismo
	 * @return servicio organismos TO
	 */
	///MIGRADO
	protected TblOrganismosServicio getServicioOrganismosTO(ServicioOrganismosBean servicioOrganismo) {

		TblOrganismosServicio o = new TblOrganismosServicio();

		o.setCreadopor(servicioOrganismo.getCreadoPor());
		o.setFechacreacion(servicioOrganismo.getFechaCreacion());
		o.setFechamodificacion(servicioOrganismo.getFechaModificacion());
		o.setModificadopor(servicioOrganismo.getModificadoPor());
		o.setServicioorganismoid((null != servicioOrganismo.getServicioOrganismoId()) ? servicioOrganismo
				.getServicioOrganismoId().longValue() : null);
		o.setTblOrganismos((null != servicioOrganismo.getOrganismoId()) ? tblOrganismosManager
				.getOrganismoById(servicioOrganismo.getOrganismoId().longValue()) : null);
		o.setTblServicios((null != servicioOrganismo.getServicioId()) ? tblServiciosManager
				.getServicio(servicioOrganismo.getServicioId().longValue()) : null);

		return o;
	}

	/**
	 * <p>
	 * Convertirmos una lista de ServicioJPA a una lista de ServidoresBean
	 * </p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos OrganismoBean
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	@Override
	public List<ServicioBean> getListServicioBean(List<TblServicios> lista) throws BusinessException {
		List<ServicioBean> result = new ArrayList<>();

		if (lista != null && !lista.isEmpty()) {
			
			for (TblServicios s : lista) {
				ServicioBean servicio = new ServicioBean();

				try {
					servicio = getServicioBean(s);
				} catch (Exception e) {
					logger.error("ServicioServicioImpl - getListServicioBean:" + e);
					throw new BusinessException(e);
				} 
				result.add(servicio);
			}
		}

		return result;
	}

	/**
	 * <p>
	 * Obtenemos un objeto ServicioBean a partir de un objeto ServicioJPA
	 * </p>.
	 *
	 * @param serv the serv
	 * @return objeto ServicioBean
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	protected ServicioBean getServicioBean(TblServicios serv) throws BusinessException {
		ServicioBean servicio = new ServicioBean();

		try {
			servicio.setActivo(serv.getActivo());
			servicio.setAgrupacioncodorg(serv.getAgrupacioncodorg());
			servicio.setAgrupacioncodorgpagador(serv.getAgrupacioncodorgpagador());
			servicio.setAgrupacioncodsia(serv.getAgrupacioncodsia());
			servicio.setAgrupacionestado(serv.getAgrupacionestado());
			servicio.setAndroidplataforma(serv.getAndroidplataforma());
			servicio.setApnspasswordcertificado(serv.getApnspasswordcertificado());
			servicio.setApnsrutacertificado(serv.getApnsrutacertificado());
			servicio.setBadge((null != serv.getBadge())? serv.getBadge().intValue() : null);
			servicio.setConservacion(serv.getConservacion());
			servicio.setCreadopor(serv.getCreadopor());
			servicio.setFrommail(serv.getCuentaenvio());
			servicio.setDescripcion(serv.getDescripcion());
			servicio.setEndpoint(serv.getEndpoint());
			servicio.setExternalid((null != serv.getExternalid())? serv.getExternalid().toString() : null);
			servicio.setFechacreacion((null != serv.getFechacreacion() ) ? DateUtils.truncate(serv.getFechacreacion(), Calendar.DATE) : null);
			servicio.setFechamodificacion((null != serv.getFechamodificacion() ) ? DateUtils.truncate(serv.getFechamodificacion(), Calendar.DATE) : null);
			servicio.setGcmprojectkey(serv.getGcmprojectkey());
			servicio.setHistorificacion(serv.getHistorificacion());
			servicio.setInformesactivo(serv.getInformesactivo());
			servicio.setInformesdestinatarios(serv.getInformesdestinatarios());
			servicio.setIosplataforma(serv.getIosplataforma());
			servicio.setModificadopor(serv.getModificadopor());
			servicio.setMotivoconservacion(serv.getMotivoconservacion());
			servicio.setMotivohistorificacion(serv.getMotivohistorificacion());
			servicio.setMultiorganismo(serv.getMultiorganismo());
			servicio.setNmaxenvios(serv.getNmaxenvios());
			servicio.setNombre(serv.getNombre());
			servicio.setFrommailname(serv.getNombrecuentaenvio());
			servicio.setNombreloteenvio(serv.getNombreloteenvio());
			servicio.setNumeroMaxReenvios(serv.getNumeroMaxReenvios());
			servicio.setPendienteaprobacion(serv.getPendienteaprobacion());
			servicio.setPremium(serv.getPremium());
			servicio.setResponsablefuncionalemail(serv.getRespFuncionalEmail());
			servicio.setResponsablefuncionalnombre(serv.getRespFuncionalNombre());
			servicio.setResponsabletecnicoemail(serv.getRespTecnicoEmail());
			servicio.setResponsabletecniconombre(serv.getRespTecnicoNombre());
			servicio.setServicioId(serv.getServicioid().intValue());
			servicio.setAplicacionid((null != serv.getTblAplicaciones()) ? serv.getTblAplicaciones().getAplicacionid()
					.intValue() : null);
			servicio.setCanalid((null != serv.getTblCanales()) ? serv.getTblCanales().getCanalid().intValue() : null);
			servicio.setVapidPrivateKey(serv.getVapidPrivateKey());
			servicio.setVapidPublicKey(serv.getVapidPublicKey());
			servicio.setCaducidadWebPush(serv.getCaducidadWebPush());
			
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - getServicioBean:" + e);
		}
		return servicio;
	}

	/**
	 * <p>
	 * Obtenemos un objeto ServicioBean a partir de un objeto ServicioBean
	 * </p>.
	 *
	 * @param serv the serv
	 * @return objeto ServicioBean
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	@Override
	public ServicioBean createServicioBean(ServicioBean serv) throws BusinessException {
		ServicioBean servicio = new ServicioBean();

		try {
			BeanUtils.copyProperties(servicio, serv);
			servicio.setActivo(serv.getActivo());
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioServicioImpl - createServicioBean:" + e);
		}
		return servicio;
	}

	/**
	 * <p>
	 * Convertirmos una lista de ViewServicioJPA a una lista de ServidoresBean
	 * </p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos OrganismoBean
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	protected List<ServicioBean> getListViewServicioBean(List<ViewServicios> lista) throws BusinessException {
		List<ServicioBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<ServicioBean>();

			for (ViewServicios v : lista) {
				ServicioBean servicio = new ServicioBean();
				try {

					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(servicio, v);
					servicio.setServicioId(v.getServicioid().intValue());
				} catch (IllegalAccessException | InvocationTargetException e) {
					logger.error("ServicioServicioImpl - getListViewServicioBean:" + e);
					throw new BusinessException(e);
				} 
				result.add(servicio);
			}
		}

		return result;
	}

	/**
	 * <p>
	 * Convertirmos una lista de ViewServidoresServiciosJPA a una lista de
	 * ServidoresServiciosBean
	 * </p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos ServidoresServiciosBean
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	protected List<ServidoresServiciosBean> getListServidoresServiciosBean(List<TblServidoresServiciosBean> lista)
			throws BusinessException {
		List<ServidoresServiciosBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<ServidoresServiciosBean>();

			for (TblServidoresServiciosBean ss : lista) {

				ServidoresServiciosBean servidorServicio = new ServidoresServiciosBean();
				try {

					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					IntegerConverter iconverter = new IntegerConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					ConvertUtils.register(iconverter, java.lang.Integer.class);
					BeanUtils.copyProperties(servidorServicio, ss);
				} catch (IllegalAccessException | InvocationTargetException e) {
					logger.error("ServicioServicioImpl - getListServidoresServiciosBean:" + e);
					throw new BusinessException(e);
				} 
				result.add(servidorServicio);
			}
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#getServiciosByAplicacionId(java.lang.Integer)
	 */
	///MIGRADO
	@Override
	public List<ServicioBean> getServiciosByAplicacionId(Integer aplicacionId) throws BusinessException {
		try {
			List<ViewServicios> lista = viewServiciosManager.getServiciosByAplicacion(aplicacionId.longValue());
			List<ServicioBean> result = getListViewServicioBean(lista);
			return result;
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - getServiciosByAplicacionId:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#deleteServidoresServicios(es.mpr.plataformamensajeria.beans.ServidoresServiciosBean, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	///MIGRADO
	@Override
	@Transactional
	public void deleteServidoresServicios(ServidoresServiciosBean servidorServicio, String source, String accion,
			Long accionId, String descripcion) throws BusinessException {
		try {
			tblServidoresServiciosManager.delete(servidorServicio.getServidorServicioId().longValue(), source, accion,
					accionId, descripcion);
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - deleteServidoresServicios:" + e);
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#deleteServicioOrganismos(es.mpr.plataformamensajeria.beans.ServicioOrganismosBean, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	///MIGRADO
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteServicioOrganismos(ServicioOrganismosBean servicioOrganismo, String source, String accion,
			Long accionId, String descripcion) throws BusinessException {
		try {
			tblOrganismoServicioManager.delete(servicioOrganismo.getServicioOrganismoId(), source, accion, accionId,
					descripcion);
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - deleteServicioOrganismos:" + e);
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#loadOrganismoServicio(es.mpr.plataformamensajeria.beans.ServicioOrganismosBean)
	 */
	///MIGRADO
	@Override
	public ServicioOrganismosBean loadOrganismoServicio(ServicioOrganismosBean organismoServicio)
			throws BusinessException {
		try {
			return getOrganismoServicioBean(tblOrganismoServicioManager.getOrganismoServicioById(organismoServicio.getServicioOrganismoId()));
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - loadOrganismoServicioTO:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#newServidoresServicio(es.mpr.plataformamensajeria.beans.ServidoresServiciosBean, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	///MIGRADO
	@Override
	@Transactional
	public void newServidoresServicio(ServidoresServiciosBean servidorServicio, String source, String accion,
			Long accionId, String descripcion) throws BusinessException {

		try {
			TblServidoresServicios servidorServicioTO = getServidorServicioTO(servidorServicio);
			servidorServicioTO.setCreadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			servidorServicioTO.setFechacreacion(new Date());
			tblServidoresServiciosManager.insert(servidorServicioTO, source, accion, accionId, descripcion);
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - newServidoresServicio:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#newServicioOrganismo(es.mpr.plataformamensajeria.beans.ServicioOrganismosBean, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	///MIGRADO
	@Override
	@Transactional
	public void newServicioOrganismo(ServicioOrganismosBean servicioOrganismo, String source, String accion,
			Long accionId, String descripcion) {
		TblOrganismosServicio servicioOrganismosTO = getServicioOrganismosTO(servicioOrganismo);

		try {
			servicioOrganismosTO.setCreadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			servicioOrganismosTO.setFechacreacion(new Date());
			tblOrganismoServicioManager.insert(servicioOrganismosTO, source, accion, accionId, descripcion);
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - newServicioOrganismo:" + e);
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#getServidoresServicios(java.lang.String)
	 */
	///MIGRADO
	@Override
	public List<ServidoresServiciosBean> getServidoresServicios(String idServicio) throws BusinessException {
		try {
			TblServidoresServiciosQuery query = new TblServidoresServiciosQuery();
			TblServiciosQuery queryServicios = new TblServiciosQuery();
			queryServicios.setServicioid(Long.parseLong(idServicio));
			query.setTblServicios(queryServicios);
			List<TblServidoresServiciosBean> lista = tblServidoresServiciosManager.getServidoresServicioByQuery(query);
			List<ServidoresServiciosBean> result = getListServidoresServiciosBean(lista);
			return result;
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - getServidoresServicios:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#getServicioOrganismo(java.lang.String)
	 */
	///MIGRADO
	@Override
	public List<ServicioOrganismosBean> getServicioOrganismo(String idServicio) throws BusinessException {
		try {
			TblOrganismosServicioQuery query = new TblOrganismosServicioQuery();
			TblServiciosQuery queryServicios = new TblServiciosQuery();
			queryServicios.setServicioid(Long.parseLong(idServicio));
			query.setTblServicios(queryServicios);
			List<OrganismosServicioBean> lista = tblOrganismoServicioManager.getOrganismosServicioByQuery(query);
			return getListServicioOrganismosBean(lista);
			
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - getServicioOrganismo:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}

	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#getOrganismoServicio(java.lang.String)
	 */
	///MIGRADO
	@Override
	public List<ServicioOrganismosBean> getOrganismoServicio(String idOrganismo) throws BusinessException {
		try {
			TblOrganismosServicioQuery query =  new TblOrganismosServicioQuery();
			TblOrganismosQuery queryOrganismo = new TblOrganismosQuery();
			queryOrganismo.setOrganismoid(Long.parseLong(idOrganismo));
			query.setTblOrganismos(queryOrganismo);
			return getListServicioOrganismosBean(tblOrganismoServicioManager.getOrganismosServicioByQuery(query));
			
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - getOrganismoServicio:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}

	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#getServiciosHistorico()
	 */
	///MIGRADO
	@Override
	@Transactional
	public List<ServicioBean> getServiciosHistorico() throws BusinessException {
		try {
			TblServiciosQuery query = new TblServiciosQuery();
			query.setEliminadoIsNull(true);
			query.setHistorificacionIsNotNull(true);
			query.setActivo(true);
			List<TblServicios> listaServicios = tblServiciosManager.getServicios(query);
			return getListServicioBean(listaServicios);

		} catch (Exception e) {
			logger.error("ServicioServicioImpl - getServiciosHistorico:" + e);
			throw new BusinessException(e, "errors.job.historico.getServicios");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#getServiciosCons()
	 */
	///MIGRADO
	@Override
	@Transactional
	public List<ServicioBean> getServiciosCons() throws BusinessException {
		try {
			TblServiciosQuery query = new TblServiciosQuery();
			query.setEliminadoIsNull(true);
			query.setConservacionIsNotNull(true);
			query.setActivo(true);
			List<TblServicios> listaServicios = tblServiciosManager.getServicios(query);
			return getListServicioBean(listaServicios);

		} catch (Exception e) {
			logger.error("ServicioServicioImpl - getServiciosCons:" + e);
			throw new BusinessException(e, "errors.job.historico.getServicios");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio#getServiciosInformes()
	 */
	///MIGRADO
	@Override
	@Transactional
	public List<ServicioBean> getServiciosInformes() throws BusinessException {
		try {
		TblServiciosQuery query = new TblServiciosQuery();
		query.setEliminadoIsNull(true);
		query.setInformesactivo(true);
		query.setActivo(true);
		List<TblServicios> listaServicios = tblServiciosManager.getServicios(query);
		return getListServicioBean(listaServicios);

	} catch (Exception e) {
		logger.error("ServicioServicioImpl - getServiciosInformes:" + e);
		throw new BusinessException(e, "errors.job.historico.getServicios");
	}
	}

	/**
	 * Obtener list servicio organismos bean.
	 *
	 * @param lista the lista
	 * @return list servicio organismos bean
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	protected List<ServicioOrganismosBean> getListServicioOrganismosBean(List<OrganismosServicioBean> lista)
			throws BusinessException {
		List<ServicioOrganismosBean> result = new ArrayList<>();

		if (lista != null && !lista.isEmpty()) {
			
			for (OrganismosServicioBean so : lista) {

				ServicioOrganismosBean servicioOrganismo = new ServicioOrganismosBean();

				try {
					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					Long defaultLongValue=null;
					LongConverter longConverter = new LongConverter(defaultLongValue);
					Integer defaultIntegerValue=null;
					IntegerConverter integerConverter = new IntegerConverter(defaultIntegerValue);
					ConvertUtils.register(longConverter, java.lang.Long.class);
					ConvertUtils.register(integerConverter, java.lang.Integer.class);
					BeanUtils.copyProperties(servicioOrganismo, so);
				} catch (IllegalAccessException | InvocationTargetException e) {
					logger.error("ServicioServicioImpl - getListServicioOrganismosBean:" + e);
					throw new BusinessException(e);
				}

				result.add(servicioOrganismo);
			}
		}

		return result;
	}

	/**
	 * Obtener organismo servicio bean.
	 *
	 * @param organismoServicio the organismo servicio
	 * @return organismo servicio bean
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	protected ServicioOrganismosBean getOrganismoServicioBean(OrganismosServicioBean organismoServicio) throws BusinessException{
		ServicioOrganismosBean servicioOrganismo = new ServicioOrganismosBean();
		try {
			Date defaultValue = null;
			DateConverter converter = new DateConverter(defaultValue);
			Long defaultLongValue=null;
			LongConverter longConverter = new LongConverter(defaultLongValue);
			Integer defaultIntegerValue=null;
			IntegerConverter integerConverter = new IntegerConverter(defaultIntegerValue);
			ConvertUtils.register(longConverter, java.lang.Long.class);
			ConvertUtils.register(integerConverter, java.lang.Integer.class);
			ConvertUtils.register(converter, java.util.Date.class);
			BeanUtils.copyProperties(servicioOrganismo, organismoServicio);
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioServicioImpl - getOrganismoServicioBean:" + e);
			throw new BusinessException(e);
		} 
		return servicioOrganismo;
	}
}
