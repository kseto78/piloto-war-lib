package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidoresOrganismosBean;
import es.mpr.plataformamensajeria.model.ServidoresOrganismosJPA;
import es.mpr.plataformamensajeria.model.views.ViewServidoresOrganismosJPA;

/**
 * <p>
 * Interface que define los m&eacute;todos para la gesti&oacute;n de servidor
 * </p>
 * 
 * @author Selered
 * 
 */
@Service
public interface ServicioServidor {

	List<ServidorBean> getServidores() throws BusinessException;

	List<ServidorBean> getServidoresNoAsignados(Integer idServicio) throws BusinessException;

	List<ServidorBean> getAllServidores() throws BusinessException;

	List<ServidorBean> getServidores(ServidorBean criterio) throws BusinessException;

	PaginatedList<ServidorBean> getServidores(int start, int size, String order, String columnSort, ServidorBean criterio) throws BusinessException;

	public List<ServidorBean> getServidoresYProveedores() throws BusinessException;

	public Integer getTotalServidores(ServidorBean criterio, EntityManager em) throws BusinessException;

	Long newServidor(ServidorBean servidor) throws BusinessException;

	void updateServidor(ServidorBean servidor) throws BusinessException;

	ServidorBean loadServidor(ServidorBean servidor) throws BusinessException;

	void deleteServidor(ServidorBean servidor) throws BusinessException;

	List<ServidorBean> getServidoresYProveedores(String rolUsuario, Integer idUsuario) throws BusinessException;

	List<ServidorBean> getServidoresByTipoPlanificacion(String tipo) throws BusinessException;

	List<ServidorBean> getAllServidoresBBDD() throws BusinessException;

	public List<ServidorBean> getServidoresYProveedores(String tipoServidor) throws BusinessException;

	public List<ServidoresOrganismosBean> getServidorOrganismo(String idOrganismo) throws BusinessException;

	public List<ViewServidoresOrganismosJPA> getServidorOrganismo(List<Integer> listaOrganismos) throws BusinessException;

	public void newServidoresOrganismo(ServidoresOrganismosBean servidorOrganismo) throws BusinessException;

	public ServidoresOrganismosJPA loadServidorOrganismoJPA(ServidoresOrganismosBean servidorOrganismo) throws BusinessException;

	public ServidoresOrganismosBean loadServidorOrganismoBean(ServidoresOrganismosBean servidorOrganismo) throws BusinessException;
	
	public void deleteServidorOrganismos(ServidoresOrganismosBean servidorOrganismo) throws BusinessException;
}
