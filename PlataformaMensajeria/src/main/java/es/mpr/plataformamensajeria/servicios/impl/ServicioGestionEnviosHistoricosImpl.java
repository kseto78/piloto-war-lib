package es.mpr.plataformamensajeria.servicios.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.common.entity.OrderType;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorAdjuntosHist;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorDestinatariosMensajesHist;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorGestionEnviosHist;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajesHist;
import es.minhap.plataformamensajeria.iop.manager.TblAdjuntosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblGestionEnviosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblGestionEnviosManager;
import es.minhap.plataformamensajeria.iop.manager.TblLotesEnviosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;
import es.minhap.plataformamensajeria.iop.manager.ViewHistoricoHistManager;
import es.minhap.plataformamensajeria.iop.manager.ViewLotesEnviosDetHistManager;
import es.minhap.plataformamensajeria.iop.misim.manager.ViewMisimManager;
import es.minhap.sim.model.TblAdjuntosHist;
import es.minhap.sim.model.TblDestinatariosHist;
import es.minhap.sim.model.TblDestinatariosMensHist;
import es.minhap.sim.model.TblGestionEnviosHist;
import es.minhap.sim.model.TblMensajesHist;
import es.minhap.sim.model.TblUsuariosPush;
import es.minhap.sim.model.ViewGestionEnviosDestHistId;
import es.minhap.sim.model.ViewHistoricoHist;
import es.minhap.sim.model.ViewHistoricoHistMultidest;
import es.minhap.sim.model.ViewLotesEnviosDetHist;
import es.minhap.sim.query.TblGestionEnviosHistQuery;
import es.minhap.sim.query.ViewHistoricoHistMultidestQuery;
import es.minhap.sim.query.ViewHistoricoHistQuery;
import es.minhap.sim.query.ViewLotesEnviosDetHistQuery;
import es.mpr.plataformamensajeria.beans.AdjuntoEmailHistoricosBean;
import es.mpr.plataformamensajeria.beans.DestinatariosMensajesHistoricosBean;
import es.mpr.plataformamensajeria.beans.DetalleEnvioHistBean;
import es.mpr.plataformamensajeria.beans.DetalleLoteBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioHistoricoBean;
import es.mpr.plataformamensajeria.beans.HistoricoHistBean;
import es.mpr.plataformamensajeria.beans.MensajeHistoricosBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.plataformamensajeria.util.UtilCreateFile;

/**
 * <p>
 * Maneja la persistencia y busqueda de canales a traves de JPA.
 * 
 * @author
 * 
 */
@Service("servicioGestionEnviosHistoricosImpl")
public class ServicioGestionEnviosHistoricosImpl implements ServicioGestionEnviosHistoricos {
	
	/**  logger. */
	Logger logger = Logger.getLogger(ServicioGestionEnviosHistoricosImpl.class);
	
	/**  view misim manager. */
	@Resource(name="ViewMisimManagerImpl")
	private ViewMisimManager viewMisimManager;
	
	/**  tbl gestion envios hist manager. */
	@Resource(name = "TblGestionEnviosHistManagerImpl")
	private TblGestionEnviosHistManager tblGestionEnviosHistManager;
	
	/**  tbl gestion envios manager. */
	@Resource(name = "TblGestionEnviosManagerImpl")
	private TblGestionEnviosManager tblGestionEnviosManager;
	
	/**  tbl mensajes hist manager. */
	@Resource(name = "TblMensajesHistManagerImpl")
	private TblMensajesHistManager tblMensajesHistManager;
	
	/**  tbl lotes envios hist manager. */
	@Resource(name="TblLotesEnviosHistManagerImpl")
	private TblLotesEnviosHistManager tblLotesEnviosHistManager;
	
	/**  tbl destinatarios mens hist manager. */
	@Resource(name="TblDestinatariosMensHistManagerImpl")
	private TblDestinatariosMensajesHistManager tblDestinatariosMensHistManager;
	
