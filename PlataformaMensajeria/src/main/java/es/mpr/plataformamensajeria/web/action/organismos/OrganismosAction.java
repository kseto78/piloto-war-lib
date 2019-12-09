package es.mpr.plataformamensajeria.web.action.organismos;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.map.j2ee.util.beanutils.converters.DateConverter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

import es.minhap.plataformamensajeria.iop.beans.PdpDiputacionesBean;
import es.minhap.sim.model.TblOrganismos;
import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.DetalleAplicacionBean;
import es.mpr.plataformamensajeria.beans.DetalleServicioBean;
import es.mpr.plataformamensajeria.beans.OrganismoBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServicioOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidoresOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;
import es.mpr.plataformamensajeria.beans.UsuarioAplicacionBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPdpDiputaciones;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuarioAplicacion;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los organismos.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de los Organismos
 * 
 * @author Altran
 * 
 */
@Controller("organismosAction")
@Scope("prototype")
public class OrganismosAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	protected static final String ORGANISMOSACTIO = "OrganismosAction - cargarComboServicioOrganismos:";

	protected static final String PLATAFORMADOTSE = "plataforma.servicio.servicioOrganismo.add.error";

	protected static final String R_CONST_REF = ":";

	protected static final String R_CONST_0 = "=";

	protected static final String ORGANISMOSACTIO0 = "OrganismosAction - load:";

	protected static final String R_CONST_1 = "20";

	protected static final String R_CONST_2 = "?";

	protected static final String PLATAFORMADOTOR = "plataforma.organismo.update.cambiopdp";

	protected static final String TXTORGANISMO = "organismo";

	protected static final String EL_IDORGANISMO_REF = "EL idOrganismo recibido es nulo";

	protected static final String PLATAFORMADOTSE0 = "plataforma.servidores.planificacion.horaDesde.menor.error";

	protected static final String PLATAFORMADOTSE1 = "plataforma.servicio.servicioOrganismo.add.ok";

	protected static final String PLATAFORMADOTSE2 = "plataforma.servicio.servidorOrganismo.delete.ok";

	protected static final String ERRORSDOTACTION = "errors.action.organismo.loadOrganismo";

	protected static final String ORGANISMOSACTIO1 = "OrganismosAction - loadSeviciosOrganismo:";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(OrganismosAction.class);
	
	/**  servicio organismo. */
	@Resource(name="servicioOrganismoImpl")
	private transient ServicioOrganismo servicioOrganismo;
	
	/**  servicio servicio. */
	@Resource(name="servicioServicioImpl")
	private transient ServicioServicio servicioServicio;
	
	/**  servicio planificacion. */
	@Resource(name="servicioPlanificacionImpl")
	private transient ServicioPlanificacion servicioPlanificacion;
	
	/**  servicio usuario aplicacion. */
	@Resource(name="servicioUsuarioAplicacionImpl")
	private transient ServicioUsuarioAplicacion servicioUsuarioAplicacion;
	
	/**  servicio servidor. */
	@Resource(name="servicioServidorImpl")
	private transient ServicioServidor servicioServidor;
	
	/**  servicio organismoPdp. */
	@Resource(name="servicioPdpDiputacionesImpl")
	private transient ServicioPdpDiputaciones servicioOrganismoPdp;
	
	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private transient PlataformaMensajeriaProperties properties;
	
	/**  servicio proveedor SMS. */
	@Resource(name = "servicioProveedorSMSImpl")
	private transient ServicioProveedorSMS servicioProveedorSMS;
	
	/**  organismo. */
	private OrganismoBean organismo; 
	
	/**  organismo padre. */
	private OrganismoBean organismoPadre; 
	
	/**  servidor organismo. */
	private ServidoresOrganismosBean servidorOrganismo;
	
	/**  aplicacion. */
	private AplicacionBean aplicacion;
	
	/**  servicio organismos. */
	private ServicioOrganismosBean servicioOrganismos;
	
	/**  servicio. */
	private ServicioBean servicio;
	
	/**  servidor. */
	private ServidorBean servidor;
	
	/**  planificacion organismo. */
	private PlanificacionBean planificacionOrganismo;
	
	/**  lista organismos. */
	public List<OrganismoBean> listaOrganismos = null;
	
	/**  lista servicio organismos. */
	public List<ServicioOrganismosBean> listaServicioOrganismos = null;
	
	/**  lista servidores organismos. */
	public List<ServidoresOrganismosBean> listaServidoresOrganismos = null;
	
	/**  lista planificaciones servicio. */
	public List<PlanificacionBean> listaPlanificacionesServicio = null;
	
	/**  combo servicio organismos. */
	transient List<KeyValueObject> comboServicioOrganismos = new ArrayList<>();
	
	/**  combo organismos hijos */
	transient List<KeyValueObject> comboOrganismosHijos = new ArrayList<>();
	
	/**  combo servidores organismos. */
	transient List<KeyValueObject> comboServidoresOrganismos = new ArrayList<>();
	
	/**  combo servidores plan. */
	transient List<KeyValueObject> comboServidoresPlan = new ArrayList<>();
	
	/**  combo servicios plan. */
	transient List<KeyValueObject> comboServiciosPlan = new ArrayList<>();
	
	/**  combo servidores. */
	transient List<KeyValueObject> comboServidores = new ArrayList<>();
	
	/**  combo tipos estados. */
	transient List<KeyValueObject> comboTiposEstados = new ArrayList<>();
	
	/**  combo organismos pdp. */
	transient List<KeyValueObject> comboOrganismosPdp = new ArrayList<>();

	/**  combo proveedores SMS */
	transient List<KeyValueObject> comboProveedoresSMS = new ArrayList<>();
			
	
	/**  check del list. */
	private String[] checkDelList;
	
	/**  check del list organismos servicios. */
	private String[] checkDelListOrganismosServicios;
	
	/**  check del list servidor organismos. */
	private String[] checkDelListServidorOrganismos;
	
	/**  check del list planificaciones organismos. */
	private String[] checkDelListPlanificacionesOrganismos;
	
	/**  id organismo. */
	private String idOrganismo;
	
	/**  id servicio organismo. */
	private String idServicioOrganismo;
	
	/**  servidor organismo id. */
	private String servidorOrganismoId;
	
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
	
	/**  result count. */
	private String resultCount;
	
	/**  check password. */
	private String checkPassword;
	
	/**  recovery. */
	private String recovery = "";
	
	/**  session. */
	@SuppressWarnings("rawtypes")
	private Map session;
	
	/** Constante INFO_USER. */
	private static final String INFO_USER = "infoUser";

	/** Constante NO_USER. */
	private static final String NO_USER = "noUser";

	/** Constante TABLE_ID. */
	private static final String TABLE_ID = "tableId";

	/** Constante GENERALES_REQUEST_ATTRIBUTE_TOTALSIZE. */
	private static final String GENERALES_REQUEST_ATTRIBUTE_TOTALSIZE = "generales.REQUEST_ATTRIBUTE_TOTALSIZE";

	/** Constante GENERALES_REQUEST_ATTRIBUTE_PAGESIZE. */
	private static final String GENERALES_REQUEST_ATTRIBUTE_PAGESIZE = "generales.REQUEST_ATTRIBUTE_PAGESIZE";

	/** Constante LOG_ACCIONID_ACTUALIZAR. */
	private static final String LOG_ACCIONID_ACTUALIZAR = "log.ACCIONID_ACTUALIZAR";

	/** Constante LOG_ACCION_ACTUALIZAR. */
	private static final String LOG_ACCION_ACTUALIZAR = "log.ACCION_ACTUALIZAR";

	/** Constante GENERALES_PAGESIZE. */
	private static final String GENERALES_PAGESIZE = "generales.PAGESIZE";

	/** Constante LOG_ACCIONID_ELIMINAR. */
	private static final String LOG_ACCIONID_ELIMINAR = "log.ACCIONID_ELIMINAR";

	/** Constante LOG_ACCION_ELIMINAR. */
	private static final String LOG_ACCION_ELIMINAR = "log.ACCION_ELIMINAR";

	/** Constante LOG_ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION. */
	private static final String LOG_ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION = "log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION";

	/** Constante LOG_SOURCE_ORGANISMOS. */
	private static final String LOG_SOURCE_ORGANISMOS = "log.SOURCE_ORGANISMOS";
	
	/** Constante RECOVERY. */
	private static final String TXTRECOVERY = "recovery";
	
	/** Constante SEPARADOR_OPCIONES_VALUES. */
	private static final String SEPARADOR_OPCIONES_VALUES = "#";

	/** Constante SEPARADOR_OPCIONES. */
	private static final String SEPARADOR_OPCIONES = "&&";

	/**  detalle aplicacion. */
	private DetalleAplicacionBean detalleAplicacion;


	/**
	 * New search.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String newSearch() throws BaseException {
		if (getRequest().getSession().getAttribute(OrganismosAction.INFO_USER) == null) {
			return OrganismosAction.NO_USER;
		}
		
		organismo = (OrganismoBean) getRequest().getSession().getAttribute(TXTORGANISMO);
		
		
		Integer totalSize = 0;
		getRequest().setAttribute(properties.getProperty(OrganismosAction.GENERALES_REQUEST_ATTRIBUTE_TOTALSIZE, null), totalSize);
		listaOrganismos =new ArrayList<>();
		
		return SUCCESS;
	}
	
	/**
	 * Arbol Organismos.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String arbolOrganismos() throws BaseException {
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error(ORGANISMOSACTIO0 + e);
			return OrganismosAction.NO_USER;
		}
		if (idOrganismo == null) {
			throw new BusinessException(EL_IDORGANISMO_REF);
		}
		try {
			organismo = new OrganismoBean();
			organismo.setOrganismoId(Integer.valueOf(idOrganismo));
			organismo = servicioOrganismo.loadOrganismo(organismo);
			comboOrganismosHijos = cargarComboOrganismosHijos(organismo.getDir3());
			organismoPadre = new OrganismoBean();			
			int idPadre = servicioOrganismo.getOrganismoIdByDir3SoloEliminado(organismo.getCodUnidadSuperior());
			if(idPadre != 0){
				organismoPadre.setOrganismoId(idPadre);
				organismoPadre = servicioOrganismo.loadOrganismo(organismoPadre);
			}
			return SUCCESS;
		} catch (NumberFormatException | BusinessException e) {
			String mensg = this.getText(ERRORSDOTACTION, new String[] { organismo.getOrganismoId().toString() });
			logger.error(ORGANISMOSACTIO0 + e);
			throw new BusinessException(mensg);
		} 
	}

	/**
	 * Search.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String search() throws BaseException {

		if (getRequest().getSession().getAttribute(OrganismosAction.INFO_USER) == null) {
			return OrganismosAction.NO_USER;
		}

		int page = getPage(OrganismosAction.TABLE_ID); 
		// Pagina a mostrar
		String order = getOrder(OrganismosAction.TABLE_ID); 
		// Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort(OrganismosAction.TABLE_ID); 
		// Columna usada para ordenar

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(OrganismosAction.GENERALES_PAGESIZE, R_CONST_1));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<OrganismoBean> result = servicioOrganismo.getOrganismos(inicio, export ? -1 : Integer.parseInt(properties.getProperty(OrganismosAction.GENERALES_PAGESIZE, R_CONST_1)), order, columnSort, organismo);
		Integer totalSize = result.getTotalList();

		listaOrganismos = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty(OrganismosAction.GENERALES_REQUEST_ATTRIBUTE_TOTALSIZE, null), totalSize);
		if (!export) {
			getRequest().setAttribute(properties.getProperty(OrganismosAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null), 
					Integer.parseInt(properties.getProperty(OrganismosAction.GENERALES_PAGESIZE, R_CONST_1)));
		} else {
			getRequest().setAttribute(properties.getProperty(OrganismosAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null), 
					totalSize);
		}

		//guardamos el organismo para que al volver tengamos la ultima busqueda
		getRequest().getSession().setAttribute(TXTORGANISMO, organismo);

		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	///MIGRADO
	public String execute() throws BaseException {

		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return OrganismosAction.NO_USER;
		}
		int page = getPage(OrganismosAction.TABLE_ID); 
		// Pagina a mostrar
		String order = getOrder(OrganismosAction.TABLE_ID); 
		// Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort(OrganismosAction.TABLE_ID); 
		// Columna usada para ordenar

		if (organismo != null && organismo.getNombre() != null && organismo.getNombre().isEmpty()) {
			organismo.setNombre(null);
		}

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(OrganismosAction.GENERALES_PAGESIZE, R_CONST_1));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<OrganismoBean> result = servicioOrganismo.getOrganismos(inicio, export ? -1 : Integer.parseInt(properties.getProperty(OrganismosAction.GENERALES_PAGESIZE, R_CONST_1)), order, columnSort, organismo);
		Integer totalSize = result.getTotalList();

		listaOrganismos = result.getPageList();

		getRequest().setAttribute(properties.getProperty(OrganismosAction.GENERALES_REQUEST_ATTRIBUTE_TOTALSIZE, null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty(OrganismosAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null),
					Integer.parseInt(properties.getProperty(OrganismosAction.GENERALES_PAGESIZE, R_CONST_1)));
		} else {
			getRequest().setAttribute(properties.getProperty(OrganismosAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null), totalSize);
		}
		if (listaOrganismos != null && !listaOrganismos.isEmpty()) {
			for (int indice = 0, s = listaOrganismos.size(); indice < s; indice++) {

				OrganismoBean organismo = listaOrganismos.get(indice);
				organismo.setNombre(StringEscapeUtils.escapeHtml(organismo.getNombre()));
				organismo.setDescripcion(StringEscapeUtils.escapeHtml(organismo.getDescripcion()));
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
		String source = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("OrganismosAction - create:" + e);
			return OrganismosAction.NO_USER;
		}
		if (organismo != null) {
			organismo.setManual(true);
			if (organismo.getIsActivo() != null && organismo.getIsActivo().contains("inactivo")) {
				organismo.setActivo(false);
			} else {
				organismo.setActivo(true);
			}
			if (!validaObligatorios(organismo, true)) {
				return ERROR;
			}
			boolean existeOrganismo = servicioOrganismo.existeOrganimo(organismo);
			
			if(!existeOrganismo){
				Integer id = servicioOrganismo.newOrganismo(organismo, source, accion, accionId);
				this.idOrganismo = id.toString();
				addActionMessageSession(this.getText("plataforma.organismo.create.ok"));
			}else{
				addActionErrorSession(this.getText("plataforma.organismo.create.organismoExiste"));
				return ERROR;
			}
			

			
		} else {
			addActionErrorSession(this.getText("plataforma.organismo.create.error"));
		}

		return SUCCESS;

	}
	
	/**
	 * Valida obligatorios.
	 *
	 * @param aplicacion2 the aplicacion 2
	 * @param isUpdate the is update
	 * @return true, if successful
	 */
	////MIGRADO
	private boolean validaObligatorios(OrganismoBean aplicacion2, boolean isUpdate) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getDir3()) && aplicacion2.getManual()) {
			addActionErrorSession(this.getText("plataforma.organismo.field.DIR3.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getNombre()) && aplicacion2.getManual()) {
			addActionErrorSession(this.getText("plataforma.organismo.field.nombre.error"));
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
		String accion = properties.getProperty(OrganismosAction.LOG_ACCION_ACTUALIZAR, null);
		Long accionId = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ACTUALIZAR, null));
		String source = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("OrganismosAction - update:" + e);
			return OrganismosAction.NO_USER;
		}
		OrganismoBean organismoBBDD = null;
		if (organismo == null) {
			addActionErrorSession(this.getText("plataforma.organismo.update.error"));

		} else {
			
			if (organismo.getOrganismoId() == null) {
				if (idOrganismo != null) {
					organismo.setOrganismoId(Integer.valueOf(idOrganismo));
					organismoBBDD = servicioOrganismo.loadOrganismo(organismo);
				} else {
					String id = (String) request.getAttribute("idOrganismo");
					
					if (id != null) {
						aplicacion.setId(Long.valueOf(id));
						organismoBBDD = servicioOrganismo.loadOrganismo(organismo);
					}
				}
			} else {
				organismoBBDD = servicioOrganismo.loadOrganismo(organismo);

			}
			if (organismoBBDD != null) {
				if (organismoBBDD.getManual()){
					organismoBBDD.setDir3(organismo.getDir3());
					organismoBBDD.setNombre(organismo.getNombre());
					organismoBBDD.setDescripcion(organismo.getDescripcion());
				}
				if( organismoBBDD.getIdPdpDiputaciones() != organismo.getIdPdpDiputaciones()){
						
					if(organismoBBDD.getIdPdpDiputaciones() != null && organismo.getIdPdpDiputaciones() != null){
						if(!organismoBBDD.getIdPdpDiputaciones().equals(organismo.getIdPdpDiputaciones()) ){
							addActionWarningMessageSession(this.getText(PLATAFORMADOTOR));
						}
					}else{
						addActionWarningMessageSession(this.getText(PLATAFORMADOTOR));
					}
					
				}
				organismoBBDD.setIdPdpDiputaciones(organismo.getIdPdpDiputaciones());
				organismoBBDD.setActivo(organismo.getActivo());
				organismo = organismoBBDD;
				if (validaObligatorios(organismoBBDD, true)) {
					servicioOrganismo.updateOrganismo(organismoBBDD, source, accion, accionId);
					addActionMessageSession(this.getText("plataforma.organismo.update.ok"));
				}
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
	///MIGRADO
	public String load() throws BaseException {
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error(ORGANISMOSACTIO0 + e);
			return OrganismosAction.NO_USER;
		}
		if (idOrganismo == null) {
			throw new BusinessException(EL_IDORGANISMO_REF);
		}
		try {
			organismo = new OrganismoBean();
			organismo.setOrganismoId(Integer.valueOf(idOrganismo));
			organismo = servicioOrganismo.loadOrganismo(organismo);

			return SUCCESS;
		} catch (NumberFormatException | BusinessException e) {
			String mensg = this.getText(ERRORSDOTACTION, new String[] { organismo.getOrganismoId().toString() });
			logger.error(ORGANISMOSACTIO0 + e);
			throw new BusinessException(mensg);
		} 

	}
	
	/**
	 * Delete.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String delete() throws BaseException {
		String accionPlanificacion = properties.getProperty(OrganismosAction.LOG_ACCION_ACTUALIZAR, null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ACTUALIZAR, null));
		String descripcionPlanificacion = properties.getProperty(OrganismosAction.LOG_ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION, null);
		String sourcePlanificacion = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);
		String accion = properties.getProperty(OrganismosAction.LOG_ACCION_ELIMINAR, null);
		Long accionId = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ELIMINAR, null));
		String source = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);
				
		if (getRequest().getSession().getAttribute(OrganismosAction.INFO_USER) == null) {
			return OrganismosAction.NO_USER;
		}
		if (idOrganismo == null) {
			addActionErrorSession(this.getText("plataforma.organismo.delete.error"));

		} else {
			//eliminamos organismos-servicios
			List<ServicioOrganismosBean> listaServiciosOrganismos = servicioServicio.getOrganismoServicio(idOrganismo);
			for (ServicioOrganismosBean servicioOrganismosBean : listaServiciosOrganismos) {
				deleteServicioOrganismo(servicioOrganismosBean.getServicioOrganismoId());
				updateServicio(servicioOrganismosBean.getServicioId());
			}
			
			//eliminamos los servidores-Organismos
			List<ServidoresOrganismosBean> listaServidorOrganismos = servicioServidor.getServidorOrganismo(idOrganismo);
			for (ServidoresOrganismosBean servidoresOrganismosBean : listaServidorOrganismos) {
				deleteServidorOrganismo(servidoresOrganismosBean.getServidorOrganismoId());
				updateServidor(servidoresOrganismosBean.getServidorId());
			}
			
			// borramos sus planificaciones
			List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
			for (PlanificacionBean o : listaPlanificacionesOrganismos) {
 	
					servicioPlanificacion.deletePlanificacion(o, sourcePlanificacion, accionPlanificacion, accionIdPlanificacion, descripcionPlanificacion);
			}
			
			
			servicioOrganismo.deleteOrganismo(Long.parseLong(idOrganismo), source, accion, accionId);
					
			addActionMessageSession(this.getText("plataforma.organismo.delete.ok"));
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
		String accionPlanificacion = properties.getProperty(OrganismosAction.LOG_ACCION_ACTUALIZAR, null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ACTUALIZAR, null));
		String descripcionPlanificacion = properties.getProperty(OrganismosAction.LOG_ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION, null);
		String sourcePlanificacion = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);
		String accion = properties.getProperty(OrganismosAction.LOG_ACCION_ELIMINAR, null);
		Long accionId = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ELIMINAR, null));
		String source = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);
		
		if (getRequest().getSession().getAttribute(OrganismosAction.INFO_USER) == null) {
			return OrganismosAction.NO_USER;
		}
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.organimo.deleteSelected.error"));

		} else {
			for (String idOrganismo : checkDelList) {
				//eliminamos organismos-servicios
				List<ServicioOrganismosBean> listaServiciosOrganismos = servicioServicio.getOrganismoServicio(idOrganismo);
				for (ServicioOrganismosBean servicioOrganismosBean : listaServiciosOrganismos) {
					deleteServicioOrganismo(servicioOrganismosBean.getServicioOrganismoId());
					updateServicio(servicioOrganismosBean.getServicioId());
				}
				
				//eliminamos los servidores-Organismos
				List<ServidoresOrganismosBean> listaServidorOrganismos = servicioServidor.getServidorOrganismo(idOrganismo);
				for (ServidoresOrganismosBean servidoresOrganismosBean : listaServidorOrganismos) {
					deleteServidorOrganismo(servidoresOrganismosBean.getServidorOrganismoId());
					updateServidor(servidoresOrganismosBean.getServidorId());
				}
				
				// borramos sus planificaciones
				List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
				for (PlanificacionBean o : listaPlanificacionesOrganismos) {
 	
						servicioPlanificacion.deletePlanificacion(o, sourcePlanificacion, accionPlanificacion, accionIdPlanificacion, descripcionPlanificacion);
				}
				
				
				servicioOrganismo.deleteOrganismo(Long.parseLong(idOrganismo), source, accion, accionId);
			}
			addActionMessageSession(this.getText("plataforma.organismo.deleteSelected.ok"));

		}
		return SUCCESS;

	}

	/**
	 * Delete organismo servicio selected.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	/////MIGRADO
	public String deleteOrganismoServicioSelected() throws BaseException {
		String accion = properties.getProperty(OrganismosAction.LOG_ACCION_ELIMINAR, null);
		Long accionId = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ELIMINAR, null));
		String descripcion = properties.getProperty(OrganismosAction.LOG_ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION, null);
		String source = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);
		
		if (getRequest().getSession().getAttribute(OrganismosAction.INFO_USER) == null) {
			return OrganismosAction.NO_USER;
		}
		if (checkDelListOrganismosServicios == null) {
			addActionErrorSession(this.getText("plataforma.servicio.servicioOrganismo.deleteSelected.error"));

		} else {

			for (String serOrg : checkDelListOrganismosServicios) {
				ServicioOrganismosBean so = new ServicioOrganismosBean();
				so.setServicioOrganismoId(Integer.valueOf(serOrg));
								
				updateServicio(servicioServicio.loadOrganismoServicio(so).getServicioId());

				updateOrganismo(Integer.parseInt(idOrganismo));

				//borrar planificaciones del organismo
				List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
				for (PlanificacionBean o : listaPlanificacionesOrganismos) {
					if (o.getServicioId().equals(Integer.parseInt(idServicio))) {
						servicioPlanificacion.deletePlanificacion(o, source, accion, accionId, descripcion);
					}
				}
				
				deleteServicioOrganismo(so.getServicioOrganismoId());
				
			}
			addActionMessageSession(this.getText(PLATAFORMADOTSE2));

		}
		return SUCCESS;
	}

    /**
     * Delete servidor organismos selected.
     *
     * @return the string
     * @throws BaseException the base exception
     */
    ///MIGRADO
	public String deleteServidorOrganismosSelected() throws BaseException {
		String accion = properties.getProperty(OrganismosAction.LOG_ACCION_ELIMINAR, null);
		Long accionId = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ELIMINAR, null));
		String descripcion = properties.getProperty(OrganismosAction.LOG_ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION, null);
		String source = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);
		if (getRequest().getSession().getAttribute(OrganismosAction.INFO_USER) == null) {
			return OrganismosAction.NO_USER;
		}

		if (checkDelListServidorOrganismos == null) {
			addActionErrorSession(this.getText("plataforma.servicio.servidorOrganismo.deleteSelected.error"));

		} else {
			
			for (String serOrg : checkDelListServidorOrganismos) {
				updateOrganismo(Integer.parseInt(idOrganismo));

				// ////borrar planificaciones
				List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
				if (null != listaPlanificacionesOrganismos){
					for (PlanificacionBean o : listaPlanificacionesOrganismos) {
 	
						if (o.getServidorId().equals(Integer.valueOf(idServidor))) {
							servicioPlanificacion.deletePlanificacion(o, source, accion, accionId, descripcion);
						}
	
					}
				}
				deleteServidorOrganismo(Long.valueOf(serOrg));

			}
			addActionMessageSession(this.getText(PLATAFORMADOTSE2));

		}
		return SUCCESS;
	}

	/**
	 * Delete planificaciones organismo selected.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deletePlanificacionesOrganismoSelected() throws BaseException {
		String accion = properties.getProperty(OrganismosAction.LOG_ACCION_ELIMINAR, null);
		Long accionId = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ELIMINAR, null));
		String source = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);
		String descripcion = properties.getProperty(OrganismosAction.LOG_ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION, null);
		if (getRequest().getSession().getAttribute(OrganismosAction.INFO_USER) == null) {
			return OrganismosAction.NO_USER;
		}
		if (checkDelListPlanificacionesOrganismos == null) {
			addActionErrorSession(this.getText("plataforma.servicio.deletePlanificacionesSelected.error"));

		} else {
			for (String idPlanificacion : checkDelListPlanificacionesOrganismos) {
				PlanificacionBean planificacion = new PlanificacionBean();
				planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
				servicioPlanificacion.deletePlanificacion(planificacion, source, accion, accionId, descripcion);
			}
			addActionMessageSession(this.getText("plataforma.servicio.deletePlanificacionesSelected.ok"));

		}
		return SUCCESS;
	}

	
	/**
	 * Load detalle organismo.
	 *
	 * @return the string
	 * @throws BusinessException the business exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	///MIGRADO
	public String loadDetalleOrganismo() throws BusinessException, IllegalAccessException, InvocationTargetException {
		if (getRequest().getSession().getAttribute(OrganismosAction.INFO_USER) == null) {
			return OrganismosAction.NO_USER;
		}
		OrganismoBean detalleApp = new OrganismoBean();
		DetalleAplicacionBean detalle = new DetalleAplicacionBean();
		if (idOrganismo != null) {
			detalleApp.setOrganismoId(Integer.valueOf(idOrganismo));
			detalleApp = servicioOrganismo.loadOrganismo(detalleApp);
			Date defaultValue = null;
			List<UsuarioAplicacionBean> listaUsuariosAplicacion = servicioUsuarioAplicacion.getUsuarioAplicacionesByAplicacionId(Integer.valueOf(idOrganismo));
			detalle.setListaUsuariosAplicacion(listaUsuariosAplicacion);
			DateConverter converter = new DateConverter(defaultValue);
			ConvertUtils.register(converter, java.util.Date.class);
			BeanUtils.copyProperties(detalle, detalleApp);
			detalle.setActivo(true);
			detalle.setFechacreacion(detalleApp.getFechacreacion());
			detalle.setFechamodificacion(detalleApp.getFechamodificacion());
			List<ServicioBean> listServicioBean = servicioServicio.getServiciosByAplicacionId(detalle.getAplicacionId().intValue());
			if (listServicioBean != null) {
				for (ServicioBean ser : listServicioBean) {
					DetalleServicioBean serBean = new DetalleServicioBean();
					BeanUtils.copyProperties(serBean, ser);
					serBean.setActivo(ser.getActivo());
					serBean.setFechacreacion(ser.getFechacreacion());
					serBean.setFechamodificacion(ser.getFechamodificacion());
					serBean.setFrommail(ser.getFrommail());
					serBean.setFrommailname(ser.getFrommailname());
					List<ServidoresServiciosBean> servidoresServiciosBeanList = servicioServicio.getServidoresServicios(ser.getServicioId().toString());
					serBean.setListaServidoresServicios(servidoresServiciosBeanList);
					List<PlanificacionBean> planificacionesList = servicioPlanificacion.getPlanificacionesByServicioID(ser.getServicioId());
					serBean.setListaPlanificaciones(planificacionesList);
					detalle.addDetalleServicio(serBean);
				}
			}

			detalleAplicacion = detalle;

		}
		return SUCCESS;
	}

	/**
	 * Load sevicios organismo.
	 *
	 * @return the list
	 */
	///MIGRADO
	private List<ServicioOrganismosBean> loadSeviciosOrganismo() {
		List<ServicioOrganismosBean> lista = null;
		if (idOrganismo != null && !idOrganismo.isEmpty()) {
			try {
				lista = servicioServicio.getOrganismoServicio(idOrganismo);
			} catch (NumberFormatException | BusinessException e) {
				logger.error(ORGANISMOSACTIO1 + e);
			} 
		} else if (organismo != null && organismo.getOrganismoId() != null) {
			try {
				lista = servicioServicio.getOrganismoServicio(String.valueOf(organismo.getOrganismoId()));
			} catch (NumberFormatException | BusinessException e) {
				logger.error(ORGANISMOSACTIO1 + e);
			} 
		}
		return lista;
	}

	/**
	 * Load servidores organismos.
	 *
	 * @return the list
	 */
	///MIGRADO
	private List<ServidoresOrganismosBean> loadServidoresOrganismos() {
		List<ServidoresOrganismosBean> lista = null;
		if (idOrganismo != null && !idOrganismo.isEmpty()) {
			try {
				lista = servicioServidor.getServidorOrganismo(idOrganismo);
			} catch (NumberFormatException | BusinessException e) {
				logger.error(ORGANISMOSACTIO1 + e);
			} 
		} else if (organismo != null && organismo.getOrganismoId() != null) {
			try {
				lista = servicioServidor.getServidorOrganismo(String.valueOf(organismo.getOrganismoId()));
			} catch (NumberFormatException |  BusinessException e) {
				logger.error(ORGANISMOSACTIO1 + e);
			} 
		}
		return lista;
	}

	/**
	 * Agrega planificacion organismo.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String addPlanificacionOrganismo() throws BaseException {
		String accion = properties.getProperty(OrganismosAction.LOG_ACCION_ACTUALIZAR, null);
		Long accionId = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ACTUALIZAR, null));
		String source = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PLANIFICACION", null);
		if (getRequest().getSession().getAttribute(OrganismosAction.INFO_USER) == null) {
			return OrganismosAction.NO_USER;
		}

		boolean sw = true;

		if (null==servidorOrganismo.getServidorId()) {
			addFieldErrorSession(this.getText("plataforma.servicio.servidorservicio.field.planificacion.servidor"));
			sw = false;
		}

		if (PlataformaMensajeriaUtil.isEmptyNumber(servicio.getServicioId())) {
			addFieldErrorSession(this.getText("plataforma.servicio.servidorservicio.field.planificacion.servicio"));
			sw = false;
		}

		if (sw) {

			planificacionOrganismo.setServicioId(servicio.getServicioId());
			planificacionOrganismo.setServidorId(servidorOrganismo.getServidorId().intValue());
			planificacionOrganismo.setOrganismoId(Integer.valueOf(idOrganismo));
			if (planificacionOrganismo != null && planificacionValida(planificacionOrganismo)) {

				planificacionOrganismo.setTipoPlanificacionId(Integer.valueOf(2));

				int valido = servicioPlanificacion.validaPlanificacionOptimaOrganismo(idPlanificacion, planificacionOrganismo.getTipoPlanificacionId(), planificacionOrganismo.getServidorId(), planificacionOrganismo.getServicioId(), planificacionOrganismo.getLunes(),
						planificacionOrganismo.getMartes(), planificacionOrganismo.getMiercoles(), planificacionOrganismo.getJueves(), planificacionOrganismo.getViernes(), planificacionOrganismo.getSabado(), planificacionOrganismo.getDomingo(),
						planificacionOrganismo.getHoraHasta(), planificacionOrganismo.getHoraDesde(), planificacionOrganismo.getOrganismoId());

				switch (valido) {
				case 1:
					planificacionOrganismo.setActivo(true);
					servicioPlanificacion.newPlanificacion(planificacionOrganismo, source, accion, accionId, descripcion);
					addActionMessageSession(this.getText("plataforma.servidores.planificacion.add.ok"));
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
		}

		return SUCCESS;
	}

	/**
	 * Planificacion valida.
	 *
	 * @param planificacionServidor the planificacion servidor
	 * @return true, if successful
	 */
	///MIGRADO
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
				if (sw && !validoHoras(planificacionServidor.getHoraDesde(), planificacionServidor.getHoraHasta())) {
					sw = false;
				}
			}
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getLunes()) && 
				PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getMartes()) && PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getMiercoles()) && PlataformaMensajeriaUtil
					.isEmpty(planificacionServidor.getJueves()) && PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getViernes()) && PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getSabado()) && PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getDomingo())) {
				addFieldErrorSession(this.getText("plataforma.servidores.planificacion.dias.error"));
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
	///MIGRADO
	private boolean validoHoras(String horaDesde, String horaHasta) {
		boolean sw = true;
		String[] horaDesdeArray = horaDesde.split(R_CONST_REF);
		String[] horaHastaArray = horaHasta.split(R_CONST_REF);
		int hDesde = Integer.parseInt(horaDesdeArray[0]);
		int mDesde = Integer.parseInt(horaDesdeArray[1]);
		int hHasta = Integer.parseInt(horaHastaArray[0]);
		int mHasta = Integer.parseInt(horaHastaArray[1]);
		if (hDesde > hHasta) {
			addFieldErrorSession(this.getText(PLATAFORMADOTSE0));
			sw = false;
		} else if (hDesde == hHasta && mDesde > mHasta) {
			addFieldErrorSession(this.getText(PLATAFORMADOTSE0));
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
		if (!PlataformaMensajeriaUtil.isEmpty(hora) && !PlataformaMensajeriaUtil.validaFormatoHora(hora)) {
			sw = false;
		}
		return sw;
	}

	
    /* (non-Javadoc)
     * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
     */
    ///MIGRADO
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare() throws Exception {
		session = (Map) ActionContext.getContext().get("session");
		recovery = (String) session.get(TXTRECOVERY);

		listaServicioOrganismos = loadSeviciosOrganismo();
		listaServidoresOrganismos = loadServidoresOrganismos();
		comboTiposEstados = getTipoEstados();

		if (idOrganismo != null) {
			if (organismo == null) {
				load();
			}
			comboServicioOrganismos = cargarComboServicioOrganismos();
			comboServidoresOrganismos = cargarComboServidoresOrganismos();
			comboServidoresPlan = cargarComboServidoresPlan();
			comboServiciosPlan = cargarComboServiciosPlan();
			comboOrganismosPdp = cargarComboOrganismosPdp();
			listaPlanificacionesServicio = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
		}
	}

	private List<KeyValueObject> cargarComboOrganismosPdp() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option;

		ArrayList<PdpDiputacionesBean> keys = null;
		try {
			keys = (ArrayList<PdpDiputacionesBean>) servicioOrganismoPdp.getPdpDiputaciones();
		} catch (BusinessException e) {
			logger.error("OrganismosAction - cargarComboOrganismosPdp:" + e);
		}

		if (keys != null && !keys.isEmpty()) {
			for (PdpDiputacionesBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getPdpDiputacionesId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}

	/**
	 * Delete planificacion organismo.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deletePlanificacionOrganismo() throws BaseException {
		String accion = properties.getProperty(OrganismosAction.LOG_ACCION_ELIMINAR, null);
		Long accionId = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ELIMINAR, null));
		String source = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);
		String descripcion = properties.getProperty(OrganismosAction.LOG_ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION, null);
		if (idPlanificacion == null) {
			addActionErrorSession(this.getText("plataforma.servicio.deletePlanificacion.error"));

		} else {
			PlanificacionBean planificacion = new PlanificacionBean();
			planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
			planificacion.setOrganismoId(Integer.valueOf(idOrganismo));
			servicioPlanificacion.deletePlanificacion(planificacion, source, accion, accionId, descripcion); 
			addActionMessageSession(this.getText("plataforma.servicio.deletePlanificacion.ok"));
		}
		return SUCCESS;
	}

	/**
	 * Cargar combo servicio organismos.
	 *
	 * @return the list
	 */
	///MIGRADO
	private List<KeyValueObject> cargarComboServicioOrganismos() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option;

		ArrayList<ServicioBean> keys = null;
		try {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServiciosMultiorganismo();
		} catch (BusinessException e) {
			logger.error(ORGANISMOSACTIO + e);
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
	 * Cargar combo servidores organismos.
	 *
	 * @return the list
	 */
	///MIGRADO
	private List<KeyValueObject> cargarComboServidoresOrganismos() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option;

		ArrayList<ServidorBean> keys = null;
		try {
			keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresYProveedores(properties.getProperty("generales.TIPO_SERVIDOR_SMS", null));
		} catch (BusinessException e) {
			logger.error(ORGANISMOSACTIO + e);
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
	 * Cargar combo organismos hijos.
	 * @param dir3 
	 *
	 * @return the list
	 */
	///MIGRADO
	private List<KeyValueObject> cargarComboOrganismosHijos(String dir3) {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option;

		ArrayList<TblOrganismos> keys = null;
		keys = (ArrayList<TblOrganismos>) servicioOrganismo.getOrganismosHijos(dir3);

		if (keys != null && !keys.isEmpty()) {
			for (TblOrganismos key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getDir3());
				option.setDescripcion(key.getNombre());
				
				result.add(option);
			}
		}
		return result;
	}
	
	/**
	 * Cargar combo servidores plan.
	 *
	 * @return the list
	 */
	///MIGRADO
	private List<KeyValueObject> cargarComboServidoresPlan() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option;

		ArrayList<ServidoresOrganismosBean> keys = null;
		try {
			keys = (ArrayList<ServidoresOrganismosBean>) servicioServidor.getServidorOrganismo(idOrganismo);
		} catch (BusinessException e) {
			logger.error("OrganismosAction - cargarComboServidoresPlan:" + e);
		}

		if (keys != null && !keys.isEmpty()) {
			for (ServidoresOrganismosBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getServidorId().toString());
				option.setDescripcion(key.getNombreServidor());
				result.add(option);
			}
		}
		return result;
	}

	/**
	 * Cargar combo servicios plan.
	 *
	 * @return the list
	 */
	///MIGRADO
	private List<KeyValueObject> cargarComboServiciosPlan() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option;

		ArrayList<ServicioOrganismosBean> keys = null;
		try {
			keys = (ArrayList<ServicioOrganismosBean>) servicioServicio.getOrganismoServicio(idOrganismo);
		} catch (BusinessException e) {
			logger.error("OrganismosAction - cargarComboServiciosPlan:" + e);
		}

		if (keys != null && !keys.isEmpty()) {
			for (ServicioOrganismosBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getServicioId().toString());
				option.setDescripcion(key.getNombreServicio());
				result.add(option);
			}
		}
		return result;
	}

	/**
	 * Agrega organismo servicios.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	@Transactional
	public String addOrganismoServicios() throws BaseException {

		String accion = properties.getProperty(OrganismosAction.LOG_ACCION_ACTUALIZAR, null);
		Long accionId = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ACTUALIZAR, null));
		String source = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);

		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_SERVICIO", null);
		if (getRequest().getSession().getAttribute(OrganismosAction.INFO_USER) == null) {
			return OrganismosAction.NO_USER;
		}
		boolean sw = true;

		if (servicioOrganismos != null) {

			if (PlataformaMensajeriaUtil.isEmptyNumber(servicioOrganismos.getServicioId())) {
				addFieldErrorSession(this.getText("plataforma.servicio.servidorServicio.field.multiorganismo.organismo.vacio"));
				sw = false;
			}

			if (null != listaServicioOrganismos) {
				for (ServicioOrganismosBean s : listaServicioOrganismos) {
					if (s.getServicioId().equals(servicioOrganismos.getServicioId())) {
						addFieldErrorSession(this.getText("plataforma.servicio.servidorservicio.field.multiorganismo.organismo.repetido"));
						sw = false;
					}
				}
			}

			if (sw) {

				ServicioBean sBean = new ServicioBean();
				sBean.setServicioId(servicioOrganismos.getServicioId());
				ServicioBean servBean = servicioServicio.loadServicio(sBean);
				servBean.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				servBean.setFechamodificacion(new Date());
				servicioServicio.updateServicio(servBean, null, null, null);

				updateOrganismo(Integer.parseInt(idOrganismo));

				servicioOrganismos.setServicioId(sBean.getServicioId());
				servicioOrganismos.setOrganismoId(Integer.valueOf(idOrganismo));

				servicioServicio.newServicioOrganismo(servicioOrganismos, source, accion, accionId, descripcion);
				addActionMessageSession(this.getText(PLATAFORMADOTSE1));
			} else {
				return ERROR;
			}
		} else {
			addActionErrorSession(this.getText(PLATAFORMADOTSE));
			return ERROR;
		}
		return SUCCESS;
	}

	//@Transactional(noRollbackFor = Exception.class)
	/**
	 * Agrega servidor organismo.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String addServidorOrganismo() throws BaseException {
		String accion = properties.getProperty(OrganismosAction.LOG_ACCION_ACTUALIZAR, null);
		Long accionId = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ACTUALIZAR, null));
		String source = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);

		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_SERVIDOR", null);
		if (getRequest().getSession().getAttribute(OrganismosAction.INFO_USER) == null) {
			return OrganismosAction.NO_USER;
		}
		boolean sw = true;

		if (servidorOrganismo != null) {

			if (PlataformaMensajeriaUtil.isEmpty(servidorOrganismo.getHeaderSMS())) {
				addFieldErrorSession(this.getText("plataforma.servicio.servidorServicio.field.headerSMS"));
				sw = false;
			}
			if (PlataformaMensajeriaUtil.isEmpty(servidorOrganismo.getProveedorUsuarioSMS())) {
				addFieldErrorSession(this.getText("plataforma.servicio.servidorServicio.field.proveedorUsuarioSMS"));
				sw = false;
			}
			if (PlataformaMensajeriaUtil.isEmpty(servidorOrganismo.getProveedorPasswordSMS())) {
				addFieldErrorSession(this.getText("plataforma.servicio.servidorServicio.field.proveedorPasswordSMS"));
				sw = false;
			}

			if (null != listaServidoresOrganismos) {
				for (ServidoresOrganismosBean s : listaServidoresOrganismos) {
					if (s.getServidorId().equals(servidorOrganismo.getServidorId())) {
						addFieldErrorSession(this.getText("plataforma.servicio.servidorOrganismo.field.multiorganismo.organismo.repetido"));
						sw = false;
					}
				}
			}
			if (sw) {
				try {
					updateServidor(servidorOrganismo.getServidorId());
					updateOrganismo(Integer.parseInt(idOrganismo));

					servidorOrganismo.setOrganismoId(Long.valueOf(idOrganismo));
					servicioServidor.newServidoresOrganismo(servidorOrganismo, source, accion, accionId, descripcion);
				} catch (Exception e) {
					logger.error("OrganismosAction - addServidorOrganismo:" + e);
					addActionErrorSession(this.getText(PLATAFORMADOTSE));
					return ERROR;
				}
				addActionMessageSession(this.getText(PLATAFORMADOTSE1));
			} else {
				return ERROR;
			}
		} else {
			addActionErrorSession(this.getText(PLATAFORMADOTSE));
			return ERROR;
		}
		return SUCCESS;
	}


	/**
	 * Delete organismo servicio.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deleteOrganismoServicio() throws BaseException {
		String accion = properties.getProperty(OrganismosAction.LOG_ACCION_ELIMINAR, null);
		Long accionId = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ELIMINAR, null));
		String descripcion = properties.getProperty(OrganismosAction.LOG_ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION, null);
		String source = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);
		if (getRequest().getSession().getAttribute(OrganismosAction.INFO_USER) == null) {
			return OrganismosAction.NO_USER;
		}

		if (idServicioOrganismo == null) {
			addActionErrorSession(this.getText("plataforma.servicio.servicioOrganismo.delete.error"));
		} else {
			try {
				updateServicio(Integer.parseInt(idServicio));
				updateOrganismo(Integer.parseInt(idOrganismo));

				// ////borrar planificaciones del organismo
				List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
				if (null != listaPlanificacionesOrganismos){
					for (PlanificacionBean o : listaPlanificacionesOrganismos) {
						if (o.getServicioId().equals(Integer.parseInt(idServicio))) {
							servicioPlanificacion.deletePlanificacion(o, source, accion, accionId, descripcion);
						}
					}
				}
				
				deleteServicioOrganismo(Integer.parseInt(idServicioOrganismo));
			} catch (Exception e) {
				logger.error("OrganismosAction - deleteOrganismoServicio:" + e);
			}
			addActionMessageSession(this.getText("plataforma.servicio.servicioOrganismo.delete.ok"));
		}
		return SUCCESS;
	}


	/**
	 * Delete servicio organismo.
	 *
	 * @param servicioOrganismoId the servicio organismo id
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	private void deleteServicioOrganismo(Integer servicioOrganismoId) throws BusinessException {
		String accion = properties.getProperty(OrganismosAction.LOG_ACCION_ELIMINAR, null);
		Long accionId = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ELIMINAR, null));
		String source = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_ORGANISMO_SERVICIO", null);
		ServicioOrganismosBean servidorOrganismos = new ServicioOrganismosBean();
		servidorOrganismos.setServicioOrganismoId(servicioOrganismoId);
		servicioServicio.deleteServicioOrganismos(servidorOrganismos, source, accion, accionId, descripcion);
	}

	
	/**
	 * Delete servidor organismo.
	 *
	 * @param servidorOrganismoId the servidor organismo id
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	private void deleteServidorOrganismo(Long servidorOrganismoId) throws BusinessException {
		String accion = properties.getProperty(OrganismosAction.LOG_ACCION_ELIMINAR, null);
		Long accionId = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ELIMINAR, null));
		String source = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_SERVIDOR_ORGANISMO", null);
		ServidoresOrganismosBean servidorOrganismos = new ServidoresOrganismosBean();
		servidorOrganismos.setServidorOrganismoId(servidorOrganismoId);
		servicioServidor.deleteServidorOrganismos(servidorOrganismos, source, accion, accionId, descripcion);
	}
	

	/**
	 * Update organismo.
	 *
	 * @param organismoId the organismo id
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	private void updateOrganismo(Integer organismoId) throws BusinessException {
		OrganismoBean oBean = new OrganismoBean();
		oBean.setOrganismoId(Integer.valueOf(organismoId));
		OrganismoBean orgBean = servicioOrganismo.loadOrganismo(oBean);
		servicioOrganismo.updateOrganismo(orgBean, null, null, null);
	}


	/**
	 * Update servicio.
	 *
	 * @param servicioId the servicio id
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	private void updateServicio(Integer servicioId) throws BusinessException {
		ServicioBean sBean = new ServicioBean();
		sBean.setServicioId(servicioId);
		ServicioBean servBean = servicioServicio.loadServicio(sBean);
		servicioServicio.updateServicio(servBean, null, null, null);
	}

	
	/**
	 * Update servidor.
	 *
	 * @param serv the serv
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	private void updateServidor(Long serv) throws BusinessException {
		ServidorBean sBean = new ServidorBean();
		sBean.setServidorid(serv);
		ServidorBean servBean = servicioServidor.loadServidor(sBean);
		servicioServidor.updateServidor(servBean, null, null, null);
	}
	
	
	/**
	 * Delete servidor organismo.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deleteServidorOrganismo() throws BaseException {
		String accion = properties.getProperty(OrganismosAction.LOG_ACCION_ELIMINAR, null);
		Long accionId = Long.parseLong(properties.getProperty(OrganismosAction.LOG_ACCIONID_ELIMINAR, null));
		String descripcion = properties.getProperty(OrganismosAction.LOG_ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION, null);
		String source = properties.getProperty(OrganismosAction.LOG_SOURCE_ORGANISMOS, null);
		if (getRequest().getSession().getAttribute(OrganismosAction.INFO_USER) == null) {
			return OrganismosAction.NO_USER;
		}

		if (servidorOrganismoId == null) {
			addActionErrorSession(this.getText("plataforma.servicio.servidorOrganismo.delete.error"));
		} else {
				updateOrganismo(Integer.parseInt(idOrganismo));

				// ////borrar planificaciones
				List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
				if (null != listaPlanificacionesOrganismos){
					for (PlanificacionBean o : listaPlanificacionesOrganismos) {
 	
						if (o.getServidorId().equals(Integer.valueOf(idServidor))) {
							servicioPlanificacion.deletePlanificacion(o, source, accion, accionId, descripcion);
						}
	
					}
				}
				deleteServidorOrganismo(Long.parseLong(servidorOrganismoId));
			addActionMessageSession(this.getText(PLATAFORMADOTSE2));
		}
		return SUCCESS;
	}

	/**
	 * Método que resuelve el lugar donde tiene que volver.
	 *
	 * @return volver servicio
	 */
	public String getVolverServicio() {
		String volver = null;
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + R_CONST_2 + var + R_CONST_0 + idFrom;
		} else {
			volver = "viewAplicacion.action?idAplicacion=" + servicio.getAplicacionid();
		}
		return volver;
	}

	/**
	 * Obtener volver.
	 *
	 * @return volver
	 */
	@SuppressWarnings("unchecked")
	public String getVolver() {
		String volver = "listarOrganismos.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + R_CONST_2 + var + R_CONST_0 + idFrom;
			session.put(TXTRECOVERY, volver);
		} else if (session.get(TXTRECOVERY) != null) {
			volver = (String) session.get(TXTRECOVERY);
			session.put(TXTRECOVERY, null);
		}
		return volver;
	}
	
	/**
	 * Obtener tipo estados.
	 *
	 * @return tipo estados
	 * @throws BusinessException the business exception
	 */
	public List<KeyValueObject> getTipoEstados() throws BusinessException {
		
		try {
			String options = properties.getProperty("generales.DIR3.ESTADOS_FILTRO", null);
			
			String[] opciones = options.split(SEPARADOR_OPCIONES);
			List<KeyValueObject> result = new ArrayList<>();

			for (String combo : opciones) {
				KeyValueObject option =  new KeyValueObject();
				String [] valores = combo.split(SEPARADOR_OPCIONES_VALUES);
				option.setDescripcion(valores[1]);
				option.setCodigo(valores[0]);
				result.add(option);
			}
			
			return result;
		} catch (Exception e){
			LOG.error("[CifradoServiceImpl] - getCertificados:" + e);
			throw new BusinessException(e,"errors.decode.getCertificados");	
		}
	}
	
	/**
	 * Modificar servidor.
	 *
	 * @param aplicacion new servidor
	 */
	public void setServidor(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
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
	 * Obtener servicio usuario aplicacion.
	 *
	 * @return servicio usuario aplicacion
	 */
	public ServicioUsuarioAplicacion getServicioUsuarioAplicacion() {
		return servicioUsuarioAplicacion;
	}

	/**
	 * Modificar servicio usuario aplicacion.
	 *
	 * @param servicioUsuarioAplicacion new servicio usuario aplicacion
	 */
	public void setServicioUsuarioAplicacion(ServicioUsuarioAplicacion servicioUsuarioAplicacion) {
		this.servicioUsuarioAplicacion = servicioUsuarioAplicacion;
	}

	/**
	 * Obtener detalle aplicacion.
	 *
	 * @return detalle aplicacion
	 */
	public DetalleAplicacionBean getDetalleAplicacion() {
		return detalleAplicacion;
	}

	/**
	 * Modificar detalle aplicacion.
	 *
	 * @param detalleAplicacion new detalle aplicacion
	 */
	public void setDetalleAplicacion(DetalleAplicacionBean detalleAplicacion) {
		this.detalleAplicacion = detalleAplicacion;
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
	 * Modificar servicio servidor.
	 *
	 * @param servicioServidor new servicio servidor
	 */
	public void setServicioServidor(ServicioServidor servicioServidor) {
		this.servicioServidor = servicioServidor;
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
	 * Obtener lista servicio organismos.
	 *
	 * @return lista servicio organismos
	 */
	public List<ServicioOrganismosBean> getListaServicioOrganismos() {
		return listaServicioOrganismos;
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
	 * Modificar combo servidores.
	 *
	 * @param comboServidores new combo servidores
	 */
	public void setComboServidores(List<KeyValueObject> comboServidores) {
		this.comboServidores = comboServidores;
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
	 * Obtener servidor organismo id.
	 *
	 * @return servidor organismo id
	 */
	public String getServidorOrganismoId() {
		return servidorOrganismoId;
	}

	/**
	 * Modificar servidor organismo id.
	 *
	 * @param servidorOrganismoId new servidor organismo id
	 */
	public void setServidorOrganismoId(String servidorOrganismoId) {
		this.servidorOrganismoId = servidorOrganismoId;
	}

	/**
	 * Obtener servidor organismo.
	 *
	 * @return servidor organismo
	 */
	public ServidoresOrganismosBean getServidorOrganismo() {
		return servidorOrganismo;
	}

	/**
	 * Modificar servidor organismo.
	 *
	 * @param servidorOrganismo new servidor organismo
	 */
	public void setServidorOrganismo(ServidoresOrganismosBean servidorOrganismo) {
		this.servidorOrganismo = servidorOrganismo;
	}

	/**
	 * Obtener lista servidores organismos.
	 *
	 * @return lista servidores organismos
	 */
	public List<ServidoresOrganismosBean> getListaServidoresOrganismos() {
		return listaServidoresOrganismos;
	}

	/**
	 * Modificar lista servidores organismos.
	 *
	 * @param listaServidoresOrganismos new lista servidores organismos
	 */
	public void setListaServidoresOrganismos(List<ServidoresOrganismosBean> listaServidoresOrganismos) {
		this.listaServidoresOrganismos = listaServidoresOrganismos;
	}

	/**
	 * Obtener combo servidores organismos.
	 *
	 * @return combo servidores organismos
	 */
	public List<KeyValueObject> getComboServidoresOrganismos() {
		return comboServidoresOrganismos;
	}

	/**
	 * Modificar combo servidores organismos.
	 *
	 * @param comboServidoresOrganismos new combo servidores organismos
	 */
	public void setComboServidoresOrganismos(List<KeyValueObject> comboServidoresOrganismos) {
		this.comboServidoresOrganismos = comboServidoresOrganismos;
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
	 * Obtener combo servidores plan.
	 *
	 * @return combo servidores plan
	 */
	public List<KeyValueObject> getComboServidoresPlan() {
		return comboServidoresPlan;
	}

	/**
	 * Modificar combo servidores plan.
	 *
	 * @param comboServidoresPlan new combo servidores plan
	 */
	public void setComboServidoresPlan(List<KeyValueObject> comboServidoresPlan) {
		this.comboServidoresPlan = comboServidoresPlan;
	}

	/**
	 * Obtener combo servicios plan.
	 *
	 * @return combo servicios plan
	 */
	public List<KeyValueObject> getComboServiciosPlan() {
		return comboServiciosPlan;
	}

	/**
	 * Modificar combo servicios plan.
	 *
	 * @param comboServiciosPlan new combo servicios plan
	 */
	public void setComboServiciosPlan(List<KeyValueObject> comboServiciosPlan) {
		this.comboServiciosPlan = comboServiciosPlan;
	}

	/**
	 * Obtener servidor.
	 *
	 * @return servidor
	 */
	public ServidorBean getServidor() {
		return servidor;
	}

	/**
	 * Modificar servidor.
	 *
	 * @param servidor new servidor
	 */
	public void setServidor(ServidorBean servidor) {
		this.servidor = servidor;
	}

	/**
	 * Obtener planificacion organismo.
	 *
	 * @return planificacion organismo
	 */
	public PlanificacionBean getPlanificacionOrganismo() {
		return planificacionOrganismo;
	}

	/**
	 * Modificar planificacion organismo.
	 *
	 * @param planificacionOrganismo new planificacion organismo
	 */
	public void setPlanificacionOrganismo(PlanificacionBean planificacionOrganismo) {
		this.planificacionOrganismo = planificacionOrganismo;
	}

	/**
	 * Obtener check del list organismos servicios.
	 *
	 * @return check del list organismos servicios
	 */
	public String[] getCheckDelListOrganismosServicios() {
		return checkDelListOrganismosServicios;
	}

	/**
	 * Modificar check del list organismos servicios.
	 *
	 * @param checkDelListOrganismosServicios new check del list organismos servicios
	 */
	public void setCheckDelListOrganismosServicios(String[] checkDelListOrganismosServicios) {
		this.checkDelListOrganismosServicios = checkDelListOrganismosServicios;
	}

	/**
	 * Obtener check del list servidor organismos.
	 *
	 * @return check del list servidor organismos
	 */
	public String[] getCheckDelListServidorOrganismos() {
		return checkDelListServidorOrganismos;
	}

	/**
	 * Modificar check del list servidor organismos.
	 *
	 * @param checkDelListServidorOrganismos new check del list servidor organismos
	 */
	public void setCheckDelListServidorOrganismos(String[] checkDelListServidorOrganismos) {
		this.checkDelListServidorOrganismos = checkDelListServidorOrganismos;
	}

	/**
	 * Obtener check del list planificaciones organismos.
	 *
	 * @return check del list planificaciones organismos
	 */
	public String[] getCheckDelListPlanificacionesOrganismos() {
		return checkDelListPlanificacionesOrganismos;
	}

	/**
	 * Modificar check del list planificaciones organismos.
	 *
	 * @param checkDelListPlanificacionesOrganismos new check del list planificaciones organismos
	 */
	public void setCheckDelListPlanificacionesOrganismos(String[] checkDelListPlanificacionesOrganismos) {
		this.checkDelListPlanificacionesOrganismos = checkDelListPlanificacionesOrganismos;
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
	 * Modificar id servidor.
	 *
	 * @param idServidor new id servidor
	 */
	public void setIdServidor(String idServidor) {
		this.idServidor = idServidor;
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
	 * Obtener lista organismos.
	 *
	 * @return lista organismos
	 */
	public List<OrganismoBean> getListaOrganismos() {
		return listaOrganismos;
	}

	/**
	 * Modificar lista organismos.
	 *
	 * @param listaOrganismos new lista organismos
	 */
	public void setListaOrganismos(List<OrganismoBean> listaOrganismos) {
		this.listaOrganismos = listaOrganismos;
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
	 * Obtener aplicacion.
	 *
	 * @return aplicacion
	 */
	public AplicacionBean getAplicacion() {
		return aplicacion;
	}

	/**
	 * Modificar aplicacion.
	 *
	 * @param aplicacion new aplicacion
	 */
	public void setAplicacion(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}
	
	/**
	 * Obtener combo servidores.
	 *
	 * @return combo servidores
	 */
	public List<KeyValueObject> getComboServidores() {
		return comboServidores;
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
	 * Obtener combo tipos estados.
	 *
	 * @return the comboTiposEstados
	 */
	public List<KeyValueObject> getComboTiposEstados() {
		return comboTiposEstados;
	}

	/**
	 * Modificar combo tipos estados.
	 *
	 * @param comboTiposEstados the comboTiposEstados to set
	 */
	public void setComboTiposEstados(List<KeyValueObject> comboTiposEstados) {
		this.comboTiposEstados = comboTiposEstados;
	}

	/**
	 * @return the comboOrganismosPdp
	 */
	public List<KeyValueObject> getComboOrganismosPdp() {
		return comboOrganismosPdp;
	}

	/**
	 * @param comboOrganismosPdp the comboOrganismosPdp to set
	 */
	public void setComboOrganismosPdp(List<KeyValueObject> comboOrganismosPdp) {
		this.comboOrganismosPdp = comboOrganismosPdp;
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

	/**
	 * @return the comboOrganismosHijos
	 */
	public List<KeyValueObject> getComboOrganismosHijos() {
		return comboOrganismosHijos;
	}

	/**
	 * @param comboOrganismosHijos the comboOrganismosHijos to set
	 */
	public void setComboOrganismosHijos(List<KeyValueObject> comboOrganismosHijos) {
		this.comboOrganismosHijos = comboOrganismosHijos;
	}

	/**
	 * @return the organismoPadre
	 */
	public OrganismoBean getOrganismoPadre() {
		return organismoPadre;
	}

	/**
	 * @param organismoPadre the organismoPadre to set
	 */
	public void setOrganismoPadre(OrganismoBean organismoPadre) {
		this.organismoPadre = organismoPadre;
	}


	public void setComboProveedoresSMS(List<KeyValueObject> comboProveedoresSMS) {
		this.comboProveedoresSMS = comboProveedoresSMS;
	}

	public List<KeyValueObject> getComboProveedoresSMS() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option;

		ArrayList<ProveedorSMSBean> keys = null;
		try {
			keys = (ArrayList<ProveedorSMSBean>) servicioProveedorSMS.getProveedoresSMS(1);
		} catch (BusinessException e) {
			logger.error("OrganismosAction - cargarComboProveedoresSMS:" + e);
		}

		if (keys != null && !keys.isEmpty()) {
			for (ProveedorSMSBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getProveedorSMSId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}
	
}
