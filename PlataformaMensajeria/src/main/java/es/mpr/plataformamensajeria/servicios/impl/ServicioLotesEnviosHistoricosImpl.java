package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorLotesEnviosHist;
import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosManager;
import es.minhap.sim.model.TblLotesEnviosHist;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioLotesEnviosHistoricos;

/**
 * <p>Maneja la persistencia de lotes de envios a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
@Service("servicioLotesEnviosHistoricosImpl")
public class ServicioLotesEnviosHistoricosImpl implements ServicioLotesEnviosHistoricos{
//	private static Logger logger = Logger.getLogger(ServicioLotesEnviosImpl.class);
	
	@Resource(name = "TblLotesEnviosHistManagerImpl")
	private TblLotesEnviosHistManager tblLotesEnviosHistManager;
	
	@Resource(name = "TblLotesEnviosManagerImpl")
	private TblLotesEnviosManager tblLotesEnviosManager;
	
	@Resource(name = "QueryExecutorLotesEnviosHistImpl")
	private QueryExecutorLotesEnviosHist queryExecutorLotesEnviosHist;
	
	
	private static final Integer MAX = 1000;

	
	////MIGRADO
	@Override
	public TblLotesEnviosHist getLoteEnvioHist(Long loteEnvio) {
		return tblLotesEnviosHistManager.convertLoteEnvioTOLoteEnvioHist(loteEnvio);
		
	}
	
	////MIGRADO
	@Override
	public Long insert(TblLotesEnviosHist loteHistorico) {
		return tblLotesEnviosHistManager.insert(loteHistorico);
	}
	
////MIGRADO
	@Override
	public void delete(Long loteenvioid) {
		tblLotesEnviosManager.delete(loteenvioid);
	}
	
	////MIGRADO
	@Override
	public void deleteHist(Long loteenvioid) {
		tblLotesEnviosHistManager.eliminar(loteenvioid);
	}
	
	////MIGRADO
	@Override
	public List<List<Long>> getListasLotesEnviosHistoricos(Integer servicioId, Date time) {
		List<List<Long>> res = new ArrayList<>();
				
		Integer totalLotes = queryExecutorLotesEnviosHist.countLotesByServicioYFecha(servicioId.longValue(), time);
		
		Integer partes = (int) Math.ceil((double) totalLotes / MAX);
		
		for (int i = 0; i < partes; i++) {
			List<Long> listaId = queryExecutorLotesEnviosHist.getIdLotesByServicio(servicioId.longValue(), time, MAX, i*MAX);
			if (null == listaId){
				listaId = new ArrayList<>();
			}
			res.add(listaId);
		}
		return res;
	}
	
	
	


}
