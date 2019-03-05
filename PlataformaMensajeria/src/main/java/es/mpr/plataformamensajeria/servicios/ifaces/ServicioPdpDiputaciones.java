package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.plataformamensajeria.iop.beans.PdpDiputacionesBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de aplicacion</p>.
 *
 * @author Selered
 */
public interface ServicioPdpDiputaciones {
	
	/**
	 * Obtener organismos.
	 *
	 * @return organismos
	 * @throws BusinessException the business exception
	 */
	List<PdpDiputacionesBean> getPdpDiputaciones() throws BusinessException;
	
	/**
	 * Obtener organismos.
	 *
	 * @param rolUsuario the rol usuario
	 * @param idUsuario the id usuario
	 * @return organismos
	 * @throws BusinessException the business exception
	 */
	List<Integer> getPdpDiputaciones(String rolUsuario, Integer idUsuario) throws BusinessException;
	
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
	PaginatedList<PdpDiputacionesBean> getOrganismosPdpDiputaciones(int start, int size, String order, String columnSort,PdpDiputacionesBean criterio) 
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
	Integer newOrganismoPdpDiputacion(PdpDiputacionesBean organismo, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Update organismo.
	 *
	 * @param organismo the organismo
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void updateOrganismoPdp(PdpDiputacionesBean organismo, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Load organismo.
	 *
	 * @param organismo the organismo
	 * @return the organismo bean
	 * @throws BusinessException the business exception
	 */
	PdpDiputacionesBean loadOrganismoPdp(PdpDiputacionesBean organismoPdp)throws BusinessException;
	
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
	Boolean existeOrganimo(PdpDiputacionesBean organismo);

	List<String> getOrganismosHijos(String search);

}

