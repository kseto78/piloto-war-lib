package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.minhap.misim.bus.model.Comunicacion;
import es.minhap.misim.bus.model.Endpoint;
import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.model.Proveedor;
import es.minhap.misim.bus.model.Transformacion;
import es.minhap.plataformamensajeria.iop.beans.FiltroProveedorMisimBean;
import es.minhap.plataformamensajeria.iop.misim.manager.ComunicacionesManager;
import es.minhap.plataformamensajeria.iop.misim.manager.EndpointsManager;
import es.minhap.plataformamensajeria.iop.misim.manager.ProductosManager;
import es.minhap.plataformamensajeria.iop.misim.manager.ProveedoresMisimManager;
import es.minhap.plataformamensajeria.iop.misim.manager.TransformacionesManager;
import es.mpr.plataformamensajeria.beans.ComunicacionBean;
import es.mpr.plataformamensajeria.beans.EndpointBean;
import es.mpr.plataformamensajeria.beans.ProductoBean;
import es.mpr.plataformamensajeria.beans.ProveedorMisimBean;
import es.mpr.plataformamensajeria.beans.TransformacionBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorMisim;
import es.mpr.plataformamensajeria.util.Utiles;

/**
 * <p>
 * Maneja la persistencia y b&uacute;squeda de servicios a traves de JPA.
 * 
 * @author
 * 
 */
@Service("servicioProveedorMisimImpl")
public class ServicioProveedorMisimImpl implements ServicioProveedorMisim {

	protected static final String NOMBRE = "nombre";

	protected static final String SERVICIOPROVEED = "ServicioProveedorMisimImpl - loadProveedorMisim:";

	protected static final String ERRORSDOTPROVEE = "errors.proveedorMisim.loadProveedorMisim";

	protected static final String R_CONST_REF = "1";

	/**  logger. */
	private static Logger logger = Logger.getLogger(ServicioProveedorMisimImpl.class);

	/**  productos manager. */
	@Resource(name = "ProductosManagerImpl")
	private ProductosManager productosManager;
	
	/**  proveedores misim manager. */
	@Resource(name = "ProveedoresMisimManagerImpl")
	private ProveedoresMisimManager proveedoresMisimManager;
	
	/**  endpoints manager. */
	@Resource(name = "EndpointsManagerImpl")
	private EndpointsManager endpointsManager;
	
	/**  transformaciones manager. */
	@Resource(name = "TransformacionesManagerImpl")
	private TransformacionesManager transformacionesManager;
	
	/**  comunicaciones manager. */
	@Resource(name = "ComunicacionesManagerImpl")
	private ComunicacionesManager comunicacionesManager;
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorMisim#getProveedoresMisim(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.ProveedorMisimBean)
	 */
	@Override
	public PaginatedList<ProveedorMisimBean> getProveedoresMisim(int start, int size, String order, String columnSort,
			ProveedorMisimBean criterio) throws BusinessException {
		try {
			// Columna para ordenar
			HashMap<String, String> columns = new HashMap<>();
			columns.put(R_CONST_REF, NOMBRE);
			if (columnSort == null) {
				columnSort = R_CONST_REF;
			} 
				// Id

			String column = columns.get(columnSort);
			if (column == null) {
				column = NOMBRE;
			}

			FiltroProveedorMisimBean filtro = new FiltroProveedorMisimBean();

			if (null != criterio && null != criterio.getNombre()) {
				
			
				filtro.setNombre(criterio.getNombre());
				filtro.setCertificado(criterio.getCertificado());
				filtro.setCertificadoPass(criterio.getCertificadoPass());
				filtro.setCifrado(criterio.getCifrado());
				filtro.setCompany(criterio.getCompany());
				/*DUDA proveedores*/
				filtro.setEncoding(criterio.getEncoding());
				filtro.setBasicAutentication(criterio.getBasicAutentication());
				filtro.setMethod(criterio.getMethod());
				filtro.setMediaType(criterio.getMediaType());
				filtro.setAnadirUim(criterio.getAnadirUim());
				filtro.setUserAutentication(criterio.getUserAutentication());
				filtro.setPassAutentication(criterio.getPassAutentication());
				filtro.setEsquemaCifrado(criterio.getEsquemaCifrado());
				filtro.setFirma(criterio.getFirma());
				filtro.setIdEndpoint(criterio.getIdEndpoint());
				filtro.setIdProducto(criterio.getIdProducto());
				filtro.setIdProveedor(criterio.getIdProveedor());
				filtro.setIdTransformacion(criterio.getIdTransformacion());
				filtro.setNodoCifrado(criterio.getNodoCifrado());
				filtro.setPassword(criterio.getPassword());
				filtro.setTipoFirma(criterio.getTipoFirma());
				filtro.setType(criterio.getType());
				filtro.setUsuario(criterio.getUsuario());
				
				if(criterio.getProducto()!=null){
					Producto producto = new Producto();
					producto.setIdProducto(criterio.getProducto().getIdProducto());
					filtro.setProducto(producto);
				}
				
				if(criterio.getEndpoint()!=null){
					Endpoint endpoint = new Endpoint();
					endpoint.setIdEndpoint(criterio.getEndpoint().getIdEndpoint());
					filtro.setEndpoint(endpoint);
				}
				
				if(criterio.getTransformacion()!=null){
					Transformacion transformacion = new Transformacion();
					transformacion.setIdTransformacion(criterio.getTransformacion().getIdTransformacion());
					filtro.setTransformacion(transformacion);
				}
			}
			
			List<Proveedor> lista = proveedoresMisimManager.getProveedoresMisimPaginado(start, size, order, column, 
					filtro, false);
			List<ProveedorMisimBean> pageList = getListProveedorMisimBean(lista);
			// Total de proveedores Misim
			Integer rowcount = proveedoresMisimManager.getProveedoresMisimPaginado(start, size, order, column, 
					filtro, true).size();

			PaginatedList<ProveedorMisimBean> result = new PaginatedList<>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			logger.error("ServicioProveedorMisimImpl - getProveedoresMisim:" + e);
			throw new BusinessException(e, "errors.proveedorMisim.getProveedoresMisim");

		}
	}
	
