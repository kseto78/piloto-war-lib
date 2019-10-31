package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.sim.model.TblDestinatariosMensajes;
import es.minhap.sim.query.TblDestinatariosMensajesQuery;


public interface TblDestinatariosMensajesManager {

	/**
	 * inserta en tabla Destinatarios Mensajes
	 * 
	 * @param mensajeId @param destinatario
	 * @param idExterno @param usuario
	 * @return Long
	 */
	Long insertarDestinatarioMensaje(String mensajeId,String destinatario, String idExterno, String usuario);

	
	/**
	 * inserta en tabla Destinatarios Mensajes
	 * 
	 * @param mensajeId @param destinatarios
	 * @param idExterno @param usuario
	 * @param estadoId
	 * @return 
	 */
	List<Long> insertarDestinatarioMensajeEmail(String mensajeId, List<Long> destinatarios, String idExterno, String usuario,Long estadoId);
	
	/**
	 * recupera los DetinatariosMensajes
	 * 
	 * @param mensajeId @param destinatarios
	 * @param idExterno @param usuario
	 */
	List<TblDestinatariosMensajes> getDestinatarioMensajes(Long mensajeId);
	
	
	/**
	 * recupera un DetinatariosMensajes
	 * 
	 * @param destinatariomensajeId 
	 * @return
	 */
	TblDestinatariosMensajes getDestinatarioMensaje(Long destinatarioMensajeId);
	
	/**
	 * Comprueba si existe el id exexteno
	 * 
	 * @param idExterno
	 * @param sended
	 * @return
	 */
	Integer checkIdExternoExists(String idExterno);
	
	/**
	 * Inserta en la tabla Destinatarios mensajes
	 * 
	 * @param destinatariosMensajes
	 */
	Long insertar(TblDestinatariosMensajes destinatariosMensajes);
	
	/**
	 * 
	 * @param mensajeId
	 * @return
	 */
	Integer contarEstadosDestinatariosMensajes(Long mensajeId);
	
	/**
	 * Este metodo recupera el codigo externo a partir del id del mensaje
	 * 
	 * @param mensajeId
	 * @return
	 */
	String getIdExterno(Long mensajeId);
	
	/**
	 * Este metodo recupera UIN del destinatarioMensaje
	 * 
	 * @param destinatariosMensaje
	 * @return
	 */
	String getUim(Long destinatariosMensaje);
	
	
	/**
	 * Este metodo actualiza la entidad a nivel de BBDD
	 * 
	 * @param destinatarioMensaje
	 */
	void update(TblDestinatariosMensajes destinatarioMensaje);
	
	
	/**
	 * 
	 * @param idExterno
	 * @param destinatario
	 * @param usuario
	 * @param ps
	 * @param mensajeId
	 * @return
	 */
	TblDestinatariosMensajes getDestinatariosMensajes(String idExterno, String destinatario, String usuario,
			PropertiesServices ps, Long mensajeId);


	/**
	 * Este del destinatario a partir del uim
	 * 
	 * @param uim
	 * @return 
	 */
	TblDestinatariosMensajes getDestinatarioMensajeByUim(String uim);


	/**
	 * 
	 * @param mensajeId
	 * @param usuarios
	 * @param estadoInicial
	 * @return
	 */
	List<TblDestinatariosMensajes> getDestinatarioMensajesUsuarios(Long mensajeId, List<String> usuarios,
			String estadoInicial);


	/**
	 * aumenta el número de intentos de encolar
	 * 
	 * @param destinatarioMensajeId
	 * @return Integer
	 */
	Integer updateNumIntentosEncolar(Long destinatarioMensajeId);


	/**
	 * recupera los DetinatariosMensajes
	 * 
	 * @param mensajeId 
	 * @param estadoPendiente
	 * @return
	 */
	List<TblDestinatariosMensajes> getDestinatarioMensajesPendientes(Long mensajeid, String estadoPendiente);

	
	/**
	 * recupera los DetinatariosMensajes
	 * 
	 * @param idExterno 
	 * @return TblMensajes
	 */
	List<Long> getIdMensajeByIdExterno(String idExterno);

	/**
	 * Elimina los DetinatariosMensajes
	 * 
	 * @param destinatarioMensajeId 
	 */
	void delete(Long destinatarioMensajeId);

	/**
	 * recupera los DetinatariosMensajes by query
	 * 
	 * @param query 
	 * @return 
	 */
	List<TblDestinatariosMensajes> getDestinatarioMensajesByQuery(TblDestinatariosMensajesQuery query);


	List<TblDestinatariosMensajes> getDestinatarioMensajesNoEnviado(
			Long idMensaje);

}
