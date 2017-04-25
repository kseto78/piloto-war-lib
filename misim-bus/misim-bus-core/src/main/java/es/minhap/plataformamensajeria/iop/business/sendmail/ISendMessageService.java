package es.minhap.plataformamensajeria.iop.business.sendmail;





/**
 * Clase interfaz de metodos comunes que utilizan los Dispatcher de JMS
 * 
 * @author everis
 *
 */
public interface ISendMessageService {
	
	public void postMail(Long mensajeId, String destinatarioMensajeId) throws Exception;

	public void postSMS(Long idMensaje, Long loteId, Long destinatarioMensajeId, String codOrganismo, String usuarioAplicacion, String passAplicacion, String aplicacionPremium) throws Exception;	
		
	public void postNotificacionPush(Long mensajeId, Long loteId, Long destinatarioMensajeId) throws Exception;
	
	public void postRecepcionSMS(Long mensajeId, Long destinatarioMensajeId) throws Exception;
}
