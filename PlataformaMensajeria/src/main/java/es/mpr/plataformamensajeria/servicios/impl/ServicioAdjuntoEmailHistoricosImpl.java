package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorAdjuntosHist;
import es.minhap.plataformamensajeria.iop.manager.TblAdjuntosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblAdjuntosManager;
import es.minhap.sim.model.TblAdjuntosHist;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAdjuntoEmailHistoricos;

/**
 * <p>Maneja la persistencia de mensajes a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
@Service("servicioAdjuntoEmailHistoricosImpl")
public class ServicioAdjuntoEmailHistoricosImpl implements ServicioAdjuntoEmailHistoricos{
	
	/**  logger. */
	Logger logger = Logger.getLogger(ServicioMensajesAdjuntosHistoricosImpl.class);
	
	/**  tbl adjuntos manager. */
	@Resource(name = "TblAdjuntosManagerImpl")
	private TblAdjuntosManager tblAdjuntosManager;
	
	/**  tbl adjuntos hist manager. */
	@Resource(name = "TblAdjuntosHistManagerImpl")
	private TblAdjuntosHistManager tblAdjuntosHistManager;
	
	/**  query executor adjuntos hist. */
	@Resource(name = "QueryExecutorAdjuntosHistImpl")
	private QueryExecutorAdjuntosHist queryExecutorAdjuntosHist;

	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioAdjuntoEmailHistoricos#getTodosIdAdjuntosCons(java.util.List)
	 */
	////MIGRADO
	@Override
	@Transactional
	public List<Long> getTodosIdAdjuntosCons(List<Long> listaMensajes) throws BusinessException {
		
		List<Long> res = new ArrayList<>();
		try {
			res = queryExecutorAdjuntosHist.getIdAdjuntosCons(listaMensajes);
		
			
		} catch (Exception e) {
			logger.error("ServicioMensajesAdjuntosHist.getTodosIdAdjuntosCons", e);
			throw new BusinessException(e, "errors.job.cons.getFicherosAdjuntos");
		}
		return res;
	}
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioAdjuntoEmailHistoricos#insert(es.minhap.sim.model.TblAdjuntosHist)
 */
////MIGRADO
	@Override
	public Long insert(TblAdjuntosHist adjuntoHistorico) {
		
		return (tblAdjuntosHistManager.getById(adjuntoHistorico.getAdjuntoid())== null)? tblAdjuntosHistManager.insertar(adjuntoHistorico):null;
	}
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioAdjuntoEmailHistoricos#delete(java.lang.Long)
 */
////MIGRADO
	@Override
	public void delete(Long adjuntoid) {
		tblAdjuntosManager.delete(adjuntoid);
	}


}
