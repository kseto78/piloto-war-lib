package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.ParametroServidorBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de parámetros del servidor</p>
 * 
 * @author Selered
 *
 */
@Service
public interface ServicioParametroServidor {
	
	List<ParametroServidorBean> getParametrosServidor(ParametroServidorBean criterio) throws BusinessException;
	
	PaginatedList<ParametroServidorBean> getParametrosServidor(int start, int size, String order, String columnSort,ParametroServidorBean criterio) 
		throws BusinessException;
	
	public Integer getTotalParametrosServidor(ParametroServidorBean criterio, EntityManager em) throws BusinessException;
	
	void newParametroServidor(ParametroServidorBean parametroServidor)throws BusinessException;
	
	void updateParametroServidor(ParametroServidorBean parametroServidor)throws BusinessException;
	
	ParametroServidorBean loadParametroServidor(ParametroServidorBean parametroServidor)throws BusinessException;
	
	void deleteParametroServidor(ParametroServidorBean parametroServidor)throws BusinessException;
	
	List<ParametroServidorBean> getParametroServidorByServidorId(Integer servidorId) throws BusinessException;
	
	List<ParametroServidorBean> getParametroServidorByProveedorSMSId(Integer proveedorSMSId) throws BusinessException;
	
	List<ParametroServidorBean> getParametroServidorByReceptorSMSId(Integer receptorSMSId) throws BusinessException;
	
	List<ParametroServidorBean> getParametroServidorByServidorPushId(Integer servidorPushId) throws BusinessException;
	
	boolean existeParametroServidor(ParametroServidorBean parametroServidor)throws BusinessException;
	
}
