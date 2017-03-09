package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;
import java.util.Map;

import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.EnvioEmailXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.MensajeSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.MensajesXMLBean;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.Aviso;
import es.minhap.plataformamensajeria.iop.beans.lotes.DestinatarioPeticionLotesPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.MensajePeticionLotesPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.respuestasEnvios.Mensaje;
import es.minhap.sim.model.TblMensajes;

/**
 * 
 * @author everis
 *
 */
public interface TblMensajesManager {
	/**
	 * Inserta un mensaje nuevo con los datos necesarios del envío
	 * 
	 * @param idLote
	 * @param mensaje
	 * @param usuario
	 * @param password
	 * @return 
	 */
	public Mensaje insertarMensajeSMS(Long idLote, MensajeSMSXMLBean mensaje, String usuario, String password);

	/**
	 * Inserta un mensaje nuevo tras una recepcion sms
	 * 
	 * @param idLote
	 * @param smsText
	 * @param messageId
	 * @param sender
	 * @param userAplicacion
	 * @param passwordAplicacion
	 * @return Mensaje
	 */
	public Integer insertarMensajeRecepcionSMS(Long idLote, String smsText, String messageId, String sender,
			String userAplicacion, String passwordAplicacion);

	/**
	 * Inserta un mensaje nuevo con los datos necesarios del envío
	 * 
	 * @param idLote
	 * @param mensaje
	 * @param notificacion
	 * @param destinatario
	 * @param usuarioId
	 * @return Mensaje
	 */
	public Mensaje insertarMensajePush(Long idLote, MensajePeticionLotesPushXMLBean mensaje,
			EnvioPushXMLBean notificacion, DestinatarioPeticionLotesPushXMLBean destinatario, Integer usuarioId);

	/**
	 * Inserta un mensaje nuevo con los datos necesarios del envío
	 * 
	 * @param idLote
	 * @param mensaje
	 * @param envio
	 * @param to
	 * @param cc
	 * @param bcc
	 * @return Mensaje
	 * 
	 */
	public Mensaje insertarMensajeEmail(Long idLote, MensajesXMLBean mensaje, EnvioEmailXMLBean envio, String cc, String to, String bcc);
	
	/**
	 * Obtiene un elemento TblMensajes
	 * 
	 * @param idMensaje
	 * @return
	 */
	public TblMensajes getMensaje(Long idMensaje);
	
	/**
	 * Inserta un nuemo mensaje para un mensaje de tipo GISS
	 * 
	 * @param idLote
	 * @param cuerpo
	 * @param docUsuario
	 * @param codOrganismoPagadorSMS
	 * @param idExterno
	 * @param destinatario
	 * @param usuario
	 * @param password
	 * @return Integer
	 */
	public Integer insertarMensajeGISS(Integer idLote, String cuerpo, String docUsuario,
			String codOrganismoPagadorSMS, String idExterno, String destinatario, String usuario,
			String password);


	/**
	 * Obtiene una Lista de  TblMensajes que pertenecen a un lote
	 * 
	 * @param idLote
	 * @return
	 */
	public List<TblMensajes> getMensajesByLote(Long idLote);
	
	/**
	 * Se actualiza el mensaje en la tabla TBL_MENSAJES
	 * 
	 * @param mensaje
	 */
	public void update(TblMensajes mensaje);
	
	
	/**
	 * Se obtiene el id de servicio a partir del id mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	public Long getIdServicioByIdMensaje(Long idMensaje);
	
	
	/**
	 * Prepara el mensaje para el reenvio
	 * 
	 * @param idMensaje
	 * @param usuario
	 * @param password
	 * @param estadoFinal
	 * @return 
	 */
	public Integer operacionMensaje(Long idMensaje, String usuario, String password, String estadoFinal);
	
	/**
	 * Cambia el estado de un mensaje
	 * 
	 * @param idMensaje
	 * @param estado
	 * @param descripcion
	 * @param controlReintentos 
	 * @param destinatarioMensajeId
	 * @param subEstadoCode
	 * @param usuario
	 * @param proveedorId
	 * @return 
	 */
	public Integer setEstadoMensaje(Long idMensaje, String estado, String descripcion, Boolean controlReintentos, 
			Long destinatarioMensajeId, String subEstadoCode, String usuario, Long proveedorId);

	/**
	 * Obtiene el mensaje a partir del UIM
	 * 
	 * @param uim
	 * @return 
	 */
	public TblMensajes getMensajeIDByUIM(String uim);
	
	
	/**
	 * Actualiza todos los mensajes de los usuarios push pasados como par�metro
	 * 
	 * @param usersId
	 * @param estadoInicial
	 * @param estadoFinal
	 * @param usuarioPeticion
	 * @return int
	 */
	public int updateMessagesUsers(List<String> usersId, String estadoInicial, String estadoFinal, String usuarioPeticion);

	/**
	 * Comprueba si un mensaje es de un usuario
	 * 
	 * @param usersId
	 * @param mensajeId
	 * @return Boolean
	 */
	public Boolean isMessageUser(List<String> usersId, Long mensajeId);
	
	/**
	 * Establece el estado indicado al a los usuarios del mensaje indicado
	 * 
	 * @param mapMensajesMult
	 * @param estadoInicial
	 * @param estadoFinal
	 * @param usuarioPeticion
	 * @return Integer
	 */
	public Integer setEstadoMensajeUsuarios(Map<Long, List<Long>> mapMensajesMult, String estadoInicial, String estadoFinal, String usuarioPeticion);
	
	/**
	 * Obtiene todas los Avisos que ha recibido un usuario
	 * 
	 * @param idDispositivo
	 * @param idPlataforma
	 * @param idServicio
	 * @param idUsuario
	 * @param numPagina
	 * @param tamPagina
	 * @param ps
	 * @return List<Aviso>
	 */
	public List<Aviso> getAvisosUsuario(String idDispositivo, String idPlataforma, String idServicio, String idUsuario, String numPagina, String tamPagina,PropertiesServices ps);
	
	/**
	 * Recupera la prioridad a partir del identificador de mensaje
	 * 
	 * @param mensajeId
	 * @return
	 */
	public Integer getPrioridadByIdMessage(Long mensajeId);

	/**
	 * Obtiene el lote a partir de un mensaje
	 * 
	 * @param mensajeId
	 * @return Boolean
	 */
	public Boolean getMultidestinatarioByMensaje(Long mensajeId);
	
	/**
	 * Obtiene Los mensajes a reenviar por estar pendientes
	 * 
	 * @return
	 */
	public Map<Long, List<MensajeJMS>> getMensajesReenviar();

		
}
