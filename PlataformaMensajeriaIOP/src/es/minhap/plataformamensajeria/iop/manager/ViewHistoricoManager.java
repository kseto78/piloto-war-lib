package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.ViewHistorico;
import es.minhap.sim.model.ViewHistoricoMultidest;
import es.minhap.sim.query.ViewHistoricoMultidestQuery;
import es.minhap.sim.query.ViewHistoricoQuery;

public interface ViewHistoricoManager {

	/**
	 * Se obtiene historico del mensaje NO multidestinatario
	 * 
	 * @param ViewHistoricoQuery
	 * @return List
	 */
	public List<ViewHistorico> getHistorico(ViewHistoricoQuery query);

	
	/**
	 * Se obtiene historico del mensaje multidestinatario
	 * 
	 * @param ViewHistoricoQuery
	 * @return List
	 */
	public List<ViewHistoricoMultidest> getHistoricoMultidest(ViewHistoricoMultidestQuery query);
}
