package es.mpr.template.ws.saludo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * <p>Interface del servicio web de ejemplo <i>{http://ws.mpr.es/saludo}SaludarService</i>
 * 
 * Contiene las anotaciones JAX-WS necesarias para el despliegue del servicio 
 * </p>
 * 
 * @author Altran
 *
 */
@WebService(targetNamespace = "http://ws.mpr.es/saludo", name = "Saludar")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ISaludoService {

    /**
     * Saludar.
     *
     * @param in the in
     * @return the es.mpr.template.ws.saludo.types. saludo return
     */
    @WebResult(name = "saludoReturn", targetNamespace = "http://ws.mpr.es/saludo/types", partName = "out")
    @WebMethod() es.mpr.template.ws.saludo.types.SaludoReturn saludar(
        @WebParam(partName = "in", name = "saludoIn", targetNamespace = "http://ws.mpr.es/saludo/types")
        es.mpr.template.ws.saludo.types.SaludoIn in
    );
}
