package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.ContactoBean;
import es.mpr.plataformamensajeria.beans.UsuarioBean;



/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de contactos</p>.
 *
 * @author Selered
 */

/**
 * @author srealarq
 *
 */
public interface ServicioContacto {

	/**
	 * Obtener contactos.
	 *
	 * @param start the start
	 * @param size the size
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @return contactos
	 * @throws BusinessException the business exception
	 */
	PaginatedList<ContactoBean> getContactos(int start, int size, String order, String columnSort,ContactoBean criterio) 
		throws BusinessException;
	
	/**
	 * New contactos. 
	 *
	 * @param usuario the contactos
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @return the integer
	 * @throws BusinessException the business exception
	 */

	Long newContacto(ContactoBean contactos, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Update contactos.
	 *
	 * @param usuario the contactos
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void updateContacto(ContactoBean contactos, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Load contactos.
	 *
	 * @param usuario the usuario
	 * @return the usuario bean
	 * @throws BusinessException the business exception
	 */
	ContactoBean loadContacto(ContactoBean contactos)throws BusinessException;
	
	/**
	 * Delete contactos.
	 *
	 * @param usuario the contactos
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void deleteContacto(Long contactoId, String source, String accion, Long accionId)throws BusinessException;


}
