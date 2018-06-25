package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblDestinatariosMensHist;
import es.minhap.sim.query.TblDestinatariosMensHistQuery;

public interface TblDestinatariosMensajesHistManager {

	/**
	 * Obtiene un TblDestinatariosMensHistHist a partir del idMensaje pasado
	 * 
	 * @param idMensaje
	 * @return TblMensajesHist
	 */
	public List<TblDestinatariosMensHist> convertDestinatarioMensTODestinatarioMensHist(Long idMensaje);

	/**
	 * Inserta el  TblDestinatariosMensHist
	 * 
	 * @param m
	 * @return Long
	 */
	public Long insert(TblDestinatariosMensHist d);

	/**
	 * Elimina el  TblDestinatariosMensHist
	 * 
	 * @param m
	 */
	void eliminar(Long id);

	/**
	 * recupera los DetinatariosMensajesHist by query
	 * 
	 * @param query 
	 * @return 
	 */
	public List<TblDestinatariosMensHist> getDestinatarioMensHistByQuery(TblDestinatariosMensHistQuery query);

	/**
	 * recupera un DetinatariosMensajes
	 * 
	 * @param destinatariomensajeId 
	 * @return
	 */
	public TblDestinatariosMensHist getDestinatarioMensaje(Long destinatarioMensajeId);
	

}
