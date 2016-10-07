package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.UsuariosPushBean;

/**
 * <p>Interface que define los m&eacute;todos para la consulta de usuarios movil</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioUsuariosPush {
	
	List<UsuariosPushBean> getUsuariosPush(UsuariosPushBean criterio) throws BusinessException;
	
	PaginatedList<UsuariosPushBean> getUsuariosPush(int start, int size, String order, String columnSort, 
			UsuariosPushBean criterio, boolean isExport, HttpServletRequest request) 
		throws BusinessException;
	
	public Integer getTotalUsuariosPush(UsuariosPushBean criterio, EntityManager em,StringBuffer query) throws BusinessException;
	
}
