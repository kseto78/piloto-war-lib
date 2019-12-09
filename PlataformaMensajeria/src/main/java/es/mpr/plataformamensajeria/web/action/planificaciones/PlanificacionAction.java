package es.mpr.plataformamensajeria.web.action.planificaciones;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;
import es.mpr.plataformamensajeria.beans.ReceptorSMSBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidorPushBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;
import es.mpr.plataformamensajeria.beans.TipoPlanificacionBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioCanal;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioReceptorSMS;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorPush;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los planificaciones.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n,
 * borrado y listado de los planificaciones
 * 
 * @author i-nercya
 * 
 */
@Controller("planificacionAction")
@Scope("prototype")
public class PlanificacionAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	protected static final String PLANIFICACIONAC = "PlanificacionAction - loadComboBusquedaServidores:";

	protected static final String INFOUSER = "infoUser";

	protected static final String NO_SE_HA_ACTUAL = "No se ha actualizado la planificaci&oacute;n. La configuraci&oacute;n seleccionada no garantiza el env&iacute;o de los mensajes";

	protected static final String LOGDOTACCION_DE = "log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION";

	protected static final String PLATAFORMADOTSE = "plataforma.servidores.planificacion.horaDesde.menor.error";

	protected static final String PLANIFICACIONAC0 = "PlanificacionAction - getComboConfiguracion:";

	protected static final String GENERALESDOTPAG = "generales.PAGESIZE";

	protected static final String R_CONST_REF = "1";

	protected static final String R_CONST_0 = "2";

	protected static final String R_CONST_1 = "3";

	protected static final String R_CONST_2 = "4";

	protected static final String R_CONST_3 = ":";

	protected static final String R_CONST_4 = "20";

	protected static final String ACTIVO = "activo";

	protected static final String PLATAFORMADOTPL = "plataforma.planificacion.delete.ok";

	protected static final String NO_SE_HA_ACTUAL0 = "No se ha actualizado la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones";

	protected static final String PLATAFORMADOTPL0 = "plataforma.planificacion.create.ok";

	protected static final String LOGDOTACCIONID_REF = "log.ACCIONID_ELIMINAR";

	protected static final String PLATAFORMADOTPL1 = "plataforma.planificacion.delete.error";

	protected static final String LOGDOTACCIONID_0 = "log.ACCIONID_INSERTAR";

	protected static final String LOGDOTACCION_DE0 = "log.ACCION_DESCRIPCION_ANADIR_PLANIFICACION";

	protected static final String NOUSER = "noUser";

	protected static final String LOGDOTACCION_IN = "log.ACCION_INSERTAR";

	protected static final String PLATAFORMADOTPL2 = "plataforma.planificacion.create.error";

	protected static final String LOGDOTACCION_EL = "log.ACCION_ELIMINAR";

	protected static final String TABLEID = "tableId";

	protected static final String GENERALESDOTREQ = "generales.REQUEST_ATTRIBUTE_PAGESIZE";

	protected static final String LOGDOTSOURCE_PL = "log.SOURCE_PLANIFICACIONES";

	protected static final String PLATAFORMADOTPL3 = "plataforma.planificacion.busquedas.formato.horadesdefin.error";

	/**  logger. */
	private static Logger logger = Logger.getLogger(PlanificacionAction.class);
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  servicio planificacion. */
	@Resource(name = "servicioPlanificacionImpl")
	private ServicioPlanificacion servicioPlanificacion;
	
	/**  servicio servicio. */
	@Resource(name = "servicioServicioImpl")
	private ServicioServicio servicioServicio;
	
	/**  servicio canal. */
	@Resource(name = "servicioCanalImpl")
	private ServicioCanal servicioCanal;
	
	/**  servicio servidor. */
	@Resource(name = "servicioServidorImpl")
	private ServicioServidor servicioServidor;
	
	/**  servicio aplicacion. */
	@Resource(name = "servicioAplicacionImpl")
	private ServicioAplicacion servicioAplicacion;
	
	/**  servicio proveedor SMS. */
	@Resource(name = "servicioProveedorSMSImpl")
	private ServicioProveedorSMS servicioProveedorSMS;
	
	/**  servicio receptor SMS. */
	@Resource(name = "servicioReceptorSMSImpl")
	private ServicioReceptorSMS servicioReceptorSMS;
	
	/**  servicio servidor push. */
	@Resource(name = "servicioServidorPushImpl")
	private ServicioServidorPush servicioServidorPush;

	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;

	/**  planificacion. */
	private PlanificacionBean planificacion;
	
	/**  combo aplicaciones. */
	private List<KeyValueObject> comboAplicaciones = new ArrayList<>();
	
	/**  combo canales. */
	private List<KeyValueObject> comboCanales = new ArrayList<>();
	
	/**  combo servidores. */
	@SuppressWarnings("unused")
	private List<KeyValueObject> comboServidores = new ArrayList<>();
	
	/**  combo busqueda servidores. */
	private List<KeyValueObject> comboBusquedaServidores = new ArrayList<>();
	
	/**  combo tipo planificaciones. */
	private List<KeyValueObject> comboTipoPlanificaciones = new ArrayList<>();
	
	/**  combo configuraciones. */
	private List<KeyValueObject> comboConfiguraciones = new ArrayList<>();
	
	/**  combo servicios. */
	private List<KeyValueObject> comboServicios = new ArrayList<>();
	
	/**  lista planificaciones. */
	public List<PlanificacionBean> listaPlanificaciones = null;
	
	/**  check del list. */
	private String[] checkDelList;
	
	/**  servicio id. */
	private String servicioId;
	
	/**  tipo planificacion id. */
	private String tipoPlanificacionId;
	
	/**  tipo parametro id. */
	private String tipoParametroId;
	
	/**  id aplicacion. */
	private String idAplicacion;
	
	/**  id planificacion. */
	private String idPlanificacion;
	
	/**  id servicio. */
	private String idServicio;
	
	/**  id servidor. */
	private String idServidor;
	
	/**  id proveedor SMS. */
	private String idProveedorSMS;
	
	/**  id receptor SMS. */
	private String idReceptorSMS;
	
	/**  id servidor push. */
	private String idServidorPush;
	
	/**  id servidor web push. */
	private String idServidorWebPush;
	
	/**  id organismo. */
	private String idOrganismo;
		
	/**  n action. */
	private String nAction;
	
	/**  result count. */
	private String resultCount;
	
	/**  modifica activo. */
	private boolean modificaActivo;


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
 * Search.
 *
 * @return the string
 * @throws BaseException the base exception
 */
