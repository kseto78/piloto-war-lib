package es.mpr.plataformamensajeria.quartz.jobs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.managerimpl.TblMensajesManagerImpl;
import es.minhap.plataformamensajeria.iop.managerimpl.TblParametrosServidorManagerImpl;
import es.mpr.plataformamensajeria.beans.JobBean;
import es.mpr.plataformamensajeria.beans.ProcesoHistBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioLotesEnvios;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajes;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesoHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.web.action.servicios.SendMailService;

/**
 * The Class HistorificacionJob.
 */
@Service("anularMensajesJob")
public class AnularMensajesJob implements Job {

	protected static final String ANULARMENSAJESJ = "AnularMensajesJob.Execute ";

	protected static final String TBLMENSAJESMANA = "TblMensajesManagerImpl";

	/**  logger. */
	private static Logger logger = Logger.getLogger(AnularMensajesJob.class);

	/**  servicio proceso historicos. */
	private ServicioProcesoHistoricos servicioProcesoHistoricos;
	
	/**  servicio servicios. */
	private ServicioServicio servicioServicios;
	
	/**  servicio lotes envios. */
	private ServicioLotesEnvios servicioLotesEnvios;
	
	/**  servicio mensajes. */
	private ServicioMensajes servicioMensajes;
	
	@Resource(name = TBLMENSAJESMANA)
	private TblMensajesManager tblMensajesManager;
	
	/**  properties. */
	private PlataformaMensajeriaProperties properties;
	
	/**  nombre job. */
	private static String NOMBRE_JOB = "Anular Mensajes";
	
	/**  estado proceso ok. */
	private static String ESTADO_PROCESO_OK = "OK";
	
	/**  estado proceso ko. */
	private static String ESTADO_PROCESO_KO = "KO";
	
	/**  caracter separador lineas. */
	private static String CARACTER_SEPARADOR_LINEAS = "<br>"; 
	//El salto de linea esta preparado para el correo en formato HTML
	
	/**  caracter tab. */
	private static String CARACTER_TAB = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; 
	//El tab esta preparado para el correo en formato HTML

	/**  job bean. */
	private JobBean jobBean = null;
	
