package es.mpr.plataformamensajeria.web.action.planificaciones;

import java.util.ArrayList;
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
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;
import es.mpr.plataformamensajeria.beans.ReceptorSMSBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidorPushBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;
import es.mpr.plataformamensajeria.beans.TipoPlanificacionBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioCanal;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioReceptorSMS;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorPush;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los planificaciones.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n,
 * borrado y listado de los planificaciones
 * 
 * @author i-nercya
 * 
 */
@Controller("planificacionAction")
@Scope("prototype")
public class PlanificacionAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	private static Logger logger = Logger.getLogger(PlanificacionAction.class);
	
	private static final long serialVersionUID = 1L;

	@Resource(name = "servicioPlanificacionImpl")
	private ServicioPlanificacion servicioPlanificacion;
	
	@Resource(name = "servicioServicioImpl")
	private ServicioServicio servicioServicio;
	
	@Resource(name = "servicioCanalImpl")
	private ServicioCanal servicioCanal;
	
	@Resource(name = "servicioServidorImpl")
	private ServicioServidor servicioServidor;
	
	@Resource(name = "servicioAplicacionImpl")
	private ServicioAplicacion servicioAplicacion;
	
	@Resource(name = "servicioProveedorSMSImpl")
	private ServicioProveedorSMS servicioProveedorSMS;
	
	@Resource(name = "servicioReceptorSMSImpl")
	private ServicioReceptorSMS servicioReceptorSMS;
	
	@Resource(name = "servicioServidorPushImpl")
	private ServicioServidorPush servicioServidorPush;

	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;

	private PlanificacionBean planificacion;
	
	private List<KeyValueObject> comboAplicaciones = new ArrayList<KeyValueObject>();
	private List<KeyValueObject> comboCanales = new ArrayList<KeyValueObject>();
	private List<KeyValueObject> comboServidores = new ArrayList<KeyValueObject>();
	private List<KeyValueObject> comboBusquedaServidores = new ArrayList<KeyValueObject>();
	private List<KeyValueObject> comboTipoPlanificaciones = new ArrayList<KeyValueObject>();
	private List<KeyValueObject> comboConfiguraciones = new ArrayList<KeyValueObject>();
	private List<KeyValueObject> comboServicios = new ArrayList<KeyValueObject>();
	
	public List<PlanificacionBean> listaPlanificaciones = null;
	private String[] checkDelList;
	
	private String servicioId;
	private String tipoPlanificacionId;
	private String tipoParametroId;
	private String idAplicacion;
	private String idPlanificacion;
	private String idServicio;
	private String idServidor;
	private String idProveedorSMS;
	private String idReceptorSMS;
	private String idServidorPush;
	private String idServidorWebPush;
	private String idOrganismo;
		
	private String nAction;
	private String resultCount;
	private boolean modificaActivo;


	public String newSearch() throws BaseException {
		return SUCCESS;
	}
////MIGRADO
	public String search() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";

		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para
														// ordenar
		boolean doSearch = true;

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		if (planificacion != null && !PlataformaMensajeriaUtil.isEmpty(planificacion.getHoraDesde())
				&& !validoFormatoHora(planificacion.getHoraDesde())) {
			addActionErrorSession(this.getText("plataforma.planificacion.busquedas.formato.horadesde.error"));
			doSearch = false;
		}
		if (planificacion != null && !PlataformaMensajeriaUtil.isEmpty(planificacion.getHoraHasta())
				&& !validoFormatoHora(planificacion.getHoraHasta())) {
			addActionErrorSession(this.getText("plataforma.planificacion.busquedas.formato.horahasta.error"));
			doSearch = false;
		}
		if (planificacion != null && !PlataformaMensajeriaUtil.isEmpty(planificacion.getHoraDesdeFin())
				&& !validoFormatoHora(planificacion.getHoraDesdeFin())) {
			addActionErrorSession(this.getText("plataforma.planificacion.busquedas.formato.horadesdefin.error"));
			doSearch = false;
		}
		if (planificacion != null && !PlataformaMensajeriaUtil.isEmpty(planificacion.getHoraHastaFin())
				&& !validoFormatoHora(planificacion.getHoraHastaFin())) {
			addActionErrorSession(this.getText("plataforma.planificacion.busquedas.formato.horadesdefin.error"));
			doSearch = false;
		}
		if (doSearch) {
			boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
			PaginatedList<PlanificacionBean> result = servicioPlanificacion.getPlanificaciones(inicio, (export) ? -1
					: Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order, columnSort, planificacion);
			Integer totalSize = result.getTotalList();

			listaPlanificaciones = result.getPageList();

			// Atributos de request
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

			if (!export) {
				getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), 
						Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
			} else {
				getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
			}

			if (listaPlanificaciones != null && !listaPlanificaciones.isEmpty()) {
				for (int indice = 0; indice < listaPlanificaciones.size(); indice++) {

					PlanificacionBean planificacion = listaPlanificaciones.get(indice);
					planificacion.setNombreServicio(StringEscapeUtils.escapeHtml(planificacion.getNombreServicio()));
					planificacion.setNombreServidor(StringEscapeUtils.escapeHtml(planificacion.getNombreServidor()));
				}
			}
		}
		return SUCCESS;
	}


