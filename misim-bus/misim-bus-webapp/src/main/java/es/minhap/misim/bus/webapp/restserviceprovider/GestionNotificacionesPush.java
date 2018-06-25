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
 * Clase interfaz GestionNotificacionesPush
 * @author ralberoc
 *
 */
@Path("/")
@WebService(name="gestionNotificacionesPush")
@Scope(value="request")
public interface GestionNotificacionesPush {
	/**
	 * Metodo que implementa la logica de la peticion de notificacion estado
	 * @param idMensaje
	 * @param status
	 * @param idUsuario
	 * @return
	 */
	@GET
    @Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)  
	String notificacionEstado( @QueryParam("notificacionId") String idMensaje,
							   @QueryParam("status") String status,
							   @QueryParam("idUsuario") String idUsuario);	
	
	
}
