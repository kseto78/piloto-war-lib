package es.minhap.misim.bus.model.servicios;

import java.util.List;

import es.minhap.misim.bus.model.Endpoint;
import es.minhap.misim.bus.query.EndpointQuery;



public interface EndpointManager {
		
	/**
	 * Guarda un elemento endpoint. Devuelve su identificador en caso de exito o null si hay error.
	 * @param endpoint
	 * @return
	 */
	Long guardarEndpoint(Endpoint endpoint);
	
	
	/**
	 * Inserta un elemento endpoint. Devuelve su identificador en caso de exito o null si hay error.
	 * @param endpoint
	 * @return
	 */	
	Long insertEndpoint(Endpoint endpoint);
	
	/**
	 * Actualiza un elemento endpoint. Devuelve true en caso de exito y false en caso de error.
	 * @param endpoint
	 * @return
	 */
	boolean updateEndpoint(Endpoint endpoint);
	
	/**
	 * Obtiene un elemento endpoint
	 * @param idEndpoint
	 * @return
	 */
	Endpoint getEndpointById(Long idEndpoint);
	
	/**
	 * Elimina un elemento endpoint
	 * @param id
	 * @return
	 */
	void eliminarEndpoint(Long id);
	
	/**
	 * Obtiene una lista de endpoint.
	 * @param query
	 * @return
	 */	
	List<Endpoint> getEndpoint(EndpointQuery query);
	
}
