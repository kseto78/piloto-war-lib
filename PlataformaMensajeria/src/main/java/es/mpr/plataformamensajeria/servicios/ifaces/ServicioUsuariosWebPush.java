package es.mpr.plataformamensajeria.servicios.ifaces;

import javax.servlet.http.HttpServletRequest;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.UsuariosWebPushBean;

/**
 * <p>Interface que define los m&eacute;todos para la consulta de usuarios movil</p>.
 *
 * @author jgonzvil
 */

public interface ServicioUsuariosWebPush {
	
	/**
	 * Generarnuevas claves.
	 *
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	String generarnuevasClaves() throws BusinessException;
	
	/**
	 * Obtener usuarios push.
	 *
	 * @param start the start
	 * @param size the size
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @param isExport the is export
	 * @param request the request
	 * @return usuarios push
	 * @throws BusinessException the business exception
	 */
	PaginatedList<UsuariosWebPushBean> getUsuariosPush(int start, int size, String order, String columnSort, 
			UsuariosWebPushBean criterio, boolean isExport, HttpServletRequest request) 
		throws BusinessException;
	
}
