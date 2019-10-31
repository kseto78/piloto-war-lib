package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblUsuarios;
import es.minhap.sim.query.TblUsuariosQuery;


public interface TblUsuariosManager {

	/**
	 * obtiene el rol según el login
	 * 
	 * @param login
	 * @return 
	 */
	Integer getRolByUsername(String login);
	
	
	/**
	 * obtiene el idUsuario según el login
	 * 
	 * @param login
	 * @return 
	 */
	Long getUsuarioByUsername(String login);

	/**
	 * obtiene el idUsuario activo según el login
	 * 
	 * @param login
	 * @return 
	 */
	Long getUsuarioByUsernameActivo(String login);

	
	/**
	 * obtiene el idUsuario según el id
	 * 
	 * @param usuarioid
	 * @return 
	 */
	TblUsuarios getById(Long usuarioid);


	/**
	 * obtiene el Usuario según el query
	 * 
	 * @param query
	 * @return TblUsuarios
	 */
	List<TblUsuarios> getUsuariosAplicacionesByQuery(TblUsuariosQuery query);


	/**
	 * Inserta el organismo según datos pasados
	 * 
	 * @param usuarioTO
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return Long
	 */
	Long insert(TblUsuarios usuarioTO, String source, String accion, Long accionId);


	/**
	 * Actualiza el usuario según datos pasados
	 * 
	 * @param usuarioTO
	 * @param source
	 * @param accion
	 * @param accionId
	 */
	void update(TblUsuarios usuario, String source, String accion, Long accionId);

	/**
	 * Elimina el usuario según datos pasados
	 * 
	 * @param usuario
	 * @param source
	 * @param accion
	 * @param accionId
	 */
	void delete(Long usuarioId, String source, String accion, Long accionId);


	


	
}
