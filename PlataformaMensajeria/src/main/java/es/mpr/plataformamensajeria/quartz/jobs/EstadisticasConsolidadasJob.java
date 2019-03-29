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

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.plataformamensajeria.iop.managerimpl.TblParametrosServidorManagerImpl;
import es.minhap.sim.model.TblEstadistitcasCons;
import es.minhap.sim.model.TblGestionEnviosHist;
import es.mpr.plataformamensajeria.beans.JobBean;
import es.mpr.plataformamensajeria.beans.ProcesoConsBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAdjuntoEmailHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioDestinatarioHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioHistoricoHist;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioLotesEnviosHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajesAdjuntosHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajesHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesoConsolidadas;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.web.action.servicios.SendMailService;

/**
 * Clase EstadisticasConsolidadasJob.
 */
@Service("estadisticasConsolidadasJob")
public class EstadisticasConsolidadasJob implements Job {

	/**  logger. */
	private static Logger logger = Logger.getLogger(EstadisticasConsolidadasJob.class);

	/**  servicio proceso consolidadas. */
	private ServicioProcesoConsolidadas servicioProcesoConsolidadas;
	
	/**  servicio servicios. */
	private ServicioServicio servicioServicios;
	
	/**  servicio lotes envios historicos. */
	private ServicioLotesEnviosHistoricos servicioLotesEnviosHistoricos;
	
	/**  servicio mensajes historicos. */
	private ServicioMensajesHistoricos servicioMensajesHistoricos;
	
	/**  servicio mensajes adjuntos historicos. */
	private ServicioMensajesAdjuntosHistoricos servicioMensajesAdjuntosHistoricos;
	
	/**  servicio adjunto email historicos. */
	private ServicioAdjuntoEmailHistoricos servicioAdjuntoEmailHistoricos;
	
	/**  servicio destinatario historicos. */
	private ServicioDestinatarioHistoricos servicioDestinatarioHistoricos;
	
	/**  servicio historico hist. */
	private ServicioHistoricoHist servicioHistoricoHist;
	
	/**  servicio gestion envios historicos. */
	private ServicioGestionEnviosHistoricos servicioGestionEnviosHistoricos;
	
	/**  servicio servidor. */
	private ServicioServidor servicioServidor;
	
	/**  properties. */
	private PlataformaMensajeriaProperties properties;
	
	/** Constante MAX. */
	private static final Integer MAX = 1000;
	
	/**  nombre job. */
	private static String NOMBRE_JOB = "Consolidacion estadisticas";
	
	/**  estado proceso ok. */
	private static String ESTADO_PROCESO_OK = "OK";
	
	/**  estado proceso ko. */
	private static String ESTADO_PROCESO_KO = "KO";
	
	/**  caracter separador lineas. */
	private static String CARACTER_SEPARADOR_LINEAS = "<br>"; // El salto de linea esta preparado para el correo en formato HTML
	
	/**  caracter tab. */
	private static String CARACTER_TAB = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; // El tab esta preparado para el correo en formato HTML
	
	/**  job bean. */
	private JobBean jobBean = null;
	
	/**  tbl parametros manager. */
	private TblParametrosServidorManagerImpl tblParametrosServidorManager;

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException{
		SchedulerContext schedulerContext;
		try {
			schedulerContext = context.getScheduler().getContext();
			
			ApplicationContext applicationContext = (ApplicationContext) schedulerContext.get("applicationContext");
			this.inicializarVariables(applicationContext);
			ejecutar();
		} catch (SchedulerException e) {
			logger.error("Error ScheduleContext en ejecucion Job consolidacion", e);
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
			logger.error("Error ejecucion Job manual Consolidacion: ", e);
		}

	}
	
