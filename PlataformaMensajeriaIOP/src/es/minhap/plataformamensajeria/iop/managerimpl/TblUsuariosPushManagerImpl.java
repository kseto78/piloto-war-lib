package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.common.entity.TextComparator;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.entity.AuditoriaBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorUsuariosPush;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblAuditoriaManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblPlataformasManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.sim.dao.TblUsuariosPushDAO;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblDestinatariosMensajes;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblUsuariosPush;
import es.minhap.sim.query.TblAplicacionesQuery;
import es.minhap.sim.query.TblUsuariosPushQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblUsuariosPushManagerImpl")
public class TblUsuariosPushManagerImpl implements TblUsuariosPushManager {

	private static final Logger logger = LoggerFactory.getLogger(TblUsuariosPushManagerImpl.class);
	
	@Resource
	private QueryExecutorUsuariosPush queryExecutor;
	
	@Resource
	private TblUsuariosPushDAO usuariosPushDAO;
	
	@Resource
	private TblDestinatariosMensajesManager destinatariosMensajesManager;
	
	@Resource
	private TblPlataformasManager plataformasManager;
	
	@Resource
	private TblAplicacionesManager aplicacionesManager;

	@Resource
	private TblServiciosManager serviciosManager;
	
	@Resource(name="TblAuditoriaManagerImpl")
	private TblAuditoriaManager auditoriaManager;
	
	@Resource
	private SessionFactory sessionFactoryApp;
	
	@Autowired
	private QueryExecutorUsuariosPush queryExecutorUsuarios;
	

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	private static final Integer timeSessionDefault = 60;
	
	/**
	 * @see es.minhap.TblUsuariosPushManager.getDispositivosUsuario
	 */
	@Override
	public List<Long> getDispositivosUsuario(String identificadorUsuario, Integer servicioId) {
		return  queryExecutor.listaUsuariosDispositivosPush(identificadorUsuario, servicioId);
		
	}
	
	@Override
	@Transactional
	public List<String> getNombresUsuariosMensaje(Long mensajeId) {
		List<String> res = new ArrayList<>();
		List<TblDestinatariosMensajes> lista = destinatariosMensajesManager.getDestinatarioMensajes(mensajeId);
				
		for (TblDestinatariosMensajes dm : lista) {
			TblUsuariosPush us = usuariosPushDAO.get(Long.parseLong(dm.getDestinatario()));
			if (null != us){
				res.add(us.getNombreusuario());
			}
		}
		
		
		return res;
	}

	@Override
	@Transactional
	public Integer altaUsuario(String nombreUsuario, String servicioId, String usuario, String password,
			String plataformaId, String tokenUsuario, String dispositivoId, String tokenSession, String uidDispositivo) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		Integer plataformaKO = Integer.parseInt(ps.getMessage("mensajesAuditoria.UsuariosPush.COD_ERROR_NO_PLATAFORMA", null));
		
