package es.mpr.plataformamensajeria.servicios.impl;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.minhap.common.entity.TextComparator;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosMovilesManager;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblServiciosMoviles;
import es.minhap.sim.model.ViewServicios;
import es.minhap.sim.query.TblServiciosMovilesQuery;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServicioMovilBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicioMovil;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.plataformamensajeria.util.Utiles;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de aplicaciones a traves de JPA.
 * 
 * @author 
 * 
 */

@Service("servicioServicioMovilImpl")
public class ServicioServicioMovilImpl implements ServicioServicioMovil{

	private static Logger logger = Logger.getLogger(ServicioServicioMovilImpl.class);
	
	@Resource(name = "tblServiciosMovilesManagerImpl")
	private TblServiciosMovilesManager tblServiciosMovilesManager;
	
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties props;
	
	Map<String,String> mapTipos = null;
	
	private static final String SEPARADOR_OPCIONES = "&&";
	
	private static final String SEPARADOR_OPCIONES_VALUES = "#";
	
	@Override
	public List<TblServiciosMoviles> getServiciosMoviles(ServicioMovilBean criterio)
			throws BusinessException {
		
		List<TblServiciosMoviles> list = null;
		
		try {			
			TblServiciosMovilesQuery query = new TblServiciosMovilesQuery();
            query.setNombre(criterio.getNombre().toUpperCase());
            query.setNombreComparator(TextComparator.CONTAINS);

            list = tblServiciosMovilesManager.getListaServiciosMovilesByQuery(query);

		} 
		catch(Exception e){
			logger.error("ServicioServicioMovilImpl - getServiciosMoviles:" + e);
			throw new BusinessException(e, "errors.servicioMovil.getServiciosMoviles");
		}
		
		return list;
	}
		
	@Override
	public List<TblServiciosMoviles> getServiciosMoviles()
			throws BusinessException {
		List<TblServiciosMoviles> list = null;
		
		try {
			
			TblServiciosMovilesQuery query = new TblServiciosMovilesQuery();
							
			list = tblServiciosMovilesManager.getListaServiciosMovilesByQuery(query);
			
		} 
		catch(Exception e){
			logger.error("ServicioServicioMovilImpl - getServiciosMoviles:" + e);
			throw new BusinessException(e, "errors.servicioMovil.getServiciosMoviles");
		}
		
		return list;
	}
	
	
	///MIGRADO
	@Override
	public PaginatedList<ServicioMovilBean> getServiciosMoviles(int start, int size, String order, String columnSort,
			ServicioMovilBean criterio) throws BusinessException {
		String nombre = null;

		try {
			// Columna para ordenar
			HashMap<String, String> columns = new HashMap<>();
			columns.put("1", "nombre");
			columns.put("4", "tipo");
			if (columnSort == null)
				columnSort = "1"; // Id

			String column = columns.get(columnSort);
			if (column == null)
				column = "nombre";

			if (null != criterio && null != criterio.getNombre()) {
				nombre = criterio.getNombre();

			}

			List<TblServiciosMoviles> lista = tblServiciosMovilesManager.getServiciosMovilesPaginado(start, size, order, column, 
					nombre, false);
			List<ServicioMovilBean> pageList = getListTblServicioMovilBean(lista);
			// Total de organismos
			Integer rowcount = tblServiciosMovilesManager.getServiciosMovilesPaginado(start, size, order, column, 
					nombre, true).size();

			PaginatedList<ServicioMovilBean> result = new PaginatedList<ServicioMovilBean>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			logger.error("ServicioServicioMovilImpl - getServiciosMoviles:" + e);
			throw new BusinessException(e, "errors.servicioMovil.getServiciosMoviles");

		}
	}

	///MIGRADO
	protected List<ServicioMovilBean> getListTblServicioMovilBean(List<TblServiciosMoviles> lista) throws BusinessException {
		List<ServicioMovilBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();
			if (null == mapTipos){
				mapTipos = new HashMap<>();
				cargarTiposServicios();
			}

			for (TblServiciosMoviles v : lista) {
				ServicioMovilBean servicioMovil = new ServicioMovilBean();
				try {

					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(servicioMovil, v);
					servicioMovil.setServicioMovilId(v.getServiciosmovilesid());
					servicioMovil.setTipoServicio((null != mapTipos.get(servicioMovil.getTipo().toString()))? mapTipos.get(servicioMovil.getTipo().toString()) : "Desconocido");
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new BusinessException(e);
				}
				result.add(servicioMovil);
			}
		}

