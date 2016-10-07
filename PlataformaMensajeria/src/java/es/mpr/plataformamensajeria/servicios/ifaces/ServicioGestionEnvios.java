package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.AdjuntoEmailBean;
import es.mpr.plataformamensajeria.beans.DestinatariosMensajesBean;
import es.mpr.plataformamensajeria.beans.DetalleEnvioBean;
import es.mpr.plataformamensajeria.beans.DetalleLoteBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioBean;
import es.mpr.plataformamensajeria.beans.HistoricoBean;
import es.mpr.plataformamensajeria.beans.MensajeBean;
import es.mpr.plataformamensajeria.model.GestionEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.views.ViewGestionEnviosJPA;

/**
 * <p>
 * Interface que define los m&eacute;todos para la gesti&oacute;n de envios
 * </p>
 * 
 * @author i-nercya
 * 
 */
@Service
public interface ServicioGestionEnvios {

	List<GestionEnvioBean> getGestionDeEnvios(GestionEnvioBean criterio) throws BusinessException;

	PaginatedList<GestionEnvioBean> getGestionDeEnvios(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioBean gestionEnvioBean, HttpServletRequest request, boolean porLotes) throws BusinessException;

	PaginatedList<MensajeBean> getMensajesLotes(int start, int size, String order, String columnSort, String criterio) throws BusinessException;

	PaginatedList<DestinatariosMensajesBean> getDestinatariosMensajes(int start, int size, String order, String columnSort, String criterio) throws BusinessException;

	PaginatedList<DestinatariosMensajesBean> getDestinatariosMensajesMultidestinatario(int start, int size, String order, String columnSort, String criterio) throws BusinessException;

	DetalleEnvioBean loadMensaje(String idEnvio) throws BusinessException;

	DetalleLoteBean loadLote(String idLote) throws BusinessException;

	// DetalleEnvioSMSBean loadEnvioSMS(String idEnvio) throws BusinessException;

	void anulaEnvio(String idEnvio, int tipoEnvio);

	ViewGestionEnviosJPA getEnvio(String string) throws BusinessException;

	AdjuntoEmailBean loadAdjunto(String idAdjunto, String idEmail);

	AdjuntoEmailBean loadAdjuntoBean(String idAdjunto, String idEmail);

	void reenviarEnvio(String idEnvio, int tipoEnvio);

	List<GestionEnvioBean> getGestionDeEnviosHist(GestionEnvioBean criterio) throws BusinessException;

	List<GestionEnvioBean> getTodosGestionEnviosHist(String listIdsMensajes, Integer servicioId) throws BusinessException;

	List<GestionEnviosHistoricosJPA> getTodosGestionEnviosJPAHist(Integer servicioId, Integer loteID) throws BusinessException;

	GestionEnvioBean getMensaje(String idMensaje) throws BusinessException;

	boolean isMultidestinatario(Integer idLote) throws BusinessException;

	DestinatariosMensajesBean getDestinatariosMensajes(String idDestinatariosMensajes)throws BusinessException;

	List<HistoricoBean> getHistoricosMensaje(String idMensaje, String idDestinatariosMensajes)throws BusinessException;

	List<ViewGestionEnviosJPA> getEnviosLote(String string) throws BusinessException;

	void anulaEnvioLote(String idLote);

	void reenviarEnvioLote(String idLote);

	PaginatedList<GestionEnvioBean> getGestionDeEnviosDestinatarios(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioBean criterio, HttpServletRequest request) throws BusinessException;

}
