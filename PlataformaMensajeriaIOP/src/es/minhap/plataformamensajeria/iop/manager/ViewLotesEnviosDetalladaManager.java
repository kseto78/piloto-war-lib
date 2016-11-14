package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.ViewLotesEnviosDetallada;
import es.minhap.sim.query.ViewLotesEnviosDetalladaQuery;

public interface ViewLotesEnviosDetalladaManager {

	/**
	 * Se comprueba si EXISTE LOTE PARA EL USUARIO/PASSWORD
	 * 
	 * @param ViewLotesEnviosDetalladaQuery
	 * @return Integer
	 */
	public Integer countViewLoteDetallado(ViewLotesEnviosDetalladaQuery query);
	
	/**
	 * Obtiene ViewLotesEnviosDetallada
	 * 
	 * @param ViewLotesEnviosDetalladaQuery
	 * @return ViewLotesEnviosDetallada
	 */
	public ViewLotesEnviosDetallada getViewLoteDetallado(ViewLotesEnviosDetalladaQuery query);

}
