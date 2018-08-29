package es.mpr.plataformamensajeria.web.action.proveedores;

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
import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;
import es.mpr.plataformamensajeria.beans.TipoParametroBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoParametro;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los proveedores sms.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n,
 * borrado y listado de los proveedores sms.
 * 
 * @author Altran
 * 
 */
@Controller("proveedorSMSAction")
@Scope("prototype")
public class ProveedoresSMSAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	/** Constante GENERALES_REQUEST_ATTRIBUTE_PAGESIZE. */
	private static final String GENERALES_REQUEST_ATTRIBUTE_PAGESIZE = "generales.REQUEST_ATTRIBUTE_PAGESIZE";

	/** Constante GENERALES_TIPO_SERVIDOR_SMS. */
	private static final String GENERALES_TIPO_SERVIDOR_SMS = "generales.TIPO_SERVIDOR_SMS";

	/** Constante GENERALES_PAGESIZE. */
	private static final String GENERALES_PAGESIZE = "generales.PAGESIZE";

	/** Constante TABLE_ID. */
	private static final String TABLE_ID = "tableId";

	/** Constante NO_USER. */
	private static final String NO_USER = "noUser";

	/** Constante INFO_USER. */
	private static final String INFO_USER = "infoUser";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(ProveedoresSMSAction.class);

	/**  servicio proveedor SMS. */
	@Resource(name = "servicioProveedorSMSImpl")
	private transient ServicioProveedorSMS servicioProveedorSMS;

	/**  servicio tipo parametro. */
	@Resource(name = "servicioTipoParametroImpl")
	private transient ServicioTipoParametro servicioTipoParametro;

	/**  servicio parametro servidor. */
	@Resource(name = "servicioParametroServidorImpl")
	private transient ServicioParametroServidor servicioParametroServidor;

	/**  servicio planificacion. */
	@Resource(name = "servicioPlanificacionImpl")
	private transient ServicioPlanificacion servicioPlanificacion;

	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private transient PlataformaMensajeriaProperties properties;

	/**  parametro servidor. */
	private ParametroServidorBean parametroServidor;
	
	/**  proveedor SMS. */
	private ProveedorSMSBean proveedorSMS;
	
	/**  planificacion servidor. */
	private PlanificacionBean planificacionServidor;

	/**  combo tipo parametros. */
	List<KeyValueObject> comboTipoParametros = new ArrayList<>();
 
	/**  lista proveedores SMS. */
	private transient List<ProveedorSMSBean> listaProveedoresSMS = null;
	
	/**  lista parametros servidor. */
	private List<ParametroServidorBean> listaParametrosServidor = null;
	
	/**  lista planificaciones servidor. */
	private List<PlanificacionBean> listaPlanificacionesServidor = null;
	
	/**  tipos parametros. */
	ArrayList<TipoParametroBean> tiposParametros = new ArrayList<>();

	/**  check del list. */
	private String[] checkDelList;

	/**  planificacion id. */
	private String planificacionId;
	
	/**  parametro servidor id. */
	private String parametroServidorId;
	
	/**  tipo parametro id. */
	private String tipoParametroId;
	
	/**  id proveedor SMS. */
	private String idProveedorSMS;
	
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
	//////MIGRADO
	public String search() throws BaseException {
		if (getRequest().getSession().getAttribute(ProveedoresSMSAction.INFO_USER) == null)
			return ProveedoresSMSAction.NO_USER;

		int page = getPage(ProveedoresSMSAction.TABLE_ID); // Pagina a mostrar
		String order = getOrder(ProveedoresSMSAction.TABLE_ID); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort(ProveedoresSMSAction.TABLE_ID); // Columna usada para
														// ordenar

		if (proveedorSMS != null)
			if (proveedorSMS.getNombre() != null && proveedorSMS.getNombre().length() <= 0)
				proveedorSMS.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(ProveedoresSMSAction.GENERALES_PAGESIZE, "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ProveedorSMSBean> result = servicioProveedorSMS.getProveedoresSMS(inicio,
				(export) ? -1 : Integer.parseInt(properties.getProperty(ProveedoresSMSAction.GENERALES_PAGESIZE, "20")), order,
				columnSort, proveedorSMS, Integer.parseInt(properties.getProperty(ProveedoresSMSAction.GENERALES_TIPO_SERVIDOR_SMS,null)));
		Integer totalSize = result.getTotalList();

		listaProveedoresSMS = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty(ProveedoresSMSAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null),
					Integer.parseInt(properties.getProperty(ProveedoresSMSAction.GENERALES_PAGESIZE, "20")));
		} else {
			getRequest().setAttribute(properties.getProperty(ProveedoresSMSAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null), totalSize);
		}

		if (listaProveedoresSMS != null && !listaProveedoresSMS.isEmpty()) {
			for (int indice = 0; indice < listaProveedoresSMS.size(); indice++) {

				ProveedorSMSBean provSMS = listaProveedoresSMS.get(indice);
				provSMS.setNombre(StringEscapeUtils.escapeHtml(provSMS.getNombre()));
				provSMS.setDescripcion(StringEscapeUtils.escapeHtml(provSMS.getDescripcion()));
			}
		}

		return SUCCESS;
	}
	
