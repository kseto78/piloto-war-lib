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
	public boolean existeOrganismo(String codOrganismoPagadorSMS);
	
	/**
	 * Comprueba si existe el organismo existe
	 * 
	 * @param query
	 * @return 
	 */
	public List<TblOrganismos> getOrganismosByQuery(TblOrganismosQuery query);

	/**
	 * Obtiene el organismo
	 * 
	 * @param organismoId
	 * @return TblOrganismos
	 */
	public TblOrganismos getOrganismoById(Long organismoId);

	/**
	 * Actualiza el organismo
	 * 
	 * @param organismoTO
	 */
	public void update(TblOrganismos organismoTO, String source, String accion, Long accionId);

	/**
	 * Inserta el organismo según datos pasados
	 * 
	 * @param organismo
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return Long
	 */
	public Long insert(TblOrganismos organismo, String source, String accion, Long accionId);
	
}
