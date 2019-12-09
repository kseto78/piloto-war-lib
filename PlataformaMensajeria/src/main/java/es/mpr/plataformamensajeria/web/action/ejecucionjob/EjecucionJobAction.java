package es.mpr.plataformamensajeria.web.action.ejecucionjob;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

import es.minhap.plataformamensajeria.iop.beans.ProcesosBean;
import es.minhap.plataformamensajeria.iop.beans.ProcesosManualesBean;
import es.minhap.plataformamensajeria.iop.managerimpl.TblProcesosManagerImpl;
import es.minhap.sim.model.ViewServicios;
import es.mpr.plataformamensajeria.beans.GestionEnvioBean;
import es.mpr.plataformamensajeria.beans.JobBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.quartz.Planificador;
import es.mpr.plataformamensajeria.quartz.jobs.AnularMensajesJob;
import es.mpr.plataformamensajeria.quartz.jobs.EstadisticasConsolidadasJob;
import es.mpr.plataformamensajeria.quartz.jobs.HistorificacionJob;
import es.mpr.plataformamensajeria.quartz.jobs.InformesServiciosJob;
import es.mpr.plataformamensajeria.quartz.jobs.RecuperarInforDIRJob;
import es.mpr.plataformamensajeria.quartz.jobs.ReenviarMensajesPendientesJob;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesosManuales;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

// TODO: Auto-generated Javadoc
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
@Controller("ejecucionJobAction")
@Scope("prototype")
public class EjecucionJobAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	protected static final String EJECUCIONJOBACT = "EjecucionJobAction - load:";

	protected static final String PLATAFORMADOTEJ = "plataforma.ejecucionjob.field.aeatgiss.error";

	protected static final String R_CONST_REF = "10";

	protected static final String LOGDOTACCION_AC = "log.ACCION_ACTUALIZAR";

	protected static final String BLANK = " ";

	protected static final String REENVIARMENSAJE = "ReenviarMensajesPendientesJob";

	protected static final String R_CONST_0 = ",";

	protected static final String R_CONST_1 = "0";

	protected static final String R_CONST_2 = "1";

	protected static final String R_CONST_3 = "2";

	protected static final String R_CONST_4 = "3";

	protected static final String R_CONST_5 = "4";

	protected static final String LOGDOTSOURCE_JO = "log.SOURCE_JOBS";

	protected static final String R_CONST_6 = "5";

	protected static final String R_CONST_7 = "6";

	protected static final String R_CONST_8 = "7";

	protected static final String R_CONST_9 = ";";

	protected static final String R_CONST_10 = "20";

	protected static final String PLATAFORMADOTEJ0 = "plataforma.ejecucionjob.update.error.planificarNodo";

	protected static final String LOGDOTSOURCE_PR = "log.SOURCE_PROCESOS_MANUALES";

	protected static final String N = "\\n";

	protected static final String R = "\\r";

	protected static final String R_CONST_11 = "50";

	protected static final String GENERALESDOTREQ = "generales.REQUEST_ATTRIBUTE_TOTALSIZE";

	protected static final String LOGDOTACCIONID_REF = "log.ACCIONID_ELIMINAR";

	protected static final String ERRORSDOTACTION = "errors.action.ejecucionjobs.loadProcesoServicio";

	protected static final String BLANK0 = "0 ";

	protected static final String R_CONST_12 = "100";

	protected static final String LOGDOTACCION_EL = "log.ACCION_ELIMINAR";

	protected static final String LOGDOTACCIONID_0 = "log.ACCIONID_ACTUALIZAR";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(EjecucionJobAction.class);

	/**  servicio servicio. */
	@Resource(name = "servicioServicioImpl")
	private transient ServicioServicio servicioServicio;
	
	/**  session. */
	@SuppressWarnings("rawtypes")
	transient Map session;
	
	/** Constante RECOVERY. */
	private static final String RECOVERY = "recovery";
	
	/**  estadisticas consolidadas job. */
	@Resource(name = "estadisticasConsolidadasJob")
	private transient EstadisticasConsolidadasJob estadisticasConsolidadasJob;
	
	/**  historificacion job. */
	@Resource(name = "historificacionJob")
	private transient HistorificacionJob historificacionJob;
	
	/**  historificacion job. */
	@Resource(name = "reenviarMensajesPendientesJob")
	private transient ReenviarMensajesPendientesJob reenviarMensajesPendientesJob;
	
	/**  informes servicios job. */
	@Resource(name = "anularMensajesJob")
	private transient AnularMensajesJob anularMensajesJob;
	
	/**  informes servicios job. */
	@Resource(name = "informesServiciosJob")
	private transient InformesServiciosJob informesServiciosJob;
	
	/**  recuperar infor DIR job. */
	@Resource(name = "recuperarInforDIRJob")
	private transient RecuperarInforDIRJob recuperarInforDIRJob;

	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private transient PlataformaMensajeriaProperties properties;
	
	/**  lista gestion envios. */
	private List<GestionEnvioBean> listaGestionEnvios = null;
	
	/**  lista procesos. */
	private List<ProcesosBean> listaProcesos = null;
	
	/**  lista procesos. */
	private List<ProcesosManualesBean> listaProcesosManuales = null;

	/**  combo page size. */
	transient List<KeyValueObject> comboPageSize = new ArrayList<>();
	
	/** Constante GENERALES_REQUEST_ATTRIBUTE_PAGESIZE. */
	private static final String GENERALES_REQUEST_ATTRIBUTE_PAGESIZE = "generales.REQUEST_ATTRIBUTE_PAGESIZE";
	
	/**  result count. */
	private String resultCount;

	/**  page size. */
	private Integer pageSize = 20;
	
	/**  id proceso. */
	private String procesosId;
	
	/**  id proceso manual. */
	private String procesoManualId;
	
	/**  proceso manual */
	private ProcesosManualesBean procesoManual;
	
		/**  proceso. */
	private ProcesosBean proceso; 
	
	/**  combo numero de dias. */
	List<KeyValueObject> comboNumeroDias = new ArrayList<>();
	
	/**  combo numero de dias. */
	List<KeyValueObject> comboListadoJobs = new ArrayList<>();

	/** Constante TABLE_ID. */
	private static final String TABLE_ID = "tableId";
	
	/** Constante TABLE_PROCESOS. */
	private static final String TABLE_PROCESOS = "tablaProcesos";	
	
	/**  servicio gestion envios. */
	@Resource(name = "servicioGestionEnviosImpl")
	private transient ServicioGestionEnvios servicioGestionEnvios;
	
	@Resource(name = "servicioProcesosImpl")
	private transient ServicioProcesos servicioProcesos;
	
	@Resource(name = "servicioProcesosManualesImpl")
	private transient ServicioProcesosManuales servicioProcesosManuales;
	
	@Resource(name = "TblProcesosManagerImpl")
	private TblProcesosManagerImpl tblProcesosManagerImpl;
	
