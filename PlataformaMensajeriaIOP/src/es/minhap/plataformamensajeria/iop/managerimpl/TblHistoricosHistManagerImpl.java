package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblHistoricosHistManager;
import es.minhap.sim.dao.TblHistoricosDAO;
import es.minhap.sim.dao.TblHistoricosHistDAO;
import es.minhap.sim.model.TblHistoricos;
import es.minhap.sim.model.TblHistoricosHist;
import es.minhap.sim.query.TblHistoricosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblHistoricosHistManagerImpl")
public class TblHistoricosHistManagerImpl implements TblHistoricosHistManager {

	@Resource
	private TblHistoricosDAO historicosDAO;
	
	@Resource
	private TblHistoricosHistDAO historicosHistDAO;
	
	@Override
	public List<TblHistoricosHist> convertHistoricoTOHistoricoHist(Long idMensaje) {
		List<TblHistoricosHist> res = new ArrayList<>();
		TblHistoricosQuery query = new TblHistoricosQuery();
		query.setMensajeid(idMensaje);
		
		List<TblHistoricos> lista = historicosDAO.search(query).getResults();
		
		for (TblHistoricos h : lista) {
			res.add(convertTOHist(h));
		}
		return res;	
	}

	@Override
	@Transactional
	public Long insert(TblHistoricosHist h) {
		return historicosHistDAO.insert(h);
	}

	@Override
	@Transactional
	public void eliminar (Long id) {
		historicosHistDAO.delete(id);
	}

	private TblHistoricosHist convertTOHist(TblHistoricos historico) {
		TblHistoricosHist h = null;
		if (null != historico){
			h = new TblHistoricosHist();
			h.setDescripcion(historico.getDescripcion());
			h.setDestinatariosmensajes(historico.getDestinatariosmensajes());
			h.setFecha(historico.getFecha());
			h.setHistoricoid(historico.getHistoricoid());
			h.setMensajeid(historico.getMensajeid());
			h.setServidorid(historico.getServidorid());
			h.setSubestadoid(historico.getSubestadoid());
			h.setTblEstados(historico.getTblEstados());
			h.setTblPlanificaciones(historico.getTblPlanificaciones());
			
		}
		return h;
	}

}
