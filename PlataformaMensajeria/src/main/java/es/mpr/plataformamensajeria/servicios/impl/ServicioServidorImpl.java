package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.minhap.common.entity.OrderType;
import es.minhap.plataformamensajeria.iop.beans.TblServidoresServiciosBean;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblOrganismosManager;
import es.minhap.plataformamensajeria.iop.manager.TblPlanificacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresOrganismosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosAplicacionesManager;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblPlanificaciones;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblServidores;
import es.minhap.sim.model.TblServidoresOrganismos;
import es.minhap.sim.model.TblUsuariosAplicaciones;
import es.minhap.sim.query.TblAplicacionesQuery;
import es.minhap.sim.query.TblServiciosQuery;
import es.minhap.sim.query.TblServidoresOrganismosQuery;
import es.minhap.sim.query.TblServidoresQuery;
import es.minhap.sim.query.TblServidoresServiciosQuery;
import es.minhap.sim.query.TblUsuariosAplicacionesQuery;
import es.minhap.sim.query.TblUsuariosQuery;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidoresOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Maneja la persistencia y b&uacute;squeda de servidores a traves de JPA.
 * 
 * @author
 * 
 */
@Service("servicioServidorImpl")
public class ServicioServidorImpl implements ServicioServidor {
	
	/**  logger. */
	private static Logger logger = Logger.getLogger(ServicioServidorImpl.class);
	
	/**  tbl servidores manager. */
	@Resource(name="TblServidoresManagerImpl")
	private TblServidoresManager tblServidoresManager;
	
	/**  tbl planificaciones manager. */
	@Resource(name="TblPlanificacionesManagerImpl")
	private TblPlanificacionesManager tblPlanificacionesManager;
	
	/**  tbl servidores servicios manager. */
	@Resource(name="TblServidoresServiciosManagerImpl")
	private TblServidoresServiciosManager tblServidoresServiciosManager;
	
	/**  tbl organismos manager. */
	@Resource(name="TblOrganismosManagerImpl")
	private TblOrganismosManager tblOrganismosManager;
	
	/**  tbl servidores organismos manager. */
	@Resource(name="tblServidoresOrganismosManagerImpl")
	private TblServidoresOrganismosManager tblServidoresOrganismosManager;
	
	/**  tbl aplicaciones manager. */
	@Resource(name = "TblAplicacionesManagerImpl")
	private TblAplicacionesManager tblAplicacionesManager;
	
	/**  tbl usuarios planificaciones manager. */
	@Resource(name = "TblUsuariosAplicacionesManagerImpl")
	private TblUsuariosAplicacionesManager tblUsuariosPlanificacionesManager;
	
	/**  tbl servicios manager. */
	@Resource(name = "TblServiciosManagerImpl")
	private TblServiciosManager tblServiciosManager;
	
