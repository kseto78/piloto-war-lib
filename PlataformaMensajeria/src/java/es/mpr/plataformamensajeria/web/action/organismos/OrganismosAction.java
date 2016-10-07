package es.mpr.plataformamensajeria.web.action.organismos;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.security.context.SecurityContextHolder;
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
public class OrganismosAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	private static final long serialVersionUID = 1L;

	private static final String REQUEST_ATTRIBUTE_TOTALSIZE = "totalSize";
	private static final String REQUEST_ATTRIBUTE_PAGESIZE = "pageSize";
	private static final String RECOVERY = "recovery";

	private static final String servidortipoSMS = "1";

	private static final Integer PAGESIZE = new Integer(20); // Elementos por pagina

	public List<OrganismoBean> listaOrganismos = null;
	private ServicioOrganismo servicioOrganismo;
	private ServicioServicio servicioServicio;
	private ServicioPlanificacion servicioPlanificacion;
	private ServicioUsuarioAplicacion servicioUsuarioAplicacion;
	private ServicioServidor servicioServidor;
	private String idOrganismo;
	private String idServicioOrganismo;

	private String servidorOrganismoId;
	private String resultCount;
	private String[] checkDelList;
	private String[] checkDelListOrganismosServicios;
	private String[] checkDelListServidorOrganismos;
	private String[] checkDelListPlanificacionesOrganismos;
	private String checkPassword;
	private String recovery = "";
	private Map session;

	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	private OrganismoBean organismo;
	private ServidoresOrganismosBean servidorOrganismo;
	private AplicacionBean aplicacion;
	private ServicioOrganismosBean servicioOrganismos;
	private ServicioBean servicio;
	private ServidorBean servidor;
	private PlanificacionBean planificacionOrganismo;
	private String idPlanificacion;
	private String idServicio;
	private String idServidor;
	private String idProveedorSMS;
	private String idReceptorSMS;
	private String idServidorPush;
	List<KeyValueObject> comboServicioOrganismos = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServidoresOrganismos = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServidoresPlan = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServiciosPlan = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServidores = new ArrayList<KeyValueObject>();

	public List<ServicioOrganismosBean> listaServicioOrganismos = null;
	public List<ServidoresOrganismosBean> listaServidoresOrganismos = null;
	public List<PlanificacionBean> listaPlanificacionesServicio = null;

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

	public String newSearch() throws BaseException {
		return SUCCESS;
	}

	public String search() throws BaseException {

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";

		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para ordenar

		if (organismo != null)
			if (organismo.getNombre() != null && organismo.getNombre().length() <= 0)
				organismo.setNombre(null);

		int inicio = (page - 1) * PAGESIZE;
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<OrganismoBean> result = servicioOrganismo.getOrganismos(inicio, (export) ? -1 : PAGESIZE, order, columnSort, organismo);
		Integer totalSize = result.getTotalList();

		listaOrganismos = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
		if (!export) {
			getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
		} else {
			getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
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

		int inicio = (page - 1) * PAGESIZE;
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<OrganismoBean> result = servicioOrganismo.getOrganismos(inicio, (export) ? -1 : PAGESIZE, order, columnSort, organismo);
		Integer totalSize = result.getTotalList();

		listaOrganismos = result.getPageList();

		getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);

		if (!export) {
			getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
		} else {
			getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
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

	public String create() throws BaseException {

		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return "noUser";
		}
		if (organismo != null) {
			// throw new BusinessException("EL aplicacion recibido es nulo");

			if (organismo.getIsActivo() != null && organismo.getIsActivo().contains("inactivo")) {
				organismo.setActivo(new Integer(0));
			} else {
				organismo.setActivo(new Integer(1));
			}
			if (!validaObligatorios(organismo, true)) {
				return ERROR;
			}
			Integer idOrganismo = servicioOrganismo.newOrganismo(organismo);
			this.idOrganismo = idOrganismo.toString();

			addActionMessageSession(this.getText("plataforma.organismo.create.ok"));
		} else {
			addActionErrorSession(this.getText("plataforma.organismo.create.error"));
			// return ERROR;
		}

		return SUCCESS;

	}

	private boolean validaObligatorios(OrganismoBean aplicacion2, boolean isUpdate) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getDIR3())) {
			addActionErrorSession(this.getText("plataforma.organismo.field.DIR3.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getNombre())) {
			addActionErrorSession(this.getText("plataforma.organismo.field.nombre.error"));
			sw = false;
		}
		// if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getDescripcion())) {
		// addActionErrorSession(this.getText("plataforma.aplicacion.field.descripcion.error"));
		// sw = false;
		// }

		return sw;
	}

	public String update() throws BaseException {

		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return "noUser";
		}
		OrganismoBean organismoBBDD = null;
		if (organismo == null) {

			addActionErrorSession(this.getText("plataforma.organismo.update.error"));

		} else {
			System.out.println("[OrganismoAction - Idorganismo] valor == " + organismo.getOrganismoId());
			if (organismo.getOrganismoId() == null) {
				if (idOrganismo != null) {
					organismo.setOrganismoId(new Integer(idOrganismo));
					organismoBBDD = servicioOrganismo.loadOrganismo(organismo);
				} else {
					String idOrganismo = (String) request.getAttribute("idOrganismo");
					System.out.println("[OrganismoAction - request.getAttribute('idOrganismo)' == " + idOrganismo);
					if (idOrganismo != null) {
						aplicacion.setId(new Long(idOrganismo));
						organismoBBDD = servicioOrganismo.loadOrganismo(organismo);
					}
				}

				System.out.println("[AplicacionAction - Idaplicacion despues de setear idAplicacion] valor == " + aplicacion.getAplicacionId());
			} else {
				organismoBBDD = servicioOrganismo.loadOrganismo(organismo);

			}
			if (organismoBBDD != null) {
				organismoBBDD.setDIR3(organismo.getDIR3());
				// DE MOMENTO NO
				// organismoBBDD.setExternalId(organismo.getExternalId());

				// organismoBBDD.setHistorificacion(organismo.getHistorificacion());
				// organismoBBDD.setMotivoHistorificacion(organismo.getMotivoHistorificacion());
				// organismoBBDD.setConservacion(organismo.getConservacion());
				// organismoBBDD.setMotivoConservacion(organismo.getMotivoConservacion());
				// organismoBBDD.setNombreCuentaEnvio(organismo.getNombreCuentaEnvio());
				organismoBBDD.setNombre(organismo.getNombre());
				organismoBBDD.setDescripcion(organismo.getDescripcion());
				organismoBBDD.setActivo(organismo.getActivo());
				organismo = organismoBBDD;
				if (validaObligatorios(organismoBBDD, true)) {
					servicioOrganismo.updateOrganismo(organismoBBDD);
					addActionMessageSession(this.getText("plataforma.organismo.update.ok"));
				}
			}

		}
		return SUCCESS;

	}

	public String load() throws BaseException {
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return "noUser";
		}
		if (idOrganismo == null)
			throw new BusinessException("EL idOrganismo recibido es nulo");
		try {
			organismo = new OrganismoBean();
			organismo.setOrganismoId(new Integer(idOrganismo));
			organismo = servicioOrganismo.loadOrganismo(organismo);

			return SUCCESS;
		} catch (NumberFormatException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { organismo.getOrganismoId().toString() });
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { aplicacion.getAplicacionId().toString() });
			throw new BusinessException(mensg);
		}

	}

	public String delete() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idOrganismo == null) {
			addActionErrorSession(this.getText("plataforma.organismo.delete.error"));

		} else {
			organismo = new OrganismoBean();
			organismo.setOrganismoId(new Integer(idOrganismo));
			servicioOrganismo.deleteOrganismo(organismo);

			// borramos sus planificaciones
			List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
			if (null != listaPlanificacionesOrganismos){
				for (PlanificacionBean o : listaPlanificacionesOrganismos) { 
					servicioPlanificacion.deletePlanificacion(o);
				}
			}
			addActionMessageSession(this.getText("plataforma.organismo.delete.ok"));
		}
		return SUCCESS;

	}

	public String deleteSelected() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.organimo.deleteSelected.error"));

		} else {
			for (String idOrganismo : checkDelList) {
				organismo = new OrganismoBean();
				organismo.setOrganismoId(new Integer(idOrganismo));
				servicioOrganismo.deleteOrganismo(organismo);

				// borramos sus planificaciones
				List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
				if (null != listaPlanificacionesOrganismos){
					for (PlanificacionBean o : listaPlanificacionesOrganismos) { 
						servicioPlanificacion.deletePlanificacion(o);
	
					}
				}
			}
			addActionMessageSession(this.getText("plataforma.organismo.deleteSelected.ok"));

		}
		return SUCCESS;

	}

	public String deleteOrganismoServicioSelected() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelListOrganismosServicios == null) {
			// throw new BusinessException("No se ha seleccionado ningún servidor/proveedor para eliminar");
			addActionErrorSession(this.getText("plataforma.servicio.servicioOrganismo.deleteSelected.error"));

		} else {

			for (String serOrg : checkDelListOrganismosServicios) {
				ServicioOrganismosBean servicioOrganismo = new ServicioOrganismosBean();
				servicioOrganismo.setServicioOrganismoId(Integer.valueOf(serOrg));
				updateServicio();

				updateOrganismo();

				// ////borrar planificaciones del organismo
				List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
				if (null != listaPlanificacionesOrganismos){
					for (PlanificacionBean o : listaPlanificacionesOrganismos) {
						if (o.getServicioId().equals(Integer.parseInt(idServicio))) {
							servicioPlanificacion.deletePlanificacion(o);
						}
					}
				}
				
				servicioServicio.deleteServicioOrganismos(servicioOrganismo);

			}
			addActionMessageSession(this.getText("plataforma.servicio.servidorOrganismo.delete.ok"));

		}
		return SUCCESS;
	}


	public String deleteServidorOrganismosSelected() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelListServidorOrganismos == null) {
			// throw new BusinessException("No se ha seleccionado ningún servidor/proveedor para eliminar");
			addActionErrorSession(this.getText("plataforma.servicio.servidorOrganismo.deleteSelected.error"));

		} else {
			
			for (String serOrg : checkDelListServidorOrganismos) {
//				try{
				updateOrganismo();

				ServidoresOrganismosBean servidorOrganismo = new ServidoresOrganismosBean();
				servidorOrganismo.setServidorOrganismoId(Long.valueOf(serOrg));
				idServidor = servicioServidor.loadServidorOrganismoBean(servidorOrganismo).getServidorId().toString();
				servicioServidor.deleteServidorOrganismos(servidorOrganismo);

				// ////borrar planificaciones
				List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
				if (null != listaPlanificacionesOrganismos){
					for (PlanificacionBean o : listaPlanificacionesOrganismos) { 
						Integer a = o.getServidorId();
						if (o.getServidorId().equals(Integer.valueOf(idServidor))) {
							servicioPlanificacion.deletePlanificacion(o);
						}
	
					}
				}
//				}catch(Exception e){
//					e.printStackTrace();
//				}

			}
			addActionMessageSession(this.getText("plataforma.servicio.servidorOrganismo.delete.ok"));

		}
		return SUCCESS;
	}

	public String deletePlanificacionesOrganismoSelected() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelListPlanificacionesOrganismos == null) {
			// throw new BusinessException("No se ha seleccionado ningúna planificacion para eliminar");
			addActionErrorSession(this.getText("plataforma.servicio.deletePlanificacionesSelected.error"));

		} else {
			for (String idPlanificacion : checkDelListPlanificacionesOrganismos) {
				PlanificacionBean planificacion = new PlanificacionBean();
				planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
				servicioPlanificacion.deletePlanificacion(planificacion);
				// servicioPlanificacion.eliminarJobPorPlanificacion(Integer.valueOf(idPlanificacion));
			}
			addActionMessageSession(this.getText("plataforma.servicio.deletePlanificacionesSelected.ok"));

		}
		return SUCCESS;
	}

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
			detalle.setActivo(detalleApp.getActivo());
			detalle.setFechaCreacion(detalleApp.getFechaCreacion());
			detalle.setFechaModificacion(detalleApp.getFechaModificacion());
			List<ServicioBean> listServicioBean = servicioServicio.getServiciosByAplicacionId(detalle.getAplicacionId());
			if (listServicioBean != null)
				for (ServicioBean ser : listServicioBean) {
					DetalleServicioBean serBean = new DetalleServicioBean();
					BeanUtils.copyProperties(serBean, ser);
					serBean.setActivo(ser.getActivo());
					serBean.setFechaCreacion(ser.getFechaCreacion());
					serBean.setFechaModificacion(ser.getFechaModificacion());
					serBean.setFromMail(ser.getFromMail());
					serBean.setFromMailName(ser.getFromMailName());
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

	private List<ServicioOrganismosBean> loadSeviciosOrganismo() {
		List<ServicioOrganismosBean> lista = null;
		if (idOrganismo != null && idOrganismo.length() > 0) {
			try {
				lista = servicioServicio.getOrganismoServicio(idOrganismo);
			} catch (NumberFormatException e) {

				e.printStackTrace();
			} catch (BusinessException e) {

				e.printStackTrace();
			}
		} else if (organismo != null && organismo.getOrganismoId() != null) {
			try {
				lista = servicioServicio.getOrganismoServicio(String.valueOf(organismo.getOrganismoId()));
			} catch (NumberFormatException e) {

				e.printStackTrace();
			} catch (BusinessException e) {

				e.printStackTrace();
			}
		}
		return lista;
	}

	private List<ServidoresOrganismosBean> loadServidoresOrganismos() {
		List<ServidoresOrganismosBean> lista = null;
		if (idOrganismo != null && idOrganismo.length() > 0) {
			try {
				lista = servicioServidor.getServidorOrganismo(idOrganismo);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (organismo != null && organismo.getOrganismoId() != null) {
			try {
				lista = servicioServidor.getServidorOrganismo(String.valueOf(organismo.getOrganismoId()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lista;
	}

	public String addPlanificacionOrganismo() throws BaseException {
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
					planificacionOrganismo.setActivo(Integer.valueOf(1));

					Integer planificacionId = servicioPlanificacion.newPlanificacion(planificacionOrganismo);
					if (planificacionId > 0) {
						servicioPlanificacion.crearJobPorPlanificacion(planificacionId);
					}
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

	private boolean validoFormatoHora(String hora) {
		boolean sw = true;
		if (!PlataformaMensajeriaUtil.isEmpty(hora)) {
			if (!PlataformaMensajeriaUtil.validaFormatoHora(hora)) {
				sw = false;
			}
		}
		return sw;
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

	@Override
	public void prepare() throws Exception {
		// contextUsuarios = getComboValues();
		session = (Map) ActionContext.getContext().get("session");
		recovery = (String) session.get(RECOVERY);

		listaServicioOrganismos = loadSeviciosOrganismo();
		listaServidoresOrganismos = loadServidoresOrganismos();
		// idOrganismo = "61";
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

	public String deletePlanificacionOrganismo() throws BaseException {
		if (idPlanificacion == null) {
			// throw new BusinessException("No se ha seleccionado ningúna planificacion para eliminar");
			addActionErrorSession(this.getText("plataforma.servicio.deletePlanificacion.error"));

		} else {
			PlanificacionBean planificacion = new PlanificacionBean();
			planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
			planificacion.setOrganismoId(Integer.valueOf(idOrganismo));
			servicioPlanificacion.deletePlanificacion(planificacion); // ver si lo hace bien
			// servicioPlanificacion.eliminarJobPorPlanificacion(Integer.valueOf(idPlanificacion));
			addActionMessageSession(this.getText("plataforma.servicio.deletePlanificacion.ok"));
		}
		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> cargarComboServicioOrganismos() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;

		ArrayList<ServicioBean> keys = null;
		try {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServiciosMultiorganismo();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (keys != null && keys.size() > 0)
			for (ServicioBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getServicioId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> cargarComboServidoresOrganismos() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;

		ArrayList<ServidorBean> keys = null;
		try {
			keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresYProveedores(servidortipoSMS);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (keys != null && keys.size() > 0)
			for (ServidorBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getServidorId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> cargarComboServidoresPlan() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;

		ArrayList<ServidoresOrganismosBean> keys = null;
		try {
			keys = (ArrayList<ServidoresOrganismosBean>) servicioServidor.getServidorOrganismo(idOrganismo);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (keys != null && keys.size() > 0)
			for (ServidoresOrganismosBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getServidorId().toString());
				option.setDescripcion(key.getNombreServidor());
				result.add(option);
			}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> cargarComboServiciosPlan() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;

		ArrayList<ServicioOrganismosBean> keys = null;
		try {
			keys = (ArrayList<ServicioOrganismosBean>) servicioServicio.getOrganismoServicio(idOrganismo);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (keys != null && keys.size() > 0)
			for (ServicioOrganismosBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getServicioId().toString());
				option.setDescripcion(key.getNombreServicio());
				result.add(option);
			}
		return result;
	}

	@Transactional
	public String addOrganismoServicios() throws BaseException {// TODO:
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
				servBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				servBean.setFechaModificacion(new Date());
				servicioServicio.updateServicio(servBean);

				updateOrganismo();

				servicioOrganismos.setServicioId(sBean.getServicioId());
				servicioOrganismos.setOrganismoId(Integer.valueOf(idOrganismo));

				servicioServicio.newServicioOrganismo(servicioOrganismos);
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

	@Transactional(noRollbackFor = Exception.class)
	public String addServidorOrganismo() throws BaseException {// TODO:
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		boolean sw = true;

		if (servidorOrganismo != null) {

			if (PlataformaMensajeriaUtil.isEmptyNumber(servidorOrganismo.getNumIntentos())) {
				addFieldErrorSession(this.getText("plataforma.servicio.servidorServicio.field.numIntentos"));
				sw = false;
			}
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
					ServidorBean sBean = new ServidorBean();
					sBean.setServidorId(servidorOrganismo.getServidorId());
					ServidorBean servBean = servicioServidor.loadServidor(sBean);
					servBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
					servBean.setFechaModificacion(new Date());
					servicioServidor.updateServidor(servBean);

					updateOrganismo();

					servidorOrganismo.setServidorId(sBean.getServidorId());
					servidorOrganismo.setOrganismoId(Long.valueOf(idOrganismo));

					servicioServidor.newServidoresOrganismo(servidorOrganismo);
				} catch (Exception e) {
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


	public String deleteOrganismoServicio() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idServicioOrganismo == null) {
			addActionErrorSession(this.getText("plataforma.servicio.servicioOrganismo.delete.error"));
		} else {
			try {
				updateServicio();

				updateOrganismo();

				// ////borrar planificaciones del organismo
				List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
				if (null != listaPlanificacionesOrganismos){
					for (PlanificacionBean o : listaPlanificacionesOrganismos) {
						if (o.getServicioId().equals(Integer.parseInt(idServicio))) {
							servicioPlanificacion.deletePlanificacion(o);
						}
					}
				}
				
				deleteServicioOrganismo();
			} catch (Exception e) {
				e.printStackTrace();
			}
			addActionMessageSession(this.getText("plataforma.servicio.servicioOrganismo.delete.ok"));
		}
		return SUCCESS;
	}


	private void deleteServicioOrganismo() throws BusinessException {
		ServicioOrganismosBean servidorOrganismos = new ServicioOrganismosBean();
		servidorOrganismos.setServicioOrganismoId(Integer.valueOf(idServicioOrganismo));
		servicioServicio.deleteServicioOrganismos(servidorOrganismos);
	}


	private void updateOrganismo() throws BusinessException {
		OrganismoBean oBean = new OrganismoBean();
		oBean.setOrganismoId(Integer.valueOf(idOrganismo));
		OrganismoBean orgBean = servicioOrganismo.loadOrganismo(oBean);
		orgBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
		orgBean.setFechaModificacion(new Date());
		servicioOrganismo.updateOrganismo(orgBean);
	}


	private void updateServicio() throws BusinessException {
		ServicioBean sBean = new ServicioBean();
		sBean.setServicioId(Integer.valueOf(idServicio));
		ServicioBean servBean = servicioServicio.loadServicio(sBean);
		servBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
		servBean.setFechaModificacion(new Date());
		servicioServicio.updateServicio(servBean);
	}


	public String deleteServidorOrganismo() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (servidorOrganismoId == null) {
			addActionErrorSession(this.getText("plataforma.servicio.servidorOrganismo.delete.error"));
		} else {
//			try {
				
				updateOrganismo();

				// ////borrar planificaciones
				List<PlanificacionBean> listaPlanificacionesOrganismos = servicioPlanificacion.getPlanificacionesByOrganismoID(Integer.valueOf(idOrganismo));
				if (null != listaPlanificacionesOrganismos){
					for (PlanificacionBean o : listaPlanificacionesOrganismos) { 
						Integer a = o.getServidorId();
						if (o.getServidorId().equals(Integer.valueOf(idServidor))) {
							servicioPlanificacion.deletePlanificacion(o);
						}
	
					}
				}

				ServidoresOrganismosBean servidorOrganismos = new ServidoresOrganismosBean();
				servidorOrganismos.setServidorOrganismoId(Long.valueOf(servidorOrganismoId));
				servicioServidor.deleteServidorOrganismos(servidorOrganismos);

//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			addActionMessageSession(this.getText("plataforma.servicio.servidorOrganismo.delete.ok"));
		}
		return SUCCESS;
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

	/**
	 * Método que resuelve el lugar donde tiene que volver
	 */
	public String getVolverServicio() {
		String volver = "editServicio.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
		} else {
			volver = "viewAplicacion.action?idAplicacion=" + servicio.getAplicacionId();
		}
		return volver;
	}

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

}
