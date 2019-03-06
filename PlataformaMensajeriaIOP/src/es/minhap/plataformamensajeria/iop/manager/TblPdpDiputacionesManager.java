package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.model.TblPdpDiputaciones;
import es.minhap.sim.query.TblPdpDiputacionesQuery;
import es.minhap.sim.query.TblOrganismosQuery;

public interface TblPdpDiputacionesManager {

	
	/**
	 * Comprueba si existe el pdpDiputaciones existe
	 * 
	 * @param query
	 * @return 
	 */
	public List<TblPdpDiputaciones> getPdpDiputacionesByQuery(TblPdpDiputacionesQuery query);

	/**
	 * Obtiene el organismo
	 * 
	 * @param organismoId
	 * @return TblOrganismos
	 */
	public TblPdpDiputaciones getPdpDiputacionesById(Long organismoId);

	/**
	 * Actualiza el organismo
	 * 
	 * @param organismoTO
	 */
	public void update(TblPdpDiputaciones organismoTO, String source, String accion, Long accionId);

	/**
	 * Inserta el organismo según datos pasados
	 * 
	 * @param organismo
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return Long
	 */
	public Long insert(TblPdpDiputaciones organismo, String source, String accion, Long accionId);

	
}
