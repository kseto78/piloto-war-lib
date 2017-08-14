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

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ServidoresPushAction.class);

	@Resource(name = "servicioServidorPushImpl")
	private ServicioServidorPush servicioServidorPush;

	@Resource(name = "servicioTipoParametroImpl")
	private ServicioTipoParametro servicioTipoParametro;

	@Resource(name = "servicioParametroServidorImpl")
	private ServicioParametroServidor servicioParametroServidor;

	@Resource(name = "servicioPlanificacionImpl")
	private ServicioPlanificacion servicioPlanificacion;

	@Resource(name = "servicioPlataformaImpl")
	private ServicioPlataforma servicioPlataforma;

	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;

	private ParametroServidorBean parametroServidor;
	private ServidorPushBean servidorPush;
	private PlanificacionBean planificacionServidor;

	List<KeyValueObject> comboTipoParametros = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboPlataformas = new ArrayList<KeyValueObject>();

	public List<ServidorPushBean> listaServidoresPush = null;
	private List<ParametroServidorBean> listaParametrosServidor = null;
	private List<PlanificacionBean> listaPlanificacionesServidor = null;
	ArrayList<TipoParametroBean> tiposParametros = new ArrayList<TipoParametroBean>();
	private String[] checkDelList;

	private String tipoParametroId;
	private String idServidorPush;
	private String planificacionId;
	private String parametroServidorId;
	private String resultCount;

	public String newSearch() throws BaseException {
		return SUCCESS;
	}

	// //MIGRADO
	public String search() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para
														// ordenar

		if (servidorPush != null)
			if (servidorPush.getNombre() != null && servidorPush.getNombre().length() <= 0)
				servidorPush.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServidorPushBean> result = servicioServidorPush.getServidoresPush(inicio,
				(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order,
				columnSort, servidorPush,
				Integer.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_PUSH", null)));
		Integer totalSize = result.getTotalList();

		listaServidoresPush = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
		} else {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
		}

		if (listaServidoresPush != null && !listaServidoresPush.isEmpty()) {
			for (int indice = 0; indice < listaServidoresPush.size(); indice++) {

				ServidorPushBean servidorPush = listaServidoresPush.get(indice);
				servidorPush.setNombre(StringEscapeUtils.escapeHtml(servidorPush.getNombre()));
				servidorPush.setDescripcion(StringEscapeUtils.escapeHtml(servidorPush.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	// //MIGRADO
	public String execute() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para
														// ordenar

		if (servidorPush != null)
			if (servidorPush.getNombre() != null && servidorPush.getNombre().length() <= 0)
				servidorPush.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServidorPushBean> result = servicioServidorPush.getServidoresPush(inicio,
				(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order,
				columnSort, servidorPush,
				Integer.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_PUSH", null)));
		Integer totalSize = result.getTotalList();

		listaServidoresPush = result.getPageList();

		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
		} else {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
		}

		if (listaServidoresPush != null && !listaServidoresPush.isEmpty()) {
			for (int indice = 0; indice < listaServidoresPush.size(); indice++) {

				ServidorPushBean receptor = listaServidoresPush.get(indice);
				receptor.setNombre(StringEscapeUtils.escapeHtml(receptor.getNombre()));
				receptor.setDescripcion(StringEscapeUtils.escapeHtml(receptor.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	///MIGRADO
	public String create() throws BaseException {
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES_PUSH", null);
		
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (servidorPush != null) {
			if (servidorPush.getIsActivo() != null && servidorPush.getIsActivo().indexOf("'activo'") != -1) {
				servidorPush.setActivo(true);
			} else {
				servidorPush.setActivo(false);
			}
			if (validaServidor(servidorPush)) {
				Long idServidorPush = servicioServidorPush.newServidorPush(servidorPush, Integer.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_PUSH",null)), 
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

	///MIGRADO
	public String update() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES_PUSH", null);
		
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		ServidorPushBean servidorPushBBDD = null;
		if (servidorPush == null) {
			// throw new BusinessException("EL servidorPush recibido es nulo");
			addActionErrorSession(this.getText("plataforma.servidorpush.update.error"));

		} else {
			
			if (servidorPush.getServidorPushId() == null) {
				if (idServidorPush != null) {
					servidorPush.setServidorPushId(new Long(idServidorPush));
					servidorPushBBDD = servicioServidorPush.loadServidorPush(servidorPush);
				} else {
					String idProvedorSMS = (String) request.getAttribute("idServidorPush");
					
					if (idProvedorSMS != null) {
						servidorPush.setId(new Long(idProvedorSMS));
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

	// //MIGRADO
	public String load() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idServidorPush == null)
			throw new BusinessException("EL idServidorPush recibido es nulo");
		try {
			servidorPush = new ServidorPushBean();
			servidorPush.setServidorPushId(new Long(idServidorPush));
			servidorPush = servicioServidorPush.loadServidorPush(servidorPush);
			return SUCCESS;
		} catch (NumberFormatException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { servidorPush
					.getServidorPushId().toString() });
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { servidorPush
					.getServidorPushId().toString() });
			throw new BusinessException(mensg);
		}

	}

	////MIGRADO
	public String deleteParametroServidorPush() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES_PUSH", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PARAMETRO", null);

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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

	////MIGRADO
	public String deletePlanificacionServidorPush() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES_PUSH", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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

	/////MIGRADO
	public String delete() throws BaseException {
		String accionPlanificacion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String accionServidor = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdServidor = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES_PUSH", null);
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idServidorPush == null) {
			addActionErrorSession(this.getText("plataforma.servidorpush.delete.error"));
		} else {
			servidorPush = new ServidorPushBean();
			servidorPush.setServidorPushId(new Long(idServidorPush));
			servicioServidorPush.deleteServidorPush(servidorPush, accionServidor, accionIdServidor, source, accionPlanificacion, accionIdPlanificacion, descripcionPlanificacion);
			addActionMessageSession(this.getText("plataforma.servidorpush.delete.ok"));

		}
		return SUCCESS;

	}

	////MIGRADO
	public String deleteSelected() throws BaseException {
		String accionPlanificacion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String accionServidor = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdServidor = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES_PUSH", null);
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.servidorpush.deleteSelected.error"));
		} else {
			for (String idServidorPush : checkDelList) {
				servidorPush = new ServidorPushBean();
				servidorPush.setServidorPushId(new Long(idServidorPush));
				servicioServidorPush.deleteServidorPush(servidorPush, accionServidor, accionIdServidor, source, accionPlanificacion, accionIdPlanificacion, descripcionPlanificacion);
			}
			addActionMessageSession(this.getText("plataforma.servidorpush.deleteSelected.ok"));

		}
		return SUCCESS;

	}

	////MIGRADO
	public String addParametroServidorPush() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES_PUSH", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PARAMETRO", null);

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (parametroServidor != null) {
			if (!validaParametro(parametroServidor)) {
				return ERROR;
			} else {
				try {
					servicioParametroServidor.newParametroServidor(parametroServidor, source, accion, accionId,
							descripcion);
					addActionMessageSession(this.getText("plataforma.servidorpush.parametro.add.ok"));
				} catch (ConstraintViolationException e) {
					addActionErrorSession(this.getText("plataforma.servidorpush.parametro.add.constraint.error"));
				}
			}
		} else {
			addActionErrorSession(this.getText("plataforma.servidorpush.parametro.add.error"));
			return ERROR;
		}
		return SUCCESS;
	}

	////MIGRADO
	public String addPlanificacionServidorPush() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES_PUSH", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PLANIFICACION", null);

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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

	// //MIGRADO
	private List<PlanificacionBean> getLoadPlanificacionesServidorPush() {
		List<PlanificacionBean> lista = null;
		if (idServidorPush != null && idServidorPush.length() > 0) {
			try {
				lista = servicioPlanificacion.getPlanificacionesByServidorId(Integer.valueOf(idServidorPush));
			} catch (NumberFormatException e) {
				logger.error("ServidoresPushAction - getLoadPlanificacionesServidorPush:" + e);
			} catch (BusinessException e) {
				logger.error("ServidoresPushAction - getLoadPlanificacionesServidorPush:" + e);
			}
		} else if (servidorPush != null && servidorPush.getServidorPushId() != null) {
			try {
				lista = servicioPlanificacion.getPlanificacionesByServidorId(Integer.valueOf(idServidorPush));
			} catch (NumberFormatException | BusinessException e) {
				logger.error("ServidoresPushAction - getLoadPlanificacionesServidorPush:" + e);
			} 
		}
		return lista;
	}

	// //MIGRADO
	private List<ParametroServidorBean> getParametrosServidorPush() {
		List<ParametroServidorBean> lista = null;
		if (idServidorPush != null && idServidorPush.length() > 0) {
			try {
				lista = servicioParametroServidor.getParametroServidorByServidorPushId(Integer.valueOf(idServidorPush));
			} catch (NumberFormatException e) {
				logger.error("ServidoresPushAction - getParametrosServidorPush:" + e);
			} catch (BusinessException e) {
				logger.error("ServidoresPushAction - getParametrosServidorPush:" + e);
			}
		} else if (servidorPush != null && servidorPush.getServidorPushId() != null) {
			try {
				lista = servicioParametroServidor.getParametroServidorByServidorPushId(servidorPush.getServidorPushId()
						.intValue());
			} catch (NumberFormatException | BusinessException e) {
				logger.error("ServidoresPushAction - getParametrosServidorPush:" + e);
			}
		}
		return lista;
	}

	// //MIGRADO
	private List<KeyValueObject> getComboValues() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;

		ArrayList<TipoParametroBean> keys = null;
		try {
			keys = (ArrayList<TipoParametroBean>) servicioTipoParametro.getTipoParametrosServidorPush();

		} catch (BusinessException e) {
			logger.error("ServidoresPushAction - getComboValues:" + e);
		}

		if (keys != null && keys.size() > 0)
			for (TipoParametroBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getTipoparametroid().toString());
				option.setDescripcion(StringUtil.capitalize(key.getNombre()));

				result.add(option);
			}
		return result;
	}

	// //MIGRADO
	private List<KeyValueObject> getComboPlataformaValues() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option = null;
		ArrayList<PlataformaBean> keys = null;

		try {
			keys = (ArrayList<PlataformaBean>) servicioPlataforma.getPlataformas();
		} catch (BusinessException e) {
			logger.error("ServidoresPushAction - getComboPlataformaValues:" + e);
		}
		if (keys != null && keys.size() > 0) {
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
	 * horas de inicio y fin
	 * 
	 * @param planificacionServidor
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

	////MIGRADO
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

	////MIGRADO
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
	 * Método que resuelve el lugar donde tiene que volver
	 */
	///MIGRADO
	public String getVolver() {
		String volver = "buscarServidorPush.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
		}
		return volver;
	}

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

	public List<KeyValueObject> getComboTipoParametros() {
		return comboTipoParametros;
	}

	public void setComboTipoParametros(List<KeyValueObject> comboTipoParametros) {
		this.comboTipoParametros = comboTipoParametros;
	}

	public List<ServidorPushBean> getlistaServidoresPush() {
		return listaServidoresPush;
	}

	public void setlistaServidoresPush(List<ServidorPushBean> listaServidoresPush) {
		this.listaServidoresPush = listaServidoresPush;
	}

	public ServicioServidorPush getServicioServidorPush() {
		return servicioServidorPush;
	}

	public void setServicioServidorPush(ServicioServidorPush servicioServidorPush) {
		this.servicioServidorPush = servicioServidorPush;
	}

	public ServicioTipoParametro getServicioTipoParametro() {
		return servicioTipoParametro;
	}

	public void setServicioTipoParametro(ServicioTipoParametro servicioTipoParametro) {
		this.servicioTipoParametro = servicioTipoParametro;
	}

	public ServicioParametroServidor getServicioParametroServidor() {
		return servicioParametroServidor;
	}

	public void setServicioParametroServidor(ServicioParametroServidor servicioParametroServidor) {
		this.servicioParametroServidor = servicioParametroServidor;
	}

	public String getTipoParametroId() {
		return tipoParametroId;
	}

	public void setTipoParametroId(String tipoParametroId) {
		this.tipoParametroId = tipoParametroId;
	}

	public ServidorPushBean getServidorPush() {
		return servidorPush;
	}

	public void setServidorPush(ServidorPushBean servidorPush) {
		this.servidorPush = servidorPush;
	}

	public String getIdServidorPush() {
		return idServidorPush;
	}

	public void setIdServidorPush(String idServidorPush) {
		this.idServidorPush = idServidorPush;
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

	public ParametroServidorBean getParametroServidor() {
		return parametroServidor;
	}

	public void setParametroServidor(ParametroServidorBean parametroServidor) {
		this.parametroServidor = parametroServidor;
	}

	public List<ParametroServidorBean> getListaParametrosServidor() {
		return listaParametrosServidor;
	}

	public void setListaParametrosServidor(List<ParametroServidorBean> listaParametrosServidor) {
		this.listaParametrosServidor = listaParametrosServidor;
	}

	public void setParametroServidorId(String parametroServidorId) {
		this.parametroServidorId = parametroServidorId;
	}

	public PlanificacionBean getPlanificacionServidor() {
		return planificacionServidor;
	}

	public void setPlanificacionServidor(PlanificacionBean planificacionServidor) {
		this.planificacionServidor = planificacionServidor;
	}

	public String getParametroServidorId() {
		if (parametroServidor != null && parametroServidor.getParametroservidorid() != null) {
			return parametroServidor.getParametroservidorid().toString();
		} else {
			return parametroServidorId;
		}

	}

	public ServicioPlanificacion getServicioPlanificacion() {
		return servicioPlanificacion;
	}

	public void setServicioPlanificacion(ServicioPlanificacion servicioPlanificacion) {
		this.servicioPlanificacion = servicioPlanificacion;
	}

	public List<KeyValueObject> getComboPlataformas() {
		return comboPlataformas;
	}

	public void setComboPlataformas(List<KeyValueObject> comboPlataformas) {
		this.comboPlataformas = comboPlataformas;
	}

	public ServicioPlataforma getServicioPlataforma() {
		return servicioPlataforma;
	}

	public void setServicioPlataforma(ServicioPlataforma servicioPlataforma) {
		this.servicioPlataforma = servicioPlataforma;
	}

	public String getPlanificacionId() {
		return planificacionId;
	}

	public void setPlanificacionId(String planificacionId) {
		this.planificacionId = planificacionId;
	}

	public List<PlanificacionBean> getListaPlanificacionesServidor() {
		return listaPlanificacionesServidor;
	}

	public void setListaPlanificacionesServidor(List<PlanificacionBean> listaPlanificacionesServidor) {
		this.listaPlanificacionesServidor = listaPlanificacionesServidor;
	}

	public ArrayList<TipoParametroBean> getTiposParametros() {
		return tiposParametros;
	}

	public void setTiposParametros(ArrayList<TipoParametroBean> tiposParametros) {
		this.tiposParametros = tiposParametros;
	}
}
