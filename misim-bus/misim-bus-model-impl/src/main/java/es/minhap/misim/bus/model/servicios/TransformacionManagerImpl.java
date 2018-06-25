package es.minhap.misim.bus.model.servicios;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import es.minhap.misim.bus.dao.TransformacionDAO;
import es.minhap.misim.bus.model.Transformacion;
import es.minhap.misim.bus.query.TransformacionQuery;

@Service("transformacionManager")
public class TransformacionManagerImpl implements TransformacionManager{

	private static final Logger logger = Logger.getLogger(TransformacionManagerImpl.class);
		
	@Resource
	TransformacionDAO TransformacionDAO;
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.TransformacionManager#guardarTransformacion(micc.bus.persistence.entities.Transformacion)
	 */
	public Long guardarTransformacion(Transformacion transformacion){
		logger.info("guardarTransformacion - start");
		return TransformacionDAO.insert(transformacion);
	}	
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.TransformacionManager#insertTransformacion(micc.bus.persistence.entities.Transformacion)
	 */
	public Long insertTransformacion(Transformacion transformacion) {
		Long id = null;
		if(transformacion!=null){
			id = TransformacionDAO.insert(transformacion);
		}
		return id;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.TransformacionManager#getTransformacionById(Long)
	 */
	public Transformacion getTransformacionById(Long idTransformacion) {
		if(idTransformacion==null)
			return null;
		
		return TransformacionDAO.get(idTransformacion);
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.TransformacionManager#updateTransformacion(micc.bus.persistence.entities.Transformacion)
	 */
	public boolean updateTransformacion(Transformacion transformacion) {
		boolean update = false;
		if(transformacion!=null && transformacion.getIdTransformacion()!=null && !("").equals(transformacion.getIdTransformacion())){
			TransformacionDAO.update(transformacion);
			update = true;
		}
		return update;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.TransformacionManager#eliminarTransformacion(Long)
	 */
	public void eliminarTransformacion(Long id) {
		logger.info("eliminarTransformacion - start");
		TransformacionDAO.delete(id);
		logger.info("eliminarTransformacion - end");
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.TransformacionManager#getTransformacion(es.minhap.micc.bus.model.query.TransformacionQuery)
	 */	
	public List<Transformacion> getTransformacion(TransformacionQuery query) {
		return TransformacionDAO.search(query).getResults();
	}
	
}