/* (non-Javadoc)
 * @see com.opensymphony.xwork2.ActionSupport#execute()
 */
/////MIGRADO
	public String execute() throws BaseException {
		if (getRequest().getSession().getAttribute(ProveedoresSMSAction.INFO_USER) == null)
			return ProveedoresSMSAction.NO_USER;

		int page = getPage(ProveedoresSMSAction.TABLE_ID); // Pagina a mostrar
		String order = getOrder(ProveedoresSMSAction.TABLE_ID); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort(ProveedoresSMSAction.TABLE_ID); // Columna usada para
														// ordenar

		if (proveedorSMS != null)
			if (proveedorSMS.getNombre() != null && proveedorSMS.getNombre().length() <= 0)
				proveedorSMS.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(ProveedoresSMSAction.GENERALES_PAGESIZE, "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ProveedorSMSBean> result = servicioProveedorSMS.getProveedoresSMS(inicio,
				(export) ? -1 : Integer.parseInt(properties.getProperty(ProveedoresSMSAction.GENERALES_PAGESIZE, "20")), order,
				columnSort, proveedorSMS, Integer.parseInt(properties.getProperty(ProveedoresSMSAction.GENERALES_TIPO_SERVIDOR_SMS,null)));
		Integer totalSize = result.getTotalList();

		listaProveedoresSMS = result.getPageList();

		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty(ProveedoresSMSAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null),
					Integer.parseInt(properties.getProperty(ProveedoresSMSAction.GENERALES_PAGESIZE, "20")));
		} else {
			getRequest().setAttribute(properties.getProperty(ProveedoresSMSAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null), totalSize);
		}

		if (listaProveedoresSMS != null && !listaProveedoresSMS.isEmpty()) {
			for (int indice = 0; indice < listaProveedoresSMS.size(); indice++) {

				ProveedorSMSBean proveedor = listaProveedoresSMS.get(indice);
				proveedor.setNombre(StringEscapeUtils.escapeHtml(proveedor.getNombre()));
				proveedor.setDescripcion(StringEscapeUtils.escapeHtml(proveedor.getDescripcion()));
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
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		if (getRequest().getSession().getAttribute(ProveedoresSMSAction.INFO_USER) == null)
			return ProveedoresSMSAction.NO_USER;
		if (proveedorSMS != null) {
			if (proveedorSMS.getIsActivo() != null && proveedorSMS.getIsActivo().indexOf("'activo'") != -1) {
				proveedorSMS.setActivo(true);
			} else {
				proveedorSMS.setActivo(false);
			}
			if (validaServidor(proveedorSMS)) {
				Long idProveedorSMS = servicioProveedorSMS.newProveedorSMS(proveedorSMS, Integer.parseInt(properties.getProperty(ProveedoresSMSAction.GENERALES_TIPO_SERVIDOR_SMS,null)),
						source, accion, accionId);
				this.idProveedorSMS = idProveedorSMS.toString();
				addActionMessageSession(this.getText("plataforma.proveedorsms.create.ok"));
			} else {
				return ERROR;
			}
		} else {
			addActionErrorSession(this.getText("plataforma.proveedorsms.create.error"));
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
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		if (getRequest().getSession().getAttribute(ProveedoresSMSAction.INFO_USER) == null)
			return ProveedoresSMSAction.NO_USER;
		ProveedorSMSBean proveedorSMSBBDD = null;
		if (proveedorSMS == null) {
			// throw new BusinessException("EL proveedorSMS recibido es nulo");
			addActionErrorSession(this.getText("plataforma.proveedorsms.update.error"));

		} else {
			if (proveedorSMS.getProveedorSMSId() == null) {
				if (idProveedorSMS != null) {
					proveedorSMS.setProveedorSMSId(new Long(idProveedorSMS));
					proveedorSMSBBDD = servicioProveedorSMS.loadProveedorSMS(proveedorSMS);
				} else {
					String idProvedorSMS = (String) request.getAttribute("idProveedorSMS");
					if (idProvedorSMS != null) {
						proveedorSMS.setId(new Long(idProvedorSMS));
						proveedorSMSBBDD = servicioProveedorSMS.loadProveedorSMS(proveedorSMS);
					}
				}

			} else {
				proveedorSMSBBDD = servicioProveedorSMS.loadProveedorSMS(proveedorSMS);

			}
			if (proveedorSMSBBDD != null) {
				proveedorSMSBBDD.setNombre(proveedorSMS.getNombre());
				proveedorSMSBBDD.setDescripcion(proveedorSMS.getDescripcion());
				proveedorSMSBBDD.setActivo(proveedorSMS.getActivo());
				proveedorSMSBBDD.setUrldestino(proveedorSMS.getUrldestino());
				proveedorSMSBBDD.setPordefecto(proveedorSMS.getPordefecto());
				// false = Recepción de Estado / true = Consulta de Estado
				proveedorSMSBBDD.setMetodoconsulta(proveedorSMS.getMetodoconsulta());
			}
			if (validaServidor(proveedorSMSBBDD)) {
				servicioProveedorSMS.updateProveedorSMS(proveedorSMSBBDD, source, accion, accionId);
				addActionMessageSession(this.getText("plataforma.proveedorsms.update.ok"));
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
	////MIGRADO
	public String load() throws BaseException {
		if (getRequest().getSession().getAttribute(ProveedoresSMSAction.INFO_USER) == null)
			return ProveedoresSMSAction.NO_USER;
		if (idProveedorSMS == null)
			throw new BusinessException("EL idProveedorSMS recibido es nulo");
		try {
			proveedorSMS = new ProveedorSMSBean();
			proveedorSMS.setProveedorSMSId(new Long(idProveedorSMS));
			proveedorSMS = servicioProveedorSMS.loadProveedorSMS(proveedorSMS);
			return SUCCESS;
		} catch (NumberFormatException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { proveedorSMS
					.getProveedorSMSId().toString() });
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { proveedorSMS
					.getProveedorSMSId().toString() });
			throw new BusinessException(mensg);
		}

	}

	/**
	 * Delete parametro proveedor SMS.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deleteParametroProveedorSMS() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PARAMETRO", null);
		if (getRequest().getSession().getAttribute(ProveedoresSMSAction.INFO_USER) == null)
			return ProveedoresSMSAction.NO_USER;
		if (parametroServidorId == null) {
			addActionErrorSession(this.getText("plataforma.proveedorsms.parametro.delete.error"));
		} else {
			parametroServidor = new ParametroServidorBean();
			parametroServidor.setParametroservidorid(new Long(parametroServidorId));
			servicioParametroServidor.deleteParametroServidor(parametroServidor, source, accion, accionId, descripcion);
			addActionMessageSession(this.getText("plataforma.proveedorsms.parametro.delete.ok"));

		}
		return SUCCESS;
	}

	/**
	 * Delete planificacion proveedor SMS.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String deletePlanificacionProveedorSMS() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		if (getRequest().getSession().getAttribute(ProveedoresSMSAction.INFO_USER) == null)
			return ProveedoresSMSAction.NO_USER;
		if (planificacionId == null) {
			addActionErrorSession(this.getText("plataforma.proveedorsms.planificacion.delete.error"));

		} else {
			planificacionServidor = new PlanificacionBean();
			planificacionServidor.setPlanificacionId(new Integer(planificacionId));
			servicioPlanificacion.deletePlanificacion(planificacionServidor, source, accion, accionId, descripcion);
			addActionMessageSession(this.getText("plataforma.proveedorsms.planificacion.delete.ok"));
		}
		return SUCCESS;
	}

	/**
	 * Delete.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String delete() throws BaseException {
		String accionPlanificacion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String accionServidor = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionIdServidor = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		
		if (getRequest().getSession().getAttribute(ProveedoresSMSAction.INFO_USER) == null)
			return ProveedoresSMSAction.NO_USER;
		if (idProveedorSMS == null) {
			addActionErrorSession(this.getText("plataforma.proveedorsms.delete.error"));
		} else {
			proveedorSMS = new ProveedorSMSBean();
			proveedorSMS.setProveedorSMSId(new Long(idProveedorSMS));
			servicioProveedorSMS.deleteProveedorSMS(proveedorSMS, accionServidor, accionIdServidor, source, accionPlanificacion, accionIdPlanificacion, descripcionPlanificacion);
			addActionMessageSession(this.getText("plataforma.proveedorsms.delete.ok"));

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
		String accionPlanificacion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String accionServidor = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdServidor = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		
		if (getRequest().getSession().getAttribute(ProveedoresSMSAction.INFO_USER) == null)
			return ProveedoresSMSAction.NO_USER;
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.proveedorsms.deleteSelected.error"));
		} else {
			for (String idProveedorSMS : checkDelList) {
				proveedorSMS = new ProveedorSMSBean();
				proveedorSMS.setProveedorSMSId(new Long(idProveedorSMS));
				servicioProveedorSMS.deleteProveedorSMS(proveedorSMS, accionServidor, accionIdServidor, source, accionPlanificacion, accionIdPlanificacion, descripcionPlanificacion);
			}
			addActionMessageSession(this.getText("plataforma.proveedorsms.deleteSelected.ok"));

		}
		return SUCCESS;

	}

	/**
	 * Agrega parametro proveedor SMS.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String addParametroProveedorSMS() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PARAMETRO", null);
		if (getRequest().getSession().getAttribute(ProveedoresSMSAction.INFO_USER) == null)
			return ProveedoresSMSAction.NO_USER;
		if (parametroServidor != null) {
			if (!validaParametro(parametroServidor)) {
				return ERROR;
			} else {
				try {
					servicioParametroServidor.newParametroServidor(parametroServidor, source, accion, accionId, descripcion);
					addActionMessageSession(this.getText("plataforma.proveedorsms.parametro.add.ok"));
				} catch (ConstraintViolationException e) {
					addActionErrorSession(this.getText("plataforma.proveedorsms.parametro.add.constraint.error"));
				}
			}
		} else {
			addActionErrorSession(this.getText("plataforma.proveedorsms.parametro.add.error"));
			return ERROR;
		}
		return SUCCESS;
	}

	
	/**
	 * Agrega planificacion proveedor SMS.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String addPlanificacionProveedorSMS() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PLANIFICACION", null);
		if (getRequest().getSession().getAttribute(ProveedoresSMSAction.INFO_USER) == null)
			return ProveedoresSMSAction.NO_USER;
		if (planificacionServidor != null && PlataformaMensajeriaUtil.isEmpty(idProveedorSMS)) {
			if (planificacionValida(planificacionServidor)) {
				planificacionServidor.setActivo(true);
				planificacionServidor.setTipoPlanificacionId(new Integer(2));

				int valido = servicioPlanificacion.validaPlanificacionServidor(planificacionId,
						planificacionServidor.getServidorId(), planificacionServidor.getLunes(),
						planificacionServidor.getMartes(), planificacionServidor.getMiercoles(),
						planificacionServidor.getJueves(), planificacionServidor.getViernes(),
						planificacionServidor.getSabado(), planificacionServidor.getDomingo(),
						planificacionServidor.getHoraHasta(), planificacionServidor.getHoraDesde());

				if (valido == 1) {
					servicioPlanificacion.newPlanificacion(planificacionServidor, source, accion, accionId, descripcion);
					addActionMessageSession(this.getText("plataforma.proveedorsms.planificacion.add.ok"));
				} else if (valido == 0) {
					addActionErrorSession("No se ha a&ntilde;adido la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
					return ERROR;
				}
			} else {
				return ERROR;
			}
		} else {
			addActionErrorSession(this.getText("plataforma.proveedorsms.planificacion.add.error"));
		}
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	//////MIGRADO
	@Override
	public void prepare() throws Exception {
		if (idProveedorSMS != null) {
			tiposParametros = (ArrayList<TipoParametroBean>) servicioTipoParametro.getTipoParametrosProveedorSMS();

			comboTipoParametros = getComboValues();
			listaParametrosServidor = getParametrosProveedorSMS();
			listaPlanificacionesServidor = getLoadPlanificacionesProveedorSMS();
		}
	}
	
	/**
	 * Método que resuelve el lugar donde tiene que volver.
	 *
	 * @return volver
	 */
	/////MIGRADO
	public String getVolver() {
		String volver = "buscarProveedorSMS.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
		}
		return volver;
	}

	/**
	 * Valida parametro.
	 *
	 * @param parametroServidor the parametro servidor
	 * @return true, if successful
	 */
	/////MIGRADO
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
				"generales.PARAMETRO_SERVIDOR_TIPO_TELEFONO", null))
				&& !PlataformaMensajeriaUtil.isMobileNumber(parametroServidor.getValor())) {
			addActionErrorSession(this.getText("plataforma.proveedorsms.parametro.telefonomovil.error"));
			sw = false;
		}
		try {
			if (servicioParametroServidor.existeParametroServidor(parametroServidor)) {
				addActionErrorSession(this.getText("plataforma.proveedorsms.parametro.add.existe"));
				sw = false;
			}

		} catch (BusinessException e) {
			logger.error("ProveedoresSMSAction - validaParametro:" + e);
		}
		return sw;
	}

