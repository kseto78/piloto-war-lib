package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.UsuarioAplicacionBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de usuarios/aplicaciones</p>
 * 
 * @author i-nercya
 *
 */
public interface ServicioUsuarioAplicacion {
	 
	List<UsuarioAplicacionBean> getUsuarioAplicacionesByUsuarioId(Integer usuarioId ) throws BusinessException;
	
	List<UsuarioAplicacionBean> getUsuarioAplicacionesByAplicacionId(Integer aplicacionId ) throws BusinessException;
	
	Integer newUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacion, String source, String accion, Long accionId, String descripcion)throws BusinessException;
	
	void deleteUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacionBean, String source, String accion, Long accionId, String descripcion)throws BusinessException;
		
	public UsuarioAplicacionBean loadUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacionBean)	throws BusinessException;

	List<UsuarioAplicacionBean> getListPermisosAplicacionesUsuario(String userName);
}
