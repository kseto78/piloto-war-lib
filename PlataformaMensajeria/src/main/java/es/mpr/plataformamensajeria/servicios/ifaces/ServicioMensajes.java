package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.Date;
import java.util.List;

import com.map.j2ee.exceptions.BusinessException;


/**
 * <p>Interface que define los metodos para la gestion de mensajes</p>
 * 
 * @author jgonzvil
 *
 */

public interface ServicioMensajes {
	
	public List<Long> getTodosMensajesLoteHistorificar(Long loteEnvioID, Date date) throws BusinessException;
	
	public Boolean testLoteSinMensajes(Long loteEnvioId) throws BusinessException;
}