	/**  tbl parametros manager. */
	private TblParametrosServidorManagerImpl tblParametrosServidorManager;

	

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SchedulerContext schedulerContext;
		try {
			schedulerContext = context.getScheduler().getContext();
			
			ApplicationContext applicationContext = (ApplicationContext) schedulerContext.get("applicationContext");
			this.inicializarVariables(applicationContext);
			ejecutar();
		} catch (SchedulerException e) {
			logger.error("Error ScheduleContext en ejecucion Job Historificacion", e);
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
			logger.error("Error ejecucion Job manual Historificacion: ", e);
		}

	}

	/**
	 * Ejecutar.
	 *
	 * @throws JobExecutionException the job execution exception
	 */
	private void ejecutar() throws JobExecutionException {
				
		logger.info("execute - INICIO Anular Mensajes");
		Calendar fechaIni = Calendar.getInstance();
		logger.info("execute - Fecha comienzo: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(fechaIni.getTime()));
		StringBuilder descripcionEstado = new StringBuilder();
		
		ProcesoHistBean procesoHistBean = new ProcesoHistBean();
		procesoHistBean.setFechaInicio(new Date());

		
		try{
			Date fechaInicio = new Date();
			Date fechaFin = new Date();	
			
			if(null != jobBean && jobBean.getFecha() != null){
				fechaInicio = jobBean.getFecha();
			}else{
	
				Calendar cal = Calendar.getInstance();
				cal.setTime(fechaInicio);
				cal.add(Calendar.DATE, -14);
				fechaInicio = cal.getTime();
			} 
			
			if(null != jobBean && jobBean.getFechaFin() != null){
				fechaFin = jobBean.getFechaFin();
			}else{
				Calendar cal = Calendar.getInstance();
				cal.setTime(fechaFin);
				cal.add(Calendar.DATE, -7);
				fechaFin = cal.getTime();
			}
			

			String listaServiciosBusqueda = properties.getProperty("jobAnularMensajes.serviciosAEATGiss", null);
			if(null == listaServiciosBusqueda || "".equals(listaServiciosBusqueda)) {
				descripcionEstado.append("Se ha produciodo un error en la carga de los servicios");
				procesoHistBean.setCodigoEstado(ESTADO_PROCESO_KO);
				servicioProcesoHistoricos.newServicioProcesoHistoricos(procesoHistBean);
				return;
			}
			
			List<Long> idMensajes = tblMensajesManager.getMensajesAnular(listaServiciosBusqueda, fechaInicio, fechaFin);

			for(Long idMensaje:idMensajes){
				tblMensajesManager.setEstadoMensaje(idMensaje, "ANULADO", "Mensaje Anulado por Job", null, null, null, "Job de Anulación de Mensajes", null);
			}			
			
			procesoHistBean.setCodigoEstado(ESTADO_PROCESO_OK);
			procesoHistBean.setFechaFin(new Date());
			servicioProcesoHistoricos.newServicioProcesoHistoricos(procesoHistBean);
			descripcionEstado.append(CARACTER_SEPARADOR_LINEAS);
			if(!idMensajes.isEmpty()){
				descripcionEstado.append("Se han anulado los siguientes mensajes: ");
				descripcionEstado.append(StringUtils.join(idMensajes, ','));
			}else{
				descripcionEstado.append("No se han encontrado mensajes para anular.");
			}
			
		}catch (Exception ex){
			logger.info("execute - FIN  Anular Mensajes Job - exception");
			procesoHistBean.setCodigoEstado(ESTADO_PROCESO_KO);
			descripcionEstado.append("Se ha producido una excepcion. Para mas informacion, consulte logs. ").append(CARACTER_SEPARADOR_LINEAS);
			
			servicioProcesoHistoricos.newServicioProcesoHistoricos(procesoHistBean);
			logger.error(ANULARMENSAJESJ , ex);
		}finally{
			logger.info("--- Fin Job Anular Mensajes Job ---");
			procesoHistBean.setFechaFin(new Date());
			
			if("S".equals(properties.getProperty("jobAnularMensajes.mail.avisar", null))) {
				//Se envia un correo informando del resultado de la ejecucion del JOB
				SendMailService sendMailService = new SendMailService();
				try {
					sendMailService.initJob(NOMBRE_JOB, procesoHistBean.getCodigoEstado(), descripcionEstado.toString(), properties, tblParametrosServidorManager);
				} catch (ServletException e) {
					logger.error(ANULARMENSAJESJ , e);
				}
			}
			
			
		}
			
//			//Buscamos los mensajes de AEAT y GISS anulados entre las fechas
			
//			////esto es para borrar
			
			
			
//				
//
//					
//
//						
//							//Indicamos la fecha introducida en la pantalla
//							//Calculamos la fecha a partir de la cual se realiza el historico de mensajes
//	
//							calendar.add(Calendar.DATE, -historificacion); //Le restamos a la fecha actual los dias marcados en el atributo de historico del servicio
//	
//						
//	
////					////esto es para borrar
//							
//
//								/*Se comprueba para cada lote que los mensajes cuya fecha de 'ultimoenvio' es igual o mayor a la definida en el servicio
//								y que ademas TODOS los mensajes del lote se encuentren en estado final (ENVIADO o ANULADO).
//								Si es asi, se historifican los mensajes del lote. */
//																								
//
//
//										
//										//Se obtienen todos los mensajes del lote actual
//
//
//											
//													
//
//
//											} //fin else lote sin mensajes
//										}//fin else no hay mensajes a historificar en el lote
//										
//											logger.info("Se van a historificar "+ listaMensajesHist.size() +" mensajes del servicio con ID "+servicio.getServicioId()+" "
//
//											
//
//
//											//Se realiza el historico por cada lote de envio
//													
//
//
//													lotesIncorrectos.add(idLote.intValue());
//													mensajesNoConservados = mensajesNoConservados + listaMensajesHist.size();
//													logger.error("Se ha producido un error al historificar el lote "+idLote+". ", e);
//												}
//										
//										}
//										listaMensajesHist.clear();
//										listaMensajesHist=null;
////									
//								}
//
//								if(numLotesEnvioFallo==0){
//									if(numLotesEnvioExito==0){
//										descripcionEstado.append(CARACTER_TAB).append("Historico del servicio "+servicio.getNombre().toUpperCase()+" ("+servicio.getServicioId()+"):"
//												+ " No existen lotes para historificar. ").
//												append(CARACTER_SEPARADOR_LINEAS);
//									} else {
//										descripcionEstado.append(CARACTER_TAB).append("Historico del servicio "+servicio.getNombre().toUpperCase()+" ("+servicio.getServicioId()+"):"
//												+ " realizado correctamente. Se han procesado Correctamente "+lotesCorrectos.size()+" lotes (" + mensajesConservados + " mensajes). ")
//												.append(CARACTER_SEPARADOR_LINEAS);
//									}
//									
//								} else {
//									exito = false;
//									if(numLotesEnvioExito==0){
//										descripcionEstado.append(CARACTER_TAB).append("Historico del servicio "+servicio.getNombre().toUpperCase()+" ("+servicio.getServicioId()+"): se han producido fallos."
//												+ "No existen lotes para historificar. Para mas informacion, consulte logs. ").append(CARACTER_SEPARADOR_LINEAS);
//									} else {
//										descripcionEstado.append(CARACTER_TAB).append("Historico del servicio "+servicio.getNombre().toUpperCase()+" ("+servicio.getServicioId()+"):"
//												+ " realizado correctamente. Se han procesado Correctamente "+lotesCorrectos.size()+" lotes (" + mensajesConservados + " mensajes). ").append("Se han procesado Incorrectamente:  " + lotesIncorrectos.size() + " lotes (" + mensajesNoConservados + " mensajes).")
//												.append(CARACTER_SEPARADOR_LINEAS);
//									}
//									
//								}
//
//								
//							} else {
//								descripcionEstado.append(CARACTER_TAB).append("Historico del servicio "+servicio.getNombre().toUpperCase()+" ("+servicio.getServicioId()+"): No existen lotes para historificar. ").append(CARACTER_SEPARADOR_LINEAS);
//								logger.info("No existen lotes en el servicio con ID "+servicio.getServicioId()+" con fecha anterior o igual a "+auxS+".");
//							}
//							
//							logger.info("Lotes procesados Correctamente:" + lotesCorrectos.toString() + ".");
//							logger.info("Se han conservado correctamente:" + mensajesConservados + " mensajes.");
//							logger.info("Lotes procesados Incorrectamente:" + lotesIncorrectos.toString() + ".");
//							logger.info("Lotes NO HISTORIFICADOS:"+lotesNoHistorificados.size()+" :" + lotesNoHistorificados.toString() + ".");
//							logger.info("NO Se han conservado correctamente:" + mensajesNoConservados + " mensajes.");
//							
//							listaLotesEnvios.clear();
//							listaLotesEnvios=null;
//							lotesCorrectos.clear();
//							lotesCorrectos = null;
//							lotesIncorrectos.clear();
//							lotesIncorrectos = null;
//							mensajesConservados = 0;
//							mensajesNoConservados = 0;
//						
//						//Se realiza la busqueda de aquellos lotes de envio cuya fecha de modificacion es igual o mayor a la definida en el servicio
//
//
//						
//					} else {
//						if(null != servicio){
//							descripcionEstado.append(CARACTER_TAB).append("Historico del servicio "+servicio.getNombre().toUpperCase()+" ("+servicio.getServicioId()+"): No existen servicios con historificacion programada. ").append(CARACTER_SEPARADOR_LINEAS);
//							logger.info("No existen servicios con historificacion programada.");
//						}
//					}
//
//				}///bucle for servicio
//
//				if(exito){
//					procesoHistBean.setCodigoEstado(ESTADO_PROCESO_OK);
//					procesoHistBean.setDescripcionEstado("La historificacion se ha realizado correctamente.");
//				} else {
//					procesoHistBean.setCodigoEstado(ESTADO_PROCESO_KO);
//					procesoHistBean.setDescripcionEstado("Se han producido fallos en la historificacion. Para mas informacion, consulte logs.");
//				}
//
//				procesoHistBean.setFechaFin(new Date());
//				servicioProcesoHistoricos.newServicioProcesoHistoricos(procesoHistBean);
//			}
//
//		}catch (Exception e) {
//			logger.info("execute - FIN  Recuperar Historificacion - exception");
//			procesoHistBean.setCodigoEstado(ESTADO_PROCESO_KO);
//			descripcionEstado.append(CARACTER_TAB).append("Historico del servicio "+servicioAnalizado+" ("+idServicioAnalizado+"):"
//					+ " realizado correctamente. Se han procesado Correctamente "+lotesCorrectos.size()+" lotes (" + mensajesConservados + " mensajes). ").append("Se han procesado Incorrectamente:  " + lotesIncorrectos.size() + " lotes (" + mensajesNoConservados + " mensajes).")
//					.append(CARACTER_SEPARADOR_LINEAS);
//			
//			logger.info("Lotes procesados Correctamente:" + lotesCorrectos.toString() + ".");
//			logger.info("Se han conservado correctamente:" + mensajesConservados + " mensajes.");
//			logger.info("Lotes procesados Incorrectamente:" + lotesIncorrectos.toString() + ".");
//			logger.info("NO Se han conservado correctamente:" + mensajesNoConservados + " mensajes.");
//			
//			descripcionEstado.append(CARACTER_TAB).append("Se ha producido una excepcion. Para mas informacion, consulte logs. ").append(CARACTER_SEPARADOR_LINEAS);
//			procesoHistBean.setDescripcionEstado("Se han producido fallos en la historificacion. Para mas informacion, consulte logs.");
//			procesoHistBean.setFechaFin(new Date());
//			servicioProcesoHistoricos.newServicioProcesoHistoricos(procesoHistBean);
//			logger.error("HistorificacionJob.Execute " , e);
//		}
//		finally{
//				//Se envia un correo informando del resultado de la ejecucion del JOB
//				SendMailService sendMailService = new SendMailService();
//				try {
//					sendMailService.initJob(NOMBRE_JOB, procesoHistBean.getCodigoEstado(), descripcionEstado.toString(), properties, tblParametrosServidorManager);
//				} catch (ServletException e) {
//					logger.error("HistorificacionJob.Execute " , e);
//				}
//		
//				Calendar fechaFin = Calendar.getInstance();
//				logger.info("execute - Fecha fin: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(fechaFin.getTime()));
//				long tiempo2 = fechaFin.getTimeInMillis();
//				long tiempo = tiempo2 - tiempo1;
//				logger.info("execute - Duracion del Proceso de historificacion: " + tiempo + " milisegundos");
//		}
	}

	/**
	 * Inicializar variables.
	 *
	 * @param applicationContext the application context
	 * @throws JobExecutionException the job execution exception
	 */
	private void inicializarVariables(ApplicationContext applicationContext) throws JobExecutionException {
		try {
			servicioProcesoHistoricos = (ServicioProcesoHistoricos) applicationContext.getBean("servicioProcesoHistoricosImpl");
			servicioServicios = (ServicioServicio) applicationContext.getBean("servicioServicioImpl");
			servicioLotesEnvios = (ServicioLotesEnvios) applicationContext.getBean("servicioLotesEnviosImpl");
			servicioMensajes = (ServicioMensajes) applicationContext.getBean("servicioMensajesImpl");
			tblParametrosServidorManager = (TblParametrosServidorManagerImpl) applicationContext.getBean("tblParametrosServidorManagerImpl");
			tblMensajesManager = (TblMensajesManagerImpl) applicationContext.getBean(TBLMENSAJESMANA);
			properties  = (PlataformaMensajeriaProperties) applicationContext.getBean("plataformaMensajeriaProperties");
			
		} catch (Exception objException) {
			logger.error("HistorificacionJob - InicializarVariables - Error: " + objException);
			throw new JobExecutionException("Un error en el planificador");
		}
	}

}
