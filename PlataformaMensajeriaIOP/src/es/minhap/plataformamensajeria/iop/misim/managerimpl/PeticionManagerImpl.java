package es.minhap.plataformamensajeria.iop.misim.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.misim.bus.dao.PeticionDAO;
import es.minhap.misim.bus.model.Peticion;
import es.minhap.misim.bus.query.PeticionQuery;
import es.minhap.plataformamensajeria.iop.misim.manager.PeticionManager;

/**
 * 
 * @author everis
 *
 */
@Service("PeticionManagerImpl")
public class PeticionManagerImpl implements PeticionManager {

	@Resource 
	private PeticionDAO peticionDAO;

	
	@Override
	@Transactional
	public Peticion getPeticionByQuery(PeticionQuery query) {
		return getPeticionDAO().searchUnique(query);
	}
	
	/**
	 * @return the peticionDAO
	 */
	public PeticionDAO getPeticionDAO() {
		return peticionDAO;
	}

	/**
	 * @param peticionDAO the peticionDAO to set
	 */
	public void setPeticionDAO(PeticionDAO peticionDAO) {
		this.peticionDAO = peticionDAO;
	}

	
	

}
