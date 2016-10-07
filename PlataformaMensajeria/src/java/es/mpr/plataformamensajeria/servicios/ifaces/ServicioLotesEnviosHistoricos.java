package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.HistoricoDTO;
import es.mpr.plataformamensajeria.beans.LotesEnviosHistoricosBean;
import es.mpr.plataformamensajeria.model.LotesEnviosHistoricosJPA;

/**
 * <p>Interface que define los metodos para la gestion de lotes de envios historicos</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioLotesEnviosHistoricos {
	
	List<LotesEnviosHistoricosBean> getLotesEnviosHist(Integer servicioId, Date fechaHistorico) throws BusinessException;
	
	List<LotesEnviosHistoricosJPA> getLotesEnviosJPAHist(Integer servicioId, Date fechaHistorico) throws BusinessException;
	
	Map<LotesEnviosHistoricosJPA,HistoricoDTO> getLotesEnviosJPAHistMap(Integer servicioId, Date fechaHistorico
				,ServicioAdjuntoEmailHistoricos servicioAdjuntoEmailHistoricos,
				ServicioDestinatarioHistoricos servicioDestinatarioHistoricos,
				ServicioHistoricoHist servicioHistoricoHist,
				ServicioGestionEnviosHistoricos servicioGestionEnviosHistoricos,
				ServicioMensajesAdjuntosHistoricos servicioMensajesAdjuntosHistoricos) throws BusinessException ;
}
