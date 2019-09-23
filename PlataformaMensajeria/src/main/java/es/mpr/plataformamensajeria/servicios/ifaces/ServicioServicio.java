package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.plataformamensajeria.iop.beans.OrganismosServicioBean;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.ViewServicios;
import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServicioOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de servidor</p>.
 *
 * @author Selered
 */
public interface ServicioServicio {
	
	/**
	 * Obtener servicios multiorganismo.
	 *
	 * @return servicios multiorganismo
	 * @throws BusinessException the business exception
	 */
	List<ServicioBean> getServiciosMultiorganismo() throws BusinessException;
	
	List<ViewServicios> getServiciosActivosNoEliminados() throws BusinessException;
	
	/**
	 * Obtener servicios.
	 *
	 * @param start the start
	 * @param size the size
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @return servicios
	 * @throws BusinessException the business exception
	 */
	PaginatedList<ServicioBean> getServicios(int start, int size, String order, String columnSort,ServicioBean criterio) 
		throws BusinessException;
	
	/**
	 * Obtener servicios by aplicacion id.
	 *
	 * @param aplicacionId the aplicacion id
	 * @return servicios by aplicacion id
	 * @throws BusinessException the business exception
	 */
	List<ServicioBean> getServiciosByAplicacionId(Integer aplicacionId) throws BusinessException;
		
	/**
	 * New servicio.
	 *
	 * @param servicio the servicio
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @return the integer
	 * @throws BusinessException the business exception
	 */
	Integer newServicio(ServicioBean servicio, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Update servicio.
	 *
	 * @param servicio the servicio
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void updateServicio(ServicioBean servicio, String source, String accion, Long accionId)throws BusinessException;
	
	/**
	 * Load servicio.
	 *
	 * @param servicio the servicio
	 * @return the servicio bean
	 * @throws BusinessException the business exception
	 */
	ServicioBean loadServicio(ServicioBean servicio)throws BusinessException;
	
	/**
	 * Delete servicio.
	 *
	 * @param servicio the servicio
	 * @param accionServicio the accion servicio
	 * @param accionIdServicio the accion id servicio
	 * @param source the source
	 * @param accionPlanificacion the accion planificacion
	 * @param accionIdPlanificacion the accion id planificacion
	 * @param descripcionPlanificacion the descripcion planificacion
	 * @param descripcionServidorServicio the descripcion servidor servicio
	 * @throws BusinessException the business exception
	 */
	void deleteServicio(ServicioBean servicio, String accionServicio, Long accionIdServicio, String source, String accionPlanificacion, Long accionIdPlanificacion, String descripcionPlanificacion, String descripcionServidorServicio)throws BusinessException;
	
	/**
	 * Obtener servidores servicios.
	 *
	 * @param idServicio the id servicio
	 * @return servidores servicios
	 * @throws BusinessException the business exception
	 */
	List <ServidoresServiciosBean> getServidoresServicios(String idServicio) throws BusinessException;
	
	/**
	 * Obtener servicio organismo.
	 *
	 * @param idServicio the id servicio
	 * @return servicio organismo
	 * @throws BusinessException the business exception
	 */
	List <ServicioOrganismosBean> getServicioOrganismo(String idServicio) throws BusinessException;
	
	/**
	 * Obtener organismo servicio.
	 *
	 * @param idOrganismo the id organismo
	 * @return organismo servicio
	 * @throws BusinessException the business exception
	 */
	List <ServicioOrganismosBean> getOrganismoServicio(String idOrganismo) throws BusinessException;

	
	/**
	 * Delete servidores servicios.
	 *
	 * @param servidorServicio the servidor servicio
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void deleteServidoresServicios(ServidoresServiciosBean servidorServicio, String source, String accion, Long accionId, String descripcion)
			throws BusinessException;

	/**
	 * New servidores servicio.
	 *
	 * @param servidorServicio the servidor servicio
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void newServidoresServicio(ServidoresServiciosBean servidorServicio, String source, String accion, Long accionId, String descripcion) throws BusinessException;
	
	/**
	 * New servicio organismo.
	 *
	 * @param servicioOrganismo the servicio organismo
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void newServicioOrganismo(ServicioOrganismosBean servicioOrganismo, String source, String accion, Long accionId, String descripcion) throws BusinessException;
	
	/**
	 * Obtener servicio TO.
	 *
	 * @param servicioBean the servicio bean
	 * @return servicio TO
	 * @throws BusinessException the business exception
	 */
	public TblServicios getServicioTO(ServicioBean servicioBean) throws BusinessException;
	
	/**
	 * Creates the servicio bean.
	 *
	 * @param servicioBean the servicio bean
	 * @return the servicio bean
	 * @throws BusinessException the business exception
	 */
	public ServicioBean createServicioBean(ServicioBean servicioBean) throws BusinessException;
	
	/**
	 * Obtener servicios.
	 *
	 * @param rolUsuario the rol usuario
	 * @param idUsuario the id usuario
	 * @return servicios
	 * @throws BusinessException the business exception
	 */
	List<ServicioBean> getServicios(String rolUsuario, Integer idUsuario)
			throws BusinessException;
	
	/**
	 * Obtener servicios historico.
	 *
	 * @return servicios historico
	 * @throws BusinessException the business exception
	 */
	List<ServicioBean> getServiciosHistorico() throws BusinessException;
	
	/**
	 * Obtener servicios cons.
	 *
	 * @return servicios cons
	 * @throws BusinessException the business exception
	 */
	List<ServicioBean> getServiciosCons() throws BusinessException;
	
	/**
	 * Obtener servicios informes.
	 *
	 * @return servicios informes
	 * @throws BusinessException the business exception
	 */
	List<ServicioBean> getServiciosInformes() throws BusinessException;
	
	/**
	 * Delete servicio organismos.
	 *
	 * @param servicioOrganismo the servicio organismo
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcion the descripcion
	 * @throws BusinessException the business exception
	 */
	void deleteServicioOrganismos(ServicioOrganismosBean servicioOrganismo, String source, String accion, Long accionId, String descripcion) throws BusinessException;
	
	/**
	 * Load organismo servicio.
	 *
	 * @param organismoServicio the organismo servicio
	 * @return the servicio organismos bean
	 * @throws BusinessException the business exception
	 */
	ServicioOrganismosBean loadOrganismoServicio(ServicioOrganismosBean organismoServicio) throws BusinessException;

	/**
	 * Obtener list servicio bean.
	 *
	 * @param lista the lista
	 * @return list servicio bean
	 * @throws BusinessException the business exception
	 */
	public List<ServicioBean> getListServicioBean(List<TblServicios> lista) throws BusinessException;

	/**
	 * Obtener list canal bean.
	 *
	 * @param lista the lista
	 * @return list canal bean
	 * @throws BusinessException the business exception
	 */
	List<CanalBean> getCanalByServicioId(String idServicio)  throws BusinessException;

	List<ViewServicios> getCanalesServicios(Integer valueOf);

	List<ServicioBean> getServiciosPorCanal(String idAplicacion, String idCanal) throws BusinessException;

	List<OrganismosServicioBean> getServicioByOrganismo(Long organismoid);

	
		
}
