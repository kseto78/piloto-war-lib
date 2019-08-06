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
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.minhap.common.entity.OrderType;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblPlanificacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosAplicacionesManager;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblCanales;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblUsuariosAplicaciones;
import es.minhap.sim.query.TblAplicacionesQuery;
import es.minhap.sim.query.TblCanalesQuery;
import es.minhap.sim.query.TblServiciosQuery;
import es.minhap.sim.query.TblUsuariosAplicacionesQuery;
import es.minhap.sim.query.TblUsuariosQuery;
import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Maneja la persistencia y b&uacute;squeda de aplicaciones a traves de JPA.
 * 
 * @author
 * 
 */
@Service("servicioAplicacionImpl")
public class ServicioAplicacionImpl implements ServicioAplicacion {

	/**  logger. */
	private static Logger logger = Logger.getLogger(ServicioAplicacionImpl.class);

	/**  tbl aplicaciones manager. */
	@Resource(name = "TblAplicacionesManagerImpl")
	TblAplicacionesManager tblAplicacionesManager;

	/**  tbl planificaciones manager. */
	@Resource(name = "TblPlanificacionesManagerImpl")
	TblPlanificacionesManager tblPlanificacionesManager;

	/**  tbl servicios manager. */
	@Resource(name = "TblServiciosManagerImpl")
	private TblServiciosManager tblServiciosManager;

	/**  servicio servicio. */
	@Resource(name = "servicioServicioImpl")
	private ServicioServicio servicioServicio;
	
