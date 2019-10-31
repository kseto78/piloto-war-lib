package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblPlataformasManager;
import es.minhap.sim.dao.TblPlataformasDAO;
import es.minhap.sim.model.TblPlataformas;
import es.minhap.sim.query.TblPlataformasQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblPlataformasManagerImpl")
public class TblPlataformasManagerImpl implements TblPlataformasManager {

	@Resource 
	private TblPlataformasDAO plataformasDAO;


	/**
	 * @see es.minhap.TblPlataformasManager.existPlataforma
	 */
	@Override
	@Transactional
	public Boolean existPlataforma(Long plataformaID) {
		return null != getPlataformasDAO().get(plataformaID);		
	}
	
	@Override
	@Transactional
	public List<TblPlataformas> getPlataformasActivas() {
		TblPlataformasQuery query = new TblPlataformasQuery();
		query.setActivo(true);
		return plataformasDAO.search(query).getResults();
	}


	/**
	 * @return the plataformasDAO
	 */
	public TblPlataformasDAO getPlataformasDAO() {
		return plataformasDAO;
	}


	/**
	 * @param plataformasDAO the plataformasDAO to set
	 */
	public void setPlataformasDAO(TblPlataformasDAO plataformasDAO) {
		this.plataformasDAO = plataformasDAO;
	}
}
