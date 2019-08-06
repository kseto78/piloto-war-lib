package es.mpr.plataformamensajeria.web.action.gestionenvio;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.w3c.dom.Document;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.minhap.common.spring.ApplicationContextProvider;
import es.minhap.misim.bus.model.ViewMisim;
import es.minhap.misim.bus.query.ViewMisimQuery;
import es.minhap.plataformamensajeria.iop.misim.manager.PeticionManager;
import es.minhap.plataformamensajeria.iop.misim.manager.ViewMisimManager;
import es.minhap.sim.model.TblGestionEnvios;
import es.mpr.plataformamensajeria.beans.AdjuntoEmailBean;
import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.beans.DecodeBean;
import es.mpr.plataformamensajeria.beans.DestinatariosMensajesBean;
import es.mpr.plataformamensajeria.beans.DetalleEnvioBean;
import es.mpr.plataformamensajeria.beans.DetalleLoteBean;
import es.mpr.plataformamensajeria.beans.EstadoBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioBean;
import es.mpr.plataformamensajeria.beans.HistoricoBean;
import es.mpr.plataformamensajeria.beans.MensajeBean;
import es.mpr.plataformamensajeria.beans.PeticionBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ViewMisimBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.CifradoService;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioCanal;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioEstado;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuarioAplicacion;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.plataformamensajeria.util.XMLUtils;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los Usuarios.
 *
 * @author i-nercya
 * @version 1.0
 */