	/**
	 * Obtener list proveedor misim bean.
	 *
	 * @param lista the lista
	 * @return list proveedor misim bean
	 * @throws BusinessException the business exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	protected List<ProveedorMisimBean> getListProveedorMisimBean(List<Proveedor> lista) throws BusinessException, IllegalAccessException, InvocationTargetException {
		List<ProveedorMisimBean> result = null;
		
		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();
			
			for (Proveedor v : lista) {
				ProveedorMisimBean proveedorMisim = new ProveedorMisimBean();
				Date defaultValue = null;
				DateConverter converter = new DateConverter(defaultValue);
				ConvertUtils.register(converter, java.util.Date.class);
				
				Producto producto = productosManager.getProducto(v.getProducto().getIdProducto());
				v.setProducto(producto);
				
				ProductoBean productoBean = Utiles.transformacionProducto(v.getProducto());
				proveedorMisim.setProducto(productoBean);
				
				EndpointBean endpointBean = Utiles.transformacionEndpoint(v.getEndpoint());
				proveedorMisim.setEndpoint(endpointBean);
				
				if(v.getTransformacion()!=null){
					TransformacionBean transformacionBean = Utiles.transformacionTransformacion(v.getTransformacion());
					proveedorMisim.setTransformacion(transformacionBean);
					}
				
				proveedorMisim.setIdProveedor(v.getIdProveedor());
				proveedorMisim.setCertificado(v.getCertificado());
				proveedorMisim.setCertificadoPass(v.getCertificadoPass());
				proveedorMisim.setCifrado(v.getCifrado());
				proveedorMisim.setCompany(v.getCompany());
				/*DUDA proveedores*/
				proveedorMisim.setEncoding(v.getEncoding());
				proveedorMisim.setBasicAutentication(v.getBasicAutentication());
				proveedorMisim.setMethod(v.getMethod());
				proveedorMisim.setMediaType(v.getMediaType());
				proveedorMisim.setAnadirUim(v.getAnadirUim());
				proveedorMisim.setUserAutentication(v.getUserAutentication());
				proveedorMisim.setPassAutentication(v.getPassAutentication());
				proveedorMisim.setEsquemaCifrado(v.getEsquemaCifrado());
				proveedorMisim.setFirma(v.getFirma());
				proveedorMisim.setNodoCifrado(v.getNodoCifrado());
				proveedorMisim.setNombre(v.getNombre());
				proveedorMisim.setPassword(v.getPassword());
				proveedorMisim.setTipoFirma(v.getTipoFirma());
				proveedorMisim.setType(v.getType());
				proveedorMisim.setUsuario(v.getUsuario());
				proveedorMisim.setIdProducto(proveedorMisim.getProducto().getIdProducto());
				proveedorMisim.setIdEndpoint(proveedorMisim.getEndpoint().getIdEndpoint());
				
				if(proveedorMisim.getTransformacion()!=null){
					proveedorMisim.setIdTransformacion(proveedorMisim.getTransformacion().getIdTransformacion());
					}
				
