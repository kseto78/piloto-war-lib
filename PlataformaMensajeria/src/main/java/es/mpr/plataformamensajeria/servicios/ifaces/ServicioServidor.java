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
 * </p>
 * 
 * @author Selered
 * 
 */
public interface ServicioServidor {

	List<ServidorBean> getServidores(int tipoServidor) throws BusinessException;

	List<ServidorBean> getServidoresNoAsignados(Integer idServicio, int tipoServidor) throws BusinessException;

	List<ServidorBean> getAllServidores() throws BusinessException;

	PaginatedList<ServidorBean> getServidores(int start, int size, String order, String columnSort, ServidorBean criterio, int tipoServidor) throws BusinessException;

	Long newServidor(ServidorBean servidor, int tipoServidor, String source, String accion, Long accionId) throws BusinessException;

	void updateServidor(ServidorBean servidor, String source, String accion, Long accionId) throws BusinessException;

	ServidorBean loadServidor(ServidorBean servidor) throws BusinessException;

	void deleteServidor(ServidorBean servidor, String accionServidor, Long accionIdServidor, String source, String accionPlanificacion, 
			Long accionIdPlanificacion, String descripcion) throws BusinessException;

	List<ServidorBean> getServidoresYProveedores(String rolUsuario, Integer idUsuario) throws BusinessException;

	List<ServidorBean> getServidoresByTipoPlanificacion(String tipo) throws BusinessException;

	List<ServidorBean> getAllServidoresBBDD() throws BusinessException;

	public List<ServidorBean> getServidoresYProveedores(String tipoServidor) throws BusinessException;

	public List<ServidoresOrganismosBean> getServidorOrganismo(String idOrganismo) throws BusinessException;

	public List<ServidoresServiciosBean> getServidorOrganismo(List<Integer> listaOrganismos) throws BusinessException;

	public void newServidoresOrganismo(ServidoresOrganismosBean servidorOrganismo, String source, String accion, Long accionId, String descripcion) throws BusinessException;

	public ServidoresOrganismosBean loadServidorOrganismoBean(ServidoresOrganismosBean servidorOrganismo) throws BusinessException;
	
	public void deleteServidorOrganismos(ServidoresOrganismosBean servidorOrganismo, String source, String accion, Long accionId, String descripcion) throws BusinessException;
}