//	@Resource(name = "pushServiceImpl")

	/**  job bean. */
	private JobBean jobBean;
	
	/** Constante INFO_USER. */
	private static final String INFO_USER = "infoUser";

	/** Constante NO_USER. */
	private static final String NO_USER = "noUser";

	/**  combo servicios. */
	transient List<KeyValueObject> comboServicios = new ArrayList<>();
	
	/**  combo jobs. */
	transient List<KeyValueObject> comboJobs = new ArrayList<>();
	
	/** Constante PROC_HIST. */
	private static final String PROC_HIST = "HISTORIFICACION";
	
	/** Constante PROC_CONS. */
	private static final String PROC_CONS = "CONSERVACION";

	/** Constante PROC_INFORMES. */
	private static final String PROC_INFORMES = "INFORMES_SERVICIOS";
	
	/** Constante PROC_ANULACION_MENSAJES. */
	private static final String PROC_ANULACION_MENSAJES = "ANULACION MENSAJES";
	
	/** Constante PROC_DIR3. */
	private static final String PROC_DIR3 = "DIR3";
	
	/** Constante PROC_REENVIO. */
	private static final String PROC_REENVIO = "REENVIO";
	
	/**  combo servicio organismos. */
	transient List<KeyValueObject> comboServicioOrganismos = new ArrayList<>();
	
	/**  combo lista jobs. */
	transient List<KeyValueObject> combolistaJobs = new ArrayList<>();
	
	
	/** datos servicios */
	private String datosServicios;
	
	/** datos servicios */
	private String datosServiciosExcluidos;
	
	/** body tabla servicios */
	private String tieneServicios;
		
	private String tieneServiciosAutomatica;
	
	/**  check del list. */
	private String[] checkDelList;
	
	private static final String EJECUCION_PROCESOS_AUTOMATICA_NODO_SET_ESTADO_PROCESO = "ejecucion.procesos.automatica.nodo.setEstadoProceso";
		
	private static final String NODO_ACTIVO = "jobNodoActivos.activacion";
	
	private static final String JOB_PARAMETRO = "job=";
	
	private static final String EJECUCIONJOB_UPDATE_OK = "plataforma.ejecucionjob.update.ok";
	
	private static final String PROCESOMANUAL_UPDATE_OK = "plataforma.procesomanual.update.ok";
	
	private String propertyServiciosAnularMensajes;
		

	/**
	 * Ejecucion job action.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	/////MIGRADO
	public String ejecucionJobAction() throws BaseException {
		if (getRequest().getSession().getAttribute(EjecucionJobAction.INFO_USER) == null) {
			return EjecucionJobAction.NO_USER;
		}

		comboServicios = getComboServicios();
		comboJobs = getComboJobs();
		return SUCCESS;
	}
	
	/**
	 * Seleccionar job action.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	// ///MIGRADO
	public String seleccionarJobAction() throws BaseException {
		if (getRequest().getSession().getAttribute(EjecucionJobAction.INFO_USER) == null) {
			return EjecucionJobAction.NO_USER;
		}
		
		try {
			ServletContext servletContext= getRequest().getSession().getServletContext();
			if(jobBean != null){
				if (PROC_HIST.equals(jobBean.getNombreJob())){
					historificacionJob.lanzarJob(servletContext, jobBean);
				}else if (PROC_CONS.equals(jobBean.getNombreJob())){
					estadisticasConsolidadasJob.lanzarJob(servletContext, jobBean);
				}else if (PROC_INFORMES.equals(jobBean.getNombreJob())){
					informesServiciosJob.lanzarJob(servletContext, jobBean);
				}else if (PROC_ANULACION_MENSAJES.equals(jobBean.getNombreJob())){
					anularMensajesJob.lanzarJob(servletContext, jobBean);
				} else{
				
					recuperarInforDIRJob.lanzarJob(servletContext, jobBean);
				}
			}

		} catch (Exception e1) {
			logger.error("EjecucionJobAction - seleccionarJobAction", e1);
			addActionErrorSession("Ha ocurrido un error en la ejecucion del job, pongase en contacto con el administrador");
		}		
		if(jobBean != null){
			addActionMessageSession("Job ejecutado correctamente");
		}
		return SUCCESS;
	}
	
	

	public String createProcesoManual() throws BusinessException {
		
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty(LOGDOTSOURCE_JO, null);
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("EjecucionJobAction - createProcesoManual:" + e);
			return EjecucionJobAction.NO_USER;
		}
		if (procesoManual != null) {
				
			if (!validaObligatoriosProcesoManual(procesoManual)) {
				return ERROR;
			}			
			Integer id = servicioProcesosManuales.newProcesoManual(procesoManual, source, accion, accionId);
			procesoManualId = id.toString();
			addActionMessageSession(this.getText("plataforma.procesomanual.create.ok"));
			
		} else {
			addActionErrorSession(this.getText("plataforma.procesomanual.create.error"));
		}

		return SUCCESS;
		
	}

	public String inicio(){
		if(null == jobBean){
			jobBean = new JobBean();
			jobBean.setNombreJob(PROC_HIST);
		}
		
		return SUCCESS;
	}
/**
 * Cargar servicios conservacion event.
 *
 * @return the string
 * @throws BaseException the base exception
 * @throws ParseException 
 */
