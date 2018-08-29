package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.ParametroServidorBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de parámetros del servidor</p>.
 *
 * @author Selered
 */
public interface ServicioParametroServidor {
	
		
	/**
	 * New parametro servidor.
	 *
	 * @param parametroServidor the parametro servidor
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void newParametroServidor(ParametroServidorBean parametroServidor, String source, String accion, Long accionId, String descripcion)throws BusinessException;
		
	/**
	 * Delete parametro servidor.
	 *
	 * @param parametroServidor the parametro servidor
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void deleteParametroServidor(ParametroServidorBean parametroServidor, String source, String accion, Long accionId, String descripcion)throws BusinessException;
	
	/**
	 * Obtener parametro servidor by servidor id.
	 *
	 * @param servidorId the servidor id
	 * @return parametro servidor by servidor id
	 * @throws BusinessException the business exception
	 */
	List<ParametroServidorBean> getParametroServidorByServidorId(Integer servidorId) throws BusinessException;
	
	/**
	 * Obtener parametro servidor by proveedor SMS id.
	 *
	 * @param proveedorSMSId the proveedor SMS id
	 * @return parametro servidor by proveedor SMS id
	 * @throws BusinessException the business exception
	 */
	List<ParametroServidorBean> getParametroServidorByProveedorSMSId(Integer proveedorSMSId) throws BusinessException;
	
	/**
	 * Obtener parametro servidor by receptor SMS id.
	 *
	 * @param receptorSMSId the receptor SMS id
	 * @return parametro servidor by receptor SMS id
	 * @throws BusinessException the business exception
	 */
	List<ParametroServidorBean> getParametroServidorByReceptorSMSId(Integer receptorSMSId) throws BusinessException;
	
	/**
	 * Obtener parametro servidor by servidor push id.
	 *
	 * @param servidorPushId the servidor push id
	 * @return parametro servidor by servidor push id
	 * @throws BusinessException the business exception
	 */
	List<ParametroServidorBean> getParametroServidorByServidorPushId(Integer servidorPushId) throws BusinessException;
	
	/**
	 * Existe parametro servidor.
	 *
	 * @param parametroServidor the parametro servidor
	 * @return true, if successful
	 * @throws BusinessException the business exception
	 */
	boolean existeParametroServidor(ParametroServidorBean parametroServidor)throws BusinessException;
	
}
