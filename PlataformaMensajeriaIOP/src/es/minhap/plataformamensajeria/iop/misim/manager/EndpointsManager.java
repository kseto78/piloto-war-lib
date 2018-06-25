package es.minhap.plataformamensajeria.iop.misim.manager;

import java.util.List;

import es.minhap.misim.bus.model.Endpoint;
import es.minhap.misim.bus.query.EndpointQuery;

public interface EndpointsManager {

	/**
	 * recupera la aplicacion según datos pasados
	 * 
	 * @param TblAplicacionesQuery
	 * @return
	 */
	public List<Endpoint> getEndpoints(EndpointQuery endpoint);

	List<Endpoint> getEndpointsOrdenados();

	/**
	 * Insertamos un nuevo servicio
	 *
	 * @param servicio
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return
	 */
	public Long insert(Endpoint endpoint, String source, String accion, Long accionId);

	/**
	 * Actualizamos el endpoint
	 *
	 * @param endpoint
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return
	 */

	void update(Endpoint endpoint, String source, String accion, Long accionId);

	Endpoint getEndpoint(Long idEndpoint);
}
