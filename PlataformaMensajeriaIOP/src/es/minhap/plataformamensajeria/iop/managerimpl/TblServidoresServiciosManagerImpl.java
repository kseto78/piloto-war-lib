package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.beans.TblServidoresServiciosBean;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresServiciosManager;
import es.minhap.sim.dao.TblServidoresServiciosDAO;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblServidoresServicios;
import es.minhap.sim.query.TblServiciosQuery;
import es.minhap.sim.query.TblServidoresServiciosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblServidoresServiciosManagerImpl")
public class TblServidoresServiciosManagerImpl implements TblServidoresServiciosManager {

	protected static final String R_CONST_1 = " ";

	@Resource 
	private TblServidoresServiciosDAO servidoresServiciosDAO;
	
	@Resource(name="TblServiciosManagerImpl")
	private TblServiciosManager tblServiciosManager;
	
	@Resource TblLogManager tblLogManager;
		 
	/**
	 * @see es.minhap.TblServidoresManager.getServidor
	 */
	@Override
	@Transactional
	public Integer countServidoresServicios(TblServidoresServiciosQuery query) {
		return getServidoresServiciosDAO().count(query);
	}

	@Override
	@Transactional
	public TblServidoresServicios getServidoresServicio(TblServicios servicio) {
		TblServidoresServiciosQuery query = new TblServidoresServiciosQuery();
		//query.setTblServicios(servicio);
		this.servidoresServiciosDAO.search(query);
		return null;
	}


	@Override
	@Transactional
	public List<Long> getServidoresFromServidoresServiciosByServicio(Long servicioId) {
		List<Long> res = new ArrayList<>();
		TblServidoresServiciosQuery query = new TblServidoresServiciosQuery();
		TblServiciosQuery queryServicio = new TblServiciosQuery();
		queryServicio.setServicioid(servicioId);
		query.setTblServicios(queryServicio);
		List<TblServidoresServicios> lista = servidoresServiciosDAO.search(query).getResults();
		if (null != lista){
			for (TblServidoresServicios ss : lista) {
				res.add(ss.getTblServidores().getServidorid());
			}
		}
		return res;
	}
	
	@Override
	@Transactional
	public List<TblServidoresServiciosBean> getServidoresServicioByQuery(TblServidoresServiciosQuery query) {
		List<TblServidoresServiciosBean> res = new ArrayList<>();
			
		List<TblServidoresServicios> lista = servidoresServiciosDAO.search(query).getResults();
		for (TblServidoresServicios ssTO : lista) {
			TblServidoresServiciosBean ssBean = createSSBean(ssTO);
			res.add(ssBean);
		}
		return res;
	}


	private TblServidoresServiciosBean createSSBean(TblServidoresServicios ssTO) {
		TblServidoresServiciosBean res = new TblServidoresServiciosBean();
		res.setDIR3Organismo(null);
		res.setHeaderSMS(ssTO.getHeadersms());
		res.setNombreServicio(ssTO.getTblServicios().getNombre());
		res.setNombreServidor(ssTO.getTblServidores().getNombre());
		res.setNumIntentos(ssTO.getNumintentos());
		res.setProveedorPasswordSMS(ssTO.getProveedorpasswordsms());
		res.setProveedorUsuarioSMS(ssTO.getProveedorusuariosms());
		res.setServicioId(ssTO.getTblServicios().getServicioid().intValue());
		res.setServidorId(ssTO.getTblServidores().getServidorid().intValue());
		res.setServidorServicioId(ssTO.getServidorservicioid().intValue());
		res.setPrefijoSMS(ssTO.getPrefijosms());		
		return res;
	}
	
	@Override
	public void insert(TblServidoresServicios servidorServicio, String source, String accion, Long accionId,
			String descripcion) {
				
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(servidorServicio.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(descripcion + R_CONST_1 + servidorServicio.getTblServidores().getServidorid());
		log.setSourceid(servidorServicio.getTblServicios().getServicioid());
		log.setSourcename(source);
		tblLogManager.insertLog(log);
			
		servidoresServiciosDAO.insert(servidorServicio);
		
	}
	
	@Override
	public void delete(Long servidorServicioId, String source, String accion, Long accionId, String descripcion) {
		TblServidoresServicios ss = servidoresServiciosDAO.get(servidorServicioId);
		servidoresServiciosDAO.delete(servidorServicioId);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(ss.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(descripcion + R_CONST_1 + ss.getTblServidores().getServidorid());
		log.setSourceid(ss.getTblServicios().getServicioid());
		log.setSourcename(source);
		tblLogManager.insertLog(log);
		
		TblServicios servicio = ss.getTblServicios();
		servicio.setModificadopor(ss.getCreadopor());
		servicio.setFechamodificacion(new Date());
		tblServiciosManager.update(servicio, null, null, null);
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

	

	

	


	
	

}
