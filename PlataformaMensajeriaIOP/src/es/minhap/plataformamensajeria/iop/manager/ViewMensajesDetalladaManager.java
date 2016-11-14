package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.query.ViewMensajesDetalladaQuery;

public interface ViewMensajesDetalladaManager {

	/**
	 * Se comprueba si hay algún mensaje con ese nombre de usuario
	 * 
	 * @param ViewMensajesDetalladaQuery
	 * @return Integer
	 */
	public Integer countViewMensajeDetallada(ViewMensajesDetalladaQuery query);

}
