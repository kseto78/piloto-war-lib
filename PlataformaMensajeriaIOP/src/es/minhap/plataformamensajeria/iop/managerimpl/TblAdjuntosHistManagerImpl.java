package es.minhap.plataformamensajeria.iop.managerimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private TblAdjuntosHistDAO adjuntosDAO;

	@Autowired
	private TblMensajesAdjuntosHistManager mensajesAdjuntosManager;

	@Autowired
	private TblMensajesHistManager mensajesManager;

	@Override
	public Long insertarAdjunto(TblAdjuntosHist adjunto) {
		return adjuntosDAO.insert(adjunto);
	}

	@Override
	public TblAdjuntosHist getAdjuntoById(Long adjuntoid) {
		return adjuntosDAO.get(adjuntoid);
	}

	@Override
	public void update(TblAdjuntosHist adjunto) {
		adjuntosDAO.update(adjunto);
		
	}



}
