package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.sim.model.TblServiciosMoviles;
import es.mpr.plataformamensajeria.beans.ServicioMovilBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de servicios moviles</p>.
 *
 * @author Selered
 */

public interface ServicioServicioMovil {
	
	/**
	 * Obtener servicios moviles.
	 *
	 * @param criterio the criterio
	 * @return servicios moviles
	 * @throws BusinessException the business exception
	 */
	List<TblServiciosMoviles> getServiciosMoviles(ServicioMovilBean criterio) throws BusinessException;
	
	/**
	 * Obtener servicios moviles.
	 *
	 * @return servicios moviles
	 * @throws BusinessException the business exception
	 */
	List<TblServiciosMoviles> getServiciosMoviles() throws BusinessException;
	
	/**
	 * Obtener servicios moviles.
	 *
	 * @param start the start
	 * @param size the size
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @return servicios moviles
	 * @throws BusinessException the business exception
	 */
	PaginatedList<ServicioMovilBean> getServiciosMoviles(int start, int size, String order, String columnSort,ServicioMovilBean criterio) 
		throws BusinessException;
	
	/**
	 * Load servicio movil.
	 *
	 * @param servicioMovil the servicio movil
	 * @return the servicio movil bean
	 * @throws BusinessException the business exception
	 */
	ServicioMovilBean loadServicioMovil(ServicioMovilBean servicioMovil)throws BusinessException;
	
	/**
	 * New servicio movil.
	 *
	 * @param servicio the servicio
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @return the long
	 * @throws BusinessException the business exception
	 */
	Long newServicioMovil(ServicioMovilBean servicio, String source, String accion, Long accionId) throws BusinessException;
	
	/**
	 * Update servicio movil.
	 *
	 * @param servicioMovil the servicio movil
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void updateServicioMovil(ServicioMovilBean servicioMovil, String source, String accion, Long accionId) throws BusinessException;
	
	/**
	 * Delete servicio movil.
	 *
	 * @param servicioMovil the servicio movil
	 * @param accionServicioMovil the accion servicio movil
	 * @param accionIdServicioMovil the accion id servicio movil
	 * @param source the source
	 * @throws BusinessException the business exception
	 */
	void deleteServicioMovil(ServicioMovilBean servicioMovil, String accionServicioMovil, Long accionIdServicioMovil, String source) throws BusinessException;
	
	/**
	 * Creates the servicio movil bean.
	 *
	 * @param servMovil the serv movil
	 * @return the servicio movil bean
	 * @throws BusinessException the business exception
	 */
	ServicioMovilBean createServicioMovilBean(ServicioMovilBean servMovil) throws BusinessException;
	
	/**
	 * Delete imagen servicio movil.
	 *
	 * @param servicioMovil the servicio movil
	 * @param accionServicioMovil the accion servicio movil
	 * @param accionIdServicioMovil the accion id servicio movil
	 * @param source the source
	 * @throws BusinessException the business exception
	 */
	void deleteImagenServicioMovil(ServicioMovilBean servicioMovil, String accionServicioMovil, Long accionIdServicioMovil,
			String source) throws BusinessException;
	
}
