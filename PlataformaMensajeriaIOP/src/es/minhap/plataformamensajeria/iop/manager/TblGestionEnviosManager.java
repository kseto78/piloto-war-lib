package es.minhap.plataformamensajeria.iop.manager;

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


}
