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
@WebService(name="registroUsuarioEnServicio")
@Scope(value="request")
public interface GestionServiciosPush {

	@GET
    @Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)  
	String registroUsuarioEnServicio(@QueryParam("IdUsuario") String idUsuario,
							   @QueryParam("IdServicioMovil") String idServicioMovil,
							   @QueryParam("accion") String accion,
							   @QueryParam("IdDispositivo") String idDispositivo);	
	
}
