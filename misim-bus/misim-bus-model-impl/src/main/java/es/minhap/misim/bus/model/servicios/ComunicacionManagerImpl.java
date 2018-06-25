package es.minhap.misim.bus.model.servicios;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import es.minhap.misim.bus.dao.ComunicacionDAO;
import es.minhap.misim.bus.model.Comunicacion;
import es.minhap.misim.bus.query.ComunicacionQuery;

@Service("comunicacionManager")
public class ComunicacionManagerImpl implements ComunicacionManager{

	private static final Logger logger = Logger.getLogger(ComunicacionManagerImpl.class);
		
	@Resource
	ComunicacionDAO ComunicacionDAO;
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ComunicacionManager#guardarComunicacion(es.minhap.misim.bus.model.Comunicacion)
	 */
	public Long guardarComunicacion(Comunicacion comunicacion){
		logger.info("guardarComunicacion - start");
		return ComunicacionDAO.insert(comunicacion);
	}	
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ComunicacionManager#insertComunicacion(es.minhap.misim.bus.model.Comunicacion)
	 */
	public Long insertComunicacion(Comunicacion comunicacion) {
		Long id = null;
		if(comunicacion!=null){
			id = ComunicacionDAO.insert(comunicacion);
		}
		return id;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ComunicacionManager#getComunicacionById(Long)
	 */
	public Comunicacion getComunicacionById(Long idComunicacion) {
		if(idComunicacion==null)
			return null;
		
		return ComunicacionDAO.get(idComunicacion);
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ComunicacionManager#updateComunicacion(es.minhap.misim.bus.model.Comunicacion)
	 */
	public boolean updateComunicacion(Comunicacion comunicacion) {
		boolean update = false;
		if(comunicacion!=null && comunicacion.getIdComunicacion()>0){
			ComunicacionDAO.update(comunicacion);
			update = true;
		}
		return update;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ComunicacionManager#eliminarComunicacion(Long)
	 */
	public void eliminarComunicacion(Long id) {
		logger.info("eliminarComunicacion - start");
		ComunicacionDAO.delete(id);
		logger.info("eliminarComunicacion - end");
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.ComunicacionManager#getComunicacion(es.minhap.misim.bus.query.ComunicacionQuery)
	 */	
	public List<Comunicacion> getComunicacion(ComunicacionQuery query) {
		return ComunicacionDAO.search(query).getResults();
	}
	
}