	/**  tbl usuarios push manager. */
	@Resource(name="TblUsuariosPushManagerImpl")
	private TblUsuariosPushManager tblUsuariosPushManager;
	
	/**  tbl destinatarios hist manager. */
	@Resource(name="TblDestinatariosHistManagerImpl")
	private TblDestinatariosHistManager tblDestinatariosHistManager;
	
	/**  tbl adjuntos hist manager. */
	@Resource(name="TblAdjuntosHistManagerImpl")
	private TblAdjuntosHistManager tblAdjuntosHistManager;
	
	/**  view lotes envios det hist manager. */
	@Resource(name="ViewLotesEnviosDetHistManagerImpl")
	private ViewLotesEnviosDetHistManager viewLotesEnviosDetHistManager;
	
	/**  view historico hist manager. */
	@Resource(name="ViewHistoricoHistManagerImpl")
	private ViewHistoricoHistManager viewHistoricoHistManager;
	
	/**  query executor adjuntos hist. */
	@Resource(name="QueryExecutorAdjuntosHistImpl")
	private QueryExecutorAdjuntosHist queryExecutorAdjuntosHist;
	
	/**  query executor mensajes hist. */
	@Resource(name="QueryExecutorMensajesHistImpl")
	private QueryExecutorMensajesHist queryExecutorMensajesHist;
	
	/**  query executor gestion envios hist. */
	@Autowired
	private QueryExecutorGestionEnviosHist queryExecutorGestionEnviosHist;
	
	/**  query executor destinatarios mensajes hist impl. */
	@Autowired
	private QueryExecutorDestinatariosMensajesHist  queryExecutorDestinatariosMensajesHistImpl;

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
//	private static final Integer VISTALOTES = 2;
//	private static final Integer VISTAMENSAJES= 1;
//	private static final String OK = "OK";

	
	/**  map permisos usuario aplicacion. */
static HashMap<Integer, Integer> mapPermisosUsuarioAplicacion = null;
	
