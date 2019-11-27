package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.plataformamensajeria.iop.beans.ContactoBean;
import es.minhap.sim.model.TblContactos;
import es.minhap.sim.model.ViewContactos;
import es.minhap.sim.query.TblContactosQuery;



public interface TblContactosManager {

	
	/**
	 * recupera la aplicacion según datos pasados
	 * 
	 * @param TblAplicacionesQuery
	 * @return
	 */
	public List<TblContactos> getContactos(TblContactosQuery auditoria);
	
	/**
	 * obtiene el idUsuario según el id
	 * 
	 * @param usuarioid
	 * @return 
	 */
	public TblContactos getById(Long contactoId);


	/**
	 * Inserta el organismo según datos pasados
	 * 
	 * @param usuarioTO
	 * @param source
	 * @param accion
	 * @param accionId
	 * @return Long
	 */
	public Long insert(TblContactos usuarioTO, String source, String accion, Long accionId);


	/**
	 * Actualiza el usuario según datos pasados
	 * 
	 * @param usuarioTO
	 * @param source
	 * @param accion
	 * @param accionId
	 */
	public void update(TblContactos contacto, String source, String accion, Long accionId);

	/**
	 * Elimina el usuario según datos pasados
	 * 
	 * @param usuario
	 * @param source
	 * @param accion
	 * @param accionId
	 */
	public void delete(Long contactoid, String source, String accion, Long accionId);


	/**
	 * recupera el servidor según datos pasados
	 * 
	 * @param start
	 * @param size
	 * @param order
	 * @param columnSort
	 * @param idAplicacion
	 * @return List<TblAplicaciones>
	 */
	public List<ViewContactos> getContactosPaginado(int start, int size, String order, String columnSort, ContactoBean criterio);

}
