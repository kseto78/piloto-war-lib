package es.mpr.template.ws.saludo;

import javax.jws.WebParam;

import es.mpr.template.ws.saludo.types.SaludoIn;
import es.mpr.template.ws.saludo.types.SaludoReturn;

/**
 * <p>Clase implementaci&oacute;n del servicio web de ejemplo <i>{http://ws.mpr.es/saludo}SaludarService</i> 
 * 
 * Contiene las anotaciones JAX-WS necesarias para el despliegue del servicio</p>
 * 
 * @author Altran
 *
 */
public class SaludoServiceImpl implements ISaludoService {

	/* (non-Javadoc)
	 * @see es.mpr.template.ws.saludo.ISaludoService#saludar(es.mpr.template.ws.saludo.types.SaludoIn)
	 */
	public SaludoReturn saludar(
	        @WebParam(partName = "in", name = "saludoIn", targetNamespace = "http://ws.mpr.es/saludo/types")
	        SaludoIn in
	    ){
				
		SaludoReturn result = new SaludoReturn();
		result.setResponseType("Bienvenido " + in.getRequestType());
			
		return result;
	}
	
}
