package es.minhap.misim.bus.webapp.restserviceprovider;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;

@Path("/")
@WebService(name="gestionAyuda")
@Scope(value="request")
public interface GestionAyuda {

	@GET
    @Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)  
	String solicitudAyuda();	
	
	
}
