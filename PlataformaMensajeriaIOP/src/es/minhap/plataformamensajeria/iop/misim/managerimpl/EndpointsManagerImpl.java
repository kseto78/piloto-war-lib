package es.minhap.plataformamensajeria.iop.misim.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.misim.bus.dao.EndpointDAO;
import es.minhap.misim.bus.model.Endpoint;
import es.minhap.misim.bus.query.EndpointQuery;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.misim.manager.EndpointsManager;
import es.minhap.sim.model.TblLog;

/**
 * 
 * @author everis
 *
 */
@Service("EndpointsManagerImpl")
public class EndpointsManagerImpl implements EndpointsManager {

	@Resource 
	private EndpointDAO endpointDAO;
	
	@Resource 
	private TblLogManager tblLogManager;
	

	/**
	 * @see es.minhap.TblLotesEnviosManager.insertarLote
	 */
	@Override
	@Transactional
	public List<Endpoint> getEndpoints(EndpointQuery query) {
		return getEndpointDAO().search(query).getResults();
	}
	
	@Transactional
	@Override
	public Endpoint getEndpoint(Long idEndpoint){
		return endpointDAO.get(idEndpoint);
	}
	
	@Override
	public List<Endpoint> getEndpointsOrdenados() {
		EndpointQuery query = new EndpointQuery();
		query.addOrder("nombre", OrderType.ASC);
		
		return endpointDAO.search(query).getResults();
	}

	@Override
	@Transactional
	public Long insert(Endpoint endpoint, String source, String accion, Long accionId) {
		Long id = endpointDAO.insert(endpoint);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(endpoint.getNombre());
		log.setSourceid(id);
		log.setSourcename(source);
		tblLogManager.insertLog(log);

		return id;
	}

	@Override
	@Transactional
	public void update(Endpoint endpoint, String source, String accion, Long accionId) {
		endpointDAO.update(endpoint);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(endpoint.getNombre());
		log.setSourceid(endpoint.getIdEndpoint());
		log.setSourcename(source);
		tblLogManager.insertLog(log);
		
	}
	
	public EndpointDAO getEndpointDAO() {
		return endpointDAO;
	}


	public void setEndpointDAO(EndpointDAO endpointDAO) {
		this.endpointDAO = endpointDAO;
	}


}
