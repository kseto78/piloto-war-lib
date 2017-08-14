package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorDestinatariosHist;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorDestinatariosMensajesHist;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.sim.model.TblDestinatariosHist;
import es.minhap.sim.model.TblDestinatariosMensHist;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioDestinatarioHistoricos;

/**
 * <p>Maneja la persistencia de adjuntos de mensajes a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
@Service("servicioDestinatarioHistoricosImpl")
public class ServicioDestinatarioHistoricosImpl implements ServicioDestinatarioHistoricos{
	Logger logger = Logger.getLogger(ServicioMensajesAdjuntosHistoricosImpl.class);
	
	@Resource(name = "TblDestinatariosHistManagerImpl")
	private TblDestinatariosHistManager tblDestinatariosHistManager;
	
	@Resource(name = "TblDestinatariosManagerImpl")
	private TblDestinatariosManager tblDestinatariosManager;
	
	@Resource(name = "TblDestinatariosMensHistManagerImpl")
	private TblDestinatariosMensajesHistManager tblDestinatariosMensHistManager;
	
	@Resource(name = "TblDestinatariosMensajesManagerImpl")
	private TblDestinatariosMensajesManager tblDestinatariosMensManager;
	
	@Resource(name = "QueryExecutorDestinatariosHistImpl")
	private QueryExecutorDestinatariosHist queryExecutorDestinatariosHist;
	
	@Resource(name = "QueryExecutorDestinatariosMensajesHistImpl")
	private QueryExecutorDestinatariosMensajesHist queryExecutorDestinatariosMensajesHist;
	
	private static final Integer MAX = 5000;
	
	////MIGRADO
	@Override
	@Transactional
	public List<Long> getTodosIdDestinatarioCons(List<Long> listaMensajes) throws BusinessException {
		List<Long> res = new ArrayList<>();
		try {
			res = queryExecutorDestinatariosHist.getIdDestinatariosCons(listaMensajes);
				
		} catch (Exception e) {
			logger.error("ServicioMensajesAdjuntosHist.getTodosIdAdjuntosCons", e);
			throw new BusinessException(e, "errors.job.cons.getFicherosAdjuntos");
		}
		return res;

	}
	
////MIGRADO
	@Override
	@Transactional
	public List<Long> getTodosIdDestinatarioMensajesCons(List<Long> listaMensajes) throws BusinessException {
		List<Long> res = new ArrayList<>();
		try {
			res = queryExecutorDestinatariosMensajesHist.getIdDestinatariosMensajesCons(listaMensajes);
				
		} catch (Exception e) {
			logger.error("ServicioMensajesAdjuntosHist.getTodosIdAdjuntosCons", e);
			throw new BusinessException(e, "errors.job.cons.getFicherosAdjuntos");
		}
		return res;

	}
	
	///MIGRADO
	@Override
	public List<List<TblDestinatariosHist>> getTblDestinatariosHist(List<Long> subList) {
		List<List<TblDestinatariosHist>> res = new ArrayList<>();
		Integer total = queryExecutorDestinatariosHist.countConvertDestinatarioTODestinatarioHist(subList);

		Integer partes = (int) Math.ceil((double) total / MAX);
		for (int i = 0; i < partes; i++) {
			List<TblDestinatariosHist> listaTblDestinatariosHist = queryExecutorDestinatariosHist.convertDestinatarioTODestinatarioHist(subList, MAX, i * MAX);
			if (null == listaTblDestinatariosHist) {
				listaTblDestinatariosHist = new ArrayList<>();
			}
			res.add(listaTblDestinatariosHist);
		}
		return res;
	}
	
	///MIGRADO
	@Override
	public Long insert(TblDestinatariosHist destinatarioHistorico) {
		return tblDestinatariosHistManager.insert(destinatarioHistorico);
	}
	
	///MIGRADO
	@Override
	public List<List<TblDestinatariosMensHist>> getTblDestinatariosMensajesHist(List<Long> subList) {
		List<List<TblDestinatariosMensHist>> res = new ArrayList<>();
		Integer total = queryExecutorDestinatariosMensajesHist.countConvertDestinatarioMensTODestinatarioMensHist(subList);

		Integer partes = (int) Math.ceil((double) total / MAX);
		for (int i = 0; i < partes; i++) {
			List<TblDestinatariosMensHist> listaTblDestinatariosMensajesHist = queryExecutorDestinatariosMensajesHist.convertDestinatarioMensTODestinatarioMensHist(subList, MAX, i * MAX);
			if (null == listaTblDestinatariosMensajesHist) {
				listaTblDestinatariosMensajesHist = new ArrayList<>();
			}
			res.add(listaTblDestinatariosMensajesHist);
		}
		return res;
	}
	
	///MIGRADO
	@Override
	public Long insert(TblDestinatariosMensHist destinatarioMensajeHistorico) {
		return tblDestinatariosMensHistManager.insert(destinatarioMensajeHistorico);
	}
	
	///MIGRADO
	@Override
	public void delete(Long destinatarioid) {
		tblDestinatariosManager.delete(destinatarioid);
		
	}
	
	///MIGRADO
	@Override
	public void deleteDestinatarioMensaje(Long destinatarioMensajeId) {
		tblDestinatariosMensManager.delete(destinatarioMensajeId);
		
	}


}