/**
 * Obtener load planificaciones proveedor SMS.
 *
 * @return load planificaciones proveedor SMS
 */
//////MIGRADO
	private List<PlanificacionBean> getLoadPlanificacionesProveedorSMS() {
		List<PlanificacionBean> lista = null;
		if (idProveedorSMS != null && idProveedorSMS.length() > 0) {
			try {
				lista = servicioPlanificacion.getPlanificacionesByServidorId(new Integer(idProveedorSMS));
			} catch (NumberFormatException e) {
				logger.error("ProveedoresSMSAction - getLoadPlanificacionesProveedorSMS:" + e);
			} catch (BusinessException e) {
				logger.error("ProveedoresSMSAction - getLoadPlanificacionesProveedorSMS:" + e);
			}
		} else if (proveedorSMS != null && proveedorSMS.getProveedorSMSId() != null) {
			try {
				lista = servicioPlanificacion.getPlanificacionesByServidorId(new Integer(idProveedorSMS));
			} catch (NumberFormatException e) {
				logger.error("ProveedoresSMSAction - getLoadPlanificacionesProveedorSMS:" + e);
			} catch (BusinessException e) {
				logger.error("ProveedoresSMSAction - getLoadPlanificacionesProveedorSMS:" + e);
			}
		}
		return lista;
	}

