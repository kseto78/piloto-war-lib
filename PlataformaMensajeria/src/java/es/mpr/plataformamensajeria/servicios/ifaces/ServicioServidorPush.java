package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.ServidorPushBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de servidores Push</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioServidorPush {
	
	List<ServidorPushBean> getServidoresPush(ServidorPushBean criterio) throws BusinessException;
	
	List<ServidorPushBean> getServidoresPush() throws BusinessException;
	
	List<ServidorPushBean> getServidoresPushNoAsignados(Integer idServicio) throws BusinessException;
	
	PaginatedList<ServidorPushBean> getServidoresPush(int start, int size, String order, String columnSort,ServidorPushBean criterio) 
		throws BusinessException;
	
	public Integer getTotalServidoresPush(ServidorPushBean criterio, EntityManager em) throws BusinessException;
	
	Long newServidorPush(ServidorPushBean servidorPush)throws BusinessException;
	
	void updateServidorPush(ServidorPushBean servidorPush)throws BusinessException;
	
	ServidorPushBean loadServidorPush(ServidorPushBean servidorPush)throws BusinessException;
	
	void deleteServidorPush(ServidorPushBean servidorPush)throws BusinessException;
}
