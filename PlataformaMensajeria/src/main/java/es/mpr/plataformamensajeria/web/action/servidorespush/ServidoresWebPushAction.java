package es.mpr.plataformamensajeria.web.action.servidorespush;

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
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.ServidorWebPushBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorWebPush;
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
@Controller("servidorWebPushAction")
@Scope("prototype")
public class ServidoresWebPushAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	/** Constante SERVIDORES_PUSH_ACTION_GET_LOAD_PLANIFICACIONES_SERVIDOR_PUSH. */
	private static final String SERVIDORES_PUSH_ACTION_GET_LOAD_PLANIFICACIONES_SERVIDOR_PUSH = "ServidoresPushAction - getLoadPlanificacionesServidorPush:";

	/** Constante LOG_ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION. */
	private static final String LOG_ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION = "log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION";

	/** Constante LOG_ACCION_ACTUALIZAR. */
	private static final String LOG_ACCION_ACTUALIZAR = "log.ACCION_ACTUALIZAR";

	/** Constante LOG_ACCIONID_ACTUALIZAR. */
	private static final String LOG_ACCIONID_ACTUALIZAR = "log.ACCIONID_ACTUALIZAR";

	/** Constante LOG_SOURCE_SERVIDORES_PUSH. */
	private static final String LOG_SOURCE_SERVIDORES_PUSH = "log.SOURCE_SERVIDORES_WEBPUSH";

	/** Constante TABLE_ID. */
	private static final String TABLE_ID = "tableId";

	/** Constante INFO_USER. */
	private static final String INFO_USER = "infoUser";

	/** Constante GENERALES_REQUEST_ATTRIBUTE_PAGESIZE. */
	private static final String GENERALES_REQUEST_ATTRIBUTE_PAGESIZE = "generales.REQUEST_ATTRIBUTE_PAGESIZE";

	/** Constante GENERALES_TIPO_SERVIDOR_WEBPUSH. */
	private static final String GENERALES_TIPO_SERVIDOR_WEBPUSH = "generales.TIPO_SERVIDOR_WEBPUSH";

	/** Constante GENERALES_PAGESIZE. */
	private static final String GENERALES_PAGESIZE = "generales.PAGESIZE";

	/** Constante NO_USER. */
	private static final String NO_USER = "noUser";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(ServidoresWebPushAction.class);

	/**  servicio servidor web push. */
	@Resource(name = "servicioServidorWebPushImpl")
	private transient ServicioServidorWebPush servicioServidorWebPush;

	/**  servicio planificacion. */
	@Resource(name = "servicioPlanificacionImpl")
	private transient ServicioPlanificacion servicioPlanificacion;

	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private transient PlataformaMensajeriaProperties properties;

	/**  servidor web push. */
	private transient ServidorWebPushBean servidorWebPush;
	
	/**  planificacion servidor. */
	private PlanificacionBean planificacionServidor;

	/**  lista servidores push. */
	public transient List<ServidorWebPushBean> listaServidoresPush = null;
	
	/**  lista planificaciones servidor. */
	private List<PlanificacionBean> listaPlanificacionesServidor = null;
	
	/**  check del list. */
	private String[] checkDelList;

	/**  id servidor web push. */
	private String idServidorWebPush;
	
	/**  planificacion id. */
	private String planificacionId;
	
	/**  result count. */
	private String resultCount;

	/**
	 * New search.
	 *
	 * @return the string
	 */
	public String newSearch() {
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
		if (getRequest().getSession().getAttribute(INFO_USER) == null)
			return ServidoresWebPushAction.NO_USER;
		int page = getPage(TABLE_ID); // Pagina a mostrar
		String order = getOrder(TABLE_ID); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort(TABLE_ID); // Columna usada para
														// ordenar

		if (servidorWebPush != null)
			if (servidorWebPush.getNombre() != null && servidorWebPush.getNombre().length() <= 0)
				servidorWebPush.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(GENERALES_PAGESIZE, "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServidorWebPushBean> result = servicioServidorWebPush.getServidoresPush(inicio,
				(export) ? -1 : Integer.parseInt(properties.getProperty(GENERALES_PAGESIZE, "20")), order,
				columnSort, servidorWebPush,
				Integer.parseInt(properties.getProperty(GENERALES_TIPO_SERVIDOR_WEBPUSH, null)));
		Integer totalSize = result.getTotalList();

		listaServidoresPush = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty(GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null),
					Integer.parseInt(properties.getProperty(GENERALES_PAGESIZE, "20")));
		} else {
			getRequest().setAttribute(properties.getProperty(GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null), totalSize);
		}

		if (listaServidoresPush != null && !listaServidoresPush.isEmpty()) {
			for (int indice = 0; indice < listaServidoresPush.size(); indice++) {

				ServidorWebPushBean servidorPush = listaServidoresPush.get(indice);
				servidorPush.setNombre(StringEscapeUtils.escapeHtml(servidorPush.getNombre()));
				servidorPush.setDescripcion(StringEscapeUtils.escapeHtml(servidorPush.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws BaseException {
		if (getRequest().getSession().getAttribute(INFO_USER) == null)
			return ServidoresWebPushAction.NO_USER;
		int page = getPage(TABLE_ID); // Pagina a mostrar
		String order = getOrder(TABLE_ID); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort(TABLE_ID); // Columna usada para
														// ordenar

		if (servidorWebPush != null)
			if (servidorWebPush.getNombre() != null && servidorWebPush.getNombre().length() <= 0)
				servidorWebPush.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(GENERALES_PAGESIZE, "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServidorWebPushBean> result = servicioServidorWebPush.getServidoresPush(inicio,
				(export) ? -1 : Integer.parseInt(properties.getProperty(GENERALES_PAGESIZE, "20")), order,
				columnSort, servidorWebPush,
				Integer.parseInt(properties.getProperty(GENERALES_TIPO_SERVIDOR_WEBPUSH, null)));
		Integer totalSize = result.getTotalList();

		listaServidoresPush = result.getPageList();

		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty(GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null),
					Integer.parseInt(properties.getProperty(GENERALES_PAGESIZE, "20")));
		} else {
			getRequest().setAttribute(properties.getProperty(GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null), totalSize);
		}

		if (listaServidoresPush != null && !listaServidoresPush.isEmpty()) {
			for (int indice = 0; indice < listaServidoresPush.size(); indice++) {
				ServidorWebPushBean servidorPush = listaServidoresPush.get(indice);
				servidorPush.setNombre(StringEscapeUtils.escapeHtml(servidorPush.getNombre()));
				servidorPush.setDescripcion(StringEscapeUtils.escapeHtml(servidorPush.getDescripcion()));
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
	public String create() throws BaseException {
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty(LOG_SOURCE_SERVIDORES_PUSH, null);
		
		if (getRequest().getSession().getAttribute(INFO_USER) == null)
			return ServidoresWebPushAction.NO_USER;
		if (servidorWebPush != null) {
			if (servidorWebPush.getIsActivo() != null && servidorWebPush.getIsActivo().indexOf("'activo'") != -1) {
				servidorWebPush.setActivo(true);
			} else {
				servidorWebPush.setActivo(false);
			}
			if (validaServidor(servidorWebPush)) {
				Long idServidorPush = servicioServidorWebPush.newServidorWebPush(servidorWebPush, Integer.parseInt(properties.getProperty(GENERALES_TIPO_SERVIDOR_WEBPUSH,null)), 
						source, accion, accionId);
				this.idServidorWebPush = idServidorPush.toString();
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
	public String update() throws BaseException {
		String accion = properties.getProperty(LOG_ACCION_ACTUALIZAR, null);
		Long accionId = Long.parseLong(properties.getProperty(LOG_ACCIONID_ACTUALIZAR, null));
		String source = properties.getProperty(LOG_SOURCE_SERVIDORES_PUSH, null);
		
		if (getRequest().getSession().getAttribute(INFO_USER) == null)
			return ServidoresWebPushAction.NO_USER;
		ServidorWebPushBean servidorWebPushBBDD = null;
		if (servidorWebPush == null) {
			addActionErrorSession(this.getText("plataforma.servidorpush.update.error"));

		} else {
			
			if (servidorWebPush.getServidorWebPushId() == null) {
				if (idServidorWebPush != null) {
					servidorWebPush.setServidorWebPushId(new Long(idServidorWebPush));
					servidorWebPushBBDD = servicioServidorWebPush.loadServidorWebPush(servidorWebPush);
				} else {
					String idProvedorSMS = (String) request.getAttribute("idServidorPush");
					
					if (idProvedorSMS != null) {
						servidorWebPush.setServidorWebPushId(new Long(idProvedorSMS));
						servidorWebPushBBDD = servicioServidorWebPush.loadServidorWebPush(servidorWebPush);
					}
				}

			} else {
				servidorWebPushBBDD = servicioServidorWebPush.loadServidorWebPush(servidorWebPush);

			}
			if (servidorWebPushBBDD != null) {
				servidorWebPushBBDD.setNombre(servidorWebPush.getNombre());
				servidorWebPushBBDD.setDescripcion(servidorWebPush.getDescripcion());
				servidorWebPushBBDD.setActivo(servidorWebPush.getActivo());
				servidorWebPushBBDD.setPordefecto(servidorWebPush.getPordefecto());
			}
			if (servidorWebPushBBDD != null && validaServidor(servidorWebPushBBDD)) {
				servicioServidorWebPush.updateServidorWebPush(servidorWebPushBBDD, source, accion, accionId);
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
	public String load() throws BaseException {
		if (getRequest().getSession().getAttribute(INFO_USER) == null)
			return ServidoresWebPushAction.NO_USER;
		if (idServidorWebPush == null)
			throw new BusinessException("EL idServidorWebPush recibido es nulo");
		try {
			servidorWebPush = new ServidorWebPushBean();
			servidorWebPush.setServidorWebPushId(new Long(idServidorWebPush));
			servidorWebPush = servicioServidorWebPush.loadServidorWebPush(servidorWebPush);
			return SUCCESS;
		} catch (NumberFormatException | BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { servidorWebPush
					.getServidorWebPushId().toString() });
			throw new BusinessException(mensg);
		} 

	}

	/**
	 * Delete planificacion servidor web push.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String deletePlanificacionServidorWebPush() throws BaseException {
		String accion = properties.getProperty(LOG_ACCION_ACTUALIZAR, null);
		Long accionId = Long.parseLong(properties.getProperty(LOG_ACCIONID_ACTUALIZAR, null));
		String source = properties.getProperty(LOG_SOURCE_SERVIDORES_PUSH, null);
		String descripcion = properties.getProperty(LOG_ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION, null);

		if (getRequest().getSession().getAttribute(INFO_USER) == null)
			return ServidoresWebPushAction.NO_USER;
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
	public String delete() throws BaseException {
		String accionPlanificacion = properties.getProperty(LOG_ACCION_ACTUALIZAR, null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty(LOG_ACCIONID_ACTUALIZAR, null));
		String accionServidor = properties.getProperty(LOG_ACCION_ACTUALIZAR, null);
		Long accionIdServidor = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty(LOG_SOURCE_SERVIDORES_PUSH, null);
		String descripcionPlanificacion = properties.getProperty(LOG_ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION, null);
		
		if (getRequest().getSession().getAttribute(INFO_USER) == null)
			return ServidoresWebPushAction.NO_USER;
		if (idServidorWebPush == null) {
			addActionErrorSession(this.getText("plataforma.servidorpush.delete.error"));
		} else {
			servidorWebPush = new ServidorWebPushBean();
			servidorWebPush.setServidorWebPushId(new Long(idServidorWebPush));
			servicioServidorWebPush.deleteServidorWebPush(servidorWebPush, accionServidor, accionIdServidor, source, accionPlanificacion, accionIdPlanificacion, descripcionPlanificacion);
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
	public String deleteSelected() throws BaseException {
		String accionPlanificacion = properties.getProperty(LOG_ACCION_ACTUALIZAR, null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty(LOG_ACCIONID_ACTUALIZAR, null));
		String accionServidor = properties.getProperty(LOG_ACCION_ACTUALIZAR, null);
		Long accionIdServidor = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty(LOG_SOURCE_SERVIDORES_PUSH, null);
		String descripcionPlanificacion = properties.getProperty(LOG_ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION, null);
		
		if (getRequest().getSession().getAttribute(INFO_USER) == null)
			return ServidoresWebPushAction.NO_USER;
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.servidorpush.deleteSelected.error"));
		} else {
			for (String idServidorPush : checkDelList) {
				servidorWebPush = new ServidorWebPushBean();
				servidorWebPush.setServidorWebPushId(new Long(idServidorPush));
				servicioServidorWebPush.deleteServidorWebPush(servidorWebPush, accionServidor, accionIdServidor, source, accionPlanificacion, accionIdPlanificacion, descripcionPlanificacion);
			}
			addActionMessageSession(this.getText("plataforma.servidorpush.deleteSelected.ok"));

		}
		return SUCCESS;

	}


	/**
	 * Agrega planificacion servidor web push.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String addPlanificacionServidorWebPush() throws BaseException {
		String accion = properties.getProperty(LOG_ACCION_ACTUALIZAR, null);
		Long accionId = Long.parseLong(properties.getProperty(LOG_ACCIONID_ACTUALIZAR, null));
		String source = properties.getProperty(LOG_SOURCE_SERVIDORES_PUSH, null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PLANIFICACION", null);

		if (getRequest().getSession().getAttribute(INFO_USER) == null)
			return ServidoresWebPushAction.NO_USER;
		if (planificacionServidor != null && PlataformaMensajeriaUtil.isEmpty(idServidorWebPush)) {
			if (planificacionValida(planificacionServidor)) {
				planificacionServidor.setActivo(true);
				planificacionServidor.setTipoPlanificacionId(Integer.valueOf(properties.getProperty(
						"generales.PARAMETRO_ID_TIPO_PLANIFICACION_WP", null)));

				int valido = servicioPlanificacion.validaPlanificacionServidor(planificacionId,
						planificacionServidor.getServidorId(), planificacionServidor.getLunes(),
						planificacionServidor.getMartes(), planificacionServidor.getMiercoles(),
						planificacionServidor.getJueves(), planificacionServidor.getViernes(),
						planificacionServidor.getSabado(), planificacionServidor.getDomingo(),
						planificacionServidor.getHoraHasta(), planificacionServidor.getHoraDesde());

				if (valido == 1) {
					servicioPlanificacion
							.newPlanificacion(planificacionServidor, source, accion, accionId, descripcion);
					addActionMessageSession(this.getText("plataforma.servidorpush.planificacion.add.ok"));
				} else if (valido == 0) {
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
	@Override
	public void prepare() throws Exception {
		if (idServidorWebPush != null) {
			listaPlanificacionesServidor = getLoadPlanificacionesServidorPush();
		}
	}

	
	/**
	 * Obtener load planificaciones servidor push.
	 *
	 * @return load planificaciones servidor push
	 */
	private List<PlanificacionBean> getLoadPlanificacionesServidorPush() {
		List<PlanificacionBean> lista = null;
		if ((idServidorWebPush != null && idServidorWebPush.length() > 0) || (servidorWebPush != null && servidorWebPush.getServidorWebPushId() != null)) {
			try {
				lista = servicioPlanificacion.getPlanificacionesByServidorId(Integer.valueOf(idServidorWebPush));
			} catch (NumberFormatException | BusinessException e) {
				logger.error(SERVIDORES_PUSH_ACTION_GET_LOAD_PLANIFICACIONES_SERVIDOR_PUSH + e);
			}
		} 
		return lista;
	}

	

	/**
	 * Verifica que se ha introducido por lo menos un día de la semana y las
	 * horas de inicio y fin.
	 *
	 * @param planificacionServidor the planificacion servidor
	 * @return true, if successful
	 */
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
	private boolean validoHoras(String horaDesde, String horaHasta) {
		boolean sw = true;
		String[] horaDesdeArray = horaDesde.split(":");
		String[] horaHastaArray = horaHasta.split(":");
		int hDesde = Integer.parseInt(horaDesdeArray[0]);
		int mDesde = Integer.parseInt(horaDesdeArray[1]);
		int hHasta = Integer.parseInt(horaHastaArray[0]);
		int mHasta = Integer.parseInt(horaHastaArray[1]);
		if ((hDesde > hHasta) || (hDesde == hHasta && mDesde > mHasta)) {
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
	 * Método que resuelve el lugar donde tiene que volver.
	 *
	 * @return volver
	 */
	public String getVolver() {
		String volver = "buscarServidorWebPush.action";
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
	private boolean validaServidor(ServidorWebPushBean servidor) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getNombre())) {
			addActionErrorSession(this.getText("plataforma.servidorpush.field.nombre.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getDescripcion())) {
			addActionErrorSession(this.getText("plataforma.servidorpush.field.descripcion.error"));
			sw = false;
		}
	
		return sw;
	}

	/**
	 * Obtener lista servidores push.
	 *
	 * @return lista servidores push
	 */
	public List<ServidorWebPushBean> getlistaServidoresPush() {
		return listaServidoresPush;
	}

	/**
	 * Modificar lista servidores push.
	 *
	 * @param listaServidoresPush new lista servidores push
	 */
	public void setlistaServidoresPush(List<ServidorWebPushBean> listaServidoresPush) {
		this.listaServidoresPush = listaServidoresPush;
	}

	/**
	 * Obtener servicio servidor push.
	 *
	 * @return servicio servidor push
	 */
	public ServicioServidorWebPush getServicioServidorPush() {
		return servicioServidorWebPush;
	}

	/**
	 * Modificar servicio servidor web push.
	 *
	 * @param servicioServidorPush new servicio servidor web push
	 */
	public void setServicioServidorWebPush(ServicioServidorWebPush servicioServidorPush) {
		this.servicioServidorWebPush = servicioServidorPush;
	}

	

	/**
	 * Obtener servidor web push.
	 *
	 * @return servidor web push
	 */
	public ServidorWebPushBean getServidorWebPush() {
		return servidorWebPush;
	}

	/**
	 * Modificar servidor web push.
	 *
	 * @param servidorPush new servidor web push
	 */
	public void setServidorWebPush(ServidorWebPushBean servidorPush) {
		this.servidorWebPush = servidorPush;
	}

	/**
	 * Obtener id servidor web push.
	 *
	 * @return id servidor web push
	 */
	public String getIdServidorWebPush() {
		return idServidorWebPush;
	}

	/**
	 * Modificar id servidor web push.
	 *
	 * @param idServidorPush new id servidor web push
	 */
	public void setIdServidorWebPush(String idServidorPush) {
		this.idServidorWebPush = idServidorPush;
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

}
