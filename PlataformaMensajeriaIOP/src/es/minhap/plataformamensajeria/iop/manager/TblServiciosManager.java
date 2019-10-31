package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblServicios;
import es.minhap.sim.query.TblServiciosQuery;

public interface TblServiciosManager {

	/**
	 * recupera la aplicacion según datos pasados
	 * 
	 * @param TblAplicacionesQuery
	 * @return
	 */
	List<TblServicios> getServicios(TblServiciosQuery servicio);
	
	/**
	 * recupera el servicio por el id
	 * 
	 * @param servicioId
	 * @return TblServicios
	 */
	TblServicios getServicio(Long servicioId);
	
	/**
	 * retorna si es multi organismo o no
	 * 
	 * @param servicioId
	 * @return
	 */
	boolean isMultiOrganismo(Integer servicioId);

	/**
	 * Insertamos un nuevo servicio
	 *
	 * @param servicio
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return
	 */
	Long insert(TblServicios servicio, String source, String accion, Long accionId);

	/**
	 * Actualizamos el servicio
	 *
	 * @param servicio
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return
	 */
	void update(TblServicios servicio, String source, String accion, Long accionId);

	

}
