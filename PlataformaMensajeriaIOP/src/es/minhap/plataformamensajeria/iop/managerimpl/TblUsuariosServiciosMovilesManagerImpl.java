package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import es.minhap.plataformamensajeria.iop.manager.TblServiciosMovilesManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosServiciosMovilesManager;
import es.minhap.sim.dao.TblUsuariosServiciosMovilesDAO;
import es.minhap.sim.model.TblUsuariosServiciosMoviles;
import es.minhap.sim.query.TblUsuariosPushQuery;
import es.minhap.sim.query.TblUsuariosServiciosMovilesQuery;

/**
 * 
 * @author everis
 *
 */
@Service("tblUsuariosServiciosMovilesManagerImpl")
public class TblUsuariosServiciosMovilesManagerImpl implements TblUsuariosServiciosMovilesManager {
	static Logger logger = LoggerFactory.getLogger(TblUsuariosServiciosMovilesManagerImpl.class);
	
	@Resource
	private TblUsuariosServiciosMovilesDAO usuariosServiciosMovilesDAO;
	
	@Resource
	private TblUsuariosPushManager usuariosPushManager;
	
	@Resource
	private TblServiciosMovilesManager serviciosMovilesManager;


	/**
	 * @see es.minhap.TblUsuariosServiciosMovilesManagerImpl.comprobarUsuarioServicio
	 */
	@Override
	public Boolean comprobarUsuarioServicio(List<String> users, String idServicioMovil) {
		boolean exists = false;
		for (String user : users) {
			TblUsuariosServiciosMovilesQuery query = new TblUsuariosServiciosMovilesQuery();
			TblUsuariosPushQuery upQuery = new TblUsuariosPushQuery();
			upQuery.setUsuarioid(Long.parseLong(user));
			query.setTblUsuariosPush(upQuery);
			query.setServiciosmovilesid(Long.parseLong(idServicioMovil));
			ListIterator<Long> it = usuariosServiciosMovilesDAO.searchId(query).getResults().listIterator();

			while (null != it && it.hasNext()) {
				exists = true;
				it.next();
			}

			if (exists) {
				break;
			}

		}
		return exists;
	}
	
	/**
	 * @see es.minhap.TblUsuariosServiciosMovilesManagerImpl.registraUsuarioServicio
	 */
	@Override
	public Boolean registraUsuarioServicio(String idUsuario, String idServicioMovil, String usuario){
		TblUsuariosServiciosMoviles us = new TblUsuariosServiciosMoviles();
		us.setCreadopor(usuario);
		us.setFechacreacion(new Date());
		us.setEstadosuscripcion(1);
		us.setTblUsuariosPush(usuariosPushManager.getUsuarioPush(Long.parseLong(idUsuario)));
		us.setServiciosmovilesid(Long.parseLong(idServicioMovil));
		return (null != usuariosServiciosMovilesDAO.insert(us)) ? true : false;
	}
	
	/**
	 * @see es.minhap.TblUsuariosServiciosMovilesManagerImpl.registraUsuarioServicio
	 */
	@Override
	public Boolean updateUsuarioServicio(String idUsuario, String idServicioMovil, String usuario, boolean activo){
		boolean res = false;
		try{
		TblUsuariosServiciosMovilesQuery query = new TblUsuariosServiciosMovilesQuery();
		TblUsuariosPushQuery upQuery = new TblUsuariosPushQuery();
		query.setServiciosmovilesid(Long.parseLong(idServicioMovil));
		upQuery.setUsuarioid(Long.parseLong(idUsuario));
		query.setTblUsuariosPush(upQuery);
		TblUsuariosServiciosMoviles us = usuariosServiciosMovilesDAO.searchUnique(query);
		
		us.setModificadopor(usuario);
		us.setFechamodificacion(new Date());
		us.setEstadosuscripcion(activo ? 1 : 0 );
		us.setTblUsuariosPush(usuariosPushManager.getUsuarioPush(Long.parseLong(idUsuario)));
		us.setServiciosmovilesid(Long.parseLong(idServicioMovil));
		usuariosServiciosMovilesDAO.update(us);
		res = true;
		}catch (Exception e){
			logger.error("[TblUsuariosServiciosMovilesManagerImpl] Actualizando usuarios Servicios Moviles",e);
			res = false;
		}
		
		return res;
	}
	
	/**
	 * @see es.minhap.TblUsuariosServiciosMovilesManagerImpl.checkSuscriptionStatus
	 */
	@Override
	public String checkSuscriptionStatus(String idUsuario, String idServicioMovil){
		TblUsuariosServiciosMovilesQuery query = new TblUsuariosServiciosMovilesQuery();
		TblUsuariosPushQuery upQuery = new TblUsuariosPushQuery();
		query.setServiciosmovilesid(Long.parseLong(idServicioMovil));
		upQuery.setUsuarioid(Long.parseLong(idUsuario));
		query.setTblUsuariosPush(upQuery);
		
		return (null != usuariosServiciosMovilesDAO.searchUnique(query))? usuariosServiciosMovilesDAO.searchUnique(query).getEstadosuscripcion().toString() : "";
	}
	
	@Override
	public boolean comprobarUsuarioServicioValido(Integer usuario, String idServicioMovil) {
		TblUsuariosServiciosMovilesQuery query = new TblUsuariosServiciosMovilesQuery();
		TblUsuariosPushQuery upQuery = new TblUsuariosPushQuery();
		query.setServiciosmovilesid(Long.parseLong(idServicioMovil));
		query.setEstadosuscripcion(1);
		upQuery.setUsuarioid(Long.valueOf(usuario));
		query.setTblUsuariosPush(upQuery);
		return (null != usuariosServiciosMovilesDAO.search(query) && !usuariosServiciosMovilesDAO.search(query).getResults().isEmpty())? true : false;
	}

	/**
	 * @return the usuariosServiciosMovilesDAO
	 */
	public TblUsuariosServiciosMovilesDAO getUsuariosServiciosMovilesDAO() {
		return usuariosServiciosMovilesDAO;
	}

	/**
	 * @param usuariosServiciosMovilesDAO the usuariosServiciosMovilesDAO to set
	 */
	public void setUsuariosServiciosMovilesDAO(TblUsuariosServiciosMovilesDAO usuariosServiciosMovilesDAO) {
		this.usuariosServiciosMovilesDAO = usuariosServiciosMovilesDAO;
	}

	/**
	 * @return the usuariosPushManager
	 */
	public TblUsuariosPushManager getUsuariosPushManager() {
		return usuariosPushManager;
	}

	/**
	 * @param usuariosPushManager the usuariosPushManager to set
	 */
	public void setUsuariosPushManager(TblUsuariosPushManager usuariosPushManager) {
		this.usuariosPushManager = usuariosPushManager;
	}

	/**
	 * @return the serviciosMovilesManager
	 */
	public TblServiciosMovilesManager getServiciosMovilesManager() {
		return serviciosMovilesManager;
	}

	/**
	 * @param serviciosMovilesManager the serviciosMovilesManager to set
	 */
	public void setServiciosMovilesManager(TblServiciosMovilesManager serviciosMovilesManager) {
		this.serviciosMovilesManager = serviciosMovilesManager;
	}

	
}
