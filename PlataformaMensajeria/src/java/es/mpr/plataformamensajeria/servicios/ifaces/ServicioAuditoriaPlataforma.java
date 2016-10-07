package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.AuditoriaPlataformaBean;
import es.mpr.plataformamensajeria.beans.AuditoriaPlataformaBean;

/**
 * <p>Interface que define los m&eacute;todos para la consulta de adutorias</p>
 * 
 * @author i-nercya
 *
 */
@Service
public interface ServicioAuditoriaPlataforma {
	
	List<AuditoriaPlataformaBean> getAuditoriasPlataforma(AuditoriaPlataformaBean criterio) throws BusinessException;
	
	PaginatedList<AuditoriaPlataformaBean> getAuditoriasPlataforma(int start, int size, String order, String columnSort,AuditoriaPlataformaBean criterio,boolean isExport) 
		throws BusinessException;
	
	public Integer getTotalAuditoriasPlataforma(AuditoriaPlataformaBean criterio, EntityManager em,StringBuffer query) throws BusinessException;
	
}