		return plataformasManager.existPlataforma(Long.parseLong(plataformaId))? 
				insertarUsuario(nombreUsuario, servicioId, usuario, password, plataformaId, tokenUsuario, dispositivoId, ps, tokenSession, uidDispositivo) : plataformaKO;
				
	}
	
	@Override
	@Transactional
	public boolean eliminarUsuario(String token) {
		boolean res = true;
		
		try{
		TblUsuariosPushQuery query = new TblUsuariosPushQuery();
		query.setTokenusuario(token);
		query.setTokenusuarioComparator(TextComparator.EQUALS);
		List<TblUsuariosPush> listaUsuarios = usuariosPushDAO.search(query).getResults();
		for (TblUsuariosPush up : listaUsuarios) {
			up.setEliminado("S");
			up.setFechamodificacion(new Date());
			usuariosPushDAO.update(up);
		}		
		
		}catch(Exception e){
			logger.error("[TblUsuariosPushManagerImpl.eliminarUsuario] Eliminando Usuario", e);
			return false;
		}
		
		return res;
	}

	@Override
	@Transactional
	public Boolean comprobarExisteDispositivo(String idServicio, String idDispositivo, String idPlataforma, String nombreUsuario) {
		TblUsuariosPushQuery query = new TblUsuariosPushQuery();
		query.setDispositivoid(idDispositivo);
		query.setDispositivoidComparator(TextComparator.EQUALS);
		query.setServicioid(Long.parseLong(idServicio));
		query.setPlataformaid(Long.parseLong(idPlataforma));
		if (null != nombreUsuario)
			query.setNombreusuario(nombreUsuario);
		query.addOrder("usuarioid", OrderType.DESC);
		List<TblUsuariosPush> listaUsuarios = usuariosPushDAO.search(query).getResults();
				
		return (null != listaUsuarios && !listaUsuarios.isEmpty())? true : false;
	}
	

	
	@Override
	@Transactional
	public TblUsuariosPush getDatosUsuario(String idServicio, String idPlataforma, String idDispositivo, String nombreUsuario, boolean filtroEqualNombreUsuario) {
				
		TblUsuariosPushQuery query = new TblUsuariosPushQuery();
		query.setDispositivoid(idDispositivo);
		query.setDispositivoidComparator(TextComparator.EQUALS);
		query.setServicioid(Long.parseLong(idServicio));
		query.setPlataformaid(Long.parseLong(idPlataforma));
		if (null != nombreUsuario){
			query.setNombreusuario(nombreUsuario);
		
			//por si queremos filtrar por que el nombreUsuario sea igual o no
			//Iequals se define en TblUsuariosPushQuery
			if (filtroEqualNombreUsuario)
				query.setNombreusuarioComparator(TextComparator.EQUALS);
			else
				query.setNombreusuarioComparator(TextComparator.IEQUALS);
		}
		return usuariosPushDAO.searchUnique(query);
	}
	
	@Override
	@Transactional
	public boolean updateUsuario(TblUsuariosPush usuario) {
		boolean res = true;
		try{
			usuariosPushDAO.update(usuario);
		}catch(Exception e){
			logger.error("[TblUsuariosPushManagerImpl.updateUsuario] Actualizando Usuario Push", e);
			return false;
		}
		return res;
	}
	
	@Override
	@Transactional
	public TblUsuariosPush getDispositivoAplicacion(String idServicio, String idDispositivo, String idPlataforma) {
		TblUsuariosPushQuery query = new TblUsuariosPushQuery();
		query.setDispositivoid(idDispositivo);
		query.setDispositivoidComparator(TextComparator.EQUALS);
		query.setServicioid(Long.parseLong(idServicio));
		query.setPlataformaid(Long.parseLong(idPlataforma));

		return usuariosPushDAO.searchUnique(query);
	}
	
	
	@Override
	@Transactional
	public boolean insertUsuario(TblUsuariosPush usuario) {
		return (usuariosPushDAO.insert(usuario)>0)? true : false;
	}
	
	@Override
	@Transactional
	public List<Long> getDatosUsuario(String idServicio, String idPlataforma, String idDispositivo) {
		TblUsuariosPushQuery query = new TblUsuariosPushQuery();
		query.setDispositivoid(idDispositivo);
		query.setDispositivoidComparator(TextComparator.EQUALS);
		query.setServicioid(Long.parseLong(idServicio));
		query.setPlataformaid(Long.parseLong(idPlataforma));
		
		return usuariosPushDAO.searchId(query).getResults();
	}
	
	private Integer insertarUsuario(String nombreUsuario, String servicioId, String usuario, String password,
			String plataformaId, String tokenUsuario, String dispositivoId, PropertiesServices ps, String tokenSession, String uidDispositivo) {
		Integer res;
		String operacion = ps.getMessage("mensajesAuditoria.UsuariosPush.OPERACION_ALTA_USUARIO_PUSH", null);		
		String error = ps.getMessage("mensajesAuditoria.UsuariosPush.ERROR_BBDD", null);
		Integer codError = Integer.parseInt(ps.getMessage("mensajesAuditoria.UsuariosPush.COD_ERROR_BBDD", null));
		String correcto = ps.getMessage("mensajesAuditoria.UsuariosPush.USUARIO_CREADO", null);
		try{
		//comprobamos Aplicacion
		res = comprobarAplicacion(usuario, password, servicioId, operacion, ps);
		
		if (res >= 0)
			res = comprobarServicioAplicacion(servicioId, res, usuario, password, operacion, ps );
		if (res >= 0)
			res = comprobarServicioActivo(servicioId, usuario, password, operacion, ps);
		
		if (res >= 0){
			TblUsuariosPush up = new TblUsuariosPush();
			up.setDispositivoid(dispositivoId);
			up.setNombreusuario(nombreUsuario);
			up.setTokenusuario(tokenUsuario);
			up.setServicioid(Long.parseLong(servicioId));
			up.setPlataformaid(Long.parseLong(plataformaId));
			up.setFechacreacion(new Date());
			if (null != uidDispositivo){
				up.setTokensession(tokenSession);
				up.setUiddispositivo(uidDispositivo);
				up.setFechacaducidad(new Date());
			}
			
			res = usuariosPushDAO.insert(up).intValue();
			
			if (res > 0){
				AuditoriaBean auditoria = new AuditoriaBean(operacion, new Date(), null, null, Long.parseLong(servicioId), null,
						usuario, password, res.longValue(), correcto);
				auditoriaManager.insertarAuditoria(auditoria);
			}else{
				AuditoriaBean auditoria = new AuditoriaBean(operacion, new Date(), null, null, Long.parseLong(servicioId), null,
						usuario, password, codError.longValue(), error);
				auditoriaManager.insertarAuditoria(auditoria);
			}
		}
		}catch(Exception e){
			AuditoriaBean auditoria = new AuditoriaBean(operacion, new Date(), null, null, Long.parseLong(servicioId), null,
					usuario, password, codError.longValue(), error);
			auditoriaManager.insertarAuditoria(auditoria);
			logger.error("[TblUsuariosPushManagerImpl.insertarUsuario] Insertando Usuario Push", e);
			return codError;
		}
			
		return res;
	}

	
	@Override
	@Transactional
	public List<String> getIdUsersFromDispositivo(String idDispositivo, String nombreUsuario) {
		return queryExecutorUsuarios.getIdUsersFromDispositivo(idDispositivo, nombreUsuario);
	}

	@Override
	@Transactional
	public TblUsuariosPush getUsuarioPush(long idUsuario) {
		return usuariosPushDAO.get(idUsuario);
	}
	
	@Override
	@Transactional
	public boolean comprobarDispositivoRepetido(String codigo) {
		TblUsuariosPushQuery query = new TblUsuariosPushQuery();
		query.setDispositivoid(codigo);
		return (usuariosPushDAO.count(query) <= 0)? true : false;
	}
	
	@Override
	@Transactional
	public TblUsuariosPush existeUimDispositivo(String uidDispositivo, Long servicioId) {
		TblUsuariosPushQuery query = new TblUsuariosPushQuery();
		query.setUiddispositivo(uidDispositivo);
		query.setUiddispositivoComparator(TextComparator.EQUALS);
		query.setServicioid(servicioId);
		//query.setNombreIsNull(true);
		query.addOrder("usuarioid", OrderType.ASC);
		return (null != usuariosPushDAO.search(query) && !usuariosPushDAO.search(query).getResults().isEmpty())? 
				usuariosPushDAO.search(query).getResults().get(0) : null;
	}
	
	@Override
	@Transactional
	public boolean comprobarTokenSession(String uidDispositivo, String tokenSession, Integer timeSession) {
				
		TblUsuariosPushQuery query = new TblUsuariosPushQuery();
		query.setUiddispositivo(uidDispositivo);
		query.setUiddispositivoComparator(TextComparator.EQUALS);
		query.setTokensession(tokenSession);
		query.setTokensessionComparator(TextComparator.EQUALS);
		//query.setNombreIsNull(true);
		query.addOrder("usuarioid", OrderType.ASC);
		TblUsuariosPush usuario = (null != usuariosPushDAO.search(query) && !usuariosPushDAO.search(query).getResults().isEmpty())? 
				usuariosPushDAO.search(query).getResults().get(0) : null;
		
		if (null == usuario || null == usuario.getFechacaducidad())
			return false;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(usuario.getFechacaducidad());
		if (null != timeSession){
			calendar.add(Calendar.MINUTE, timeSession);
		}else{
			calendar.add(Calendar.MINUTE, timeSessionDefault);
		}
		
		return (calendar.after(Calendar.getInstance())? true : false);
	}
	
	@Override
	public TblUsuariosPush getUsuarioPushByQuery(TblUsuariosPushQuery query) {
		return (null != usuariosPushDAO.search(query) && !usuariosPushDAO.search(query).getResults().isEmpty())? 
				usuariosPushDAO.search(query).getResults().get(0) : null;
	}

	private Integer comprobarServicioActivo(String servicioId, String usuario, String password, String operacion, PropertiesServices ps) {
		String error = ps.getMessage("mensajesAuditoria.UsuariosPush.ERROR_SERVICIO", null);
		Integer codError = Integer.parseInt(ps.getMessage("mensajesAuditoria.UsuariosPush.COD_ERROR_SERVICIO", null));
		Long canalPush =  Long.parseLong(ps.getMessage("constantes.CANAL_PUSH", null));
		int res = 0;
		
		TblServicios servicio = serviciosManager.getServicio(Long.parseLong(servicioId));
		if (null ==servicio || null == servicio.getTblCanales() 
				|| !servicio.getTblCanales().getCanalid().equals(canalPush) || !servicio.getActivo()){
			AuditoriaBean auditoria = new AuditoriaBean(operacion, new Date(), null, null, null, null,
					usuario, password, codError.longValue(), error);
			auditoriaManager.insertarAuditoria(auditoria);
			return codError;
		}
		
		return res;
	}

	private Integer comprobarServicioAplicacion(String servicioId, Integer aplicacionId, String usuario, String password, String operacion, PropertiesServices ps) {
		String error = ps.getMessage("mensajesAuditoria.UsuariosPush.ERROR_APLICACION", null);
		Integer codError = Integer.parseInt(ps.getMessage("mensajesAuditoria.UsuariosPush.COD_ERROR_APLICACION", null));
		int res = 0;
		TblServicios servicio = serviciosManager.getServicio(Long.parseLong(servicioId));
		
		if (null == servicio || null == servicio.getTblAplicaciones() || 
				!servicio.getTblAplicaciones().getAplicacionid().equals(aplicacionId.longValue())){
			AuditoriaBean auditoria = new AuditoriaBean(operacion, new Date(), null, null, null, null,
					usuario, password, codError.longValue(), error);
			auditoriaManager.insertarAuditoria(auditoria);
			return codError;
		}
		
		return res;
	}

	private Integer comprobarAplicacion(String usuario, String password, String servicioId, String operacion, PropertiesServices ps) {
		String error = ps.getMessage("mensajesAuditoria.UsuariosPush.ERROR_USUARIO_APLICACION", null);
		Integer codError = Integer.parseInt(ps.getMessage("mensajesAuditoria.UsuariosPush.COD_ERROR_USUARIO_APLICACION", null));
		
		
		TblAplicacionesQuery query = new TblAplicacionesQuery();
		query.setUsuario(usuario);
		query.setPassword(Utils.encode64(password));
		List<TblAplicaciones> listaAplicaciones = aplicacionesManager.getAplicaciones(query);
		if (listaAplicaciones.size() != 1){
			AuditoriaBean auditoria = new AuditoriaBean(operacion, new Date(), null, null, Long.parseLong(servicioId), null,
					usuario, password, codError.longValue(), error);
			auditoriaManager.insertarAuditoria(auditoria);
			return codError;
		}else{
			return listaAplicaciones.get(0).getAplicacionid().intValue();
		}
		
	}

	/**
	 * @return the queryExecutor
	 */
	public QueryExecutorUsuariosPush getQueryExecutor() {
		return queryExecutor;
	}

	/**
	 * @param queryExecutor the queryExecutor to set
	 */
	public void setQueryExecutor(QueryExecutorUsuariosPush queryExecutor) {
		this.queryExecutor = queryExecutor;
	}

	/**
	 * @return the usuariosPushDAO
	 */
	public TblUsuariosPushDAO getUsuariosPushDAO() {
		return usuariosPushDAO;
	}

	/**
	 * @param usuariosPushDAO the usuariosPushDAO to set
	 */
	public void setUsuariosPushDAO(TblUsuariosPushDAO usuariosPushDAO) {
		this.usuariosPushDAO = usuariosPushDAO;
	}

	/**
	 * @return the destinatariosMensajesManager
	 */
	public TblDestinatariosMensajesManager getDestinatariosMensajesManager() {
		return destinatariosMensajesManager;
	}

	/**
	 * @param destinatariosMensajesManager the destinatariosMensajesManager to set
	 */
	public void setDestinatariosMensajesManager(TblDestinatariosMensajesManager destinatariosMensajesManager) {
		this.destinatariosMensajesManager = destinatariosMensajesManager;
	}

	/**
	 * @return the plataformasManager
	 */
	public TblPlataformasManager getPlataformasManager() {
		return plataformasManager;
	}

	/**
	 * @param plataformasManager the plataformasManager to set
	 */
	public void setPlataformasManager(TblPlataformasManager plataformasManager) {
		this.plataformasManager = plataformasManager;
	}

	/**
	 * @return the aplicacionesManager
	 */
	public TblAplicacionesManager getAplicacionesManager() {
		return aplicacionesManager;
	}

	/**
	 * @param aplicacionesManager the aplicacionesManager to set
	 */
	public void setAplicacionesManager(TblAplicacionesManager aplicacionesManager) {
		this.aplicacionesManager = aplicacionesManager;
	}

	/**
	 * @return the serviciosManager
	 */
	public TblServiciosManager getServiciosManager() {
		return serviciosManager;
	}

	/**
	 * @param serviciosManager the serviciosManager to set
	 */
	public void setServiciosManager(TblServiciosManager serviciosManager) {
		this.serviciosManager = serviciosManager;
	}

	/**
	 * @return the sessionFactoryApp
	 */
	public SessionFactory getSessionFactoryApp() {
		return sessionFactoryApp;
	}

	/**
	 * @param sessionFactoryApp the sessionFactoryApp to set
	 */
	public void setSessionFactoryApp(SessionFactory sessionFactoryApp) {
		this.sessionFactoryApp = sessionFactoryApp;
	}

	/**
	 * @return the queryExecutorUsuarios
	 */
	public QueryExecutorUsuariosPush getQueryExecutorUsuarios() {
		return queryExecutorUsuarios;
	}

	/**
	 * @param queryExecutorUsuarios the queryExecutorUsuarios to set
	 */
	public void setQueryExecutorUsuarios(QueryExecutorUsuariosPush queryExecutorUsuarios) {
		this.queryExecutorUsuarios = queryExecutorUsuarios;
	}

	/**
	 * @return the reloadableResourceBundleMessageSource
	 */
	public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		return reloadableResourceBundleMessageSource;
	}

	/**
	 * @param reloadableResourceBundleMessageSource the reloadableResourceBundleMessageSource to set
	 */
	public void setReloadableResourceBundleMessageSource(
			ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}

}
