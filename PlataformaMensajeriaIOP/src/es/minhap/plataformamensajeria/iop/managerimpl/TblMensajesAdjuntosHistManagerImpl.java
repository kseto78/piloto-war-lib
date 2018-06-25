package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblAdjuntosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesAdjuntosHistManager;
import es.minhap.sim.dao.TblMensajesAdjuntosDAO;
import es.minhap.sim.dao.TblMensajesAdjuntosHistDAO;
import es.minhap.sim.model.TblAdjuntos;
import es.minhap.sim.model.TblAdjuntosHist;
import es.minhap.sim.model.TblMensajesAdjuntos;
import es.minhap.sim.model.TblMensajesAdjuntosHist;
import es.minhap.sim.model.TblMensajesHist;
import es.minhap.sim.query.TblMensajesAdjuntosHistQuery;
import es.minhap.sim.query.TblMensajesAdjuntosQuery;
import es.minhap.sim.query.TblMensajesHistQuery;
import es.minhap.sim.query.TblMensajesQuery;

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

	@Resource
	private TblMensajesAdjuntosDAO mensajesAdjuntosDAO;

	
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

	
	
	@Override
	public List<TblMensajesAdjuntosHist> convertMensajeTOMensajeHist(Long idMensaje, TblMensajesHist mensajeHist) {
		List<TblMensajesAdjuntosHist> res = new ArrayList<>();
		TblMensajesAdjuntosQuery query = new TblMensajesAdjuntosQuery();
		TblMensajesQuery queryMensaje = new TblMensajesQuery();
		queryMensaje.setMensajeid(idMensaje);
		query.setTblMensajes(queryMensaje);
		
		List<TblMensajesAdjuntos> lista = mensajesAdjuntosDAO.search(query).getResults();
		
		for (TblMensajesAdjuntos ma : lista) {
			res.add(convertTOHist(ma, mensajeHist));
		}
		return res;	
	}

	@Override
	@Transactional
	public Long insert(TblMensajesAdjuntosHist m) {
		return mensajesAdjuntosHistDAO.insert(m);
	}

	@Override
	@Transactional
	public void eliminar (Long id) {
		mensajesAdjuntosHistDAO.delete(id);
	}


	private TblMensajesAdjuntosHist convertTOHist(TblMensajesAdjuntos mensajeAdjunto, TblMensajesHist mensajeHist) {
		TblMensajesAdjuntosHist mah = null;
		if (null != mensajeAdjunto){
			mah = new TblMensajesAdjuntosHist();
			mah.setTblMensajesHist(mensajeHist);
			mah.setTblAdjuntosHist(converTOAdjuntoHist(mensajeAdjunto.getTblAdjuntos()));
			mah.setMensajeadjuntoid(mensajeAdjunto.getMensajeadjuntoid());
		}
		return mah;
	}

	private TblAdjuntosHist converTOAdjuntoHist(TblAdjuntos adjunto) {
		TblAdjuntosHist a = null;
		if (null != adjunto){
			a = new TblAdjuntosHist();
			a.setAdjuntoid(adjunto.getAdjuntoid());
			a.setContenido(adjunto.getContenido());
			a.setCreadopor(adjunto.getCreadopor());
			a.setFechacreacion(adjunto.getFechacreacion());
			a.setFechamodificacion(adjunto.getFechamodificacion());
			a.setImagen(adjunto.getImagen());
			a.setModificadopor(adjunto.getModificadopor());
			a.setNombre(adjunto.getNombre());
			
		}
		return a;
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
