package es.minhap.plataformamensajeria.iop.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.entity.DetallesMensajesBean;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.Aviso;
import es.minhap.plataformamensajeria.sm.modelo.DatosServicio;
import es.minhap.plataformamensajeria.sm.modelo.MailData;
import es.minhap.plataformamensajeria.sm.modelo.NotificacionPushData;
import es.minhap.plataformamensajeria.sm.modelo.NotificacionWebPushData;
import es.minhap.plataformamensajeria.sm.modelo.ReceptorSMSData;
import es.minhap.plataformamensajeria.sm.modelo.SMSData;
import es.minhap.sim.model.TblAplicaciones;

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
	List<Long> getMensajesParaAnular(Long servicio, Integer reintentos);
	
	/**
	 * Se obtiene los detalles a nivel de destinatarios mensajes y mensajes para un 
	 * id mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	DetallesMensajesBean getDetallesMensaje(Long idMensaje);
	
	/**
	 * Obtiene los datos del servicio a partir del id mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	DatosServicio getDatosServicios(Long idMensaje);
	
	
	/**
	 * Obtiene los mensajes por lote y estado
	 * 
	 * @param idLote
	 * @param stado
	 * @return
	 */
	List<Long> getMensajesPorLoteYEstado(Long idLote, String estado);

	
	/**
	 * Obtiene los mensajes por lote y estado Multidestinatario
	 * 
	 * @param usersId
	 * @param estado
	 * @return Map<Integer,List<Long>>
	 */
	Map<Long, List<Long>> getMensajesPorUsuariosPushYEstadoMultidest(String usersId, String estado);
	
	/**
	 * Obtiene los mensajes por mensaje id y estado Multidestinatario
	 * 
	 * @param usersId
	 * @param estado
	 * @return Map<Integer,List<Long>>
	 */
	Map<Long, List<Long>> getMensajesPorUsuariosPushYEstadoYMensajeMultidest(String usersId, String estado, Long mensajeId);

	/**
	 * Obtiene los mensajes por lote y estado
	 * 
	 * @param usersId
	 * @param estado
	 * @return List<Long>
	 */
	List<Long> getMensajesPorUsuariosPushYEstado(String usersId, String estado);

	/**
	 * Obtiene los Avisos del usuario paginados
	 * 
	 * @param usuarios
	 * @param numPagina
	 * @param tamPagina
	 * @param ps
	 * @return List<Aviso>
	 */
	List<Aviso> getAvisosMensajeUsuario(String usuarios, String numPagina, String tamPagina, PropertiesServices ps);


	/**
	 * Se recupera un bean de tipo DatoServicio a partir del identificador dle mensaje
	 * 
	 * @param mensajeId
	 * @return
	 */
	DatosServicio getDataFromServices(Long mensajeId);
	
	/**
	 * Se recupera un listado con los detalles de los multi destinatarios
	 * 
	 * @param mmensajeId
	 * @return
	 */
	MailData getDetailsMultidestinatario(Long mensajeId);
	

	/**
	 * Se recupera un listado con los detalles de los destinatarios
	 * 
	 * @param mensajeId
	 * @return
	 */
	MailData getDetails(Long mensajeId);

	/**
	 * Se recupera un listado de los servidores push a partir del mensaje id y los datos del servicio para multidestinatarios
	 * 
	 * @param mensajeId
	 * @param destinatarioMensajeId
	 * @param smsServiceData
	 * @return
	 */
	NotificacionPushData getDetailsServidorPushMultidestinatario(Long mensajeId, Long destinatarioMensajeId, DatosServicio smsServiceData);
	
	/**
	 * Se recupera un listado de los servidores push a partir del mensaje id y los datos del servicio para multidestinatarios
	 * 
	 * @param mensajeId
	 * @param destinatarioMensajeId
	 * @param smsServiceData
	 * @return
	 */
	NotificacionWebPushData getDetailsServidorWebPushMultidestinatario(Long mensajeId, Long destinatarioMensajeId);
	
	/**
	 * Se recupera un listado de los servidores push a partir del mensaje id y los datos del servicio
	 * 
	 * @param mensajeId
	 * @param serviceData
	 * @return
	 */
	NotificacionPushData getDetailsServidorPush(Long mensajeId, DatosServicio serviceData);
	
	/**
	 * Se recupera el listado de SMS receptores para un mensajeId y los datos del servicio receptor para multidestinatarios
	 * 
	 * @param mensajeId
	 * @param destinatarioMensajeId
	 * @param receptorSMSData
	 * @return
	 */
	ReceptorSMSData getDetailsReceptorSMSMultidestinatario(Long mensajeId, Long destinatarioMensajeId, ReceptorSMSData receptorSMSData);
	
	/**
	 * Se recupera los datos del receptor de SMS para un mensajeId y los datos del servicio receptor
	 * 
	 * @param mensajeId
	 * @param receptorSMSData
	 * @return
	 */
	ReceptorSMSData getDetailsReceptorSMS(Long mensajeId, ReceptorSMSData receptorSMSData);
	
	/***
	 * Recupera una lista de servidores SMS a partir de un mensajeId y los datos del servidor SMS
	 * 
	 * @param mensajeId
	 * @param destinatarioMensajeId
	 * @param smsDataComun
	 * @return
	 */
	SMSData getDetailsSMSMultidestinatario(Long mensajeId, Long destinatarioMensajeId, SMSData smsDataComun);

	/***
	 * Recupera una lista con los mensajes que tienen algún destinatrario pendiente de envío
	 * @param serviciosExcluidos 
	 * 
	 * @return
	 */
	List<Long> getMensajesPendientes(Date fechaInicio, Date fechaFinal, String serviciosExcluidos, String serviciosIncluidos);
	
	/***
	 * Numero de mensajes enviados/anulados por lote y fecha 
	 * 
	 * @param loteId
	 * @param fecha
	 * @return
	 */
	Integer countMensajesHistorificacion(Long loteId, Date fecha);


	/***
	 * Recupera una lista con los Id mensajes 
	 * 
	 * @param loteId
	 * @return
	 */
	List<Long> getIdMensajesByLote(Long loteEnvioID);

	/***
	 * Numero de mensajes enviados/anulados por lote 
	 * 
	 * @param loteId
	 * @return
	 */
	Integer countMensajesByLote(Long loteId);

	/***
	 * Obtenemos la aplicacion desde el mensaje
	 * 
	 * @param mensajeId
	 * @return
	 */
	TblAplicaciones getAplicacionFromMensaje(Long mensajeId);

	/***
	 * Obtenemos la  aplicacion desde el lote
	 * 
	 * @param loteId
	 * @return
	 */
	TblAplicaciones getAplicacionFromLote(Integer idLote);


}
