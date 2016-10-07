package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.MensajesAdjuntosBean;
import es.mpr.plataformamensajeria.model.MensajesAdjuntosHistoricosJPA;

/**
 * <p>Interface que define los metodos para la gestion de adjuntos</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioMensajesAdjuntos {
	
	List<MensajesAdjuntosBean> getMensajesAdjuntosHist(MensajesAdjuntosBean criterio) throws BusinessException;
	
	List<MensajesAdjuntosBean> getTodosMensajesAdjuntosHist(String listIdsMensajes, Integer servicioId) throws BusinessException;
	
	List<MensajesAdjuntosHistoricosJPA> getTodosMensajesAdjuntosJPAHist(Integer servicioId, Integer loteID) throws BusinessException;
		
	
}
