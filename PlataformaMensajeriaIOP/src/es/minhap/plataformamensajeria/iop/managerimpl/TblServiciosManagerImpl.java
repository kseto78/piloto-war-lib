package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.sim.dao.TblServiciosDAO;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.query.TblServiciosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblServiciosManagerImpl")
public class TblServiciosManagerImpl implements TblServiciosManager {

	@Resource 
	private TblServiciosDAO serviciosDAO;

	/**
	 * @see es.minhap.TblLotesEnviosManager.insertarLote
	 */
	@Override
	@Transactional
	public List<TblServicios> getServicios(TblServiciosQuery query) {
		return getServiciosDAO().search(query).getResults();
	}

	@Transactional
	@Override
	public TblServicios getServicio(Long servicioId){
		return getServiciosDAO().get(servicioId);
	}

	/**
	 * Comprueba si un servicio es multiorganismo
	 */
	@Transactional
	@Override
	public boolean isMultiOrganismo(Integer servicioId) {
		TblServiciosQuery query = new TblServiciosQuery();
		if (null != servicioId) {
			query.setServicioid(servicioId.longValue());
		}
		TblServicios servicio  = getServiciosDAO().searchUnique(query);
		return servicio.getMultiorganismo();
	}

	/**
	 * @return the serviciosDAO
	 */
	public TblServiciosDAO getServiciosDAO() {
		return serviciosDAO;
	}

	/**
	 * @param serviciosDAO the serviciosDAO to set
	 */
	public void setServiciosDAO(TblServiciosDAO serviciosDAO) {
		this.serviciosDAO = serviciosDAO;
	}
}
