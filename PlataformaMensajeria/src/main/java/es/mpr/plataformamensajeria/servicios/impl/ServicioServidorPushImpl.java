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
import es.mpr.plataformamensajeria.beans.ServidorPushBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorPush;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de servidores Push a traves de JPA.
 * 
 * @author 
 * 
 */
@Service("servicioServidorPushImpl")
public class ServicioServidorPushImpl implements ServicioServidorPush{
	
	private static Logger logger = Logger.getLogger(ServicioServidorPushImpl.class);
	
	@Resource(name="TblServidoresManagerImpl")
	TblServidoresManager tblServidoresManager;
	
	@Resource(name="TblPlanificacionesManagerImpl")
	TblPlanificacionesManager tblPlanificacionesManager;
	
	@Resource(name="TblServidoresServiciosManagerImpl")
	TblServidoresServiciosManager tblServidoresServiciosManager;
	
	
	///MIGRADO
	@Override
	public List<ServidorPushBean> getServidoresPush(int tipoServidor)
			throws BusinessException {
		try {
			TblServidoresQuery query = new TblServidoresQuery();
			query.setTipo(tipoServidor);
			query.setEliminadoIsNull(true);
			List<TblServidores> lista = tblServidoresManager.getServidoresByQuery(query);
			List<ServidorPushBean> result = getListViewServidorPushBean(lista);					
			
			return result;		
		} 
		catch (Exception e){
			logger.error("ServicioServidorPushImpl - getServidoresPush:" + e);
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}	
	
////MIGRADO
	@Override
	public PaginatedList<ServidorPushBean> getServidoresPush(int start, int size,
			String order, String columnSort, ServidorPushBean criterio, int tipoServidor)
			throws BusinessException {
		String nombre = null;
		
		try {
			//Columna para ordenar
			Hashtable<String, String> columns = new Hashtable<String,String>();
			columns.put("2","nombre");
			
			if (columnSort==null)
				columnSort = "2"; //Id
			
			String column = columns.get(columnSort);
			if (column==null)
				column = "nombre";
			
			if (null != criterio && null != criterio.getNombre()){
				nombre = criterio.getNombre();
			}
			
			List<TblServidores> lista = tblServidoresManager.getServidoresPaginado(start, size, order, column, nombre, tipoServidor, false);
			List<ServidorPushBean> pageList = getListViewServidorPushBean(lista);
			
			// Total de organismos
			Integer rowcount = tblServidoresManager.getServidoresPaginado(start, size, order, column, nombre, tipoServidor, true).size();
			
			PaginatedList<ServidorPushBean> result = new PaginatedList<ServidorPushBean>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);
			
			return result;
		}
		catch (Exception e){
			logger.error("ServicioServidorPushImpl - getServidoresPush:" + e);
			throw new BusinessException(e,"errors.organismo.getOrganismos");
			
		}
	}

////MIGRADO
	@Override
	@Transactional
	public Long newServidorPush(ServidorPushBean servidor, int tipoServidor, String source, String accion, Long accionId) throws BusinessException {
		try{
			TblServidores servidorTO = getServidoresPushJPA(servidor);
			servidorTO.setTipo(tipoServidor);
			servidorTO.setFechacreacion(new Date());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			servidorTO.setCreadopor(modificador);
			Long idServidor = tblServidoresManager.insert(servidorTO, source, accion, accionId);
			
			servidor.setServidorPushId(idServidor);
			servidor.setActivo(servidor.getActivo());
			servidor.setFechacreacion(servidorTO.getFechacreacion());
			servidor.setCreadopor(servidorTO.getCreadopor());
			return servidor.getServidorPushId();

		}catch (Exception e){
			logger.error("ServicioServidoresPush - newServidorPush:" + e);
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}


////MIGRADO
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateServidorPush(ServidorPushBean servidor, String source, String accion, Long accionId) throws BusinessException {
		try {
			TblServidores servidorTO = getServidoresPushJPA(servidor);
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			servidorTO.setModificadopor(modificador);
			servidorTO.setFechamodificacion(new Date());
			tblServidoresManager.update(servidorTO, source, accion, accionId);
		}
		catch (Exception e){
			logger.error("ServicioServidoresPush - updateServidorPush:" + e);
			throw new BusinessException(e,"errors.organismo.updateOrganismo");		
		}	
		
	}

	////MIGRADO
	@Override
	@Transactional
	public ServidorPushBean loadServidorPush(ServidorPushBean servidor) throws BusinessException {
		try {
			TblServidores serv =tblServidoresManager.getServidorById(servidor.getServidorPushId());
			return getServidorPushBean(serv);
		}
		catch (Exception e){
			logger.error("ServicioServidoresPush - loadServidorPush:" + e);
			throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}

	///MIGRADO
	@Override
	@Transactional
	public void deleteServidorPush(ServidorPushBean proveedor, String accionServidor, Long accionIdServidor, String source, String accionPlanificacion, 
			Long accionIdPlanificacion, String descripcion) throws BusinessException {
		try {
			TblServidores servidorTO = tblServidoresManager.getServidorById(proveedor.getServidorPushId());
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
		}catch (Exception e) {
			throw new BusinessException("Error eliminando proveedor sms y planificaciones asociadas");
		}
		
	}
	
	/**
	 * <p>Obtenemos un objeto OrganismoJPA a partir de un objeto OrganismoBean</p>
	 * 
	 * @param organismoBean 
	 * 
	 * @return objeto OrganismoJPA
	 */
	////MIGRADO
	protected TblServidores getServidoresPushJPA(ServidorPushBean servidorPush) throws BusinessException
	{
		TblServidores servidorTO = new TblServidores();
		try{
			Date defaultValue = null;         
			DateConverter converter = new DateConverter (defaultValue);         
			ConvertUtils.register (converter, java.util.Date.class);
			BeanUtils.copyProperties(servidorTO,servidorPush);
			servidorTO.setServidorid(servidorPush.getServidorPushId());
			servidorTO.setFechacreacion(servidorPush.getFechacreacion());
			servidorTO.setPlataforma(servidorPush.getPlataformaid());
		}catch (IllegalAccessException  e) {
			logger.error("ServicioServidorPushImpl - getServidoresPushJPA:" + e);
		}catch (InvocationTargetException e){
			logger.error("ServicioServidorPushImpl - getServidoresPushJPA:" + e);
		}
		
		return servidorTO;
	}
	
	
	/**
	 * <p>Obtenemos un objeto ServidorPushBean a partir de un objeto ServidoresPushJPA</p>
	 * 
	 * @param servidorJPA
	 * 
	 * @return objeto ServidorPushBean
	 */
	////MIGRADO
	protected ServidorPushBean getServidorPushBean(TblServidores serv) throws BusinessException
	{
		ServidorPushBean servidor = new ServidorPushBean();
		
		try {
			BeanUtils.copyProperties(servidor, serv);
			servidor.setServidorPushId(serv.getServidorid());
			servidor.setPlataformaid(serv.getPlataforma());
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}
		
		return servidor;
	}
	/**
	 * <p>Convertirmos una lista de ViewServidoresPushJPA a una lista de ServidorPushBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	////MIGRADO
	protected List<ServidorPushBean> getListViewServidorPushBean(List<TblServidores> lista) throws BusinessException
	{	
		List<ServidorPushBean> result = null;
		
		if (lista!=null && !lista.isEmpty()){
			result = new ArrayList<ServidorPushBean>();
			for (TblServidores s : lista) {
				ServidorPushBean servidor =  new ServidorPushBean();
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(servidor, s);
					servidor.setServidorPushId(s.getServidorid());
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(servidor);
			}
		}
			
		return result;
	}

	
	/////MIGRADO
	@Override
	public List<ServidorPushBean> getServidoresPushNoAsignados(
			Integer idServicio, int tipoServidor) throws BusinessException {
		ArrayList<ServidorPushBean> listaServidores = new ArrayList<ServidorPushBean>();
		try{
			TblServidoresQuery query = new TblServidoresQuery();
			query.setEliminadoIsNull(true);
			for (Long id : tblServidoresServiciosManager.getServidoresFromServidoresServiciosByServicio(idServicio.longValue())) {
				query.addServidoridNotIn(id);
			}
			query.setTipo(tipoServidor);
			List<TblServidores> lista = tblServidoresManager.getServidoresByQuery(query);
			
			for (TblServidores tblServidores : lista) {
				ServidorPushBean servidorBean = new ServidorPushBean();
				servidorBean.setServidorPushId(tblServidores.getServidorid());
				servidorBean.setNombre(tblServidores.getNombre());
				listaServidores.add(servidorBean);
			}
			
		} catch (Exception e) {
			logger.error("ServicioProveedorSMS - getProveedoresSMSNoAsignados:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
		
		return listaServidores;
	}
	
}
