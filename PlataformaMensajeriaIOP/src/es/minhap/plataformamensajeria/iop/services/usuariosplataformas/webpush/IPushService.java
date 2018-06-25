package es.minhap.plataformamensajeria.iop.services.usuariosplataformas.webpush;

import es.minhap.plataformamensajeria.iop.beans.DatosEspecificosWebPush;




/**
 * Clase interfaz de metodos comunes que utilizan los Dispatcher de JMS
 * 
 * @author everis
 *
 */
public interface IPushService {

	
	/**
	 * Se envía el mensaje PUsh
	 * 
	 * @param datos
	 * @param mensajeId is the mensaje id
	 * @return
	 */
	public String sendPush(DatosEspecificosWebPush datos, String mensajeId); 
	
	/**
	 * Se generan nuevas clasves para codificar la web push
	 * 
	 * @return String
	 */
	public String getNewKeys(); 

}