	/**  tbl usuarios aplicaciones manager. */
	@Resource(name = "TblUsuariosAplicacionesManagerImpl")
	private TblUsuariosAplicacionesManager tblUsuariosAplicacionesManager;

	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion#getAplicaciones()
	 */
	// //MIGRADO
	@Override
	public List<AplicacionBean> getAplicaciones() throws BusinessException {
		try {
			List<TblAplicaciones> lista = tblAplicacionesManager.getAplicacionesNoEliminadasOrdenadas();
			return getListViewAplicacionBean(lista);
			
		} catch (Exception e) {
			logger.error("ServicioAplicacionImpl - getAplicaciones:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion#getAplicacionesNoAsignadas(java.lang.String)
	 */
	////MIGRADO
	@Override
	public List<AplicacionBean> getAplicacionesNoAsignadas(String idUsuario) throws BusinessException {
		List<AplicacionBean> listBean = new ArrayList<>();
		
		try {
			TblUsuariosAplicacionesQuery queryUsuariosAplicaciones = new TblUsuariosAplicacionesQuery();
			TblUsuariosQuery queryUsuarios = new TblUsuariosQuery();
			queryUsuarios.setUsuarioid(Long.parseLong(idUsuario));
			queryUsuariosAplicaciones.setTblUsuarios(queryUsuarios);
			List<TblUsuariosAplicaciones> listaUsuarioAplicaciones = tblUsuariosAplicacionesManager.getUsuariosAplicacionesByQuery(queryUsuariosAplicaciones);
			
			
			TblAplicacionesQuery queryAplicacionesQuery = new TblAplicacionesQuery();
			for (TblUsuariosAplicaciones tblUsuariosAplicaciones : listaUsuarioAplicaciones) {
				queryAplicacionesQuery.addAplicacionidNOTIn(tblUsuariosAplicaciones.getAplicacionid());
			}
			queryAplicacionesQuery.setEliminadoIsNull(true);
			queryAplicacionesQuery.setActivo(true);
			queryAplicacionesQuery.addOrder("nombre", OrderType.ASC);
			
			
			List<TblAplicaciones> listaAplicaciones = tblAplicacionesManager.getAplicaciones(queryAplicacionesQuery);
			if (null != listaAplicaciones){
				for (TblAplicaciones a : listaAplicaciones) {
					AplicacionBean app = new AplicacionBean();
					app.setAplicacionId(a.getAplicacionid().intValue());
					app.setNombre(a.getNombre());
					listBean.add(app);
				}
			}
		} catch (Exception e) {
			logger.error("ServicioAplicacionImpl - getAplicacionesNoAsignadas:" + e);
		}
		return listBean;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion#existeUsuario(java.lang.String)
	 */
	// ///MIGRADO
	@Override
	public boolean existeUsuario(String usuario) {
		boolean existe = false;
		try {
			tblAplicacionesManager.existeAplicacionUsuario(usuario);
		} catch (Exception e) {
			logger.error("ServicioAplicacionImpl - existeUsuario:" + e);
		}
		return existe;

	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion#getAplicacionesMenu(java.lang.String, java.lang.Integer)
	 */
	////MIGRADO
	@Override
	public List<AplicacionBean> getAplicacionesMenu(String rolUsuario, Integer userName) {
		List<AplicacionBean> listBean = new ArrayList<>();
		try {
			if (rolUsuario != null && (rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)||rolUsuario.equals(PlataformaMensajeriaUtil.ROL_CAID))) {
				TblAplicacionesQuery query = new TblAplicacionesQuery();
				query.setActivo(true);
				query.setEliminadoIsNull(true);
				query.addOrder("nombre", OrderType.ASC);
				List<TblAplicaciones> lista = tblAplicacionesManager.getAplicaciones(query);
				for (TblAplicaciones a : lista) {
					AplicacionBean app = new AplicacionBean();
					app.setAplicacionId(a.getAplicacionid().intValue());
					app.setNombre(a.getNombre());
					listBean.add(app);
				}
				
			} else {
				TblUsuariosAplicacionesQuery queryUsuariosAplicaciones = new TblUsuariosAplicacionesQuery();
				TblUsuariosQuery queryUsuarios = new TblUsuariosQuery();
				queryUsuarios.setUsuarioid((null != userName)? userName.longValue() : null);
				queryUsuariosAplicaciones.setTblUsuarios(queryUsuarios);
				List<TblUsuariosAplicaciones> listaUsuarioAplicaciones = tblUsuariosAplicacionesManager.getUsuariosAplicacionesByQuery(queryUsuariosAplicaciones);
				
				if (null != listaUsuarioAplicaciones && !listaUsuarioAplicaciones.isEmpty()){
					TblAplicacionesQuery queryAplicacionesQuery = new TblAplicacionesQuery();
					for (TblUsuariosAplicaciones tblUsuariosAplicaciones : listaUsuarioAplicaciones) {
						queryAplicacionesQuery.addAplicacionidIn(tblUsuariosAplicaciones.getAplicacionid());
					}
					queryAplicacionesQuery.setActivo(true);
					queryAplicacionesQuery.setEliminadoIsNull(true);
					queryAplicacionesQuery.addOrder("nombre", OrderType.ASC);
					List<TblAplicaciones> listaAplicaciones = tblAplicacionesManager.getAplicaciones(queryAplicacionesQuery);
					if (null != listaAplicaciones){
						for (TblAplicaciones a : listaAplicaciones) {
							AplicacionBean app = new AplicacionBean();
							app.setAplicacionId(a.getAplicacionid().intValue());
							app.setNombre(a.getNombre());
							listBean.add(app);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("ServicioAplicacionImpl - existeUsuario:" + e);
		} 
		return listBean;

	}
	
	@Override
	public List<AplicacionBean> getAplicacionesByCanal(String rolUsuario, Integer userName, long canalId) {
		List<AplicacionBean> listBean = new ArrayList<>();
		try {
			
			if (rolUsuario != null && (rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)||rolUsuario.equals(PlataformaMensajeriaUtil.ROL_CAID))) {
				TblServiciosQuery squery = new TblServiciosQuery();
				TblCanalesQuery canal= new TblCanalesQuery();
				TblAplicacionesQuery aquery = new TblAplicacionesQuery();
				aquery.setActivo(true);
				aquery.setEliminadoIsNull(true);
				aquery.addOrder("nombre", OrderType.ASC);				
				
				canal.setCanalid(Long.valueOf(canalId));
				squery.setTblCanales(canal);
				squery.setTblAplicaciones(aquery);
				squery.setEliminadoIsNull(true);
				squery.setActivo(true);
				
				List<TblServicios> lista = tblServiciosManager.getServicios(squery);
				for (TblServicios a : lista) {
					
					if (a.getTblCanales().getCanalid().equals(canalId)){
						Boolean encontrado = false;
						AplicacionBean app = new AplicacionBean();
						app.setAplicacionId(a.getTblAplicaciones().getAplicacionid().intValue());						
						app.setNombre(a.getTblAplicaciones().getNombre());
						for (AplicacionBean ap : listBean){							
						
							if(ap.getAplicacionId().equals(app.getAplicacionId())){
								encontrado = true;
							}
						}
																	
						if(!encontrado) listBean.add(app);
						
					}					
				}
				
				
			} else {
				TblUsuariosAplicacionesQuery queryUsuariosAplicaciones = new TblUsuariosAplicacionesQuery();
				TblUsuariosQuery queryUsuarios = new TblUsuariosQuery();
				queryUsuarios.setUsuarioid((null != userName)? userName.longValue() : null);
				queryUsuariosAplicaciones.setTblUsuarios(queryUsuarios);
				List<TblUsuariosAplicaciones> listaUsuarioAplicaciones = tblUsuariosAplicacionesManager.getUsuariosAplicacionesByQuery(queryUsuariosAplicaciones);
							
				if (null != listaUsuarioAplicaciones && !listaUsuarioAplicaciones.isEmpty()){

					TblServiciosQuery squery = new TblServiciosQuery();
					TblCanalesQuery canal= new TblCanalesQuery();
					canal.setCanalid(Long.valueOf(canalId));
					TblAplicacionesQuery aquery = new TblAplicacionesQuery();
					aquery.setActivo(true);
					aquery.setEliminadoIsNull(true);									
					squery.setTblCanales(canal);
					squery.setTblAplicaciones(aquery);
					squery.setEliminadoIsNull(true);
					squery.setActivo(true);
										
					List<TblServicios> lista = tblServiciosManager.getServicios(squery);
					for (TblServicios a : lista) {						
						Boolean encontrado = false;
							
							AplicacionBean app = new AplicacionBean();
							app.setAplicacionId(a.getTblAplicaciones().getAplicacionid().intValue());						
							app.setNombre(a.getTblAplicaciones().getNombre());
															
							for(TblUsuariosAplicaciones usuApl : listaUsuarioAplicaciones){
								
								if( usuApl.getAplicacionid().equals(a.getTblAplicaciones().getAplicacionid()) ){
									for (AplicacionBean ap : listBean){							
										
										if(ap.getAplicacionId().equals(app.getAplicacionId())){
											encontrado = true;
										}
									}
									if(!encontrado) listBean.add(app);									
								}
							}	
					}
					
					
				
				}
			}
		} catch (Exception e) {
			logger.error("ServicioAplicacionImpl - existeUsuario:" + e);
		} 
		return listBean;

	}
	@Override
	public boolean validarSms(Long idAplicacion){
		
		TblAplicaciones aplicacion = tblAplicacionesManager.getAplicacion(idAplicacion);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  
        
		Date fechaUltimo = aplicacion.getSmsFechaUltimo();
		Date today = new Date();

		String todayString = sdf.format(today);
		String fechaUltimoString = sdf.format(fechaUltimo);
		
		try {
			today = sdf.parse(todayString);
			fechaUltimo = sdf.parse(fechaUltimoString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if(today.after(fechaUltimo)){ // Si es true, en el dia actual no se han enviado SMS.
			aplicacion.setSmsEnviadosDia(0);
			aplicacion.setSmsFechaUltimo(today);
			
			tblAplicacionesManager.updateSMS(aplicacion);
			//Por hacer actualizacion tblAplicacionesManager.update(aplicacion, source, accion, accionId);
			
			return true;
		} else{
			if ( (Integer)aplicacion.getSmsEnviadosDia() >= (Integer)aplicacion.getSmsMaximos() ) {
				return false;				
			}else{
				aplicacion.setSmsEnviadosDia((Integer)aplicacion.getSmsEnviadosDia()+1);
				aplicacion.setSmsFechaUltimo(today);
				return true;
				//Por hacer actualizacion tblAplicacionesManager.update(aplicacion, source, accion, accionId);
			}
		}
			
	}
	
		
	@Override
	public void smsEnviado(Long idAplicacion){
		
		TblAplicaciones aplicacion = tblAplicacionesManager.getAplicacion(idAplicacion);

		Date today = new Date();
		
		aplicacion.setSmsFechaUltimo(today);
		aplicacion.setSmsEnviadosDia(aplicacion.getSmsEnviadosDia()+1);
		
		tblAplicacionesManager.updateSMS(aplicacion);
		
			
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion#getAplicaciones(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.AplicacionBean)
	 */
	// //MIGRADO
	@Override
	public PaginatedList<AplicacionBean> getAplicaciones(int start, int size, String order, String columnSort,
			AplicacionBean criterio) throws BusinessException {
		String nombre = null;
		int aplicacionId = 0;
		
		try {
			// Columna para ordenar
			HashMap<String, String> columns = new HashMap<>();
			columns.put("2", "nombre");
			
			if (columnSort == null) {
				columnSort = "2"; // Id
			}
			String column = columns.get(columnSort);
			if (column == null) {
				column = "nombre";
			}
			
			if (null != criterio && null != criterio.getNombre()) {
				
				nombre = criterio.getNombre();

			}
			if (null != criterio && null != criterio.getAplicacionId()) {
				
				aplicacionId = criterio.getAplicacionId();

			}
			
			List<TblAplicaciones> lista = tblAplicacionesManager.getAplicacionesPaginado(start, size, order, column, nombre, aplicacionId);

			List<AplicacionBean> pageList = getListViewAplicacionBean(lista);

			// Total de organismos
			Integer rowcount = lista.size();

			PaginatedList<AplicacionBean> result = new PaginatedList<AplicacionBean>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			logger.error("ServicioAplicacionImpl - getAplicaciones:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion#newAplicacion(es.mpr.plataformamensajeria.beans.AplicacionBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	// ///MIGRADO
	@Override
	@Transactional
	public Integer newAplicacion(AplicacionBean aplicacion, String source, String accion, Long accionId)
			throws BusinessException {
		try {
			TblAplicaciones aplicacionTO = getAplicacionTO(aplicacion);
			aplicacionTO.setFechacreacion(new Date());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			aplicacionTO.setCreadopor(modificador);
			Long idAplicacion = tblAplicacionesManager.insert(aplicacionTO, source, accion, accionId);

			aplicacion.setAplicacionId(idAplicacion.intValue());
			aplicacion.setActivo(aplicacion.getActivo());
			aplicacion.setFechacreacion(aplicacionTO.getFechacreacion());
			aplicacion.setCreadopor(aplicacionTO.getCreadopor());
			return aplicacion.getAplicacionId();

		} catch (Exception e) {
			logger.error("ServicioAplicaciones - newAplicacion:" + e);
			BusinessException exc = new BusinessException(e, "errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion#updateAplicacion(es.mpr.plataformamensajeria.beans.AplicacionBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	/////MIGRADO
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateAplicacion(AplicacionBean aplicacion, String source, String accion, Long accionId)
			throws BusinessException {
		try {
			TblAplicaciones aplicacionTO = getAplicacionTO(aplicacion);
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			aplicacionTO.setModificadopor(modificador);
			aplicacionTO.setFechamodificacion(new Date());
			tblAplicacionesManager.update(aplicacionTO, source, accion, accionId);
		} catch (Exception e) {
			logger.error("ServicioAplicaciones - updateAplicacion:" + e);
			throw new BusinessException(e, "errors.organismo.updateOrganismo");
		}

	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion#loadAplicacion(es.mpr.plataformamensajeria.beans.AplicacionBean)
	 */
	// ///MIGRADO
	@Override
	@Transactional
	public AplicacionBean loadAplicacion(AplicacionBean aplicacion) throws BusinessException {
		try {
			TblAplicaciones aplicacionTO = tblAplicacionesManager.getAplicacion(aplicacion.getAplicacionId()
					.longValue());
			return getAplicacionBean(aplicacionTO);
		} catch (Exception e) {
			logger.error("ServicioProveedorSMS - loadProveedorSMS:" + e);
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion#deleteAplicacion(es.mpr.plataformamensajeria.beans.AplicacionBean, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	// ////MIGRADO
	@Override
	@Transactional
	public void deleteAplicacion(AplicacionBean aplicacion, String source, String accion, Long accionId,
			String descripcionServicio, String accionActualizar, Long accionIdActualizar,
			String descripcionPlanificacion) throws BusinessException {
		try {
			TblAplicaciones aplicacionTO = tblAplicacionesManager.getAplicacion(aplicacion.getAplicacionId()
					.longValue());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();

			// //Eliminamos los servicios --> planificaciones -->
			// servidores-servicios
			TblServiciosQuery query = new TblServiciosQuery();
			TblAplicacionesQuery queryAplicaciones = new TblAplicacionesQuery();
			queryAplicaciones.setAplicacionid(aplicacionTO.getAplicacionid());
			query.setTblAplicaciones(queryAplicaciones);
			List<TblServicios> listaServicios = tblServiciosManager.getServicios(query);
			List<ServicioBean> listaServiciosBean = servicioServicio.getListServicioBean(listaServicios);

			if (!listaServiciosBean.isEmpty()) {
				for (ServicioBean s : listaServiciosBean) {
					servicioServicio.deleteServicio(s, accion, accionId, source, accionActualizar,
							accionIdActualizar, descripcionPlanificacion, descripcionServicio);
				}
			}
			aplicacionTO.setModificadopor(modificador);
			aplicacionTO.setFechamodificacion(new Date());
			aplicacionTO.setEliminado("S");
			tblAplicacionesManager.update(aplicacionTO, source, accion, accionId);
			
			return;

		} catch (Exception e) {
			logger.error("ServicioProveedorSMS - deleteAplicacion:" + e);
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}


	/**
	 * <p>
	 * Obtenemos un objeto AplicacionBean a partir de un objeto AplicacionJPA
	 * </p>.
	 *
	 * @param apli the apli
	 * @return objeto AplicacionBean
	 * @throws BusinessException the business exception
	 */
	// //MIGRADO
	protected AplicacionBean getAplicacionBean(TblAplicaciones apli) throws BusinessException {
		AplicacionBean aplicacion = new AplicacionBean();

		try {
			Date defaultValue = null;
			DateConverter converter = new DateConverter(defaultValue);
			ConvertUtils.register(converter, java.util.Date.class);
			BeanUtils.copyProperties(aplicacion, apli);
			aplicacion.setAplicacionId(apli.getAplicacionid().intValue());
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}

		return aplicacion;
	}

	/**
	 * <p>
	 * Convertirmos una lista de ViewAplicacionJPA a una lista de AplicacionBean
	 * </p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos AplicacionBean
	 * @throws BusinessException the business exception
	 */
	// //MIGRADO
	protected List<AplicacionBean> getListViewAplicacionBean(List<TblAplicaciones> lista) throws BusinessException {
		List<AplicacionBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<AplicacionBean>();
			for (TblAplicaciones a : lista) {
				AplicacionBean aplicacion = new AplicacionBean();
				try {
					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(aplicacion, a);
					aplicacion.setAplicacionId(a.getAplicacionid().intValue());
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(aplicacion);
			}
		}

		return result;
	}

	/**
	 * <p>
	 * Obtenemos un objeto proveedorTO a partir de un objeto ProveedorSMSBean
	 * </p>.
	 *
	 * @param aplicacion the aplicacion
	 * @return objeto OrganismoJPA
	 * @throws BusinessException the business exception
	 */
	// ///MIGRADO
	protected TblAplicaciones getAplicacionTO(AplicacionBean aplicacion) throws BusinessException {
		TblAplicaciones aplicacionTO = new TblAplicaciones();

		try {
			BeanUtils.copyProperties(aplicacionTO, aplicacion);
			aplicacionTO.setAplicacionid((null != aplicacion.getAplicacionId()) ? aplicacion.getAplicacionId()
					.longValue() : null);
			aplicacionTO.setFechacreacion(aplicacion.getFechacreacion());
		} catch (IllegalAccessException e) {
			logger.error("ServicioAplicacionImpl - getAplicacionTO:" + e);
		} catch (InvocationTargetException e) {
			logger.error("ServicioAplicacionImpl - getAplicacionTO:" + e);
		}
		return aplicacionTO;
	}
	
	
}
