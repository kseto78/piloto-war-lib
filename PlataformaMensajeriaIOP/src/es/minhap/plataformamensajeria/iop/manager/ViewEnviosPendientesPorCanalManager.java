package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.ViewEnviosPendientesPorCanal;

public interface ViewEnviosPendientesPorCanalManager {

	/**
	 * Se obtienen todos los datos
	 * 
	 * @return List
	 */
	public List<ViewEnviosPendientesPorCanal> getAll();

	
}
