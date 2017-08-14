package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorHistoricosHist;
import es.minhap.plataformamensajeria.iop.manager.TblHistoricosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblHistoricosManager;
import es.minhap.sim.model.TblHistoricosHist;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioHistoricoHist;

/**
 * <p>Maneja la persistencia de historico a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
@Service("servicioHistoricoHistImpl")
public class ServicioHistoricoHistImpl implements ServicioHistoricoHist{
	Logger logger = Logger.getLogger(ServicioMensajesAdjuntosHistoricosImpl.class);
	
	@Resource(name = "TblHistoricosHistManagerImpl")
	private TblHistoricosHistManager tblHistoricosHistManager;
	
	@Resource(name = "TblHistoricosManagerImpl")
	private TblHistoricosManager tblHistoricosManager;
	
	@Resource(name = "QueryExecutorHistoricosHistImpl")
	private QueryExecutorHistoricosHist queryExecutorHistoricosHist;
	
	private static final Integer MAX = 5000;
	
	
////MIGRADO
	@Override
	public List<Long> getTodosIdHistoricosCons(List<Long> listaMensajesHistoricosCons) throws BusinessException{

		List<Long> result = new ArrayList<>();
		try {
			result = queryExecutorHistoricosHist.getIdHistoricosCons(listaMensajesHistoricosCons);
			
		} catch (Exception e) {
			logger.error("ServicioHistoricosHist.getTodosIdHistoricosCons", e);
			throw new BusinessException(e, "errors.job.cons.getMensajesAdjuntos");
		}
		return result;
	}
	
	
	////MIGRADO
	@Override
	public List<List<TblHistoricosHist>> getTblHistoricosHist(List<Long> subList) {
		List<List<TblHistoricosHist>> res = new ArrayList<>();
		Integer total = queryExecutorHistoricosHist.countConvertHistoricoTOHistoricoHist(subList);

		Integer partes = (int) Math.ceil((double) total / MAX);
		for (int i = 0; i < partes; i++) {
			List<TblHistoricosHist> listaTblHistoricosHist = queryExecutorHistoricosHist.convertHistoricoTOHistoricoHist(subList, MAX, i * MAX);
			if (null == listaTblHistoricosHist) {
				listaTblHistoricosHist = new ArrayList<>();
			}
			res.add(listaTblHistoricosHist);
		}
		return res;
	}
	
////MIGRADO
	@Override
	public Long insert(TblHistoricosHist historicosHistorico) {
		return tblHistoricosHistManager.insert(historicosHistorico);
	}
	
////MIGRADO
	@Override
	public void detele(Long historicoid) {
		tblHistoricosManager.delete(historicoid);
		
	}
	


}
