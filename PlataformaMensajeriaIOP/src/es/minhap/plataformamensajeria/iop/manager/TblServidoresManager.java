package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.TblServidores;
import es.minhap.sim.query.TblServidoresQuery;

public interface TblServidoresManager {

	
	/**
	 * recupera el servidor por ID
	 * 
	 * @param servidorId
	 * @return TblServidores
	 */
	public TblServidores getServidorById(Long servidorId);
	
	/**
	 * recupera el servidor según datos pasados
	 * 
	 * @param TblServidoresQuery
	 * @return TblServidores
	 */
	public TblServidores getServidor(TblServidoresQuery query);
	
	/**
	 * recupera el servidor según datos pasados
	 * 
	 * @param TblServidoresQuery
	 * @return Integer
	 */
	public Integer countServidor(TblServidoresQuery query);

}
