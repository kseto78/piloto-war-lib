package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
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
import es.minhap.plataformamensajeria.iop.beans.OrganismosServicioBean;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblOrganismosManager;
import es.minhap.plataformamensajeria.iop.manager.TblOrganismosServicioManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosAplicacionesManager;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblUsuariosAplicaciones;
import es.minhap.sim.query.TblAplicacionesQuery;
import es.minhap.sim.query.TblOrganismosQuery;
import es.minhap.sim.query.TblOrganismosServicioQuery;
import es.minhap.sim.query.TblServiciosQuery;
import es.minhap.sim.query.TblUsuariosAplicacionesQuery;
import es.minhap.sim.query.TblUsuariosQuery;
import es.mpr.plataformamensajeria.beans.OrganismoBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de aplicaciones a traves de JPA.
 * 
 * @author 
 * 
 */
@Service("servicioOrganismoImpl")
public class ServicioOrganismoImpl implements ServicioOrganismo{
	
	private static Logger logger = Logger.getLogger(ServicioOrganismoImpl.class);
	
	@Resource(name="TblOrganismosManagerImpl")
	private TblOrganismosManager tblOrganismosManager;
	
	@Resource(name = "TblUsuariosAplicacionesManagerImpl")
	private TblUsuariosAplicacionesManager tblUsuariosPlanificacionesManager;
	
	@Resource(name = "TblAplicacionesManagerImpl")
	private TblAplicacionesManager tblAplicacionesManager;
	
	@Resource(name = "TblServiciosManagerImpl")
	private TblServiciosManager tblServiciosManager;
	
