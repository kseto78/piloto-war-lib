package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServidores;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.sim.dao.TblMensajesHistDAO;
import es.minhap.sim.model.TblMensajesHist;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.query.TblMensajesHistQuery;

/**
 * 
 * @author everis
 * 
 */
@Service("TblMensajesHistManagerImpl")
public class TblMensajesHistManagerImpl implements TblMensajesHistManager {

	@Resource
	private TblMensajesHistDAO tblMensajesHistDAO;
	
	@Autowired
	private TblServiciosManager serviciosManager;

	@Autowired
	private QueryExecutorServidores queryExecutorServidores;

	@Override
	@Transactional
	public List<TblMensajesHist> getMensajesByQuery(TblMensajesHistQuery query) {
		return tblMensajesHistDAO.search(query).getResults();
	}

	@Override
	public Integer countMensajesHistoricosByQuery(TblMensajesHistQuery query) {
		return tblMensajesHistDAO.count(query);
	}
	
	
	@Override
	@Transactional
	public void update(TblMensajesHist mensaje) {
		tblMensajesHistDAO.update(mensaje);

	}

	@Override
	public TblServicios getServicioByIdMensaje(Long idMensaje) {
		return serviciosManager.getServicio(queryExecutorServidores.getIdServicioByIdMensajeHist(idMensaje));
	}

}
