package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.ComunicacionBean;
import es.mpr.plataformamensajeria.beans.EndpointBean;
import es.mpr.plataformamensajeria.beans.ProductoBean;
import es.mpr.plataformamensajeria.beans.ProveedorMisimBean;
import es.mpr.plataformamensajeria.beans.TransformacionBean;


/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de servidor</p>.
 *
 * @author Selered
 */
public interface ServicioProveedorMisim {
	
	/**
	 * Obtener proveedores misim.
	 *
	 * @param start the start
	 * @param size the size
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @return proveedores misim
	 * @throws BusinessException the business exception
	 */
	PaginatedList<ProveedorMisimBean> getProveedoresMisim(int start, int size,
			String order, String columnSort, ProveedorMisimBean criterio)
			throws BusinessException;

	/**
	 * Obtener productos.
	 *
	 * @return productos
	 * @throws BusinessException the business exception
	 */
	List<ProductoBean> getProductos() throws BusinessException;

	/**
	 * Obtener endpoints.
	 *
	 * @return endpoints
	 * @throws BusinessException the business exception
	 */
	List<EndpointBean> getEndpoints() throws BusinessException;

	/**
	 * Obtener transformaciones.
	 *
	 * @return transformaciones
	 * @throws BusinessException the business exception
	 */
	List<TransformacionBean> getTransformaciones() throws BusinessException;

	/**
	 * Obtener comunicaciones.
	 *
	 * @return comunicaciones
	 * @throws BusinessException the business exception
	 */
	List<ComunicacionBean> getComunicaciones() throws BusinessException;

	/**
	 * New proveedor misim.
	 *
	 * @param proveedorMisim the proveedor misim
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @return the long
	 * @throws BusinessException the business exception
	 */
	Long newProveedorMisim(ProveedorMisimBean proveedorMisim, String source, String accion, Long accionId) throws BusinessException;
	
	/**
	 * New producto misim.
	 *
	 * @param producto the producto
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @return the long
	 * @throws BusinessException the business exception
	 */
	Long newProductoMisim(ProductoBean producto, String source, String accion, Long accionId) throws BusinessException;

	/**
	 * New endpoint misim.
	 *
	 * @param endpoint the endpoint
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @return the long
	 * @throws BusinessException the business exception
	 */
	Long newEndpointMisim(EndpointBean endpoint, String source, String accion, Long accionId) throws BusinessException;

	/**
	 * New transformacion misim.
	 *
	 * @param transformacion the transformacion
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @return the long
	 * @throws BusinessException the business exception
	 */
	Long newTransformacionMisim(TransformacionBean transformacion, String source, String accion, Long accionId) throws BusinessException;

	/**
	 * Load proveedor misim.
	 *
	 * @param proveedorMisim the proveedor misim
	 * @return the proveedor misim bean
	 * @throws BusinessException the business exception
	 */
	ProveedorMisimBean loadProveedorMisim(ProveedorMisimBean proveedorMisim) throws BusinessException;

	/**
	 * Delete proveedor misim.
	 *
	 * @param proveedorMisim the proveedor misim
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param source the source
	 * @throws BusinessException the business exception
	 */
	void deleteProveedorMisim(ProveedorMisimBean proveedorMisim, String accion, Long accionId, String source) throws BusinessException;

	/**
	 * Load proveedor misim update.
	 *
	 * @param proveedorMisim the proveedor misim
	 * @return the proveedor misim bean
	 * @throws BusinessException the business exception
	 */
	ProveedorMisimBean loadProveedorMisimUpdate(ProveedorMisimBean proveedorMisim) throws BusinessException;

	/**
	 * Update proveedor misim.
	 *
	 * @param proveedorMisim the proveedor misim
	 * @param proveedorMisimNuevo the proveedor misim nuevo
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void updateProveedorMisim(ProveedorMisimBean proveedorMisim, ProveedorMisimBean proveedorMisimNuevo, String source, String accion, Long accionId) throws BusinessException;
	

}
