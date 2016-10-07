package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.ReceptorSMSBean;

/**
 * <p>Interface que define los metodos para la gestion de receptores SMS</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioReceptorSMS {
	
	List<ReceptorSMSBean> getReceptoresSMS(ReceptorSMSBean criterio) throws BusinessException;
	
	List<ReceptorSMSBean> getReceptoresSMS() throws BusinessException;
	
	List<ReceptorSMSBean> getReceptoresSMSNoAsignados(Integer idServicio) throws BusinessException;
	
	PaginatedList<ReceptorSMSBean> getReceptoresSMS(int start, int size, String order, String columnSort,ReceptorSMSBean criterio) 
		throws BusinessException;
	
	public Integer getTotalReceptoresSMS(ReceptorSMSBean criterio, EntityManager em) throws BusinessException;
	
	Long newReceptorSMS(ReceptorSMSBean receptorSMS)throws BusinessException;
	
	void updateReceptorSMS(ReceptorSMSBean receptorSMS)throws BusinessException;
	
	ReceptorSMSBean loadReceptorSMS(ReceptorSMSBean receptorSMS)throws BusinessException;
	
	void deleteReceptorSMS(ReceptorSMSBean receptorSMS)throws BusinessException;
}
