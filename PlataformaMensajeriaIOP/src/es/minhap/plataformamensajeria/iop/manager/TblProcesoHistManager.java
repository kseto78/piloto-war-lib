package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.TblProcesoHist;

public interface TblProcesoHistManager {

	/**
	 * Inserta una entrada 
	 * 
	 * @param procesoHist
	* @return id
	 */
	public Long insertar(TblProcesoHist procesoHist);
	
	

}
