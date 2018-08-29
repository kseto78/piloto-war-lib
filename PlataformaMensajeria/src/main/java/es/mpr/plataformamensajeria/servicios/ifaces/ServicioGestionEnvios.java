package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.common.spring.ApplicationContextProvider;
import es.minhap.misim.bus.model.ViewMisim;
import es.minhap.sim.model.TblGestionEnvios;
import es.mpr.plataformamensajeria.beans.AdjuntoEmailBean;
import es.mpr.plataformamensajeria.beans.DestinatariosMensajesBean;
import es.mpr.plataformamensajeria.beans.DetalleEnvioBean;
import es.mpr.plataformamensajeria.beans.DetalleLoteBean;
import es.mpr.plataformamensajeria.beans.EnvioMensajesAplicacionBean;
import es.mpr.plataformamensajeria.beans.ViewMisimBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioBean;
import es.mpr.plataformamensajeria.beans.HistoricoBean;
import es.mpr.plataformamensajeria.beans.MensajeBean;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;

/**
 * <p>
 * Interface que define los m&eacute;todos para la gesti&oacute;n de envios
 * </p>.
 *
 * @author i-nercya
 */
public interface ServicioGestionEnvios {

	/**
	 * Obtener gestion de envios.
	 *
	 * @param inicio the inicio
	 * @param pagesize the pagesize
	 * @param order the order
	 * @param columnSort the column sort
	 * @param gestionEnvioBean the gestion envio bean
	 * @param request the request
	 * @param porLotes the por lotes
	 * @return gestion de envios
	 * @throws BusinessException the business exception
	 */
	PaginatedList<GestionEnvioBean> getGestionDeEnvios(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioBean gestionEnvioBean, HttpServletRequest request, boolean porLotes) throws BusinessException;

	/**
	 * Obtener mensajes lotes.
	 *
	 * @param start the start
	 * @param size the size
	 * @param loteId the lote id
	 * @return mensajes lotes
	 * @throws BusinessException the business exception
	 */
	PaginatedList<MensajeBean> getMensajesLotes(int start, int size, Long loteId) throws BusinessException;

	/**
	 * Obtener destinatarios mensajes.
	 *
	 * @param start the start
	 * @param size the size
	 * @param mensajeId the mensaje id
	 * @return destinatarios mensajes
	 * @throws BusinessException the business exception
	 */
	PaginatedList<DestinatariosMensajesBean> getDestinatariosMensajes(int start, int size, Long mensajeId) throws BusinessException;

	/**
	 * Obtener destinatarios mensajes multidestinatario.
	 *
	 * @param start the start
	 * @param size the size
	 * @param mensajeId the mensaje id
	 * @return destinatarios mensajes multidestinatario
	 * @throws BusinessException the business exception
	 */
	PaginatedList<DestinatariosMensajesBean> getDestinatariosMensajesMultidestinatario(int start, int size, Long mensajeId) throws BusinessException;

	/**
	 * Load mensaje.
	 *
	 * @param idEnvio the id envio
	 * @return the detalle envio bean
	 * @throws BusinessException the business exception
	 */
	DetalleEnvioBean loadMensaje(String idEnvio) throws BusinessException;

	/**
	 * Load lote.
	 *
	 * @param idLote the id lote
	 * @return the detalle lote bean
	 * @throws BusinessException the business exception
	 */
	DetalleLoteBean loadLote(String idLote) throws BusinessException;

	/**
	 * Anula envio.
	 *
	 * @param mensajeId the mensaje id
	 * @param applicationContext the application context
	 * @return true, if successful
	 */
	boolean anulaEnvio(Integer mensajeId,  ApplicationContextProvider applicationContext);

	/**
	 * Obtener envio.
	 *
	 * @param string the string
	 * @return envio
	 * @throws BusinessException the business exception
	 */
	TblGestionEnvios getEnvio(String string) throws BusinessException;

	/**
	 * Load adjunto.
	 *
	 * @param idAdjunto the id adjunto
	 * @param emailId the email id
	 * @return the adjunto email bean
	 */
	AdjuntoEmailBean loadAdjunto(Long idAdjunto, Long emailId);

