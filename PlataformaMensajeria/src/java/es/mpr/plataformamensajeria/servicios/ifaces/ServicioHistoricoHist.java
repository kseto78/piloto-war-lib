package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.HistoricoBean;
import es.mpr.plataformamensajeria.beans.HistoricoHistBean;
import es.mpr.plataformamensajeria.model.HistoricoHistJPA;

/**
 * <p>Interface que define los metodos para la gestion de historico</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioHistoricoHist {
	
	List<HistoricoBean> getDestinatarioHist(HistoricoBean criterio) throws BusinessException;
	
	List<HistoricoBean> getTodosHistoricosHist(String listIdsMensajes);
	
	List<HistoricoHistBean> getTodosHistoricosCons(String listIdsMensajes, Integer servicioId) throws BusinessException;
	
	List<HistoricoHistJPA> getTodosHistoricosJPACons(Integer servicioId, Integer loteID) throws BusinessException;
	
}
