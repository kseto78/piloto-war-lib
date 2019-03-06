package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.sim.model.ViewServicios;
import es.minhap.sim.model.ViewUsuariosAplicaciones;

/**
 * 
 * @author everis
 * 
 */
public interface QueryExecutorViewServicios {

	List<ViewServicios> getCanalesDistintos(int aplicacionId);

	/**
	 * Obtiene el los diferentes canales de una aplicacion
	 *  
	 * @param aplicacionId
	 * 
	 * @return
	 */
	

}
