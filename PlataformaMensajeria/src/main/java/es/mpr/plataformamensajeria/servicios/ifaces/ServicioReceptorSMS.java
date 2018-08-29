package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.ReceptorSMSBean;

/**
 * <p>Interface que define los metodos para la gestion de receptores SMS</p>.
 *
 * @author jgonzvil
 */

public interface ServicioReceptorSMS {
	
	/**
	 * Obtener receptores SMS.
	 *
	 * @param tipoServidor the tipo servidor
	 * @return receptores SMS
	 * @throws BusinessException the business exception
	 */
	List<ReceptorSMSBean> getReceptoresSMS(int tipoServidor) throws BusinessException;
	
	/**
	 * Obtener receptores SMS no asignados.
	 *
	 * @param idServicio the id servicio
	 * @param tipoServidor the tipo servidor
	 * @return receptores SMS no asignados
	 * @throws BusinessException the business exception
	 */
	List<ReceptorSMSBean> getReceptoresSMSNoAsignados(Integer idServicio, int tipoServidor) throws BusinessException;
	
	/**
	 * Obtener receptores SMS.
	 *
	 * @param start the start
	 * @param size the size
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @param tipoServidor the tipo servidor
	 * @return receptores SMS
	 * @throws BusinessException the business exception
	 */
	PaginatedList<ReceptorSMSBean> getReceptoresSMS(int start, int size, String order, String columnSort,ReceptorSMSBean criterio, int tipoServidor) 
		throws BusinessException;
	
	/**
	 * New receptor SMS.
	 *
	 * @param receptorSMS the receptor SMS
	 * @param tipoServidor the tipo servidor
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @return the long
	 * @throws BusinessException the business exception
	 */
	Long newReceptorSMS(ReceptorSMSBean receptorSMS, int tipoServidor, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Update receptor SMS.
	 *
	 * @param receptorSMS the receptor SMS
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void updateReceptorSMS(ReceptorSMSBean receptorSMS, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Load receptor SMS.
	 *
	 * @param receptorSMS the receptor SMS
	 * @return the receptor SMS bean
	 * @throws BusinessException the business exception
	 */
	ReceptorSMSBean loadReceptorSMS(ReceptorSMSBean receptorSMS)throws BusinessException;
	
	/**
	 * Delete receptor SMS.
	 *
	 * @param receptorSMS the receptor SMS
	 * @param accionServidor the accion servidor
	 * @param accionIdServidor the accion id servidor
	 * @param source the source
	 * @param accionPlanificacion the accion planificacion
	 * @param accionIdPlanificacion the accion id planificacion
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void deleteReceptorSMS(ReceptorSMSBean receptorSMS, String accionServidor, Long accionIdServidor, String source, String accionPlanificacion, 
			Long accionIdPlanificacion, String descripcion)throws BusinessException;
}
