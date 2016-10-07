package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServicioOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;
import es.mpr.plataformamensajeria.model.OrganismosServicioJPA;
import es.mpr.plataformamensajeria.model.ServicioJPA;
import es.mpr.plataformamensajeria.model.ServidoresServiciosJPA;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de servidor</p>
 * 
 * @author Selered
 *
 */
@Service
public interface ServicioServicio {
	
	List<ServicioBean> getServicios(ServicioBean criterio) throws BusinessException;
	List<ServicioBean> getServicios() throws BusinessException;
	
	List<ServicioBean> getServiciosMultiorganismo() throws BusinessException;
	
	PaginatedList<ServicioBean> getServicios(int start, int size, String order, String columnSort,ServicioBean criterio) 
		throws BusinessException;
	
	List<ServicioBean> getServiciosByAplicacionId(Integer aplicacionId) throws BusinessException;
	List<ServicioBean> getServiciosByOrganismoId(Integer organismoId) throws BusinessException;
	
	public Integer getTotalServicios(ServicioBean criterio, EntityManager em,String namedQuery, int tipoConsulta) throws BusinessException;
	
	Integer newServicio(ServicioBean servicio)throws BusinessException;
	
	void updateServicio(ServicioBean servicio)throws BusinessException;
	
	ServicioBean loadServicio(ServicioBean servicio)throws BusinessException;
	
	void deleteServicio(ServicioBean servicio)throws BusinessException;
	
	List <ServidoresServiciosBean> getServidoresServicios(String idServicio) throws BusinessException;
	
	List <ServicioOrganismosBean> getServicioOrganismo(String idServicio) throws BusinessException;
	
	List <ServicioOrganismosBean> getOrganismoServicio(String idOrganismo) throws BusinessException;

	ServidoresServiciosBean loadServidorServicio(
			ServidoresServiciosBean servidorServicio) throws BusinessException;

	ServidoresServiciosJPA loadServidorServicioJPA(
			ServidoresServiciosBean servidorServicio) throws BusinessException;

	void deleteServidoresServicios(ServidoresServiciosBean servidorServicio)
			throws BusinessException;

	void newServidoresServicio(ServidoresServiciosBean servidorServicio);
	
	void newServicioOrganismo(ServicioOrganismosBean servicioOrganismo);
	
	public ServicioJPA getServicioJPA(ServicioBean servicioBean) throws BusinessException;
	List<ServicioBean> getServicios(String rolUsuario, Integer idUsuario)
			throws BusinessException;
	
	List<ServicioBean> getServiciosHistorico() throws BusinessException;
	
	List<ServicioBean> getServiciosCons() throws BusinessException;
	
	List<ServicioBean> getServiciosInformes() throws BusinessException;
	
	void deleteServicioOrganismos(ServicioOrganismosBean servicioOrganismo) throws BusinessException;
	
	OrganismosServicioJPA loadOrganismoServicioJPA(ServicioOrganismosBean organismoServicio) throws BusinessException;
	
	boolean actualizarCheckMultiorganismo(boolean isMultiorganismo, Integer idServicio) throws BusinessException;
}
