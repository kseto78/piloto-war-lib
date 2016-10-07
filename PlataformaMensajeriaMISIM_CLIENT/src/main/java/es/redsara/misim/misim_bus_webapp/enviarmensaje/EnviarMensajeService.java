package es.redsara.misim.misim_bus_webapp.enviarmensaje;



import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import es.redsara.misim.misim_bus_webapp.enviarmensaje.peticion.DatosEspecificos;
import es.redsara.misim.misim_bus_webapp.enviarmensaje.peticion.Peticion;
import es.redsara.misim.misim_bus_webapp.enviarmensaje.respuesta.Respuesta;



public class EnviarMensajeService {
	
	
	public static URL wsdlURL = null;
	public static QName serviceName = null;
	public static EnviarMensajesWS service = null;
	
	static EnviarMensajesWSBindingPortType port = null;
	
	public static Respuesta enviarMensaje(String usuario, String password, String producto, String proveedor, String mensajeID, String usuarioProveedor, String passwordProveedor, String SMS_ID, String SMS_DESTINATARIO, String SMS_HEADER, String SMS_TEXTO, String urlEndpoint ) {
		
		if (port == null) {		
			service = new EnviarMensajesWS(wsdlURL, serviceName);
			port = service.getEnviarMensajesWSPort();
		}
		
		Peticion pet = new Peticion();
		
		pet.setUsuario(usuario);
		pet.setPassword(password);
		pet.setProducto(producto);
		pet.setProveedor(proveedor);
		pet.setMensajeId(mensajeID);
		pet.setUrlEndpoint(urlEndpoint);
	
		DatosEspecificos de = new DatosEspecificos();

		de.setSMS_ID(SMS_ID);
		de.setSMS_USUARIO(usuarioProveedor);
		de.setSMS_PASSWORD(passwordProveedor);
		de.setSMS_DESTINATARIO(SMS_DESTINATARIO);
		de.setSMS_HEADER(SMS_HEADER);
		de.setSMS_TEXTO(SMS_TEXTO);

		pet.setDatosEspecificos(de);
		
		
		System.out.println("Peticion a MISIM: "+pet);

		return port.enviarMensaje(pet);

		
	}
	public static Respuesta enviarMensajeUrlWsdl(String usuario, String password, String producto, String proveedor, String mensajeID, String usuarioProveedor, String passwordProveedor, String SMS_ID, String SMS_DESTINATARIO, String SMS_HEADER, String SMS_TEXTO ,String URL_WSDL) {
		
		Peticion pet = new Peticion();
		if (port == null) {		
			try {
				service = new EnviarMensajesWS(new URL(URL_WSDL), serviceName);
				port = service.getEnviarMensajesWSPort();
				
				
				pet.setUsuario(usuario);
				pet.setPassword(password);
				pet.setProducto(producto);
				pet.setProveedor(proveedor);
				pet.setMensajeId(mensajeID);
				
			
				DatosEspecificos de = new DatosEspecificos();

				de.setSMS_ID(SMS_ID);
				de.setSMS_USUARIO(usuarioProveedor);
				de.setSMS_PASSWORD(passwordProveedor);
				de.setSMS_DESTINATARIO(SMS_DESTINATARIO);
				de.setSMS_HEADER(SMS_HEADER);
				de.setSMS_TEXTO(SMS_TEXTO);

				pet.setDatosEspecificos(de);
				
				
				System.out.println("Peticion a MISIM: "+pet);

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return port.enviarMensaje(pet);

		
	}
	
	public static Respuesta enviarMensajeTestTest() {
		
		if (port == null) {			
			port = service.getEnviarMensajesWSPort();
		}
		
		Peticion pet = new Peticion();

		pet.setUsuario("pruebasSIMdes");
		pet.setPassword("pruebasSIMdes");
		pet.setProducto("ESTADO_SMS");
		pet.setProveedor("VODAFONE");
		pet.setMensajeId("1234");
		
		
	
		DatosEspecificos de = new DatosEspecificos();

		de.setSMS_ID("99999");
		de.setSMS_DESTINATARIO("649592368");
		de.setSMS_HEADER("215039");
		de.setSMS_TEXTO("PREUBA ENVIO");

		pet.setDatosEspecificos(de);

		return port.enviarMensaje(pet);
	}

}
