package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblMensajesHist;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.query.TblMensajesHistQuery;

public interface TblMensajesHistManager {

	/**
	 * Obtiene Los mensajes a partir de query
	 * 
	 * @param query
	 * @return
	 */
	public List<TblMensajesHist> getMensajesByQuery(TblMensajesHistQuery query);

	
	/**
	 * actualiza el mensaje 
	 * 
	 * @param mensaje
	 */
	public void update(TblMensajesHist mensaje);


	/**
	 * cuenta los mensajes by query
	 * 
	 * @param createQueryMensaje
	 * @return Integer
	 */
	public Integer countMensajesHistoricosByQuery(TblMensajesHistQuery createQueryMensaje);

	/**
	 * Se obtiene el id de servicio a partir del id mensaje
	 * 
	 * @param idMensaje
	 * @return TblServicios
	 */
	public TblServicios getServicioByIdMensaje(Long idMensaje);
	
}
