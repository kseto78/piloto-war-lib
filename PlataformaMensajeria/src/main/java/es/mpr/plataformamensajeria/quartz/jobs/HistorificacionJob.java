package es.mpr.plataformamensajeria.quartz.jobs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import es.minhap.plataformamensajeria.iop.managerimpl.TblParametrosServidorManagerImpl;
import es.mpr.plataformamensajeria.beans.JobBean;
import es.mpr.plataformamensajeria.beans.ProcesoHistBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioLotesEnvios;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajes;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesoHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.web.action.servicios.SendMailService;

/**
 * The Class HistorificacionJob.
 */
@Service("historificacionJob")
public class HistorificacionJob implements Job {

	protected static final String DDMMYYYY__HHMMS = "dd/MM/yyyy  HH:mm:ss.SSS";

	protected static final String BLANK = " (";

	protected static final String HISTORIFICACION = "HistorificacionJob.Execute ";

	protected static final String BLANKREALIZADO_REF = " realizado correctamente. Se han procesado Correctamente ";

	protected static final String HISTORICO_DEL_S = "Historico del servicio ";

	protected static final String BLANKLOTES_REF = " lotes (";

	protected static final String BLANKMENSAJESDOT = " mensajes).";

	protected static final String DOT = ".";

	protected static final String R_CONST_REF = "):";

	protected static final String DOT_REF = ". ";

	protected static final String LOTES_PROCESADO = "Lotes procesados Incorrectamente:";

	protected static final String BLANKMENSAJESDOT0 = " mensajes.";

	protected static final String SE_HA_PRODUCIDO = "Se ha producido un error al historificar el lote ";

	protected static final String NO_SE_HAN_CONSE = "NO Se han conservado correctamente:";

	protected static final String SE_HAN_PROCESAD = "Se han procesado Incorrectamente:  ";

	protected static final String LOTES_PROCESADO0 = "Lotes procesados Correctamente:";

	protected static final String SE_HAN_CONSERVA = "Se han conservado correctamente:";

	protected static final String SE_HAN_PRODUCID = "Se han producido fallos en la historificacion. Para mas informacion, consulte logs.";

	protected static final String CONSULTA_LOTES_REF = "Consulta Lotes envios: ";

	protected static final String SERVICIO_ID_REF = "Servicio ID ";

	protected static final String DDMMYYYY = "dd/MM/yyyy";

	protected static final String BLANKMENSAJESDOT1 = " mensajes). ";

	protected static final String BLANKCON_FECHA_REF = " con fecha anterior o igual a ";

	/**  logger. */
	private static Logger logger = Logger.getLogger(HistorificacionJob.class);

	/**  servicio proceso historicos. */
	private ServicioProcesoHistoricos servicioProcesoHistoricos;
	
	/**  servicio servicios. */
	private ServicioServicio servicioServicios;
	
	/**  servicio lotes envios. */
	private ServicioLotesEnvios servicioLotesEnvios;
	
	/**  servicio mensajes. */
	private ServicioMensajes servicioMensajes;
	
	
	/**  properties. */
	private PlataformaMensajeriaProperties properties;
	
	/**  nombre job. */
	private static String NOMBRE_JOB = "Historificacion";
	
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
		ArrayList<Integer> lotesCorrectos = new ArrayList<>();
		ArrayList<Integer> lotesIncorrectos = new ArrayList<>();
		ArrayList<Integer> lotesNoHistorificados = new ArrayList<>();
		String servicioAnalizado = "";
		Integer idServicioAnalizado = 0;
		Integer mensajesConservados = 0;
		Integer mensajesNoConservados = 0;
		logger.info("execute - INICIO Historificacion");
		Calendar fechaIni = Calendar.getInstance();
		logger.info("execute - Fecha comienzo: " + new SimpleDateFormat(DDMMYYYY__HHMMS).format(fechaIni.getTime()));
		long tiempo1 = fechaIni.getTimeInMillis();
		StringBuilder descripcionEstado = new StringBuilder();

