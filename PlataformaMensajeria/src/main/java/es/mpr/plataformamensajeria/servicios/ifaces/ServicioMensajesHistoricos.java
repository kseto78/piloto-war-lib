package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.Date;
import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.sim.model.TblLotesEnviosHist;
import es.minhap.sim.model.TblMensajesHist;


/**
 * <p>Interface que define los metodos para la gestion de mensajes historico</p>.
 *
 * @author jgonzvil
 */

public interface ServicioMensajesHistoricos {
	
	/**
	 * Obtener tbl mensajes hist.
	 *
	 * @param subList the sub list
	 * @param loteHistorico the lote historico
	 * @return tbl mensajes hist
	 */
	List<TblMensajesHist> getTblMensajesHist(List<Long> subList, TblLotesEnviosHist loteHistorico);

	/**
	 * Insert.
	 *
	 * @param mensajeHistorico the mensaje historico
	 * @return the long
	 */
	Long insert(TblMensajesHist mensajeHistorico);

	/**
	 * Delete.
	 *
	 * @param mensajeid the mensajeid
	 */
	void delete(Long mensajeid);

	/**
	 * Obtener todos mensajes lote historificar.
	 *
	 * @param loteEnvioID the lote envio ID
	 * @param fecha the fecha
	 * @return todos mensajes lote historificar
	 * @throws BusinessException the business exception
	 */
	List<List<Long>> getTodosMensajesLoteHistorificar(Long loteEnvioID, Date fecha) throws BusinessException;
	
}
