package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblMonitorDIR3;
import es.minhap.sim.query.TblMonitorDIR3Query;



public interface TblMonitorDIR3Manager {
		
	/**
	 * Guarda un elemento TblMonitorDIR3. Devuelve su identificador en caso de exito o null si hay error.
	 * @param monitorDIR3
	 * @return
	 */
	Long guardarDir3MonitorWS(TblMonitorDIR3 monitorDIR3);
	
	/**
	 * Obtiene el ultimo elemento insertado tipo Dir3MonitorWs con el estado pasado por parametro.
	 * Devuelve TblMonitorDIR3 en caso de exito o null si hay error.
	 * @param estadoProceso
	 * @return
	 */
	TblMonitorDIR3 obtenerUltimoDir3MonitorWS(String estadoProceso);
	
	/**
	 * Buscar una lista de ejecuciones del monitor dada una query. Devuelve una lista de ejecuciones.
	 * @param query
	 */
	List<TblMonitorDIR3> buscarListaMonitorDir3(TblMonitorDIR3Query query);
}
