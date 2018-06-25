package es.minhap.plataformamensajeria.iop.misim.managerimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.misim.bus.dao.ComunicacionDAO;
import es.minhap.misim.bus.model.Comunicacion;
import es.minhap.misim.bus.query.ComunicacionQuery;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.misim.manager.ComunicacionesManager;

/**
 * 
 * @author everis
 *
 */
@Service("ComunicacionesManagerImpl")
public class ComunicacionesManagerImpl implements ComunicacionesManager {

	@Resource 
	private ComunicacionDAO comunicacionDAO;
	
	@Resource 
	private TblLogManager tblLogManager;
	

	/**
	 * @see es.minhap.TblLotesEnviosManager.insertarLote
	 */
	@Override
	@Transactional
	public List<Comunicacion> getComunicaciones(ComunicacionQuery query) {
		List<Comunicacion> comunicaciones = getComunicacionDAO().search(query).getResults();
		
		return comunicaciones;
	}
	
	@Override
	public List<Comunicacion> getComunicacionesOrdenados() {
		ComunicacionQuery query = new ComunicacionQuery();
		query.addOrder("nombre", OrderType.ASC);
		
		return comunicacionDAO.search(query).getResults();
	}
	
	@Transactional
	@Override
	public Comunicacion getComunicacion(Long idComunicacion){
		return comunicacionDAO.get(idComunicacion);
	}

//	@Override
//	@Transactional
//	public Long insert(TblServicios servicio, String source, String accion, Long accionId) {
//		Long id = serviciosDAO.insert(servicio);
//		
//		TblLog log = new TblLog();
//		log.setAdtfecha(new Date());
//		log.setAdtusuario(servicio.getCreadopor());
//		log.setLogaccion(accionId);
//		log.setLogdescripcion(accion);
//		log.setSourcedescription(servicio.getNombre());
//		log.setSourceid(id);
//		log.setSourcename(source);
//		tblLogManager.insertLog(log);
//
//		return id;
//	}
//	
//	@Override
//	public void update(TblServicios servicio, String source, String accion, Long accionId) {
//		serviciosDAO.update(servicio);
//		
//		TblLog log = new TblLog();
//		
//		log.setAdtfecha(new Date());
//		log.setAdtusuario(servicio.getCreadopor());
//		log.setLogaccion(accionId);
//		log.setLogdescripcion(accion);
//		log.setSourcedescription(servicio.getNombre());
//		log.setSourceid(servicio.getServicioid());
//		log.setSourcename(source);
//		tblLogManager.insertLog(log);
//		
//	}
	
	public ComunicacionDAO getComunicacionDAO() {
		return comunicacionDAO;
	}


	public void setComunicacionDAO(ComunicacionDAO comunicacionDAO) {
		this.comunicacionDAO = comunicacionDAO;
	}

}
