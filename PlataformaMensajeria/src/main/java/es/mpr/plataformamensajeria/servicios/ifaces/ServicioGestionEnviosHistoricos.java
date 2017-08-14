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
 * </p>
 * 
 * @author jgonzvil
 * 
 */
public interface ServicioGestionEnviosHistoricos {

	PaginatedList<GestionEnvioHistoricoBean> getGestionDeEnviosHistoricos(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioHistoricoBean gestionEnvioBean, HttpServletRequest request, boolean porLotes) throws BusinessException;

	DetalleEnvioHistBean loadMensaje(String idEnvio) throws BusinessException;

	
	AdjuntoEmailHistoricosBean loadAdjunto(Long idAdjunto, Long idEmail);

	DetalleLoteBean loadLote(String idLote) throws BusinessException;

	PaginatedList<MensajeHistoricosBean> getMensajesLotes(int start, int size, Long loteId) throws BusinessException;

	boolean isMultidestinatario(Long mensajeId) throws BusinessException;

	PaginatedList<DestinatariosMensajesHistoricosBean> getDestinatariosMensajesMultidestinatario(int start, int size, Long idMensaje) throws BusinessException;

	PaginatedList<DestinatariosMensajesHistoricosBean> getDestinatariosMensajes(int start, int size, Long idMensaje) throws BusinessException;

	DestinatariosMensajesHistoricosBean getDestinatariosMensajesHistoricos(String idDestinatariosMensajes) throws BusinessException;

	GestionEnvioHistoricoBean getMensajeHistorico(String idMensaje) throws BusinessException;

	List<HistoricoHistBean> getHistoricosHistMensaje(String idMensaje, String idDestinatariosMensajes) throws BusinessException;

	PaginatedList<GestionEnvioHistoricoBean> getGestionDeEnviosDestinatariosHistoricos(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioHistoricoBean criterio, HttpServletRequest request) throws BusinessException;
	
	List<TblGestionEnviosHist> getTodosGestionEnviosCons(List<Long> listaMensajes) throws BusinessException;
	
	public List<TblGestionEnviosHist> getTblGestionEnviosHist(List<Long> subList)throws BusinessException;
	
	public Integer insert(TblGestionEnviosHist gestionEnvioHistorico)throws BusinessException;

	void delete(Long mensajeid);
	
	TblGestionEnviosHist getEnvio(String idMensaje) throws BusinessException;

}
