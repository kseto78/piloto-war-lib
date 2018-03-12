package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

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
import es.mpr.plataformamensajeria.beans.PeticionBean;
import es.mpr.plataformamensajeria.beans.ViewMisimBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioBean;
import es.mpr.plataformamensajeria.beans.HistoricoBean;
import es.mpr.plataformamensajeria.beans.MensajeBean;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;

/**
 * <p>
 * Interface que define los m&eacute;todos para la gesti&oacute;n de envios
 * </p>
 * 
 * @author i-nercya
 * 
 */
public interface ServicioGestionEnvios {

	PaginatedList<GestionEnvioBean> getGestionDeEnvios(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioBean gestionEnvioBean, HttpServletRequest request, boolean porLotes) throws BusinessException;

	PaginatedList<MensajeBean> getMensajesLotes(int start, int size, Long loteId) throws BusinessException;

	PaginatedList<DestinatariosMensajesBean> getDestinatariosMensajes(int start, int size, Long mensajeId) throws BusinessException;

	PaginatedList<DestinatariosMensajesBean> getDestinatariosMensajesMultidestinatario(int start, int size, Long mensajeId) throws BusinessException;

	DetalleEnvioBean loadMensaje(String idEnvio) throws BusinessException;

	DetalleLoteBean loadLote(String idLote) throws BusinessException;

	boolean anulaEnvio(Integer mensajeId,  ApplicationContextProvider applicationContext);

	TblGestionEnvios getEnvio(String string) throws BusinessException;

	AdjuntoEmailBean loadAdjunto(Long idAdjunto, Long emailId);

	Boolean reenviarEnvio(Integer mensajeId, ApplicationContextProvider applicationContext);

	GestionEnvioBean getMensaje(String idMensaje) throws BusinessException;

	boolean isMultidestinatario(Long idMensaje) throws BusinessException;

	DestinatariosMensajesBean getDestinatariosMensajes(String idDestinatariosMensajes)throws BusinessException;

	List<HistoricoBean> getHistoricosMensaje(String idMensaje, String idDestinatariosMensajes)throws BusinessException;

	List<TblGestionEnvios> getEnviosLote(String string) throws BusinessException;

	boolean anulaEnvioLote(Integer idLote,  ApplicationContextProvider applicationContext);

	boolean reenviarEnvioLote(Integer idLote,  ApplicationContextProvider applicationContext);

	PaginatedList<GestionEnvioBean> getGestionDeEnviosDestinatarios(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioBean criterio, HttpServletRequest request) throws BusinessException;

	PaginatedList<ViewMisimBean> getIntercambiosMisim(int start, int size, Long idLote) throws BusinessException;

	ViewMisimBean loadMisim(String idLote) throws BusinessException;

	ViewMisim getViewMisim(String idLote) throws BusinessException;
	
//	nat
	Respuesta enviarPeticion(ApplicationContextProvider applicationContext, EnvioMensajesAplicacionBean envioMensajesAplicacionBean);
//	nat

}
