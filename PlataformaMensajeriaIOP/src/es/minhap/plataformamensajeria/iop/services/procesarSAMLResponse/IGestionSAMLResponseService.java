package es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse;

import javax.jws.WebService;

import es.minhap.plataformamensajeria.iop.beans.PeticionClaveAuthResponse;

/** La interfaz del servicio web se anota con @WebService y el resto de las
 * anotaciones estandar de JWS. Con esta unica anotacion es suficiente para
 * tener un web service funcionando con Apache CXF.
 * @author i-nercya
 */
@WebService
public interface IGestionSAMLResponseService {

	String insertarDatosUsuario(PeticionClaveAuthResponse peticion, String nombre, String nif, String apellido1, String apellido2);

}
