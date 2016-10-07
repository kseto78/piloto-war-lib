package es.mpr.plataformamensajeria.web.action.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

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
import es.mpr.plataformamensajeria.beans.ServidoresOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.model.ServicioJPA;
import es.mpr.plataformamensajeria.model.views.ViewServidoresOrganismosJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioCanal;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioReceptorSMS;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorPush;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los servicios.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de los Servicios
 * 
 * @author i-nercya
 * 
 */

public class ServicioAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	private static final long serialVersionUID = 1L;

	private static final String REQUEST_ATTRIBUTE_TOTALSIZE = "totalSize";
	private static final String REQUEST_ATTRIBUTE_PAGESIZE = "pageSize";
	private static final Integer CANAL_SMTP_ID = 1;
	private static final Integer CANAL_SMS_ID = 2;
	private static final Integer CANAL_RECEPCION_SMS_ID = 3;
	private static final Integer CANAL_SERVIDOR_PUSH_ID = 4;
	private static final Integer VALOR_MAXIMO_PREDEFINIDO_HISTORIFICACION = 90;
	private static final Integer VALOR_MAXIMO_PREDEFINIDO_CONSERVACION = 3;
	private static final Integer VALOR_1_HISTORIFICACION = 30;
	private static final Integer VALOR_2_HISTORIFICACION = 60;
	private static final Integer VALOR_3_HISTORIFICACION = 90;
	private static final Integer VALOR_1_CONSERVACION = 1;
	private static final Integer VALOR_2_CONSERVACION = 2;
	private static final Integer VALOR_3_CONSERVACION = 3;
	private static final String RECOVERY = "recovery";

	private static final Integer PAGESIZE = Integer.valueOf(20); // Elementos por pagina
	List<KeyValueObject> comboAplicaciones = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboCanales = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboConfiguraciones = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboConfiguracionesPlan = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServicioOrganismos = new ArrayList<KeyValueObject>();
	private String servicioServidorId;
	private String idAplicacion;
	private String idPlanificacion;
	private String idOrganismo;
	private String idServicioOrganismo;
	private String newActivo;
	private String newPremium;
	private String newPlataformaAndroid;
	private String newPlataformaiOS;
	private String canalDisabled = null;
	private String readonly = "false";
	private Integer newHistorificacion = 1;
	private Integer newConservacion = 1;
	private String checkPassword;
	private String newInformeActivo;
	private String newAgrupacionEstado;
	private String newAgrupacionCodOrg;
	private String newAgrupacionCodSia;
	private String newAgrupacionCodOrgPagador;
	private String newInformesDestinatarios;
	private boolean isMultiorganismo;
	private String recovery = "";
	private Map session;
	private String activo ="";

	private SendMailService sendMailService = new SendMailService();

	public String getCanalDisabled() {
		return canalDisabled;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getNewActivo() {
		return newActivo;
	}

	public void setNewActivo(String newActivo) {
		this.newActivo = newActivo;
	}
	public String getNewPremium() {
		return newPremium;
	}

	public void setNewPremium(String newPremium) {
		this.newPremium = newPremium;
	}

	public String getIdPlanificacion() {
		return idPlanificacion;
	}

	public void setIdPlanificacion(String idPlanificacion) {
		this.idPlanificacion = idPlanificacion;
	}

	private String servidorServicioId;

	public String getServidorServicioId() {
		return servidorServicioId;
	}

	public void setServidorServicioId(String servidorServicioId) {
		this.servidorServicioId = servidorServicioId;
	}

	private String servicioOrganismoId;

	public String getServicioOrganismoId() {
		return servicioOrganismoId;
	}

	public void setServicioOrganismoId(String servicioOrganismoId) {
		this.servicioOrganismoId = servicioOrganismoId;
	}

	public String getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	public String getServicioServidorId() {
		return servicioServidorId;
	}

	public void setServicioServidorId(String servicioServidorId) {
		this.servicioServidorId = servicioServidorId;
	}

	public List<ServicioBean> listaServicios = null;
	public List<PlanificacionBean> listaPlanificacionesServicio = null;
	public List<ServidoresServiciosBean> listaServidoresServicios = null;
	public List<ServicioOrganismosBean> listaServicioOrganismos = null;

	private ServicioPlanificacion servicioPlanificacion;

	public ServidoresServiciosBean getServidorServicio() {
		return servidorServicio;
	}

	public void setServidorServicio(ServidoresServiciosBean servidorServicio) {
		this.servidorServicio = servidorServicio;
	}

	private ServicioOrganismo servicioOrganismo;
	private ServicioServicio servicioServicio;
	private ServicioAplicacion servicioAplicacion;
	private ServicioProveedorSMS servicioProveedorSMS;
	private ServicioReceptorSMS servicioReceptorSMS;
	private ServicioServidor servicioServidor;
	private ServicioServidorPush servicioServidorPush;
	private ServicioCanal servicioCanal;
	private ServidoresServiciosBean servidorServicio;
	private ServicioOrganismosBean servicioOrganismos;
	private ServidoresOrganismosBean servidorOrganismos;
	private String idServicio;
	private String resultCount;
	private String[] checkDelList;
	private String[] checkDelListPlanificaciones;
	private String[] checkDelListServidorServicios;
	private String[] checkDelListServiciosOrganismos;

	private PlanificacionBean planificacionServidor;

	private OrganismoBean organismo;

	public String[] getCheckDelListServidorServicios() {
		return checkDelListServidorServicios;
	}

	public void setCheckDelListServidorServicios(String[] checkDelListServidorServicios) {
		this.checkDelListServidorServicios = checkDelListServidorServicios;
	}

	public String[] getCheckDelListPlanificaciones() {
		return checkDelListPlanificaciones;
	}

	public void setCheckDelListPlanificaciones(String[] checkDelListPlanificaciones) {
		this.checkDelListPlanificaciones = checkDelListPlanificaciones;
	}

	private ServicioBean servicio;

	private String parametroServidorId;
	private String planificacionId;

	public String getPlanificacionId() {
		return planificacionId;
	}

	public void setPlanificacionId(String planificacionId) {
		this.planificacionId = planificacionId;
	}

	private List<ServidorBean> listaServidoresDetalle = new ArrayList<ServidorBean>();
	private List<ProveedorSMSBean> listaProveedorSMSDetalle = new ArrayList<ProveedorSMSBean>();

	public String newSearch() throws BaseException {
		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SkipValidation
	public String searchServicios() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";

		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para ordenar

		if (servicio != null)
			if (servicio.getNombre() != null && servicio.getNombre().length() <= 0)
				servicio.setNombre(null);

		int inicio = (page - 1) * PAGESIZE;
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServicioBean> result = servicioServicio.getServicios(inicio, (export) ? -1 : PAGESIZE, order, columnSort, servicio);
		Integer totalSize = result.getTotalList();

		listaServicios = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);

		if (!export) {
			getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
		} else {
			getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
		}

		if (listaServicios != null && !listaServicios.isEmpty()) {
			for (int indice = 0; indice < listaServicios.size(); indice++) {

				ServicioBean servicio = listaServicios.get(indice);
				servicio.setNombre(StringEscapeUtils.escapeHtml(servicio.getNombre()));
				servicio.setAplicacionNombre(StringEscapeUtils.escapeCsv(servicio.getAplicacionNombre()));
				servicio.setDescripcion(StringEscapeUtils.escapeHtml(servicio.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	public String execute() throws BaseException {

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";

		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para ordenar

		if (servicio != null)
			if (servicio.getNombre() != null && servicio.getNombre().length() <= 0)
				servicio.setNombre(null);

		int inicio = (page - 1) * PAGESIZE;
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServicioBean> result = servicioServicio.getServicios(inicio, (export) ? -1 : PAGESIZE, order, columnSort, servicio);
		Integer totalSize = result.getTotalList();

		listaServicios = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);

		if (!export) {
			getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
		} else {
			getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
		}

		if (listaServicios != null && !listaServicios.isEmpty()) {
			for (int indice = 0; indice < listaServicios.size(); indice++) {

				ServicioBean servicio = listaServicios.get(indice);
				servicio.setNombre(StringEscapeUtils.escapeHtml(servicio.getNombre()));
				servicio.setAplicacionNombre(StringEscapeUtils.escapeCsv(servicio.getAplicacionNombre()));
				servicio.setDescripcion(StringEscapeUtils.escapeHtml(servicio.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	String json;

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

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
					listado = (ArrayList<ServicioBean>) servicioServicio.getServiciosByAplicacionId(Integer.valueOf(idAplicacion));
				} else {
					String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
					Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
					listado = (ArrayList<ServicioBean>) servicioServicio.getServicios(rolUsuario, idUsuario);
				}

				for (ServicioBean servicio : listado) {
					rOptions.getItems().add(new SelectOption(servicio.getServicioId().toString(), servicio.getNombre()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			json = new Gson().toJson(rOptions);
		}
		return SUCCESS;

	}

	public String aplicacionSelectEvent() {

		if (servicio != null) {
			AplicacionBean aplicacion = new AplicacionBean();
			aplicacion.setId(servicio.getAplicacionId());
			try {
				aplicacion = servicioAplicacion.loadAplicacion(aplicacion);
				servicio.setResponsableFuncionalEmail(aplicacion.getResponsableFuncionalEmail());
				servicio.setResponsableFuncionalNombre(aplicacion.getResponsableFuncionalNombre());
				servicio.setResponsableTecnicoEmail(aplicacion.getResponsableTecnicoEmail());
				servicio.setResponsableTecnicoNombre(aplicacion.getResponsableTecnicoNombre());

			} catch (BusinessException e) {

			}

		}
		return SUCCESS;
	}

	public String activarMultiorganismoSelectEvent() {
		boolean sw = true;
		if (servicio != null) {

			try {
				ServicioBean servicioBBDD;
				servicio.setId(Integer.valueOf(idServicio));
				servicioBBDD = servicioServicio.loadServicio(servicio);

				if (null != servicioBBDD.getMultiorganismo() && servicioBBDD.getMultiorganismo() == 1) {
					if (null != listaServicioOrganismos && listaServicioOrganismos.size() > 0) {
						addFieldErrorSession(this.getText("plataforma.servicio.servidorservicio.field.listaOrganismosNoVacia"));
						sw = false;
					}
					servicioBBDD.setMultiorganismo(0);
					servicioBBDD.setIsMultiorganismo("false");
					
				} else {
					servicioBBDD.setMultiorganismo(1);
					servicioBBDD.setIsMultiorganismo("true");
					
				}
				if (sw) {
					servicioServicio.updateServicio(servicioBBDD);

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

			}

		}
		return SUCCESS;
//		String[] aux = activo.split("_");
//		activo = aux[0];
//		idServicio = aux[1];
//		if (idServicio != null) {
//			
//			try {
//				
//				if (null != activo && activo.equals("1")) {
//					if (null != listaServicioOrganismos && listaServicioOrganismos.size() > 0) {
//						addFieldErrorSession(this.getText("plataforma.servicio.servidorservicio.field.listaOrganismosNoVacia"));
//						
//					}
//					servicioServicio.actualizarCheckMultiorganismo(true, Integer.parseInt(idServicio));
//					return SUCCESS;
//				} else {
//					servicioServicio.actualizarCheckMultiorganismo(false, Integer.parseInt(idServicio));
//					return SUCCESS;
//				}
//				
//			} catch (BusinessException e) {
//				return ERROR;
//			}
//
//		}
//		return SUCCESS;
	}

	public String create() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";

		if (servicio == null) {
			// throw new BusinessException("EL servicio recibido es nulo");
			addActionErrorSession(this.getText("plataforma.servicio.create.error"));
			return ERROR;
		} else {
			if (newActivo != null && newActivo.equals("true")) {
				servicio.setActivo(Integer.valueOf(1));
			} else {
				servicio.setActivo(Integer.valueOf(0));
			}
			if (newPremium != null && newPremium.equals("true")) {
				servicio.setPremium(Integer.valueOf(1));
			} else {
				servicio.setPremium(Integer.valueOf(0));
			}
			if (servicio.getCanalId() != null && servicio.getCanalId().equals(Integer.valueOf(CANAL_SERVIDOR_PUSH_ID))) {
				if (newPlataformaAndroid != null && newPlataformaAndroid.equals("true")) {
					servicio.setAndroidPlataforma(Integer.valueOf(1));
				} else {
					servicio.setAndroidPlataforma(Integer.valueOf(0));
				}
				if (newPlataformaiOS != null && newPlataformaiOS.equals("true")) {
					servicio.setIosPlataforma(Integer.valueOf(1));
				} else {
					servicio.setIosPlataforma(Integer.valueOf(0));
				}
			}

			// Informes
			if (newInformeActivo != null && newInformeActivo.equals("true")) {
				servicio.setInformesActivo(Integer.valueOf(1));
			} else {
				servicio.setInformesActivo(Integer.valueOf(0));
			}

			if (servicio.getInformesActivo() != null && servicio.getInformesActivo().equals(Integer.valueOf(1))) {

				if (newInformesDestinatarios != null && !newInformesDestinatarios.isEmpty()) {
					servicio.setInformesDestinatarios(newInformesDestinatarios.trim()); // Eliminamos los espacios
				}

				if (newAgrupacionEstado != null && newAgrupacionEstado.equals("true")) {
					servicio.setAgrupacionEstado(Integer.valueOf(1));
				} else {
					servicio.setAgrupacionEstado(Integer.valueOf(0));
				}

				if (servicio.getCanalId().equals(Integer.valueOf(CANAL_SMTP_ID)) || servicio.getCanalId().equals(Integer.valueOf(CANAL_SERVIDOR_PUSH_ID))) {
					if (newAgrupacionCodOrg != null && newAgrupacionCodOrg.equals("true")) {
						servicio.setAgrupacionCodOrg(Integer.valueOf(1));
					} else {
						servicio.setAgrupacionCodOrg(Integer.valueOf(0));
					}
					if (newAgrupacionCodSia != null && newAgrupacionCodSia.equals("true")) {
						servicio.setAgrupacionCodSia(Integer.valueOf(1));
					} else {
						servicio.setAgrupacionCodSia(Integer.valueOf(0));
					}
				} else if (servicio.getCanalId().equals(Integer.valueOf(CANAL_SMS_ID))) {
					if (newAgrupacionCodOrg != null && newAgrupacionCodOrg.equals("true")) {
						servicio.setAgrupacionCodOrg(Integer.valueOf(1));
					} else {
						servicio.setAgrupacionCodOrg(Integer.valueOf(0));
					}
					if (newAgrupacionCodSia != null && newAgrupacionCodSia.equals("true")) {
						servicio.setAgrupacionCodSia(Integer.valueOf(1));
					} else {
						servicio.setAgrupacionCodSia(Integer.valueOf(0));
					}
					if (newAgrupacionCodOrgPagador != null && newAgrupacionCodOrgPagador.equals("true")) {
						servicio.setAgrupacionCodOrgPagador(Integer.valueOf(1));
					} else {
						servicio.setAgrupacionCodOrgPagador(Integer.valueOf(0));
					}
				}
			}

			if (newHistorificacion != null) {
				servicio.setHistorificacion(newHistorificacion);
				// if(!newHistorificacion.equals(4)){
				// servicio.setHistorificacionInput(null);
				// }
			}
			if (newConservacion != null) {
				servicio.setConservacion(newConservacion);
				// if(!newConservacion.equals(4)){
				// servicio.setConservacionInput(null);
				// }
			}

			// Historificacion
			Integer historificacion = servicio.getHistorificacion();
			if (null != historificacion) {
				switch (historificacion) {
				case 1:
					servicio.setHistorificacion(VALOR_1_HISTORIFICACION);
					break;
				case 2:
					servicio.setHistorificacion(VALOR_2_HISTORIFICACION);
					break;
				case 3:
					servicio.setHistorificacion(VALOR_3_HISTORIFICACION);
					break;
				case 4:
					servicio.setHistorificacion(servicio.getHistorificacionInput());
					break;
				default:
					servicio.setHistorificacion(VALOR_1_HISTORIFICACION);
					break;
				}
				// servicio.setMotivoHistorificacion(servicio.getMotivoHistorificacion());
			} else {
				servicio.setHistorificacion(historificacion);
			}
			// Conservacion

			Integer conservacion = servicio.getConservacion();
			if (null != conservacion) {
				switch (conservacion) {
				case 1:
					servicio.setConservacion(VALOR_1_CONSERVACION);
					break;
				case 2:
					servicio.setConservacion(VALOR_2_CONSERVACION);
					break;
				case 3:
					servicio.setConservacion(VALOR_3_CONSERVACION);
					break;
				case 4:
					servicio.setConservacion(servicio.getConservacionInput());
					break;
				default:
					servicio.setConservacion(VALOR_1_CONSERVACION);
					break;
				}
				// if(null!= servicio.getMotivoConservacion()){
				// servicio.setMotivoConservacion(servicio.getMotivoConservacion());
				// } else {
				// servicio.setMotivoConservacion(null);
				// }
			} else {
				servicio.setConservacion(conservacion);
			}

			// Integer conservacion = servicio.getConservacion();
			// if(conservacion != null){
			// switch (conservacion) {
			// case 1: servicio.setConservacion(VALOR_1_CONSERVACION);
			// break;
			// case 2: servicio.setConservacion(VALOR_2_CONSERVACION);
			// break;
			// case 3: servicio.setConservacion(VALOR_3_CONSERVACION);
			// break;
			// case 4: servicio.setConservacion(servicio.getConservacionInput());
			// break;
			// default: servicio.setConservacion(VALOR_1_CONSERVACION);
			// break;
			// }
			// if(servicio.getConservacionInput()!= null && servicio.getConservacion()>VALOR_3_CONSERVACION){
			// servicio.setMotivoConservacion(servicio.getMotivoConservacion());
			// } else {
			// servicio.setMotivoConservacion(null);
			// }
			// } else {
			// servicio.setConservacion(conservacion);
			// }

			ServicioJPA servicioJPA = servicioServicio.getServicioJPA(servicio);

			boolean validServicio = false;

			if (servicioJPA != null) {
				validServicio = validServicio(servicioJPA);
			}

			if (!validServicio) {
				// addActionErrorSession(this.getText("plataforma.servicio.create.error"));
				return ERROR;
			} else {

				Integer idServicio = servicioServicio.newServicio(servicio);
				this.idServicio = idServicio.toString();

				addActionMessageSession(this.getText("plataforma.servicio.create.ok"));
			}
		}
		return SUCCESS;

	}

	public String createServicioAplicacion() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (servicio == null) {
			// throw new BusinessException("EL servicio recibido es nulo");
			addActionErrorSession(this.getText("plataforma.servicio.create.error"));
			return ERROR;
		} else {

			if (servicio.getCanalId() != null && servicio.getCanalId().equals(Integer.valueOf(CANAL_SERVIDOR_PUSH_ID))) {
				if (newPlataformaAndroid != null && newPlataformaAndroid.equals("true")) {
					servicio.setAndroidPlataforma(Integer.valueOf(1));
				} else {
					servicio.setAndroidPlataforma(Integer.valueOf(0));
				}
				if (newPlataformaiOS != null && newPlataformaiOS.equals("true")) {
					servicio.setIosPlataforma(Integer.valueOf(1));
				} else {
					servicio.setIosPlataforma(Integer.valueOf(0));
				}
			}

			// El servicio se crea desactivado y pendiente de aprobacion
			servicio.setActivo(Integer.valueOf(0));
			servicio.setPendienteAprobacion(Integer.valueOf(1));
			servicio.setPremium(Integer.valueOf(0));

			// Informes
			if (newInformeActivo != null && newInformeActivo.equals("true")) {
				servicio.setInformesActivo(Integer.valueOf(1));
			} else {
				servicio.setInformesActivo(Integer.valueOf(0));
			}

			if (servicio.getInformesActivo() != null && servicio.getInformesActivo().equals(Integer.valueOf(1))) {

				if (newInformesDestinatarios != null && !newInformesDestinatarios.isEmpty()) {
					servicio.setInformesDestinatarios(newInformesDestinatarios.trim()); // Eliminamos los espacios
				}

				if (newAgrupacionEstado != null && newAgrupacionEstado.equals("true")) {
					servicio.setAgrupacionEstado(Integer.valueOf(1));
				} else {
					servicio.setAgrupacionEstado(Integer.valueOf(0));
				}

				if (servicio.getCanalId().equals(Integer.valueOf(CANAL_SMTP_ID)) || servicio.getCanalId().equals(Integer.valueOf(CANAL_SERVIDOR_PUSH_ID))) {
					if (newAgrupacionCodOrg != null && newAgrupacionCodOrg.equals("true")) {
						servicio.setAgrupacionCodOrg(Integer.valueOf(1));
					} else {
						servicio.setAgrupacionCodOrg(Integer.valueOf(0));
					}
					if (newAgrupacionCodSia != null && newAgrupacionCodSia.equals("true")) {
						servicio.setAgrupacionCodSia(Integer.valueOf(1));
					} else {
						servicio.setAgrupacionCodSia(Integer.valueOf(0));
					}
				} else if (servicio.getCanalId().equals(Integer.valueOf(CANAL_SMS_ID))) {
					if (newAgrupacionCodOrg != null && newAgrupacionCodOrg.equals("true")) {
						servicio.setAgrupacionCodOrg(Integer.valueOf(1));
					} else {
						servicio.setAgrupacionCodOrg(Integer.valueOf(0));
					}
					if (newAgrupacionCodSia != null && newAgrupacionCodSia.equals("true")) {
						servicio.setAgrupacionCodSia(Integer.valueOf(1));
					} else {
						servicio.setAgrupacionCodSia(Integer.valueOf(0));
					}
					if (newAgrupacionCodOrgPagador != null && newAgrupacionCodOrgPagador.equals("true")) {
						servicio.setAgrupacionCodOrgPagador(Integer.valueOf(1));
					} else {
						servicio.setAgrupacionCodOrgPagador(Integer.valueOf(0));
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
					servicio.setHistorificacion(VALOR_1_HISTORIFICACION);
					break;
				case 2:
					servicio.setHistorificacion(VALOR_2_HISTORIFICACION);
					break;
				case 3:
					servicio.setHistorificacion(VALOR_3_HISTORIFICACION);
					break;
				case 4:
					servicio.setHistorificacion(servicio.getHistorificacionInput());
					break;
				default:
					servicio.setHistorificacion(VALOR_1_HISTORIFICACION);
					break;
				}
				// servicio.setMotivoHistorificacion(servicio.getMotivoHistorificacion());

				// if(servicio.getHistorificacionInput()!=null && servicio.getHistorificacion()>VALOR_3_HISTORIFICACION){
				// servicio.setMotivoHistorificacion(servicio.getMotivoHistorificacion());
				// } else {
				// servicio.setMotivoHistorificacion(null);
				// }
			} else {
				servicio.setHistorificacion(null);
			}
			// Conservacion
			Integer conservacion = servicio.getConservacion();
			if (conservacion != null) {
				switch (conservacion) {
				case 1:
					servicio.setConservacion(VALOR_1_CONSERVACION);
					break;
				case 2:
					servicio.setConservacion(VALOR_2_CONSERVACION);
					break;
				case 3:
					servicio.setConservacion(VALOR_3_CONSERVACION);
					break;
				case 4:
					servicio.setConservacion(servicio.getConservacionInput());
					break;
				default:
					servicio.setConservacion(VALOR_1_CONSERVACION);
					break;
				}
				// if(null!= servicio.getMotivoConservacion()){
				// servicio.setMotivoConservacion(servicio.getMotivoConservacion());
				// } else {
				// servicio.setMotivoConservacion(null);
				// }
				// if(servicio.getConservacionInput()!= null && servicio.getConservacion()>VALOR_3_CONSERVACION){
				// servicio.setMotivoConservacion(servicio.getMotivoConservacion());
				// } else {
				// servicio.setMotivoConservacion(null);
				// }
			} else {
				servicio.setConservacion(null);
			}

			ServicioJPA servicioJPA = servicioServicio.getServicioJPA(servicio);

			boolean validServicio = false;

			if (servicioJPA != null) {
				validServicio = validServicio(servicioJPA);
			}

			if (!validServicio) {
				// addActionErrorSession(this.getText("plataforma.servicio.create.error"));
				return ERROR;
			} else {

				Integer idServicio = servicioServicio.newServicio(servicio);
				this.idServicio = idServicio.toString();

				addActionMessageSession(this.getText("plataforma.servicio.create.ok"));

				// Se envia un correo informativo a la lista de correo
				try {
					List<AplicacionBean> listaAplicaciones = servicioAplicacion.getAplicaciones();
					if (null != listaAplicaciones && !listaAplicaciones.isEmpty()) {
						for (AplicacionBean aplicacion : listaAplicaciones) {
							if (aplicacion.getAplicacionId().equals(servicio.getAplicacionId())) {
								sendMailService.initServicio(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombre(), aplicacion.getNombre(), idServicio.toString());
								break;
							}
						}
					}
				} catch (ServletException e) {
					return SUCCESS;
				}

			}
		}
		return SUCCESS;

	}

	private boolean validServicio(ServicioJPA servicioJPA) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmptyNumber(servicioJPA.getAplicacionId())) {
			addActionErrorSession(this.getText("plataforma.servicio.field.aplicacionId.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmptyNumber(servicioJPA.getCanalId())) {
			addActionErrorSession(this.getText("plataforma.servicio.field.canal.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(servicioJPA.getNombre())) {
			addActionErrorSession(this.getText("plataforma.servicio.field.nombre.error"));
			sw = false;
		}
		/*
		 * if(PlataformaMensajeriaUtil.isEmpty(servicioJPA.getDescripcion())){ addActionErrorSession(this.getText("plataforma.servicio.field.descripcion.error")); sw=false; }
		 */
		if (PlataformaMensajeriaUtil.isEmptyNumber(servicioJPA.getNmaxenvios())) {
			addActionErrorSession(this.getText("plataforma.servicio.field.nmaxenvios.error"));
			sw = false;
		} else if (!PlataformaMensajeriaUtil.isNumber(servicioJPA.getNmaxenvios())) {
			addActionErrorSession(this.getText("plataforma.servicio.field.nmaxenvios.format.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmptyNumber(servicioJPA.getHistorificacion())) {
			addActionErrorSession(this.getText("plataforma.servicio.field.historificacion.empty"));
			sw = false;
		} else if (servicioJPA.getHistorificacion() > VALOR_MAXIMO_PREDEFINIDO_HISTORIFICACION && servicioJPA.getMotivoHistorificacion().isEmpty()) {
			addActionErrorSession(this.getText("plataforma.servicio.field.motivoHistorificacion.empty"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmptyNumber(servicioJPA.getConservacion())) {
			addActionErrorSession(this.getText("plataforma.servicio.field.conservacion.empty"));
			sw = false;
		} else if (servicioJPA.getConservacion() > VALOR_MAXIMO_PREDEFINIDO_CONSERVACION && servicioJPA.getMotivoConservacion().isEmpty()) {
			addActionErrorSession(this.getText("plataforma.servicio.field.motivoConservacion.empty"));
			sw = false;
		}

		if (servicioJPA.getCanalId() != null && servicioJPA.getCanalId().intValue() == 3) {
			if (PlataformaMensajeriaUtil.isEmpty(servicioJPA.getNombreLoteEnvio())) {
				addActionErrorSession(this.getText("plataforma.servicio.field.nombreloteenvio.error"));
				sw = false;
			}
			if (PlataformaMensajeriaUtil.isEmpty(servicioJPA.getEndPoint())) {
				addActionErrorSession(this.getText("plataforma.servicio.field.endpoint.error"));
				sw = false;
			}

		}
		if (servicioJPA.getCanalId() != null && servicioJPA.getCanalId().intValue() == 4) {
			if (servicioJPA.getAndroidPlataforma().equals(Integer.valueOf(0)) && servicioJPA.getIosPlataforma().equals(Integer.valueOf(0))) {
				addActionErrorSession(this.getText("plataforma.servicio.field.plataforma.error"));
				sw = false;
			} else {
				if (servicioJPA.getAndroidPlataforma().equals(Integer.valueOf(1)) && PlataformaMensajeriaUtil.isEmpty(servicioJPA.getGcmProjectKey())) {
					addActionErrorSession(this.getText("plataforma.servicio.field.plataformaAndroid.error"));
					sw = false;
				}
				if (servicioJPA.getIosPlataforma().equals(Integer.valueOf(1)) && (PlataformaMensajeriaUtil.isEmpty(servicioJPA.getApnsRutaCertificado()) || PlataformaMensajeriaUtil.isEmpty(servicioJPA.getApnsPasswordCertificado()))) {
					addActionErrorSession(this.getText("plataforma.servicio.field.plataformaiOS.error"));
					sw = false;
				}
			}
			if (PlataformaMensajeriaUtil.isEmptyNumber(servicioJPA.getBadge())) {
				addActionErrorSession(this.getText("plataforma.servicio.field.badge.error"));
				sw = false;
			}
		}
		if (servicioJPA.getInformesActivo() != null && servicioJPA.getInformesActivo().equals(Integer.valueOf(1))) {
			if (PlataformaMensajeriaUtil.isEmpty(servicioJPA.getInformesDestinatarios())) {
				addActionErrorSession(this.getText("plataforma.servicio.field.informesdestinatarios.error"));
				sw = false;
			} else {
				String[] emails = servicioJPA.getInformesDestinatarios().split(";");
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
			if (PlataformaMensajeriaUtil.isEmptyNumber(servicioJPA.getAgrupacionEstado()) && PlataformaMensajeriaUtil.isEmptyNumber(servicioJPA.getAgrupacionCodOrg()) && PlataformaMensajeriaUtil.isEmptyNumber(servicioJPA.getAgrupacionCodSia()) && PlataformaMensajeriaUtil
					.isEmptyNumber(servicioJPA.getAgrupacionCodOrgPagador())) {
				addActionErrorSession(this.getText("plataforma.servicio.field.informesagrupaciones.error"));
				sw = false;
			}
		}
		if (PlataformaMensajeriaUtil.isEmpty(servicioJPA.getResponsableFuncionalNombre())) {
			addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.funcional.nombre"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(servicioJPA.getResponsableFuncionalEmail())) {
			addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.funcional.email"));
			sw = false;
		}

		if (PlataformaMensajeriaUtil.isEmpty(servicioJPA.getResponsableTecnicoNombre())) {
			addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.tecnico.nombre"));
			sw = false;
		}

		if (PlataformaMensajeriaUtil.isEmpty(servicioJPA.getResponsableTecnicoEmail())) {
			addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.tecnico.email"));
			sw = false;
		}
		return sw;
	}

	public String update() throws BaseException {

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		ServicioBean servicioBBDD = null;
		if (servicio == null) {
			addActionErrorSession(this.getText("plataforma.servicio.update.error"));
		} else {
			System.out.println("[ServicioAction - IdServicio] valor == " + servicio.getServicioId());
			if (servicio.getServicioId() == null) {
				if (idServicio != null) {
					servicio.setServicioId(Integer.valueOf(idServicio));
					servicioBBDD = servicioServicio.loadServicio(servicio);
				} else {
					String idServicio = (String) request.getAttribute("idServicio");
					System.out.println("[ServicioAction - request.getAttribute('idServicio)' == " + idServicio);
					if (idServicio != null) {
						servicio.setId(Integer.valueOf(idServicio));
						servicioBBDD = servicioServicio.loadServicio(servicio);
					}
				}

				System.out.println("[ServicioAction - idServicio despues de setear idServicio] valor == " + servicio.getServicioId());
			} else {
				servicioBBDD = servicioServicio.loadServicio(servicio);

			}

			if (servicioBBDD != null) {
				servicioBBDD.setNombre(servicio.getNombre());
				servicioBBDD.setDescripcion(servicio.getDescripcion());
				servicioBBDD.setActivo(servicio.getActivo());
				servicioBBDD.setPremium(servicio.getPremium());
				servicioBBDD.setMultiorganismo(servicio.getMultiorganismo());
				servicioBBDD.setAplicacionId(servicio.getAplicacionId());
				servicioBBDD.setCanalId(servicio.getCanalId());
				Integer i = servicio.getCanalId();
				servicioBBDD.setNmaxenvios(servicio.getNmaxenvios());
				servicioBBDD.setFromMail(servicio.getFromMail());
				servicioBBDD.setFromMailName(servicio.getFromMailName());
				if (null != servicio.getEndPoint() && !servicio.getEndPoint().isEmpty()) {
					servicioBBDD.setEndPoint(servicio.getEndPoint().trim()); // Eliminamos los espacios
				}
				if (servicio.getCanalId().equals(Integer.valueOf(CANAL_SERVIDOR_PUSH_ID))) {
					servicioBBDD.setBadge(servicio.getBadge());
					if (servicio.getIsAndroidPlataforma().equals("true")) {
						servicio.setAndroidPlataforma(Integer.valueOf(1));
						servicioBBDD.setGcmProjectKey(servicio.getGcmProjectKey());
					} else {
						servicio.setAndroidPlataforma(Integer.valueOf(0));
						servicioBBDD.setGcmProjectKey(null);
					}
					if (servicio.getIsIosPlataforma().equals("true")) {
						servicio.setIosPlataforma(Integer.valueOf(1));
						servicioBBDD.setApnsRutaCertificado(servicio.getApnsRutaCertificado());
						if (null != servicio.getApnsPasswordCertificado() && !servicio.getApnsPasswordCertificado().isEmpty()) {
							servicioBBDD.setApnsPasswordCertificado(servicio.getApnsPasswordCertificado().trim()); // Eliminamos los espacios
						}
					} else {
						servicio.setIosPlataforma(Integer.valueOf(0));
						servicioBBDD.setApnsRutaCertificado(null);
						servicioBBDD.setApnsPasswordCertificado(null);
					}
					servicioBBDD.setIsAndroidPlataforma(servicio.getIsAndroidPlataforma());
					servicioBBDD.setIsIosPlataforma(servicio.getIsIosPlataforma());
				}

				// Informes
				if (servicio.getIsInformesActivo() != null && servicio.getIsInformesActivo().equals("true")) {
					servicioBBDD.setInformesActivo(Integer.valueOf(1));
				} else {
					servicioBBDD.setInformesActivo(Integer.valueOf(0));
				}

				if (servicio.getIsInformesActivo() != null && servicio.getIsInformesActivo().equals("true")) {

					if (servicio.getInformesDestinatarios() != null && !servicio.getInformesDestinatarios().isEmpty()) {
						servicioBBDD.setInformesDestinatarios(servicio.getInformesDestinatarios().trim()); // Eliminamos los espacios
					}

					if (servicio.getIsAgrupacionEstado() != null && servicio.getIsAgrupacionEstado().equals("true")) {
						servicioBBDD.setAgrupacionEstado(Integer.valueOf(1));
					} else {
						servicioBBDD.setAgrupacionEstado(Integer.valueOf(0));
					}

					if (servicio.getCanalId().equals(Integer.valueOf(CANAL_SMTP_ID)) || servicio.getCanalId().equals(Integer.valueOf(CANAL_SERVIDOR_PUSH_ID))) {
						if (servicio.getIsAgrupacionCodOrg() != null && servicio.getIsAgrupacionCodOrg().equals("true")) {
							servicioBBDD.setAgrupacionCodOrg(Integer.valueOf(1));
						} else {
							servicioBBDD.setAgrupacionCodOrg(Integer.valueOf(0));
						}
						if (servicio.getIsAgrupacionCodSia() != null && servicio.getIsAgrupacionCodSia().equals("true")) {
							servicioBBDD.setAgrupacionCodSia(Integer.valueOf(1));
						} else {
							servicioBBDD.setAgrupacionCodSia(Integer.valueOf(0));
						}
					} else if (servicio.getCanalId().equals(Integer.valueOf(CANAL_SMS_ID))) {
						if (servicio.getIsAgrupacionCodOrg() != null && servicio.getIsAgrupacionCodOrg().equals("true")) {
							servicioBBDD.setAgrupacionCodOrg(Integer.valueOf(1));
						} else {
							servicioBBDD.setAgrupacionCodOrg(Integer.valueOf(0));
						}
						if (servicio.getIsAgrupacionCodSia() != null && servicio.getIsAgrupacionCodSia().equals("true")) {
							servicioBBDD.setAgrupacionCodSia(Integer.valueOf(1));
						} else {
							servicioBBDD.setAgrupacionCodSia(Integer.valueOf(0));
						}
						if (servicio.getIsAgrupacionCodOrgPagador() != null && servicio.getIsAgrupacionCodOrgPagador().equals("true")) {
							servicioBBDD.setAgrupacionCodOrgPagador(Integer.valueOf(1));
						} else {
							servicioBBDD.setAgrupacionCodOrgPagador(Integer.valueOf(0));
						}
					}
				} else {
					servicioBBDD.setInformesDestinatarios(null);
					servicioBBDD.setAgrupacionEstado(null);
					servicioBBDD.setAgrupacionCodOrg(null);
					servicioBBDD.setAgrupacionCodSia(null);
					servicioBBDD.setAgrupacionCodOrgPagador(null);
				}

				// Historificacion
				Integer historificacion = servicio.getHistorificacion();
				if (null != historificacion) {
					switch (historificacion) {
					case 1:
						servicioBBDD.setHistorificacion(VALOR_1_HISTORIFICACION);
						break;
					case 2:
						servicioBBDD.setHistorificacion(VALOR_2_HISTORIFICACION);
						break;
					case 3:
						servicioBBDD.setHistorificacion(VALOR_3_HISTORIFICACION);
						break;
					case 4:
						if (null == servicio.getHistorificacionInput()){
							servicioBBDD.setHistorificacion(null);
						}else{
							servicioBBDD.setHistorificacion(servicio.getHistorificacionInput());
						}
						break;
					default:
						servicioBBDD.setHistorificacion(VALOR_1_HISTORIFICACION);
						break;
					}
					servicioBBDD.setMotivoHistorificacion(servicio.getMotivoHistorificacion());
					// if(servicio.getHistorificacionInput()!=null && servicioBBDD.getHistorificacion()>VALOR_3_HISTORIFICACION){
					// servicioBBDD.setMotivoHistorificacion(servicio.getMotivoHistorificacion());
					// } else {
					// servicioBBDD.setMotivoHistorificacion(null);
					// }
				} else {
					servicioBBDD.setHistorificacion(null);
				}
				// Conservacion
				Integer conservacion = servicio.getConservacion();
				if (conservacion != null) {
					switch (conservacion) {
					case 1:
						servicioBBDD.setConservacion(VALOR_1_CONSERVACION);
						break;
					case 2:
						servicioBBDD.setConservacion(VALOR_2_CONSERVACION);
						break;
					case 3:
						servicioBBDD.setConservacion(VALOR_3_CONSERVACION);
						break;
					case 4:
						servicioBBDD.setConservacion(servicio.getConservacionInput());
						break;
					default:
						servicioBBDD.setConservacion(VALOR_1_CONSERVACION);
						break;
					}
					if (null != servicio.getMotivoConservacion()) {
						servicioBBDD.setMotivoConservacion(servicio.getMotivoConservacion());
					} else {
						servicioBBDD.setMotivoConservacion(null);
					}
					// if(servicio.getConservacionInput()!= null && servicioBBDD.getConservacion()>VALOR_3_CONSERVACION){
					// servicioBBDD.setMotivoConservacion(servicio.getMotivoConservacion());
					// } else {
					// servicioBBDD.setMotivoConservacion(null);
					// }
				} else {
					servicioBBDD.setConservacion(null);
				}

				ServicioJPA servicioJPA = servicioServicio.getServicioJPA(servicio);

				boolean validServicio = false;

				if (servicioJPA != null) {
					validServicio = validServicio(servicioJPA);
				}
				if (null == servicioBBDD.getHistorificacion()){
					validServicio = false;
					addActionErrorSession(this.getText("plataforma.servicio.field.historificacion.empty"));
				}

				if (!validServicio) {
					return SUCCESS;
					// Si el servicio esta pendiente de aprobacion, se aprueba dicho servicio
				} else if (null != servicioBBDD.getPendienteAprobacion() && servicioBBDD.getPendienteAprobacion().equals(Integer.valueOf(1))) {
					servicioBBDD.setPendienteAprobacion(Integer.valueOf(0));
				}

				servicioBBDD.setResponsableFuncionalEmail(servicio.getResponsableFuncionalEmail());
				servicioBBDD.setResponsableFuncionalNombre(servicio.getResponsableFuncionalNombre());
				servicioBBDD.setResponsableTecnicoEmail(servicio.getResponsableTecnicoEmail());
				servicioBBDD.setResponsableTecnicoNombre(servicio.getResponsableTecnicoNombre());
			}
			servicioServicio.updateServicio(servicioBBDD);

			addActionMessageSession(this.getText("plataforma.servicio.update.ok"));
		}
		return SUCCESS;

	}

	public String updateServicioAplicacion() throws BaseException {

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		ServicioBean servicioBBDD = null;
		if (servicio == null) {
			addActionErrorSession(this.getText("plataforma.servicio.update.error"));
		} else {
			System.out.println("[ServicioAction - IdServicio] valor == " + servicio.getServicioId());
			if (servicio.getServicioId() == null) {
				if (idServicio != null) {
					servicio.setServicioId(Integer.valueOf(idServicio));
					servicioBBDD = servicioServicio.loadServicio(servicio);
				} else {
					String idServicio = (String) request.getAttribute("idServicio");
					System.out.println("[ServicioAction - request.getAttribute('idServicio)' == " + idServicio);
					if (idServicio != null) {
						servicio.setId(Integer.valueOf(idServicio));
						servicioBBDD = servicioServicio.loadServicio(servicio);
					}
				}

				System.out.println("[ServicioAction - idServicio despues de setear idServicio] valor == " + servicio.getServicioId());
			} else {
				servicioBBDD = servicioServicio.loadServicio(servicio);

			}
			if (servicioBBDD != null) {
				servicioBBDD.setNombre(servicio.getNombre());
				servicioBBDD.setDescripcion(servicio.getDescripcion());
				servicioBBDD.setActivo(servicio.getActivo());
				servicioBBDD.setAplicacionId(servicio.getAplicacionId());
				servicioBBDD.setCanalId(servicio.getCanalId());
				servicioBBDD.setNmaxenvios(servicio.getNmaxenvios());
				servicioBBDD.setFromMail(servicio.getFromMail());
				servicioBBDD.setFromMailName(servicio.getFromMailName());
				servicioBBDD.setResponsableFuncionalEmail(servicio.getResponsableFuncionalEmail());
				servicioBBDD.setResponsableFuncionalNombre(servicio.getResponsableFuncionalNombre());
				servicioBBDD.setResponsableTecnicoEmail(servicio.getResponsableTecnicoEmail());
				servicioBBDD.setResponsableTecnicoNombre(servicio.getResponsableTecnicoNombre());

				if (null != servicio.getEndPoint() && !servicio.getEndPoint().isEmpty()) {
					servicioBBDD.setEndPoint(servicio.getEndPoint().trim()); // Eliminamos los espacios
				}
				if (servicio.getCanalId().equals(Integer.valueOf(CANAL_SERVIDOR_PUSH_ID))) {
					servicioBBDD.setBadge(servicio.getBadge());
					if (servicio.getIsAndroidPlataforma().equals("true")) {
						servicio.setAndroidPlataforma(Integer.valueOf(1));
						servicioBBDD.setGcmProjectKey(servicio.getGcmProjectKey());
					} else {
						servicio.setAndroidPlataforma(Integer.valueOf(0));
						servicioBBDD.setGcmProjectKey(null);
					}
					if (servicio.getIsIosPlataforma().equals("true")) {
						servicio.setIosPlataforma(Integer.valueOf(1));
						servicioBBDD.setApnsRutaCertificado(servicio.getApnsRutaCertificado());
						if (null != servicio.getApnsPasswordCertificado() && !servicio.getApnsPasswordCertificado().isEmpty()) {
							servicioBBDD.setApnsPasswordCertificado(servicio.getApnsPasswordCertificado().trim()); // Eliminamos los espacios
						}
					} else {
						servicio.setIosPlataforma(Integer.valueOf(0));
						servicioBBDD.setApnsRutaCertificado(null);
						servicioBBDD.setApnsPasswordCertificado(null);
					}
					servicioBBDD.setIsAndroidPlataforma(servicio.getIsAndroidPlataforma());
					servicioBBDD.setIsIosPlataforma(servicio.getIsIosPlataforma());
				}

				// Informes
				if (servicio.getIsInformesActivo() != null && servicio.getIsInformesActivo().equals("true")) {
					servicioBBDD.setInformesActivo(Integer.valueOf(1));
				} else {
					servicioBBDD.setInformesActivo(Integer.valueOf(0));
				}

				if (servicio.getIsInformesActivo() != null && servicio.getIsInformesActivo().equals("true")) {

					if (servicio.getInformesDestinatarios() != null && !servicio.getInformesDestinatarios().isEmpty()) {
						servicioBBDD.setInformesDestinatarios(servicio.getInformesDestinatarios().trim()); // Eliminamos los espacios
					}

					if (servicio.getIsAgrupacionEstado() != null && servicio.getIsAgrupacionEstado().equals("true")) {
						servicioBBDD.setAgrupacionEstado(Integer.valueOf(1));
					} else {
						servicioBBDD.setAgrupacionEstado(Integer.valueOf(0));
					}

					if (servicio.getCanalId().equals(Integer.valueOf(CANAL_SMTP_ID)) || servicio.getCanalId().equals(Integer.valueOf(CANAL_SERVIDOR_PUSH_ID))) {
						if (servicio.getIsAgrupacionCodOrg() != null && servicio.getIsAgrupacionCodOrg().equals("true")) {
							servicioBBDD.setAgrupacionCodOrg(Integer.valueOf(1));
						} else {
							servicioBBDD.setAgrupacionCodOrg(Integer.valueOf(0));
						}
						if (servicio.getIsAgrupacionCodSia() != null && servicio.getIsAgrupacionCodSia().equals("true")) {
							servicioBBDD.setAgrupacionCodSia(Integer.valueOf(1));
						} else {
							servicioBBDD.setAgrupacionCodSia(Integer.valueOf(0));
						}
					} else if (servicio.getCanalId().equals(Integer.valueOf(CANAL_SMS_ID))) {
						if (servicio.getIsAgrupacionCodOrg() != null && servicio.getIsAgrupacionCodOrg().equals("true")) {
							servicioBBDD.setAgrupacionCodOrg(Integer.valueOf(1));
						} else {
							servicioBBDD.setAgrupacionCodOrg(Integer.valueOf(0));
						}
						if (servicio.getIsAgrupacionCodSia() != null && servicio.getIsAgrupacionCodSia().equals("true")) {
							servicioBBDD.setAgrupacionCodSia(Integer.valueOf(1));
						} else {
							servicioBBDD.setAgrupacionCodSia(Integer.valueOf(0));
						}
						if (servicio.getIsAgrupacionCodOrgPagador() != null && servicio.getIsAgrupacionCodOrgPagador().equals("true")) {
							servicioBBDD.setAgrupacionCodOrgPagador(Integer.valueOf(1));
						} else {
							servicioBBDD.setAgrupacionCodOrgPagador(Integer.valueOf(0));
						}
					}
				} else {
					servicioBBDD.setInformesDestinatarios(null);
					servicioBBDD.setAgrupacionEstado(null);
					servicioBBDD.setAgrupacionCodOrg(null);
					servicioBBDD.setAgrupacionCodSia(null);
					servicioBBDD.setAgrupacionCodOrgPagador(null);
				}

				// Historificacion
				Integer historificacion = servicio.getHistorificacion();
				if (historificacion != null) {
					switch (historificacion) {
					case 1:
						servicioBBDD.setHistorificacion(VALOR_1_HISTORIFICACION);
						break;
					case 2:
						servicioBBDD.setHistorificacion(VALOR_2_HISTORIFICACION);
						break;
					case 3:
						servicioBBDD.setHistorificacion(VALOR_3_HISTORIFICACION);
						break;
					case 4:
						servicioBBDD.setHistorificacion(servicio.getHistorificacionInput());
						break;
					default:
						servicioBBDD.setHistorificacion(VALOR_1_HISTORIFICACION);
						break;
					}
					servicioBBDD.setMotivoHistorificacion(servicio.getMotivoHistorificacion());
					// if(servicio.getHistorificacionInput()!=null && servicioBBDD.getHistorificacion()>VALOR_3_HISTORIFICACION){
					// servicioBBDD.setMotivoHistorificacion(servicio.getMotivoHistorificacion());
					// } else {
					// servicioBBDD.setMotivoHistorificacion(null);
					// }
				} else {
					servicioBBDD.setHistorificacion(null);
				}
				// Conservacion
				Integer conservacion = servicio.getConservacion();
				if (conservacion != null) {
					switch (conservacion) {
					case 1:
						servicioBBDD.setConservacion(VALOR_1_CONSERVACION);
						break;
					case 2:
						servicioBBDD.setConservacion(VALOR_2_CONSERVACION);
						break;
					case 3:
						servicioBBDD.setConservacion(VALOR_3_CONSERVACION);
						break;
					case 4:
						servicioBBDD.setConservacion(servicio.getConservacionInput());
						break;
					default:
						servicioBBDD.setConservacion(VALOR_1_CONSERVACION);
						break;
					}
					if (null != servicio.getMotivoConservacion()) {
						servicioBBDD.setMotivoConservacion(servicio.getMotivoConservacion());
					} else {
						servicioBBDD.setMotivoConservacion(null);
					}
					// if(servicio.getConservacionInput()!= null && servicioBBDD.getConservacion()>VALOR_3_CONSERVACION){
					// servicioBBDD.setMotivoConservacion(servicio.getMotivoConservacion());
					// } else {
					// servicioBBDD.setMotivoConservacion(null);
					// }
				} else {
					servicioBBDD.setConservacion(null);
				}

				ServicioJPA servicioJPA = servicioServicio.getServicioJPA(servicio);

				boolean validServicio = false;

				if (servicioJPA != null) {
					validServicio = validServicio(servicioJPA);
				}

				if (!validServicio) {
					return SUCCESS;
				}
			}
			servicioServicio.updateServicio(servicioBBDD);
			addActionMessageSession(this.getText("plataforma.servicio.update.ok"));
		}
		return SUCCESS;

	}

	public String load() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idServicio == null)
			throw new BusinessException("EL idServicio recibido es nulo");
		try {
			servicio = new ServicioBean();
			servicio.setServicioId(Integer.valueOf(idServicio));
			servicio = servicioServicio.loadServicio(servicio);

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
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { servicio.getServicioId().toString() });
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { servicio.getServicioId().toString() });
			throw new BusinessException(mensg);
		}

	}

	public String deleteServicioAplicacion() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idAplicacion == null && idServicio == null) {
			// throw new BusinessException("El idServicio es nulo");
			addActionErrorSession(this.getText("plataforma.servicio.deleteServicioAplicacion.error"));
		} else {
			ServicioBean servicio = new ServicioBean();
			servicio.setServicioId(Integer.valueOf(idServicio));
			ServicioBean servBBDD = servicioServicio.loadServicio(servicio);
			servBBDD.setAplicacionId(null);
			servicioServicio.updateServicio(servBBDD);
			addActionMessageSession(this.getText("plataforma.servicio.deleteServicioAplicacion.ok"));
		}
		return SUCCESS;
	}

	public String delete() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idServicio == null) {
			// throw new BusinessException("EL idServicio recibido es nulo");
			addActionErrorSession(this.getText("plataforma.servicio.delete.error"));
		} else {
			servicio = new ServicioBean();
			servicio.setServicioId(Integer.valueOf(idServicio));
			// List<PlanificacionBean> listaPlanificacionesServicio = servicioPlanificacion.getPlanificacionesByServicioID(servicio.getServicioId());
			// if(listaPlanificacionesServicio!=null){
			// Iterator<PlanificacionBean> iteratorPlanificacion = listaPlanificacionesServicio.iterator();
			// while(iteratorPlanificacion.hasNext()){
			// PlanificacionBean planificacionBean = iteratorPlanificacion.next();
			// servicioPlanificacion.deletePlanificacion(planificacionBean);
			// }
			// }
			servicioServicio.deleteServicio(servicio);
			addActionMessageSession(this.getText("plataforma.servicio.delete.ok"));

		}
		return SUCCESS;

	}

	public String deleteSelected() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.servicio.deleteSelected.error"));
		} else {
			for (String idServicio : checkDelList) {
				servicio = new ServicioBean();
				servicio.setServicioId(Integer.valueOf(idServicio));
				servicioServicio.deleteServicio(servicio);
			}
			addActionMessageSession(this.getText("plataforma.servicio.deleteSelected.ok"));
		}
		return SUCCESS;

	}

	public String deletePlanificacionesSelected() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelListPlanificaciones == null) {
			// throw new BusinessException("No se ha seleccionado ningúna planificacion para eliminar");
			addActionErrorSession(this.getText("plataforma.servicio.deletePlanificacionesSelected.error"));

		} else {
			for (String idPlanificacion : checkDelListPlanificaciones) {
				PlanificacionBean planificacion = new PlanificacionBean();
				planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
				servicioPlanificacion.deletePlanificacion(planificacion);
				servicioPlanificacion.eliminarJobPorPlanificacion(Integer.valueOf(idPlanificacion));
			}
			addActionMessageSession(this.getText("plataforma.servicio.deletePlanificacionesSelected.ok"));

		}
		return SUCCESS;
	}

	public String addPlanificacionServicio() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (planificacionServidor != null && planificacionValida(planificacionServidor)) {
			// planificacionServidor.setTipoPlanificacionId(Integer.valueOf(1));
			if (servicio != null && servicio.getCanalId() != null && servicio.getCanalId().intValue() == 2) {
				planificacionServidor.setTipoPlanificacionId(Integer.valueOf(2));
			} else if (servicio != null && servicio.getCanalId() != null && servicio.getCanalId().intValue() == 1) {
				planificacionServidor.setTipoPlanificacionId(Integer.valueOf(1));
			} else if (servicio != null && servicio.getCanalId() != null && servicio.getCanalId().intValue() == 3) {
				planificacionServidor.setTipoPlanificacionId(Integer.valueOf(3));
			} else if (servicio != null && servicio.getCanalId() != null && servicio.getCanalId().intValue() == 4) {
				planificacionServidor.setTipoPlanificacionId(Integer.valueOf(4));
			}

			int valido = servicioPlanificacion.validaPlanificacionOptima(idPlanificacion, planificacionServidor.getTipoPlanificacionId(), planificacionServidor.getServidorId(), planificacionServidor.getServicioId(), planificacionServidor.getLunes(),
					planificacionServidor.getMartes(), planificacionServidor.getMiercoles(), planificacionServidor.getJueves(), planificacionServidor.getViernes(), planificacionServidor.getSabado(), planificacionServidor.getDomingo(), planificacionServidor.getHoraHasta(),
					planificacionServidor.getHoraDesde());

			if (valido == 1) {
				planificacionServidor.setActivo(Integer.valueOf(1));

				Integer planificacionId = servicioPlanificacion.newPlanificacion(planificacionServidor);
				if (planificacionId > 0) {
					servicioPlanificacion.crearJobPorPlanificacion(planificacionId);
				}
				addActionMessageSession(this.getText("plataforma.servidores.planificacion.add.ok"));
			} else if (valido == 2) {
				addActionErrorSession("No se ha guardado la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
			} else {
				addActionErrorSession("No se ha guardado la planificaci&oacute;n. La configuraci&oacute;n seleccionada no garantiza el env&iacute;o de los mensajes");
			}
		} else {
			// addActionErrorSession(this.getText("plataforma.servidores.planificacion.add.error"));
			return ERROR;
		}

		return SUCCESS;
	}

	public PlanificacionBean getPlanificacionServidor() {
		return planificacionServidor;
	}

	public void setPlanificacionServidor(PlanificacionBean planificacionServidor) {
		this.planificacionServidor = planificacionServidor;
	}

	/**
	 * Verifica que se ha introducido por lo menos un día de la semana y las horas de inicio y fin
	 * 
	 * @param planificacionServidor
	 */
	private boolean planificacionValida(PlanificacionBean planificacionServidor) {
		boolean sw = true;
		if (planificacionServidor != null) {
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde()) && PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
				addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horas.error"));
				sw = false;

			}
			if (!PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde()) && PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
				addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horaHasta.error"));
				sw = false;
			}
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde()) && !PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
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
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getLunes()) && PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getMartes()) && PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getMiercoles()) && PlataformaMensajeriaUtil
					.isEmpty(planificacionServidor.getJueves()) && PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getViernes()) && PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getSabado()) && PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getDomingo())) {
				addFieldErrorSession(this.getText("plataforma.servidores.planificacion.dias.error"));
				sw = false;
			}
			// Comentada esta validación segun el correo de Raúl:
			/**
			 * al añadir una planificación en los servicios no es obligatorio que tenga un servidor asociado.
			 */
			/*
			 * if(PlataformaMensajeriaUtil.isEmptyNumber(planificacionServidor.getServidorId())){ addFieldErrorSession(this.getText("plataforma.servidores.planificacion.field.servidorid.error")); sw=false; }
			 */

		}
		return sw;
	}

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

	private boolean validoFormatoHora(String hora) {
		boolean sw = true;
		if (!PlataformaMensajeriaUtil.isEmpty(hora)) {
			if (!PlataformaMensajeriaUtil.validaFormatoHora(hora)) {
				sw = false;
			}
		}
		return sw;
	}

	public String deletePlanificacionServicio() throws BaseException {
		if (idPlanificacion == null) {
			// throw new BusinessException("No se ha seleccionado ningúna planificacion para eliminar");
			addActionErrorSession(this.getText("plataforma.servicio.deletePlanificacion.error"));

		} else {
			PlanificacionBean planificacion = new PlanificacionBean();
			planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
			servicioPlanificacion.deletePlanificacion(planificacion);
			servicioPlanificacion.eliminarJobPorPlanificacion(Integer.valueOf(idPlanificacion));
			addActionMessageSession(this.getText("plataforma.servicio.deletePlanificacion.ok"));
		}
		return SUCCESS;
	}

	public String addServidorServicio() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		boolean sw = true;
		if (servidorServicio != null) {
			if (PlataformaMensajeriaUtil.isEmptyNumber(servidorServicio.getNumIntentos())) {
				addFieldErrorSession(this.getText("plataforma.servicio.servidorservicio.field.numIntentos"));
				sw = false;
			}
			if (PlataformaMensajeriaUtil.isEmptyNumber(servidorServicio.getServidorId())) {
				addFieldErrorSession(this.getText("plataforma.servicio.servidorservicio.field.servidorId"));
				sw = false;
			}
			Integer s = servicio.getCanalId();
			if (servicio.getCanalId() == CANAL_SMS_ID) {
				if (PlataformaMensajeriaUtil.isEmpty(servidorServicio.getHeaderSMS())) {
					addFieldErrorSession(this.getText("plataforma.servicio.servidorservicio.field.headerSMS"));
					sw = false;
				}
				if (PlataformaMensajeriaUtil.isEmpty(servidorServicio.getProveedorUsuarioSMS())) {
					addFieldErrorSession(this.getText("plataforma.servicio.servidorservicio.field.proveedorUsuarioSMS"));
					sw = false;
				}
				if (PlataformaMensajeriaUtil.isEmpty(servidorServicio.getProveedorPasswordSMS())) {
					addFieldErrorSession(this.getText("plataforma.servicio.servidorservicio.field.proveedorPasswordSMS"));
					sw = false;
				}
			}
			if (sw) {
				ServicioBean sBean = new ServicioBean();
				sBean.setServicioId(Integer.valueOf(idServicio));
				ServicioBean servBean = servicioServicio.loadServicio(sBean);
				servBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				servBean.setFechaModificacion(new Date());
				servicioServicio.updateServicio(servBean);
				servidorServicio.setServicioId(servicio.getServicioId());
				servicioServicio.newServidoresServicio(servidorServicio);
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

	@Transactional
	public String addServicioOrganismos() throws BaseException {// TODO:
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		boolean sw = true;
		Integer i = servicioOrganismos.getServicioId();
		if (servicioOrganismos != null) {
			servicio.setServicioId(Integer.valueOf(idServicio));
			ServicioBean servicioBBDD = servicioServicio.loadServicio(servicio);
			if (servicioBBDD.getMultiorganismo() == 0) {
				addFieldErrorSession(this.getText("plataforma.servicio.servidorservicio.field.multiorganismo"));
				sw = false;
			}
			if (PlataformaMensajeriaUtil.isEmptyNumber(servicioOrganismos.getOrganismoId())) {
				addFieldErrorSession(this.getText("plataforma.servicio.servidorServicio.field.multiorganismo.servicio.vacio"));
				sw = false;
			}

			if (null != listaServicioOrganismos) {
				for (ServicioOrganismosBean s : listaServicioOrganismos) {
					if (s.getOrganismoId().equals(servicioOrganismos.getOrganismoId())) {
						addFieldErrorSession(this.getText("plataforma.servicio.servidorservicio.field.multiorganismo.organismo.repetido"));
						sw = false;
					}
				}
			}

			if (sw) {

				ServicioBean sBean = new ServicioBean();
				sBean.setServicioId(Integer.valueOf(idServicio));
				ServicioBean servBean = servicioServicio.loadServicio(sBean);
				servBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				servBean.setFechaModificacion(new Date());
				servicioServicio.updateServicio(servBean);

				// modificamos el organismo
				OrganismoBean oBean = new OrganismoBean();
				oBean.setOrganismoId(servicioOrganismos.getOrganismoId());
				OrganismoBean orgBean = servicioOrganismo.loadOrganismo(oBean);
				orgBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				orgBean.setFechaModificacion(new Date());

				servicioOrganismo.updateOrganismo(orgBean);

				servicioOrganismos.setServicioId(sBean.getServicioId());
				servicioOrganismos.setOrganismoId(servicioOrganismos.getOrganismoId());

				servicioServicio.newServicioOrganismo(servicioOrganismos);
				addActionMessageSession(this.getText("plataforma.servicio.servicioOrganismo.add.ok"));
			} else {
				return ERROR;
			}
		} else {
			addActionErrorSession(this.getText("plataforma.servicio.servicioOrganismo.add.error"));
			return ERROR;
		}
		return SUCCESS;
	}

	public String deleteServidoresServiciosSelected() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelListServidorServicios == null) {
			// throw new BusinessException("No se ha seleccionado ningún servidor/proveedor para eliminar");
			addActionErrorSession(this.getText("plataforma.servicio.servidorservicio.deleteSelected.error"));

		} else {
			for (String servidorServicioId : checkDelListServidorServicios) {
				ServidoresServiciosBean servidorServicio = new ServidoresServiciosBean();
				servidorServicio.setServidorServicioId(Integer.valueOf(servidorServicioId));
				ServidoresOrganismosBean servidorOrg = new ServidoresOrganismosBean();
				servidorOrg.setServidorOrganismoId(Long.valueOf(servidorServicioId));
				
				ServidoresOrganismosBean so = servicioServidor.loadServidorOrganismoBean(servidorOrg);
				if (so != null){
					//se borran de los organismos
					servidorOrganismos = new ServidoresOrganismosBean();
					servidorOrganismos.setServidorOrganismoId(Long.valueOf(servidorServicioId));
					OrganismoBean oBean = new OrganismoBean();
					oBean.setOrganismoId(so.getOrganismoId().intValue());
					OrganismoBean orgBean = servicioOrganismo.loadOrganismo(oBean);
					orgBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
					orgBean.setFechaModificacion(new Date());
					servicioOrganismo.updateOrganismo(orgBean);

					Long id = servicioServidor.loadServidorOrganismoBean(servidorOrganismos).getServidorId();
					// ////borrar planificaciones del organismo
					List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(oBean.getOrganismoId());
					if (null != listaPlanificacionesOrganismos){
						for (PlanificacionBean o : listaPlanificacionesOrganismos) {
							if (o.getServidorId().equals(id.intValue())) {
								servicioPlanificacion.deletePlanificacion(o);
							}
						}
					}

					servicioServidor.deleteServidorOrganismos(servidorOrganismos);
				}else{
					ServicioBean sBean = new ServicioBean();
					sBean.setServicioId(Integer.valueOf(idServicio));
					ServicioBean servBean = servicioServicio.loadServicio(sBean);
					servBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
					servBean.setFechaModificacion(new Date());
					servicioServicio.updateServicio(servBean);
					servicioServicio.deleteServidoresServicios(servidorServicio);
				}

			}
			addActionMessageSession(this.getText("plataforma.servicio.servidorservicio.deleteSelected.ok"));

		}
		return SUCCESS;
	}

	public String deleteServicioOrganismosSelected() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelListServiciosOrganismos == null) {
			// throw new BusinessException("No se ha seleccionado ningún servidor/proveedor para eliminar");
			addActionErrorSession(this.getText("plataforma.servicio.servicioOrganismo.deleteSelected.error"));

		} else {

			for (String serOrg : checkDelListServiciosOrganismos) {
				ServicioOrganismosBean servicioOrganismo = new ServicioOrganismosBean();
				servicioOrganismo.setServicioOrganismoId(Integer.valueOf(serOrg));

				// ////borrar planificaciones del organismo
				List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
				if (null != listaPlanificacionesOrganismos){
					for (PlanificacionBean o : listaPlanificacionesOrganismos) {
						if (o.getServicioId().equals(Integer.valueOf(idServicio))) {
							servicioPlanificacion.deletePlanificacion(o);
						}
					}
				}
				
				servicioServicio.deleteServicioOrganismos(servicioOrganismo);

			}
			addActionMessageSession(this.getText("plataforma.servicio.servicioOrganismo.delete.ok"));

		}
		return SUCCESS;
	}

	public String deleteServicioOrganismo() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idServicioOrganismo == null) {
			addActionErrorSession(this.getText("plataforma.servicio.servicioOrganismo.delete.error"));
		} else {
			servicioOrganismos = new ServicioOrganismosBean();
			servicioOrganismos.setServicioOrganismoId(Integer.valueOf(idServicioOrganismo));
			ServicioBean sBean = new ServicioBean();
			sBean.setServicioId(Integer.valueOf(idServicio));
			ServicioBean servBean = servicioServicio.loadServicio(sBean);
			servBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			servBean.setFechaModificacion(new Date());
			servicioServicio.updateServicio(servBean);
			// modifico el organismo
			OrganismoBean oBean = new OrganismoBean();
			oBean.setOrganismoId(Integer.valueOf(idOrganismo));
			OrganismoBean orgBean = servicioOrganismo.loadOrganismo(oBean);
			orgBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			orgBean.setFechaModificacion(new Date());
			servicioOrganismo.updateOrganismo(orgBean);

			// ////borrar planificaciones del organismo
			List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
			if (null != listaPlanificacionesOrganismos){
				for (PlanificacionBean o : listaPlanificacionesOrganismos) {
					if (o.getServicioId().equals(Integer.valueOf(idServicio))) {
						servicioPlanificacion.deletePlanificacion(o);
					}
				}
			}
			
			ServicioOrganismosBean servicioOrganismo = new ServicioOrganismosBean();
			servicioOrganismo.setServicioOrganismoId(Integer.valueOf(idServicioOrganismo));
			servicioServicio.deleteServicioOrganismos(servicioOrganismo);
			addActionMessageSession(this.getText("plataforma.servicio.servicioOrganismo.delete.ok"));

		}

		return SUCCESS;
	}


	public String deleteServidorServicio() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (servidorServicioId == null) {
			// throw new BusinessException("No se ha seleccionado ningún servidor Servicio Id para eliminar");
			addActionErrorSession(this.getText("plataforma.servicio.servidorservicio.delete.error"));

		} else {

			if (null != idOrganismo && idOrganismo.length() > 0) {
				servidorOrganismos = new ServidoresOrganismosBean();
				servidorOrganismos.setServidorOrganismoId(Long.valueOf(servidorServicioId));
				OrganismoBean oBean = new OrganismoBean();
				oBean.setOrganismoId(Integer.valueOf(idOrganismo));
				OrganismoBean orgBean = servicioOrganismo.loadOrganismo(oBean);
				orgBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				orgBean.setFechaModificacion(new Date());
				servicioOrganismo.updateOrganismo(orgBean);

				Long id = servicioServidor.loadServidorOrganismoBean(servidorOrganismos).getServidorId();
				// ////borrar planificaciones del organismo
				List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
				if (null != listaPlanificacionesOrganismos){
					for (PlanificacionBean o : listaPlanificacionesOrganismos) {
						if (o.getServidorId().equals(id.intValue())) {
							servicioPlanificacion.deletePlanificacion(o);
						}
					}
				}

				servicioServidor.deleteServidorOrganismos(servidorOrganismos);
			} else {
				servidorServicio = new ServidoresServiciosBean();
				servidorServicio.setServidorServicioId(Integer.valueOf(servidorServicioId));
				ServicioBean sBean = new ServicioBean();
				sBean.setServicioId(Integer.valueOf(idServicio));
				ServicioBean servBean = servicioServicio.loadServicio(sBean);
				servBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				servBean.setFechaModificacion(new Date());
				servicioServicio.updateServicio(servBean);
				servicioServicio.deleteServidoresServicios(servidorServicio);
			}
			addActionMessageSession(this.getText("plataforma.servicio.servidorservicio.delete.ok"));
		}
		return SUCCESS;
	}

	@Override
	public void prepare() throws Exception {
		// contextUsuarios = getComboValues();

		session = (Map) ActionContext.getContext().get("session");
		recovery = (String) session.get(RECOVERY);

		comboAplicaciones = getComboValues();
		comboCanales = getComboValuesCanales();

		// Si se viene de Aplicaciones para crear un nuevo servicio
		if (idAplicacion != null && idAplicacion.length() > 0 && idServicio == null) {
			servicio = new ServicioBean();
			servicio.setAplicacionId(Integer.valueOf(idAplicacion));

			AplicacionBean detalleApp = new AplicacionBean();
			detalleApp.setAplicacionId(Integer.valueOf(idAplicacion));
			detalleApp = servicioAplicacion.loadAplicacion(detalleApp);
			if (null != detalleApp) {
				servicio.setAplicacionNombre(detalleApp.getNombre());
			}
		}

		if (idServicio != null) {
			if (servicio == null)
				load();
			comboConfiguraciones = getComboConfiguracion(servicio.getCanalId());
			comboConfiguracionesPlan = getComboConfiguracionesPlan(servicio.getCanalId());
			comboServicioOrganismos = cargarComboServicioOrganismos();
		}
		if (idServicio != null) {
			listaPlanificacionesServicio = servicioPlanificacion.getPlanificacionesByServicioID(Integer.valueOf(idServicio));
			listaServicioOrganismos = servicioServicio.getServicioOrganismo(idServicio);
			listaServidoresServicios = loadServidoresServicio();// servicioServicio.getServidoresServicios(idServicio);

			if ((listaPlanificacionesServicio != null && listaPlanificacionesServicio.size() > 0) || (listaServidoresServicios != null && listaServidoresServicios.size() > 0)) {
				canalDisabled = "disable";
			} else {
				canalDisabled = null;
			}
		}
	}

	public List<KeyValueObject> getComboConfiguracionesPlan() {
		return comboConfiguracionesPlan;
	}

	public void setComboConfiguracionesPlan(List<KeyValueObject> comboConfiguracionesPlan) {
		this.comboConfiguracionesPlan = comboConfiguracionesPlan;
	}

	public void setCanalDisabled(String canalDisabled) {
		this.canalDisabled = canalDisabled;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> getComboConfiguracion(Integer idCanal) {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;
		// TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
		ArrayList<ProveedorSMSBean> keysSMS = null;
		ArrayList<ReceptorSMSBean> keysReceptorSMS = null;
		ArrayList<ServidorBean> keysSMTP = null;
		ArrayList<ServidorPushBean> keysServidorPush = null;
		if (idCanal != null && idCanal.intValue() == CANAL_SMS_ID) {
			try {
				keysSMS = (ArrayList<ProveedorSMSBean>) servicioProveedorSMS.getProveedoresSMSNoAsignados(Integer.valueOf(idServicio));
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (keysSMS != null && keysSMS.size() > 0) {
				for (ProveedorSMSBean key : keysSMS) {

					option = new KeyValueObject();
					option.setCodigo(key.getProveedorSMSId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
			}
		} else if (idCanal != null && idCanal.intValue() == CANAL_RECEPCION_SMS_ID) {
			try {
				keysReceptorSMS = (ArrayList<ReceptorSMSBean>) servicioReceptorSMS.getReceptoresSMSNoAsignados(Integer.valueOf(idServicio));
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (keysReceptorSMS != null && keysReceptorSMS.size() > 0) {
				for (ReceptorSMSBean key : keysReceptorSMS) {

					option = new KeyValueObject();
					option.setCodigo(key.getReceptorSMSId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
			}
		} else if (idCanal != null && idCanal.intValue() == CANAL_SMTP_ID) {
			try {
				keysSMTP = (ArrayList<ServidorBean>) servicioServidor.getServidoresNoAsignados(Integer.valueOf(idServicio));
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (keysSMTP != null && keysSMTP.size() > 0)
				for (ServidorBean key : keysSMTP) {

					option = new KeyValueObject();
					option.setCodigo(key.getServidorId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
		} else if (idCanal != null && idCanal.intValue() == CANAL_SERVIDOR_PUSH_ID) {
			try {
				keysServidorPush = (ArrayList<ServidorPushBean>) servicioServidorPush.getServidoresPushNoAsignados(Integer.valueOf(idServicio));
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (keysServidorPush != null && keysServidorPush.size() > 0)
				for (ServidorPushBean key : keysServidorPush) {

					option = new KeyValueObject();
					option.setCodigo(key.getServidorPushId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
		}

		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> getComboConfiguracionesPlan(Integer idCanal) {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;
		// TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
		ArrayList<ProveedorSMSBean> keysSMS = null;
		ArrayList<ReceptorSMSBean> keysReceptorSMS = null;
		ArrayList<ServidorBean> keysSMTP = null;
		ArrayList<ServidorPushBean> keysServidoresPush = null;
		if (idCanal != null && idCanal.intValue() == CANAL_SMS_ID) {
			try {
				keysSMS = (ArrayList<ProveedorSMSBean>) servicioProveedorSMS.getProveedoresSMS();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (keysSMS != null && keysSMS.size() > 0) {
				for (ProveedorSMSBean key : keysSMS) {

					option = new KeyValueObject();
					option.setCodigo(key.getProveedorSMSId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
			}
		} else if (idCanal != null && idCanal.intValue() == CANAL_RECEPCION_SMS_ID) {
			try {
				keysReceptorSMS = (ArrayList<ReceptorSMSBean>) servicioReceptorSMS.getReceptoresSMS();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (keysReceptorSMS != null && keysReceptorSMS.size() > 0) {
				for (ReceptorSMSBean key : keysReceptorSMS) {

					option = new KeyValueObject();
					option.setCodigo(key.getReceptorSMSId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
			}
		} else if (idCanal != null && idCanal.intValue() == CANAL_SMTP_ID) {
			try {
				keysSMTP = (ArrayList<ServidorBean>) servicioServidor.getServidores();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (keysSMTP != null && keysSMTP.size() > 0)
				for (ServidorBean key : keysSMTP) {

					option = new KeyValueObject();
					option.setCodigo(key.getServidorId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
		} else if (idCanal != null && idCanal.intValue() == CANAL_SERVIDOR_PUSH_ID) {
			try {
				keysServidoresPush = (ArrayList<ServidorPushBean>) servicioServidorPush.getServidoresPush();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (keysServidoresPush != null && keysServidoresPush.size() > 0) {
				for (ServidorPushBean key : keysServidoresPush) {

					option = new KeyValueObject();
					option.setCodigo(key.getServidorPushId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
			}
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> cargarComboServicioOrganismos() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;

		ArrayList<OrganismoBean> keys = null;
		try {
			keys = (ArrayList<OrganismoBean>) servicioOrganismo.getOrganismos();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (keys != null && keys.size() > 0)
			for (OrganismoBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getOrganismoId().toString());
				option.setDescripcion(key.getDIR3());
				result.add(option);
			}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> getComboValuesCanales() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;
		// TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
		ArrayList<CanalBean> keys = null;
		try {
			keys = (ArrayList<CanalBean>) servicioCanal.getCanales();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> getComboValues() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;
		// TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
		ArrayList<AplicacionBean> keys = null;
		try {
			keys = (ArrayList<AplicacionBean>) servicioAplicacion.getAplicaciones();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	private List<ServidoresServiciosBean> loadServidoresServicio() {
		List<ServidoresServiciosBean> res = null;
		List<Integer> listaOrganismos = new ArrayList<Integer>();

		List<ViewServidoresOrganismosJPA> lista = new ArrayList<ViewServidoresOrganismosJPA>();
		if (idServicio != null && idServicio.length() > 0) {
			try {
				res = servicioServicio.getServidoresServicios(idServicio);
				// tenemos los id de los organismo
				if (listaServicioOrganismos != null && listaServicioOrganismos.size() > 0) {
					for (ServicioOrganismosBean l : listaServicioOrganismos) {
						if (!listaOrganismos.contains(l.getOrganismoId()))
							listaOrganismos.add(l.getOrganismoId());
					}
					lista = servicioServidor.getServidorOrganismo(listaOrganismos);
					if (null == res)
						res = new ArrayList<ServidoresServiciosBean>();
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (ViewServidoresOrganismosJPA so : lista) {
				ServidoresServiciosBean aux = new ServidoresServiciosBean();
				aux.setHeaderSMS(so.getHeaderSMS());
				aux.setDIR3Organismo(so.getDIR3Organismo());
				aux.setOrganismoId(so.getOrganismoId());
				aux.setNombreServidor(so.getNombreServidor());
				aux.setNumIntentos(so.getNumIntentos());
				aux.setProveedorPasswordSMS(so.getProveedorPasswordSMS());
				aux.setProveedorUsuarioSMS(so.getProveedorUsuarioSMS());
				aux.setServidorServicioId(so.getServidorOrganismoId());
				res.add(aux);
			}
		}
		return res;
	}

	public List<KeyValueObject> getComboServicioOrganismos() {
		return comboServicioOrganismos;
	}

	public void setComboServicioOrganismos(List<KeyValueObject> comboServicioOrganismos) {
		this.comboServicioOrganismos = comboServicioOrganismos;
	}

	public List<KeyValueObject> getComboCanales() {
		return comboCanales;
	}

	public void setComboCanales(List<KeyValueObject> comboCanales) {
		this.comboCanales = comboCanales;
	}

	public String getResultCount() {
		return resultCount;
	}

	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	public String[] getCheckDelList() {
		return checkDelList;
	}

	public void setCheckDelList(String[] checkDelList) {
		this.checkDelList = checkDelList;
	}

	public void setParametroServidorId(String parametroServidorId) {
		this.parametroServidorId = parametroServidorId;
	}

	public List<KeyValueObject> getComboAplicaciones() {
		return comboAplicaciones;
	}

	public void setComboAplicaciones(List<KeyValueObject> comboAplicaciones) {
		this.comboAplicaciones = comboAplicaciones;
	}

	public List<ServicioBean> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(List<ServicioBean> listaServicios) {
		this.listaServicios = listaServicios;
	}

	public ServicioServicio getServicioServicio() {
		return servicioServicio;
	}

	public void setServicioServicio(ServicioServicio servicioServicio) {
		this.servicioServicio = servicioServicio;
	}

	public ServicioAplicacion getServicioAplicacion() {
		return servicioAplicacion;
	}

	public void setServicioAplicacion(ServicioAplicacion servicioAplicacion) {
		this.servicioAplicacion = servicioAplicacion;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	public ServicioBean getServicio() {
		return servicio;
	}

	public void setServicio(ServicioBean servicio) {
		this.servicio = servicio;
	}

	public List<ServidorBean> getListaServidoresDetalle() {
		return listaServidoresDetalle;
	}

	public void setListaServidoresDetalle(List<ServidorBean> listaServidoresDetalle) {
		this.listaServidoresDetalle = listaServidoresDetalle;
	}

	public List<ProveedorSMSBean> getListaProveedorSMSDetalle() {
		return listaProveedorSMSDetalle;
	}

	public void setListaProveedorSMSDetalle(List<ProveedorSMSBean> listaProveedorSMSDetalle) {
		this.listaProveedorSMSDetalle = listaProveedorSMSDetalle;
	}

	public String getParametroServidorId() {
		return parametroServidorId;
	}

	public List<KeyValueObject> getComboConfiguraciones() {
		return comboConfiguraciones;
	}

	public void setComboConfiguraciones(List<KeyValueObject> comboConfiguraciones) {
		this.comboConfiguraciones = comboConfiguraciones;
	}

	public ServicioProveedorSMS getServicioProveedorSMS() {
		return servicioProveedorSMS;
	}

	public void setServicioProveedorSMS(ServicioProveedorSMS servicioProveedorSMS) {
		this.servicioProveedorSMS = servicioProveedorSMS;
	}

	public ServicioReceptorSMS getServicioReceptorSMS() {
		return servicioReceptorSMS;
	}

	public void setServicioReceptorSMS(ServicioReceptorSMS servicioReceptorSMS) {
		this.servicioReceptorSMS = servicioReceptorSMS;
	}

	public ServicioServidor getServicioServidor() {
		return servicioServidor;
	}

	public void setServicioServidor(ServicioServidor servicioServidor) {
		this.servicioServidor = servicioServidor;
	}

	public ServicioCanal getServicioCanal() {
		return servicioCanal;
	}

	public void setServicioCanal(ServicioCanal servicioCanal) {
		this.servicioCanal = servicioCanal;
	}

	public List<ServidoresServiciosBean> getListaServidoresServicios() {
		return listaServidoresServicios;
	}

	public void setListaServidoresServicios(List<ServidoresServiciosBean> listaServidoresServicios) {
		this.listaServidoresServicios = listaServidoresServicios;
	}

	public List<ServicioOrganismosBean> getListaServicioOrganismos() {
		return listaServicioOrganismos;
	}

	public void setListaSeerviciosOrganismos(List<ServicioOrganismosBean> listaServicioOrganismos) {
		this.listaServicioOrganismos = listaServicioOrganismos;
	}

	public List<PlanificacionBean> getListaPlanificacionesServicio() {
		return listaPlanificacionesServicio;
	}

	public void setListaPlanificacionesServicio(List<PlanificacionBean> listaPlanificacionesServicio) {
		this.listaPlanificacionesServicio = listaPlanificacionesServicio;
	}

	public ServicioPlanificacion getServicioPlanificacion() {
		return servicioPlanificacion;
	}

	public void setServicioPlanificacion(ServicioPlanificacion servicioPlanificacion) {
		this.servicioPlanificacion = servicioPlanificacion;
	}

	/**
	 * Método que resuelve el lugar donde tiene que volver
	 */
	@SuppressWarnings("unchecked")
	public String getVolver() {
		String volver = "buscarServicios.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
			session.put(RECOVERY, volver);
		} else if (session.get(RECOVERY) != null) {
			volver = (String) session.get(RECOVERY);
			session.put(RECOVERY, null);
		}
		return volver;
	}

	/**
	 * Método que resuelve el lugar donde tiene que volver
	 */
	public String getVolverAplicacion() {
		String volverAplicacion = "viewAplicacion.action?idAplicacion=" + servicio.getAplicacionId();
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volverAplicacion = from + "?" + var + "=" + idFrom;
		}
		return volverAplicacion;
	}

	

	public Integer getNewHistorificacion() {
		return newHistorificacion;
	}

	public void setNewHistorificacion(Integer newHistorificacion) {
		this.newHistorificacion = newHistorificacion;
	}

	public Integer getNewConservacion() {
		return newConservacion;
	}

	public void setNewConservacion(Integer newConservacion) {
		this.newConservacion = newConservacion;
	}

	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	public ServicioServidorPush getServicioServidorPush() {
		return servicioServidorPush;
	}

	public void setServicioServidorPush(ServicioServidorPush servicioServidorPush) {
		this.servicioServidorPush = servicioServidorPush;
	}

	public String getNewPlataformaAndroid() {
		return newPlataformaAndroid;
	}

	public void setNewPlataformaAndroid(String newPlataformaAndroid) {
		this.newPlataformaAndroid = newPlataformaAndroid;
	}

	public String getNewPlataformaiOS() {
		return newPlataformaiOS;
	}

	public void setNewPlataformaiOS(String newPlataformaiOS) {
		this.newPlataformaiOS = newPlataformaiOS;
	}

	public String getNewInformeActivo() {
		return newInformeActivo;
	}

	public void setNewInformeActivo(String newInformeActivo) {
		this.newInformeActivo = newInformeActivo;
	}

	public String getNewAgrupacionEstado() {
		return newAgrupacionEstado;
	}

	public void setNewAgrupacionEstado(String newAgrupacionEstado) {
		this.newAgrupacionEstado = newAgrupacionEstado;
	}

	public String getNewAgrupacionCodOrg() {
		return newAgrupacionCodOrg;
	}

	public void setNewAgrupacionCodOrg(String newAgrupacionCodOrg) {
		this.newAgrupacionCodOrg = newAgrupacionCodOrg;
	}

	public String getNewAgrupacionCodSia() {
		return newAgrupacionCodSia;
	}

	public void setNewAgrupacionCodSia(String newAgrupacionCodSia) {
		this.newAgrupacionCodSia = newAgrupacionCodSia;
	}

	public String getNewAgrupacionCodOrgPagador() {
		return newAgrupacionCodOrgPagador;
	}

	public void setNewAgrupacionCodOrgPagador(String newAgrupacionCodOrgPagador) {
		this.newAgrupacionCodOrgPagador = newAgrupacionCodOrgPagador;
	}

	public String getNewInformesDestinatarios() {
		return newInformesDestinatarios;
	}

	public void setNewInformesDestinatarios(String newInformesDestinatarios) {
		this.newInformesDestinatarios = newInformesDestinatarios;
	}

	public ServicioOrganismosBean getServicioOrganismos() {
		return servicioOrganismos;
	}

	public void setServicioOrganismos(ServicioOrganismosBean servicioOrganismos) {
		this.servicioOrganismos = servicioOrganismos;
	}

	public void setListaServicioOrganismos(List<ServicioOrganismosBean> listaServicioOrganismos) {
		this.listaServicioOrganismos = listaServicioOrganismos;
	}

	public ServicioOrganismo getServicioOrganismo() {
		return servicioOrganismo;
	}

	public void setServicioOrganismo(ServicioOrganismo servicioOrganismo) {
		this.servicioOrganismo = servicioOrganismo;
	}

	public OrganismoBean getOrganismo() {
		return organismo;
	}

	public void setOrganismo(OrganismoBean organismo) {
		this.organismo = organismo;
	}

	public String getIdOrganismo() {
		return idOrganismo;
	}

	public void setIdOrganismo(String idOrganismo) {
		this.idOrganismo = idOrganismo;
	}

	public String getIdServicioOrganismo() {
		return idServicioOrganismo;
	}

	public void setIdServicioOrganismo(String idServicioOrganismo) {
		this.idServicioOrganismo = idServicioOrganismo;
	}

	public boolean isMultiorganismo() {
		return isMultiorganismo;
	}

	public void setMultiorganismo(boolean isMultiorganismo) {
		this.isMultiorganismo = isMultiorganismo;
	}

	public ServidoresOrganismosBean getServidorOrganismos() {
		return servidorOrganismos;
	}

	public void setServidorOrganismos(ServidoresOrganismosBean servidorOrganismos) {
		this.servidorOrganismos = servidorOrganismos;
	}

	public String[] getCheckDelListServiciosOrganismos() {
		return checkDelListServiciosOrganismos;
	}

	public void setCheckDelListServiciosOrganismos(String[] checkDelListServiciosOrganismos) {
		this.checkDelListServiciosOrganismos = checkDelListServiciosOrganismos;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

}
