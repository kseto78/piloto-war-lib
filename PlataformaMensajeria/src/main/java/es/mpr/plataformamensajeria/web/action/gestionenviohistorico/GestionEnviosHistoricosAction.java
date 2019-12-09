package es.mpr.plataformamensajeria.web.action.gestionenviohistorico;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.minhap.misim.bus.model.ViewMisim;
import es.minhap.misim.bus.query.ViewMisimQuery;
import es.minhap.plataformamensajeria.iop.misim.manager.ViewMisimManager;
import es.mpr.plataformamensajeria.beans.AdjuntoEmailHistoricosBean;
import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.beans.DestinatariosMensajesHistoricosBean;
import es.mpr.plataformamensajeria.beans.DetalleEnvioHistBean;
import es.mpr.plataformamensajeria.beans.DetalleLoteBean;
import es.mpr.plataformamensajeria.beans.EstadoBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioHistoricoBean;
import es.mpr.plataformamensajeria.beans.HistoricoHistBean;
import es.mpr.plataformamensajeria.beans.MensajeHistoricosBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ViewMisimBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.CifradoService;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioCanal;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioEstado;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
 
/**
 * <p>Clase Action de Struts2 para la gestion de los Envios Historicos.
 *
 * @author jgonzvil
 * @version 1.0
 */
@Controller("gestionEnvioHistoricoAction")
@Scope("prototype")
public class GestionEnviosHistoricosAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable{
	
	protected static final String INFOUSER = "infoUser";

	protected static final String EL_IDMENSAJE_RE = "EL idMensaje recibido es nulo";

	protected static final String R_CONST_REF = "50";

	protected static final String R_CONST_0 = "10";

	protected static final String GENERALESDOTREQ = "generales.REQUEST_ATTRIBUTE_TOTALSIZE";

	protected static final String R_CONST_1 = "0";

	protected static final String R_CONST_2 = "1";

	protected static final String EL_IDLOTE_RECIB = "EL idLote recibido es nulo";

	protected static final String R_CONST_3 = "20";

	protected static final String NOUSER = "noUser";

	protected static final String ERRORSDOTACTION = "errors.action.organismo.loadOrganismo";

	protected static final String GENERALESDOTPAG = "generales.PAGESIZEM";

	protected static final String R_CONST_4 = "100";

	protected static final String TABLEID = "tableId";

	protected static final String GENERALESDOTREQ0 = "generales.REQUEST_ATTRIBUTE_PAGESIZE";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**  logger. */
	private static Logger logger = Logger.getLogger(GestionEnviosHistoricosAction.class);
	
	/**  servicio aplicacion. */
	@Resource(name = "servicioAplicacionImpl")
	private ServicioAplicacion servicioAplicacion;

	/**  servicio servidor. */
	@Resource(name = "servicioServidorImpl")
	private ServicioServidor servicioServidor;

	/**  servicio servicio. */
	@Resource(name = "servicioServicioImpl")
	private ServicioServicio servicioServicio;

	/**  servicio estado. */
	@Resource(name = "servicioEstadoImpl")
	private ServicioEstado servicioEstado;

	/**  servicio canal. */
	@Resource(name = "servicioCanalImpl")
	private ServicioCanal servicioCanal;

	/**  servicio gestion envios historicos. */
	@Resource(name = "servicioGestionEnviosHistoricosImpl")
	private ServicioGestionEnviosHistoricos servicioGestionEnviosHistoricos;
	
	/**  servicio gestion envios. */
	@Resource(name = "servicioGestionEnviosImpl")
	private ServicioGestionEnvios servicioGestionEnvios;
    
	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;
	
	/**  view misim manager. */
	@Resource(name="ViewMisimManagerImpl")
	private ViewMisimManager viewMisimManager;
	
	/**  cifrado service. */
	@Resource
	private CifradoService cifradoService;
	
	/**  gestion envio historico bean. */
	private GestionEnvioHistoricoBean gestionEnvioHistoricoBean;
	
	/**  detalle email. */
	private DetalleEnvioHistBean detalleEmail;
	
	/**  detalle lote. */
	private DetalleLoteBean detalleLote;
	
	/**  detalle mensaje. */
	private GestionEnvioHistoricoBean detalleMensaje;
	
	/**  destinatarios mensajes. */
	private DestinatariosMensajesHistoricosBean destinatariosMensajes;
	
	
	/**  lista gestion envios historicos. */
	private List<GestionEnvioHistoricoBean> listaGestionEnviosHistoricos= null;
	
	/**  lista gestion envios mensajes historicos. */
	private List<MensajeHistoricosBean> listaGestionEnviosMensajesHistoricos = null;
	
	/**  lista gestion envios destinatarios mensaje historicos. */
	private List<DestinatariosMensajesHistoricosBean> listaGestionEnviosDestinatariosMensajeHistoricos = null;
	
	/**  lista historicos mensaje historicos. */
	private List<HistoricoHistBean> listaHistoricosMensajeHistoricos = null;
	
