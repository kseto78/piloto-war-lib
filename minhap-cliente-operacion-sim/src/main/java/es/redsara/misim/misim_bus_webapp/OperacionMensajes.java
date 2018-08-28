package es.redsara.misim.misim_bus_webapp;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.common.spring.ApplicationContextProvider;
import es.redsara.misim.misim_bus_webapp.peticionOperacion.PeticionLote;
import es.redsara.misim.misim_bus_webapp.peticionOperacion.PeticionMensaje;
import es.redsara.misim.misim_bus_webapp.respuestaOperacion.RespuestaOperacion;

/**
 * @author everis
 *
 */
public class OperacionMensajes {
	
	private static final Logger LOGGER = Logger.getLogger(OperacionMensajes.class);
	
	private static OperacionMensajes operacionMensaje;
	
	@Resource
	private OperacionMensajesServicePortType_OperacionMensajesServicePort_Client operacionMensajesClient;

		
	private PeticionLote peticionLote = new PeticionLote();
	
	private PeticionMensaje peticionMensaje = new PeticionMensaje();
	
	private ApplicationContextProvider context;
	
	private OperacionMensajes() {
		// TODO Auto-generated constructor stub
	}
	
	public static OperacionMensajes getInstance(){
		if(operacionMensaje==null){
			operacionMensaje = new OperacionMensajes();
		}
		return operacionMensaje;
	}
	
	
	private void initializeEnvironmentMensaje() {
		try {
			PropertiesServices ps = new PropertiesServices(getContext());
		    peticionMensaje.setUsuario(ps.getMessage("usuario", null, null, null));
		    peticionMensaje.setPassword(ps.getMessage("password", null, null, null));
		} catch(Exception e) {
			LOGGER.error("Se ha producido un error al cargar las propiedades de configuracion", e);
			System.exit(0);
		}
	}
	
	private void initializeEnvironmentLote() {
		try {
			PropertiesServices ps = new PropertiesServices(getContext());
		    peticionLote.setUsuario(ps.getMessage("usuario", null, null, null));
		    peticionLote.setPassword(ps.getMessage("password", null, null, null));
		} catch(Exception e) {
			LOGGER.error("Se ha producido un error al cargar las propiedades de configuracion", e);
			System.exit(0);
		}
	}


	public RespuestaOperacion sendAnularLoteRequest(OperacionMensajesServicePortType port) {
        LOGGER.info("Invoking sendAnularLoteRequest...");
        RespuestaOperacion respuesta = port.anularLote(this.peticionLote);
        LOGGER.info("sendAnularLoteRequest.result=" + respuesta.getStatus().getDetails());
        return respuesta;
	}

	public RespuestaOperacion sendReenviarLoteRequest(OperacionMensajesServicePortType port) {
        LOGGER.info("Invoking sendReenviarLoteRequest...");
        RespuestaOperacion respuesta = port.reenviarLote(this.peticionLote);
        LOGGER.info("sendReenviarLoteRequest.result=" + respuesta.getStatus().getDetails());
        return respuesta;
	}
	
	public RespuestaOperacion sendAnularMensajeRequest(OperacionMensajesServicePortType port) {
        LOGGER.info("Invoking sendAnularMensajeRequest...");
        RespuestaOperacion respuesta = port.anularMensaje(this.peticionMensaje);
        LOGGER.info("sendAnularMensajeRequest.result=" + respuesta.getStatus().getDetails());
        return respuesta;
	}

	public RespuestaOperacion sendReenviarMensajeRequest(OperacionMensajesServicePortType port) {
        LOGGER.info("Invoking sendReenviarMensajeRequest...");
        RespuestaOperacion respuesta = port.reenviarMensaje(this.peticionMensaje);
        LOGGER.info("sendReenviarMensajeRequest.result=" + respuesta.getStatus().getDetails());
        return respuesta;
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
	 * @return the peticionLote
	 */
	public PeticionLote getPeticionLote() {
		return peticionLote;
	}

	/**
	 * @param peticionLote the peticionLote to set
	 */
	public void setPeticionLote(PeticionLote peticionLote) {
		this.peticionLote = peticionLote;
		initializeEnvironmentLote();
	}

	/**
	 * @return the peticionMensaje
	 */
	public PeticionMensaje getPeticionMensaje() {
		return peticionMensaje;
	}

	/**
	 * @param peticionMensaje the peticionMensaje to set
	 */
	public void setPeticionMensaje(PeticionMensaje peticionMensaje) {
		this.peticionMensaje = peticionMensaje;
		initializeEnvironmentMensaje();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
