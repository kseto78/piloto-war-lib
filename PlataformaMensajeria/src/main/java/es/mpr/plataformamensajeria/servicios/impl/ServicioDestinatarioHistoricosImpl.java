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

// TODO: Auto-generated Javadoc
/**
 * <p>Maneja la persistencia de adjuntos de mensajes a traves de JPA.
 * 
 * @author jgonzvil
 * 
 */
@Service("servicioDestinatarioHistoricosImpl")
public class ServicioDestinatarioHistoricosImpl implements ServicioDestinatarioHistoricos{
	
	protected static final String SERVICIOMENSAJE = "ServicioMensajesAdjuntosHist.getTodosIdAdjuntosCons";

	protected static final String ERRORSDOTJOBDOT = "errors.job.cons.getFicherosAdjuntos";

	/**  logger. */
	Logger logger = Logger.getLogger(ServicioMensajesAdjuntosHistoricosImpl.class);
	
	/**  tbl destinatarios hist manager. */
	@Resource(name = "TblDestinatariosHistManagerImpl")
	private TblDestinatariosHistManager tblDestinatariosHistManager;
	
	/**  tbl destinatarios manager. */
	@Resource(name = "TblDestinatariosManagerImpl")
	private TblDestinatariosManager tblDestinatariosManager;
	
	/**  tbl destinatarios mens hist manager. */
	@Resource(name = "TblDestinatariosMensHistManagerImpl")
	private TblDestinatariosMensajesHistManager tblDestinatariosMensHistManager;
	
	/**  tbl destinatarios mens manager. */
	@Resource(name = "TblDestinatariosMensajesManagerImpl")
	private TblDestinatariosMensajesManager tblDestinatariosMensManager;
	
	/**  query executor destinatarios hist. */
	@Resource(name = "QueryExecutorDestinatariosHistImpl")
	private QueryExecutorDestinatariosHist queryExecutorDestinatariosHist;
	
	/**  query executor destinatarios mensajes hist. */
	@Resource(name = "QueryExecutorDestinatariosMensajesHistImpl")
	private QueryExecutorDestinatariosMensajesHist queryExecutorDestinatariosMensajesHist;
	
	/** Constante MAX. */
	private static final Integer MAX = 5000;
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioDestinatarioHistoricos#getTodosIdDestinatarioCons(java.util.List)
	 */
	////MIGRADO
	@Override
	@Transactional
	public List<Long> getTodosIdDestinatarioCons(List<Long> listaMensajes) throws BusinessException {
		List<Long> res = new ArrayList<>();
		try {
			res = queryExecutorDestinatariosHist.getIdDestinatariosCons(listaMensajes);
				
		} catch (Exception e) {
			logger.error(SERVICIOMENSAJE, e);
			throw new BusinessException(e, ERRORSDOTJOBDOT);
		}
		return res;

	}
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioDestinatarioHistoricos#getTodosIdDestinatarioMensajesCons(java.util.List)
 */
////MIGRADO
	@Override
	@Transactional
	public List<Long> getTodosIdDestinatarioMensajesCons(List<Long> listaMensajes) throws BusinessException {
		List<Long> res = new ArrayList<>();
		try {
			res = queryExecutorDestinatariosMensajesHist.getIdDestinatariosMensajesCons(listaMensajes);
				
		} catch (Exception e) {
			logger.error(SERVICIOMENSAJE, e);
			throw new BusinessException(e, ERRORSDOTJOBDOT);
		}
		return res;

	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioDestinatarioHistoricos#getTblDestinatariosHist(java.util.List)
	 */
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
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioDestinatarioHistoricos#insert(es.minhap.sim.model.TblDestinatariosHist)
	 */
	///MIGRADO
	@Override
	public Long insert(TblDestinatariosHist destinatarioHistorico) {
		return tblDestinatariosHistManager.insert(destinatarioHistorico);
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioDestinatarioHistoricos#getTblDestinatariosMensajesHist(java.util.List)
	 */
	///MIGRADO
	@Override
	public List<List<TblDestinatariosMensHist>> getTblDestinatariosMensajesHist(List<Long> subList) {
		List<List<TblDestinatariosMensHist>> res = new ArrayList<>();
		Integer total = queryExecutorDestinatariosMensajesHist.countConvertDestinatarioMensTODestinatarioMensHist(subList);

		Integer partes = (int) Math.ceil((double) total / MAX);
		for (int i = 0; i < partes; i++) {
			List<TblDestinatariosMensHist> listaTblDestinatariosMensajesHist = queryExecutorDestinatariosMensajesHist.convertDestinatarioMensTODestinatarioMensHist(subList, MAX, i * 
				MAX);
			if (null == listaTblDestinatariosMensajesHist) {
				listaTblDestinatariosMensajesHist = new ArrayList<>();
			}
			res.add(listaTblDestinatariosMensajesHist);
		}
		return res;
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioDestinatarioHistoricos#insert(es.minhap.sim.model.TblDestinatariosMensHist)
	 */
	///MIGRADO
	@Override
	public Long insert(TblDestinatariosMensHist destinatarioMensajeHistorico) {
		return tblDestinatariosMensHistManager.insert(destinatarioMensajeHistorico);
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioDestinatarioHistoricos#delete(java.lang.Long)
	 */
	///MIGRADO
	@Override
	public void delete(Long destinatarioid) {
		tblDestinatariosManager.delete(destinatarioid);
		
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioDestinatarioHistoricos#deleteDestinatarioMensaje(java.lang.Long)
	 */
	///MIGRADO
	@Override
	public void deleteDestinatarioMensaje(Long destinatarioMensajeId) {
		tblDestinatariosMensManager.delete(destinatarioMensajeId);
		
	}


}
