package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.sim.model.TblServiciosMoviles;
import es.mpr.plataformamensajeria.beans.ServicioMovilBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de servicios moviles</p>
 * 
 * @author Selered
 *
 */

public interface ServicioServicioMovil {
	
	List<TblServiciosMoviles> getServiciosMoviles(ServicioMovilBean criterio) throws BusinessException;
	List<TblServiciosMoviles> getServiciosMoviles() throws BusinessException;
	
	PaginatedList<ServicioMovilBean> getServiciosMoviles(int start, int size, String order, String columnSort,ServicioMovilBean criterio) 
		throws BusinessException;
	
	ServicioMovilBean loadServicioMovil(ServicioMovilBean servicioMovil)throws BusinessException;
	
	Long newServicioMovil(ServicioMovilBean servicio, String source, String accion, Long accionId) throws BusinessException;
	
	void updateServicioMovil(ServicioMovilBean servicioMovil, String source, String accion, Long accionId) throws BusinessException;
	
	void deleteServicioMovil(ServicioMovilBean servicioMovil, String accionServicioMovil, Long accionIdServicioMovil, String source) throws BusinessException;
	
	ServicioMovilBean createServicioMovilBean(ServicioMovilBean servMovil) throws BusinessException;
	
	void deleteImagenServicioMovil(ServicioMovilBean servicioMovil, String accionServicioMovil, Long accionIdServicioMovil,
			String source) throws BusinessException;
	
}
