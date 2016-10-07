package es.minhap.misim.bus.model.servicios;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import es.minhap.misim.bus.dao.PeticionDAO;
import es.minhap.misim.bus.model.Peticion;
import es.minhap.misim.bus.query.PeticionQuery;

@Service("peticionManager")
public class PeticionManagerImpl implements PeticionManager{

	private static final Logger logger = Logger.getLogger(PeticionManagerImpl.class);
		
	@Resource
	PeticionDAO PeticionDAO;
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.PeticionManager#guardarPeticion(es.minhap.misim.bus.model.Peticion)
	 */
	public Long guardarPeticion(Peticion peticion){
		logger.info("guardarPeticion - start");
		return PeticionDAO.insert(peticion);
	}	
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.PeticionManager#insertPeticion(es.minhap.misim.bus.model.Peticion)
	 */
	public Long insertPeticion(Peticion peticion) {
		Long id = null;
		if(peticion!=null){
			id = PeticionDAO.insert(peticion);
		}
		return id;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.PeticionManager#getPeticionById(Long)
	 */
	public Peticion getPeticionById(Long idPeticion) {
		if(idPeticion==null)
			return null;
		
		return PeticionDAO.get(idPeticion);
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.PeticionManager#updatePeticion(es.minhap.misim.bus.model.Peticion)
	 */
	public boolean updatePeticion(Peticion peticion) {
		boolean update = false;
		if(peticion!=null && peticion.getIdPeticion()>0){
			PeticionDAO.update(peticion);
			update = true;
		}
		return update;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.PeticionManager#eliminarPeticion(Long)
	 */
	public void eliminarPeticion(Long idPeticion) {
		logger.info("eliminarPeticion - start");
		PeticionDAO.delete(idPeticion);
		logger.info("eliminarPeticion - end");
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.PeticionManager#getPeticion(es.minhap.misim.bus.query.PeticionQuery)
	 */	
	public List<Peticion> getPeticion(PeticionQuery query) {
		return PeticionDAO.search(query).getResults();
	}
	
}
