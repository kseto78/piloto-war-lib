package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

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

import es.minhap.plataformamensajeria.iop.manager.TblPlanificacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresServiciosManager;
import es.minhap.sim.model.TblPlanificaciones;
import es.minhap.sim.model.TblServidores;
import es.minhap.sim.query.TblServidoresQuery;
import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;


// TODO: Auto-generated Javadoc
/**
 * <p>Maneja la persistencia y b&uacute;squeda de servidores a traves de JPA.
 * 
 * @author 
 * 
 */
@Service("servicioProveedorSMSImpl")
public class ServicioProveedorSMSImpl implements ServicioProveedorSMS{
	
	protected static final String SERVICIOPROVEED = "ServicioProveedorSMS - getProveedoresSMS:";

	protected static final String NOMBRE = "nombre";

	protected static final String SERVICIOPROVEED0 = "ServicioProveedorImpl - getProveedoresSMSTO:";

	protected static final String ERRORSDOTORGANI = "errors.organismo.getOrganismos";

	protected static final String R_CONST_REF = "2";

	protected static final String S = "S";

	/**  logger. */
	private static Logger logger = Logger.getLogger(ServicioProveedorSMSImpl.class);
	
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
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS#getProveedoresSMS(int)
	 */
	////MIGRADO
	@Override
	public List<ProveedorSMSBean> getProveedoresSMS(int tipoServidor)
			throws BusinessException {
	
		try {
			TblServidoresQuery query = new TblServidoresQuery();
			query.setTipo(tipoServidor);
			query.setEliminadoIsNull(true);
			List<TblServidores> lista = tblServidoresManager.getServidoresByQuery(query);
			
			return getListViewProveedorSMSBean(lista);
			
		} catch (Exception e){
			logger.error(SERVICIOPROVEED + e);
			throw new BusinessException(e,ERRORSDOTORGANI);	
		}
	}	
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS#getProveedoresSMS(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.ProveedorSMSBean, int)
	 */
	////MIGRADO
	@Override
	public PaginatedList<ProveedorSMSBean> getProveedoresSMS(int start, int size,
			String order, String columnSort, ProveedorSMSBean criterio, int tipoServidor)
			throws BusinessException {
		String nombre = null;
		try {
			//Columna para ordenar
			Hashtable<String, String> columns = new Hashtable<>();
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
			List<ProveedorSMSBean> pageList = getListViewProveedorSMSBean(lista);
			
			// Total de organismos
			Integer rowcount = tblServidoresManager.getServidoresPaginado(start, size, order, column, nombre, tipoServidor, true).size();
			
			PaginatedList<ProveedorSMSBean> result = new PaginatedList<>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);
			
			return result;
		} catch (Exception e){
			logger.error(SERVICIOPROVEED + e);
			throw new BusinessException(e, ERRORSDOTORGANI);
			
		}
	}


/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS#newProveedorSMS(es.mpr.plataformamensajeria.beans.ProveedorSMSBean, int, java.lang.String, java.lang.String, java.lang.Long)
 */
