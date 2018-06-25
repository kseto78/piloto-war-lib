package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesHistManager;
import es.minhap.sim.dao.TblDestinatariosMensHistDAO;
import es.minhap.sim.dao.TblDestinatariosMensajesDAO;
import es.minhap.sim.model.TblDestinatariosMensHist;
import es.minhap.sim.model.TblDestinatariosMensajes;
import es.minhap.sim.query.TblDestinatariosMensHistQuery;
import es.minhap.sim.query.TblDestinatariosMensajesQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblDestinatariosMensHistManagerImpl")
public class TblDestinatariosMensHistManagerImpl implements TblDestinatariosMensajesHistManager {

	@Resource
	private TblDestinatariosMensajesDAO destinatarioMensajeDAO;
	
	@Resource
	private TblDestinatariosMensHistDAO tblDestinatariosMensHistDAO;
	
	@Override
	public List<TblDestinatariosMensHist> convertDestinatarioMensTODestinatarioMensHist(Long idMensaje) {
		List<TblDestinatariosMensHist> res = new ArrayList<>();
		TblDestinatariosMensajesQuery query = new TblDestinatariosMensajesQuery();
		query.setMensajeid(idMensaje);
		
		List<TblDestinatariosMensajes> lista = destinatarioMensajeDAO.search(query).getResults();
		
		for (TblDestinatariosMensajes dm : lista) {
			res.add(convertTOHist(dm));
		}
		return res;	
	}

	@Override
	@Transactional
	public Long insert(TblDestinatariosMensHist h) {
		return tblDestinatariosMensHistDAO.insert(h);
	}

	@Override
	@Transactional
	public void eliminar (Long id) {
		tblDestinatariosMensHistDAO.delete(id);
	}

	@Override
	@Transactional
	public List<TblDestinatariosMensHist> getDestinatarioMensHistByQuery(TblDestinatariosMensHistQuery query) {
		return tblDestinatariosMensHistDAO.search(query).getResults();
	}

	@Override
	@Transactional
	public TblDestinatariosMensHist getDestinatarioMensaje(Long destinatarioMensajeId) {
		return tblDestinatariosMensHistDAO.get(destinatarioMensajeId);
	}

	
	private TblDestinatariosMensHist convertTOHist(TblDestinatariosMensajes destinatario) {
		TblDestinatariosMensHist dm = null;
		if (null != destinatario){
			dm = new TblDestinatariosMensHist();
			dm.setCodigoexterno(destinatario.getCodigoexterno());
			dm.setCreadopor(destinatario.getCreadopor());
			dm.setDestinatario(destinatario.getDestinatario());
			dm.setDestinatariosmensajes(destinatario.getDestinatariosmensajes());
			dm.setEstado(destinatario.getEstado());
			dm.setFechacreacion(destinatario.getFechacreacion());
			dm.setFechamodificacion(destinatario.getFechamodificacion());
			dm.setMensajeid(destinatario.getMensajeid());
			dm.setModificadopor(destinatario.getModificadopor());
			dm.setNodo(destinatario.getNodo());
			dm.setUim(destinatario.getUim());
			dm.setUltimoenvio(destinatario.getUltimoenvio());
			
			
		}
		return dm;
	}


}
