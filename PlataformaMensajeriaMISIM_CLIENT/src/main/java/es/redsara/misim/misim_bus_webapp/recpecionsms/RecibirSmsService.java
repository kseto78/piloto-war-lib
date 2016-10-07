package es.redsara.misim.misim_bus_webapp.recpecionsms;

import java.net.URL;

import javax.xml.namespace.QName;

import es.redsara.misim.misim_bus_webapp.recpecionsms.EnviarMensajesWS;
import es.redsara.misim.misim_bus_webapp.recpecionsms.peticion.DatosEspecificos;
import es.redsara.misim.misim_bus_webapp.recpecionsms.peticion.EnvioAplicacionRequest;
import es.redsara.misim.misim_bus_webapp.recpecionsms.peticion.PeticionRecibirSms;
import es.redsara.misim.misim_bus_webapp.recpecionsms.respuesta.Respuesta;


public class RecibirSmsService {
	
	public static URL wsdlURL = null;
	public static QName serviceName = null;
	public static EnviarMensajesWS service = null;
	
	static EnviarMensajesWSBindingPortType port = null;
	
public static Respuesta recpecionsms(String usuario, String password, String proveedor, String mensajeID, EnvioAplicacionRequest envioAplicacionRequest) {
		
		if (port == null) {			
			service =  new EnviarMensajesWS(wsdlURL, serviceName);
			port = service.getEnviarMensajesWSPort();
		}
		
		PeticionRecibirSms pet = new PeticionRecibirSms();
		
		pet.setUsuario(usuario);
		pet.setPassword(password);
		pet.setProducto("SMS_APLICACION");
		pet.setProveedor(proveedor);
		pet.setMensajeId(mensajeID);
		
	
		DatosEspecificos de = new DatosEspecificos();

		de.setEnvioAplicacionRequest(envioAplicacionRequest);
				
		pet.setDatosEspecificos(de);
		
		System.out.println("Peticion a MISIM: "+pet);
				
		return port.enviarMensaje(pet);

		
	}
	
	public static Respuesta recpecionSmsTest() {
		
		if (port == null) {			
			port = service.getEnviarMensajesWSPort();
		}
		
		PeticionRecibirSms pet = new PeticionRecibirSms();

		pet.setUsuario("pruebasSIMdes");
		pet.setPassword("pruebasSIMdes");
		pet.setProducto("ESTADO_SMS");
		pet.setProveedor("VODAFONE");
		pet.setMensajeId("1234");
		
			
	
		DatosEspecificos de = new DatosEspecificos();
		
		EnvioAplicacionRequest env = new EnvioAplicacionRequest();
		
		
	

		pet.setDatosEspecificos(de);

		return port.enviarMensaje(pet);
	}

}
