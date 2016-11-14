package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.TblEstados;

public interface TblEstadosManager {

	/**
	 * recupera el estado segun datos pasados
	 * 
	 * @param TblEstadosQuery
	 * @return TblEstados
	 */
	public TblEstados getEstadoByName(String nombre);
	
	
	/**
	 * recupera el estado segun datos pasados
	 * 
	 * @param TblEstadosQuery
	 * @return TblEstados
	 */
	public TblEstados getEstadoById(Long idEstado);
	
	/**
	 * recupera el id del estado pasado en properties
	 * @param ps
	 * @return Long
	 */
//	public Long getEstado(PropertiesServices ps);
}
