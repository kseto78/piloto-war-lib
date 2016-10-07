package es.minhap.misim.bus.model.servicios;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import es.minhap.misim.bus.dao.EndpointDAO;
import es.minhap.misim.bus.model.Endpoint;
import es.minhap.misim.bus.query.EndpointQuery;

@Service("endpointManager")
public class EndpointManagerImpl implements EndpointManager{

	private static final Logger logger = Logger.getLogger(EndpointManagerImpl.class);
		
	@Resource
	EndpointDAO EndpointDAO;
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.EndpointManager#guardarEndpoint(micc.bus.persistence.entities.Endpoint)
	 */
	public Long guardarEndpoint(Endpoint endpoint){
		logger.info("guardarEndpoint - start");
		return EndpointDAO.insert(endpoint);
	}	
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.EndpointManager#insertEndpoint(micc.bus.persistence.entities.Endpoint)
	 */
	public Long insertEndpoint(Endpoint endpoint) {
		Long id = null;
		if(endpoint!=null){
			id = EndpointDAO.insert(endpoint);
		}
		return id;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.EndpointManager#getEndpointById(Long)
	 */
	public Endpoint getEndpointById(Long idEndpoint) {
		if(idEndpoint==null)
			return null;
		
		return EndpointDAO.get(idEndpoint);
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.EndpointManager#updateEndpoint(micc.bus.persistence.entities.Endpoint)
	 */
	public boolean updateEndpoint(Endpoint endpoint) {
		boolean update = false;
		if(endpoint!=null && endpoint.getIdEndpoint()>0){
			EndpointDAO.update(endpoint);
			update = true;
		}
		return update;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.EndpointManager#eliminarEndpoint(Long)
	 */
	public void eliminarEndpoint(Long id) {
		logger.info("eliminarEndpoint - start");
		EndpointDAO.delete(id);
		logger.info("eliminarEndpoint - end");
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.EndpointManager#getEndpoint(es.minhap.micc.bus.model.query.EndpointQuery)
	 */	
	public List<Endpoint> getEndpoint(EndpointQuery query) {
		return EndpointDAO.search(query).getResults();
	}
	
}