/////MIGRADO
	public String cargarServiciosConservacionEvent() throws BaseException, ParseException {
		if (getRequest().getSession().getAttribute(EjecucionJobAction.INFO_USER) == null) {
			return EjecucionJobAction.NO_USER;
		}

		if (null != jobBean){
			if(PROC_HIST.equals(jobBean.getNombreJob())){
				comboServicios = getComboServicios();
			}else if(PROC_CONS.equals(jobBean.getNombreJob())){
				comboServicios = getComboServiciosConservacion();
			} else if(PROC_ANULACION_MENSAJES.equals(jobBean.getNombreJob())){
				propertyServiciosAnularMensajes = getPropertyServiciosAnularMensajes();
			}else if(PROC_REENVIO.equals(jobBean.getNombreJob())){
				PaginatedList<ProcesosBean> result;
				
				PaginatedList<ProcesosManualesBean> resultProcesosManuales;
				
				int page = getPage(EjecucionJobAction.TABLE_ID); 
				// Pagina a mostrar
				String order = getOrder(EjecucionJobAction.TABLE_ID); 
				// Ordenar de modo ascendente o
				String columnSort = getColumnSort(EjecucionJobAction.TABLE_ID); 
				// Columna usada para ordenar
				
				String orderProcesos = getOrder(EjecucionJobAction.TABLE_PROCESOS); 
				// Ordenar de modo ascendente o
				String columnSortProcesos = getColumnSort(EjecucionJobAction.TABLE_PROCESOS); 
				// Columna usada para ordenar
				
				ProcesosBean procesosBean = new ProcesosBean();
				
				int inicio = (page - 1) * pageSize;
				boolean export = false;
				result = servicioProcesos.getProcesos(export ? -1 : pageSize, orderProcesos,
						columnSortProcesos, procesosBean);				
				
				listaProcesos = result.getPageList();				
				
				
				ProcesosManualesBean procesosManualesBean = new ProcesosManualesBean();
				resultProcesosManuales = servicioProcesosManuales.getProcesosManuales(inicio,export ? -1 : pageSize, order,
						columnSort, procesosManualesBean);
				Integer totalSize = resultProcesosManuales.getTotalList();
				listaProcesosManuales = resultProcesosManuales.getPageList();
				resultCount = (totalSize != null) ? totalSize.toString() : R_CONST_1;
				getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ, null), totalSize);
				getRequest().setAttribute(properties.getProperty(EjecucionJobAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null),
						pageSize);

			}
		}else{
			comboServicios = getComboServicios();
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
	public String deleteProcesoManual() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_EL, null);		
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_REF, null));		
		String source = properties.getProperty(LOGDOTSOURCE_PR, null);
		if (getRequest().getSession().getAttribute(EjecucionJobAction.INFO_USER) == null) {
			return NO_USER;
		}
		if (procesoManualId == null) {
			addActionErrorSession(this.getText("plataforma.ejecucionjob.delete.error"));			
			
		} else {
			procesoManual = new ProcesosManualesBean();
			procesoManual.setProcesosManualesId(Integer.valueOf(procesoManualId));
			
			servicioProcesosManuales.deleteProcesoManual(Long.parseLong(procesoManualId), source, accion, accionId);
			addActionMessageSession(this.getText("plataforma.procesomanual.delete.ok"));
		}
		return SUCCESS;

	}
	
	public String loadMensajesReenvios() throws BusinessException{
		PaginatedList<GestionEnvioBean> result = null;		
		Integer totalSize;
		
		String serviciosExcluidos = "";
		if(datosServiciosExcluidos != null) {
			serviciosExcluidos = datosServiciosExcluidos;
		}
		
		
		if (getRequest().getSession().getAttribute(EjecucionJobAction.INFO_USER) == null) {
			return EjecucionJobAction.NO_USER;
		}

		// Carga Destinatarios_mensajes		
		int page = getPage(EjecucionJobAction.TABLE_ID); 
		// Pagina a mostrar
		int inicio = (page - 1) * pageSize;
		String order = getOrder(EjecucionJobAction.TABLE_ID); 
		// Ordenar de modo ascendente o
		// descendente
		String columnSort = getColumnSort(EjecucionJobAction.TABLE_ID); 
		// Columna usada para
		// ordenar
		GestionEnvioBean gestionEnvioBean = new GestionEnvioBean();
		
		Date fechaInicio = null;
		Date fechaFin = null;
		
		if (null != jobBean && null != jobBean.getFecha()) {
				
			fechaInicio = jobBean.getFecha();			
		}else{
			int numeroDiasAtras;
			if(null != jobBean && jobBean.getParametro1() != null){
				numeroDiasAtras = Integer.parseInt(jobBean.getParametro1());
			}else if(proceso != null && proceso.getParametro1() != null){
				numeroDiasAtras = Integer.parseInt(proceso.getParametro1());
			}else{
				numeroDiasAtras=7;
			}			
		    Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.DATE, -numeroDiasAtras);
		    fechaInicio = cal.getTime();
		}
		if (null != jobBean && null != jobBean.getFechaFin()) {
			fechaFin = jobBean.getFechaFin();
		} else {
			fechaFin = new Date();
		}

		gestionEnvioBean.setEstadoId(3L); 
		//Estado pendiente de envio
		gestionEnvioBean.setFechaDesde(fechaInicio);
		gestionEnvioBean.setFechaHasta(fechaFin);
		
		
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		
		result = servicioGestionEnvios.getGestionDeEnviosDestinatariosReenvioJob(inicio,
				export ? -1 : pageSize, order,
				columnSort, gestionEnvioBean, request, serviciosExcluidos,datosServicios);
		
				
		
		totalSize = result.getTotalList();
		listaGestionEnvios = result.getPageList();
		
		resultCount = (totalSize != null) ? totalSize.toString() : R_CONST_1;
		getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ, null), totalSize);
		getRequest().setAttribute(properties.getProperty(EjecucionJobAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null),
				pageSize);
		
		return SUCCESS;
	}
	
	/**
	 * Load.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 * @throws ParseException 
	 */
	///MIGRADO
	public String load() throws BaseException, ParseException {
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error(EJECUCIONJOBACT + e);
			return EjecucionJobAction.NO_USER;
		}
		if (procesosId == null) {
			throw new BusinessException("El procesoId recibido es nulo");
		}
		try {
			proceso = new ProcesosBean();
			proceso.setProcesosId(Integer.valueOf(procesosId));
			proceso = servicioProcesos.loadProceso(proceso);
			
			if ( proceso.getParametro2() != null && !"".equals(proceso.getParametro2()) ){
				List<String> list = Arrays.asList(proceso.getParametro2().split("\\s*,\\s*"));
				
				StringBuilder infoServicio = new StringBuilder(); 
				for(String serv : list){
						
					ServicioBean sBean = new ServicioBean();
					sBean.setServicioId(Integer.valueOf(serv));
					ServicioBean servBean = servicioServicio.loadServicio(sBean);
					String act = servBean.getServicioId() + R_CONST_0 + servBean.getNombre() + R_CONST_0+ servBean.getDescripcion()+R_CONST_9;
					infoServicio.append(act);					
				}
				tieneServicios = infoServicio.toString().replace(N, "").replace(R, "");
				tieneServicios = tieneServicios.substring(0, tieneServicios.length() -1);
			}
			
			addActionWarningMessageSession(properties.getProperty("plataforma.jobs.update.aviso", null));
						
			return SUCCESS;
		} catch (NumberFormatException | BusinessException e) {
			String mensg = this.getText(ERRORSDOTACTION, new String[] { proceso.getProcesosId().toString() });
			logger.error(EJECUCIONJOBACT + e);
			throw new BusinessException(mensg);
		} 

	}
	
	////MIGRADO
	private boolean validaObligatoriosProcesoManual(ProcesosManualesBean procesoManual) {
		boolean sw = true;		
		if (PlataformaMensajeriaUtil.isEmpty(procesoManual.getNombre())) {
			addActionErrorSession(this.getText("plataforma.procesomanual.field.nombre.error"));
			sw = false;
		}
		
		return sw;
	}
	
