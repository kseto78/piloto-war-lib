package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.TblMensajesAdjuntos;

public interface TblMensajesAdjuntosManager {

	/**
	 * Inserta en tabla Mensajes Adjuntos
	 * 
	 * @param TblMensajesAdjuntos
	 * @return id
	 */
	public Long insertarMensajesAdjuntos(TblMensajesAdjuntos menAd);

}
