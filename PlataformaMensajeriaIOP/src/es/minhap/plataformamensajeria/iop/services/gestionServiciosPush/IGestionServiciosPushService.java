package es.minhap.plataformamensajeria.iop.services.gestionServiciosPush;

import javax.jws.WebService;

import es.minhap.plataformamensajeria.iop.beans.RegistroUsuarioXMLBean;
import es.minhap.plataformamensajeria.iop.beans.ServiciosDisponiblesXMLBean;

/** La interfaz del servicio web se anota con @WebService y el resto de las
 * anotaciones estandar de JWS. Con esta unica anotacion es suficiente para
 * tener un web service funcionando con Apache CXF.
 * @author i-nercya
 */
@WebService
public interface IGestionServiciosPushService {


	String consultaServiciosDisponibles(ServiciosDisponiblesXMLBean servDispXMLBean);

	String registroUsuarioEnServicio(RegistroUsuarioXMLBean registroUsuarioXMLBean);
}
