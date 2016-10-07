package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.DestinatarioBean;
import es.mpr.plataformamensajeria.beans.DestinatarioHistoricosBean;
import es.mpr.plataformamensajeria.model.DestinatarioHistoricosJPA;

/**
 * <p>Interface que define los metodos para la gestion de destinatarios historico</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioDestinatarioHistoricos {
	
	List<DestinatarioBean> getDestinatarioHist(DestinatarioBean criterio) throws BusinessException;
	
	List<DestinatarioBean> getTodosDestinatarioHist(String listIdsMensajes);
	
	List<DestinatarioHistoricosBean> getTodosDestinatarioCons(String listIdsMensajes, Integer servicioId) throws BusinessException;
	
	List<DestinatarioHistoricosJPA> getTodosDestinatarioJPACons(Integer servicioId, Integer loteID) throws BusinessException;
	
}
