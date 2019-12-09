package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidoresOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;

/**
 * <p>
 * Interface que define los m&eacute;todos para la gesti&oacute;n de servidor
 * </p>.
 *
 * @author Selered
 */
public interface ServicioServidor {

	/**
	 * Obtener servidores.
	 *
	 * @param tipoServidor the tipo servidor
	 * @return servidores
	 * @throws BusinessException the business exception
	 */
	List<ServidorBean> getServidores(int tipoServidor) throws BusinessException;

	/**
	 * Obtener servidores no asignados.
	 *
	 * @param idServicio the id servicio
	 * @param tipoServidor the tipo servidor
	 * @return servidores no asignados
	 * @throws BusinessException the business exception
	 */
	List<ServidorBean> getServidoresNoAsignados(Integer idServicio, int tipoServidor) throws BusinessException;

	/**
	 * Obtener all servidores.
	 *
	 * @return all servidores
	 * @throws BusinessException the business exception
	 */
	List<ServidorBean> getAllServidores() throws BusinessException;

	/**
	 * Obtener servidores.
	 *
	 * @param start the start
	 * @param size the size
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @param tipoServidor the tipo servidor
	 * @return servidores
	 * @throws BusinessException the business exception
	 */
	PaginatedList<ServidorBean> getServidores(int start, int size, String order, String columnSort, ServidorBean criterio, int tipoServidor) throws BusinessException;

	/**
	 * New servidor.
	 *
	 * @param servidor the servidor
	 * @param tipoServidor the tipo servidor
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @return the long
	 * @throws BusinessException the business exception
	 */
	Long newServidor(ServidorBean servidor, int tipoServidor, String source, String accion, Long accionId) throws BusinessException;

	/**
	 * Update servidor.
	 *
	 * @param servidor the servidor
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void updateServidor(ServidorBean servidor, String source, String accion, Long accionId) throws BusinessException;

	/**
	 * Load servidor.
	 *
	 * @param servidor the servidor
	 * @return the servidor bean
	 * @throws BusinessException the business exception
	 */
	ServidorBean loadServidor(ServidorBean servidor) throws BusinessException;

	/**
	 * Delete servidor.
	 *
	 * @param servidor the servidor
	 * @param accionServidor the accion servidor
	 * @param accionIdServidor the accion id servidor
	 * @param source the source
	 * @param accionPlanificacion the accion planificacion
	 * @param accionIdPlanificacion the accion id planificacion
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void deleteServidor(ServidorBean servidor, String accionServidor, Long accionIdServidor, String source, String accionPlanificacion, 
			Long accionIdPlanificacion, String descripcion) throws BusinessException;

	/**
	 * Obtener servidores Y proveedores.
	 *
	 * @param rolUsuario the rol usuario
	 * @param idUsuario the id usuario
	 * @return servidores Y proveedores
	 * @throws BusinessException the business exception
	 */
	List<ServidorBean> getServidoresYProveedores(String rolUsuario, Integer idUsuario) throws BusinessException;

	/**
	 * Obtener servidores by tipo planificacion.
	 *
	 * @param tipo the tipo
	 * @return servidores by tipo planificacion
	 * @throws BusinessException the business exception
	 */
	List<ServidorBean> getServidoresByTipoPlanificacion(String tipo) throws BusinessException;

	/**
	 * Obtener all servidores BBDD.
	 *
	 * @return all servidores BBDD
	 * @throws BusinessException the business exception
	 */
	List<ServidorBean> getAllServidoresBBDD() throws BusinessException;

	/**
	 * Obtener servidores Y proveedores.
	 *
	 * @param tipoServidor the tipo servidor
	 * @return servidores Y proveedores
	 * @throws BusinessException the business exception
	 */
	List<ServidorBean> getServidoresYProveedores(String tipoServidor) throws BusinessException;

	/**
	 * Obtener servidor organismo.
	 *
	 * @param idOrganismo the id organismo
	 * @return servidor organismo
	 * @throws BusinessException the business exception
	 */
	List<ServidoresOrganismosBean> getServidorOrganismo(String idOrganismo) throws BusinessException;

	/**
	 * Obtener servidor organismo.
	 *
	 * @param listaOrganismos the lista organismos
	 * @return servidor organismo
	 * @throws BusinessException the business exception
	 */
	List<ServidoresServiciosBean> getServidorOrganismo(List<Integer> listaOrganismos) throws BusinessException;

	/**
	 * New servidores organismo.
	 *
	 * @param servidorOrganismo the servidor organismo
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void newServidoresOrganismo(ServidoresOrganismosBean servidorOrganismo, String source, String accion, Long accionId, String descripcion) throws BusinessException;

	/**
	 * Load servidor organismo bean.
	 *
	 * @param servidorOrganismo the servidor organismo
	 * @return the servidores organismos bean
	 * @throws BusinessException the business exception
	 */
	ServidoresOrganismosBean loadServidorOrganismoBean(ServidoresOrganismosBean servidorOrganismo) throws BusinessException;
	
	/**
	 * Delete servidor organismos.
	 *
	 * @param servidorOrganismo the servidor organismo
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void deleteServidorOrganismos(ServidoresOrganismosBean servidorOrganismo, String source, String accion, Long accionId, String descripcion) throws BusinessException;
}
