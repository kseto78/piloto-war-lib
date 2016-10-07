package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.OrganismoBean;
import es.mpr.plataformamensajeria.model.AplicacionJPA;
import es.mpr.plataformamensajeria.model.OrganismoJPA;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de aplicacion</p>
 * 
 * @author Selered
 *
 */
@Service
public interface ServicioOrganismo {
	
	List<OrganismoBean> getOrganismos(OrganismoBean criterio) throws BusinessException;
	List<OrganismoBean> getOrganismos() throws BusinessException;
	OrganismoJPA loadOrganismoJPA(OrganismoJPA organismo) throws BusinessException;
	PaginatedList<OrganismoBean> getOrganismos(int start, int size, String order, String columnSort,OrganismoBean criterio) 
		throws BusinessException;
	
	public Integer getTotalOrganismos(OrganismoBean criterio, EntityManager em) throws BusinessException;
	
	Integer newOrganismo(OrganismoBean organismo)throws BusinessException;
	
	void updateOrganismo(OrganismoBean organismo)throws BusinessException;
	
	OrganismoBean loadOrganismo(OrganismoBean organismo)throws BusinessException;
	
	void deleteOrganismo(OrganismoBean organismo)throws BusinessException;
	
}

