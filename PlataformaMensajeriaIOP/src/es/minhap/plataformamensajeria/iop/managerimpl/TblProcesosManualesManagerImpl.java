package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblProcesosManualesManager;
import es.minhap.sim.dao.TblProcesosManualesDAO;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.TblProcesos;
import es.minhap.sim.model.TblProcesosManuales;
import es.minhap.sim.query.TblProcesosManualesQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblProcesosManualesManagerImpl")
public class TblProcesosManualesManagerImpl implements TblProcesosManualesManager {

	@Resource 
	private TblProcesosManualesDAO procesosManualesDAO;
	
	@Resource 
	private TblLogManager tblLogManager;
	
	
	@Override
	public List<TblProcesosManuales> getProcesosManualesByQuery(TblProcesosManualesQuery query) {
		return procesosManualesDAO.search(query).getResults();
	}

	
	@Override
	public void update(TblProcesosManuales procesoManual, String source, String accion, Long accionId) {
		procesosManualesDAO.update(procesoManual);
		
		if (null != source && null != accion && null != accionId){
			TblLog log = new TblLog();
			
			log.setAdtfecha(new Date());			
//			log.setAdtusuario(servicio.getCreadopor());
			log.setLogaccion(accionId);
			log.setLogdescripcion(accion);
			log.setSourcedescription(procesoManual.getNombre());
			log.setSourceid(procesoManual.getProcesosManualesId());
			log.setSourcename(source);
			tblLogManager.insertLog(log);
		}
		
	}


	@Override
	public TblProcesosManuales getProcesoManualById(Long procesoManualId) {
		return procesosManualesDAO.get(procesoManualId);
	}


	@Override
	public Long insert(TblProcesosManuales procesoManual, String source,
			String accion, Long accionId) {
		Long id = procesosManualesDAO.insert(procesoManual);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(procesoManual.getCreadoPor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(procesoManual.getNombre());
		log.setSourceid(id);
		log.setSourcename(source);
		tblLogManager.insertLog(log);

		return id;

	}	
	
}
