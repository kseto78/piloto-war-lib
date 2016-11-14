package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.beans.entity.AuditoriaBean;
import es.minhap.plataformamensajeria.iop.manager.TblAuditoriaManager;
import es.minhap.sim.dao.TblAuditoriaDAO;
import es.minhap.sim.model.TblAuditoria;

/**
 * 
 * @author everis
 *
 */
@Service("TblAuditoriaManagerImpl")
public class TblAuditoriaManagerImpl implements TblAuditoriaManager {

	@Resource
	private TblAuditoriaDAO auditoriaDAO;

	/**
	 * @see es.minhap.TblLotesEnviosManager.insertarLote
	 */
	@Override
	@Transactional
	public Integer insertarAuditoria(AuditoriaBean auditoria) {
		Integer res;
		TblAuditoria a = createAuditoria(auditoria);
		res = getAuditoriaDAO().insert(a).intValue();

		return res;
	}

	private TblAuditoria createAuditoria(AuditoriaBean a) {
		TblAuditoria res = new TblAuditoria();
		res.setOperacion(a.getOperacion());
		res.setFecha(new Date());
		res.setServicioid(a.getServicioid());
		res.setUsuario(a.getUsuario());
		res.setPassword(a.getPassword());
		res.setCoderror(a.getCoderror());
		res.setDescripcion(a.getDescripcion());
		return res;
	}

	/**
	 * @return the auditoriaDAO
	 */
	public TblAuditoriaDAO getAuditoriaDAO() {
		return auditoriaDAO;
	}

	/**
	 * @param auditoriaDAO the auditoriaDAO to set
	 */
	public void setAuditoriaDAO(TblAuditoriaDAO auditoriaDAO) {
		this.auditoriaDAO = auditoriaDAO;
	}

}