	/**  lista intercambios misim. */
	private List<ViewMisimBean> listaIntercambiosMisim = null;
	
	/**  combo aplicaciones. */
	transient List<KeyValueObject> comboAplicaciones = new ArrayList<>();
	
	/**  combo servidores. */
	transient List<KeyValueObject> comboServidores = new ArrayList<>();
	
	/**  combo servicios. */
	transient List<KeyValueObject> comboServicios = new ArrayList<>();
	
	/**  combo estados. */
	transient List<KeyValueObject> comboEstados = new ArrayList<>();
	
	/**  combo canales. */
	transient List<KeyValueObject> comboCanales = new ArrayList<>();
	
	/**  combo page size. */
	transient List<KeyValueObject> comboPageSize = new ArrayList<>();
	
	/**  check del list. */
	private String[] checkDelList;
	
	/**  result count. */
	private String resultCount;
	
	/**  id envio. */
	private String idEnvio;
    
    /**  id adjunto. */
    private String idAdjunto;
    
    /**  id email. */
    private String idEmail;
	
	/**  id lote. */
	private String idLote;
	
	/**  id mensaje. */
	private String idMensaje;
	
	/**  id destinatarios mensajes. */
	private String idDestinatariosMensajes;
    
    /**  operacion msg. */
    private String operacionMsg;
    
    /**  Check all S. */
    private String CheckAllS;
    
    /**  vista envios id selected. */
    private String vistaEnviosIdSelected;
	
	/**  page size. */
	private Integer pageSize = 20;
	
	/** Constante FORMATO_FECHA_TITULO_AUDITORIA. */
	public static final String FORMATO_FECHA_TITULO_AUDITORIA = "yyyyMMdd HHmmss";
	
	/** Constante TIPO_FICHERO. */
	public static final String TIPO_FICHERO = "xml";

	/**  adjunto descargable. */
	private String adjuntoDescargable;

	/**  file input stream. */
	private InputStream fileInputStream;