	@Resource(name = "tblOrganismosServicioManagerImpl")
	private TblOrganismosServicioManager tblOrganismosServicioManager;
	
		
	////MIGRADO
	@Override
	public List<OrganismoBean> getOrganismos()
			throws BusinessException {
		try {
			TblOrganismosQuery query = new TblOrganismosQuery();
			query.setEliminadoIsNull(true);
			query.addOrder("nombre", OrderType.ASC);
			List<TblOrganismos> lista = tblOrganismosManager.getOrganismosByQuery(query);
			
			return getListOrganismoBean(lista);					
		} 
		catch (Exception e){
			logger.error("ServicioOrganismoImpl - getOrganismos:" + e);
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}
	
	
	// /MIGRADO
	@Override
	public List<Integer> getOrganismos(String rolUsuario, Integer idUsuario) throws BusinessException {
		List<Integer> res = new ArrayList<>();
		try {

			TblUsuariosAplicacionesQuery queryUsuariosAplicaciones = new TblUsuariosAplicacionesQuery();
			TblUsuariosQuery queryUsuarios = new TblUsuariosQuery();
			queryUsuarios.setUsuarioid(idUsuario.longValue());
			queryUsuariosAplicaciones.setTblUsuarios(queryUsuarios);
			List<TblUsuariosAplicaciones> listaUsuarioAplicaciones = tblUsuariosPlanificacionesManager
					.getUsuariosAplicacionesByQuery(queryUsuariosAplicaciones);

			if (null != listaUsuarioAplicaciones && !listaUsuarioAplicaciones.isEmpty()) {

				TblAplicacionesQuery queryAplicacionesQuery = new TblAplicacionesQuery();
				for (TblUsuariosAplicaciones tblUsuariosAplicaciones : listaUsuarioAplicaciones) {
					queryAplicacionesQuery.addAplicacionidIn(tblUsuariosAplicaciones.getAplicacionid());
				}

				List<TblAplicaciones> listaAplicaciones = tblAplicacionesManager
						.getAplicaciones(queryAplicacionesQuery);

				TblServiciosQuery queryServicios = new TblServiciosQuery();
				for (TblAplicaciones aplicacion : listaAplicaciones) {
					queryServicios.addTblAplicacionesIdIn(aplicacion);
				}
				queryServicios.setEliminadoIsNull(true);
				List<TblServicios> listaServicios = tblServiciosManager.getServicios(queryServicios);
				
				if (null != listaServicios && !listaServicios.isEmpty()) {
					TblOrganismosServicioQuery queryOrganismoServicio = new TblOrganismosServicioQuery();
					
					for (TblServicios s : listaServicios) {
						queryOrganismoServicio.addTblServiciosIdIn(s);
					}
					List<OrganismosServicioBean> os = tblOrganismosServicioManager.getOrganismosServicioByQuery(queryOrganismoServicio);
					
					if (null != os && !os.isEmpty()){
						for (OrganismosServicioBean organismosServicioBean : os) {
							res.add(organismosServicioBean.getOrganismoId());
						}
					}
					//eliminado posibles organismos duplicados
					Set<Integer> set = new HashSet<>();
					set.addAll(res);
					res = new ArrayList<Integer>(set);
				}
			}

		} catch (Exception e) {
			logger.error("ServicioServicioImpl - getServicios:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
		return res;
	}
	
	////MIGRADO
	@Override
	public PaginatedList<OrganismoBean> getOrganismos(int start, int size,
			String order, String columnSort, OrganismoBean criterio)
			throws BusinessException {
		String nombre = null;
		
		try {
			//Columna para ordenar
			HashMap<String, String> columns = new HashMap<>();
			columns.put("2","dir3");
			columns.put("3","nombre");
			
			if (columnSort==null){
				columnSort = "2"; //Id
			}
			String column = columns.get(columnSort);
			if (column==null){
				column = "dir3";
			}
		
			if (null != criterio && null != criterio.getNombre()){
				nombre = criterio.getNombre();
			}

			List<TblOrganismos> lista = tblOrganismosManager.getOrganismosPaginado(start, size, order, column, nombre, false);
			List<OrganismoBean> pageList = 	getListOrganismoBean(lista);
			 
			//Total de organismos
			Integer rowcount = tblOrganismosManager.getOrganismosPaginado(start, size, order, column, nombre, true).size();
			
			PaginatedList<OrganismoBean> result = new PaginatedList<OrganismoBean>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);

			return result;
		}
		catch (Exception e){
			logger.error("ServicioOrganismoImpl - getOrganismos:" + e);
			throw new BusinessException(e,"errors.organismo.getOrganismos");
			
		}
	}

	
	////MIGRADO
	@Override
	@Transactional
	public Integer newOrganismo(OrganismoBean organismo, String source, String accion, Long accionId ) throws BusinessException {
		try{
			TblOrganismos organismoTO = getOrganismoTO(organismo);
						
			organismoTO.setFechacreacion(new Date());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			organismoTO.setCreadopor(modificador);
			
			Long idOrganismo =  tblOrganismosManager.insert(organismoTO, source, accion, accionId);
			organismo.setActivo(organismoTO.getActivo());
			organismo.setFechacreacion(organismoTO.getFechacreacion());
			organismo.setCreadopor(organismoTO.getCreadopor());
			organismo.setOrganismoId(idOrganismo.intValue());
			return organismo.getOrganismoId();
		}catch (Exception e){
			logger.error("ServicioOrganismoImpl - newOrganismo:" + e);
			BusinessException exc = new BusinessException(e,"errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}


	////MIGRADO
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateOrganismo(OrganismoBean organismo, String source, String accion, Long accionId) throws BusinessException {
		
		try {
			TblOrganismos organismoTO = getOrganismoTO(organismo);
			organismoTO.setFechamodificacion(new Date());
			organismoTO.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			tblOrganismosManager.update(organismoTO, source, accion, accionId);
			
		}
		catch (Exception e){
			logger.error("ServicioOrganismoImpl - updateOrganismo:" + e);
			throw new BusinessException(e,"errors.organismo.updateOrganismo");		
		} 
		
	}

	////MIGRADO
	@Override
	@Transactional
	public OrganismoBean loadOrganismo(OrganismoBean organismo)	throws BusinessException {
		try {
			TblOrganismos organismoTO = tblOrganismosManager.getOrganismoById(organismo.getOrganismoId().longValue());
			return getOrganismoBean(organismoTO);
		}catch (Exception e) {
			logger.error("ServicioOrganismoImpl - loadOrganismo:" + e);
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}
	
	
	///MIGRADO
	@Override
	@Transactional
	public void deleteOrganismo(Long organismoId, String source, String accion, Long accionId) throws BusinessException {
		try {
			TblOrganismos o = tblOrganismosManager.getOrganismoById(organismoId);
									
			o.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			o.setFechamodificacion(new Date());
			o.setEliminado("S");
			tblOrganismosManager.update(o, source, accion, accionId);			
		}
		catch (Exception e){
			logger.error("ServicioOrganismoImpl - deleteOrganismo:" + e);
			throw new BusinessException(e,"errors.organismo.deleteOrganismo");			
		}
	}
	
	/**
	 * <p>Obtenemos un objeto TblOrganismos a partir de un objeto OrganismoBean</p>
	 * 
	 * @param aplicacion 
	 * 
	 * @return objeto TblOrganismos
	 */
	/////MIGRADO
	protected TblOrganismos getOrganismoTO(OrganismoBean organismo)
	{
		 
		TblOrganismos organismoTO = new TblOrganismos();
		
		try {
			Date defaultValue = null;         
			DateConverter converter = new DateConverter (defaultValue);   
			Long defaultLongValue=null;
			LongConverter longConverter = new LongConverter(defaultLongValue);
			Integer defaultIntegerValue=null;
			IntegerConverter integerConverter = new IntegerConverter(defaultIntegerValue);
			ConvertUtils.register(longConverter, java.lang.Long.class);
			ConvertUtils.register(integerConverter, java.lang.Integer.class);
			ConvertUtils.register (converter, java.util.Date.class);
			BeanUtils.copyProperties(organismoTO,organismo);
			organismoTO.setOrganismoid((null != organismo.getOrganismoId()) ? organismo.getOrganismoId().longValue() : null);
			organismoTO.setFechacreacion(organismo.getFechacreacion());
			organismoTO.setFechamodificacion(organismo.getFechamodificacion());
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioOrganismoImpl - getOrganismoTO:" + e);
		}

		return organismoTO;
	}
	
	/**
	 * <p>Convertirmos una lista de AplicacionJPA a una lista de ServidoresBean</p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	////MIGRADO
	protected List<OrganismoBean> getListOrganismoBean(List<TblOrganismos> lista) throws BusinessException
	{	
		List<OrganismoBean> result = null;
		
		if (lista!=null && !lista.isEmpty())
		{
			result = new ArrayList<OrganismoBean>();
		
			for (TblOrganismos o : lista) {
				OrganismoBean organismo =  new OrganismoBean();
			
				try {
					Date defaultValue = null;         
					DateConverter converter = new DateConverter (defaultValue);   
					Long defaultLongValue=null;
					LongConverter longConverter = new LongConverter(defaultLongValue);
					Integer defaultIntegerValue=null;
					IntegerConverter integerConverter = new IntegerConverter(defaultIntegerValue);
					ConvertUtils.register(longConverter, java.lang.Long.class);
					ConvertUtils.register(integerConverter, java.lang.Integer.class);
					ConvertUtils.register (converter, java.util.Date.class);
					BeanUtils.copyProperties(organismo, o);
					organismo.setOrganismoId(o.getOrganismoid().intValue());
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(organismo);
			}
		}
			
		return result;
	}
	
	/**
	 * <p>Obtenemos un objeto OrganismoBean a partir de un objeto TblOrganismos</p>
	 * 
	 * @param organismoTO
	 * 
	 * @return objeto OrganismoBean
	 */
	///MIGRADO
	protected OrganismoBean getOrganismoBean(TblOrganismos o) throws BusinessException
	{
		OrganismoBean organismo = new OrganismoBean();
		
		try {
			Date defaultValue = null;         
			DateConverter converter = new DateConverter (defaultValue);   
			Long defaultLongValue=null;
			LongConverter longConverter = new LongConverter(defaultLongValue);
			Integer defaultIntegerValue=null;
			IntegerConverter integerConverter = new IntegerConverter(defaultIntegerValue);
			ConvertUtils.register(longConverter, java.lang.Long.class);
			ConvertUtils.register(integerConverter, java.lang.Integer.class);
			ConvertUtils.register (converter, java.util.Date.class);
			BeanUtils.copyProperties(organismo, o);
			organismo.setOrganismoId(o.getOrganismoid().intValue());
		} catch (IllegalAccessException e) {
			logger.error("ServicioOrganismoImpl - getOrganismoBean:" + e);
		} catch (InvocationTargetException e) {
			logger.error("ServicioOrganismoImpl - getOrganismoBean:" + e);
		}
		return organismo;
	}
	
}
