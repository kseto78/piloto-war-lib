package es.mpr.plataformamensajeria.servicios.ifaces;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.AuditoriaPlataformaBean;

/**
 * <p>Interface que define los m&eacute;todos para la consulta de adutorias</p>.
 *
 * @author i-nercya
 */
public interface ServicioAuditoriaPlataforma {
	
	/**
	 * Obtener auditorias plataforma.
	 *
	 * @param start the start
	 * @param size the size
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @param isExport the is export
	 * @return auditorias plataforma
	 * @throws BusinessException the business exception
	 */
	PaginatedList<AuditoriaPlataformaBean> getAuditoriasPlataforma(int start, int size, String order, String columnSort,AuditoriaPlataformaBean criterio,boolean isExport) 
		throws BusinessException;
		
}
