package es.minhap.misim.bus.model.servicios;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import es.minhap.misim.bus.dao.EstadoDAO;
import es.minhap.misim.bus.model.Estado;
import es.minhap.misim.bus.query.EstadoQuery;

@Service("estadoManager")
public class EstadoManagerImpl implements EstadoManager{

	private static final Logger logger = Logger.getLogger(EstadoManagerImpl.class);
		
	@Resource
	EstadoDAO EstadoDAO;
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.EstadoManager#guardarEstado(es.minhap.misim.bus.model.Estado)
	 */
	public Long guardarEstado(Estado estado){
		logger.info("guardarEstado - start");
		return EstadoDAO.insert(estado);
	}	
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.EstadoManager#insertEstado(es.minhap.misim.bus.model.Estado)
	 */
	public Long insertEstado(Estado estado) {
		Long id = null;
		if(estado!=null){
			id = EstadoDAO.insert(estado);
		}
		return id;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.EstadoManager#getEstadoById(Long)
	 */
	public Estado getEstadoById(Long idEstado) {
		if(idEstado==null)
			return null;
		
		return EstadoDAO.get(idEstado);
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.EstadoManager#updateEstado(es.minhap.misim.bus.model.Estado)
	 */
	public boolean updateEstado(Estado estado) {
		boolean update = false;
		if(estado!=null && estado.getIdEstado()>0){
			EstadoDAO.update(estado);
			update = true;
		}
		return update;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.EstadoManager#eliminarEstado(Long)
	 */
	public void eliminarEstado(Long id) {
		logger.info("eliminarEstado - start");
		EstadoDAO.delete(id);
		logger.info("eliminarEstado - end");
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.EstadoManager#getEstado(es.minhap.misim.bus.query.EstadoQuery)
	 */	
	public List<Estado> getEstado(EstadoQuery query) {
		return EstadoDAO.search(query).getResults();
	}
	
}
