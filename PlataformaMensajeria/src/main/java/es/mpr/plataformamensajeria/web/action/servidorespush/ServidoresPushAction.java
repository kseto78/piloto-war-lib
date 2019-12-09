package es.mpr.plataformamensajeria.web.action.servidorespush;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.map.j2ee.util.StringUtil;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.ParametroServidorBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.PlataformaBean;
import es.mpr.plataformamensajeria.beans.ServidorPushBean;
import es.mpr.plataformamensajeria.beans.TipoParametroBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlataforma;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorPush;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoParametro;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Clase Action de Struts2 para la gestion de los servidores Push.
 * 
 * <p>
 * Proporciona metodos para la creacion, modificacion, borrado y listado de los
 * servidores Push.
 * 
 * @author jgonzvil
 * 
 */
@Controller("servidorPushAction")
@Scope("prototype")
public class ServidoresPushAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	protected static final String INFOUSER = "infoUser";

	protected static final String LOGDOTACCION_DE = "log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION";

	protected static final String PLATAFORMADOTSE = "plataforma.servidores.planificacion.horaDesde.menor.error";

	protected static final String LOGDOTACCION_AC = "log.ACCION_ACTUALIZAR";

	protected static final String GENERALESDOTREQ = "generales.REQUEST_ATTRIBUTE_TOTALSIZE";

	protected static final String SERVIDORESPUSHA = "ServidoresPushAction - getLoadPlanificacionesServidorPush:";

	protected static final String SERVIDORESPUSHA0 = "ServidoresPushAction - getParametrosServidorPush:";

	protected static final String R_CONST_REF = ":";

	protected static final String R_CONST_0 = "20";

	protected static final String LOGDOTSOURCE_SE = "log.SOURCE_SERVIDORES_PUSH";

	protected static final String LOGDOTACCIONID_REF = "log.ACCIONID_ELIMINAR";

	protected static final String ERRORSDOTACTION = "errors.action.organismo.loadOrganismo";

	protected static final String TABLEID = "tableId";

	protected static final String LOGDOTACCIONID_0 = "log.ACCIONID_ACTUALIZAR";

	/** Constante GENERALES_REQUEST_ATTRIBUTE_PAGESIZE. */
	private static final String GENERALES_REQUEST_ATTRIBUTE_PAGESIZE = "generales.REQUEST_ATTRIBUTE_PAGESIZE";

	/** Constante GENERALES_TIPO_SERVIDOR_PUSH. */
	private static final String GENERALES_TIPO_SERVIDOR_PUSH = "generales.TIPO_SERVIDOR_PUSH";

	/** Constante GENERALES_PAGESIZE. */
	private static final String GENERALES_PAGESIZE = "generales.PAGESIZE";

	/** Constante NO_USER. */
	private static final String NO_USER = "noUser";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(ServidoresPushAction.class);

	/**  servicio servidor push. */
	@Resource(name = "servicioServidorPushImpl")
	private transient ServicioServidorPush servicioServidorPush;

	/**  servicio tipo parametro. */
	@Resource(name = "servicioTipoParametroImpl")
	private transient ServicioTipoParametro servicioTipoParametro;

	/**  servicio parametro servidor. */
	@Resource(name = "servicioParametroServidorImpl")
	private transient ServicioParametroServidor servicioParametroServidor;

	/**  servicio planificacion. */
	@Resource(name = "servicioPlanificacionImpl")
	private transient ServicioPlanificacion servicioPlanificacion;

	/**  servicio plataforma. */
	@Resource(name = "servicioPlataformaImpl")
	private transient ServicioPlataforma servicioPlataforma;

	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private transient PlataformaMensajeriaProperties properties;

	/**  parametro servidor. */
	private ParametroServidorBean parametroServidor;
	
	/**  servidor push. */
	private ServidorPushBean servidorPush;
	
	/**  planificacion servidor. */
	private PlanificacionBean planificacionServidor;

	/**  combo tipo parametros. */
	List<KeyValueObject> comboTipoParametros = new ArrayList<>();
	
	/**  combo plataformas. */
	List<KeyValueObject> comboPlataformas = new ArrayList<>();

	/**  lista servidores push. */
	public transient List<ServidorPushBean> listaServidoresPush = null;
	
	/**  lista parametros servidor. */
	private List<ParametroServidorBean> listaParametrosServidor = null;
	
	/**  lista planificaciones servidor. */
	private List<PlanificacionBean> listaPlanificacionesServidor = null;
	
	/**  tipos parametros. */
	ArrayList<TipoParametroBean> tiposParametros = new ArrayList<>();
	
	/**  check del list. */
	private String[] checkDelList;

	/**  tipo parametro id. */
	private String tipoParametroId;
	
	/**  id servidor push. */
	private String idServidorPush;
	
	/**  planificacion id. */
	private String planificacionId;
	
	/**  parametro servidor id. */
	private String parametroServidorId;
	
	/**  result count. */
	private String resultCount;

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
	// //MIGRADO
	public String search() throws BaseException {
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return ServidoresPushAction.NO_USER;
		}
		int page = getPage(TABLEID); 
		// Pagina a mostrar
		String order = getOrder(TABLEID); 
		// Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort(TABLEID); 
		// Columna usada para
														// ordenar

		if (servidorPush != null && servidorPush.getNombre() != null && servidorPush.getNombre().isEmpty()) {
			servidorPush.setNombre(null);
		}

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(GENERALES_PAGESIZE, R_CONST_0));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServidorPushBean> result = servicioServidorPush.getServidoresPush(inicio,
				export ? -1 : Integer.parseInt(properties.getProperty(GENERALES_PAGESIZE, R_CONST_0)), order,
				columnSort, servidorPush,
				Integer.parseInt(properties.getProperty(GENERALES_TIPO_SERVIDOR_PUSH, null)));
		Integer totalSize = result.getTotalList();

		listaServidoresPush = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ, null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty(GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null),
					Integer.parseInt(properties.getProperty(GENERALES_PAGESIZE, R_CONST_0)));
		} else {
			getRequest().setAttribute(properties.getProperty(GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null), totalSize);
		}

		if (listaServidoresPush != null && !listaServidoresPush.isEmpty()) {
			for (int indice = 0, s = listaServidoresPush.size(); indice < s; indice++) {

				ServidorPushBean servidorPush = listaServidoresPush.get(indice);
				servidorPush.setNombre(StringEscapeUtils.escapeHtml(servidorPush.getNombre()));
				servidorPush.setDescripcion(StringEscapeUtils.escapeHtml(servidorPush.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	// //MIGRADO
	public String execute() throws BaseException {
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return ServidoresPushAction.NO_USER;
		}
		int page = getPage(TABLEID); 
		// Pagina a mostrar
		String order = getOrder(TABLEID); 
		// Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort(TABLEID); 
		// Columna usada para
														// ordenar

		if (servidorPush != null && servidorPush.getNombre() != null && servidorPush.getNombre().isEmpty()) {
			servidorPush.setNombre(null);
		}

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(GENERALES_PAGESIZE, R_CONST_0));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServidorPushBean> result = servicioServidorPush.getServidoresPush(inicio,
				export ? -1 : Integer.parseInt(properties.getProperty(GENERALES_PAGESIZE, R_CONST_0)), order,
				columnSort, servidorPush,
				Integer.parseInt(properties.getProperty(GENERALES_TIPO_SERVIDOR_PUSH, null)));
		Integer totalSize = result.getTotalList();

		listaServidoresPush = result.getPageList();

		getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ, null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty(GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null),
					Integer.parseInt(properties.getProperty(GENERALES_PAGESIZE, R_CONST_0)));
		} else {
			getRequest().setAttribute(properties.getProperty(GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null), totalSize);
		}

		if (listaServidoresPush != null && !listaServidoresPush.isEmpty()) {
			for (int indice = 0, s = listaServidoresPush.size(); indice < s; indice++) {

				ServidorPushBean receptor = listaServidoresPush.get(indice);
				receptor.setNombre(StringEscapeUtils.escapeHtml(receptor.getNombre()));
				receptor.setDescripcion(StringEscapeUtils.escapeHtml(receptor.getDescripcion()));
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
		String source = properties.getProperty(LOGDOTSOURCE_SE, null);
		
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return ServidoresPushAction.NO_USER;
		}
		if (servidorPush != null) {
			if (servidorPush.getIsActivo() != null && servidorPush.getIsActivo().indexOf("'activo'") != -1) {
				servidorPush.setActivo(true);
			} else {
				servidorPush.setActivo(false);
			}
			if (validaServidor(servidorPush)) {
				Long idServidorPush = servicioServidorPush.newServidorPush(servidorPush, Integer.parseInt(properties.getProperty(GENERALES_TIPO_SERVIDOR_PUSH,null)), 
						source, accion, accionId);
				this.idServidorPush = idServidorPush.toString();
				addActionMessageSession(this.getText("plataforma.servidorpush.create.ok"));
			} else {
				return ERROR;
			}
		} else {
			addActionErrorSession(this.getText("plataforma.servidorpush.create.error"));
		}
		return SUCCESS;

	}

	/**
	 * Update.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String update() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String source = properties.getProperty(LOGDOTSOURCE_SE, null);
		
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return ServidoresPushAction.NO_USER;
		}
		ServidorPushBean servidorPushBBDD = null;
		if (servidorPush == null) {
			addActionErrorSession(this.getText("plataforma.servidorpush.update.error"));

		} else {
			
			if (servidorPush.getServidorPushId() == null) {
				if (idServidorPush != null) {
					servidorPush.setServidorPushId(Long.valueOf(idServidorPush));
					servidorPushBBDD = servicioServidorPush.loadServidorPush(servidorPush);
				} else {
					String idProvedorSMS = (String) request.getAttribute("idServidorPush");
					
					if (idProvedorSMS != null) {
						servidorPush.setId(Long.valueOf(idProvedorSMS));
						servidorPushBBDD = servicioServidorPush.loadServidorPush(servidorPush);
					}
				}

			} else {
				servidorPushBBDD = servicioServidorPush.loadServidorPush(servidorPush);

			}
			if (servidorPushBBDD != null) {
				servidorPushBBDD.setNombre(servidorPush.getNombre());
				servidorPushBBDD.setDescripcion(servidorPush.getDescripcion());
				servidorPushBBDD.setActivo(servidorPush.getActivo());
				servidorPushBBDD.setUrldestino(servidorPush.getUrldestino());
				servidorPushBBDD.setUrlfeedback(servidorPush.getUrlfeedback());
				servidorPushBBDD.setPlataformaid(servidorPush.getPlataformaid());
				servidorPushBBDD.setPordefecto(servidorPush.getPordefecto());
			}
			if (servidorPushBBDD != null && validaServidor(servidorPushBBDD)) {
				servicioServidorPush.updateServidorPush(servidorPushBBDD, source, accion, accionId);
				addActionMessageSession(this.getText("plataforma.servidorpush.update.ok"));
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
	// //MIGRADO
	public String load() throws BaseException {
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return ServidoresPushAction.NO_USER;
		}
		if (idServidorPush == null) {
			throw new BusinessException("EL idServidorPush recibido es nulo");
		}
		try {
			servidorPush = new ServidorPushBean();
			servidorPush.setServidorPushId(Long.valueOf(idServidorPush));
			servidorPush = servicioServidorPush.loadServidorPush(servidorPush);
			return SUCCESS;
		} catch (NumberFormatException | BusinessException e) {
			logger.error(e.getMessage(), e);
			String mensg = this.getText(ERRORSDOTACTION, new String[] { servidorPush
					.getServidorPushId().toString() });
			throw new BusinessException(mensg);
		}

	}

	/**
	 * Delete parametro servidor push.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String deleteParametroServidorPush() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_REF, null));
		String source = properties.getProperty(LOGDOTSOURCE_SE, null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PARAMETRO", null);

		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return ServidoresPushAction.NO_USER;
		}
		if (parametroServidorId == null) {
			addActionErrorSession(this.getText("plataforma.servidorpush.parametro.delete.error"));
		} else {
			parametroServidor = new ParametroServidorBean();
			parametroServidor.setParametroservidorid(Long.valueOf(parametroServidorId));
			servicioParametroServidor.deleteParametroServidor(parametroServidor, source, accion, accionId, descripcion);
			addActionMessageSession(this.getText("plataforma.servidorpush.parametro.delete.ok"));

		}
		return SUCCESS;
	}

	/**
	 * Delete planificacion servidor push.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String deletePlanificacionServidorPush() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String source = properties.getProperty(LOGDOTSOURCE_SE, null);
		String descripcion = properties.getProperty(LOGDOTACCION_DE, null);

		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return ServidoresPushAction.NO_USER;
		}
		if (planificacionId == null) {
			addActionErrorSession(this.getText("plataforma.servidorpush.planificacion.delete.error"));

		} else {
			planificacionServidor = new PlanificacionBean();
			planificacionServidor.setPlanificacionId(Integer.valueOf(planificacionId));
			servicioPlanificacion.deletePlanificacion(planificacionServidor, source, accion, accionId, descripcion);
			addActionMessageSession(this.getText("plataforma.servidorpush.planificacion.delete.ok"));
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
		String accionPlanificacion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String accionServidor = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionIdServidor = Long.parseLong(properties.getProperty(LOGDOTACCIONID_REF, null));
		String source = properties.getProperty(LOGDOTSOURCE_SE, null);
		String descripcionPlanificacion = properties.getProperty(LOGDOTACCION_DE, null);
		
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return ServidoresPushAction.NO_USER;
		}
		if (idServidorPush == null) {
			addActionErrorSession(this.getText("plataforma.servidorpush.delete.error"));
		} else {
			servidorPush = new ServidorPushBean();
			servidorPush.setServidorPushId(Long.valueOf(idServidorPush));
			servicioServidorPush.deleteServidorPush(servidorPush, accionServidor, accionIdServidor, source, accionPlanificacion, accionIdPlanificacion, descripcionPlanificacion);
			addActionMessageSession(this.getText("plataforma.servidorpush.delete.ok"));

		}
		return SUCCESS;

	}

	/**
	 * Delete selected.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String deleteSelected() throws BaseException {
		String accionPlanificacion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String accionServidor = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionIdServidor = Long.parseLong(properties.getProperty(LOGDOTACCIONID_REF, null));
		String source = properties.getProperty(LOGDOTSOURCE_SE, null);
		String descripcionPlanificacion = properties.getProperty(LOGDOTACCION_DE, null);
		
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return ServidoresPushAction.NO_USER;
		}
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.servidorpush.deleteSelected.error"));
		} else {
			for (String idServidorPush : checkDelList) {
				servidorPush = new ServidorPushBean();
				servidorPush.setServidorPushId(Long.valueOf(idServidorPush));
				servicioServidorPush.deleteServidorPush(servidorPush, accionServidor, accionIdServidor, source, accionPlanificacion, accionIdPlanificacion, descripcionPlanificacion);
			}
			addActionMessageSession(this.getText("plataforma.servidorpush.deleteSelected.ok"));

		}
		return SUCCESS;

	}

	/**
	 * Agrega parametro servidor push.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String addParametroServidorPush() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String source = properties.getProperty(LOGDOTSOURCE_SE, null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PARAMETRO", null);

		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return ServidoresPushAction.NO_USER;
		}
		if (parametroServidor != null) {
			if (!validaParametro(parametroServidor)) {
				return ERROR;
			} else {
				try {
					servicioParametroServidor.newParametroServidor(parametroServidor, source, accion, accionId,
							descripcion);
					addActionMessageSession(this.getText("plataforma.servidorpush.parametro.add.ok"));
				} catch (ConstraintViolationException e) {
					logger.error(e.getMessage(), e);
					addActionErrorSession(this.getText("plataforma.servidorpush.parametro.add.constraint.error"));
				}
			}
		} else {
			addActionErrorSession(this.getText("plataforma.servidorpush.parametro.add.error"));
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * Agrega planificacion servidor push.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String addPlanificacionServidorPush() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String source = properties.getProperty(LOGDOTSOURCE_SE, null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PLANIFICACION", null);

		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return ServidoresPushAction.NO_USER;
		}
		if (planificacionServidor != null && PlataformaMensajeriaUtil.isEmpty(idServidorPush)) {
			if (planificacionValida(planificacionServidor)) {
				planificacionServidor.setActivo(true);
				planificacionServidor.setTipoPlanificacionId(Integer.valueOf(properties.getProperty(
						"generales.PARAMETRO_ID_TIPO_PLANIFICACION", null)));

				int valido = servicioPlanificacion.validaPlanificacionServidor(planificacionId,
						planificacionServidor.getServidorId(), planificacionServidor.getLunes(),
						planificacionServidor.getMartes(), planificacionServidor.getMiercoles(),
						planificacionServidor.getJueves(), planificacionServidor.getViernes(),
						planificacionServidor.getSabado(), planificacionServidor.getDomingo(),
						planificacionServidor.getHoraHasta(), planificacionServidor.getHoraDesde());

				switch (valido) {
				case 1:
					servicioPlanificacion
							.newPlanificacion(planificacionServidor, source, accion, accionId, descripcion);
					addActionMessageSession(this.getText("plataforma.servidorpush.planificacion.add.ok"));
					break;
				case 0:
					addActionErrorSession("No se ha a&ntilde;adido la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
					return ERROR;
				}
			} else {
				return ERROR;
			}
		} else {
			addActionErrorSession(this.getText("plataforma.servidorpush.planificacion.add.error"));
		}
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	// ///MIGRADO
	@Override
	public void prepare() throws Exception {
		comboPlataformas = getComboPlataformaValues();
		if (idServidorPush != null) {
			tiposParametros = (ArrayList<TipoParametroBean>) servicioTipoParametro.getTipoParametrosServidorPush();
			comboTipoParametros = getComboValues();
			listaParametrosServidor = getParametrosServidorPush();
			listaPlanificacionesServidor = getLoadPlanificacionesServidorPush();
		}
	}

	/**
	 * Valida parametro.
	 *
	 * @param parametroServidor the parametro servidor
	 * @return true, if successful
	 */
	// //MIGRADO
	private boolean validaParametro(ParametroServidorBean parametroServidor) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmptyNumber(parametroServidor.getTipoparametroid().intValue())) {
			addActionErrorSession(this.getText("plataforma.servidores.parametro.add.parametroid.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(parametroServidor.getValor())) {
			addActionErrorSession(this.getText("plataforma.servidores.parametro.add.valor.error"));
			sw = false;
		}
		if (!PlataformaMensajeriaUtil.isEmpty(parametroServidor.getValor())) {
			try {
				Integer.parseInt(parametroServidor.getValor());
			} catch (NumberFormatException e) {
				logger.error(e.getMessage(), e);
				addActionErrorSession(this.getText("plataforma.servidores.parametro.add.valor.error.integer"));
				sw = false;
			}
		}
		if (parametroServidor.getTipoparametroid() == Long.parseLong(properties.getProperty(
				"generales.PARAMETRO_SERVIDOR_TIPO_TELEFONO", null))
				&& !PlataformaMensajeriaUtil.isMobileNumber(parametroServidor.getValor())) {
			addActionErrorSession(this.getText("plataforma.servidorpush.parametro.telefonomovil.error"));
			sw = false;
		}
		try {
			if (servicioParametroServidor.existeParametroServidor(parametroServidor)) {
				addActionErrorSession(this.getText("plataforma.servidorpush.parametro.add.existe"));
				sw = false;
			}

		} catch (BusinessException e) {
			logger.error("ServidoresPushAction - validaParametro:" + e);
		}
		return sw;
	}

	/**
	 * Obtener load planificaciones servidor push.
	 *
	 * @return load planificaciones servidor push
	 */
	// //MIGRADO
	private List<PlanificacionBean> getLoadPlanificacionesServidorPush() {
		List<PlanificacionBean> lista = null;
		if (idServidorPush != null && !idServidorPush.isEmpty()) {
			try {
				lista = servicioPlanificacion.getPlanificacionesByServidorId(Integer.valueOf(idServidorPush));
			} catch (NumberFormatException | BusinessException e) {
				logger.error(SERVIDORESPUSHA + e);
			}
		} else if (servidorPush != null && servidorPush.getServidorPushId() != null) {
			try {
				lista = servicioPlanificacion.getPlanificacionesByServidorId(Integer.valueOf(idServidorPush));
			} catch (NumberFormatException | BusinessException e) {
				logger.error(SERVIDORESPUSHA + e);
			} 
		}
		return lista;
	}

	/**
	 * Obtener parametros servidor push.
	 *
	 * @return parametros servidor push
	 */
	// //MIGRADO
	private List<ParametroServidorBean> getParametrosServidorPush() {
		List<ParametroServidorBean> lista = null;
		if (idServidorPush != null && !idServidorPush.isEmpty()) {
			try {
				lista = servicioParametroServidor.getParametroServidorByServidorPushId(Integer.valueOf(idServidorPush));
			} catch (NumberFormatException | BusinessException e) {
				logger.error(SERVIDORESPUSHA0 + e);
			}
		} else if (servidorPush != null && servidorPush.getServidorPushId() != null) {
			try {
				lista = servicioParametroServidor.getParametroServidorByServidorPushId(servidorPush.getServidorPushId()
						.intValue());
			} catch (NumberFormatException | BusinessException e) {
				logger.error(SERVIDORESPUSHA0 + e);
			}
		}
		return lista;
	}

	/**
	 * Obtener combo values.
	 *
	 * @return combo values
	 */
	// //MIGRADO
	private List<KeyValueObject> getComboValues() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option = null;

		ArrayList<TipoParametroBean> keys = null;
		try {
			keys = (ArrayList<TipoParametroBean>) servicioTipoParametro.getTipoParametrosServidorPush();

		} catch (BusinessException e) {
			logger.error("ServidoresPushAction - getComboValues:" + e);
		}

		if (keys != null && !keys.isEmpty()) {
			for (TipoParametroBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getTipoparametroid().toString());
				option.setDescripcion(StringUtil.capitalize(key.getNombre()));

				result.add(option);
			}
		}
		return result;
	}

	/**
	 * Obtener combo plataforma values.
	 *
	 * @return combo plataforma values
	 */
	// //MIGRADO
	private List<KeyValueObject> getComboPlataformaValues() {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option = null;
		ArrayList<PlataformaBean> keys = null;

		try {
			keys = (ArrayList<PlataformaBean>) servicioPlataforma.getPlataformas();
		} catch (BusinessException e) {
			logger.error("ServidoresPushAction - getComboPlataformaValues:" + e);
		}
		if (keys != null && !keys.isEmpty()) {
			for (PlataformaBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getPlataformaid().toString());
				option.setDescripcion(StringUtil.capitalize(key.getNombre()));
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
	////MIGRADO
	private boolean planificacionValida(PlanificacionBean planificacionServidor) {
		boolean sw = true;
		if (planificacionServidor != null) {
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
				addFieldErrorSession(this.getText("plataforma.servidorpush.planificacion.horas.error"));
				sw = false;

			}
			if (!PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
				addFieldErrorSession(this.getText("plataforma.servidorpush.planificacion.horaHasta.error"));
				sw = false;
			}
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())
					&& !PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
				addFieldErrorSession(this.getText("plataforma.servidorpush.planificacion.horaDesde.error"));
				sw = false;
			}
			if (sw) {
				if (!validoFormatoHora(planificacionServidor.getHoraDesde())) {
					addFieldErrorSession(this.getText("plataforma.servidorpush.planificacion.horaDesde.formato.error"));
					sw = false;
				}
				if (!validoFormatoHora(planificacionServidor.getHoraHasta())) {
					addFieldErrorSession(this.getText("plataforma.servidorpush.planificacion.horaHasta.formato.error"));
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
				addFieldErrorSession(this.getText("plataforma.servidorpush.planificacion.dias.error"));
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
		String[] horaDesdeArray = horaDesde.split(R_CONST_REF);
		String[] horaHastaArray = horaHasta.split(R_CONST_REF);
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
	 * Método que resuelve el lugar donde tiene que volver.
	 *
	 * @return volver
	 */
	///MIGRADO
	public String getVolver() {
		String volver = "buscarServidorPush.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
		}
		return volver;
	}

	/**
	 * Valida servidor.
	 *
	 * @param servidor the servidor
	 * @return true, if successful
	 */
	////MIGRADO
	private boolean validaServidor(ServidorPushBean servidor) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getNombre())) {
			addActionErrorSession(this.getText("plataforma.servidorpush.field.nombre.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getDescripcion())) {
			addActionErrorSession(this.getText("plataforma.servidorpush.field.descripcion.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getUrldestino())) {
			addActionErrorSession(this.getText("plataforma.servidorpush.field.urldestino.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmptyNumber(servidor.getPlataformaid())) {
			addActionErrorSession(this.getText("plataforma.servidorpush.field.plataforma.error"));
			sw = false;
		}
		if (!PlataformaMensajeriaUtil.isEmptyNumber(servidor.getPlataformaid())
				&& servidor.getPlataformaid()== Integer.parseInt(properties.getProperty("generales.PARAMETRO_ID_PLATAFORMA_IOS", null))
				&& PlataformaMensajeriaUtil.isEmpty(servidor.getUrlfeedback())) {
			addActionErrorSession(this.getText("plataforma.servidorpush.field.urlfeedback.error"));
			sw = false;
		}
		return sw;
	}

	/**
	 * Obtener combo tipo parametros.
	 *
	 * @return combo tipo parametros
	 */
	public List<KeyValueObject> getComboTipoParametros() {
		return comboTipoParametros;
	}

	/**
	 * Modificar combo tipo parametros.
	 *
	 * @param comboTipoParametros new combo tipo parametros
	 */
	public void setComboTipoParametros(List<KeyValueObject> comboTipoParametros) {
		this.comboTipoParametros = comboTipoParametros;
	}

	/**
	 * Obtener lista servidores push.
	 *
	 * @return lista servidores push
	 */
	public List<ServidorPushBean> getlistaServidoresPush() {
		return listaServidoresPush;
	}

	/**
	 * Modificar lista servidores push.
	 *
	 * @param listaServidoresPush new lista servidores push
	 */
	public void setlistaServidoresPush(List<ServidorPushBean> listaServidoresPush) {
		this.listaServidoresPush = listaServidoresPush;
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
	 * Obtener servicio tipo parametro.
	 *
	 * @return servicio tipo parametro
	 */
	public ServicioTipoParametro getServicioTipoParametro() {
		return servicioTipoParametro;
	}

	/**
	 * Modificar servicio tipo parametro.
	 *
	 * @param servicioTipoParametro new servicio tipo parametro
	 */
	public void setServicioTipoParametro(ServicioTipoParametro servicioTipoParametro) {
		this.servicioTipoParametro = servicioTipoParametro;
	}

	/**
	 * Obtener servicio parametro servidor.
	 *
	 * @return servicio parametro servidor
	 */
	public ServicioParametroServidor getServicioParametroServidor() {
		return servicioParametroServidor;
	}

	/**
	 * Modificar servicio parametro servidor.
	 *
	 * @param servicioParametroServidor new servicio parametro servidor
	 */
	public void setServicioParametroServidor(ServicioParametroServidor servicioParametroServidor) {
		this.servicioParametroServidor = servicioParametroServidor;
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
	 * Obtener servidor push.
	 *
	 * @return servidor push
	 */
	public ServidorPushBean getServidorPush() {
		return servidorPush;
	}

	/**
	 * Modificar servidor push.
	 *
	 * @param servidorPush new servidor push
	 */
	public void setServidorPush(ServidorPushBean servidorPush) {
		this.servidorPush = servidorPush;
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
	 * Obtener parametro servidor.
	 *
	 * @return parametro servidor
	 */
	public ParametroServidorBean getParametroServidor() {
		return parametroServidor;
	}

	/**
	 * Modificar parametro servidor.
	 *
	 * @param parametroServidor new parametro servidor
	 */
	public void setParametroServidor(ParametroServidorBean parametroServidor) {
		this.parametroServidor = parametroServidor;
	}

	/**
	 * Obtener lista parametros servidor.
	 *
	 * @return lista parametros servidor
	 */
	public List<ParametroServidorBean> getListaParametrosServidor() {
		return listaParametrosServidor;
	}

	/**
	 * Modificar lista parametros servidor.
	 *
	 * @param listaParametrosServidor new lista parametros servidor
	 */
	public void setListaParametrosServidor(List<ParametroServidorBean> listaParametrosServidor) {
		this.listaParametrosServidor = listaParametrosServidor;
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
	 * Obtener parametro servidor id.
	 *
	 * @return parametro servidor id
	 */
	public String getParametroServidorId() {
		if (parametroServidor != null && parametroServidor.getParametroservidorid() != null) {
			return parametroServidor.getParametroservidorid().toString();
		} else {
			return parametroServidorId;
		}

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
	 * Obtener combo plataformas.
	 *
	 * @return combo plataformas
	 */
	public List<KeyValueObject> getComboPlataformas() {
		return comboPlataformas;
	}

	/**
	 * Modificar combo plataformas.
	 *
	 * @param comboPlataformas new combo plataformas
	 */
	public void setComboPlataformas(List<KeyValueObject> comboPlataformas) {
		this.comboPlataformas = comboPlataformas;
	}

	/**
	 * Obtener servicio plataforma.
	 *
	 * @return servicio plataforma
	 */
	public ServicioPlataforma getServicioPlataforma() {
		return servicioPlataforma;
	}

	/**
	 * Modificar servicio plataforma.
	 *
	 * @param servicioPlataforma new servicio plataforma
	 */
	public void setServicioPlataforma(ServicioPlataforma servicioPlataforma) {
		this.servicioPlataforma = servicioPlataforma;
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
	 * Obtener lista planificaciones servidor.
	 *
	 * @return lista planificaciones servidor
	 */
	public List<PlanificacionBean> getListaPlanificacionesServidor() {
		return listaPlanificacionesServidor;
	}

	/**
	 * Modificar lista planificaciones servidor.
	 *
	 * @param listaPlanificacionesServidor new lista planificaciones servidor
	 */
	public void setListaPlanificacionesServidor(List<PlanificacionBean> listaPlanificacionesServidor) {
		this.listaPlanificacionesServidor = listaPlanificacionesServidor;
	}

	/**
	 * Obtener tipos parametros.
	 *
	 * @return tipos parametros
	 */
	public ArrayList<TipoParametroBean> getTiposParametros() {
		return tiposParametros;
	}

	/**
	 * Modificar tipos parametros.
	 *
	 * @param tiposParametros new tipos parametros
	 */
	public void setTiposParametros(ArrayList<TipoParametroBean> tiposParametros) {
		this.tiposParametros = tiposParametros;
	}
}
