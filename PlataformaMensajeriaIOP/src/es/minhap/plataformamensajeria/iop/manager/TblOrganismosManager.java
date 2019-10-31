package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.query.TblOrganismosQuery;

public interface TblOrganismosManager {

	/**
	 * Comprueba si existe el organismo existe
	 * 
	 * @return boolean
	 */
	boolean existeOrganismo(String codOrganismoPagadorSMS);
	
	/**
	 * Comprueba si existe el organismo existe
	 * 
	 * @param query
	 * @return 
	 */
	List<TblOrganismos> getOrganismosByQuery(TblOrganismosQuery query);

	/**
	 * Obtiene el organismo
	 * 
	 * @param organismoId
	 * @return TblOrganismos
	 */
	TblOrganismos getOrganismoById(Long organismoId);

	/**
	 * Actualiza el organismo
	 * 
	 * @param organismoTO
	 */
	void update(TblOrganismos organismoTO, String source, String accion, Long accionId);

	/**
	 * Inserta el organismo según datos pasados
	 * 
	 * @param organismo
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return Long
	 */
	Long insert(TblOrganismos organismo, String source, String accion, Long accionId);
	
}
