package es.minhap.plataformamensajeria.iop.services.ayuda;

import javax.jws.WebService;

/** La interfaz del servicio web se anota con @WebService y el resto de las
 * anotaciones estandar de JWS. Con esta unica anotacion es suficiente para
 * tener un web service funcionando con Apache CXF.
 * @author i-nercya
 */
@WebService
public interface IGestionAyudaService {

	String gestionAyuda(String username, String password);

}
