package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.TblErrorMensajeLogManager;
import es.minhap.sim.dao.TblErrorMensajeLogDAO;
import es.minhap.sim.model.TblErrorMensajeLog;

/**
 * Manager encargado de gestionar la tabla TBL_ERRORMENSAJE_LOG
 * 
 * @author everis
 *
 */
@Service("TblErrorMensajeLogManagerImpl")
public class TblErrorMensajeLogManagerImpl implements TblErrorMensajeLogManager {

	@Resource 
	private TblErrorMensajeLogDAO errorMensajeLogDAO;

	@Override
	public Long insertarLogError(TblErrorMensajeLog tblErrorMensajeLog) {
		return getErrorMensajeLogDAO().insert(tblErrorMensajeLog);
	}

	/**
	 * @return the errorMensajeLogDAO
	 */
	public TblErrorMensajeLogDAO getErrorMensajeLogDAO() {
		return errorMensajeLogDAO;
	}

	/**
	 * @param errorMensajeLogDAO the errorMensajeLogDAO to set
	 */
	public void setErrorMensajeLogDAO(TblErrorMensajeLogDAO errorMensajeLogDAO) {
		this.errorMensajeLogDAO = errorMensajeLogDAO;
	}

}
