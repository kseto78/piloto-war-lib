package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.MensajesAdjuntosBean;
import es.mpr.plataformamensajeria.beans.MensajesAdjuntosHistoricosBean;
import es.mpr.plataformamensajeria.model.MensajesAdjuntosHistoricosJPA;

/**
 * <p>Interface que define los metodos para la gestion de adjuntos historico</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioMensajesAdjuntosHistoricos {
	
	List<MensajesAdjuntosBean> getMensajesAdjuntosHist(MensajesAdjuntosBean criterio) throws BusinessException;
	
	List<MensajesAdjuntosBean> getTodosMensajesAdjuntosHist(String listIdsMensajes);
	
	List<MensajesAdjuntosHistoricosBean> getTodosMensajesAdjuntosCons(String listIdsMensajesHist, Integer servicioId) throws BusinessException;
	
	List<MensajesAdjuntosHistoricosJPA> getTodosMensajesAdjuntosJPACons(Integer servicioId, Integer loteID) throws BusinessException;
	
}