		return result;
	}
	
	
	private void cargarTiposServicios() {
		String options = props.getProperty("generales.serviciosMoviles.opciones", null);
		
		String[] opciones = options.split(SEPARADOR_OPCIONES);
		for (String opcion : opciones) {
			String [] valores = opcion.split(SEPARADOR_OPCIONES_VALUES);
			mapTipos.put(valores[0], valores[1]);
		}
	}

	///MIGRADO
	@Override
	@Transactional
	public Long newServicioMovil(ServicioMovilBean servicioMovil, String source, String accion, Long accionId)
			throws BusinessException {
		try {
			TblServiciosMoviles servicioMovilTO = getServicioMovilTO(servicioMovil);
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			servicioMovilTO.setCreadopor(modificador);
			servicioMovilTO.setFechacreacion(new Date());			
			
			if(servicioMovilTO.getIcono() != null){
				//agregar en ruta temporal
				File originalFile = new File(servicioMovilTO.getIcono());
		        String iconoBase64 = Utiles.FiletoBase64String(originalFile);
	
		        servicioMovilTO.setIcono(iconoBase64);
	        }
			
			Long idServicioMovil = tblServiciosMovilesManager.insert(servicioMovilTO, source, accion, accionId);

			servicioMovil.setServicioMovilId(idServicioMovil);
			servicioMovil.setNombre(servicioMovilTO.getNombre());
			servicioMovil.setDescripcion(servicioMovilTO.getDescripcion());
			servicioMovil.setTipo(servicioMovilTO.getTipo());
			servicioMovil.setUrlServicio(servicioMovilTO.getUrlServicio());
			servicioMovil.setEstado(servicioMovilTO.getEstado());
			servicioMovil.setNombreContacto(servicioMovilTO.getNombrecontacto());
			servicioMovil.setTelefonoContacto(servicioMovilTO.getTelefonocontacto());	
			servicioMovil.setIcono(servicioMovilTO.getIcono());
			servicioMovil.setFechaCreacion(servicioMovilTO.getFechacreacion());
			servicioMovil.setCreadoPor(servicioMovilTO.getCreadopor());
			
			return servicioMovil.getServicioMovilId();
		} catch (Exception e) {
			logger.error("ServicioServicioMovilImpl - newServicioMovil:" + e);
			BusinessException exc = new BusinessException(e, "errors.servicioMovil.newServicioMovil");
			exc.mRechargeInput();
			throw exc;
		}
	}

	///MIGRADO
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateServicioMovil(ServicioMovilBean servicioMovil, String source, String accion, Long accionId)
			throws BusinessException {
		try {
			TblServiciosMoviles servicioMovilTO = getServicioMovilTO(servicioMovil);
			servicioMovilTO.setFechamodificacion(new Date());
			servicioMovilTO.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			servicioMovilTO.setFechacreacion(servicioMovil.getFechaCreacion());
			tblServiciosMovilesManager.update(servicioMovilTO, source, accion, accionId);

		} catch (Exception e) {
			logger.error("ServicioServicioMovilImpl - updateServicioMovil:" + e);
			throw new BusinessException(e, "errors.servicioMovil.updateServicioMovil");
		}

	}

	///MIGRADO
	@Override
	@Transactional
	public ServicioMovilBean loadServicioMovil(ServicioMovilBean servicioMovil) throws BusinessException {
		try {
			TblServiciosMoviles servicioMovilTO = tblServiciosMovilesManager.getServicioMovil(servicioMovil.getServicioMovilId());
			ServicioMovilBean serMov = getServicioMovilBean(servicioMovilTO);
			return serMov;
		} catch (Exception e) {
			logger.error("ServicioServicioMovilImpl - loadServicioMovil:" + e);
			throw new BusinessException(e, "errors.servicioMovil.loadServicioMovil");
		}
	}

	///MIGRADO
	@Override
	@Transactional
	public void deleteServicioMovil(ServicioMovilBean servicioMovil, String accionServicioMovil, Long accionIdServicioMovil, String source) throws BusinessException {
		try {
			TblServiciosMoviles servicioMovilTO = tblServiciosMovilesManager.getServicioMovil(servicioMovil.getServicioMovilId());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			
			servicioMovilTO.setModificadopor(modificador);
			servicioMovilTO.setFechamodificacion(new Date());
			tblServiciosMovilesManager.delete(servicioMovilTO.getServiciosmovilesid(), source, accionServicioMovil, accionIdServicioMovil);
		} catch (Exception e) {
			logger.error("ServicioServicioMovilImpl - deleteServicioMovil:" + e);
			throw new BusinessException(e, "errors.servicioMovil.deleteServicioMovil");
		}
	}
	
	@Override
	public void deleteImagenServicioMovil(ServicioMovilBean servicioMovil, String accionServicioMovil, Long accionIdServicioMovil, String source)
			throws BusinessException {
		try {
			TblServiciosMoviles servicioMovilTO = tblServiciosMovilesManager.getServicioMovil(servicioMovil.getServicioMovilId());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			
			servicioMovilTO.setIcono(null);
			servicioMovilTO.setModificadopor(modificador);
			servicioMovilTO.setFechamodificacion(new Date());
			tblServiciosMovilesManager.update(servicioMovilTO, source, accionServicioMovil, accionIdServicioMovil);
			
		} catch (Exception e) {
			logger.error("ServicioServicioMovilImpl - deleteImagenServicioMovil:" + e);
			throw new BusinessException(e, "errors.servicioMovil.deleteImagenServicioMovil");
		}
	}

	/**
	 * <p>
	 * Obtenemos un objeto OrganismoJPA a partir de un objeto OrganismoBean
	 * </p>
	 * 
	 * @param organismoBean
	 * 
	 * @return objeto OrganismoJPA
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	///MIGRADO
	public TblServiciosMoviles getServicioMovilTO(ServicioMovilBean servicioMovil) {

		TblServiciosMoviles servicioMovilTO = new TblServiciosMoviles();

		try {
			BeanUtils.copyProperties(servicioMovilTO, servicioMovil);
			servicioMovilTO.setServiciosmovilesid((null != servicioMovil.getServicioMovilId()) ? servicioMovil.getServicioMovilId().longValue() : null);
			servicioMovilTO.setFechacreacion(servicioMovil.getFechaCreacion());
			servicioMovilTO.setNombre(servicioMovil.getNombre());
			servicioMovilTO.setDescripcion(servicioMovil.getDescripcion());
			servicioMovilTO.setFechamodificacion(servicioMovil.getFechaModificacion());
			servicioMovilTO.setUrlServicio(servicioMovil.getUrlServicio());
			servicioMovilTO.setTipo(servicioMovil.getTipo()); 
			servicioMovilTO.setEstado(servicioMovil.getEstado());
			servicioMovilTO.setNombrecontacto(servicioMovil.getNombreContacto());
			servicioMovilTO.setTelefonocontacto(servicioMovil.getTelefonoContacto());
			servicioMovilTO.setUrl_AvisoSuscripcion(servicioMovil.getUrlAvisoSuscripcion());
			servicioMovilTO.setInd_Suscripcion(servicioMovil.getIndSuscripcion()); 
			servicioMovilTO.setEndpoint_User(servicioMovil.getEndpointUser());
			servicioMovilTO.setEndpoint_Pass(servicioMovil.getEndpointPass());
			servicioMovilTO.setCreadopor(servicioMovil.getCreadoPor());
			servicioMovilTO.setModificadopor(servicioMovil.getModificadoPor());
			
			if(servicioMovil.getIcono()!=null){
				
	        servicioMovilTO.setIcono(servicioMovil.getIcono());
	        }

		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioServicioMovilImpl - getServicioMovilTO:" + e);
		} 
		return servicioMovilTO;
	}


	/**
	 * <p>
	 * Obtenemos un objeto ServicioBean a partir de un objeto ServicioJPA
	 * </p>
	 * 
	 * @param servicioJPA
	 * 
	 * @return objeto ServicioBean
	 */
	///MIGRADO
	protected ServicioBean getServicioBean(TblServicios serv) throws BusinessException {
		ServicioBean servicio = new ServicioBean();

		try {
			BeanUtils.copyProperties(servicio, serv);
			servicio.setServicioId(serv.getServicioid().intValue());
			servicio.setAplicacionid((null != serv.getTblAplicaciones()) ? serv.getTblAplicaciones().getAplicacionid()
					.intValue() : null);
			servicio.setCanalid((null != serv.getTblCanales()) ? serv.getTblCanales().getCanalid().intValue() : null);
			servicio.setResponsablefuncionalemail(serv.getRespFuncionalEmail());
			servicio.setResponsablefuncionalnombre(serv.getRespFuncionalNombre());
			servicio.setResponsabletecnicoemail(serv.getRespTecnicoEmail());
			servicio.setResponsabletecniconombre(serv.getRespTecnicoNombre());
		} catch (IllegalAccessException e) {
			logger.error("ServicioServicioImpl - getServicioBean:" + e);
		} catch (InvocationTargetException e) {
			logger.error("ServicioServicioImpl - getServicioBean:" + e);
		}

		return servicio;
	}

	/**
	 * <p>
	 * Obtenemos un objeto ServicioBean a partir de un objeto ServicioBean
	 * </p>
	 * 
	 * @param ServicioBean
	 * 
	 * @return objeto ServicioBean
	 */
	///MIGRADO
	@Override
	public ServicioMovilBean createServicioMovilBean(ServicioMovilBean servMovil) throws BusinessException {
		ServicioMovilBean servicioMovil = new ServicioMovilBean();

		try {
			BeanUtils.copyProperties(servicioMovil, servMovil);
			servicioMovil.setActivado(servMovil.getActivado());
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioServicioMovilImpl - createServicioMovilBean:" + e);
		}
		return servicioMovil;
	}

	/**
	 * <p>
	 * Convertirmos una lista de ViewServicioJPA a una lista de ServidoresBean
	 * </p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
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
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
				result.add(servicio);
			}
		}

		return result;
	}
	
	protected ServicioMovilBean getServicioMovilBean(TblServiciosMoviles servMovil) throws BusinessException {
		ServicioMovilBean servicioMovil = new ServicioMovilBean();

		try {
			BeanUtils.copyProperties(servicioMovil, servMovil);
			servicioMovil.setServicioMovilId(servMovil.getServiciosmovilesid());
			servicioMovil.setUrlServicio(servMovil.getUrlServicio());
			servicioMovil.setNombre(servMovil.getNombre());
			servicioMovil.setDescripcion(servMovil.getDescripcion());
			servicioMovil.setTipo(servMovil.getTipo()); 
			servicioMovil.setEstado(servMovil.getEstado());
			servicioMovil.setNombreContacto(servMovil.getNombrecontacto());
			servicioMovil.setTelefonoContacto(servMovil.getTelefonocontacto());
			servicioMovil.setUrlAvisoSuscripcion(servMovil.getUrl_AvisoSuscripcion());
			servicioMovil.setIndSuscripcion(servMovil.getInd_Suscripcion()); 
			servicioMovil.setEndpointUser(servMovil.getEndpoint_User());
			servicioMovil.setEndpointPass(servMovil.getEndpoint_Pass());
			servicioMovil.setCreadoPor(servMovil.getCreadopor());
			servicioMovil.setFechaCreacion((null != servMovil.getFechacreacion() ) ? DateUtils.truncate(servMovil.getFechacreacion(), Calendar.DATE) : null);
			servicioMovil.setModificadoPor(servMovil.getModificadopor());
			servicioMovil.setFechaModificacion((null != servMovil.getFechamodificacion() ) ? DateUtils.truncate(servMovil.getFechamodificacion(), Calendar.DATE) : null);
			
			if(servMovil.getIcono()!=null){
				
		        servicioMovil.setIcono(servMovil.getIcono());
		        
			}
			
		} catch (IllegalAccessException e) {
			logger.error("ServicioServicioMovilImpl - getServicioMovilBean:" + e);
		} catch (InvocationTargetException e) {
			logger.error("ServicioServicioMovilImpl - getServicioMovilBean:" + e);
		}

		return servicioMovil;
	}
}
