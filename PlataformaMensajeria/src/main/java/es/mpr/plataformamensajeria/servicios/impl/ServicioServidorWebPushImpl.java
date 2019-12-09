package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.plataformamensajeria.iop.manager.TblPlanificacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresServiciosManager;
import es.minhap.sim.model.TblPlanificaciones;
import es.minhap.sim.model.TblServidores;
import es.minhap.sim.query.TblServidoresQuery;
import es.mpr.plataformamensajeria.beans.ServidorWebPushBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorWebPush;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de servidores Push a traves de JPA.
 * 
 * @author 
 * 
 */
@Service("servicioServidorWebPushImpl")
public class ServicioServidorWebPushImpl implements ServicioServidorWebPush{
	
	protected static final String NOMBRE = "nombre";

	protected static final String R_CONST_REF = "2";

	protected static final String S = "S";

	/** Constante ERRORS_ORGANISMO_GET_ORGANISMOS. */
	private static final String ERRORS_ORGANISMO_GET_ORGANISMOS = "errors.organismo.getOrganismos";

	/**  logger. */
	private static Logger logger = Logger.getLogger(ServicioServidorWebPushImpl.class);
	
	/**  tbl servidores manager. */
	@Resource(name="TblServidoresManagerImpl")
	TblServidoresManager tblServidoresManager;
	
	/**  tbl planificaciones manager. */
	@Resource(name="TblPlanificacionesManagerImpl")
	TblPlanificacionesManager tblPlanificacionesManager;
	
