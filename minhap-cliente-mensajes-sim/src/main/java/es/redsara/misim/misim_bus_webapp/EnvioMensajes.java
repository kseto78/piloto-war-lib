package es.redsara.misim.misim_bus_webapp;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.common.spring.ApplicationContextProvider;
import es.redsara.misim.misim_bus_webapp.peticion.Peticion;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;

/**
 * @author everis
 *
 */
public class EnvioMensajes {
	
	private static final Logger LOGGER = Logger.getLogger(EnvioMensajes.class);
	
	private static EnvioMensajes envioMensajes;
	
	@Resource
	private EnvioMensajesServiceWSBindingPortType_EnvioMensajesServicePort_Client envioMensajesClient;

		
	private Peticion peticion = new Peticion();
	
	private ApplicationContextProvider context;
	

	private EnvioMensajes() {
		
		// TODO Auto-generated constructor stub
	}
	
	public static EnvioMensajes getInstance(){
		if(envioMensajes==null){
			envioMensajes = new EnvioMensajes();
		}
		
		return envioMensajes;
	}
	
	
	private void initializeEnvironment() {
		try {
			PropertiesServices ps = new PropertiesServices(getContext());
			if (peticion.getUsuario()==null && "".equals(peticion.getUsuario())){
				peticion.setUsuario(ps.getMessage("usuario", null, null, null));
			}
			if (peticion.getPassword()==null && "".equals(peticion.getPassword())){
				peticion.setPassword(ps.getMessage("password", null, null, null));
			}
			if (peticion.getCodOrganismo()==null && "".equals(peticion.getCodOrganismo())){
				peticion.setCodOrganismo(ps.getMessage("codOrganismo", null, null, null));
			}
			if (peticion.getCodOrganismoPagadorSMS()==null && "".equals(peticion.getCodOrganismoPagadorSMS())){
				peticion.setCodOrganismoPagadorSMS(ps.getMessage("codOrganismoPagadorSMS", null, null, null));
			}
			if (peticion.getCodSia()==null && "".equals(peticion.getCodSia())){
				peticion.setCodSia(ps.getMessage("codSia", null, null, null));
			}
			if (peticion.getNombreLote()==null && "".equals(peticion.getNombreLote())){
				peticion.setNombreLote(ps.getMessage("nombreLote", null, null, null));
			}
			if (peticion.getServicio()==null && "".equals(peticion.getServicio())){
				peticion.setServicio(ps.getMessage("servicio", null, null, null));
			}
		} catch(Exception e) {
			LOGGER.error("EnvioMensajes - initializeEnvironment: ",e);
		}
	}

	public Respuesta sendMessage(EnvioMensajesServiceWSBindingPortType port) {
		LOGGER.info("Invoking enviarMensaje...");
        Respuesta respuesta = port.enviarMensaje(this.peticion);
        LOGGER.info("enviarMensaje.result=" + respuesta.getStatus().getDetails());
        return respuesta;
	}


	/**
	 * @return the peticion
	 */
	public Peticion getPeticion() {
		return peticion;
	}

	/**
	 * @param peticion the peticion to set
	 */
	public void setPeticion(Peticion peticion) {
		this.peticion = peticion;
		initializeEnvironment();
	}
	
	/**
	 * @return the context
	 */
	public ApplicationContextProvider getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(ApplicationContextProvider context) {
		this.context = context;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*EnvioMensajes envio = EnvioMensajes.getInstance();
		
		// Creación de la peticion
		Peticion peticion = new Peticion();
		Mensajes mensajes = new Mensajes();
		
		MensajeEmail mensajeEmail = new MensajeEmail();
		mensajeEmail.setAsunto("Pruebas");
		mensajeEmail.setCuerpo("Buenos días, Esta es una breve descripcion del cuerpo del mensaje");
		mensajeEmail.setModo("0");
		mensajeEmail.setOrigen("origen");
		
		Destinatarios  destinatarios = new Destinatarios();
		destinatarios.setTo("francisco.javier.escobar.fernandez@everis.com");
		destinatarios.setBcc("frankj.escobar@mail.com");
		destinatarios.setCC("francisco@harakirimail.com");
		
		DestinatarioMail  destinatarioMail = new DestinatarioMail();
		destinatarioMail.setIdExterno("IdExterno");
		destinatarioMail.setDocUsuario("Docusuario");
		destinatarioMail.setDestinatarios(destinatarios);
		
		DestinatariosMail destinatariosMail = new DestinatariosMail();
		destinatariosMail.getDestinatarioMail().add(destinatarioMail);
		mensajeEmail.setDestinatariosMail(destinatariosMail);
		
		mensajes.getMensajeEmail().add(mensajeEmail);
		peticion.setMensajes(mensajes);
		ApplicationContextProvider context = ApplicationContextProvider.getInstance();
		envio.setContext(context);
		envio.setPeticion(peticion);
		Respuesta respuesta = envio.sendMessage();
		LOGGER.info("Respuesta : " + respuesta);*/
	}

}
