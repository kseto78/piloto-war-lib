package es.minhap.plataformamensajeria.iop.managerimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblAdjuntosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesAdjuntosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesHistManager;
import es.minhap.sim.dao.TblAdjuntosHistDAO;
import es.minhap.sim.model.TblAdjuntosHist;

/**
 * 
 * @author everis
 *
 */
@Service("TblAdjuntosHistManagerImpl")
public class TblAdjuntosHistManagerImpl implements TblAdjuntosHistManager {

	@Autowired
	private TblAdjuntosHistDAO adjuntosHistDAO;

	@Autowired
	private TblMensajesAdjuntosHistManager mensajesAdjuntosManager;

	@Autowired
	private TblMensajesHistManager mensajesManager;

	/**
	 * @see es.minhap.TblLotesEnviosManager.inserta
	 */
	@Override
	@Transactional
	public Long insertar(TblAdjuntosHist adjunto) {
		return adjuntosHistDAO.insert(adjunto);
	}



	@Override
	@Transactional
	public void eliminar(Long adjunto) {
		adjuntosHistDAO.delete(adjunto);
		
	}
	
	@Override
	@Transactional
	public TblAdjuntosHist getById(Long idAdjunto) {
		return adjuntosHistDAO.get(idAdjunto);
	}

	@Override
	public Long insertarAdjunto(TblAdjuntosHist adjunto) {
		return adjuntosHistDAO.insert(adjunto);
	}

	@Override
	public TblAdjuntosHist getAdjuntoById(Long adjuntoid) {
		return adjuntosHistDAO.get(adjuntoid);
	}

	@Override
	public void update(TblAdjuntosHist adjunto) {
		adjuntosHistDAO.update(adjunto);
		
	}
}
