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
import es.mpr.plataformamensajeria.beans.ReceptorSMSBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioReceptorSMS;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia y busqueda de servidores a traves de JPA.
 * 
 * @author 
 * 
 */
@Service("servicioReceptorSMSImpl")
public class ServicioReceptorSMSImpl implements ServicioReceptorSMS{
	
	private static Logger logger = Logger.getLogger(ServicioReceptorSMSImpl.class);
	
	@Resource(name="TblServidoresManagerImpl")
	TblServidoresManager tblServidoresManager;
	
	@Resource(name="TblPlanificacionesManagerImpl")
	TblPlanificacionesManager tblPlanificacionesManager;
	
	@Resource(name="TblServidoresServiciosManagerImpl")
	TblServidoresServiciosManager tblServidoresServiciosManager;
	
	
	
	////MIGRADO
	@Override
	public List<ReceptorSMSBean> getReceptoresSMS(int tipoServidor)
			throws BusinessException {
		
		try {
			TblServidoresQuery query = new TblServidoresQuery();
			query.setTipo(tipoServidor);
			query.setEliminadoIsNull(true);
			List<TblServidores> lista = tblServidoresManager.getServidoresByQuery(query);
			List<ReceptorSMSBean> result = getListReceptorSMSBean(lista);					
			
			return result;
		} 
		catch (Exception e){
			logger.error("ServicioReceptorSMS - getReceptoresSMS:" + e);
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}	
	

	////MIGRADO
	@Override
	public PaginatedList<ReceptorSMSBean> getReceptoresSMS(int start, int size,
			String order, String columnSort, ReceptorSMSBean criterio, int tipoServidor)
			throws BusinessException {
		String nombre = null;
		try {
			// Columna para ordenar
			Hashtable<String, String> columns = new Hashtable<String, String>();
			columns.put("2", "nombre");
			
			if (columnSort == null)
				columnSort = "2"; // Id

			String column = columns.get(columnSort);
			if (column == null)
				column = "nombre";

			if (null != criterio && null != criterio.getNombre()) {
				nombre = criterio.getNombre();
			}
			List<TblServidores> lista = tblServidoresManager.getServidoresPaginado(start, size, order, column, nombre,
					tipoServidor, false);
			List<ReceptorSMSBean> pageList = getListReceptorSMSBean(lista);

			// Total de organismos
			Integer rowcount = tblServidoresManager.getServidoresPaginado(start, size, order, column, nombre, tipoServidor, true).size();

			PaginatedList<ReceptorSMSBean> result = new PaginatedList<ReceptorSMSBean>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e){
			logger.error("ServicioReceptorSMS - getReceptoresSMS:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");	
		}
	}

	

	//////MIGRADO
	@Override
	@Transactional
	public Long newReceptorSMS(ReceptorSMSBean servidor, int tipoServidor, String source, String accion, Long accionId) throws BusinessException {
		try{
			TblServidores servidorTO = getReceptoresSMSTO(servidor);
			servidorTO.setTipo(tipoServidor);
			servidorTO.setFechacreacion(new Date());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			servidorTO.setCreadopor(modificador);
			Long idServidor = tblServidoresManager.insert(servidorTO, source, accion, accionId);
						
			servidor.setReceptorSMSId(idServidor);
			servidor.setNombre(servidorTO.getNombre());
			servidor.setDescripcion(servidorTO.getDescripcion());
			servidor.setActivo(servidorTO.getActivo());
			servidor.setFechacreacion(servidorTO.getFechacreacion());
			servidor.setCreadopor(servidorTO.getCreadopor());
			servidor.setFechamodificacion(servidorTO.getFechamodificacion());
			return servidor.getReceptorSMSId();
		}catch (Exception e){
			logger.error("ServicioReceptorSMS - newReceptorSMS:" + e);
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}


////MIGRADO
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateReceptorSMS(ReceptorSMSBean servidor, String source, String accion, Long accionId) throws BusinessException {
		try {
			TblServidores servidorTO = getReceptoresSMSTO(servidor);
			servidorTO.setFechamodificacion(new Date());
			servidorTO.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			servidorTO.setFechacreacion(servidor.getFechacreacion());
			tblServidoresManager.update(servidorTO, source, accion, accionId);
		}
		catch (Exception e){
			logger.error("ServicioReceptorSMS - updateReceptorSMS:" + e);
			throw new BusinessException(e,"errors.organismo.updateOrganismo");		
		}	
		
	}

	////////MIGRADO
	@Override
	@Transactional
	public ReceptorSMSBean loadReceptorSMS(ReceptorSMSBean servidor)	throws BusinessException {
		try {
			TblServidores serv =tblServidoresManager.getServidorById(servidor.getReceptorSMSId());
			return getReceptorSMSBean(serv);
		}
		catch (Exception e){
			logger.error("ServicioReceptorSMS - loadReceptorSMS:" + e);
			throw new BusinessException(e,"errors.organismo.loadOrganismo");			
		}
	}

	////MIGRADO
	@Override
	@Transactional
	public void deleteReceptorSMS(ReceptorSMSBean receptor, String accionServidor, Long accionIdServidor, String source, String accionPlanificacion, 
			Long accionIdPlanificacion, String descripcion) throws BusinessException {
		try {
			TblServidores servidorTO = tblServidoresManager.getServidorById(receptor.getReceptorSMSId());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			List<TblPlanificaciones> listaPlanificaciones = tblPlanificacionesManager.getPlanificacionesByServidor(servidorTO.getServidorid());
			for (TblPlanificaciones p : listaPlanificaciones) {
				p.setModificadopor(modificador);
				p.setFechamodificacion(new Date());
				p.setEliminado("S");
				tblPlanificacionesManager.updatePlanificacion(p, source, accionPlanificacion, accionIdPlanificacion, descripcion);
			}
			servidorTO.setModificadopor(modificador);
			servidorTO.setFechamodificacion(new Date());
			servidorTO.setEliminado("S");
			tblServidoresManager.update(servidorTO, source, accionServidor, accionIdServidor);
			return;
		}catch (Exception e) {
			throw new BusinessException("Error eliminando receptor sms y planificaciones asociadas");
		}
		
	}
	
	/**
	 * <p>Obtenemos un objeto OrganismoJPA a partir de un objeto OrganismoBean</p>
	 * 
	 * @param organismoBean 
	 * 
	 * @return objeto OrganismoJPA
	 */
	///MIGRADO
	protected TblServidores getReceptoresSMSTO(ReceptorSMSBean receptorSMS) throws BusinessException
	{
		TblServidores servidorTO = new TblServidores();
		
		try {
			Date defaultValue = null;         
			DateConverter converter = new DateConverter (defaultValue);         
			ConvertUtils.register (converter, java.util.Date.class);
			BeanUtils.copyProperties(servidorTO,receptorSMS);
			servidorTO.setServidorid(receptorSMS.getReceptorSMSId());
			servidorTO.setFechacreacion(receptorSMS.getFechacreacion());
		} catch (IllegalAccessException  e) {
			logger.error("ServicioReceptorImpl - getServidorTO:" + e);
		}catch (InvocationTargetException e){
			logger.error("ServicioReceptorImpl - getServidorTO:" + e);
		}
		
		return servidorTO;
	}
	
	
	/**
	 * <p>Obtenemos un objeto ReceptorSMSBean a partir de un objeto ReceptoresSMSJPA</p>
	 * 
	 * @param servidorJPA
	 * 
	 * @return objeto ReceptorSMSBean
	 */
	////MIGRADO
	protected ReceptorSMSBean getReceptorSMSBean(TblServidores serv) throws BusinessException
	{
		ReceptorSMSBean servidor = new ReceptorSMSBean();
		
		try {
			BeanUtils.copyProperties(servidor, serv);
			servidor.setReceptorSMSId(serv.getServidorid());
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}
		
		return servidor;
	}
	/**
	 * <p>Convertirmos una lista de ViewReceptoresSMSJPA a una lista de ReceptorSMSBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	//////MIGRADO
	protected List<ReceptorSMSBean> getListReceptorSMSBean(List<TblServidores> lista) throws BusinessException
	{	
		List<ReceptorSMSBean> result = null;
		
		if (lista!=null && !lista.isEmpty()){
			result = new ArrayList<ReceptorSMSBean>();
		
			for (TblServidores s : lista) {
				ReceptorSMSBean servidor =  new ReceptorSMSBean();
			
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);         
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(servidor, s);
					servidor.setReceptorSMSId(s.getServidorid());
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

	///MIGRADO
	@Override
	public List<ReceptorSMSBean> getReceptoresSMSNoAsignados(
			Integer idServicio, int tipoServidor) throws BusinessException {
		ArrayList<ReceptorSMSBean> listaServidores = new ArrayList<ReceptorSMSBean>();
	
		try{
			TblServidoresQuery query = new TblServidoresQuery();
			query.setEliminadoIsNull(true);

			query.setTipo(tipoServidor);
			List<TblServidores> lista = tblServidoresManager.getServidoresByQuery(query);
			
			for (TblServidores tblServidores : lista) {
				ReceptorSMSBean servidorBean = new ReceptorSMSBean();
				servidorBean.setReceptorSMSId(tblServidores.getServidorid());
				servidorBean.setNombre(tblServidores.getNombre());
				listaServidores.add(servidorBean);
			}
			
		} catch (Exception e) {
			logger.error("ServicioReceptorSMS - getReceptoresSMSNoAsignados:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
		
		return listaServidores;
	}
}
