package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.TblAdjuntosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesAdjuntosHistManager;
import es.minhap.sim.dao.TblMensajesAdjuntosHistDAO;
import es.minhap.sim.model.TblMensajesAdjuntosHist;
import es.minhap.sim.query.TblMensajesAdjuntosHistQuery;
import es.minhap.sim.query.TblMensajesHistQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblMensajesAdjuntosHistManagerImpl")
public class TblMensajesAdjuntosHistManagerImpl implements TblMensajesAdjuntosHistManager {

	@Resource
	private TblMensajesAdjuntosHistDAO mensajesAdjuntosHistDAO;
	
	@Resource(name="TblAdjuntosHistManagerImpl")
	private TblAdjuntosHistManager adjuntosHistManager;

	/**
	 * @see es.minhap.TblMensajesAdjuntosManager.insertarMensajesAdjuntos
	 */
	@Override
	public Long insertarMensajesAdjuntos(TblMensajesAdjuntosHist menAd) {
		Long res;

		res = getMensajesAdjuntosHistDAO().insert(menAd);

		return res;
	}
	
	
	@Override
	public List<TblMensajesAdjuntosHist> listaAdjuntosByMensaje(Long mensajeId) {
		TblMensajesAdjuntosHistQuery query = new TblMensajesAdjuntosHistQuery();
		TblMensajesHistQuery mensajesQuery = new TblMensajesHistQuery();
		
		mensajesQuery.setMensajeid(mensajeId);
		query.setTblMensajesHist(mensajesQuery);
		List<TblMensajesAdjuntosHist> res = mensajesAdjuntosHistDAO.search(query).getResults();
		
		for (TblMensajesAdjuntosHist ma : res) {
			ma.setTblAdjuntosHist(adjuntosHistManager.getAdjuntoById(ma.getTblAdjuntosHist().getAdjuntoid()));
		}
		
		return res;
		
	}
	
	@Override
	public Integer countAdjuntosByMensaje(Long mensajeId) {
		TblMensajesAdjuntosHistQuery query = new TblMensajesAdjuntosHistQuery();
		TblMensajesHistQuery mensajesQuery = new TblMensajesHistQuery();
		
		mensajesQuery.setMensajeid(mensajeId);
		query.setTblMensajesHist(mensajesQuery);
		
		return mensajesAdjuntosHistDAO.count(query);
		
	}


	/**
	 * @return the mensajesAdjuntosHistDAO
	 */
	public TblMensajesAdjuntosHistDAO getMensajesAdjuntosHistDAO() {
		return mensajesAdjuntosHistDAO;
	}


	/**
	 * @param mensajesAdjuntosHistDAO the mensajesAdjuntosHistDAO to set
	 */
	public void setMensajesAdjuntosHistDAO(TblMensajesAdjuntosHistDAO mensajesAdjuntosHistDAO) {
		this.mensajesAdjuntosHistDAO = mensajesAdjuntosHistDAO;
	}


	/**
	 * @return the adjuntosHistManager
	 */
	public TblAdjuntosHistManager getAdjuntosHistManager() {
		return adjuntosHistManager;
	}


	/**
	 * @param adjuntosHistManager the adjuntosHistManager to set
	 */
	public void setAdjuntosHistManager(TblAdjuntosHistManager adjuntosHistManager) {
		this.adjuntosHistManager = adjuntosHistManager;
	}

	

}