	/**
	 * Reenviar envio.
	 *
	 * @param mensajeId the mensaje id
	 * @param applicationContext the application context
	 * @return the boolean
	 */
	Boolean reenviarEnvio(Integer mensajeId, ApplicationContextProvider applicationContext);

	/**
	 * Obtener mensaje.
	 *
	 * @param idMensaje the id mensaje
	 * @return mensaje
	 * @throws BusinessException the business exception
	 */
	GestionEnvioBean getMensaje(String idMensaje) throws BusinessException;

	/**
	 * Comprueba multidestinatario.
	 *
	 * @param idMensaje the id mensaje
	 * @return true, si es multidestinatario
	 * @throws BusinessException the business exception
	 */
	boolean isMultidestinatario(Long idMensaje) throws BusinessException;

	/**
	 * Obtener destinatarios mensajes.
	 *
	 * @param idDestinatariosMensajes the id destinatarios mensajes
	 * @return destinatarios mensajes
	 * @throws BusinessException the business exception
	 */
	DestinatariosMensajesBean getDestinatariosMensajes(String idDestinatariosMensajes)throws BusinessException;

	/**
	 * Obtener historicos mensaje.
	 *
	 * @param idMensaje the id mensaje
	 * @param idDestinatariosMensajes the id destinatarios mensajes
	 * @return historicos mensaje
	 * @throws BusinessException the business exception
	 */
	List<HistoricoBean> getHistoricosMensaje(String idMensaje, String idDestinatariosMensajes)throws BusinessException;

	/**
	 * Obtener envios lote.
	 *
	 * @param string the string
	 * @return envios lote
	 * @throws BusinessException the business exception
	 */
	List<TblGestionEnvios> getEnviosLote(String string) throws BusinessException;

	/**
	 * Anula envio lote.
	 *
	 * @param idLote the id lote
	 * @param applicationContext the application context
	 * @return true, if successful
	 */
	boolean anulaEnvioLote(Integer idLote,  ApplicationContextProvider applicationContext);

	/**
	 * Reenviar envio lote.
	 *
	 * @param idLote the id lote
	 * @param applicationContext the application context
	 * @return true, if successful
	 */
	boolean reenviarEnvioLote(Integer idLote,  ApplicationContextProvider applicationContext);

	/**
	 * Obtener gestion de envios destinatarios.
	 *
	 * @param inicio the inicio
	 * @param pagesize the pagesize
	 * @param order the order
	 * @param columnSort the column sort
	 * @param criterio the criterio
	 * @param request the request
	 * @return gestion de envios destinatarios
	 * @throws BusinessException the business exception
	 */
	PaginatedList<GestionEnvioBean> getGestionDeEnviosDestinatarios(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioBean criterio, HttpServletRequest request) throws BusinessException;

	/**
	 * Obtener intercambios misim.
	 *
	 * @param start the start
	 * @param size the size
	 * @param idLote the id lote
	 * @return intercambios misim
	 * @throws BusinessException the business exception
	 */
	PaginatedList<ViewMisimBean> getIntercambiosMisim(int start, int size, Long idLote) throws BusinessException;

	/**
	 * Load misim.
	 *
	 * @param idLote the id lote
	 * @return the view misim bean
	 * @throws BusinessException the business exception
	 */
	ViewMisimBean loadMisim(String idLote) throws BusinessException;

	/**
	 * Obtener view misim.
	 *
	 * @param idLote the id lote
	 * @return view misim
	 * @throws BusinessException the business exception
	 */
	ViewMisim getViewMisim(String idLote) throws BusinessException;
	
/**
 * Enviar peticion.
 *
 * @param applicationContext the application context
 * @param envioMensajesAplicacionBean the envio mensajes aplicacion bean
 * @return the respuesta
 */
//	nat
	Respuesta enviarPeticion(ApplicationContextProvider applicationContext, EnvioMensajesAplicacionBean envioMensajesAplicacionBean);
//	nat

}
