package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de proveedores SMS</p>
 * 
 * @author Selered
 *
 */
public interface ServicioProveedorSMS {
	
	List<ProveedorSMSBean> getProveedoresSMS(int tipoServidor) throws BusinessException;
	
	List<ProveedorSMSBean> getProveedoresSMSNoAsignados(Integer idServicio, int tipoServidor) throws BusinessException;
	
	PaginatedList<ProveedorSMSBean> getProveedoresSMS(int start, int size, String order, String columnSort,ProveedorSMSBean criterio, int tipoServidor) 
		throws BusinessException;
	
	Long newProveedorSMS(ProveedorSMSBean proveedorSMS, int tipoServidor, String source, String accion, Long accionId)throws BusinessException;
	
	void updateProveedorSMS(ProveedorSMSBean proveedorSMS, String source, String accion, Long accionId)throws BusinessException;
	
	ProveedorSMSBean loadProveedorSMS(ProveedorSMSBean proveedorSMS)throws BusinessException;
	
	void deleteProveedorSMS(ProveedorSMSBean proveedorSMS,  String accionServidor, Long accionIdServidor, String source, String accionPlanificacion, 
			Long accionIdPlanificacion, String descripcion)throws BusinessException;

}
