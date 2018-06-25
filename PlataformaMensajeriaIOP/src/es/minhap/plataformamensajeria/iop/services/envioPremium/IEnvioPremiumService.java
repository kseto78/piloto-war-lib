package es.minhap.plataformamensajeria.iop.services.envioPremium;

import javax.jws.WebService;

import es.minhap.plataformamensajeria.iop.beans.EnvioAEATXMLBean;

/** La interfaz del servicio web se anota con @WebService y el resto de las
 * anotaciones estandar de JWS. Con esta unica anotacion es suficiente para
 * tener un web service funcionando con Apache CXF.
 * @author i-nercya
 */
@WebService
public interface IEnvioPremiumService {
	
    String enviarSMSPremium(EnvioAEATXMLBean envio, String username,String password,Integer servicio,String usernameMISIM,String passwordMISIM, Integer reintentos);

    String cambiarEstadoSMSPremium(Integer idMEnsaje, String estado);

	String gerUrlEndpoint(String messageId);
    
}
