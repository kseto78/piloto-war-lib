package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.TextComparator;
import es.minhap.plataformamensajeria.iop.manager.TblEstadosManager;
import es.minhap.sim.dao.TblEstadosDAO;
import es.minhap.sim.model.TblEstados;
import es.minhap.sim.query.TblEstadosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblEstadosManagerImpl")
public class TblEstadosManagerImpl implements TblEstadosManager {

	@Resource 
	private TblEstadosDAO estadosDAO;

	/**
	 * @see es.minhap.TblEstadosManager.getEstadoByName
	 */
	@Override
	@Transactional
	public TblEstados getEstadoByName(String nombre) {
		TblEstadosQuery query = new TblEstadosQuery();
		query.setNombre(nombre);
		query.setNombreComparator(TextComparator.EQUALS);
		return getEstadosDAO().searchUnique(query);
	}


	/**
	 * @see es.minhap.TblEstadosManager.getEstadoById
	 */
	@Override
	public TblEstados getEstadoById(Long idEstado) {
		return getEstadosDAO().get(idEstado);
	}


	/**
	 * @return the estadosDAO
	 */
	public TblEstadosDAO getEstadosDAO() {
		return estadosDAO;
	}


	/**
	 * @param estadosDAO the estadosDAO to set
	 */
	public void setEstadosDAO(TblEstadosDAO estadosDAO) {
		this.estadosDAO = estadosDAO;
	}
}
