package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.AdjuntoEmailBean;
import es.mpr.plataformamensajeria.model.AdjuntoEmailHistoricosJPA;

/**
 * <p>Interface que define los metodos para la gestion de adjuntos</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioAdjuntoEmail {
	
	List<AdjuntoEmailBean> getAdjuntosHist(AdjuntoEmailBean criterio) throws BusinessException;
	
	List<AdjuntoEmailBean> getTodosAdjuntosHist(String listIdsAdjuntos, Integer servicioId) throws BusinessException;
	
	List<AdjuntoEmailHistoricosJPA> getTodosAdjuntosJPAHist(Integer servicioId, Integer loteID) throws BusinessException;
	
}
