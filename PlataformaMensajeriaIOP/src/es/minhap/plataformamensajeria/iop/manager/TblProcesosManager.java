package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblPdpDiputaciones;
import es.minhap.sim.model.TblProcesos;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.query.TblProcesosQuery;
import es.minhap.sim.query.TblServiciosQuery;

public interface TblProcesosManager {

	/**
	 * recupera la aplicacion según datos pasados
	 * 
	 * @param TblProcesosQuery
	 * @return
	 */
	List<TblProcesos> getProcesosByQuery(TblProcesosQuery procesos);
	
	/**
	 * Obtiene el proceso
	 * 
	 * @param procesoId
	 * @return TblProcesos
	 */
	TblProcesos getProcesoById(Long procesosId);
	
		/**
	 * Actualizamos el procesos
	 *
	 * @param servicio
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return
	 */
	void update(TblProcesos proceso, String source, String accion, Long accionId);

	

}