///////////MIGRADO
	@Override
	@Transactional
	public Long newProveedorSMS(ProveedorSMSBean servidor, int tipoServidor, String source, String accion, Long accionId) throws BusinessException {
		try{
			TblServidores servidorTO = getProveedoresSMSTO(servidor);
			servidorTO.setTipo(tipoServidor);
			servidorTO.setFechacreacion(new Date());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			servidorTO.setCreadopor(modificador);
			Long idServidor = tblServidoresManager.insert(servidorTO, source, accion, accionId);
			
			servidor.setProveedorSMSId(idServidor);
			servidor.setActivo(servidor.getActivo());
			servidor.setFechacreacion(servidorTO.getFechacreacion());
			servidor.setCreadopor(servidorTO.getCreadopor());
			return servidor.getProveedorSMSId();
		}catch (Exception e){
			logger.error("ServicioProveedorSMS - newProveedorSMS:" + e);
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}


/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS#updateProveedorSMS(es.mpr.plataformamensajeria.beans.ProveedorSMSBean, java.lang.String, java.lang.String, java.lang.Long)
 */
//////MIGRADO
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateProveedorSMS(ProveedorSMSBean servidor, String source, String accion, Long accionId) throws BusinessException {
		try {
			TblServidores servidorTO = getProveedoresSMSTO(servidor);
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			servidorTO.setModificadopor(modificador);
			servidorTO.setFechamodificacion(new Date());
			tblServidoresManager.update(servidorTO, source, accion, accionId);
		} catch (Exception e){
			logger.error("ServicioProveedorSMS - updateProveedorSMS:" + e);
			throw new BusinessException(e,"errors.organismo.updateOrganismo");		
		}	
		
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS#loadProveedorSMS(es.mpr.plataformamensajeria.beans.ProveedorSMSBean)
	 */
	////MIGRADO
	@Override
	@Transactional
	public ProveedorSMSBean loadProveedorSMS(ProveedorSMSBean servidor)	throws BusinessException {
		try {
			TblServidores serv =tblServidoresManager.getServidorById(servidor.getProveedorSMSId());
			return getProveedorSMSBean(serv);
		} catch (Exception e){
			logger.error("ServicioProveedorSMS - loadProveedorSMS:" + e);
			throw new BusinessException(e, ERRORSDOTORGANI);		
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS#deleteProveedorSMS(es.mpr.plataformamensajeria.beans.ProveedorSMSBean, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	//////MIGRADO
	@Override
	@Transactional
	public void deleteProveedorSMS(ProveedorSMSBean proveedor, String accionServidor, Long accionIdServidor, String source, String accionPlanificacion, 
			Long accionIdPlanificacion, String descripcion) throws BusinessException {
		try {
			TblServidores servidorTO = tblServidoresManager.getServidorById(proveedor.getProveedorSMSId());
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
			logger.error("ServicioProveedorSMSImpl - deleteProveedorSMS:" + e);
			throw new BusinessException("Error eliminando proveedor sms y planificaciones asociadas");
		}
		
	}
	
	/**
	 * <p>Obtenemos un objeto proveedorTO a partir de un objeto ProveedorSMSBean</p>.
	 *
	 * @param proveedorSMS the proveedor SMS
	 * @return objeto OrganismoJPA
	 * @throws BusinessException the business exception
	 */
	/////MIGRADO
	protected TblServidores getProveedoresSMSTO(ProveedorSMSBean proveedorSMS) throws BusinessException {
		TblServidores servidorTO = new TblServidores();
		
		try {
			BeanUtils.copyProperties(servidorTO,proveedorSMS);
			servidorTO.setServidorid(proveedorSMS.getProveedorSMSId());
			//false = Recepción de Estado / true= Consulta de Estado
			servidorTO.setFechacreacion(proveedorSMS.getFechacreacion());
			servidorTO.setMetodoconsulta("true".equals(proveedorSMS.getMetodoconsulta()));
			
		} catch (IllegalAccessException | InvocationTargetException e){
			logger.error(SERVICIOPROVEED0 + e);
		}
		return servidorTO;
	}
	
	
	/**
	 * <p>Obtenemos un objeto ProveedorSMSBean a partir de un objeto ProveedoresSMSJPA</p>.
	 *
	 * @param serv the serv
	 * @return objeto ProveedorSMSBean
	 * @throws BusinessException the business exception
	 */
	//////MIGRADO
	protected ProveedorSMSBean getProveedorSMSBean(TblServidores serv) throws BusinessException {
		ProveedorSMSBean servidor = new ProveedorSMSBean();
		
		try {
			BeanUtils.copyProperties(servidor, serv);
			//0= Recepción de Estado / 1= Consulta de Estado 
			servidor.setMetodoconsulta(String.valueOf(serv.getMetodoconsulta()));
			servidor.setProveedorSMSId(serv.getServidorid());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new BusinessException(e);
		}
		
		return servidor;
	}
	
	/**
	 * <p>Convertirmos una lista de ViewProveedoresSMSJPA a una lista de ProveedorSMSBean</p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos OrganismoBean
	 * @throws BusinessException the business exception
	 */
	//////MIGRADO
	protected List<ProveedorSMSBean> getListViewProveedorSMSBean(List<TblServidores> lista) throws BusinessException {
	
		List<ProveedorSMSBean> result = null;
		
		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();
		
			for (TblServidores s : lista) {
				ProveedorSMSBean servidor =  new ProveedorSMSBean();
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(servidor, s);
					servidor.setProveedorSMSId(s.getServidorid());
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(servidor);
			}
		}
			
		return result;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS#getProveedoresSMSNoAsignados(java.lang.Integer, int)
	 */
	/////MIGRADOS
	@Override
	public List<ProveedorSMSBean> getProveedoresSMSNoAsignados(
			Integer idServicio, int tipoServidor) throws BusinessException {
		ArrayList<ProveedorSMSBean> listaServidores = new ArrayList<>();
		try{
			TblServidoresQuery query = new TblServidoresQuery();
			query.setEliminadoIsNull(true);
			for (Long id : tblServidoresServiciosManager.getServidoresFromServidoresServiciosByServicio(idServicio.longValue())) {
				query.addServidoridNotIn(id);
			}
			query.setTipo(tipoServidor);
			List<TblServidores> lista = tblServidoresManager.getServidoresByQuery(query);
			
			for (TblServidores tblServidores : lista) {
				ProveedorSMSBean servidorBean = new ProveedorSMSBean();
				servidorBean.setProveedorSMSId(tblServidores.getServidorid());
				servidorBean.setNombre(tblServidores.getNombre());
				listaServidores.add(servidorBean);
			}
			
		} catch (Exception e) {
			logger.error("ServicioProveedorSMS - getProveedoresSMSNoAsignados:" + e);
			throw new BusinessException(e, ERRORSDOTORGANI);
		}
		return listaServidores;
	}
}
