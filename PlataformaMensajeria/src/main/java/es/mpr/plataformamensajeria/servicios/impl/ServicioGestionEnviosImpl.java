package es.mpr.plataformamensajeria.servicios.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.common.entity.OrderType;
import es.minhap.common.spring.ApplicationContextProvider;
import es.minhap.misim.bus.model.ViewMisim;
import es.minhap.misim.bus.query.ViewMisimQuery;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorAdjuntos;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorDestinatariosMensajes;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorGestionEnvios;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajes;
import es.minhap.plataformamensajeria.iop.manager.TblAdjuntosManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblGestionEnviosManager;
import es.minhap.plataformamensajeria.iop.manager.TblHistoricosManager;
import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;
import es.minhap.plataformamensajeria.iop.manager.ViewHistoricoManager;
import es.minhap.plataformamensajeria.iop.manager.ViewLotesEnviosDetalladaManager;
import es.minhap.plataformamensajeria.iop.misim.manager.PeticionManager;
import es.minhap.plataformamensajeria.iop.misim.manager.ViewMisimManager;
import es.minhap.sim.model.TblAdjuntos;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblDestinatarios;
import es.minhap.sim.model.TblDestinatariosMensajes;
import es.minhap.sim.model.TblGestionEnvios;
import es.minhap.sim.model.TblMensajes;
import es.minhap.sim.model.TblUsuariosPush;
import es.minhap.sim.model.ViewGestionEnviosDestId;
import es.minhap.sim.model.ViewHistorico;
import es.minhap.sim.model.ViewHistoricoMultidest;
import es.minhap.sim.model.ViewLotesEnviosDetallada;
import es.minhap.sim.query.TblDestinatariosMensajesQuery;
import es.minhap.sim.query.TblGestionEnviosQuery;
import es.minhap.sim.query.ViewHistoricoMultidestQuery;
import es.minhap.sim.query.ViewHistoricoQuery;
import es.minhap.sim.query.ViewLotesEnviosDetalladaQuery;
import es.mpr.plataformamensajeria.beans.AdjuntoEmailBean;
import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.DestinatariosMensajesBean;
import es.mpr.plataformamensajeria.beans.DetalleEnvioBean;
import es.mpr.plataformamensajeria.beans.DetalleLoteBean;
import es.mpr.plataformamensajeria.beans.EnvioMensajesAplicacionBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioBean;
import es.mpr.plataformamensajeria.beans.HistoricoBean;
import es.mpr.plataformamensajeria.beans.MensajeBean;
import es.mpr.plataformamensajeria.beans.ViewMisimBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.plataformamensajeria.util.UtilCreateFile;
import es.redsara.misim.misim_bus_webapp.EnvioMensajes;
import es.redsara.misim.misim_bus_webapp.EnvioMensajesServiceWSBindingPortType;
import es.redsara.misim.misim_bus_webapp.OperacionMensajes;
import es.redsara.misim.misim_bus_webapp.OperacionMensajesServicePortType;
import es.redsara.misim.misim_bus_webapp.peticion.Adjunto;
import es.redsara.misim.misim_bus_webapp.peticion.Adjuntos;
import es.redsara.misim.misim_bus_webapp.peticion.CamposAuxiliares;
import es.redsara.misim.misim_bus_webapp.peticion.CamposDetalleTrasero;
import es.redsara.misim.misim_bus_webapp.peticion.CamposPrincipales;
import es.redsara.misim.misim_bus_webapp.peticion.CamposSecundarios;
import es.redsara.misim.misim_bus_webapp.peticion.DestinatarioMail;
import es.redsara.misim.misim_bus_webapp.peticion.DestinatarioPush;
import es.redsara.misim.misim_bus_webapp.peticion.DestinatarioSMS;
import es.redsara.misim.misim_bus_webapp.peticion.DestinatarioWebPush;
import es.redsara.misim.misim_bus_webapp.peticion.Destinatarios;
import es.redsara.misim.misim_bus_webapp.peticion.DestinatariosMail;
import es.redsara.misim.misim_bus_webapp.peticion.DestinatariosPush;
import es.redsara.misim.misim_bus_webapp.peticion.DestinatariosSMS;
import es.redsara.misim.misim_bus_webapp.peticion.DestinatariosWebPush;
import es.redsara.misim.misim_bus_webapp.peticion.MensajeEmail;
import es.redsara.misim.misim_bus_webapp.peticion.MensajePush;
import es.redsara.misim.misim_bus_webapp.peticion.MensajeSMS;
import es.redsara.misim.misim_bus_webapp.peticion.MensajeWebPush;
import es.redsara.misim.misim_bus_webapp.peticion.Mensajes;
import es.redsara.misim.misim_bus_webapp.peticion.Passbook;
import es.redsara.misim.misim_bus_webapp.peticion.Peticion;
import es.redsara.misim.misim_bus_webapp.peticion.PkFields;
import es.redsara.misim.misim_bus_webapp.peticionOperacion.Mensaje;
import es.redsara.misim.misim_bus_webapp.peticionOperacion.PeticionLote;
import es.redsara.misim.misim_bus_webapp.peticionOperacion.PeticionMensaje;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;


/**
 * <p>
 * Maneja la persistencia y b&uacute;squeda de canales a traves de JPA.
 * 
 * @author
 * 
 */
@Service("servicioGestionEnviosImpl")
public class ServicioGestionEnviosImpl implements ServicioGestionEnvios {
	
	/**  logger. */
	Logger logger = Logger.getLogger(ServicioGestionEnviosImpl.class);

	/**  servicio aplicacion. */
	@Resource(name = "servicioAplicacionImpl")
	private ServicioAplicacion servicioAplicacion;
	
	/**  tbl gestion envios manager. */
	@Resource(name="TblGestionEnviosManagerImpl")
	private TblGestionEnviosManager tblGestionEnviosManager;
	
	/**  view misim manager. */
	@Resource(name="ViewMisimManagerImpl")
	private ViewMisimManager viewMisimManager;
	
	/**  tbl adjuntos manager. */
	@Resource(name="TblAdjuntosManagerImpl")
	private TblAdjuntosManager tblAdjuntosManager;
	
	/**  tbl mensajes manager. */
	@Resource(name="TblMensajesManagerImpl")
	private TblMensajesManager tblMensajesManager;
	
	/**  tbl destinatarios mensajes manager. */
	@Resource(name="TblDestinatariosMensajesManagerImpl")
	private TblDestinatariosMensajesManager tblDestinatariosMensajesManager;
	
	/**  tbl lotes envios manager. */
	@Resource(name="TblLotesEnviosManagerImpl")
	private TblLotesEnviosManager tblLotesEnviosManager;
	
	/**  tbl usuarios push manager. */
	@Resource(name="TblUsuariosPushManagerImpl")
	private TblUsuariosPushManager tblUsuariosPushManager;
	
	/**  tbl destinatarios manager. */
	@Resource(name="TblDestinatariosManagerImpl")
	private TblDestinatariosManager tblDestinatariosManager;
	
	/**  tbl historicos manager. */
	@Resource(name="TblHistoricosManagerImpl")
	private TblHistoricosManager tblHistoricosManager;
	
	/**  view lotes envios detallada manager. */
	@Resource(name="ViewLotesEnviosDetalladaManagerImpl")
	private ViewLotesEnviosDetalladaManager viewLotesEnviosDetalladaManager;
	
	/**  view historico manager. */
	@Resource(name="ViewHistoricoManagerImpl")
	private ViewHistoricoManager viewHistoricoManager;
	
	/**  query executor mensajes. */
	@Resource(name="QueryExecutorMensajesImpl")
	private QueryExecutorMensajes queryExecutorMensajes;
	
	/**  query executor adjuntos. */
	@Resource(name="QueryExecutorAdjuntosImpl")
	private QueryExecutorAdjuntos queryExecutorAdjuntos;
	
	/**  operacion mensajes service. */
	@Resource(name = "operacionMensajesService")
	OperacionMensajesServicePortType operacionMensajesService;
	
	/**  envio mensajes service. */
	@Resource(name = "envioMensajesService")
	EnvioMensajesServiceWSBindingPortType envioMensajesService;
	
	/**  peticion manager. */
	@Resource(name="PeticionManagerImpl")
	private PeticionManager peticionManager;

