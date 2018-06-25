package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.query.ViewMensajeAdjuntosDetalladaQuery;

public interface ViewMensajeAdjuntosDetalladaManager {

	/**
	 * Se comprueba si hay algún mensaje con ese nombre de usuario
	 * 
	 * @param ViewMensajeAdjuntosDetalladaQuery
	 * @return Integer
	 */
	public Integer countViewMensajeAdjuntosDetallada(ViewMensajeAdjuntosDetalladaQuery query);

}
