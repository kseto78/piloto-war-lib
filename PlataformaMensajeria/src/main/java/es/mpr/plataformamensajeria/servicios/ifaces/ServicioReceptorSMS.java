package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.ReceptorSMSBean;

/**
 * <p>Interface que define los metodos para la gestion de receptores SMS</p>
 * 
 * @author jgonzvil
 *
 */

public interface ServicioReceptorSMS {
	
	List<ReceptorSMSBean> getReceptoresSMS(int tipoServidor) throws BusinessException;
	
	List<ReceptorSMSBean> getReceptoresSMSNoAsignados(Integer idServicio, int tipoServidor) throws BusinessException;
	
	PaginatedList<ReceptorSMSBean> getReceptoresSMS(int start, int size, String order, String columnSort,ReceptorSMSBean criterio, int tipoServidor) 
		throws BusinessException;
	
	Long newReceptorSMS(ReceptorSMSBean receptorSMS, int tipoServidor, String source, String accion, Long accionId)throws BusinessException;
	
	void updateReceptorSMS(ReceptorSMSBean receptorSMS, String source, String accion, Long accionId)throws BusinessException;
	
	ReceptorSMSBean loadReceptorSMS(ReceptorSMSBean receptorSMS)throws BusinessException;
	
	void deleteReceptorSMS(ReceptorSMSBean receptorSMS, String accionServidor, Long accionIdServidor, String source, String accionPlanificacion, 
			Long accionIdPlanificacion, String descripcion)throws BusinessException;
}
