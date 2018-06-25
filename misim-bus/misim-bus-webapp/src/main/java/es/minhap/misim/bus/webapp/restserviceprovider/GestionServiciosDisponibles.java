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
 * Clase interfaz GestionServiciosDisponibles
 * @author ralberoc
 *
 */
@Path("/")
@WebService(name="consultaServiciosDisponibles")
@Scope(value="request")
public interface GestionServiciosDisponibles {
	/**
	 * Metodo que implementa la logica de la peticion de servicios disponibles
	 * @param map
	 * @return
	 */
	@POST
    @Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)  
	String getServiciosDisponibles(MultivaluedMap<String,String>map);	
}
