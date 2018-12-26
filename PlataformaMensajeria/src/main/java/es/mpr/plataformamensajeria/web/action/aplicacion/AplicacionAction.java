package es.mpr.plataformamensajeria.web.action.aplicacion;

import java.io.Serializable;
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
public class AplicacionAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable, Serializable {

	/** Constante GENERALES_REQUEST_ATTRIBUTE_PAGESIZE. */
	private static final String GENERALES_REQUEST_ATTRIBUTE_PAGESIZE = "generales.REQUEST_ATTRIBUTE_PAGESIZE";

	/** Constante GENERALES_PAGESIZE. */
	private static final String GENERALES_PAGESIZE = "generales.PAGESIZE";

	/** Constante TABLE_ID. */
	private static final String TABLE_ID = "tableId";

	/** Constante INFO_USER. */
	private static final String INFO_USER = "infoUser";

	/** Constante LOG_ACCION_ACTUALIZAR. */
	private static final String LOG_ACCION_ACTUALIZAR = "log.ACCION_ACTUALIZAR";

	/** Constante NO_USER. */
	private static final String NO_USER = "noUser";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(AplicacionAction.class);

	/**  servicio aplicacion. */
	@Resource(name = "servicioAplicacionImpl")
	private transient ServicioAplicacion servicioAplicacion;

	/**  servicio servicio. */
	@Resource(name = "servicioServicioImpl")

	private transient ServicioServicio servicioServicio;

	/**  servicio planificacion. */
	@Resource(name = "servicioPlanificacionImpl")
	private transient ServicioPlanificacion servicioPlanificacion;

	/**  servicio usuario aplicacion. */
	@Resource(name = "servicioUsuarioAplicacionImpl")
	private transient ServicioUsuarioAplicacion servicioUsuarioAplicacion;

	/**  servicio servidor. */
	@Resource(name = "servicioServidorImpl")
	private transient ServicioServidor servicioServidor;

	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private transient PlataformaMensajeriaProperties properties;  
	

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

	/**  aplicacion. */
	private AplicacionBean aplicacion;
	
	/**  planificacion. */
	private PlanificacionBean planificacion;
	
	/**  servicio. */
	private ServicioBean servicio;

	/**  lista aplicaciones. */
	public List<AplicacionBean> listaAplicaciones = null;
	
	/**  lista servicios aplicacion. */
	private List<ServicioBean> listaServiciosAplicacion;
	
	/**  check del list. */
	private String[] checkDelList;
	
	/**  new activo. */
	private String newActivo;

	/**  combo servidores. */
	List<KeyValueObject> comboServidores = new ArrayList<>();

	/**  result count. */
	private String resultCount;
	
	/**  check password. */
	private String checkPassword;
	
	/**  recovery. */
	private String recovery = "";
	
	/**  txt recovery. */
	private String txtRecovery;
	
	/**  session. */
	@SuppressWarnings("rawtypes")
	private Map session;

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
	/////MIGRADO
	public String search() throws BaseException {
		
		if (getRequest().getSession().getAttribute(AplicacionAction.INFO_USER) == null)
			return NO_USER;
		
		int page = getPage(AplicacionAction.TABLE_ID); // Pagina a mostrar
		String order = getOrder(AplicacionAction.TABLE_ID); // Ordenar de modo ascendente o

											// descendente
		String columnSort = getColumnSort(AplicacionAction.TABLE_ID); // Columna usada para
														// ordenar

		if (aplicacion != null){
			if (aplicacion.getNombre() != null && aplicacion.getNombre().length() <= 0)
				aplicacion.setNombre(null);
			if (aplicacion.getAplicacionId() != null && aplicacion.getAplicacionId() <= 0)
				aplicacion.setAplicacionId(null);
		}

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(AplicacionAction.GENERALES_PAGESIZE, "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<AplicacionBean> result = servicioAplicacion.getAplicaciones(inicio,
				(export) ? -1 : Integer.parseInt(properties.getProperty(AplicacionAction.GENERALES_PAGESIZE, "20")), order,
				columnSort, aplicacion);
		Integer totalSize = result.getTotalList();

		listaAplicaciones = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
		if (!export) {
			getRequest().setAttribute(properties.getProperty(AplicacionAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null),
					Integer.parseInt(properties.getProperty(AplicacionAction.GENERALES_PAGESIZE, "20")));
		} else {
			getRequest().setAttribute(properties.getProperty(AplicacionAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null), totalSize);
		}
		if (listaAplicaciones != null && !listaAplicaciones.isEmpty()) {
			for (int indice = 0; indice < listaAplicaciones.size(); indice++) {

				AplicacionBean apli = listaAplicaciones.get(indice);
				apli.setNombre(StringEscapeUtils.escapeHtml(apli.getNombre()));
				apli.setDescripcion(StringEscapeUtils.escapeHtml(apli.getDescripcion()));
			}
		}
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
			return NO_USER;
		}
		int page = getPage(AplicacionAction.TABLE_ID); // Pagina a mostrar
		String order = getOrder(AplicacionAction.TABLE_ID); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort(AplicacionAction.TABLE_ID); // Columna usada para
														// ordenar