	/**  servicio organismo. */
	@Resource(name = "servicioOrganismoImpl")
	private ServicioOrganismo servicioOrganismo;
	
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#getServidores(int)
	 */
	////MIGRADO
	@Override
	public List<ServidorBean> getServidores(int tipoServidor) throws BusinessException {
		try {
			TblServidoresQuery query = new TblServidoresQuery();
			query.setTipo(tipoServidor);
			query.setEliminadoIsNull(true);
			List<TblServidores> lista = tblServidoresManager.getServidoresByQuery(query);
		
			List<ServidorBean> result = getListServidorBean(lista);

			return result;

		} catch (Exception e) {
			logger.error("ServicioServidorImpl - getServidoresByTipoServidor:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}



	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#getServidoresYProveedores(java.lang.String)
	 */
	////MIGRADO
	@Override
	public List<ServidorBean> getServidoresYProveedores(String tipoServidor) throws BusinessException {
		try {
			TblServidoresQuery query = new TblServidoresQuery();
			query.setTipo(Integer.parseInt(tipoServidor));
			query.setEliminadoIsNull(true);
			query.setActivo(true);
			tblServidoresManager.getServidoresByQuery(query);
			return getListServidorBean(tblServidoresManager.getServidoresByQuery(query));
		} catch (Exception e) {
			logger.error("ServicioServidorImpl - getServidoresYProveedores:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#getServidoresYProveedores(java.lang.String, java.lang.Integer)
	 */
	/////MIGRADO
	@Override
	public List<ServidorBean> getServidoresYProveedores(String rolUsuario, Integer idUsuario) throws BusinessException {
		List<ServidorBean> listBean = new ArrayList<>();
		List<TblServidores> listaServidores = new ArrayList<>();
		
		try {
			if (rolUsuario != null && (rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)||rolUsuario.equals(PlataformaMensajeriaUtil.ROL_CAID))) {
				TblServidoresQuery query = new TblServidoresQuery();
				query.setEliminadoIsNull(true);
				query.addOrder("nombre", OrderType.ASC);
				listaServidores = tblServidoresManager.getServidoresByQuery(query);
				for (TblServidores s : listaServidores) {
					ServidorBean servidor = new ServidorBean();
					servidor.setServidorid(s.getServidorid());
					servidor.setNombre(s.getNombre());
					listBean.add(servidor);
				}
			} else {
				TblServidoresServiciosQuery query = new TblServidoresServiciosQuery();
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
					
					List<TblServicios> listaServicios = tblServiciosManager.getServicios(queryServicios);
					
					for (TblServicios s : listaServicios) {
						query.addTblServiciosIdIn(s);
					}
					
					List<TblServidoresServiciosBean> listaSS = tblServidoresServiciosManager.getServidoresServicioByQuery(query);
					for (TblServidoresServiciosBean tblSS : listaSS) {
						ServidorBean servidor = new ServidorBean();
						servidor.setServidorid(tblSS.getServidorId().longValue());
						servidor.setNombre(tblSS.getNombreServidor());
						listBean.add(servidor);
					}
					
					//anadimos los servidores de sus organismos si existen y no se encuentran en el listado
					listBean = getServidoresOrganismosUsuario(listBean, rolUsuario, idUsuario);
				}
			}
		} catch (Exception e) {
			logger.error("ServicioServidorImpl - getServidoresYProveedores:" + e);
		}
		return listBean;
	}

	/**
	 * Obtener servidores organismos usuario.
	 *
	 * @param listBean the list bean
	 * @param rolUsuario the rol usuario
	 * @param idUsuario the id usuario
	 * @return servidores organismos usuario
	 */
	protected List<ServidorBean> getServidoresOrganismosUsuario(List<ServidorBean> listBean, String rolUsuario, Integer idUsuario){
		try{
		List<Integer> listaOrganismos = servicioOrganismo.getOrganismos(rolUsuario, idUsuario);
		if (null != listaOrganismos && !listaOrganismos.isEmpty()){
			List<ServidoresServiciosBean> listaServidoresOrganismos = getServidorOrganismo(listaOrganismos);
			for (ServidoresServiciosBean ss : listaServidoresOrganismos) {
				ServidorBean servidor = new ServidorBean();
				servidor.setServidorid(ss.getServidorId().longValue());
				servidor.setNombre(ss.getNombreServidor());
				listBean.add(servidor);
			}
			//quitamos los repetidos
			Map<String, ServidorBean> repetidos = new TreeMap<>();
			for (ServidorBean sBean : listBean) {
				repetidos.put(sBean.getNombre(), sBean);
			}
			listBean = new ArrayList<>(repetidos.values());
		}
		} catch (Exception e) {
			logger.error("ServicioServidorImpl - getServidoresOrganismosUsuario:" + e);
		}
		return listBean;
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#getServidoresByTipoPlanificacion(java.lang.String)
	 */
	/////MIGRADO
	@Override
	public List<ServidorBean> getServidoresByTipoPlanificacion(String tipo) throws BusinessException {
		TblServidoresQuery query = new TblServidoresQuery();
		
		query.setEliminadoIsNull(true);
		query.setTipo(Integer.parseInt(tipo));
		return getListServidorBean(tblServidoresManager.getServidoresByQuery(query));

	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#getServidoresNoAsignados(java.lang.Integer, int)
	 */
	////MIGRADO
	@Override
	public List<ServidorBean> getServidoresNoAsignados(Integer idServicio, int tipoServidor) throws BusinessException {
		ArrayList<ServidorBean> listaServidores = new ArrayList<>();

		try{
			TblServidoresQuery query = new TblServidoresQuery();
			query.setEliminadoIsNull(true);
			for (Long id : tblServidoresServiciosManager.getServidoresFromServidoresServiciosByServicio(idServicio.longValue())) {
				query.addServidoridNotIn(id);
			}
			query.setTipo(tipoServidor);
			List<TblServidores> lista = tblServidoresManager.getServidoresByQuery(query);
			
			for (TblServidores tblServidores : lista) {
				ServidorBean servidorBean = new ServidorBean();
				servidorBean.setServidorid(tblServidores.getServidorid());
				servidorBean.setNombre(tblServidores.getNombre());
				listaServidores.add(servidorBean);
			}
			
		} catch (Exception e) {
			logger.error("ServicioServidorImpl - getServidoresNoAsignados:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}

		return listaServidores;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#getAllServidores()
	 */
	/////MIGRADO
	@Override
	public List<ServidorBean> getAllServidores() throws BusinessException {
		TblServidoresQuery query = new TblServidoresQuery();
		query.setEliminadoIsNull(true);
		query.addOrder("nombre", OrderType.ASC);
		List<TblServidores> lista = tblServidoresManager.getServidoresByQuery(query);
		return getListServidorBean(lista);
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#getServidores(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.ServidorBean, int)
	 */
	//////////////////////////MIGRADO
	@Override
	public PaginatedList<ServidorBean> getServidores(int start, int size, String order, String columnSort, ServidorBean criterio, int tipoServidor) throws BusinessException {
		String nombre = null;
		try {

			// Columna para ordenar
			Hashtable<String, String> columns = new Hashtable<>();
			columns.put("2", "nombre");
			
			if (columnSort == null)
				columnSort = "2"; // Id

			String column = columns.get(columnSort);
			if (column == null)
				column = "nombre";

			if (null != criterio && null != criterio.getNombre()){
				nombre = criterio.getNombre();
			}
			
			List<TblServidores> lista = tblServidoresManager.getServidoresPaginado(start, size, order, column, nombre, tipoServidor, false);
			List<ServidorBean> pageList = getListServidorBean(lista);

			// Total de organismos
			Integer rowcount = tblServidoresManager.getServidoresPaginado(start, size, order, column, nombre, tipoServidor, true).size();

			PaginatedList<ServidorBean> result = new PaginatedList<>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);
			
			return result;
		} catch (Exception e) {
			logger.error("ServicioServidorImpl - getServidores:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}

	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#newServidor(es.mpr.plataformamensajeria.beans.ServidorBean, int, java.lang.String, java.lang.String, java.lang.Long)
 */
///////MIGRADO
	@Override
	@Transactional
	public Long newServidor(ServidorBean servidor, int tipoServidor, String source, String accion, Long accionId) throws BusinessException {
		try {
			TblServidores servidorTO = getServidorTO(servidor);
			servidorTO.setTipo(tipoServidor);
			servidorTO.setFechacreacion(new Date());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			servidorTO.setCreadopor(modificador);
			Long idServidor = tblServidoresManager.insert(servidorTO, source, accion, accionId);
			
			servidor.setServidorid(idServidor);
			servidor.setActivo(servidor.getActivo());
			servidor.setFechacreacion(servidorTO.getFechacreacion());
			servidor.setCreadopor(servidorTO.getCreadopor());
			return servidor.getServidorid();
		} catch (Exception e) {
			logger.error("ServicioServidorImpl - newServidor:" + e);
			BusinessException exc = new BusinessException(e, "errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#updateServidor(es.mpr.plataformamensajeria.beans.ServidorBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	//////MIGRADO
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateServidor(ServidorBean servidor, String source, String accion, Long accionId) throws BusinessException {
		try {
			TblServidores servidorTO = getServidorTO(servidor);
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			servidorTO.setModificadopor(modificador);
			servidorTO.setFechamodificacion(new Date());
			tblServidoresManager.update(servidorTO, source, accion, accionId);
		} catch (Exception e) {
			logger.error("ServicioServidorImpl - updateServidor:" + e);
			throw new BusinessException(e, "errors.organismo.updateOrganismo");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#loadServidor(es.mpr.plataformamensajeria.beans.ServidorBean)
	 */
	/////MIGRADO
	@Override
	@Transactional
	public ServidorBean loadServidor(ServidorBean servidor) throws BusinessException {
		try {
			TblServidores serv =tblServidoresManager.getServidorById(servidor.getServidorid());
			
			return getServidorBean(serv);
		} catch (Exception e) {
			logger.error("ServicioServidorImpl - loadServidor:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#deleteServidor(es.mpr.plataformamensajeria.beans.ServidorBean, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	/////MIGRADO
	@Override
	@Transactional
	public void deleteServidor(ServidorBean servidor, String accionServidor, Long accionIdServidor, String source, String accionPlanificacion, 
			Long accionIdPlanificacion, String descripcion) throws BusinessException {
		try {
			TblServidores servidorTO = tblServidoresManager.getServidorById(servidor.getServidorid());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			List<TblPlanificaciones> listaPlanificaciones = tblPlanificacionesManager.getPlanificacionesByServidor(servidorTO.getServidorid());
			for (TblPlanificaciones p : listaPlanificaciones) {
				p.setModificadopor(modificador);
				p.setFechamodificacion(new Date());
				p.setEliminado("S");
				tblPlanificacionesManager.updatePlanificacion(p, source, accionPlanificacion, accionIdPlanificacion, descripcion);
			}
			
			servidorTO.setEliminado("S");
			servidorTO.setModificadopor(modificador);
			servidorTO.setFechamodificacion(new Date());
			tblServidoresManager.update(servidorTO, source, accionServidor, accionIdServidor);

		} catch (Exception e) {
			logger.error("ServicioServidorImpl - deleteServidor:" + e);
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
		}
	}

	/**
	 * <p>
	 * Obtenemos un objeto OrganismoJPA a partir de un objeto OrganismoBean
	 * </p>.
	 *
	 * @param servidor the servidor
	 * @return objeto OrganismoJPA
	 */
	
	////MIGRADO
	protected TblServidores getServidorTO(ServidorBean servidor) {

		TblServidores servidorTO = new TblServidores();

		try {
			BeanUtils.copyProperties(servidorTO, servidor);
			servidorTO.setFechacreacion(servidor.getFechacreacion());
			servidorTO.setFechamodificacion(servidor.getFechamodificacion());
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioServidorImpl - getServidorTO:" + e);
		} 
		return servidorTO;
	}

	
	/**
	 * <p>
	 * Obtenemos un objeto ServidorBean a partir de un objeto ServidoresJPA
	 * </p>.
	 *
	 * @param serv the serv
	 * @return objeto ServidorBean
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	protected ServidorBean getServidorBean(TblServidores serv) throws BusinessException {
		ServidorBean servidor = new ServidorBean();

		try {
			BeanUtils.copyProperties(servidor, serv);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioServidorImpl - getServidorBean:" + e);
			throw new BusinessException(e);
		}

		return servidor;
	}

	/**
	 * <p>
	 * Convertirmos una lista de ViewServidoresJPA a una lista de ServidoresBean
	 * </p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos OrganismoBean
	 * @throws BusinessException the business exception
	 */
	/////MIGRADO
	protected List<ServidorBean> getListServidorBean(List<TblServidores> lista) throws BusinessException {
		List<ServidorBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();

			for (TblServidores s : lista) {
				ServidorBean servidor = new ServidorBean();
				try {
					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(servidor, s);
				} catch (IllegalAccessException | InvocationTargetException e) {
					logger.error("ServicioServidorImpl - getListServidorBean:" + e);
					throw new BusinessException(e);
				}

				result.add(servidor);
			}
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#getAllServidoresBBDD()
	 */
	////MIGRADO
	@Override
	public List<ServidorBean> getAllServidoresBBDD() throws BusinessException {

		List<ServidorBean> listAllServidores = new ArrayList<ServidorBean>();
		TblServidoresQuery query = new TblServidoresQuery();
		query.addOrder("nombre", OrderType.ASC);
		List<TblServidores> listaServidores = tblServidoresManager.getServidoresByQuery(query);
		if (null != listaServidores){
			for (TblServidores servidor : listaServidores) {
				ServidorBean servidorBean = new ServidorBean();
				servidorBean.setServidorid(servidor.getServidorid());
				servidorBean.setNombre(servidor.getNombre());

				listAllServidores.add(servidorBean);
			}
		}
		return listAllServidores;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#getServidorOrganismo(java.lang.String)
	 */
	/////MIGRADO
	@Override
	public List<ServidoresOrganismosBean> getServidorOrganismo(String idOrganismo) throws BusinessException {
		try {
			TblServidoresOrganismosQuery query = new TblServidoresOrganismosQuery();
			query.setOrganismoid(Long.parseLong(idOrganismo));
			return getListServidorOrganismosBean(tblServidoresOrganismosManager.getServidoresOrganismosByQuery(query));
		} catch (Exception e) {
			logger.error("ServicioServidorImpl - getServidorOrganismo:" + e);
		}
		return new ArrayList<>();
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#getServidorOrganismo(java.util.List)
	 */
	////MIGRADO
	@Override
	public List<ServidoresServiciosBean> getServidorOrganismo(List<Integer> listaOrganismos) throws BusinessException {
		List<ServidoresServiciosBean> res = new ArrayList<>();

		try {
			TblServidoresOrganismosQuery query = new TblServidoresOrganismosQuery();
			for (Integer o : listaOrganismos) {
				query.addOrganismoidIn(o.longValue());
			}
			List<TblServidoresOrganismos> lista = tblServidoresOrganismosManager.getServidoresOrganismosByQuery(query);
			for (TblServidoresOrganismos so : lista) {
				ServidoresServiciosBean aux = new ServidoresServiciosBean();
				aux.setHeaderSMS(so.getHeadersms());
				aux.setDIR3Organismo(tblOrganismosManager.getOrganismoById(so.getOrganismoid()).getDir3());
				aux.setOrganismoId(so.getOrganismoid().intValue());
				aux.setNombreServidor(tblServidoresManager.getServidorById(so.getServidorid()).getNombre());
				aux.setNumIntentos(so.getNumintentos());
				aux.setProveedorPasswordSMS(so.getProveedorpasswordsms());
				aux.setProveedorUsuarioSMS(so.getProveedorusuariosms());
				aux.setServidorServicioId(so.getServidororganismoid().intValue());
				aux.setServidorId(so.getServidorid().intValue());

				res.add(aux);
			}
		} catch (Exception e) {
			logger.error("ServicioServidorImpl - getServidorOrganismo:" + e);
		}

		return res;
	}

	/**
	 * Obtener list servidor organismos bean.
	 *
	 * @param lista the lista
	 * @return list servidor organismos bean
	 * @throws BusinessException the business exception
	 */
	// //MIGRADO
	protected List<ServidoresOrganismosBean> getListServidorOrganismosBean(List<TblServidoresOrganismos> lista)
			throws BusinessException {
		List<ServidoresOrganismosBean> result = new ArrayList<>();

		if (lista != null && !lista.isEmpty()) {
			for (TblServidoresOrganismos so : lista) {
				result.add(getServidorOrganismoBean(so));
			}		
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#newServidoresOrganismo(es.mpr.plataformamensajeria.beans.ServidoresOrganismosBean, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	////MIGRADO
	@Override
	public void newServidoresOrganismo(ServidoresOrganismosBean servidorOrganismo, String source, String accion, Long accionId, String descripcion) {
		try {
			TblServidoresOrganismos soTO = getServidoresOrganismoTO(servidorOrganismo);
			soTO.setFechacreacion(new Date());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			soTO.setCreadopor(modificador);
			if("ALTA_MASIVA_APLICACION".equals(source)){
				soTO.setCreadopor("ALTA_MASIVA_APLICACION");
			}
			tblServidoresOrganismosManager.insert(soTO, source, accion, accionId, descripcion);
		} catch (Exception e) {
			logger.error("ServicioServidorImpl - newServidoresOrganismo:" + e);
		} 
	}

	/**
	 * Obtener servidores organismo TO.
	 *
	 * @param servidorOrganismo the servidor organismo
	 * @return servidores organismo TO
	 */
	////MIGRADO
	protected TblServidoresOrganismos getServidoresOrganismoTO(ServidoresOrganismosBean servidorOrganismo) {

		TblServidoresOrganismos so = new TblServidoresOrganismos();

		so.setCreadopor(servidorOrganismo.getCreadoPor());
		so.setFechacreacion(servidorOrganismo.getFechaCreacion());
		so.setFechamodificacion(servidorOrganismo.getFechaModificacion());
		so.setHeadersms(servidorOrganismo.getHeaderSMS());
		so.setModificadopor(servidorOrganismo.getModificadoPor());
		so.setNumintentos(servidorOrganismo.getNumIntentos());
		so.setOrganismoid((null != servidorOrganismo.getOrganismoId()) ? servidorOrganismo.getOrganismoId() : null);
		so.setProveedorpasswordsms(servidorOrganismo.getProveedorPasswordSMS());
		so.setProveedorusuariosms(servidorOrganismo.getProveedorUsuarioSMS());
		so.setServidorid((null != servidorOrganismo.getServidorId())? servidorOrganismo.getServidorId() : null);
		so.setServidororganismoid((null != servidorOrganismo.getServidorOrganismoId()) ? servidorOrganismo.getServidorOrganismoId() : null);
				
		return so;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#deleteServidorOrganismos(es.mpr.plataformamensajeria.beans.ServidoresOrganismosBean, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	/////MIGRADO
	@Override
	public void deleteServidorOrganismos(ServidoresOrganismosBean servidorOrganismo, String source, String accion, Long accionId, String descripcion) throws BusinessException {
		try {
			tblServidoresOrganismosManager.delete(servidorOrganismo.getServidorOrganismoId(), source, accion, accionId,
					descripcion);
		} catch (Exception e) {
			logger.error("ServicioServicioImpl - deleteServicioOrganismos:" + e);
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
		}
	}


	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor#loadServidorOrganismoBean(es.mpr.plataformamensajeria.beans.ServidoresOrganismosBean)
	 */
	////MIGRADO
	@Override
	public ServidoresOrganismosBean loadServidorOrganismoBean(ServidoresOrganismosBean servidorOrganismo) throws BusinessException {
		try {
			TblServidoresOrganismos servidorOrganismoTO = tblServidoresOrganismosManager.getServidoresOrganismosById(servidorOrganismo.getServidorOrganismoId());
			return getServidorOrganismoBean(servidorOrganismoTO);

		} catch (Exception e) {
			logger.error("ServicioServidorImpl - loadServidorOrganismoBean:" + e);
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}

	/**
	 * Obtener servidor organismo bean.
	 *
	 * @param servidorOrganismoTO the servidor organismo TO
	 * @return servidor organismo bean
	 * @throws BusinessException the business exception
	 */
	// //MIGRADO
	protected ServidoresOrganismosBean getServidorOrganismoBean(TblServidoresOrganismos servidorOrganismoTO)
			throws BusinessException {
		ServidoresOrganismosBean so = new ServidoresOrganismosBean();

		if (servidorOrganismoTO != null) {
			so.setCreadoPor(servidorOrganismoTO.getCreadopor());
			so.setFechaCreacion(servidorOrganismoTO.getFechacreacion());
			so.setFechaModificacion(servidorOrganismoTO.getFechamodificacion());
			so.setHeaderSMS(servidorOrganismoTO.getHeadersms());
			so.setModificadoPor(servidorOrganismoTO.getModificadopor());
			so.setNombreOrganismo((null != servidorOrganismoTO.getOrganismoid()) ? tblOrganismosManager
					.getOrganismoById(servidorOrganismoTO.getOrganismoid()).getNombre() : null);
			so.setNombreServidor((null != servidorOrganismoTO.getServidorid()) ? tblServidoresManager.getServidorById(
					servidorOrganismoTO.getServidorid()).getNombre() : null);
			so.setNumIntentos(servidorOrganismoTO.getNumintentos());
			so.setOrganismoId((null != servidorOrganismoTO.getOrganismoid()) ? tblOrganismosManager
					.getOrganismoById(servidorOrganismoTO.getOrganismoid()).getOrganismoid() : null);
			so.setProveedorPasswordSMS(servidorOrganismoTO.getProveedorpasswordsms());
			so.setProveedorUsuarioSMS(servidorOrganismoTO.getProveedorusuariosms());
			so.setServidorId((null != servidorOrganismoTO.getServidorid())? tblServidoresManager.getServidorById(servidorOrganismoTO.getServidorid()).getServidorid() : null);
			so.setServidorOrganismoId((null != servidorOrganismoTO.getServidororganismoid())? servidorOrganismoTO.getServidororganismoid() : null);
		} else
			return null;
		return so;
	}

}
