package es.minhap.plataformamensajeria.iop.services.envio;

import java.util.List;

import javax.jws.WebService;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.EnvioEmailXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioSMSXMLBean;

/** La interfaz del servicio web se anota con @WebService y el resto de las
 * anotaciones estandar de JWS. Con esta unica anotacion es suficiente para
 * tener un web service funcionando con Apache CXF.
 * @author i-nercya
 */
@WebService
public interface IEnvioMensajesService {

	/**
	 * 
	 * @param xmlEmail
	 * @return Devuelve el xml del proceso
	 */
    String enviarEmail(EnvioEmailXMLBean envioEmail, List<String> listaErrores);
    
    String enviarEmail(EnvioEmailXMLBean envioEmail);

    /**
     * 
     * @param xmlMensaje
     * @return Devuelve el XML del proceso
     */
    String enviarSMS(EnvioSMSXMLBean envioSMS);
    
    
    String enviarNotificacion(EnvioPushXMLBean notificacionPush);
    
    String enviarNotificacion(EnvioPushXMLBean notificacionPush, PropertiesServices ps);

	boolean asociadoAlOrganismo(String servicio, String OrganismoPagador);

	boolean esMultiorganismo(String servicio);
}