		if (aplicacion != null)
			if (aplicacion.getNombre() != null && aplicacion.getNombre().length() <= 0)
				aplicacion.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(AplicacionAction.GENERALES_PAGESIZE, "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<AplicacionBean> result = servicioAplicacion.getAplicaciones(inicio,
				(export) ? -1 : Integer.parseInt(properties.getProperty(AplicacionAction.GENERALES_PAGESIZE, "20")), order,
				columnSort, aplicacion);
		Integer totalSize = result.getTotalList();

		listaAplicaciones = result.getPageList();

		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty(AplicacionAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null),
					Integer.parseInt(properties.getProperty(AplicacionAction.GENERALES_PAGESIZE, "20")));
		} else {
			getRequest().setAttribute(properties.getProperty(AplicacionAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null), totalSize);
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
		String source = properties.getProperty("log.SOURCE_APLICACIONES", null);
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return NO_USER;
		}
		if (aplicacion != null) {

			if (newActivo != null && newActivo.equals("true")) {
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

	/**
	 * Update.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	//////MIGRADO
	public String update() throws BaseException {
		String accion = properties.getProperty(AplicacionAction.LOG_ACCION_ACTUALIZAR, null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_APLICACIONES", null);
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return NO_USER;
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

//				String oldPass = "";
//				try {
//					byte[] oldPassByte = Base64.decode(aplicacionBBDD.getPassword());
//					oldPass = new String(oldPassByte);
//				} catch (Exception e) {
//					logger.error("AplicacionAction - update:", e);
//				}
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

	/**
	 * Load.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String load() throws BaseException {
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("AplicacionAction - load:" + e);
			return NO_USER;
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

	/**
	 * Delete.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String delete() throws BaseException {
		String accionEliminar = properties.getProperty("log.ACCION_ELIMINAR", null);
		String accionActualizar = properties.getProperty(AplicacionAction.LOG_ACCION_ACTUALIZAR, null);
		Long accionIdActualizar = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		Long accionIdEliminar = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String descripcionServicio = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_SERVIDOR_SERVICIO", null);
		String source = properties.getProperty("log.SOURCE_APLICACIONES", null);
		if (getRequest().getSession().getAttribute(AplicacionAction.INFO_USER) == null)
			return NO_USER;
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

	/**
	 * Delete selected.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String deleteSelected() throws BaseException {
		String accionEliminar = properties.getProperty("log.ACCION_ELIMINAR", null);
		String accionActualizar = properties.getProperty(AplicacionAction.LOG_ACCION_ACTUALIZAR, null);
		Long accionIdActualizar = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		Long accionIdEliminar = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		String descripcionServicio = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_SERVIDOR_SERVICIO", null);
		String source = properties.getProperty("log.SOURCE_APLICACIONES", null);
		if (getRequest().getSession().getAttribute(AplicacionAction.INFO_USER) == null)
			return NO_USER;
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

	/**
	 * Load detalle aplicacion.
	 *
	 * @return the string
	 * @throws BusinessException the business exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	///MIGRADO
	public String loadDetalleAplicacion() throws BusinessException, IllegalAccessException, InvocationTargetException {
		if (getRequest().getSession().getAttribute(AplicacionAction.INFO_USER) == null)
			return NO_USER;
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

	/**
	 * Obtener detalle bean.
	 *
	 * @param detalle the detalle
	 * @param detalleApp the detalle app
	 * @param listaUsuariosAplicacion the lista usuarios aplicacion
	 * @return detalle bean
	 */
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

	/**
	 * Load sevicios aplicacion.
	 *
	 * @return the list
	 */
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

	/**
	 * Load planificaciones.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	////MIGRADO
	public String loadPlanificaciones() throws BaseException {
		if (getRequest().getSession().getAttribute(AplicacionAction.INFO_USER) == null)
			return NO_USER;
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

	/**
	 * Load combo servidores.
	 *
	 * @return the list
	 */
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

	/**
	 * Load combo servidores servicio.
	 *
	 * @param servicioId the servicio id
	 * @return the list
	 * @throws BusinessException the business exception
	 */
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

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	////MIGRADO
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare() throws Exception {
		txtRecovery = properties.getProperty("servicioAction.RECOVERY", null);
		session = (Map) ActionContext.getContext().get("session");
		recovery = (String) session.get(txtRecovery);
		listaServiciosAplicacion = loadSeviciosAplicacion();
	}

	/**
	 * Valida obligatorios.
	 *
	 * @param aplicacion2 the aplicacion 2
	 * @param isUpdate the is update
	 * @return true, if successful
	 */
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

	/**
	 * Valid passwords.
	 *
	 * @param aplicacion2 the aplicacion 2
	 * @return true, if successful
	 */
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
	 * Método que resuelve el lugar donde tiene que volver.
	 *
	 * @return volver
	 */
	@SuppressWarnings("unchecked")
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

	/**
	 * Obtener lista servidores.
	 *
	 * @return lista servidores
	 */
	public List<AplicacionBean> getListaServidores() {
		return listaAplicaciones;
	}

	/**
	 * Modificar lista servidores.
	 *
	 * @param listaAplicaciones new lista servidores
	 */
	public void setListaServidores(List<AplicacionBean> listaAplicaciones) {
		this.listaAplicaciones = listaAplicaciones;
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
	 * Obtener servidor.
	 *
	 * @return servidor
	 */
	public AplicacionBean getServidor() {
		return aplicacion;
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
	 * Modificar id aplicacion.
	 *
	 * @param idAplicacion new id aplicacion
	 */
	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
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

	/**  detalle aplicacion. */
	private DetalleAplicacionBean detalleAplicacion;

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
	 * Obtener lista servicios aplicacion.
	 *
	 * @return lista servicios aplicacion
	 */
	public List<ServicioBean> getListaServiciosAplicacion() {
		return listaServiciosAplicacion;
	}

	/**
	 * Modificar lista servicios aplicacion.
	 *
	 * @param listaServiciosAplicacion new lista servicios aplicacion
	 */
	public void setListaServiciosAplicacion(List<ServicioBean> listaServiciosAplicacion) {
		this.listaServiciosAplicacion = listaServiciosAplicacion;
	}

	/**
	 * Obtener lista aplicaciones.
	 *
	 * @return lista aplicaciones
	 */
	public List<AplicacionBean> getListaAplicaciones() {
		return listaAplicaciones;
	}

	/**
	 * Modificar lista aplicaciones.
	 *
	 * @param listaAplicaciones new lista aplicaciones
	 */
	public void setListaAplicaciones(List<AplicacionBean> listaAplicaciones) {
		this.listaAplicaciones = listaAplicaciones;
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
	 * Obtener id aplicacion.
	 *
	 * @return id aplicacion
	 */
	public String getIdAplicacion() {
		return idAplicacion;
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
	 * Obtener servicio.
	 *
	 * @return the servicio
	 */
	public ServicioBean getServicio() {
		return servicio;
	}

	/**
	 * Modificar servicio.
	 *
	 * @param servicio            the servicio to set
	 */
	public void setServicio(ServicioBean servicio) {
		this.servicio = servicio;
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
	 * @return the newActivo
	 */
	public String getNewActivo() {
		return newActivo;
	}

	/**
	 * @param newActivo the newActivo to set
	 */
	public void setNewActivo(String newActivo) {
		this.newActivo = newActivo;
	}
	
	
}
