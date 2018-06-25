package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.ViewLotesEnviosDetHist;
import es.minhap.sim.query.ViewLotesEnviosDetHistQuery;

public interface ViewLotesEnviosDetHistManager {

	
	/**
	 * Obtiene ViewLotesEnviosDetalladaHist
	 * 
	 * @param query
	 * @return ViewLotesEnviosDetHist
	 */
	public ViewLotesEnviosDetHist getViewLoteDetallado(ViewLotesEnviosDetHistQuery query);

}
