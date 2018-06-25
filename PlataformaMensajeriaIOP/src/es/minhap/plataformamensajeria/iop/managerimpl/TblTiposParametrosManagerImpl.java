package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblTiposParametrosManager;
import es.minhap.sim.dao.TblTiposParametrosDAO;
import es.minhap.sim.model.TblTiposParametros;
import es.minhap.sim.query.TblTiposParametrosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("tblTiposParametrosManagerImpl")
public class TblTiposParametrosManagerImpl implements TblTiposParametrosManager {

	@Resource 
	private TblTiposParametrosDAO tblTiposParametrosDAO;

	@Override
	@Transactional
	public List<TblTiposParametros> listaTiposParametrosPorTipo(Integer tipo) {
		TblTiposParametrosQuery query = new TblTiposParametrosQuery();
		query.setActivo(true);
		query.setTipo(tipo);
		
		return tblTiposParametrosDAO.search(query).getResults();
	}

	@Override
	@Transactional
	public TblTiposParametros getTipoParametroById(Long tipoParametroId) {
		return tblTiposParametrosDAO.get(tipoParametroId);
	}
	
	
}
