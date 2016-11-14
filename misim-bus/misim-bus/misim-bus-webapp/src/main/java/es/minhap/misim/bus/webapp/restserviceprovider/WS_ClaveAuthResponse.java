package es.minhap.misim.bus.webapp.restserviceprovider;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;

@Path("/")
@WebService(name="WS_ClaveAuthResponse")
@Scope(value="request")
public interface WS_ClaveAuthResponse {

	@POST
    @Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)  
	String getSAMLResponse( String msg);	
}
