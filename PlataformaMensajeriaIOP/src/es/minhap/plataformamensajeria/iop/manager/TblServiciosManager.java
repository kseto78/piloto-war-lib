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
	public List<TblServicios> getServicios(TblServiciosQuery servicio);
	
	/**
	 * recupera el servicio por el id
	 * 
	 * @param servicioId
	 * @return TblServicios
	 */
	public TblServicios getServicio(Long servicioId);
	
	/**
	 * retorna si es multi organismo o no
	 * 
	 * @param servicioId
	 * @return
	 */
	public boolean isMultiOrganismo(Integer servicioId);

}
