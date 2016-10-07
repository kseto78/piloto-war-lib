package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.mpr.plataformamensajeria.beans.AdjuntoEmailHistoricosBean;
import es.mpr.plataformamensajeria.beans.DestinatariosMensajesHistoricosBean;
import es.mpr.plataformamensajeria.beans.DetalleEnvioHistBean;
import es.mpr.plataformamensajeria.beans.DetalleLoteBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioHistoricoBean;
import es.mpr.plataformamensajeria.beans.HistoricoHistBean;
import es.mpr.plataformamensajeria.beans.MensajeHistoricosBean;
import es.mpr.plataformamensajeria.model.GestionEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.views.ViewGestionEnviosHistJPA;

/**
 * <p>
 * Interface que define los metodos para la gestion de envios historicos
 * </p>
 * 
 * @author jgonzvil
 * 
 */
@Service
public interface ServicioGestionEnviosHistoricos {

	List<GestionEnvioHistoricoBean> getGestionDeEnviosHistoricos(GestionEnvioHistoricoBean criterio) throws BusinessException;

	PaginatedList<GestionEnvioHistoricoBean> getGestionDeEnviosHistoricos(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioHistoricoBean gestionEnvioBean, HttpServletRequest request, boolean porLotes) throws BusinessException;

	DetalleEnvioHistBean loadMensaje(String idEnvio) throws BusinessException;

	ViewGestionEnviosHistJPA getEnvioHistorico(String string) throws BusinessException;

	AdjuntoEmailHistoricosBean loadAdjunto(String idAdjunto, String idEmail);

	AdjuntoEmailHistoricosBean loadAdjuntoBean(String idAdjunto, String idEmail);

	List<GestionEnvioHistoricoBean> getTodosGestionEnviosCons(String listIdsMensajes, Integer servicioId) throws BusinessException;

	List<GestionEnviosHistoricosJPA> getTodosGestionEnviosJPACons(Integer servicioId, Integer loteID) throws BusinessException;

	DetalleLoteBean loadLote(String idLote) throws BusinessException;

	PaginatedList<MensajeHistoricosBean> getMensajesLotes(int start, int size, String order, String columnSort, String criterio) throws BusinessException;

	boolean isMultidestinatario(Integer idLote) throws BusinessException;

	PaginatedList<DestinatariosMensajesHistoricosBean> getDestinatariosMensajesMultidestinatario(int start, int size, String order, String columnSort, String idMensaje) throws BusinessException;

	PaginatedList<DestinatariosMensajesHistoricosBean> getDestinatariosMensajes(int start, int size, String order, String columnSort, String idMensaje) throws BusinessException;

	DestinatariosMensajesHistoricosBean getDestinatariosMensajesHistoricos(String idDestinatariosMensajes) throws BusinessException;

	GestionEnvioHistoricoBean getMensajeHistorico(String idMensaje) throws BusinessException;

	List<HistoricoHistBean> getHistoricosHistMensaje(String idMensaje, String idDestinatariosMensajes) throws BusinessException;

	PaginatedList<GestionEnvioHistoricoBean> getGestionDeEnviosDestinatariosHistoricos(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioHistoricoBean criterio, HttpServletRequest request) throws BusinessException;
}