	/**  tbl servidores servicios manager. */
	@Resource(name="TblServidoresServiciosManagerImpl")
	TblServidoresServiciosManager tblServidoresServiciosManager;
	
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorWebPush#getServidoresWebPush(int)
	 */
	///MIGRADO
	@Override
	public List<ServidorWebPushBean> getServidoresWebPush(int tipoServidor)
			throws BusinessException {
		try {
			TblServidoresQuery query = new TblServidoresQuery();
			query.setTipo(tipoServidor);
			query.setEliminadoIsNull(true);
			List<TblServidores> lista = tblServidoresManager.getServidoresByQuery(query);
			return getListViewServidorPushBean(lista);					
		} catch (Exception e){
			logger.error("ServicioServidorPushImpl - getServidoresPush:" + e);
			throw new BusinessException(e,ERRORS_ORGANISMO_GET_ORGANISMOS);	
		}
	}	
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorWebPush#getServidoresPush(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.ServidorWebPushBean, int)
	 */
	@Override
	public PaginatedList<ServidorWebPushBean> getServidoresPush(int start, int size, String order, String columnSort,
			ServidorWebPushBean criterio, int tipoServidor) throws BusinessException {
		String nombre = null;
		
		try {
			//Columna para ordenar
			HashMap<String, String> columns = new HashMap<>();
			columns.put(R_CONST_REF,NOMBRE);
			
			if (columnSort==null) {
				columnSort = R_CONST_REF;
			} 
				//Id
			
			String column = columns.get(columnSort);
			if (column==null) {
				column = NOMBRE;
			}
			
			if (null != criterio && null != criterio.getNombre()){
				nombre = criterio.getNombre();
			}
			
			List<TblServidores> lista = tblServidoresManager.getServidoresPaginado(start, size, order, column, nombre, tipoServidor, false);
			List<ServidorWebPushBean> pageList = getListViewServidorPushBean(lista);
			
			// Total de organismos
			Integer rowcount = tblServidoresManager.getServidoresPaginado(start, size, order, column, nombre, tipoServidor, true).size();
			
			PaginatedList<ServidorWebPushBean> result = new PaginatedList<>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);
			
			return result;
		} catch (Exception e){
			logger.error("ServicioServidorWebPushImpl - getServidoresPush:" + e);
			throw new BusinessException(e,ERRORS_ORGANISMO_GET_ORGANISMOS);
			
		}

	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorWebPush#newServidorWebPush(es.mpr.plataformamensajeria.beans.ServidorWebPushBean, int, java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	@Transactional
	public Long newServidorWebPush(ServidorWebPushBean servidor, int tipoServidor, String source, String accion, Long accionId) throws BusinessException {
		try{
			TblServidores servidorTO = getServidoresWebPushJPA(servidor);
			servidorTO.setTipo(tipoServidor);
			servidorTO.setFechacreacion(new Date());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			servidorTO.setCreadopor(modificador);
			Long idServidor = tblServidoresManager.insert(servidorTO, source, accion, accionId);
			
			servidor.setServidorWebPushId(idServidor);
			servidor.setActivo(servidor.getActivo());
			servidor.setFechacreacion(servidorTO.getFechacreacion());
			servidor.setCreadopor(servidorTO.getCreadopor());
			return servidor.getServidorWebPushId();

		}catch (Exception e){
			logger.error("ServicioServidoresWebPush - newServidorWebPush:" + e);
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}


/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorWebPush#updateServidorWebPush(es.mpr.plataformamensajeria.beans.ServidorWebPushBean, java.lang.String, java.lang.String, java.lang.Long)
 */
////MIGRADO
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateServidorWebPush(ServidorWebPushBean servidor, String source, String accion, Long accionId) throws BusinessException {
		try {
			TblServidores servidorTO = getServidoresWebPushJPA(servidor);
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			servidorTO.setModificadopor(modificador);
			servidorTO.setFechamodificacion(new Date());
			tblServidoresManager.update(servidorTO, source, accion, accionId);
		} catch (Exception e){
			logger.error("ServicioServidoresWebPush - updateServidorWebPush:" + e);
			throw new BusinessException(e,"errors.organismo.updateOrganismo");		
		}	
		
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorWebPush#loadServidorWebPush(es.mpr.plataformamensajeria.beans.ServidorWebPushBean)
	 */
	@Override
	@Transactional
	public ServidorWebPushBean loadServidorWebPush(ServidorWebPushBean servidor) throws BusinessException {
		try {
			TblServidores serv =tblServidoresManager.getServidorById(servidor.getServidorWebPushId());
			return getServidorPushBean(serv);
		} catch (Exception e){
			logger.error("ServicioServidoresPush - loadServidorPush:" + e);
			throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorWebPush#deleteServidorWebPush(es.mpr.plataformamensajeria.beans.ServidorWebPushBean, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	@Override
	@Transactional
	public void deleteServidorWebPush(ServidorWebPushBean proveedor, String accionServidor, Long accionIdServidor, String source, String accionPlanificacion, 
			Long accionIdPlanificacion, String descripcion) throws BusinessException {
		try {
			TblServidores servidorTO = tblServidoresManager.getServidorById(proveedor.getServidorWebPushId());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			List<TblPlanificaciones> listaPlanificaciones = tblPlanificacionesManager.getPlanificacionesByServidor(servidorTO.getServidorid());
			for (TblPlanificaciones p : listaPlanificaciones) {
				p.setModificadopor(modificador);
				p.setFechamodificacion(new Date());
				p.setEliminado(S);
				
				tblPlanificacionesManager.updatePlanificacion(p, source, accionPlanificacion, accionIdPlanificacion, descripcion);
			}
			
			servidorTO.setEliminado(S);
			servidorTO.setModificadopor(modificador);
			servidorTO.setFechamodificacion(new Date());
			tblServidoresManager.update(servidorTO, source, accionServidor, accionIdServidor);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException("Error eliminando proveedor sms y planificaciones asociadas");
		}
		
	}
	
	/**
	 * <p>Obtenemos un objeto OrganismoJPA a partir de un objeto OrganismoBean</p>.
	 *
	 * @param servidorPush the servidor push
	 * @return objeto OrganismoJPA
	 */
	
	protected TblServidores getServidoresWebPushJPA(ServidorWebPushBean servidorPush) {
		TblServidores servidorTO = new TblServidores();

		servidorTO.setActivo(servidorPush.getActivo());
		servidorTO.setCreadopor(servidorPush.getCreadopor());
		servidorTO.setDescripcion(servidorPush.getDescripcion());
		servidorTO.setEliminado(servidorPush.getEliminado());
		servidorTO.setFechacreacion(servidorPush.getFechacreacion());
		servidorTO.setServidorid(servidorPush.getServidorWebPushId());
		servidorTO.setNombre(servidorPush.getNombre());
		servidorTO.setPordefecto(servidorPush.getPordefecto());
		servidorTO.setServidorid(servidorPush.getServidorWebPushId());
		servidorTO.setTipo(servidorPush.getTipo());
		return servidorTO;
	}
	
	
	/**
	 * <p>Obtenemos un objeto ServidorPushBean a partir de un objeto ServidoresPushJPA</p>.
	 *
	 * @param serv the serv
	 * @return objeto ServidorPushBean
	 */
	protected ServidorWebPushBean getServidorPushBean(TblServidores serv) {
		ServidorWebPushBean servidor = new ServidorWebPushBean();

		servidor.setActivo(serv.getActivo());
		servidor.setCreadopor(serv.getCreadopor());
		servidor.setDescripcion(serv.getDescripcion());
		servidor.setFechacreacion((null != serv.getFechacreacion() ) ? DateUtils.truncate(serv.getFechacreacion(), Calendar.DATE) : null);
		servidor.setFechamodificacion((null != serv.getFechamodificacion() ) ? DateUtils.truncate(serv.getFechamodificacion(), Calendar.DATE) : null);
		servidor.setModificadopor(serv.getModificadopor());
		servidor.setNombre(serv.getNombre());
		servidor.setPordefecto(serv.getPordefecto());
		servidor.setServidorWebPushId(serv.getServidorid());
		servidor.setTipo(serv.getTipo());

		return servidor;
	}
	
	/**
	 * <p>Convertirmos una lista de ViewServidoresPushJPA a una lista de ServidorPushBean</p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<ServidorWebPushBean> getListViewServidorPushBean(List<TblServidores> lista) {
	
		List<ServidorWebPushBean> result = null;
		
		if (lista!=null && !lista.isEmpty()){
			result = new ArrayList<>();
			for (TblServidores s : lista) {
				ServidorWebPushBean servidor = new ServidorWebPushBean();
				servidor.setActivo(s.getActivo());
				servidor.setCreadopor(s.getCreadopor());
				servidor.setDescripcion(s.getDescripcion());
				servidor.setFechacreacion((null != s.getFechacreacion() ) ? DateUtils.truncate(s.getFechacreacion(), Calendar.DATE) : null);
				servidor.setFechamodificacion((null != s.getFechamodificacion() ) ? DateUtils.truncate(s.getFechamodificacion(), Calendar.DATE) : null);
				servidor.setModificadopor(s.getModificadopor());
				servidor.setNombre(s.getNombre());
				servidor.setPordefecto(s.getPordefecto());
				servidor.setServidorWebPushId(s.getServidorid());
				servidor.setTipo(s.getTipo());
				result.add(servidor);
			}
		}
			
		return result;
	}

	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorWebPush#getServidoresWebPushNoAsignados(java.lang.Integer, int)
	 */
	/////MIGRADO
	@Override
	public List<ServidorWebPushBean> getServidoresWebPushNoAsignados(
			Integer idServicio, int tipoServidor) throws BusinessException {
		ArrayList<ServidorWebPushBean> listaServidores = new ArrayList<>();
		try{
			TblServidoresQuery query = new TblServidoresQuery();
			query.setEliminadoIsNull(true);
			for (Long id : tblServidoresServiciosManager.getServidoresFromServidoresServiciosByServicio(idServicio.longValue())) {
				query.addServidoridNotIn(id);
			}
			query.setTipo(tipoServidor);
			List<TblServidores> lista = tblServidoresManager.getServidoresByQuery(query);
			
			for (TblServidores tblServidores : lista) {
				ServidorWebPushBean servidorBean = new ServidorWebPushBean();
				servidorBean.setServidorWebPushId(tblServidores.getServidorid());
				servidorBean.setNombre(tblServidores.getNombre());
				listaServidores.add(servidorBean);
			}
			
		} catch (Exception e) {
			logger.error("ServicioProveedorSMS - getProveedoresSMSNoAsignados:" + e);
			throw new BusinessException(e, ERRORS_ORGANISMO_GET_ORGANISMOS);
		}
		
		return listaServidores;
	}
	
}
