package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.OrganismoBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de aplicacion</p>
 * 
 * @author Selered
 *
 */
public interface ServicioOrganismo {
	
	List<OrganismoBean> getOrganismos() throws BusinessException;
	
	List<Integer> getOrganismos(String rolUsuario, Integer idUsuario) throws BusinessException;
	
	PaginatedList<OrganismoBean> getOrganismos(int start, int size, String order, String columnSort,OrganismoBean criterio) 
		throws BusinessException;
	
	Integer newOrganismo(OrganismoBean organismo, String source, String accion, Long accionId)throws BusinessException;
	
	void updateOrganismo(OrganismoBean organismo, String source, String accion, Long accionId)throws BusinessException;
	
	OrganismoBean loadOrganismo(OrganismoBean organismo)throws BusinessException;
	
	void deleteOrganismo(Long organismoId, String source, String accion, Long accionId)throws BusinessException;

	List<String> listAutocomplete(String term);

	Integer getOrganismoIdByDir3(String search);

	Boolean existeOrganimo(OrganismoBean organismo);
	
}