	/**
	 * New search.
	 *
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public String newSearch() throws BusinessException {
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (validUsuario()) {
			int page = getPage(TABLEID); 
			// Pagina a mostrar
			String order = getOrder(TABLEID); 
			// Ordenar de modo ascendente o
												// descendente
			String columnSort = getColumnSort(TABLEID); 
			// Columna usada para
															// ordenar
			int inicio = (page - 1) * pageSize;
			if (gestionEnvioHistoricoBean == null) {
				gestionEnvioHistoricoBean = new GestionEnvioHistoricoBean();
			}
			if (gestionEnvioHistoricoBean != null && gestionEnvioHistoricoBean.getVistaEnviosId() != null) {
				vistaEnviosIdSelected = gestionEnvioHistoricoBean.getVistaEnviosId().toString();
			} else {
				vistaEnviosIdSelected = R_CONST_2;
			}

			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());

			PaginatedList<GestionEnvioHistoricoBean> result = servicioGestionEnviosHistoricos
					.getGestionDeEnviosHistoricos(inicio - 1, export ? -1 : pageSize - 1, order, columnSort,
							gestionEnvioHistoricoBean, request, false);
			Integer totalSize = result.getTotalList();
			gestionEnvioHistoricoBean.setFechaDesde(null);
			gestionEnvioHistoricoBean.setFechaHasta(null);
			listaGestionEnviosHistoricos = result.getPageList();
			
			for(GestionEnvioHistoricoBean geb: listaGestionEnviosHistoricos){
				
				geb.setBotonIntercambios(true);
				
				ViewMisimQuery query = new ViewMisimQuery();
				query.setIdLote(geb.getIdLote());
				List<ViewMisim> viewMisim = viewMisimManager.getIntercambiosMisimByQuery(query, 0, 20);
				
				if(viewMisim == null || viewMisim.isEmpty()){
					geb.setBotonIntercambios(false);
				}
			}
			
			resultCount = (totalSize != null) ? totalSize.toString() : R_CONST_1;
			// Atributos de request
			getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ, null), totalSize);
			if (!export) {
				getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ0, null),
						pageSize);
			} else {
				getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ0, null),
						totalSize);
			}
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	/**
	 * Search.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String search() throws BaseException {
		PaginatedList<GestionEnvioHistoricoBean> result = null;
		Integer totalSize = null;
		if(getRequest().getSession().getAttribute(INFOUSER)==null){
			return NOUSER; 
		}
		if(validUsuario()){
		   	int page = getPage(TABLEID); 
		   	//Pagina a mostrar
	    	String order = getOrder(TABLEID); 
	    	//Ordenar de modo ascendente o descendente
	    	String columnSort = getColumnSort(TABLEID); 
	    	//Columna usada para ordenar
	    	int inicio = (page-1)*pageSize;
	    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
	    	
	    	if (gestionEnvioHistoricoBean != null && gestionEnvioHistoricoBean.getVistaEnviosId()!= null){
	    		vistaEnviosIdSelected = gestionEnvioHistoricoBean.getVistaEnviosId().toString();
			} else {
				vistaEnviosIdSelected = R_CONST_2;
			}
			if (R_CONST_2.equals(vistaEnviosIdSelected)) {
				result = servicioGestionEnviosHistoricos.getGestionDeEnviosHistoricos(inicio, export ? -1 : pageSize, order, columnSort, gestionEnvioHistoricoBean, request, false);
				totalSize = result.getTotalList();
			} else if ("3".equals(vistaEnviosIdSelected)){
				result = servicioGestionEnviosHistoricos.getGestionDeEnviosDestinatariosHistoricos(inicio, export ? -1 : pageSize, order, columnSort, gestionEnvioHistoricoBean, request);
				totalSize = result.getTotalList();
			}else {
				result = servicioGestionEnviosHistoricos.getGestionDeEnviosHistoricos(inicio, export ? -1 : pageSize, order, columnSort, gestionEnvioHistoricoBean, request, true);
				totalSize = result.getTotalList();
			}
	    	
	    	
	    	listaGestionEnviosHistoricos =  result.getPageList();
	    	
			for(GestionEnvioHistoricoBean geb: listaGestionEnviosHistoricos){
				
				geb.setBotonIntercambios(true);
				
				ViewMisimQuery query = new ViewMisimQuery();
				query.setIdLote(geb.getIdLote());
				List<ViewMisim> viewMisim = viewMisimManager.getIntercambiosMisimByQuery(query, 0, 20);
				
				if(viewMisim == null || viewMisim.isEmpty()){
					geb.setBotonIntercambios(false);
				}
			}
	    	
	    	resultCount = (totalSize!=null)?totalSize.toString():R_CONST_1;
	    	//Atributos de request
	    	getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ, null), totalSize);
	    	if(!export){
	    		getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ0, null), pageSize);
	    	}else{
	    		getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ0, null), totalSize);
	    	}
	        return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	/**
	 * Load contenido mensaje.
	 *
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public String loadContenidoMensaje() throws BusinessException{
		if(getRequest().getSession().getAttribute(INFOUSER)==null){
			return NOUSER; 
		}
    	if(idEnvio == null){
    		throw new BusinessException("EL idEnvio recibido es nulo");
    	}
    	try {
    		String[] arrayEnvioId = idEnvio.split("_");
    		idEmail = arrayEnvioId[1];
			detalleEmail = servicioGestionEnviosHistoricos.loadMensaje(idEmail);
			
			return SUCCESS;
		} catch (BusinessException e) {
			logger.error("GestionEnviosHistoricosAction - loadContenidoMensaje:" + e);
			throw new BusinessException(this.getText(ERRORSDOTACTION));
		}
	}
	
	/**
	 * Load lote.
	 *
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public String loadLote() throws BusinessException {
		Integer totalSize = null;
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (idLote == null) {
			throw new BusinessException(EL_IDLOTE_RECIB);
		}
		try {
			int page = getPage("tableLotesHistoricoId");
			int inicio = (page - 1) * Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_3));
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
			detalleLote = servicioGestionEnviosHistoricos.loadLote(idLote);
			PaginatedList<MensajeHistoricosBean> result = servicioGestionEnviosHistoricos.getMensajesLotes(inicio, 
					export ? -1 : Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_3)), detalleLote.getIdLoteEnvio().longValue());
			totalSize = result.getTotalList();
			resultCount = (totalSize != null) ? totalSize.toString() : R_CONST_1;
			listaGestionEnviosMensajesHistoricos = result.getPageList();
			
			return SUCCESS;

		} catch (BusinessException e) {
			logger.error("GestionEnviosHistoricosAction - loadLote:" + e);
			throw new BusinessException(this.getText("errors.action.organismo.loadLote"));
		}
	}
	
	/**
	 * Load mensaje.
	 *
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public String loadMensaje() throws BusinessException{
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (idMensaje == null) {
			throw new BusinessException(EL_IDMENSAJE_RE);
		}
		if (idLote == null) {
			throw new BusinessException(EL_IDLOTE_RECIB);
		}
    	try {
			detalleEmail = servicioGestionEnviosHistoricos.loadMensaje(idMensaje);
						
			// Carga Destinatarios_mensajes
			Integer totalSize = null;
			int page = getPage("tableMensajesId");
			int inicio = (page - 1) * pageSize;
			PaginatedList<DestinatariosMensajesHistoricosBean> result = null;
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());

			if (servicioGestionEnviosHistoricos.isMultidestinatario(detalleEmail.getMensajeId())) {
				// sacar destinatarios de tabla destinatario_mensaje
				result = servicioGestionEnviosHistoricos.getDestinatariosMensajesMultidestinatario(inicio, 
						export ? -1 : Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_3)), detalleEmail.getMensajeId());
				totalSize = result.getTotalList();
				resultCount = (totalSize != null) ? totalSize.toString() : R_CONST_1;
				listaGestionEnviosDestinatariosMensajeHistoricos = result.getPageList();
			} else {
				result = servicioGestionEnviosHistoricos.getDestinatariosMensajes(inicio, 
						export ? -1 : Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_3)), detalleEmail.getMensajeId());
				totalSize = result.getTotalList();
				resultCount = (totalSize != null) ? totalSize.toString() : R_CONST_1;
				listaGestionEnviosDestinatariosMensajeHistoricos = result.getPageList();
			}
						
			return SUCCESS;
		} catch (BusinessException e) {
			logger.error("[GestionEnviosHistoricosAction] - loadMensaje:" + e);
			throw new BusinessException(this.getText(ERRORSDOTACTION));
		}
	}
	
	/**
	 * Load misim historico.
	 *
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	public String loadMisimHistorico() throws BusinessException {
		
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (idLote == null) {
			throw new BusinessException("El idLote recibido es nulo");
		}
		
		if(idMensaje == null) {
			throw new BusinessException("El idMensaje recibido es nulo");
		}
		
		try {
			
			idEmail = idMensaje;
			
			detalleEmail = servicioGestionEnviosHistoricos.loadMensaje(idEmail);

			Integer totalSize = null;
			int page = getPage("tableMisimId");
			int inicio = (page - 1) * pageSize;
			PaginatedList<ViewMisimBean> result = null;
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
			
			result = servicioGestionEnvios.getIntercambiosMisim(inicio, export ? -1
					: Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_3)), Long.valueOf(idLote), idMensaje);
			totalSize = result.getTotalList();
			resultCount = (totalSize != null) ? totalSize.toString() : R_CONST_1;
			listaIntercambiosMisim = result.getPageList();
			
			return SUCCESS;

		} catch (BusinessException e) {
			logger.error("[GestionEnviosHistoricosAction] - loadMisimHistorico:" + e);
			throw new BusinessException(this.getText("errors.action.gestionEnvios.loadMisimHistorico"));
		}
	}
	
	/**
	 * Load historico msj.
	 *
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public String loadHistoricoMsj() throws BusinessException {
		destinatariosMensajes = null;
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (idMensaje == null) {
			throw new BusinessException(EL_IDMENSAJE_RE);
		}
		if (idDestinatariosMensajes != null && !"null".equals(idDestinatariosMensajes) && Integer.parseInt(idDestinatariosMensajes) > 0) {
			destinatariosMensajes = servicioGestionEnviosHistoricos.getDestinatariosMensajesHistoricos(idDestinatariosMensajes);
		}else{
			idDestinatariosMensajes = null;
		}
		try {
			detalleMensaje = servicioGestionEnviosHistoricos.getMensajeHistorico(idMensaje);
			
			if (null == destinatariosMensajes) {
				//se trata de un mensaje antiguo
				//cargamos los datos a mostrar de destinatarios Mensajes
				destinatariosMensajes = new DestinatariosMensajesHistoricosBean();
				destinatariosMensajes.setCodigoExterno(detalleMensaje.getIdExterno());
			}
			listaHistoricosMensajeHistoricos = servicioGestionEnviosHistoricos.getHistoricosHistMensaje(idMensaje,idDestinatariosMensajes);
			

			return SUCCESS;

		} catch (BusinessException e) {
			logger.error("GestionEnviosHistoricosAction - loadHistoricoMsj:" + e);
			throw new BusinessException(this.getText("errors.action.organismo.loadHistoricos"));
		}
	}

	/**
	 * Load adjunto.
	 *
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	////MIGRADO
	public String loadAdjunto() throws IOException{
		if(getRequest().getSession().getAttribute(INFOUSER)==null) {
			return NOUSER;
		} 
		try{
			AdjuntoEmailHistoricosBean adjunto = servicioGestionEnviosHistoricos.loadAdjunto(Long.parseLong(idAdjunto), Long.parseLong(idEmail));
			
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(adjunto.getContenido());
			
		
			this.fileInputStream = byteArrayInputStream;
			this.adjuntoDescargable = "attachment;filename=\"" + adjunto.getNombre() + "\"";
		}catch(Exception exc){
			logger.error("GestionEnviosHistoricosAction - loadAdjunto:" + exc);
			addActionErrorSession("No se puede descargar el documento seleccionado: " + exc.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * Comprueba si hay un usuario logueado y con un rol definido.
	 *
	 * @return boolean
	 */
	////MIGRADO
	private boolean validUsuario() {
	 	boolean sw = true;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if(PlataformaMensajeriaUtil.isEmpty(rolUsuario)||PlataformaMensajeriaUtil.isEmptyNumber(idUsuario)){
			sw=false;
		}
		return sw;
	}
 
