package es.minhap.plataformamensajeria.iop.services.operaciones;

import javax.jws.WebService;

import es.minhap.plataformamensajeria.iop.beans.OperacionesLotesMensajesXMLBean;
import es.minhap.plataformamensajeria.iop.beans.OperacionesMensajesXMLBean;

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
	 * @return Devuelve el xml del resultado de la operaci�n
	 */
	String reenviarMensaje(Integer idMensaje, String usuario, String password);

    /**
     * 
     * @param idMensaje
     * @param usuario
     * @param password
     * @return Devuelve el xml del resultado de la operaci�n
     */
    String anularMensaje(Integer idMensaje, String usuario, String password);
    
    /**
     * 
     * @param idLote
     * @param usuario
     * @param password
     * @return Devuelve el xml del resultado de la operaci�n
     */
    String reenviarLote(Integer idLote, String usuario, String password);
    
    /**
     * 
     * @param idLote
     * @param usuario
     * @param password
     * @return Devuelve el xml del resultado de la operaci�n
     */
    String anularLote(Integer idLote, String usuario, String password);
    
    
    String reenviarMensaje(OperacionesMensajesXMLBean operacionesMensajes);
	
    String anularMensaje(OperacionesMensajesXMLBean operacionesMensajes);
	
	String reenviarLote(OperacionesLotesMensajesXMLBean operacionesLotesMensajes);
	
	String anularLote(OperacionesLotesMensajesXMLBean operacionesLotesMensajes);
				
}
