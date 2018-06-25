package es.minhap.misim.bus.model.servicios;

import java.util.List;

import es.minhap.misim.bus.model.Auditoria;
import es.minhap.misim.bus.query.AuditoriaQuery;



public interface AuditoriaManager {
		
	/**
	 * Guarda un elemento auditoria. Devuelve su identificador en caso de exito o null si hay error.
	 * @param auditoria
	 * @return
	 */
	Long guardarAuditoria(Auditoria auditoria);
	
	
	/**
	 * Inserta un elemento auditoria. Devuelve su identificador en caso de exito o null si hay error.
	 * @param auditoria
	 * @return
	 */	
	Long insertAuditoria(Auditoria auditoria);
	
	/**
	 * Actualiza un elemento auditoria. Devuelve true en caso de exito y false en caso de error.
	 * @param auditoria
	 * @return
	 */
	boolean updateAuditoria(Auditoria auditoria);
	
	/**
	 * Obtiene un elemento auditoria
	 * @param idAuditoria
	 * @return
	 */
	Auditoria getAuditoriaById(Long idAuditoria);
	
	/**
	 * Elimina un elemento auditoria
	 * @param id
	 * @return
	 */
	void eliminarAuditoria(Long id);
	
	/**
	 * Obtiene una lista de auditoria.
	 * @param query
	 * @return
	 */	
	List<Auditoria> getAuditoria(AuditoriaQuery query);
	
}
