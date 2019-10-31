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
import es.minhap.sim.dao.TblOrganismosDAO;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.query.TblOrganismosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblOrganismosManagerImpl")
public class TblOrganismosManagerImpl implements TblOrganismosManager {

	@Resource 
	private TblOrganismosDAO organismosDAO;
	
	@Resource 
	private TblLogManager tblLogManager;
	
	@Override
	@Transactional
	public boolean existeOrganismo(String codOrganismoPagadorSMS) {
		TblOrganismosQuery query =  crearFiltro(codOrganismoPagadorSMS);
		TblOrganismos organismo = getOrganismosDAO().searchUnique(query);
		return null != organismo;
	}

	/**
	 * 
	 * @param codOrganismoPagadorSMS
	 * @return
	 */
	@Transactional
	public TblOrganismosQuery crearFiltro(String codOrganismoPagadorSMS) {
		TblOrganismosQuery query = new TblOrganismosQuery();
		query.setDir3(codOrganismoPagadorSMS);
		query.setDir3Comparator(TextComparator.EQUALS);
		return query;
		
	}
	
	@Override
	public List<TblOrganismos> getOrganismosByQuery(TblOrganismosQuery query) {
		return organismosDAO.search(query).getResults();
	}

	@Override
	public TblOrganismos getOrganismoById(Long organismoId) {
		return organismosDAO.get(organismoId);
	}
	
	@Override
	@Transactional
	public void update(TblOrganismos organismoTO, String source, String accion, Long accionId) {
		organismosDAO.update(organismoTO);		
		
		if (null != source && null != accion && null != accionId){
			TblLog log = new TblLog();
			
			log.setAdtfecha(new Date());
			log.setAdtusuario(organismoTO.getCreadopor());
			log.setLogaccion(accionId);
			log.setLogdescripcion(accion);
			log.setSourcedescription(organismoTO.getNombre());
			log.setSourceid(organismoTO.getOrganismoid());
			log.setSourcename(source);
			tblLogManager.insertLog(log);
		}

	}
	
	@Override
	public Long insert(TblOrganismos organismo, String source, String accion, Long accionId) {
		Long id = organismosDAO.insert(organismo);
		
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
	public TblOrganismosDAO getOrganismosDAO() {
		return organismosDAO;
	}

	/**
	 * @param organismosDAO the organismosDAO to set
	 */
	public void setOrganismosDAO(TblOrganismosDAO organismosDAO) {
		this.organismosDAO = organismosDAO;
	}



	
}
