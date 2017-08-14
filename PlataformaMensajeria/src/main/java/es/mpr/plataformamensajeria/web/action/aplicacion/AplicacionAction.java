package es.mpr.plataformamensajeria.web.action.aplicacion;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.ws.security.util.Base64;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.DetalleAplicacionBean;
import es.mpr.plataformamensajeria.beans.DetalleServicioBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;
import es.mpr.plataformamensajeria.beans.UsuarioAplicacionBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
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
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n,
 * borrado y listado de los Organismos
 * 
 * @author Altran
 * 
 */
@Controller("aplicacionAction")
@Scope("prototype")
public class AplicacionAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(AplicacionAction.class);

	@Resource(name = "servicioAplicacionImpl")
	private ServicioAplicacion servicioAplicacion;

	@Resource(name = "servicioServicioImpl")
	private ServicioServicio servicioServicio;

	@Resource(name = "servicioPlanificacionImpl")
	private ServicioPlanificacion servicioPlanificacion;

	@Resource(name = "servicioUsuarioAplicacionImpl")
	private ServicioUsuarioAplicacion servicioUsuarioAplicacion;

	@Resource(name = "servicioServidorImpl")
	private ServicioServidor servicioServidor;

	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;

	private String idAplicacion;
	private String idPlanificacion;
	private String idServicio;
	private String idServidor;
	private String idProveedorSMS;
	private String idReceptorSMS;
	private String idServidorPush;

	private AplicacionBean aplicacion;
	private PlanificacionBean planificacion;
	private ServicioBean servicio;

	public List<AplicacionBean> listaAplicaciones = null;
	private List<ServicioBean> listaServiciosAplicacion;
	private String[] checkDelList;

	List<KeyValueObject> comboServidores = new ArrayList<KeyValueObject>();

	private String resultCount;
	private String checkPassword;
	private String recovery = "";
	private String txtRecovery;
	private Map session;

	public String newSearch() throws BaseException {
		return SUCCESS;
	}

	/////MIGRADO
	public String search() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";

		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para
														// ordenar

		if (aplicacion != null)
			if (aplicacion.getNombre() != null && aplicacion.getNombre().length() <= 0)
				aplicacion.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<AplicacionBean> result = servicioAplicacion.getAplicaciones(inicio,
				(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order,
				columnSort, aplicacion);
		Integer totalSize = result.getTotalList();

		listaAplicaciones = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
		if (!export) {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
		} else {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
		}
		if (listaAplicaciones != null && !listaAplicaciones.isEmpty()) {
			for (int indice = 0; indice < listaAplicaciones.size(); indice++) {

				AplicacionBean aplicacion = listaAplicaciones.get(indice);
				aplicacion.setNombre(StringEscapeUtils.escapeHtml(aplicacion.getNombre()));
				aplicacion.setDescripcion(StringEscapeUtils.escapeHtml(aplicacion.getDescripcion()));
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
		String order = getOrder("tableId"); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para
														// ordenar

		if (aplicacion != null)
			if (aplicacion.getNombre() != null && aplicacion.getNombre().length() <= 0)
				aplicacion.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<AplicacionBean> result = servicioAplicacion.getAplicaciones(inicio,
				(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order,
				columnSort, aplicacion);
		Integer totalSize = result.getTotalList();

		listaAplicaciones = result.getPageList();

		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
		} else {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
		}
		if (listaAplicaciones != null && !listaAplicaciones.isEmpty()) {
			for (int indice = 0; indice < listaAplicaciones.size(); indice++) {

				AplicacionBean aplicacion = listaAplicaciones.get(indice);
				aplicacion.setNombre(StringEscapeUtils.escapeHtml(aplicacion.getNombre()));
				aplicacion.setDescripcion(StringEscapeUtils.escapeHtml(aplicacion.getDescripcion()));
			}
		}
		return SUCCESS;
	}

	////MIGRADO
	public String create() throws BaseException {
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty("log.SOURCE_APLICACIONES", null);
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return "noUser";
		}
		if (aplicacion != null) {

			if (aplicacion.getIsActivo() != null && aplicacion.getIsActivo().indexOf("activo") != -1) {
				aplicacion.setActivo(true);
			} else {
				aplicacion.setActivo(false);
			}
			if (!validPasswords(aplicacion) || !validaObligatorios(aplicacion, false))
				return ERROR;
			aplicacion.setPassword(Base64.encode(aplicacion.getPassword().trim().getBytes())); // Eliminamos los espacios
			
			Integer idAplicacion = servicioAplicacion.newAplicacion(aplicacion, source, accion, accionId);
			this.idAplicacion = idAplicacion.toString();

			addActionMessageSession(this.getText("plataforma.aplicacion.create.ok"));
		} else {
			addActionErrorSession(this.getText("plataforma.aplicacion.create.error"));
		}
		return SUCCESS;

	}

	//////MIGRADO
	public String update() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_APLICACIONES", null);
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return "noUser";
		}
		AplicacionBean aplicacionBBDD = null;
		if (aplicacion == null) {
			addActionErrorSession(this.getText("plataforma.aplicacion.update.error"));
		} else {
			logger.info("[AplicacionAction - Idaplicacion] valor == " + aplicacion.getAplicacionId());
			
			if (aplicacion.getAplicacionId() == null) {
				if (idAplicacion != null) {
					aplicacion.setAplicacionId(new Integer(idAplicacion));
					aplicacionBBDD = servicioAplicacion.loadAplicacion(aplicacion);
				} else {
					String idAplicacion = (String) request.getAttribute("idAplicacion");
					logger.info("[ServidoresAction - request.getAttribute('idAplicacion)' == " + idAplicacion);
					
					if (idAplicacion != null) {
						aplicacion.setId(new Long(idAplicacion));
						aplicacionBBDD = servicioAplicacion.loadAplicacion(aplicacion);
					}
				}
				logger.info("[AplicacionAction - Idaplicacion despues de setear idAplicacion] valor == "
						+ aplicacion.getAplicacionId());
				
			} else {
				aplicacionBBDD = servicioAplicacion.loadAplicacion(aplicacion);

			}
			if (aplicacionBBDD != null) {
				aplicacionBBDD.setNombre(aplicacion.getNombre());
				aplicacionBBDD.setDescripcion(aplicacion.getDescripcion());
				aplicacionBBDD.setActivo(aplicacion.getActivo());
				aplicacionBBDD.setUsuario(aplicacion.getUsuario());
				aplicacionBBDD.setRespFuncionalEmail(aplicacion.getRespFuncionalEmail());
				aplicacionBBDD.setRespFuncionalNombre(aplicacion.getRespFuncionalNombre());
				aplicacionBBDD.setRespTecnicoEmail(aplicacion.getRespTecnicoEmail());
				aplicacionBBDD.setRespTecnicoNombre(aplicacion.getRespTecnicoNombre());

				String oldPass = "";
				try {
					byte[] oldPassByte = Base64.decode(aplicacionBBDD.getPassword());
					oldPass = new String(oldPassByte);
				} catch (Exception e) {
					logger.error("AplicacionAction - update:", e);
				}
				if (null != aplicacion.getPassword()) {
					aplicacionBBDD.setPassword(Base64.encode(aplicacion.getPassword().trim().getBytes())); // Eliminamos espacios
				}
				if (!validPasswords(aplicacion)) {
					return SUCCESS;
				}

				if (!validaObligatorios(aplicacionBBDD, true)) {
					return SUCCESS;
				}
			}
			servicioAplicacion.updateAplicacion(aplicacionBBDD, source, accion, accionId);
			addActionMessageSession(this.getText("plataforma.aplicacion.update.ok"));
		}
		return SUCCESS;
	}

	////MIGRADO
	public String load() throws BaseException {
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("AplicacionAction - load:" + e);
			return "noUser";
		}
		if (idAplicacion == null)
			throw new BusinessException("EL idAplicacion recibido es nulo");
		try {
			aplicacion = new AplicacionBean();
			aplicacion.setAplicacionId(Integer.parseInt((idAplicacion)));
			aplicacion = servicioAplicacion.loadAplicacion(aplicacion);
			try {
				aplicacion.setPassword(new String(Base64.decode(aplicacion.getPassword())));
			} catch (Exception e) {
				logger.error("AplicacionAction - load:" + e);
			}
			return SUCCESS;
		} catch (NumberFormatException | BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { aplicacion
					.getAplicacionId().toString() });
			logger.error("AplicacionAction - load:" + e);
			throw new BusinessException(mensg);
		}

	}

	////MIGRADO
	public String delete() throws BaseException {
		String accionEliminar = properties.getProperty("log.ACCION_ELIMINAR", null);
		String accionActualizar = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdActualizar = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		Long accionIdEliminar = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String descripcionServicio = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_SERVIDOR_SERVICIO", null);
		String source = properties.getProperty("log.SOURCE_APLICACIONES", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idAplicacion == null) {
			addActionErrorSession(this.getText("plataforma.aplicacion.delete.error"));
		} else {
			aplicacion = new AplicacionBean();
			aplicacion.setAplicacionId(new Integer(idAplicacion));
			
			servicioAplicacion.deleteAplicacion(aplicacion, source, accionEliminar, accionIdEliminar, descripcionServicio, accionActualizar, accionIdActualizar, descripcionPlanificacion);
			addActionMessageSession(this.getText("plataforma.aplicacion.delete.ok"));
		}
		return SUCCESS;

	}

	////MIGRADO
	public String deleteSelected() throws BaseException {
		String accionEliminar = properties.getProperty("log.ACCION_ELIMINAR", null);
		String accionActualizar = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdActualizar = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		Long accionIdEliminar = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String descripcionServicio = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_SERVIDOR_SERVICIO", null);
		String source = properties.getProperty("log.SOURCE_APLICACIONES", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.aplicacion.deleteSelected.error"));

		} else {
			for (String idAplicacion : checkDelList) {
				aplicacion = new AplicacionBean();
				aplicacion.setAplicacionId(new Integer(idAplicacion));
				servicioAplicacion.deleteAplicacion(aplicacion, source, accionEliminar, accionIdEliminar, descripcionServicio, accionActualizar, accionIdActualizar, descripcionPlanificacion);
			}
			addActionMessageSession(this.getText("plataforma.aplicacion.deleteSelected.ok"));

		}
		return SUCCESS;

	}

	///MIGRADO
	public String loadDetalleAplicacion() throws BusinessException, IllegalAccessException, InvocationTargetException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		AplicacionBean detalleApp = new AplicacionBean();
		DetalleAplicacionBean detalle = new DetalleAplicacionBean();
		if (idAplicacion != null) {
			detalleApp.setAplicacionId(new Integer(idAplicacion));
			detalleApp = servicioAplicacion.loadAplicacion(detalleApp);
			List<UsuarioAplicacionBean> listaUsuariosAplicacion = servicioUsuarioAplicacion
					.getUsuarioAplicacionesByAplicacionId(new Integer(idAplicacion));
			
			detalle = getDetalleBean(detalle, detalleApp, listaUsuariosAplicacion);
			
			List<ServicioBean> listServicioBean = servicioServicio
					.getServiciosByAplicacionId(detalle.getAplicacionId().intValue());
			if (listServicioBean != null)
				for (ServicioBean ser : listServicioBean) {
					DetalleServicioBean serBean = new DetalleServicioBean();
					BeanUtils.copyProperties(serBean, ser);
					serBean.setActivo(true);//ser.getActivo());
					serBean.setFechacreacion(ser.getFechacreacion());
					serBean.setFechamodificacion(ser.getFechamodificacion());
					serBean.setFrommail(ser.getFrommail());
					serBean.setFrommailname(ser.getFrommailname());
					List<ServidoresServiciosBean> servidoresServiciosBeanList = servicioServicio
							.getServidoresServicios(ser.getServicioId().toString());
					serBean.setListaServidoresServicios(servidoresServiciosBeanList);
					List<PlanificacionBean> planificacionesList = servicioPlanificacion
							.getPlanificacionesByServicioID(ser.getServicioId());
					serBean.setListaPlanificaciones(planificacionesList);
					detalle.addDetalleServicio(serBean);
				}

			detalleAplicacion = detalle;

		}
		return SUCCESS;
	}

	private DetalleAplicacionBean getDetalleBean(DetalleAplicacionBean detalle, AplicacionBean detalleApp, List<UsuarioAplicacionBean> listaUsuariosAplicacion) {
		
		detalle.setListaUsuariosAplicacion(listaUsuariosAplicacion);
		detalle.setAplicacionId(detalleApp.getAplicacionId().longValue());
		detalle.setNombre(detalleApp.getNombre());
		detalle.setDescripcion(detalleApp.getDescripcion());
		detalle.setUsuario(detalleApp.getUsuario());
		detalle.setPassword(detalleApp.getPassword());
		detalle.setActivo(detalleApp.getActivo());
		detalle.setFechacreacion(detalleApp.getFechacreacion());
		detalle.setFechamodificacion(detalleApp.getFechamodificacion());
		
		return detalle;
	}

	////MIGRADO
	private List<ServicioBean> loadSeviciosAplicacion() {
		List<ServicioBean> lista = null;
		if (idAplicacion != null && idAplicacion.length() > 0) {
			try {
				lista = servicioServicio.getServiciosByAplicacionId(new Integer(idAplicacion));
			} catch (NumberFormatException e) {
				logger.error("AplicacionAction - loadSeviciosAplicacion:" + e);
			} catch (BusinessException e) {
				logger.error("AplicacionAction - loadSeviciosAplicacion:" + e);
			}
		} else if (aplicacion != null && aplicacion.getAplicacionId() != null) {
			try {
				lista = servicioServicio.getServiciosByAplicacionId(new Long(aplicacion.getAplicacionId()).intValue());
			} catch (NumberFormatException e) {
				logger.error("AplicacionAction - loadSeviciosAplicacion:" + e);
			} catch (BusinessException e) {
				logger.error("AplicacionAction - loadSeviciosAplicacion:" + e);
			}
		}
		return lista;
	}

	////MIGRADO
	public String loadPlanificaciones() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idPlanificacion == null)
			throw new BusinessException("EL idPlanificacion recibido es nulo");
		try {
			planificacion = new PlanificacionBean();
			planificacion.setPlanificacionId(new Integer(idPlanificacion));
			planificacion = servicioPlanificacion.loadPlanificacion(planificacion);
			servicio = new ServicioBean();
			if (planificacion.getServicioId() != null) {
				servicio.setId(planificacion.getServicioId());
				servicio = servicioServicio.loadServicio(servicio);
			}
			String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
			if (rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)) {
				comboServidores = loadComboServidores();
			} else if (rolUsuario.equals(PlataformaMensajeriaUtil.ROL_PROPIETARIO)) {
				comboServidores = loadComboServidoresServicio(servicio.getServicioId());
			}
			return SUCCESS;
		} catch (NumberFormatException | BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { planificacion
					.getPlanificacionId().toString() });
			logger.error("AplicacionAction - loadPlanificaciones:" + e);
			throw new BusinessException(mensg);
		} 

	}

	////MIGRADO
	private List<KeyValueObject> loadComboServidores() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option;
		ArrayList<ServidorBean> keys = null;
		try {
			if (planificacion != null && planificacion.getTipoPlanificacionId() != null
					&& planificacion.getTipoPlanificacionId() == 1) {
				keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresByTipoPlanificacion("2");
			} else if (planificacion != null && planificacion.getTipoPlanificacionId() != null
					&& planificacion.getTipoPlanificacionId() == 2) {
				keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresByTipoPlanificacion("1");
			} else if (planificacion != null && planificacion.getTipoPlanificacionId() != null
					&& planificacion.getTipoPlanificacionId() == 3) {
				keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresByTipoPlanificacion("3");
			} else if (planificacion != null && planificacion.getTipoPlanificacionId() != null
					&& planificacion.getTipoPlanificacionId() == 4) {
				keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresByTipoPlanificacion("4");
			}
		} catch (BusinessException e) {
			logger.error("AplicacionAction - loadComboServidores:" + e);
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

	////MIGRADO
	private List<KeyValueObject> loadComboServidoresServicio(Integer servicioId) throws BusinessException {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option;

		ArrayList<ServidoresServiciosBean> keys = null;

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

	////MIGRADO
	@Override
	public void prepare() throws Exception {
		txtRecovery = properties.getProperty("servicioAction.RECOVERY", null);
		session = (Map) ActionContext.getContext().get("session");
		recovery = (String) session.get(txtRecovery);
		listaServiciosAplicacion = loadSeviciosAplicacion();
	}

	////MIGRADO
	private boolean validaObligatorios(AplicacionBean aplicacion2, boolean isUpdate) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getNombre())) {
			addActionErrorSession(this.getText("plataforma.aplicacion.field.nombre.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getDescripcion())) {
			addActionErrorSession(this.getText("plataforma.aplicacion.field.descripcion.error"));
			sw = false;
		}
		if (!isUpdate && servicioAplicacion.existeUsuario(aplicacion2.getUsuario())) {
			addActionErrorSession("El nombre de usuario '" + aplicacion2.getUsuario()
					+ "' ya existe. Por favor seleccione otro.");
			sw = false;
		}

		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getRespFuncionalNombre())) {
			addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.funcional.nombre"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getRespFuncionalEmail())) {
			addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.funcional.email"));
			sw = false;
		}

		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getRespTecnicoNombre())) {
			addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.tecnico.nombre"));
			sw = false;
		}

		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getRespTecnicoEmail())) {
			addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.tecnico.email"));
			sw = false;
		}

		return sw;
	}

	////MIGRADO
	private boolean validPasswords(AplicacionBean aplicacion2) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getPassword())) {
			addFieldErrorSession(this.getText("plataforma.aplicacion.field.password"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getRePassword())
				&& !PlataformaMensajeriaUtil.isEmpty(checkPassword) && checkPassword.equals("true")) {
			addFieldErrorSession(this.getText("plataforma.aplicacion.field.rePassword"));
			sw = false;
		}
		// Eliminamos los espacios
		if (!PlataformaMensajeriaUtil.isEmpty(aplicacion2.getPassword())
				&& !PlataformaMensajeriaUtil.isEmpty(aplicacion2.getRePassword())
				&& !(aplicacion2.getPassword().trim().equals(aplicacion2.getRePassword().trim()))
				&& !PlataformaMensajeriaUtil.isEmpty(checkPassword) && checkPassword.equals("true")) {
			addFieldErrorSession(this.getText("plataforma.aplicacion.passwords.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getUsuario())) {
			addFieldErrorSession(this.getText("plataforma.aplicacion.field.usuario"));
			sw = false;
		}
		return sw;

	}

	/**
	 * Método que resuelve el lugar donde tiene que volver
	 */
	public String getVolver() {
		String volver = "buscarAplicaciones.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
			session.put(txtRecovery, volver);
		} else if (session.get(txtRecovery) != null) {
			volver = (String) session.get(txtRecovery);
			session.put(txtRecovery, null);
		}
		return volver;
	}

	public List<AplicacionBean> getListaServidores() {
		return listaAplicaciones;
	}

	public void setListaServidores(List<AplicacionBean> listaAplicaciones) {
		this.listaAplicaciones = listaAplicaciones;
	}

	public ServicioAplicacion getServicioAplicacion() {
		return servicioAplicacion;
	}

	public void setServicioAplicacion(ServicioAplicacion servicioAplicacion) {
		this.servicioAplicacion = servicioAplicacion;
	}

	public AplicacionBean getServidor() {
		return aplicacion;
	}

	public void setServidor(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getIdServidor() {
		return idServidor;
	}

	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
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

	public List<ServicioBean> getListaServiciosAplicacion() {
		return listaServiciosAplicacion;
	}

	public void setListaServiciosAplicacion(List<ServicioBean> listaServiciosAplicacion) {
		this.listaServiciosAplicacion = listaServiciosAplicacion;
	}

	public List<AplicacionBean> getListaAplicaciones() {
		return listaAplicaciones;
	}

	public void setListaAplicaciones(List<AplicacionBean> listaAplicaciones) {
		this.listaAplicaciones = listaAplicaciones;
	}

	public AplicacionBean getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getIdAplicacion() {
		return idAplicacion;
	}

	public PlanificacionBean getPlanificacion() {
		return planificacion;
	}

	public void setPlanificacion(PlanificacionBean planificacion) {
		this.planificacion = planificacion;
	}

	public String getIdPlanificacion() {
		return idPlanificacion;
	}

	public void setIdPlanificacion(String idPlanificacion) {
		this.idPlanificacion = idPlanificacion;
	}

	/**
	 * @return the servicio
	 */
	public ServicioBean getServicio() {
		return servicio;
	}

	/**
	 * @param servicio
	 *            the servicio to set
	 */
	public void setServicio(ServicioBean servicio) {
		this.servicio = servicio;
	}

	public List<KeyValueObject> getComboServidores() {
		return comboServidores;
	}
}