@Controller("gestionEnvioAction")
@Scope("prototype")
public class GestionEnviosAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	/** Constante GENERALES_PAGESIZEM. */
	private static final String GENERALES_PAGESIZEM = "generales.PAGESIZEM";

	/** Constante GENERALES_REQUEST_ATTRIBUTE_PAGESIZE. */
	private static final String GENERALES_REQUEST_ATTRIBUTE_PAGESIZE = "generales.REQUEST_ATTRIBUTE_PAGESIZE";

	/** Constante TABLE_ID. */
	private static final String TABLE_ID = "tableId";

	/** Constante INFO_USER. */
	private static final String INFO_USER = "infoUser";

	/** Constante NO_USER. */
	private static final String NO_USER = "noUser";
	
	/** Constante ES_AEAT. */
	public static final String ES_AEAT = "ES_AEAT";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(GestionEnviosAction.class);

	/**  servicio aplicacion. */
	@Resource(name = "servicioAplicacionImpl")
	private transient ServicioAplicacion servicioAplicacion;

	/**  servicio servidor. */
	@Resource(name = "servicioServidorImpl")
	private transient ServicioServidor servicioServidor;

	/**  servicio servicio. */
	@Resource(name = "servicioServicioImpl")
	private transient ServicioServicio servicioServicio;

	/**  servicio estado. */
	@Resource(name = "servicioEstadoImpl")
	private transient ServicioEstado servicioEstado;

	/**  servicio canal. */
	@Resource(name = "servicioCanalImpl")
	private transient ServicioCanal servicioCanal;

	/**  servicio gestion envios. */
	@Resource(name = "servicioGestionEnviosImpl")
	private transient ServicioGestionEnvios servicioGestionEnvios;
	
	/**  servicio usuario aplicacion. */
	@Resource(name = "servicioUsuarioAplicacionImpl")
	private transient ServicioUsuarioAplicacion servicioUsuarioAplicacion;
	
	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private transient PlataformaMensajeriaProperties properties;
	
	/**  view misim manager. */
	@Resource(name="ViewMisimManagerImpl")
	private transient ViewMisimManager viewMisimManager;
	
	/**  peticion manager. */
	@Resource(name="PeticionManagerImpl")
	private transient PeticionManager peticionManager;
	
	/**  cifrado service. */
	@Resource
	private transient CifradoService cifradoService;
	
	/**  gestion envio bean. */
	private GestionEnvioBean gestionEnvioBean;
	
	/**  detalle email. */
	private DetalleEnvioBean detalleEmail;
	
	/**  detalle lote. */
	private DetalleLoteBean detalleLote;
	
	/**  detalle mensaje. */
	private GestionEnvioBean detalleMensaje;
	
	/**  destinatarios mensajes. */
	private DestinatariosMensajesBean destinatariosMensajes;

	/**  lista gestion envios. */
	private List<GestionEnvioBean> listaGestionEnvios = null;
	
	/**  lista gestion envios mensajes. */
	private List<MensajeBean> listaGestionEnviosMensajes = null;
	
	/**  lista gestion envios destinatarios mensaje. */
	private List<DestinatariosMensajesBean> listaGestionEnviosDestinatariosMensaje = null;
	
	/**  lista historicos mensaje. */
	private List<HistoricoBean> listaHistoricosMensaje = null;
	
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
	
	/**  aa data. */
	private List<ArrayList<String>> aaData;
	
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
	
	/**  id peticion. */
	private String idPeticion;
	
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
	
	/**  adjunto descargable. */
	private String adjuntoDescargable;
	
	/**  search. */
	private String search;
	
	/**  file input stream. */
	private InputStream fileInputStream;
	
	/**  page size. */
	private Integer pageSize = 20;
	
	/** Constante FORMATO_FECHA_TITULO_AUDITORIA. */
	public static final String FORMATO_FECHA_TITULO_AUDITORIA = "yyyyMMdd HHmmss";
	
	/** Constante TIPO_FICHERO. */
	public static final String TIPO_FICHERO = "xml";

	/**
	 * New search.
	 *
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public String newSearch() throws BusinessException {
		if (getRequest().getSession().getAttribute(GestionEnviosAction.INFO_USER) == null)
			return GestionEnviosAction.NO_USER;
		if (validUsuario()) {
		
			gestionEnvioBean = new GestionEnvioBean();
			vistaEnviosIdSelected = "1";
			
			Integer totalSize = 0;
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
			listaGestionEnvios =new ArrayList<>();
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
		PaginatedList<GestionEnvioBean> result;
		Integer totalSize;
		if (getRequest().getSession().getAttribute(GestionEnviosAction.INFO_USER) == null)
			return GestionEnviosAction.NO_USER;
		if (validUsuario()) {
			int page = getPage(GestionEnviosAction.TABLE_ID); // Pagina a mostrar
			String order = getOrder(GestionEnviosAction.TABLE_ID); // Ordenar de modo ascendente o
												// descendente
			String columnSort = getColumnSort(GestionEnviosAction.TABLE_ID); // Columna usada para
															// ordenar
			int inicio = (page - 1) * pageSize;
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
			
			if (gestionEnvioBean != null && gestionEnvioBean.getVistaEnviosId() != null) {
				vistaEnviosIdSelected = gestionEnvioBean.getVistaEnviosId().toString();
			} else {
				vistaEnviosIdSelected = "1";
			}
			if (null != gestionEnvioBean){
				//Recuperamos de sesion los organismos a los que está asociado en usuario
				@SuppressWarnings("unchecked")
				List<String> arrayOrganismos = (ArrayList<String>)request.getSession().getAttribute(properties.getProperty("PlataformaMensajeriaUtil.ARRAY_ORGANISMOS", null));
			
				//Comprobamos si el usuario es propietario
				String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
				
				//Comprobamos si el usuario es propietario AEAT
				String esAeat = (String)request.getSession().getAttribute(GestionEnviosAction.ES_AEAT);
				if(search != null && search.length() > 0) {
					if (PlataformaMensajeriaUtil.ROL_PROPIETARIO.equals(rolUsuario) && "OK".equals(esAeat)){
						if(!validOrganismoAeat(search, arrayOrganismos)){
							gestionEnvioBean = new GestionEnvioBean();
							vistaEnviosIdSelected = "1";
							totalSize = 0;
							getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
							listaGestionEnvios =new ArrayList<>();
							
							return SUCCESS;
						}	
					}
			
					if (-1 != search.indexOf('|')){
						gestionEnvioBean.setCodOrganismo(search.substring(0, search.indexOf('|')).trim());
					}else{
						gestionEnvioBean.setCodOrganismo(search);
					}
				}else{
					if(arrayOrganismos!=null && !arrayOrganismos.isEmpty() 
							&& PlataformaMensajeriaUtil.ROL_PROPIETARIO.equals(rolUsuario) && "OK".equals(esAeat)){
						gestionEnvioBean.setArrayOrganismos(arrayOrganismos);
					}
				}
			}

			if ("1".equals(vistaEnviosIdSelected)) {
				result = servicioGestionEnvios.getGestionDeEnvios(inicio,
						(export) ? -1 : pageSize, order,
						columnSort, gestionEnvioBean, request, false);
				totalSize = result.getTotalList();
			} else if ("3".equals(vistaEnviosIdSelected)) {
				result = servicioGestionEnvios.getGestionDeEnviosDestinatarios(inicio,
						(export) ? -1 : pageSize, order,
						columnSort, gestionEnvioBean, request);
				totalSize = result.getTotalList();
			} else {
				result = servicioGestionEnvios.getGestionDeEnvios(inicio,
						(export) ? -1 : pageSize, order,
						columnSort, gestionEnvioBean, request, true);
				totalSize = result.getTotalList();
			}

			listaGestionEnvios = result.getPageList();
			
			for(GestionEnvioBean geb: listaGestionEnvios){
				
				geb.setBotonIntercambios(true);
				
				//se quita esta comprobacion por la lentitud de los mensajes en GestionEnvios
								
//								geb.setBotonIntercambios(true);
//								
//								ViewMisimQuery query = new ViewMisimQuery();
//								query.setIdLote(geb.getIdLote());
//								List<ViewMisim> viewMisim = viewMisimManager.getIntercambiosMisimByQuery(query, 0, 20);
//								
//								if(viewMisim == null || viewMisim.isEmpty()){
//									geb.setBotonIntercambios(false);
//								}
		   }

			
			resultCount = (totalSize != null) ? totalSize.toString() : "0";
			// Atributos de request
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
			gestionEnvioBean.setCodOrganismo(search);
			if (!export) {
				getRequest().setAttribute(properties.getProperty(GestionEnviosAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null),
						pageSize);
			} else {
				getRequest().setAttribute(properties.getProperty(GestionEnviosAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null),
						totalSize);
			}
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	/**
	 * Load contenido mensaje.
	 *
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	// //MIGRADO
	public String loadContenidoMensaje() throws BusinessException {
		if (getRequest().getSession().getAttribute(GestionEnviosAction.INFO_USER) == null)
			return GestionEnviosAction.NO_USER;
		if (idEnvio == null) {
			throw new BusinessException("EL idEnvio recibido es nulo");
		}
		try {
			String[] arrayEnvioId = idEnvio.split("_");
			idEmail = arrayEnvioId[1];
			detalleEmail = servicioGestionEnvios.loadMensaje(idEmail);

			return SUCCESS;
		} catch (BusinessException e) {
			logger.error("GestionEnviosAction - loadContenidoMensaje:" + e);
			throw new BusinessException(this.getText("errors.action.organismo.loadOrganismo"));
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
		Integer totalSize;
		if (getRequest().getSession().getAttribute(GestionEnviosAction.INFO_USER) == null)
			return GestionEnviosAction.NO_USER;
		if (idLote == null) {
			throw new BusinessException("EL idLote recibido es nulo");
		}
		try {
			int page = getPage("tableLotesId");
			int inicio = (page - 1) * Integer.parseInt(properties.getProperty(GestionEnviosAction.GENERALES_PAGESIZEM, "20"));
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
			detalleLote = servicioGestionEnvios.loadLote(idLote);
			PaginatedList<MensajeBean> result = servicioGestionEnvios.getMensajesLotes(inicio,
					(export) ? -1 : Integer.parseInt(properties.getProperty(GestionEnviosAction.GENERALES_PAGESIZEM, "20")), detalleLote.getIdLoteEnvio().longValue());
			totalSize = result.getTotalList();
			resultCount = (totalSize != null) ? totalSize.toString() : "0";
			listaGestionEnviosMensajes = result.getPageList();

			return SUCCESS;

		} catch (BusinessException e) {
			logger.error("GestionEnviosAction - loadLote:" + e);
			throw new BusinessException(this.getText("errors.action.organismo.loadLote"));
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
		if (getRequest().getSession().getAttribute(GestionEnviosAction.INFO_USER) == null)
			return GestionEnviosAction.NO_USER;
		if (idMensaje == null) {
			throw new BusinessException("EL idMensaje recibido es nulo");
		}
		if (idDestinatariosMensajes != null && !idDestinatariosMensajes.equals("null")
				&& Integer.parseInt(idDestinatariosMensajes) > 0) {
			destinatariosMensajes = servicioGestionEnvios.getDestinatariosMensajes(idDestinatariosMensajes);
		} else {
			idDestinatariosMensajes = null;
		}
		try {
			detalleMensaje = servicioGestionEnvios.getMensaje(idMensaje);

			if (null == destinatariosMensajes) {
				// se trata de un mensaje antiguo
				// cargamos los datos a mostrar de destinatarios Mensajes
				destinatariosMensajes = new DestinatariosMensajesBean();
				destinatariosMensajes.setCodigoExterno(detalleMensaje.getIdExterno());
			}
			listaHistoricosMensaje = servicioGestionEnvios.getHistoricosMensaje(idMensaje, idDestinatariosMensajes);
			idDestinatariosMensajes = null;

			return SUCCESS;

		} catch (BusinessException e) {
			logger.error("GestionEnviosAction - loadHistoricoMsj:" + e);
			throw new BusinessException(this.getText("errors.action.organismo.loadHistoricos"));
		}
	}
	
	/**
	 * Load misim.
	 *
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	public String loadMisim() throws BusinessException {
		
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idLote == null) {
			throw new BusinessException("EL idLote recibido es nulo");
		}
		
		if(idMensaje == null) {
			throw new BusinessException("El idMensaje recibido es nulo");
		}
		
		try {
			
			idEmail = idMensaje;
			
			detalleEmail = servicioGestionEnvios.loadMensaje(idEmail);

			Integer totalSize = null;
			int page = getPage("tableMisimId");
			int inicio = (page - 1) * pageSize;
			PaginatedList<ViewMisimBean> result = null;
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
			
			result = servicioGestionEnvios.getIntercambiosMisim(inicio, (export) ? -1
					: Integer.parseInt(properties.getProperty("generales.PAGESIZEM", "20")), Long.valueOf(idLote),idMensaje);
			totalSize = result.getTotalList();
			resultCount = (totalSize != null) ? totalSize.toString() : "0";
			listaIntercambiosMisim = result.getPageList();
			
			return SUCCESS;

		} catch (BusinessException e) {
			logger.error("GestionEnviosAction - loadMisim:" + e);
			throw new BusinessException(this.getText("errors.action.gestionEnvios.loadMisim"));
		}
	}
	
	/**
	 * Load xml peticion.
	 *
	 * @throws BusinessException the business exception
	 */
	public void loadXmlPeticion() throws BusinessException {
		
//		if (getRequest().getSession().getAttribute("infoUser") == null)
//			return "noUser";
		
		if (idPeticion == null) {
			throw new BusinessException("EL idPeticion recibido es nulo");
		}
		
		try {
			
		PeticionBean peticionBean = PlataformaMensajeriaUtil.loadXmlPeticion(idPeticion, peticionManager);
		
		DecodeBean decodeBean = new DecodeBean();
		
		decodeBean.setXmlCifrado(peticionBean.getMensajePeticion());
		
		if (decodeBean.getXmlCifrado()!=null && !("").equals(decodeBean.getXmlCifrado())){
				
				String certificado;
				if(decodeBean.getCertificado()!=null && !"".equals(decodeBean.getCertificado())){
					certificado = decodeBean.getCertificado();
				}else{
					certificado = properties.getProperty("decode.keystore.alias.defecto",null);
				}
				
				String xmlCifrado = decodeBean.getXmlCifrado();

				final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				dbf.setNamespaceAware(true);
				
				final Document docOriginal = XMLUtils.xml2doc(xmlCifrado, Charset.forName("UTF-8"));
				
				logger.debug("[GestionEnviosAction] - decode: XML a decodificar: " + xmlCifrado);

				String keystoreType = properties.getProperty("decode.keystore.type", null);
				String keystore = properties.getProperty("decode.keystore.path", null);
				String keystorePassword = properties.getProperty("decode.keystore.password", null);
				String alias = properties.getProperty("decode.keystore.alias." + certificado, null);
				String aliasPassword = properties.getProperty("decode.keystore.alias.password." + certificado, null);
				
				// Desciframos el documento cifrado
				Document docDescifrado = cifradoService.descifrarKey(
						docOriginal,
						keystoreType,
						keystorePassword,
						alias,
						aliasPassword,
						keystore);

				docDescifrado = cifradoService.descifrar(
						docDescifrado,
						keystoreType,
						keystorePassword,
						alias,
						aliasPassword,
						keystore);
				
				String xmlDescifrado = XMLUtils.dom2xml(docDescifrado);
				decodeBean.setXmlDescifrado(xmlDescifrado);
		
			}
		
		peticionBean.setMensajePeticion(decodeBean.getXmlDescifrado());
		
		ViewMisimQuery query = new ViewMisimQuery();
		query.setIdPeticion(Long.valueOf(idPeticion));
		
		ViewMisim viewMisim = viewMisimManager.getViewMisim(query);
		
		logger.debug("[GestionEnviosAction] - descargaFichero - inicio");
		String fechaTitulo = null;
		String horaTitulo = null;
		String[] itemsTitulo = null;

		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA_TITULO_AUDITORIA);
		Calendar fechaCal = new GregorianCalendar();
		fechaCal.setTime(viewMisim.getFechaCreacion());
		fechaTitulo = sdf.format(fechaCal.getTime());
			
		if(fechaTitulo!=null){
			itemsTitulo = fechaTitulo.split(" ");
		}
		if(itemsTitulo!=null && itemsTitulo.length>0){
			fechaTitulo = itemsTitulo[0];
			horaTitulo = itemsTitulo[1];
		}

		String contenido = peticionBean.getMensajePeticion();

		StringBuilder titulo =new  StringBuilder();
		titulo.append(fechaTitulo).append("_").append(horaTitulo).append("_").append(idPeticion).append("_Peticion");
		
		PlataformaMensajeriaUtil.descargaFicheroXml(response, titulo.toString(), contenido, TIPO_FICHERO);

		}
		
		catch (BusinessException e) {
			logger.error("[GestionEnviosAction] - loadXmlPeticion:" + e);
			throw new BusinessException(e);
		}
		
		catch (Exception e){
				logger.error("[GestionEnviosAction] Error al descifrar el XML: ",e);
				addActionErrorSession(this.getText("plataforma.decodificador.decodeDescifrar.error"));
			}
		
