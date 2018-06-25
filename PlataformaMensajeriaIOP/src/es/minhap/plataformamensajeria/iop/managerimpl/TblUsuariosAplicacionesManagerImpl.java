package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosManager;
import es.minhap.sim.dao.TblUsuariosAplicacionesDAO;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.TblUsuariosAplicaciones;
import es.minhap.sim.query.TblUsuariosAplicacionesQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblUsuariosAplicacionesManagerImpl")
public class TblUsuariosAplicacionesManagerImpl implements TblUsuariosAplicacionesManager {

	@Resource
	private TblUsuariosAplicacionesDAO usuariosAplicacionesDAO;
	
	@Resource(name="tblUsuariosManagerImpl")
	private TblUsuariosManager tblUsuariosManager;
	
	@Resource 
	private TblLogManager tblLogManager;

	/**
	 * @see es.minhap.TblUsuariosAplicacionesManagerImpl.insertarLote
	 */
	@Override
	@Transactional
	public List<TblUsuariosAplicaciones> getUsuariosAplicacionesByQuery(TblUsuariosAplicacionesQuery query) {
		return usuariosAplicacionesDAO.search(query).getResults();
	}

	@Override
	@Transactional
	public TblUsuariosAplicaciones getUsuariosAplicacionesById(Long usuarioAplicacionId) {
		return usuariosAplicacionesDAO.get(usuarioAplicacionId);
	}

	@Override
	public Long insert(TblUsuariosAplicaciones usuarioAplicacionTO, String source, String accion, Long accionId,
			String descripcion) {
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(usuarioAplicacionTO.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(descripcion + " " + usuarioAplicacionTO.getTblUsuarios().getUsuarioid());
		log.setSourceid(usuarioAplicacionTO.getTblUsuarios().getUsuarioid());
		log.setSourcename(source);
		tblLogManager.insertLog(log);
		
		return usuariosAplicacionesDAO.insert(usuarioAplicacionTO);
	}

	@Override
	public void delete(TblUsuariosAplicaciones usuarioAplicacionTO, String source, String accion, Long accionId,
			String descripcion) {
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(usuarioAplicacionTO.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
//		log.setSourcedescription(descripcion + " " + usuarioAplicacionTO.getTblUsuarios().getUsuarioid());
//		log.setSourceid(usuarioAplicacionTO.getTblUsuarios().getUsuarioid());
		//Incidencia al borrar la aplicacion en Usuarios
		log.setSourcedescription(descripcion + " " + usuarioAplicacionTO.getUsuarioaplicacionid());
		log.setSourceid(usuarioAplicacionTO.getUsuarioaplicacionid());
		log.setSourcename(source);
		tblLogManager.insertLog(log);
		
		usuariosAplicacionesDAO.delete(usuarioAplicacionTO.getUsuarioaplicacionid());
		
	}

	
	
}
