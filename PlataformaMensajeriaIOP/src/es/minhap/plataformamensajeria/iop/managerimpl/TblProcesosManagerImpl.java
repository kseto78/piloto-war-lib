package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblCanalesManager;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblProcesosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.sim.dao.TblPdpDiputacionesDAO;
import es.minhap.sim.dao.TblProcesosDAO;
import es.minhap.sim.dao.TblServiciosDAO;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.TblPdpDiputaciones;
import es.minhap.sim.model.TblProcesos;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.query.TblPdpDiputacionesQuery;
import es.minhap.sim.query.TblProcesosQuery;
import es.minhap.sim.query.TblServiciosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblProcesosManagerImpl")
public class TblProcesosManagerImpl implements TblProcesosManager {

	@Resource 
	private TblProcesosDAO procesosDAO;
	
	@Resource 
	private TblLogManager tblLogManager;
	
	
	@Override
	public List<TblProcesos> getProcesosByQuery(TblProcesosQuery query) {
		return procesosDAO.search(query).getResults();
	}

	
	@Override
	public void update(TblProcesos proceso, String source, String accion, Long accionId) {
		procesosDAO.update(proceso);
		
		if (null != source && null != accion && null != accionId){
			TblLog log = new TblLog();
			
			log.setAdtfecha(new Date());			
//			log.setAdtusuario(servicio.getCreadopor());
			log.setLogaccion(accionId);
			log.setLogdescripcion(accion);
			log.setSourcedescription(proceso.getNombre());
			log.setSourceid(proceso.getProcesosid());
			log.setSourcename(source);
			tblLogManager.insertLog(log);
		}
		
	}


	@Override
	public TblProcesos getProcesoById(Long procesosId) {
		return procesosDAO.get(procesosId);
	}	
	
}