/**
 * Obtener parametros proveedor SMS.
 *
 * @return parametros proveedor SMS
 */
//////MIGRADO
	private List<ParametroServidorBean> getParametrosProveedorSMS() {
		List<ParametroServidorBean> lista = null;
		if (idProveedorSMS != null && idProveedorSMS.length() > 0) {
			try {
				lista = servicioParametroServidor.getParametroServidorByProveedorSMSId(new Integer(idProveedorSMS));
			} catch (NumberFormatException e) {
				logger.error("ProveedoresSMSAction - getParametrosProveedorSMS: idProveedor no es numerico" + e);
			} catch (BusinessException e) {
				logger.error("ProveedoresSMSAction - getParametrosProveedorSMS:" + e);
			}
		} else if (proveedorSMS != null && proveedorSMS.getProveedorSMSId() != null) {
			try {
				lista = servicioParametroServidor.getParametroServidorByProveedorSMSId(proveedorSMS.getProveedorSMSId()
						.intValue());
			} catch (NumberFormatException e) {
				logger.error("ProveedoresSMSAction - getParametrosProveedorSMS: idProveedor no es numerico" + e);
			} catch (BusinessException e) {
				logger.error("ProveedoresSMSAction - getParametrosProveedorSMS:" + e);
			}
		}
		return lista;
	}

