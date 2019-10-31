package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresOrganismosManager;
import es.minhap.sim.dao.TblServidoresOrganismosDAO;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.TblServidoresOrganismos;
import es.minhap.sim.query.TblServidoresOrganismosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("tblServidoresOrganismosManagerImpl")
public class TblServidoresOrganismosManagerImpl implements TblServidoresOrganismosManager {

	protected static final String R_CONST_1 = " ";

	@Resource
	private TblServidoresOrganismosDAO tblServidoresOrganismosDAO;
	
	@Resource 
	private TblLogManager tblLogManager;

	@Override
	public List<TblServidoresOrganismos> getServidoresOrganismosByQuery(TblServidoresOrganismosQuery query) {
		return tblServidoresOrganismosDAO.search(query).getResults();
	}

	@Override
	public TblServidoresOrganismos getServidoresOrganismosById(Long servidorOrganismoId) {
		return tblServidoresOrganismosDAO.get(servidorOrganismoId);
	}

	@Override
	public Long insert(TblServidoresOrganismos soTO, String source, String accion, Long accionId, String descripcion) {
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(soTO.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(descripcion + R_CONST_1 + soTO.getServidorid());
		log.setSourceid(soTO.getOrganismoid());
		log.setSourcename(source);
		tblLogManager.insertLog(log);
		
		return tblServidoresOrganismosDAO.insert(soTO);
	}

	@Override
	public void delete(Long servidorOrganismoId, String source, String accion, Long accionId, String descripcion) {
		TblServidoresOrganismos so = tblServidoresOrganismosDAO.get(servidorOrganismoId);
		tblServidoresOrganismosDAO.delete(servidorOrganismoId);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(so.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(descripcion + R_CONST_1 + so.getServidorid());
		log.setSourceid(so.getOrganismoid());
		log.setSourcename(source);
		tblLogManager.insertLog(log);		
		
	}
	
	

}
