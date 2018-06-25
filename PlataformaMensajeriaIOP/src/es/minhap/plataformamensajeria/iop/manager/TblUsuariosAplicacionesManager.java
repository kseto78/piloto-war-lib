package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblUsuariosAplicaciones;
import es.minhap.sim.query.TblUsuariosAplicacionesQuery;

public interface TblUsuariosAplicacionesManager {

	/**
	 * Obtiene los usuarios aplicaciones
	 * 
	 * @param query
	 * @return
	 */
	public List<TblUsuariosAplicaciones> getUsuariosAplicacionesByQuery(TblUsuariosAplicacionesQuery query);

	/**
	 * Obtiene el usuarios aplicaciones by Id
	 * 
	 * @param usuarioAplicacionId
	 * @return TblUsuariosAplicaciones
	 */
	public TblUsuariosAplicaciones getUsuariosAplicacionesById(Long usuarioAplicacionId);

	/**
	 * inserta un nuevo TblUsuariosAplicaciones
	 * 
	 * @param usuarioAplicacionTO
	 * @param source
	 * @param accion
	 * @param accionId
	 * @param descripcion
	 * @return id
	 */
	public Long insert(TblUsuariosAplicaciones usuarioAplicacionTO, String source, String accion, Long accionId,
			String descripcion);

	/**
	 * elimina un nuevo TblUsuariosAplicaciones
	 * 
	 * @param usuarioAplicacionTO
	 * @param source
	 * @param accion
	 * @param accionId
	 * @param descripcion
	 * @return id
	 */
	public void delete(TblUsuariosAplicaciones usuarioAplicacionTO, String source, String accion, Long accionId,
			String descripcion);

	
}
