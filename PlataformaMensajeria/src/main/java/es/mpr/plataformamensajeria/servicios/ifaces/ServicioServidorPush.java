package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.ServidorPushBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de servidores Push</p>
 * 
 * @author jgonzvil
 *
 */
public interface ServicioServidorPush {
	
	List<ServidorPushBean> getServidoresPush(int tipoServidor) throws BusinessException;
	
	List<ServidorPushBean> getServidoresPushNoAsignados(Integer idServicio, int tipoServidor) throws BusinessException;
	
	PaginatedList<ServidorPushBean> getServidoresPush(int start, int size, String order, String columnSort,ServidorPushBean criterio, int tipoServidor) 
		throws BusinessException;
	
	Long newServidorPush(ServidorPushBean servidorPush, int tipoServidor, String source, String accion, Long accionId)throws BusinessException;
	
	void updateServidorPush(ServidorPushBean servidorPush, String source, String accion, Long accionId)throws BusinessException;
	
	ServidorPushBean loadServidorPush(ServidorPushBean servidorPush)throws BusinessException;
	
	void deleteServidorPush(ServidorPushBean servidorPush, String accionServidor, Long accionIdServidor, String source,
			String accionPlanificacion, Long accionIdPlanificacion, String descripcion)throws BusinessException;

}
