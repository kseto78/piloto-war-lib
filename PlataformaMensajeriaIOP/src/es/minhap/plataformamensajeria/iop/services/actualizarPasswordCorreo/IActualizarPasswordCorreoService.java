package es.minhap.plataformamensajeria.iop.services.actualizarPasswordCorreo;

import javax.jws.WebService;

import es.minhap.plataformamensajeria.iop.beans.PeticionActualizarPasswordCorreo;

/**
 * La interfaz del servicio web se anota con @WebService y el resto de las
 * anotaciones estandar de JWS. Con esta unica anotacion es suficiente para
 * tener un web service funcionando con Apache CXF.
 * 
 * @author i-nercya
 */
@WebService
public interface IActualizarPasswordCorreoService {

	/**
	 * 
	 * @param peticion
	 * @return Devuelve un xml con los resultados
	 */

	String consultarEstado(PeticionActualizarPasswordCorreo peticion);

}
