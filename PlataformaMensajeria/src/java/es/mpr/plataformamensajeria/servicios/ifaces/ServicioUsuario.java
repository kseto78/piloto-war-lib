package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.UsuarioBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de usuarios</p>
 * 
 * @author Selered
 *
 */
@Service
public interface ServicioUsuario {
	 
	List<UsuarioBean> getUsuarios(UsuarioBean criterio) throws BusinessException;
	
	List<UsuarioBean> getUsuarios() throws BusinessException;
	
	PaginatedList<UsuarioBean> getUsuarios(int start, int size, String order, String columnSort,UsuarioBean criterio) 
		throws BusinessException;
	
	public Integer getTotalUsuarios(UsuarioBean criterio, EntityManager em,StringBuffer namedCountQuery,boolean nombre, boolean aplicacionId, boolean rolId) throws BusinessException;
	
	Integer newUsuario(UsuarioBean usuario)throws BusinessException;
	
	void updateUsuario(UsuarioBean usuario)throws BusinessException;
	
	UsuarioBean loadUsuario(UsuarioBean usuario)throws BusinessException;
	
	void deleteUsuario(UsuarioBean usuario)throws BusinessException;

	boolean existeUsuario(String loginUsuario) throws BusinessException;
	
	boolean existeUsuarioEdicion(Integer idUsuario, String loginUsuario) throws BusinessException;
	
	
}
