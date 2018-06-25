package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.TblSubEstadosManager;
import es.minhap.sim.dao.TblSubestadosDAO;
import es.minhap.sim.model.TblSubestados;
import es.minhap.sim.query.TblSubestadosQuery;

/**
 * Manager encargado de realizar las acciones sobre la tabla TBL_SUBESTADOS
 * 
 * @author everis
 *
 */
@Service("TblSubEstadosManagerImpl")
public class TblSubEstadosManagerImpl implements TblSubEstadosManager {

	@Resource 
	private TblSubestadosDAO subEstadosDAO;

	@Override
	public TblSubestados getSubEstadoByName(TblSubestadosQuery subEstadoQuery) {
		return getSubEstadosDAO().searchUnique(subEstadoQuery);
	}

	@Override
	public TblSubestados getSubEstadoById(Long idSubEstado) {
		return getSubEstadosDAO().get(idSubEstado);
	}

	/**
	 * @return the subEstadosDAO
	 */
	public TblSubestadosDAO getSubEstadosDAO() {
		return subEstadosDAO;
	}

	/**
	 * @param subEstadosDAO the subEstadosDAO to set
	 */
	public void setSubEstadosDAO(TblSubestadosDAO subEstadosDAO) {
		this.subEstadosDAO = subEstadosDAO;
	}

}
