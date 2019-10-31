package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblUsuariosPush;
import es.minhap.sim.query.TblUsuariosPushQuery;

public interface TblUsuariosPushManager {

	/**
	 * recupera el estado según datos pasados
	 * 
	 * @param identificadorUsuario
	 * @param servicioId
	 * @return List<Integer>
	 */
	List<Long> getDispositivosUsuario(String identificadorUsuario, Integer servicioId);

	/**
	 * recupera el estado segun datos pasados
	 * 
	 * @param mensajeId
	* @return List<String>
	 */
	List<String> getNombresUsuariosMensaje(Long mensajeId);
	
	/**
	 * Da de alta un usuario Push
	 * 
	 * @param nombreUsuario
	 * @param servicioId
	 * @param usuario
	 * @param password
	 * @param plataformaId
	 * @param tokenUsuario
	 * @param dispositivoId
	 * @param uidDispositivo 
	 * @param tokenSession 
	 * @return Integer
	 */
	Integer altaUsuario(String nombreUsuario, String servicioId,
			String usuario, String password, String plataformaId,
			String tokenUsuario, String dispositivoId, String tokenSession, String uidDispositivo);

	/**
	 * elimina un usuario Push
	 * 
	 * @param token
	* @return boolean
	 */
	boolean eliminarUsuario(String token);
	
	
	/**
	 * Comprueba si un dispositivo pertenece a una aplicacion
	 * 
	 * @param idServicio
	 * @param idDispositivo
	 * @param idPlataforma
	 * @param nombreUsuario
	* @return Boolean
	 */
	Boolean comprobarExisteDispositivo(String idServicio, String idDispositivo, String idPlataforma, String nombreUsuario);
	
	
	/**
	 * Obtiene los datos del usuario
	 * 
	 * @param idServicio
	 * @param idPlataforma
	 * @param idDispositivo
	 * @param nombreUsuario
	 * @param filtroEqualNombreUsuario
	* @return TblUsuariosPush
	 */
	TblUsuariosPush getDatosUsuario(String idServicio, String idPlataforma, String idDispositivo, String nombreUsuario, boolean filtroEqualNombreUsuario);
	
	
	/**
	 * actualiza el usuario
	 * 
	 * @param usuario
	 * @return boolean
	 */
	boolean updateUsuario(TblUsuariosPush usuario);
	

	/**
	 * Obtiene el usuario a partir de los datos
	 * 
	 * @param idServicio
	 * @param idDispositivo
	 * @param idPlataforma
	* @return TblUsuariosPush
	 */
	TblUsuariosPush getDispositivoAplicacion(String idServicio, String idDispositivo, String idPlataforma);
	
	
	/**
	 * inserta el usuario
	 * 
	 * @param usuario
	 * @return Long
	 */
	boolean insertUsuario(TblUsuariosPush usuario);

	/**
	 * Obtiene el listado con los id de usuario
	 * 
	 * @param idServicio
	 * @param idPlataforma
	 * @param idDispositivo
	 * @return List<Long>
	 */
	List<Long> getDatosUsuario(String idServicio, String idPlataforma, String idDispositivo);

	/**
	 * Obtiene el listado con los id de usuario
	 * 
	 * @param nombreUsuario
	 * @param idDispositivo
	 * @return List<String>
	 */
	List<String> getIdUsersFromDispositivo(String idDispositivo, String nombreUsuario);

	
	/**
	 * Obtiene usuario a partir del id
	 * 
	 * @param idUsuario
	 * @return TblUsuariosPush
	 */
	TblUsuariosPush getUsuarioPush(long idUsuario);

	/**
	 * Comnprpueba si el id_dispositivo generado es repetido
	 * 
	 * @param codigo
	 * @return boolean
	 */
	boolean comprobarDispositivoRepetido(String codigo);

	

	/**
	 * Comnprpueba si el uidDispositivo es repetido
	 * 
	 * @param uidDispositivo
	 * @return TblUsuariosPush
	 */
	TblUsuariosPush existeUimDispositivo(String uidDispositivo, Long servicioId);

	/**
	 * Comnprpueba si el toten de sessión es válido y esta caducado
	 * 
	 * @param uidDispositivo
	 * @param tokenSession
	 * @param timeSession
	 * @return boolean
	 */
	boolean comprobarTokenSession(String uidDispositivo, String tokenSession, Integer timeSession);

	/**
	 * Comnprpueba si el toten de sessión es válido y esta caducado
	 * 
	 * @param query
	 * @return TblUsuariosPush
	 */
	TblUsuariosPush getUsuarioPushByQuery(TblUsuariosPushQuery query);



}
