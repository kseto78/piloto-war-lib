package es.minhap.misim.bus.model.servicios;

import java.util.List;

import es.minhap.misim.bus.model.Estado;
import es.minhap.misim.bus.query.EstadoQuery;



public interface EstadoManager {
		
	/**
	 * Guarda un elemento estado. Devuelve su identificador en caso de exito o null si hay error.
	 * @param estado
	 * @return
	 */
	Long guardarEstado(Estado estado);
	
	
	/**
	 * Inserta un elemento estado. Devuelve su identificador en caso de exito o null si hay error.
	 * @param estado
	 * @return
	 */	
	Long insertEstado(Estado estado);
	
	/**
	 * Actualiza un elemento estado. Devuelve true en caso de exito y false en caso de error.
	 * @param estado
	 * @return
	 */
	boolean updateEstado(Estado estado);
	
	/**
	 * Obtiene un elemento estado
	 * @param idEstado
	 * @return
	 */
	Estado getEstadoById(Long idEstado);
	
	/**
	 * Elimina un elemento estado
	 * @param id
	 * @return
	 */
	void eliminarEstado(Long id);
	
	/**
	 * Obtiene una lista de estado.
	 * @param query
	 * @return
	 */	
	List<Estado> getEstado(EstadoQuery query);
	
}
