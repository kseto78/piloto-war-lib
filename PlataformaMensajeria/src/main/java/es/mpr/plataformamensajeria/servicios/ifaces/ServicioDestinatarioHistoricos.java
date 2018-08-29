package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.sim.model.TblDestinatariosHist;
import es.minhap.sim.model.TblDestinatariosMensHist;

/**
 * <p>Interface que define los metodos para la gestion de destinatarios historico</p>.
 *
 * @author jgonzvil
 */

public interface ServicioDestinatarioHistoricos {
	
	/**
	 * Obtener todos id destinatario cons.
	 *
	 * @param listaMensajes the lista mensajes
	 * @return todos id destinatario cons
	 * @throws BusinessException the business exception
	 */
	List<Long> getTodosIdDestinatarioCons(List<Long> listaMensajes) throws BusinessException;
	
	/**
	 * Obtener todos id destinatario mensajes cons.
	 *
	 * @param listaMensajesHistoricosCons the lista mensajes historicos cons
	 * @return todos id destinatario mensajes cons
	 * @throws BusinessException the business exception
	 */
	List<Long> getTodosIdDestinatarioMensajesCons(List<Long> listaMensajesHistoricosCons) throws BusinessException;
	
	/**
	 * Obtener tbl destinatarios hist.
	 *
	 * @param subList the sub list
	 * @return tbl destinatarios hist
	 */
	List<List<TblDestinatariosHist>> getTblDestinatariosHist(List<Long> subList);

	/**
	 * Insert.
	 *
	 * @param destinatarioHistorico the destinatario historico
	 * @return the long
	 */
	Long insert(TblDestinatariosHist destinatarioHistorico);

	/**
	 * Obtener tbl destinatarios mensajes hist.
	 *
	 * @param subList the sub list
	 * @return tbl destinatarios mensajes hist
	 */
	List<List<TblDestinatariosMensHist>> getTblDestinatariosMensajesHist(List<Long> subList);
	
	/**
	 * Insert.
	 *
	 * @param destinatarioMensajeHistorico the destinatario mensaje historico
	 * @return the long
	 */
	Long insert(TblDestinatariosMensHist destinatarioMensajeHistorico);

	/**
	 * Delete.
	 *
	 * @param destinatarioid the destinatarioid
	 */
	void delete(Long destinatarioid);
	
	/**
	 * Delete destinatario mensaje.
	 *
	 * @param destinatarioMensajeId the destinatario mensaje id
	 */
	void deleteDestinatarioMensaje(Long destinatarioMensajeId);




}
