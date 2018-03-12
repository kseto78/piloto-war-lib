package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblCanalesManager;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.sim.dao.TblServiciosDAO;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.query.TblServiciosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblServiciosManagerImpl")
public class TblServiciosManagerImpl implements TblServiciosManager {

	@Resource(name="TblAplicacionesManagerImpl") 
	private TblAplicacionesManager aplicacionesManager;
	
	@Resource(name="tblCanalesManagerImpl") 
	private TblCanalesManager canalesManager;
	
	@Resource 
	private TblServiciosDAO serviciosDAO;
	
	@Resource 
	private TblLogManager tblLogManager;
	

	/**
	 * @see es.minhap.TblLotesEnviosManager.insertarLote
	 */
	@Override
	@Transactional
	public List<TblServicios> getServicios(TblServiciosQuery query) {
		List<TblServicios> servicios = getServiciosDAO().search(query).getResults();
		
		for (TblServicios tblServicios : servicios) {
			if(tblServicios.getTblAplicaciones() != null && tblServicios.getTblAplicaciones().getAplicacionid() != null &&
					tblServicios.getTblCanales() != null && tblServicios.getTblCanales().getCanalid() != null){
				tblServicios.setTblAplicaciones(aplicacionesManager.getAplicacion(tblServicios.getTblAplicaciones().getAplicacionid()));
				tblServicios.setTblCanales(canalesManager.getCanalById(tblServicios.getTblCanales().getCanalid()));
			}
		}
		
		return servicios;
	}

	@Transactional
	@Override
	public TblServicios getServicio(Long servicioId){
		TblServicios servicio = serviciosDAO.get(servicioId);
		if (null != servicio){
			servicio.setTblAplicaciones(aplicacionesManager.getAplicacion(servicio.getTblAplicaciones().getAplicacionid()));
			servicio.setTblCanales(canalesManager.getCanalById(servicio.getTblCanales().getCanalid()));
		}
		return servicio;
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
	
	@Override
	@Transactional
	public Long insert(TblServicios servicio, String source, String accion, Long accionId) {
		Long id = serviciosDAO.insert(servicio);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(servicio.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(servicio.getNombre());
		log.setSourceid(id);
		log.setSourcename(source);
		tblLogManager.insertLog(log);

		return id;
	}
	
	@Override
	public void update(TblServicios servicio, String source, String accion, Long accionId) {
		serviciosDAO.update(servicio);
		
		if (null != source && null != accion && null != accionId){
			TblLog log = new TblLog();
			
			log.setAdtfecha(new Date());
			log.setAdtusuario(servicio.getCreadopor());
			log.setLogaccion(accionId);
			log.setLogdescripcion(accion);
			log.setSourcedescription(servicio.getNombre());
			log.setSourceid(servicio.getServicioid());
			log.setSourcename(source);
			tblLogManager.insertLog(log);
		}
		
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
