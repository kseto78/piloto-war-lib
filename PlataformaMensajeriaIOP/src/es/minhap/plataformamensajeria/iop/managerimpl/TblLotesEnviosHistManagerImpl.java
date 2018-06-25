package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.sim.dao.TblLotesEnviosDAO;
import es.minhap.sim.dao.TblLotesEnviosHistDAO;
import es.minhap.sim.model.TblLotesEnvios;
import es.minhap.sim.model.TblLotesEnviosHist;
import es.minhap.sim.model.TblMensajesHist;
import es.minhap.sim.query.TblLotesEnviosHistQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblLotesEnviosHistManagerImpl")
public class TblLotesEnviosHistManagerImpl implements TblLotesEnviosHistManager {

	@Resource
	private TblLotesEnviosDAO tblLotesEnviosDAO;
	
	@Resource(name="TblLotesEnviosHistDAOImpl")
	private TblLotesEnviosHistDAO tblLotesEnviosHistDAO;

	@Resource(name="TblServiciosManagerImpl")
	private TblServiciosManager tblServiciosManager;
	
	@Autowired
	TblMensajesHistManager mensajeHistManager;
	
	@Override
	public TblLotesEnviosHist convertLoteEnvioTOLoteEnvioHist(Long idLote) {
		TblLotesEnvios lote = tblLotesEnviosDAO.get(idLote);
		return convertTOHist(lote);
		
	}

	@Override
	@Transactional
	public Long insert(TblLotesEnviosHist loteHist) {
		return tblLotesEnviosHistDAO.insert(loteHist);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Long> getIdByQuery(TblLotesEnviosHistQuery query) {
		return tblLotesEnviosHistDAO.searchId(query).getResults();
	}
	
	@Override
	@Transactional
	public void eliminar(Long loteenvioid) {
		tblLotesEnviosHistDAO.delete(loteenvioid);
	}
	
	@Override
	@Transactional
	public boolean isMultidestinatario(Long mensajeId) {
		TblMensajesHist mensaje = mensajeHistManager.getMensaje(mensajeId);
		return (null != mensaje.getTblLotesEnviosHist().getMultidestinatario()) ? mensaje.getTblLotesEnviosHist()
				.getMultidestinatario() : false;
	}
	
		@Override
	public TblLotesEnviosHist getLoteHistoricoById(Long loteHistId) {
		return tblLotesEnviosHistDAO.get(loteHistId);
	}
	
	private TblLotesEnviosHist convertTOHist(TblLotesEnvios lote) {
		TblLotesEnviosHist lh = null;
		if (null != lote){
			lh = new TblLotesEnviosHist();
			lh.setCreadopor(lote.getCreadopor());
			lh.setDescripcion(lote.getDescripcion());
			lh.setEstadoenvioid(lote.getEstadoenvioid());
			lh.setFechacreacion(lote.getFechacreacion());
			lh.setFechamodificacion(lote.getFechamodificacion());
			lh.setLoteenvioid(lote.getLoteenvioid());
			lh.setModificadopor(lote.getModificadopor());
			lh.setMultidestinatario(lote.getMultidestinatario());
			lh.setNombre(lote.getNombre());
			lh.setTblServicios(tblServiciosManager.getServicio(lote.getTblServicios().getServicioid()));
			
		}
		return lh;
	}

	
	
}
