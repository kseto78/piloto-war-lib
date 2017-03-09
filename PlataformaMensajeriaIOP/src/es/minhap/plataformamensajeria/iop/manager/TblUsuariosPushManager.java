package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblUsuariosPush;

public interface TblUsuariosPushManager {

	/**
	 * recupera el estado según datos pasados
	 * 
	 * @param identificadorUsuario
	 * @param servicioId
	 * @return List<Integer>
	 */
	public List<Long> getDispositivosUsuario(String identificadorUsuario, Integer servicioId);

	/**
	 * recupera el estado segun datos pasados
	 * 
	 * @param mensajeId
	* @return List<String>
	 */
	public List<String> getNombresUsuariosMensaje(Long mensajeId);
	
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
	public Integer altaUsuario(String nombreUsuario, String servicioId,
			String usuario, String password, String plataformaId,
			String tokenUsuario, String dispositivoId, String tokenSession, String uidDispositivo);

	/**
	 * elimina un usuario Push
	 * 
	 * @param token
	* @return boolean
	 */
	public boolean eliminarUsuario(String token);
	
	
	/**
	 * Comprueba si un dispositivo pertenece a una aplicacion
	 * 
	 * @param idServicio
	 * @param idDispositivo
	 * @param idPlataforma
	 * @param nombreUsuario
	* @return Boolean
	 */
	public Boolean comprobarExisteDispositivo(String idServicio, String idDispositivo, String idPlataforma, String nombreUsuario);
	
	
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
	public TblUsuariosPush getDatosUsuario(String idServicio, String idPlataforma, String idDispositivo, String nombreUsuario, boolean filtroEqualNombreUsuario);
	
	
	/**
	 * actualiza el usuario
	 * 
	 * @param usuario
	 * @return boolean
	 */
	public boolean updateUsuario(TblUsuariosPush usuario);
	

	/**
	 * Obtiene el usuario a partir de los datos
	 * 
	 * @param idServicio
	 * @param idDispositivo
	 * @param idPlataforma
	* @return TblUsuariosPush
	 */
	public TblUsuariosPush getDispositivoAplicacion(String idServicio, String idDispositivo, String idPlataforma);
	
	
	/**
	 * inserta el usuario
	 * 
	 * @param usuario
	 * @return Long
	 */
	public boolean insertUsuario(TblUsuariosPush usuario);

	/**
	 * Obtiene el listado con los id de usuario
	 * 
	 * @param idServicio
	 * @param idPlataforma
	 * @param idDispositivo
	 * @return List<Long>
	 */
	public List<Long> getDatosUsuario(String idServicio, String idPlataforma, String idDispositivo);

	/**
	 * Obtiene el listado con los id de usuario
	 * 
	 * @param nombreUsuario
	 * @param idDispositivo
	 * @return List<String>
	 */
	public List<String> getIdUsersFromDispositivo(String idDispositivo, String nombreUsuario);

	
	/**
	 * Obtiene usuario a partir del id
	 * 
	 * @param idUsuario
	 * @return TblUsuariosPush
	 */
	public TblUsuariosPush getUsuarioPush(long idUsuario);

	/**
	 * Comnprpueba si el id_dispositivo generado es repetido
	 * 
	 * @param codigo
	 * @return boolean
	 */
	public boolean comprobarDispositivoRepetido(String codigo);

	

	/**
	 * Comnprpueba si el uidDispositivo es repetido
	 * 
	 * @param uidDispositivo
	 * @return TblUsuariosPush
	 */
	public TblUsuariosPush existeUimDispositivo(String uidDispositivo, Long servicioId);

	/**
	 * Comnprpueba si el toten de sessión es válido y esta caducado
	 * 
	 * @param uidDispositivo
	 * @param tokenSession
	 * @param timeSession
	 * @return boolean
	 */
	public boolean comprobarTokenSession(String uidDispositivo, String tokenSession, Integer timeSession);


}