//		return SUCCESS;
	}
	
	/**
	 * Load xml respuesta.
	 *
	 * @throws BusinessException the business exception
	 */
	public void loadXmlRespuesta() throws BusinessException {
		
//			if (getRequest().getSession().getAttribute("infoUser") == null)
//			return "noUser";
	
		if (idPeticion == null) {
			throw new BusinessException("EL idPeticion recibido es nulo");
		}
		
		try {
			
		PeticionBean peticionBean = PlataformaMensajeriaUtil.loadXmlRespuesta(idPeticion, peticionManager);
		
		DecodeBean decodeBean = new DecodeBean();
		
		decodeBean.setXmlCifrado(peticionBean.getMensajeRespuesta());
		
		if (decodeBean.getXmlCifrado()!=null && !("").equals(decodeBean.getXmlCifrado())){
				
				String certificado;
				if(decodeBean.getCertificado()!=null && !"".equals(decodeBean.getCertificado())){
					certificado = decodeBean.getCertificado();
				}else{
					certificado = properties.getProperty("decode.keystore.alias.defecto",null);
				}
				
				String xmlCifrado = decodeBean.getXmlCifrado();
	
				final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				dbf.setNamespaceAware(true);
				
				final Document docOriginal = XMLUtils.xml2doc(xmlCifrado, Charset.forName("UTF-8"));
				
				logger.debug("[GestionEnviosAction] - decode: XML a decodificar: " + xmlCifrado);
	
				String keystoreType = properties.getProperty("decode.keystore.type", null);
				String keystore = properties.getProperty("decode.keystore.path", null);
				String keystorePassword = properties.getProperty("decode.keystore.password", null);
				String alias = properties.getProperty("decode.keystore.alias." + certificado, null);
				String aliasPassword = properties.getProperty("decode.keystore.alias.password." + certificado, null);
				
				// Desciframos el documento cifrado
				Document docDescifrado = cifradoService.descifrarKey(
						docOriginal,
						keystoreType,
						keystorePassword,
						alias,
						aliasPassword,
						keystore);
	
				docDescifrado = cifradoService.descifrar(
						docDescifrado,
						keystoreType,
						keystorePassword,
						alias,
						aliasPassword,
						keystore);
				
				String xmlDescifrado = XMLUtils.dom2xml(docDescifrado);
				decodeBean.setXmlDescifrado(xmlDescifrado);
		
			}
		
		peticionBean.setMensajeRespuesta(decodeBean.getXmlDescifrado());
		
		ViewMisimQuery query = new ViewMisimQuery();
		query.setIdPeticion(Long.valueOf(idPeticion));
		
		ViewMisim viewMisim = viewMisimManager.getViewMisim(query);
		
		logger.debug("[GestionEnviosAction] - descargaFichero - inicio");
		String fechaTitulo = null;
		String horaTitulo = null;
		String[] itemsTitulo = null;
	
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA_TITULO_AUDITORIA);
		Calendar fechaCal = new GregorianCalendar();
		fechaCal.setTime(viewMisim.getFechaCreacion());
		fechaTitulo = sdf.format(fechaCal.getTime());
			
		if(fechaTitulo!=null){
			itemsTitulo = fechaTitulo.split(" ");
		}
		if(itemsTitulo!=null && itemsTitulo.length>0){
			fechaTitulo = itemsTitulo[0];
			horaTitulo = itemsTitulo[1];
		}
	
		String contenido = peticionBean.getMensajeRespuesta();
	
		StringBuffer titulo =new  StringBuffer();
		titulo.append(fechaTitulo).append("_").append(horaTitulo).append("_").append(idPeticion).append("_Respuesta");
		
		PlataformaMensajeriaUtil.descargaFicheroXml(response, titulo.toString(), contenido, TIPO_FICHERO);
	
		}
		
		catch (BusinessException e) {
			logger.error("[GestionEnviosAction] - loadXmlRespuesta:" + e);
			throw new BusinessException(e);
		}
		
		catch (Exception e){
				logger.error("[GestionEnviosAction] Error al descifrar el XML: ",e);
				addActionErrorSession(this.getText("plataforma.decodificador.decodeDescifrar.error"));
			}
		
	//	return SUCCESS;
	}

	/**
	 * Load mensaje.
	 *
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public String loadMensaje() throws BusinessException {

		if (getRequest().getSession().getAttribute(GestionEnviosAction.INFO_USER) == null)
			return GestionEnviosAction.NO_USER;
		if (idMensaje == null) {
			throw new BusinessException("EL idMensaje recibido es nulo");
		}
		if (idLote == null) {
			throw new BusinessException("EL idLote recibido es nulo");
		}
		try {
			idEmail = idMensaje;
			detalleEmail = servicioGestionEnvios.loadMensaje(idEmail);
			
			// Carga Destinatarios_mensajes
			Integer totalSize;
			int page = getPage("tableMensajesId");
			int inicio = (page - 1) * pageSize;
			PaginatedList<DestinatariosMensajesBean> result;
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());

			if (servicioGestionEnvios.isMultidestinatario(detalleEmail.getMensajeId())) {
				// sacar destinatarios de tabla destinatario_mensaje
				result = servicioGestionEnvios.getDestinatariosMensajesMultidestinatario(inicio, (export) ? -1
						: Integer.parseInt(properties.getProperty(GestionEnviosAction.GENERALES_PAGESIZEM, "20")), detalleEmail.getMensajeId());
				totalSize = result.getTotalList();
				resultCount = (totalSize != null) ? totalSize.toString() : "0";
				listaGestionEnviosDestinatariosMensaje = result.getPageList();
			} else {
				result = servicioGestionEnvios.getDestinatariosMensajes(inicio,
						(export) ? -1 : Integer.parseInt(properties.getProperty(GestionEnviosAction.GENERALES_PAGESIZEM, "20")), detalleEmail.getMensajeId());
				totalSize = result.getTotalList();
				resultCount = (totalSize != null) ? totalSize.toString() : "0";
				listaGestionEnviosDestinatariosMensaje = result.getPageList();
			}
			

			return SUCCESS;
		} catch (BusinessException e) {
			logger.error("GestionEnviosAction - loadMensaje:" + e);
			throw new BusinessException(this.getText("errors.action.organismo.loadOrganismo"));
		}
	}

	/**
	 * Load adjunto.
	 *
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	/////MIGRADO
	public String loadAdjunto() throws IOException {
		if (getRequest().getSession().getAttribute(GestionEnviosAction.INFO_USER) == null)
			return GestionEnviosAction.NO_USER;
		// Comprobar primero si se ha cargado alguna vez el fichero
		//AdjuntoEmailBean adjunto = servicioGestionEnvios.loadAdjuntoBean(idAdjunto, idEmail);
		try {
			AdjuntoEmailBean adjunto = servicioGestionEnvios.loadAdjunto(Long.parseLong(idAdjunto), Long.parseLong(idEmail));

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(adjunto.getContenido());

			this.fileInputStream = byteArrayInputStream;
			this.adjuntoDescargable = "attachment;filename=\"" + adjunto.getNombre() + "\"";
		} catch (Exception exc) {
			logger.error("GestionEnviosAction - loadAdjunto:" + exc);
			addActionErrorSession("No se puede descargar el documento seleccionado: " + exc.getMessage());
		}

		return SUCCESS;
	}

	/**
	 * Accion seleccionados.
	 *
	 * @return the string
	 */
	////MIGRADO
	public String accionSeleccionados() {
		try{
		List<TblGestionEnvios> listaEnvios = new ArrayList<>();
		if (getRequest().getSession().getAttribute(GestionEnviosAction.INFO_USER) == null)
			return GestionEnviosAction.NO_USER;
		boolean sw = true;
		boolean algunoEnviado = false;

//		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getRequest().getSession().getServletContext());
//		OperacionMensajesServicePortType operacionMensajesService = (OperacionMensajesServicePortType) applicationContext.getBean("operacionMensajesService");
		ApplicationContext  applicationContext = WebApplicationContextUtils.getWebApplicationContext(getRequest().getSession().getServletContext());
		
		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		ApplicationContextProvider context = ApplicationContextProvider.getInstance();
		
		if (checkDelList != null) {
			for (String id : checkDelList) {
				String[] idArray = id.split("_");
				try {
					if (idArray.length > 1) {
						TblGestionEnvios env = servicioGestionEnvios.getEnvio(idArray[1]);
						listaEnvios = new ArrayList<>();
						listaEnvios.add(env);
					} else {
						listaEnvios = servicioGestionEnvios.getEnviosLote(id);
					}
				} catch (BusinessException e) {
					logger.error("GestionEnviosAction - accionSeleccionados:" + e);
				}
				for (TblGestionEnvios envio : listaEnvios) {

					if (envio != null) {
						Integer tipoPermiso = PlataformaMensajeriaUtil.getMapPermisosAplicaciones(
								PlataformaMensajeriaUtil.getUsuarioLogueado().getUsername(), servicioUsuarioAplicacion).get(
								envio.getAplicacionid().toString());
						if (tipoPermiso != null
								&& tipoPermiso == 2
								|| PlataformaMensajeriaUtil.getRolFromSession(request).equals(
										PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)||(PlataformaMensajeriaUtil.getRolFromSession(request).equals(
												PlataformaMensajeriaUtil.ROL_CAID))) {
							// Tiene permisos lectura escritura sobre el envio
							if (operacionMsg != null && operacionMsg.equals("A")) {
								boolean anulacionCorrecta = false;
								if (envio != null && !envio.getEstado().equals("ENVIADO")) {
									if (idArray.length > 1) {
										anulacionCorrecta = servicioGestionEnvios.anulaEnvio(new Integer(idArray[1]),context);
									} else {
										anulacionCorrecta = servicioGestionEnvios.anulaEnvioLote(new Integer(idArray[0]),context);
										if (anulacionCorrecta){
											addActionMessageSession("Envios seleccionados anulados correctamente");
										}else{
											addActionErrorSession("Ha ocurrido un error anulando los envios, pongase en contacto con el administrador");
										}
										break;
									}
									if (anulacionCorrecta){
										addActionMessageSession("Envios seleccionados anulados correctamente");
									}else{
										addActionErrorSession("Ha ocurrido un error anulando los envios, pongase en contacto con el administrador");
									}
								} else {
									addActionErrorSession("No se puede anular un envio ya realizado");
								}

							} else if (operacionMsg != null && operacionMsg.equals("R")) {
								Boolean reenvioCorrecto = false;
								if (idArray.length > 1) {
									reenvioCorrecto = servicioGestionEnvios.reenviarEnvio(new Integer(idArray[1]),context);
								} else {
									reenvioCorrecto = servicioGestionEnvios.reenviarEnvioLote(new Integer(idArray[0]),context);
									if(reenvioCorrecto){
										addActionMessageSession("Envios seleccionados reenviados correctamente");
									}else{
										addActionErrorSession("Ha ocurrido un error reenviando los envios, pongase en contacto con el administrador");
									}
									break;
								}
								if(reenvioCorrecto!=null){
									if(reenvioCorrecto){
										addActionMessageSession("Envios seleccionados reenviados correctamente");
									}else if(!reenvioCorrecto){
										addActionErrorSession("El mensaje ya ha sido enviado y no se puede reenviar");
									}
								}else{
									addActionErrorSession("Ha ocurrido un error reenviando los envios, pongase en contacto con el administrador");
								}
							}
							algunoEnviado = true;
						} else {
							// No tiene permisos de lectura escritura y no es
							// administrador
							addActionErrorSession(this.getText("plataforma.gestionenvios.permisos.error",
									envio.getAplicacion()));
							sw = false;
						}
					} else {
						sw = false;
					}
				}// del for
			}
		}
		if (!sw && algunoEnviado) {
			addActionMessageSession("Accion realizada sobre los envios en las aplicaciones sobre las que tiene permisos de escritura");
		} else if (!sw && !algunoEnviado) {
			addActionErrorSession("No se ha realizado ninguna accion. Permiso denegado");
		}
		}catch(Exception e){
			logger.error("GestionEnviosAction - accionSeleccionados:" + e);
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
		if (PlataformaMensajeriaUtil.isEmpty(rolUsuario) || PlataformaMensajeriaUtil.isEmptyNumber(idUsuario)) {
			sw = false;
		}
		return sw;
	}
	
	/**
	 * Valid organismo aeat.
	 *
	 * @param organismo the organismo
	 * @param arrayOrganismos the array organismos
	 * @return true, if successful
	 */
	private boolean validOrganismoAeat(String organismo, List<String> arrayOrganismos) {
		boolean sw = false;
		if(arrayOrganismos != null && !arrayOrganismos.isEmpty()){
			if (-1 != search.indexOf('|')){
				organismo = search.substring(0, search.indexOf('|')).trim();
			}else{
				organismo = search;
			}
			
			for(int i=0; i < arrayOrganismos.size(); i++){
				if(arrayOrganismos.get(i).equals(organismo)){
					sw = true;
					break;
				}
			}
		}

		if(!sw){
			addActionErrorSession("El organismo "+organismo + " no se corresponde con el usuario.Permiso denegado");
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
		keys = (ArrayList<AplicacionBean>) servicioAplicacion.getAplicacionesMenu(rolUsuario, idUsuario);
		if (keys != null && !keys.isEmpty()) {
			for (AplicacionBean key : keys) {
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
		keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresYProveedores(rolUsuario, idUsuario);
		if (keys != null && !keys.isEmpty()) {
			for (ServidorBean key : keys) {

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
		if (gestionEnvioBean != null && gestionEnvioBean.getAplicacionId() != null) {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServiciosByAplicacionId(gestionEnvioBean
					.getAplicacionId().intValue());
		} else {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServicios(rolUsuario, idUsuario);
		}
		if (keys != null && !keys.isEmpty()) {
			for (ServicioBean key : keys) {

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
		keys = (ArrayList<EstadoBean>) servicioEstado.getEstados();
		if (keys != null && !keys.isEmpty()) {
			for (EstadoBean key : keys) {

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
		keys = (ArrayList<CanalBean>) servicioCanal.getCanales();
		if (keys != null && !keys.isEmpty()) {
			for (CanalBean key : keys) {
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
	public List<KeyValueObject> getComboPageSize() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option10 = new KeyValueObject("10", "10");
		KeyValueObject option20 = new KeyValueObject("20", "20");
		KeyValueObject option50 = new KeyValueObject("50", "50");
		KeyValueObject option100 = new KeyValueObject("100", "100");
		result.add(option10);
		result.add(option20);
		result.add(option50);
		result.add(option100);

		return result;

	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	///MIGRADO
	@Override
	public void prepare() throws Exception {
		pageSize = (Integer) getRequest().getAttribute(properties.getProperty(GestionEnviosAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null));
		if (null == pageSize){
			pageSize = Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
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
		this.comboAplicaciones = new ArrayList<KeyValueObject>(comboAplicaciones);
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
	 * Obtener lista gestion envios.
	 *
	 * @return lista gestion envios
	 */
	public List<GestionEnvioBean> getListaGestionEnvios() {
		return new ArrayList<GestionEnvioBean>(listaGestionEnvios);
	}

	/**
	 * Modificar lista gestion envios.
	 *
	 * @param listaGestionEnvios new lista gestion envios
	 */
	public void setListaGestionEnvios(List<GestionEnvioBean> listaGestionEnvios) {
		this.listaGestionEnvios = new ArrayList<GestionEnvioBean>(listaGestionEnvios);
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
	 * Obtener gestion envio bean.
	 *
	 * @return gestion envio bean
	 */
	public GestionEnvioBean getGestionEnvioBean() {
		return gestionEnvioBean;
	}

	/**
	 * Modificar gestion envio bean.
	 *
	 * @param gestionEnvioBean new gestion envio bean
	 */
	public void setGestionEnvioBean(GestionEnvioBean gestionEnvioBean) {
		this.gestionEnvioBean = gestionEnvioBean;
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
	public DetalleEnvioBean getDetalleEmail() {
		return detalleEmail;
	}

	/**
	 * Modificar detalle email.
	 *
	 * @param detalleEmail new detalle email
	 */
	public void setDetalleEmail(DetalleEnvioBean detalleEmail) {
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
	 * Obtener id peticion.
	 *
	 * @return id peticion
	 */
	public String getIdPeticion() {
		return idPeticion;
	}

	/**
	 * Modificar id peticion.
	 *
	 * @param idPeticion new id peticion
	 */
	public void setIdPeticion(String idPeticion) {
		this.idPeticion = idPeticion;
	}

	/**
	 * Obtener lista gestion envios mensajes.
	 *
	 * @return lista gestion envios mensajes
	 */
	public List<MensajeBean> getListaGestionEnviosMensajes() {
		return listaGestionEnviosMensajes;
	}

	/**
	 * Modificar lista gestion envios mensajes.
	 *
	 * @param listaGestionEnviosMensajes new lista gestion envios mensajes
	 */
	public void setListaGestionEnviosMensajes(List<MensajeBean> listaGestionEnviosMensajes) {
		this.listaGestionEnviosMensajes = listaGestionEnviosMensajes;
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
	 * Obtener detalle mensaje.
	 *
	 * @return detalle mensaje
	 */
	public GestionEnvioBean getDetalleMensaje() {
		return detalleMensaje;
	}

	/**
	 * Modificar detalle mensaje.
	 *
	 * @param detalleMensaje new detalle mensaje
	 */
	public void setDetalleMensaje(GestionEnvioBean detalleMensaje) {
		this.detalleMensaje = detalleMensaje;
	}

	/**
	 * Obtener lista gestion envios destinatarios mensaje.
	 *
	 * @return lista gestion envios destinatarios mensaje
	 */
	public List<DestinatariosMensajesBean> getListaGestionEnviosDestinatariosMensaje() {
		return listaGestionEnviosDestinatariosMensaje;
	}

	/**
	 * Modificar lista gestion envios destinatarios mensaje.
	 *
	 * @param listaGestionEnviosDestinatariosMensaje new lista gestion envios destinatarios mensaje
	 */
	public void setListaGestionEnviosDestinatariosMensaje(
			List<DestinatariosMensajesBean> listaGestionEnviosDestinatariosMensaje) {
		this.listaGestionEnviosDestinatariosMensaje = listaGestionEnviosDestinatariosMensaje;
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
	 * Obtener destinatarios mensajes.
	 *
	 * @return destinatarios mensajes
	 */
	public DestinatariosMensajesBean getDestinatariosMensajes() {
		return destinatariosMensajes;
	}

	/**
	 * Modificar destinatarios mensajes.
	 *
	 * @param destinatariosMensajes new destinatarios mensajes
	 */
	public void setDestinatariosMensajes(DestinatariosMensajesBean destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}

	/**
	 * Obtener lista historicos mensaje.
	 *
	 * @return lista historicos mensaje
	 */
	public List<HistoricoBean> getListaHistoricosMensaje() {
		return listaHistoricosMensaje;
	}

	/**
	 * Modificar lista historicos mensaje.
	 *
	 * @param listaHistoricosMensaje new lista historicos mensaje
	 */
	public void setListaHistoricosMensaje(List<HistoricoBean> listaHistoricosMensaje) {
		this.listaHistoricosMensaje = listaHistoricosMensaje;
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
	 * Obtener aa data.
	 *
	 * @return aa data
	 */
	public List<ArrayList<String>> getAaData() {
		return aaData;
	}

	/**
	 * Modificar aa data.
	 *
	 * @param aaData new aa data
	 */
	public void setAaData(List<ArrayList<String>> aaData) {
		this.aaData = aaData;
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
	 * Devuelve el servicio de gestion de envios.
	 *
	 * @return servicio gestion envios
	 */
	public ServicioGestionEnvios getServicioGestionEnvios() {
		return servicioGestionEnvios;
	}

	/**
	 * Modificar servicio gestion envios.
	 *
	 * @param servicioGestionEnvios new servicio gestion envios
	 */
	public void setServicioGestionEnvios(ServicioGestionEnvios servicioGestionEnvios) {
		this.servicioGestionEnvios = servicioGestionEnvios;
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
	 * Obtener search.
	 *
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * Modificar search.
	 *
	 * @param search the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}


}
