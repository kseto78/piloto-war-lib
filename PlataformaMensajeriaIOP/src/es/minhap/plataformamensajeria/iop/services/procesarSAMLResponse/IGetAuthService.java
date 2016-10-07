package es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse;

import javax.jws.WebService;

import es.minhap.plataformamensajeria.iop.beans.PeticionClaveAuthRequest;

/** La interfaz del servicio web se anota con @WebService y el resto de las
 * anotaciones estandar de JWS. Con esta unica anotacion es suficiente para
 * tener un web service funcionando con Apache CXF.
 * @author everis
 */
@WebService
public interface IGetAuthService {

	/**
	 * Obtiene los datos del usuario a partir de la peticion
	 * @param peticion
	 * @return
	 */
	String getDatosUsuario(PeticionClaveAuthRequest peticion);

}
