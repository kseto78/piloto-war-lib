package es.minhap.misim.bus.model.servicios;

import java.util.List;

import es.minhap.misim.bus.model.Transformacion;
import es.minhap.misim.bus.query.TransformacionQuery;



public interface TransformacionManager {
		
	/**
	 * Guarda un elemento transformacion. Devuelve su identificador en caso de exito o null si hay error.
	 * @param transformacion
	 * @return
	 */
	Long guardarTransformacion(Transformacion transformacion);
	
	
	/**
	 * Inserta un elemento transformacion. Devuelve su identificador en caso de exito o null si hay error.
	 * @param transformacion
	 * @return
	 */	
	Long insertTransformacion(Transformacion transformacion);
	
	/**
	 * Actualiza un elemento transformacion. Devuelve true en caso de exito y false en caso de error.
	 * @param transformacion
	 * @return
	 */
	boolean updateTransformacion(Transformacion transformacion);
	
	/**
	 * Obtiene un elemento transformacion
	 * @param idTransformacion
	 * @return
	 */
	Transformacion getTransformacionById(Long idTransformacion);
	
	/**
	 * Elimina un elemento transformacion
	 * @param id
	 * @return
	 */
	void eliminarTransformacion(Long id);
	
	/**
	 * Obtiene una lista de transformacion.
	 * @param query
	 * @return
	 */	
	List<Transformacion> getTransformacion(TransformacionQuery query);
	
}
