package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.query.TblAplicacionesQuery;

public interface TblAplicacionesManager {

	/**
	 * recupera la aplicacion según datos pasados
	 * 
	 * @param TblAplicacionesQuery
	 * @return
	 */
	List<TblAplicaciones> getAplicaciones(TblAplicacionesQuery auditoria);
	
	/**
	 * recupera la aplicacion según datos pasados
	 * 
	 * @param TblAplicacionesQuery
	 * @return TblAplicaciones
	 */
	TblAplicaciones getAplicacion(TblAplicacionesQuery query);

	
	/**
	 * comprueba si existe la aplicacion
	 * 
	 * @param usuario
	 * @param password
	 * @return Boolean
	 */
	Boolean existeAplicacion(String usuario, String password);
	
	
	/**
	 * comprueba si existe la aplicacion para el usuario
	 * 
	 * @param usuario
	 * @return Boolean
	 */
	Boolean existeAplicacionUsuario(String usuario);

	/**
	 * recupera la aplicacion según datos pasados
	 * 
	 * @param idAplicacion
	 * @return
	 */
	TblAplicaciones getAplicacion(Long idAplicacion);
	
	/**
	 * recupera el servidor según datos pasados
	 * 
	 * @param start
	 * @param size
	 * @param order
	 * @param columnSort
	 * @param nombreAplicacion
	 * @param idAplicacion
	 * @return List<TblAplicaciones>
	 */
	List<TblAplicaciones> getAplicacionesPaginado(int start, int size, String order, String columnSort, String nombreAplicacion, int idAplicacion);
	
	/**
	 * actualizamos la aplicacion
	 *
	 * @param servidor
	 * @param source
	 * @param accion
	 * @param accionId
	 */
	void update(TblAplicaciones aplicacion, String source, String accion, Long accionId);

	
	/**
	 * Insertamos una nueva aplicacion
	 *
	 * @param servidor
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return
	 */
	Long insert(TblAplicaciones aplicacion, String source, String accion, Long accionId);
	
	
	/**
	 * recupera todas las aplicaciones no eliminadas ordenadas por nombre
	 * @return
	 */
	List<TblAplicaciones> getAplicacionesNoEliminadasOrdenadas();

	void updateSMS(TblAplicaciones aplicacion);


}
