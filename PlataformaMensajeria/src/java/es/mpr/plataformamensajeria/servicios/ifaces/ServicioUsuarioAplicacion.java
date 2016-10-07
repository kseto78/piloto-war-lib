package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.UsuarioAplicacionBean;
import es.mpr.plataformamensajeria.model.UsuarioAplicacionJPA;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de usuarios/aplicaciones</p>
 * 
 * @author i-nercya
 *
 */
@Service
public interface ServicioUsuarioAplicacion {
	 
	List<UsuarioAplicacionBean> getUsuarioAplicacionesByUsuarioId(Integer usuarioId ) throws BusinessException;
	
	List<UsuarioAplicacionBean> getUsuarioAplicacionesByAplicacionId(Integer aplicacionId ) throws BusinessException;
	
	Integer newUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacion)throws BusinessException;
	
	void deleteUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacionBean)throws BusinessException;
	
	UsuarioAplicacionJPA loadUsuarioAplicacionJPA(UsuarioAplicacionJPA usuarioAplicacionJPA) throws BusinessException;
	
	public UsuarioAplicacionBean loadUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacionBean)	throws BusinessException;
}