	/**  reloadable resource bundle message source. */
	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
//	@Resource(name = "AplicacionManagerImp")
//	AplicacionManager aplicacionManager;
	
	/**  query executor gestion envios impl. */
@Autowired
	private QueryExecutorGestionEnvios  queryExecutorGestionEnviosImpl;
	
	/**  query executor destinatarios mensajes impl. */
	@Autowired
	private QueryExecutorDestinatariosMensajes  queryExecutorDestinatariosMensajesImpl;
	
	/**  map permisos usuario aplicacion. */
	static HashMap<Integer, Integer> mapPermisosUsuarioAplicacion = null;
	
	/**  rol usuario. */
	static String rolUsuario = null;
	
	
	/**  mensajesms. */
	private static String MENSAJESMS = "SMS";
	
	/**  mensajeemail. */
	private static String MENSAJEEMAIL = "EMAIL";
	
	/**  mensajenotificacion. */
	private static String MENSAJENOTIFICACION = "NOTIFICACION PUSH";
	
	/**  mensajerecepcion. */
	private static String MENSAJERECEPCION = "RECEPCION SMS";
	
	/** Constante VISTADESTINATARIO. */
	private static final Integer VISTADESTINATARIO = 3;
	
	/** Constante VISTALOTES. */
	private static final Integer VISTALOTES = 2;
	
	/** Constante VISTAMENSAJES. */
	private static final Integer VISTAMENSAJES= 1;
	
	/** Constante OK. */
	private static final String OK = "OK";
	
	/**  adjunto. */
	private String adjunto;
	
	/**  adjunto 64. */
	private String adjunto64;
	
	/**  binary types. */
	private static HashMap<String,String> binaryTypes = new HashMap<>();
	
	/**  text types. */
	private static HashMap<String,String> textTypes = new HashMap<>();
	static {        
        binaryTypes.put("gif", "image/gif");
        binaryTypes.put("jpg", "image/jpeg");
        binaryTypes.put("png", "image/png");
        binaryTypes.put("jpeg", "image/jpeg");   
        textTypes.put("htm", "text/html");
        textTypes.put("html", "text/html");
        textTypes.put("xml", "application/xml");
        textTypes.put("xhtml", "application/xhtml+xml");  
        textTypes.put("js", "application/x-javascript");
        textTypes.put("css", "text/css");
        textTypes.put("txt", "text/plain");
    }    

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#getGestionDeEnvios(int, java.lang.Integer, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.GestionEnvioBean, javax.servlet.http.HttpServletRequest, boolean)
	 */
	///MIGRADO
	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<GestionEnvioBean> getGestionDeEnvios(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioBean criterio, HttpServletRequest request, boolean porLotes) throws BusinessException {
		
		// listaAuditoriaBean
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		rolUsuario = (String) request.getSession().getAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA);
		Hashtable<String, String> columns = new Hashtable<String, String>();
		String column = null;
		if (porLotes){
			columns.put("1", "aplicacion");
			columns.put("2", "servicio");
			columns.put("3", "nombre");
			columns.put("4", "loteenvioid");
			columns.put("5", "ultimoenvio");
			columns.put("6", "estadolote");
			if (null != columnSort && columnSort.equals("5")){
				if (null != order && order.equals("2"))
					column = "ultimoenvio desc, loteenvioid ";
				else if (order.equals("1"))
					column = "ultimoenvio asc, loteenvioid ";
			}else if (null != columnSort){
				column = columns.get(columnSort);
			}else
				column = "ultimoenvio desc, loteenvioid ";
				
		}else{
			columns.put("1", "aplicacion");
			columns.put("2", "servicio");
			columns.put("3", "nombre");
			columns.put("4", "loteenvioid");
			columns.put("5", "mensajeid");
			columns.put("6", "ultimoenvio");
			columns.put("7", "estado");
			if (null != columnSort && columnSort.equals("6")){
				if (null != order && order.equals("2"))
					column = "ultimoenvio desc, mensajeid ";
				else if (order.equals("1"))
					column = "ultimoenvio asc, mensajeid ";
			}else if (null != columnSort){
				column = columns.get(columnSort);
			}else
				column = "ultimoenvio desc, mensajeid ";
		}
		
		if (column == null) {
			column = "loteenvioid desc, ultimoenvio";
		}
		if (order == null) {
			order = "2";
		}
	
		es.minhap.plataformamensajeria.iop.beans.GestionEnvioBean eg = new es.minhap.plataformamensajeria.iop.beans.GestionEnvioBean();
		eg = createGestionEnvioBean(criterio,eg);
		
		Integer rowcount = 0;
		List<TblGestionEnvios> lista = new ArrayList<>();
		if (porLotes) {
			rowcount = getTotalLotesGestionEnvio(eg, request);
			lista = queryExecutorGestionEnviosImpl.getGestionEnvioLotesPaginado(inicio, pagesize, order, column, eg);
		} else {
			rowcount = getTotalGestionEnvio(eg, request);
			lista = queryExecutorGestionEnviosImpl.getGestionEnvioMensajesPaginado(inicio, pagesize, order, column, eg);
		}

		
		List<GestionEnvioBean> pageList = getListGestionEnvioBean(lista, porLotes);
		
		PaginatedList<GestionEnvioBean> result = new PaginatedList<>();
		result.setPageList(pageList);
		result.setTotalList(rowcount);
		
