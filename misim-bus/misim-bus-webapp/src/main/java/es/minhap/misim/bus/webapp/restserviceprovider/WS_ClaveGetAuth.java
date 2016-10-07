package es.minhap.misim.bus.webapp.restserviceprovider;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;

@Path("/")
@WebService(name="WS_ClaveGetAuth")
@Scope(value="request")
public interface WS_ClaveGetAuth {

	@GET
    @Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)  
	String getAuth( @QueryParam("Servicio") String idServicio ,
			@QueryParam("Plataforma") String idPlataforma,
			@QueryParam("IdDispositivo") String idDispositivo);	
}
