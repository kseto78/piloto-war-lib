package es.mpr.plataformamensajeria.servicios.ifaces;

import javax.servlet.http.HttpServletRequest;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.UsuariosPushBean;

/**
 * <p>Interface que define los m&eacute;todos para la consulta de usuarios movil</p>
 * 
 * @author jgonzvil
 *
 */

public interface ServicioUsuariosPush {
	
	PaginatedList<UsuariosPushBean> getUsuariosPush(int start, int size, String order, String columnSort, 
			UsuariosPushBean criterio, boolean isExport, HttpServletRequest request) 
		throws BusinessException;
	
}
