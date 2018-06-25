package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.TblAdjuntosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesAdjuntosManager;
import es.minhap.sim.dao.TblMensajesAdjuntosDAO;
import es.minhap.sim.model.TblMensajesAdjuntos;
import es.minhap.sim.query.TblMensajesAdjuntosQuery;
import es.minhap.sim.query.TblMensajesQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblMensajesAdjuntosManagerImpl")
public class TblMensajesAdjuntosManagerImpl implements TblMensajesAdjuntosManager {

	@Resource
	private TblMensajesAdjuntosDAO mensajesAdjuntosDAO;
	
	@Resource(name="TblAdjuntosManagerImpl")
	private TblAdjuntosManager adjuntosManager;

	/**
	 * @see es.minhap.TblMensajesAdjuntosManager.insertarMensajesAdjuntos
	 */
	@Override
	public Long insertarMensajesAdjuntos(TblMensajesAdjuntos menAd) {
		Long res;

		res = getMensajesAdjuntosDAO().insert(menAd);

		return res;
	}
	
	
	@Override
	public List<TblMensajesAdjuntos> listaAdjuntosByMensaje(Long mensajeId) {
		TblMensajesAdjuntosQuery query = new TblMensajesAdjuntosQuery();
		TblMensajesQuery mensajesQuery = new TblMensajesQuery();
		
		mensajesQuery.setMensajeid(mensajeId);
		query.setTblMensajes(mensajesQuery);
		List<TblMensajesAdjuntos> res = mensajesAdjuntosDAO.search(query).getResults();
		
		for (TblMensajesAdjuntos ma : res) {
			ma.setTblAdjuntos(adjuntosManager.getAdjuntoById(ma.getTblAdjuntos().getAdjuntoid()));
		}
		
		return res;
		
	}
	
	@Override
	public Integer countAdjuntosByMensaje(Long mensajeId) {
		TblMensajesAdjuntosQuery query = new TblMensajesAdjuntosQuery();
		TblMensajesQuery mensajesQuery = new TblMensajesQuery();
		
		mensajesQuery.setMensajeid(mensajeId);
		query.setTblMensajes(mensajesQuery);
		
		return mensajesAdjuntosDAO.count(query);
		
	}

	@Override
	public void delete(Long idMensajeAdjunto) {
		mensajesAdjuntosDAO.delete(idMensajeAdjunto);
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
