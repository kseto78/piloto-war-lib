package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.sim.model.TblDestinatariosHist;
import es.minhap.sim.model.TblDestinatariosMensHist;

/**
 * <p>Interface que define los metodos para la gestion de destinatarios historico</p>
 * 
 * @author jgonzvil
 *
 */

public interface ServicioDestinatarioHistoricos {
	
	List<Long> getTodosIdDestinatarioCons(List<Long> listaMensajes) throws BusinessException;
	
	List<Long> getTodosIdDestinatarioMensajesCons(List<Long> listaMensajesHistoricosCons) throws BusinessException;
	
	List<List<TblDestinatariosHist>> getTblDestinatariosHist(List<Long> subList);

	Long insert(TblDestinatariosHist destinatarioHistorico);

	List<List<TblDestinatariosMensHist>> getTblDestinatariosMensajesHist(List<Long> subList);
	
	Long insert(TblDestinatariosMensHist destinatarioMensajeHistorico);

	void delete(Long destinatarioid);
	
	void deleteDestinatarioMensaje(Long destinatarioMensajeId);




}
