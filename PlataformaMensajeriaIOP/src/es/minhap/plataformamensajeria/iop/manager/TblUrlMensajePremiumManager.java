package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.TblUrlMensajePremium;


public interface TblUrlMensajePremiumManager {
	/**
	 * recupera la url premium del mensaje
	 * 
	 * @param mensajeId
	 * @return TblUrlMensajePremium
	 */
	TblUrlMensajePremium getUrlByMensaje(Long mensajeId);

	
	/**
	 * actualiza el registro
	 * 
	 * @param tbl
	*/
	void update(TblUrlMensajePremium tbl);
	
	/**
	 * inserta el registro
	 * 
	 * @param tbl
	*/
	void insert(TblUrlMensajePremium tbl);
	
}
