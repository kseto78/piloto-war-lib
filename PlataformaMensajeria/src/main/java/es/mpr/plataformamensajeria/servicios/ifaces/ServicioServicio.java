package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.sim.model.TblServicios;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServicioOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de servidor</p>
 * 
 * @author Selered
 *
 */
public interface ServicioServicio {
	
	List<ServicioBean> getServiciosMultiorganismo() throws BusinessException;
	
	PaginatedList<ServicioBean> getServicios(int start, int size, String order, String columnSort,ServicioBean criterio) 
		throws BusinessException;
	
	List<ServicioBean> getServiciosByAplicacionId(Integer aplicacionId) throws BusinessException;
		
	Integer newServicio(ServicioBean servicio, String source, String accion, Long accionId)throws BusinessException;
	
	void updateServicio(ServicioBean servicio, String source, String accion, Long accionId)throws BusinessException;
	
	ServicioBean loadServicio(ServicioBean servicio)throws BusinessException;
	
	void deleteServicio(ServicioBean servicio, String accionServicio, Long accionIdServicio, String source, String accionPlanificacion, Long accionIdPlanificacion, String descripcionPlanificacion, String descripcionServidorServicio)throws BusinessException;
	
	List <ServidoresServiciosBean> getServidoresServicios(String idServicio) throws BusinessException;
	
	List <ServicioOrganismosBean> getServicioOrganismo(String idServicio) throws BusinessException;
	
	List <ServicioOrganismosBean> getOrganismoServicio(String idOrganismo) throws BusinessException;

	
	void deleteServidoresServicios(ServidoresServiciosBean servidorServicio, String source, String accion, Long accionId, String descripcion)
			throws BusinessException;

	void newServidoresServicio(ServidoresServiciosBean servidorServicio, String source, String accion, Long accionId, String descripcion) throws BusinessException;
	
	void newServicioOrganismo(ServicioOrganismosBean servicioOrganismo, String source, String accion, Long accionId, String descripcion) throws BusinessException;
	
	public TblServicios getServicioTO(ServicioBean servicioBean) throws BusinessException;
	
	public ServicioBean createServicioBean(ServicioBean servicioBean) throws BusinessException;
	
	List<ServicioBean> getServicios(String rolUsuario, Integer idUsuario)
			throws BusinessException;
	
	List<ServicioBean> getServiciosHistorico() throws BusinessException;
	
	List<ServicioBean> getServiciosCons() throws BusinessException;
	
	List<ServicioBean> getServiciosInformes() throws BusinessException;
	
	void deleteServicioOrganismos(ServicioOrganismosBean servicioOrganismo, String source, String accion, Long accionId, String descripcion) throws BusinessException;
	
	ServicioOrganismosBean loadOrganismoServicio(ServicioOrganismosBean organismoServicio) throws BusinessException;

	public List<ServicioBean> getListServicioBean(List<TblServicios> lista) throws BusinessException;
	
}