/**
 * Obtener combo values.
 *
 * @return combo values
 */
//////MIGRADO
	private List<KeyValueObject> getComboValues() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;
		ArrayList<TipoParametroBean> keys = null;
		try {
			keys = (ArrayList<TipoParametroBean>) servicioTipoParametro.getTipoParametrosProveedorSMS();

		} catch (BusinessException e) {
			logger.error("ProveedoresSMSAction - getComboValues:" + e);
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
				addFieldErrorSession(this.getText("plataforma.proveedorsms.planificacion.horas.error"));
				sw = false;

			}
			if (!PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
				addFieldErrorSession(this.getText("plataforma.proveedorsms.planificacion.horaHasta.error"));
				sw = false;
			}
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())
					&& !PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
				addFieldErrorSession(this.getText("plataforma.proveedorsms.planificacion.horaDesde.error"));
				sw = false;
			}
			if (sw) {
				if (!validoFormatoHora(planificacionServidor.getHoraDesde())) {
					addFieldErrorSession(this.getText("plataforma.proveedorsms.planificacion.horaDesde.formato.error"));
					sw = false;
				}
				if (!validoFormatoHora(planificacionServidor.getHoraHasta())) {
					addFieldErrorSession(this.getText("plataforma.proveedorsms.planificacion.horaHasta.formato.error"));
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
				addFieldErrorSession(this.getText("plataforma.proveedorsms.planificacion.dias.error"));
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
		String[] horaDesdeArray = horaDesde.split(":");
		String[] horaHastaArray = horaHasta.split(":");
		int hDesde = new Integer(horaDesdeArray[0]);
		int mDesde = new Integer(horaDesdeArray[1]);
		int hHasta = new Integer(horaHastaArray[0]);
		int mHasta = new Integer(horaHastaArray[1]);
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
 * Valida servidor.
 *
 * @param servidor the servidor
 * @return true, if successful
 */
////MIGRADO
	private boolean validaServidor(ProveedorSMSBean servidor) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getNombre())) {
			addActionErrorSession(this.getText("plataforma.proveedorsms.field.nombre.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getDescripcion())) {
			addActionErrorSession(this.getText("plataforma.proveedorsms.field.descripcion.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getUrldestino())) {
			addActionErrorSession(this.getText("plataforma.proveedorsms.field.urldestino.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getMetodoconsulta())) {
			addActionErrorSession(this.getText("plataforma.proveedorsms.field.metodoConsulta.error"));
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
		if (!PlataformaMensajeriaUtil.isEmpty(hora)) {
			if (!PlataformaMensajeriaUtil.validaFormatoHora(hora)) {
				sw = false;
			}
		}
		return sw;
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
	 * Obtener lista proveedores SMS.
	 *
	 * @return lista proveedores SMS
	 */
	public List<ProveedorSMSBean> getListaProveedoresSMS() {
		return listaProveedoresSMS;
	}

	/**
	 * Modificar lista proveedores SMS.
	 *
	 * @param listaProveedoresSMS new lista proveedores SMS
	 */
	public void setListaProveedoresSMS(List<ProveedorSMSBean> listaProveedoresSMS) {
		this.listaProveedoresSMS = listaProveedoresSMS;
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
	 * Obtener proveedor SMS.
	 *
	 * @return proveedor SMS
	 */
	public ProveedorSMSBean getProveedorSMS() {
		return proveedorSMS;
	}

	/**
	 * Modificar proveedor SMS.
	 *
	 * @param proveedorSMS new proveedor SMS
	 */
	public void setProveedorSMS(ProveedorSMSBean proveedorSMS) {
		this.proveedorSMS = proveedorSMS;
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
}
