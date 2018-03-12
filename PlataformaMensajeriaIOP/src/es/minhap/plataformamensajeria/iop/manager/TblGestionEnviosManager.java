package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblGestionEnvios;
import es.minhap.sim.query.TblGestionEnviosQuery;

public interface TblGestionEnviosManager {

	/**
	 * Inserta en tabla gestion de envios
	 * 
	 * @param TblGestionEnvios
	 * @return Integer
	 */
	Integer insertarGestionEnvios(TblGestionEnvios ge);
	
	/**
	 * Actualiza en tabla gestion de envios
	 * 
	 * @param TblGestionEnvios
	 */
	void actualizarGestionEnvios(TblGestionEnvios ge);
	
	/**
	 * obtiene si existe ya una entrada con para ese mensaje
	 * 
	 * @param TblGestionEnviosQuery
	 * @return TblGestionEnvios
	 */
	TblGestionEnvios getGestionEnvios(TblGestionEnviosQuery query);

	List<TblGestionEnvios> getEnviosLote(String idLote);
	
		
	/**
	 * Elimina Gestion envios
	 * 
	 * @param mensajeid
	 */
	void delete(Long mensajeid);

	/**
	 * obtiene Gestion envio por id
	 * 
	 * @param mensajeid
	 * @return
	 */
	TblGestionEnvios getById(Long mensajeid);

}
