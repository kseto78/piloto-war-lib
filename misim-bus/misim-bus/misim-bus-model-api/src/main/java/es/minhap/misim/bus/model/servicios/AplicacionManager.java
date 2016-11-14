package es.minhap.misim.bus.model.servicios;

import java.util.List;

import es.minhap.misim.bus.model.Aplicacion;
import es.minhap.misim.bus.query.AplicacionQuery;



public interface AplicacionManager {
		
	/**
	 * Guarda un elemento aplicacion. Devuelve su identificador en caso de exito o null si hay error.
	 * @param aplicacion
	 * @return
	 */
	Long guardarAplicacion(Aplicacion aplicacion);
	
	
	/**
	 * Inserta un elemento aplicacion. Devuelve su identificador en caso de exito o null si hay error.
	 * @param aplicacion
	 * @return
	 */	
	Long insertAplicacion(Aplicacion aplicacion);
	
	/**
	 * Actualiza un elemento aplicacion. Devuelve true en caso de exito y false en caso de error.
	 * @param aplicacion
	 * @return
	 */
	boolean updateAplicacion(Aplicacion aplicacion);
	
	/**
	 * Obtiene un elemento aplicacion
	 * @param idAplicacion
	 * @return
	 */
	Aplicacion getAplicacionById(Long idAplicacion);
	
	/**
	 * Elimina un elemento aplicacion
	 * @param id
	 * @return
	 */
	void eliminarAplicacion(Long id);
	
	/**
	 * Obtiene una lista de aplicacion.
	 * @param query
	 * @return
	 */	
	List<Aplicacion> getAplicacion(AplicacionQuery query);
	
}
