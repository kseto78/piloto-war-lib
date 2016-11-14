package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.sim.dao.TblServidoresDAO;
import es.minhap.sim.model.TblServidores;
import es.minhap.sim.query.TblServidoresQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblServidoresManagerImpl")
public class TblServidoresManagerImpl implements TblServidoresManager {

	@Resource 
	private TblServidoresDAO servidoresDAO;
	
	 
	/**
	 * @see es.minhap.TblServidoresManager.getServidor
	 */
	@Override
	public TblServidores getServidor(TblServidoresQuery query) {
		return getServidoresDAO().searchUnique(query);
	}
	
	/**
	 * @see es.minhap.TblServidoresManager.countServidor
	 */
	@Override
	public Integer countServidor(TblServidoresQuery query) {
		return getServidoresDAO().count(query);
	}

	
	/**
	 * @see es.minhap.TblServidoresManager.getServidorById
	 */
	@Override
	public TblServidores getServidorById(Long servidorId) {
		return getServidoresDAO().get(servidorId);
	}

	/**
	 * @return the servidoresDAO
	 */
	public TblServidoresDAO getServidoresDAO() {
		return servidoresDAO;
	}

	/**
	 * @param servidoresDAO the servidoresDAO to set
	 */
	public void setServidoresDAO(TblServidoresDAO servidoresDAO) {
		this.servidoresDAO = servidoresDAO;
	}

}