 /**
  * Obtener combo aplicaciones.
  *
  * @return combo aplicaciones
  */
	////MIGRADO
	public List<KeyValueObject> getComboAplicaciones() {
        List<KeyValueObject> result = new ArrayList<>();
        KeyValueObject option;
        ArrayList<AplicacionBean> keys;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		keys = (ArrayList<AplicacionBean>)servicioAplicacion.getAplicacionesMenu(rolUsuario, idUsuario);
		if(keys!=null&& !keys.isEmpty()){
	        for (AplicacionBean key :keys) {
	            option = new KeyValueObject();
	            option.setCodigo(key.getAplicacionId().toString());
	            option.setDescripcion(key.getNombre());
	            result.add(option);
	        }
		}
        return result;
    }
	
	/**
	 * Obtener combo servidores.
	 *
	 * @return combo servidores
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public List<KeyValueObject> getComboServidores() throws BusinessException {
        List<KeyValueObject> result = new ArrayList<>();
        KeyValueObject option;
        ArrayList<ServidorBean> keys;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		keys = (ArrayList<ServidorBean>)servicioServidor.getServidoresYProveedores(rolUsuario,idUsuario);
		if(keys!=null&& !keys.isEmpty()){
	        for (ServidorBean key :keys) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getServidorid().toString());
	            option.setDescripcion(key.getNombre());
	            result.add(option);
	        }
		}
        return result;
    }
	
	/**
	 * Obtener combo servicios.
	 *
	 * @return combo servicios
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public List<KeyValueObject> getComboServicios() throws BusinessException {
        List<KeyValueObject> result = new ArrayList<>();
        KeyValueObject option;
        ArrayList<ServicioBean> keys;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if(gestionEnvioHistoricoBean!=null&&gestionEnvioHistoricoBean.getAplicacionId()!=null){
			keys = (ArrayList<ServicioBean>)servicioServicio.getServiciosByAplicacionId(gestionEnvioHistoricoBean.getAplicacionId().intValue());
		}else{
			keys = (ArrayList<ServicioBean>)servicioServicio.getServicios(rolUsuario,idUsuario);
		}
		if(keys!=null&& !keys.isEmpty()){
	        for (ServicioBean key :keys) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getServicioId().toString());
	            option.setDescripcion(key.getNombre());
	            result.add(option);
	        }
		}
        return result;
    }	
	
	/**
	 * Obtener combo estados.
	 *
	 * @return combo estados
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public List<KeyValueObject> getComboEstados() throws BusinessException {
        List<KeyValueObject> result = new ArrayList<>();
        KeyValueObject option;
        ArrayList<EstadoBean> keys;
		keys = (ArrayList<EstadoBean>)servicioEstado.getEstados();
		if(keys!=null&&!keys.isEmpty()){
	        for (EstadoBean key :keys) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getEstadoId().toString());
	            option.setDescripcion(key.getNombre());
	            result.add(option);
	        }
		}
        return result;
    }		
	
	/**
	 * Obtener combo canales.
	 *
	 * @return combo canales
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public List<KeyValueObject> getComboCanales() throws BusinessException {
        List<KeyValueObject> result = new ArrayList<>();
        KeyValueObject option;
        ArrayList<CanalBean> keys;
		keys = (ArrayList<CanalBean>)servicioCanal.getCanales();
		if(keys!=null&&!keys.isEmpty()){
	        for (CanalBean key :keys) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getCanalId().toString());
	            option.setDescripcion(key.getNombre());
	            result.add(option);
	        }
		}
        return result;
    }	
	
	/**
	 * Obtener combo page size.
	 *
	 * @return combo page size
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public List<KeyValueObject> getComboPageSize() throws BusinessException{
		  List<KeyValueObject> result = new ArrayList<>();
	      KeyValueObject option10 = new KeyValueObject(R_CONST_0,R_CONST_0);
	      KeyValueObject option20 = new KeyValueObject(R_CONST_3,R_CONST_3);
	      KeyValueObject option50 = new KeyValueObject(R_CONST_REF,R_CONST_REF);
	      KeyValueObject option100 = new KeyValueObject(R_CONST_4,R_CONST_4);
	      result.add(option10);
	      result.add(option20);
	      result.add(option50);
	      result.add(option100);
	      
	      return result;
	        
		
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	// /MIGRADO
	@Override
	public void prepare() throws Exception {
		pageSize = (Integer) getRequest().getAttribute(
				properties.getProperty(GENERALESDOTREQ0, null));
		if (null == pageSize) {
			pageSize = Integer.parseInt(properties.getProperty("generales.PAGESIZE", R_CONST_3));
		}
	}
	
	/**
	 * Obtener servicio aplicacion.
	 *
	 * @return servicio aplicacion
	 */
	public ServicioAplicacion getServicioAplicacion() {
		return servicioAplicacion;
	}
	
