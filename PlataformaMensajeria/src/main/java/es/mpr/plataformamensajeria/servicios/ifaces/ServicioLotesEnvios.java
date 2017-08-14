package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.Date;
import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

/**
 * <p>Interface que define los metodos para la gestion de lotes de envios</p>
 * 
 * @author jgonzvil
 *
 */

public interface ServicioLotesEnvios {
	
	List<Long> getLotesEnviosTOHist(Integer servicioId, Date fechaHistorico) throws BusinessException;

}
