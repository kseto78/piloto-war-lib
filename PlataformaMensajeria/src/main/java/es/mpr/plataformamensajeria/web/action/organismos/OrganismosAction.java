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

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.DetalleAplicacionBean;
import es.mpr.plataformamensajeria.beans.DetalleServicioBean;
import es.mpr.plataformamensajeria.beans.OrganismoBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServicioOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidoresOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;
import es.mpr.plataformamensajeria.beans.UsuarioAplicacionBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
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

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(OrganismosAction.class);
	
	@Resource(name="servicioOrganismoImpl")
	private ServicioOrganismo servicioOrganismo;
	
	@Resource(name="servicioServicioImpl")
	private ServicioServicio servicioServicio;
	
	@Resource(name="servicioPlanificacionImpl")
	private ServicioPlanificacion servicioPlanificacion;
	
	@Resource(name="servicioUsuarioAplicacionImpl")
	private ServicioUsuarioAplicacion servicioUsuarioAplicacion;
	
	@Resource(name="servicioServidorImpl")
	private ServicioServidor servicioServidor;
	
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;
	
	
	private OrganismoBean organismo;
	private ServidoresOrganismosBean servidorOrganismo;
	private AplicacionBean aplicacion;
	private ServicioOrganismosBean servicioOrganismos;
	private ServicioBean servicio;
	private ServidorBean servidor;
	private PlanificacionBean planificacionOrganismo;
	
	public List<OrganismoBean> listaOrganismos = null;
	public List<ServicioOrganismosBean> listaServicioOrganismos = null;
	public List<ServidoresOrganismosBean> listaServidoresOrganismos = null;
	public List<PlanificacionBean> listaPlanificacionesServicio = null;
	
	List<KeyValueObject> comboServicioOrganismos = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServidoresOrganismos = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServidoresPlan = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServiciosPlan = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServidores = new ArrayList<KeyValueObject>();
	
	private String[] checkDelList;
	private String[] checkDelListOrganismosServicios;
	private String[] checkDelListServidorOrganismos;
	private String[] checkDelListPlanificacionesOrganismos;
	
	private String idOrganismo;
	private String idServicioOrganismo;
	private String servidorOrganismoId;
	private String idPlanificacion;
	private String idServicio;
	private String idServidor;
	private String idProveedorSMS;
	private String idReceptorSMS;
	private String idServidorPush;
	
	private String resultCount;
	private String checkPassword;
	private String recovery = "";
	private Map session;
	private static final String RECOVERY = "recovery";


	public String newSearch() throws BaseException {
		return SUCCESS;
	}

	///MIGRADO
	public String search() throws BaseException {

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";

		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para ordenar

		if (organismo != null)
			if (organismo.getNombre() != null && organismo.getNombre().length() <= 0)
				organismo.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<OrganismoBean> result = servicioOrganismo.getOrganismos(inicio, (export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order, columnSort, organismo);
		Integer totalSize = result.getTotalList();

		listaOrganismos = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
		if (!export) {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), 
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
		} else {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), 
					totalSize);
		}

		if (listaOrganismos != null && !listaOrganismos.isEmpty()) {
			for (int indice = 0; indice < listaOrganismos.size(); indice++) {

				OrganismoBean organismo = new OrganismoBean();
				organismo = listaOrganismos.get(indice);
				organismo.setNombre(StringEscapeUtils.escapeHtml(organismo.getNombre()));
				organismo.setDescripcion(StringEscapeUtils.escapeHtml(organismo.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	///MIGRADO
	public String execute() throws BaseException {

		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return "noUser";
		}
		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para ordenar

		if (organismo != null)
			if (organismo.getNombre() != null && organismo.getNombre().length() <= 0)
				organismo.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<OrganismoBean> result = servicioOrganismo.getOrganismos(inicio, (export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order, columnSort, organismo);
		Integer totalSize = result.getTotalList();

		listaOrganismos = result.getPageList();

		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
		} else {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
		}
		if (listaOrganismos != null && !listaOrganismos.isEmpty()) {
			for (int indice = 0; indice < listaOrganismos.size(); indice++) {

				OrganismoBean organismo = listaOrganismos.get(indice);
				organismo.setNombre(StringEscapeUtils.escapeHtml(organismo.getNombre()));
				organismo.setDescripcion(StringEscapeUtils.escapeHtml(organismo.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	///MIGRADO
	public String create() throws BaseException {
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("OrganismosAction - create:" + e);
			return "noUser";
		}
		if (organismo != null) {
			if (organismo.getIsActivo() != null && organismo.getIsActivo().contains("inactivo")) {
				organismo.setActivo(false);
			} else {
				organismo.setActivo(true);
			}
			if (!validaObligatorios(organismo, true)) {
				return ERROR;
			}
			Integer id = servicioOrganismo.newOrganismo(organismo, source, accion, accionId);
			this.idOrganismo = id.toString();

			addActionMessageSession(this.getText("plataforma.organismo.create.ok"));
		} else {
			addActionErrorSession(this.getText("plataforma.organismo.create.error"));
		}

		return SUCCESS;

	}

	////MIGRADO
	private boolean validaObligatorios(OrganismoBean aplicacion2, boolean isUpdate) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getDir3())) {
			addActionErrorSession(this.getText("plataforma.organismo.field.DIR3.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getNombre())) {
			addActionErrorSession(this.getText("plataforma.organismo.field.nombre.error"));
			sw = false;
		}
		
		return sw;
	}

	///MIGRADO
	public String update() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("OrganismosAction - update:" + e);
			return "noUser";
		}
		OrganismoBean organismoBBDD = null;
		if (organismo == null) {
			addActionErrorSession(this.getText("plataforma.organismo.update.error"));

		} else {
			
			if (organismo.getOrganismoId() == null) {
				if (idOrganismo != null) {
					organismo.setOrganismoId(new Integer(idOrganismo));
					organismoBBDD = servicioOrganismo.loadOrganismo(organismo);
				} else {
					String id = (String) request.getAttribute("idOrganismo");
					
					if (id != null) {
						aplicacion.setId(new Long(id));
						organismoBBDD = servicioOrganismo.loadOrganismo(organismo);
					}
				}
			} else {
				organismoBBDD = servicioOrganismo.loadOrganismo(organismo);

			}
			if (organismoBBDD != null) {
				organismoBBDD.setDir3(organismo.getDir3());
				organismoBBDD.setNombre(organismo.getNombre());
				organismoBBDD.setDescripcion(organismo.getDescripcion());
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

	///MIGRADO
	public String load() throws BaseException {
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("OrganismosAction - load:" + e);
			return "noUser";
		}
		if (idOrganismo == null)
			throw new BusinessException("EL idOrganismo recibido es nulo");
		try {
			organismo = new OrganismoBean();
			organismo.setOrganismoId(new Integer(idOrganismo));
			organismo = servicioOrganismo.loadOrganismo(organismo);

			return SUCCESS;
		} catch (NumberFormatException | BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { organismo.getOrganismoId().toString() });
			logger.error("OrganismosAction - load:" + e);
			throw new BusinessException(mensg);
		} 

	}
	
	///MIGRADO
	public String delete() throws BaseException {
		String accionPlanificacion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String sourcePlanificacion = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
				
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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

	///MIGRADO
	public String deleteSelected() throws BaseException {
		String accionPlanificacion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String sourcePlanificacion = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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

	/////MIGRADO
	public String deleteOrganismoServicioSelected() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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
			addActionMessageSession(this.getText("plataforma.servicio.servidorOrganismo.delete.ok"));

		}
		return SUCCESS;
	}

    ///MIGRADO
	public String deleteServidorOrganismosSelected() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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
			addActionMessageSession(this.getText("plataforma.servicio.servidorOrganismo.delete.ok"));

		}
		return SUCCESS;
	}

	///MIGRADO
	public String deletePlanificacionesOrganismoSelected() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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

	
	///MIGRADO
	public String loadDetalleOrganismo() throws BusinessException, IllegalAccessException, InvocationTargetException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		OrganismoBean detalleApp = new OrganismoBean();
		DetalleAplicacionBean detalle = new DetalleAplicacionBean();
		if (idOrganismo != null) {
			detalleApp.setOrganismoId(new Integer(idOrganismo));
			detalleApp = servicioOrganismo.loadOrganismo(detalleApp);
			Date defaultValue = null;
			List<UsuarioAplicacionBean> listaUsuariosAplicacion = servicioUsuarioAplicacion.getUsuarioAplicacionesByAplicacionId(new Integer(idOrganismo));
			detalle.setListaUsuariosAplicacion(listaUsuariosAplicacion);
			DateConverter converter = new DateConverter(defaultValue);
			ConvertUtils.register(converter, java.util.Date.class);
			BeanUtils.copyProperties(detalle, detalleApp);
			detalle.setActivo(true);//detalleApp.getActivo());
			detalle.setFechacreacion(detalleApp.getFechacreacion());
			detalle.setFechamodificacion(detalleApp.getFechamodificacion());
			List<ServicioBean> listServicioBean = servicioServicio.getServiciosByAplicacionId(detalle.getAplicacionId().intValue());
			if (listServicioBean != null)
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

			detalleAplicacion = detalle;

		}
		return SUCCESS;
	}

	///MIGRADO
	private List<ServicioOrganismosBean> loadSeviciosOrganismo() {
		List<ServicioOrganismosBean> lista = null;
		if (idOrganismo != null && idOrganismo.length() > 0) {
			try {
				lista = servicioServicio.getOrganismoServicio(idOrganismo);
			} catch (NumberFormatException | BusinessException e) {
				logger.error("OrganismosAction - loadSeviciosOrganismo:" + e);
			} 
		} else if (organismo != null && organismo.getOrganismoId() != null) {
			try {
				lista = servicioServicio.getOrganismoServicio(String.valueOf(organismo.getOrganismoId()));
			} catch (NumberFormatException | BusinessException e) {
				logger.error("OrganismosAction - loadSeviciosOrganismo:" + e);
			} 
		}
		return lista;
	}

	///MIGRADO
	private List<ServidoresOrganismosBean> loadServidoresOrganismos() {
		List<ServidoresOrganismosBean> lista = null;
		if (idOrganismo != null && idOrganismo.length() > 0) {
			try {
				lista = servicioServidor.getServidorOrganismo(idOrganismo);
			} catch (NumberFormatException | BusinessException e) {
				logger.error("OrganismosAction - loadSeviciosOrganismo:" + e);
			} 
		} else if (organismo != null && organismo.getOrganismoId() != null) {
			try {
				lista = servicioServidor.getServidorOrganismo(String.valueOf(organismo.getOrganismoId()));
			} catch (NumberFormatException |  BusinessException e) {
				logger.error("OrganismosAction - loadSeviciosOrganismo:" + e);
			} 
		}
		return lista;
	}

	///MIGRADO
	public String addPlanificacionOrganismo() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PLANIFICACION", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";

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

				if (valido == 1) {
					planificacionOrganismo.setActivo(true);

					servicioPlanificacion.newPlanificacion(planificacionOrganismo, source, accion, accionId, descripcion);
					
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
		} else {
			return SUCCESS;
		}

		return SUCCESS;
	}

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

		}
		return sw;
	}

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

	
    ///MIGRADO
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare() throws Exception {
		session = (Map) ActionContext.getContext().get("session");
		recovery = (String) session.get(RECOVERY);

		listaServicioOrganismos = loadSeviciosOrganismo();
		listaServidoresOrganismos = loadServidoresOrganismos();

		if (idOrganismo != null) {
			if (organismo == null)
				load();
			comboServicioOrganismos = cargarComboServicioOrganismos();
			comboServidoresOrganismos = cargarComboServidoresOrganismos();
			comboServidoresPlan = cargarComboServidoresPlan();
			comboServiciosPlan = cargarComboServiciosPlan();
			listaPlanificacionesServicio = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
		}
	}

	///MIGRADO
	public String deletePlanificacionOrganismo() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
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

	///MIGRADO
	private List<KeyValueObject> cargarComboServicioOrganismos() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option;

		ArrayList<ServicioBean> keys = null;
		try {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServiciosMultiorganismo();
		} catch (BusinessException e) {
			logger.error("OrganismosAction - cargarComboServicioOrganismos:" + e);
		}

		if (keys != null && !keys.isEmpty())
			for (ServicioBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getServicioId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		return result;
	}

	///MIGRADO
	private List<KeyValueObject> cargarComboServidoresOrganismos() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option;

		ArrayList<ServidorBean> keys = null;
		try {
			keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresYProveedores(properties.getProperty("generales.TIPO_SERVIDOR_SMS", null));
		} catch (BusinessException e) {
			logger.error("OrganismosAction - cargarComboServicioOrganismos:" + e);
		}

		if (keys != null && !keys.isEmpty())
			for (ServidorBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getServidorid().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		return result;
	}

	///MIGRADO
	private List<KeyValueObject> cargarComboServidoresPlan() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option;

		ArrayList<ServidoresOrganismosBean> keys = null;
		try {
			keys = (ArrayList<ServidoresOrganismosBean>) servicioServidor.getServidorOrganismo(idOrganismo);
		} catch (BusinessException e) {
			logger.error("OrganismosAction - cargarComboServidoresPlan:" + e);
		}

		if (keys != null && !keys.isEmpty())
			for (ServidoresOrganismosBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getServidorId().toString());
				option.setDescripcion(key.getNombreServidor());
				result.add(option);
			}
		return result;
	}

	///MIGRADO
	private List<KeyValueObject> cargarComboServiciosPlan() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option;

		ArrayList<ServicioOrganismosBean> keys = null;
		try {
			keys = (ArrayList<ServicioOrganismosBean>) servicioServicio.getOrganismoServicio(idOrganismo);
		} catch (BusinessException e) {
			logger.error("OrganismosAction - cargarComboServiciosPlan:" + e);
		}

		if (keys != null && !keys.isEmpty())
			for (ServicioOrganismosBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getServicioId().toString());
				option.setDescripcion(key.getNombreServicio());
				result.add(option);
			}
		return result;
	}

	///MIGRADO
	@Transactional
	public String addOrganismoServicios() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_SERVICIO", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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

	//@Transactional(noRollbackFor = Exception.class)
	///MIGRADO
	public String addServidorOrganismo() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_SERVIDOR", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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
					addActionErrorSession(this.getText("plataforma.servicio.servicioOrganismo.add.error"));
					return ERROR;
				}
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


	///MIGRADO
	public String deleteOrganismoServicio() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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


	///MIGRADO
	private void deleteServicioOrganismo(Integer servicioOrganismoId) throws BusinessException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_ORGANISMO_SERVICIO", null);
		ServicioOrganismosBean servidorOrganismos = new ServicioOrganismosBean();
		servidorOrganismos.setServicioOrganismoId(servicioOrganismoId);
		servicioServicio.deleteServicioOrganismos(servidorOrganismos, source, accion, accionId, descripcion);
	}

	
	///MIGRADO
	private void deleteServidorOrganismo(Long servidorOrganismoId) throws BusinessException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_SERVIDOR_ORGANISMO", null);
		ServidoresOrganismosBean servidorOrganismos = new ServidoresOrganismosBean();
		servidorOrganismos.setServidorOrganismoId(servidorOrganismoId);
		servicioServidor.deleteServidorOrganismos(servidorOrganismos, source, accion, accionId, descripcion);
	}
	

	///MIGRADO
	private void updateOrganismo(Integer organismoId) throws BusinessException {
		OrganismoBean oBean = new OrganismoBean();
		oBean.setOrganismoId(Integer.valueOf(organismoId));
		OrganismoBean orgBean = servicioOrganismo.loadOrganismo(oBean);
		servicioOrganismo.updateOrganismo(orgBean, null, null, null);
	}


	///MIGRADO
	private void updateServicio(Integer servicioId) throws BusinessException {
		ServicioBean sBean = new ServicioBean();
		sBean.setServicioId(servicioId);
		ServicioBean servBean = servicioServicio.loadServicio(sBean);
		servicioServicio.updateServicio(servBean, null, null, null);
	}

	
	///MIGRADO
	private void updateServidor(Long serv) throws BusinessException {
		ServidorBean sBean = new ServidorBean();
		sBean.setServidorid(serv);
		ServidorBean servBean = servicioServidor.loadServidor(sBean);
		servicioServidor.updateServidor(servBean, null, null, null);
	}
	
	
	///MIGRADO
	public String deleteServidorOrganismo() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String source = properties.getProperty("log.SOURCE_ORGANISMOS", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
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
			addActionMessageSession(this.getText("plataforma.servicio.servidorOrganismo.delete.ok"));
		}
		return SUCCESS;
	}

	/**
	 * Método que resuelve el lugar donde tiene que volver
	 */
	public String getVolverServicio() {
		String volver = "editServicio.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
		} else {
			volver = "viewAplicacion.action?idAplicacion=" + servicio.getAplicacionid();
		}
		return volver;
	}

	@SuppressWarnings("unchecked")
	public String getVolver() {
		String volver = "buscarOrganismos.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
			session.put(RECOVERY, volver);
		} else if (session.get(RECOVERY) != null) {
			volver = (String) session.get(RECOVERY);
			session.put(RECOVERY, null);
		}
		return volver;
	}
	
	public void setServidor(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getIdServidor() {
		return idServidor;
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

	public ServicioUsuarioAplicacion getServicioUsuarioAplicacion() {
		return servicioUsuarioAplicacion;
	}

	public void setServicioUsuarioAplicacion(ServicioUsuarioAplicacion servicioUsuarioAplicacion) {
		this.servicioUsuarioAplicacion = servicioUsuarioAplicacion;
	}

	private DetalleAplicacionBean detalleAplicacion;

	public DetalleAplicacionBean getDetalleAplicacion() {
		return detalleAplicacion;
	}

	public void setDetalleAplicacion(DetalleAplicacionBean detalleAplicacion) {
		this.detalleAplicacion = detalleAplicacion;
	}

	public ServicioPlanificacion getServicioPlanificacion() {
		return servicioPlanificacion;
	}

	public void setServicioPlanificacion(ServicioPlanificacion servicioPlanificacion) {
		this.servicioPlanificacion = servicioPlanificacion;
	}

	public ServicioServicio getServicioServicio() {
		return servicioServicio;
	}

	public void setServicioServicio(ServicioServicio servicioServicio) {
		this.servicioServicio = servicioServicio;
	}

	public void setServicioServidor(ServicioServidor servicioServidor) {
		this.servicioServidor = servicioServidor;
	}

	public ServicioServidor getServicioServidor() {
		return servicioServidor;
	}



	public String getIdServidorPush() {
		return idServidorPush;
	}

	public void setIdServidorPush(String idServidorPush) {
		this.idServidorPush = idServidorPush;
	}

	public ServicioOrganismo getServicioOrganismo() {
		return servicioOrganismo;
	}

	public void setServicioOrganismo(ServicioOrganismo servicioOrganismo) {
		this.servicioOrganismo = servicioOrganismo;
	}

	public List<ServicioOrganismosBean> getListaServicioOrganismos() {
		return listaServicioOrganismos;
	}

	public void setListaServicioOrganismos(List<ServicioOrganismosBean> listaServicioOrganismos) {
		this.listaServicioOrganismos = listaServicioOrganismos;
	}

	public ServicioOrganismosBean getServicioOrganismos() {
		return servicioOrganismos;
	}

	public void setServicioOrganismos(ServicioOrganismosBean servicioOrganismos) {
		this.servicioOrganismos = servicioOrganismos;
	}

	public List<KeyValueObject> getComboServicioOrganismos() {
		return comboServicioOrganismos;
	}

	public void setComboServicioOrganismos(List<KeyValueObject> comboServicioOrganismos) {
		this.comboServicioOrganismos = comboServicioOrganismos;
	}

	public ServicioBean getServicio() {
		return servicio;
	}

	public void setServicio(ServicioBean servicio) {
		this.servicio = servicio;
	}

	public void setComboServidores(List<KeyValueObject> comboServidores) {
		this.comboServidores = comboServidores;
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

	public String getServidorOrganismoId() {
		return servidorOrganismoId;
	}

	public void setServidorOrganismoId(String servidorOrganismoId) {
		this.servidorOrganismoId = servidorOrganismoId;
	}

	public ServidoresOrganismosBean getServidorOrganismo() {
		return servidorOrganismo;
	}

	public void setServidorOrganismo(ServidoresOrganismosBean servidorOrganismo) {
		this.servidorOrganismo = servidorOrganismo;
	}

	public List<ServidoresOrganismosBean> getListaServidoresOrganismos() {
		return listaServidoresOrganismos;
	}

	public void setListaServidoresOrganismos(List<ServidoresOrganismosBean> listaServidoresOrganismos) {
		this.listaServidoresOrganismos = listaServidoresOrganismos;
	}

	public List<KeyValueObject> getComboServidoresOrganismos() {
		return comboServidoresOrganismos;
	}

	public void setComboServidoresOrganismos(List<KeyValueObject> comboServidoresOrganismos) {
		this.comboServidoresOrganismos = comboServidoresOrganismos;
	}

	public List<PlanificacionBean> getListaPlanificacionesServicio() {
		return listaPlanificacionesServicio;
	}

	public void setListaPlanificacionesServicio(List<PlanificacionBean> listaPlanificacionesServicio) {
		this.listaPlanificacionesServicio = listaPlanificacionesServicio;
	}

	public List<KeyValueObject> getComboServidoresPlan() {
		return comboServidoresPlan;
	}

	public void setComboServidoresPlan(List<KeyValueObject> comboServidoresPlan) {
		this.comboServidoresPlan = comboServidoresPlan;
	}

	public List<KeyValueObject> getComboServiciosPlan() {
		return comboServiciosPlan;
	}

	public void setComboServiciosPlan(List<KeyValueObject> comboServiciosPlan) {
		this.comboServiciosPlan = comboServiciosPlan;
	}

	public ServidorBean getServidor() {
		return servidor;
	}

	public void setServidor(ServidorBean servidor) {
		this.servidor = servidor;
	}

	public PlanificacionBean getPlanificacionOrganismo() {
		return planificacionOrganismo;
	}

	public void setPlanificacionOrganismo(PlanificacionBean planificacionOrganismo) {
		this.planificacionOrganismo = planificacionOrganismo;
	}

	public String[] getCheckDelListOrganismosServicios() {
		return checkDelListOrganismosServicios;
	}

	public void setCheckDelListOrganismosServicios(String[] checkDelListOrganismosServicios) {
		this.checkDelListOrganismosServicios = checkDelListOrganismosServicios;
	}

	public String[] getCheckDelListServidorOrganismos() {
		return checkDelListServidorOrganismos;
	}

	public void setCheckDelListServidorOrganismos(String[] checkDelListServidorOrganismos) {
		this.checkDelListServidorOrganismos = checkDelListServidorOrganismos;
	}

	public String[] getCheckDelListPlanificacionesOrganismos() {
		return checkDelListPlanificacionesOrganismos;
	}

	public void setCheckDelListPlanificacionesOrganismos(String[] checkDelListPlanificacionesOrganismos) {
		this.checkDelListPlanificacionesOrganismos = checkDelListPlanificacionesOrganismos;
	}
	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}
	public String getIdProveedorSMS() {
		return idProveedorSMS;
	}

	public void setIdProveedorSMS(String idProveedorSMS) {
		this.idProveedorSMS = idProveedorSMS;
	}

	public String getIdReceptorSMS() {
		return idReceptorSMS;
	}

	public void setIdReceptorSMS(String idReceptorSMS) {
		this.idReceptorSMS = idReceptorSMS;
	}

	public void setIdServidor(String idServidor) {
		this.idServidor = idServidor;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	public List<OrganismoBean> getListaOrganismos() {
		return listaOrganismos;
	}

	public void setListaOrganismos(List<OrganismoBean> listaOrganismos) {
		this.listaOrganismos = listaOrganismos;
	}

	public OrganismoBean getOrganismo() {
		return organismo;
	}

	public void setOrganismo(OrganismoBean organismo) {
		this.organismo = organismo;
	}

	public AplicacionBean getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}
	public List<KeyValueObject> getComboServidores() {
		return comboServidores;
	}

	public String getIdPlanificacion() {
		return idPlanificacion;
	}

	public void setIdPlanificacion(String idPlanificacion) {
		this.idPlanificacion = idPlanificacion;
	}
}
