package es.mpr.plataformamensajeria.quartz.jobs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosCodOrgBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosCodOrgPagadorBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosCodSiaBean;
import es.mpr.plataformamensajeria.beans.InformesServiciosEstadoBean;
import es.mpr.plataformamensajeria.beans.JobBean;
import es.mpr.plataformamensajeria.beans.ProcesoInformesServiciosBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesoInformesServicios;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.web.action.servicios.SendMailService;

// TODO: Auto-generated Javadoc
/**
 * Clase InformesServiciosJob.
 */
////MIGRADO
@Service("informesServiciosJob")
public class InformesServiciosJob implements Job {
	
	/**  logger. */
	private static Logger logger = Logger.getLogger(InformesServiciosJob.class);
	
	/**  servicio proceso informes servicios. */
	private ServicioProcesoInformesServicios servicioProcesoInformesServicios;

	/**  servicio servicios. */
	private ServicioServicio servicioServicios;
	
	/**  servicio aplicacion. */
	private ServicioAplicacion servicioAplicacion;
	
	/**  properties. */
	private PlataformaMensajeriaProperties properties;
	
	
	/**  nombre job. */
	private static String NOMBRE_JOB = "Informes Servicios";
	
	/**  estado proceso ok. */
	private static String ESTADO_PROCESO_OK = "OK";
	
	/**  estado proceso ko. */
	private static String ESTADO_PROCESO_KO = "KO";
	
	/**  agrupacion estados. */
	private static String AGRUPACION_ESTADOS = "ESTADOS";
	
	/**  agrupacion organismos. */
	private static String AGRUPACION_ORGANISMOS = "ORGANISMOS";
	
	/**  agrupacion procedimientos. */
	private static String AGRUPACION_PROCEDIMIENTOS = "PROCEDIMIENTOS";
	
	/**  agrupacion organismos pagadores. */
	private static String AGRUPACION_ORGANISMOS_PAGADORES = "ORGANISMOS PAGADORES";
	
	/**  columna estado. */
	private static String COLUMNA_ESTADO = "Estado";
	
	/**  columna organismo. */
	private static String COLUMNA_ORGANISMO = "Organismo";
	
	/**  columna procedimiento. */
	private static String COLUMNA_PROCEDIMIENTO = "Procedimiento";
	
	/**  columna organismo pagador. */
	private static String COLUMNA_ORGANISMO_PAGADOR = "Organismo pagador";
	
	/**  columna total. */
	private static String COLUMNA_TOTAL = "Total";
	
	/**  total. */
	private static String TOTAL = "TOTAL:";
	
	/**  label separador lineas. */
	//Constantes para formato de correo
	private static String LABEL_SEPARADOR_LINEAS = "<br>";
	
	/**  label apertura negrita. */
	private static String LABEL_APERTURA_NEGRITA = "<strong>";
	
	/**  label cierre negrita. */
	private static String LABEL_CIERRE_NEGRITA = "</strong>";
	
	/**  label margin left. */
	private static String LABEL_MARGIN_LEFT = "<div style=\"margin-left:20px;\">";
	
	/**  label apertura tabla. */
	private static String LABEL_APERTURA_TABLA = "<table>";
	
	/**  label cierre tabla. */
	private static String LABEL_CIERRE_TABLA = "</table>";
	
	/**  label cierre div. */
	private static String LABEL_CIERRE_DIV = "</div>";
	
	/**  label apertura fila. */
	private static String LABEL_APERTURA_FILA = "<tr>";
	
	/**  label cierre fila. */
	private static String LABEL_CIERRE_FILA = "</tr>";
	
	/**  label apertura columna. */
	private static String LABEL_APERTURA_COLUMNA = "<td>";
	
	/**  label cierre columna. */
	private static String LABEL_CIERRE_COLUMNA = "</td>";
	
	/**  label apertura columna ancho. */
	private static String LABEL_APERTURA_COLUMNA_ANCHO = "<td width=\"160\">";
	
