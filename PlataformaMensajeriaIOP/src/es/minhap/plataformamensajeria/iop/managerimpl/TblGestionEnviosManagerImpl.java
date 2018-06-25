package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblGestionEnviosManager;
import es.minhap.sim.dao.TblGestionEnviosDAO;
import es.minhap.sim.model.TblGestionEnvios;
import es.minhap.sim.query.TblGestionEnviosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblGestionEnviosManagerImpl")
public class TblGestionEnviosManagerImpl implements TblGestionEnviosManager {

	@Resource 
	private TblGestionEnviosDAO gestionDAO;

	/**
	 * @see es.minhap.TblGestionEnviosManager.insertarGestionEnvios
	 */
	@Override
	@Transactional
	public Integer insertarGestionEnvios(TblGestionEnvios ge) {
		Integer res;

		res = getGestionDAO().insert(ge).intValue();

		return res;
	}

	@Override
	@Transactional
	public void actualizarGestionEnvios(TblGestionEnvios ge) {
		getGestionDAO().update(ge);

		}

	@Override
	@Transactional
	public TblGestionEnvios getGestionEnvios(TblGestionEnviosQuery query) {
		
		return  getGestionDAO().searchUnique(query);

	}

		
	@Override
	@Transactional
	public void delete(Long mensajeid) {
		gestionDAO.delete(mensajeid);
		
	}


	@Override
	@Transactional
	public List<TblGestionEnvios> getEnviosLote(String idLote) {
		TblGestionEnviosQuery query = new TblGestionEnviosQuery();
		query.setLoteenvioid(Long.parseLong(idLote));

		List<TblGestionEnvios> lista = gestionDAO.search(query).getResults();
		
		return lista;
	}

	@Override
	@Transactional
	public TblGestionEnvios getById(Long mensajeid) {
		return gestionDAO.get(mensajeid);
	}
	
	/**
	 * @return the gestionDAO
	 */
	public TblGestionEnviosDAO getGestionDAO() {
		return gestionDAO;
	}

	/**
	 * @param gestionDAO the gestionDAO to set
	 */
	public void setGestionDAO(TblGestionEnviosDAO gestionDAO) {
		this.gestionDAO = gestionDAO;
	}

	
	

}
