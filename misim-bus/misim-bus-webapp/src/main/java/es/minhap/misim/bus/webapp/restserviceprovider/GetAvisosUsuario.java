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
 * Clase interfaz GetAvisosUsuario
 * @author ralberoc
 *
 */
@Path("/")
@WebService(name="getAvisosUsuario")
@Scope(value="request")
public interface GetAvisosUsuario {
	/**
	 * Metodo que implementa la logica de la peticion de avisos a usuarios
	 * @param map
	 * @return
	 */
	@POST
    @Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)  
	String getAvisosUsuario(MultivaluedMap<String,String>map);	

}
