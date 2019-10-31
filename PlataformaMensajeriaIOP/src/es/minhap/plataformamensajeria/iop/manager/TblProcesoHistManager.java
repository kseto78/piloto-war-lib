package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.TblProcesoHist;

public interface TblProcesoHistManager {

	/**
	 * Inserta una entrada 
	 * 
	 * @param procesoHist
	* @return id
	 */
	Long insertar(TblProcesoHist procesoHist);
	
	

}
