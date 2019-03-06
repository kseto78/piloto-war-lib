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
import es.minhap.plataformamensajeria.iop.manager.TblPdpDiputacionesManager;
import es.minhap.sim.dao.TblOrganismosDAO;
import es.minhap.sim.dao.TblPdpDiputacionesDAO;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.model.TblPdpDiputaciones;
import es.minhap.sim.query.TblPdpDiputacionesQuery;
import es.minhap.sim.query.TblOrganismosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblPdpDiputacionesManagerImpl")
public class TblPdpDiputacionesManagerImpl implements TblPdpDiputacionesManager {

	@Resource 
	private TblPdpDiputacionesDAO organismosPdpDAO;
	
	@Resource 
	private TblLogManager tblLogManager;
	
	
	@Override
	public List<TblPdpDiputaciones> getPdpDiputacionesByQuery(TblPdpDiputacionesQuery query) {
		return organismosPdpDAO.search(query).getResults();
	}

	@Override
	public TblPdpDiputaciones getPdpDiputacionesById(Long pdpDiputacionesId) {
		return organismosPdpDAO.get(pdpDiputacionesId);
	}
	
	@Override
	@Transactional
	public void update(TblPdpDiputaciones organismoTO, String source, String accion, Long accionId) {
		organismosPdpDAO.update(organismoTO);		
		
		if (null != source && null != accion && null != accionId){
			TblLog log = new TblLog();
			
			log.setAdtfecha(new Date());
			log.setAdtusuario(organismoTO.getCreadopor());
			log.setLogaccion(accionId);
			log.setLogdescripcion(accion);
			log.setSourcedescription(organismoTO.getNombre());
			log.setSourceid(organismoTO.getPdpDiputacionesId());
			log.setSourcename(source);
			tblLogManager.insertLog(log);
		}

	}
	
	@Override
	public Long insert(TblPdpDiputaciones organismo, String source, String accion, Long accionId) {
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
	public TblPdpDiputacionesDAO getOrganismosPdpDAO() {
		return organismosPdpDAO;
	}

	/**
	 * @param organismosDAO the organismosDAO to set
	 */
	public void setOrganismosPdpDAO(TblPdpDiputacionesDAO organismosPdpDAO) {
		this.organismosPdpDAO = organismosPdpDAO;
	}



	
}
