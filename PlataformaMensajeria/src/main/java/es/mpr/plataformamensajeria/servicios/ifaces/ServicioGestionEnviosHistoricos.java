package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.sim.model.TblGestionEnviosHist;
import es.mpr.plataformamensajeria.beans.AdjuntoEmailHistoricosBean;
import es.mpr.plataformamensajeria.beans.DestinatariosMensajesHistoricosBean;
import es.mpr.plataformamensajeria.beans.DetalleEnvioHistBean;
import es.mpr.plataformamensajeria.beans.DetalleLoteBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioHistoricoBean;
import es.mpr.plataformamensajeria.beans.HistoricoHistBean;
import es.mpr.plataformamensajeria.beans.MensajeHistoricosBean;

/**
 * <p>
 * Interface que define los metodos para la gestion de envios historicos
 * </p>.
 *
 * @author jgonzvil
 */
public interface ServicioGestionEnviosHistoricos {

	/**
	 * Obtener gestion de envios historicos.
	 *
	 * @param inicio the inicio
	 * @param pagesize the pagesize
	 * @param order the order
	 * @param columnSort the column sort
	 * @param gestionEnvioBean the gestion envio bean
	 * @param request the request
	 * @param porLotes the por lotes
	 * @return gestion de envios historicos
	 * @throws BusinessException the business exception
	 */
	PaginatedList<GestionEnvioHistoricoBean> getGestionDeEnviosHistoricos(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioHistoricoBean gestionEnvioBean, HttpServletRequest request, boolean porLotes) throws BusinessException;

	/**
	 * Load mensaje.
	 *
	 * @param idEnvio the id envio
	 * @return the detalle envio hist bean
	 * @throws BusinessException the business exception
	 */
	DetalleEnvioHistBean loadMensaje(String idEnvio) throws BusinessException;

	
	/**
	 * Load adjunto.
	 *
	 * @param idAdjunto the id adjunto
	 * @param idEmail the id email
	 * @return the adjunto email historicos bean
	 */
	AdjuntoEmailHistoricosBean loadAdjunto(Long idAdjunto, Long idEmail);

	/**
	 * Load lote.
	 *
	 * @param idLote the id lote
	 * @return the detalle lote bean
	 * @throws BusinessException the business exception
	 */
	DetalleLoteBean loadLote(String idLote) throws BusinessException;

	/**
	 * Obtener mensajes lotes.
	 *
	 * @param start the start
	 * @param size the size
	 * @param loteId the lote id
	 * @return mensajes lotes
	 * @throws BusinessException the business exception
	 */
	PaginatedList<MensajeHistoricosBean> getMensajesLotes(int start, int size, Long loteId) throws BusinessException;

	/**
	 * Comprueba multidestinatario.
	 *
	 * @param mensajeId the mensaje id
	 * @return true, si es multidestinatario
	 * @throws BusinessException the business exception
	 */
	boolean isMultidestinatario(Long mensajeId) throws BusinessException;

	/**
	 * Obtener destinatarios mensajes multidestinatario.
	 *
	 * @param start the start
	 * @param size the size
	 * @param idMensaje the id mensaje
	 * @return destinatarios mensajes multidestinatario
	 * @throws BusinessException the business exception
	 */
	PaginatedList<DestinatariosMensajesHistoricosBean> getDestinatariosMensajesMultidestinatario(int start, int size, Long idMensaje) throws BusinessException;

	/**
	 * Obtener destinatarios mensajes.
	 *
	 * @param start the start
	 * @param size the size
	 * @param idMensaje the id mensaje
	 * @return destinatarios mensajes
	 * @throws BusinessException the business exception
	 */
	PaginatedList<DestinatariosMensajesHistoricosBean> getDestinatariosMensajes(int start, int size, Long idMensaje) throws BusinessException;

	/**
	 * Obtener destinatarios mensajes historicos.
	 *
	 * @param idDestinatariosMensajes the id destinatarios mensajes
	 * @return destinatarios mensajes historicos
	 * @throws BusinessException the business exception
	 */
	DestinatariosMensajesHistoricosBean getDestinatariosMensajesHistoricos(String idDestinatariosMensajes) throws BusinessException;

	/**
	 * Obtener mensaje historico.
	 *
	 * @param idMensaje the id mensaje
	 * @return mensaje historico
	 * @throws BusinessException the business exception
	 */
	GestionEnvioHistoricoBean getMensajeHistorico(String idMensaje) throws BusinessException;

	/**
	 * Obtener historicos hist mensaje.
	 *
	 * @param idMensaje the id mensaje
	 * @param idDestinatariosMensajes the id destinatarios mensajes
	 * @return historicos hist mensaje
	 * @throws BusinessException the business exception
	 */
	List<HistoricoHistBean> getHistoricosHistMensaje(String idMensaje, String idDestinatariosMensajes) throws BusinessException;

	/**
	 * Obtener gestion de envios destinatarios historicos.
	 *
	 * @param inicio the inicio
	 * @param pagesize the pagesize
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @param request the request
	 * @return gestion de envios destinatarios historicos
	 * @throws BusinessException the business exception
	 */
	PaginatedList<GestionEnvioHistoricoBean> getGestionDeEnviosDestinatariosHistoricos(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioHistoricoBean criterio, HttpServletRequest request) throws BusinessException;
	
	/**
	 * Obtener todos gestion envios cons.
	 *
	 * @param listaMensajes the lista mensajes
	 * @return todos gestion envios cons
	 * @throws BusinessException the business exception
	 */
	List<TblGestionEnviosHist> getTodosGestionEnviosCons(List<Long> listaMensajes) throws BusinessException;
	
	/**
	 * Obtener tbl gestion envios hist.
	 *
	 * @param subList the sub list
	 * @return tbl gestion envios hist
	 * @throws BusinessException the business exception
	 */
	List<TblGestionEnviosHist> getTblGestionEnviosHist(List<Long> subList)throws BusinessException;
	
	/**
	 * Insert.
	 *
	 * @param gestionEnvioHistorico the gestion envio historico
	 * @return the integer
	 * @throws BusinessException the business exception
	 */
	Integer insert(TblGestionEnviosHist gestionEnvioHistorico)throws BusinessException;

	/**
	 * Delete.
	 *
	 * @param mensajeid the mensajeid
	 */
	void delete(Long mensajeid);
	
	/**
	 * Obtener envio.
	 *
	 * @param idMensaje the id mensaje
	 * @return envio
	 * @throws BusinessException the business exception
	 */
	TblGestionEnviosHist getEnvio(String idMensaje) throws BusinessException;


}
