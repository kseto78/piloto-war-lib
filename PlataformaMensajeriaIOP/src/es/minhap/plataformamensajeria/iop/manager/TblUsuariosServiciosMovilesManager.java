package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;


public interface TblUsuariosServiciosMovilesManager {

	/**
	 * recupera el servidor segun datos pasados
	 * 
	 * @param users
	 * @param idServicioMovil
	 * @return boolean
	 */
	public Boolean comprobarUsuarioServicio(List<String> users, String idServicioMovil);

	/**
	 * Registra el usuario en el servicio push
	 * 
	 * @param idUsuario
	 * @param idServicioMovil
	 * @param usuario
	 * @return boolean
	 */
	public Boolean registraUsuarioServicio(String idUsuario, String idServicioMovil, String usuario);

	
	/**
	 * actualiza el usuario en el servicio push
	 * 
	 * @param idUsuario
	 * @param idServicioMovil
	 * @param usuario
	 * @param activo
	 * @return boolean
	 */
	public Boolean updateUsuarioServicio(String idUsuario, String idServicioMovil, String usuario, boolean activo);

	
	/**
	 * obtiene el estado de la suscripci�n del usuario
	 * 
	 * @param idUsuario
	 * @param idServicioMovil
	 * @return String
	 */
	public String checkSuscriptionStatus(String idUsuario, String idServicioMovil);
	
	/**
	 * Comprueba si es un usuario servicio válido
	 * 
	 * @param usuario
	 * @param idServicioMovil
	 * @return String
	 */
	public boolean comprobarUsuarioServicioValido(Integer usuario, String idServicioMovil);
}
