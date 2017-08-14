package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.ProcesoHistBean;

/**
 * <p>Interface que define los metodos para la gestion del resultado del JOB de historificacion</p>
 * 
 * @author jgonzvil
 *
 */

public interface ServicioProcesoHistoricos {
	
	Long newServicioProcesoHistoricos(ProcesoHistBean procesoHistBean);
	
	
	boolean procesoHistoricoLotesEnvio(Long loteEnvio, List<Long> listaMensajes) throws BusinessException;
	
}
