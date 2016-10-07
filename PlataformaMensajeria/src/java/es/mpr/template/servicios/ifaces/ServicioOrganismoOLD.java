package es.mpr.template.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.template.beans.OrganismoBeanOLD;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de organismos</p>
 * 
 * @author Altran
 *
 */
@Service
public interface ServicioOrganismoOLD {
	
	List<OrganismoBeanOLD> getOrganismos(OrganismoBeanOLD criterio) throws BusinessException;
	
	PaginatedList<OrganismoBeanOLD> getOrganismos(int start, int size, String order, String columnSort,OrganismoBeanOLD criterio) 
		throws BusinessException;
	
	public Integer getTotalOrganismos(OrganismoBeanOLD criterio) throws BusinessException;
	
	void newOrganismo(OrganismoBeanOLD organismo)throws BusinessException;
	
	void updateOrganismo(OrganismoBeanOLD organismo)throws BusinessException;
	
	OrganismoBeanOLD loadOrganismo(OrganismoBeanOLD organismo)throws BusinessException;
	
	void deleteOrganismo(OrganismoBeanOLD organismo)throws BusinessException;
}
