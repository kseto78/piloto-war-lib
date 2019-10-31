package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import es.minhap.common.entity.OrderType;
import es.minhap.common.entity.TextComparator;
import es.minhap.plataformamensajeria.iop.manager.TblMonitorDIR3Manager;
import es.minhap.sim.dao.TblMonitorDIR3DAO;
import es.minhap.sim.model.TblMonitorDIR3;
import es.minhap.sim.query.TblMonitorDIR3Query;

@Service("tblMonitorDIR3Manager")
public class TblMonitorDIR3ManagerImpl implements TblMonitorDIR3Manager{

	private static final Logger LOGGER = Logger.getLogger(TblMonitorDIR3ManagerImpl.class);
	
	@Resource
	TblMonitorDIR3DAO tblMonitorDIR3DAO;
	
	@Override
	public Long guardarDir3MonitorWS(TblMonitorDIR3 tblMonitorDIR3){
		LOGGER.info("guardarDir3MonitorWS - start");
		return tblMonitorDIR3DAO.insert(tblMonitorDIR3);
	}
	
	@Override
	public TblMonitorDIR3 obtenerUltimoDir3MonitorWS(String estadoProceso){
		TblMonitorDIR3 dir3MonitorWs = null;
		try{
			TblMonitorDIR3Query query = new TblMonitorDIR3Query();
			query.addOrder(TblMonitorDIR3Query.FECHAEJECUCION, OrderType.DESC);
			if(StringUtils.isNotEmpty(estadoProceso)){
				query.setCodEstado(estadoProceso);
				query.setCodEstadoComparator(TextComparator.EQUALS);
			}
			List<TblMonitorDIR3> result = tblMonitorDIR3DAO.search(query).getResults();
			if(result!=null && !result.isEmpty()){
				dir3MonitorWs = result.get(0);
			}
		}catch(Exception e){
			LOGGER.error("[Dir3MonitorManagerImpl] - obtenerUltimoDir3MonitorWS - Error: ", e);
			dir3MonitorWs = null;
		}
		return dir3MonitorWs;
	}
	
	@Override
	public List<TblMonitorDIR3> buscarListaMonitorDir3(TblMonitorDIR3Query query) {
		return tblMonitorDIR3DAO.search(query).getResults();
	}
}
