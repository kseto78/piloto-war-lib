package es.redsara.misim.misim_bus_webapp.enviarnotificacion;

import java.net.URL;

import javax.xml.namespace.QName;

import es.minhap.plataformamensajeria.sm.modelo.NotificacionPushData;
import es.redsara.misim.misim_bus_webapp.enviarnotificacion.EnviarMensajesWS;
import es.redsara.misim.misim_bus_webapp.enviarnotificacion.peticion.DatosEspecificos;
import es.redsara.misim.misim_bus_webapp.enviarnotificacion.peticion.NotificacionDataRequest;
import es.redsara.misim.misim_bus_webapp.enviarnotificacion.peticion.Peticion;
import es.redsara.misim.misim_bus_webapp.enviarnotificacion.respuesta.Respuesta;


public class EnviarNotificacionService {
	
	public static URL wsdlURL = null;
	public static QName serviceName = null;
	public static EnviarMensajesWS service = null;
	
	static EnviarMensajesWSBindingPortType port = null;
	
public static Respuesta enviarNotificacion(String usuario, String password, String proveedor, String producto, int messageId, NotificacionDataRequest notificacionPushData) {
		
		if (port == null) {			
			service =  new EnviarMensajesWS(wsdlURL, serviceName);
			port = service.getEnviarMensajesWSPort();
		}
		
		Peticion pet = new Peticion();
		
		pet.setUsuario(usuario);
		pet.setPassword(password);
		pet.setProducto(producto);
		pet.setProveedor(proveedor);
		pet.setMensajeId(""+messageId);
		
	
		DatosEspecificos de = new DatosEspecificos();
		de.setNotificacionDataRequest(notificacionPushData);
				
		pet.setDatosEspecificos(de);
		
		System.out.println("Peticion a MISIM: "+pet);
				
		return port.enviarMensaje(pet);
		
		

		
	}
	
	public static Respuesta recpecionSmsTest() {
		
	/*	if (port == null) {			
			port = service.getEnviarMensajesWSPort();
		}
		
//		PeticionRecibirSms pet = new PeticionRecibirSms();

		pet.setUsuario("pruebasSIMdes");
		pet.setPassword("pruebasSIMdes");
		pet.setProducto("ESTADO_SMS");
		pet.setProveedor("VODAFONE");
		pet.setMensajeId("1234");
		
			
	
		DatosEspecificos de = new DatosEspecificos();
		
	//	EnvioAplicacionRequest env = new EnvioAplicacionRequest();
		
		
	

		pet.setDatosEspecificos(de);

		return port.enviarMensaje(pet);
		*/
		return null;
	}

}
