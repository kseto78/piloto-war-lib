package es.minhap.plataformamensajeria.iop.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean;
import es.minhap.plataformamensajeria.iop.beans.UsuariosServiciosMovilesBean;

public interface QueryExecutorUsuariosPush {
	
	/**
	 * Obtiene la lista de dispositivos de un usuario por su servicio
	 * @param nombre usuario
	 * @param servicio
	 * @return list<Long>
	 */
	List<Long> listaUsuariosDispositivosPush(String identificadorUsuario, Integer servicioId);
	
	/**
	 * Obtiene el siguiente numero en la secuencia de dispositivo
	 * @return Integer
	 */
	Integer getNextDispositivo();

	
	/**
	 * Obtiene la lista de dispositivos de un usuario
	 * @param idDispositivo
	 * @param nombreUsuario
	 * @return list<String>
	 */
	List<String> getIdUsersFromDispositivo(String idDispositivo, String nombreUsuario);

	/**
	 * Obtiene la lista de usuarios
	 * @param idDispositivo
	 * @param idServicio
	 * @return list<BigDecimal>
	 */
	List<BigDecimal> getUsuarioConsultaServiciosDisponibles(String idDispositivo, String idServicioMovil);

	/**
	 * Recupera la plataforma a partird el mensajeId
	 * 
	 * @param mensajeId
	 * @return
	 */
	Integer getPlataformaUsuario(Long mensajeId);

	
	/**
	 * Recupera los usuarios por servicios según el servicioId
	 * 
	 * @param servicioID
	 * @return
	 */
	List<UsuariosServiciosMovilesBean> getUsuarioPorServicio(Integer servicioID);

	/**
	 * Recupera los usuarios por servicios según el servicioId
	 * 
	 * @param identificadorUsuario
	 * @param servicioID
	 * @param idServicioMovil
	 * @return
	 */
	ArrayList<Integer> getDispositivosUsuarioServicioMovil(String identificadorUsuario, Integer servicioID,
			Integer idServicioMovil);

	
	List<UsuariosPushBean> listaUsuariosPushbyServicioMovilId(Long servicioMovilId);


}
