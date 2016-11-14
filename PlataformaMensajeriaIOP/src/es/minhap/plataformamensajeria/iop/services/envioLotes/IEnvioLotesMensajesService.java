package es.minhap.plataformamensajeria.iop.services.envioLotes;

import javax.jws.WebService;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.lotes.PeticionXMLBean;

/** La interfaz del servicio web se anota con @WebService y el resto de las
 * anotaciones estandar de JWS. Con esta unica anotacion es suficiente para
 * tener un web service funcionando con Apache CXF.
 * @author i-nercya
 */
@WebService
public interface IEnvioLotesMensajesService {

	
    String enviarLotesEmail(PeticionXMLBean peticionXML, PropertiesServices ps);
    
    String enviarLotesEmail(PeticionXMLBean peticionXML);

   
    String enviarLotesSMS(PeticionXMLBean peticionXML);
    
    
    String enviarLotesNotificacion(PeticionXMLBean peticionXML);


}
