package es.mpr.plataformamensajeria.ws.operaciones;

import javax.jws.WebService;

/** La interfaz del servicio web se anota con @WebService y el resto de las
 * anotaciones estandar de JWS. Con esta unica anotacion es suficiente para
 * tener un web service funcionando con Apache CXF.
 * @author i-nercya
 */
@WebService
public interface IOperacionesMensajesService {
	
	/**
	 * 
	 * @param idMensaje
	 * @param usuario
	 * @param password
	 * @return Devuelve el xml del resultado de la operación
	 */
	String reenviarMensaje(Integer idMensaje, String usuario, String password);

    /**
     * 
     * @param idMensaje
     * @param usuario
     * @param password
     * @return Devuelve el xml del resultado de la operación
     */
    String anularMensaje(Integer idMensaje, String usuario, String password);
    
    /**
     * 
     * @param idLote
     * @param usuario
     * @param password
     * @return Devuelve el xml del resultado de la operación
     */
    String reenviarLote(Integer idLote, String usuario, String password);
    
    /**
     * 
     * @param idLote
     * @param usuario
     * @param password
     * @return Devuelve el xml del resultado de la operación
     */
    String anularLote(Integer idLote, String usuario, String password);
}
