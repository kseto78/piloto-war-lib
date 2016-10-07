package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.MensajeBean;
import es.mpr.plataformamensajeria.beans.MensajeHistoricosBean;
import es.mpr.plataformamensajeria.model.MensajesHistoricosJPA;


/**
 * <p>Interface que define los metodos para la gestion de mensajes historico</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioMensajesHistoricos {
	
	List<MensajeBean> getMensajesHist(MensajeBean criterio);
	
	Boolean historificarMensajes(Integer loteEnvioId, Date fechaUltimoEnvio);
	
	Boolean consolidarMensajes(Integer loteEnvioId, Date fechaHistorificacion) throws BusinessException;
	
	List<MensajeBean> getTodosMensajesHist(String listIdsLotesEnvios, Date fechaHist);
	
	List<MensajeHistoricosBean> getTodosMensajesCons(String listIdsLotesEnvios, Date fechaHist) throws BusinessException;
	
	List<MensajesHistoricosJPA> getTodosMensajeJPACons(Integer servicioId, Integer loteID) throws BusinessException;
	
}
