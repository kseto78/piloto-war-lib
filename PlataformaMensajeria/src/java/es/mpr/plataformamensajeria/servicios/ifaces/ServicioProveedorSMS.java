package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de proveedores SMS</p>
 * 
 * @author Selered
 *
 */
@Service
public interface ServicioProveedorSMS {
	
	List<ProveedorSMSBean> getProveedoresSMS(ProveedorSMSBean criterio) throws BusinessException;
	
	List<ProveedorSMSBean> getProveedoresSMS() throws BusinessException;
	
	List<ProveedorSMSBean> getProveedoresSMSNoAsignados(Integer idServicio) throws BusinessException;
	
	PaginatedList<ProveedorSMSBean> getProveedoresSMS(int start, int size, String order, String columnSort,ProveedorSMSBean criterio) 
		throws BusinessException;
	
	public Integer getTotalProveedoresSMS(ProveedorSMSBean criterio, EntityManager em) throws BusinessException;
	
	Long newProveedorSMS(ProveedorSMSBean proveedorSMS)throws BusinessException;
	
	void updateProveedorSMS(ProveedorSMSBean proveedorSMS)throws BusinessException;
	
	ProveedorSMSBean loadProveedorSMS(ProveedorSMSBean proveedorSMS)throws BusinessException;
	
	void deleteProveedorSMS(ProveedorSMSBean proveedorSMS)throws BusinessException;
}
