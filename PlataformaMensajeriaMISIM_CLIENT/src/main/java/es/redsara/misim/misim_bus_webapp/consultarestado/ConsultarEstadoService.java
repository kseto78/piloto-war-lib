package es.redsara.misim.misim_bus_webapp.consultarestado;

import java.net.URL;

import javax.xml.namespace.QName;

import es.redsara.misim.misim_bus_webapp.consultarestado.peticion.DatosEspecificos;
import es.redsara.misim.misim_bus_webapp.consultarestado.peticion.PeticionMisim;
import es.redsara.misim.misim_bus_webapp.consultarestado.respuesta.Respuesta;
import es.redsara.misim.misim_bus_webapp.consultarestado.EnviarMensajesWS;


public class ConsultarEstadoService {
	
	public static URL wsdlURL = null;
	public static QName serviceName = null;
	public static EnviarMensajesWS service = null;
	
	static EnviarMensajesWSBindingPortType port = null;
	
public static Respuesta consultarEstado(String usuario, String password, String producto, String proveedor, String mensajeID, String SMS_ID, String usuarioProveedor, String passwordProveedor,String SMS_UIM, String SMS_HEADER) {
		
		if (port == null) {		
			service =  new EnviarMensajesWS(wsdlURL, serviceName);
			port = service.getEnviarMensajesWSPort();
		}
		
		PeticionMisim pet = new PeticionMisim();
		
		pet.setUsuario(usuario);
		pet.setPassword(password);
		pet.setProducto(producto);
		pet.setProveedor(proveedor);
		pet.setMensajeId(mensajeID);
		
	
		DatosEspecificos de = new DatosEspecificos();

		de.setSMS_ID(SMS_ID);
		de.setSMS_USUARIO(usuarioProveedor);
		de.setSMS_PASSWORD(passwordProveedor);
		de.setSMS_UIM(SMS_UIM);
		de.setSMS_HEADER(SMS_HEADER);
		
		pet.setDatosEspecificos(de);
		
		System.out.println(pet.toXML());
				
		return port.enviarMensaje(pet);

		
	}
	
	public static Respuesta consultarEstadoTest() {
		
		if (port == null) {			
			port = service.getEnviarMensajesWSPort();
		}
		
		PeticionMisim pet = new PeticionMisim();

		pet.setUsuario("pruebasSIMdes");
		pet.setPassword("pruebasSIMdes");
		pet.setProducto("ESTADO_SMS");
		pet.setProveedor("VODAFONE");
		pet.setMensajeId("1234");
		
		
	
		DatosEspecificos de = new DatosEspecificos();

		de.setSMS_ID("99999");
		de.setSMS_UIM("SwitchSMS_MT_84205_9871");
		de.setSMS_HEADER("215039");
	

		pet.setDatosEspecificos(de);

		return port.enviarMensaje(pet);
	}

}
