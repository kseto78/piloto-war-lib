package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.UsuarioBean;
import es.mpr.plataformamensajeria.beans.UsuariosPushBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de usuarios</p>
 * 
 * @author Selered
 *
 */

public interface ServicioUsuario {
	 
	PaginatedList<UsuarioBean> getUsuarios(int start, int size, String order, String columnSort,UsuarioBean criterio) 
		throws BusinessException;
	
	Integer newUsuario(UsuarioBean usuario, String source, String accion, Long accionId)throws BusinessException;
	
	void updateUsuario(UsuarioBean usuario, String source, String accion, Long accionId)throws BusinessException;
	
	UsuarioBean loadUsuario(UsuarioBean usuario)throws BusinessException;
	
	void deleteUsuario(UsuarioBean usuario, String source, String accion, Long accionId)throws BusinessException;

	boolean existeUsuario(String loginUsuario) throws BusinessException;
	
	boolean existeUsuarioEdicion(Integer idUsuario, String loginUsuario) throws BusinessException;

	Integer getRolIdByUsername(String userName);

	List<UsuariosPushBean> getUsuariosByServicioMovilId(Long servicioMovilId) throws BusinessException;
	
	
}
