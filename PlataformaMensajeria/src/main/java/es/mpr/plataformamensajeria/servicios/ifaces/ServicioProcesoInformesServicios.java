package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.InformesServiciosCodOrgBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosCodOrgPagadorBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosCodSiaBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosEstadoBean;
import es.mpr.plataformamensajeria.beans.ProcesoInformesServiciosBean;

/**
 * <p>Interface que define los metodos para la gestion del resultado del JOB de informes de servicios</p>.
 *
 * @author jgonzvil
 */
public interface ServicioProcesoInformesServicios {
	
	/**
	 * New servicio proceso informes servicios.
	 *
	 * @param procesoInformesServiciosBean the proceso informes servicios bean
	 * @return the long
	 */
	Long newServicioProcesoInformesServicios(ProcesoInformesServiciosBean procesoInformesServiciosBean);
	
	/**
	 * Obtener informes servicios estado.
	 *
	 * @param servicioId the servicio id
	 * @param anno the anno
	 * @param mes the mes
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	List<InformesServiciosEstadoBean> obtenerInformesServiciosEstado(Integer servicioId, Integer anno, Integer mes) throws BusinessException;
	
	/**
	 * Obtener informes servicios cod org.
	 *
	 * @param servicioId the servicio id
	 * @param anno the anno
	 * @param mes the mes
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	List<InformesServiciosCodOrgBean> obtenerInformesServiciosCodOrg(Integer servicioId, Integer anno, Integer mes) throws BusinessException;
	
	/**
	 * Obtener informes servicios cod sia.
	 *
	 * @param servicioId the servicio id
	 * @param anno the anno
	 * @param mes the mes
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	List<InformesServiciosCodSiaBean> obtenerInformesServiciosCodSia(Integer servicioId, Integer anno, Integer mes) throws BusinessException;
	
	/**
	 * Obtener informes servicios cod org pagador.
	 *
	 * @param servicioId the servicio id
	 * @param anno the anno
	 * @param mes the mes
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	List<InformesServiciosCodOrgPagadorBean> obtenerInformesServiciosCodOrgPagador(Integer servicioId, Integer anno, Integer mes) throws BusinessException;
	
}