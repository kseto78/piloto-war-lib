package es.minhap.plataformamensajeria.iop.services.gestionNotificacionesPush;

import javax.jws.WebService;

import es.minhap.plataformamensajeria.iop.beans.NotificacionesPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.PeticionGetAvisosUsuario;

/** La interfaz del servicio web se anota con @WebService y el resto de las
 * anotaciones estandar de JWS. Con esta unica anotacion es suficiente para
 * tener un web service funcionando con Apache CXF.
 * @author i-nercya
 */
@WebService
public interface IGestionNotificacionesPushService {

	String notificacionCambioEstado(NotificacionesPushXMLBean notificacionesPushXMLBean);
	
	String getAvisosUsuarioPush(PeticionGetAvisosUsuario peticion);

}
