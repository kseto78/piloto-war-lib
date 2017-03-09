package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;
import java.util.Map;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.entity.DetallesMensajesBean;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.Aviso;
import es.minhap.plataformamensajeria.sm.modelo.DatosServicio;
import es.minhap.plataformamensajeria.sm.modelo.MailData;
import es.minhap.plataformamensajeria.sm.modelo.NotificacionPushData;
import es.minhap.plataformamensajeria.sm.modelo.ReceptorSMSData;
import es.minhap.plataformamensajeria.sm.modelo.SMSData;

/**
 * 
 * @author everis
 *
 */
public interface QueryExecutorMensajes {
	
	/**
	 * Obtiene un listado de mensajes para anular a partir del servicio y del numero reintentos
	 * 
	 * @param servicio
	 * @param reintentos
	 * @param string
	 * @return
	 */
	public List<Long> getMensajesParaAnular(Long servicio, Integer reintentos);
	
	/**
	 * Se obtiene los detalles a nivel de destinatarios mensajes y mensajes para un 
	 * id mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	public DetallesMensajesBean getDetallesMensaje(Long idMensaje);
	
	/**
	 * Obtiene los datos del servicio a partir del id mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	public DatosServicio getDatosServicios(Long idMensaje);
	
	
	/**
	 * Obtiene los mensajes por lote y estado
	 * 
	 * @param idLote
	 * @param stado
	 * @return
	 */
	public List<Long> getMensajesPorLoteYEstado(Long idLote, String estado);

	
	/**
	 * Obtiene los mensajes por lote y estado Multidestinatario
	 * 
	 * @param usersId
	 * @param estado
	 * @return Map<Integer,List<Long>>
	 */
	public Map<Long, List<Long>> getMensajesPorUsuariosPushYEstadoMultidest(String usersId, String estado);

	/**
	 * Obtiene los mensajes por lote y estado
	 * 
	 * @param usersId
	 * @param estado
	 * @return List<Long>
	 */
	public List<Long> getMensajesPorUsuariosPushYEstado(String usersId, String estado);

	/**
	 * Obtiene los Avisos del usuario paginados
	 * 
	 * @param usuarios
	 * @param numPagina
	 * @param tamPagina
	 * @param ps
	 * @return List<Aviso>
	 */
	public List<Aviso> getAvisosMensajeUsuario(String usuarios, String numPagina, String tamPagina, PropertiesServices ps);


	/**
	 * Se recupera un bean de tipo DatoServicio a partir del identificador dle mensaje
	 * 
	 * @param mensajeId
	 * @return
	 */
	public DatosServicio getDataFromServices(Long mensajeId);
	
	/**
	 * Se recupera un listado con los detalles de los multi destinatarios
	 * 
	 * @param mmensajeId
	 * @return
	 */
	public MailData getDetailsMultidestinatario(Long mensajeId);
	

	/**
	 * Se recupera un listado con los detalles de los destinatarios
	 * 
	 * @param mensajeId
	 * @return
	 */
	public MailData getDetails(Long mensajeId);

	/**
	 * Se recupera un listado de los servidores push a partir del mensaje id y los datos del servicio para multidestinatarios
	 * 
	 * @param mensajeId
	 * @param destinatarioMensajeId
	 * @param smsServiceData
	 * @return
	 */
	public NotificacionPushData getDetailsServidorPushMultidestinatario(Long mensajeId, Long destinatarioMensajeId, DatosServicio smsServiceData);
	
	
	/**
	 * Se recupera un listado de los servidores push a partir del mensaje id y los datos del servicio
	 * 
	 * @param mensajeId
	 * @param serviceData
	 * @return
	 */
	public NotificacionPushData getDetailsServidorPush(Long mensajeId, DatosServicio serviceData);
	
	/**
	 * Se recupera el listado de SMS receptores para un mensajeId y los datos del servicio receptor para multidestinatarios
	 * 
	 * @param mensajeId
	 * @param destinatarioMensajeId
	 * @param receptorSMSData
	 * @return
	 */
	public ReceptorSMSData getDetailsReceptorSMSMultidestinatario(Long mensajeId, Long destinatarioMensajeId, ReceptorSMSData receptorSMSData);
	
	/**
	 * Se recupera los datos del receptor de SMS para un mensajeId y los datos del servicio receptor
	 * 
	 * @param mensajeId
	 * @param receptorSMSData
	 * @return
	 */
	public ReceptorSMSData getDetailsReceptorSMS(Long mensajeId, ReceptorSMSData receptorSMSData);
	
	/***
	 * Recupera una lista de servidores SMS a partir de un mensajeId y los datos del servidor SMS
	 * 
	 * @param mensajeId
	 * @param destinatarioMensajeId
	 * @param smsDataComun
	 * @return
	 */
	public SMSData getDetailsSMSMultidestinatario(Long mensajeId, Long destinatarioMensajeId, SMSData smsDataComun);

	/***
	 * Recupera una lista con los mensajes que tienen algún destinatrario pendiente de envío
	 * 
	 * @return
	 */
	public List<Long> getMensajesPendientes();
}
