package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.Date;
import java.util.List;

import es.minhap.sim.model.TblLotesEnviosHist;

/**
 * <p>Interface que define los metodos para la gestion de lotes de envios historicos</p>
 * 
 * @author jgonzvil
 *
 */
public interface ServicioLotesEnviosHistoricos {
	
	TblLotesEnviosHist getLoteEnvioHist(Long loteEnvio);

	Long insert(TblLotesEnviosHist loteHistorico);

	void delete(Long loteenvioid);

	List<List<Long>> getListasLotesEnviosHistoricos(Integer servicioId, Date time);

	void deleteHist(Long loteenvioid);
}
