package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.UsuarioBean;
import es.mpr.plataformamensajeria.beans.UsuariosPushBean;


/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de usuarios</p>.
 *
 * @author Selered
 */

public interface ServicioUsuario {
	 
	/**
	 * Obtener usuarios.
	 *
	 * @param start the start
	 * @param size the size
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @return usuarios
	 * @throws BusinessException the business exception
	 */
	PaginatedList<UsuarioBean> getUsuarios(int start, int size, String order, String columnSort,UsuarioBean criterio) 
		throws BusinessException;
	
	/**
	 * New usuario.
	 *
	 * @param usuario the usuario
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @return the integer
	 * @throws BusinessException the business exception
	 */
	Integer newUsuario(UsuarioBean usuario, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Update usuario.
	 *
	 * @param usuario the usuario
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void updateUsuario(UsuarioBean usuario, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Load usuario.
	 *
	 * @param usuario the usuario
	 * @return the usuario bean
	 * @throws BusinessException the business exception
	 */
	UsuarioBean loadUsuario(UsuarioBean usuario)throws BusinessException;
	
	/**
	 * Delete usuario.
	 *
	 * @param usuario the usuario
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void deleteUsuario(UsuarioBean usuario, String source, String accion, Long accionId)throws BusinessException;

	/**
	 * Existe usuario.
	 *
	 * @param loginUsuario the login usuario
	 * @return true, if successful
	 * @throws BusinessException the business exception
	 */
	boolean existeUsuario(String loginUsuario) throws BusinessException;
	
	/**
	 * Existe usuario edicion.
	 *
	 * @param idUsuario the id usuario
	 * @param loginUsuario the login usuario
	 * @return true, if successful
	 * @throws BusinessException the business exception
	 */
	boolean existeUsuarioEdicion(Integer idUsuario, String loginUsuario) throws BusinessException;

	/**
	 * Obtener rol id by username.
	 *
	 * @param userName the user name
	 * @return rol id by username
	 */
	Integer getRolIdByUsername(String userName);

	/**
	 * Obtener usuarios by servicio movil id.
	 *
	 * @param servicioMovilId the servicio movil id
	 * @return usuarios by servicio movil id
	 * @throws BusinessException the business exception
	 */
	List<UsuariosPushBean> getUsuariosByServicioMovilId(Long servicioMovilId) throws BusinessException;
	
	
}
