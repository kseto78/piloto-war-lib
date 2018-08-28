/**
 * 
 */
package es.minhap.misim.misim_bus_webapp.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.redsara.misim.misim_bus_webapp.RegUsuario;
import es.redsara.misim.misim_bus_webapp.peticion.PeticionRegistroUsuarioWebPush;
import es.redsara.misim.misim_bus_webapp.respuesta.RespuestaRegistroWebPush;


/**
 * @author everis
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { ModelTestUtil.SPRING_CONTEXT_LOCATION })
public class EnvioMensajesTest extends AbstractJUnit38SpringContextTests{

	
	
	
	@Test
	public final static void testEnvioMensaje() {
		
		String endpoint = "https://des-misim.redsara.es/misim-bus-webapp/rest/gestionServiciosPush/registroUsuarioWebPush";
		PeticionRegistroUsuarioWebPush peticion = new PeticionRegistroUsuarioWebPush();
		
		peticion.setAccion("A");
		peticion.setAuth("E+V+ZP78JcrbCUvgF/6XeA==");
		peticion.setEndpoint("https://updates.push.services.mozilla.com/wpush/v2/gAAAAABZ07ZsLd-GIcEh1WbJevBoWys7nwBSOpZcqpVtjvDPvnP-eo01_rxFgo2uHMyf5S-wd9mWFlmf8R5KU5HLn7fhzDEsR6botnDv0uPKdrM1DRe5gshKRQt_hyhBVQv-UwZLU-90JZ7QKdHViEsle2JzaJ4rm3AfnB70PgYuEQvhawKb_g8");
		peticion.setIdServicio("2502");
		peticion.setIdUsuario("55");
		peticion.setKey("BKzIymT4M/ew/38khjkK5zBvEnYLDwonUHZWw6EAW0xp4LeqXEn5xERBMx3qsHIdNYMLAfWafilUCoJvq9lAoAE=");
		peticion.setUsuario("pruebasSIMdes");
		peticion.setPassword("pruebasSIMdes");
		
		RespuestaRegistroWebPush res = new RespuestaRegistroWebPush();
				
		try{
			RegUsuario envioMensajes = RegUsuario.getNewInstance(endpoint, peticion);
						
			RespuestaRegistroWebPush respuesta = envioMensajes.sendMessage();
			
		}catch(Exception e){
			System.out.println("Respuesta: " );
			generarRespuesta(e.getMessage());
		}
		
//		return res;
	}

	
	
	private static void generarRespuesta(String message) {
		// TODO Auto-generated method stub
		
	}



}
