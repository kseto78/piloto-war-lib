package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblPlataformasManager;
import es.minhap.sim.dao.TblPlataformasDAO;

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
		return (null == getPlataformasDAO().get(plataformaID))? false : true;		
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