////MIGRADO
	public String search() throws BaseException {
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}

		int page = getPage(TABLEID); 
		// Pagina a mostrar
		String order = getOrder(TABLEID); 
		// Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort(TABLEID); 
		// Columna usada para
														// ordenar
		boolean doSearch = true;

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_4));
		if (planificacion != null && !PlataformaMensajeriaUtil.isEmpty(planificacion.getHoraDesde())
				&& !validoFormatoHora(planificacion.getHoraDesde())) {
			addActionErrorSession(this.getText("plataforma.planificacion.busquedas.formato.horadesde.error"));
			doSearch = false;
		}
		if (planificacion != null && !PlataformaMensajeriaUtil.isEmpty(planificacion.getHoraHasta())
				&& !validoFormatoHora(planificacion.getHoraHasta())) {
			addActionErrorSession(this.getText("plataforma.planificacion.busquedas.formato.horahasta.error"));
			doSearch = false;
		}
		if (planificacion != null && !PlataformaMensajeriaUtil.isEmpty(planificacion.getHoraDesdeFin())
				&& !validoFormatoHora(planificacion.getHoraDesdeFin())) {
			addActionErrorSession(this.getText(PLATAFORMADOTPL3));
			doSearch = false;
		}
		if (planificacion != null && !PlataformaMensajeriaUtil.isEmpty(planificacion.getHoraHastaFin())
				&& !validoFormatoHora(planificacion.getHoraHastaFin())) {
			addActionErrorSession(this.getText(PLATAFORMADOTPL3));
			doSearch = false;
		}
		if (doSearch) {
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
			PaginatedList<PlanificacionBean> result = servicioPlanificacion.getPlanificaciones(inicio, export ? -1
					: Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_4)), order, columnSort, planificacion);
			Integer totalSize = result.getTotalList();

			listaPlanificaciones = result.getPageList();

			// Atributos de request
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

			if (!export) {
				getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ, null), 
						Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_4)));
			} else {
				getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ, null), totalSize);
			}

			if (listaPlanificaciones != null && !listaPlanificaciones.isEmpty()) {
				for (int indice = 0, s = listaPlanificaciones.size(); indice < s; indice++) {

					PlanificacionBean planificacion = listaPlanificaciones.get(indice);
					planificacion.setNombreServicio(StringEscapeUtils.escapeHtml(planificacion.getNombreServicio()));
					planificacion.setNombreServidor(StringEscapeUtils.escapeHtml(planificacion.getNombreServidor()));
				}
			}
		}
		return SUCCESS;
	}


/**
 * Creates the planificacion app.
 *
 * @return the string
 * @throws BaseException the base exception
 */
