package es.minhap.misim.bus.webapp.restserviceprovider;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.springframework.context.annotation.Scope;
/**
 * Clase interfaz ConfirmarAltaUsuario
 * @author ralberoc
 *
 */
@Path("/")
@WebService(name="confirmarAltaUsuario")
@Scope(value="request")
public interface ConfirmarAltaUsuario {
	/**
	 * Metodo que confirma el alta de un usuario de la aplicacion
	 * @param map
	 * @return
	 */
	@POST
    @Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)  
	String confirmarAltaUsuario(MultivaluedMap<String,String>map);	
	
}
