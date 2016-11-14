package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.TblMensajesAdjuntosManager;
import es.minhap.sim.dao.TblMensajesAdjuntosDAO;
import es.minhap.sim.model.TblMensajesAdjuntos;

/**
 * 
 * @author everis
 *
 */
@Service("TblMensajesAdjuntosManagerImpl")
public class TblMensajesAdjuntosManagerImpl implements TblMensajesAdjuntosManager {

	@Resource
	private TblMensajesAdjuntosDAO mensajesAdjuntosDAO;

	/**
	 * @see es.minhap.TblMensajesAdjuntosManager.insertarMensajesAdjuntos
	 */
	@Override
	public Long insertarMensajesAdjuntos(TblMensajesAdjuntos menAd) {
		Long res;

		res = getMensajesAdjuntosDAO().insert(menAd);

		return res;
	}

	/**
	 * @return the mensajesAdjuntosDAO
	 */
	public TblMensajesAdjuntosDAO getMensajesAdjuntosDAO() {
		return mensajesAdjuntosDAO;
	}

	/**
	 * @param mensajesAdjuntosDAO the mensajesAdjuntosDAO to set
	 */
	public void setMensajesAdjuntosDAO(TblMensajesAdjuntosDAO mensajesAdjuntosDAO) {
		this.mensajesAdjuntosDAO = mensajesAdjuntosDAO;
	}

}
