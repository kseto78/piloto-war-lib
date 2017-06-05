package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblAltaNumMovilManager;
import es.minhap.sim.dao.TblAltaNumMovilDAO;
import es.minhap.sim.model.TblAltaNumMovil;
import es.minhap.sim.query.TblAltaNumMovilQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblAltaNumMovilManagerImpl")
public class TblAltaNumMovilManagerImpl implements TblAltaNumMovilManager {

	@Autowired
	private TblAltaNumMovilDAO altaNumMovilDAO;
	
	@Override
	@Transactional
	public TblAltaNumMovil getTblAltaNumMovilByQuery(TblAltaNumMovilQuery query) {
		List<TblAltaNumMovil> listado = altaNumMovilDAO.search(query).getResults();
		return (null !=listado && !listado.isEmpty())? listado.get(0) : null;
	}

	@Override
	@Transactional
	public void actualizar(TblAltaNumMovil tblAltaNumMovil) {
		altaNumMovilDAO.update(tblAltaNumMovil);
	}

	@Override
	@Transactional
	public Long insertar(TblAltaNumMovil alta) {
		return altaNumMovilDAO.insert(alta);
	}

	@Override
	@Transactional
	public void eliminar(Long idAltaNumMovil) {
		altaNumMovilDAO.delete(idAltaNumMovil);
	}

	@Override
	public List<Long> getListaIdTblAltaNumMovilByQuery(TblAltaNumMovilQuery query) {
		return altaNumMovilDAO.searchId(query).getResults();
	}

	
}
