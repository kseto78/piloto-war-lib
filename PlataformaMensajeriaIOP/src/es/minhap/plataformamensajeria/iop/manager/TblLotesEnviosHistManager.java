package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.TblLotesEnviosHist;


public interface TblLotesEnviosHistManager {

	/**
	 * Obtiene un Lote historico por Id
	 * 
	 * @param loteEnvioHistId
	 * @return TblAdjuntosHist
	 */
	public TblLotesEnviosHist getLoteHistoricoById(Long loteEnvioHistId);

}
