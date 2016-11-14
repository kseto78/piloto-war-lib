package es.minhap.misim.bus.model.servicios;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import es.minhap.misim.bus.dao.AuditoriaDAO;
import es.minhap.misim.bus.model.Auditoria;
import es.minhap.misim.bus.query.AuditoriaQuery;

@Service("auditoriaManager")
public class AuditoriaManagerImpl implements AuditoriaManager{

	private static final Logger logger = Logger.getLogger(AuditoriaManagerImpl.class);
		
	@Resource
	AuditoriaDAO AuditoriaDAO;
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.AuditoriaManager#guardarAuditoria(es.minhap.misim.bus.model.Auditoria)
	 */
	public Long guardarAuditoria(Auditoria auditoria){
		logger.info("guardarAuditoria - start");
		return AuditoriaDAO.insert(auditoria);
	}	
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.AuditoriaManager#insertAuditoria(es.minhap.misim.bus.model.Auditoria)
	 */
	public Long insertAuditoria(Auditoria auditoria) {
		Long id = null;
		if(auditoria!=null){
			id = AuditoriaDAO.insert(auditoria);
		}
		return id;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.AuditoriaManager#getAuditoriaById(Long)
	 */
	public Auditoria getAuditoriaById(Long idAuditoria) {
		if(idAuditoria==null)
			return null;
		
		return AuditoriaDAO.get(idAuditoria);
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.AuditoriaManager#updateAuditoria(es.minhap.misim.bus.model.Auditoria)
	 */
	public boolean updateAuditoria(Auditoria auditoria) {
		boolean update = false;
		if(auditoria!=null && auditoria.getIdAuditoria()>0){
			AuditoriaDAO.update(auditoria);
			update = true;
		}
		return update;
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.AuditoriaManager#eliminarAuditoria(Long)
	 */
	public void eliminarAuditoria(Long id) {
		logger.info("eliminarAuditoria - start");
		AuditoriaDAO.delete(id);
		logger.info("eliminarAuditoria - end");
	}
	
	/**
	 * @see es.minhap.misim.bus.model.servicios.AuditoriaManager#getAuditoria(es.minhap.misim.bus.query.AuditoriaQuery)
	 */	
	public List<Auditoria> getAuditoria(AuditoriaQuery query) {
		return AuditoriaDAO.search(query).getResults();
	}
	
}
