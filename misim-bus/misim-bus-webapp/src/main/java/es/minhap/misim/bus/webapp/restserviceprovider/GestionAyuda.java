package es.minhap.misim.bus.webapp.restserviceprovider;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;
/**
 * Clase Interfaz GestionAyuda
 * @author ralberoc
 *
 */
@Path("/")
@WebService(name="gestionAyuda")
@Scope(value="request")
public interface GestionAyuda {
	/**
	 * Procesa la peticion de solicitud ayuda
	 * @return
	 */
	@POST
    @Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)  
	String solicitudAyuda();	
		
}
