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
	public TblLotesEnviosHist convertLoteEnvioTOLoteEnvioHist(Long idLote);

	/**
	 * Inserta el lote historicos
	 * 
	 * @param loteHistorico
	 * @return Long
	 */
	public Long insert(TblLotesEnviosHist v);

	
	/**
	 * Extraemos los id 
	 * 
	 * @param query
	 * @return List<Long>
	 */
	public List<Long> getIdByQuery(TblLotesEnviosHistQuery query);

	/**
	 * Elimina el lote historicos
	 * 
	 * @param loteenvioid
	 */
	public void eliminar(Long loteenvioid);

	/**
	 * Obtiene si para el mensaje si es de tipo multidestinatario
	 * 
	 * @param mensajeId
	 * @return
	 */
	public boolean isMultidestinatario(Long mensajeId);

	/**
	 * Obtiene un Lote historico por Id
	 * 
	 * @param loteEnvioHistId
	 * @return TblAdjuntosHist
	 */
	public TblLotesEnviosHist getLoteHistoricoById(Long loteEnvioHistId);
	
	
}
