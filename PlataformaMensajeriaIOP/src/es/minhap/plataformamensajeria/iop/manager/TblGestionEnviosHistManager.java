package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblGestionEnviosHist;
import es.minhap.sim.query.TblGestionEnviosHistQuery;

public interface TblGestionEnviosHistManager {

	/**
	 * Inserta en tabla gestion de envios
	 * 
	 * @param TblGestionEnvios
	 * @return Integer
	 */
	Integer insertarGestionEnvios(TblGestionEnviosHist ge);
	

	/**
	 * obtiene listado según query
	 * 
	 * @param queryGE
	 * @return List<TblGestionEnvios>
	 */
	List<TblGestionEnviosHist> getGestionEnviosByQuery(TblGestionEnviosHistQuery queryGE);


	/**
	 * Obtiene un gestionEnvio a partir del idMensaje pasado
	 * 
	 * @param idMensaje
	 * @return TblMensajesHist
	 */
	List<TblGestionEnviosHist> convertGestionEnviosTOGestionEnviosHist(Long idMensaje);
	
	/**
	 * Elimina id en BBDD
	 * 
	 * @param idGestionEnvioHist
	 */
	void eliminar(Long idGestionEnvioHist);
	
	
	/**
	 * obtiene gestion envio por idMensaje
	 * 
	 * @param idMensaje
	 * @return 
	 */
	TblGestionEnviosHist getGestionEnviosById(Long idMensaje);
}
