package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.ServidorWebPushBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de servidores Push</p>.
 *
 * @author jgonzvil
 */
public interface ServicioServidorWebPush {
	
	/**
	 * Obtener servidores web push.
	 *
	 * @param tipoServidor the tipo servidor
	 * @return servidores web push
	 * @throws BusinessException the business exception
	 */
	List<ServidorWebPushBean> getServidoresWebPush(int tipoServidor) throws BusinessException;
	
	/**
	 * Obtener servidores push.
	 *
	 * @param start the start
	 * @param size the size
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @param tipoServidor the tipo servidor
	 * @return servidores push
	 * @throws BusinessException the business exception
	 */
	PaginatedList<ServidorWebPushBean> getServidoresPush(int start, int size, String order, String columnSort,ServidorWebPushBean criterio, int tipoServidor) 
		throws BusinessException;
	
	/**
	 * New servidor web push.
	 *
	 * @param servidorWebPush the servidor web push
	 * @param tipoServidor the tipo servidor
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @return the long
	 * @throws BusinessException the business exception
	 */
	Long newServidorWebPush(ServidorWebPushBean servidorWebPush, int tipoServidor, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Update servidor web push.
	 *
	 * @param servidorPush the servidor push
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void updateServidorWebPush(ServidorWebPushBean servidorPush, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Load servidor web push.
	 *
	 * @param servidorPush the servidor push
	 * @return the servidor web push bean
	 * @throws BusinessException the business exception
	 */
	ServidorWebPushBean loadServidorWebPush(ServidorWebPushBean servidorPush)throws BusinessException;
	
	/**
	 * Delete servidor web push.
	 *
	 * @param servidorPush the servidor push
	 * @param accionServidor the accion servidor
	 * @param accionIdServidor the accion id servidor
	 * @param source the source
	 * @param accionPlanificacion the accion planificacion
	 * @param accionIdPlanificacion the accion id planificacion
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void deleteServidorWebPush(ServidorWebPushBean servidorPush, String accionServidor, Long accionIdServidor, String source,
			String accionPlanificacion, Long accionIdPlanificacion, String descripcion)throws BusinessException;

	/**
	 * Obtener servidores web push no asignados.
	 *
	 * @param idServicio the id servicio
	 * @param tipoServidor the tipo servidor
	 * @return servidores web push no asignados
	 * @throws BusinessException the business exception
	 */
	List<ServidorWebPushBean> getServidoresWebPushNoAsignados(Integer idServicio, int tipoServidor)
			throws BusinessException;

}
