package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.plataformamensajeria.iop.beans.EnvioGISSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.entity.DestinatariosMensajesBean;
import es.minhap.plataformamensajeria.sm.modelo.Recipients;

/**
 * 
 * @author everis
 *
 */
public interface QueryExecutorDestinatariosMensajes {

	/**
	 * Comprueba si el id externo existe
	 * 
	 * @param idExterno
	 * @param sended
	 * @return
	 */
	Integer checkIdExternoExists(String idExterno, Boolean sended);
	
	/**
	 * Obtiene el numero de diferentes estados de los distintos destinatarios mensajes
	 * para un mensaje id
	 * 
	 * @param mensajeId
	 * @return
	 */
	Integer countDistinctStatus(Long mensajeId);
	
	/**
	 * Obtenermos un bean del tipo DestinatariosMensajesBean con la información
	 * a partir de un mensajeId y destinatario mensaje id
	 * 
	 * @param mensajeId
	 * @param destinatariosMensajesId
	 * @return
	 */
	DestinatariosMensajesBean getDestMenByFilters(Long mensajeId, Long destinatariosMensajesId);
	
	/**
	 * Obtenemos un listado de peticiones para reenviar a partir del id servicio y el rango de reintetos
	 * 
	 * @param servicio
	 * @param reintentos
	 * @return
	 */
	List<EnvioGISSXMLBean> obtenerMensajesReenvioGISS(Long servicio, Integer reintentos);
	
	
	/**
	 * Recupera la lista de destinatarios del identificador de mensaje
	 * 
	 * @param mensajeId
	 * @param destinatarioMensajeId
	 * @return
	 */
	Recipients getRecipientsMultidestinatarioModo1(Long mensajeId, Long destinatarioMensajeId);
	
	
	/**
	 * Recupera los destinatarios para mensajes multidestinatarios a partir del identificador de mensaje 
	 * 
	 * @param mensajeId
	 * @return
	 */
	Recipients getRecipientsMultidestinatario(Long mensajeId);
	
	/**
	 * Recupera los destinatarios a partir del identificador de mensaje
	 * 
	 * @param mensajeId
	 * @return
	 */
	Recipients getRecipients(Long mensajeId);

	/**
	 * Cuenta los destinatarios a partir del identificador de mensaje
	 * 
	 * @param mensajeId
	 * @return
	 */
	Integer countDestinatariosByMensaje(Long mensajeId);
	
}