	/**
	 * Ejecutar.
	 */
	private void ejecutar() {
		logger.info("execute - INICIO Estadisticas consolidadas");
		Calendar fechaIni = Calendar.getInstance();
		logger.info("execute - Fecha comienzo: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(fechaIni.getTime()));
		long tiempo1 = fechaIni.getTimeInMillis();
		StringBuilder descripcionEstado = new StringBuilder();

		ProcesoConsBean procesoConsBean = new ProcesoConsBean();
		procesoConsBean.setFechaInicio(new Date());
		ArrayList<Integer> lotesCorrectos = new ArrayList<>();
		ArrayList<Integer> lotesIncorrectos = new ArrayList<>();
		ArrayList<Integer> lotesSinMensajes = new ArrayList<>();
		ArrayList<Integer> lotesNoCoincideSuma = new ArrayList<>();
		String servicioAnalizado = "";
		Integer idServicioAnalizado = 0;
		Integer mensajesConservados = 0;
		Integer mensajesNoConservados = 0;
	

		try {
			
			// Buscamos los servicios que tienen fecha definido fecha consolidada
			List<ServicioBean> listaServicios;
			if (null != jobBean && null != jobBean.getServicioId()){
				listaServicios = new ArrayList<>();
				ServicioBean sb = new ServicioBean();
				sb.setServicioId(jobBean.getServicioId().intValue());
				listaServicios.add(servicioServicios.loadServicio(sb));
			}else{
				listaServicios = servicioServicios.getServiciosCons();
			}

			if (null != listaServicios) {
				 for(ServicioBean servicio : listaServicios){
//					 if (servicio.getServicioId() != 522){
//						 continue;
//					 }
 				    List<List<Long>> listasLotesEnviosHistoricos = new ArrayList<>();
					lotesCorrectos = new ArrayList<>();
					lotesIncorrectos = new ArrayList<>();
					lotesSinMensajes = new ArrayList<>();
					lotesNoCoincideSuma = new ArrayList<>();
					mensajesConservados = 0;
					mensajesNoConservados = 0;
					
					servicioAnalizado = servicio.getNombre();
					idServicioAnalizado = servicio.getServicioId();
					Calendar calendar = Calendar.getInstance();
					
					//Modificamos la fecha inicio si es mediante boton
					if (null != jobBean && null != jobBean.getFecha()){
						calendar.setTime(jobBean.getFecha());
					}else{
						// Calculamos la fecha a partir de la cual se realiza el historico de mensajes
						Integer conservacion = servicio.getConservacion();
						calendar.add(Calendar.YEAR, -conservacion); // Le restamos a la fecha actual los anios marcados en el atributo de conservacion del servicio
					}
					logger.info("Consolidamos hasta fecha: " + new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime()));

					if (null != servicio && null != servicio.getConservacion() && null != servicio.getServicioId()) {
						String auxS = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());

						logger.info("Consulta Lotes envios: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));

						// Se realiza la busqueda de aquellos lotes de envio historicos cuya fecha de modificacion es igual o mayor a la definida en el servicio
						listasLotesEnviosHistoricos = servicioLotesEnviosHistoricos.getListasLotesEnviosHistoricos(servicio.getServicioId(), calendar.getTime());
						
						if (null != listasLotesEnviosHistoricos && !listasLotesEnviosHistoricos.isEmpty()) {
							/*
							 * Se comprueba para cada lote historico la fecha 'ultimoenvio' es igual o mayor a la definida en el servicio. Si es asi, se consolidan los mensajes historicos del lote historico.
							 * la busqueda se realiza en la tabla gestionenvios que seguro no tiene filas null
							 */
							int a = listasLotesEnviosHistoricos.size()-1;
							Integer total = ((a * 1000) 
									+ (listasLotesEnviosHistoricos.get(listasLotesEnviosHistoricos.size()-1).size()));
							logger.info("CONSOLIDACION DE----> :" + ((a * 1000) 
									+ (listasLotesEnviosHistoricos.get(listasLotesEnviosHistoricos.size()-1).size())) + " lotes, del SERVICIO ->" + servicio.getServicioId());
							for (List<Long> listaLotes : listasLotesEnviosHistoricos){//por si hay más de MAX lotes a historificar del servicio
								int contador = 0;
								for (Long idLote : listaLotes) {
									try{
										contador++;
										logger.info("CONSOLIDANDO lote ---> " + contador + " de " + total);
										logger.info("ANALIZANDO LOTE:" + idLote);
										logger.info("Consulta Mensajes: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));
										
										// Se obtienen todos los mensajes historicos del lote
										List<List<Long>> listasMensajesHistoricos = servicioMensajesHistoricos.getTodosMensajesLoteHistorificar(idLote, calendar.getTime());
										
										for (List<Long> listaMensajesHistoricosCons : listasMensajesHistoricos) {
											boolean consolidamosLote=false;
											if (null != listaMensajesHistoricosCons && !listaMensajesHistoricosCons.isEmpty()){
												logger.info("TOTAL: "+(MAX*(listasMensajesHistoricos.size()-1) + listasMensajesHistoricos.get(listasMensajesHistoricos.size()-1).size())+" Mensajes.");
												consolidamosLote = true;
											}else{//Es un lote sin mensajes
												logger.info("No existen mensajes en el lote de envio historico con ID " + idLote + " con fecha anterior o igual a " + auxS + ". ");
												servicioLotesEnviosHistoricos.deleteHist(idLote);
											}
											if(consolidamosLote){
												//cargamos Mensajes Adjuntos de la lista de mensajes
												List<Long> listaIdMensajesAdjuntosHist = servicioMensajesAdjuntosHistoricos.getIdMensajesAdjuntosCons(listaMensajesHistoricosCons);
												List<Long> listaIdAdjuntosHist = servicioAdjuntoEmailHistoricos.getTodosIdAdjuntosCons(listaMensajesHistoricosCons);
												List<Long> listaIdDestinatariosHist = servicioDestinatarioHistoricos.getTodosIdDestinatarioCons(listaMensajesHistoricosCons);
												List<Long> listaIdDestinatariosMensajesHist = servicioDestinatarioHistoricos.getTodosIdDestinatarioMensajesCons(listaMensajesHistoricosCons);
												List<Long> listaHistoricoHist = servicioHistoricoHist.getTodosIdHistoricosCons(listaMensajesHistoricosCons);
												List<TblGestionEnviosHist> listaGestionEnviosHist = servicioGestionEnviosHistoricos.getTodosGestionEnviosCons(listaMensajesHistoricosCons);
												
												// Se van calculando la informacion de las estadisticas consolidadas
												if (null != listaGestionEnviosHist && !listaGestionEnviosHist.isEmpty()) {
	
													logger.info("Proceso generar estadisticas consolidadas: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));
	
													/*
													 * En el caso de que un lote disponga de mas de un mensaje y que estos tengas los mismos valores de servicio, servidor, estado, anio y mes, documento usuario, codigo SIA, codigo organismo y codigo organismo pagador, lo que hay que hacer
													 * es aumentar el valor de 'numTotal' del nuevo registro de estadistica consolidada y 'eliminar' dicho registro para evitar que el valor de 'numTotal' sea incorrecto
													 */
													List<TblEstadistitcasCons> listaEstadisticasConsolidadas = obtenterEstadisticasConsolidadas(listaGestionEnviosHist);
	
													/*
													 * Evaluamos que el numTotal de todas las estadisticas coincida con el numero de registros de Gestion de Envios historicos
													 */
													int numTotalConsolidados = 0;
	
													if (null != listaEstadisticasConsolidadas && !listaEstadisticasConsolidadas.isEmpty()) {
														for (TblEstadistitcasCons estCon : listaEstadisticasConsolidadas) {
															numTotalConsolidados = numTotalConsolidados + estCon.getNumtotal();
														}
													}
													if (listaGestionEnviosHist.size() == numTotalConsolidados) {
														boolean exitoLE = false;
	
														try {
															exitoLE = servicioProcesoConsolidadas.procesoConsolidacion(listaEstadisticasConsolidadas, idLote, listaMensajesHistoricosCons, 
																	listaIdAdjuntosHist, listaIdMensajesAdjuntosHist, listaIdDestinatariosHist,	
																	listaHistoricoHist, listaIdDestinatariosMensajesHist, listaGestionEnviosHist);
	
															if (exitoLE) {
																lotesCorrectos.add(idLote.intValue());
																mensajesConservados = mensajesConservados + listaMensajesHistoricosCons.size();
																
															} else {
																
																lotesIncorrectos.add(idLote.intValue());
																mensajesNoConservados = mensajesNoConservados + listaMensajesHistoricosCons.size();
																logger.info("Procesada lote: " + idLote);
															}
	
														} catch (Exception e) {
															logger.error("Lanzando eliminacion en BBDD:  ", e);
															lotesIncorrectos.add(idLote.intValue());
														}
	
													} else {
														lotesNoCoincideSuma.add(idLote.intValue());
														
														logger.info("Consolidacion de estadisticas del servicio " + servicio.getServicioId() + ": y lote:" + idLote.intValue() + " se han producido fallos. El numero de registros de gestion de envios historicos no coincide con la suma del numero total de registros de las estadisticas consolidadas.");
													}
												}
												
											}
										}//for de listaMensajesHistoricos
									
										//eliminamos el lote porque ya está todo consolidado Para que no haya excepciones y continue se hace la comprobacion
										if (null != servicioLotesEnviosHistoricos.getLoteEnvioHist(idLote)){
											servicioLotesEnviosHistoricos.deleteHist(idLote);
										}
									}catch(Exception e){
										logger.error("Se ha producido un error en la historificacion del lote --->" +idLote, e);
									}
								}// for IDLOTE este es el for que vamos completando con las cosas a realizar
							
							} /// del for por si hay más de 1000 lotes a consolidar
						} else {
							logger.info("No existen lotes historicos en el servicio con ID " + servicio.getServicioId() + " con fecha anterior o igual a " + auxS + ".");
						}

					} // del if servicio!=null...

					logger.info("Datos Servicio " + servicio.getServicioId() + ".");
					descripcionEstado.append(CARACTER_TAB).append("Consolidacion de estadisticas del servicio " + servicio.getNombre().toUpperCase() + " (" + servicio.getServicioId() + "): ")
							.append("Se han procesado Correctamente:  " + lotesCorrectos.size() + " lotes (" + mensajesConservados + " mensajes). ").append("Se han procesado Incorrectamente:  " + lotesIncorrectos.size() + " lotes (" + mensajesNoConservados + " mensajes).")
							.append(CARACTER_SEPARADOR_LINEAS);

					logger.info("Lotes procesados Correctamente:" + lotesCorrectos.toString() + ".");
					logger.info("Se han conservado correctamente:" + mensajesConservados + " mensajes.");
					logger.info("Lotes procesados Incorrectamente:" + lotesIncorrectos.toString() + ".");
					logger.info("NO Se han conservado correctamente:" + mensajesNoConservados + " mensajes.");
					logger.info("Lotes procesados Sin Mensajes:" + lotesSinMensajes.toString() + ".");
					logger.info("No coincide la suma de registros en los lotes: " + lotesNoCoincideSuma.toString() + ".");

					lotesCorrectos.clear();
					lotesCorrectos = null;
					lotesIncorrectos.clear();
					lotesIncorrectos = null;
					lotesSinMensajes.clear();
					lotesSinMensajes = null;
					lotesNoCoincideSuma.clear();
					lotesNoCoincideSuma = null;
					mensajesConservados = 0;
					mensajesNoConservados = 0;

				}// del for de todos los servicios
			} // del if null != listaServicios
		
			descripcionEstado.append(CARACTER_SEPARADOR_LINEAS).append(CARACTER_TAB).append("Para mas informacion, consulte logs.").append(CARACTER_SEPARADOR_LINEAS).append(CARACTER_SEPARADOR_LINEAS).append(CARACTER_SEPARADOR_LINEAS);
			procesoConsBean.setCodigoEstado(ESTADO_PROCESO_OK);
			procesoConsBean.setDescripcionEstado("La consolidacion de estadisticas se ha realizado correctamente.");
			procesoConsBean.setFechaFin(new Date());
			servicioProcesoConsolidadas.newServicioProcesoConsolidadas(procesoConsBean);

		} catch (Exception e) {
			logger.info("execute - FIN  Recuperar Estadísticas consolidadas - exception");
			procesoConsBean.setCodigoEstado(ESTADO_PROCESO_KO);

			descripcionEstado.append(CARACTER_TAB).append("Consolidacion de estadisticas del servicio " + servicioAnalizado.toUpperCase() + " (" + idServicioAnalizado + "): ")
					.append("Se han procesado Correctamente:  " + lotesCorrectos.size() + " lotes (" + mensajesConservados + " mensajes). ").append("Se han procesado Incorrectamente:  " + lotesIncorrectos.size() + " lotes (" + mensajesNoConservados + " mensajes).")
					.append(CARACTER_SEPARADOR_LINEAS);

			logger.info("Lotes procesados Correctamente:" + lotesCorrectos.toString() + ".");
			logger.info("Se han conservado correctamente:" + mensajesConservados + " mensajes.");
			logger.info("Lotes procesados Incorrectamente:" + lotesIncorrectos.toString() + ".");
			logger.info("NO Se han conservado correctamente:" + mensajesNoConservados + " mensajes.");
			logger.info("Lotes procesados Sin Mensajes:" + lotesSinMensajes.toString() + ".");
			logger.info("No coincide la suma de registros en los lotes: " + lotesNoCoincideSuma.toString() + ".");

			descripcionEstado.append(CARACTER_TAB).append("Se ha producido una excepcion. Para mas informacion, consulte logs. ").append(CARACTER_SEPARADOR_LINEAS);
			procesoConsBean.setDescripcionEstado("Se han producido fallos en la consolidacion de estadisticas. Para mas informacion, consulte logs.");
			procesoConsBean.setFechaFin(new Date());

			servicioProcesoConsolidadas.newServicioProcesoConsolidadas(procesoConsBean);
			logger.error("execute - ERROR  Recuperar Estadísticas consolidadas - exception", e);
		}finally{
			descripcionEstado.append(CARACTER_SEPARADOR_LINEAS).append(CARACTER_SEPARADOR_LINEAS).append("Un saludo,").append(CARACTER_SEPARADOR_LINEAS);
			
			// Se envia un correo informando del resultado de la ejecucion del JOB
			SendMailService sendMailService = new SendMailService();
			Calendar fechaFin = Calendar.getInstance();
			logger.info("execute - Fecha fin: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(fechaFin.getTime()));
			long tiempo2 = fechaFin.getTimeInMillis();
			long tiempo = tiempo2 - tiempo1;
			logger.info("execute - Duracion del Proceso de Estadisticas consolidadas: " + tiempo + " milisegundos");
			try {
				sendMailService.initJob(NOMBRE_JOB, procesoConsBean.getCodigoEstado(), descripcionEstado.toString(), properties, tblParametrosServidorManager);
			} catch (ServletException e) {
				logger.error("execute - ERROR  Enviando email - exception", e);
			}
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
			
			servicioProcesoConsolidadas = (ServicioProcesoConsolidadas) applicationContext.getBean("servicioProcesoConsolidadasImpl");
			servicioServicios = (ServicioServicio) applicationContext.getBean("servicioServicioImpl");
			servicioLotesEnviosHistoricos = (ServicioLotesEnviosHistoricos) applicationContext.getBean("servicioLotesEnviosHistoricosImpl");
			servicioMensajesHistoricos = (ServicioMensajesHistoricos) applicationContext.getBean("servicioMensajesHistoricosImpl");
			servicioMensajesAdjuntosHistoricos = (ServicioMensajesAdjuntosHistoricos) applicationContext.getBean("servicioMensajesAdjuntosHistoricosImpl");
			servicioAdjuntoEmailHistoricos = (ServicioAdjuntoEmailHistoricos) applicationContext.getBean("servicioAdjuntoEmailHistoricosImpl");
			servicioDestinatarioHistoricos = (ServicioDestinatarioHistoricos) applicationContext.getBean("servicioDestinatarioHistoricosImpl");
			servicioHistoricoHist = (ServicioHistoricoHist) applicationContext.getBean("servicioHistoricoHistImpl");
			servicioGestionEnviosHistoricos = (ServicioGestionEnviosHistoricos) applicationContext.getBean("servicioGestionEnviosHistoricosImpl");
			servicioServidor = (ServicioServidor) applicationContext.getBean("servicioServidorImpl");
			tblParametrosServidorManager = (TblParametrosServidorManagerImpl) applicationContext.getBean("tblParametrosServidorManagerImpl");
			properties  = (PlataformaMensajeriaProperties) applicationContext.getBean("plataformaMensajeriaProperties");
		
		} catch (Exception objException) {
			logger.error("EstadisticasConsolidadasJob - InicializarVariables - Error: " + objException);
			throw new JobExecutionException("Un error en el planificador");
		}
	}

	/**
	 * Obtenter estadisticas consolidadas.
	 *
	 * @param listaGestionEnviosHist the lista gestion envios hist
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	private List<TblEstadistitcasCons> obtenterEstadisticasConsolidadas(List<TblGestionEnviosHist> listaGestionEnviosHist) throws BusinessException {

		// Recuperamos los servidores para despues agregar su nombre a las estadisticas
		List<ServidorBean> listaServidores = servicioServidor.getAllServidoresBBDD();

		List<TblEstadistitcasCons> listaEstadisticasConsolidadas = new ArrayList<>();

		List<Long> listaGestionEnvioIdsAgregados = new ArrayList<>();

		for (TblGestionEnviosHist geh : listaGestionEnviosHist) {
			TblEstadistitcasCons estadisticasConsolidadas = new TblEstadistitcasCons();
			int numTotal = 1;
			boolean agregado = false;

			// Evaluamos que el registro que queremos comprobar no haya sido 'agregado'
			// a un registro previo en su valor de 'numTotal'
			for (Long gestionEnvioId : listaGestionEnvioIdsAgregados) {
				if (gestionEnvioId.equals(geh.getMensajeid())) {
					agregado = true;
					break;
				}
			}

			// Si el registro no ha sido agregado, realizamos la evaluacion de condiciones
			if (!agregado) {

				for (TblGestionEnviosHist gehAux : listaGestionEnviosHist) {
					if (!geh.getMensajeid().equals(gehAux.getMensajeid())) {
						if (geh.getServicioid().equals(gehAux.getServicioid()) && geh.getEstadoid().equals(gehAux.getEstadoid()) && geh.getAnio().equals(gehAux.getAnio()) && geh.getMes().equals(gehAux.getMes()) && geh.getServidorid().equals(gehAux.getServidorid()) && ((null == geh
								.getDocusuario() && null == gehAux.getDocusuario()) || (null != geh.getDocusuario() && null != gehAux.getDocusuario() && geh.getDocusuario().equals(gehAux.getDocusuario()))) && ((null == geh.getCodsia() && null == gehAux.getCodsia()) || (null != geh
								.getCodsia() && null != gehAux.getCodsia() && geh.getCodsia().equals(gehAux.getCodsia()))) && ((null == geh.getCodorganismo() && null == gehAux.getCodorganismo()) || (null != geh.getCodorganismo() && null != gehAux.getCodorganismo() && geh
								.getCodorganismo().equals(gehAux.getCodorganismo()))) && ((null == geh.getCodorganismopagador() && null == gehAux.getCodorganismopagador()) || (null != geh.getCodorganismopagador() && null != gehAux.getCodorganismopagador() && geh
								.getCodorganismopagador().equals(gehAux.getCodorganismopagador())))) {

							numTotal++;
							listaGestionEnvioIdsAgregados.add(gehAux.getMensajeid());
						}
					}
				}

				estadisticasConsolidadas.setServidorid(geh.getServidorid());

				// Agregamos al nuevo registro el nombre del servidor
				for (ServidorBean servidor : listaServidores) {
					if (null != geh.getServidorid() && null != servidor && null != servidor.getServidorid() && servidor.getServidorid().equals(Long.valueOf(geh.getServidorid()))) {
						estadisticasConsolidadas.setServidornombre(servidor.getNombre());
						break;
					}
				}

				estadisticasConsolidadas.setAplicacionid(geh.getAplicacionid());
				estadisticasConsolidadas.setAplicacionnombre(geh.getAplicacion());
				estadisticasConsolidadas.setServicioid(geh.getServicioid());
				estadisticasConsolidadas.setServicionombre(geh.getServicio());
				estadisticasConsolidadas.setCanalid(geh.getCanalid());
				estadisticasConsolidadas.setCanalnombre(geh.getCanal());
				estadisticasConsolidadas.setEstadoid(geh.getEstadoid());
				estadisticasConsolidadas.setEstadonombre(geh.getEstado());
				estadisticasConsolidadas.setAnno(geh.getAnio());
				estadisticasConsolidadas.setMes(geh.getMes() + "_" + geh.getAnio());
				estadisticasConsolidadas.setNumtotal(numTotal);
				estadisticasConsolidadas.setDocusuario(geh.getDocusuario());
				estadisticasConsolidadas.setCodsia(geh.getCodsia());
				estadisticasConsolidadas.setCodorganismo(geh.getCodorganismo());
				estadisticasConsolidadas.setCodorganismopagador(geh.getCodorganismopagador());

				listaEstadisticasConsolidadas.add(estadisticasConsolidadas);
			}

		}

		return listaEstadisticasConsolidadas;
	}

}