	/**  job bean. */
	private JobBean jobBean = null;

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	///MIGRADO
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SchedulerContext schedulerContext;
		try {
			schedulerContext = context.getScheduler().getContext();
			
			ApplicationContext applicationContext = (ApplicationContext) schedulerContext.get("applicationContext");
			this.inicializarVariables(applicationContext);
			ejecutar();
		} catch (SchedulerException e) {
			logger.error("Error ScheduleContext en ejecucion Job Informes Servicios", e);
		}
	}
	
	/**
	 * Lanzar job.
	 *
	 * @param servletContext the servlet context
	 * @param bean the bean
	 */
	public void lanzarJob(ServletContext servletContext, JobBean bean) {
		try {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			this.inicializarVariables(applicationContext);
			jobBean = bean;
			ejecutar();
		} catch (SchedulerException e) {
			logger.error("Error ejecucion Job manual Informes Servicios: ", e);
		}

	}

	/**
	 * Ejecutar.
	 */
	private void ejecutar() {
		logger.info("execute - INICIO Job Informes Servicios");
		Calendar fechaIni = Calendar.getInstance();
		logger.info("execute - Fecha comienzo: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(fechaIni.getTime()));
		long tiempo1 = fechaIni.getTimeInMillis();
		
		ProcesoInformesServiciosBean procesoInformesServiciosBean = new ProcesoInformesServiciosBean();
		procesoInformesServiciosBean.setFechaInicio(new Date());
		
		try{
			
			boolean exito = true;
			
			Calendar calendar = Calendar.getInstance();
			Integer anno = calendar.get(Calendar.YEAR);
			Integer mes = calendar.get(Calendar.MONTH); //Los meses los recupera del 0 (enero) al 11 (diciembre), por lo que ya tenemos en mes anterior
			
			//Si el mes es 0 (enero), debemos de calcular las estadisticas para el mes 12 (diciembre) del anno anterior
			if(mes.equals(Integer.valueOf(0))){
				anno--;
				mes = Integer.valueOf(12);
			}
						
			//Recuperamos todas las aplicaciones para setearle el nombre en el mensaje por servicio
			List<AplicacionBean> listaAplicaciones = servicioAplicacion.getAplicaciones();
			
			//Buscamos los servicios que tienen activada la generacion de informes
			List<ServicioBean> listaServicios = servicioServicios.getServiciosInformes();
			
			if(null != listaServicios){
				
				for(ServicioBean servicio : listaServicios){
					
					StringBuilder cuerpoMensajes = new StringBuilder();
					
					boolean agrupacionEstado = false;
					boolean agrupacionCodOrganismo = false;
					boolean agrupacionCodSia = false;
					boolean agrupacionCodOrganismoPagador = false;
					
					if(null!=servicio){
						
						int numTotalMensajes = 0;
						
						if(null!=servicio.getAgrupacionestado() && servicio.getAgrupacionestado()){
							agrupacionEstado = true;
						}
						if(null!=servicio.getAgrupacioncodorg() && servicio.getAgrupacioncodorg()){
							agrupacionCodOrganismo = true;
						}
						if(null!=servicio.getAgrupacioncodsia() && servicio.getAgrupacioncodsia()){
							agrupacionCodSia = true;
						}
						if(null!=servicio.getAgrupacioncodorgpagador() && servicio.getAgrupacioncodorgpagador()){
							agrupacionCodOrganismoPagador = true;
						}
						
						StringBuilder descripcionEstado = new StringBuilder();
						StringBuilder descripcionCodOrganismo = new StringBuilder();
						StringBuilder descripcionCodSia = new StringBuilder();
						StringBuilder descripcionCodOrganismoPagador = new StringBuilder();
						
						if(agrupacionEstado){
							
							descripcionEstado.append(cabeceraTabla(AGRUPACION_ESTADOS, COLUMNA_ESTADO));
							
							List<InformesServiciosEstadoBean> informesServiciosEstado = servicioProcesoInformesServicios.obtenerInformesServiciosEstado(servicio.getServicioId(), anno, mes);
							
							int numTotalMensajesEstado = 0;
							
							if(null!=informesServiciosEstado && !informesServiciosEstado.isEmpty()){
								
								for(InformesServiciosEstadoBean informe : informesServiciosEstado){
									descripcionEstado.append(cuerpoTabla(informe.getEstado(), informe.getNumTotal()));
									numTotalMensajes = numTotalMensajes + informe.getNumTotal();
									numTotalMensajesEstado = numTotalMensajesEstado + informe.getNumTotal();
								}
								
							}
							
							descripcionEstado.append(pieTabla(numTotalMensajesEstado));
						}
						
						if(agrupacionCodOrganismo){
							
							descripcionCodOrganismo.append(cabeceraTabla(AGRUPACION_ORGANISMOS, COLUMNA_ORGANISMO));
							
							List<InformesServiciosCodOrgBean> informesServiciosCodOrg = servicioProcesoInformesServicios.obtenerInformesServiciosCodOrg(servicio.getServicioId(), anno, mes);
							
							int numTotalMensajesCodOrganismo = 0;
							
							if(null!=informesServiciosCodOrg && !informesServiciosCodOrg.isEmpty()){
								for(InformesServiciosCodOrgBean informe : informesServiciosCodOrg){
									descripcionCodOrganismo.append(cuerpoTabla(informe.getCodOrganismo(), informe.getNumTotal()));
									numTotalMensajes = numTotalMensajes + informe.getNumTotal();
									numTotalMensajesCodOrganismo = numTotalMensajesCodOrganismo + informe.getNumTotal();
								}
							} 
							
							descripcionCodOrganismo.append(pieTabla(numTotalMensajesCodOrganismo));
							
						}
						
						if(agrupacionCodSia){
							
							descripcionCodSia.append(cabeceraTabla(AGRUPACION_PROCEDIMIENTOS, COLUMNA_PROCEDIMIENTO));
							
							List<InformesServiciosCodSiaBean> informesServiciosCodSia = servicioProcesoInformesServicios.obtenerInformesServiciosCodSia(servicio.getServicioId(), anno, mes);
							
							int numTotalMensajesCodSia = 0;
							
							if(null!=informesServiciosCodSia && !informesServiciosCodSia.isEmpty()){
								for(InformesServiciosCodSiaBean informe : informesServiciosCodSia){
									descripcionCodSia.append(cuerpoTabla(informe.getCodSia(), informe.getNumTotal()));
									numTotalMensajes = numTotalMensajes + informe.getNumTotal();
									numTotalMensajesCodSia = numTotalMensajesCodSia + informe.getNumTotal();
								}
							}
							
							descripcionCodSia.append(pieTabla(numTotalMensajesCodSia));
							
						}
						
						if(agrupacionCodOrganismoPagador){
							
							descripcionCodOrganismoPagador.append(cabeceraTabla(AGRUPACION_ORGANISMOS_PAGADORES, COLUMNA_ORGANISMO_PAGADOR));
							
							List<InformesServiciosCodOrgPagadorBean> informesServiciosCodOrgPagador = servicioProcesoInformesServicios.obtenerInformesServiciosCodOrgPagador(servicio.getServicioId(), anno, mes);
							
							int numTotalMensajesCodOrgPagador = 0;
							
							if(null!=informesServiciosCodOrgPagador && !informesServiciosCodOrgPagador.isEmpty()){
								for(InformesServiciosCodOrgPagadorBean informe : informesServiciosCodOrgPagador){
									descripcionCodOrganismoPagador.append(cuerpoTabla(informe.getCodOrganismoPagador(), informe.getNumTotal()));
									numTotalMensajes = numTotalMensajes + informe.getNumTotal();
									numTotalMensajesCodOrgPagador = numTotalMensajesCodOrgPagador + informe.getNumTotal();
								}
							}
							
							descripcionCodOrganismoPagador.append(pieTabla(numTotalMensajesCodOrgPagador));
							
						}
						
						//Obtenemos el nombre de la aplicacion asociada al servicio
						String nombreAplicacion = "";
						
						if(null!=listaAplicaciones && !listaAplicaciones.isEmpty()){
							for(AplicacionBean aplicacion : listaAplicaciones){
								if(servicio.getAplicacionid().equals(aplicacion.getAplicacionId())){
									nombreAplicacion = aplicacion.getNombre();
								}
							}
						}
						
						//Construimos la entrada con la informacion del servicio
						StringBuilder cabecera = new StringBuilder();
						cabecera.append("El servicio '" + servicio.getNombre() + "' de la aplicacion '" + nombreAplicacion 
								+ "' ha enviado un total de " + numTotalMensajes + " mensajes.").append(LABEL_SEPARADOR_LINEAS);
						cabecera.append("Los mensajes estan distribuidos de la siguiente forma:").append(LABEL_SEPARADOR_LINEAS).append(LABEL_SEPARADOR_LINEAS);
						
						cuerpoMensajes.append(cabecera).append(descripcionEstado).append(descripcionCodOrganismo).append(descripcionCodSia).append(descripcionCodOrganismoPagador);
						
						//Se envía un correo informando del resultado de la ejecución del JOB
						SendMailService sendMailService = new SendMailService();
						try {
							sendMailService.initInformesServicios(NOMBRE_JOB, ESTADO_PROCESO_OK, 
									servicio.getInformesdestinatarios(), cuerpoMensajes.toString(), properties);
						} catch (ServletException e) {
							logger.error("InformesServiciosJob - execute:" + e);
							exito = false;
						}
					}
				}	
			}
			
			if(exito){
				procesoInformesServiciosBean.setCodigoEstado(ESTADO_PROCESO_OK);
				procesoInformesServiciosBean.setDescripcionEstado("La generacion y envio de informes de servicios se ha realizado correctamente.");
			} else {
				procesoInformesServiciosBean.setCodigoEstado(ESTADO_PROCESO_KO);
				procesoInformesServiciosBean.setDescripcionEstado("Se han producido fallos en el envio de informes de servicios. Para mas informacion, consulte logs.");
			}
			
			procesoInformesServiciosBean.setFechaFin(new Date());
			servicioProcesoInformesServicios.newServicioProcesoInformesServicios(procesoInformesServiciosBean);
			
		} catch (Exception e) {
			logger.info("execute - FIN Job Informes Servicios - exception" + e);
			procesoInformesServiciosBean.setCodigoEstado(ESTADO_PROCESO_KO);
			StringBuilder descripcionEstado = new StringBuilder();
			descripcionEstado.append("Se ha producido una excepcion. Para mas informacion, consulte logs. ").append(LABEL_SEPARADOR_LINEAS);
			procesoInformesServiciosBean.setDescripcionEstado("Se han producido fallos en la generacion de los informes de servicios. Para mas informacion, consulte logs.");
			procesoInformesServiciosBean.setFechaFin(new Date());
			servicioProcesoInformesServicios.newServicioProcesoInformesServicios(procesoInformesServiciosBean);
			
		}
		
		//Se envía un correo informando del resultado de la ejecución del JOB
		SendMailService sendMailService = new SendMailService();
		try {
			sendMailService.initJob(NOMBRE_JOB, procesoInformesServiciosBean.getCodigoEstado(), procesoInformesServiciosBean.getDescripcionEstado(), properties);
		} catch (ServletException e) {
			logger.info("execute - FIN Job Informes Servicios - ServletException" + e);
		}
		
		Calendar fechaFin = Calendar.getInstance();
		logger.info("execute - Fecha fin: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(fechaFin.getTime()));
		long tiempo2 = fechaFin.getTimeInMillis();
		long tiempo = tiempo2 - tiempo1;
		logger.info("execute - Duracion del Proceso de Informes Servicios: " + tiempo + " milisegundos");
	}
	
	/**
	 * Inicializar variables.
	 *
	 * @param applicationContext the application context
	 * @throws JobExecutionException the job execution exception
	 */
	private void inicializarVariables(ApplicationContext applicationContext) throws JobExecutionException {
		try {
			servicioProcesoInformesServicios = (ServicioProcesoInformesServicios) applicationContext.getBean("servicioProcesoInformesServiciosImpl");
			servicioServicios = (ServicioServicio) applicationContext.getBean("servicioServicioImpl");
			servicioAplicacion = (ServicioAplicacion) applicationContext.getBean("servicioAplicacionImpl");
			properties  = (PlataformaMensajeriaProperties) applicationContext.getBean("plataformaMensajeriaProperties");
		} catch (Exception objException) {
			logger.error("InformesServiciosJob - InicializarVariables - Error: " + objException);
			throw new JobExecutionException("Un error en el planificador");
		}
	}
	
	/**
	 * Cabecera tabla.
	 *
	 * @param agrupacion the agrupacion
	 * @param columna the columna
	 * @return the string builder
	 */
	private StringBuilder cabeceraTabla(String agrupacion, String columna) {
		
		StringBuilder cadena = new StringBuilder();
		
		cadena.append(LABEL_APERTURA_NEGRITA).append(agrupacion).append(LABEL_CIERRE_NEGRITA).append(LABEL_SEPARADOR_LINEAS).append(LABEL_SEPARADOR_LINEAS);
		cadena.append(LABEL_MARGIN_LEFT);
		cadena.append(LABEL_APERTURA_TABLA);
		cadena.append(LABEL_APERTURA_FILA).append(LABEL_APERTURA_COLUMNA_ANCHO).append(LABEL_APERTURA_NEGRITA).append(columna).append(LABEL_CIERRE_NEGRITA)
			.append(LABEL_CIERRE_COLUMNA).append(LABEL_APERTURA_COLUMNA).append(LABEL_APERTURA_NEGRITA).append(COLUMNA_TOTAL).append(LABEL_CIERRE_NEGRITA)
			.append(LABEL_CIERRE_COLUMNA).append(LABEL_CIERRE_FILA);
		
		return cadena;
	}
	
	/**
	 * Cuerpo tabla.
	 *
	 * @param atributoCelda the atributo celda
	 * @param numTotal the num total
	 * @return the string builder
	 */
	private StringBuilder cuerpoTabla(String atributoCelda, Integer numTotal) {
		
		StringBuilder cadena = new StringBuilder();
		
		cadena.append(LABEL_APERTURA_FILA).append(LABEL_APERTURA_COLUMNA).append(atributoCelda).append(LABEL_CIERRE_COLUMNA)
			.append(LABEL_APERTURA_COLUMNA).append(numTotal).append(LABEL_CIERRE_COLUMNA).append(LABEL_CIERRE_FILA);
		
		return cadena;
	}
	
	/**
	 * Pie tabla.
	 *
	 * @param numTotalMensajes the num total mensajes
	 * @return the string builder
	 */
	private StringBuilder pieTabla(Integer numTotalMensajes) {
		
		StringBuilder cadena = new StringBuilder();
		
		cadena.append(LABEL_APERTURA_FILA).append(LABEL_APERTURA_COLUMNA).append(LABEL_APERTURA_NEGRITA).append(TOTAL).append(LABEL_CIERRE_NEGRITA)
			.append(LABEL_CIERRE_COLUMNA).append(LABEL_APERTURA_COLUMNA).append(LABEL_APERTURA_NEGRITA).append(numTotalMensajes)
			.append(LABEL_CIERRE_NEGRITA).append(LABEL_CIERRE_COLUMNA).append(LABEL_CIERRE_FILA);
		cadena.append(LABEL_CIERRE_TABLA);
		cadena.append(LABEL_CIERRE_DIV).append(LABEL_SEPARADOR_LINEAS);
		
		return cadena;
	}


	/**
	 * Obtener job bean.
	 *
	 * @return job bean
	 */
	public JobBean getJobBean() {
		return jobBean;
	}


	/**
	 * Modificar job bean.
	 *
	 * @param jobBean new job bean
	 */
	public void setJobBean(JobBean jobBean) {
		this.jobBean = jobBean;
	}
}
