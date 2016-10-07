package es.minhap.misim.bus.model.servicios;

import java.util.List;

import es.minhap.misim.bus.model.Comunicacion;
import es.minhap.misim.bus.query.ComunicacionQuery;



public interface ComunicacionManager {
		
	/**
	 * Guarda un elemento comunicacion. Devuelve su identificador en caso de exito o null si hay error.
	 * @param comunicacion
	 * @return
	 */
	Long guardarComunicacion(Comunicacion comunicacion);
	
	
	/**
	 * Inserta un elemento comunicacion. Devuelve su identificador en caso de exito o null si hay error.
	 * @param comunicacion
	 * @return
	 */	
	Long insertComunicacion(Comunicacion comunicacion);
	
	/**
	 * Actualiza un elemento comunicacion. Devuelve true en caso de exito y false en caso de error.
	 * @param comunicacion
	 * @return
	 */
	boolean updateComunicacion(Comunicacion comunicacion);
	
	/**
	 * Obtiene un elemento comunicacion
	 * @param idComunicacion
	 * @return
	 */
	Comunicacion getComunicacionById(Long idComunicacion);
	
	/**
	 * Elimina un elemento comunicacion
	 * @param id
	 * @return
	 */
	void eliminarComunicacion(Long id);
	
	/**
	 * Obtiene una lista de comunicacion.
	 * @param query
	 * @return
	 */	
	List<Comunicacion> getComunicacion(ComunicacionQuery query);
	
}
