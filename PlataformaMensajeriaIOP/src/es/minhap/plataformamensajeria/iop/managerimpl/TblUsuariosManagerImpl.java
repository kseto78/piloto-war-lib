package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosManager;
import es.minhap.sim.dao.TblUsuariosDAO;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.TblUsuarios;
import es.minhap.sim.query.TblUsuariosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("tblUsuariosManagerImpl")
public class TblUsuariosManagerImpl implements TblUsuariosManager {

	private static final Logger logger = Logger.getLogger(TblUsuariosManagerImpl.class);
	
	@Resource
	private TblUsuariosDAO tblUsuariosDAO;
	
	@Resource 
	private TblLogManager tblLogManager;

	///MIGRADO
	@Override
	public Integer getRolByUsername(String login) {
		TblUsuariosQuery query = new TblUsuariosQuery();
		query.setLogin(login);
		List<TblUsuarios> lista = tblUsuariosDAO.search(query).getResults();
		return (null != lista && lista.size()==1)? lista.get(0).getRolid() : null;
	}

	///MIGRADO
	@Override
	public Long getUsuarioByUsername(String login) {
		TblUsuariosQuery query = new TblUsuariosQuery();
		query.setLogin(login);
		List<TblUsuarios> lista = tblUsuariosDAO.search(query).getResults();
		return (null != lista && lista.size()==1)? lista.get(0).getUsuarioid() : null;
	}

	@Override
	public Long getUsuarioByUsernameActivo(String login) {
		TblUsuariosQuery query = new TblUsuariosQuery();
		query.setLogin(login);
		query.setActivo(true);
		List<TblUsuarios> lista = tblUsuariosDAO.search(query).getResults();
		return (null != lista && lista.size()==1)? lista.get(0).getUsuarioid() : null;
	}
	
	///MIGRADO
	@Override
	public TblUsuarios getById(Long usuarioid) {
		return tblUsuariosDAO.get(usuarioid);
	}
	
	///MIGRADO
	@Override
	public List<TblUsuarios> getUsuariosAplicacionesByQuery(TblUsuariosQuery query) {
		return tblUsuariosDAO.search(query).getResults();
	}
	
	///MIGRADO
	@Override
	public Long insert(TblUsuarios usuarioTO, String source, String accion, Long accionId) {
		Long id = tblUsuariosDAO.insert(usuarioTO);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(usuarioTO.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(usuarioTO.getNombre());
		log.setSourceid(id);
		log.setSourcename(source);
		tblLogManager.insertLog(log);

		return id;
	}
	
	///MIGRADO
	@Override
	public void update(TblUsuarios usuario, String source, String accion, Long accionId) {
		tblUsuariosDAO.update(usuario);		
		
		TblLog log = new TblLog();
		
		log.setAdtfecha(new Date());
		log.setAdtusuario(usuario.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(usuario.getNombre());
		log.setSourceid(usuario.getUsuarioid());
		log.setSourcename(source);
		tblLogManager.insertLog(log);
	}
	
	///MIGRADO
	@Override
	public void delete(Long usuarioId, String source, String accion, Long accionId) {
		TblUsuarios u  = tblUsuariosDAO.get(usuarioId);
		tblUsuariosDAO.delete(usuarioId);
	
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(u.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourceid(u.getUsuarioid());
		log.setSourcename(source);
		tblLogManager.insertLog(log);		
	}
	
	/**
	 * @return the tblUsuariosDAO
	 */
	public TblUsuariosDAO getTblUsuariosDAO() {
		return tblUsuariosDAO;
	}

	/**
	 * @param tblUsuariosDAO the tblUsuariosDAO to set
	 */
	public void setTblUsuariosDAO(TblUsuariosDAO tblUsuariosDAO) {
		this.tblUsuariosDAO = tblUsuariosDAO;
	}

	

	

	

	

	

	

	
	
	}
