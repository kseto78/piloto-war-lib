package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblServidores;
import es.minhap.sim.query.TblServidoresQuery;

public interface TblServidoresManager {

	
	/**
	 * recupera el servidor por ID
	 * 
	 * @param servidorId
	 * @return TblServidores
	 */
	TblServidores getServidorById(Long servidorId);
	
	/**
	 * recupera el servidor según datos pasados
	 * 
	 * @param TblServidoresQuery
	 * @return TblServidores
	 */
	TblServidores getServidor(TblServidoresQuery query);
	
	/**
	 * recupera el servidor según datos pasados
	 * 
	 * @param TblServidoresQuery
	 * @return Integer
	 */
	Integer countServidor(TblServidoresQuery query);

	
	/**
	 * recupera el servidor según datos pasados
	 * 
	 * @param start
	 * @param size
	 * @param order
	 * @param columnSort
	 * @param nombreServidor
	 * @param tipoServidor
	 * @param count
	 * @return List<TblServidores>
	 */
	List<TblServidores> getServidoresPaginado(int start, int size, String order, String columnSort, String nombreServidor,
			int tipoServidor, boolean count);

	
	/**
	 * actualizamos el servidor
	 *
	 * @param servidor
	 * @param source
	 * @param accion
	 * @param accionId
	 */
	void update(TblServidores servidor, String source, String accion, Long accionId);

	
	/**
	 * Insertamos un nuevo servidor
	 *
	 * @param servidor
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return
	 */
	Long insert(TblServidores servidor, String source, String accion, Long accionId);
	
	
	/**
	 * Insertamos un nuevo servidor
	 *
	 * @param query
	 * @return
	 */
	List<TblServidores> getServidoresByQuery(TblServidoresQuery query);

}
