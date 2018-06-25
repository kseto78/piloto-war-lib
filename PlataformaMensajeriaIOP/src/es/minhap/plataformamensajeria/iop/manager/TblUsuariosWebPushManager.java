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
	public boolean eliminarUsuario(TblUsuariosWebPushQuery query);
	
	

	
	/**
	 * inserta el usuario
	 * 
	 * @param usuario
	 * @return Long
	 */
	public boolean insertUsuario(TblUsuariosWebPush usuario);



	/**
	 * obtiene el dato segun query
	 * 
	 * @param query
	 * @return TblUsuariosPush
	 */
	public TblUsuariosWebPush getUsuarioPushByQuery(TblUsuariosWebPushQuery query);



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
	public boolean comprobarDispositivoRepetido(String codigo);
	
	/**
	 * Obtiene lista de id de usuarios por servicio
	 * 
	 * @param servicio
	 * @return 
	 */
	public List<Long> getUsuariosPorServicio(String servicio);



	/**
	 * Obtiene lista de id de usuarios por servicio
	 * 
	 * @param servicio
	 * @param identificadorUsuario
	 * @return 
	 */
	public List<Long> getDispositivosUsuarioServicio(String identificadorUsuario, String servicio);



	/**
	 * elimina los usuarios que coincidan con query
	 * 
	 * @param query
	 */
	public void establecerUsuarioEliminado(TblUsuariosWebPushQuery query);



	/**
	 * recupera el estado segun datos pasados
	 * 
	 * @param mensajeId
	* @return List<String>
	 */
	public void updateUsuario(TblUsuariosWebPush uwp);



	/**
	 * recupera el nombre segun idmensaje
	 * 
	 * @param mensajeId
	* @return List<String>
	 */
	public List<String> getUsuarioIdMensaje(long mensajeId);


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
	public List<TblUsuariosWebPush> getUsuariosWebPushPaginado(int start, int size, String order, String column,
			es.minhap.plataformamensajeria.iop.beans.UsuariosWebPushBean upb, boolean b);

}
