/**
 * 
 */
package es.minhap.misim.misim_bus_webapp.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.common.spring.ApplicationContextProvider;
import es.redsara.misim.misim_bus_webapp.OperacionMensajes;
import es.redsara.misim.misim_bus_webapp.OperacionMensajesServicePortType;
import es.redsara.misim.misim_bus_webapp.peticionOperacion.Mensaje;
import es.redsara.misim.misim_bus_webapp.peticionOperacion.PeticionLote;
import es.redsara.misim.misim_bus_webapp.peticionOperacion.PeticionMensaje;
import es.redsara.misim.misim_bus_webapp.respuestaOperacion.RespuestaOperacion;


/**
 * @author everis
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { ModelTestUtil.SPRING_CONTEXT_LOCATION })
public class OperacionesMensajesTest extends AbstractJUnit38SpringContextTests{

	@Resource(name = "operacionMensajesService")
	OperacionMensajesServicePortType operacionMensajesService;
	
	private ApplicationContextProvider context;
	
	private PropertiesServices ps;
	
	
	
	@Test
	public final void testOperacioneMensajes() {
		
		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);
		reenviarMensaje();
		anularMensaje();
		reenviarLote();
		anularLote();
	}
	
	private void reenviarMensaje() {
		/** Envio peticion de estado ***/
		OperacionMensajes operacion = OperacionMensajes.getInstance();
		PeticionMensaje peticionMensaje = new PeticionMensaje();
		Mensaje mensaje = new Mensaje();
		mensaje.setIdExterno("IdExterno");
		mensaje.setIdMensaje(277323);
		peticionMensaje.setMensaje(mensaje);
		operacion.setContext(this.context);
		operacion.setPeticionMensaje(peticionMensaje);
		RespuestaOperacion respuestaOperacion = operacion.sendReenviarMensajeRequest(operacionMensajesService);
		System.out.println("Respuesta : " + respuestaOperacion.getStatus().getDetails());
	}
	private void anularMensaje() {
		/** Envio peticion de estado ***/
		OperacionMensajes operacion = OperacionMensajes.getInstance();
		PeticionMensaje peticionMensaje = new PeticionMensaje();
		Mensaje mensaje = new Mensaje();
		mensaje.setIdExterno("IdExterno");
		mensaje.setIdMensaje(277323);
		peticionMensaje.setMensaje(mensaje);
		operacion.setContext(this.context);
		operacion.setPeticionMensaje(peticionMensaje);
		RespuestaOperacion respuestaOperacion = operacion.sendAnularMensajeRequest(operacionMensajesService);
		System.out.println("Respuesta : " + respuestaOperacion.getStatus().getDetails());
	}
	
	private void reenviarLote() {
		/** Envio peticion de estado ***/
		OperacionMensajes operacion = OperacionMensajes.getInstance();
		PeticionLote peticionLote = new PeticionLote();
		peticionLote.setLote("32517");
		operacion.setContext(this.context);
		operacion.setPeticionLote(peticionLote);
		RespuestaOperacion respuestaOperacion = operacion.sendReenviarLoteRequest(operacionMensajesService);
		System.out.println("Respuesta : " + respuestaOperacion.getStatus().getDetails());
	}
	
	private void anularLote() {
		/** Envio peticion de estado ***/
		OperacionMensajes operacion = OperacionMensajes.getInstance();
		PeticionLote peticionLote = new PeticionLote();
		peticionLote.setLote("32517");
		operacion.setContext(this.context);
		operacion.setPeticionLote(peticionLote);
		RespuestaOperacion respuestaOperacion = operacion.sendAnularLoteRequest(operacionMensajesService);
		System.out.println("Respuesta : " + respuestaOperacion.getStatus().getDetails());
	}
}
