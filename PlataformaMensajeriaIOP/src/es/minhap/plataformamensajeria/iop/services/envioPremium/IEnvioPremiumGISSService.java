package es.minhap.plataformamensajeria.iop.services.envioPremium;

import java.util.List;

import javax.jws.WebService;

import es.minhap.plataformamensajeria.iop.beans.EnvioGISSXMLBean;

/** La interfaz del servicio web se anota con @WebService y el resto de las
 * anotaciones estandar de JWS. Con esta unica anotacion es suficiente para
 * tener un web service funcionando con Apache CXF.
 * @author i-nercya
 */ 
@WebService
public interface IEnvioPremiumGISSService {
	
    String enviarSMSGISS(EnvioGISSXMLBean envio, String username,String password,Integer servicio,String usernameMISIM,String passwordMISIM);
    
    List<EnvioGISSXMLBean> reenviarSMSGISS(String username,String password,Integer servicio, Integer reintentos,String usernameMISIM,String passwordMISIM);
    
    void anularSMS(Integer servicio, Integer reintentos);

    String cambiarEstadoSMSPremium(Integer idMEnsaje, String estado);
    
}
