package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.PeticionActualizarPasswordCorreo;
import es.minhap.plataformamensajeria.iop.beans.RespuestaActualizarPasswordCorreo;
import es.minhap.sim.model.TblParametrosServidor;
import es.minhap.sim.query.TblParametrosServidorQuery;


public interface TblParametrosServidorManager {

	/**
	 * Obtiene una lista con los datos de patametrosServidor
	 * 
	 * @return List<TblParametrosServidor>
	 */
	public List<TblParametrosServidor> getAll();
	
	/**
	 * Inserta en la tabla parametrosServidor
	 * @param parametroServidor
	 * @param source
	 * @param accion
	 * @param accionId
	 * @param descricpion
	 * @return 
	 */
	public Long insert(TblParametrosServidor parametroServidor, String source, String accion, Long accionId, String descripcion);
	
	/**
	 * borra en la tabla parametrosServidor
	 * @param parametroServidorId
	 * @param source
	 * @param accion
	 * @param accionId
	 * @param descripcion
	 */
	public void delete(Long parametroServidorId, String source, String accion, Long accionId, String descripcion);
	
	/**
	 * obtiene el valor de la tabla por criterios
	 * @param servidorId
	 * @param tipoParametroId
	 * @return List<TblParametrosServidor>
	 */
	public List<TblParametrosServidor> getParametrosPorServidor(Long servidorId, Long tipoParametroId);
	
	/**
	 * comprueba si existe el valor con el tipo de parametro
	 * @param usuario
	 * @param ps
	 * @return RespuestaActualizarPasswordCorreo 
	 */
	public RespuestaActualizarPasswordCorreo checkActualizarPassword(PeticionActualizarPasswordCorreo peticion,
			PropertiesServices ps);
	
	/**
	 * obtiene el valor de la tabla por criterios
	 * @param query
	 * @return List<TblParametrosServidor>
	 */
	public List<TblParametrosServidor> getByQuery(TblParametrosServidorQuery query);
}
