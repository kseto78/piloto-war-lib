package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.InformesServiciosCodOrgBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosCodOrgPagadorBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosCodSiaBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosEstadoBean;
import es.mpr.plataformamensajeria.beans.ProcesoInformesServiciosBean;

/**
 * <p>Interface que define los metodos para la gestion del resultado del JOB de informes de servicios</p>
 * 
 * @author jgonzvil
 *
 */
public interface ServicioProcesoInformesServicios {
	
	Long newServicioProcesoInformesServicios(ProcesoInformesServiciosBean procesoInformesServiciosBean);
	
	List<InformesServiciosEstadoBean> obtenerInformesServiciosEstado(Integer servicioId, Integer anno, Integer mes) throws BusinessException;
	
	List<InformesServiciosCodOrgBean> obtenerInformesServiciosCodOrg(Integer servicioId, Integer anno, Integer mes) throws BusinessException;
	
	List<InformesServiciosCodSiaBean> obtenerInformesServiciosCodSia(Integer servicioId, Integer anno, Integer mes) throws BusinessException;
	
	List<InformesServiciosCodOrgPagadorBean> obtenerInformesServiciosCodOrgPagador(Integer servicioId, Integer anno, Integer mes) throws BusinessException;
	
}