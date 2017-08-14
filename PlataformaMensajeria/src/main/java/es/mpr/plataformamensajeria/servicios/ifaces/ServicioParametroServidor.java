package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.ParametroServidorBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de parámetros del servidor</p>
 * 
 * @author Selered
 *
 */
public interface ServicioParametroServidor {
	
		
	void newParametroServidor(ParametroServidorBean parametroServidor, String source, String accion, Long accionId, String descripcion)throws BusinessException;
		
	void deleteParametroServidor(ParametroServidorBean parametroServidor, String source, String accion, Long accionId, String descripcion)throws BusinessException;
	
	List<ParametroServidorBean> getParametroServidorByServidorId(Integer servidorId) throws BusinessException;
	
	List<ParametroServidorBean> getParametroServidorByProveedorSMSId(Integer proveedorSMSId) throws BusinessException;
	
	List<ParametroServidorBean> getParametroServidorByReceptorSMSId(Integer receptorSMSId) throws BusinessException;
	
	List<ParametroServidorBean> getParametroServidorByServidorPushId(Integer servidorPushId) throws BusinessException;
	
	boolean existeParametroServidor(ParametroServidorBean parametroServidor)throws BusinessException;
	
}