				result.add(proveedorMisim);
			}
		}
		return result;
	}	
	
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorMisim#getProductos()
	 */
	@Override
	public List<ProductoBean> getProductos()
			throws BusinessException {
		
		try {
			List<Producto> lista = productosManager.getProductosOrdenados();
			return getListProductoBean(lista);					
			
		} catch (Exception e){
			logger.error("ServicioProveedorMisimImpl - getProductos:" + e);
			throw new BusinessException(e,"errors.proveedorMisim.getProductos");	
		}
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorMisim#getEndpoints()
	 */
	@Override
	public List<EndpointBean> getEndpoints()
			throws BusinessException {
		
		try {
			List<Endpoint> lista = endpointsManager.getEndpointsOrdenados();
			return getListEndpointBean(lista);					
		} catch (Exception e){
			logger.error("ServicioProveedorMisimImpl - getEndpoints:" + e);
			throw new BusinessException(e,"errors.proveedorMisim.getEndpoints");	
		}
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorMisim#getTransformaciones()
	 */
	@Override
	public List<TransformacionBean> getTransformaciones()
			throws BusinessException {
		
		try {
			List<Transformacion> lista = transformacionesManager.getTransformacionesOrdenados();
			return getListTransformacionBean(lista);					
			
		} catch (Exception e){
			logger.error("ServicioProveedorMisimImpl - getTransformaciones:" + e);
			throw new BusinessException(e,"errors.proveedorMisim.getTransformaciones");	
		}
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorMisim#getComunicaciones()
	 */
	@Override
	public List<ComunicacionBean> getComunicaciones() throws BusinessException {
		
		try {
			List<Comunicacion> lista = comunicacionesManager.getComunicacionesOrdenados();
			return getListComunicacionBean(lista);					
		} catch (Exception e){
			logger.error("ServicioProveedorMisimImpl - getComunicaciones:" + e);
			throw new BusinessException(e,"errors.proveedorMisim.getComunicaciones");	
		}
	}
		
	/**
	 * Obtener list producto bean.
	 *
	 * @param lista the lista
	 * @return list producto bean
	 * @throws BusinessException the business exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	protected List<ProductoBean> getListProductoBean(List<Producto> lista) throws BusinessException, IllegalAccessException, InvocationTargetException {
	
		List<ProductoBean> result = null;
		
		if (lista!=null && !lista.isEmpty()) {
			result = new ArrayList<>();
		
			for (Producto p : lista) {
				
				Date defaultValue = null;         
				DateConverter converter = new DateConverter (defaultValue);         
				ConvertUtils.register (converter, java.util.Date.class);
				
				ProductoBean producto = Utiles.transformacionProducto(p);
			
				result.add(producto);
			}
		}
			
		return result;
	}
	
	/**
	 * Obtener list endpoint bean.
	 *
	 * @param lista the lista
	 * @return list endpoint bean
	 * @throws BusinessException the business exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	protected List<EndpointBean> getListEndpointBean(List<Endpoint> lista) throws BusinessException, IllegalAccessException, InvocationTargetException {
	
		List<EndpointBean> result = null;
		
		if (lista!=null && !lista.isEmpty()) {
			result = new ArrayList<>();
		
			for (Endpoint end : lista) {
				
				Date defaultValue = null;         
				DateConverter converter = new DateConverter (defaultValue);         
				ConvertUtils.register (converter, java.util.Date.class);
				
				EndpointBean endpoint = Utiles.transformacionEndpoint(end);
			
				result.add(endpoint);
			}
		}
			
		return result;
	}
	
	/**
	 * Obtener list transformacion bean.
	 *
	 * @param lista the lista
	 * @return list transformacion bean
	 * @throws BusinessException the business exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	protected List<TransformacionBean> getListTransformacionBean(List<Transformacion> lista) throws BusinessException, IllegalAccessException, InvocationTargetException {
	
		List<TransformacionBean> result = null;
		
		if (lista!=null && !lista.isEmpty()) {
			result = new ArrayList<>();
		
			for (Transformacion t : lista) {
							
				Date defaultValue = null;         
				DateConverter converter = new DateConverter (defaultValue);         
				ConvertUtils.register (converter, java.util.Date.class);
				
				TransformacionBean transformacion = Utiles.transformacionTransformacion(t);
			
				result.add(transformacion);
			}
		}
			
		return result;
	}
	
	/**
	 * Obtener list comunicacion bean.
	 *
	 * @param lista the lista
	 * @return list comunicacion bean
	 * @throws BusinessException the business exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	protected List<ComunicacionBean> getListComunicacionBean(List<Comunicacion> lista) throws BusinessException, IllegalAccessException, InvocationTargetException {
	
		List<ComunicacionBean> result = null;
		
		if (lista!=null && !lista.isEmpty()) {
			result = new ArrayList<>();
		
			for (Comunicacion c : lista) {
			
				Date defaultValue = null;         
				DateConverter converter = new DateConverter (defaultValue);         
				ConvertUtils.register (converter, java.util.Date.class);
				
				ComunicacionBean comunicacion = Utiles.transformacionComunicacion(c);
			
				result.add(comunicacion);
			}
		}
			
		return result;
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorMisim#newProveedorMisim(es.mpr.plataformamensajeria.beans.ProveedorMisimBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	@Transactional
	public Long newProveedorMisim(ProveedorMisimBean proveedorMisim, String source, String accion, Long accionId)
			throws BusinessException {
		try {
			
			if (proveedorMisim.getProducto().getIdProducto()==-2){
				Long idProducto = newProductoMisim(proveedorMisim.getProducto(), source, accion, accionId);
				if(idProducto!=null) {
					proveedorMisim.setIdProducto(idProducto);
				}
			}
			
			if (proveedorMisim.getEndpoint().getIdEndpoint()==-2){
				
				Comunicacion comunicacion = comunicacionesManager.getComunicacion(proveedorMisim.getEndpoint().getComunicacion().getIdComunicacion());
				
				ComunicacionBean comunicacionBean = new ComunicacionBean();
				comunicacionBean.setIdComunicacion(comunicacion.getIdComunicacion());
				comunicacionBean.setNombre(comunicacion.getNombre());
				
				proveedorMisim.getEndpoint().setComunicacion(comunicacionBean);
				
				Long idEndpoint = newEndpointMisim(proveedorMisim.getEndpoint(), source, accion, accionId);
				if(idEndpoint!=null) {
					proveedorMisim.setIdEndpoint(idEndpoint);
				}
			}
			
			if (null != proveedorMisim.getTransformacion().getIdTransformacion() && proveedorMisim.getTransformacion().getIdTransformacion()==-2){
				Long idTransformacion = newTransformacionMisim(proveedorMisim.getTransformacion(), source, accion, accionId);
				if(idTransformacion!=null) {
					proveedorMisim.setIdTransformacion(idTransformacion);
				}
			}
			
			Proveedor proveedorTO = getProveedorTO(proveedorMisim);
			
			Long idProveedor = proveedoresMisimManager.insert(proveedorTO, source, accion, accionId);
			
			proveedorMisim.setIdProveedor(idProveedor);
			
			proveedorMisim.setNombre(proveedorTO.getNombre());
			proveedorMisim.setCertificado(proveedorTO.getCertificado());
			proveedorMisim.setCertificadoPass(proveedorTO.getCertificadoPass());
			proveedorMisim.setCifrado(proveedorTO.getCifrado());
			proveedorMisim.setCompany(proveedorTO.getCompany());
			/*DUDA proveedores*/
			proveedorMisim.setEncoding(proveedorTO.getEncoding());
			proveedorMisim.setBasicAutentication(proveedorTO.getBasicAutentication());
			proveedorMisim.setMethod(proveedorTO.getMethod());
			proveedorMisim.setMediaType(proveedorTO.getMediaType());
			proveedorMisim.setAnadirUim(proveedorTO.getAnadirUim());
			proveedorMisim.setUserAutentication(proveedorTO.getUserAutentication());
			proveedorMisim.setPassAutentication(proveedorTO.getPassAutentication());
			proveedorMisim.setEsquemaCifrado(proveedorTO.getEsquemaCifrado());
			proveedorMisim.setFirma(proveedorTO.getFirma());
			proveedorMisim.setIdEndpoint(proveedorTO.getEndpoint().getIdEndpoint());
			proveedorMisim.setIdProducto(proveedorTO.getProducto().getIdProducto());
			proveedorMisim.setIdTransformacion((null != proveedorTO.getTransformacion())? proveedorTO.getTransformacion().getIdTransformacion() : null);
			proveedorMisim.setNodoCifrado(proveedorTO.getNodoCifrado());
			proveedorMisim.setPassword(proveedorTO.getPassword());
			proveedorMisim.setTipoFirma(proveedorTO.getTipoFirma());
			proveedorMisim.setType(proveedorTO.getType());
			proveedorMisim.setUsuario(proveedorTO.getUsuario());
			
			ProductoBean productoBean = Utiles.transformacionProducto(proveedorTO.getProducto());
			proveedorMisim.setProducto(productoBean);
			
			EndpointBean endpointBean = Utiles.transformacionEndpoint(proveedorTO.getEndpoint());
			proveedorMisim.setEndpoint(endpointBean);
			
			if (null != proveedorTO.getTransformacion()){
				TransformacionBean transformacionBean = Utiles.transformacionTransformacion(proveedorTO.getTransformacion());
				proveedorMisim.setTransformacion(transformacionBean);
			}
			
			return proveedorMisim.getIdProveedor();
		} catch (Exception e) {
			logger.error("ServicioProveedorMisimImpl - newProveedorMisim:" + e);
			BusinessException exc = new BusinessException(e, "errors.proveedorMisim.newProveedorMisim");
			exc.mRechargeInput();
			throw exc;
		}
	}
	
	/**
	 * Obtener proveedor TO.
	 *
	 * @param proveedorMisim the proveedor misim
	 * @return proveedor TO
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	public Proveedor getProveedorTO(ProveedorMisimBean proveedorMisim) throws IllegalAccessException, InvocationTargetException {

		Proveedor proveedorTO = new Proveedor();

		proveedorTO.setIdProveedor((null != proveedorMisim.getIdProveedor()) ? proveedorMisim.getIdProveedor().longValue() : null);
		proveedorTO.setNombre(proveedorMisim.getNombre());
		proveedorTO.setCertificado(proveedorMisim.getCertificado());
		proveedorTO.setCertificadoPass(proveedorMisim.getCertificadoPass());
		proveedorTO.setCifrado(proveedorMisim.getCifrado());
		proveedorTO.setCompany(proveedorMisim.getCompany());
		/*DUDA proveedores*/
		proveedorTO.setEncoding(proveedorMisim.getEncoding());
		proveedorTO.setBasicAutentication(proveedorMisim.getBasicAutentication());
		proveedorTO.setMethod(proveedorMisim.getMethod());
		proveedorTO.setMediaType(proveedorMisim.getMediaType());
		proveedorTO.setAnadirUim(proveedorMisim.getAnadirUim());
		proveedorTO.setUserAutentication(proveedorMisim.getUserAutentication());
		proveedorTO.setPassAutentication(proveedorMisim.getPassAutentication());		
		proveedorTO.setEsquemaCifrado(proveedorMisim.getEsquemaCifrado());
		proveedorTO.setFirma(proveedorMisim.getFirma());
		proveedorTO.setNodoCifrado(proveedorMisim.getNodoCifrado());
		proveedorTO.setPassword(proveedorMisim.getPassword());
		proveedorTO.setTipoFirma(proveedorMisim.getTipoFirma());
		proveedorTO.setType(proveedorMisim.getType());
		proveedorTO.setUsuario(proveedorMisim.getUsuario());
		
		/*producto*/
		Producto producto = new Producto();
		producto.setIdProducto(proveedorMisim.getProducto().getIdProducto());
		producto = productosManager.getProducto(producto.getIdProducto());
		proveedorTO.setProducto(producto);
		
		/*endpoint*/
		Endpoint endpoint = new Endpoint();
		endpoint.setIdEndpoint(proveedorMisim.getEndpoint().getIdEndpoint());
		endpoint = endpointsManager.getEndpoint(endpoint.getIdEndpoint());
		
		/*endpoint:Comunicación*/
		Comunicacion comunicacion = new Comunicacion();
		comunicacion.setIdComunicacion(endpoint.getComunicacion().getIdComunicacion());
		comunicacion.setNombre(endpoint.getComunicacion().getNombre());		
		endpoint.setComunicacion(comunicacion);

		proveedorTO.setEndpoint(endpoint);
		
		/*transformación*/
		if (null != proveedorMisim.getTransformacion().getIdTransformacion()){
			Transformacion transformacion = new Transformacion();
			transformacion.setIdTransformacion(proveedorMisim.getTransformacion().getIdTransformacion());
			transformacion = transformacionesManager.getTransformacion(transformacion.getIdTransformacion());
			proveedorTO.setTransformacion(transformacion);
		}
		
		return proveedorTO;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorMisim#newProductoMisim(es.mpr.plataformamensajeria.beans.ProductoBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	@Transactional
	public Long newProductoMisim(ProductoBean producto, String source, String accion, Long accionId)
			throws BusinessException {
		try {
			Producto productoTO = getProductoTO(producto);
			
			Long idProducto = productosManager.insert(productoTO, source, accion, accionId);

			producto.setIdProducto(idProducto);
			
			producto.setNombre(productoTO.getNombre());
			producto.setCodigo(productoTO.getCodigo());
			
			return producto.getIdProducto();
			
		} catch (Exception e) {
			logger.error("ServicioProveedorMisimImpl - newProductoMisim:" + e);
			BusinessException exc = new BusinessException(e, "errors.proveedorMisim.newProductoMisim");
			exc.mRechargeInput();
			throw exc;
		}
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorMisim#newEndpointMisim(es.mpr.plataformamensajeria.beans.EndpointBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	@Transactional
	public Long newEndpointMisim(EndpointBean endpoint, String source, String accion, Long accionId)
			throws BusinessException {
		try {
			Endpoint endpointTO = getEndpointTO(endpoint);
			endpointTO.setFechaCreacion(new Date());
			endpointTO.setFechaActualizacion(new Date());
			
			Long idEndpoint = endpointsManager.insert(endpointTO, source, accion, accionId);

			endpoint.setIdEndpoint(idEndpoint);
			
			ComunicacionBean comunicacionBean = new ComunicacionBean();
			comunicacionBean.setIdComunicacion(endpoint.getComunicacion().getIdComunicacion());
			comunicacionBean.setNombre(endpoint.getComunicacion().getNombre());
			endpoint.setComunicacion(comunicacionBean);
			
			endpoint.setNombre(endpointTO.getNombre());
			endpoint.setUrlOperation(endpointTO.getOperation());
			endpoint.setPortName(endpointTO.getPortName());
			endpoint.setServiceName(endpointTO.getServiceName());
			endpoint.setUrlTargetName(endpointTO.getTargetName());
			endpoint.setTimeout(endpointTO.getTimeout());
			endpoint.setUrlEndpoint(endpointTO.getUrlEndpoint());
			endpoint.setFechaCreacion(endpointTO.getFechaCreacion());
			endpoint.setFechaActualizacion(endpointTO.getFechaActualizacion());
			
			return endpoint.getIdEndpoint();
			
		} catch (Exception e) {
			logger.error("ServicioProveedorMisimImpl - newEndpointMisim:" + e);
			BusinessException exc = new BusinessException(e, "errors.proveedorMisim.newEndpointMisim");
			exc.mRechargeInput();
			throw exc;
		}
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorMisim#newTransformacionMisim(es.mpr.plataformamensajeria.beans.TransformacionBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	@Transactional
	public Long newTransformacionMisim(TransformacionBean transformacion, String source, String accion, Long accionId)
			throws BusinessException {
		try {
			Transformacion transformacionTO = getTransformacionTO(transformacion);
			transformacionTO.setFechaCreacion(new Date());
			transformacionTO.setFechaActualizacion(new Date());
			
			Long idTransformacion = transformacionesManager.insert(transformacionTO, source, accion, accionId);

			transformacion.setIdTransformacion(idTransformacion);
			
			transformacion.setNombre(transformacionTO.getNombre());
			transformacion.setXslPeticion(transformacionTO.getXslPeticion());
			transformacion.setXslRespuesta(transformacionTO.getXslRespuesta());
			transformacion.setXslFault(transformacionTO.getXslFault());
			transformacion.setFechaCreacion(transformacionTO.getFechaCreacion());
			transformacion.setFechaActualizacion(transformacionTO.getFechaActualizacion());
			
			return transformacion.getIdTransformacion();
			
		} catch (Exception e) {
			logger.error("ServicioProveedorMisimImpl - newTransformacionMisim:" + e);
			BusinessException exc = new BusinessException(e, "errors.proveedorMisim.newTransformacionMisim");
			exc.mRechargeInput();
			throw exc;
		}
	}
	
	/**
	 * Obtener producto TO.
	 *
	 * @param producto the producto
	 * @return producto TO
	 */
	public Producto getProductoTO(ProductoBean producto) {

		Producto productoTO = new Producto();

		productoTO.setNombre(producto.getNombre());
		productoTO.setCodigo(producto.getCodigo()); 
		return productoTO;
	}
	
	/**
	 * Obtener endpoint TO.
	 *
	 * @param endpoint the endpoint
	 * @return endpoint TO
	 */
	public Endpoint getEndpointTO(EndpointBean endpoint) {

		Endpoint endpointTO = new Endpoint();

		Comunicacion comunicacion = new Comunicacion();
		comunicacion.setIdComunicacion(endpoint.getComunicacion().getIdComunicacion());
		comunicacion.setNombre(endpoint.getComunicacion().getNombre());
		
		endpointTO.setComunicacion(comunicacion);
		
		endpointTO.setNombre(endpoint.getNombre());
		endpointTO.setOperation(endpoint.getUrlOperation());
		endpointTO.setPortName(endpoint.getPortName());
		endpointTO.setServiceName(endpoint.getServiceName());
		endpointTO.setTargetName(endpoint.getUrlTargetName());
		endpointTO.setTimeout(endpoint.getTimeout());
		endpointTO.setUrlEndpoint(endpoint.getUrlEndpoint());
		endpointTO.setFechaCreacion(endpoint.getFechaCreacion());
		endpointTO.setFechaActualizacion(endpoint.getFechaActualizacion());
		return endpointTO;
	}
	
	/**
	 * Obtener transformacion TO.
	 *
	 * @param transformacion the transformacion
	 * @return transformacion TO
	 */
	public Transformacion getTransformacionTO(TransformacionBean transformacion) {

		Transformacion transformacionTO = new Transformacion();

		transformacionTO.setNombre(transformacion.getNombre());
		transformacionTO.setXslPeticion(transformacion.getXslPeticion());
		transformacionTO.setXslRespuesta(transformacion.getXslRespuesta());
		transformacionTO.setFechaCreacion(transformacion.getFechaCreacion());
		transformacionTO.setFechaActualizacion(transformacion.getFechaActualizacion());
		transformacionTO.setXslFault(transformacion.getXslFault());
		return transformacionTO;
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorMisim#updateProveedorMisim(es.mpr.plataformamensajeria.beans.ProveedorMisimBean, es.mpr.plataformamensajeria.beans.ProveedorMisimBean, java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateProveedorMisim(ProveedorMisimBean proveedorMisim, ProveedorMisimBean proveedorMisimNuevo, String source, String accion, Long accionId)
			throws BusinessException {
		try {
			Proveedor proveedorTO = getProveedorTO(proveedorMisim);
			
			if(!proveedorMisimNuevo.getProducto().getIdProducto().equals(proveedorMisim.getProducto().getIdProducto())){
				Producto producto = new Producto();
				if(proveedorMisimNuevo.getProducto().getIdProducto()!=-2){
					producto = productosManager.getProducto(proveedorMisimNuevo.getProducto().getIdProducto());
					proveedorTO.setProducto(producto);
				} else {
					Long idProducto = newProductoMisim(proveedorMisimNuevo.getProducto(), source, accion, accionId);
					if(idProducto!=null) {
						proveedorMisimNuevo.setIdProducto(idProducto);
						producto = productosManager.getProducto(proveedorMisimNuevo.getIdProducto());
					}
					proveedorTO.setProducto(producto);
				}
			}
			
			if(!proveedorMisimNuevo.getEndpoint().getIdEndpoint().equals(proveedorMisim.getEndpoint().getIdEndpoint())){
				Endpoint endpoint = new Endpoint();
				if(proveedorMisimNuevo.getEndpoint().getIdEndpoint()!=-2){
					endpoint = endpointsManager.getEndpoint(proveedorMisimNuevo.getEndpoint().getIdEndpoint());
					proveedorTO.setEndpoint(endpoint);
				} else {
					Comunicacion comunicacion = comunicacionesManager.getComunicacion(proveedorMisimNuevo.getEndpoint().getComunicacion().getIdComunicacion());
					
					ComunicacionBean comunicacionBean = new ComunicacionBean();
					comunicacionBean.setIdComunicacion(comunicacion.getIdComunicacion());
					comunicacionBean.setNombre(comunicacion.getNombre());
					
					proveedorMisimNuevo.getEndpoint().setComunicacion(comunicacionBean);
					
					Long idEndpoint = newEndpointMisim(proveedorMisimNuevo.getEndpoint(), source, accion, accionId);
					if(idEndpoint!=null) {
						proveedorMisimNuevo.setIdEndpoint(idEndpoint);
						endpoint = endpointsManager.getEndpoint(proveedorMisimNuevo.getIdEndpoint());
					}
					proveedorTO.setEndpoint(endpoint);
				}
			}	
			
			if (null == proveedorMisimNuevo.getTransformacion().getIdTransformacion()){
				proveedorTO.setTransformacion(null);
			}else if(null == proveedorMisim.getTransformacion().getIdTransformacion() || 
				!proveedorMisimNuevo.getTransformacion().getIdTransformacion().equals(proveedorMisim.getTransformacion().getIdTransformacion())){
				Transformacion transformacion = new Transformacion();
				if(proveedorMisimNuevo.getTransformacion().getIdTransformacion()!=-2){
					transformacion = transformacionesManager.getTransformacion(proveedorMisimNuevo.getTransformacion().getIdTransformacion());
					proveedorTO.setTransformacion(transformacion);
				} else {
					Long idTransformacion = newTransformacionMisim(proveedorMisimNuevo.getTransformacion(), source, accion, accionId);
					if(idTransformacion!=null) {
						proveedorMisimNuevo.setIdTransformacion(idTransformacion);
						transformacion = transformacionesManager.getTransformacion(proveedorMisimNuevo.getIdTransformacion());
					}
					proveedorTO.setTransformacion(transformacion);
				}
			}
			
			proveedoresMisimManager.update(proveedorTO, source, accion, accionId);

		} catch (Exception e) {
			logger.error("ServicioProveedorMisimImpl - updateProveedorMisim:" + e);
			throw new BusinessException(e, "errors.proveedorMisim.updateProveedorMisim");
		}

	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorMisim#loadProveedorMisim(es.mpr.plataformamensajeria.beans.ProveedorMisimBean)
	 */
	@Override
	@Transactional
	public ProveedorMisimBean loadProveedorMisim(ProveedorMisimBean proveedorMisim) throws BusinessException {
		try {
			Proveedor proveedorTO = proveedoresMisimManager.getProveedor(proveedorMisim.getIdProveedor());
			return getProveedorMisimBean(proveedorTO);
		} catch (Exception e) {
			logger.error(SERVICIOPROVEED + e);
			throw new BusinessException(e, ERRORSDOTPROVEE);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorMisim#loadProveedorMisimUpdate(es.mpr.plataformamensajeria.beans.ProveedorMisimBean)
	 */
	@Override
	@Transactional
	public ProveedorMisimBean loadProveedorMisimUpdate(ProveedorMisimBean proveedorMisim) throws BusinessException {
		try {
			Proveedor proveedorTO = proveedoresMisimManager.getProveedor(proveedorMisim.getIdProveedor());
			return getProveedorMisimBean(proveedorTO);
		} catch (Exception e) {
			logger.error(SERVICIOPROVEED + e);
			throw new BusinessException(e, ERRORSDOTPROVEE);
		}
	}
	
	/**
	 * Obtener proveedor misim bean.
	 *
	 * @param proveedor the proveedor
	 * @return proveedor misim bean
	 * @throws BusinessException the business exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	protected ProveedorMisimBean getProveedorMisimBean(Proveedor proveedor) throws BusinessException, IllegalAccessException, InvocationTargetException {
		ProveedorMisimBean proveedorMisim = new ProveedorMisimBean();

		/*Proveedor*/
		proveedorMisim.setNombre(proveedor.getNombre());
		proveedorMisim.setCertificado(proveedor.getCertificado());
		proveedorMisim.setCertificadoPass(proveedor.getCertificadoPass());
		proveedorMisim.setCifrado(proveedor.getCifrado());
		proveedorMisim.setCompany(proveedor.getCompany());
		/*DUDA proveedores*/
		proveedorMisim.setEncoding(proveedor.getEncoding());
		proveedorMisim.setBasicAutentication(proveedor.getBasicAutentication());
		proveedorMisim.setMethod(proveedor.getMethod());
		proveedorMisim.setMediaType(proveedor.getMediaType());
		proveedorMisim.setAnadirUim(proveedor.getAnadirUim());
		proveedorMisim.setUserAutentication(proveedor.getUserAutentication());
		proveedorMisim.setPassAutentication(proveedor.getPassAutentication());	
		
		proveedorMisim.setEsquemaCifrado(proveedor.getEsquemaCifrado());
		proveedorMisim.setFirma(proveedor.getFirma());
		proveedorMisim.setIdEndpoint(proveedor.getEndpoint().getIdEndpoint());
		proveedorMisim.setIdProducto(proveedor.getProducto().getIdProducto());

		proveedorMisim.setNodoCifrado(proveedor.getNodoCifrado());
		proveedorMisim.setPassword(proveedor.getPassword());
		proveedorMisim.setTipoFirma(proveedor.getTipoFirma());
		proveedorMisim.setType(proveedor.getType());
		proveedorMisim.setUsuario(proveedor.getUsuario());
		
		/*Producto*/
		ProductoBean productoBean = new ProductoBean();
		productoBean.setIdProducto(proveedor.getProducto().getIdProducto());
		productoBean.setNombre(proveedor.getProducto().getNombre());
		productoBean.setCodigo(proveedor.getProducto().getCodigo());
		
		proveedorMisim.setProducto(productoBean);
		
		/*Endpoint*/
		EndpointBean endpointBean = new EndpointBean();
		endpointBean.setIdEndpoint(proveedor.getEndpoint().getIdEndpoint());
		
		endpointBean.setNombre(proveedor.getEndpoint().getNombre());
		endpointBean.setUrlOperation(proveedor.getEndpoint().getOperation());
		endpointBean.setPortName(proveedor.getEndpoint().getPortName());
		endpointBean.setServiceName(proveedor.getEndpoint().getServiceName());
		endpointBean.setUrlTargetName(proveedor.getEndpoint().getTargetName());
		endpointBean.setTimeout(proveedor.getEndpoint().getTimeout());
		endpointBean.setUrlEndpoint(proveedor.getEndpoint().getUrlEndpoint());
		endpointBean.setFechaCreacion(proveedor.getEndpoint().getFechaCreacion());
		endpointBean.setFechaActualizacion(proveedor.getEndpoint().getFechaActualizacion());
		
		/*Endpoint:Comunicación*/
		ComunicacionBean comunicacionBean = new ComunicacionBean();
		comunicacionBean.setIdComunicacion(proveedor.getEndpoint().getComunicacion().getIdComunicacion());
		comunicacionBean.setNombre(proveedor.getEndpoint().getComunicacion().getNombre());
		endpointBean.setComunicacion(comunicacionBean);
		
		proveedorMisim.setEndpoint(endpointBean);
		
		/*Transformación*/
		if(proveedor.getTransformacion()!=null){
			TransformacionBean transformacionBean = new TransformacionBean();
			transformacionBean.setIdTransformacion(proveedor.getTransformacion().getIdTransformacion());
			transformacionBean.setNombre(proveedor.getTransformacion().getNombre());
			transformacionBean.setXslPeticion(proveedor.getTransformacion().getXslPeticion());
			transformacionBean.setXslRespuesta(proveedor.getTransformacion().getXslRespuesta());
			transformacionBean.setXslFault(proveedor.getTransformacion().getXslFault());
			transformacionBean.setFechaCreacion(proveedor.getEndpoint().getFechaCreacion());
			transformacionBean.setFechaActualizacion(proveedor.getEndpoint().getFechaActualizacion());
			
			proveedorMisim.setIdTransformacion(proveedor.getTransformacion().getIdTransformacion());
			
			proveedorMisim.setTransformacion(transformacionBean);
		}

		return proveedorMisim;
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorMisim#deleteProveedorMisim(es.mpr.plataformamensajeria.beans.ProveedorMisimBean, java.lang.String, java.lang.Long, java.lang.String)
	 */
	@Override
	@Transactional
	public void deleteProveedorMisim(ProveedorMisimBean proveedorMisim, String accion, Long accionId, String source) throws BusinessException {
		try {
			Proveedor proveedorTO = proveedoresMisimManager.getProveedor(proveedorMisim.getIdProveedor());
			
			proveedoresMisimManager.delete(proveedorTO.getIdProveedor(), source, accion, accionId);
		} catch (Exception e) {
			logger.error("ServicioProveedorMisimImpl - deleteProveedorMisim:" + e);
			throw new BusinessException(e, "errors.proveedorMisim.deleteProveedorMisim");
		}
	}
	
}
