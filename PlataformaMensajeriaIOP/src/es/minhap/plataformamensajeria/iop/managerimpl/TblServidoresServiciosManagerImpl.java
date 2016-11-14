package es.minhap.plataformamensajeria.iop.managerimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.TblServidoresServiciosManager;
import es.minhap.sim.dao.TblServidoresServiciosDAO;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblServidoresServicios;
import es.minhap.sim.query.TblServidoresServiciosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblServidoresServiciosManagerImpl")
public class TblServidoresServiciosManagerImpl implements TblServidoresServiciosManager {

	@Resource 
	private TblServidoresServiciosDAO servidoresServiciosDAO;
	
	 
	/**
	 * @see es.minhap.TblServidoresManager.getServidor
	 */
	@Override
	public Integer countServidoresServicios(TblServidoresServiciosQuery query) {
		return getServidoresServiciosDAO().count(query);
	}


	/**
	 * @return the servidoresServiciosDAO
	 */
	public TblServidoresServiciosDAO getServidoresServiciosDAO() {
		return servidoresServiciosDAO;
	}


	/**
	 * @param servidoresServiciosDAO the servidoresServiciosDAO to set
	 */
	public void setServidoresServiciosDAO(TblServidoresServiciosDAO servidoresServiciosDAO) {
		this.servidoresServiciosDAO = servidoresServiciosDAO;
	}


	@Override
	public TblServidoresServicios getServidoresServicio(TblServicios servicio) {
		TblServidoresServiciosQuery query = new TblServidoresServiciosQuery();
		//query.setTblServicios(servicio);
		this.servidoresServiciosDAO.search(query);
		return null;
	}
	
	

}
