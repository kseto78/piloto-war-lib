package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.plataformamensajeria.iop.beans.OrganismoPdpBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de aplicacion</p>.
 *
 * @author Selered
 */
public interface ServicioOrganismoPdp {
	
	/**
	 * Obtener organismos.
	 *
	 * @return organismos
	 * @throws BusinessException the business exception
	 */
	List<OrganismoPdpBean> getOrganismosPdp() throws BusinessException;
	
	/**
	 * Obtener organismos.
	 *
	 * @param rolUsuario the rol usuario
	 * @param idUsuario the id usuario
	 * @return organismos
	 * @throws BusinessException the business exception
	 */
	List<Integer> getOrganismosPdp(String rolUsuario, Integer idUsuario) throws BusinessException;
	
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
	PaginatedList<OrganismoPdpBean> getOrganismosPdp(int start, int size, String order, String columnSort,OrganismoPdpBean criterio) 
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
	Integer newOrganismoPdp(OrganismoPdpBean organismo, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Update organismo.
	 *
	 * @param organismo the organismo
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void updateOrganismoPdp(OrganismoPdpBean organismo, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Load organismo.
	 *
	 * @param organismo the organismo
	 * @return the organismo bean
	 * @throws BusinessException the business exception
	 */
	OrganismoPdpBean loadOrganismoPdp(OrganismoPdpBean organismoPdp)throws BusinessException;
	
	/**
	 * Delete organismo.
	 *
	 * @param organismoId the organismo id
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void deleteOrganismoPdp(Long organismoPdpId, String source, String accion, Long accionId)throws BusinessException;

	/**
	 * List autocomplete.
	 *
	 * @param term the term
	 * @return the list
	 */
	List<String> listAutocomplete(String term);


	/**
	 * Existe organimo.
	 *
	 * @param organismo the organismo
	 * @return the boolean
	 */
	Boolean existeOrganimo(OrganismoPdpBean organismo);

	List<String> getOrganismosHijos(String search);

}