////MIGRADO
	private boolean validaObligatoriosProcesoAutomatico(ProcesosBean proceso) {
		boolean sw = true;		
		if (PlataformaMensajeriaUtil.isEmpty(proceso.getNombre())) {
			addActionErrorSession(this.getText("plataforma.ejecucionjob.field.nombre.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(proceso.getParametro2())) {
			addActionErrorSession(this.getText(PLATAFORMADOTEJ));
			sw = false;
		}
		if (!PlataformaMensajeriaUtil.isEmpty(proceso.getParametro2())) {
			List<String> serviciosAeatGissProperty = new ArrayList<>(Arrays.asList(properties.getProperty("activemq.job.cronReenvio.serviciosExcluidos", null).trim().split(R_CONST_0)));
			List<String> serviciosAeatGiss= new ArrayList<>(Arrays.asList(proceso.getParametro2().split(R_CONST_0)));
				
			Boolean encontrado;
			for(String servProperty:serviciosAeatGissProperty){
				encontrado = false;
				for(String servProceso:serviciosAeatGiss){
					if(servProperty.equals(servProceso)){
						encontrado = true;
					}								
				}				
				if(!encontrado){
					addActionErrorSession(this.getText(PLATAFORMADOTEJ));
					sw = false;
				}
					
			}
			
			
			
		}
		
		return sw;
	}
	
	/**
	 * Load.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 * @throws ParseException 
	 */
	///MIGRADO
	public String loadProcesoManual() throws BaseException, ParseException {
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("EjecucionJobAction - load:ProcesoManual" + e);
			return EjecucionJobAction.NO_USER;
		}
		if (procesoManualId == null) {
			throw new BusinessException("El procesoManualId recibido es nulo");
		}
		try {
			procesoManual = new ProcesosManualesBean();
			procesoManual.setProcesosManualesId(Integer.valueOf(procesoManualId));
			procesoManual = servicioProcesosManuales.loadProcesoManual(procesoManual);
			
			if ( procesoManual.getParametro2() != null && !"".equals(procesoManual.getParametro2()) ){
				List<String> list = Arrays.asList(procesoManual.getParametro2().split("\\s*,\\s*"));
				
				StringBuilder infoServicio = new StringBuilder(); 
				for(String serv : list){
						
					ServicioBean sBean = new ServicioBean();
					sBean.setServicioId(Integer.valueOf(serv));
					ServicioBean servBean = servicioServicio.loadServicio(sBean);
					String act = servBean.getServicioId() + R_CONST_0 + servBean.getNombre() + R_CONST_0+ servBean.getDescripcion()+R_CONST_9;
					infoServicio.append(act);					
				}
				tieneServicios = infoServicio.toString().replace(N, "").replace(R, "");
				tieneServicios = tieneServicios.substring(0, tieneServicios.length() -1);
			}	
			
			ProcesosBean proceso = new ProcesosBean();
			proceso.setNombreClase(REENVIARMENSAJE);
			proceso = servicioProcesos.loadProcesoNombreClase(proceso);
			if ( proceso.getParametro2() != null && !"".equals(proceso.getParametro2()) ){
				List<String> list = Arrays.asList(proceso.getParametro2().split("\\s*,\\s*"));
				
				StringBuilder infoServicio = new StringBuilder(); 
				for(String serv : list){
						
					ServicioBean sBean = new ServicioBean();
					sBean.setServicioId(Integer.valueOf(serv));
					ServicioBean servBean = servicioServicio.loadServicio(sBean);
					String act = servBean.getServicioId() + R_CONST_0 + servBean.getNombre() + R_CONST_0+ servBean.getDescripcion()+R_CONST_9;
					infoServicio.append(act);					
				}
				tieneServiciosAutomatica = infoServicio.toString().replace(N, "").replace(R, "");
				tieneServiciosAutomatica = tieneServiciosAutomatica.substring(0, tieneServiciosAutomatica.length() -1);
			}	
			
						
			return SUCCESS;
		} catch (NumberFormatException | BusinessException e) {
			String mensg = this.getText(ERRORSDOTACTION, new String[] { proceso.getProcesosId().toString() });
			logger.error(EJECUCIONJOBACT + e);
			throw new BusinessException(mensg);
		} 

	}
	
	public String ejecutarJob() throws BusinessException, JobExecutionException{
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error(EJECUCIONJOBACT + e);
			return EjecucionJobAction.NO_USER;
		}
		
		try {
			
			if(procesosId !=null && !"".equals(procesosId)){
				proceso = new ProcesosBean();
				proceso.setProcesosId(Integer.valueOf(procesosId));
				proceso = servicioProcesos.loadProceso(proceso);
				if(null != proceso.getParametro1() && !"".equals(proceso.getParametro1()) ){
					jobBean = new JobBean();
					jobBean.setParametro1(proceso.getParametro1());
				}				
			}
			if(proceso !=null && proceso.getProcesosId() !=null){
				proceso = servicioProcesos.loadProceso(proceso);
			}
			if(procesoManual != null && procesoManual.getProcesoId() != null) {
				proceso = new ProcesosBean();
				proceso.setProcesosId(procesoManual.getProcesoId());
				proceso = servicioProcesos.loadProceso(proceso);			
			}
			
			if(procesoManualId !=null && !"".equals(procesoManualId)){
				procesoManual = new ProcesosManualesBean();
				procesoManual.setProcesosManualesId(Integer.valueOf(procesoManualId));
				procesoManual = servicioProcesosManuales.loadProcesoManual(procesoManual);
				if(null != procesoManual.getParametro2() && !"".equals(procesoManual.getParametro2()) ){
					datosServicios = procesoManual.getParametro2();
				}
				if(null != procesoManual.getParametro1() && !"".equals(procesoManual.getParametro1()) ){
					jobBean = new JobBean();
					jobBean.setParametro1(procesoManual.getParametro1());
				}
				proceso = new ProcesosBean();
				proceso.setProcesosId(procesoManual.getProcesoId());
				proceso = servicioProcesos.loadProceso(proceso);
			}	
			
			if("En ejecucion...".equals(proceso.getEstado())){
				addActionErrorSession(this.getText("errors.action.ejecucionjobs.estadoEjecucion"));
				return SUCCESS;
			}
			
			if(REENVIARMENSAJE.equals(proceso.getNombreClase())){
				ServletContext servletContext= getRequest().getSession().getServletContext();			
				ReenviarMensajesPendientesJob reenvio = new ReenviarMensajesPendientesJob();
				reenvio.lanzarJob(servletContext, jobBean, datosServicios);
				
				addActionMessageSession(this.getText("plataforma.ejecucionjob.ejecucion.ok"));
				return SUCCESS;
			}else{
				addActionErrorSession(this.getText("Job no implementado"));
				return SUCCESS;
			}
			
		} catch (NumberFormatException | BusinessException e) {
			String mensg = this.getText("errors.action.ejecucionjobs.ejecucion", new String[] { proceso.getProcesosId().toString() });
			logger.error("EjecucionJobAction - ejecucion:" + e);
			throw new BusinessException(mensg);
		} 
	}
	
	/**
	 * Delete selected.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deleteSelected() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_EL, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_REF, null));
		String source = properties.getProperty("log.SOURCE_USUARIOS", null);
				
		
		if (getRequest().getSession().getAttribute(INFO_USER) == null) {
			return NO_USER;
		}
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.procesomanual.deleteselected.error"));
			
		} else {
			for (String idProcesoManual : checkDelList) {
									
				servicioProcesosManuales.deleteProcesoManual(Long.valueOf(idProcesoManual), source, accion, accionId);
			}
			addActionMessageSession(this.getText("plataforma.procesomanual.deleteselected.ok"));
			
		}
		return SUCCESS;

	}
	
	/**
	 * Update.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 * @throws ServletException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	///MIGRADO
	public String update() throws BaseException, ClassNotFoundException, IOException {
		String accion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String source = properties.getProperty(LOGDOTSOURCE_JO, null);
		
		if (getRequest().getSession().getAttribute(INFO_USER) == null) {
			return NO_USER;
		}
		ProcesosBean procesoBBDD = null;
		ProcesosBean procesoBBDDcopia = null;
		
		if (proceso == null) {
			addActionErrorSession(this.getText("plataforma.ejecucionjob.update.error"));
		} else {
			if(!validaObligatoriosProcesoAutomatico(proceso)){
				return SUCCESS;
			}
			
			procesoBBDD = servicioProcesos.loadProceso(proceso);
			
			procesoBBDDcopia = copiarProceso(procesoBBDD); 
			//Se realiza una copia del proceso, por si hay un fallo en la conexion con el nodo principal
									
			if (procesoBBDD != null) {
					
				if(!proceso.getActivo() && procesoBBDD.getActivo()){
						
					addActionWarningMessageSession(this.getText("plataforma.jobs.update.desactivado"));
					procesoBBDD.setEstado("Desactivado");
				}
				if(proceso.getActivo() && !procesoBBDD.getActivo()){
						
					addActionWarningMessageSession(this.getText("plataforma.jobs.update.activado"));
					procesoBBDD.setEstado("Sin Inicializar");
				}
				procesoBBDD.setActivo(proceso.getActivo());	
				procesoBBDD.setHoraInicio(proceso.getHoraInicio());												
				procesoBBDD.setParametro1(proceso.getParametro1());
				procesoBBDD.setParametro2(proceso.getParametro2());	
				procesoBBDD.setTipo(proceso.getTipo());
				procesoBBDD.setNombre(proceso.getNombre());				
				
				String proxEje[] = proceso.getHoraInicio().split(":");
				String hora = proxEje[0];
				String minutos = proxEje[1];
				if("diaria".equals(proceso.getTipo())){
					procesoBBDD.setProximaEjecucion(BLANK0+minutos+BLANK+hora+" * * ?");
				}else{
					StringBuilder diasSemana = new StringBuilder();
					
					if(proceso.getLunes()){
			
						diasSemana.append("MON,");
						procesoBBDD.setLunes(true);
					} else {
						procesoBBDD.setLunes(false);
					}
					if(proceso.getMartes()){
						diasSemana.append("TUE,");
						procesoBBDD.setMartes(true);
					} else {
						procesoBBDD.setMartes(false);
					}
					if(proceso.getMiercoles()){
						diasSemana.append("WED,");
						procesoBBDD.setMiercoles(true);
					} else {
						procesoBBDD.setMiercoles(false);
					}
					if(proceso.getJueves()){
						diasSemana.append("THU,");
						procesoBBDD.setJueves(true);
					} else {
						procesoBBDD.setJueves(false);
					}
					if(proceso.getViernes()){
						diasSemana.append("FRI,");
						procesoBBDD.setViernes(true);
					} else {
						procesoBBDD.setViernes(false);
					}
					if(proceso.getSabado()){
						diasSemana.append("SAT,");
						procesoBBDD.setSabado(true);
					} else {
						procesoBBDD.setSabado(false);
					}
					if(proceso.getDomingo()){
						diasSemana.append("SUN,");
						procesoBBDD.setDomingo(true);
					} else {
						procesoBBDD.setDomingo(false);
					}
					diasSemana.setLength(diasSemana.length() - 1); 
					//Eliminamos la ultima coma
					
					procesoBBDD.setProximaEjecucion(BLANK0+minutos+BLANK+hora+" ? * "+diasSemana+ " *");
				}
				
				
				servicioProcesos.updateProceso(procesoBBDD, source, accion, accionId);
				
				String nodoActivos = properties.getProperty(NODO_ACTIVO, null);
				String res = "";
				if("S".equals(nodoActivos)){
					ApplicationContext  applicationContext = WebApplicationContextUtils.getWebApplicationContext(getRequest().getSession().getServletContext());
					Planificador p = new Planificador(applicationContext);
					p.planificarProcesos(procesoBBDD);
					addActionMessageSession(this.getText(EJECUCIONJOB_UPDATE_OK));
				}else{
					StringBuilder urlProceso = new StringBuilder();
					urlProceso.append(properties.getProperty(EJECUCION_PROCESOS_AUTOMATICA_NODO_SET_ESTADO_PROCESO,null));
					urlProceso.append(JOB_PARAMETRO + procesoBBDD.getNombreClase());
					try {
						res = connectionHTTP(urlProceso.toString());	
						if(res.contains("correcta")){
							addActionMessageSession(this.getText(EJECUCIONJOB_UPDATE_OK));
						}else if(res.contains("Nodo no principal, no se planifica ningun job.")){
							servicioProcesos.updateProceso(procesoBBDDcopia, source, accion, accionId);
							addActionErrorSession(this.getText("plataforma.ejecucionjob.update.error.planificarNodoPrincipal"));
						} else{
								servicioProcesos.updateProceso(procesoBBDDcopia, source, accion, accionId);
								addActionErrorSession(this.getText(PLATAFORMADOTEJ0));
							}
					} catch (IOException e) {
							
						servicioProcesos.updateProceso(procesoBBDDcopia, source, accion, accionId);
						getRequest().getSession().removeAttribute("MSGPLT");
						getRequest().getSession().removeAttribute("MSGPLT_ERROR");
						addActionErrorSession(this.getText(PLATAFORMADOTEJ0));
					}
				}				
			}			
		}		
		
		return SUCCESS;

	}	
	
	private ProcesosBean copiarProceso(ProcesosBean procesoBBDD) throws IOException, ClassNotFoundException {
		// Create byte array output stream and use it to create object output stream
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);

		oos.writeObject(procesoBBDD);		
		// serialize
		oos.flush();
		
		
		// toByteArray creates & returns a copy of stream’s byte array
		byte[] bytes = bos.toByteArray();

		// Create byte array input stream and use it to create object input stream
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

		ObjectInputStream ois = new ObjectInputStream(bis);
		return (ProcesosBean) ois.readObject();		
		// deserialize & typecast
	}

	/**
	 * Conexion HTTP con el nodo que ejecuta los proceso de forma automatica
	 * 
	 * @param nodo
	 * @return
	 * @throws IOException
	 */
	public static String connectionHTTP(String nodo) throws IOException{
		String result = "";
		if (null != nodo && !"".equals(nodo)){
			logger.info("connectionHTTP - Llamada al nodo que ejecuta los procesos automaticamente: " + nodo);
			URL url = new URL(nodo);
			URLConnection con = url.openConnection();
			con.setReadTimeout(10000);
			
			InputStreamReader input = new InputStreamReader(con.getInputStream());
			BufferedReader in = new BufferedReader(input);
			String linea;
			StringBuilder resultado = new StringBuilder(); 
			while ((linea = in.readLine()) != null) {
				resultado.append(linea);
			}
			
			result = resultado.toString();
			
			logger.info("connectionHTTP - Resultado:");
			logger.info("connectionHTTP - " + resultado);
		}
		
		return result;
	}
	
	/**
	 * Update.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String updateProcesoManual() throws BaseException {
		
		String accion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String source = properties.getProperty(LOGDOTSOURCE_PR, null);
		
		if (getRequest().getSession().getAttribute(INFO_USER) == null) {
			return NO_USER;
		}
		ProcesosManualesBean procesoManualBBDD = null;
		if (procesoManual == null) {
			addActionErrorSession(this.getText("plataforma.procesomanual.update.error"));
		} else {
	
			procesoManualBBDD = servicioProcesosManuales.loadProcesoManual(procesoManual);
			
			if (procesoManualBBDD != null) {
								
				procesoManualBBDD.setNombre(procesoManual.getNombre());
				procesoManualBBDD.setParametro1(procesoManual.getParametro1());
				procesoManualBBDD.setParametro2(procesoManual.getParametro2());
				procesoManualBBDD.setProcesoId(procesoManual.getProcesoId());				
				
				if(validaObligatoriosProcesoManual(procesoManualBBDD)){
					servicioProcesosManuales.updateProcesoManual(procesoManualBBDD, source, accion, accionId);
					addActionMessageSession(this.getText(PROCESOMANUAL_UPDATE_OK));
				}
												
			}
			
		}		
		return SUCCESS;

	}
	

	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	///MIGRADO
		@Override
		public void prepare() throws Exception {
			session = (Map) ActionContext.getContext().get("session");
			this.validate();
		}
		

	/**
	 * Obtener combo servicios conservacion.
	 *
	 * @return combo servicios conservacion
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public List<KeyValueObject> getComboServiciosConservacion() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<ServicioBean> keys;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		keys = (ArrayList<ServicioBean>) servicioServicio.getServicios(rolUsuario, idUsuario);
	
		if (keys != null && !keys.isEmpty()) {
			for (ServicioBean key : keys) {
		
				if (null != key.getConservacion()) {
					option = new KeyValueObject();
					option.setCodigo(key.getServicioId().toString());
					option.setDescripcion(key.getNombre());
					result.add(option);
				}	
			}
		}
		return result;
	}
		
		/**
		 * Obtener combo jobs.
		 *
		 * @return combo jobs
		 * @throws BusinessException the business exception
		 */
		////MIGRADO
	public List<KeyValueObject> getComboJobs(){
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;

		option = new KeyValueObject();
		option.setCodigo(PROC_HIST);
		option.setDescripcion(PROC_HIST);
		result.add(option);
		option = new KeyValueObject();
		option.setCodigo(PROC_CONS);
		option.setDescripcion(PROC_CONS);
		result.add(option);
		option = new KeyValueObject();
		option.setCodigo(PROC_INFORMES);
		option.setDescripcion(PROC_INFORMES);
		result.add(option);
		option = new KeyValueObject();
		option.setCodigo(PROC_DIR3);
		option.setDescripcion(PROC_DIR3);
		result.add(option);
		option = new KeyValueObject();
		option.setCodigo(PROC_REENVIO);
		option.setDescripcion(PROC_REENVIO);
		result.add(option);
		option = new KeyValueObject();
		option.setCodigo(PROC_ANULACION_MENSAJES);
		option.setDescripcion(PROC_ANULACION_MENSAJES);
		result.add(option);

		return result;
	}
	
	/**
	 * Obtener volver.
	 *
	 * @return volver
	 */
	@SuppressWarnings("unchecked")
	public String getVolver() {
		String volver = "cargarServiciosConservacionEvent.action?jobBean.nombreJob=REENVIO";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
			session.put(RECOVERY, volver);
		} else if (session.get(RECOVERY) != null) {
			volver = (String) session.get(RECOVERY);
			session.put(RECOVERY, null);
		}
		return volver;
	}
	
	public List<KeyValueObject> getComboNumeroDias() {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option1 = new KeyValueObject(R_CONST_2, R_CONST_2);
		KeyValueObject option2 = new KeyValueObject(R_CONST_3, R_CONST_3);
		KeyValueObject option3 = new KeyValueObject(R_CONST_4, R_CONST_4);
		KeyValueObject option4 = new KeyValueObject(R_CONST_5, R_CONST_5);
		KeyValueObject option5 = new KeyValueObject(R_CONST_6, R_CONST_6);
		KeyValueObject option6 = new KeyValueObject(R_CONST_7, R_CONST_7);
		KeyValueObject option7 = new KeyValueObject(R_CONST_8, R_CONST_8);
		
		
		result.add(option1);
		result.add(option2);
		result.add(option3);
		result.add(option4);
		result.add(option5);
		result.add(option6);
		result.add(option7);			
		
		return result;
	}

	public void setComboNumeroDias(List<KeyValueObject> comboNumeroDias) {
		this.comboNumeroDias = comboNumeroDias;
	}
	
	/**
	 * Obtener combo page size.
	 *
	 * @return combo page size
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public List<KeyValueObject> getComboPageSize() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option10 = new KeyValueObject(R_CONST_REF, R_CONST_REF);
		KeyValueObject option20 = new KeyValueObject(R_CONST_10, R_CONST_10);
		KeyValueObject option50 = new KeyValueObject(R_CONST_11, R_CONST_11);
		KeyValueObject option100 = new KeyValueObject(R_CONST_12, R_CONST_12);
		result.add(option10);
		result.add(option20);
		result.add(option50);
		result.add(option100);

		return result;

	}
	

		/**
		 * Obtener servicio servicio.
		 *
		 * @return the servicioServicio
		 */
		public ServicioServicio getServicioServicio() {
			return servicioServicio;
		}

		/**
		 * Modificar servicio servicio.
		 *
		 * @param servicioServicio the servicioServicio to set
		 */
		public void setServicioServicio(ServicioServicio servicioServicio) {
			this.servicioServicio = servicioServicio;
		}

		/**
		 * Obtener properties.
		 *
		 * @return the properties
		 */
		public PlataformaMensajeriaProperties getProperties() {
			return properties;
		}

		/**
		 * Modificar properties.
		 *
		 * @param properties the properties to set
		 */
		public void setProperties(PlataformaMensajeriaProperties properties) {
			this.properties = properties;
		}

		/**
		 * Obtener job bean.
		 *
		 * @return the jobBean
		 */
		public JobBean getJobBean() {
			return jobBean;
		}

		/**
		 * Modificar job bean.
		 *
		 * @param jobBean the jobBean to set
		 */
		public void setJobBean(JobBean jobBean) {
			this.jobBean = jobBean;
		}

		/**
		 * Modificar combo servicios.
		 *
		 * @param comboServicios the comboServicios to set
		 */
		public void setComboServicios(List<KeyValueObject> comboServicios) {
			this.comboServicios = comboServicios;
		}

		/**
		 * Modificar combo jobs.
		 *
		 * @param comboJobs the comboJobs to set
		 */
		public void setComboJobs(List<KeyValueObject> comboJobs) {
			this.comboJobs = comboJobs;
		}
		
		public List<GestionEnvioBean> getListaGestionEnvios() {
			return listaGestionEnvios;
		}

		public void setListaGestionEnvios(List<GestionEnvioBean> listaGestionEnvios) {
			this.listaGestionEnvios = listaGestionEnvios;
		}
		
		public String getResultCount() {
			return resultCount;
		}

		public void setResultCount(String resultCount) {
			this.resultCount = resultCount;
		}
		
		public List<ProcesosBean> getListaProcesos() {
			return listaProcesos;
		}

		public void setListaProcesos(List<ProcesosBean> listaProcesos) {
			this.listaProcesos = listaProcesos;
		}
		
		public String getProcesosId() {
			return procesosId;
		}

		public void setProcesosId(String procesosId) {
			this.procesosId = procesosId;
		}
		
		public ProcesosBean getProceso() {
			return proceso;
		}

		public void setProceso(ProcesosBean proceso) {
			this.proceso = proceso;
		}
		
		public List<KeyValueObject> getComboServicioOrganismos() {
			List<KeyValueObject> result = new ArrayList<>();

			KeyValueObject option;
			List<String> serviciosAeatGiss = new ArrayList<>();
			ArrayList<ViewServicios> keys = null;
			try {
				keys = (ArrayList<ViewServicios>) servicioServicio.getServiciosActivosNoEliminados();
			} catch (BusinessException e) {
				logger.error("EjecucionJob - cargarComboServicioOrganismos:" + e);
			}
			
			if (keys != null && !keys.isEmpty()) {
				for (ViewServicios key : keys) {
					if(null != key.getActivo() && key.getActivo()){
						boolean encontrado = false;
						for(String idServ : serviciosAeatGiss){
							if(idServ.trim().equals(key.getServicioid().toString())){
								encontrado = true;						
							}
						}
						if(!encontrado){
							option = new KeyValueObject();
							option.setCodigo(key.getServicioid().toString());
							option.setDescripcion(key.getNombre());
							result.add(option);
						}
					}
				}
			}

			return result;
		}

		public void setComboServicioOrganismos(
				List<KeyValueObject> comboServicioOrganismos) {
			this.comboServicioOrganismos = comboServicioOrganismos;
		}
		
		public String getDatosServicios() {
			return datosServicios;
		}

		public void setDatosServicios(String datosServicios) {
			this.datosServicios = datosServicios;
		}
		
		public List<ProcesosManualesBean> getListaProcesosManuales() {
			return listaProcesosManuales;
		}

		public void setListaProcesosManuales(List<ProcesosManualesBean> listaProcesosManuales) {
			this.listaProcesosManuales = listaProcesosManuales;
		}

		public String getProcesoManualId() {
			return procesoManualId;
		}

		public void setProcesoManualId(String procesoManualId) {
			this.procesoManualId = procesoManualId;
		}

		public ProcesosManualesBean getProcesoManual() {
			return procesoManual;
		}

		public void setProcesoManual(ProcesosManualesBean procesoManual) {
			this.procesoManual = procesoManual;
		}

		public String getTieneServicios() {
			return tieneServicios;
		}

		public void setTieneServicios(String tieneServicios) {
			this.tieneServicios = tieneServicios;
		}

		public List<KeyValueObject> getComboListadoJobs() throws BusinessException {
			List<KeyValueObject> result = new ArrayList<>();
			KeyValueObject option;
			ArrayList<ServicioBean> keys;
			String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
			Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
			keys = (ArrayList<ServicioBean>) servicioServicio.getServicios(rolUsuario, idUsuario);

			if (keys != null && !keys.isEmpty()) {
				for (ServicioBean key : keys) {
					if (null != key.getHistorificacion()) {
						option = new KeyValueObject();
						option.setCodigo(key.getServicioId().toString());
						option.setDescripcion(key.getNombre());
						result.add(option);
					}
				}
			}
			return result;
		}

		public void setComboListadoJobs(List<KeyValueObject> comboListadoJobs) {
			this.comboListadoJobs = comboListadoJobs;
		}

		/**
		 * Obtener combo servicios.
		 *
		 * @return combo servicios
		 * @throws BusinessException the business exception
		 */
		////MIGRADO
		public List<KeyValueObject> getComboServicios() throws BusinessException {
			List<KeyValueObject> result = new ArrayList<>();
			KeyValueObject option;
			ArrayList<ServicioBean> keys;
			String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
			Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
			keys = (ArrayList<ServicioBean>) servicioServicio.getServicios(rolUsuario, idUsuario);
	
			if (keys != null && !keys.isEmpty()) {
				for (ServicioBean key : keys) {
	
					if (null != key.getHistorificacion()) {
						option = new KeyValueObject();
						option.setCodigo(key.getServicioId().toString());
						option.setDescripcion(key.getNombre());
						result.add(option);
					}
	
				}
			}
			return result;
		}

		public List<KeyValueObject> getCombolistaJobs() throws BusinessException {
			List<KeyValueObject> result = new ArrayList<>();
			KeyValueObject option;
			ArrayList<ProcesosBean> keys;			
			keys = (ArrayList<ProcesosBean>) servicioProcesos.getAllProcesos();

			if (keys != null && !keys.isEmpty()) {
				for (ProcesosBean key : keys) {
					if (null != key.getNombreClase()) {
						option = new KeyValueObject();
						option.setCodigo(String.valueOf(key.getProcesosId()));
						option.setDescripcion(key.getNombreClase());
						result.add(option);
					}
				}
			}
			return result;
		}

		public void setCombolistaJobs(List<KeyValueObject> combolistaJobs) {
			this.combolistaJobs = combolistaJobs;
		}

		public String[] getCheckDelList() {
			return checkDelList;
		}

		public void setCheckDelList(String[] checkDelList) {
			this.checkDelList = checkDelList;
		}

		public String getDatosServiciosExcluidos() {
			return datosServiciosExcluidos;
		}

		public void setDatosServiciosExcluidos(String datosServiciosExcluidos) {
			this.datosServiciosExcluidos = datosServiciosExcluidos;
		}

		public String getTieneServiciosAutomatica() {
			return tieneServiciosAutomatica;
		}

		public void setTieneServiciosAutomatica(String tieneServiciosAutomatica) {
			this.tieneServiciosAutomatica = tieneServiciosAutomatica;
		}

		public String getPropertyServiciosAnularMensajes() throws BusinessException {
			String idServ = properties.getProperty("jobAnularMensajes.serviciosAEATGiss", null);
			ArrayList<String> serviciosAeatGiss = new ArrayList<>(Arrays.asList(idServ.split(R_CONST_0)));
			propertyServiciosAnularMensajes ="";
			for(String serv:serviciosAeatGiss){
				ServicioBean servicio = new ServicioBean();
				servicio.setServicioId(Integer.valueOf(serv));
				servicio = servicioServicio.loadServicio(servicio);
				propertyServiciosAnularMensajes += servicio.getNombre()+R_CONST_0;
			}
			
			return propertyServiciosAnularMensajes.substring(0, propertyServiciosAnularMensajes.length() - 1);
		}

		public void setPropertyServiciosAnularMensajes(
				String propertyServiciosAnularMensajes) {
			this.propertyServiciosAnularMensajes = propertyServiciosAnularMensajes;
		}

		
}
