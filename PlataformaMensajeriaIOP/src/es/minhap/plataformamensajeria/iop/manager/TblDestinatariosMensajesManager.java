package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.sim.model.TblDestinatariosMensajes;


public interface TblDestinatariosMensajesManager {

	/**
	 * inserta en tabla Destinatarios Mensajes
	 * 
	 * @param mensajeId @param destinatario
	 * @param idExterno @param usuario
	 * @return Long
	 */
	public Long insertarDestinatarioMensaje(String mensajeId,String destinatario, String idExterno, String usuario);

	
	/**
	 * inserta en tabla Destinatarios Mensajes
	 * 
	 * @param mensajeId @param destinatarios
	 * @param idExterno @param usuario
	 * @param estadoId
	 * @return 
	 */
	public List<Long> insertarDestinatarioMensajeEmail(String mensajeId, List<Long> destinatarios, String idExterno, String usuario,Long estadoId);
	
	/**
	 * recupera los DetinatariosMensajes
	 * 
	 * @param mensajeId @param destinatarios
	 * @param idExterno @param usuario
	 */
	public List<TblDestinatariosMensajes> getDestinatarioMensajes(Long mensajeId);
	
	
	/**
	 * recupera un DetinatariosMensajes
	 * 
	 * @param destinatariomensajeId 
	 */
	public TblDestinatariosMensajes getDestinatarioMensaje(Long destinatarioMensajeId);
	
	/**
	 * Comprueba si existe el id exexteno
	 * 
	 * @param idExterno
	 * @param sended
	 * @return
	 */
	public Integer checkIdExternoExists(String idExterno);
	
	/**
	 * Inserta en la tabla Destinatarios mensajes
	 * 
	 * @param destinatariosMensajes
	 */
	public Long insertar(TblDestinatariosMensajes destinatariosMensajes);
	
	/**
	 * 
	 * @param mensajeId
	 * @return
	 */
	public Integer contarEstadosDestinatariosMensajes(Long mensajeId);
	
	/**
	 * Este metodo recupera el codigo externo a partir del id del mensaje
	 * 
	 * @param mensajeId
	 * @return
	 */
	public String getIdExterno(Long mensajeId);
	
	/**
	 * Este metodo recupera UIN del destinatarioMensaje
	 * 
	 * @param destinatariosMensaje
	 * @return
	 */
	public String getUim(Long destinatariosMensaje);
	
	
	/**
	 * Este metodo actualiza la entidad a nivel de BBDD
	 * 
	 * @param destinatarioMensaje
	 */
	public void update(TblDestinatariosMensajes destinatarioMensaje);
	
	
	/**
	 * 
	 * @param idExterno
	 * @param destinatario
	 * @param usuario
	 * @param ps
	 * @param mensajeId
	 * @return
	 */
	public TblDestinatariosMensajes getDestinatariosMensajes(String idExterno, String destinatario, String usuario,
			PropertiesServices ps, Long mensajeId);


	/**
	 * Este del destinatario a partir del uim
	 * 
	 * @param uim
	 * @return 
	 */
	public TblDestinatariosMensajes getDestinatarioMensajeByUim(String uim);


	/**
	 * 
	 * @param mensajeId
	 * @param usuarios
	 * @param estadoInicial
	 * @return
	 */
	public List<TblDestinatariosMensajes> getDestinatarioMensajesUsuarios(Long mensajeId, List<String> usuarios,
			String estadoInicial);




}
