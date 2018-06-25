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
 * Clase interfaz WS_ClaveAuthRequest
 * @author ralberoc
 *
 */
@Path("/")
@WebService(name="WS_ClaveAuthRequest")
@Scope(value="request")
public interface WS_ClaveAuthRequest {
	/**
	 * Metodo que implementa la logica de la peticion SAMLRequest 
	 * @param map
	 * @return
	 */
	@POST
    @Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)  
	String getSAMLRequest(MultivaluedMap<String,String>map);	
}