////MIGRADO
	public String createPlanificacionApp() throws BaseException {
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty("log.SOURCE_PLANIFICACIONES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PLANIFICACION", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (planificacion != null) {

			if (planificacion.getIsActivo() != null && planificacion.getIsActivo().indexOf("activo") != -1) {
				planificacion.setActivo(true);
			} else {
				planificacion.setActivo(false);
			}
			if (planificacionValida(planificacion)) {
				int valido = servicioPlanificacion.validaPlanificacionOptima(idPlanificacion,
						planificacion.getTipoPlanificacionId(), planificacion.getServidorId(), planificacion.getServicioId(),
						planificacion.getLunes(), planificacion.getMartes(), planificacion.getMiercoles(),
						planificacion.getJueves(), planificacion.getViernes(), planificacion.getSabado(),
						planificacion.getDomingo(), planificacion.getHoraHasta(), planificacion.getHoraDesde());

				// Si valido = 1 es correcto
				if (valido == 1) {
					Integer id = servicioPlanificacion.newPlanificacion(planificacion, source, accion, accionId,
							descripcion);

					this.idPlanificacion = id.toString();
					addActionMessageSession(this.getText("plataforma.planificacion.create.ok"));
				} else if (valido == 2) {
					addActionErrorSession("No se ha guardado la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
				} else
					addActionErrorSession("No se ha guardado la planificaci&oacute;n. La configuraci&oacute;n seleccionada no garantiza el env&iacute;o de los mensajes");

			} else {
				return ERROR;
			}

		} else {
			addActionErrorSession(this.getText("plataforma.planificacion.create.error"));
			return ERROR;
		}
		return SUCCESS;

	}

	////MIGRADO
	public String create() throws BaseException {
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty("log.SOURCE_PLANIFICACIONES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PLANIFICACION", null);	
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (planificacion != null) {
			
			if (planificacion.getIsActivo() != null && planificacion.getIsActivo().indexOf("activo") != -1) {
				planificacion.setActivo(true);
			} else {
				planificacion.setActivo(false);
			}
			if (planificacionValida(planificacion)) {
				Integer id = servicioPlanificacion.newPlanificacion(planificacion, source, accion, accionId, descripcion);
				this.idPlanificacion = id.toString();
				addActionMessageSession(this.getText("plataforma.planificacion.create.ok"));
				
			} else {
				return ERROR;
			}

		} else {
			addActionErrorSession(this.getText("plataforma.planificacion.create.error"));
			return ERROR;
		}
		return SUCCESS;

	}

	
	////MIGRADO
	public String updatePlanificacionViewApp() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		modificaActivo = false;
		int valido = servicioPlanificacion.validaPlanificacionOptima(idPlanificacion,
				planificacion.getTipoPlanificacionId(), planificacion.getServidorId(), planificacion.getServicioId(), planificacion.getLunes(),
				planificacion.getMartes(), planificacion.getMiercoles(), planificacion.getJueves(),
				planificacion.getViernes(), planificacion.getSabado(), planificacion.getDomingo(),
				planificacion.getHoraHasta(), planificacion.getHoraDesde());
		
		// Si valido = 1 es correcto
		if (valido == 1) {
			update();
			return SUCCESS;

		} else if (valido == 2) {
			addActionErrorSession("No se ha actualizado la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
			return ERROR;
		} else {
			addActionErrorSession("No se ha actualizado la planificaci&oacute;n. La configuraci&oacute;n seleccionada no garantiza el env&iacute;o de los mensajes");
			return ERROR;
		}
	}

	
	////MIGRADO
	public String updatePlanificacionServicio() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		modificaActivo = false;
		int valido = servicioPlanificacion.validaPlanificacionOptima(idPlanificacion,
				planificacion.getTipoPlanificacionId(), planificacion.getServidorId(), planificacion.getServicioId(), planificacion.getLunes(),
				planificacion.getMartes(), planificacion.getMiercoles(), planificacion.getJueves(),
				planificacion.getViernes(), planificacion.getSabado(), planificacion.getDomingo(),
				planificacion.getHoraHasta(), planificacion.getHoraDesde());

		if (valido == 1) {
			update();
			return SUCCESS;
		} else if (valido == 2) {
			addActionErrorSession("No se ha actualizado la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
			return ERROR;
		} else {
			addActionErrorSession("No se ha actualizado la planificaci&oacute;n. La configuraci&oacute;n seleccionada no garantiza el env&iacute;o de los mensajes");
			return ERROR;
		}
	}

	/////MIGRADO
	public String updatePlanificacionOrganismo() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		modificaActivo = false;
		int valido = servicioPlanificacion.validaPlanificacionOptimaOrganismo(idPlanificacion,
				planificacion.getTipoPlanificacionId(), planificacion.getServidorId(), planificacion.getServicioId(), planificacion.getLunes(),
				planificacion.getMartes(), planificacion.getMiercoles(), planificacion.getJueves(),
				planificacion.getViernes(), planificacion.getSabado(), planificacion.getDomingo(),
				planificacion.getHoraHasta(), planificacion.getHoraDesde(), Integer.valueOf(idOrganismo));

		if (valido == 1) {
			String retorno = update();
			return SUCCESS;
		} else if (valido == 2) {
			addActionErrorSession("No se ha actualizado la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
			return ERROR;
		} else {
			addActionErrorSession("No se ha actualizado la planificaci&oacute;n. La configuraci&oacute;n seleccionada no garantiza el env&iacute;o de los mensajes");
			return ERROR;
		}
	}

	////MIGRADO
	public String updatePlanificacionServer() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		modificaActivo = false;
		int valido = servicioPlanificacion.validaPlanificacionServidor(idPlanificacion, planificacion.getServidorId(),
				planificacion.getLunes(), planificacion.getMartes(), planificacion.getMiercoles(),
				planificacion.getJueves(), planificacion.getViernes(), planificacion.getSabado(),
				planificacion.getDomingo(), planificacion.getHoraHasta(), planificacion.getHoraDesde());

		if (valido == 1) {
			return update();
		} else {
			addActionErrorSession("No se ha actualizado la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
			return ERROR;
		}
	}

	/////MIGRADO
	public String update() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_PLANIFICACIONES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ACTUALIZAR_PLANIFICACION", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		PlanificacionBean planificacionBBDD = null;
		if (planificacion == null) {
			addActionErrorSession(this.getText("plataforma.planificacion.update.error"));

		} else {
			if (planificacion.getPlanificacionId() == null) {
				if (idPlanificacion != null) {
					planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
					planificacionBBDD = servicioPlanificacion.loadPlanificacion(planificacion);
				} else {
					String idPlanificacion = (String) request.getAttribute("idPlanificacion");
					if (idPlanificacion != null) {
						planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
						planificacionBBDD = servicioPlanificacion.loadPlanificacion(planificacion);
					}
				}
			} else {
				planificacionBBDD = servicioPlanificacion.loadPlanificacion(planificacion);

			}
			if (planificacionBBDD != null) {
				planificacionBBDD.setServicioId(planificacion.getServicioId());
				planificacionBBDD.setLunes(planificacion.getLunes());
				planificacionBBDD.setMartes(planificacion.getMartes());
				planificacionBBDD.setMiercoles(planificacion.getMiercoles());
				planificacionBBDD.setJueves(planificacion.getJueves());
				planificacionBBDD.setViernes(planificacion.getViernes());
				planificacionBBDD.setSabado(planificacion.getSabado());
				planificacionBBDD.setDomingo(planificacion.getDomingo());
				planificacionBBDD.setHoraDesde(planificacion.getHoraDesde());
				planificacionBBDD.setHoraHasta(planificacion.getHoraHasta());
				planificacionBBDD.setServidorId(planificacion.getServidorId());
				planificacionBBDD.setActivo(planificacion.getActivo());
				
				planificacionBBDD.setTipoPlanificacionId(planificacion.getTipoPlanificacionId());
				planificacionBBDD.setServidorId(planificacion.getServidorId());
				planificacionBBDD.setAplicacionId(planificacion.getAplicacionId());
				if (planificacionBBDD.getServicioId() != null && planificacionBBDD.getServicioId() == 0) {
					planificacionBBDD.setServicioId(null);
				}
			}
			if (planificacionValida(planificacion)) {
				servicioPlanificacion.updatePlanificacion(planificacionBBDD, source, accion, accionId, descripcion);
				addActionMessageSession(this.getText("plataforma.planificacion.update.ok"));
			} else {
				return ERROR;
			}

		}
		return SUCCESS;

	}

	/////MIGRADO
	public String load() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idPlanificacion == null)
			throw new BusinessException("EL idPlanificacion recibido es nulo");
		try {
			modificaActivo = true;
			planificacion = new PlanificacionBean();
			planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
			planificacion = servicioPlanificacion.loadPlanificacion(planificacion);
			if (servicioId == null && planificacion.getServicioId() != null) {
				servicioId = planificacion.getServicioId().toString();
			}

			if (planificacion.getTipoPlanificacionId() != null && planificacion.getTipoPlanificacionId() == 1) {
				tipoPlanificacionId = "2";
			} else if (planificacion.getTipoPlanificacionId() != null && planificacion.getTipoPlanificacionId() == 2) {
				tipoPlanificacionId = "1";
			} else if (planificacion.getTipoPlanificacionId() != null && planificacion.getTipoPlanificacionId() == 3) {
				tipoPlanificacionId = "3";
			} else if (planificacion.getTipoPlanificacionId() != null && planificacion.getTipoPlanificacionId() == 4) {
				tipoPlanificacionId = "4";
			}

			return SUCCESS;
		} catch (NumberFormatException |  BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { planificacion
					.getPlanificacionId().toString() });
			logger.error("PlanificacionAction - load:" + e);
			throw new BusinessException(mensg);
		} 

	}

	////MIGRADO
	public String deletePlanificacionServicioViewApp() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		boolean sw = true;
		if (idPlanificacion == null) {
			addActionErrorSession(this.getText("plataforma.planificacion.delete.error"));
		} else {
			planificacion = new PlanificacionBean();
			planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
			sw = servicioPlanificacion.deletePlanificacion(planificacion, source, accion, accionId, descripcion);
			if (!sw) {
				addActionErrorSession(this.getText("plataforma.planificacion.delete.error"));
			} else {

				addActionMessageSession(this.getText("plataforma.planificacion.delete.ok"));
			}
		}
		return SUCCESS;

	}

	/////MIGRADO
	public String delete() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_PLANIFICACIONES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		boolean sw = true;
		if (idPlanificacion == null) {
			addActionErrorSession(this.getText("plataforma.planificacion.delete.error"));
		} else {
			planificacion = new PlanificacionBean();
			planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
			sw = servicioPlanificacion.deletePlanificacion(planificacion, source, accion, accionId, descripcion);
			if (!sw) {
				addActionErrorSession(this.getText("plataforma.planificacion.delete.error"));
			} else {
				addActionMessageSession(this.getText("plataforma.planificacion.delete.ok"));
			}
		}
		return SUCCESS;

	}

	/////MIGRADO
	public String deleteSelected() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_PLANIFICACIONES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		boolean sw = true;
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.planificacion.deleteSelected.error"));

		} else {
			for (String id : checkDelList) {
				planificacion = new PlanificacionBean();
				planificacion.setPlanificacionId(Integer.valueOf(id));
				sw = servicioPlanificacion.deletePlanificacion(planificacion, source, accion, accionId, descripcion);
				if (!sw) {
					addActionErrorSession(this.getText("plataforma.planificacion.delete.error") + " [Identificador: "
							+ planificacion.getPlanificacionId() + "]");
				}
			}
			if (sw)
				addActionMessageSession(this.getText("plataforma.planificacion.deleteSelected.ok"));

		}
		return SUCCESS;

	}

	/////MIGRADO
	public String loadServidoresByTipoPlan() {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (tipoPlanificacionId == null || (tipoPlanificacionId != null && "".equals(tipoPlanificacionId))) {
			if (planificacion != null && planificacion.getTipoPlanificacionId() != null) {
				tipoPlanificacionId = planificacion.getTipoPlanificacionId().toString();
			}
		}
		if (tipoPlanificacionId != null && "1".equals(tipoPlanificacionId)) {
			tipoPlanificacionId = "2";
		} else if (tipoPlanificacionId != null && "2".equals(tipoPlanificacionId)) {
			tipoPlanificacionId = "1";
		}
		return SUCCESS;
	}

	
	////MIGRADO
	public String loadTipoPlanificacionByServicioCanal() {
		if (servicioId != null) {
			try {
				comboTipoPlanificaciones = loadTipoPlanificaciones();
			} catch (BusinessException e) {
				logger.error("PlanificacionAction - loadTipoPlanificacionByServicioCanal:" + e);
			}
		}
		return SUCCESS;
	}

	////MIGRADO
	@Override
	public void prepare() throws Exception {
		if (idServicio != null) {
			ServicioBean servicioBean = new ServicioBean();
			servicioBean.setId(Integer.valueOf(idServicio));

			ServicioBean servicio = servicioServicio.loadServicio(servicioBean);
			String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
			if (rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)) {
				comboConfiguraciones = getComboConfiguracion(servicio.getCanalid());
			} else if (rolUsuario.equals(PlataformaMensajeriaUtil.ROL_PROPIETARIO)) {
				comboConfiguraciones = getComboServidoresServicio(servicio.getServicioId());
			}
			if (planificacion == null) {
				planificacion = new PlanificacionBean();
			}
			planificacion.setServicioId(Integer.valueOf(idServicio));
			if (servicio.getCanalid() == 1) {
				planificacion.setTipoPlanificacionId(Integer.valueOf(1));
			} else if (servicio.getCanalid() == 2) {
				planificacion.setTipoPlanificacionId(Integer.valueOf(2));
			} else if (servicio.getCanalid() == 3) {
				planificacion.setTipoPlanificacionId(Integer.valueOf(3));
			} else if (servicio.getCanalid() == 4) {
				planificacion.setTipoPlanificacionId(Integer.valueOf(4));
			}
		} else {
			if (tipoPlanificacionId == null && servicioId == null) {
				comboAplicaciones = loadComboAplicaciones();
				comboCanales = loadComboCanales();
				comboBusquedaServidores = loadComboBusquedaServidores();
				comboServicios = loadComboServicios();
				comboTipoPlanificaciones = loadTipoPlanificaciones();
			}
		}
	}

	////MIGRADO
	private List<KeyValueObject> getComboConfiguracion(Integer idCanal) {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option;
		ArrayList<ProveedorSMSBean> keysSMS = null;
		ArrayList<ServidorBean> keysSMTP = null;
		ArrayList<ReceptorSMSBean> keysReceptorSMS = null;
		ArrayList<ServidorPushBean> keysServidorPush = null;
		if (idCanal != null && idCanal.intValue() == Integer.parseInt(properties.getProperty("generales.CANAL_SMS_ID", null))) {
			try {
				keysSMS = (ArrayList<ProveedorSMSBean>) servicioProveedorSMS.getProveedoresSMS(Integer
						.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_SMS", null)));
			} catch (BusinessException e) {
				logger.error("PlanificacionAction - getComboConfiguracion:" + e);
			}

			if (keysSMS != null && !keysSMS.isEmpty())
				for (ProveedorSMSBean key : keysSMS) {

					option = new KeyValueObject();
					option.setCodigo(key.getProveedorSMSId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
		} else if (idCanal != null && idCanal.intValue() == Integer.parseInt(properties.getProperty("generales.CANAL_SMTP_ID", null))) {
			try {
				keysSMTP = (ArrayList<ServidorBean>) servicioServidor.getServidores(Integer.parseInt(properties
						.getProperty("generales.TIPO_SERVIDOR_SMTP", null)));
			} catch (BusinessException e) {
				logger.error("PlanificacionAction - getComboConfiguracion:" + e);
			}

			if (keysSMTP != null && !keysSMTP.isEmpty())
				for (ServidorBean key : keysSMTP) {

					option = new KeyValueObject();
					option.setCodigo(key.getServidorid().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
		} else if (idCanal != null && idCanal.intValue() == Integer.parseInt(properties.getProperty("generales.CANAL_RECEPCION_SMS_ID", null))) {
			try {
				keysReceptorSMS = (ArrayList<ReceptorSMSBean>) servicioReceptorSMS.getReceptoresSMS(Integer
						.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_RECEPCION", null)));
			} catch (BusinessException e) {
				logger.error("PlanificacionAction - getComboConfiguracion:" + e);
			}

			if (keysReceptorSMS != null && !keysReceptorSMS.isEmpty())
				for (ReceptorSMSBean key : keysReceptorSMS) {

					option = new KeyValueObject();
					option.setCodigo(key.getReceptorSMSId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
		} else if (idCanal != null && idCanal.intValue() == Integer.parseInt(properties.getProperty("generales.CANAL_SERVIDOR_PUSH_ID", null))) {
			try {
				keysServidorPush = (ArrayList<ServidorPushBean>) servicioServidorPush.getServidoresPush(Integer
						.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_PUSH", null)));
			} catch (BusinessException e) {
				logger.error("PlanificacionAction - getComboConfiguracion:" + e);
			}

			if (keysServidorPush != null && !keysServidorPush.isEmpty())
				for (ServidorPushBean key : keysServidorPush) {

					option = new KeyValueObject();
					option.setCodigo(key.getServidorPushId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}
		}
		return result;
	}

	// //MIGRADO
	private List<KeyValueObject> getComboServidoresServicio(Integer servicioId) throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;

		ArrayList<ServidoresServiciosBean> keys;

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
	private List<KeyValueObject> loadComboServicios() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<ServicioBean> keys;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if (planificacion != null && planificacion.getAplicacionId() != null) {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServiciosByAplicacionId(planificacion
					.getAplicacionId());
		} else {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServicios(rolUsuario, idUsuario);
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

////MIGRADO
	private List<KeyValueObject> loadComboServidores(String tipoPlanificacionId) {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<ServidorBean> keys = null;
		try {

			keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresByTipoPlanificacion(tipoPlanificacionId);

		} catch (BusinessException e) {
			logger.error("PlanificacionAction - loadComboBusquedaServidores:" + e);
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
	private List<KeyValueObject> loadComboServidores() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option;
		ArrayList<ServidorBean> keys = null;;
		try {
			if (planificacion != null && planificacion.getTipoPlanificacionId() != null
					&& "1".equals(planificacion.getTipoPlanificacionId())) {
				keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresByTipoPlanificacion("2");
			} else if (planificacion != null && planificacion.getTipoPlanificacionId() != null
					&& "2".equals(planificacion.getTipoPlanificacionId())) {
				keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresByTipoPlanificacion("1");
			} else if (planificacion != null && planificacion.getTipoPlanificacionId() != null
					&& "3".equals(planificacion.getTipoPlanificacionId())) {
				keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresByTipoPlanificacion("3");
			}
		} catch (BusinessException e) {
			logger.error("PlanificacionAction - loadComboServidores:" + e);
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
	private List<KeyValueObject> loadComboBusquedaServidores() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option;
		ArrayList<ServidorBean> keys = null;
		try {
			keys = (ArrayList<ServidorBean>) servicioServidor.getAllServidores();
		} catch (BusinessException e) {
			logger.error("PlanificacionAction - loadComboBusquedaServidores:" + e);
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
	private List<KeyValueObject> loadComboCanales() {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<CanalBean> keys = null;
		try {
			keys = (ArrayList<CanalBean>) servicioCanal.getCanales();
		} catch (BusinessException e) {
			logger.error("PlanificacionAction - loadComboCanales:" + e);
		}

		if (keys != null && !keys.isEmpty())
			for (CanalBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getCanalId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		return result;
	}

/////MIGRADO
	private List<KeyValueObject> loadTipoPlanificaciones() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<TipoPlanificacionBean> keys;
		ServicioBean servicioBean = null;
		if (servicioId != null) {
			ServicioBean servicio = new ServicioBean();
			servicio.setServicioId(Integer.valueOf(servicioId));
			servicioBean = servicioServicio.loadServicio(servicio);
		}

		keys = new ArrayList<>();
		TipoPlanificacionBean tp = new TipoPlanificacionBean();
		if (servicioBean != null && servicioBean.getCanalid() != null && servicioBean.getCanalid() == 1) {
			tp.setNombre("Email");
			tp.setTipoPlanificacionId(1);
			keys.add(tp);
		} else if (servicioBean != null && servicioBean.getCanalid() != null && servicioBean.getCanalid() == 2) {
			tp.setNombre("SMS");
			tp.setTipoPlanificacionId(2);
			keys.add(tp);
		} else if (servicioBean != null && servicioBean.getCanalid() != null && servicioBean.getCanalid() == 3) {
			tp.setNombre("Receptor SMS");
			tp.setTipoPlanificacionId(3);
			keys.add(tp);
		} else if (servicioBean != null && servicioBean.getCanalid() != null && servicioBean.getCanalid() == 4) {
			tp.setNombre("Notificaciones Push");
			tp.setTipoPlanificacionId(4);
			keys.add(tp);
		}
		if (!keys.isEmpty()) {
			for (TipoPlanificacionBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getTipoPlanificacionId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}

/////MIGRADO
	private List<KeyValueObject> loadComboAplicaciones() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option = null;
		ArrayList<AplicacionBean> keys = null;
		try {
			keys = (ArrayList<AplicacionBean>) servicioAplicacion.getAplicaciones();
		} catch (BusinessException e) {
			logger.error("PlanificacionAction - loadComboAplicaciones:" + e);
		}

		if (keys != null && !keys.isEmpty())
			for (AplicacionBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getAplicacionId().toString());
				option.setDescripcion(key.getNombre());
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
	//////MIGRADO
	private boolean planificacionValida(PlanificacionBean planificacionServidor) {
		boolean sw = true;
		if (planificacionServidor != null) {
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
				addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horas.error"));
				sw = false;

			}
			if (!PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
				addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horaHasta.error"));
				sw = false;
			}
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())
					&& !PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())) {
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
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getLunes())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getMartes())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getMiercoles())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getJueves())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getViernes())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getSabado())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getDomingo())) {
				addFieldErrorSession(this.getText("plataforma.servidores.planificacion.dias.error"));
				sw = false;
			}

			if (PlataformaMensajeriaUtil.isEmptyNumber(planificacionServidor.getTipoPlanificacionId())) {
				addFieldErrorSession(this.getText("plataforma.servidores.planificacion.field.tipoplanificacion.error"));
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

	////MIGRADO
	public List<KeyValueObject> getComboServidores() {
		if (tipoPlanificacionId != null) {
			return loadComboServidores(tipoPlanificacionId);
		} else if (planificacion != null && planificacion.getTipoPlanificacionId() != null) {
			String tipoPlanificacionId = planificacion.getTipoPlanificacionId().toString();
			if (tipoPlanificacionId.equals("2")) {
				tipoPlanificacionId = "1";
			}
			if (tipoPlanificacionId.equals("1")) {
				tipoPlanificacionId = "2";
			}
			if (tipoPlanificacionId.equals("3")) {
				tipoPlanificacionId = "3";
			}
			if (tipoPlanificacionId.equals("4")) {
				tipoPlanificacionId = "4";
			}

			return loadComboServidores(tipoPlanificacionId);
		}
		return loadComboServidores();
	}

	public String getVolver() {
		String volver = "buscarPlanificaciones.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
		}
		return volver;
	}
	
	public ServicioPlanificacion getServicioPlanificacion() {
		return servicioPlanificacion;
	}

	public void setServicioPlanificacion(ServicioPlanificacion servicioPlanificacion) {
		this.servicioPlanificacion = servicioPlanificacion;
	}

	public String getTipoParametroId() {
		return tipoParametroId;
	}

	public void setTipoParametroId(String tipoParametroId) {
		this.tipoParametroId = tipoParametroId;
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

	public ServicioServicio getServicioServicio() {
		return servicioServicio;
	}

	public void setServicioServicio(ServicioServicio servicioServicio) {
		this.servicioServicio = servicioServicio;
	}

	public List<KeyValueObject> getComboAplicaciones() {
		return comboAplicaciones;
	}

	public void setComboAplicaciones(List<KeyValueObject> comboAplicaciones) {
		this.comboAplicaciones = comboAplicaciones;
	}

	public List<KeyValueObject> getComboCanales() {
		return comboCanales;
	}

	public void setComboCanales(List<KeyValueObject> comboCanales) {
		this.comboCanales = comboCanales;
	}

	public List<KeyValueObject> getComboServicios() {
		return comboServicios;
	}

	public void setComboServicios(List<KeyValueObject> comboServicios) {
		this.comboServicios = comboServicios;
	}

	public PlanificacionBean getPlanificacion() {
		return planificacion;
	}

	public void setPlanificacion(PlanificacionBean planificacion) {
		this.planificacion = planificacion;
	}

	public List<PlanificacionBean> getListaPlanificaciones() {
		return listaPlanificaciones;
	}

	public void setListaPlanificaciones(List<PlanificacionBean> listaPlanificaciones) {
		this.listaPlanificaciones = listaPlanificaciones;
	}

	public ServicioCanal getServicioCanal() {
		return servicioCanal;
	}

	public void setServicioCanal(ServicioCanal servicioCanal) {
		this.servicioCanal = servicioCanal;
	}

	

	public String getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	public String getNAction() {
		return nAction;
	}

	public void setNAction(String action) {
		this.nAction = action;
	}

	public String getIdServidorPush() {
		return idServidorPush;
	}

	public void setIdServidorPush(String idServidorPush) {
		this.idServidorPush = idServidorPush;
	}

	public String getIdOrganismo() {
		return idOrganismo;
	}

	public void setIdOrganismo(String idOrganismo) {
		this.idOrganismo = idOrganismo;
	}

	public List<KeyValueObject> getComboBusquedaServidores() {
		return comboBusquedaServidores;
	}

	public void setComboBusquedaServidores(List<KeyValueObject> comboBusquedaServidores) {
		this.comboBusquedaServidores = comboBusquedaServidores;
	}

	public String getServicioId() {
		return servicioId;
	}

	public void setServicioId(String servicioId) {
		this.servicioId = servicioId;
	}

	public String getTipoPlanificacionId() {
		return tipoPlanificacionId;
	}

	public void setTipoPlanificacionId(String tipoPlanificacionId) {
		this.tipoPlanificacionId = tipoPlanificacionId;
	}

	public List<KeyValueObject> getComboTipoPlanificaciones() {
		return comboTipoPlanificaciones;
	}

	public void setComboTipoPlanificaciones(List<KeyValueObject> comboTipoPlanificaciones) {
		this.comboTipoPlanificaciones = comboTipoPlanificaciones;
	}

	public void setComboServidores(List<KeyValueObject> comboServidores) {
		this.comboServidores = comboServidores;
	}

	public String getIdPlanificacion() {
		return idPlanificacion;
	}

	public void setIdPlanificacion(String idPlanificacion) {
		this.idPlanificacion = idPlanificacion;
	}

	public ServicioProveedorSMS getServicioProveedorSMS() {
		return servicioProveedorSMS;
	}

	public void setServicioProveedorSMS(ServicioProveedorSMS servicioProveedorSMS) {
		this.servicioProveedorSMS = servicioProveedorSMS;
	}

	public ServicioReceptorSMS getServicioReceptorSMS() {
		return servicioReceptorSMS;
	}

	public void setServicioReceptorSMS(ServicioReceptorSMS servicioReceptorSMS) {
		this.servicioReceptorSMS = servicioReceptorSMS;
	}

	public ServicioServidorPush getServicioServidorPush() {
		return servicioServidorPush;
	}

	public void setServicioServidorPush(ServicioServidorPush servicioServidorPush) {
		this.servicioServidorPush = servicioServidorPush;
	}

	public ServicioAplicacion getServicioAplicacion() {
		return servicioAplicacion;
	}

	public void setServicioAplicacion(ServicioAplicacion servicioAplicacion) {
		this.servicioAplicacion = servicioAplicacion;
	}

	public ServicioServidor getServicioServidor() {
		return servicioServidor;
	}

	public void setServicioServidor(ServicioServidor servicioServidor) {
		this.servicioServidor = servicioServidor;
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

	public String getIdServidor() {
		return idServidor;
	}

	public void setIdServidor(String idServidor) {
		this.idServidor = idServidor;
	}

	public String getnAction() {
		return nAction;
	}

	public void setnAction(String nAction) {
		this.nAction = nAction;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	public boolean isModificaActivo() {
		return modificaActivo;
	}

	public void setModificaActivo(boolean modificaActivo) {
		this.modificaActivo = modificaActivo;
	}
	public List<KeyValueObject> getComboConfiguraciones() {
		return comboConfiguraciones;
	}

	public void setComboConfiguraciones(List<KeyValueObject> comboConfiguraciones) {
		this.comboConfiguraciones = comboConfiguraciones;
	}
	/**
	 * @return the idServidorWebPush
	 */
	public String getIdServidorWebPush() {
		return idServidorWebPush;
	}
	/**
	 * @param idServidorWebPush the idServidorWebPush to set
	 */
	public void setIdServidorWebPush(String idServidorWebPush) {
		this.idServidorWebPush = idServidorWebPush;
	}
	
}