	/**  rol usuario. */
	static String rolUsuario = null;

	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#getGestionDeEnviosHistoricos(int, java.lang.Integer, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.GestionEnvioHistoricoBean, javax.servlet.http.HttpServletRequest, boolean)
	 */
	///MIGRADO
	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<GestionEnvioHistoricoBean> getGestionDeEnviosHistoricos(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioHistoricoBean criterio, HttpServletRequest request, boolean porLotes) throws BusinessException {
		// Columna para ordenar
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		rolUsuario = (String) request.getSession().getAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA);
		Hashtable<String, String> columns = new Hashtable<String, String>();
		String column = null;
		if (porLotes) {
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
		} else {
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

		es.minhap.plataformamensajeria.iop.beans.GestionEnvioHistoricoBean eg = new es.minhap.plataformamensajeria.iop.beans.GestionEnvioHistoricoBean();
		eg = createGestionEnvioHistoricoBean(criterio,eg);
		
		Integer rowcount = 0;
		List<TblGestionEnviosHist> lista = new ArrayList<TblGestionEnviosHist>();
		
		if (porLotes) {
			rowcount = getTotalLotesGestionEnvio(eg, request);
			lista = queryExecutorGestionEnviosHist.getGestionEnvioLotesPaginado(inicio, pagesize, order, column, eg);
		} else {
			rowcount = getTotalGestionEnvio(eg, request);
			lista = queryExecutorGestionEnviosHist.getGestionEnvioMensajesPaginado(inicio, pagesize, order, column, eg);
		}

		List<GestionEnvioHistoricoBean> pageList = getListGestionEnvioHistoricoBean(lista, porLotes);
		
		PaginatedList<GestionEnvioHistoricoBean> result = new PaginatedList<>();
		result.setPageList(pageList);
		result.setTotalList(rowcount);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#getGestionDeEnviosDestinatariosHistoricos(int, java.lang.Integer, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.GestionEnvioHistoricoBean, javax.servlet.http.HttpServletRequest)
	 */
	///MIGRADO
	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<GestionEnvioHistoricoBean> getGestionDeEnviosDestinatariosHistoricos(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioHistoricoBean criterio, HttpServletRequest request) throws BusinessException {
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
		
		es.minhap.plataformamensajeria.iop.beans.GestionEnvioHistoricoBean eg = new es.minhap.plataformamensajeria.iop.beans.GestionEnvioHistoricoBean();
		eg = createGestionEnvioHistoricoBean(criterio,eg);
		
		Integer rowcount = queryExecutorGestionEnviosHist.countGestionEnviosHistDestinatarios(eg);
		List<ViewGestionEnviosDestHistId> lista = new ArrayList<>();
		lista = queryExecutorGestionEnviosHist.getGestionEnvioDestinatariosHistPaginado(inicio, pagesize, order, column, eg);	
			
		List<GestionEnvioHistoricoBean> pageList = getListGestionEnvioHistBeanFromDestinatario(lista);
		
		PaginatedList<GestionEnvioHistoricoBean> result = new PaginatedList<>();
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
	 */
	///MIGRADO
	@SuppressWarnings("unchecked")
	public Integer getTotalGestionEnvio(es.minhap.plataformamensajeria.iop.beans.GestionEnvioHistoricoBean eg, HttpServletRequest request) {
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		
		try{
			StringBuffer sbf = new StringBuffer();
			
			if (mapPermisosUsuarioAplicacion != null && (!PlataformaMensajeriaUtil.isEmpty(rolUsuario) && !rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)&&!rolUsuario.equals(PlataformaMensajeriaUtil.ROL_CAID))) {
	
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
			
			return queryExecutorGestionEnviosHist.countGestionEnviosHistorico(eg);
			
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - getTotalLotesGestionEnvio:" + e);
			return 0;
		}
	}

	/**
	 * Obtener total lotes gestion envio.
	 *
	 * @param eg the eg
	 * @param request the request
	 * @return total lotes gestion envio
	 */
	///MIGRADO
	@SuppressWarnings("unchecked")
	public Integer getTotalLotesGestionEnvio(es.minhap.plataformamensajeria.iop.beans.GestionEnvioHistoricoBean eg, HttpServletRequest request) {
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		
		try{
			StringBuilder sbf = new StringBuilder();
			
			if (mapPermisosUsuarioAplicacion != null && 
					(!PlataformaMensajeriaUtil.isEmpty(rolUsuario) && 
					!rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)&&!rolUsuario.equals(PlataformaMensajeriaUtil.ROL_CAID))){
				
				Set<Integer> idAplicaciones = mapPermisosUsuarioAplicacion.keySet();
				Iterator<Integer> itAplicaciones = idAplicaciones.iterator();

				boolean first = true;
				while (itAplicaciones.hasNext()) {
					Integer idAplicacion = itAplicaciones.next();
					// System.out.println("@@@ ITERANDO IDS DE APLICACIONES "+ idAplicacion);

					if (!first) {
						sbf.append(",");
					}
					sbf.append(idAplicacion);
					first = false;
				}
			}
			
			eg.setListaIdAplicaciones(sbf.toString());
			
			return queryExecutorGestionEnviosHist.countGestionEnviosHistoricoLotes(eg);
			
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - getTotalLotesGestionEnvio:" + e);
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#loadMensaje(java.lang.String)
	 */
	////MIGRADO
	@Override
	public DetalleEnvioHistBean loadMensaje(String idMensaje) throws BusinessException {
		DetalleEnvioHistBean detalleEmail = new DetalleEnvioHistBean();

		try {
			Long mensajeId = Long.parseLong(idMensaje);
			TblGestionEnviosHist gestionEnviosHist = getEnvio(idMensaje);
			
			List<TblAdjuntosHist> listaAdjuntosHistTO = queryExecutorAdjuntosHist.getAdjuntosByMensaje(mensajeId);
			
			List<AdjuntoEmailHistoricosBean> listaAdjuntos = getListAdjuntoEmailBean(listaAdjuntosHistTO, mensajeId);
					
			detalleEmail.setNombreAplicacion(gestionEnviosHist.getAplicacion());
			detalleEmail.setNombreServicio(gestionEnviosHist.getServicio());
			detalleEmail.setNombreLoteEnvio(gestionEnviosHist.getNombre());
			detalleEmail.setIdExterno(gestionEnviosHist.getCodigoexterno());
			detalleEmail.setIdLote(gestionEnviosHist.getLoteenvioid());
			detalleEmail.setMensajeId(gestionEnviosHist.getMensajeid());
			TblMensajesHist mensaje = tblMensajesHistManager.getMensaje(gestionEnviosHist.getMensajeid());
			
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
			
			detalleEmail.setFechaHistorificacion(mensaje.getFechahistorificacion());
			detalleEmail.setDocUsuario(mensaje.getDocusuario());
			detalleEmail.setCodSIA(mensaje.getCodsia());
			detalleEmail.setCodOrganismo(mensaje.getCodorganismo());
			detalleEmail.setCodOrganismoPagador(mensaje.getCodorganismopagador());
			detalleEmail.setNombreUsuario(mensaje.getNombreusuario());
			detalleEmail.setIcono(mensaje.getIcono());
			detalleEmail.setSonido(mensaje.getSonido());
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosHistoricosImpl - loadMensaje:" + e);
		}

		return detalleEmail;
	}

	/**
	 * <p>.
	 *
	 * @param lista the lista
	 * @param mensajeId the mensaje id
	 * @return Lista de objetos
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	protected List<AdjuntoEmailHistoricosBean> getListAdjuntoEmailBean(List<TblAdjuntosHist> lista, Long mensajeId) throws BusinessException {
		List<AdjuntoEmailHistoricosBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();
			for (TblAdjuntosHist aTO : lista) {
				result.add(getAdjuntoEmailHistBean(aTO, mensajeId));
			}
		}

		return result;
	}

	/**
	 * Obtener adjunto email hist bean.
	 *
	 * @param adjuntoTO the adjunto TO
	 * @param mensajeId the mensaje id
	 * @return adjunto email hist bean
	 * @throws BusinessException the business exception
	 */
	// /MIGRADO
	protected AdjuntoEmailHistoricosBean getAdjuntoEmailHistBean(TblAdjuntosHist adjuntoTO, Long mensajeId)
			throws BusinessException {
		AdjuntoEmailHistoricosBean ad = null;
		if (adjuntoTO != null) {
			ad = new AdjuntoEmailHistoricosBean();
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
	protected List<HistoricoHistBean> getListHistoricoMultidestBean(List<ViewHistoricoHistMultidest> lista) throws BusinessException {
		List<HistoricoHistBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();

			for (ViewHistoricoHistMultidest h : lista) {
				HistoricoHistBean historico = new HistoricoHistBean();
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
	 * <p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	protected List<HistoricoHistBean> getListHistoricoBean(List<ViewHistoricoHist> lista) throws BusinessException {
		List<HistoricoHistBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();

			for (ViewHistoricoHist h : lista) {
				HistoricoHistBean historico = new HistoricoHistBean();
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

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#loadAdjunto(java.lang.Long, java.lang.Long)
	 */
	////MIGRADO
	@Override
	public AdjuntoEmailHistoricosBean loadAdjunto(Long idAdjunto, Long idEmail) {
		AdjuntoEmailHistoricosBean adjunto = new AdjuntoEmailHistoricosBean();
		
		try {
			TblAdjuntosHist adjuntoTO = tblAdjuntosHistManager.getById(idAdjunto);
			if (adjuntoTO != null ) {
				adjunto.setNombre(adjuntoTO.getNombre());
				adjunto.setEmailId(idEmail);
				adjunto.setAdjuntoId(adjuntoTO.getAdjuntoid());
				adjunto.setContenidoFile(adjuntoTO.getContenidofile());
				adjunto.setContenido(UtilCreateFile.getAdjuntoMensaje(adjunto.getContenidoFile()));
			}
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosHistorivcosImpl - loadAdjunto:" + e);
		} 
		return adjunto;
	}


	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#loadLote(java.lang.String)
	 */
	////MIGRADO
	@Override
	public DetalleLoteBean loadLote(String idLote) throws BusinessException {
		DetalleLoteBean detalleLote = new DetalleLoteBean();

		ViewLotesEnviosDetHistQuery query = new ViewLotesEnviosDetHistQuery();
		query.setLoteenvioid(Long.parseLong(idLote));
		
		try {
			ViewLotesEnviosDetHist lote = viewLotesEnviosDetHistManager.getViewLoteDetallado(query);
			
			detalleLote.setIdLoteEnvio(lote.getLoteenvioid().intValue());
			detalleLote.setNombreAplicacion(lote.getAplicacion());
			detalleLote.setNombreLoteEnvio(lote.getNombre());
			detalleLote.setNombreServicio(lote.getServicio());
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosHistImpl - loadLote:" + e);
			throw new BusinessException(e);
		} 
		return detalleLote;
	}

/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#getMensajesLotes(int, int, java.lang.Long)
 */
////MIGRADO
	@Override
	public PaginatedList<MensajeHistoricosBean> getMensajesLotes(int start, int size, Long loteId) throws BusinessException {
		try {
			List<TblMensajesHist> lista = tblMensajesHistManager.getMensajesByLote(loteId, size, start);
			Integer rowcount =queryExecutorMensajesHist.countMensajesByLote(loteId);
			
			List<MensajeHistoricosBean> listaMensajes =  getListMensajesLoteBean(lista, loteId);

			PaginatedList<MensajeHistoricosBean> result = new PaginatedList<>();
			result.setPageList(listaMensajes);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosHistImpl - getMensajesLotes:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
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
	protected ArrayList<MensajeHistoricosBean> getListMensajesLoteBean(List<TblMensajesHist> lista, Long idLote) throws BusinessException {
		ArrayList<MensajeHistoricosBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();
			for (TblMensajesHist m : lista) {
				MensajeHistoricosBean mb = new MensajeHistoricosBean();
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
				mb.setFechaHistorificacion(m.getFechahistorificacion());
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
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#isMultidestinatario(java.lang.Long)
	 */
	////MIGRADO
	@Override
	public boolean isMultidestinatario(Long mensajeId) throws BusinessException {
		return tblLotesEnviosHistManager.isMultidestinatario(mensajeId);
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#getDestinatariosMensajesMultidestinatario(int, int, java.lang.Long)
	 */
	////MIGRADO
	@Override
	public PaginatedList<DestinatariosMensajesHistoricosBean> getDestinatariosMensajesMultidestinatario(int start, int size, Long idMensaje) throws BusinessException {
		PaginatedList<DestinatariosMensajesHistoricosBean> result = new PaginatedList<>();
		ArrayList<DestinatariosMensajesHistoricosBean> listaDestinatariosMensajes = new ArrayList<>();
		try {
			// traemos el mensaje
			TblMensajesHist m = tblMensajesHistManager.getMensaje(idMensaje);
			
			//traemos los destinatarios
			List<TblDestinatariosMensHist> listaDestinariosMensajesTO = queryExecutorDestinatariosMensajesHistImpl.getDestinatarioMensHist(idMensaje, size, start);
			
			listaDestinatariosMensajes = getListDestinatariosMensajesHistoricosBean(listaDestinariosMensajesTO);
			
			if (m.getTipomensaje().equals(MENSAJENOTIFICACION)) {
				for (DestinatariosMensajesHistoricosBean dmBean : listaDestinatariosMensajes) {
					TblUsuariosPush usuario =  tblUsuariosPushManager.getUsuarioPush(Long.parseLong(dmBean.getDestinatario()));
					String us = (null != usuario)? usuario.getNombreusuario() : "";
					dmBean.setDestinatario(us);
				}
			} else if (m.getTipomensaje().equals(MENSAJEEMAIL)) {
				for (DestinatariosMensajesHistoricosBean dmBean : listaDestinatariosMensajes) {
					TblDestinatariosHist destinatario = tblDestinatariosHistManager.getDestinatario(Long.parseLong(dmBean.getDestinatario()));
					String dest = (null != destinatario)? destinatario.getEmail() : "";
					dmBean.setDestinatario(dest);
				}
			}

			Integer rowcount = queryExecutorDestinatariosMensajesHistImpl.countDestinatariosByMensaje(idMensaje);

			result.setPageList(listaDestinatariosMensajes);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosHistoricosImpl - getDestinatariosMensajesMultidestinatario:",e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}

	}

	/**
	 * Obtener list destinatarios mensajes historicos bean.
	 *
	 * @param lista the lista
	 * @return list destinatarios mensajes historicos bean
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	protected ArrayList<DestinatariosMensajesHistoricosBean> getListDestinatariosMensajesHistoricosBean(List<TblDestinatariosMensHist> lista) throws BusinessException {
		ArrayList<DestinatariosMensajesHistoricosBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();
			for (TblDestinatariosMensHist dm : lista) {
				DestinatariosMensajesHistoricosBean dmb = new DestinatariosMensajesHistoricosBean();
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
				dmb.setFechaHistorificacion(dm.getFechahistorificacion());
				result.add(dmb);
			}
		}
		return result;
	}

	

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#getDestinatariosMensajes(int, int, java.lang.Long)
	 */
	////MIGRADO
	@Override
	public PaginatedList<DestinatariosMensajesHistoricosBean> getDestinatariosMensajes(int start, int size, Long idMensaje) throws BusinessException {
		PaginatedList<DestinatariosMensajesHistoricosBean> result = new PaginatedList<>();
		ArrayList<DestinatariosMensajesHistoricosBean> listaDestinatariosMensajes = new ArrayList<>();
		try {
			// traemos el mensaje
			TblMensajesHist m = tblMensajesHistManager.getMensaje(idMensaje);
			
			if (m.getTipomensaje().equals(MENSAJESMS) || m.getTipomensaje().equals(MENSAJERECEPCION)) {
				DestinatariosMensajesHistoricosBean dmBean = loadDestinatarioMensaje(m);
				dmBean.setDestinatario(m.getTelefono());
				listaDestinatariosMensajes.add(dmBean);
			} else if (m.getTipomensaje().equals(MENSAJENOTIFICACION)) {
				DestinatariosMensajesHistoricosBean dmBean = loadDestinatarioMensaje(m);
				dmBean.setDestinatario(m.getNombreusuario());
				listaDestinatariosMensajes.add(dmBean);
			} else {
				List<String> listaDestinatarios = tblDestinatariosHistManager.getDestinatarios(m.getMensajeid());
				for (String email : listaDestinatarios) {
					DestinatariosMensajesHistoricosBean dmBean = loadDestinatarioMensaje(m);
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
			logger.error("ServicioGestionEnviosHistoricosImpl - getDestinatariosMensajes:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}

	/**
	 * Load destinatario mensaje.
	 *
	 * @param ms the ms
	 * @return the destinatarios mensajes historicos bean
	 */
	////MIGRADO
	private DestinatariosMensajesHistoricosBean loadDestinatarioMensaje(TblMensajesHist ms) {
		DestinatariosMensajesHistoricosBean dmBean = new DestinatariosMensajesHistoricosBean();
		dmBean.setCodigoExterno(ms.getCodigoexterno());
		dmBean.setCreadoPor(ms.getCreadopor());
		dmBean.setEstado(ms.getEstadoactual());
		dmBean.setFechaCreacion(ms.getFechacreacion());
		dmBean.setFechaModificacion(ms.getFechamodificacion());
		dmBean.setMensajeId(ms.getMensajeid());
		dmBean.setModificadoPor(ms.getModificadopor());
		dmBean.setUim(ms.getUim());
		dmBean.setTipoMensaje(ms.getTipomensaje());
		dmBean.setUltimoEnvio(ms.getUltimoenvio());
		dmBean.setFechaHistorificacion(ms.getFechahistorificacion());
		return dmBean;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#getDestinatariosMensajesHistoricos(java.lang.String)
	 */
	////MIGRADO
	@Override
	public DestinatariosMensajesHistoricosBean getDestinatariosMensajesHistoricos(String idDestinatariosMensajes) throws BusinessException {
		DestinatariosMensajesHistoricosBean res = new DestinatariosMensajesHistoricosBean();

		try {
			TblDestinatariosMensHist dm = tblDestinatariosMensHistManager.getDestinatarioMensaje(Long.parseLong(idDestinatariosMensajes));
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
			res.setFechaHistorificacion(dm.getFechahistorificacion());
		}catch (Exception e) {
			logger.error("ServicioGestionEnviosHistoricosImpl - getDestinatariosMensajes:" + e);
			throw new BusinessException(e);
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#getMensajeHistorico(java.lang.String)
	 */
	////MIGRADO
	@Override
	public GestionEnvioHistoricoBean getMensajeHistorico(String idMensaje) throws BusinessException {
		GestionEnvioHistoricoBean geBean = new GestionEnvioHistoricoBean();

		try {
			// Obtenemos los datos generales del envio
			TblGestionEnviosHist ge = tblGestionEnviosHistManager.getGestionEnviosById(Long.parseLong(idMensaje));

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
			geBean.setFechaHistorificacion(ge.getFechahistorificacion());

		} catch (Exception e) {
			logger.error("ServicioGestionEnviosHistoricosImpl - getMensaje:" + e);
			throw new BusinessException(e);
		}
		return geBean;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#getHistoricosHistMensaje(java.lang.String, java.lang.String)
	 */
	////MIGRADO
	@Override
	public List<HistoricoHistBean> getHistoricosHistMensaje(String idMensaje, String idDestinatariosMensajes) throws BusinessException {
		List<HistoricoHistBean> result = new ArrayList<>();

		try {
			if (null != idDestinatariosMensajes) {
				ViewHistoricoHistMultidestQuery query = new ViewHistoricoHistMultidestQuery();
				query.setMensajeid(Long.parseLong(idMensaje));
				query.setDestinatariosmensajes(Long.parseLong(idDestinatariosMensajes));
				query.addOrder("historicoid", OrderType.DESC);
				List<ViewHistoricoHistMultidest> listaHistoricos = viewHistoricoHistManager.getHistoricoMultidest(query);
				result = getListHistoricoMultidestBean(listaHistoricos);
			} else {
				ViewHistoricoHistQuery query = new ViewHistoricoHistQuery();
				query.setMensajeid(Long.parseLong(idMensaje));
				query.addOrder("historicoid", OrderType.DESC);
				List<ViewHistoricoHist> listaHistoricos = viewHistoricoHistManager.getHistorico(query);
				result = getListHistoricoBean(listaHistoricos);
			}

			return result;
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosHistImpl - getHistoricosHistMensaje:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#getTodosGestionEnviosCons(java.util.List)
 */
////MIGRADO
	@Override
	public List<TblGestionEnviosHist> getTodosGestionEnviosCons(List<Long> listaMensajes)
			throws BusinessException {
		List<TblGestionEnviosHist> result = new ArrayList<>();
		try {
			result = queryExecutorGestionEnviosHist.getTodosGestionEnviosCons(listaMensajes);
			
		} catch (Exception e) {
			logger.error("ServicioMensajesAdjuntosHist.getIdMensajesAdjuntosCons", e);
			throw new BusinessException(e, "errors.job.cons.getMensajesAdjuntos");
		}
		return result;
	}
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#getTblGestionEnviosHist(java.util.List)
 */
////MIGRADO
	@Override
	public List<TblGestionEnviosHist> getTblGestionEnviosHist(List<Long> subList) {
		return queryExecutorGestionEnviosHist.convertGestionEnviosTOGestionEnviosHist(subList);
	}
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#insert(es.minhap.sim.model.TblGestionEnviosHist)
 */
////MIGRADO
	@Override
	public Integer insert(TblGestionEnviosHist gestionEnvioHistorico) {
		return tblGestionEnviosHistManager.insertarGestionEnvios(gestionEnvioHistorico);
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#delete(java.lang.Long)
	 */
	////MIGRADO
	@Override
	public void delete(Long mensajeid) {
		tblGestionEnviosManager.delete(mensajeid);
	}

	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos#getEnvio(java.lang.String)
	 */
	///MIGRADO
		@Override
		public TblGestionEnviosHist getEnvio(String idEnvio) throws BusinessException {
			try {
				TblGestionEnviosHistQuery query = new TblGestionEnviosHistQuery();
				query.setMensajeid(Long.parseLong(idEnvio));
				return tblGestionEnviosHistManager.getGestionEnviosById(Long.parseLong(idEnvio));
			} catch (Exception e) {
				logger.error("ServicioGestionEnviosHistImpl - getEnvio:" + e);
				throw new BusinessException(e, "errors.organismo.loadOrganismo");
			}
		}
	
	/**
	 * Obtener list gestion envio historico bean.
	 *
	 * @param lista the lista
	 * @param porLote the por lote
	 * @return list gestion envio historico bean
	 */
	///MIGRADO
	private List<GestionEnvioHistoricoBean> getListGestionEnvioHistoricoBean(List<TblGestionEnviosHist> lista,
			boolean porLote) {
		List<GestionEnvioHistoricoBean> result = new ArrayList<>();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		if (lista!=null && !lista.isEmpty())
		{
					
			for (TblGestionEnviosHist ge : lista) {
				GestionEnvioHistoricoBean gestionEnvio =  new GestionEnvioHistoricoBean();
			
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
				}else{
					gestionEnvio.setEstado(ge.getEstadolote());
				}
				gestionEnvio.setUltimoEnvioStr((null != ge.getUltimoenvio())? df.format(ge.getUltimoenvio()) : "");
			
				result.add(gestionEnvio);
			}
		}
			
		return result;
	}

	/**
	 * Creates the gestion envio historico bean.
	 *
	 * @param gestionEnvio the gestion envio
	 * @param ge the ge
	 * @return the es.minhap.plataformamensajeria.iop.beans. gestion envio historico bean
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	private es.minhap.plataformamensajeria.iop.beans.GestionEnvioHistoricoBean createGestionEnvioHistoricoBean(GestionEnvioHistoricoBean gestionEnvio,
			es.minhap.plataformamensajeria.iop.beans.GestionEnvioHistoricoBean ge) throws BusinessException{
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
			
			return ge;
		} catch (Exception e) {
			logger.error("ServicioGestionEnviosImpl - createGestionEnvioBean:" + e);
			throw new BusinessException(e);
		}
	}
	
/**
 * Obtener list gestion envio hist bean from destinatario.
 *
 * @param lista the lista
 * @return list gestion envio hist bean from destinatario
 */
////MIGRADO
	private List<GestionEnvioHistoricoBean> getListGestionEnvioHistBeanFromDestinatario(List<ViewGestionEnviosDestHistId> lista) {
		List<GestionEnvioHistoricoBean> result = new ArrayList<>();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		if (lista!=null && !lista.isEmpty())
		{
					
			for (ViewGestionEnviosDestHistId ge : lista) {
				GestionEnvioHistoricoBean gestionEnvio = new GestionEnvioHistoricoBean();
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
	
}
