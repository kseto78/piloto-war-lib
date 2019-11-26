package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.sim.model.TblLotesEnviosHist;
import es.minhap.sim.model.TblMensajesAdjuntosHist;

/**
 * <p>Interface que define los metodos para la gestion de adjuntos historico</p>.
 *
 * @author jgonzvil
 */

public interface ServicioMensajesAdjuntosHistoricos {
	
	/**
	 * Obtener id mensajes adjuntos cons.
	 *
	 * @param listaMensajes the lista mensajes
	 * @return id mensajes adjuntos cons
	 * @throws BusinessException the business exception
	 */
	List<Long> getIdMensajesAdjuntosCons(List<Long> listaMensajes) throws BusinessException;
	
	/**
	 * Obtener tbl mensajes adjuntos hist.
	 *
	 * @param subList the sub list
	 * @param loteHistorico the lote historico
	 * @return tbl mensajes adjuntos hist
	 */
	List<List<TblMensajesAdjuntosHist>> getTblMensajesAdjuntosHist(List<Long> subList, TblLotesEnviosHist loteHistorico);

	/**
	 * Insert.
	 *
	 * @param mensajeAdjuntoHistorico the mensaje adjunto historico
	 * @return the long
	 */
	Long insert(TblMensajesAdjuntosHist mensajeAdjuntoHistorico);

	/**
	 * Delete.
	 *
	 * @param idMensajeAdjunto the id mensaje adjunto
	 */
	void delete(Long idMensajeAdjunto);

	TblMensajesAdjuntosHist getMensajesAdjuntosHistById(Long mensajeadjuntoid);

}
