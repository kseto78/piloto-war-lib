package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.UsuarioAplicacionBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de usuarios/aplicaciones</p>.
 *
 * @author i-nercya
 */
public interface ServicioUsuarioAplicacion {
	 
	/**
	 * Obtener usuario aplicaciones by usuario id.
	 *
	 * @param usuarioId the usuario id
	 * @return usuario aplicaciones by usuario id
	 * @throws BusinessException the business exception
	 */
	List<UsuarioAplicacionBean> getUsuarioAplicacionesByUsuarioId(Integer usuarioId ) throws BusinessException;
	
	/**
	 * Obtener usuario aplicaciones by aplicacion id.
	 *
	 * @param aplicacionId the aplicacion id
	 * @return usuario aplicaciones by aplicacion id
	 * @throws BusinessException the business exception
	 */
	List<UsuarioAplicacionBean> getUsuarioAplicacionesByAplicacionId(Integer aplicacionId ) throws BusinessException;
	
	/**
	 * New usuario aplicacion.
	 *
	 * @param usuarioAplicacion the usuario aplicacion
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcion the descripcion
	 * @return the integer
	 * @throws BusinessException the business exception
	 */
	Integer newUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacion, String source, String accion, Long accionId, String descripcion)throws BusinessException;
	
	/**
	 * Delete usuario aplicacion.
	 *
	 * @param usuarioAplicacionBean the usuario aplicacion bean
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void deleteUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacionBean, String source, String accion, Long accionId, String descripcion)throws BusinessException;
		
	/**
	 * Load usuario aplicacion.
	 *
	 * @param usuarioAplicacionBean the usuario aplicacion bean
	 * @return the usuario aplicacion bean
	 * @throws BusinessException the business exception
	 */
	public UsuarioAplicacionBean loadUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacionBean)	throws BusinessException;

	/**
	 * Obtener list permisos aplicaciones usuario.
	 *
	 * @param userName the user name
	 * @return list permisos aplicaciones usuario
	 */
	List<UsuarioAplicacionBean> getListPermisosAplicacionesUsuario(String userName);
}
