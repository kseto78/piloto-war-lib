package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.ViewGestionenviosDetallada;
import es.minhap.sim.query.ViewGestionenviosDetalladaQuery;

public interface ViewGestionEnviosDetalladaManager {

	/**
	 * Se obtiene el seguimiento del mensaje
	 * 
	 * @param ViewGestionEnviosDetalladaQuery
	 * @return List
	 */
	List<ViewGestionenviosDetallada> getEstadosFiltroMensaje(ViewGestionenviosDetalladaQuery query);

}
