package es.mpr.plataformamensajeria.web.action.servidores;

import java.util.ArrayList;
import java.util.HashMap;
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

import es.mpr.plataformamensajeria.beans.ParametroServidorBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.TipoParametroBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoParametro;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.plataformamensajeria.util.SMTPConnectionTest;

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
@Controller("servidoresAction")
@Scope("prototype")
public class ServidoresAction extends PlataformaPaginationAction implements
		ServletRequestAware, Preparable {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(ServidoresAction.class);
	
	@Resource(name="servicioServidorImpl")
	private transient ServicioServidor servicioServidor;
	
	@Resource(name="servicioTipoParametroImpl")
	private transient ServicioTipoParametro servicioTipoParametro;
	
	@Resource(name="servicioParametroServidorImpl")
	private transient ServicioParametroServidor servicioParametroServidor;
	
	@Resource(name="servicioPlanificacionImpl")
	private transient ServicioPlanificacion servicioPlanificacion;
	
	@Resource(name = "plataformaMensajeriaProperties")
	private transient PlataformaMensajeriaProperties properties;

	List<KeyValueObject> comboTipoParametros = new ArrayList<>();
	
	private ParametroServidorBean parametroServidor;
	private ServidorBean servidor;
	private PlanificacionBean planificacionServidor;
	 
	public List<ServidorBean> listaServidores = null;
	private List<ParametroServidorBean> listaParametrosServidor = null;
	private List<PlanificacionBean> listaPlanificacionesServidor = null;
	ArrayList<TipoParametroBean> tiposParametros = new ArrayList<TipoParametroBean>();

	private String tipoParametroId;
	private String idServidor;
	private String resultCount;
	private String parametroServidorId;
	private String planificacionId;
	private String[] checkDelList;
	
	private String filtroBuscador;
	
//	private Boolean busqueda = false; 


	public String newSearch() throws BaseException {
		return SUCCESS;
	}
	
	///MIGRADO
	public String search() throws BaseException {
		
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		
		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para
														// ordenar
		
		if (servidor != null)
			if (servidor.getNombre() != null
					&& servidor.getNombre().length() <= 0)
				servidor.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServidorBean> result = servicioServidor.getServidores(
				inicio, (export)?-1:Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order, columnSort, servidor,Integer.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_SMTP",null)));
		Integer totalSize = result.getTotalList();

		listaServidores = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE",null), totalSize);
		
		if (!export) {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE",null), Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
		} else {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE",null), totalSize);
		}

		if (listaServidores != null && !listaServidores.isEmpty()) {
			for (int indice = 0; indice < listaServidores.size(); indice++) {

				ServidorBean servidor = listaServidores.get(indice);
				servidor.setNombre(StringEscapeUtils.escapeHtml(servidor
						.getNombre()));
				servidor.setDescripcion(StringEscapeUtils.escapeHtml(servidor
						.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	
	/////MIGRADO
	public String execute() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para
														// ordenar

		if (servidor != null)
			if (servidor.getNombre() != null
					&& servidor.getNombre().length() <= 0)
				servidor.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServidorBean> result = servicioServidor.getServidores(
				inicio, (export)?-1:Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order, columnSort, servidor, Integer.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_SMTP",null)));
		Integer totalSize = result.getTotalList();

		listaServidores = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE",null), totalSize);
		
		if (!export) {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE",null), Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
		} else {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE",null), totalSize);
		}

		if (listaServidores != null && !listaServidores.isEmpty()) {
			for (ServidorBean servidor : listaServidores) {
				servidor.setNombre(StringEscapeUtils.escapeHtml(servidor
						.getNombre()));
				servidor.setDescripcion(StringEscapeUtils.escapeHtml(servidor
						.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	////////MIGRADO
	public String create() throws BaseException {
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if (servidor != null) { 
			if (servidor.getIsActivo() != null
					&& servidor.getIsActivo().indexOf("'activo'") != -1) {
				servidor.setActivo(true);
			} else {
				servidor.setActivo(false);
			}
			if (validaServidor(servidor)) {
				Long idServidor = servicioServidor.newServidor(servidor, Integer.parseInt(properties.getProperty("generales.TIPO_SERVIDOR_SMTP",null)), source, accion, accionId);
				this.idServidor = idServidor.toString();
				addActionMessageSession(this
						.getText("plataforma.servidores.create.ok"));
			} else {
				return ERROR;
			}
		} else {
			addActionErrorSession(this
					.getText("plataforma.servidores.create.error"));
			return ERROR;
		}
		return SUCCESS;
	}

	/////MIGRADO
	private boolean validaServidor(ServidorBean servidor) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getNombre())) {
			addActionErrorSession(this
					.getText("plataforma.servidores.field.nombre.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getDescripcion())) {
			addActionErrorSession(this
					.getText("plataforma.servidores.field.descripcion.error"));
			sw = false;
		}
		return sw;
	}

	
	/////MIGRADO
	public String update() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES", null);
		
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		
		ServidorBean servidorBBDD = null;
		if (servidor == null) {
			addActionErrorSession(this.getText("plataforma.servidores.update.error"));
		} else {
			if (servidor.getServidorid() == null) {
				if (idServidor != null) {
					servidor.setServidorid(new Long(idServidor));
					servidorBBDD = servicioServidor.loadServidor(servidor);
				} else {
					String idServidor = (String) request.getAttribute("idServidor");
					
					if (idServidor != null) {
						servidor.setId(new Long(idServidor));
						servidorBBDD = servicioServidor.loadServidor(servidor);
					}
				}

			} else {
				servidorBBDD = servicioServidor.loadServidor(servidor);
			}
			if (servidorBBDD != null) {
				servidorBBDD.setNombre(servidor.getNombre());
				servidorBBDD.setDescripcion(servidor.getDescripcion());
				servidorBBDD.setActivo(servidor.getActivo());
				servidorBBDD.setPordefecto(servidor.getPordefecto());
			}
			if (validaServidor(servidorBBDD)) {
				
				servicioServidor.updateServidor(servidorBBDD, source, accion, accionId);
				addActionMessageSession(this.getText("plataforma.servidores.update.ok"));
			}
		}		
		return SUCCESS;
	}

	/////MIGRADO
	public String load() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if (idServidor == null)
			throw new BusinessException("EL idServidor recibido es nulo");
		
		try {
			servidor = new ServidorBean();
			servidor.setServidorid(new Long(idServidor));
			servidor = servicioServidor.loadServidor(servidor);
			return SUCCESS;
		} catch (NumberFormatException e) {
			String mensg = this.getText(
					"errors.action.organismo.loadOrganismo",
					new String[] { servidor.getServidorid().toString() });
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			String mensg = this.getText(
					"errors.action.organismo.loadOrganismo",
					new String[] { servidor.getServidorid().toString() });
			throw new BusinessException(mensg);
		}

	}

	
	////MIGRADO
	public String deleteParametroServidor() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PARAMETRO", null);
		
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		
		if (parametroServidorId == null) {
			addActionErrorSession(this.getText("plataforma.servidores.parametro.delete.error"));
		} else {
			parametroServidor = new ParametroServidorBean();
			parametroServidor.setParametroservidorid(new Long(parametroServidorId));
			servidor = new ServidorBean();
			servidor.setServidorid(new Long(idServidor));
			servidor = servicioServidor.loadServidor(servidor);
			
			//se actualiza para actualizar la fecha de modificado y modificadopor
			servicioParametroServidor.deleteParametroServidor(parametroServidor, source, accion, accionId, descripcion);
			addActionMessageSession(this.getText("plataforma.servidores.parametro.delete.ok"));
			
		}
		return SUCCESS;
	}

	
	/////MIGRADO
	public String deletePlanificacionServidor() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		
		if (planificacionId == null) {
			addActionErrorSession(this.getText("plataforma.servidores.planificacion.delete.error"));
		} else {
			planificacionServidor = new PlanificacionBean();
			planificacionServidor.setPlanificacionId(new Integer(
					planificacionId));
			servicioPlanificacion.deletePlanificacion(planificacionServidor, source, accion, accionId, descripcion);
			addActionMessageSession(this
					.getText("plataforma.servidores.planificacion.delete.ok"));
		}
		return SUCCESS;
	}

	
	////////MIGRADO
	public String delete() throws BaseException {
		String accionPlanificacion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String accionServidor = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionIdServidor = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES", null);
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if (idServidor == null) {
			addActionErrorSession(this
					.getText("plataforma.servidores.delete.error"));
		} else {
			servidor = new ServidorBean();
			servidor.setServidorid(new Long(idServidor));
			servicioServidor.deleteServidor(servidor, accionServidor, accionIdServidor, source, accionPlanificacion, accionIdPlanificacion, descripcionPlanificacion);
			addActionMessageSession(this
					.getText("plataforma.servidores.delete.ok"));
		}
		return SUCCESS;

	}

	/////MIGRADO
	public String deleteSelected() throws BaseException {
		String accionPlanificacion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdPlanificacion = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String accionServidor = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdServidor = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES", null);
		String descripcionPlanificacion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PLANIFICACION", null);
		
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if (checkDelList == null) {
			addActionErrorSession(this
					.getText("plataforma.servidores.deleteSelected.error"));
		} else {
			for (String idServidor : checkDelList) {
				servidor = new ServidorBean();
				servidor.setServidorid(new Long(idServidor));
				servicioServidor.deleteServidor(servidor, accionServidor, accionIdServidor, source, accionPlanificacion, accionIdPlanificacion, descripcionPlanificacion);
			}
			addActionMessageSession(this
					.getText("plataforma.servidores.deleteSelected.ok"));
		}
		return SUCCESS;

	}

	/////MIGRADO
	private boolean validaParametro(ParametroServidorBean parametroServidor) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmptyNumber(parametroServidor
				.getTipoparametroid().intValue())) {
			addActionErrorSession(this
					.getText("plataforma.servidores.parametro.add.parametroid.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(parametroServidor.getValor())) {
			addActionErrorSession(this
					.getText("plataforma.servidores.parametro.add.valor.error"));
			sw = false;
		}
		try {
			if (servicioParametroServidor
					.existeParametroServidor(parametroServidor)) {
				addActionErrorSession(this
						.getText("plataforma.servidores.parametro.add.existe"));
				sw = false;
			}
		} catch (BusinessException e) {
			logger.error("ServidoresAction - validaParametro:" + e);
		}
		return sw;
	}

	/////MIGRADO
	public String checkSMTPConnection() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		
		// Obtener los parametros configurados para el servidor y realizar una
		// validacion para comprobar que se han configurado todos
		String ip = null, user = null, password = null, secure = null, port = null, reqAuth= null;
		String msgError = this.getText("plataforma.servidores.checkparameters");
		List<ParametroServidorBean> listaParametrosServidor = servicioParametroServidor
				.getParametroServidorByServidorId(new Integer(idServidor)); 
		List<TipoParametroBean> listaTiposParametrosServidor = servicioTipoParametro
				.getTipoParametrosServidor();

		// Validar el numero de parametros configurados en el servidor
		// y numero de tipos de parametros
		HashMap<Integer, String> mapaParametrosServidor = new HashMap<Integer, String>();
		for (ParametroServidorBean parametroServidor : listaParametrosServidor) {
			mapaParametrosServidor.put(parametroServidor.getTipoparametroid().intValue(),
					parametroServidor.getValor());
		}
		boolean sw = true;
		for (TipoParametroBean tipoParametroBean : listaTiposParametrosServidor) {
			if (tipoParametroBean.getTipoparametroid()!=ParametroServidorBean.CONEXION_SEGURA&&
				tipoParametroBean.getTipoparametroid()!=ParametroServidorBean.REQ_AUTH&&
				tipoParametroBean.getTipoparametroid()!=ParametroServidorBean.USUARIO&&
				tipoParametroBean.getTipoparametroid()!=ParametroServidorBean.PASSWORD&&
					!mapaParametrosServidor.containsKey(tipoParametroBean
					.getTipoparametroid())) {
					sw = false;
					msgError += "<br/>- " + tipoParametroBean.getNombre();
			} else {
				if (ParametroServidorBean.IP == tipoParametroBean
						.getTipoparametroid()) {
					ip = mapaParametrosServidor.get(tipoParametroBean
							.getTipoparametroid());
				}
				if (ParametroServidorBean.USUARIO == tipoParametroBean
						.getTipoparametroid()) {
					user = mapaParametrosServidor.get(tipoParametroBean
							.getTipoparametroid());
				}
				if (ParametroServidorBean.PASSWORD == tipoParametroBean
						.getTipoparametroid()) {
					password = mapaParametrosServidor.get(tipoParametroBean
							.getTipoparametroid());
				}
				if (ParametroServidorBean.CONEXION_SEGURA == tipoParametroBean
						.getTipoparametroid()) {
					secure = mapaParametrosServidor.get(tipoParametroBean
							.getTipoparametroid());
				}
				if (ParametroServidorBean.PUERTO == tipoParametroBean
						.getTipoparametroid()) {
					port = mapaParametrosServidor.get(tipoParametroBean
							.getTipoparametroid());
				}
				if (ParametroServidorBean.REQ_AUTH == tipoParametroBean
						.getTipoparametroid()){
					reqAuth = mapaParametrosServidor.get(tipoParametroBean
							.getTipoparametroid());
				}
			}
		}
		if (sw) {
			// Validar conexion
			SMTPConnectionTest smtpTestConn = new SMTPConnectionTest();
			String validSMTPParams = smtpTestConn.checkConnectionClassic(
					ip, user, password, secure, port,reqAuth);

			if (validSMTPParams == null) {
				addActionMessageSession(this
						.getText("plataforma.servidores.checkparameters.ok"));
			} else {
				addActionErrorSession(this
						.getText("plataforma.servidores.checkparameters.error")
						+ ":<br/><br/>" + validSMTPParams);
			}

		} else {
			addActionErrorSession(msgError);
		}

		return SUCCESS;
	}

	/////MIGRADO
	public String addParametroServidor() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PARAMETRO", null);
		
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if (parametroServidor != null) {
			if (!validaParametro(parametroServidor)) {
				return ERROR;
			} else {
				servicioParametroServidor.newParametroServidor(parametroServidor, source, accion, accionId, descripcion);
				addActionMessageSession(this.getText("plataforma.servidores.parametro.add.ok"));
			}
		} else {
			addActionErrorSession(this.getText("plataforma.servidores.parametro.add.error"));
			return ERROR;
		}
		
		return SUCCESS;
	}

	//////MIGRADO
	public String addPlanificacion() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_SERVIDORES", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PLANIFICACION", null);
		
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if (planificacionServidor != null
				&& !PlataformaMensajeriaUtil
						.isEmptyNumber(planificacionServidor.getServidorId())) {
			if (planificacionValida(planificacionServidor)) {
				planificacionServidor.setActivo(true);
				planificacionServidor.setTipoPlanificacionId(new Integer(1));
				
				int valido = servicioPlanificacion.validaPlanificacionServidor(planificacionId, planificacionServidor.getServidorId(),
						planificacionServidor.getLunes(),planificacionServidor.getMartes(),planificacionServidor.getMiercoles(),
						planificacionServidor.getJueves(),planificacionServidor.getViernes(),planificacionServidor.getSabado(),
						planificacionServidor.getDomingo(),planificacionServidor.getHoraHasta(),planificacionServidor.getHoraDesde());
				
				if(valido == 1){
					servicioPlanificacion.newPlanificacion(planificacionServidor, source, accion, accionId, descripcion);
					addActionMessageSession(this
							.getText("plataforma.servidores.planificacion.add.ok"));

				}else if (valido == 0)
				{
			    	   addActionErrorSession("No se ha a&ntilde;adido la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
			    	   return ERROR;
				}
			} else {
				return ERROR;
			}
		} else {
			addActionErrorSession(this
					.getText("plataforma.servidores.planificacion.add.error"));
			return ERROR;
		}
		return SUCCESS;
	}

	

	/**
	 * Verifica que se ha introducido por lo menos un día de la semana y las
	 * horas de inicio y fin
	 * 
	 * @param planificacionServidor
	 */
	///MIGRADO
	private boolean planificacionValida(PlanificacionBean planificacionServidor) {
		boolean sw = true;
		if (planificacionServidor != null) {
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor
					.getHoraDesde())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getHoraHasta())) {
				addFieldErrorSession(this
						.getText("plataforma.servidores.planificacion.horas.error"));
				sw = false;

			}
			if (!PlataformaMensajeriaUtil.isEmpty(planificacionServidor
					.getHoraDesde())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getHoraHasta())) {
				addFieldErrorSession(this
						.getText("plataforma.servidores.planificacion.horaHasta.error"));
				sw = false;
			}
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor
					.getHoraDesde())
					&& !PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getHoraHasta())) {
				addFieldErrorSession(this
						.getText("plataforma.servidores.planificacion.horaDesde.error"));
				sw = false;
			}
			if (sw) {
				if (!validoFormatoHora(planificacionServidor.getHoraDesde())) {
					addFieldErrorSession(this
							.getText("plataforma.servidores.planificacion.horaDesde.formato.error"));
					sw = false;
				}
				if (!validoFormatoHora(planificacionServidor.getHoraHasta())) {
					addFieldErrorSession(this
							.getText("plataforma.servidores.planificacion.horaHasta.formato.error"));
					sw = false;
				}
				if (sw) {
					if (!validoHoras(planificacionServidor.getHoraDesde(),
							planificacionServidor.getHoraHasta())) {
						sw = false;
					}
				}
			}
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor
					.getLunes())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getMartes())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getMiercoles())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getJueves())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getViernes())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getSabado())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getDomingo())) {
				addFieldErrorSession(this
						.getText("plataforma.servidores.planificacion.dias.error"));
				sw = false;
			}

		}
		return sw;
	}

	/////MIGRADO
	private boolean validoHoras(String horaDesde, String horaHasta) {
		boolean sw = true;
		String[] horaDesdeArray = horaDesde.split(":");
		String[] horaHastaArray = horaHasta.split(":");
		int hDesde = new Integer(horaDesdeArray[0]);
		int mDesde = new Integer(horaDesdeArray[1]);
		int hHasta = new Integer(horaHastaArray[0]);
		int mHasta = new Integer(horaHastaArray[1]);
		if (hDesde > hHasta) {
			addFieldErrorSession(this
					.getText("plataforma.servidores.planificacion.horaDesde.menor.error"));
			sw = false;
		} else if (hDesde == hHasta && mDesde > mHasta) {
			addFieldErrorSession(this
					.getText("plataforma.servidores.planificacion.horaDesde.menor.error"));
			sw = false;
		} else if (hDesde == hHasta && mDesde == mHasta) {
			addFieldErrorSession(this
					.getText("plataforma.servidores.planificacion.horas.iguales.error"));
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
	
/////MIGRADO
	@Override
	public void prepare() throws Exception {
		if (idServidor != null) {
			tiposParametros = (ArrayList<TipoParametroBean>) servicioTipoParametro
					.getTipoParametrosServidor();
			comboTipoParametros = getComboValues();
			listaParametrosServidor = getParametrosServidor();
			listaPlanificacionesServidor = getLoadPlanificacionesServidor();
		}
	}

	//////migrado
	/**
	 * Método que resuelve el lugar donde tiene que volver
	 */
	public String getVolver() {
		String volver = "buscarServidores.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from)
				&& !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
		}

		return volver;
	}
	
	
    ///MIGRADO
	private List<PlanificacionBean> getLoadPlanificacionesServidor() {
		List<PlanificacionBean> lista = null;
		if (idServidor != null && idServidor.length() > 0) {
			try {
				lista = servicioPlanificacion.getPlanificacionesByServidorId(new Integer(idServidor));
			} catch (NumberFormatException e) {
				logger.error("ServidoresActioin - getLoadPlanificacionesServidor:" + e);
			} catch (BusinessException e) {
				logger.error("ServidoresActioin - getLoadPlanificacionesServidor:" + e);
			}
		} else if (servidor != null && servidor.getServidorid() != null) {
			try {
				lista = servicioPlanificacion
						.getPlanificacionesByServidorId(new Integer(idServidor));
			} catch (NumberFormatException e) {
				logger.error("ServidoresActioin - getLoadPlanificacionesServidor:" + e);
			} catch (BusinessException e) {
				logger.error("ServidoresActioin - getLoadPlanificacionesServidor:" + e);
			}
		}
		return lista;
	}

	///////MIGRADO
	private List<ParametroServidorBean> getParametrosServidor() {
		List<ParametroServidorBean> lista = null;
		if (idServidor != null && idServidor.length() > 0) {
			try {
				lista = servicioParametroServidor
						.getParametroServidorByServidorId(new Integer(
								idServidor));
			} catch (NumberFormatException e) {
				logger.error("ServidoresActioin - getParametrosServidor:" + e);
			} catch (BusinessException e) {
				logger.error("ServidoresActioin - getParametrosServidor:" + e);
			}
		} else if (servidor != null && servidor.getServidorid() != null) {
			try {
				lista = servicioParametroServidor
						.getParametroServidorByServidorId(servidor
								.getServidorid().intValue());
			} catch (NumberFormatException e) {
				logger.error("ServidoresActioin - getParametrosServidor:" + e);
			} catch (BusinessException e) {
				logger.error("ServidoresActioin - getParametrosServidor:" + e);
			}
		}
		return lista;
	}

/////MIGRADO
	private List<KeyValueObject> getComboValues() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option = null;
		
		ArrayList<TipoParametroBean> keys = null;
		try {
			keys = (ArrayList<TipoParametroBean>) servicioTipoParametro
					.getTipoParametrosServidor();
		} catch (BusinessException e) {
			logger.error("ServidoresActioin - getComboValues:" + e);
		}

		if (keys != null && keys.size() > 0)
			for (TipoParametroBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getTipoparametroid().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		return result;
	}


	
	public List<KeyValueObject> getComboTipoParametros() {
		return comboTipoParametros;
	}

	public void setComboTipoParametros(List<KeyValueObject> comboTipoParametros) {
		this.comboTipoParametros = comboTipoParametros;
	}

	public List<ServidorBean> getListaServidores() {
		return listaServidores;
	}

	public void setListaServidores(List<ServidorBean> listaServidores) {
		this.listaServidores = listaServidores;
	}

	public ServicioServidor getServicioServidor() {
		return servicioServidor;
	}

	public void setServicioServidor(ServicioServidor servicioServidor) {
		this.servicioServidor = servicioServidor;
	}

	public ServicioTipoParametro getServicioTipoParametro() {
		return servicioTipoParametro;
	}

	public void setServicioTipoParametro(
			ServicioTipoParametro servicioTipoParametro) {
		this.servicioTipoParametro = servicioTipoParametro;
	}

	public ServicioParametroServidor getServicioParametroServidor() {
		return servicioParametroServidor;
	}

	public void setServicioParametroServidor(
			ServicioParametroServidor servicioParametroServidor) {
		this.servicioParametroServidor = servicioParametroServidor;
	}

	public String getTipoParametroId() {
		return tipoParametroId;
	}

	public void setTipoParametroId(String tipoParametroId) {
		this.tipoParametroId = tipoParametroId;
	}

	public ServidorBean getServidor() {
		return servidor;
	}

	public void setServidor(ServidorBean servidor) {
		this.servidor = servidor;
	}

	public String getIdServidor() {
		return idServidor;
	}

	public void setIdServidor(String idServidor) {
		this.idServidor = idServidor;
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

	public void setListaParametrosServidor(
			List<ParametroServidorBean> listaParametrosServidor) {
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
		if (parametroServidor != null
				&& parametroServidor.getParametroservidorid() != null) {
			return parametroServidor.getParametroservidorid().toString();
		} else {
			return parametroServidorId;
		}

	}

	public ServicioPlanificacion getServicioPlanificacion() {
		return servicioPlanificacion;
	}

	public void setServicioPlanificacion(
			ServicioPlanificacion servicioPlanificacion) {
		this.servicioPlanificacion = servicioPlanificacion;
	}

	public List<PlanificacionBean> getListaPlanificacionesServidor() {
		return listaPlanificacionesServidor;
	}

	public void setListaPlanificacionesServidor(
			List<PlanificacionBean> listaPlanificacionesServidor) {
		this.listaPlanificacionesServidor = listaPlanificacionesServidor;
	}

	public ArrayList<TipoParametroBean> getTiposParametros() {
		return tiposParametros;
	}

	public void setTiposParametros(ArrayList<TipoParametroBean> tiposParametros) {
		this.tiposParametros = tiposParametros;
	}
	
	public String getPlanificacionId() {
		return planificacionId;
	}

	public void setPlanificacionId(String planificacionId) {
		this.planificacionId = planificacionId;
	}

	/**
	 * @return the filtroBuscador
	 */
	public String getFiltroBuscador() {
		return filtroBuscador;
	}

	/**
	 * @param filtroBuscador the filtroBuscador to set
	 */
	public void setFiltroBuscador(String filtroBuscador) {
		this.filtroBuscador = filtroBuscador;
	}


}
