package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.common.entity.TextComparator;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblOrganismosManager;
import es.minhap.plataformamensajeria.iop.manager.TblOrganismosPdpManager;
import es.minhap.sim.dao.TblOrganismosDAO;
import es.minhap.sim.dao.TblOrganismosPdpDAO;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.model.TblOrganismosPdp;
import es.minhap.sim.query.TblOrganismosPdpQuery;
import es.minhap.sim.query.TblOrganismosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblOrganismosPdpManagerImpl")
public class TblOrganismosPdpManagerImpl implements TblOrganismosPdpManager {

	@Resource 
	private TblOrganismosPdpDAO organismosPdpDAO;
	
	@Resource 
	private TblLogManager tblLogManager;
	
	
	@Override
	public List<TblOrganismosPdp> getOrganismosPdpByQuery(TblOrganismosPdpQuery query) {
		return organismosPdpDAO.search(query).getResults();
	}

	@Override
	public TblOrganismosPdp getOrganismoPdpById(Long organismoPdpId) {
		return organismosPdpDAO.get(organismoPdpId);
	}
	
	@Override
	@Transactional
	public void update(TblOrganismosPdp organismoTO, String source, String accion, Long accionId) {
		organismosPdpDAO.update(organismoTO);		
		
		if (null != source && null != accion && null != accionId){
			TblLog log = new TblLog();
			
			log.setAdtfecha(new Date());
			log.setAdtusuario(organismoTO.getCreadopor());
			log.setLogaccion(accionId);
			log.setLogdescripcion(accion);
			log.setSourcedescription(organismoTO.getNombre());
			log.setSourceid(organismoTO.getOrganismoPdpId());
			log.setSourcename(source);
			tblLogManager.insertLog(log);
		}

	}
	
	@Override
	public Long insert(TblOrganismosPdp organismo, String source, String accion, Long accionId) {
		Long id = organismosPdpDAO.insert(organismo);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(organismo.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(organismo.getNombre());
		log.setSourceid(id);
		log.setSourcename(source);
		tblLogManager.insertLog(log);

		return id;
	}
	
	/**
	 * @return the organismosDAO
	 */
	public TblOrganismosPdpDAO getOrganismosPdpDAO() {
		return organismosPdpDAO;
	}

	/**
	 * @param organismosDAO the organismosDAO to set
	 */
	public void setOrganismosPdpDAO(TblOrganismosPdpDAO organismosPdpDAO) {
		this.organismosPdpDAO = organismosPdpDAO;
	}



	
}
