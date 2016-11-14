package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.TblUrlMensajePremiumManager;
import es.minhap.sim.dao.TblUrlMensajePremiumDAO;
import es.minhap.sim.model.TblUrlMensajePremium;
import es.minhap.sim.query.TblUrlMensajePremiumQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblUrlMensajePremiumManagerImpl")
public class TblUrlMensajePremiumManagerImpl implements TblUrlMensajePremiumManager {

	@Resource
	private TblUrlMensajePremiumDAO urlMensajePremiumDAO;

	/**
	 * @see es.minhap.TblUrlMensajePremiumManager.getUrlByMensaje
	 */
	@Override
	public TblUrlMensajePremium getUrlByMensaje(Long mensajeId) {
		TblUrlMensajePremiumQuery query = new TblUrlMensajePremiumQuery();

		query.setMensajeId(mensajeId);

		return getUrlMensajePremiumDAO().searchUnique(query);
	}

	/**
	 * @see es.minhap.TblUrlMensajePremiumManager.getUrlByMensaje
	 */
	@Override
	public void update(TblUrlMensajePremium tbl) {
		getUrlMensajePremiumDAO().update(tbl);
	}
	
	@Override
	public void insert(TblUrlMensajePremium tbl) {
		urlMensajePremiumDAO.insert(tbl);
		
	}

	/**
	 * @return the urlMensajePremiumDAO
	 */
	public TblUrlMensajePremiumDAO getUrlMensajePremiumDAO() {
		return urlMensajePremiumDAO;
	}

	/**
	 * @param urlMensajePremiumDAO the urlMensajePremiumDAO to set
	 */
	public void setUrlMensajePremiumDAO(TblUrlMensajePremiumDAO urlMensajePremiumDAO) {
		this.urlMensajePremiumDAO = urlMensajePremiumDAO;
	}



}
