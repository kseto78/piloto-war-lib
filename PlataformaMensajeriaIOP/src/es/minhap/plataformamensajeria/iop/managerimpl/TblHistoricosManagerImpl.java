package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.entity.EstadosBean;
import es.minhap.plataformamensajeria.iop.beans.entity.HistoricoBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorDestinatariosMensajes;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorEstados;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorHistoricos;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorSubEstados;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblEstadosManager;
import es.minhap.plataformamensajeria.iop.manager.TblGestionEnviosManager;
import es.minhap.plataformamensajeria.iop.manager.TblHistoricosManager;
import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosWebPushManager;
import es.minhap.sim.dao.TblHistoricosDAO;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblCanales;
import es.minhap.sim.model.TblDestinatariosMensajes;
import es.minhap.sim.model.TblGestionEnvios;
import es.minhap.sim.model.TblHistoricos;
import es.minhap.sim.model.TblLotesEnvios;
import es.minhap.sim.model.TblMensajes;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.query.TblEstadosQuery;
import es.minhap.sim.query.TblGestionEnviosQuery;
import es.minhap.sim.query.TblHistoricosQuery;

/**
 * ESta clase es la encargada de soportar toda la logica de negocio para la tabla TBL_HISTORICOS
 * 
 * 
 * @author everis
 *
 */
@Service("TblHistoricosManagerImpl")
public class TblHistoricosManagerImpl implements TblHistoricosManager {

	@Resource 
	private TblHistoricosDAO historicosDAO; 
	
	@Resource 
	private TblEstadosManager estadosManager;
	
	@Resource
	private TblMensajesManager mensajesManager;
	
	@Resource
	private TblLotesEnviosManager lotesManager;
	
	@Resource 
	private TblDestinatariosMensajesManager destinatariosMensajesManager;
	
	@Resource
	private TblDestinatariosManager destinatariosManager;
	
	@Resource
	private TblUsuariosPushManager usuariosPushManager;
	
	@Resource(name="tblUsuariosWebPushManagerImpl")
	private TblUsuariosWebPushManager usuariosWebPushManager;
	
	@Resource
	private TblGestionEnviosManager gestionEnviosManager;
	
	@Autowired
	private QueryExecutorEstados queryExecutorEstados;
	
	@Autowired
	private QueryExecutorDestinatariosMensajes queryExecutorDestinatariosMensajes;
	
	@Autowired
	private QueryExecutorSubEstados queryExecutorSubEstados;
	
	@Autowired
	private QueryExecutorHistoricos queryExecutorHistoricos;
	
	@Autowired
    private SessionFactory sessionFactorySIMApp;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	private static Logger LOG = LoggerFactory.getLogger(TblHistoricosManagerImpl.class);
	
	
	/**
	 * @see es.minhap.TblHistoricosManagerImpl.insertarHistorico
	 */
	@Override
	@Transactional
	public Integer insertarHistoricos(HistoricoBean hist, String usuario) {
		
		// Se implenta l�gica del trigger.
		Boolean isMultidestinatario = lotesManager.isMultidestinatario(hist.getMensajeid());
		EstadosBean estadoMensaje;
				
		Long historicoId;
		TblHistoricos historico = createHistorico(hist);
		historicoId = historicosDAO.insert(historico);
		sessionFactorySIMApp.getCurrentSession().flush();
		TblMensajes  mensaje = mensajesManager.getMensaje(hist.getMensajeid());
		//aqu� ahora hay que actualizar el lote tambi�n. tras actualizar los mensajes
		if (isMultidestinatario) {
			
//			if (null != hist.getSubestadoid()) { //si es porque tiene subestado
//				estadoMensaje = queryExecutorSubEstados.getEstadoByCode(hist.getSubestadoid().intValue());
//				updateDestinatariosMensajes(estadoMensaje, hist.getDestinatariosmensajes(), usuario);
//				sessionFactorySIMApp.getCurrentSession().flush();
//			}
						
			//utlizamos las prioridades para sacar el estado de mensaje a partir de destinatarios mensajes
			// Tratamiento cuando tenemos 1 lote -> n mensajes -> n destinatarios mensajes
			estadoMensaje = queryExecutorEstados.getEstadoByMensajeId(mensaje.getMensajeid());
			updateMensajes(estadoMensaje, mensaje, historicoId);
			sessionFactorySIMApp.getCurrentSession().flush();
			
			//actualizamos el lote
			actualizarLote(usuario, mensaje);
	
		} 	
		else { // no es multidestinatario
			
			//actualizamos el mensaje
			estadoMensaje = new EstadosBean(hist.getEstadoId(),estadosManager.getEstadoById(hist.getEstadoId()).getNombre());
			
			updateMensajes(estadoMensaje, mensaje, historicoId);
			sessionFactorySIMApp.getCurrentSession().flush();
			
			//actualizamos el lote
			actualizarLote(usuario, mensaje);
		}
		
		//actualizamos el estado de tabla historicos porque es calculado. NO HAY QUE HACERLO VER
		insertarGestionEnvios(hist.getMensajeid().toString(), hist.getDestinatariosmensajes(),hist.getServidorid());
		return historicoId.intValue();
	}

