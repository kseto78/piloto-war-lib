package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.DestinatarioBean;
import es.mpr.plataformamensajeria.model.DestinatarioHistoricosJPA;
import es.mpr.plataformamensajeria.model.DestinatariosMensajesHistoricosJPA;

/**
 * <p>Interface que define los metodos para la gestion de destinatarios</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioDestinatario {
	
	List<DestinatarioBean> getDestinatarioHist(DestinatarioBean criterio) throws BusinessException;
	
	List<DestinatarioBean> getTodosDestinatarioHist(String listIdsMensajes, Integer servicioId) throws BusinessException;
	
	List<DestinatarioHistoricosJPA> getTodosDestinatarioJPAHist(Integer servicioId, Integer loteID) throws BusinessException;

	List<DestinatariosMensajesHistoricosJPA> getTodosDestinatariosMensajes(Integer servicioId, Integer loteEnvioId) throws BusinessException ;
	
	
}
