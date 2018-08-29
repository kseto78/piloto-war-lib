package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.ServidorPushBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de servidores Push</p>.
 *
 * @author jgonzvil
 */
public interface ServicioServidorPush {
	
	/**
	 * Obtener servidores push.
	 *
	 * @param tipoServidor the tipo servidor
	 * @return servidores push
	 * @throws BusinessException the business exception
	 */
	List<ServidorPushBean> getServidoresPush(int tipoServidor) throws BusinessException;
	
	/**
	 * Obtener servidores push no asignados.
	 *
	 * @param idServicio the id servicio
	 * @param tipoServidor the tipo servidor
	 * @return servidores push no asignados
	 * @throws BusinessException the business exception
	 */
	List<ServidorPushBean> getServidoresPushNoAsignados(Integer idServicio, int tipoServidor) throws BusinessException;
	
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
	PaginatedList<ServidorPushBean> getServidoresPush(int start, int size, String order, String columnSort,ServidorPushBean criterio, int tipoServidor) 
		throws BusinessException;
	
	/**
	 * New servidor push.
	 *
	 * @param servidorPush the servidor push
	 * @param tipoServidor the tipo servidor
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @return the long
	 * @throws BusinessException the business exception
	 */
	Long newServidorPush(ServidorPushBean servidorPush, int tipoServidor, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Update servidor push.
	 *
	 * @param servidorPush the servidor push
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void updateServidorPush(ServidorPushBean servidorPush, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Load servidor push.
	 *
	 * @param servidorPush the servidor push
	 * @return the servidor push bean
	 * @throws BusinessException the business exception
	 */
	ServidorPushBean loadServidorPush(ServidorPushBean servidorPush)throws BusinessException;
	
	/**
	 * Delete servidor push.
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
	void deleteServidorPush(ServidorPushBean servidorPush, String accionServidor, Long accionIdServidor, String source,
			String accionPlanificacion, Long accionIdPlanificacion, String descripcion)throws BusinessException;

}