////MIGRADO
	public String createPlanificacionApp() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_IN, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String source = properties.getProperty(LOGDOTSOURCE_PL, null);
		String descripcion = properties.getProperty(LOGDOTACCION_DE0, null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (planificacion != null) {

			if (planificacion.getIsActivo() != null && planificacion.getIsActivo().indexOf(ACTIVO) != -1) {
				planificacion.setActivo(true);
			} else {
				planificacion.setActivo(false);
			}
			if (planificacionValida(planificacion)) {
				int valido = servicioPlanificacion.validaPlanificacionOptima(idPlanificacion,
						planificacion.getTipoPlanificacionId(), planificacion.getServidorId(), planificacion.getServicioId(),
						planificacion.getLunes(), planificacion.getMartes(), planificacion.getMiercoles(),
						planificacion.getJueves(), planificacion.getViernes(), planificacion.getSabado(),
						planificacion.getDomingo(), planificacion.getHoraHasta(), planificacion.getHoraDesde());

				// Si valido = 1 es correcto
				switch (valido) {
				case 1:
					Integer id = servicioPlanificacion.newPlanificacion(planificacion, source, accion, accionId,
							descripcion);
					this.idPlanificacion = id.toString();
					addActionMessageSession(this.getText(PLATAFORMADOTPL0));
					break;
				case 2:
					addActionErrorSession("No se ha guardado la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
					break;
				default:
					addActionErrorSession("No se ha guardado la planificaci&oacute;n. La configuraci&oacute;n seleccionada no garantiza el env&iacute;o de los mensajes");
					break;
				}

			} else {
				return ERROR;
			}

		} else {
			addActionErrorSession(this.getText(PLATAFORMADOTPL2));
			return ERROR;
		}
		return SUCCESS;

	}

	/**
	 * Creates the.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String create() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_IN, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String source = properties.getProperty(LOGDOTSOURCE_PL, null);
		String descripcion = properties.getProperty(LOGDOTACCION_DE0, null);	
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (planificacion != null) {
			
			if (planificacion.getIsActivo() != null && planificacion.getIsActivo().indexOf(ACTIVO) != -1) {
				planificacion.setActivo(true);
			} else {
				planificacion.setActivo(false);
			}
			if (planificacionValida(planificacion)) {
				Integer id = servicioPlanificacion.newPlanificacion(planificacion, source, accion, accionId, descripcion);
				this.idPlanificacion = id.toString();
				addActionMessageSession(this.getText(PLATAFORMADOTPL0));
				
			} else {
				return ERROR;
			}

		} else {
			addActionErrorSession(this.getText(PLATAFORMADOTPL2));
			return ERROR;
		}
		return SUCCESS;

	}

	
	/**
	 * Update planificacion view app.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String updatePlanificacionViewApp() throws BaseException {
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		modificaActivo = false;
		int valido = servicioPlanificacion.validaPlanificacionOptima(idPlanificacion,
				planificacion.getTipoPlanificacionId(), planificacion.getServidorId(), planificacion.getServicioId(), planificacion.getLunes(),
				planificacion.getMartes(), planificacion.getMiercoles(), planificacion.getJueves(),
				planificacion.getViernes(), planificacion.getSabado(), planificacion.getDomingo(),
				planificacion.getHoraHasta(), planificacion.getHoraDesde());
		
		// Si valido = 1 es correcto
		switch (valido) {
		case 1:
			update();
			return SUCCESS;
		case 2:
			addActionErrorSession(NO_SE_HA_ACTUAL0);
			return ERROR;
		default:
			addActionErrorSession(NO_SE_HA_ACTUAL);
			return ERROR;
		}
	}

	
	/**
	 * Update planificacion servicio.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String updatePlanificacionServicio() throws BaseException {
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		modificaActivo = false;
		int valido = servicioPlanificacion.validaPlanificacionOptima(idPlanificacion,
				planificacion.getTipoPlanificacionId(), planificacion.getServidorId(), planificacion.getServicioId(), planificacion.getLunes(),
				planificacion.getMartes(), planificacion.getMiercoles(), planificacion.getJueves(),
				planificacion.getViernes(), planificacion.getSabado(), planificacion.getDomingo(),
				planificacion.getHoraHasta(), planificacion.getHoraDesde());

		switch (valido) {
		case 1:
			update();
			return SUCCESS;
		case 2:
			addActionErrorSession(NO_SE_HA_ACTUAL0);
			return ERROR;
		default:
			addActionErrorSession(NO_SE_HA_ACTUAL);
			return ERROR;
		}
	}

	/**
	 * Update planificacion organismo.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	/////MIGRADO
	public String updatePlanificacionOrganismo() throws BaseException {
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		modificaActivo = false;
		int valido = servicioPlanificacion.validaPlanificacionOptimaOrganismo(idPlanificacion,
				planificacion.getTipoPlanificacionId(), planificacion.getServidorId(), planificacion.getServicioId(), planificacion.getLunes(),
				planificacion.getMartes(), planificacion.getMiercoles(), planificacion.getJueves(),
				planificacion.getViernes(), planificacion.getSabado(), planificacion.getDomingo(),
				planificacion.getHoraHasta(), planificacion.getHoraDesde(), Integer.valueOf(idOrganismo));

		switch (valido) {
		case 1:
			/* String retorno = */update();
			return SUCCESS;
		case 2:
			addActionErrorSession(NO_SE_HA_ACTUAL0);
			return ERROR;
		default:
			addActionErrorSession(NO_SE_HA_ACTUAL);
			return ERROR;
		}
	}

	/**
	 * Update planificacion server.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String updatePlanificacionServer() throws BaseException {
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		modificaActivo = false;
		int valido = servicioPlanificacion.validaPlanificacionServidor(idPlanificacion, planificacion.getServidorId(),
				planificacion.getLunes(), planificacion.getMartes(), planificacion.getMiercoles(),
				planificacion.getJueves(), planificacion.getViernes(), planificacion.getSabado(),
				planificacion.getDomingo(), planificacion.getHoraHasta(), planificacion.getHoraDesde());

		if (valido == 1) {
			return update();
		} else {
			addActionErrorSession(NO_SE_HA_ACTUAL0);
			return ERROR;
		}
	}

	/**
	 * Update.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	/////MIGRADO
	public String update() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty(LOGDOTSOURCE_PL, null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ACTUALIZAR_PLANIFICACION", null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		PlanificacionBean planificacionBBDD = null;
		if (planificacion == null) {
			addActionErrorSession(this.getText("plataforma.planificacion.update.error"));

		} else {
			if (planificacion.getPlanificacionId() == null) {
				if (idPlanificacion != null) {
					planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
					planificacionBBDD = servicioPlanificacion.loadPlanificacion(planificacion);
				} else {
					String idPlanificacion = (String) request.getAttribute("idPlanificacion");
					if (idPlanificacion != null) {
						planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
						planificacionBBDD = servicioPlanificacion.loadPlanificacion(planificacion);
					}
				}
			} else {
				planificacionBBDD = servicioPlanificacion.loadPlanificacion(planificacion);

			}
			if (planificacionBBDD != null) {
				planificacionBBDD.setServicioId(planificacion.getServicioId());
				planificacionBBDD.setLunes(planificacion.getLunes());
				planificacionBBDD.setMartes(planificacion.getMartes());
				planificacionBBDD.setMiercoles(planificacion.getMiercoles());
				planificacionBBDD.setJueves(planificacion.getJueves());
				planificacionBBDD.setViernes(planificacion.getViernes());
				planificacionBBDD.setSabado(planificacion.getSabado());
				planificacionBBDD.setDomingo(planificacion.getDomingo());
				planificacionBBDD.setHoraDesde(planificacion.getHoraDesde());
				planificacionBBDD.setHoraHasta(planificacion.getHoraHasta());
				planificacionBBDD.setServidorId(planificacion.getServidorId());
				planificacionBBDD.setActivo(planificacion.getActivo());
				
				planificacionBBDD.setTipoPlanificacionId(planificacion.getTipoPlanificacionId());
				planificacionBBDD.setServidorId(planificacion.getServidorId());
				planificacionBBDD.setAplicacionId(planificacion.getAplicacionId());
				if (planificacionBBDD.getServicioId() != null && planificacionBBDD.getServicioId() == 0) {
					planificacionBBDD.setServicioId(null);
				}
			}
			if (planificacionValida(planificacion)) {
				servicioPlanificacion.updatePlanificacion(planificacionBBDD, source, accion, accionId, descripcion);
				addActionMessageSession(this.getText("plataforma.planificacion.update.ok"));
			} else {
				return ERROR;
			}

		}
		return SUCCESS;

	}

	/**
	 * Load.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	/////MIGRADO
	public String load() throws BaseException {
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (idPlanificacion == null) {
			throw new BusinessException("EL idPlanificacion recibido es nulo");
		}
		try {
			modificaActivo = true;
			planificacion = new PlanificacionBean();
			planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
			planificacion = servicioPlanificacion.loadPlanificacion(planificacion);
			if (servicioId == null && planificacion.getServicioId() != null) {
				servicioId = planificacion.getServicioId().toString();
			}

			if (planificacion.getTipoPlanificacionId() != null && planificacion.getTipoPlanificacionId() == 1) {
				tipoPlanificacionId = R_CONST_0;
			} else if (planificacion.getTipoPlanificacionId() != null && planificacion.getTipoPlanificacionId() == 2) {
				tipoPlanificacionId = R_CONST_REF;
			} else if (planificacion.getTipoPlanificacionId() != null && planificacion.getTipoPlanificacionId() == 3) {
				tipoPlanificacionId = R_CONST_1;
			} else if (planificacion.getTipoPlanificacionId() != null && planificacion.getTipoPlanificacionId() == 4) {
				tipoPlanificacionId = R_CONST_2;
			}

			return SUCCESS;
		} catch (NumberFormatException |  BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { planificacion
					.getPlanificacionId().toString() });
			logger.error("PlanificacionAction - load:" + e);
			throw new BusinessException(mensg);
		} 

	}

	/**
	 * Delete planificacion servicio view app.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String deletePlanificacionServicioViewApp() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_EL, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_REF, null));
		String source = properties.getProperty("log.SOURCE_SERVICIOS", null);
		String descripcion = properties.getProperty(LOGDOTACCION_DE, null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		boolean sw = true;
		if (idPlanificacion == null) {
			addActionErrorSession(this.getText(PLATAFORMADOTPL1));
		} else {
			planificacion = new PlanificacionBean();
			planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
			sw = servicioPlanificacion.deletePlanificacion(planificacion, source, accion, accionId, descripcion);
			if (!sw) {
				addActionErrorSession(this.getText(PLATAFORMADOTPL1));
			} else {

				addActionMessageSession(this.getText(PLATAFORMADOTPL));
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
	/////MIGRADO
	public String delete() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_EL, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_REF, null));
		String source = properties.getProperty(LOGDOTSOURCE_PL, null);
		String descripcion = properties.getProperty(LOGDOTACCION_DE, null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		boolean sw = true;
		if (idPlanificacion == null) {
			addActionErrorSession(this.getText(PLATAFORMADOTPL1));
		} else {
			planificacion = new PlanificacionBean();
			planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
			sw = servicioPlanificacion.deletePlanificacion(planificacion, source, accion, accionId, descripcion);
			if (!sw) {
				addActionErrorSession(this.getText(PLATAFORMADOTPL1));
			} else {
				addActionMessageSession(this.getText(PLATAFORMADOTPL));
			}
		}
		return SUCCESS;

	}

	/**
	 * Delete selected.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	/////MIGRADO
	public String deleteSelected() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_EL, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_REF, null));
		String source = properties.getProperty(LOGDOTSOURCE_PL, null);
		String descripcion = properties.getProperty(LOGDOTACCION_DE, null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		boolean sw = true;
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.planificacion.deleteSelected.error"));

		} else {
			for (String id : checkDelList) {
				planificacion = new PlanificacionBean();
				planificacion.setPlanificacionId(Integer.valueOf(id));
				sw = servicioPlanificacion.deletePlanificacion(planificacion, source, accion, accionId, descripcion);
				if (!sw) {
					addActionErrorSession(this.getText(PLATAFORMADOTPL1) + " [Identificador: "
							+ planificacion.getPlanificacionId() + "]");
				}
			}
			if (sw) {
				addActionMessageSession(this.getText("plataforma.planificacion.deleteSelected.ok"));
			}

		}
		return SUCCESS;

	}

	/**
	 * Load servidores by tipo plan.
	 *
	 * @return the string
	 */
	/////MIGRADO
	public String loadServidoresByTipoPlan() {
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if ((tipoPlanificacionId == null || "".equals(tipoPlanificacionId)) && planificacion != null && planificacion.getTipoPlanificacionId() != null) {
			tipoPlanificacionId = planificacion.getTipoPlanificacionId().toString();
		}
		if (R_CONST_REF.equals(tipoPlanificacionId)) {
			tipoPlanificacionId = R_CONST_0;
		} else if (R_CONST_0.equals(tipoPlanificacionId)) {
			tipoPlanificacionId = R_CONST_REF;
		}
		return SUCCESS;
	}

	
	/**
	 * Load tipo planificacion by servicio canal.
	 *
	 * @return the string
	 */
	////MIGRADO
	public String loadTipoPlanificacionByServicioCanal() {
		if (servicioId != null) {
			try {
				comboTipoPlanificaciones = loadTipoPlanificaciones();
			} catch (BusinessException e) {
				logger.error("PlanificacionAction - loadTipoPlanificacionByServicioCanal:" + e);
			}
		}
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	////MIGRADO
	@Override
	public void prepare() throws Exception {
		if (idServicio != null) {
			ServicioBean servicioBean = new ServicioBean();
			servicioBean.setId(Integer.valueOf(idServicio));

			ServicioBean servicio = servicioServicio.loadServicio(servicioBean);
			String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
			if (PlataformaMensajeriaUtil.ROL_ADMINISTRADOR.equals(rolUsuario)||PlataformaMensajeriaUtil.ROL_CAID.equals(rolUsuario)) {
				comboConfiguraciones = getComboConfiguracion(servicio.getCanalid());
			} else if (PlataformaMensajeriaUtil.ROL_PROPIETARIO.equals(rolUsuario)) {
				comboConfiguraciones = getComboServidoresServicio(servicio.getServicioId());
			}
			if (planificacion == null) {
				planificacion = new PlanificacionBean();
			}
			planificacion.setServicioId(Integer.valueOf(idServicio));
			if (servicio.getCanalid() == 1) {
				planificacion.setTipoPlanificacionId(Integer.valueOf(1));
			} else if (servicio.getCanalid() == 2) {
				planificacion.setTipoPlanificacionId(Integer.valueOf(2));
			} else if (servicio.getCanalid() == 3) {
				planificacion.setTipoPlanificacionId(Integer.valueOf(3));
			} else if (servicio.getCanalid() == 4) {
				planificacion.setTipoPlanificacionId(Integer.valueOf(4));
			}
		} else {
			if (tipoPlanificacionId == null && servicioId == null) {
				comboAplicaciones = loadComboAplicaciones();
				comboCanales = loadComboCanales();
				comboBusquedaServidores = loadComboBusquedaServidores();
				comboServicios = loadComboServicios();
				comboTipoPlanificaciones = loadTipoPlanificaciones();
			}
		}
	}

	/**
	 * Obtener combo configuracion.
	 *
	 * @param idCanal the id canal
	 * @return combo configuracion
	 */
	////MIGRADO
	private List<KeyValueObject> getComboConfiguracion(Integer idCanal) {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option;
		ArrayList<ProveedorSMSBean> keysSMS = null;
		ArrayList<ServidorBean> keysSMTP = null;
		ArrayList<ReceptorSMSBean> keysReceptorSMS = null;
		ArrayList<ServidorPushBean> keysServidorPush = null;
		if (idCanal != null && idCanal.intValue() == Integer.parseInt(properties.getProperty("generales.CANAL_SMS_ID", null))) {
			try {
				keysSMS = (ArrayList<ProveedorSMSBean>) servicioProveedorSMS.getProveedoresSMS(Integer
						.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_SMS", null)));
			} catch (BusinessException e) {
				logger.error(PLANIFICACIONAC0 + e);
			}

			if (keysSMS != null && !keysSMS.isEmpty()) {
				for (ProveedorSMSBean key : keysSMS) {

					option = new KeyValueObject();
					option.setCodigo(key.getProveedorSMSId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
			}
		} else if (idCanal != null && idCanal.intValue() == Integer.parseInt(properties.getProperty("generales.CANAL_SMTP_ID", null))) {
			try {
				keysSMTP = (ArrayList<ServidorBean>) servicioServidor.getServidores(Integer.parseInt(properties
						.getProperty("generales.TIPO_SERVIDOR_SMTP", null)));
			} catch (BusinessException e) {
				logger.error(PLANIFICACIONAC0 + e);
			}

			if (keysSMTP != null && !keysSMTP.isEmpty()) {
				for (ServidorBean key : keysSMTP) {

					option = new KeyValueObject();
					option.setCodigo(key.getServidorid().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
			}
		} else if (idCanal != null && idCanal.intValue() == Integer.parseInt(properties.getProperty("generales.CANAL_RECEPCION_SMS_ID", null))) {
			try {
				keysReceptorSMS = (ArrayList<ReceptorSMSBean>) servicioReceptorSMS.getReceptoresSMS(Integer
						.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_RECEPCION", null)));
			} catch (BusinessException e) {
				logger.error(PLANIFICACIONAC0 + e);
			}

			if (keysReceptorSMS != null && !keysReceptorSMS.isEmpty()) {
				for (ReceptorSMSBean key : keysReceptorSMS) {

					option = new KeyValueObject();
					option.setCodigo(key.getReceptorSMSId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
			}
		} else if (idCanal != null && idCanal.intValue() == Integer.parseInt(properties.getProperty("generales.CANAL_SERVIDOR_PUSH_ID", null))) {
			try {
				keysServidorPush = (ArrayList<ServidorPushBean>) servicioServidorPush.getServidoresPush(Integer
						.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_PUSH", null)));
			} catch (BusinessException e) {
				logger.error(PLANIFICACIONAC0 + e);
			}

			if (keysServidorPush != null && !keysServidorPush.isEmpty()) {
				for (ServidorPushBean key : keysServidorPush) {

					option = new KeyValueObject();
					option.setCodigo(key.getServidorPushId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
			}
		}
		return result;
	}

	/**
	 * Obtener combo servidores servicio.
	 *
	 * @param servicioId the servicio id
	 * @return combo servidores servicio
	 * @throws BusinessException the business exception
	 */
	// //MIGRADO
	private List<KeyValueObject> getComboServidoresServicio(Integer servicioId) throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;

		ArrayList<ServidoresServiciosBean> keys;

		if (null != servicioId) {
			keys = (ArrayList<ServidoresServiciosBean>) servicioServicio.getServidoresServicios(servicioId.toString());

			if (keys != null && !keys.isEmpty()) {
				for (ServidoresServiciosBean key : keys) {

					option = new KeyValueObject();
					option.setCodigo(key.getServidorId().toString());
					option.setDescripcion(key.getNombreServidor());
					result.add(option);
				}
			}
		}

		return result;
	}

/**
 * Load combo servicios.
 *
 * @return the list
 * @throws BusinessException the business exception
 */
////MIGRADO
	private List<KeyValueObject> loadComboServicios() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<ServicioBean> keys;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if (planificacion != null && planificacion.getAplicacionId() != null) {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServiciosByAplicacionId(planificacion
					.getAplicacionId());
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
 * Load combo servidores.
 *
 * @param tipoPlanificacionId the tipo planificacion id
 * @return the list
 */
////MIGRADO
	private List<KeyValueObject> loadComboServidores(String tipoPlanificacionId) {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<ServidorBean> keys = null;
		try {

			keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresByTipoPlanificacion(tipoPlanificacionId);

		} catch (BusinessException e) {
			logger.error(PLANIFICACIONAC + e);
		}

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
	 * Load combo servidores.
	 *
	 * @return the list
	 */
	///MIGRADO
	private List<KeyValueObject> loadComboServidores() {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<ServidorBean> keys = null;
		try {
			if (planificacion != null && planificacion.getTipoPlanificacionId() != null
					&& R_CONST_REF.equals(planificacion.getTipoPlanificacionId())) {
				keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresByTipoPlanificacion(R_CONST_0);
			} else if (planificacion != null && planificacion.getTipoPlanificacionId() != null
					&& R_CONST_0.equals(planificacion.getTipoPlanificacionId())) {
				keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresByTipoPlanificacion(R_CONST_REF);
			} else if (planificacion != null && planificacion.getTipoPlanificacionId() != null
					&& R_CONST_1.equals(planificacion.getTipoPlanificacionId())) {
				keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresByTipoPlanificacion(R_CONST_1);
			}
		} catch (BusinessException e) {
			logger.error("PlanificacionAction - loadComboServidores:" + e);
		}

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
 * Load combo busqueda servidores.
 *
 * @return the list
 */
////MIGRADO
	private List<KeyValueObject> loadComboBusquedaServidores() {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<ServidorBean> keys = null;
		try {
			keys = (ArrayList<ServidorBean>) servicioServidor.getAllServidores();
		} catch (BusinessException e) {
			logger.error(PLANIFICACIONAC + e);
		}

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
 * Load combo canales.
 *
 * @return the list
 */
////MIGRADO
	private List<KeyValueObject> loadComboCanales() {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<CanalBean> keys = null;
		try {
			keys = (ArrayList<CanalBean>) servicioCanal.getCanales();
		} catch (BusinessException e) {
			logger.error("PlanificacionAction - loadComboCanales:" + e);
		}

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
 * Load tipo planificaciones.
 *
 * @return the list
 * @throws BusinessException the business exception
 */
/////MIGRADO
	private List<KeyValueObject> loadTipoPlanificaciones() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<TipoPlanificacionBean> keys;
		ServicioBean servicioBean = null;
		if (servicioId != null) {
			ServicioBean servicio = new ServicioBean();
			servicio.setServicioId(Integer.valueOf(servicioId));
			servicioBean = servicioServicio.loadServicio(servicio);
		}

		keys = new ArrayList<>();
		TipoPlanificacionBean tp = new TipoPlanificacionBean();
		if (servicioBean != null && servicioBean.getCanalid() != null && servicioBean.getCanalid() == 1) {
			tp.setNombre("Email");
			tp.setTipoPlanificacionId(1);
			keys.add(tp);
		} else if (servicioBean != null && servicioBean.getCanalid() != null && servicioBean.getCanalid() == 2) {
			tp.setNombre("SMS");
			tp.setTipoPlanificacionId(2);
			keys.add(tp);
		} else if (servicioBean != null && servicioBean.getCanalid() != null && servicioBean.getCanalid() == 3) {
			tp.setNombre("Receptor SMS");
			tp.setTipoPlanificacionId(3);
			keys.add(tp);
		} else if (servicioBean != null && servicioBean.getCanalid() != null && servicioBean.getCanalid() == 4) {
			tp.setNombre("Notificaciones Push");
			tp.setTipoPlanificacionId(4);
			keys.add(tp);
		}
		if (!keys.isEmpty()) {
			for (TipoPlanificacionBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getTipoPlanificacionId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}

/**
 * Load combo aplicaciones.
 *
 * @return the list
 */
/////MIGRADO
	private List<KeyValueObject> loadComboAplicaciones() {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option = null;
		ArrayList<AplicacionBean> keys = null;
		try {
			keys = (ArrayList<AplicacionBean>) servicioAplicacion.getAplicaciones();
		} catch (BusinessException e) {
			logger.error("PlanificacionAction - loadComboAplicaciones:" + e);
		}

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
	 * Verifica que se ha introducido por lo menos un día de la semana y las
	 * horas de inicio y fin.
	 *
	 * @param planificacionServidor the planificacion servidor
	 * @return true, if successful
	 */
	//////MIGRADO
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
				if (sw && !validoHoras(planificacionServidor.getHoraDesde(), planificacionServidor.getHoraHasta())) {
					sw = false;
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

			if (PlataformaMensajeriaUtil.isEmptyNumber(planificacionServidor.getTipoPlanificacionId())) {
				addFieldErrorSession(this.getText("plataforma.servidores.planificacion.field.tipoplanificacion.error"));
				sw = false;
			}

		}
		return sw;
	}

	/**
	 * Valido horas.
	 *
	 * @param horaDesde the hora desde
	 * @param horaHasta the hora hasta
	 * @return true, if successful
	 */
	////MIGRADO
	private boolean validoHoras(String horaDesde, String horaHasta) {
		boolean sw = true;
		String[] horaDesdeArray = horaDesde.split(R_CONST_3);
		String[] horaHastaArray = horaHasta.split(R_CONST_3);
		int hDesde = Integer.parseInt(horaDesdeArray[0]);
		int mDesde = Integer.parseInt(horaDesdeArray[1]);
		int hHasta = Integer.parseInt(horaHastaArray[0]);
		int mHasta = Integer.parseInt(horaHastaArray[1]);
		if (hDesde > hHasta) {
			addFieldErrorSession(this.getText(PLATAFORMADOTSE));
			sw = false;
		} else if (hDesde == hHasta && mDesde > mHasta) {
			addFieldErrorSession(this.getText(PLATAFORMADOTSE));
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
	////MIGRADO
	private boolean validoFormatoHora(String hora) {
		boolean sw = true;
		if (!PlataformaMensajeriaUtil.isEmpty(hora) && !PlataformaMensajeriaUtil.validaFormatoHora(hora)) {
			sw = false;
		}
		return sw;
	}

	/**
	 * Obtener combo servidores.
	 *
	 * @return combo servidores
	 */
	////MIGRADO
	public List<KeyValueObject> getComboServidores() {
		if (tipoPlanificacionId != null) {
			return loadComboServidores(tipoPlanificacionId);
		} else if (planificacion != null && planificacion.getTipoPlanificacionId() != null) {
			String tipoPlanificacionId = planificacion.getTipoPlanificacionId().toString();
			if (R_CONST_0.equals(tipoPlanificacionId)) {
				tipoPlanificacionId = R_CONST_REF;
			}
			if (R_CONST_REF.equals(tipoPlanificacionId)) {
				tipoPlanificacionId = R_CONST_0;
			}
			if (R_CONST_1.equals(tipoPlanificacionId)) {
				tipoPlanificacionId = R_CONST_1;
			}
			if (R_CONST_2.equals(tipoPlanificacionId)) {
				tipoPlanificacionId = R_CONST_2;
			}

			return loadComboServidores(tipoPlanificacionId);
		}
		return loadComboServidores();
	}

	/**
	 * Obtener volver.
	 *
	 * @return volver
	 */
	public String getVolver() {
		String volver = "buscarPlanificaciones.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
		}
		return volver;
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
	 * Obtener tipo parametro id.
	 *
	 * @return tipo parametro id
	 */
	public String getTipoParametroId() {
		return tipoParametroId;
	}

	/**
	 * Modificar tipo parametro id.
	 *
	 * @param tipoParametroId new tipo parametro id
	 */
	public void setTipoParametroId(String tipoParametroId) {
		this.tipoParametroId = tipoParametroId;
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
	 * Obtener combo servicios.
	 *
	 * @return combo servicios
	 */
	public List<KeyValueObject> getComboServicios() {
		return comboServicios;
	}

	/**
	 * Modificar combo servicios.
	 *
	 * @param comboServicios new combo servicios
	 */
	public void setComboServicios(List<KeyValueObject> comboServicios) {
		this.comboServicios = comboServicios;
	}

	/**
	 * Obtener planificacion.
	 *
	 * @return planificacion
	 */
	public PlanificacionBean getPlanificacion() {
		return planificacion;
	}

	/**
	 * Modificar planificacion.
	 *
	 * @param planificacion new planificacion
	 */
	public void setPlanificacion(PlanificacionBean planificacion) {
		this.planificacion = planificacion;
	}

	/**
	 * Obtener lista planificaciones.
	 *
	 * @return lista planificaciones
	 */
	public List<PlanificacionBean> getListaPlanificaciones() {
		return listaPlanificaciones;
	}

	/**
	 * Modificar lista planificaciones.
	 *
	 * @param listaPlanificaciones new lista planificaciones
	 */
	public void setListaPlanificaciones(List<PlanificacionBean> listaPlanificaciones) {
		this.listaPlanificaciones = listaPlanificaciones;
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
	 * Obtener id servidor push.
	 *
	 * @return id servidor push
	 */
	public String getIdServidorPush() {
		return idServidorPush;
	}

	/**
	 * Modificar id servidor push.
	 *
	 * @param idServidorPush new id servidor push
	 */
	public void setIdServidorPush(String idServidorPush) {
		this.idServidorPush = idServidorPush;
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
	 * Obtener combo busqueda servidores.
	 *
	 * @return combo busqueda servidores
	 */
	public List<KeyValueObject> getComboBusquedaServidores() {
		return comboBusquedaServidores;
	}

	/**
	 * Modificar combo busqueda servidores.
	 *
	 * @param comboBusquedaServidores new combo busqueda servidores
	 */
	public void setComboBusquedaServidores(List<KeyValueObject> comboBusquedaServidores) {
		this.comboBusquedaServidores = comboBusquedaServidores;
	}

	/**
	 * Obtener servicio id.
	 *
	 * @return servicio id
	 */
	public String getServicioId() {
		return servicioId;
	}

	/**
	 * Modificar servicio id.
	 *
	 * @param servicioId new servicio id
	 */
	public void setServicioId(String servicioId) {
		this.servicioId = servicioId;
	}

	/**
	 * Obtener tipo planificacion id.
	 *
	 * @return tipo planificacion id
	 */
	public String getTipoPlanificacionId() {
		return tipoPlanificacionId;
	}

	/**
	 * Modificar tipo planificacion id.
	 *
	 * @param tipoPlanificacionId new tipo planificacion id
	 */
	public void setTipoPlanificacionId(String tipoPlanificacionId) {
		this.tipoPlanificacionId = tipoPlanificacionId;
	}

	/**
	 * Obtener combo tipo planificaciones.
	 *
	 * @return combo tipo planificaciones
	 */
	public List<KeyValueObject> getComboTipoPlanificaciones() {
		return comboTipoPlanificaciones;
	}

	/**
	 * Modificar combo tipo planificaciones.
	 *
	 * @param comboTipoPlanificaciones new combo tipo planificaciones
	 */
	public void setComboTipoPlanificaciones(List<KeyValueObject> comboTipoPlanificaciones) {
		this.comboTipoPlanificaciones = comboTipoPlanificaciones;
	}

	/**
	 * Modificar combo servidores.
	 *
	 * @param comboServidores new combo servidores
	 */
	public void setComboServidores(List<KeyValueObject> comboServidores) {
		this.comboServidores = comboServidores;
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
	 * Obtener id proveedor SMS.
	 *
	 * @return id proveedor SMS
	 */
	public String getIdProveedorSMS() {
		return idProveedorSMS;
	}

	/**
	 * Modificar id proveedor SMS.
	 *
	 * @param idProveedorSMS new id proveedor SMS
	 */
	public void setIdProveedorSMS(String idProveedorSMS) {
		this.idProveedorSMS = idProveedorSMS;
	}

	/**
	 * Obtener id receptor SMS.
	 *
	 * @return id receptor SMS
	 */
	public String getIdReceptorSMS() {
		return idReceptorSMS;
	}

	/**
	 * Modificar id receptor SMS.
	 *
	 * @param idReceptorSMS new id receptor SMS
	 */
	public void setIdReceptorSMS(String idReceptorSMS) {
		this.idReceptorSMS = idReceptorSMS;
	}

	/**
	 * Obtener id servidor.
	 *
	 * @return id servidor
	 */
	public String getIdServidor() {
		return idServidor;
	}

	/**
	 * Modificar id servidor.
	 *
	 * @param idServidor new id servidor
	 */
	public void setIdServidor(String idServidor) {
		this.idServidor = idServidor;
	}

	/**
	 * Obtener n action.
	 *
	 * @return n action
	 */
	public String getnAction() {
		return nAction;
	}

	/**
	 * Modificar n action.
	 *
	 * @param nAction new n action
	 */
	public void setnAction(String nAction) {
		this.nAction = nAction;
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
	 * Comprueba modifica activo.
	 *
	 * @return true, si es modifica activo
	 */
	public boolean isModificaActivo() {
		return modificaActivo;
	}

	/**
	 * Modificar modifica activo.
	 *
	 * @param modificaActivo new modifica activo
	 */
	public void setModificaActivo(boolean modificaActivo) {
		this.modificaActivo = modificaActivo;
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
	 * Obtener id servidor web push.
	 *
	 * @return the idServidorWebPush
	 */
	public String getIdServidorWebPush() {
		return idServidorWebPush;
	}
	
	/**
	 * Modificar id servidor web push.
	 *
	 * @param idServidorWebPush the idServidorWebPush to set
	 */
	public void setIdServidorWebPush(String idServidorWebPush) {
		this.idServidorWebPush = idServidorWebPush;
	}
	
}
