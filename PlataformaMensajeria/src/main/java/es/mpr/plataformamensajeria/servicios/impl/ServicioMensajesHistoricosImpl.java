package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajes;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajesHist;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.sim.model.TblLotesEnviosHist;
import es.minhap.sim.model.TblMensajesHist;
import es.minhap.sim.query.TblLotesEnviosHistQuery;
import es.minhap.sim.query.TblMensajesHistQuery;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajesHistoricos;

/**
 * <p>Maneja la persistencia de mensajes historico a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
@Service("servicioMensajesHistoricosImpl")
public class ServicioMensajesHistoricosImpl implements ServicioMensajesHistoricos{
	
//	private static Logger logger = Logger.getLogger(ServicioMensajesHistoricosImpl.class);
	
	@Resource(name = "TblMensajesHistManagerImpl")
	private TblMensajesHistManager tblMensajesHistManager;
	
	@Resource(name = "TblMensajesManagerImpl")
	private TblMensajesManager tblMensajesManager;
	
	@Resource(name = "QueryExecutorMensajesImpl")
	private QueryExecutorMensajes queryExecutorMensajes;
	
	@Resource(name = "QueryExecutorMensajesHistImpl")
	private QueryExecutorMensajesHist queryExecutorMensajesHist;
	
	
	private static final Integer MAX = 1000;
	
	///MIGRADO
	@Override
	public List<TblMensajesHist> getTblMensajesHist(List<Long> subList, TblLotesEnviosHist loteHistorico) {
		return queryExecutorMensajesHist.convertMensajeTOMensajeHist(subList, loteHistorico);
	}
	
	///MIGRADO
	@Override
	public Long insert(TblMensajesHist mensajeHistorico) {
		return tblMensajesHistManager.insert(mensajeHistorico);
	}
	
	///MIGRADO
	@Override
	public void delete(Long mensajeid) {
		tblMensajesManager.delete(mensajeid);
	}
	
	///MIGRADO
	@Override
	public List<List<Long>> getTodosMensajesLoteHistorificar(Long loteEnvioID, Date fecha) throws BusinessException {
		List<List<Long>> res = new ArrayList<>();
		TblMensajesHistQuery query = new TblMensajesHistQuery();
		TblLotesEnviosHistQuery queryLotes = new TblLotesEnviosHistQuery();
		queryLotes.setLoteenvioid(loteEnvioID);
		query.setTblLotesEnviosHist(queryLotes);
		Integer totalMensajes = queryExecutorMensajesHist.countMensajesHistorificacion(loteEnvioID, null);
				
		if (totalMensajes == queryExecutorMensajesHist.countMensajesHistorificacion(loteEnvioID, fecha)){
			Integer partes = (int) Math.ceil((double) totalMensajes / MAX);
			for (int i = 0; i < partes; i++) {
				List<Long> listaId = queryExecutorMensajesHist.getIdMensajesByLote(loteEnvioID, MAX, i*MAX);
				if (null == listaId){
					listaId = new ArrayList<>();
				}
				res.add(listaId);
			}
			return res;
		}else{
			List<Long> listaVacia = new ArrayList<>();
			res.add(listaVacia);
			return res;
		}
	}

}
