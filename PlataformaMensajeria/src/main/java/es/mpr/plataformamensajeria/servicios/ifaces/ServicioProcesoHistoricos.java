package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.ProcesoHistBean;

/**
 * <p>Interface que define los metodos para la gestion del resultado del JOB de historificacion</p>.
 *
 * @author jgonzvil
 */

public interface ServicioProcesoHistoricos {
	
	/**
	 * New servicio proceso historicos.
	 *
	 * @param procesoHistBean the proceso hist bean
	 * @return the long
	 */
	Long newServicioProcesoHistoricos(ProcesoHistBean procesoHistBean);
	
	
	/**
	 * Proceso historico lotes envio.
	 *
	 * @param loteEnvio the lote envio
	 * @param listaMensajes the lista mensajes
	 * @return true, if successful
	 * @throws BusinessException the business exception
	 */
	boolean procesoHistoricoLotesEnvio(Long loteEnvio, List<Long> listaMensajes) throws BusinessException;
	
}
