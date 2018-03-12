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
 * @version 1.0
 * @author i-nercya
 * 
 */
@Controller("gestionEnvioAction")
@Scope("prototype")
public class GestionEnviosAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	private static final String GENERALES_PAGESIZEM = "generales.PAGESIZEM";

	private static final String GENERALES_REQUEST_ATTRIBUTE_PAGESIZE = "generales.REQUEST_ATTRIBUTE_PAGESIZE";

	private static final String TABLE_ID = "tableId";

	private static final String INFO_USER = "infoUser";

	private static final String NO_USER = "noUser";

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(GestionEnviosAction.class);

	@Resource(name = "servicioAplicacionImpl")
	private transient ServicioAplicacion servicioAplicacion;

	@Resource(name = "servicioServidorImpl")
	private transient ServicioServidor servicioServidor;

	@Resource(name = "servicioServicioImpl")
	private transient ServicioServicio servicioServicio;

	@Resource(name = "servicioEstadoImpl")
	private transient ServicioEstado servicioEstado;

	@Resource(name = "servicioCanalImpl")
	private transient ServicioCanal servicioCanal;

	@Resource(name = "servicioGestionEnviosImpl")
	private transient ServicioGestionEnvios servicioGestionEnvios;
	
	@Resource(name = "servicioUsuarioAplicacionImpl")
	private transient ServicioUsuarioAplicacion servicioUsuarioAplicacion;
	
	@Resource(name = "plataformaMensajeriaProperties")
	private transient PlataformaMensajeriaProperties properties;
	
	@Resource(name="ViewMisimManagerImpl")
	private transient ViewMisimManager viewMisimManager;
	
	@Resource(name="PeticionManagerImpl")
	private transient PeticionManager peticionManager;
	
	@Resource
	private transient CifradoService cifradoService;
	
	private GestionEnvioBean gestionEnvioBean;
	private DetalleEnvioBean detalleEmail;
	private DetalleLoteBean detalleLote;
	private GestionEnvioBean detalleMensaje;
	private DestinatariosMensajesBean destinatariosMensajes;

	private List<GestionEnvioBean> listaGestionEnvios = null;
	private List<MensajeBean> listaGestionEnviosMensajes = null;
	private List<DestinatariosMensajesBean> listaGestionEnviosDestinatariosMensaje = null;
	private List<HistoricoBean> listaHistoricosMensaje = null;
	private List<ViewMisimBean> listaIntercambiosMisim = null;

	transient List<KeyValueObject> comboAplicaciones = new ArrayList<>();
	transient List<KeyValueObject> comboServidores = new ArrayList<>();
	transient List<KeyValueObject> comboServicios = new ArrayList<>();
	transient List<KeyValueObject> comboEstados = new ArrayList<>();
	transient List<KeyValueObject> comboCanales = new ArrayList<>();
	transient List<KeyValueObject> comboPageSize = new ArrayList<>();
	private List<ArrayList<String>> aaData;
	private String[] checkDelList;

	private String resultCount;
	private String idEnvio;
	private String idAdjunto;
	private String idEmail;
	private String idLote;
	private String idPeticion;
	private String idMensaje;
	private String idDestinatariosMensajes;
	private String operacionMsg;
	private String CheckAllS;
	private String vistaEnviosIdSelected;
	private String adjuntoDescargable;
	private String search;
	private InputStream fileInputStream;
	private Integer pageSize = 20;
	
	public static final String FORMATO_FECHA_TITULO_AUDITORIA = "yyyyMMdd HHmmss";
	public static final String TIPO_FICHERO = "xml";

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
			if (null != gestionEnvioBean && search != null && search.length() > 0) {
				if (-1 != search.indexOf('|')){
					gestionEnvioBean.setCodOrganismo(search.substring(0,search.indexOf('|')).trim());
				}else{
					gestionEnvioBean.setCodOrganismo(search);
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
					: Integer.parseInt(properties.getProperty("generales.PAGESIZEM", "20")), Long.valueOf(idLote));
			totalSize = result.getTotalList();
			resultCount = (totalSize != null) ? totalSize.toString() : "0";
			listaIntercambiosMisim = result.getPageList();
			
			return SUCCESS;

		} catch (BusinessException e) {
			logger.error("GestionEnviosAction - loadMisim:" + e);
			throw new BusinessException(this.getText("errors.action.gestionEnvios.loadMisim"));
		}
	}
	
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
										PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)) {
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
	 * Comprueba si hay un usuario logueado y con un rol definido
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
	 * 
	 * @return
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
	 * 
	 * @return
	 * @throws BusinessException
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
	 * 
	 * @return
	 * @throws BusinessException
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
	 * 
	 * @return
	 * @throws BusinessException
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
	 * 
	 * @return
	 * @throws BusinessException
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

	///MIGRADO
	@Override
	public void prepare() throws Exception {
		pageSize = (Integer) getRequest().getAttribute(properties.getProperty(GestionEnviosAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null));
		if (null == pageSize){
			pageSize = Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		}
	}
	/**
	 * 
	 * @return
	 */
	public ServicioAplicacion getServicioAplicacion() {
		return servicioAplicacion;
	}

	public void setServicioAplicacion(ServicioAplicacion servicioAplicacion) {
		this.servicioAplicacion = servicioAplicacion;
	}

	public void setComboAplicaciones(List<KeyValueObject> comboAplicaciones) {
		this.comboAplicaciones = new ArrayList<KeyValueObject>(comboAplicaciones);
	}

	/**
	 * 
	 * @return
	 */
	public String getResultCount() {
		return resultCount;
	}

	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	/**
	 * 
	 * @return
	 */
	public ServicioCanal getServicioCanal() {
		return servicioCanal;
	}

	public void setServicioCanal(ServicioCanal servicioCanal) {
		this.servicioCanal = servicioCanal;
	}

	/**
	 * 
	 * @return
	 */
	public ServicioEstado getServicioEstado() {
		return servicioEstado;
	}

	public void setServicioEstado(ServicioEstado servicioEstado) {
		this.servicioEstado = servicioEstado;
	}

	/**
	 * 
	 * @return
	 */
	public List<GestionEnvioBean> getListaGestionEnvios() {
		return new ArrayList<GestionEnvioBean>(listaGestionEnvios);
	}

	public void setListaGestionEnvios(List<GestionEnvioBean> listaGestionEnvios) {
		this.listaGestionEnvios = new ArrayList<GestionEnvioBean>(listaGestionEnvios);
	}

	/**
	 * 
	 * @return
	 */
	public ServicioServidor getServicioServidor() {
		return servicioServidor;
	}

	public void setServicioServidor(ServicioServidor servicioServidor) {
		this.servicioServidor = servicioServidor;
	}

	/**
	 * 
	 * @return
	 */
	public ServicioServicio getServicioServicio() {
		return servicioServicio;
	}

	public void setServicioServicio(ServicioServicio servicioServicio) {
		this.servicioServicio = servicioServicio;
	}

	public void setComboServidores(List<KeyValueObject> comboServidores) {
		this.comboServidores = new ArrayList<>(comboServidores);
	}

	public void setComboServicios(List<KeyValueObject> comboServicios) {
		this.comboServicios = new ArrayList<>(comboServicios);
	}

	public void setComboEstados(List<KeyValueObject> comboEstados) {
		this.comboEstados = new ArrayList<>(comboEstados);
	}

	public void setComboCanales(List<KeyValueObject> comboCanales) {
		this.comboCanales = new ArrayList<>(comboCanales);
	}

	/**
	 * 
	 * @return
	 */
	public GestionEnvioBean getGestionEnvioBean() {
		return gestionEnvioBean;
	}

	public void setGestionEnvioBean(GestionEnvioBean gestionEnvioBean) {
		this.gestionEnvioBean = gestionEnvioBean;
	}

	/**
	 * 
	 * @return
	 */
	public String getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio(String idEnvio) {
		this.idEnvio = idEnvio;
	}

	/**
	 * 
	 * @return
	 */
	public DetalleEnvioBean getDetalleEmail() {
		return detalleEmail;
	}

	public void setDetalleEmail(DetalleEnvioBean detalleEmail) {
		this.detalleEmail = detalleEmail;
	}

	public String[] getCheckDelList() {
		return checkDelList;
	}

	public void setCheckDelList(String[] checkDelList) {
		this.checkDelList = checkDelList;
	}

	public String getCheckAllS() {
		return CheckAllS;
	}

	public void setCheckAllS(String checkAllS) {
		CheckAllS = checkAllS;
	}

	public String getVistaEnviosIdSelected() {
		return vistaEnviosIdSelected;
	}

	public void setVistaEnviosIdSelected(String vistaEnviosIdSelected) {
		this.vistaEnviosIdSelected = vistaEnviosIdSelected;
	}

	public DetalleLoteBean getDetalleLote() {
		return detalleLote;
	}

	public void setDetalleLote(DetalleLoteBean detalleLote) {
		this.detalleLote = detalleLote;
	}

	public String getIdLote() {
		return idLote;
	}

	public void setIdLote(String idLote) {
		this.idLote = idLote;
	}
	
	public String getIdPeticion() {
		return idPeticion;
	}

	public void setIdPeticion(String idPeticion) {
		this.idPeticion = idPeticion;
	}

	public List<MensajeBean> getListaGestionEnviosMensajes() {
		return listaGestionEnviosMensajes;
	}

	public void setListaGestionEnviosMensajes(List<MensajeBean> listaGestionEnviosMensajes) {
		this.listaGestionEnviosMensajes = listaGestionEnviosMensajes;
	}

	public String getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}

	public GestionEnvioBean getDetalleMensaje() {
		return detalleMensaje;
	}

	public void setDetalleMensaje(GestionEnvioBean detalleMensaje) {
		this.detalleMensaje = detalleMensaje;
	}

	public List<DestinatariosMensajesBean> getListaGestionEnviosDestinatariosMensaje() {
		return listaGestionEnviosDestinatariosMensaje;
	}

	public void setListaGestionEnviosDestinatariosMensaje(
			List<DestinatariosMensajesBean> listaGestionEnviosDestinatariosMensaje) {
		this.listaGestionEnviosDestinatariosMensaje = listaGestionEnviosDestinatariosMensaje;
	}

	public String getIdDestinatariosMensajes() {
		return idDestinatariosMensajes;
	}

	public void setIdDestinatariosMensajes(String idDestinatariosMensajes) {
		this.idDestinatariosMensajes = idDestinatariosMensajes;
	}

	public DestinatariosMensajesBean getDestinatariosMensajes() {
		return destinatariosMensajes;
	}

	public void setDestinatariosMensajes(DestinatariosMensajesBean destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}

	public List<HistoricoBean> getListaHistoricosMensaje() {
		return listaHistoricosMensaje;
	}

	public void setListaHistoricosMensaje(List<HistoricoBean> listaHistoricosMensaje) {
		this.listaHistoricosMensaje = listaHistoricosMensaje;
	}

	public List<ViewMisimBean> getListaIntercambiosMisim() {
		return listaIntercambiosMisim;
	}

	public void setListaIntercambiosMisim(List<ViewMisimBean> listaIntercambiosMisim) {
		this.listaIntercambiosMisim = listaIntercambiosMisim;
	}

	public List<ArrayList<String>> getAaData() {
		return aaData;
	}

	public void setAaData(List<ArrayList<String>> aaData) {
		this.aaData = aaData;
	}

	public String getOperacionMsg() {
		return operacionMsg;
	}

	public void setOperacionMsg(String operacionMsg) {
		this.operacionMsg = operacionMsg;
	}

	public String getIdEmail() {
		return idEmail;
	}

	public void setIdEmail(String idEmail) {
		this.idEmail = idEmail;
	}

	public String getAdjuntoDescargable() {
		return adjuntoDescargable;
	}

	public void setAdjuntoDescargable(String adjuntoDescargable) {
		this.adjuntoDescargable = adjuntoDescargable;
	}

	public String getIdAdjunto() {
		return idAdjunto;
	}

	public void setIdAdjunto(String idAdjunto) {
		this.idAdjunto = idAdjunto;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	/**
	 * Devuelve el servicio de gestion de envios
	 * 
	 * @return
	 */
	public ServicioGestionEnvios getServicioGestionEnvios() {
		return servicioGestionEnvios;
	}

	public void setServicioGestionEnvios(ServicioGestionEnvios servicioGestionEnvios) {
		this.servicioGestionEnvios = servicioGestionEnvios;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}


}
