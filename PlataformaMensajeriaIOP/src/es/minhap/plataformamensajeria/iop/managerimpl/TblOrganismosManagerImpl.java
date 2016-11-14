package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblOrganismosManager;
import es.minhap.sim.dao.TblOrganismosDAO;
import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.query.TblOrganismosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblOrganismosManagerImpl")
public class TblOrganismosManagerImpl implements TblOrganismosManager {

	@Resource 
	private TblOrganismosDAO organismosDAO;
	
	@Override
	@Transactional
	public boolean existeOrganismo(String codOrganismoPagadorSMS) {
		TblOrganismosQuery query =  crearFiltro(codOrganismoPagadorSMS);
		TblOrganismos organismo = getOrganismosDAO().searchUnique(query);
		return (null != organismo)? true : false;
	}

	/**
	 * 
	 * @param codOrganismoPagadorSMS
	 * @return
	 */
	@Transactional
	public TblOrganismosQuery crearFiltro(String codOrganismoPagadorSMS) {
		TblOrganismosQuery query = new TblOrganismosQuery();
		query.setDir3(codOrganismoPagadorSMS);
		return query;
		
	}

	/**
	 * @return the organismosDAO
	 */
	public TblOrganismosDAO getOrganismosDAO() {
		return organismosDAO;
	}

	/**
	 * @param organismosDAO the organismosDAO to set
	 */
	public void setOrganismosDAO(TblOrganismosDAO organismosDAO) {
		this.organismosDAO = organismosDAO;
	}
}