		ProcesoHistBean procesoHistBean = new ProcesoHistBean();
		procesoHistBean.setFechaInicio(new Date());
		String infoEjecucion = "";
		try{

			String auxS = "";
			boolean exito = true;
			List<ServicioBean> listaServicios;
			
			//Buscamos los servicios que tienen fecha definido fecha historico
			if (null != jobBean && null != jobBean.getServicioId()){
				listaServicios = new ArrayList<>();
				ServicioBean sb = new ServicioBean();
				sb.setServicioId(jobBean.getServicioId().intValue());
				listaServicios.add(servicioServicios.loadServicio(sb));
			}else{
				listaServicios = servicioServicios.getServiciosHistorico();
			}
			
//			////esto es para borrar
			
			if(null != listaServicios){


				for(ServicioBean servicio : listaServicios){
					
					Calendar calendar = Calendar.getInstance();

					if(null != servicio && null != servicio.getHistorificacion() && null != servicio.getServicioId()){
						
						if (null != jobBean && null != jobBean.getFecha()){
							//Indicamos la fecha introducida en la pantalla
							logger.info(SERVICIO_ID_REF+servicio.getServicioId());
							calendar.setTime(jobBean.getFecha());
							auxS = new SimpleDateFormat(DDMMYYYY).format(calendar.getTime());
							logger.info(CONSULTA_LOTES_REF + new SimpleDateFormat(DDMMYYYY__HHMMS).format(new Date()));
							infoEjecucion = "Ejecución Manual realizada con busqueda de lotes hasta la fecha de: "+ new SimpleDateFormat(DDMMYYYY).format(jobBean.getFecha());
							
						}else{
							//Calculamos la fecha a partir de la cual se realiza el historico de mensajes
							Integer historificacion = servicio.getHistorificacion();
							logger.info(SERVICIO_ID_REF+servicio.getServicioId());
	
							calendar.add(Calendar.DATE, -historificacion); 
							//Le restamos a la fecha actual los dias marcados en el atributo de historico del servicio
							auxS = new SimpleDateFormat(DDMMYYYY).format(calendar.getTime());
	
							logger.info(CONSULTA_LOTES_REF + new SimpleDateFormat(DDMMYYYY__HHMMS).format(new Date()));
						}
						lotesCorrectos = new ArrayList<>();
						lotesIncorrectos = new ArrayList<>();
						lotesNoHistorificados = new ArrayList<>();
						mensajesConservados = 0;
						mensajesNoConservados = 0;
						
						servicioAnalizado = servicio.getNombre();
						idServicioAnalizado = servicio.getServicioId();
						List<Long> listaLotesEnvios = servicioLotesEnvios.getLotesEnviosTOHist(servicio.getServicioId(), calendar.getTime());
	
//					////esto es para borrar
							
							if(null != listaLotesEnvios && !listaLotesEnvios.isEmpty()){
								logger.info("Existen "+ 
									listaLotesEnvios.size() +" lotes de envios del servicio con ID "+servicio.getServicioId()+BLANKCON_FECHA_REF+auxS+DOT_REF);

								int numLotesEnvioExito = 0;
								int numLotesEnvioFallo = 0;
								/*Se comprueba para cada lote que los mensajes cuya fecha de 'ultimoenvio' es igual o mayor a la definida en el servicio
								y que ademas TODOS los mensajes del lote se encuentren en estado final (ENVIADO o ANULADO).
								Si es asi, se historifican los mensajes del lote. */
								for(Long idLote : listaLotesEnvios){
 	
									long startTime = System.currentTimeMillis();
																								
									boolean exitoLE = false;


										boolean historificamosLote=false;
										
										//Se obtienen todos los mensajes del lote actual
										logger.info("Obtener mensajes del lote "+ idLote +" del servicio con ID "+servicio.getServicioId()+DOT_REF);
										List<Long> listaMensajesHist = servicioMensajes.getTodosMensajesLoteHistorificar(idLote);

										if(null != listaMensajesHist && !listaMensajesHist.isEmpty()){
											logger.info("El lote de envio "+ idLote +" tiene "+listaMensajesHist.size()+" mensajes. ");
											historificamosLote = true;

										} else {
											///NO HAY MENSAJES A HISTORIFICAR Si el lote no tiene mensajes se historifica
											if (!servicioMensajes.testLoteSinMensajes(idLote)){
												historificamosLote=false;
												lotesNoHistorificados.add(idLote.intValue());
												logger.info("No existen mensajes en el lote de envios con ID "+idLote+BLANKCON_FECHA_REF+auxS+DOT_REF);
											
											}else{
	
												//Estamos en un lote que no tiene mensajes
												try{
													
													exitoLE = servicioProcesoHistoricos.procesoHistoricoLotesEnvio(idLote, listaMensajesHist);

													if(exitoLE){
														numLotesEnvioExito++;
														lotesCorrectos.add(idLote.intValue());
														mensajesConservados = mensajesConservados + listaMensajesHist.size();
													} else {
														numLotesEnvioFallo++;
														lotesIncorrectos.add(idLote.intValue());
														mensajesNoConservados = mensajesNoConservados + listaMensajesHist.size();
														logger.info(SE_HA_PRODUCIDO+idLote+DOT_REF);
													}

												} catch (Exception e) {
													numLotesEnvioFallo++;
													lotesIncorrectos.add(idLote.intValue());
													mensajesNoConservados = mensajesNoConservados + listaMensajesHist.size();
													logger.info(SE_HA_PRODUCIDO+idLote+DOT_REF);
													logger.error(SE_HA_PRODUCIDO+idLote+DOT_REF, e);
												}
											} 
											//fin else lote sin mensajes
										}
										//fin else no hay mensajes a historificar en el lote
										
										if(historificamosLote){
											logger.info("Se van a historificar "+ listaMensajesHist.size() +" mensajes del servicio con ID "+servicio.getServicioId()+" "
												+ "con fecha anterior o igual a "+auxS+DOT);

											

												logger.info("Proceso extraer info lote envio: " + new SimpleDateFormat(DDMMYYYY__HHMMS).format(new Date()));

											//Se realiza el historico por cada lote de envio
												try{
													exitoLE = servicioProcesoHistoricos.procesoHistoricoLotesEnvio(idLote, listaMensajesHist);
													 long stopTime = System.currentTimeMillis();
												     long elapsedTime = stopTime - startTime;
												     logger.info("Tiempo TOTAL Historificar lote "+idLote+" ------->" + elapsedTime);
													

													if(exitoLE){
														numLotesEnvioExito++;
														lotesCorrectos.add(idLote.intValue());
														mensajesConservados = mensajesConservados + listaMensajesHist.size();
													} else {
														numLotesEnvioFallo++;
														lotesIncorrectos.add(idLote.intValue());
														mensajesNoConservados = mensajesNoConservados + listaMensajesHist.size();
														logger.info(SE_HA_PRODUCIDO+idLote+DOT_REF);
													}

												} catch (Exception e) {
													numLotesEnvioFallo++;
													lotesIncorrectos.add(idLote.intValue());
													mensajesNoConservados = mensajesNoConservados + listaMensajesHist.size();
													logger.error(SE_HA_PRODUCIDO+idLote+DOT_REF, e);
												}
										
										}
										listaMensajesHist.clear();
										listaMensajesHist=null;
//									
								}

								if(numLotesEnvioFallo==0){
									if(numLotesEnvioExito==0){
										descripcionEstado.append(CARACTER_TAB).append(HISTORICO_DEL_S+servicio.getNombre().toUpperCase()+BLANK+servicio.getServicioId()+R_CONST_REF
												+ " No existen lotes para historificar. ").
												append(CARACTER_SEPARADOR_LINEAS);
									} else {
										descripcionEstado.append(CARACTER_TAB).append(HISTORICO_DEL_S+servicio.getNombre().toUpperCase()+BLANK+servicio.getServicioId()+R_CONST_REF
												+ BLANKREALIZADO_REF+lotesCorrectos.size()+BLANKLOTES_REF + mensajesConservados + BLANKMENSAJESDOT1)
												.append(CARACTER_SEPARADOR_LINEAS);
									}
									
								} else {
									exito = false;
									if(numLotesEnvioExito==0){
										descripcionEstado.append(CARACTER_TAB).append(HISTORICO_DEL_S+
											servicio.getNombre().toUpperCase()+BLANK+servicio.getServicioId()+"): se han producido fallos."
												+ "No existen lotes para historificar. Para mas informacion, consulte logs. ").append(CARACTER_SEPARADOR_LINEAS);
									} else {
										descripcionEstado.append(CARACTER_TAB).append(HISTORICO_DEL_S+servicio.getNombre().toUpperCase()+BLANK+servicio.getServicioId()+R_CONST_REF
												+ BLANKREALIZADO_REF+lotesCorrectos.size()+BLANKLOTES_REF + mensajesConservados + BLANKMENSAJESDOT1).append(SE_HAN_PROCESAD + 
													lotesIncorrectos.size() + BLANKLOTES_REF + mensajesNoConservados + BLANKMENSAJESDOT)
												.append(CARACTER_SEPARADOR_LINEAS);
									}
									
								}

								
							} else {
								descripcionEstado.append(CARACTER_TAB).append(HISTORICO_DEL_S+
									servicio.getNombre().toUpperCase()+BLANK+servicio.getServicioId()+"): No existen lotes para historificar. ").append(CARACTER_SEPARADOR_LINEAS);
								logger.info("No existen lotes en el servicio con ID "+servicio.getServicioId()+BLANKCON_FECHA_REF+auxS+DOT);
							}
							
							logger.info(LOTES_PROCESADO0 + lotesCorrectos + DOT);
							logger.info(SE_HAN_CONSERVA + mensajesConservados + BLANKMENSAJESDOT0);
							logger.info(LOTES_PROCESADO + lotesIncorrectos + DOT);
							logger.info("Lotes NO HISTORIFICADOS:"+lotesNoHistorificados.size()+" :" + lotesNoHistorificados + DOT);
							logger.info(NO_SE_HAN_CONSE + mensajesNoConservados + BLANKMENSAJESDOT0);
							
							listaLotesEnvios.clear();
							listaLotesEnvios=null;
							lotesCorrectos.clear();
							lotesCorrectos = null;
							lotesIncorrectos.clear();
							lotesIncorrectos = null;
							mensajesConservados = 0;
							mensajesNoConservados = 0;
						
						//Se realiza la busqueda de aquellos lotes de envio cuya fecha de modificacion es igual o mayor a la definida en el servicio


						
					} else {
						if(null != servicio){
							descripcionEstado.append(CARACTER_TAB).append(HISTORICO_DEL_S+
								servicio.getNombre().toUpperCase()+BLANK+servicio.getServicioId()+"): No existen servicios con historificacion programada. ").append(CARACTER_SEPARADOR_LINEAS);
							logger.info("No existen servicios con historificacion programada.");
						}
					}

				}
				///bucle for servicio

				if(exito){
					procesoHistBean.setCodigoEstado(ESTADO_PROCESO_OK);
					procesoHistBean.setDescripcionEstado("La historificacion se ha realizado correctamente.");
				} else {
					procesoHistBean.setCodigoEstado(ESTADO_PROCESO_KO);
					procesoHistBean.setDescripcionEstado(SE_HAN_PRODUCID);
				}

				procesoHistBean.setFechaFin(new Date());
				servicioProcesoHistoricos.newServicioProcesoHistoricos(procesoHistBean);
			}

		}catch (Exception e) {
			logger.info("execute - FIN  Recuperar Historificacion - exception");
			procesoHistBean.setCodigoEstado(ESTADO_PROCESO_KO);
			descripcionEstado.append(CARACTER_TAB).append(HISTORICO_DEL_S+servicioAnalizado+BLANK+idServicioAnalizado+R_CONST_REF
					+ BLANKREALIZADO_REF+lotesCorrectos.size()+BLANKLOTES_REF + mensajesConservados + BLANKMENSAJESDOT1).append(SE_HAN_PROCESAD + 
						lotesIncorrectos.size() + BLANKLOTES_REF + mensajesNoConservados + BLANKMENSAJESDOT)
					.append(CARACTER_SEPARADOR_LINEAS);
			
			logger.info(LOTES_PROCESADO0 + lotesCorrectos + DOT);
			logger.info(SE_HAN_CONSERVA + mensajesConservados + BLANKMENSAJESDOT0);
			logger.info(LOTES_PROCESADO + lotesIncorrectos + DOT);
			logger.info(NO_SE_HAN_CONSE + mensajesNoConservados + BLANKMENSAJESDOT0);
			
			descripcionEstado.append(CARACTER_TAB).append("Se ha producido una excepcion. Para mas informacion, consulte logs. ").append(CARACTER_SEPARADOR_LINEAS);
			procesoHistBean.setDescripcionEstado(SE_HAN_PRODUCID);
			procesoHistBean.setFechaFin(new Date());
			servicioProcesoHistoricos.newServicioProcesoHistoricos(procesoHistBean);
			logger.error(HISTORIFICACION , e);
		} finally{
				//Se envia un correo informando del resultado de la ejecucion del JOB
				SendMailService sendMailService = new SendMailService();
				if(!"".equals(infoEjecucion)){
					descripcionEstado.append(CARACTER_SEPARADOR_LINEAS + infoEjecucion);
				}
				try {
					sendMailService.initJob(NOMBRE_JOB, procesoHistBean.getCodigoEstado(), descripcionEstado.toString(), properties, tblParametrosServidorManager);
				} catch (ServletException e) {
					logger.error(HISTORIFICACION , e);
				}
		
				Calendar fechaFin = Calendar.getInstance();
				logger.info("execute - Fecha fin: " + new SimpleDateFormat(DDMMYYYY__HHMMS).format(fechaFin.getTime()));
				long tiempo2 = fechaFin.getTimeInMillis();
				long tiempo = tiempo2 - tiempo1;
				logger.info("execute - Duracion del Proceso de historificacion: " + tiempo + " milisegundos");
		}
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
			properties  = (PlataformaMensajeriaProperties) applicationContext.getBean("plataformaMensajeriaProperties");
			
		} catch (Exception objException) {
			logger.error("HistorificacionJob - InicializarVariables - Error: " + objException);
			throw new JobExecutionException("Un error en el planificador");
		}
	}

}
