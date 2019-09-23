package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.model.TblProcesos;
import es.minhap.sim.model.TblProcesosManuales;
import es.minhap.sim.query.TblProcesosManualesQuery;

public interface TblProcesosManualesManager {

	/**
	 * recupera la aplicacion según datos pasados
	 * 
	 * @param TblProcesosManualesQuery
	 * @return
	 */
	public List<TblProcesosManuales> getProcesosManualesByQuery(TblProcesosManualesQuery procesos);
	
	/**
	 * Obtiene el proceso manual
	 * 
	 * @param procesoManualId
	 * @return TblProcesosManuales
	 */
	public TblProcesosManuales getProcesoManualById(Long procesoManualId);
	
		/**
	 * Actualizamos el proceso manual
	 *
	 * @param servicio
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return
	 */
	public void update(TblProcesosManuales procesoManual, String source, String accion, Long accionId);

	public Long insert(TblProcesosManuales procesoManual, String source, String accion, Long accionId);

}
