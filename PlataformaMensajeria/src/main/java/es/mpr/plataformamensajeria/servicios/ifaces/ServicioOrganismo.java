package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.sim.model.TblOrganismos;
import es.mpr.plataformamensajeria.beans.OrganismoBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de aplicacion</p>.
 *
 * @author Selered
 */
public interface ServicioOrganismo {
	
	/**
	 * Obtener organismos.
	 *
	 * @return organismos
	 * @throws BusinessException the business exception
	 */
	List<OrganismoBean> getOrganismos() throws BusinessException;
	
	/**
	 * Obtener organismos.
	 *
	 * @param rolUsuario the rol usuario
	 * @param idUsuario the id usuario
	 * @return organismos
	 * @throws BusinessException the business exception
	 */
	List<Integer> getOrganismos(String rolUsuario, Integer idUsuario) throws BusinessException;
	
	/**
	 * Obtener organismos.
	 *
	 * @param start the start
	 * @param size the size
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @return organismos
	 * @throws BusinessException the business exception
	 */
	PaginatedList<OrganismoBean> getOrganismos(int start, int size, String order, String columnSort,OrganismoBean criterio) 
		throws BusinessException;
	
	/**
	 * New organismo.
	 *
	 * @param organismo the organismo
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @return the integer
	 * @throws BusinessException the business exception
	 */
	Integer newOrganismo(OrganismoBean organismo, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Update organismo.
	 *
	 * @param organismo the organismo
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void updateOrganismo(OrganismoBean organismo, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Load organismo.
	 *
	 * @param organismo the organismo
	 * @return the organismo bean
	 * @throws BusinessException the business exception
	 */
	OrganismoBean loadOrganismo(OrganismoBean organismo)throws BusinessException;
	
	/**
	 * Delete organismo.
	 *
	 * @param organismoId the organismo id
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void deleteOrganismo(Long organismoId, String source, String accion, Long accionId)throws BusinessException;

	/**
	 * List autocomplete.
	 *
	 * @param term the term
	 * @return the list
	 */
	List<String> listAutocomplete(String term);

	/**
	 * Obtener organismo id by dir 3.
	 *
	 * @param search the search
	 * @return organismo id by dir 3
	 */
	Integer getOrganismoIdByDir3(String search);

	/**
	 * Existe organimo.
	 *
	 * @param organismo the organismo
	 * @return the boolean
	 */
	Boolean existeOrganimo(OrganismoBean organismo);

	List<String> getOrganismosHijos(String search);

	Integer getOrganismoIdByDir3SoloEliminado(String search);

	List<TblOrganismos> getOrganismoById(String dir3);
	
	List<TblOrganismos> getOrganismosByPdp(Long idOrganismoPdp);

	Boolean existeOrganismoServicio(Long idOrganismo, Long idServicio);
	
}

