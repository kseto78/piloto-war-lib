package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

public interface TblPlanificacionesManager {

	/**
	 * recupera la lista con los servicios que estan en planificacion
	 *
	 * @return List<Long>
	 */
	public List<Long> getServiciosPlanificacion();
	
	

}
