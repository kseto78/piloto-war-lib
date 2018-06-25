package es.minhap.misim.bus.model.servicios;

import java.util.List;

import es.minhap.misim.bus.model.Peticion;
import es.minhap.misim.bus.query.PeticionQuery;



public interface PeticionManager {
		
	/**
	 * Guarda un elemento peticion. Devuelve su identificador en caso de exito o null si hay error.
	 * @param peticion
	 * @return
	 */
	Long guardarPeticion(Peticion peticion);
	
	
	/**
	 * Inserta un elemento peticion. Devuelve su identificador en caso de exito o null si hay error.
	 * @param peticion
	 * @return
	 */	
	Long insertPeticion(Peticion peticion);
	
	/**
	 * Actualiza un elemento peticion. Devuelve true en caso de exito y false en caso de error.
	 * @param peticion
	 * @return
	 */
	boolean updatePeticion(Peticion peticion);
	
	/**
	 * Obtiene un elemento peticion
	 * @param idPeticion
	 * @return
	 */
	Peticion getPeticionById(Long idPeticion);
	
	/**
	 * Elimina un elemento peticion
	 * @param id
	 * @return
	 */
	void eliminarPeticion(Long id);
	
	/**
	 * Obtiene una lista de peticion.
	 * @param query
	 * @return
	 */	
	List<Peticion> getPeticion(PeticionQuery query);
	
}
