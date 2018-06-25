package es.minhap.misim.bus.webapp.restserviceprovider;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;
/**
 * Clase interfaz RegistroUsuarioWebPush
 * @author ralberoc
 *
 */
@Path("/")
@WebService(name="registroUsuarioWebPush")
@Scope(value="request")

public interface RegistroUsuarioWebPush {
	/**
	 * Metodo que implementa la logica de la peticion de registro de usuarios Web Push
	 * @param json
	 * @return
	 */
	@POST
    @Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)  
	String registrarUsuarioWebPush(String json);	
	
}
