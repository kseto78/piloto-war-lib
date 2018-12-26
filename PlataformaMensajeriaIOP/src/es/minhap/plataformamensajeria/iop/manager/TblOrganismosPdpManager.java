package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.model.TblOrganismosPdp;
import es.minhap.sim.query.TblOrganismosPdpQuery;
import es.minhap.sim.query.TblOrganismosQuery;

public interface TblOrganismosPdpManager {

	
	/**
	 * Comprueba si existe el organismoPdp existe
	 * 
	 * @param query
	 * @return 
	 */
	public List<TblOrganismosPdp> getOrganismosPdpByQuery(TblOrganismosPdpQuery query);

	/**
	 * Obtiene el organismo
	 * 
	 * @param organismoId
	 * @return TblOrganismos
	 */
	public TblOrganismosPdp getOrganismoPdpById(Long organismoId);

	/**
	 * Actualiza el organismo
	 * 
	 * @param organismoTO
	 */
	public void update(TblOrganismosPdp organismoTO, String source, String accion, Long accionId);

	/**
	 * Inserta el organismo según datos pasados
	 * 
	 * @param organismo
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return Long
	 */
	public Long insert(TblOrganismosPdp organismo, String source, String accion, Long accionId);

	
}
