package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.ViewHistoricoHist;
import es.minhap.sim.model.ViewHistoricoHistMultidest;
import es.minhap.sim.query.ViewHistoricoHistMultidestQuery;
import es.minhap.sim.query.ViewHistoricoHistQuery;

public interface ViewHistoricoHistManager {

	/**
	 * Se obtiene historico by query
	 * 
	 * @param ViewHistoricoQuery
	 * @return List
	 */
	public List<ViewHistoricoHistMultidest> getHistoricoMultidest(ViewHistoricoHistMultidestQuery query);

	/**
	 * Se obtiene historico del mensaje NO multidestinatario
	 * 
	 * @param query
	 * @return List
	 */
	public List<ViewHistoricoHist> getHistorico(ViewHistoricoHistQuery query);
	

}
