package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblLotesEnviosHist;
import es.minhap.sim.query.TblLotesEnviosHistQuery;

public interface TblLotesEnviosHistManager {

	/**
	 * Obtiene un lote historico a partir del idLote pasado
	 * 
	 * @param idLote
	 * @return TblLotesEnviosHist
	 */
	TblLotesEnviosHist convertLoteEnvioTOLoteEnvioHist(Long idLote);

	/**
	 * Inserta el lote historicos
	 * 
	 * @param loteHistorico
	 * @return Long
	 */
	Long insert(TblLotesEnviosHist v);

	
	/**
	 * Extraemos los id 
	 * 
	 * @param query
	 * @return List<Long>
	 */
	List<Long> getIdByQuery(TblLotesEnviosHistQuery query);

	/**
	 * Elimina el lote historicos
	 * 
	 * @param loteenvioid
	 */
	void eliminar(Long loteenvioid);

	/**
	 * Obtiene si para el mensaje si es de tipo multidestinatario
	 * 
	 * @param mensajeId
	 * @return
	 */
	boolean isMultidestinatario(Long mensajeId);

	/**
	 * Obtiene un Lote historico por Id
	 * 
	 * @param loteEnvioHistId
	 * @return TblAdjuntosHist
	 */
	TblLotesEnviosHist getLoteHistoricoById(Long loteEnvioHistId);
	
	
}
