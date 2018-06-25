package es.minhap.plataformamensajeria.iop.services.gestionServiciosPush;

import javax.jws.WebService;

import es.minhap.plataformamensajeria.iop.beans.PeticionConfirmarAltaUsuario;
import es.minhap.plataformamensajeria.iop.beans.PeticionSolicitudRegistroMovil;

/** La interfaz del servicio web se anota con @WebService y el resto de las
 * anotaciones estandar de JWS. Con esta unica anotacion es suficiente para
 * tener un web service funcionando con Apache CXF.
 * @author i-nercya
 */
@WebService
public interface IRegistroMovilService {


	String solicitudRegistroMovil(PeticionSolicitudRegistroMovil peticionBean);

	String confirmarAltaUsuario(PeticionConfirmarAltaUsuario peticionBean);
	
	void eliminarRegistrosCaducados();
}
