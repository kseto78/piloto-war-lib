package es.mpr.plataformamensajeria.web.action.receptores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.ws.security.util.Base64;
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
import es.mpr.plataformamensajeria.beans.ReceptorSMSBean;
import es.mpr.plataformamensajeria.beans.TipoParametroBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioReceptorSMS;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoParametro;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Clase Action de Struts2 para la gestion de los receptores sms.
 * 
 * <p>
 * Proporciona metodos para la creacion, modificacion, borrado y listado de los
 * receptores sms.
 * 
 * @author jgonzvil
 * 
 */
@Controller("receptorSMSAction")
@Scope("prototype")
public class ReceptoresSMSAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	protected static final String INFOUSER = "infoUser";

	protected static final String LOGDOTACCION_DE = "log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION";

	protected static final String LOGDOTSOURCE_PR = "log.SOURCE_PROVEEDORES";

	protected static final String PLATAFORMADOTSE = "plataforma.servidores.planificacion.horaDesde.menor.error";

	protected static final String LOGDOTACCION_AC = "log.ACCION_ACTUALIZAR";

	protected static final String GENERALESDOTREQ = "generales.REQUEST_ATTRIBUTE_TOTALSIZE";

	protected static final String RECEPTORESSMSAC = "ReceptoresSMSAction - getParametrosReceptorSMS:";

	protected static final String GENERALESDOTPAG = "generales.PAGESIZE";

	protected static final String TRUE = "true";

	protected static final String R_CONST_REF = ":";

	protected static final String R_CONST_0 = "20";

	protected static final String LOGDOTACCIONID_REF = "log.ACCIONID_ELIMINAR";

	protected static final String NOUSER = "noUser";

	protected static final String GENERALESDOTTIP = "generales.TIPO_SERVIDOR_RECEPCION";

	protected static final String LOGDOTSOURCE_RE = "log.SOURCE_RECEPTORES";

	protected static final String ERRORSDOTACTION = "errors.action.organismo.loadOrganismo";

	protected static final String LOGDOTACCION_EL = "log.ACCION_ELIMINAR";

	protected static final String TABLEID = "tableId";

	protected static final String GENERALESDOTREQ0 = "generales.REQUEST_ATTRIBUTE_PAGESIZE";

	protected static final String LOGDOTACCIONID_0 = "log.ACCIONID_ACTUALIZAR";

	protected static final String RECEPTORESSMSAC0 = "ReceptoresSMSAction - getLoadPlanificacionesReceptorSMS:";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(ReceptoresSMSAction.class);

	/**  servicio receptor SMS. */
	@Resource(name = "servicioReceptorSMSImpl")
	private ServicioReceptorSMS servicioReceptorSMS;

	/**  servicio tipo parametro. */
	@Resource(name = "servicioTipoParametroImpl")
	private ServicioTipoParametro servicioTipoParametro;

	/**  servicio parametro servidor. */
	@Resource(name = "servicioParametroServidorImpl")
	private ServicioParametroServidor servicioParametroServidor;

	/**  servicio planificacion. */
	@Resource(name = "servicioPlanificacionImpl")
	private ServicioPlanificacion servicioPlanificacion;

	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;

	/**  parametro servidor. */
	private ParametroServidorBean parametroServidor;
	
	/**  receptor SMS. */
	private ReceptorSMSBean receptorSMS;
	
	/**  planificacion servidor. */
	private PlanificacionBean planificacionServidor;

	/**  combo tipo parametros. */
	List<KeyValueObject> comboTipoParametros = new ArrayList<>();

	/**  lista receptores SMS. */
	public List<ReceptorSMSBean> listaReceptoresSMS = null;
	
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
	
	/**  id receptor SMS. */
	private String idReceptorSMS;
	
	/**  parametro servidor id. */
	private String parametroServidorId;
	
	/**  planificacion id. */
	private String planificacionId;
	
	/**  result count. */
	private String resultCount;

	/**  check password. */
	private String checkPassword;

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

		if (receptorSMS != null && receptorSMS.getNombre() != null && receptorSMS.getNombre().isEmpty()) {
			receptorSMS.setNombre(null);
		}

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_0));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ReceptorSMSBean> result = servicioReceptorSMS.getReceptoresSMS(inicio,
				export ? -1 : Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_0)), order,
				columnSort, receptorSMS,
				Integer.parseInt(properties.getProperty(GENERALESDOTTIP, null)));
		Integer totalSize = result.getTotalList();

		listaReceptoresSMS = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ, null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ0, null),
					Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_0)));
		} else {
			getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ0, null), totalSize);
		}

		if (listaReceptoresSMS != null && !listaReceptoresSMS.isEmpty()) {
			for (int indice = 0, s = listaReceptoresSMS.size(); indice < s; indice++) {

				ReceptorSMSBean receptorSMS = listaReceptoresSMS.get(indice);
				receptorSMS.setNombre(StringEscapeUtils.escapeHtml(receptorSMS.getNombre()));
				receptorSMS.setDescripcion(StringEscapeUtils.escapeHtml(receptorSMS.getDescripcion()));
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

		if (receptorSMS != null && receptorSMS.getNombre() != null && receptorSMS.getNombre().isEmpty()) {
			receptorSMS.setNombre(null);
		}

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_0));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ReceptorSMSBean> result = servicioReceptorSMS.getReceptoresSMS(inicio,
				export ? -1 : Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_0)), order,
				columnSort, receptorSMS,
				Integer.parseInt(properties.getProperty(GENERALESDOTTIP, null)));
		Integer totalSize = result.getTotalList();

		listaReceptoresSMS = result.getPageList();

		getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ, null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ0, null),
					Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_0)));
		} else {
			getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ0, null), totalSize);
		}

		if (listaReceptoresSMS != null && !listaReceptoresSMS.isEmpty()) {
			for (int indice = 0, s = listaReceptoresSMS.size(); indice < s; indice++) {

				ReceptorSMSBean receptor = listaReceptoresSMS.get(indice);
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
	////MIGRADO
	public String create() throws BaseException {
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty(LOGDOTSOURCE_RE, null);
		
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (receptorSMS != null) {
			if (receptorSMS.getIsActivo() != null && receptorSMS.getIsActivo().indexOf("'activo'") != -1) {
				receptorSMS.setActivo(true);
			} else {
				receptorSMS.setActivo(false);
			}
			if (validaServidor(receptorSMS) && validPassword(receptorSMS)) {
				if (null != receptorSMS.getPassword()) {
					receptorSMS.setPassword(Base64.encode(receptorSMS.getPassword().trim().getBytes())); 
					// Eliminamos
																											// los
																											// espacios
				}
				Long idReceptorSMS = servicioReceptorSMS.newReceptorSMS(receptorSMS,
						Integer.parseInt(properties.getProperty(GENERALESDOTTIP, null)), source, accion, accionId);
				this.idReceptorSMS = idReceptorSMS.toString();
				addActionMessageSession(this.getText("plataforma.receptorsms.create.ok"));
			} else {
				return ERROR;
			}
		} else {
			addActionErrorSession(this.getText("plataforma.receptorsms.create.error"));
		}
		return SUCCESS;

	}

	/**
	 * Update.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	/////MIGRADO
	public String update() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String source = properties.getProperty(LOGDOTSOURCE_RE, null);
		
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		ReceptorSMSBean receptorSMSBBDD = null;
		if (receptorSMS == null) {
			addActionErrorSession(this.getText("plataforma.receptorsms.update.error"));

		} else {
			if (receptorSMS.getReceptorSMSId() == null) {
				if (idReceptorSMS != null) {
					receptorSMS.setReceptorSMSId(Long.valueOf(idReceptorSMS));
					receptorSMSBBDD = servicioReceptorSMS.loadReceptorSMS(receptorSMS);
				} else {
					String idProvedorSMS = (String) request.getAttribute("idReceptorSMS");
					if (idProvedorSMS != null) {
						receptorSMS.setId(Long.valueOf(idProvedorSMS));
						receptorSMSBBDD = servicioReceptorSMS.loadReceptorSMS(receptorSMS);
					}
				}

			} else {
				receptorSMSBBDD = servicioReceptorSMS.loadReceptorSMS(receptorSMS);

			}
			if (receptorSMSBBDD != null) {
				receptorSMSBBDD.setNombre(receptorSMS.getNombre());
				receptorSMSBBDD.setDescripcion(receptorSMS.getDescripcion());
				receptorSMSBBDD.setActivo(receptorSMS.getActivo());
				receptorSMSBBDD.setUsuario(receptorSMS.getUsuario());
				receptorSMSBBDD.setPassword(receptorSMS.getPassword());
				receptorSMSBBDD.setRePassword(receptorSMS.getRePassword());
				receptorSMSBBDD.setPordefecto(receptorSMS.getPordefecto());
			}
			if (receptorSMSBBDD != null && validaServidor(receptorSMSBBDD) && validPassword(receptorSMSBBDD)) {
				if (null != receptorSMS.getPassword() && !receptorSMS.getPassword().isEmpty()) {
					receptorSMSBBDD.setPassword(Base64.encode(receptorSMS.getPassword().trim().getBytes())); 
					// Eliminamos
																												// los
																												// espacios
				}
				servicioReceptorSMS.updateReceptorSMS(receptorSMSBBDD, source, accion, accionId);
				addActionMessageSession(this.getText("plataforma.receptorsms.update.ok"));
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
	// /MIGRADO
	public String load() throws BaseException {
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (idReceptorSMS == null) {
			throw new BusinessException("EL idReceptorSMS recibido es nulo");
		}
		try {
			receptorSMS = new ReceptorSMSBean();
			receptorSMS.setReceptorSMSId(Long.valueOf(idReceptorSMS));
			receptorSMS = servicioReceptorSMS.loadReceptorSMS(receptorSMS);
			if (null != receptorSMS.getPassword()) {
				try {
					receptorSMS.setPassword(new String(Base64.decode(receptorSMS.getPassword())));
				} catch (Exception e) {
					logger.error("ReceptoresSMSAction - load: Codificando password: " + e);
				}
			}
			return SUCCESS;
		} catch (NumberFormatException | BusinessException e) {
			logger.error(e.getMessage(), e);
			String mensg = this.getText(ERRORSDOTACTION, new String[] { receptorSMS
					.getReceptorSMSId().toString() });
			throw new BusinessException(mensg);
		}

	}

	/**
	 * Delete parametro receptor SMS.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	/////MIGRADO
	public String deleteParametroReceptorSMS() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_EL, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_REF, null));
		String source = properties.getProperty(LOGDOTSOURCE_RE, null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PARAMETRO", null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (parametroServidorId == null) {
			addActionErrorSession(this.getText("plataforma.receptorsms.parametro.delete.error"));
		} else {
			parametroServidor = new ParametroServidorBean();
			parametroServidor.setParametroservidorid(Long.valueOf(parametroServidorId));
			servicioParametroServidor.deleteParametroServidor(parametroServidor, source, accion, accionId, descripcion);
			addActionMessageSession(this.getText("plataforma.receptorsms.parametro.delete.ok"));

		}
		return SUCCESS;
	}

	/**
	 * Delete planificacion receptor SMS.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	//////MIGRADO
	public String deletePlanificacionReceptorSMS() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String source = properties.getProperty(LOGDOTSOURCE_RE, null);
		String descripcion = properties.getProperty(LOGDOTACCION_DE, null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (planificacionId == null) {
			addActionErrorSession(this.getText("plataforma.receptorsms.planificacion.delete.error"));

		} else {
			planificacionServidor = new PlanificacionBean();
			planificacionServidor.setPlanificacionId(Integer.valueOf(planificacionId));
			servicioPlanificacion.deletePlanificacion(planificacionServidor, source, accion, accionId, descripcion);
			addActionMessageSession(this.getText("plataforma.receptorsms.planificacion.delete.ok"));
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
		String accionPlanificacion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String accionServidor = properties.getProperty(LOGDOTACCION_EL, null);
		Long accionIdServidor = Long.parseLong(properties.getProperty(LOGDOTACCIONID_REF, null));
		String source = properties.getProperty(LOGDOTSOURCE_PR, null);
		String descripcionPlanificacion = properties.getProperty(LOGDOTACCION_DE, null);
		
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (idReceptorSMS == null) {
			addActionErrorSession(this.getText("plataforma.receptorsms.delete.error"));
		} else {
			receptorSMS = new ReceptorSMSBean();
			receptorSMS.setReceptorSMSId(Long.valueOf(idReceptorSMS));
			servicioReceptorSMS.deleteReceptorSMS(receptorSMS, accionServidor, accionIdServidor, source, accionPlanificacion, accionIdPlanificacion, descripcionPlanificacion);
			addActionMessageSession(this.getText("plataforma.receptorsms.delete.ok"));

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
		String accionPlanificacion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String accionServidor = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionIdServidor = Long.parseLong(properties.getProperty(LOGDOTACCIONID_REF, null));
		String source = properties.getProperty(LOGDOTSOURCE_PR, null);
		String descripcionPlanificacion = properties.getProperty(LOGDOTACCION_DE, null);
		
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.receptorsms.deleteSelected.error"));
		} else {
			for (String idReceptorSMS : checkDelList) {
				receptorSMS = new ReceptorSMSBean();
				receptorSMS.setReceptorSMSId(Long.valueOf(idReceptorSMS));
				servicioReceptorSMS.deleteReceptorSMS(receptorSMS,accionServidor, accionIdServidor, source, accionPlanificacion, accionIdPlanificacion, descripcionPlanificacion);
			}
			addActionMessageSession(this.getText("plataforma.receptorsms.deleteSelected.ok"));

		}
		return SUCCESS;

	}

/**
 * Agrega parametro receptor SMS.
 *
 * @return the string
 * @throws BaseException the base exception
 */
////MIGRADO
	public String addParametroReceptorSMS() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String source = properties.getProperty(LOGDOTSOURCE_RE, null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PARAMETRO", null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (parametroServidor != null) {
			if (!validaParametro(parametroServidor)) {
				return ERROR;
			} else {
				try {
					servicioParametroServidor.newParametroServidor(parametroServidor, source, accion, accionId, descripcion);
					addActionMessageSession(this.getText("plataforma.receptorsms.parametro.add.ok"));
				} catch (ConstraintViolationException e) {
					logger.error(e.getMessage(), e);
					addActionErrorSession(this.getText("plataforma.receptorsms.parametro.add.constraint.error"));
				}
			}
		} else {
			addActionErrorSession(this.getText("plataforma.receptorsms.parametro.add.error"));
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * Agrega planificacion receptor SMS.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String addPlanificacionReceptorSMS() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String source = properties.getProperty(LOGDOTSOURCE_RE, null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PLANIFICACION", null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (planificacionServidor != null && PlataformaMensajeriaUtil.isEmpty(idReceptorSMS)) {
			if (planificacionValida(planificacionServidor)) {
				planificacionServidor.setActivo(true);
				planificacionServidor.setTipoPlanificacionId(Integer.valueOf(3));

				int valido = servicioPlanificacion.validaPlanificacionServidor(planificacionId,
						planificacionServidor.getServidorId(), planificacionServidor.getLunes(),
						planificacionServidor.getMartes(), planificacionServidor.getMiercoles(),
						planificacionServidor.getJueves(), planificacionServidor.getViernes(),
						planificacionServidor.getSabado(), planificacionServidor.getDomingo(),
						planificacionServidor.getHoraHasta(), planificacionServidor.getHoraDesde());

				switch (valido) {
				case 1:
					servicioPlanificacion.newPlanificacion(planificacionServidor, source, accion, accionId, descripcion);
					addActionMessageSession(this.getText("plataforma.receptorsms.planificacion.add.ok"));
					break;
				case 0:
					addActionErrorSession("No se ha a&ntilde;adido la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
					return ERROR;
				}
			} else {
				return ERROR;
			}
		} else {
			addActionErrorSession(this.getText("plataforma.receptorsms.planificacion.add.error"));
		}
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	// ////MIGRADO
	@Override
	public void prepare() throws Exception {
		if (idReceptorSMS != null) {
			tiposParametros = (ArrayList<TipoParametroBean>) servicioTipoParametro.getTipoParametrosReceptorSMS();

			comboTipoParametros = getComboValues();
			listaParametrosServidor = getParametrosReceptorSMS();
			listaPlanificacionesServidor = getLoadPlanificacionesReceptorSMS();
		}
	}
	
	/**
	 * Valida parametro.
	 *
	 * @param parametroServidor the parametro servidor
	 * @return true, if successful
	 */
	////MIGRADO
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
		if (parametroServidor.getTipoparametroid() == Long.parseLong(properties.getProperty(
				"generales.PARAMETRO_SERVIDOR_TIPO_TELEFONO_RECEPCION", null))
				&& !PlataformaMensajeriaUtil.isMobileNumber(parametroServidor.getValor())) {
			addActionErrorSession(this.getText("plataforma.receptorsms.parametro.telefonomovil.error"));
			sw = false;
		}
		try {
			if (servicioParametroServidor.existeParametroServidor(parametroServidor)) {
				addActionErrorSession(this.getText("plataforma.receptorsms.parametro.add.existe"));
				sw = false;
			}

		} catch (BusinessException e) {
			logger.error("ReceptoresSMSAction - validaParametro:" + e);
		}
		return sw;
	}

	/**
	 * Obtener load planificaciones receptor SMS.
	 *
	 * @return load planificaciones receptor SMS
	 */
	// //MIGRADO
	private List<PlanificacionBean> getLoadPlanificacionesReceptorSMS() {
		List<PlanificacionBean> lista = null;
		if (idReceptorSMS != null && !idReceptorSMS.isEmpty()) {
			try {
				lista = servicioPlanificacion.getPlanificacionesByServidorId(Integer.valueOf(idReceptorSMS));
			} catch (NumberFormatException | BusinessException e) {
				logger.error(RECEPTORESSMSAC0 + e);
			}
		} else if (receptorSMS != null && receptorSMS.getReceptorSMSId() != null) {
			try {
				lista = servicioPlanificacion.getPlanificacionesByServidorId(Integer.valueOf(idReceptorSMS));
			} catch (NumberFormatException | BusinessException e) {
				logger.error(RECEPTORESSMSAC0 + e);
				
			}
		}
		return lista;
	}

	/**
	 * Obtener parametros receptor SMS.
	 *
	 * @return parametros receptor SMS
	 */
	// //MIGRADO
	private List<ParametroServidorBean> getParametrosReceptorSMS() {
		List<ParametroServidorBean> lista = null;
		if (idReceptorSMS != null && !idReceptorSMS.isEmpty()) {
			try {
				lista = servicioParametroServidor.getParametroServidorByReceptorSMSId(Integer.valueOf(idReceptorSMS));
			} catch (NumberFormatException | BusinessException e) {
				logger.error(RECEPTORESSMSAC + e);
			}
		} else if (receptorSMS != null && receptorSMS.getReceptorSMSId() != null) {
			try {
				lista = servicioParametroServidor.getParametroServidorByReceptorSMSId(receptorSMS.getReceptorSMSId()
						.intValue());
			} catch (NumberFormatException | BusinessException e) {
				logger.error(RECEPTORESSMSAC + e);
			}
		}
		return lista;
	}

	/**
	 * Obtener combo values.
	 *
	 * @return combo values
	 */
	// ///MIGRADO
	private List<KeyValueObject> getComboValues() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option = null;
		ArrayList<TipoParametroBean> keys = null;
		try {
			keys = (ArrayList<TipoParametroBean>) servicioTipoParametro.getTipoParametrosReceptorSMS();

		} catch (BusinessException e) {
			logger.error("ReceptoresSMSAction - getComboValues:" + e);
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
	 * Comprueba empty.
	 *
	 * @param value the value
	 * @return true, si es empty
	 */
	///MIGRADO
	public boolean isEmpty(String value) {
		return value == null || "".equals(value);
	}

	/**
	 * Valida servidor.
	 *
	 * @param servidor the servidor
	 * @return true, if successful
	 */
	// //MIGRADO
	private boolean validaServidor(ReceptorSMSBean servidor) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getNombre())) {
			addActionErrorSession(this.getText("plataforma.receptorsms.field.nombre.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getDescripcion())) {
			addActionErrorSession(this.getText("plataforma.receptorsms.field.descripcion.error"));
			sw = false;
		}
		return sw;
	}

	/**
	 * Valid password.
	 *
	 * @param servidor the servidor
	 * @return true, if successful
	 */
	// //MIGRADO
	private boolean validPassword(ReceptorSMSBean servidor) {
		boolean sw = true;

		if (PlataformaMensajeriaUtil.isEmpty(servidor.getUsuario())) {
			addFieldErrorSession(this.getText("plataforma.receptorsms.field.usuario.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getPassword())) {
			addFieldErrorSession(this.getText("plataforma.receptorsms.field.password.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getRePassword())
				&& !PlataformaMensajeriaUtil.isEmpty(checkPassword) && TRUE.equals(checkPassword)) {
			addFieldErrorSession(this.getText("plataforma.receptorsms.field.rePassword.error"));
			sw = false;
		}
		// Eliminamos los espacios
		if (!PlataformaMensajeriaUtil.isEmpty(servidor.getPassword())
				&& !PlataformaMensajeriaUtil.isEmpty(servidor.getRePassword())
				&& !servidor.getPassword().trim().equals(servidor.getRePassword().trim())
				&& !PlataformaMensajeriaUtil.isEmpty(checkPassword) && TRUE.equals(checkPassword)) {
			addFieldErrorSession(this.getText("plataforma.receptorsms.field.passwords.error"));
			sw = false;
		}

		return sw;
	}

	/**
	 * Verifica que se ha introducido por lo menos un día de la semana y las
	 * horas de inicio y fin.
	 *
	 * @param planificacionServidor the planificacion servidor
	 * @return true, if successful
	 */
	// //MIGRADO
	private boolean planificacionValida(PlanificacionBean planificacionServidor) {
		boolean sw = true;
		if (planificacionServidor != null) {
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
				addFieldErrorSession(this.getText("plataforma.receptorsms.planificacion.horas.error"));
				sw = false;

			}
			if (!PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
				addFieldErrorSession(this.getText("plataforma.receptorsms.planificacion.horaHasta.error"));
				sw = false;
			}
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())
					&& !PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
				addFieldErrorSession(this.getText("plataforma.receptorsms.planificacion.horaDesde.error"));
				sw = false;
			}
			if (sw) {
				if (!validoFormatoHora(planificacionServidor.getHoraDesde())) {
					addFieldErrorSession(this.getText("plataforma.receptorsms.planificacion.horaDesde.formato.error"));
					sw = false;
				}
				if (!validoFormatoHora(planificacionServidor.getHoraHasta())) {
					addFieldErrorSession(this.getText("plataforma.receptorsms.planificacion.horaHasta.formato.error"));
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
				addFieldErrorSession(this.getText("plataforma.receptorsms.planificacion.dias.error"));
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
	// //MIGRADO
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
	// //MIGRADO
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
	// //MIGRADO
	public String getVolver() {
		String volver = "buscarReceptorSMS.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
		}
		return volver;
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
	 * Obtener lista receptores SMS.
	 *
	 * @return lista receptores SMS
	 */
	public List<ReceptorSMSBean> getlistaReceptoresSMS() {
		return listaReceptoresSMS;
	}

	/**
	 * Modificar lista receptores SMS.
	 *
	 * @param listaReceptoresSMS new lista receptores SMS
	 */
	public void setlistaReceptoresSMS(List<ReceptorSMSBean> listaReceptoresSMS) {
		this.listaReceptoresSMS = listaReceptoresSMS;
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
	 * Obtener receptor SMS.
	 *
	 * @return receptor SMS
	 */
	public ReceptorSMSBean getReceptorSMS() {
		return receptorSMS;
	}

	/**
	 * Modificar receptor SMS.
	 *
	 * @param receptorSMS new receptor SMS
	 */
	public void setReceptorSMS(ReceptorSMSBean receptorSMS) {
		this.receptorSMS = receptorSMS;
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
