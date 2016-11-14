package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblServidoresServicios;
import es.minhap.sim.query.TblServidoresServiciosQuery;

public interface TblServidoresServiciosManager {

	
	/**
	 * recupera el el valos del contador según datos pasados
	 * 
	 * @param TblServidoresServiciosQuery
	 * @return Integer
	 */
	public Integer countServidoresServicios(TblServidoresServiciosQuery query);

	/**
	 * 
	 * @param servicioid
	 * @return
	 */
	public TblServidoresServicios getServidoresServicio(TblServicios servicio);

}
