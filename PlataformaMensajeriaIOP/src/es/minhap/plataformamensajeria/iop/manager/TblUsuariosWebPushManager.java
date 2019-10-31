package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblUsuariosWebPush;
import es.minhap.sim.query.TblUsuariosWebPushQuery;

public interface TblUsuariosWebPushManager {

	
	/**
	 * elimina un usuario Push
	 * 
	 * @param query
	* @return boolean
	 */
	boolean eliminarUsuario(TblUsuariosWebPushQuery query);
	
	

	
	/**
	 * inserta el usuario
	 * 
	 * @param usuario
	 * @return Long
	 */
	boolean insertUsuario(TblUsuariosWebPush usuario);



	/**
	 * obtiene el dato segun query
	 * 
	 * @param query
	 * @return TblUsuariosPush
	 */
	TblUsuariosWebPush getUsuarioPushByQuery(TblUsuariosWebPushQuery query);



	/**
	 * obtiene la lista segun query
	 * 
	 * @param query
	 * @return TblUsuariosPush
	 */
	List<TblUsuariosWebPush> getListUsuarioPushByQuery(TblUsuariosWebPushQuery query);

	/**
	 * Comprueba si el idUsuario generado está repetido
	 * 
	 * @param codigo
	 * @return 
	 */
	boolean comprobarDispositivoRepetido(String codigo);
	
	/**
	 * Obtiene lista de id de usuarios por servicio
	 * 
	 * @param servicio
	 * @return 
	 */
	List<Long> getUsuariosPorServicio(String servicio);



	/**
	 * Obtiene lista de id de usuarios por servicio
	 * 
	 * @param servicio
	 * @param identificadorUsuario
	 * @return 
	 */
	List<Long> getDispositivosUsuarioServicio(String identificadorUsuario, String servicio);



	/**
	 * elimina los usuarios que coincidan con query
	 * 
	 * @param query
	 */
	void establecerUsuarioEliminado(TblUsuariosWebPushQuery query);



	/**
	 * recupera el estado segun datos pasados
	 * 
	 * @param mensajeId
	* @return List<String>
	 */
	void updateUsuario(TblUsuariosWebPush uwp);



	/**
	 * recupera el nombre segun idmensaje
	 * 
	 * @param mensajeId
	* @return List<String>
	 */
	List<String> getUsuarioIdMensaje(long mensajeId);


	/**
	 * la lista con los servicios
	 * 
	 * @param start
	 * @param size
	 * @param order
	 * @param column
	 * @param upb
	 * @param b
	 * @return
	 */
	List<TblUsuariosWebPush> getUsuariosWebPushPaginado(int start, int size, String order, String column,
			es.minhap.plataformamensajeria.iop.beans.UsuariosWebPushBean upb, boolean b);

}
