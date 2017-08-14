package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.AplicacionBean;

/**
 * <p>
 * Interface que define los m&eacute;todos para la gesti&oacute;n de aplicacion
 * </p>
 * 
 * @author Selered
 * 
 */
public interface ServicioAplicacion {

	List<AplicacionBean> getAplicaciones() throws BusinessException;

	PaginatedList<AplicacionBean> getAplicaciones(int start, int size, String order, String columnSort,
			AplicacionBean criterio) throws BusinessException;

	Integer newAplicacion(AplicacionBean aplicacion, String source, String accion, Long accionId)
			throws BusinessException;

	void updateAplicacion(AplicacionBean aplicacion, String source, String accion, Long accionId)
			throws BusinessException;

	AplicacionBean loadAplicacion(AplicacionBean aplicacion) throws BusinessException;

	void deleteAplicacion(AplicacionBean aplicacion, String source, String accion, Long accionId,
			String descripcionServicio, String accionActualizar, Long accionIdActualizar,
			String descripcionPlanificacion) throws BusinessException;

	public List<AplicacionBean> getAplicacionesNoAsignadas(String idUsuario) throws BusinessException;

	public List<AplicacionBean> getAplicacionesMenu(String rolUsuario, Integer userName);

	boolean existeUsuario(String usuario);

}
