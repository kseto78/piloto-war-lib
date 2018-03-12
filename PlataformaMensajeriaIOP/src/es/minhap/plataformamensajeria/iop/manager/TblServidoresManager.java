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
	public List<TblServidores> getServidoresPaginado(int start, int size, String order, String columnSort, String nombreServidor,
			int tipoServidor, boolean count);

	
	/**
	 * actualizamos el servidor
	 *
	 * @param servidor
	 * @param source
	 * @param accion
	 * @param accionId
	 */
	public void update(TblServidores servidor, String source, String accion, Long accionId);

	
	/**
	 * Insertamos un nuevo servidor
	 *
	 * @param servidor
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return
	 */
	public Long insert(TblServidores servidor, String source, String accion, Long accionId);
	
	
	/**
	 * Insertamos un nuevo servidor
	 *
	 * @param query
	 * @return
	 */
	public List<TblServidores> getServidoresByQuery(TblServidoresQuery query);

}
