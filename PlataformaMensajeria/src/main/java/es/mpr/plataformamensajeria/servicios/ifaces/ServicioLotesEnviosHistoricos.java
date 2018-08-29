package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.Date;
import java.util.List;

import es.minhap.sim.model.TblLotesEnviosHist;

/**
 * <p>Interface que define los metodos para la gestion de lotes de envios historicos</p>.
 *
 * @author jgonzvil
 */
public interface ServicioLotesEnviosHistoricos {
	
	/**
	 * Obtener lote envio hist.
	 *
	 * @param loteEnvio the lote envio
	 * @return lote envio hist
	 */
	TblLotesEnviosHist getLoteEnvioHist(Long loteEnvio);

	/**
	 * Insert.
	 *
	 * @param loteHistorico the lote historico
	 * @return the long
	 */
	Long insert(TblLotesEnviosHist loteHistorico);

	/**
	 * Delete.
	 *
	 * @param loteenvioid the loteenvioid
	 */
	void delete(Long loteenvioid);

	/**
	 * Obtener listas lotes envios historicos.
	 *
	 * @param servicioId the servicio id
	 * @param time the time
	 * @return listas lotes envios historicos
	 */
	List<List<Long>> getListasLotesEnviosHistoricos(Integer servicioId, Date time);

	/**
	 * Delete hist.
	 *
	 * @param loteenvioid the loteenvioid
	 */
	void deleteHist(Long loteenvioid);
}
