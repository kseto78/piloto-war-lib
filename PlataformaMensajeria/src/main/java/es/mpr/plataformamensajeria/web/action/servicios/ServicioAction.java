package es.mpr.plataformamensajeria.web.action.servicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

import de.brendamour.jpasskit.PKField;
import de.brendamour.jpasskit.PassbookGenerator;
import de.brendamour.jpasskit.enums.PKTextAlignment;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.lotes.PkFieldsXMLBean;
import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.beans.OrganismoBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;
import es.mpr.plataformamensajeria.beans.ReceptorSMSBean;
import es.mpr.plataformamensajeria.beans.ResultOptions;
import es.mpr.plataformamensajeria.beans.SelectOption;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServicioOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidorPushBean;
import es.mpr.plataformamensajeria.beans.ServidorWebPushBean;
import es.mpr.plataformamensajeria.beans.ServidoresOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioCanal;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioReceptorSMS;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorPush;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorWebPush;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuariosWebPush;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los servicios.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n,
 * borrado y listado de los Servicios
 * 
 * @author i-nercya
 * 
 */
@Controller("serviciosAction")
@Scope("prototype")
public class ServicioAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(ServicioAction.class);
	
	/**  reloadable resource bundle message source. */
	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	/**  servicio organismo. */
	@Resource(name = "servicioOrganismoImpl")
	private ServicioOrganismo servicioOrganismo;

	/**  servicio servicio. */
	@Resource(name = "servicioServicioImpl")
	private ServicioServicio servicioServicio;

	/**  servicio aplicacion. */
	@Resource(name = "servicioAplicacionImpl")
	private ServicioAplicacion servicioAplicacion;

	/**  servicio proveedor SMS. */
	@Resource(name = "servicioProveedorSMSImpl")
	private ServicioProveedorSMS servicioProveedorSMS;

	/**  servicio receptor SMS. */
	@Resource(name = "servicioReceptorSMSImpl")
	private ServicioReceptorSMS servicioReceptorSMS;

	/**  servicio servidor. */
	@Resource(name = "servicioServidorImpl")
	private ServicioServidor servicioServidor;

	/**  servicio servidor push. */
	@Resource(name = "servicioServidorPushImpl")
	private ServicioServidorPush servicioServidorPush;
	
	/**  servicio servidor web push. */
	@Resource(name = "servicioServidorWebPushImpl")
	private ServicioServidorWebPush servicioServidorWebPush;

	/**  servicio canal. */
	@Resource(name = "servicioCanalImpl")
	private ServicioCanal servicioCanal;

	/**  servicio planificacion. */
	@Resource(name = "servicioPlanificacionImpl")
	private ServicioPlanificacion servicioPlanificacion;
	
	/**  servicio usuarios web push. */
	@Resource(name = "servicioUsuariosWebPushImpl")
	private ServicioUsuariosWebPush servicioUsuariosWebPush;

	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;

	/**  servidor servicio. */
	private ServidoresServiciosBean servidorServicio;
	
	/**  servicio organismos. */
	private ServicioOrganismosBean servicioOrganismos;
	
	/**  servidor organismos. */
	private ServidoresOrganismosBean servidorOrganismos;
	
	/**  planificacion servidor. */
	private PlanificacionBean planificacionServidor;
	
	/**  organismo. */
	private OrganismoBean organismo;
	
	/**  servicio. */
	private ServicioBean servicio;

	/**  send mail service. */
	private SendMailService sendMailService = new SendMailService();

	/**  combo aplicaciones. */
	List<KeyValueObject> comboAplicaciones = new ArrayList<KeyValueObject>();
	
	/**  combo canales. */
	List<KeyValueObject> comboCanales = new ArrayList<KeyValueObject>();
	
	/**  combo configuraciones. */
	List<KeyValueObject> comboConfiguraciones = new ArrayList<KeyValueObject>();
	
	/**  combo configuraciones plan. */
	List<KeyValueObject> comboConfiguracionesPlan = new ArrayList<KeyValueObject>();
	
	/**  combo servicio organismos. */
	List<KeyValueObject> comboServicioOrganismos = new ArrayList<KeyValueObject>();

	/**  lista servicios. */
	public List<ServicioBean> listaServicios = null;
	
	/**  lista planificaciones servicio. */
	public List<PlanificacionBean> listaPlanificacionesServicio = null;
	
	/**  lista servidores servicios. */
	public List<ServidoresServiciosBean> listaServidoresServicios = null;
	
	/**  lista servicio organismos. */
	public List<ServicioOrganismosBean> listaServicioOrganismos = null;
	
	/**  lista servidores detalle. */
	private List<ServidorBean> listaServidoresDetalle = new ArrayList<ServidorBean>();
	
	/**  lista proveedor SMS detalle. */
	private List<ProveedorSMSBean> listaProveedorSMSDetalle = new ArrayList<ProveedorSMSBean>();

	/**  check del list. */
	private String[] checkDelList;
	
	/**  check del list planificaciones. */
	private String[] checkDelListPlanificaciones;
	
	/**  check del list servidor servicios. */
	private String[] checkDelListServidorServicios;
	
	/**  check del list servicios organismos. */
	private String[] checkDelListServiciosOrganismos;

	/**  servicio servidor id. */
	private String servicioServidorId;
	
	/**  id aplicacion. */
	private String idAplicacion;
	
	/**  id planificacion. */
	private String idPlanificacion;
	
	/**  id organismo. */
	private String idOrganismo;
	
	/**  id servicio organismo. */
	private String idServicioOrganismo;
	
	/**  servidor servicio id. */
	private String servidorServicioId;
	
	/**  servicio organismo id. */
	private String servicioOrganismoId;
	
	/**  id servicio. */
	private String idServicio;
	
	/**  parametro servidor id. */
	private String parametroServidorId;
	
	/**  planificacion id. */
	private String planificacionId;
	
	/**  search. */
	private String search;
	
	/**  logo. */
	private String logo;
	
	/**  logo 64. */
	private String logo64;
	
	/**  background. */
	private String background;
	
	/**  icon. */
	private String icon;
	
	/**  background 64. */
	private String background64;
	
	/**  icon 64. */
	private String icon64;
	
	/**  vapid public key. */
	private String vapidPublicKey;
	
	/**  vapid private key. */
	private String vapidPrivateKey;

	/**  new activo. */
	private String newActivo;
	
	/**  new premium. */
	private String newPremium;
	
	/**  new plataforma android. */
	private String newPlataformaAndroid;
	
	/**  new plataformai OS. */
	private String newPlataformaiOS;
	
	/**  canal disabled. */
	private String canalDisabled = null;
	
	/**  readonly. */
	private String readonly = "false";
	
	/**  check password. */
	private String checkPassword;
	
	/**  new informe activo. */
	private String newInformeActivo;
	
	/**  new agrupacion estado. */
	private String newAgrupacionEstado;
	
	/**  new agrupacion cod org. */
	private String newAgrupacionCodOrg;
	
	/**  new agrupacion cod sia. */
	private String newAgrupacionCodSia;
	
	/**  new agrupacion cod org pagador. */
	private String newAgrupacionCodOrgPagador;
	
	/**  new informes destinatarios. */
	private String newInformesDestinatarios;
	
	/**  recovery. */
	private String recovery = "";
	
	/**  activo. */
	private String activo = "";
	
	/**  result count. */
	private String resultCount;
	
	/**  json. */
	String json;
	
	/**  is multiorganismo. */
	private boolean isMultiorganismo;
	
	/**  new historificacion. */
	private Integer newHistorificacion = 1;
	
	/**  new conservacion. */
	private Integer newConservacion = 1;
	
	/**  session. */
	@SuppressWarnings("rawtypes")
	private Map session;

	/**  request attribute totalsize. */
	private String requestAttributeTotalsize;
	
	/**  request attribute pagesize. */
	private String requestAttributePagesize;
	
	/**  pagesize. */
	private Integer pagesize;
	
	/**  canal SMTP id. */
	private Integer canalSMTPId;
	
	/**  canal SMS id. */
	private Integer canalSMSId;
	
	/**  canal recepcion SMS id. */
	private Integer canalRecepcionSMSId;
	
	/**  canal servidor push id. */
	private Integer canalServidorPushId;
	
	/**  canal servidor web push id. */
	private Integer canalServidorWebPushId;
	
	/**  valor maximo predefinido historificacion. */
	private Integer valorMaximoPredefinidoHistorificacion;
	
	/**  valor maximo predefinidor conservacion. */
	private Integer valorMaximoPredefinidorConservacion;
	
	/**  valor 1 historificacion. */
	private Integer valor1Historificacion;
	
	/**  valor 2 historificacion. */
	private Integer valor2Historificacion;
	
	/**  valor 3 historificacion. */
	private Integer valor3Historificacion;
	
	/**  valor 1 conservacion. */
	private Integer valor1Conservacion;
	
	/**  valor 2 conservacion. */
	private Integer valor2Conservacion;
	
	/**  valor 3 conservacion. */
	private Integer valor3Conservacion;
	
	/**  txt recovery. */
	private String txtRecovery;
	
	/**  accion update servicio. */
	private String accionUpdateServicio;
	
	/**  accion id update servicio. */
	private Long accionIdUpdateServicio;
	
	/**  source update servicio. */
	private String sourceUpdateServicio;
	
	/**  accion update organismo. */
	private String accionUpdateOrganismo;
	
	/**  accion id update organismo. */
	private Long accionIdUpdateOrganismo;
	
	/**  source update organismo. */
	private String sourceUpdateOrganismo;
	
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
	
	/** Constante TIPO_FICHERO. */
	public static final String TIPO_FICHERO = "pkpass";

	/**
	 * New search.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String newSearch() throws BaseException {
		return SUCCESS;
	}

	/**
	 * Search servicios.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	@SkipValidation
	public String searchServicios() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";

		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para
														// ordenar

		if (servicio != null){
			if (servicio.getNombre() != null && servicio.getNombre().length() <= 0){
				servicio.setNombre(null);
			}
			if(servicio.getServicioId() != null && servicio.getServicioId() <= 0){
				servicio.setServicioId(null);
			}
		}

		int inicio = (page - 1) * pagesize;
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServicioBean> result = servicioServicio.getServicios(inicio, (export) ? -1 : pagesize, order,
				columnSort, servicio);
		Integer totalSize = result.getTotalList();

		listaServicios = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(requestAttributeTotalsize, totalSize);

		if (!export) {
			getRequest().setAttribute(requestAttributePagesize, pagesize);
		} else {
			getRequest().setAttribute(requestAttributePagesize, totalSize);
		}

		if (listaServicios != null && !listaServicios.isEmpty()) {
			for (int indice = 0; indice < listaServicios.size(); indice++) {

				ServicioBean servicio = listaServicios.get(indice);
				servicio.setNombre(StringEscapeUtils.escapeHtml(servicio.getNombre()));
				servicio.setAplicacionnombre(org.apache.commons.lang3.StringEscapeUtils.escapeCsv(servicio.getAplicacionnombre()));
				servicio.setDescripcion(StringEscapeUtils.escapeHtml(servicio.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	/**
	 * Ajax load servicios.
	 *
	 * @return the string
	 */
	///MIGRADO
	public String ajaxLoadServicios() {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idAplicacion == null) {
			addFieldErrorSession("Datos incorrectos");
		} else {
			ResultOptions rOptions = new ResultOptions();
			try {
				ArrayList<ServicioBean> listado = new ArrayList<ServicioBean>();
				if (idAplicacion != null && idAplicacion.length() > 0) {
					listado = (ArrayList<ServicioBean>) servicioServicio.getServiciosByAplicacionId(Integer
							.valueOf(idAplicacion));
				} else {
					String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
					Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
					listado = (ArrayList<ServicioBean>) servicioServicio.getServicios(rolUsuario, idUsuario);
				}

				for (ServicioBean servicio : listado) {
					rOptions.getItems()
							.add(new SelectOption(servicio.getServicioId().toString(), servicio.getNombre()));
				}
			} catch (Exception e) {
				logger.error("ServicioAction - ajaxLoadServicios:" + e);
			}
			json = new Gson().toJson(rOptions);
		}
		return SUCCESS;

	}

	/**
	 * Aplicacion select event.
	 *
	 * @return the string
	 */
	///MIGRADO
	public String aplicacionSelectEvent() {

		if (servicio != null) {
			AplicacionBean aplicacion = new AplicacionBean();
			aplicacion.setId(servicio.getAplicacionid());
			try {
				aplicacion = servicioAplicacion.loadAplicacion(aplicacion);
				servicio.setResponsablefuncionalemail(aplicacion.getRespFuncionalEmail());
				servicio.setResponsablefuncionalnombre(aplicacion.getRespFuncionalNombre());
				servicio.setResponsabletecnicoemail(aplicacion.getRespTecnicoEmail());
				servicio.setResponsabletecniconombre(aplicacion.getRespTecnicoNombre());

			} catch (BusinessException e) {
				logger.error("ServicioAction - aplicacionSelectEvent:" + e);
			}

		}
		return SUCCESS;
	}

	/**
	 * Activar multiorganismo select event.
	 *
	 * @return the string
	 */
	///MIGRADO
	public String activarMultiorganismoSelectEvent() {
		boolean sw = true;
		if (servicio != null) {

			try {
				ServicioBean servicioBBDD;
				servicio.setId(Integer.valueOf(idServicio));
				servicioBBDD = servicioServicio.loadServicio(servicio);

				comboConfiguraciones = getComboConfiguracion(servicioBBDD.getCanalid());
				comboConfiguracionesPlan = getComboConfiguracionesPlan(servicioBBDD.getCanalid());
				//comboServicioOrganismos = cargarComboServicioOrganismos();

				listaPlanificacionesServicio = servicioPlanificacion.getPlanificacionesByServicioID(Integer
						.parseInt(idServicio));
				listaServicioOrganismos = servicioServicio.getServicioOrganismo(idServicio);
				listaServidoresServicios = loadServidoresServicio();

				if ((listaPlanificacionesServicio != null && listaPlanificacionesServicio.size() > 0)
						|| (listaServidoresServicios != null && listaServidoresServicios.size() > 0)) {
					canalDisabled = "disable";
				} else {
					canalDisabled = null;
				}

				if (null != servicioBBDD.getMultiorganismo() && servicioBBDD.getMultiorganismo()) {
					if (null != listaServicioOrganismos && listaServicioOrganismos.size() > 0) {
						addFieldErrorSession(this
								.getText("plataforma.servicio.servidorservicio.field.listaOrganismosNoVacia"));
						sw = false;
					}
					servicioBBDD.setMultiorganismo(false);
					servicioBBDD.setIsMultiorganismo("false");

				} else {
					servicioBBDD.setMultiorganismo(true);
					servicioBBDD.setIsMultiorganismo("true");

				}
				if (sw) {
					servicioServicio.updateServicio(servicioBBDD, sourceUpdateServicio, accionUpdateServicio,
							accionIdUpdateServicio);

				}
				servicio = servicioServicio.loadServicio(servicioBBDD);

				// Historificacion
				Integer historificacion = servicio.getHistorificacion();

				if (null != historificacion) {
					switch (historificacion) {
					case 30:
						servicio.setHistorificacion(1);
						break;
					case 60:
						servicio.setHistorificacion(2);
						break;
					case 90:
						servicio.setHistorificacion(3);
						break;
					default:
						servicio.setHistorificacionInput(servicio.getHistorificacion());
						servicio.setHistorificacion(4);
						break;
					}
				} else {
					servicio.setHistorificacion(historificacion);
				}
			} catch (BusinessException e) {
				logger.error("ServicioAction - activarMultiorganismoSelectEvent:" + e);
			}

		}
		return SUCCESS;

	}

	/**
	 * Creates the.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String create() throws BaseException {
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOS", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";

		if (servicio == null) {
			addActionErrorSession(this.getText("plataforma.servicio.create.error"));
			return ERROR;
		} else {
			if (newActivo != null && newActivo.equals("true")) {
				servicio.setActivo(true);
			} else {
				servicio.setActivo(false);
			}
			if (newPremium != null && newPremium.equals("true")) {
				servicio.setPremium(true);
			} else {
				servicio.setPremium(false);
			}
			if (servicio.getCanalid() != null && servicio.getCanalid().equals(Integer.valueOf(canalServidorPushId))) {
				if (newPlataformaAndroid != null && newPlataformaAndroid.equals("true")) {
					servicio.setAndroidplataforma(true);
				} else {
					servicio.setAndroidplataforma(false);
				}
				if (newPlataformaiOS != null && newPlataformaiOS.equals("true")) {
					servicio.setIosplataforma(true);
				} else {
					servicio.setIosplataforma(false);
				}
			}
			if (servicio.getCanalid() != null && servicio.getCanalid().equals(Integer.valueOf(canalServidorWebPushId))) {
				if ((vapidPrivateKey != null && vapidPrivateKey.length() > 0)
						|| (vapidPublicKey != null && vapidPrivateKey.length() > 0)) {
					servicio.setVapidPrivateKey(vapidPrivateKey);
					servicio.setVapidPublicKey(vapidPublicKey);
				}

			}
			// Informes
			if (newInformeActivo != null && newInformeActivo.equals("true")) {
				servicio.setInformesactivo(true);
			} else {
				servicio.setInformesactivo(false);
			}

			if (servicio.getInformesactivo() != null && servicio.getInformesactivo()) {

				if (newInformesDestinatarios != null && !newInformesDestinatarios.isEmpty()) {
					servicio.setInformesdestinatarios(newInformesDestinatarios.trim()); // Eliminamos
																						// los
																						// espacios
				}

				if (newAgrupacionEstado != null && newAgrupacionEstado.equals("true")) {
					servicio.setAgrupacionestado(true);
				} else {
					servicio.setAgrupacionestado(false);
				}

				if (servicio.getCanalid().equals(Integer.valueOf(canalSMTPId))
						|| servicio.getCanalid().equals(Integer.valueOf(canalServidorPushId))) {
					if (newAgrupacionCodOrg != null && newAgrupacionCodOrg.equals("true")) {
						servicio.setAgrupacioncodorg(true);
					} else {
						servicio.setAgrupacioncodorg(false);
					}
					if (newAgrupacionCodSia != null && newAgrupacionCodSia.equals("true")) {
						servicio.setAgrupacioncodsia(true);
					} else {
						servicio.setAgrupacioncodsia(false);
					}
				} else if (servicio.getCanalid().equals(Integer.valueOf(canalSMSId))) {
					if (newAgrupacionCodOrg != null && newAgrupacionCodOrg.equals("true")) {
						servicio.setAgrupacioncodorg(true);
					} else {
						servicio.setAgrupacioncodorg(false);
					}
					if (newAgrupacionCodSia != null && newAgrupacionCodSia.equals("true")) {
						servicio.setAgrupacioncodsia(true);
					} else {
						servicio.setAgrupacioncodsia(false);
					}
					if (newAgrupacionCodOrgPagador != null && newAgrupacionCodOrgPagador.equals("true")) {
						servicio.setAgrupacioncodorgpagador(true);
					} else {
						servicio.setAgrupacioncodorgpagador(false);
					}
				}
			}

			if (newHistorificacion != null) {
				servicio.setHistorificacion(newHistorificacion);
			}
			if (newConservacion != null) {
				servicio.setConservacion(newConservacion);
			}

			// Historificacion
			Integer historificacion = servicio.getHistorificacion();
			if (null != historificacion) {
				switch (historificacion) {
				case 1:
					servicio.setHistorificacion(valor1Historificacion);
					break;
				case 2:
					servicio.setHistorificacion(valor2Historificacion);
					break;
				case 3:
					servicio.setHistorificacion(valor3Historificacion);
					break;
				case 4:
					servicio.setHistorificacion(servicio.getHistorificacionInput());
					break;
				default:
					servicio.setHistorificacion(valor1Historificacion);
					break;
				}
			} else {
				servicio.setHistorificacion(historificacion);
			}

			// Conservacion
			Integer conservacion = servicio.getConservacion();
			if (null != conservacion) {
				switch (conservacion) {
				case 1:
					servicio.setConservacion(valor1Conservacion);
					break;
				case 2:
					servicio.setConservacion(valor2Conservacion);
					break;
				case 3:
					servicio.setConservacion(valor3Conservacion);
					break;
				case 4:
					servicio.setConservacion(servicio.getConservacionInput());
					break;
				default:
					servicio.setConservacion(valor1Conservacion);
					break;
				}

			} else {
				servicio.setConservacion(conservacion);
			}
						
			ServicioBean servicioBean = servicioServicio.createServicioBean(servicio);

			boolean validServicio = false;

			if (servicio != null) {
				validServicio = validServicio(servicioBean);
			}

			if (!validServicio) {
				return ERROR;
			} else {

				Integer idServicio = servicioServicio.newServicio(servicioBean, source, accion, accionId);
				this.idServicio = idServicio.toString();
				try {
					//Se crea una planificacion por defecto
					crearPlanificacionPorDefecto(accion, accionId, source, servicioBean);
				} catch(BusinessException e) {
					addActionErrorSession(this.getText("plataforma.servicio.field.planificacion.error"));
					return ERROR;
				}


				addActionMessageSession(this.getText("plataforma.servicio.create.ok"));
			}
		}
		return SUCCESS;

	}

	/**
	 * Crear planificacion por defecto.
	 *
	 * @param accion the accion
	 * @param accionId the accion id
	 * @param source the source
	 * @param servicioBean the servicio bean
	 * @throws BusinessException the business exception
	 */
	private void crearPlanificacionPorDefecto(String accion, Long accionId, String source, ServicioBean servicioBean)
			throws BusinessException {
		planificacionServidor = new PlanificacionBean();
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PLANIFICACION", null);
		planificacionServidor.setLunes("S");
		planificacionServidor.setMartes("S");
		planificacionServidor.setMiercoles("S");
		planificacionServidor.setJueves("S");
		planificacionServidor.setViernes("S");
		planificacionServidor.setSabado("N");
		planificacionServidor.setDomingo("N");
		if (servicioBean != null && servicioBean.getCanalid() != null && servicioBean.getCanalid().intValue() == 2) {
			planificacionServidor.setTipoPlanificacionId(Integer.valueOf(2));
		} else if (servicioBean != null && servicioBean.getCanalid() != null && servicioBean.getCanalid().intValue() == 1) {
			planificacionServidor.setTipoPlanificacionId(Integer.valueOf(1));
		} else if (servicioBean != null && servicioBean.getCanalid() != null && servicioBean.getCanalid().intValue() == 3) {
			planificacionServidor.setTipoPlanificacionId(Integer.valueOf(3));
		} else if (servicioBean != null && servicioBean.getCanalid() != null && servicioBean.getCanalid().intValue() == 4) {
			planificacionServidor.setTipoPlanificacionId(Integer.valueOf(4));
		}else if (servicioBean != null && servicioBean.getCanalid() != null && servicioBean.getCanalid().intValue() == 5) {
			planificacionServidor.setTipoPlanificacionId(Integer.valueOf(5));
		}	
		if (servicioBean.getServicioId() != null && servicioBean.getServicioId() > 0) {
			planificacionServidor.setServicioId(servicioBean.getServicioId());
		}
		
		planificacionServidor.setServidorId(null);
//		planificacionServidor.setServidorId(801);
		planificacionServidor.setActivo(true);
		planificacionServidor.setFechaCreacion(new Date());
		planificacionServidor.setFechaModificacion(new Date());
		planificacionServidor.setCreadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
		planificacionServidor.setHoraDesde("00:00");
		planificacionServidor.setHoraHasta("00:30");
		servicioPlanificacion.newPlanificacion(planificacionServidor, source, accion, accionId, descripcion);
	}

	/**
	 * Creates the servicio aplicacion.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String createServicioAplicacion() throws BaseException {
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOS", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (servicio == null) {
			addActionErrorSession(this.getText("plataforma.servicio.create.error"));
			return ERROR;
		} else {

			if (servicio.getCanalid() != null && servicio.getCanalid().equals(Integer.valueOf(canalServidorPushId))) {
				if (newPlataformaAndroid != null && newPlataformaAndroid.equals("true")) {
					servicio.setAndroidplataforma(true);
				} else {
					servicio.setAndroidplataforma(false);
				}
				if (newPlataformaiOS != null && newPlataformaiOS.equals("true")) {
					servicio.setIosplataforma(true);
				} else {
					servicio.setIosplataforma(false);
				}
			}

			// El servicio se crea desactivado y pendiente de aprobacion
			servicio.setActivo(false);
			servicio.setPendienteaprobacion(true);
			servicio.setPremium(false);

			// Informes
			if (newInformeActivo != null && newInformeActivo.equals("true")) {
				servicio.setInformesactivo(true);
			} else {
				servicio.setInformesactivo(false);
			}

			if (servicio.getInformesactivo() != null && servicio.getInformesactivo()) {

				if (newInformesDestinatarios != null && !newInformesDestinatarios.isEmpty()) {
					servicio.setInformesdestinatarios(newInformesDestinatarios.trim()); // Eliminamos
																						// los
																						// espacios
				}

				if (newAgrupacionEstado != null && newAgrupacionEstado.equals("true")) {
					servicio.setAgrupacionestado(true);
				} else {
					servicio.setAgrupacionestado(false);
				}

				if (servicio.getCanalid().equals(Integer.valueOf(canalSMTPId))
						|| servicio.getCanalid().equals(Integer.valueOf(canalServidorPushId))) {
					if (newAgrupacionCodOrg != null && newAgrupacionCodOrg.equals("true")) {
						servicio.setAgrupacioncodorg(true);
					} else {
						servicio.setAgrupacioncodorg(false);
					}
					if (newAgrupacionCodSia != null && newAgrupacionCodSia.equals("true")) {
						servicio.setAgrupacioncodsia(true);
					} else {
						servicio.setAgrupacioncodsia(false);
					}
				} else if (servicio.getCanalid().equals(Integer.valueOf(canalSMSId))) {
					if (newAgrupacionCodOrg != null && newAgrupacionCodOrg.equals("true")) {
						servicio.setAgrupacioncodorg(true);
					} else {
						servicio.setAgrupacioncodorg(false);
					}
					if (newAgrupacionCodSia != null && newAgrupacionCodSia.equals("true")) {
						servicio.setAgrupacioncodsia(true);
					} else {
						servicio.setAgrupacioncodsia(false);
					}
					if (newAgrupacionCodOrgPagador != null && newAgrupacionCodOrgPagador.equals("true")) {
						servicio.setAgrupacioncodorgpagador(true);
					} else {
						servicio.setAgrupacioncodorgpagador(false);
					}
				}
			}

			if (newHistorificacion != null) {
				servicio.setHistorificacion(newHistorificacion);
				if (!newHistorificacion.equals(4)) {
					servicio.setHistorificacionInput(null);
				}
			}
			if (newConservacion != null) {
				servicio.setConservacion(newConservacion);
				if (!newConservacion.equals(4)) {
					servicio.setConservacionInput(null);
				}
			}

			// Historificacion
			Integer historificacion = servicio.getHistorificacion();

			if (null != historificacion) {
				switch (historificacion) {
				case 1:
					servicio.setHistorificacion(valor1Historificacion);
					break;
				case 2:
					servicio.setHistorificacion(valor2Historificacion);
					break;
				case 3:
					servicio.setHistorificacion(valor3Historificacion);
					break;
				case 4:
					servicio.setHistorificacion(servicio.getHistorificacionInput());
					break;
				default:
					servicio.setHistorificacion(valor1Historificacion);
					break;
				}

			} else {
				servicio.setHistorificacion(null);
			}
			// Conservacion
			Integer conservacion = servicio.getConservacion();
			if (conservacion != null) {
				switch (conservacion) {
				case 1:
					servicio.setConservacion(valor1Conservacion);
					break;
				case 2:
					servicio.setConservacion(valor2Conservacion);
					break;
				case 3:
					servicio.setConservacion(valor3Conservacion);
					break;
				case 4:
					servicio.setConservacion(servicio.getConservacionInput());
					break;
				default:
					servicio.setConservacion(valor1Conservacion);
					break;
				}

			} else {
				servicio.setConservacion(null);
			}

			ServicioBean servicioBean = servicioServicio.createServicioBean(servicio);

			boolean validServicio = false;

			if (servicio != null) {
				validServicio = validServicio(servicioBean);
			}

			if (!validServicio) {
				return ERROR;
			} else {

				Integer idServicio = servicioServicio.newServicio(servicioBean, source, accion, accionId);
				this.idServicio = idServicio.toString();

				addActionMessageSession(this.getText("plataforma.servicio.create.ok"));

				// Se envia un correo informativo a la lista de correo
				try {
					List<AplicacionBean> listaAplicaciones = servicioAplicacion.getAplicaciones();
					if (null != listaAplicaciones && !listaAplicaciones.isEmpty()) {
						for (AplicacionBean aplicacion : listaAplicaciones) {
							if (aplicacion.getAplicacionId().equals(servicio.getAplicacionid())) {
								sendMailService.initServicio(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombre(),
										aplicacion.getNombre(), idServicio.toString(), properties);
								break;
							}
						}
					}
				} catch (ServletException e) {
					logger.error("ServicioAction - createServicioAplicacion:" + e);
					return SUCCESS;
				}

			}
		}
		return SUCCESS;

	}

	/**
	 * Valid servicio.
	 *
	 * @param servicio the servicio
	 * @return true, if successful
	 */
	///MIGRADO
	private boolean validServicio(ServicioBean servicio) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmptyNumber(servicio.getAplicacionid())) {
			addActionErrorSession(this.getText("plataforma.servicio.field.aplicacionId.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmptyNumber(servicio.getCanalid())) {
			addActionErrorSession(this.getText("plataforma.servicio.field.canal.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(servicio.getNombre())) {
			addActionErrorSession(this.getText("plataforma.servicio.field.nombre.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmptyNumber(servicio.getNmaxenvios())) {
			addActionErrorSession(this.getText("plataforma.servicio.field.nmaxenvios.error"));
			sw = false;
		} else if (!PlataformaMensajeriaUtil.isNumber(servicio.getNmaxenvios())) {
			addActionErrorSession(this.getText("plataforma.servicio.field.nmaxenvios.format.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmptyNumber(servicio.getHistorificacion())) {
			addActionErrorSession(this.getText("plataforma.servicio.field.historificacion.empty"));
			sw = false;
		} else if (servicio.getHistorificacion() > valorMaximoPredefinidoHistorificacion && servicio.getMotivohistorificacion().isEmpty()) {
			addActionErrorSession(this.getText("plataforma.servicio.field.motivoHistorificacion.empty"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmptyNumber(servicio.getConservacion())) {
			addActionErrorSession(this.getText("plataforma.servicio.field.conservacion.empty"));
			sw = false;
		} else if (servicio.getConservacion() > valorMaximoPredefinidorConservacion && servicio.getMotivoconservacion().isEmpty()) {
			addActionErrorSession(this.getText("plataforma.servicio.field.motivoConservacion.empty"));
			sw = false;
		}

		if (servicio.getCanalid() != null && servicio.getCanalid().intValue() == 3) {
			if (PlataformaMensajeriaUtil.isEmpty(servicio.getNombreloteenvio())) {
				addActionErrorSession(this.getText("plataforma.servicio.field.nombreloteenvio.error"));
				sw = false;
			}
			if (PlataformaMensajeriaUtil.isEmpty(servicio.getEndpoint())) {
				addActionErrorSession(this.getText("plataforma.servicio.field.endpoint.error"));
				sw = false;
			}

		}
		if (servicio.getCanalid() != null && servicio.getCanalid().intValue() == 4) {
			if (!servicio.getAndroidplataforma() && !servicio.getIosplataforma()) {
				addActionErrorSession(this.getText("plataforma.servicio.field.plataforma.error"));
				sw = false;
			} else {
				if (servicio.getAndroidplataforma() && PlataformaMensajeriaUtil.isEmpty(servicio.getGcmprojectkey())) {
					addActionErrorSession(this.getText("plataforma.servicio.field.plataformaAndroid.error"));
					sw = false;
				}
				if (servicio.getIosplataforma() && (PlataformaMensajeriaUtil.isEmpty(servicio.getApnsrutacertificado()) || PlataformaMensajeriaUtil.isEmpty(servicio.getApnspasswordcertificado()))) {
					addActionErrorSession(this.getText("plataforma.servicio.field.plataformaiOS.error"));
					sw = false;
				}
			}
			if (PlataformaMensajeriaUtil.isEmptyNumber(servicio.getBadge())) {
				addActionErrorSession(this.getText("plataforma.servicio.field.badge.error"));
				sw = false;
			}
		}
		if (servicio.getCanalid() != null && servicio.getCanalid().intValue() == 5) {
			if (PlataformaMensajeriaUtil.isEmpty(servicio.getVapidPublicKey())) {
				addActionErrorSession(this.getText("plataforma.servicio.field.vapidPublicKey.error"));
				sw = false;
			}
			if (PlataformaMensajeriaUtil.isEmpty(servicio.getVapidPrivateKey())) {
				addActionErrorSession(this.getText("plataforma.servicio.field.vapidPrivateKey.error"));
				sw = false;
			}

		}
		if (servicio.getInformesactivo() != null && servicio.getInformesactivo()) {
			if (PlataformaMensajeriaUtil.isEmpty(servicio.getInformesdestinatarios())) {
				addActionErrorSession(this.getText("plataforma.servicio.field.informesdestinatarios.error"));
				sw = false;
			} else {
				String[] emails = servicio.getInformesdestinatarios().split(";");
				if (emails.length > 0) {
					for (int i = 0; i < emails.length; i++) {
						if (!PlataformaMensajeriaUtil.validateEmail(emails[i])) {
							
							addActionErrorSession(this.getText("plataforma.servicio.field.informesdestinatarios.email.error"));
							sw = false;
							break;
						}
					}
				}
			}
			if (servicio.getCanalid() != null && servicio.getCanalid().intValue() == 2) {
				if (!servicio.getAgrupacionestado() && !servicio.getAgrupacioncodorg() && !servicio.getAgrupacioncodsia() && !servicio.getAgrupacioncodorgpagador()) {
					addActionErrorSession(this.getText("plataforma.servicio.field.informesagrupaciones.error"));
					sw = false;
				}
			}else{
				if (!servicio.getAgrupacionestado() && !servicio.getAgrupacioncodorg() && !servicio.getAgrupacioncodsia()) {
					addActionErrorSession(this.getText("plataforma.servicio.field.informesagrupaciones.error"));
					sw = false;
				}
			}
			
		}
		if (PlataformaMensajeriaUtil.isEmpty(servicio.getResponsablefuncionalnombre())) {
			addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.funcional.nombre"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(servicio.getResponsablefuncionalemail())) {
			addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.funcional.email"));
			sw = false;
		}

		if (PlataformaMensajeriaUtil.isEmpty(servicio.getResponsabletecniconombre())) {
			addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.tecnico.nombre"));
			sw = false;
		}

		if (PlataformaMensajeriaUtil.isEmpty(servicio.getResponsabletecnicoemail())) {
			addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.tecnico.email"));
			sw = false;
		}
		return sw;
	}

	/**
	 * Update.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String update() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		ServicioBean servicioBBDD = null;
		if (servicio == null) {
			addActionErrorSession(this.getText("plataforma.servicio.update.error"));
		} else {
			
			if (servicio.getServicioId() == null) {
				if (idServicio != null) {
					servicio.setServicioId(Integer.valueOf(idServicio));
					servicioBBDD = servicioServicio.loadServicio(servicio);
				} else {
					String idServicio = (String) request.getAttribute("idServicio");
					if (idServicio != null) {
						servicio.setId(Integer.valueOf(idServicio));
						servicioBBDD = servicioServicio.loadServicio(servicio);
					}
				}

			} else {
				servicioBBDD = servicioServicio.loadServicio(servicio);
			}

			if (servicioBBDD != null) {
				servicioBBDD.setNombre(servicio.getNombre());
				servicioBBDD.setDescripcion(servicio.getDescripcion());
				servicioBBDD.setActivo(servicio.getActivo());
				servicioBBDD.setPremium(servicio.getPremium());
				servicioBBDD.setMultiorganismo(servicio.getMultiorganismo());
				servicioBBDD.setAplicacionid(servicio.getAplicacionid());
				servicioBBDD.setCanalid(servicio.getCanalid());
				servicioBBDD.setNmaxenvios(servicio.getNmaxenvios());
				servicioBBDD.setFrommail(servicio.getFrommail());
				servicioBBDD.setFrommailname(servicio.getFrommailname());
				servicioBBDD.setNumeroMaxReenvios(servicio.getNumeroMaxReenvios());
				servicioBBDD.setResponsablefuncionalemail(servicio.getResponsablefuncionalemail());
				servicioBBDD.setResponsablefuncionalnombre(servicio.getResponsablefuncionalnombre());
				servicioBBDD.setResponsabletecnicoemail(servicio.getResponsabletecnicoemail());
				servicioBBDD.setResponsabletecniconombre(servicio.getResponsabletecniconombre());

				if (null != servicio.getEndpoint() && !servicio.getEndpoint().isEmpty()) {
					servicioBBDD.setEndpoint(servicio.getEndpoint().trim()); // Eliminamos
																				// los
																				// espacios
				}
				if (servicio.getCanalid().equals(Integer.valueOf(canalServidorPushId))) {
					servicioBBDD.setBadge(servicio.getBadge());
					if ("true".equals(servicio.getIsAndroidPlataforma())) {
						servicio.setAndroidplataforma(true);
						servicioBBDD.setGcmprojectkey(servicio.getGcmprojectkey());
					} else {
						servicio.setAndroidplataforma(false);
						servicioBBDD.setGcmprojectkey(null);
					}
					if ("true".equals(servicio.getIsIosPlataforma())) {
						servicio.setIosplataforma(true);
						servicioBBDD.setApnsrutacertificado(servicio.getApnsrutacertificado());
						if (null != servicio.getApnspasswordcertificado()
								&& !servicio.getApnspasswordcertificado().isEmpty()) {
							servicioBBDD.setApnspasswordcertificado(servicio.getApnspasswordcertificado().trim()); // Eliminamos
																													// los
																													// espacios
						}
					} else {
						servicio.setIosplataforma(false);
						servicioBBDD.setApnsrutacertificado(null);
						servicioBBDD.setApnspasswordcertificado(null);
					}
					servicioBBDD.setIsAndroidPlataforma(servicio.getIsAndroidPlataforma());
					servicioBBDD.setIsIosPlataforma(servicio.getIsIosPlataforma());
				}
				
				if (servicio.getCanalid().equals(Integer.valueOf(canalServidorWebPushId))) {
					if((vapidPrivateKey != null && vapidPrivateKey.length()> 0) || (vapidPublicKey != null && vapidPrivateKey.length()>0) ){
						servicioBBDD.setVapidPrivateKey(vapidPrivateKey);
						servicioBBDD.setVapidPublicKey(vapidPublicKey);
					}
					servicioBBDD.setCaducidadWebPush(servicio.getCaducidadWebPush());
				}
				
				// Informes
				if (servicio.getIsInformesActivo() != null && "true".equals(servicio.getIsInformesActivo())) {
					servicioBBDD.setInformesactivo(true);
				} else {
					servicioBBDD.setInformesactivo(false);
				}

				if (servicio.getIsInformesActivo() != null && "true".equals(servicio.getIsInformesActivo())) {

					if (servicio.getInformesdestinatarios() != null && !servicio.getInformesdestinatarios().isEmpty()) {
						servicioBBDD.setInformesdestinatarios(servicio.getInformesdestinatarios().trim()); // Eliminamos
																											// los
																											// espacios
					}
					// //VER//////////COMPARACION TRUE/////
					if (servicio.getIsAgrupacionEstado() != null && "true".equals(servicio.getIsAgrupacionEstado())) {
						servicioBBDD.setAgrupacionestado(true);
					} else {
						servicioBBDD.setAgrupacionestado(false);
					}

					if (servicio.getCanalid().equals(Integer.valueOf(canalSMTPId))
							|| servicio.getCanalid().equals(Integer.valueOf(canalServidorPushId))) {
						if (servicio.getIsAgrupacionCodOrg() != null && "true".equals(servicio.getIsAgrupacionCodOrg())) {
							servicioBBDD.setAgrupacioncodorg(true);
						} else {
							servicioBBDD.setAgrupacioncodorg(false);
						}
						if (servicio.getIsAgrupacionCodSia() != null && "true".equals(servicio.getIsAgrupacionCodSia())) {
							servicioBBDD.setAgrupacioncodsia(true);
						} else {
							servicioBBDD.setAgrupacioncodsia(false);
						}
					} else if (servicio.getCanalid().equals(Integer.valueOf(canalSMSId))) {
						if (servicio.getIsAgrupacionCodOrg() != null && "true".equals(servicio.getIsAgrupacionCodOrg())) {
							servicioBBDD.setAgrupacioncodorg(true);
						} else {
							servicioBBDD.setAgrupacioncodorg(false);
						}
						if (servicio.getIsAgrupacionCodSia() != null && "true".equals(servicio.getIsAgrupacionCodSia())) {
							servicioBBDD.setAgrupacioncodsia(true);
						} else {
							servicioBBDD.setAgrupacioncodsia(false);
						}
						if (servicio.getIsAgrupacionCodOrgPagador() != null
								&& "true".equals(servicio.getIsAgrupacionCodOrgPagador())) {
							servicioBBDD.setAgrupacioncodorgpagador(true);
						} else {
							servicioBBDD.setAgrupacioncodorgpagador(false);
						}
					}
				} else {
					servicioBBDD.setInformesdestinatarios(null);
					servicioBBDD.setAgrupacionestado(null);
					servicioBBDD.setAgrupacioncodorg(null);
					servicioBBDD.setAgrupacioncodsia(null);
					servicioBBDD.setAgrupacioncodorgpagador(null);
				}

				// Historificacion
				Integer historificacion = servicio.getHistorificacion();
				if (null != historificacion) {
					switch (historificacion) {
					case 1:
						servicioBBDD.setHistorificacion(valor1Historificacion);
						break;
					case 2:
						servicioBBDD.setHistorificacion(valor2Historificacion);
						break;
					case 3:
						servicioBBDD.setHistorificacion(valor3Historificacion);
						break;
					case 4:
						if (null == servicio.getHistorificacionInput()) {
							servicioBBDD.setHistorificacion(null);
						} else {
							servicioBBDD.setHistorificacion(servicio.getHistorificacionInput());
						}
						break;
					default:
						servicioBBDD.setHistorificacion(valor1Historificacion);
						break;
					}
					servicioBBDD.setMotivohistorificacion(servicio.getMotivohistorificacion());

				} else {
					servicioBBDD.setHistorificacion(null);
				}

				// Conservacion
				Integer conservacion = servicio.getConservacion();
				if (conservacion != null) {
					switch (conservacion) {
					case 1:
						servicioBBDD.setConservacion(valor1Conservacion);
						break;
					case 2:
						servicioBBDD.setConservacion(valor2Conservacion);
						break;
					case 3:
						servicioBBDD.setConservacion(valor3Conservacion);
						break;
					case 4:
						servicioBBDD.setConservacion(servicio.getConservacionInput());
						break;
					default:
						servicioBBDD.setConservacion(valor1Conservacion);
						break;
					}
					if (null != servicio.getMotivoconservacion()) {
						servicioBBDD.setMotivoconservacion(servicio.getMotivoconservacion());
					} else {
						servicioBBDD.setMotivoconservacion(null);
					}

				} else {
					servicioBBDD.setConservacion(null);
				}

				boolean validServicio = false;

				if (servicioBBDD != null) {
					validServicio = validServicio(servicioBBDD);
				}
				if (null == servicioBBDD.getHistorificacion()) {
					validServicio = false;
					addActionErrorSession(this.getText("plataforma.servicio.field.historificacion.empty"));
				}

				if (!validServicio) {
					return SUCCESS;
					// Si el servicio esta pendiente de aprobacion, se aprueba
					// dicho servicio
				} else if (null != servicioBBDD.getPendienteaprobacion()
						&& servicioBBDD.getPendienteaprobacion().equals(true)) {
					servicioBBDD.setPendienteaprobacion(false);
				}

			}
			servicioServicio.updateServicio(servicioBBDD, sourceUpdateServicio, accionUpdateServicio,
					accionIdUpdateServicio);

			addActionMessageSession(this.getText("plataforma.servicio.update.ok"));
		}
		return SUCCESS;

	}

	/**
	 * Update servicio aplicacion.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String updateServicioAplicacion() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		ServicioBean servicioBBDD = null;
		if (servicio == null) {
			addActionErrorSession(this.getText("plataforma.servicio.update.error"));
		} else {
			if (servicio.getServicioId() == null) {
				if (idServicio != null) {
					servicio.setServicioId(Integer.valueOf(idServicio));
					servicioBBDD = servicioServicio.loadServicio(servicio);
				} else {
					String idServicio = (String) request.getAttribute("idServicio");
					if (idServicio != null) {
						servicio.setId(Integer.valueOf(idServicio));
						servicioBBDD = servicioServicio.loadServicio(servicio);
					}
				}

			} else {
				servicioBBDD = servicioServicio.loadServicio(servicio);

			}
			if (servicioBBDD != null) {
				servicioBBDD.setNombre(servicio.getNombre());
				servicioBBDD.setDescripcion(servicio.getDescripcion());
				servicioBBDD.setActivo(servicio.getActivo());
				servicioBBDD.setAplicacionid(servicio.getAplicacionid());
				servicioBBDD.setCanalid(servicio.getCanalid());
				servicioBBDD.setNmaxenvios(servicio.getNmaxenvios());
				servicioBBDD.setFrommail(servicio.getFrommail());
				servicioBBDD.setFrommailname(servicio.getFrommailname());
				servicioBBDD.setNumeroMaxReenvios(servicio.getNumeroMaxReenvios());
				servicioBBDD.setResponsablefuncionalemail(servicio.getResponsablefuncionalemail());
				servicioBBDD.setResponsablefuncionalnombre(servicio.getResponsablefuncionalnombre());
				servicioBBDD.setResponsabletecnicoemail(servicio.getResponsabletecnicoemail());
				servicioBBDD.setResponsabletecniconombre(servicio.getResponsabletecniconombre());

				if (null != servicio.getEndpoint() && !servicio.getEndpoint().isEmpty()) {
					servicioBBDD.setEndpoint(servicio.getEndpoint().trim()); // Eliminamos los espacios
				}
				if (servicio.getCanalid().equals(Integer.valueOf(canalServidorPushId))) {
					servicioBBDD.setBadge(servicio.getBadge());
					if (servicio.getIsAndroidPlataforma().equals("true")) {
						servicio.setAndroidplataforma(true);
						servicioBBDD.setGcmprojectkey(servicio.getGcmprojectkey());
					} else {
						servicio.setAndroidplataforma(false);
						servicioBBDD.setGcmprojectkey(null);
					}
					if (servicio.getIsIosPlataforma().equals("true")) {
						servicio.setIosplataforma(true);
						servicioBBDD.setApnsrutacertificado(servicio.getApnsrutacertificado());
						if (null != servicio.getApnspasswordcertificado()
								&& !servicio.getApnspasswordcertificado().isEmpty()) {
							servicioBBDD.setApnspasswordcertificado(servicio.getApnspasswordcertificado().trim()); // Eliminamos
																													// los
																													// espacios
						}
					} else {
						servicio.setIosplataforma(false);
						servicioBBDD.setApnsrutacertificado(null);
						servicioBBDD.setApnspasswordcertificado(null);
					}
					servicioBBDD.setIsAndroidPlataforma(servicio.getIsAndroidPlataforma());
					servicioBBDD.setIsIosPlataforma(servicio.getIsIosPlataforma());
				}

				// Informes
				if (servicio.getIsInformesActivo() != null && servicio.getIsInformesActivo().equals("true")) {
					servicioBBDD.setInformesactivo(true);
				} else {
					servicioBBDD.setInformesactivo(false);
				}

				if (servicio.getIsInformesActivo() != null && servicio.getIsInformesActivo().equals("true")) {

					if (servicio.getInformesdestinatarios() != null && !servicio.getInformesdestinatarios().isEmpty()) {
						servicioBBDD.setInformesdestinatarios(servicio.getInformesdestinatarios().trim()); // Eliminamos
																											// los
																											// espacios
					}

					if (servicio.getIsAgrupacionEstado() != null && servicio.getIsAgrupacionEstado().equals("true")) {
						servicioBBDD.setAgrupacionestado(true);
					} else {
						servicioBBDD.setAgrupacionestado(false);
					}

					if (servicio.getCanalid().equals(Integer.valueOf(canalSMTPId))
							|| servicio.getCanalid().equals(Integer.valueOf(canalServidorPushId))) {
						if (servicio.getIsAgrupacionCodOrg() != null && servicio.getIsAgrupacionCodOrg().equals("true")) {
							servicioBBDD.setAgrupacioncodorg(true);
						} else {
							servicioBBDD.setAgrupacioncodorg(false);
						}
						if (servicio.getIsAgrupacionCodSia() != null && servicio.getIsAgrupacionCodSia().equals("true")) {
							servicioBBDD.setAgrupacioncodsia(true);
						} else {
							servicioBBDD.setAgrupacioncodsia(false);
						}
					} else if (servicio.getCanalid().equals(Integer.valueOf(canalSMSId))) {
						if (servicio.getIsAgrupacionCodOrg() != null && servicio.getIsAgrupacionCodOrg().equals("true")) {
							servicioBBDD.setAgrupacioncodorg(true);
						} else {
							servicioBBDD.setAgrupacioncodorg(false);
						}
						if (servicio.getIsAgrupacionCodSia() != null && servicio.getIsAgrupacionCodSia().equals("true")) {
							servicioBBDD.setAgrupacioncodsia(true);
						} else {
							servicioBBDD.setAgrupacioncodsia(false);
						}
						if (servicio.getIsAgrupacionCodOrgPagador() != null
								&& servicio.getIsAgrupacionCodOrgPagador().equals("true")) {
							servicioBBDD.setAgrupacioncodorgpagador(true);
						} else {
							servicioBBDD.setAgrupacioncodorgpagador(false);
						}
					}
				} else {
					servicioBBDD.setInformesdestinatarios(null);
					servicioBBDD.setAgrupacionestado(null);
					servicioBBDD.setAgrupacioncodorg(null);
					servicioBBDD.setAgrupacioncodsia(null);
					servicioBBDD.setAgrupacioncodorgpagador(null);
				}

				// Historificacion
				Integer historificacion = servicio.getHistorificacion();
				if (historificacion != null) {
					switch (historificacion) {
					case 1:
						servicioBBDD.setHistorificacion(valor1Historificacion);
						break;
					case 2:
						servicioBBDD.setHistorificacion(valor2Historificacion);
						break;
					case 3:
						servicioBBDD.setHistorificacion(valor3Historificacion);
						break;
					case 4:
						servicioBBDD.setHistorificacion(servicio.getHistorificacionInput());
						break;
					default:
						servicioBBDD.setHistorificacion(valor1Historificacion);
						break;
					}
					servicioBBDD.setMotivohistorificacion(servicio.getMotivohistorificacion());

				} else {
					servicioBBDD.setHistorificacion(null);
				}
				// Conservacion
				Integer conservacion = servicio.getConservacion();
				if (conservacion != null) {
					switch (conservacion) {
					case 1:
						servicioBBDD.setConservacion(valor1Conservacion);
						break;
					case 2:
						servicioBBDD.setConservacion(valor2Conservacion);
						break;
					case 3:
						servicioBBDD.setConservacion(valor3Conservacion);
						break;
					case 4:
						servicioBBDD.setConservacion(servicio.getConservacionInput());
						break;
					default:
						servicioBBDD.setConservacion(valor1Conservacion);
						break;
					}
					if (null != servicio.getMotivoconservacion()) {
						servicioBBDD.setMotivoconservacion(servicio.getMotivoconservacion());
					} else {
						servicioBBDD.setMotivoconservacion(null);
					}

				} else {
					servicioBBDD.setConservacion(null);
				}

				boolean validServicio = false;

				if (servicio != null) {
					validServicio = validServicio(servicio);
				}

				if (!validServicio) {
					return SUCCESS;
				}
			}
			servicioServicio.updateServicio(servicioBBDD, sourceUpdateServicio, accionUpdateServicio,
					accionIdUpdateServicio);
			addActionMessageSession(this.getText("plataforma.servicio.update.ok"));
		}
		return SUCCESS;

	}

	/**
	 * Load.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String load() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idServicio == null)
			throw new BusinessException("EL idServicio recibido es nulo");
		try {
			servicio = new ServicioBean();
			servicio.setServicioId(Integer.valueOf(idServicio));
			servicio = servicioServicio.loadServicio(servicio);

			comboConfiguraciones = getComboConfiguracion(servicio.getCanalid());
			comboConfiguracionesPlan = getComboConfiguracionesPlan(servicio.getCanalid());
//			comboServicioOrganismos = cargarComboServicioOrganismos();

			listaPlanificacionesServicio = servicioPlanificacion.getPlanificacionesByServicioID(Integer
					.parseInt(idServicio));
			listaServicioOrganismos = servicioServicio.getServicioOrganismo(idServicio);
			listaServidoresServicios = loadServidoresServicio();

			if ((listaPlanificacionesServicio != null && listaPlanificacionesServicio.size() > 0)
					|| (listaServidoresServicios != null && listaServidoresServicios.size() > 0)) {
				canalDisabled = "disable";
			} else {
				canalDisabled = null;
			}

			// Historificacion
			Integer historificacion = servicio.getHistorificacion();

			if (null != historificacion) {
				switch (historificacion) {
				case 30:
					servicio.setHistorificacion(1);
					break;
				case 60:
					servicio.setHistorificacion(2);
					break;
				case 90:
					servicio.setHistorificacion(3);
					break;
				default:
					servicio.setHistorificacionInput(servicio.getHistorificacion());
					servicio.setHistorificacion(4);
					break;
				}
			} else {
				servicio.setHistorificacion(historificacion);
			}
			// Conservacion
			Integer conservacion = servicio.getConservacion();
			if (conservacion != null) {
				switch (conservacion) {
				case 1:
					servicio.setConservacion(1);
					break;
				case 2:
					servicio.setConservacion(2);
					break;
				case 3:
					servicio.setConservacion(3);
					break;
				default:
					servicio.setConservacionInput(servicio.getConservacion());
					servicio.setConservacion(4);
					break;
				}
			} else {
				servicio.setConservacion(conservacion);
			}

			return SUCCESS;
		} catch (NumberFormatException e) {
			logger.error("ServicioAction - load:" + e);
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { servicio
					.getServicioId().toString() });
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			logger.error("ServicioAction - load:" + e);
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { servicio
					.getServicioId().toString() });
			throw new BusinessException(mensg);
		}

	}

	/**
	 * Delete servicio aplicacion.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deleteServicioAplicacion() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOS", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idAplicacion == null && idServicio == null) {
			addActionErrorSession(this.getText("plataforma.servicio.deleteServicioAplicacion.error"));
		} else {
			ServicioBean servicio = new ServicioBean();
			servicio.setServicioId(Integer.valueOf(idServicio));
			ServicioBean servBBDD = servicioServicio.loadServicio(servicio);
			servBBDD.setAplicacionid(null);
			servBBDD.setEliminado("S");
			servicioServicio.updateServicio(servBBDD, source, accion, accionId);
			addActionMessageSession(this.getText("plataforma.servicio.deleteServicioAplicacion.ok"));
		}
		return SUCCESS;
	}

	/**
	 * Delete servicio aplicacion selected.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deleteServicioAplicacionSelected() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOS", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.servicio.deleteSelected.error"));
		} else {
			for (String idServicio : checkDelList) {
				ServicioBean servicio = new ServicioBean();
				servicio.setServicioId(Integer.valueOf(idServicio));
				ServicioBean servBBDD = servicioServicio.loadServicio(servicio);
				servBBDD.setAplicacionid(null);
				servicioServicio.updateServicio(servBBDD, source, accion, accionId);
				addActionMessageSession(this.getText("plataforma.servicio.deleteServicioAplicacion.ok"));
			}
		}
		return SUCCESS;
	}

	/**
	 * Delete.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String delete() throws BaseException {
		String accionPlanificacion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String accionServicio = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionIdServicio = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOS", null);
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String descripcionServidorServicio = properties.getProperty(
				"log.ACCION_DESCRIPCION_ELIMINAR_SERVIDOR_SERVICIO", null);

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idServicio == null) {
			addActionErrorSession(this.getText("plataforma.servicio.delete.error"));
		} else {
			servicio = new ServicioBean();
			servicio.setServicioId(Integer.valueOf(idServicio));
			servicioServicio.deleteServicio(servicio, accionServicio, accionIdServicio, source, accionPlanificacion,
					accionIdPlanificacion, descripcionPlanificacion, descripcionServidorServicio);
			addActionMessageSession(this.getText("plataforma.servicio.delete.ok"));

		}
		return SUCCESS;

	}

	/**
	 * Delete selected.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deleteSelected() throws BaseException {
		String accionPlanificacion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String accionServicio = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionIdServicio = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOS", null);
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String descripcionServidorServicio = properties.getProperty(
				"log.ACCION_DESCRIPCION_ELIMINAR_SERVIDOR_SERVICIO", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.servicio.deleteSelected.error"));
		} else {
			for (String idServicio : checkDelList) {
				servicio = new ServicioBean();
				servicio.setServicioId(Integer.valueOf(idServicio));
				servicioServicio.deleteServicio(servicio, accionServicio, accionIdServicio, source,
						accionPlanificacion, accionIdPlanificacion, descripcionPlanificacion,
						descripcionServidorServicio);
			}
			addActionMessageSession(this.getText("plataforma.servicio.deleteSelected.ok"));
		}
		return SUCCESS;

	}

	/**
	 * Delete planificaciones selected.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deletePlanificacionesSelected() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelListPlanificaciones == null) {
			addActionErrorSession(this.getText("plataforma.servicio.deletePlanificacionesSelected.error"));

		} else {
			for (String idPlanificacion : checkDelListPlanificaciones) {
				PlanificacionBean planificacion = new PlanificacionBean();
				planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
				servicioPlanificacion.deletePlanificacion(planificacion, source, accion, accionId, descripcion);
			}
			addActionMessageSession(this.getText("plataforma.servicio.deletePlanificacionesSelected.ok"));

		}
		return SUCCESS;
	}

	/**
	 * Agrega planificacion servicio.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String addPlanificacionServicio() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PLANIFICACION", null);

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (planificacionServidor != null && planificacionValida(planificacionServidor)) {
			if (servicio != null && servicio.getCanalid() != null && servicio.getCanalid().intValue() == 2) {
				planificacionServidor.setTipoPlanificacionId(Integer.valueOf(2));
			} else if (servicio != null && servicio.getCanalid() != null && servicio.getCanalid().intValue() == 1) {
				planificacionServidor.setTipoPlanificacionId(Integer.valueOf(1));
			} else if (servicio != null && servicio.getCanalid() != null && servicio.getCanalid().intValue() == 3) {
				planificacionServidor.setTipoPlanificacionId(Integer.valueOf(3));
			} else if (servicio != null && servicio.getCanalid() != null && servicio.getCanalid().intValue() == 4) {
				planificacionServidor.setTipoPlanificacionId(Integer.valueOf(4));
			}else if (servicio != null && servicio.getCanalid() != null && servicio.getCanalid().intValue() == 5) {
				planificacionServidor.setTipoPlanificacionId(Integer.valueOf(5));
			}

			int valido = servicioPlanificacion.validaPlanificacionOptima(idPlanificacion,
					planificacionServidor.getTipoPlanificacionId(), planificacionServidor.getServidorId(),
					planificacionServidor.getServicioId(), planificacionServidor.getLunes(),
					planificacionServidor.getMartes(), planificacionServidor.getMiercoles(),
					planificacionServidor.getJueves(), planificacionServidor.getViernes(),
					planificacionServidor.getSabado(), planificacionServidor.getDomingo(),
					planificacionServidor.getHoraHasta(), planificacionServidor.getHoraDesde());

			if (valido == 1) {
				planificacionServidor.setActivo(true);
				servicioPlanificacion.newPlanificacion(planificacionServidor, source, accion, accionId, descripcion);

				addActionMessageSession(this.getText("plataforma.servidores.planificacion.add.ok"));
			} else if (valido == 2) {
				addActionErrorSession("No se ha guardado la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
			} else {
				addActionErrorSession("No se ha guardado la planificaci&oacute;n. La configuraci&oacute;n seleccionada no garantiza el env&iacute;o de los mensajes");
			}
		} else {
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * Generar claves servicio.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String generarClavesServicio() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
//		String a = "{\"PublicKey\": \"BHGsU5-_LWDAmckOy0a_u5Y59xDWkkSiptWCYdg1pGbJEkvFy8bpmpwGJZb_xOI8wuZHa4fs1bOnkFaYFCG8WgM=\",\"PrivateKey\": \"B_tmJfK_As_XUXD6dbbqYi4RHgTP-CBz3jw7D8w_vMU=\"}";
		json = new Gson().toJson(servicioUsuariosWebPush.generarnuevasClaves());
		return SUCCESS;
	}

	/**
	 * Verifica que se ha introducido por lo menos un dÃ­a de la semana y las
	 * horas de inicio y fin.
	 *
	 * @param planificacionServidor the planificacion servidor
	 * @return true, if successful
	 */
	///MIGRADO
	private boolean planificacionValida(PlanificacionBean planificacionServidor) {
		boolean sw = true;
		if (planificacionServidor != null) {
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
				addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horas.error"));
				sw = false;

			}
			if (!PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
				addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horaHasta.error"));
				sw = false;
			}
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())
					&& !PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
				addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horaDesde.error"));
				sw = false;
			}
			if (sw) {
				if (!validoFormatoHora(planificacionServidor.getHoraDesde())) {
					addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horaDesde.formato.error"));
					sw = false;
				}
				if (!validoFormatoHora(planificacionServidor.getHoraHasta())) {
					addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horaHasta.formato.error"));
					sw = false;
				}
				if (sw) {
					if (!validoHoras(planificacionServidor.getHoraDesde(), planificacionServidor.getHoraHasta())) {
						sw = false;
					}
				}
			}
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getLunes())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getMartes())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getMiercoles())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getJueves())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getViernes())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getSabado())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getDomingo())) {
				addFieldErrorSession(this.getText("plataforma.servidores.planificacion.dias.error"));
				sw = false;
			}
			
		}
		return sw;
	}

	/**
	 * Delete planificacion servicio.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deletePlanificacionServicio() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		if (idPlanificacion == null) {
			addActionErrorSession(this.getText("plataforma.servicio.deletePlanificacion.error"));

		} else {
			PlanificacionBean planificacion = new PlanificacionBean();
			planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
			servicioPlanificacion.deletePlanificacion(planificacion, source, accion, accionId, descripcion);
			addActionMessageSession(this.getText("plataforma.servicio.deletePlanificacion.ok"));
		}
		return SUCCESS;
	}

	/**
	 * Agrega servidor servicio.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String addServidorServicio() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_SERVIDOR", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		boolean sw = true;
		if (servidorServicio != null) {

			if (PlataformaMensajeriaUtil.isEmptyNumber(servidorServicio.getServidorId())) {
				addFieldErrorSession(this.getText("plataforma.servicio.servidorservicio.field.servidorId"));
				sw = false;
			}

			if (servicio.getCanalid() == canalSMSId) {
				if (PlataformaMensajeriaUtil.isEmpty(servidorServicio.getHeaderSMS())) {
					addFieldErrorSession(this.getText("plataforma.servicio.servidorservicio.field.headerSMS"));
					sw = false;
				}
				if (PlataformaMensajeriaUtil.isEmpty(servidorServicio.getProveedorUsuarioSMS())) {
					addFieldErrorSession(this.getText("plataforma.servicio.servidorservicio.field.proveedorUsuarioSMS"));
					sw = false;
				}
				if (PlataformaMensajeriaUtil.isEmpty(servidorServicio.getProveedorPasswordSMS())) {
					addFieldErrorSession(this
							.getText("plataforma.servicio.servidorservicio.field.proveedorPasswordSMS"));
					sw = false;
				}
			}
			if (sw) {
				ServicioBean sBean = new ServicioBean();
				sBean.setServicioId(Integer.valueOf(idServicio));
				ServicioBean servBean = servicioServicio.loadServicio(sBean);
				servBean.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				servBean.setFechamodificacion(new Date());
				servicioServicio.updateServicio(servBean, null, null,
						null);
				servidorServicio.setServicioId(servicio.getServicioId());
				servicioServicio.newServidoresServicio(servidorServicio, source, accion, accionId, descripcion);
				addActionMessageSession(this.getText("plataforma.servicio.servidorservicio.add.ok"));
			} else {
				return ERROR;
			}
		} else {
			addActionErrorSession(this.getText("plataforma.servicio.servidorservicio.add.error"));
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * Agrega servicio organismos.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	// /MIGRADO
	@Transactional
	public String addServicioOrganismos() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_ORGANISMO", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		boolean sw = true;
		servicioOrganismos = new ServicioOrganismosBean();
		
		if (search.length() > 0) {
			servicioOrganismos.setOrganismoId(servicioOrganismo.getOrganismoIdByDir3(search.substring(0,
					search.indexOf("|")).trim()));
		}
		if (PlataformaMensajeriaUtil.isEmptyNumber(servicioOrganismos.getOrganismoId())) {
			addFieldErrorSession(this
					.getText("plataforma.servicio.servidorServicio.field.multiorganismo.servicio.vacio"));
			sw = false;
		}

		servicio.setServicioId(Integer.valueOf(idServicio));
		ServicioBean servicioBBDD = servicioServicio.loadServicio(servicio);
		if (!servicioBBDD.getMultiorganismo()) {
			addFieldErrorSession(this.getText("plataforma.servicio.servidorServicio.field.multiorganismo"));
			sw = false;
		}
		listaServicioOrganismos = servicioServicio.getServicioOrganismo(idServicio);
		if (null != listaServicioOrganismos) {
			for (ServicioOrganismosBean s : listaServicioOrganismos) {
				if (s.getOrganismoId().equals(servicioOrganismos.getOrganismoId())) {
					addFieldErrorSession(this
							.getText("plataforma.servicio.servidorservicio.field.multiorganismo.organismo.repetido"));
					sw = false;
				}
			}
		}

		if (sw) {


			ServicioBean sBean = new ServicioBean();
			sBean.setServicioId(Integer.valueOf(idServicio));
			ServicioBean servBean = servicioServicio.loadServicio(sBean);
			servicioServicio.updateServicio(servBean, null, null, null);



			// modificamos el organismo
			OrganismoBean oBean = new OrganismoBean();
			oBean.setOrganismoId(servicioOrganismos.getOrganismoId());
			OrganismoBean orgBean = servicioOrganismo.loadOrganismo(oBean);
			servicioOrganismo.updateOrganismo(orgBean, null, null, null);

			servicioOrganismos.setServicioId(sBean.getServicioId());
			servicioOrganismos.setOrganismoId(servicioOrganismos.getOrganismoId());

			servicioServicio.newServicioOrganismo(servicioOrganismos, source, accion, accionId, descripcion);
			addActionMessageSession(this.getText("plataforma.servicio.servicioOrganismo.add.ok"));
		} else {
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * Delete servidores servicios selected.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deleteServidoresServiciosSelected() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String sourceServicios = properties.getProperty("log.SOURCE_SERVICIOS", null);
		String descripcionServidores = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_SERVIDOR", null);
		String sourceOrganismo = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		String accionPlanificacion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String descripcionServidorOrganismo = properties.getProperty(
				"log.ACCION_DESCRIPCION_ELIMINAR_SERVIDOR_ORGANISMO", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelListServidorServicios == null) {
			addActionErrorSession(this.getText("plataforma.servicio.servidorservicio.deleteSelected.error"));
		} else {
			for (String servidorServicioId : checkDelListServidorServicios) {
				ServidoresServiciosBean servidorServicio = new ServidoresServiciosBean();
				servidorServicio.setServidorServicioId(Integer.valueOf(servidorServicioId));
				ServidoresOrganismosBean servidorOrg = new ServidoresOrganismosBean();
				servidorOrg.setServidorOrganismoId(Long.valueOf(servidorServicioId));

				ServidoresOrganismosBean so = servicioServidor.loadServidorOrganismoBean(servidorOrg);
				if (so != null) {
					// se borran de los organismos
					servidorOrganismos = new ServidoresOrganismosBean();
					servidorOrganismos.setServidorOrganismoId(Long.valueOf(servidorServicioId));
					OrganismoBean oBean = new OrganismoBean();
					oBean.setOrganismoId(so.getOrganismoId().intValue());
					OrganismoBean orgBean = servicioOrganismo.loadOrganismo(oBean);
					orgBean.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
					orgBean.setFechamodificacion(new Date());
					servicioOrganismo.updateOrganismo(orgBean, null, null,null);

					Long id = servicioServidor.loadServidorOrganismoBean(servidorOrganismos).getServidorId();
					// ////borrar planificaciones del organismo
					List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion
							.getPlanificacionesByOrganismoID(oBean.getOrganismoId());
					if (null != listaPlanificacionesOrganismos) {
						for (PlanificacionBean o : listaPlanificacionesOrganismos) {
							if (o.getServidorId().equals(id.intValue())) {
								servicioPlanificacion.deletePlanificacion(o, sourceOrganismo, accionPlanificacion,
										accionIdPlanificacion, descripcionPlanificacion);
							}
						}
					}

					servicioServidor.deleteServidorOrganismos(servidorOrganismos, sourceOrganismo, accion, accionId,
							descripcionServidorOrganismo);
				} else {
					ServicioBean sBean = new ServicioBean();
					sBean.setServicioId(Integer.valueOf(idServicio));
					ServicioBean servBean = servicioServicio.loadServicio(sBean);
					servBean.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
					servBean.setFechamodificacion(new Date());
					servicioServicio.updateServicio(servBean, null, null,null);
					servicioServicio.deleteServidoresServicios(servidorServicio, sourceServicios, accion, accionId,
							descripcionServidores);
				}

			}
			addActionMessageSession(this.getText("plataforma.servicio.servidorservicio.deleteSelected.ok"));

		}
		return SUCCESS;
	}

	/**
	 * Delete servicio organismos selected.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deleteServicioOrganismosSelected() throws BaseException {
		String accionPlanificacion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String sourceOrganismo = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_ORGANISMO_SERVICIO", null);

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelListServiciosOrganismos == null) {
			addActionErrorSession(this.getText("plataforma.servicio.servicioOrganismo.deleteSelected.error"));

		} else {

			for (String serOrg : checkDelListServiciosOrganismos) {
				ServicioOrganismosBean servicioOrganismo = new ServicioOrganismosBean();
				servicioOrganismo.setServicioOrganismoId(Integer.valueOf(serOrg));

				// ////borrar planificaciones del organismo
				List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion
						.getPlanificacionesByOrganismoID(Integer.valueOf(serOrg));
				if (null != listaPlanificacionesOrganismos) {
					for (PlanificacionBean o : listaPlanificacionesOrganismos) {
						if (o.getServicioId().equals(Integer.valueOf(idServicio))) {
							servicioPlanificacion.deletePlanificacion(o, sourceOrganismo, accionPlanificacion,
									accionIdPlanificacion, descripcionPlanificacion);
						}
					}
				}

				servicioServicio.deleteServicioOrganismos(servicioOrganismo, source, accion, accionId, descripcion);

			}
			addActionMessageSession(this.getText("plataforma.servicio.servicioOrganismo.delete.ok"));

		}
		return SUCCESS;
	}

	/**
	 * Delete servicio organismo.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deleteServicioOrganismo() throws BaseException {
		String accionPlanificacion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String sourceOrganismo = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_ORGANISMO", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idServicioOrganismo == null) {
			addActionErrorSession(this.getText("plataforma.servicio.servicioOrganismo.delete.error"));
		} else {
			// ////borrar planificaciones del organismo
			List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion
					.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
			if (null != listaPlanificacionesOrganismos) {
				for (PlanificacionBean o : listaPlanificacionesOrganismos) {
					if (o.getServicioId().equals(Integer.valueOf(idServicio))) {
						servicioPlanificacion.deletePlanificacion(o, sourceOrganismo, accionPlanificacion,
								accionIdPlanificacion, descripcionPlanificacion);
					}
				}
			}

			ServicioOrganismosBean servicioOrganismo = new ServicioOrganismosBean();
			servicioOrganismo.setServicioOrganismoId(Integer.valueOf(idServicioOrganismo));
			servicioServicio.deleteServicioOrganismos(servicioOrganismo, source, accion, accionId, descripcion);
			addActionMessageSession(this.getText("plataforma.servicio.servicioOrganismo.delete.ok"));

		}

		return SUCCESS;
	}

	/**
	 * Delete servidor servicio.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deleteServidorServicio() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_SERVIDOR", null);
		String accionPlanificacion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String sourceServicio = properties.getProperty("log.SOURCE_SERVICIOS", null);
		String sourceOrganismo = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		String descripcionServidorOrganismo = properties.getProperty(
				"log.ACCION_DESCRIPCION_ELIMINAR_SERVIDOR_ORGANISMO", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (servidorServicioId == null) {
			addActionErrorSession(this.getText("plataforma.servicio.servidorservicio.delete.error"));

		} else {

			if (null != idOrganismo && idOrganismo.length() > 0) {
				servidorOrganismos = new ServidoresOrganismosBean();
				servidorOrganismos.setServidorOrganismoId(Long.valueOf(servidorServicioId));
				OrganismoBean oBean = new OrganismoBean();
				oBean.setOrganismoId(Integer.valueOf(idOrganismo));
				OrganismoBean orgBean = servicioOrganismo.loadOrganismo(oBean);
				orgBean.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				orgBean.setFechamodificacion(new Date());
				servicioOrganismo.updateOrganismo(orgBean, sourceUpdateOrganismo, accionUpdateOrganismo,
						accionIdUpdateOrganismo);

				Long id = servicioServidor.loadServidorOrganismoBean(servidorOrganismos).getServidorId();
				// ////borrar planificaciones del organismo que tienen el mismo servidor y servicio ///////
				////Esto antes no se hacÃ­a y no se tenÃ­a en cuenta el servicio////////
				////Comprobar si estÃ¡ bien/////
				List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion
						.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
				if (null != listaPlanificacionesOrganismos) {
					for (PlanificacionBean o : listaPlanificacionesOrganismos) {
						if (o.getServidorId().equals(id.intValue()) && o.getServicioId().equals(Integer.valueOf(idServicio))) {
							servicioPlanificacion.deletePlanificacion(o, sourceServicio, accionPlanificacion,
									accionIdPlanificacion, descripcionPlanificacion);
						}
					}
				}

				servicioServidor.deleteServidorOrganismos(servidorOrganismos, sourceOrganismo, accion, accionId,
						descripcionServidorOrganismo);
			} else {
				servidorServicio = new ServidoresServiciosBean();
				servidorServicio.setServidorServicioId(Integer.valueOf(servidorServicioId));
				ServicioBean sBean = new ServicioBean();
				sBean.setServicioId(Integer.valueOf(idServicio));
				ServicioBean servBean = servicioServicio.loadServicio(sBean);
				servBean.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				servBean.setFechamodificacion(new Date());
				servicioServicio.updateServicio(servBean, sourceUpdateServicio, accionUpdateServicio,
						accionIdUpdateServicio);
				servicioServicio.deleteServidoresServicios(servidorServicio, source, accion, accionId, descripcion);
			}
			addActionMessageSession(this.getText("plataforma.servicio.servidorservicio.delete.ok"));
		}
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	///MIGRADO
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare() throws Exception {
		this.validate();
		cargarCtes();
		session = (Map) ActionContext.getContext().get("session");
		recovery = (String) session.get(txtRecovery);

		comboAplicaciones = getComboValues();
		comboCanales = getComboValuesCanales();

		// Si se viene de Aplicaciones para crear un nuevo servicio
		if (idAplicacion != null && idAplicacion.length() > 0 && idServicio == null) {
			servicio = new ServicioBean();
			servicio.setAplicacionid(Integer.valueOf(idAplicacion));

			AplicacionBean detalleApp = new AplicacionBean();
			detalleApp.setAplicacionId(Integer.valueOf(idAplicacion));
			detalleApp = servicioAplicacion.loadAplicacion(detalleApp);
			if (null != detalleApp) {
				servicio.setAplicacionnombre(detalleApp.getNombre());
			}
		}
		if (idServicio != null) {
			if (servicio == null)
				load();
		}
	}

	/**
	 * Cargar ctes.
	 */
	///MIGRADO
	private void cargarCtes() {
		requestAttributeTotalsize = properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null);
		requestAttributePagesize = properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null);
		canalSMTPId = Integer.parseInt(properties.getProperty("generales.CANAL_SMTP_ID", null));
		canalSMSId = Integer.parseInt(properties.getProperty("generales.CANAL_SMS_ID", null));
		canalRecepcionSMSId = Integer.parseInt(properties.getProperty("generales.CANAL_RECEPCION_SMS_ID", null));
		canalServidorPushId = Integer.parseInt(properties.getProperty("generales.CANAL_SERVIDOR_PUSH_ID", null));
		canalServidorWebPushId = Integer.parseInt(properties.getProperty("generales.CANAL_SERVIDOR_WEBPUSH_ID", null));
		valorMaximoPredefinidoHistorificacion = Integer.parseInt(properties.getProperty(
				"servicioAction.VALOR_MAXIMO_PREDEFINIDO_HISTORIFICACION", null));
		valorMaximoPredefinidorConservacion = Integer.parseInt(properties.getProperty(
				"servicioAction.VALOR_MAXIMO_PREDEFINIDO_CONSERVACION", null));
		valor1Historificacion = Integer
				.parseInt(properties.getProperty("servicioAction.VALOR_1_HISTORIFICACION", null));
		valor2Historificacion = Integer
				.parseInt(properties.getProperty("servicioAction.VALOR_2_HISTORIFICACION", null));
		valor3Historificacion = Integer
				.parseInt(properties.getProperty("servicioAction.VALOR_3_HISTORIFICACION", null));
		valor1Conservacion = Integer.parseInt(properties.getProperty("servicioAction.VALOR_1_CONSERVACION", null));
		valor2Conservacion = Integer.parseInt(properties.getProperty("servicioAction.VALOR_2_CONSERVACION", null));
		valor3Conservacion = Integer.parseInt(properties.getProperty("servicioAction.VALOR_3_CONSERVACION", null));
		txtRecovery = properties.getProperty("servicioAction.RECOVERY", null);
		pagesize = Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		accionUpdateServicio = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		accionIdUpdateServicio = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		sourceUpdateServicio = properties.getProperty("log.SOURCE_SERVICIOS", null);
		accionUpdateOrganismo = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		accionIdUpdateOrganismo = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		sourceUpdateOrganismo = properties.getProperty("log.SOURCE_SERVICIOS", null);
	}

	/**
	 * Valido horas.
	 *
	 * @param horaDesde the hora desde
	 * @param horaHasta the hora hasta
	 * @return true, if successful
	 */
	///MIGRADO
	private boolean validoHoras(String horaDesde, String horaHasta) {
		boolean sw = true;
		String[] horaDesdeArray = horaDesde.split(":");
		String[] horaHastaArray = horaHasta.split(":");
		int hDesde = Integer.valueOf(horaDesdeArray[0]);
		int mDesde = Integer.valueOf(horaDesdeArray[1]);
		int hHasta = Integer.valueOf(horaHastaArray[0]);
		int mHasta = Integer.valueOf(horaHastaArray[1]);
		if (hDesde > hHasta) {
			addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horaDesde.menor.error"));
			sw = false;
		} else if (hDesde == hHasta && mDesde > mHasta) {
			addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horaDesde.menor.error"));
			sw = false;
		} else if (hDesde == hHasta && mDesde == mHasta) {
			addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horas.iguales.error"));
			sw = false;
		}
		return sw;
	}

	/**
	 * Valido formato hora.
	 *
	 * @param hora the hora
	 * @return true, if successful
	 */
	///MIGRADO
	private boolean validoFormatoHora(String hora) {
		boolean sw = true;
		if (!PlataformaMensajeriaUtil.isEmpty(hora)) {
			if (!PlataformaMensajeriaUtil.validaFormatoHora(hora)) {
				sw = false;
			}
		}
		return sw;
	}

	/**
	 * Obtener combo configuracion.
	 *
	 * @param idCanal the id canal
	 * @return combo configuracion
	 */
	///MIGRADO
	private List<KeyValueObject> getComboConfiguracion(Integer idCanal) {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;
		ArrayList<ProveedorSMSBean> keysSMS = null;
		ArrayList<ReceptorSMSBean> keysReceptorSMS = null;
		ArrayList<ServidorBean> keysSMTP = null;
		ArrayList<ServidorPushBean> keysServidorPush = null;
		ArrayList<ServidorWebPushBean> keysServidorWebPush = null;
		if (idCanal != null && idCanal.intValue() == canalSMSId) {
			try {
				keysSMS = (ArrayList<ProveedorSMSBean>) servicioProveedorSMS.getProveedoresSMSNoAsignados(
						Integer.valueOf(idServicio),
						Integer.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_SMS", null)));
			} catch (BusinessException e) {
				logger.error("ServicioAction - getComboConfiguracion:" + e);
			}

			if (keysSMS != null && keysSMS.size() > 0) {
				for (ProveedorSMSBean key : keysSMS) {

					option = new KeyValueObject();
					option.setCodigo(key.getProveedorSMSId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
			}
		} else if (idCanal != null && idCanal.intValue() == canalRecepcionSMSId) {
			try {
				keysReceptorSMS = (ArrayList<ReceptorSMSBean>) servicioReceptorSMS.getReceptoresSMSNoAsignados(
						Integer.valueOf(idServicio),
						Integer.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_RECEPCION", null)));
			} catch (BusinessException e) {
				logger.error("ServicioAction - getComboConfiguracion:" + e);
			}

			if (keysReceptorSMS != null && keysReceptorSMS.size() > 0) {
				for (ReceptorSMSBean key : keysReceptorSMS) {

					option = new KeyValueObject();
					option.setCodigo(key.getReceptorSMSId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
			}
		} else if (idCanal != null && idCanal.intValue() == canalSMTPId) {
			try {
				keysSMTP = (ArrayList<ServidorBean>) servicioServidor.getServidoresNoAsignados(
						Integer.valueOf(idServicio),
						Integer.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_SMTP", null)));
			} catch (BusinessException e) {
				logger.error("ServicioAction - getComboConfiguracion:" + e);
			}
			if (keysSMTP != null && keysSMTP.size() > 0)
				for (ServidorBean key : keysSMTP) {

					option = new KeyValueObject();
					option.setCodigo(key.getServidorid().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
		} else if (idCanal != null && idCanal.intValue() == canalServidorPushId) {
			try {
				keysServidorPush = (ArrayList<ServidorPushBean>) servicioServidorPush.getServidoresPushNoAsignados(
						Integer.valueOf(idServicio),
						Integer.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_PUSH", null)));
			} catch (BusinessException e) {
				logger.error("ServicioAction - getComboConfiguracion:" + e);
			}
			if (keysServidorPush != null && keysServidorPush.size() > 0)
				for (ServidorPushBean key : keysServidorPush) {

					option = new KeyValueObject();
					option.setCodigo(key.getServidorPushId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
		}else if (idCanal != null && idCanal.intValue() == canalServidorWebPushId) {
			try {
				keysServidorWebPush = (ArrayList<ServidorWebPushBean>) servicioServidorWebPush.getServidoresWebPushNoAsignados(
						Integer.valueOf(idServicio),
						Integer.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_WEBPUSH", null)));
			} catch (BusinessException e) {
				logger.error("ServicioAction - getComboConfiguracion:" + e);
			}
			if (keysServidorWebPush != null && !keysServidorWebPush.isEmpty())
				for (ServidorWebPushBean key : keysServidorWebPush) {

					option = new KeyValueObject();
					option.setCodigo(key.getServidorWebPushId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
		}

		return result;
	}

	/**
	 * Obtener combo configuraciones plan.
	 *
	 * @param idCanal the id canal
	 * @return combo configuraciones plan
	 */
	///MIGRADO
	private List<KeyValueObject> getComboConfiguracionesPlan(Integer idCanal) {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;
		ArrayList<ProveedorSMSBean> keysSMS = null;
		ArrayList<ReceptorSMSBean> keysReceptorSMS = null;
		ArrayList<ServidorBean> keysSMTP = null;
		ArrayList<ServidorPushBean> keysServidoresPush = null;
		ArrayList<ServidorWebPushBean> keysServidoresWebPush = null;
		if (idCanal != null && idCanal.intValue() == canalSMSId) {
			try {
				keysSMS = (ArrayList<ProveedorSMSBean>) servicioProveedorSMS.getProveedoresSMS(Integer
						.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_SMS", null)));
			} catch (BusinessException e) {
				logger.error("ServicioAction - getComboConfiguracionesPlan:" + e);
			}

			if (keysSMS != null && keysSMS.size() > 0) {
				for (ProveedorSMSBean key : keysSMS) {

					option = new KeyValueObject();
					option.setCodigo(key.getProveedorSMSId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
			}
		} else if (idCanal != null && idCanal.intValue() == canalRecepcionSMSId) {
			try {
				keysReceptorSMS = (ArrayList<ReceptorSMSBean>) servicioReceptorSMS.getReceptoresSMS(Integer
						.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_RECEPCION", null)));
			} catch (BusinessException e) {
				logger.error("ServicioAction - getComboConfiguracionesPlan:" + e);
			}

			if (keysReceptorSMS != null && keysReceptorSMS.size() > 0) {
				for (ReceptorSMSBean key : keysReceptorSMS) {

					option = new KeyValueObject();
					option.setCodigo(key.getReceptorSMSId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
			}
		} else if (idCanal != null && idCanal.intValue() == canalSMTPId) {
			try {
				keysSMTP = (ArrayList<ServidorBean>) servicioServidor.getServidores(Integer.parseInt(properties
						.getProperty("generales.TIPO_SERVIDOR_SMTP", null)));
			} catch (BusinessException e) {
				logger.error("ServicioAction - getComboConfiguracionesPlan:" + e);
			}

			if (keysSMTP != null && keysSMTP.size() > 0)
				for (ServidorBean key : keysSMTP) {

					option = new KeyValueObject();
					option.setCodigo(key.getServidorid().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
		} else if (idCanal != null && idCanal.intValue() == canalServidorPushId) {
			try {
				keysServidoresPush = (ArrayList<ServidorPushBean>) servicioServidorPush.getServidoresPush(Integer
						.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_PUSH", null)));
			} catch (BusinessException e) {
				logger.error("ServicioAction - getComboConfiguracionesPlan:" + e);
			}

			if (keysServidoresPush != null && keysServidoresPush.size() > 0) {
				for (ServidorPushBean key : keysServidoresPush) {

					option = new KeyValueObject();
					option.setCodigo(key.getServidorPushId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
			}
		}else if (idCanal != null && idCanal.intValue() == canalServidorWebPushId) {
			try {
				keysServidoresWebPush = (ArrayList<ServidorWebPushBean>) servicioServidorWebPush.getServidoresWebPush(Integer
						.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_WEBPUSH", null)));
			} catch (BusinessException e) {
				logger.error("ServicioAction - getComboConfiguracionesPlan:" + e);
			}

			if (keysServidoresWebPush != null && !keysServidoresWebPush.isEmpty()) {
				for (ServidorWebPushBean key : keysServidoresWebPush) {

					option = new KeyValueObject();
					option.setCodigo(key.getServidorWebPushId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
			}
		}
		return result;
	}

	/**
	 * Cargar combo servicio organismos.
	 *
	 * @return the list
	 */
	///MIGRADO
	List<KeyValueObject> cargarComboServicioOrganismos() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;

		ArrayList<OrganismoBean> keys = null;
		try {
			keys = (ArrayList<OrganismoBean>) servicioOrganismo.getOrganismos();
		} catch (BusinessException e) {
			logger.error("ServicioAction - cargarComboServicioOrganismos:" + e);
		}

		if (keys != null && keys.size() > 0)
			for (OrganismoBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getOrganismoId().toString());
				option.setDescripcion(key.getDir3());
				result.add(option);
			}
		return result;
	}

	/**
	 * Obtener combo values canales.
	 *
	 * @return combo values canales
	 */
	///MIGRADO
	private List<KeyValueObject> getComboValuesCanales() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;
		ArrayList<CanalBean> keys = null;
		try {
			keys = (ArrayList<CanalBean>) servicioCanal.getCanales();
		} catch (BusinessException e) {
			logger.error("ServicioAction - getComboValuesCanales:" + e);
		}

		if (keys != null && keys.size() > 0)
			for (CanalBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getCanalId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		return result;
	}

	/**
	 * Obtener combo values.
	 *
	 * @return combo values
	 */
	///MIGRADO
	private List<KeyValueObject> getComboValues() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option = null;
		ArrayList<AplicacionBean> keys = null;
		try {
			keys = (ArrayList<AplicacionBean>) servicioAplicacion.getAplicaciones();
		} catch (BusinessException e) {
			logger.error("ServicioAction - getComboValues:" + e);
		}
		if (keys != null && keys.size() > 0)
			for (AplicacionBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getAplicacionId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		return result;
	}

	/**
	 * Load servidores servicio.
	 *
	 * @return the list
	 */
	///MIGRADO
	private List<ServidoresServiciosBean> loadServidoresServicio() {
		List<ServidoresServiciosBean> res = null;
		List<Integer> listaOrganismos = new ArrayList<Integer>();
		if (idServicio != null && idServicio.length() > 0) {
			try {
				res = servicioServicio.getServidoresServicios(idServicio);
				if (null == res)
					res = new ArrayList<ServidoresServiciosBean>();
				// tenemos los id de los organismo
				if (listaServicioOrganismos != null && listaServicioOrganismos.size() > 0) {
					for (ServicioOrganismosBean l : listaServicioOrganismos) {
						if (!listaOrganismos.contains(l.getOrganismoId()))
							listaOrganismos.add(l.getOrganismoId());
					}
					List<ServidoresServiciosBean> lista = servicioServidor.getServidorOrganismo(listaOrganismos);
					
					if (null != lista && !lista.isEmpty()){
						for (ServidoresServiciosBean ss : lista) {
							if (null != ss.getServicioId() && ss.getServicioId().toString().equals(idServicio)){
								res.add(ss);
							}
						}
				
					}
				}
			} catch (NumberFormatException e) {
				logger.error("ServicioAction - loadServidoresServicio:" + e);
			} catch (BusinessException e) {
				logger.error("ServicioAction - loadServidoresServicio:" + e);
			}
		}
		return res;
	}

	///MIGRADO
	/**
	 * MÃ©todo que resuelve el lugar donde tiene que volver.
	 *
	 * @return volver
	 */
	@SuppressWarnings("unchecked")
	public String getVolver() {
		String volver = "buscarServicios.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
			session.put(txtRecovery, volver);
		} else if (session.get(txtRecovery) != null) {
			volver = (String) session.get(txtRecovery);
			session.put(txtRecovery, null);
		}
		return volver;
	}

	///MIGRADO
	/**
	 * MÃ©todo que resuelve el lugar donde tiene que volver.
	 *
	 * @return volver aplicacion
	 */
	public String getVolverAplicacion() {
		String volverAplicacion = "viewAplicacion.action?idAplicacion=" + servicio.getAplicacionid();
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volverAplicacion = from + "?" + var + "=" + idFrom;
		}
		return volverAplicacion;
	}
	
	
	/**
	 * Load passbook.
	 *
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	public String loadPassbook() throws BusinessException {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String pathBase = ps.getMessage("filesystem.pathBase", null);
		//String nombreOrganismo = "";
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		
		if(idServicioOrganismo == null || idServicioOrganismo.isEmpty()) {
			throw new BusinessException("El idServicioOrganismo recibido es nulo");
		}
		
		if(idServicio == null || idServicio.isEmpty()) {
			throw new BusinessException("El idServicio recibido es nulo");
		}
		
		if(idOrganismo == null || idOrganismo.isEmpty()) {
			throw new BusinessException("El idOrganismo recibido es nulo");
		}
		
		servicioOrganismos = new ServicioOrganismosBean();
		servicioOrganismos.setServicioOrganismoId(Integer.valueOf(idServicioOrganismo));
		servicioOrganismos.setServicioId(Integer.valueOf(idServicio));
		servicioOrganismos.setOrganismoId(Integer.valueOf(idOrganismo));
		
		OrganismoBean o = new OrganismoBean();
		o.setOrganismoId(Integer.parseInt(idOrganismo));
		o = servicioOrganismo.loadOrganismo(o);
		
		String l = pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "logo.png";
				
		Path pathLogo = Paths.get(l);
		if(Files.exists(pathLogo, LinkOption.NOFOLLOW_LINKS)){
			logo = pathLogo.toString();
						
			logo64 = generateDataURI(pathLogo);
			servicioOrganismos.setLogo(logo);
		}
		
		Path pathBackground = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "background.png");
		if(Files.exists(pathBackground, LinkOption.NOFOLLOW_LINKS)){
			background = pathBackground.toString();
			
			background64 = generateDataURI(pathBackground);
			servicioOrganismos.setBackground(background);
		}
		
		Path pathIcon = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "icon.png");
		if(Files.exists(pathIcon, LinkOption.NOFOLLOW_LINKS)){
			icon = pathIcon.toString();
			
			icon64 = generateDataURI(pathIcon);
			servicioOrganismos.setIcon(icon);			
		}
			
			return SUCCESS;

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
			String mimeType = getMimeType(path.toString());
					
			 //create the output
	        buffer = new StringBuffer();        
	        buffer.append("data:");        
	        
	        //add MIME type        
	        buffer.append(mimeType);
	        
	        //output base64-encoding
	        buffer.append(";base64,");
	        buffer.append(new String(Base64.encodeBase64(bytes)));
		}catch(IOException e){
			return null;
		}
        //output to writer
       return buffer.toString();        

	}
	
	/**
	 * Obtener mime type.
	 *
	 * @param filename the filename
	 * @return mime type
	 */
	private String getMimeType(String filename) {
		String res = "";
		String type = getFileType(filename);
		 
		if (binaryTypes.containsKey(type)){    
             res = (String) binaryTypes.get(type);        
         } else if (textTypes.containsKey(type)){
             res = (String) textTypes.get(type) + ";charset=UTF-8";    
         } 
		return res;
	}
	
	 /**
 	 * Obtener file type.
 	 *
 	 * @param filename the filename
 	 * @return file type
 	 */
 	private static String getFileType(String filename){
	        String type = "";

	        int idx = filename.lastIndexOf('.');
	        if (idx >= 0 && idx < filename.length() - 1) {
	            type = filename.substring(idx + 1);
	        }
	        
	        return type;
	    }


	/**
	 * Save passbook.
	 *
	 * @throws BusinessException the business exception
	 */
	public void savePassbook() throws BusinessException {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String pathBase = ps.getMessage("filesystem.pathBase", null);
		Path path = null;
		Path path2x = null;
		
//		if (getRequest().getSession().getAttribute("infoUser") == null)
//			return "noUser";
		
		if(idServicioOrganismo == null) {
			throw new BusinessException("El idServicioOrganismo recibido es nulo");
		}
		
		if(idServicio == null) {
			throw new BusinessException("El idServicio recibido es nulo");
		}
		
		if(idOrganismo == null) {
			throw new BusinessException("El idOrganismo recibido es nulo");
		}
		OrganismoBean o = new OrganismoBean();
		o.setOrganismoId(Integer.parseInt(idOrganismo));
		o = servicioOrganismo.loadOrganismo(o);
		
		try {
			if(logo!=null){
				Path logoFile = Paths.get(logo);
				path = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "logo.png");
				path2x = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "logo@2x.png");
				Files.createDirectories(path.getParent());
				Files.copy(logoFile, path, StandardCopyOption.REPLACE_EXISTING);
				Files.copy(logoFile, path2x, StandardCopyOption.REPLACE_EXISTING);
				addActionMessageSession(this.getText("plataforma.servicio.saveLogo.ok"));		
			}
			
			if(background!=null){
				Path backgroundFile = Paths.get(background);
				path = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "background.png");
				path2x = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "background@2x.png");
				Files.createDirectories(path.getParent());
				Files.copy(backgroundFile, path, StandardCopyOption.REPLACE_EXISTING);
				Files.copy(backgroundFile, path2x, StandardCopyOption.REPLACE_EXISTING);
				addActionMessageSession(this.getText("plataforma.servicio.saveBackground.ok"));		
			}
			
			
			if(icon!=null){
				Path iconFile = Paths.get(icon);
				path = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "icon.png");
				path2x = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "icon@2x.png");
				Files.createDirectories(path.getParent());
				Files.copy(iconFile, path, StandardCopyOption.REPLACE_EXISTING);
				Files.copy(iconFile, path2x, StandardCopyOption.REPLACE_EXISTING);
				addActionMessageSession(this.getText("plataforma.servicio.saveIcon.ok"));		
			}
		}
		
		catch(IOException e){
			LOG.error("[ServicioAction] Se ha producido un error al copiar un archivo del idServicioOrganismo:" + idServicioOrganismo, e);			
		}
		
//		return SUCCESS;
	}
	
	/**
	 * Delete imagen logo.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String deleteImagenLogo() throws BaseException {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String pathBase = ps.getMessage("filesystem.pathBase", null);
		Path path2x = null;
		logger.info("entrando en deleteImagenLogo------");
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idServicioOrganismo == null) {
			addActionErrorSession(this.getText("plataforma.servicio.deleteLogo.error"));
			return ERROR;
		} else {
			OrganismoBean o = new OrganismoBean();
			o.setOrganismoId(Integer.parseInt(idOrganismo));
			o = servicioOrganismo.loadOrganismo(o);
			try {
				Path pathLogo = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "logo.png");
				if(Files.exists(pathLogo, LinkOption.NOFOLLOW_LINKS)){
					path2x = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "logo@2x.png");
					Files.delete(pathLogo);
					Files.delete(path2x);
					addActionMessageSession(this.getText("plataforma.servicio.deleteLogo.ok"));					
				}
			}
			
			catch(IOException e){
				LOG.error("[ServicioAction] Se ha producido un error al borrar el logo del idServicioOrganismo:" + idServicioOrganismo, e);			
			}
		}
		return SUCCESS;
	}
	
	/**
	 * Delete imagen background.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String deleteImagenBackground() throws BaseException {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String pathBase = ps.getMessage("filesystem.pathBase", null);
		Path path2x = null;
		logger.info("entrando en deleteImagenBackground------");
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idServicioOrganismo == null) {
			addActionErrorSession(this.getText("plataforma.servicio.deleteBackground.error"));
			return ERROR;
		} else {
			OrganismoBean o = new OrganismoBean();
			o.setOrganismoId(Integer.parseInt(idOrganismo));
			o = servicioOrganismo.loadOrganismo(o);
			try {
				Path pathBackground = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "background.png");
				if(Files.exists(pathBackground, LinkOption.NOFOLLOW_LINKS)){
					path2x = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "background@2x.png");
					Files.delete(pathBackground);
					Files.delete(path2x);
					addActionMessageSession(this.getText("plataforma.servicio.deleteBackground.ok"));
				}
			}
			
			catch(IOException e){
				LOG.error("[ServicioAction] Se ha producido un error al borrar el background del idServicioOrganismo:" + idServicioOrganismo, e);			
			}
		}
		return SUCCESS;
	}
	
	/**
	 * Delete imagen icon.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String deleteImagenIcon() throws BaseException {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String pathBase = ps.getMessage("filesystem.pathBase", null);
		Path path2x = null;
		logger.info("entrando en deleteImagenIcon------");
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idServicioOrganismo == null) {
			addActionErrorSession(this.getText("plataforma.servicio.deleteIcon.error"));
			return ERROR;
		} else {
			OrganismoBean o = new OrganismoBean();
			o.setOrganismoId(Integer.parseInt(idOrganismo));
			o = servicioOrganismo.loadOrganismo(o);
			try {
				Path pathIcon = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "icon.png");
				if(Files.exists(pathIcon, LinkOption.NOFOLLOW_LINKS)){
					path2x = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "icon@2x.png");
					Files.delete(pathIcon);
					Files.delete(path2x);
					addActionMessageSession(this.getText("plataforma.servicio.deleteIcon.ok"));
				}
			}
			
			catch(IOException e){
				LOG.error("[ServicioAction] Se ha producido un error al borrar el icon del idServicioOrganismo:" + idServicioOrganismo, e);			
			}
		}
		return SUCCESS;
	}
	
	/**
	 * Previsualizar.
	 *
	 * @throws BusinessException the business exception
	 */
	public void previsualizar() throws BusinessException {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String pathBase = ps.getMessage("filesystem.pathBase", null);
		
		if(idServicioOrganismo == null || idServicioOrganismo.isEmpty()) {
			throw new BusinessException("El idServicioOrganismo recibido es nulo");
		}
		
		if(idServicio == null || idServicio.isEmpty()) {
			throw new BusinessException("El idServicio recibido es nulo");
		}
		
		if(idOrganismo == null || idOrganismo.isEmpty()) {
			throw new BusinessException("El idOrganismo recibido es nulo");
		}
		OrganismoBean o = new OrganismoBean();
		o.setOrganismoId(Integer.parseInt(idOrganismo));
		o = servicioOrganismo.loadOrganismo(o);
		Path pathLogo = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "logo.png");		
		Path pathBackground = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "background.png");		
		Path pathIcon = Paths.get(pathBase + "/" + idServicio + "/" + o.getDir3() + "/" + "icon.png");
		
		if(!Files.exists(pathLogo, LinkOption.NOFOLLOW_LINKS) || !Files.exists(pathBackground, LinkOption.NOFOLLOW_LINKS) || 
				!Files.exists(pathIcon, LinkOption.NOFOLLOW_LINKS)){
			addActionErrorSession(this.getText("plataforma.servicio.previsualizar.error"));
		}

		else{
		
			try {
			
			String url = ps.getMessage("passbook.url", null, null, null);
			String logoText= ps.getMessage("passbook.logoText", null, null, null);
			String description= ps.getMessage("passbook.description", null, null, null);
			
			String serialNumber = String.valueOf(System.currentTimeMillis());
			String autheticationToken = Base64.encodeBase64String(serialNumber.getBytes());
			
			String teamIdentifier = ps.getMessage("passbook.teamIdentifier", null, null, null);
			String organizationName = ps.getMessage("passbook.organizationName", null, null, null);
			String passTypeIdentifier = ps.getMessage("passbook.passTypeIdentifier", null, null, null);
			
			String templatePath = (pathBase + "/" + idServicio + "/" + o.getDir3() + "/");
			
			String tempPath = ps.getMessage("passbook.tempPath", null, null, null);
			
			String appleWWDRCA = ps.getMessage("passbook.appleWWDRCA", null, null, null);
			String keyStorePath = ps.getMessage("passbook.keyStorePath", null, null, null);
			String keyStorePassword = ps.getMessage("passbook.keyStorePassword", null, null, null);
			
			String foregroundColor=ps.getMessage("passbook.foregroundColor", null, null, null);
			String backgroundColor=ps.getMessage("passbook.backgroundColor", null, null, null);
			String labelColor=ps.getMessage("passbook.labelColor", null, null, null);
			
			List<PKField> camposPrincipales = getPkFieldsListAlign(new ArrayList<PkFieldsXMLBean>());
			List<PKField> camposSecundarios = getPkFieldsListAlign(new ArrayList<PkFieldsXMLBean>());
			List<PKField> camposAuxiliares = getPkFieldsListAlign(new ArrayList<PkFieldsXMLBean>());
			List<PKField> camposCabecera = getPkFieldsList(new ArrayList<PkFieldsXMLBean>() );
			List<PKField> camposDetalleTrasera = getPkFieldsList(new ArrayList<PkFieldsXMLBean>());
			String pkpassFile = null;
			
			pkpassFile = PassbookGenerator.generate(camposPrincipales, camposSecundarios, camposAuxiliares, camposCabecera, 
					camposDetalleTrasera, url, logoText, description, backgroundColor, foregroundColor, labelColor,passTypeIdentifier, 
					serialNumber, autheticationToken, teamIdentifier, organizationName, templatePath,appleWWDRCA,keyStorePath,keyStorePassword, tempPath);
				
			logger.debug("[ServicioAction] - descargaFicheroPkPass - inicio");
		
			StringBuffer titulo =new  StringBuffer();
			titulo.append("passbook").append("_").append("previsualizar").append("_").append(idServicio).append("_")
			.append(idOrganismo).append("_").append(idServicioOrganismo);
				
			PlataformaMensajeriaUtil.descargaFicheroPkPass(response, titulo.toString(), pkpassFile, TIPO_FICHERO);
			}
		
			catch (Exception e) {
				LOG.error("[ServicioAction] Se ha producido un error al generar el passbook:" + idServicioOrganismo, e);	
			}	
		} 
//		return SUCCESS;
	}
	
	/**
	 * Obtener pk fields list align.
	 *
	 * @param pkFieldsList the pk fields list
	 * @return pk fields list align
	 */
	private List<PKField> getPkFieldsListAlign(List<PkFieldsXMLBean> pkFieldsList) {
        List<PKField> camposPrincipales = new ArrayList<PKField>();
        for (int i=0; i<pkFieldsList.size();i++) {
            
            PkFieldsXMLBean pkFieldXMLField = pkFieldsList.get(i);
            
            PKField field = new PKField();
            field.setKey(pkFieldXMLField.getKey());
            field.setLabel(pkFieldXMLField.getLabel());
            field.setValue(pkFieldXMLField.getValue());
            
            if(i%2==0){
                field.setTextAlignment(PKTextAlignment.PKTextAlignmentLeft);
            }else{
                field.setTextAlignment(PKTextAlignment.PKTextAlignmentRight);
            }
            
            camposPrincipales.add(field);
        }
        
        return camposPrincipales;
    }
	
	/**
	 * Obtener pk fields list.
	 *
	 * @param pkFieldsList the pk fields list
	 * @return pk fields list
	 */
	private List<PKField> getPkFieldsList(List<PkFieldsXMLBean> pkFieldsList) {
		List<PKField> camposPrincipales = new ArrayList<PKField>();
		for (PkFieldsXMLBean pkFieldXMLField : pkFieldsList) {
			PKField field = new PKField();
			field.setKey(pkFieldXMLField.getKey());
			field.setLabel(pkFieldXMLField.getLabel());
			field.setValue(pkFieldXMLField.getValue());
			camposPrincipales.add(field);
		}
		return camposPrincipales;
	}
	

	/**
	 * Obtener combo configuraciones plan.
	 *
	 * @return combo configuraciones plan
	 */
	public List<KeyValueObject> getComboConfiguracionesPlan() {
		return comboConfiguracionesPlan;
	}

	/**
	 * Modificar combo configuraciones plan.
	 *
	 * @param comboConfiguracionesPlan new combo configuraciones plan
	 */
	public void setComboConfiguracionesPlan(List<KeyValueObject> comboConfiguracionesPlan) {
		this.comboConfiguracionesPlan = comboConfiguracionesPlan;
	}

	/**
	 * Modificar canal disabled.
	 *
	 * @param canalDisabled new canal disabled
	 */
	public void setCanalDisabled(String canalDisabled) {
		this.canalDisabled = canalDisabled;
	}

	/**
	 * Obtener combo servicio organismos.
	 *
	 * @return combo servicio organismos
	 */
	public List<KeyValueObject> getComboServicioOrganismos() {
		return comboServicioOrganismos;
	}

	/**
	 * Modificar combo servicio organismos.
	 *
	 * @param comboServicioOrganismos new combo servicio organismos
	 */
	public void setComboServicioOrganismos(List<KeyValueObject> comboServicioOrganismos) {
		this.comboServicioOrganismos = comboServicioOrganismos;
	}

	/**
	 * Obtener combo canales.
	 *
	 * @return combo canales
	 */
	public List<KeyValueObject> getComboCanales() {
		return comboCanales;
	}

	/**
	 * Modificar combo canales.
	 *
	 * @param comboCanales new combo canales
	 */
	public void setComboCanales(List<KeyValueObject> comboCanales) {
		this.comboCanales = comboCanales;
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
	 * Modificar parametro servidor id.
	 *
	 * @param parametroServidorId new parametro servidor id
	 */
	public void setParametroServidorId(String parametroServidorId) {
		this.parametroServidorId = parametroServidorId;
	}

	/**
	 * Obtener combo aplicaciones.
	 *
	 * @return combo aplicaciones
	 */
	public List<KeyValueObject> getComboAplicaciones() {
		return comboAplicaciones;
	}

	/**
	 * Modificar combo aplicaciones.
	 *
	 * @param comboAplicaciones new combo aplicaciones
	 */
	public void setComboAplicaciones(List<KeyValueObject> comboAplicaciones) {
		this.comboAplicaciones = comboAplicaciones;
	}

	/**
	 * Obtener lista servicios.
	 *
	 * @return lista servicios
	 */
	public List<ServicioBean> getListaServicios() {
		return listaServicios;
	}

	/**
	 * Modificar lista servicios.
	 *
	 * @param listaServicios new lista servicios
	 */
	public void setListaServicios(List<ServicioBean> listaServicios) {
		this.listaServicios = listaServicios;
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
	 * Obtener id servicio.
	 *
	 * @return id servicio
	 */
	public String getIdServicio() {
		return idServicio;
	}

	/**
	 * Modificar id servicio.
	 *
	 * @param idServicio new id servicio
	 */
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * Obtener servicio.
	 *
	 * @return servicio
	 */
	public ServicioBean getServicio() {
		return servicio;
	}

	/**
	 * Modificar servicio.
	 *
	 * @param servicio new servicio
	 */
	public void setServicio(ServicioBean servicio) {
		this.servicio = servicio;
	}

	/**
	 * Obtener lista servidores detalle.
	 *
	 * @return lista servidores detalle
	 */
	public List<ServidorBean> getListaServidoresDetalle() {
		return listaServidoresDetalle;
	}

	/**
	 * Modificar lista servidores detalle.
	 *
	 * @param listaServidoresDetalle new lista servidores detalle
	 */
	public void setListaServidoresDetalle(List<ServidorBean> listaServidoresDetalle) {
		this.listaServidoresDetalle = listaServidoresDetalle;
	}

	/**
	 * Obtener lista proveedor SMS detalle.
	 *
	 * @return lista proveedor SMS detalle
	 */
	public List<ProveedorSMSBean> getListaProveedorSMSDetalle() {
		return listaProveedorSMSDetalle;
	}

	/**
	 * Modificar lista proveedor SMS detalle.
	 *
	 * @param listaProveedorSMSDetalle new lista proveedor SMS detalle
	 */
	public void setListaProveedorSMSDetalle(List<ProveedorSMSBean> listaProveedorSMSDetalle) {
		this.listaProveedorSMSDetalle = listaProveedorSMSDetalle;
	}

	/**
	 * Obtener parametro servidor id.
	 *
	 * @return parametro servidor id
	 */
	public String getParametroServidorId() {
		return parametroServidorId;
	}

	/**
	 * Obtener combo configuraciones.
	 *
	 * @return combo configuraciones
	 */
	public List<KeyValueObject> getComboConfiguraciones() {
		return comboConfiguraciones;
	}

	/**
	 * Modificar combo configuraciones.
	 *
	 * @param comboConfiguraciones new combo configuraciones
	 */
	public void setComboConfiguraciones(List<KeyValueObject> comboConfiguraciones) {
		this.comboConfiguraciones = comboConfiguraciones;
	}

	/**
	 * Obtener servicio proveedor SMS.
	 *
	 * @return servicio proveedor SMS
	 */
	public ServicioProveedorSMS getServicioProveedorSMS() {
		return servicioProveedorSMS;
	}

	/**
	 * Modificar servicio proveedor SMS.
	 *
	 * @param servicioProveedorSMS new servicio proveedor SMS
	 */
	public void setServicioProveedorSMS(ServicioProveedorSMS servicioProveedorSMS) {
		this.servicioProveedorSMS = servicioProveedorSMS;
	}

	/**
	 * Obtener servicio receptor SMS.
	 *
	 * @return servicio receptor SMS
	 */
	public ServicioReceptorSMS getServicioReceptorSMS() {
		return servicioReceptorSMS;
	}

	/**
	 * Modificar servicio receptor SMS.
	 *
	 * @param servicioReceptorSMS new servicio receptor SMS
	 */
	public void setServicioReceptorSMS(ServicioReceptorSMS servicioReceptorSMS) {
		this.servicioReceptorSMS = servicioReceptorSMS;
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
	 * Obtener lista servidores servicios.
	 *
	 * @return lista servidores servicios
	 */
	public List<ServidoresServiciosBean> getListaServidoresServicios() {
		return listaServidoresServicios;
	}

	/**
	 * Modificar lista servidores servicios.
	 *
	 * @param listaServidoresServicios new lista servidores servicios
	 */
	public void setListaServidoresServicios(List<ServidoresServiciosBean> listaServidoresServicios) {
		this.listaServidoresServicios = listaServidoresServicios;
	}

	/**
	 * Obtener lista servicio organismos.
	 *
	 * @return lista servicio organismos
	 */
	public List<ServicioOrganismosBean> getListaServicioOrganismos() {
		return listaServicioOrganismos;
	}

	/**
	 * Modificar lista seervicios organismos.
	 *
	 * @param listaServicioOrganismos new lista seervicios organismos
	 */
	public void setListaSeerviciosOrganismos(List<ServicioOrganismosBean> listaServicioOrganismos) {
		this.listaServicioOrganismos = listaServicioOrganismos;
	}

	/**
	 * Obtener lista planificaciones servicio.
	 *
	 * @return lista planificaciones servicio
	 */
	public List<PlanificacionBean> getListaPlanificacionesServicio() {
		return listaPlanificacionesServicio;
	}

	/**
	 * Modificar lista planificaciones servicio.
	 *
	 * @param listaPlanificacionesServicio new lista planificaciones servicio
	 */
	public void setListaPlanificacionesServicio(List<PlanificacionBean> listaPlanificacionesServicio) {
		this.listaPlanificacionesServicio = listaPlanificacionesServicio;
	}

	/**
	 * Obtener servicio planificacion.
	 *
	 * @return servicio planificacion
	 */
	public ServicioPlanificacion getServicioPlanificacion() {
		return servicioPlanificacion;
	}

	/**
	 * Modificar servicio planificacion.
	 *
	 * @param servicioPlanificacion new servicio planificacion
	 */
	public void setServicioPlanificacion(ServicioPlanificacion servicioPlanificacion) {
		this.servicioPlanificacion = servicioPlanificacion;
	}

	/**
	 * Obtener new historificacion.
	 *
	 * @return new historificacion
	 */
	public Integer getNewHistorificacion() {
		return newHistorificacion;
	}

	/**
	 * Modificar new historificacion.
	 *
	 * @param newHistorificacion new new historificacion
	 */
	public void setNewHistorificacion(Integer newHistorificacion) {
		this.newHistorificacion = newHistorificacion;
	}

	/**
	 * Obtener new conservacion.
	 *
	 * @return new conservacion
	 */
	public Integer getNewConservacion() {
		return newConservacion;
	}

	/**
	 * Modificar new conservacion.
	 *
	 * @param newConservacion new new conservacion
	 */
	public void setNewConservacion(Integer newConservacion) {
		this.newConservacion = newConservacion;
	}

	/**
	 * Obtener check password.
	 *
	 * @return check password
	 */
	public String getCheckPassword() {
		return checkPassword;
	}

	/**
	 * Modificar check password.
	 *
	 * @param checkPassword new check password
	 */
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	/**
	 * Obtener servicio servidor push.
	 *
	 * @return servicio servidor push
	 */
	public ServicioServidorPush getServicioServidorPush() {
		return servicioServidorPush;
	}

	/**
	 * Modificar servicio servidor push.
	 *
	 * @param servicioServidorPush new servicio servidor push
	 */
	public void setServicioServidorPush(ServicioServidorPush servicioServidorPush) {
		this.servicioServidorPush = servicioServidorPush;
	}

	/**
	 * Obtener new plataforma android.
	 *
	 * @return new plataforma android
	 */
	public String getNewPlataformaAndroid() {
		return newPlataformaAndroid;
	}

	/**
	 * Modificar new plataforma android.
	 *
	 * @param newPlataformaAndroid new new plataforma android
	 */
	public void setNewPlataformaAndroid(String newPlataformaAndroid) {
		this.newPlataformaAndroid = newPlataformaAndroid;
	}

	/**
	 * Obtener new plataformai OS.
	 *
	 * @return new plataformai OS
	 */
	public String getNewPlataformaiOS() {
		return newPlataformaiOS;
	}

	/**
	 * Modificar new plataformai OS.
	 *
	 * @param newPlataformaiOS new new plataformai OS
	 */
	public void setNewPlataformaiOS(String newPlataformaiOS) {
		this.newPlataformaiOS = newPlataformaiOS;
	}

	/**
	 * Obtener new informe activo.
	 *
	 * @return new informe activo
	 */
	public String getNewInformeActivo() {
		return newInformeActivo;
	}

	/**
	 * Modificar new informe activo.
	 *
	 * @param newInformeActivo new new informe activo
	 */
	public void setNewInformeActivo(String newInformeActivo) {
		this.newInformeActivo = newInformeActivo;
	}

	/**
	 * Obtener new agrupacion estado.
	 *
	 * @return new agrupacion estado
	 */
	public String getNewAgrupacionEstado() {
		return newAgrupacionEstado;
	}

	/**
	 * Modificar new agrupacion estado.
	 *
	 * @param newAgrupacionEstado new new agrupacion estado
	 */
	public void setNewAgrupacionEstado(String newAgrupacionEstado) {
		this.newAgrupacionEstado = newAgrupacionEstado;
	}

	/**
	 * Obtener new agrupacion cod org.
	 *
	 * @return new agrupacion cod org
	 */
	public String getNewAgrupacionCodOrg() {
		return newAgrupacionCodOrg;
	}

	/**
	 * Modificar new agrupacion cod org.
	 *
	 * @param newAgrupacionCodOrg new new agrupacion cod org
	 */
	public void setNewAgrupacionCodOrg(String newAgrupacionCodOrg) {
		this.newAgrupacionCodOrg = newAgrupacionCodOrg;
	}

	/**
	 * Obtener new agrupacion cod sia.
	 *
	 * @return new agrupacion cod sia
	 */
	public String getNewAgrupacionCodSia() {
		return newAgrupacionCodSia;
	}

	/**
	 * Modificar new agrupacion cod sia.
	 *
	 * @param newAgrupacionCodSia new new agrupacion cod sia
	 */
	public void setNewAgrupacionCodSia(String newAgrupacionCodSia) {
		this.newAgrupacionCodSia = newAgrupacionCodSia;
	}

	/**
	 * Obtener new agrupacion cod org pagador.
	 *
	 * @return new agrupacion cod org pagador
	 */
	public String getNewAgrupacionCodOrgPagador() {
		return newAgrupacionCodOrgPagador;
	}

	/**
	 * Modificar new agrupacion cod org pagador.
	 *
	 * @param newAgrupacionCodOrgPagador new new agrupacion cod org pagador
	 */
	public void setNewAgrupacionCodOrgPagador(String newAgrupacionCodOrgPagador) {
		this.newAgrupacionCodOrgPagador = newAgrupacionCodOrgPagador;
	}

	/**
	 * Obtener new informes destinatarios.
	 *
	 * @return new informes destinatarios
	 */
	public String getNewInformesDestinatarios() {
		return newInformesDestinatarios;
	}

	/**
	 * Modificar new informes destinatarios.
	 *
	 * @param newInformesDestinatarios new new informes destinatarios
	 */
	public void setNewInformesDestinatarios(String newInformesDestinatarios) {
		this.newInformesDestinatarios = newInformesDestinatarios;
	}

	/**
	 * Obtener servicio organismos.
	 *
	 * @return servicio organismos
	 */
	public ServicioOrganismosBean getServicioOrganismos() {
		return servicioOrganismos;
	}

	/**
	 * Modificar servicio organismos.
	 *
	 * @param servicioOrganismos new servicio organismos
	 */
	public void setServicioOrganismos(ServicioOrganismosBean servicioOrganismos) {
		this.servicioOrganismos = servicioOrganismos;
	}

	/**
	 * Modificar lista servicio organismos.
	 *
	 * @param listaServicioOrganismos new lista servicio organismos
	 */
	public void setListaServicioOrganismos(List<ServicioOrganismosBean> listaServicioOrganismos) {
		this.listaServicioOrganismos = listaServicioOrganismos;
	}

	/**
	 * Obtener servicio organismo.
	 *
	 * @return servicio organismo
	 */
	public ServicioOrganismo getServicioOrganismo() {
		return servicioOrganismo;
	}

	/**
	 * Modificar servicio organismo.
	 *
	 * @param servicioOrganismo new servicio organismo
	 */
	public void setServicioOrganismo(ServicioOrganismo servicioOrganismo) {
		this.servicioOrganismo = servicioOrganismo;
	}

	/**
	 * Obtener organismo.
	 *
	 * @return organismo
	 */
	public OrganismoBean getOrganismo() {
		return organismo;
	}

	/**
	 * Modificar organismo.
	 *
	 * @param organismo new organismo
	 */
	public void setOrganismo(OrganismoBean organismo) {
		this.organismo = organismo;
	}

	/**
	 * Obtener id organismo.
	 *
	 * @return id organismo
	 */
	public String getIdOrganismo() {
		return idOrganismo;
	}

	/**
	 * Modificar id organismo.
	 *
	 * @param idOrganismo new id organismo
	 */
	public void setIdOrganismo(String idOrganismo) {
		this.idOrganismo = idOrganismo;
	}

	/**
	 * Obtener id servicio organismo.
	 *
	 * @return id servicio organismo
	 */
	public String getIdServicioOrganismo() {
		return idServicioOrganismo;
	}

	/**
	 * Modificar id servicio organismo.
	 *
	 * @param idServicioOrganismo new id servicio organismo
	 */
	public void setIdServicioOrganismo(String idServicioOrganismo) {
		this.idServicioOrganismo = idServicioOrganismo;
	}

	/**
	 * Comprueba multiorganismo.
	 *
	 * @return true, si es multiorganismo
	 */
	public boolean isMultiorganismo() {
		return isMultiorganismo;
	}

	/**
	 * Modificar multiorganismo.
	 *
	 * @param isMultiorganismo new multiorganismo
	 */
	public void setMultiorganismo(boolean isMultiorganismo) {
		this.isMultiorganismo = isMultiorganismo;
	}

	/**
	 * Obtener servidor organismos.
	 *
	 * @return servidor organismos
	 */
	public ServidoresOrganismosBean getServidorOrganismos() {
		return servidorOrganismos;
	}

	/**
	 * Modificar servidor organismos.
	 *
	 * @param servidorOrganismos new servidor organismos
	 */
	public void setServidorOrganismos(ServidoresOrganismosBean servidorOrganismos) {
		this.servidorOrganismos = servidorOrganismos;
	}

	/**
	 * Obtener check del list servicios organismos.
	 *
	 * @return check del list servicios organismos
	 */
	public String[] getCheckDelListServiciosOrganismos() {
		return checkDelListServiciosOrganismos;
	}

	/**
	 * Modificar check del list servicios organismos.
	 *
	 * @param checkDelListServiciosOrganismos new check del list servicios organismos
	 */
	public void setCheckDelListServiciosOrganismos(String[] checkDelListServiciosOrganismos) {
		this.checkDelListServiciosOrganismos = checkDelListServiciosOrganismos;
	}

	/**
	 * Obtener activo.
	 *
	 * @return activo
	 */
	public String getActivo() {
		return activo;
	}

	/**
	 * Modificar activo.
	 *
	 * @param activo new activo
	 */
	public void setActivo(String activo) {
		this.activo = activo;
	}

	/**
	 * Obtener canal disabled.
	 *
	 * @return canal disabled
	 */
	public String getCanalDisabled() {
		return canalDisabled;
	}

	/**
	 * Obtener readonly.
	 *
	 * @return readonly
	 */
	public String getReadonly() {
		return readonly;
	}

	/**
	 * Modificar readonly.
	 *
	 * @param readonly new readonly
	 */
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	/**
	 * Obtener new activo.
	 *
	 * @return new activo
	 */
	public String getNewActivo() {
		return newActivo;
	}

	/**
	 * Modificar new activo.
	 *
	 * @param newActivo new new activo
	 */
	public void setNewActivo(String newActivo) {
		this.newActivo = newActivo;
	}

	/**
	 * Obtener new premium.
	 *
	 * @return new premium
	 */
	public String getNewPremium() {
		return newPremium;
	}

	/**
	 * Modificar new premium.
	 *
	 * @param newPremium new new premium
	 */
	public void setNewPremium(String newPremium) {
		this.newPremium = newPremium;
	}

	/**
	 * Obtener id planificacion.
	 *
	 * @return id planificacion
	 */
	public String getIdPlanificacion() {
		return idPlanificacion;
	}

	/**
	 * Modificar id planificacion.
	 *
	 * @param idPlanificacion new id planificacion
	 */
	public void setIdPlanificacion(String idPlanificacion) {
		this.idPlanificacion = idPlanificacion;
	}

	/**
	 * Obtener servidor servicio id.
	 *
	 * @return servidor servicio id
	 */
	public String getServidorServicioId() {
		return servidorServicioId;
	}

	/**
	 * Modificar servidor servicio id.
	 *
	 * @param servidorServicioId new servidor servicio id
	 */
	public void setServidorServicioId(String servidorServicioId) {
		this.servidorServicioId = servidorServicioId;
	}

	/**
	 * Obtener servicio organismo id.
	 *
	 * @return servicio organismo id
	 */
	public String getServicioOrganismoId() {
		return servicioOrganismoId;
	}

	/**
	 * Modificar servicio organismo id.
	 *
	 * @param servicioOrganismoId new servicio organismo id
	 */
	public void setServicioOrganismoId(String servicioOrganismoId) {
		this.servicioOrganismoId = servicioOrganismoId;
	}

	/**
	 * Obtener id aplicacion.
	 *
	 * @return id aplicacion
	 */
	public String getIdAplicacion() {
		return idAplicacion;
	}

	/**
	 * Modificar id aplicacion.
	 *
	 * @param idAplicacion new id aplicacion
	 */
	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	/**
	 * Obtener servicio servidor id.
	 *
	 * @return servicio servidor id
	 */
	public String getServicioServidorId() {
		return servicioServidorId;
	}

	/**
	 * Modificar servicio servidor id.
	 *
	 * @param servicioServidorId new servicio servidor id
	 */
	public void setServicioServidorId(String servicioServidorId) {
		this.servicioServidorId = servicioServidorId;
	}

	/**
	 * Obtener servidor servicio.
	 *
	 * @return servidor servicio
	 */
	public ServidoresServiciosBean getServidorServicio() {
		return servidorServicio;
	}

	/**
	 * Modificar servidor servicio.
	 *
	 * @param servidorServicio new servidor servicio
	 */
	public void setServidorServicio(ServidoresServiciosBean servidorServicio) {
		this.servidorServicio = servidorServicio;
	}

	/**
	 * Obtener check del list servidor servicios.
	 *
	 * @return check del list servidor servicios
	 */
	public String[] getCheckDelListServidorServicios() {
		return checkDelListServidorServicios;
	}

	/**
	 * Modificar check del list servidor servicios.
	 *
	 * @param checkDelListServidorServicios new check del list servidor servicios
	 */
	public void setCheckDelListServidorServicios(String[] checkDelListServidorServicios) {
		this.checkDelListServidorServicios = checkDelListServidorServicios;
	}

	/**
	 * Obtener check del list planificaciones.
	 *
	 * @return check del list planificaciones
	 */
	public String[] getCheckDelListPlanificaciones() {
		return checkDelListPlanificaciones;
	}

	/**
	 * Modificar check del list planificaciones.
	 *
	 * @param checkDelListPlanificaciones new check del list planificaciones
	 */
	public void setCheckDelListPlanificaciones(String[] checkDelListPlanificaciones) {
		this.checkDelListPlanificaciones = checkDelListPlanificaciones;
	}

	/**
	 * Obtener planificacion id.
	 *
	 * @return planificacion id
	 */
	public String getPlanificacionId() {
		return planificacionId;
	}

	/**
	 * Modificar planificacion id.
	 *
	 * @param planificacionId new planificacion id
	 */
	public void setPlanificacionId(String planificacionId) {
		this.planificacionId = planificacionId;
	}

	/**
	 * Obtener json.
	 *
	 * @return json
	 */
	public String getJson() {
		return json;
	}

	/**
	 * Modificar json.
	 *
	 * @param json new json
	 */
	public void setJson(String json) {
		this.json = json;
	}

	/**
	 * Obtener planificacion servidor.
	 *
	 * @return planificacion servidor
	 */
	public PlanificacionBean getPlanificacionServidor() {
		return planificacionServidor;
	}

	/**
	 * Modificar planificacion servidor.
	 *
	 * @param planificacionServidor new planificacion servidor
	 */
	public void setPlanificacionServidor(PlanificacionBean planificacionServidor) {
		this.planificacionServidor = planificacionServidor;
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
	
	/**
	 * Obtener logo.
	 *
	 * @return logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * Modificar logo.
	 *
	 * @param logo new logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * Obtener background.
	 *
	 * @return background
	 */
	public String getBackground() {
		return background;
	}

	/**
	 * Modificar background.
	 *
	 * @param background new background
	 */
	public void setBackground(String background) {
		this.background = background;
	}

	/**
	 * Obtener icon.
	 *
	 * @return icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Modificar icon.
	 *
	 * @param icon new icon
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * Obtener vapid public key.
	 *
	 * @return the vapidPublicKey
	 */
	public String getVapidPublicKey() {
		return vapidPublicKey;
	}

	/**
	 * Modificar vapid public key.
	 *
	 * @param vapidPublicKey the vapidPublicKey to set
	 */
	public void setVapidPublicKey(String vapidPublicKey) {
		this.vapidPublicKey = vapidPublicKey;
	}

	/**
	 * Obtener vapid private key.
	 *
	 * @return the vapidPrivateKey
	 */
	public String getVapidPrivateKey() {
		return vapidPrivateKey;
	}

	/**
	 * Modificar vapid private key.
	 *
	 * @param vapidPrivateKey the vapidPrivateKey to set
	 */
	public void setVapidPrivateKey(String vapidPrivateKey) {
		this.vapidPrivateKey = vapidPrivateKey;
	}

	/**
	 * Obtener logo 64.
	 *
	 * @return the logo64
	 */
	public String getLogo64() {
		return logo64;
	}

	/**
	 * Modificar logo 64.
	 *
	 * @param logo64 the logo64 to set
	 */
	public void setLogo64(String logo64) {
		this.logo64 = logo64;
	}

	/**
	 * Obtener background 64.
	 *
	 * @return the background64
	 */
	public String getBackground64() {
		return background64;
	}

	/**
	 * Modificar background 64.
	 *
	 * @param background64 the background64 to set
	 */
	public void setBackground64(String background64) {
		this.background64 = background64;
	}

	/**
	 * Obtener icon 64.
	 *
	 * @return the icon64
	 */
	public String getIcon64() {
		return icon64;
	}

	/**
	 * Modificar icon 64.
	 *
	 * @param icon64 the icon64 to set
	 */
	public void setIcon64(String icon64) {
		this.icon64 = icon64;
	}

	/**
	 * Obtener recovery.
	 *
	 * @return recovery
	 */
	public String getRecovery() {
		return recovery;
	}


	/**
	 * Modificar recovery.
	 *
	 * @param recovery new recovery
	 */
	public void setRecovery(String recovery) {
		this.recovery = recovery;
	}
}