	/**
	 * Modificar servicio aplicacion.
	 *
	 * @param servicioAplicacion new servicio aplicacion
	 */
	public void setServicioAplicacion(ServicioAplicacion servicioAplicacion) {
		this.servicioAplicacion = servicioAplicacion;
	}

	/**
	 * Modificar combo aplicaciones.
	 *
	 * @param comboAplicaciones new combo aplicaciones
	 */
	public void setComboAplicaciones(List<KeyValueObject> comboAplicaciones) {
		this.comboAplicaciones = new ArrayList<>(comboAplicaciones);
	}
	
	/**
	 * Obtener result count.
	 *
	 * @return result count
	 */
	public String getResultCount() {
		return resultCount;
	}
	
	/**
	 * Modificar result count.
	 *
	 * @param resultCount new result count
	 */
	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}
	
	/**
	 * Obtener servicio canal.
	 *
	 * @return servicio canal
	 */
    public ServicioCanal getServicioCanal() {
		return servicioCanal;
	}
	
	/**
	 * Modificar servicio canal.
	 *
	 * @param servicioCanal new servicio canal
	 */
	public void setServicioCanal(ServicioCanal servicioCanal) {
		this.servicioCanal = servicioCanal;
	}
	
	/**
	 * Obtener servicio estado.
	 *
	 * @return servicio estado
	 */
	public ServicioEstado getServicioEstado() {
		return servicioEstado;
	}
	
	/**
	 * Modificar servicio estado.
	 *
	 * @param servicioEstado new servicio estado
	 */
	public void setServicioEstado(ServicioEstado servicioEstado) {
		this.servicioEstado = servicioEstado;
	}


	/**
	 * Obtener lista gestion envios historicos.
	 *
	 * @return lista gestion envios historicos
	 */
	public List<GestionEnvioHistoricoBean> getListaGestionEnviosHistoricos() {
		return new ArrayList<>(listaGestionEnviosHistoricos);
	}
	
	/**
	 * Modificar lista gestion envios historicos.
	 *
	 * @param listaGestionEnviosHistoricos new lista gestion envios historicos
	 */
	public void setListaGestionEnviosHistoricos(List<GestionEnvioHistoricoBean> listaGestionEnviosHistoricos) {
		this.listaGestionEnviosHistoricos = new ArrayList<>(listaGestionEnviosHistoricos);
	}
	
	/**
	 * Obtener lista intercambios misim.
	 *
	 * @return lista intercambios misim
	 */
	public List<ViewMisimBean> getListaIntercambiosMisim() {
		return listaIntercambiosMisim;
	}

	/**
	 * Modificar lista intercambios misim.
	 *
	 * @param listaIntercambiosMisim new lista intercambios misim
	 */
	public void setListaIntercambiosMisim(List<ViewMisimBean> listaIntercambiosMisim) {
		this.listaIntercambiosMisim = listaIntercambiosMisim;
	}

	/**
	 * Obtener servicio servidor.
	 *
	 * @return servicio servidor
	 */
	public ServicioServidor getServicioServidor() {
		return servicioServidor;
	}
	
	/**
	 * Modificar servicio servidor.
	 *
	 * @param servicioServidor new servicio servidor
	 */
	public void setServicioServidor(ServicioServidor servicioServidor) {
		this.servicioServidor = servicioServidor;
	}
	
	/**
	 * Obtener servicio servicio.
	 *
	 * @return servicio servicio
	 */
	public ServicioServicio getServicioServicio() {
		return servicioServicio;
	}
	
	/**
	 * Modificar servicio servicio.
	 *
	 * @param servicioServicio new servicio servicio
	 */
	public void setServicioServicio(ServicioServicio servicioServicio) {
		this.servicioServicio = servicioServicio;
	}
	
	/**
	 * Modificar combo servidores.
	 *
	 * @param comboServidores new combo servidores
	 */
	public void setComboServidores(List<KeyValueObject> comboServidores) {
		this.comboServidores = new ArrayList<>(comboServidores);
	}
	
	/**
	 * Modificar combo servicios.
	 *
	 * @param comboServicios new combo servicios
	 */
	public void setComboServicios(List<KeyValueObject> comboServicios) {
		this.comboServicios = new ArrayList<>(comboServicios);
	}
	
	/**
	 * Modificar combo estados.
	 *
	 * @param comboEstados new combo estados
	 */
	public void setComboEstados(List<KeyValueObject> comboEstados) {
		this.comboEstados = new ArrayList<>(comboEstados);
	}
	
	/**
	 * Modificar combo canales.
	 *
	 * @param comboCanales new combo canales
	 */
	public void setComboCanales(List<KeyValueObject> comboCanales) {
		this.comboCanales = new ArrayList<>(comboCanales);
	}
	
	/**
	 * Obtener gestion envio historico bean.
	 *
	 * @return gestion envio historico bean
	 */
    public GestionEnvioHistoricoBean getGestionEnvioHistoricoBean() {
		return gestionEnvioHistoricoBean;
	}
	
	/**
	 * Modificar gestion envio historico bean.
	 *
	 * @param gestionEnvioHistoricoBean new gestion envio historico bean
	 */
	public void setGestionEnvioHistoricoBean(GestionEnvioHistoricoBean gestionEnvioHistoricoBean) {
		this.gestionEnvioHistoricoBean = gestionEnvioHistoricoBean;
	}	
	
	/**
	 * Obtener id envio.
	 *
	 * @return id envio
	 */
	public String getIdEnvio() {
		return idEnvio;
	}
	
	/**
	 * Modificar id envio.
	 *
	 * @param idEnvio new id envio
	 */
	public void setIdEnvio(String idEnvio) {
		this.idEnvio = idEnvio;
	}
	
	/**
	 * Obtener detalle email.
	 *
	 * @return detalle email
	 */
	public DetalleEnvioHistBean getDetalleEmail() {
		return detalleEmail;
	}
	
	/**
	 * Modificar detalle email.
	 *
	 * @param detalleEmail new detalle email
	 */
	public void setDetalleEmail(DetalleEnvioHistBean detalleEmail) {
		this.detalleEmail = detalleEmail;
	}
	
	/**
	 * Obtener check del list.
	 *
	 * @return check del list
	 */
	public String[] getCheckDelList() {
		return checkDelList;
	}
	
	/**
	 * Modificar check del list.
	 *
	 * @param checkDelList new check del list
	 */
	public void setCheckDelList(String[] checkDelList) {
		this.checkDelList = checkDelList;
	}
    
    /**
     * Obtener check all S.
     *
     * @return check all S
     */
    public String getCheckAllS() {
		return CheckAllS;
	}
	
	/**
	 * Modificar check all S.
	 *
	 * @param checkAllS new check all S
	 */
	public void setCheckAllS(String checkAllS) {
		CheckAllS = checkAllS;
	}
	
	/**
	 * Obtener page size.
	 *
	 * @return page size
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * Modificar page size.
	 *
	 * @param pageSize new page size
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * Obtener lista gestion envios mensajes historicos.
	 *
	 * @return lista gestion envios mensajes historicos
	 */
	public List<MensajeHistoricosBean> getListaGestionEnviosMensajesHistoricos() {
		return listaGestionEnviosMensajesHistoricos;
	}
	
	/**
	 * Modificar lista gestion envios mensajes historicos.
	 *
	 * @param listaGestionEnviosMensajesHistoricos new lista gestion envios mensajes historicos
	 */
	public void setListaGestionEnviosMensajesHistoricos(List<MensajeHistoricosBean> listaGestionEnviosMensajesHistoricos) {
		this.listaGestionEnviosMensajesHistoricos = listaGestionEnviosMensajesHistoricos;
	}
	
	/**
	 * Obtener lista gestion envios destinatarios mensaje historicos.
	 *
	 * @return lista gestion envios destinatarios mensaje historicos
	 */
	public List<DestinatariosMensajesHistoricosBean> getListaGestionEnviosDestinatariosMensajeHistoricos() {
		return listaGestionEnviosDestinatariosMensajeHistoricos;
	}
	
	/**
	 * Modificar lista gestion envios destinatarios mensaje historicos.
	 *
	 * @param listaGestionEnviosDestinatariosMensajeHistoricos new lista gestion envios destinatarios mensaje historicos
	 */
	public void setListaGestionEnviosDestinatariosMensajeHistoricos(List<DestinatariosMensajesHistoricosBean> listaGestionEnviosDestinatariosMensajeHistoricos) {
		this.listaGestionEnviosDestinatariosMensajeHistoricos = listaGestionEnviosDestinatariosMensajeHistoricos;
	}
	
	/**
	 * Obtener lista historicos mensaje historicos.
	 *
	 * @return lista historicos mensaje historicos
	 */
	public List<HistoricoHistBean> getListaHistoricosMensajeHistoricos() {
		return listaHistoricosMensajeHistoricos;
	}
	
	/**
	 * Modificar lista historicos mensaje historicos.
	 *
	 * @param listaHistoricosMensajeHistoricos new lista historicos mensaje historicos
	 */
	public void setListaHistoricosMensajeHistoricos(List<HistoricoHistBean> listaHistoricosMensajeHistoricos) {
		this.listaHistoricosMensajeHistoricos = listaHistoricosMensajeHistoricos;
	}
	
	/**
	 * Obtener detalle lote.
	 *
	 * @return detalle lote
	 */
	public DetalleLoteBean getDetalleLote() {
		return detalleLote;
	}
	
	/**
	 * Modificar detalle lote.
	 *
	 * @param detalleLote new detalle lote
	 */
	public void setDetalleLote(DetalleLoteBean detalleLote) {
		this.detalleLote = detalleLote;
	}
	
	/**
	 * Obtener detalle mensaje.
	 *
	 * @return detalle mensaje
	 */
	public GestionEnvioHistoricoBean getDetalleMensaje() {
		return detalleMensaje;
	}
	
	/**
	 * Modificar detalle mensaje.
	 *
	 * @param detalleMensaje new detalle mensaje
	 */
	public void setDetalleMensaje(GestionEnvioHistoricoBean detalleMensaje) {
		this.detalleMensaje = detalleMensaje;
	}
	
	/**
	 * Obtener destinatarios mensajes.
	 *
	 * @return destinatarios mensajes
	 */
	public DestinatariosMensajesHistoricosBean getDestinatariosMensajes() {
		return destinatariosMensajes;
	}
	
	/**
	 * Modificar destinatarios mensajes.
	 *
	 * @param destinatariosMensajes new destinatarios mensajes
	 */
	public void setDestinatariosMensajes(DestinatariosMensajesHistoricosBean destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}
	
	/**
	 * Obtener id lote.
	 *
	 * @return id lote
	 */
	public String getIdLote() {
		return idLote;
	}
	
	/**
	 * Modificar id lote.
	 *
	 * @param idLote new id lote
	 */
	public void setIdLote(String idLote) {
		this.idLote = idLote;
	}
	
	/**
	 * Obtener id mensaje.
	 *
	 * @return id mensaje
	 */
	public String getIdMensaje() {
		return idMensaje;
	}
	
	/**
	 * Modificar id mensaje.
	 *
	 * @param idMensaje new id mensaje
	 */
	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}
	
	/**
	 * Obtener id destinatarios mensajes.
	 *
	 * @return id destinatarios mensajes
	 */
	public String getIdDestinatariosMensajes() {
		return idDestinatariosMensajes;
	}
	
	/**
	 * Modificar id destinatarios mensajes.
	 *
	 * @param idDestinatariosMensajes new id destinatarios mensajes
	 */
	public void setIdDestinatariosMensajes(String idDestinatariosMensajes) {
		this.idDestinatariosMensajes = idDestinatariosMensajes;
	}
	
	/**
	 * Obtener vista envios id selected.
	 *
	 * @return vista envios id selected
	 */
	public String getVistaEnviosIdSelected() {
		return vistaEnviosIdSelected;
	}
	
	/**
	 * Modificar vista envios id selected.
	 *
	 * @param vistaEnviosIdSelected new vista envios id selected
	 */
	public void setVistaEnviosIdSelected(String vistaEnviosIdSelected) {
		this.vistaEnviosIdSelected = vistaEnviosIdSelected;
	}
	
	/**
	 * Obtener properties.
	 *
	 * @return the properties
	 */
	public PlataformaMensajeriaProperties getProperties() {
		return properties;
	}
	
	/**
	 * Modificar properties.
	 *
	 * @param properties the properties to set
	 */
	public void setProperties(PlataformaMensajeriaProperties properties) {
		this.properties = properties;
	}
	
	/**
	 * Obtener operacion msg.
	 *
	 * @return operacion msg
	 */
	public String getOperacionMsg() {
		return operacionMsg;
	}
	
	/**
	 * Modificar operacion msg.
	 *
	 * @param operacionMsg new operacion msg
	 */
	public void setOperacionMsg(String operacionMsg) {
		this.operacionMsg = operacionMsg;
	}
	
	/**
	 * Obtener id email.
	 *
	 * @return id email
	 */
	public String getIdEmail() {
		return idEmail;
	}
	
	/**
	 * Modificar id email.
	 *
	 * @param idEmail new id email
	 */
	public void setIdEmail(String idEmail) {
		this.idEmail = idEmail;
	}
	
	/**
     * Obtener adjunto descargable.
     *
     * @return adjunto descargable
     */
    public String getAdjuntoDescargable() {
		return adjuntoDescargable;
	}
	
	/**
	 * Modificar adjunto descargable.
	 *
	 * @param adjuntoDescargable new adjunto descargable
	 */
	public void setAdjuntoDescargable(String adjuntoDescargable) {
		this.adjuntoDescargable = adjuntoDescargable;
	}
	
	/**
	 * Obtener id adjunto.
	 *
	 * @return id adjunto
	 */
	public String getIdAdjunto() {
		return idAdjunto;
	}
	
	/**
	 * Modificar id adjunto.
	 *
	 * @param idAdjunto new id adjunto
	 */
	public void setIdAdjunto(String idAdjunto) {
		this.idAdjunto = idAdjunto;
	}
	
	/**
	 * Obtener file input stream.
	 *
	 * @return file input stream
	 */
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	  
  	/**
  	 * Devuelve el servicio de gestion de envios historicos.
  	 *
  	 * @return servicio gestion envios historicos
  	 */
	public ServicioGestionEnviosHistoricos getServicioGestionEnviosHistoricos() {
		return servicioGestionEnviosHistoricos;
	}
	
	/**
	 * Modificar servicio gestion envios historicos.
	 *
	 * @param servicioGestionEnviosHistoricos new servicio gestion envios historicos
	 */
	public void setServicioGestionEnviosHistoricos(ServicioGestionEnviosHistoricos servicioGestionEnviosHistoricos) {
		this.servicioGestionEnviosHistoricos = servicioGestionEnviosHistoricos;
	}
}
