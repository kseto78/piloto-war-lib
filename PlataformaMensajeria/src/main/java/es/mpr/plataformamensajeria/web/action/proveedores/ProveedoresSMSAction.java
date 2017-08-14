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

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ProveedoresSMSAction.class);

	@Resource(name = "servicioProveedorSMSImpl")
	private ServicioProveedorSMS servicioProveedorSMS;

	@Resource(name = "servicioTipoParametroImpl")
	private ServicioTipoParametro servicioTipoParametro;

	@Resource(name = "servicioParametroServidorImpl")
	private ServicioParametroServidor servicioParametroServidor;

	@Resource(name = "servicioPlanificacionImpl")
	private ServicioPlanificacion servicioPlanificacion;

	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;

	private ParametroServidorBean parametroServidor;
	private ProveedorSMSBean proveedorSMS;
	private PlanificacionBean planificacionServidor;

	List<KeyValueObject> comboTipoParametros = new ArrayList<KeyValueObject>();

	public List<ProveedorSMSBean> listaProveedoresSMS = null;
	private List<ParametroServidorBean> listaParametrosServidor = null;
	private List<PlanificacionBean> listaPlanificacionesServidor = null;
	ArrayList<TipoParametroBean> tiposParametros = new ArrayList<TipoParametroBean>();

	private String[] checkDelList;

	private String planificacionId;
	private String parametroServidorId;
	private String tipoParametroId;
	private String idProveedorSMS;
	private String resultCount;

	public String newSearch() throws BaseException {
		return SUCCESS;
	}

	//////MIGRADO
	public String search() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";

		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para
														// ordenar

		if (proveedorSMS != null)
			if (proveedorSMS.getNombre() != null && proveedorSMS.getNombre().length() <= 0)
				proveedorSMS.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ProveedorSMSBean> result = servicioProveedorSMS.getProveedoresSMS(inicio,
				(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order,
				columnSort, proveedorSMS, Integer.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_SMS",null)));
		Integer totalSize = result.getTotalList();

		listaProveedoresSMS = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
		} else {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
		}

		if (listaProveedoresSMS != null && !listaProveedoresSMS.isEmpty()) {
			for (int indice = 0; indice < listaProveedoresSMS.size(); indice++) {

				ProveedorSMSBean proveedorSMS = listaProveedoresSMS.get(indice);
				proveedorSMS.setNombre(StringEscapeUtils.escapeHtml(proveedorSMS.getNombre()));
				proveedorSMS.setDescripcion(StringEscapeUtils.escapeHtml(proveedorSMS.getDescripcion()));
			}
		}

		return SUCCESS;
	}
	
/////MIGRADO
	public String execute() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";

		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para
														// ordenar

		if (proveedorSMS != null)
			if (proveedorSMS.getNombre() != null && proveedorSMS.getNombre().length() <= 0)
				proveedorSMS.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ProveedorSMSBean> result = servicioProveedorSMS.getProveedoresSMS(inicio,
				(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order,
				columnSort, proveedorSMS, Integer.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_SMS",null)));
		Integer totalSize = result.getTotalList();

		listaProveedoresSMS = result.getPageList();

		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
		} else {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
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

	
	////MIGRADO
	public String create() throws BaseException {
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (proveedorSMS != null) {
			if (proveedorSMS.getIsActivo() != null && proveedorSMS.getIsActivo().indexOf("'activo'") != -1) {
				proveedorSMS.setActivo(true);
			} else {
				proveedorSMS.setActivo(false);
			}
			if (validaServidor(proveedorSMS)) {
				Long idProveedorSMS = servicioProveedorSMS.newProveedorSMS(proveedorSMS, Integer.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_SMS",null)),
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

	///MIGRADO
	public String update() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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

	////MIGRADO
	public String load() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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

	///MIGRADO
	public String deleteParametroProveedorSMS() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PARAMETRO", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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

	////MIGRADO
	public String deletePlanificacionProveedorSMS() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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

	////MIGRADO
	public String delete() throws BaseException {
		String accionPlanificacion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String accionServidor = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionIdServidor = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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

	////MIGRADO
	public String deleteSelected() throws BaseException {
		String accionPlanificacion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String accionServidor = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdServidor = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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

	////MIGRADO
	public String addParametroProveedorSMS() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PARAMETRO", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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

	
	////MIGRADO
	public String addPlanificacionProveedorSMS() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PLANIFICACION", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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
	 * Método que resuelve el lugar donde tiene que volver
	 */
	/////MIGRADO
	public String getVolver() {
		String volver = "buscarProveedorSMS.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
		}
		return volver;
	}

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

	public List<KeyValueObject> getComboTipoParametros() {
		return comboTipoParametros;
	}

	public void setComboTipoParametros(List<KeyValueObject> comboTipoParametros) {
		this.comboTipoParametros = comboTipoParametros;
	}

	public List<ProveedorSMSBean> getListaProveedoresSMS() {
		return listaProveedoresSMS;
	}

	public void setListaProveedoresSMS(List<ProveedorSMSBean> listaProveedoresSMS) {
		this.listaProveedoresSMS = listaProveedoresSMS;
	}

	public ServicioProveedorSMS getServicioProveedorSMS() {
		return servicioProveedorSMS;
	}

	public void setServicioProveedorSMS(ServicioProveedorSMS servicioProveedorSMS) {
		this.servicioProveedorSMS = servicioProveedorSMS;
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

	public ProveedorSMSBean getProveedorSMS() {
		return proveedorSMS;
	}

	public void setProveedorSMS(ProveedorSMSBean proveedorSMS) {
		this.proveedorSMS = proveedorSMS;
	}

	public String getIdProveedorSMS() {
		return idProveedorSMS;
	}

	public void setIdProveedorSMS(String idProveedorSMS) {
		this.idProveedorSMS = idProveedorSMS;
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
}
