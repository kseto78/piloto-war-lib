package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de proveedores SMS</p>.
 *
 * @author Selered
 */
public interface ServicioProveedorSMS {
	
	/**
	 * Obtener proveedores SMS.
	 *
	 * @param tipoServidor the tipo servidor
	 * @return proveedores SMS
	 * @throws BusinessException the business exception
	 */
	List<ProveedorSMSBean> getProveedoresSMS(int tipoServidor) throws BusinessException;
	
	/**
	 * Obtener proveedores SMS no asignados.
	 *
	 * @param idServicio the id servicio
	 * @param tipoServidor the tipo servidor
	 * @return proveedores SMS no asignados
	 * @throws BusinessException the business exception
	 */
	List<ProveedorSMSBean> getProveedoresSMSNoAsignados(Integer idServicio, int tipoServidor) throws BusinessException;
	
	/**
	 * Obtener proveedores SMS.
	 *
	 * @param start the start
	 * @param size the size
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @param tipoServidor the tipo servidor
	 * @return proveedores SMS
	 * @throws BusinessException the business exception
	 */
	PaginatedList<ProveedorSMSBean> getProveedoresSMS(int start, int size, String order, String columnSort,ProveedorSMSBean criterio, int tipoServidor) 
		throws BusinessException;
	
	/**
	 * New proveedor SMS.
	 *
	 * @param proveedorSMS the proveedor SMS
	 * @param tipoServidor the tipo servidor
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @return the long
	 * @throws BusinessException the business exception
	 */
	Long newProveedorSMS(ProveedorSMSBean proveedorSMS, int tipoServidor, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Update proveedor SMS.
	 *
	 * @param proveedorSMS the proveedor SMS
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void updateProveedorSMS(ProveedorSMSBean proveedorSMS, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Load proveedor SMS.
	 *
	 * @param proveedorSMS the proveedor SMS
	 * @return the proveedor SMS bean
	 * @throws BusinessException the business exception
	 */
	ProveedorSMSBean loadProveedorSMS(ProveedorSMSBean proveedorSMS)throws BusinessException;
	
	/**
	 * Delete proveedor SMS.
	 *
	 * @param proveedorSMS the proveedor SMS
	 * @param accionServidor the accion servidor
	 * @param accionIdServidor the accion id servidor
	 * @param source the source
	 * @param accionPlanificacion the accion planificacion
	 * @param accionIdPlanificacion the accion id planificacion
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void deleteProveedorSMS(ProveedorSMSBean proveedorSMS,  String accionServidor, Long accionIdServidor, String source, String accionPlanificacion, 
			Long accionIdPlanificacion, String descripcion)throws BusinessException;

}