	private void actualizarLote(String usuario, TblMensajes mensaje) {
		EstadosBean estadoLote;
		estadoLote = queryExecutorEstados.getEstadoByLoteId(mensaje.getTblLotesEnvios().getLoteenvioid());
		
		updateLote(estadoLote, mensaje.getTblLotesEnvios().getLoteenvioid() ,  usuario);
		sessionFactorySIMApp.getCurrentSession().flush();
	}

	private void updateLote(EstadosBean estado, Long loteenvioid, String usuario) {
		TblLotesEnvios lote = lotesManager.getLoteEnvioById(loteenvioid);
		if (null != lote){
			lote.setModificadopor(usuario);
			lote.setFechamodificacion(new Date());
			lote.setEstadoenvioid(estado.getEstadoId());
		}
		
	}

//	private void updateDestinatariosMensajes(EstadosBean estado, Long destinatarioMensaje, String usuario) {
//		TblDestinatariosMensajes dest = destinatariosMensajesManager.getDestinatarioMensaje(destinatarioMensaje);
//		if (null != dest){
//			dest.setEstado(estadosManager.getEstadoById(estado.getEstadoId()).getNombre());
//			dest.setUltimoenvio(new Date());
//			dest.setNodo(null);
//			dest.setModificadopor(usuario);
//		}
//		destinatariosMensajesManager.update(dest);
//	}
	
	/**
	 * @see es.minhap.TblHistoricosManagerImpl.creaHistorico
	 */
//	hitoricosManager.creaHistorico(idMensaje.longValue(), destinatario.getDestinatariosmensajes(),
//			estadoId, null, null, null, username);
	@Override
	public Integer creaHistorico(Long mensajeId, Long destinatariosMensajesId, Long estadoId, 
			Long servidorId, String descripcion, String subestado, String usuario) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String estadoEnviando = ps.getMessage("constantes.ESTADO_ENVIANDO", null);
		
