package es.mpr.plataformamensajeria.servicios.ifaces;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.AuditoriaPlataformaBean;

/**
 * <p>Interface que define los m&eacute;todos para la consulta de adutorias</p>
 * 
 * @author i-nercya
 *
 */
public interface ServicioAuditoriaPlataforma {
	
	PaginatedList<AuditoriaPlataformaBean> getAuditoriasPlataforma(int start, int size, String order, String columnSort,AuditoriaPlataformaBean criterio,boolean isExport) 
		throws BusinessException;
		
}
