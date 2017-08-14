package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajesAdjuntosHist;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesAdjuntosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesAdjuntosManager;
import es.minhap.sim.model.TblLotesEnviosHist;
import es.minhap.sim.model.TblMensajesAdjuntosHist;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajesAdjuntosHistoricos;

/**
 * <p>Maneja la persistencia de adjuntos de mensajes historico a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
@Service("servicioMensajesAdjuntosHistoricosImpl")
public class ServicioMensajesAdjuntosHistoricosImpl implements ServicioMensajesAdjuntosHistoricos{
	
	Logger logger = Logger.getLogger(ServicioMensajesAdjuntosHistoricosImpl.class);
	
	@Resource(name = "TblMensajesAdjuntosHistManagerImpl")
	private TblMensajesAdjuntosHistManager tblMensajesAdjuntosHistManager;
	
	@Resource(name = "TblMensajesAdjuntosManagerImpl")
	private TblMensajesAdjuntosManager tblMensajesAdjuntosManager;
	
	@Resource(name = "QueryExecutorMensajesAdjuntosHistImpl")
	private QueryExecutorMensajesAdjuntosHist queryExecutorMensajesAdjuntosHist;
	
	private static final Integer MAX = 5000;
	
	/////MIGRADO
	@Override
	@Transactional
	public List<Long> getIdMensajesAdjuntosCons(List<Long> listaMensajes) throws BusinessException {
		
		List<Long> result = new ArrayList<>();
		try {
			result = queryExecutorMensajesAdjuntosHist.getIdMensajesAdjuntosCons(listaMensajes);
			
		} catch (Exception e) {
			logger.error("ServicioMensajesAdjuntosHist.getIdMensajesAdjuntosCons", e);
			throw new BusinessException(e, "errors.job.cons.getMensajesAdjuntos");
		}
		return result;
	}
	
	// //MIGRADO
	@Override
	public List<List<TblMensajesAdjuntosHist>> getTblMensajesAdjuntosHist(List<Long> subList,
			TblLotesEnviosHist loteHistorico) {
		List<List<TblMensajesAdjuntosHist>> res = new ArrayList<>();
		Integer total = queryExecutorMensajesAdjuntosHist.countConvertAdjuntosTOAdjuntosHist(subList, loteHistorico);

		Integer partes = (int) Math.ceil((double) total / MAX);
		for (int i = 0; i < partes; i++) {
			List<TblMensajesAdjuntosHist> listaTblMensajesAdjuntosHist = queryExecutorMensajesAdjuntosHist
					.convertAdjuntosTOAdjuntosHist(subList, loteHistorico, MAX, i * MAX);
			if (null == listaTblMensajesAdjuntosHist) {
				listaTblMensajesAdjuntosHist = new ArrayList<>();
			}
			res.add(listaTblMensajesAdjuntosHist);
		}
		return res;

	}
	
	////MIGRADO
	@Override
	public Long insert(TblMensajesAdjuntosHist mensajeAdjuntoHistorico) {
		return tblMensajesAdjuntosHistManager.insert(mensajeAdjuntoHistorico);
	}
	
	////MIGRADO
	@Override
	public void delete(Long idMensajeAdjunto) {
		tblMensajesAdjuntosManager.delete(idMensajeAdjunto);
	}

}
