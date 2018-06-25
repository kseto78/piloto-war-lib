package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosHistManager;
import es.minhap.sim.dao.TblDestinatariosDAO;
import es.minhap.sim.dao.TblDestinatariosHistDAO;
import es.minhap.sim.model.TblDestinatarios;
import es.minhap.sim.model.TblDestinatariosHist;
import es.minhap.sim.query.TblDestinatariosHistQuery;
import es.minhap.sim.query.TblDestinatariosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblDestinatariosHistManagerImpl")
public class TblDestinatariosHistManagerImpl implements TblDestinatariosHistManager {

	@Resource
	private TblDestinatariosDAO destinatariosDAO;
	
	@Resource
	private TblDestinatariosHistDAO destinatariosHistDAO;
	
	@Override
	public List<TblDestinatariosHist> convertDestinatarioTODestinatarioHist(Long idMensaje) {
		List<TblDestinatariosHist> res = new ArrayList<>();
		TblDestinatariosQuery query = new TblDestinatariosQuery();
		query.setMensajeid(idMensaje);
		
		List<TblDestinatarios> lista = destinatariosDAO.search(query).getResults();
		
		for (TblDestinatarios ma : lista) {
			res.add(convertTOHist(ma));
		}
		return res;	
	}

	@Override
	@Transactional
	public Long insert(TblDestinatariosHist m) {
		return destinatariosHistDAO.insert(m);
	}

	@Override
	@Transactional
	public void eliminar (Long id) {
		destinatariosHistDAO.delete(id);
	}

	@Override
	@Transactional
	public TblDestinatariosHist getDestinatario(Long destinatarioId) {
		return destinatariosHistDAO.get(destinatarioId);
	}

	@Override
	@Transactional
	public List<String> getDestinatarios(Long mensajeid) {
		List<String> res = new ArrayList<>();
		TblDestinatariosHistQuery query = new TblDestinatariosHistQuery();
		query.setMensajeid(mensajeid);
		
		for (TblDestinatariosHist d : destinatariosHistDAO.search(query).getResults()) {
			if (!res.contains(d.getEmail()))
				res.add(d.getEmail());
		}
		
		return res;
	}
	
	private TblDestinatariosHist convertTOHist(TblDestinatarios destinatario) {
		TblDestinatariosHist d = null;
		if (null != destinatario){
			d = new TblDestinatariosHist();
			d.setCreadopor(destinatario.getCreadopor());
			d.setDestinatarioid(destinatario.getDestinatarioid());
			d.setEmail(destinatario.getEmail());
			d.setFechacreacion(destinatario.getFechacreacion());
			d.setFechamodificacion(destinatario.getFechamodificacion());
			d.setMensajeid(destinatario.getMensajeid());
			d.setModificadopor(destinatario.getModificadopor());
			d.setNombre(destinatario.getNombre());
			d.setTipodestinatario(destinatario.getTipodestinatario());
		}
		return d;
	}

	

	

}
