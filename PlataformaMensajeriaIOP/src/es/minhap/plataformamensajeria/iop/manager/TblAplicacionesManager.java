package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.query.TblAplicacionesQuery;

public interface TblAplicacionesManager {

	/**
	 * recupera la aplicacion según datos pasados
	 * 
	 * @param TblAplicacionesQuery
	 * @return
	 */
	public List<TblAplicaciones> getAplicaciones(TblAplicacionesQuery auditoria);
	
	/**
	 * recupera la aplicacion según datos pasados
	 * 
	 * @param TblAplicacionesQuery
	 * @return TblAplicaciones
	 */
	public TblAplicaciones getAplicacion(TblAplicacionesQuery query);

	
	/**
	 * comprueba si existe la aplicacion
	 * 
	 * @param String
	 * @param String
	 * @return Boolean
	 */
	public Boolean existeAplicacion(String usuario, String password);

	/**
	 * recupera la aplicacion según datos pasados
	 * 
	 * @param idAplicacion
	 * @return
	 */
	public TblAplicaciones getAplicacion(Long idAplicacion);

}
