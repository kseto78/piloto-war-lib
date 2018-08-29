package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.Date;
import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

/**
 * <p>Interface que define los metodos para la gestion de lotes de envios</p>.
 *
 * @author jgonzvil
 */

public interface ServicioLotesEnvios {
	
	/**
	 * Obtener lotes envios TO hist.
	 *
	 * @param servicioId the servicio id
	 * @param fechaHistorico the fecha historico
	 * @return lotes envios TO hist
	 * @throws BusinessException the business exception
	 */
	List<Long> getLotesEnviosTOHist(Integer servicioId, Date fechaHistorico) throws BusinessException;

}
