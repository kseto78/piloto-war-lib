package es.minhap.misim.bus.webapp.restserviceprovider;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;
/**
 * Clase interfaz RegistroUsuario
 * @author ralberoc
 *
 */
@Path("/")
@WebService(name="registroUsuario")
@Scope(value="request")
public interface RegistroUsuario {
	/**
	 * Metodo que implementa la logica de la peticion de registro de usuarios
	 * @param map
	 * @return
	 */
	@GET
    @Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)  
	String registrarUsuario(@QueryParam("Servicio") String servicio,
							   @QueryParam("IdUsuario") String idUsuario,
							   @QueryParam("Plataforma") String plataforma,
							   @QueryParam("IdRegistro") String idRegistro,
							   @QueryParam("IdDispositivo") String idDispositivo);	
	
	
}
