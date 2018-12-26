package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.AplicacionBean;

/**
 * <p>
 * Interface que define los m&eacute;todos para la gesti&oacute;n de aplicacion
 * </p>.
 *
 * @author Selered
 */
public interface ServicioAplicacion {

	/**
	 * Obtener aplicaciones.
	 *
	 * @return aplicaciones
	 * @throws BusinessException the business exception
	 */
	List<AplicacionBean> getAplicaciones() throws BusinessException;

	/**
	 * Obtener aplicaciones.
	 *
	 * @param start the start
	 * @param size the size
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @return aplicaciones
	 * @throws BusinessException the business exception
	 */
	PaginatedList<AplicacionBean> getAplicaciones(int start, int size, String order, String columnSort,
			AplicacionBean criterio) throws BusinessException;

	/**
	 * New aplicacion.
	 *
	 * @param aplicacion the aplicacion
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @return the integer
	 * @throws BusinessException the business exception
	 */
	Integer newAplicacion(AplicacionBean aplicacion, String source, String accion, Long accionId)
			throws BusinessException;

	/**
	 * Update aplicacion.
	 *
	 * @param aplicacion the aplicacion
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @throws BusinessException the business exception
	 */
	void updateAplicacion(AplicacionBean aplicacion, String source, String accion, Long accionId)
			throws BusinessException;

	/**
	 * Load aplicacion.
	 *
	 * @param aplicacion the aplicacion
	 * @return the aplicacion bean
	 * @throws BusinessException the business exception
	 */
	AplicacionBean loadAplicacion(AplicacionBean aplicacion) throws BusinessException;

	/**
	 * Delete aplicacion.
	 *
	 * @param aplicacion the aplicacion
	 * @param source the source
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param descripcionServicio the descripcion servicio
	 * @param accionActualizar the accion actualizar
	 * @param accionIdActualizar the accion id actualizar
	 * @param descripcionPlanificacion the descripcion planificacion
	 * @throws BusinessException the business exception
	 */
	void deleteAplicacion(AplicacionBean aplicacion, String source, String accion, Long accionId,
			String descripcionServicio, String accionActualizar, Long accionIdActualizar,
			String descripcionPlanificacion) throws BusinessException;

	/**
	 * Obtener aplicaciones no asignadas.
	 *
	 * @param idUsuario the id usuario
	 * @return aplicaciones no asignadas
	 * @throws BusinessException the business exception
	 */
	public List<AplicacionBean> getAplicacionesNoAsignadas(String idUsuario) throws BusinessException;

	/**
	 * Obtener aplicaciones menu.
	 *
	 * @param rolUsuario the rol usuario
	 * @param userName the user name
	 * @return aplicaciones menu
	 */
	public List<AplicacionBean> getAplicacionesMenu(String rolUsuario, Integer userName);

	/**
	 * Existe usuario.
	 *
	 * @param usuario the usuario
	 * @return true, if successful
	 */
	boolean existeUsuario(String usuario);

//	boolean validarSms(Long idAplicacion);

//	void smsEnviado(Long idAplicacion);

}
