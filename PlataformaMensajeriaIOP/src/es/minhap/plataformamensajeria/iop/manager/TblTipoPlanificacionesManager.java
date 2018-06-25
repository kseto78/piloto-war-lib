package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.TblTipoPlanificaciones;

public interface TblTipoPlanificacionesManager {

	/**
	 * recupera la lista con los servicios que estan en planificacion
	 * @param tipoPlanificacionId
	 * @return 
	 */
	public TblTipoPlanificaciones getTipoPlanificacionById(Long tipoPlanificacionId);
	
	
	
}
