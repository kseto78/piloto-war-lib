package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.LotesEnviosBean;
import es.mpr.plataformamensajeria.model.LotesEnviosHistoricosJPA;

/**
 * <p>Interface que define los metodos para la gestion de lotes de envios</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioLotesEnvios {
	
	List<LotesEnviosBean> getLotesEnviosHist(Integer servicioId, Date fechaHistorico) throws BusinessException;
	
	List<LotesEnviosHistoricosJPA> getLotesEnviosJPAHist(Integer servicioId, Date fechaHistorico) throws BusinessException;

	
	
}
