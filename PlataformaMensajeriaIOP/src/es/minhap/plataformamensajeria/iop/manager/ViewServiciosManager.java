package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.ViewServicios;
import es.minhap.sim.query.ViewServiciosQuery;

public interface ViewServiciosManager {

	/**
	 * Se obtiene la aplicacion de la vista
	 * 
	 * @param ViewServiciosQuery
	 * @return ViewServicios
	 */
	public ViewServicios getAplicacionId(ViewServiciosQuery query);
	
	

}
