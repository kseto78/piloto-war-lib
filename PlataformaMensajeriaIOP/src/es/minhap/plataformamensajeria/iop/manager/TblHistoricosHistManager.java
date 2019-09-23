package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblGestionEnviosHist;
import es.minhap.sim.model.TblHistoricosHist;

public interface TblHistoricosHistManager {

	/**
	 * Obtiene un historico a partir del idMensaje pasado
	 * 
	 * @param idMensaje
	 * @return TblMensajesHist
	 */
	public List<TblHistoricosHist> convertHistoricoTOHistoricoHist(Long idMensaje);

	/**
	 * Inserta el  historicos
	 * 
	 * @param m
	 * @return Long
	 */
	public Long insert(TblHistoricosHist d);

	/**
	 * Elimina el  historicos
	 * 
	 * @param id
	 */
	void eliminar(Long id);
	
	/**
	 * obtiene historico hist por idMensaje
	 * 
	 * @param idMensaje
	 * @return 
	 */
	TblHistoricosHist getHistoricosHistById(Long HISTORICOID);
	

}
