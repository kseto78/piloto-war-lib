package es.mpr.plataformamensajeria.ws.envio;

import javax.jws.WebService;

/** La interfaz del servicio web se anota con @WebService y el resto de las
 * anotaciones estandar de JWS. Con esta unica anotacion es suficiente para
 * tener un web service funcionando con Apache CXF.
 * @author i-nercya
 */
@WebService
public interface IEnvioMensajesService {

	/**
	 * 
	 * @param xmlEmail
	 * @return Devuelve el xml del proceso
	 */
    String enviarEmail(String xmlEmail);

    /**
     * 
     * @param xmlMensaje
     * @return Devuelve el XML del proceso
     */
    String enviarMensaje(String xmlMensaje);
}
