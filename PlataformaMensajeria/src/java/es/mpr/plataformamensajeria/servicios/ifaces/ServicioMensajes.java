package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.MensajeBean;
import es.mpr.plataformamensajeria.model.MensajesHistoricosJPA;


/**
 * <p>Interface que define los metodos para la gestion de mensajes</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioMensajes {
	
	List<MensajeBean> getMensajesHist(MensajeBean criterio) throws BusinessException;
	
	Boolean historificarMensajes(Integer loteEnvioId, Date fechaUltimoEnvio) throws BusinessException;
	
	List<MensajeBean> getTodosMensajesHist(String listIdsLotesEnvios, Date fechaHist) throws BusinessException;
	
	List<MensajeBean> getMensajesByLoteEnvio(Integer loteEnvioId, Date fechaHist) throws BusinessException;
	
	List<MensajesHistoricosJPA> getTodosMensajesJPAHist(Integer servicioId, Integer loteID) throws BusinessException;
	
}
