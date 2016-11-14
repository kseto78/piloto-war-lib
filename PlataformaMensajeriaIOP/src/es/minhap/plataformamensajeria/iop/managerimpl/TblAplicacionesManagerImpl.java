package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.common.entity.TextComparator;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.sim.dao.TblAplicacionesDAO;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.query.TblAplicacionesQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblAplicacionesManagerImpl")
public class TblAplicacionesManagerImpl implements TblAplicacionesManager {

	@Resource 
	private TblAplicacionesDAO aplicacionesDAO;
	
	 
	/**
	 * @see es.minhap.TblAplicacionesManager.getAplicaciones
	 */
	@Override
	public List<TblAplicaciones> getAplicaciones(TblAplicacionesQuery query) {
		return getAplicacionesDAO().search(query).getResults();
	}

	/**
	 * @see es.minhap.TblAplicacionesManager.getAplicacion
	 */
	@Override
	public TblAplicaciones getAplicacion(TblAplicacionesQuery query) {
		return getAplicacionesDAO().searchUnique(query);
	}

	/**
	 * @see es.minhap.TblAplicacionesManager.existeAplicacion
	 */
	@Override
	public Boolean existeAplicacion(String usuario, String password) {
		TblAplicacionesQuery query = new TblAplicacionesQuery();
		query.setUsuario(usuario);
		query.setPassword(Utils.encode64(password));
		query.setUsuarioComparator(TextComparator.EQUALS);
		query.setPasswordComparator(TextComparator.EQUALS);
		List<TblAplicaciones> listaAplicaciones = getAplicaciones(query);
		
		return (null != listaAplicaciones && listaAplicaciones.size() == 1)? true : false;
	}

	/**
	 * @return the aplicacionesDAO
	 */
	public TblAplicacionesDAO getAplicacionesDAO() {
		return aplicacionesDAO;
	}

	/**
	 * @param aplicacionesDAO the aplicacionesDAO to set
	 */
	public void setAplicacionesDAO(TblAplicacionesDAO aplicacionesDAO) {
		this.aplicacionesDAO = aplicacionesDAO;
	}
	
	/**
	 * @see es.minhap.TblAplicacionesManager.getAplicacion
	 */
	@Override
	public TblAplicaciones getAplicacion(Long idAplicacion) {
		return aplicacionesDAO.get(idAplicacion);
	}
}
