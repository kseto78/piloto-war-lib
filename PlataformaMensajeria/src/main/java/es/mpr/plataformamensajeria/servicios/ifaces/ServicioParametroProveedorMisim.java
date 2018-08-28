package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.ParametrosProveedorBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de parámetros del servidor</p>.
 *
 * @author Selered
 */
public interface ServicioParametroProveedorMisim {

	/**
	 * Obtener parametros proveedor misim by proveedor id.
	 *
	 * @param idProveedor the id proveedor
	 * @return parametros proveedor misim by proveedor id
	 * @throws BusinessException the business exception
	 */
	List<ParametrosProveedorBean> getParametrosProveedorMisimByProveedorId(Long idProveedor) throws BusinessException;

	/**
	 * Existe parametro proveedor misim.
	 *
	 * @param parametroProveedor the parametro proveedor
	 * @return true, if successful
	 */
	boolean existeParametroProveedorMisim(ParametrosProveedorBean parametroProveedor);

	/**
	 * New parametro proveedor misim.
	 *
	 * @param parametroProveedorMisim the parametro proveedor misim
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void newParametroProveedorMisim(ParametrosProveedorBean parametroProveedorMisim, String source,String accion, Long accionId, String descripcion) throws BusinessException;

	/**
	 * Delete parametro proveedor misim.
	 *
	 * @param parametrosProveedor the parametros proveedor
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void deleteParametroProveedorMisim(ParametrosProveedorBean parametrosProveedor, String source,String accion, Long accionId, String descripcion) throws BusinessException;
	
}
