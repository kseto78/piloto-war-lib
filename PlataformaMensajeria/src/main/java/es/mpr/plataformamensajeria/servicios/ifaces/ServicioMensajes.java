package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;


/**
 * <p>Interface que define los metodos para la gestion de mensajes</p>.
 *
 * @author jgonzvil
 */

public interface ServicioMensajes {
	
	/**
	 * Obtener todos mensajes lote historificar.
	 *
	 * @param loteEnvioID the lote envio ID
	 * @param date the date
	 * @return todos mensajes lote historificar
	 * @throws BusinessException the business exception
	 */
	List<Long> getTodosMensajesLoteHistorificar(Long loteEnvioID) throws BusinessException;
	
	/**
	 * Test lote sin mensajes.
	 *
	 * @param loteEnvioId the lote envio id
	 * @return the boolean
	 * @throws BusinessException the business exception
	 */
	Boolean testLoteSinMensajes(Long loteEnvioId) throws BusinessException;
}