		return result;
		
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#getGestionDeEnviosDestinatarios(int, java.lang.Integer, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.GestionEnvioBean, javax.servlet.http.HttpServletRequest)
	 */
	////MIGRADO
	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<GestionEnvioBean> getGestionDeEnviosDestinatarios(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioBean criterio, HttpServletRequest request) throws BusinessException {
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>)request.getSession().
				getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		rolUsuario = (String)request.getSession().getAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA);
		Hashtable<String, String> columns = new Hashtable<String,String>();	
		columns.put("1","aplicacion");
		columns.put("2", "servicio");
		columns.put("3", "loteenvio");
		columns.put("4", "loteenvioid");
		columns.put("5", "mensajeid");
		columns.put("6", "ultimoenvio");
		columns.put("7", "estado");
		columns.put("8", "destinatario");
		
		if (columnSort==null){
			columnSort = "6"; //FECHA
		}
		if(order==null){
			order = "2";
		}
		String column = columns.get(columnSort) ;	
		
		es.minhap.plataformamensajeria.iop.beans.GestionEnvioBean eg = new es.minhap.plataformamensajeria.iop.beans.GestionEnvioBean();
		eg = createGestionEnvioBean(criterio,eg);
		
		Integer rowcount = queryExecutorGestionEnviosImpl.countGestionEnviosDestinatarios(eg);
		List<ViewGestionEnviosDestId> lista = new ArrayList<>();
		lista = queryExecutorGestionEnviosImpl.getGestionEnvioDestinatariosPaginado(inicio, pagesize, order, column, eg);	
			
		List<GestionEnvioBean> pageList = getListGestionEnvioBeanFromDestinatario(lista);
		
		PaginatedList<GestionEnvioBean> result = new PaginatedList<>();
		result.setPageList(pageList);
		result.setTotalList(rowcount);
		
		return result;
	}

	
	/**
	 * Obtener total gestion envio.
	 *
	 * @param eg the eg
	 * @param request the request
	 * @return total gestion envio
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	@SuppressWarnings("unchecked")
	public Integer getTotalGestionEnvio(es.minhap.plataformamensajeria.iop.beans.GestionEnvioBean eg,
			HttpServletRequest request) throws BusinessException {
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(
				PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);

		try {
			StringBuilder sbf = new StringBuilder();

			if (mapPermisosUsuarioAplicacion != null
					&& (!PlataformaMensajeriaUtil.isEmpty(rolUsuario) && !rolUsuario
							.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR))) {

				Set<Integer> idAplicaciones = mapPermisosUsuarioAplicacion.keySet();
				Iterator<Integer> itAplicaciones = idAplicaciones.iterator();

				boolean first = true;
				while (itAplicaciones.hasNext()) {
					Integer idAplicacion = itAplicaciones.next();
					if (!first) {
						sbf.append(",");
					}
					sbf.append(idAplicacion);
					first = false;
				}
			}
			eg.setListaIdAplicaciones(sbf.toString());

			return queryExecutorGestionEnviosImpl.countGestionEnviosMensajes(eg);

		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - getTotalLotesGestionEnvio:" + e);
			return 0;
		}
	}

	
	/**
	 * Obtener total gestion envio destinatarios.
	 *
	 * @param eg the eg
	 * @param request the request
	 * @return total gestion envio destinatarios
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	@SuppressWarnings("unchecked")
	public Integer getTotalGestionEnvioDestinatarios(es.minhap.plataformamensajeria.iop.beans.GestionEnvioBean eg, HttpServletRequest request)
			throws BusinessException {
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(
				PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);

		try {
			StringBuilder sbf = new StringBuilder();

			if (mapPermisosUsuarioAplicacion != null
					&& (!PlataformaMensajeriaUtil.isEmpty(rolUsuario) && !rolUsuario
							.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR))) {

				Set<Integer> idAplicaciones = mapPermisosUsuarioAplicacion.keySet();
				Iterator<Integer> itAplicaciones = idAplicaciones.iterator();

				boolean first = true;
				while (itAplicaciones.hasNext()) {
					Integer idAplicacion = itAplicaciones.next();
					if (!first) {
						sbf.append(",");
					}
					sbf.append(idAplicacion);
					first = false;
				}
			}
			eg.setListaIdAplicaciones(sbf.toString());

			return queryExecutorGestionEnviosImpl.countGestionEnviosDestinatarios(eg);

		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - getTotalGestionEnvioDestinatarios:" + e);
			return 0;
		}

	}
	
	/**
	 * Obtener total lotes gestion envio.
	 *
	 * @param eg the eg
	 * @param request the request
	 * @return total lotes gestion envio
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	@SuppressWarnings("unchecked")
	public Integer getTotalLotesGestionEnvio(es.minhap.plataformamensajeria.iop.beans.GestionEnvioBean eg, HttpServletRequest request)
			throws BusinessException {
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(
				PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);

		try {
			StringBuilder sbf = new StringBuilder();

			if (mapPermisosUsuarioAplicacion != null
					&& (!PlataformaMensajeriaUtil.isEmpty(rolUsuario) && !rolUsuario
							.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR))) {

				Set<Integer> idAplicaciones = mapPermisosUsuarioAplicacion.keySet();
				Iterator<Integer> itAplicaciones = idAplicaciones.iterator();

				boolean first = true;
				while (itAplicaciones.hasNext()) {
					Integer idAplicacion = itAplicaciones.next();
					if (!first) {
						sbf.append(",");
					}
					sbf.append(idAplicacion);
					first = false;
				}
			}
			eg.setListaIdAplicaciones(sbf.toString());

			return queryExecutorGestionEnviosImpl.countGestionEnviosLotes(eg);

		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - getTotalLotesGestionEnvio:" + e);
			return 0;
		}

	}
	
	

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#loadMensaje(java.lang.String)
	 */
	///MIGRADO
	@Override
	public DetalleEnvioBean loadMensaje(String idEmail) throws BusinessException {
		DetalleEnvioBean detalleEmail = new DetalleEnvioBean();

		try {
			TblGestionEnvios gestionEnvio = getEnvio(idEmail);
			List<TblAdjuntos> listaAdjuntosTO = queryExecutorAdjuntos.getAdjuntosByMensaje(Long.parseLong(idEmail));
						
			List<AdjuntoEmailBean> listaAdjuntos = getListAdjuntoEmailBean(listaAdjuntosTO, Long.parseLong(idEmail));
			
			detalleEmail.setNombreAplicacion(gestionEnvio.getAplicacion());
			detalleEmail.setNombreServicio(gestionEnvio.getServicio());
			detalleEmail.setNombreLoteEnvio(gestionEnvio.getNombre());
			detalleEmail.setIdExterno(gestionEnvio.getCodigoexterno());
			detalleEmail.setIdLote(gestionEnvio.getLoteenvioid());
			detalleEmail.setMensajeId(gestionEnvio.getMensajeid());
			TblMensajes mensaje = tblMensajesManager.getMensaje(gestionEnvio.getMensajeid());
			
			detalleEmail.setTipoContenido(mensaje.getTipocuerpo());
			detalleEmail.setCodificacion(mensaje.getTipocodificacion());
			detalleEmail.setPrioridad(mensaje.getPrioridad());
			detalleEmail.setAsunto(mensaje.getCabecera());
			if (null == mensaje.getCuerpofile()){
				detalleEmail.setCuerpo(mensaje.getCuerpo());
			}else{
				detalleEmail.setCuerpo(UtilCreateFile.getCuerpoMensajeFromFile(mensaje.getCuerpofile()));
			}
			detalleEmail.setTelefono(mensaje.getTelefono());
			detalleEmail.setTipoMensaje(mensaje.getTipomensaje());
			if (listaAdjuntos != null) {
				detalleEmail.setListadoAdjuntos(listaAdjuntos);
			}
			
			detalleEmail.setDocUsuario(mensaje.getDocusuario());
			detalleEmail.setCodSIA(mensaje.getCodsia());
			detalleEmail.setCodOrganismo(mensaje.getCodorganismo());
			detalleEmail.setCodOrganismoPagador(mensaje.getCodorganismopagador());
			detalleEmail.setIcono(mensaje.getIcono());
			detalleEmail.setSonido(mensaje.getSonido());
			detalleEmail.setNombreUsuario(mensaje.getNombreusuario());
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - loadMensaje:" + e);
		}
		return detalleEmail;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#loadMisim(java.lang.String)
	 */
	@Override
	public ViewMisimBean loadMisim(String idLote) throws BusinessException {
		ViewMisimBean detalleMisim = new ViewMisimBean();

		ViewMisimQuery query = new ViewMisimQuery();
		query.setIdLote(Long.parseLong(idLote));
		
		try {
			ViewMisim misim = viewMisimManager.getViewMisim(query);

			detalleMisim.setIdLote(misim.getIdLote());
			detalleMisim.setFechaCreacion(misim.getFechaCreacion());
			detalleMisim.setIdPeticion(misim.getIdPeticion());
			detalleMisim.setProveedorProducto(misim.getProveedorProducto());
			
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - loadMisim:" + e);
			throw new BusinessException(e);
		}
		return detalleMisim;
	}	

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#loadLote(java.lang.String)
	 */
	////MIGRADO
	@Override
	public DetalleLoteBean loadLote(String idLote) throws BusinessException {
		DetalleLoteBean detalleLote = new DetalleLoteBean();

		ViewLotesEnviosDetalladaQuery query = new ViewLotesEnviosDetalladaQuery();
		query.setLoteenvioid(Long.parseLong(idLote));
		
		try {
			ViewLotesEnviosDetallada lote = viewLotesEnviosDetalladaManager.getViewLoteDetallado(query);

			detalleLote.setIdLoteEnvio(lote.getLoteenvioid().intValue());
			detalleLote.setNombreAplicacion(lote.getAplicacion());
			detalleLote.setNombreLoteEnvio(lote.getNombre());
			detalleLote.setNombreServicio(lote.getServicio());
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - loadLote:" + e);
			throw new BusinessException(e);
		}
		return detalleLote;
	}

	/**
	 * Obtener list mensajes lote bean.
	 *
	 * @param lista the lista
	 * @param idLote the id lote
	 * @return list mensajes lote bean
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	protected ArrayList<MensajeBean> getListMensajesLoteBean(List<TblMensajes> lista, Long idLote) throws BusinessException {
		ArrayList<MensajeBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();
			for (TblMensajes m : lista) {
				MensajeBean mb = new MensajeBean();
				mb.setCabecera(m.getCabecera());
				mb.setCodigoExterno(m.getCodigoexterno());
				mb.setCreadoPor(m.getCreadopor());
				mb.setCuerpo(m.getCuerpo());
				mb.setEstadoActual(m.getEstadoactual());
				mb.setFechaCreacion(m.getFechacreacion());
				mb.setFechaModificacion(m.getFechamodificacion());
				mb.setIdEnviosSms(m.getIdenviossms());
				mb.setLoteEnvioId(idLote);
				mb.setMensajeId(m.getMensajeid());
				mb.setModificadoPor(m.getModificadopor());
				mb.setNodo(m.getNodo());
				mb.setNumeroEnvios(m.getNumeroenvios());
				mb.setPrioridad(m.getPrioridad());
				mb.setTelefono(m.getTelefono());
				mb.setTipoCodificacion(m.getTipocodificacion());
				mb.setTipoCuerpo(m.getTipocuerpo());
				mb.setTipoMensaje(m.getTipomensaje());
				mb.setUim(m.getUim());
				mb.setUltimoEnvio(m.getUltimoenvio());
				mb.setUltimoIdHistorico(m.getUltimoidhistorico());
				result.add(mb);
			}
		
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#reenviarEnvio(java.lang.Integer, es.minhap.common.spring.ApplicationContextProvider)
	 */
	@Override
	@Transactional
	public Boolean reenviarEnvio(Integer mensajeId,  ApplicationContextProvider applicationContext) {

		Boolean resultado = null;
		try {
			TblAplicaciones aplicacion = queryExecutorMensajes.getAplicacionFromMensaje(mensajeId.longValue());
			String usuario = aplicacion.getUsuario();
			byte[] valueDecoded= Base64.decodeBase64(aplicacion.getPassword());
			String pass = new String(valueDecoded);
			OperacionMensajes operacion = OperacionMensajes.getInstance();
			PeticionMensaje pm = new PeticionMensaje();
			Mensaje m = new Mensaje();
			m.setIdMensaje(mensajeId);
			pm.setMensaje(m);
			operacion.setContext(applicationContext);
			operacion.setPeticionMensaje(pm);
			operacion.getPeticionMensaje().setUsuario(usuario);
			operacion.getPeticionMensaje().setPassword(pass);
			es.redsara.misim.misim_bus_webapp.respuestaOperacion.RespuestaOperacion respuesta = operacion.sendReenviarMensajeRequest(operacionMensajesService);

			if(respuesta.getStatus()!=null && respuesta.getStatus().getStatusText()!=null 
					&& respuesta.getStatus().getStatusText().contains(OK)){
				resultado = true;
			}else{
				resultado = false;
			}

		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - reenviarEnvio:" + e);
		}
		
		return resultado;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#anulaEnvio(java.lang.Integer, es.minhap.common.spring.ApplicationContextProvider)
	 */
	@Override
	@Transactional
	public boolean anulaEnvio(Integer mensajeId,  ApplicationContextProvider applicationContext) {
		try {
			TblAplicaciones aplicacion = queryExecutorMensajes.getAplicacionFromMensaje(mensajeId.longValue());
			String usuario = aplicacion.getUsuario();
			byte[] valueDecoded= Base64.decodeBase64(aplicacion.getPassword());
			String pass = new String(valueDecoded);
			OperacionMensajes operacion = OperacionMensajes.getInstance();
			PeticionMensaje pm = new PeticionMensaje();
			Mensaje m = new Mensaje();
			m.setIdMensaje(mensajeId);
			pm.setMensaje(m);
			operacion.setContext(applicationContext);
			operacion.setPeticionMensaje(pm);
			operacion.getPeticionMensaje().setUsuario(usuario);
			operacion.getPeticionMensaje().setPassword(pass);
			es.redsara.misim.misim_bus_webapp.respuestaOperacion.RespuestaOperacion respuesta =  operacion.sendAnularMensajeRequest(operacionMensajesService);
			
			boolean a = (respuesta.getStatus().getStatusText().contains(OK))? true: false;
			return a;
			
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - reenviarEnvio:" + e);
		}
		return false;
	}

/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#anulaEnvioLote(java.lang.Integer, es.minhap.common.spring.ApplicationContextProvider)
 */
////MIGRADO
	@Override
	public boolean anulaEnvioLote(Integer idLote,  ApplicationContextProvider applicationContext) {
		try {
			TblAplicaciones aplicacion = queryExecutorMensajes.getAplicacionFromLote(idLote);
			String usuario = aplicacion.getUsuario();
			byte[] valueDecoded= Base64.decodeBase64(aplicacion.getPassword());
			String pass = new String(valueDecoded);
			OperacionMensajes operacion = OperacionMensajes.getInstance();
			PeticionLote pm = new PeticionLote();
			pm.setLote(idLote.toString());
			pm.setPassword(pass);
			pm.setUsuario(usuario);
			operacion.setContext(applicationContext);
			operacion.setPeticionLote(pm);
			operacion.getPeticionLote().setUsuario(usuario);
			operacion.getPeticionLote().setPassword(pass);
			es.redsara.misim.misim_bus_webapp.respuestaOperacion.RespuestaOperacion respuesta =  operacion.sendAnularLoteRequest(operacionMensajesService);
			
			boolean a = (respuesta.getStatus().getStatusText().contains(OK))? true: false;
			return a;
			
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - reenviarEnvio:" + e);
		}
		return false;
	}

/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#reenviarEnvioLote(java.lang.Integer, es.minhap.common.spring.ApplicationContextProvider)
 */
////MIGRADO
	@Override
	public boolean reenviarEnvioLote(Integer idLote,  ApplicationContextProvider applicationContext) {
		try {
			TblAplicaciones aplicacion = queryExecutorMensajes.getAplicacionFromLote(idLote);
			String usuario = aplicacion.getUsuario();
			byte[] valueDecoded= Base64.decodeBase64(aplicacion.getPassword());
			String pass = new String(valueDecoded);
			OperacionMensajes operacion = OperacionMensajes.getInstance();
			PeticionLote pm = new PeticionLote();
			pm.setLote(idLote.toString());
			pm.setPassword(pass);
			pm.setUsuario(usuario);
			operacion.setContext(applicationContext);
			operacion.setPeticionLote(pm);
			operacion.getPeticionLote().setUsuario(usuario);
			operacion.getPeticionLote().setPassword(pass);
			es.redsara.misim.misim_bus_webapp.respuestaOperacion.RespuestaOperacion respuesta =  operacion.sendReenviarLoteRequest(operacionMensajesService);
			
			boolean a = (respuesta.getStatus().getStatusText().contains(OK))? true: false;
			return a;
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - reenviarEnvio:" + e);
		}
		return false;
	}
	
		
	
	/**
	 * Obtener list adjunto email bean.
	 *
	 * @param lista the lista
	 * @param mensajeId the mensaje id
	 * @return list adjunto email bean
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	protected List<AdjuntoEmailBean> getListAdjuntoEmailBean(List<TblAdjuntos> lista, Long mensajeId) throws BusinessException {
		List<AdjuntoEmailBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();
			for (TblAdjuntos aTO : lista) {
				result.add(getAdjuntoEmailBean(aTO, mensajeId));
			}
		}

		return result;
	}
	
	/**
	 * Obtener adjunto email bean.
	 *
	 * @param adjuntoTO the adjunto TO
	 * @param mensajeId the mensaje id
	 * @return adjunto email bean
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	protected AdjuntoEmailBean getAdjuntoEmailBean(TblAdjuntos adjuntoTO, Long mensajeId) throws BusinessException {
		AdjuntoEmailBean  ad = null;
		if (adjuntoTO != null) {
			ad = new  AdjuntoEmailBean();
			ad.setAdjuntoId(adjuntoTO.getAdjuntoid());
			ad.setContenido(adjuntoTO.getContenido());
			ad.setCreadoPor(adjuntoTO.getCreadopor());
			ad.setEmailId(mensajeId);
			ad.setFechaCreacion(adjuntoTO.getFechacreacion());
			ad.setFechaModificacion(adjuntoTO.getFechamodificacion());
			ad.setModificadoPor(adjuntoTO.getModificadopor());
			ad.setNombre(adjuntoTO.getNombre());
			
		}

		return ad;
	}

	/**
	 * <p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	protected List<HistoricoBean> getListHistoricoMultidestBean(List<ViewHistoricoMultidest> lista) throws BusinessException {
		List<HistoricoBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();

			for (ViewHistoricoMultidest h : lista) {
				HistoricoBean historico = new HistoricoBean();
				historico.setDescripcion(h.getDescripcion());
				historico.setDestinatariosMensajes(h.getDestinatariosmensajes());
				historico.setEstado(h.getEstado());
				historico.setEstadoId(h.getEstadoid());
				historico.setFecha(h.getFecha());
				historico.setHistoricoId(h.getHistoricoid());
				historico.setMensajeId(h.getMensajeid());
				historico.setNombreServidor(h.getServidor());
				historico.setPlanificacionId(h.getPlanificacionid());
				historico.setServidorId(h.getServidorid());
								
				result.add(historico);
			}
		}

		return result;
	}

/**
 * Obtener list historico bean.
 *
 * @param lista the lista
 * @return list historico bean
 * @throws BusinessException the business exception
 */
////MIGRADO
	protected List<HistoricoBean> getListHistoricoBean(List<ViewHistorico> lista) throws BusinessException {
		List<HistoricoBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();

			for (ViewHistorico h : lista) {
				HistoricoBean historico = new HistoricoBean();
				historico.setDescripcion(h.getDescripcion());
				historico.setEstado(h.getEstado());
				historico.setEstadoId(h.getEstadoid());
				historico.setFecha(h.getFecha());
				historico.setHistoricoId(h.getHistoricoid());
				historico.setMensajeId(h.getMensajeid());
				historico.setNombreServidor(h.getServidor());
				historico.setPlanificacionId(h.getPlanificacionid());
				historico.setServidorId(h.getServidorid());
								
				result.add(historico);
			}
		}

		return result;
	}
	
	/**
	 * Obtener list view misim bean.
	 *
	 * @param lista the lista
	 * @return list view misim bean
	 * @throws BusinessException the business exception
	 */
	protected List<ViewMisimBean> getListViewMisimBean(List<ViewMisim> lista) throws BusinessException {
		List<ViewMisimBean> result = null;
		
		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();

			for (ViewMisim v : lista) {
				ViewMisimBean viewMisimBean = new ViewMisimBean();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				viewMisimBean.setFechaCreacionStr(sdf.format(v.getFechaCreacion()));
				viewMisimBean.setIdLote(v.getIdLote());
				viewMisimBean.setIdPeticion(v.getIdPeticion());
				viewMisimBean.setProveedorProducto(v.getProveedorProducto());
								
				result.add(viewMisimBean);
			}
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#getEnvio(java.lang.String)
	 */
	///MIGRADO
	@Override
	public TblGestionEnvios getEnvio(String idEnvio) throws BusinessException {
		try {
			TblGestionEnviosQuery query = new TblGestionEnviosQuery();
			query.setMensajeid(Long.parseLong(idEnvio));
			return tblGestionEnviosManager.getGestionEnvios(query);
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - getEnvio:" + e);
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#getViewMisim(java.lang.String)
	 */
	@Override
	public ViewMisim getViewMisim(String idLote) throws BusinessException {
		try {
			ViewMisimQuery query = new ViewMisimQuery();
			query.setIdLote(Long.parseLong(idLote));
			return viewMisimManager.getViewMisim(query);
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - getViewMisim:" + e);
			throw new BusinessException(e, "errors.gestionEnvios.getViewMisim");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#getEnviosLote(java.lang.String)
	 */
	///MIGRADO
	@Override
	public List<TblGestionEnvios> getEnviosLote(String idLote) throws BusinessException {
		try {
			return tblGestionEnviosManager.getEnviosLote(idLote);
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - getEnviosLote:" + e);
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#loadAdjunto(java.lang.Long, java.lang.Long)
	 */
	////MIGRADO
	@Override
	public AdjuntoEmailBean loadAdjunto(Long idAdjunto, Long emailId) {
		AdjuntoEmailBean adjunto = new AdjuntoEmailBean();
		
		try {
			TblAdjuntos adjuntoTO = tblAdjuntosManager.getById(idAdjunto);
			if (adjuntoTO != null ) {
				adjunto.setNombre(adjuntoTO.getNombre());
				adjunto.setEmailId(emailId);
				adjunto.setAdjuntoId(adjuntoTO.getAdjuntoid());
				adjunto.setContenidoFile(adjuntoTO.getContenidofile());
				adjunto.setContenido(UtilCreateFile.getAdjuntoMensaje(adjunto.getContenidoFile()));
			}
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - loadAdjunto:" + e);
		} 
		return adjunto;
	}


	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#getMensajesLotes(int, int, java.lang.Long)
	 */
	/////MIGRADO
	@Override
	public PaginatedList<MensajeBean> getMensajesLotes(int start, int size, Long loteId) throws BusinessException {
		try {
			List<TblMensajes> lista = tblMensajesManager.getMensajesByLote(loteId, size, start);
			Integer rowcount =queryExecutorMensajes.countMensajesByLote(loteId);
			
			ArrayList<MensajeBean> listaMensajes = getListMensajesLoteBean(lista, loteId);

			PaginatedList<MensajeBean> result = new PaginatedList<>();
			result.setPageList(listaMensajes);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - getMensajesLotes:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#getMensaje(java.lang.String)
	 */
	////MIGRADO
	@Override
	public GestionEnvioBean getMensaje(String idMensaje) throws BusinessException {
		GestionEnvioBean geBean = new GestionEnvioBean();

		try {
			// Obtenemos los datos generales del envio
			TblGestionEnvios ge = tblGestionEnviosManager.getById(Long.parseLong(idMensaje));
			
			geBean.setAplicacion(ge.getAplicacion());
			geBean.setServicio(ge.getServicio());
			geBean.setLoteEnvio(ge.getNombre());
			geBean.setIdExterno(ge.getCodigoexterno());
			geBean.setIdLote(ge.getLoteenvioid());
			geBean.setMensajeId(ge.getMensajeid());
			geBean.setDocUsuario(ge.getDocusuario());
			geBean.setCodSIA(ge.getCodsia());
			geBean.setCodOrganismo(ge.getCodorganismo());
			geBean.setCodOrganismoPagador(ge.getCodorganismopagador());

		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - getMensaje:" + e);
			throw new BusinessException(e);
		}
		return geBean;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#isMultidestinatario(java.lang.Long)
	 */
	///MIGRADO
	@Override
	public boolean isMultidestinatario(Long idMensaje) throws BusinessException {
		return tblLotesEnviosManager.isMultidestinatario(idMensaje);
	}
	
	

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#getDestinatariosMensajes(int, int, java.lang.Long)
	 */
	////MIGRADO
	@Override
	public PaginatedList<DestinatariosMensajesBean> getDestinatariosMensajes(int start, int size, Long mensajeId) throws BusinessException {
		PaginatedList<DestinatariosMensajesBean> result = new PaginatedList<DestinatariosMensajesBean>();
		ArrayList<DestinatariosMensajesBean> listaDestinatariosMensajes = new ArrayList<>();
		try {
			// traemos el mensaje
			TblMensajes m = tblMensajesManager.getMensaje(mensajeId);
			
			if (m.getTipomensaje().equals(MENSAJESMS) || m.getTipomensaje().equals(MENSAJERECEPCION)) {
				DestinatariosMensajesBean dmBean = loadDestinatarioMensaje(m);
				dmBean.setDestinatario(m.getTelefono());
				listaDestinatariosMensajes.add(dmBean);
			} else if (m.getTipomensaje().equals(MENSAJENOTIFICACION)) {
				DestinatariosMensajesBean dmBean = loadDestinatarioMensaje(m);
				dmBean.setDestinatario(m.getNombreusuario());
				listaDestinatariosMensajes.add(dmBean);
			} else {
				List<String> listaDestinatarios = tblDestinatariosManager.getDestinatarios(m.getMensajeid());
				for (String email : listaDestinatarios) {
					DestinatariosMensajesBean dmBean = loadDestinatarioMensaje(m);
					dmBean.setDestinatario(email);
					listaDestinatariosMensajes.add(dmBean);
				}
			}

			// solo hay un destinatario mpor mensaje
			Integer rowcount = 1;

			result.setPageList(listaDestinatariosMensajes);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - getDestinatariosMensajes:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}

	/**
	 * Load destinatario mensaje.
	 *
	 * @param ms the ms
	 * @return the destinatarios mensajes bean
	 */
	///MIGRADO
	private DestinatariosMensajesBean loadDestinatarioMensaje(TblMensajes ms) {
		DestinatariosMensajesBean dmBean = new DestinatariosMensajesBean();
		dmBean.setCodigoExterno(ms.getCodigoexterno());
		dmBean.setCreadoPor(ms.getCreadopor());
		dmBean.setEstado(ms.getEstadoactual());
		dmBean.setFechaCreacion(ms.getFechacreacion());
		dmBean.setFechaModificacion(ms.getFechamodificacion());
		dmBean.setMensajeId(ms.getMensajeid());
		dmBean.setModificadoPor(ms.getModificadopor());
		dmBean.setNodo(ms.getNodo());
		dmBean.setUim(ms.getUim());
		dmBean.setTipoMensaje(ms.getTipomensaje());
		dmBean.setUltimoEnvio(ms.getUltimoenvio());
		return dmBean;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#getDestinatariosMensajesMultidestinatario(int, int, java.lang.Long)
	 */
	////MIGRADO
	@Override
	public PaginatedList<DestinatariosMensajesBean> getDestinatariosMensajesMultidestinatario(int start, int size, Long mensajeId) throws BusinessException {
		PaginatedList<DestinatariosMensajesBean> result = new PaginatedList<>();
		List<DestinatariosMensajesBean> listaDestinatariosMensajes = new ArrayList<>();
		try {
			// traemos el mensaje
			TblMensajes m = tblMensajesManager.getMensaje(mensajeId);
			
			//traemos los destinatarios
			TblDestinatariosMensajesQuery query = new TblDestinatariosMensajesQuery();
			query.setMensajeid(mensajeId);
			query.setMaxResult(size);
			query.setFirstResult(start);
			List<TblDestinatariosMensajes> listaDestinatariosMensajesTO = tblDestinatariosMensajesManager.getDestinatarioMensajesByQuery(query);
			
			listaDestinatariosMensajes = getListDestinatariosMensajesBean(listaDestinatariosMensajesTO);

			if (m.getTipomensaje().equals(MENSAJENOTIFICACION)) {
				for (DestinatariosMensajesBean dmBean : listaDestinatariosMensajes) {
					TblUsuariosPush usuario =  tblUsuariosPushManager.getUsuarioPush(Long.parseLong(dmBean.getDestinatario()));
					String us = (null != usuario)? usuario.getNombreusuario() : "";
					dmBean.setDestinatario(us);
				}
			} else if (m.getTipomensaje().equals(MENSAJEEMAIL)){
				for (DestinatariosMensajesBean dmBean : listaDestinatariosMensajes) {
					TblDestinatarios destinatario = tblDestinatariosManager.getDestinatario(Long.parseLong(dmBean.getDestinatario()));
					String dest = (null != destinatario)? destinatario.getEmail() : "";
					dmBean.setDestinatario(dest);
				}
			}

			Integer rowcount = queryExecutorDestinatariosMensajesImpl.countDestinatariosByMensaje(mensajeId);

			result.setPageList(listaDestinatariosMensajes);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - getDestinatariosMensajesMultidestinatario:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}

	/**
	 * Obtener list destinatarios mensajes bean.
	 *
	 * @param lista the lista
	 * @return list destinatarios mensajes bean
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	protected List<DestinatariosMensajesBean> getListDestinatariosMensajesBean(List<TblDestinatariosMensajes> lista) throws BusinessException {
		List<DestinatariosMensajesBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();
			for (TblDestinatariosMensajes dm : lista) {
				DestinatariosMensajesBean dmb = new DestinatariosMensajesBean();
				dmb.setCodigoExterno(dm.getCodigoexterno());
				dmb.setCreadoPor(dm.getCreadopor());
				dmb.setDestinatario(dm.getDestinatario());
				dmb.setDestinatariosMensajes(dm.getDestinatariosmensajes());
				dmb.setEstado(dm.getEstado());
				dmb.setFechaCreacion(dm.getFechacreacion());
				dmb.setFechaModificacion(dm.getFechamodificacion());
				dmb.setMensajeId(dm.getMensajeid());
				dmb.setModificadoPor(dm.getModificadopor());
				dmb.setNodo(dm.getNodo());
				dmb.setUim(dm.getUim());
				dmb.setUltimoEnvio(dm.getUltimoenvio());
				result.add(dmb);
			}
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#getDestinatariosMensajes(java.lang.String)
	 */
	////MIGRADO
	@Override
	public DestinatariosMensajesBean getDestinatariosMensajes(String idDestinatariosMensajes) throws BusinessException {
		DestinatariosMensajesBean res = new DestinatariosMensajesBean();
			
		try {
			TblDestinatariosMensajes dm = tblDestinatariosMensajesManager.getDestinatarioMensaje(Long.parseLong(idDestinatariosMensajes));
			
			res.setCodigoExterno(dm.getCodigoexterno());
			res.setCreadoPor(dm.getCreadopor());
			res.setDestinatario(dm.getDestinatario());
			res.setDestinatariosMensajes(dm.getDestinatariosmensajes());
			res.setEstado(dm.getEstado());
			res.setFechaCreacion(dm.getFechacreacion());
			res.setFechaModificacion(dm.getFechamodificacion());
			res.setMensajeId(dm.getMensajeid());
			res.setModificadoPor(dm.getModificadopor());
			res.setNodo(dm.getNodo());
			res.setUim(dm.getUim());
			res.setUltimoEnvio(dm.getUltimoenvio());
			
		}catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - getDestinatariosMensajes:" + e);
			throw new BusinessException(e);
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#getHistoricosMensaje(java.lang.String, java.lang.String)
	 */
	// //MIGRADO
	@Override
	public List<HistoricoBean> getHistoricosMensaje(String idMensaje, String idDestinatariosMensajes)
			throws BusinessException {
		List<HistoricoBean> result = new ArrayList<>();

		try {
			if (null != idDestinatariosMensajes) {
				ViewHistoricoMultidestQuery query = new ViewHistoricoMultidestQuery();
				query.setMensajeid(Long.parseLong(idMensaje));
				query.setDestinatariosmensajes(Long.parseLong(idDestinatariosMensajes));
				query.addOrder("fecha", OrderType.DESC);
				List<ViewHistoricoMultidest> listaHistoricos = viewHistoricoManager.getHistoricoMultidest(query);
				result = getListHistoricoMultidestBean(listaHistoricos);
			} else {
				ViewHistoricoQuery query = new ViewHistoricoQuery();
				query.setMensajeid(Long.parseLong(idMensaje));
				query.addOrder("fecha", OrderType.DESC);
				List<ViewHistorico> listaHistoricos = viewHistoricoManager.getHistorico(query);
				result = getListHistoricoBean(listaHistoricos);
			}
			return result;
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - getHistoricosMensaje:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#getIntercambiosMisim(int, int, java.lang.Long)
	 */
	@Override
	public PaginatedList<ViewMisimBean> getIntercambiosMisim(int start, int size, Long idLote, Long idMensaje) throws BusinessException {
		PaginatedList<ViewMisimBean> result = new PaginatedList<>();

		try {
			//traemos los intercambios
			ViewMisimQuery query = new ViewMisimQuery();
			query.setIdLote(idLote);
			query.setIdMensaje(idMensaje); //El criteria esta modificado para que incluya los idMensajes que son nulos
			List<ViewMisim> listaViewMisimTO = viewMisimManager.getIntercambiosMisimByQuery(query, start, size);
			
			if(!listaViewMisimTO.isEmpty()){
				List<ViewMisimBean> listaIntercambiosMisim = getListViewMisimBean(listaViewMisimTO);
				Integer rowcount = listaIntercambiosMisim.size();
				result.setPageList(listaIntercambiosMisim);
				result.setTotalList(rowcount);
			}

			return result;
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - getIntercambiosMisim: " + e);
			throw new BusinessException(e, "errors.gestionEnvios.getIntercambiosMisim");

		}
	}
	
	/**
	 * Creates the gestion envio bean.
	 *
	 * @param gestionEnvio the gestion envio
	 * @param ge the ge
	 * @return the es.minhap.plataformamensajeria.iop.beans. gestion envio bean
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	private es.minhap.plataformamensajeria.iop.beans.GestionEnvioBean createGestionEnvioBean(GestionEnvioBean gestionEnvio,
			es.minhap.plataformamensajeria.iop.beans.GestionEnvioBean ge) throws BusinessException{
		try {
			ge.setAnio(gestionEnvio.getAnio());
			ge.setAplicacion(gestionEnvio.getAplicacion());
			ge.setAplicacionId(gestionEnvio.getAplicacionId());
			ge.setCanal(gestionEnvio.getCanal());
			ge.setCanalId(gestionEnvio.getCanalId());
			ge.setCodigoExterno(gestionEnvio.getCodigoExterno());
			ge.setCodOrganismo(gestionEnvio.getCodOrganismo());
			ge.setCodOrganismoPagador(gestionEnvio.getCodOrganismoPagador());
			ge.setCodSIA(gestionEnvio.getCodSIA());
			ge.setDestinatario(gestionEnvio.getDestinatario());
			ge.setDestinatariosMensajes(gestionEnvio.getDestinatariosMensajes());
			ge.setDocUsuario(gestionEnvio.getDocUsuario());
			ge.setEditableCheckbox(gestionEnvio.getEditableCheckbox());
			ge.setEmail(gestionEnvio.getEmail());
			ge.setEnvioId(gestionEnvio.getEnvioId());
			ge.setEstado(gestionEnvio.getEstado());
			ge.setEstadoId(gestionEnvio.getEstadoId());
			ge.setFechaDesde(gestionEnvio.getFechaDesde());
			ge.setFechaHasta(gestionEnvio.getFechaHasta());
			ge.setIcono(gestionEnvio.getIcono());
			ge.setId(gestionEnvio.getId());
			ge.setIdExterno(gestionEnvio.getIdExterno());
			ge.setIdLote(gestionEnvio.getIdLote());
			ge.setLoteEnvio(gestionEnvio.getLoteEnvio());
			ge.setMensajeId(gestionEnvio.getMensajeId());
			ge.setMes(gestionEnvio.getMes());
			ge.setMultidestinatario(gestionEnvio.getMultidestinatario());
			ge.setNombre(gestionEnvio.getNombre());
			ge.setNombreUsuario(gestionEnvio.getNombreUsuario());
			ge.setServicio(gestionEnvio.getServicio());
			ge.setServicioId(gestionEnvio.getServicioId());
			ge.setServidor(gestionEnvio.getServidor());
			ge.setServidorId(gestionEnvio.getServidorId());
			ge.setSonido(gestionEnvio.getSonido());
			ge.setTelefonoMovil(gestionEnvio.getTelefonoMovil());
			ge.setUltimoEnvio(gestionEnvio.getUltimoEnvio());
			ge.setUltimoEnvioStr(gestionEnvio.getUltimoEnvioStr());
			ge.setVistaEnviosId(gestionEnvio.getVistaEnviosId());
			ge.setArrayOrganismos(gestionEnvio.getArrayOrganismos());
			
			return ge;
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - createGestionEnvioBean:" + e);
			throw new BusinessException(e);
		}
	}

	/**
	 * Obtener list gestion envio bean.
	 *
	 * @param lista the lista
	 * @param porLote the por lote
	 * @return list gestion envio bean
	 */
	////MIGRADO
	private List<GestionEnvioBean> getListGestionEnvioBean(List<TblGestionEnvios> lista, boolean porLote) {
		List<GestionEnvioBean> result = new ArrayList<>();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		if (lista!=null && !lista.isEmpty())
		{
					
			for (TblGestionEnvios ge : lista) {
				GestionEnvioBean gestionEnvio =  new GestionEnvioBean();
			
				gestionEnvio.setAplicacion(ge.getAplicacion());
				gestionEnvio.setServicio(ge.getServicio());
				gestionEnvio.setLoteEnvio(ge.getNombre());
				gestionEnvio.setIdLote((null != ge.getLoteenvioid())? ge.getLoteenvioid() : null);
				gestionEnvio.setMensajeId((null != ge.getMensajeid())? ge.getMensajeid() : null);
				gestionEnvio.setUltimoEnvio(ge.getUltimoenvio());
				if(!porLote){
					gestionEnvio.setEstado(ge.getEstado());
					gestionEnvio.setEnvioId(ge.getCanalid()+"_"+ge.getMensajeid());
					gestionEnvio.setCanalId((null != ge.getCanalid())? ge.getCanalid() : null);
					gestionEnvio.setVistaEnviosId(VISTAMENSAJES);
				}else{
					gestionEnvio.setEstado(ge.getEstadolote());
					gestionEnvio.setVistaEnviosId(VISTALOTES);
				}
				gestionEnvio.setUltimoEnvioStr((null != ge.getUltimoenvio())? df.format(ge.getUltimoenvio()) : "");
			
				result.add(gestionEnvio);
			}
		}
			
		return result;
	}
	
/**
 * Obtener list gestion envio bean from destinatario.
 *
 * @param lista the lista
 * @return list gestion envio bean from destinatario
 */
////MIGRADO
	private List<GestionEnvioBean> getListGestionEnvioBeanFromDestinatario(List<ViewGestionEnviosDestId> lista) {
		List<GestionEnvioBean> result = new ArrayList<>();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		if (lista!=null && !lista.isEmpty())
		{
					
			for (ViewGestionEnviosDestId ge : lista) {
				GestionEnvioBean gestionEnvio = new GestionEnvioBean();
				gestionEnvio.setAplicacion(ge.getAplicacion());
				gestionEnvio.setServicio(ge.getServicio());
				gestionEnvio.setLoteEnvio(ge.getLoteenvio());
				gestionEnvio.setIdLote((null != ge.getLoteenvioid()) ? ge.getLoteenvioid() : null);
				gestionEnvio.setMensajeId((null != ge.getMensajeid()) ? ge.getMensajeid() : null);
				gestionEnvio.setUltimoEnvio(ge.getUltimoenvio());

				gestionEnvio.setEstado(ge.getEstado());
				gestionEnvio.setEnvioId(ge.getCanalid() + "_" + ge.getMensajeid());
				gestionEnvio.setCanalId((null != ge.getCanalid()) ? ge.getCanalid() : null);
				gestionEnvio.setDestinatario(ge.getDestinatario());
				gestionEnvio.setUltimoEnvioStr((null != ge.getUltimoenvio()) ? df.format(ge.getUltimoenvio()) : "");
				gestionEnvio.setDestinatariosMensajes((null != ge.getDestinatariosmensajes())? ge.getDestinatariosmensajes() : null);
				gestionEnvio.setVistaEnviosId(VISTADESTINATARIO);
				result.add(gestionEnvio);
			}
		}
			
		return result;
	}
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios#enviarPeticion(es.minhap.common.spring.ApplicationContextProvider, es.mpr.plataformamensajeria.beans.EnvioMensajesAplicacionBean)
 */
//	nat
	public Respuesta enviarPeticion(ApplicationContextProvider applicationContext,EnvioMensajesAplicacionBean envioMensajesAplicacionBean) {

		Respuesta respuesta = null;
		
		try {
			
			String usuario = null;
			String pass = null;
			
			AplicacionBean aplicacion = new AplicacionBean();
			aplicacion.setId(envioMensajesAplicacionBean.getAplicacionId());
			try {
				aplicacion = servicioAplicacion.loadAplicacion(aplicacion);
				usuario = aplicacion.getUsuario();
				byte[] valueDecoded= Base64.decodeBase64(aplicacion.getPassword());
				pass = new String(valueDecoded);
			} catch (BusinessException e) {
				logger.error("EnviosMensajesAcyion - aplicacionSelectEvent:" + e);
			}

			
			EnvioMensajes envio = EnvioMensajes.getInstance();			
			Peticion peticion = new Peticion();
			Mensajes m = new Mensajes();
			
//			sms
			MensajeSMS sms =  new MensajeSMS();
			DestinatariosSMS destinariosSms = new DestinatariosSMS();
			DestinatarioSMS destSms = new DestinatarioSMS();
			
//			email
			MensajeEmail email = new MensajeEmail();
			Adjuntos adjuntos = new Adjuntos();
			Adjunto adj = new Adjunto();
			DestinatariosMail destinatariosMail = new DestinatariosMail();
			DestinatarioMail destMail = new DestinatarioMail();
			Destinatarios destinatarios = new Destinatarios();
			
			Passbook passbook = new Passbook();
			CamposPrincipales camposPrinc = new CamposPrincipales();
			PkFields pkfieldsPrinc = new PkFields();
			CamposSecundarios camposSec = new CamposSecundarios();
			PkFields pkfieldsSec = new PkFields();
			CamposAuxiliares camposAux =  new CamposAuxiliares();
			PkFields pkfieldsAux = new PkFields();
			CamposDetalleTrasero camposTras = new CamposDetalleTrasero();
			PkFields pkfieldsTras = new PkFields();
			
//			notificaciones push
			MensajePush push = new MensajePush();
			DestinatariosPush destinatariosPush = new DestinatariosPush();
			DestinatarioPush destPush = new DestinatarioPush();
			
//			web push
			MensajeWebPush webPush = new MensajeWebPush();
			DestinatariosWebPush destinatariosWebPush = new DestinatariosWebPush();
			DestinatarioWebPush destWebPush = new DestinatarioWebPush();
			
		
			//Datos de la peticion comunes a los distintos tipos de mensajes
			peticion.setUsuario(usuario);
			peticion.setPassword(pass);
			
			peticion.setNombreLote(envioMensajesAplicacionBean.getNombreLote());			
			peticion.setServicio(envioMensajesAplicacionBean.getServicioId());
			
				
			
			if(envioMensajesAplicacionBean.getCanalId() != null){
				if(envioMensajesAplicacionBean.getCanalId().equals("1")){//Email
					peticion.setCodOrganismo(envioMensajesAplicacionBean.getOrganismo());
					destinatarios.setTo(envioMensajesAplicacionBean.getTo());
					if(!envioMensajesAplicacionBean.getCc().equals("")){
						destinatarios.setCC(envioMensajesAplicacionBean.getCc());
					}
					if(!envioMensajesAplicacionBean.getCco().equals("")){
						destinatarios.setBcc(envioMensajesAplicacionBean.getCco());
					}
					destMail.setDestinatarios(destinatarios);
					destMail.setIdExterno(envioMensajesAplicacionBean.getIdExterno());
					
					destinatariosMail.getDestinatarioMail().add(destMail);
					
					if(envioMensajesAplicacionBean.getAdjunto() != null){

						Path path = Paths.get(envioMensajesAplicacionBean.getAdjunto().getPath());
						if(Files.exists(path, LinkOption.NOFOLLOW_LINKS)){
							adjunto = path.toString();									
							adjunto64 = generateDataURI(path);
							
							adj.setContenido(adjunto64);
//							adj.setNombre(envioMensajesAplicacionBean.getAdjunto().getName());
							adj.setNombre("descarga.jpg");
						}
			    		
						adjuntos.getAdjunto().add(adj);
					}
					email.setAsunto(envioMensajesAplicacionBean.getAsunto());
					email.setCuerpo(envioMensajesAplicacionBean.getMensaje());
					email.setAdjuntos(adjuntos);
					email.setDestinatariosMail(destinatariosMail);

					if (envioMensajesAplicacionBean.getPassbook().equals("true")){
					    pkfieldsPrinc.setKey(envioMensajesAplicacionBean.getKeyPrinc());
					    pkfieldsPrinc.setLabel(envioMensajesAplicacionBean.getLabelPrinc());
					    pkfieldsPrinc.setValue(envioMensajesAplicacionBean.getValuePrinc());
					    
					    camposPrinc.setPkFields(pkfieldsPrinc);
					    
					    pkfieldsSec.setKey(envioMensajesAplicacionBean.getKeySec());
					    pkfieldsSec.setLabel(envioMensajesAplicacionBean.getLabelSec());
					    pkfieldsSec.setValue(envioMensajesAplicacionBean.getValueSec());
					    
					    camposSec.getPkFields().add(pkfieldsSec);
					    
					    pkfieldsAux.setKey(envioMensajesAplicacionBean.getKeyAux());
					    pkfieldsAux.setLabel(envioMensajesAplicacionBean.getLabelAux());
					    pkfieldsAux.setValue(envioMensajesAplicacionBean.getValueAux());
					    
					    camposAux.getPkFields().add(pkfieldsAux);
					    
					    pkfieldsTras.setKey(envioMensajesAplicacionBean.getKeyTras());
					    pkfieldsTras.setLabel(envioMensajesAplicacionBean.getLabelTras());
					    pkfieldsTras.setValue(envioMensajesAplicacionBean.getValueTras());
					    
					    camposTras.getPkFields().add(pkfieldsTras);
					    
					    passbook.setURL(envioMensajesAplicacionBean.getUrl());
					    passbook.setLogoText(envioMensajesAplicacionBean.getLogoPassbook());
					    passbook.setDescription(envioMensajesAplicacionBean.getDescripcionPassbook());
					    passbook.setCamposPrincipales(camposPrinc);
					    passbook.setCamposSecundarios(camposSec);
					    passbook.setCamposAuxiliares(camposAux);
					    passbook.setCamposDetalleTrasero(camposTras);
					    
					    email.setPassBook(passbook);
					}
				    
					m.getMensajeEmail().add(email);
					
					peticion.setMensajes(m);
					
				}else if(envioMensajesAplicacionBean.getCanalId().equals("2")){//SMS
								
					destSms.setDestinatario(envioMensajesAplicacionBean.getMovil());
	
					destinariosSms.getDestinatarioSMS().add(destSms);
					
					sms.setCuerpo(envioMensajesAplicacionBean.getMensaje());
					sms.setDestinatariosSMS(destinariosSms);
					
				    m.getMensajeSMS().add(sms);
				    
				    peticion.setCodOrganismo(envioMensajesAplicacionBean.getOrganismo());
					peticion.setCodOrganismoPagadorSMS(envioMensajesAplicacionBean.getOrganismo());			
					peticion.setMensajes(m);
								
				}else if(envioMensajesAplicacionBean.getCanalId().equals("4")){//Notificaciones Push
					
					destPush.setIdExterno(envioMensajesAplicacionBean.getIdExterno());
					destPush.setIdentificadorUsuario(envioMensajesAplicacionBean.getIdUsuario());
					
					destinatariosPush.getDestinatarioPush().add(destPush);
					
					push.setTitulo(envioMensajesAplicacionBean.getTitulo());
					push.setCuerpo(envioMensajesAplicacionBean.getMensaje());
//					push.setIcono(envioMensajesAplicacionBean.getIcono());
//					push.setSonido(envioMensajesAplicacionBean.getSonido());
					push.setDestinatariosPush(destinatariosPush);
					
					m.getMensajePush().add(push);
					
					peticion.setMensajes(m);
					
				}else if(envioMensajesAplicacionBean.getCanalId().equals("5")){//Web Push
					
					destWebPush.setIdExterno(envioMensajesAplicacionBean.getIdExterno());
					destWebPush.setIdentificadorUsuario(envioMensajesAplicacionBean.getIdUsuario());
					
					destinatariosWebPush.getDestinatarioWebPush().add(destWebPush);
					
					webPush.setTitulo(envioMensajesAplicacionBean.getTitulo());
					webPush.setCuerpo(envioMensajesAplicacionBean.getCuerpo());
//					push.setIcono(envioMensajesAplicacionBean.getIcono());
//					push.setSonido(envioMensajesAplicacionBean.getSonido());
					webPush.setDestinatariosWebPush(destinatariosWebPush);
					
					m.getMensajeWebPush().add(webPush);
					
					peticion.setMensajes(m);
				}
					
			}
			
			envio.setContext(applicationContext);
			envio.setPeticion(peticion);
			
//			Respuesta respuesta = envio.sendMessage(envioMensajesService);
			respuesta = envio.sendMessage(envioMensajesService);
			
//			if(!respuesta.getStatus().getStatusCode().equals("100")){
//				dato =  respuesta.getStatus().getStatusText();
//			}else{
//				if(respuesta.getLote().getIdLote() != null){
//					dato = respuesta.getStatus().getDetails()+" El Id Lote es: "+respuesta.getLote().getIdLote();
//				}
//			}

		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - enviarPeticion:" + e);
		}
//		return dato;
		return respuesta;
}
	
	/**
	 * Generate data URI.
	 *
	 * @param path the path
	 * @return the string
	 */
	private String generateDataURI(Path path) {
		StringBuffer buffer = null;
		try{
			byte[] bytes = Files.readAllBytes(path);
							
	        buffer = new StringBuffer();        

	        buffer.append(new String(Base64.encodeBase64(bytes)));
		}catch(IOException e){
			return null;
		}
       return buffer.toString();        

	}


	/**
	 * Obtener adjunto.
	 *
	 * @return adjunto
	 */
	public String getAdjunto() {
		return adjunto;
	}


	/**
	 * Modificar adjunto.
	 *
	 * @param adjunto new adjunto
	 */
	public void setAdjunto(String adjunto) {
		this.adjunto = adjunto;
	}
	
	
	
}