		HistoricoBean historicoBean = new HistoricoBean();
		historicoBean.setMensajeid(mensajeId);
		historicoBean.setDestinatariosmensajes(destinatariosMensajesId);
		if (null == servidorId && estadosManager.getEstadoById(estadoId).getNombre().equals(estadoEnviando)){
			historicoBean.setServidorid(servidorId);
		}else if (null == servidorId)
			historicoBean.setServidorid(queryExecutorHistoricos.getServidorByMensaje(mensajeId));
		else	
			historicoBean.setServidorid(servidorId);
		historicoBean.setFecha(new Date());
		historicoBean.setSubestadoid((null != subestado)? Long.parseLong(subestado) : null);
		if (null != subestado){
			EstadosBean estadoMensaje = queryExecutorSubEstados.getEstadoByCode(subestado);
			historicoBean.setDescripcion(estadoMensaje.getDescripcion());
			historicoBean.setEstadoId(estadoMensaje.getEstadoId());
		}
		else{
			historicoBean.setDescripcion(descripcion);
			historicoBean.setEstadoId(estadoId);
		}
		return insertarHistoricos(historicoBean, usuario);
	}
	
	@Override
	public Integer creaHistoricoPremium(Long mensajeId, Long destinatariosMensajesId, Long estadoId, 
			String usuario) {
				
		HistoricoBean historicoBean = new HistoricoBean();
		historicoBean.setMensajeid(mensajeId);
		historicoBean.setDestinatariosmensajes(destinatariosMensajesId);
		
		historicoBean.setFecha(new Date());
		historicoBean.setEstadoId(estadoId);
		
		//insertamos en tabla historicos y gestion envios
		TblHistoricos historico = createHistorico(historicoBean);
		Long historicoId = historicosDAO.insert(historico);
		sessionFactorySIMApp.getCurrentSession().flush();
		
		insertarGestionEnvios(mensajeId.toString(), destinatariosMensajesId,null);
		return historicoId.intValue();
	}
	
	@Override
	public void creaHistoricoEmail(String mensajeId, List<Long> destinatariosMensajes,Long estadoId, Long servidorId, String descripcion, String usuario) {
		for (Long d : destinatariosMensajes) {
			creaHistorico(Long.parseLong(mensajeId), d, estadoId, null, null, null, usuario);
		}		
	}
	
	@Override
	public Long getUltimoEstadoHistorico(Long mensajeId, Long destinatariosMensajes) {
		TblHistoricosQuery query = new TblHistoricosQuery();
		query.setDestinatariosmensajes(destinatariosMensajes);
		query.setMensajeid(mensajeId); 
		query.addOrder("historicoid", OrderType.DESC);
		List<TblHistoricos> lista = historicosDAO.search(query).getResults();
		return (null != lista && !lista.isEmpty())? lista.get(0).getTblEstados().getEstadoid() : 0;
	}

	@Override
	@Transactional
	public void delete(Long historicoid) {
		historicosDAO.delete(historicoid);
		
	}
	
	@Override
	public Boolean checkMensajeYaEnviado(Long mensajeId, Long destinatarioMensajeId, Long estado) {
		TblHistoricosQuery query = new TblHistoricosQuery();
		query.setMensajeid(mensajeId);
		if (null != destinatarioMensajeId){
			query.setDestinatariosmensajes(destinatarioMensajeId);
		}
		TblEstadosQuery queryEstados = new TblEstadosQuery();
		queryEstados.setEstadoid(estado);
		query.setTblEstados(queryEstados);
		List<Long> historicosEnviados = historicosDAO.searchId(query).getResults();
		return (null != historicosEnviados && !historicosEnviados.isEmpty())? true : false;
	}
	
	
	private void updateMensajes(EstadosBean estado, TblMensajes mensaje, Long historicoId) {
		mensaje.setEstadoactual(estado.getDescripcion());
		mensaje.setTblEstados(estadosManager.getEstadoById(estado.getEstadoId()));
		mensaje.setUltimoenvio(new Date());
		mensaje.setUltimoidhistorico(historicoId);
		mensaje.setNodo(null);
		mensajesManager.update(mensaje);
	}


	private TblHistoricos createHistorico(HistoricoBean a) {
		TblHistoricos res = new TblHistoricos();
		res.setDescripcion(a.getDescripcion());
		res.setDestinatariosmensajes(a.getDestinatariosmensajes());
		res.setFecha(a.getFecha());
		res.setMensajeid(a.getMensajeid());
		res.setServidorid(a.getServidorid());
		res.setSubestadoid(a.getSubestadoid());
		if (null != a.getEstadoId())
			res.setTblEstados(estadosManager.getEstadoById(a.getEstadoId()));
		if (null != a.getPlanificacionId())
			res.setTblPlanificaciones(null);
					
		return res;
	}
	
	/**
	 * @param mensajeId
	 * @param dest
	 */
	private void insertarGestionEnvios(String mensajeId, Long dest, Long servidorId) {
		TblGestionEnvios ge;
		TblMensajes tblMensajes = mensajesManager.getMensaje(Long.parseLong(mensajeId));
		String estado= tblMensajes.getEstadoactual();
		TblLotesEnvios lote = tblMensajes.getTblLotesEnvios();
		TblServicios servicio = lote.getTblServicios();
		TblCanales canal = servicio.getTblCanales();
				
		//comprobamos si hay que insertar o actualizar, segun exista o no
		TblGestionEnviosQuery query = new TblGestionEnviosQuery(Long.parseLong(mensajeId));
		ge = gestionEnviosManager.getGestionEnvios(query);
		
		if (null != ge){
			updateGestionEnvios(servidorId, ge, tblMensajes, estado, lote, canal);
		}else{
			insertGestionEnvios(dest, servidorId, tblMensajes, estado, lote, canal, servicio);
		}
		
	}

	private void insertGestionEnvios(Long dest, Long servidorId, TblMensajes tblMensajes,
			String estado, TblLotesEnvios lote, TblCanales canal, TblServicios servicio) {
		TblGestionEnvios ge;
		ge = new TblGestionEnvios();
		String codigoExterno;
		if (null != dest)
			codigoExterno = destinatariosMensajesManager.getDestinatarioMensaje(dest).getCodigoexterno();
		else
			codigoExterno = tblMensajes.getCodigoexterno();
		
		TblAplicaciones aplicacion = servicio.getTblAplicaciones();
				
		ge.setEstado(tblMensajes.getEstadoactual());
		ge.setEstadoid(estadosManager.getEstadoByName(estado).getEstadoid());
		ge.setEstadolote(estadosManager.getEstadoById(lote.getEstadoenvioid()).getNombre());
		ge.setUltimoenvio(Calendar.getInstance().getTime());
		ge.setAnio(Calendar.getInstance().get(Calendar.YEAR));
		ge.setMes(Calendar.getInstance().get(Calendar.MONTH)+1);
		ge.setServidorid(servidorId);
		ge.setAplicacion(aplicacion.getNombre());
		ge.setAplicacionid(aplicacion.getAplicacionid());
		ge.setCanal(canal.getNombre());
		ge.setCanalid(canal.getCanalid());
		ge.setCodigoexterno(codigoExterno);
		ge.setCodorganismo(tblMensajes.getCodorganismo());
		ge.setCodorganismopagador(tblMensajes.getCodorganismopagador());
		ge.setCodsia(tblMensajes.getCodsia());
		ge.setDocusuario(tblMensajes.getDocusuario());
		
		ge.setIcono(tblMensajes.getIcono());
		ge.setLoteenvioid(lote.getLoteenvioid());
		ge.setMensajeid(tblMensajes.getMensajeid());
		ge.setNombre(lote.getNombre());
		ge.setNombreusuario(tblMensajes.getNombreusuario());
		ge.setServicio(servicio.getNombre());
		ge.setServicioid(servicio.getServicioid());
		ge.setSonido(tblMensajes.getSonido());
		
		//obtenemos los destinatarios
		ge.setDestinatario(concatenarDestinatarios(tblMensajes.getMensajeid().toString(), canal));
		gestionEnviosManager.insertarGestionEnvios(ge);
	}

	private void updateGestionEnvios(Long servidorId, TblGestionEnvios ge, TblMensajes tblMensajes,
			String estado, TblLotesEnvios lote, TblCanales canal) {
		ge.setEstado(tblMensajes.getEstadoactual());
		ge.setEstadoid(estadosManager.getEstadoByName(estado).getEstadoid());
		ge.setEstadolote(estadosManager.getEstadoById(lote.getEstadoenvioid()).getNombre());
		ge.setUltimoenvio(Calendar.getInstance().getTime());
		ge.setAnio(Calendar.getInstance().get(Calendar.YEAR));
		ge.setMes(Calendar.getInstance().get(Calendar.MONTH)+1);
		ge.setServidorid(servidorId);
		//obtenemos los destinatarios
		ge.setDestinatario(concatenarDestinatarios(tblMensajes.getMensajeid().toString(), canal));
		gestionEnviosManager.actualizarGestionEnvios(ge);
	}
	/**
	 * @param mensajeId
	 * @param canal
	 * @return String
	 */
	private String concatenarDestinatarios(String mensajeId, TblCanales canal) {
		StringBuilder res = new StringBuilder();
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		
		String numMaxDestinatariosProp = ps.getMessage("num.max.destinatarios",null);
		int numMaxDestinatarios = 0;
		if((numMaxDestinatariosProp != null) && (numMaxDestinatariosProp != "")){
			 numMaxDestinatarios  = Integer.parseInt(numMaxDestinatariosProp);
		}
		LOG.debug("numMaxDestinatarios" + numMaxDestinatarios);
		
		switch (canal.getCanalid().intValue()) {
		case 1:
			List<String> listaDestinatarios = destinatariosManager.getDestinatarios(Long.parseLong(mensajeId));
			if (null != listaDestinatarios && listaDestinatarios.size() > 0 && listaDestinatarios.size() <= numMaxDestinatarios){
				for (String d : listaDestinatarios) {
					res.append(d + ";");
				}
				break;
			} else if (null != listaDestinatarios && listaDestinatarios.size() > 0 && listaDestinatarios.size() >= numMaxDestinatarios) {
				for (int i = 0; i < numMaxDestinatarios; i++) {
					res.append(listaDestinatarios.get(i) + ";");
				}
				break;
			}
		case 4:
			List<String> listaDestinatariosPush = usuariosPushManager.getNombresUsuariosMensaje(Long.parseLong(mensajeId));
			if (null != listaDestinatariosPush && listaDestinatariosPush.size() > 0){
				for (String d : listaDestinatariosPush) {
					res.append(d + ";");
				}
				break;
			}
		case 5:
			List<String> listaDestinatariosWebPush = usuariosWebPushManager.getUsuarioIdMensaje(Long.parseLong(mensajeId));
			if (null != listaDestinatariosWebPush && !listaDestinatariosWebPush.isEmpty()){
				for (String d : listaDestinatariosWebPush) {
					res.append(d + ";");
				}
				break;
			}
		default:
			for (TblDestinatariosMensajes dm : destinatariosMensajesManager.getDestinatarioMensajes(Long.parseLong(mensajeId))) {
				res.append(dm.getDestinatario() + ";");
			}
			break;
		}
		
		return res.substring(0, res.length()-1);
	}

	/**
	 * @return the historicosDAO
	 */
	public TblHistoricosDAO getHistoricosDAO() {
		return historicosDAO;
	}

	/**
	 * @param historicosDAO the historicosDAO to set
	 */
	public void setHistoricosDAO(TblHistoricosDAO historicosDAO) {
		this.historicosDAO = historicosDAO;
	}

	/**
	 * @return the estadosManager
	 */
	public TblEstadosManager getEstadosManager() {
		return estadosManager;
	}

	/**
	 * @param estadosManager the estadosManager to set
	 */
	public void setEstadosManager(TblEstadosManager estadosManager) {
		this.estadosManager = estadosManager;
	}

	/**
	 * @return the mensajesManager
	 */
	public TblMensajesManager getMensajesManager() {
		return mensajesManager;
	}

	/**
	 * @param mensajesManager the mensajesManager to set
	 */
	public void setMensajesManager(TblMensajesManager mensajesManager) {
		this.mensajesManager = mensajesManager;
	}

	/**
	 * @return the lotesManager
	 */
	public TblLotesEnviosManager getLotesManager() {
		return lotesManager;
	}

	/**
	 * @param lotesManager the lotesManager to set
	 */
	public void setLotesManager(TblLotesEnviosManager lotesManager) {
		this.lotesManager = lotesManager;
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
	 * @return the destinatariosManager
	 */
	public TblDestinatariosManager getDestinatariosManager() {
		return destinatariosManager;
	}

	/**
	 * @param destinatariosManager the destinatariosManager to set
	 */
	public void setDestinatariosManager(TblDestinatariosManager destinatariosManager) {
		this.destinatariosManager = destinatariosManager;
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
	 * @return the gestionEnviosManager
	 */
	public TblGestionEnviosManager getGestionEnviosManager() {
		return gestionEnviosManager;
	}

	/**
	 * @param gestionEnviosManager the gestionEnviosManager to set
	 */
	public void setGestionEnviosManager(TblGestionEnviosManager gestionEnviosManager) {
		this.gestionEnviosManager = gestionEnviosManager;
	}

	/**
	 * @return the queryExecutorEstados
	 */
	public QueryExecutorEstados getQueryExecutorEstados() {
		return queryExecutorEstados;
	}

	/**
	 * @param queryExecutorEstados the queryExecutorEstados to set
	 */
	public void setQueryExecutorEstados(QueryExecutorEstados queryExecutorEstados) {
		this.queryExecutorEstados = queryExecutorEstados;
	}

	/**
	 * @return the queryExecutorDestinatariosMensajes
	 */
	public QueryExecutorDestinatariosMensajes getQueryExecutorDestinatariosMensajes() {
		return queryExecutorDestinatariosMensajes;
	}

	/**
	 * @param queryExecutorDestinatariosMensajes the queryExecutorDestinatariosMensajes to set
	 */
	public void setQueryExecutorDestinatariosMensajes(QueryExecutorDestinatariosMensajes queryExecutorDestinatariosMensajes) {
		this.queryExecutorDestinatariosMensajes = queryExecutorDestinatariosMensajes;
	}

	/**
	 * @return the queryExecutorSubEstados
	 */
	public QueryExecutorSubEstados getQueryExecutorSubEstados() {
		return queryExecutorSubEstados;
	}

	/**
	 * @param queryExecutorSubEstados the queryExecutorSubEstados to set
	 */
	public void setQueryExecutorSubEstados(QueryExecutorSubEstados queryExecutorSubEstados) {
		this.queryExecutorSubEstados = queryExecutorSubEstados;
	}

	/**
	 * @return the queryExecutorHistoricos
	 */
	public QueryExecutorHistoricos getQueryExecutorHistoricos() {
		return queryExecutorHistoricos;
	}

	/**
	 * @param queryExecutorHistoricos the queryExecutorHistoricos to set
	 */
	public void setQueryExecutorHistoricos(QueryExecutorHistoricos queryExecutorHistoricos) {
		this.queryExecutorHistoricos = queryExecutorHistoricos;
	}

	/**
	 * @return the sessionFactorySIMApp
	 */
	public SessionFactory getSessionFactorySIMApp() {
		return sessionFactorySIMApp;
	}

	/**
	 * @param sessionFactorySIMApp the sessionFactorySIMApp to set
	 */
	public void setSessionFactorySIMApp(SessionFactory sessionFactorySIMApp) {
		this.sessionFactorySIMApp = sessionFactorySIMApp;
	}

}
