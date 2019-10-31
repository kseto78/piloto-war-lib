package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.TblProcesoCons;

public interface TblProcesoConsManager {

	/**
	 * Inserta una entrada 
	 * 
	 * @param procesoCons
	* @return id
	 */
	Long insertar(TblProcesoCons procesoCons);
	
	

}
