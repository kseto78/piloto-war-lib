package es.mpr.plataformamensajeria.quartz.jobs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.springframework.context.ApplicationContext;

import es.mpr.plataformamensajeria.beans.ProcesoHistBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.model.AdjuntoEmailHistoricosJPA;
import es.mpr.plataformamensajeria.model.DestinatarioHistoricosJPA;
import es.mpr.plataformamensajeria.model.DestinatariosMensajesHistoricosJPA;
import es.mpr.plataformamensajeria.model.GestionEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.HistoricoHistJPA;
import es.mpr.plataformamensajeria.model.LotesEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.MensajesAdjuntosHistoricosJPA;
import es.mpr.plataformamensajeria.model.MensajesHistoricosJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAdjuntoEmail;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioDestinatario;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioHistorico;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioLotesEnvios;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajes;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioMensajesAdjuntos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesoHistoricos;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.web.action.servicios.SendMailService;


public class HistorificacionJob implements Job {

	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HistorificacionJob.class);

	private static String NOMBRE_JOB = "Historificacion";
	private static String ESTADO_PROCESO_OK = "OK";
	private static String ESTADO_PROCESO_KO = "KO";
	private static String CARACTER_SEPARADOR_LINEAS = "<br>"; //El salto de linea esta preparado para el correo en formato HTML
	private static String CARACTER_TAB = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; //El tab esta preparado para el correo en formato HTML


	private ServicioProcesoHistoricos servicioProcesoHistoricos;
	private ServicioServicio servicioServicios;
	private ServicioLotesEnvios servicioLotesEnvios;
	private ServicioMensajes servicioMensajes;
	private ServicioMensajesAdjuntos servicioMensajesAdjuntos;
	private ServicioAdjuntoEmail servicioAdjuntoEmail;
	private ServicioDestinatario servicioDestinatario;
	private ServicioHistorico servicioHistorico;
	private ServicioGestionEnvios servicioGestionEnvios;
	


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ArrayList<Integer> lotesCorrectos = new ArrayList<Integer>();
		ArrayList<Integer> lotesIncorrectos = new ArrayList<Integer>();
		ArrayList<Integer> lotesNoHistorificados = new ArrayList<Integer>();
		String servicioAnalizado = "";
		Integer idServicioAnalizado = 0;
		Integer mensajesConservados = 0;
		Integer mensajesNoConservados = 0;
		this.inicializarVariables(context);
		logger.info("execute - INICIO Historificacion");
		Calendar fechaIni = Calendar.getInstance();
		logger.info("execute - Fecha comienzo: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(fechaIni.getTime()));
		long tiempo1 = fechaIni.getTimeInMillis();
		StringBuilder descripcionEstado = new StringBuilder();

		ProcesoHistBean procesoHistBean = new ProcesoHistBean();
		procesoHistBean.setFechaInicio(new Date());

		try{

			String auxS = new String();
			boolean exito = true;

			//Buscamos los servicios que tienen fecha definido fecha historico
			List<ServicioBean> listaServicios = servicioServicios.getServiciosHistorico();

			if(null != listaServicios){


				for(ServicioBean servicio : listaServicios){
							
					
					Calendar calendar = Calendar.getInstance();

					if(null != servicio && null != servicio.getHistorificacion() && null != servicio.getServicioId()){
						//Calculamos la fecha a partir de la cual se realiza el historico de mensajes
						Integer historificacion = servicio.getHistorificacion();
						logger.info("Servicio ID "+servicio.getServicioId());

						calendar.add(Calendar.DATE, -historificacion); //Le restamos a la fecha actual los dias marcados en el atributo de historico del servicio
						auxS = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());

						logger.info("Consulta Lotes envios: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));

						lotesCorrectos = new ArrayList<Integer>();
						lotesIncorrectos = new ArrayList<Integer>();
						lotesNoHistorificados = new ArrayList<Integer>();
						mensajesConservados = 0;
						mensajesNoConservados = 0;
						
						servicioAnalizado = servicio.getNombre();
						idServicioAnalizado = servicio.getServicioId();
						
							List<LotesEnviosHistoricosJPA> listaLotesEnvios = servicioLotesEnvios.getLotesEnviosJPAHist(servicio.getServicioId(), calendar.getTime());
							
							if(null != listaLotesEnvios && !listaLotesEnvios.isEmpty()){
								logger.info("Existen "+ listaLotesEnvios.size() +" lotes de envios del servicio con ID "+servicio.getServicioId()+" con fecha anterior o igual a "+auxS+". ");

								int numLotesEnvioExito = 0;
								int numLotesEnvioFallo = 0;
								/*Se comprueba para cada lote que los mensajes cuya fecha de 'ultimoenvio' es igual o mayor a la definida en el servicio
								y que ademas TODOS los mensajes del lote se encuentren en estado final (ENVIADO o ANULADO).
								Si es asi, se historifican los mensajes del lote. */
								for(LotesEnviosHistoricosJPA loteEnvio : listaLotesEnvios){ 
									
									boolean exitoLE = false;

									if(null!=loteEnvio && null!=loteEnvio.getLoteEnvioId()){

										boolean historificamosLote=false;
										List<MensajesHistoricosJPA> listaMensajesHist = new ArrayList<MensajesHistoricosJPA>();
										//Se obtienen todos los mensajes del lote actual
										logger.info("Obtener mensajes del lote "+ loteEnvio.getLoteEnvioId() +" del servicio con ID "+servicio.getServicioId()+". ");
										List<MensajesHistoricosJPA> listaMensajesAux = servicioMensajes.getTodosMensajesJPAHist(servicio.getServicioId(), loteEnvio.getLoteEnvioId());

										if(null != listaMensajesAux && !listaMensajesAux.isEmpty()){
											int numMensajesTotal = listaMensajesAux.size();
											int numMensajesReq = 0;
											for(MensajesHistoricosJPA mensaje : listaMensajesAux){
												//Si la fecha de ultimo envio es anterior o igual a la fecha de historificacion
												if(mensaje.getUltimoEnvio().compareTo(calendar.getTime()) <= 0){
													if(mensaje.getEstadoActual().equals("ENVIADO") || mensaje.getEstadoActual().equals("ANULADO")){
														numMensajesReq++;
													}
												}
											}
											if(numMensajesReq > 1){
												logger.info("El lote de envio "+ loteEnvio.getLoteEnvioId() +" tiene "+numMensajesReq+" mensajes. ");
											}

											//Se historifica el lote junto a todos sus mensajes
											if(numMensajesTotal==numMensajesReq){
												historificamosLote=true;
												for(MensajesHistoricosJPA mensaje : listaMensajesAux){
													listaMensajesHist.add(mensaje);
												}
											} else {
												logger.info("No se puede historificar el lote de envios con ID "+loteEnvio.getLoteEnvioId()+" con fecha anterior o igual a "+auxS+". ");
											}

										} else {
											historificamosLote=false;
											lotesNoHistorificados.add(loteEnvio.getLoteEnvioId());
											logger.info("No existen mensajes en el lote de envios con ID "+loteEnvio.getLoteEnvioId()+" con fecha anterior o igual a "+auxS+". ");
										}
										listaMensajesAux.clear();listaMensajesAux=null;
										

										if(historificamosLote){
											if(null != listaMensajesHist && !listaMensajesHist.isEmpty()){
												
												logger.info("Se van a historificar "+ listaMensajesHist.size() +" mensajes del servicio con ID "+servicio.getServicioId()+" "
														+ "con fecha anterior o igual a "+auxS+".");

												List<MensajesAdjuntosHistoricosJPA> listaMensajesAdjuntosHist = new ArrayList<MensajesAdjuntosHistoricosJPA>();
												List<AdjuntoEmailHistoricosJPA> listaAdjuntosEmail = new ArrayList<AdjuntoEmailHistoricosJPA>();
												List<DestinatarioHistoricosJPA> listaDestinatarios = new ArrayList<DestinatarioHistoricosJPA>();
												List<HistoricoHistJPA> listaHistorico = new ArrayList<HistoricoHistJPA>();
												List<GestionEnviosHistoricosJPA> listaGestionEnvios = new ArrayList<GestionEnviosHistoricosJPA>();
												List<DestinatariosMensajesHistoricosJPA> listaDestinatariosMensajesHistoricos = new ArrayList<DestinatariosMensajesHistoricosJPA>();
												

												logger.info("Consulta Adjuntos/Mensaje: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));

												//Se obtienen todos los adjuntos de los mensajes del lote
												listaMensajesAdjuntosHist = servicioMensajesAdjuntos.getTodosMensajesAdjuntosJPAHist(servicio.getServicioId(), loteEnvio.getLoteEnvioId());
												//Si existen adjuntos a los mensajes, se recuperan de base de datos
												if(null != listaMensajesAdjuntosHist && !listaMensajesAdjuntosHist.isEmpty()){
													logger.info("Consulta Adjuntos: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));
													listaAdjuntosEmail = servicioAdjuntoEmail.getTodosAdjuntosJPAHist(servicio.getServicioId(), loteEnvio.getLoteEnvioId());
													logger.info("Se van a historificar "+ listaAdjuntosEmail.size() +" ficheros adjuntos del servicio con ID "+servicio.getServicioId()+".");
												}
												logger.info("Consulta Destinatarios: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));
												listaDestinatarios = servicioDestinatario.getTodosDestinatarioJPAHist(servicio.getServicioId(), loteEnvio.getLoteEnvioId());
												if(null!=listaDestinatarios){
													logger.info("Se van a historificar "+ listaDestinatarios.size() +" destinatarios del servicio con ID "+servicio.getServicioId()+".");
												}
												logger.info("Consulta Historico: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));
												listaHistorico = servicioHistorico.getTodosHistoricosJPAHist(servicio.getServicioId(), loteEnvio.getLoteEnvioId());
												if(null!=listaHistorico){
													logger.info("Se van a historificar "+ listaHistorico.size() +" registros historicos del servicio con ID "+servicio.getServicioId()+".");
												}
												logger.info("Consulta Gestion Envios: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));
												listaGestionEnvios = servicioGestionEnvios.getTodosGestionEnviosJPAHist(servicio.getServicioId(), loteEnvio.getLoteEnvioId());
												if(null!=listaGestionEnvios){
													logger.info("Se van a historificar "+ listaGestionEnvios.size() +" registros de la gestion de envios del servicio con ID "+servicio.getServicioId()+".");
												}
												logger.info("Consulta Destinatarios Mensajes: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));
												listaDestinatariosMensajesHistoricos = servicioDestinatario.getTodosDestinatariosMensajes(servicio.getServicioId(), loteEnvio.getLoteEnvioId());
												if(null!=listaDestinatariosMensajesHistoricos){
													logger.info("Se van a historificar "+ listaDestinatariosMensajesHistoricos.size() +" registros de la tabla destinatariosMensajes del servicio "+servicio.getServicioId()+".");
												}

												logger.info("Proceso extraer info lote envio: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));

												//Se realiza el historico por cada lote de envio
												try{
													exitoLE = servicioProcesoHistoricos.procesoHistoricoJPALotesEnvio(loteEnvio, listaMensajesHist,
															listaAdjuntosEmail, listaMensajesAdjuntosHist, listaDestinatarios, listaHistorico, listaGestionEnvios, listaDestinatariosMensajesHistoricos);
													

													if(exitoLE){
														numLotesEnvioExito++;
														lotesCorrectos.add(loteEnvio.getLoteEnvioId());
														mensajesConservados = mensajesConservados + listaMensajesHist.size();
														listaMensajesAdjuntosHist.clear();listaMensajesAdjuntosHist=null;
														listaAdjuntosEmail.clear();listaAdjuntosEmail=null;
														listaDestinatarios.clear();listaDestinatarios=null;
														listaHistorico.clear();listaHistorico=null;
														listaGestionEnvios.clear();listaGestionEnvios=null;
														listaDestinatariosMensajesHistoricos.clear(); listaDestinatariosMensajesHistoricos = null;
													} else {
														numLotesEnvioFallo++;
														lotesIncorrectos.add(loteEnvio.getLoteEnvioId());
														mensajesNoConservados = mensajesNoConservados + listaMensajesHist.size();
														logger.info("Se ha producido un error al historificar el lote "+loteEnvio.getLoteEnvioId()+". ");
													}

												} catch (Exception e) {
													numLotesEnvioFallo++;
													lotesIncorrectos.add(loteEnvio.getLoteEnvioId());
													mensajesNoConservados = mensajesNoConservados + listaMensajesHist.size();
													logger.info("Se ha producido un error al historificar el lote "+loteEnvio.getLoteEnvioId()+". ");
												}
												
											}else{
												//Estamos en un lote que no tiene mensajes
												try{
													exitoLE = servicioProcesoHistoricos.procesoHistoricoJPALotesEnvio(loteEnvio, listaMensajesHist,
															null, null, null, null, null,null);

													if(exitoLE){
														numLotesEnvioExito++;
														lotesCorrectos.add(loteEnvio.getLoteEnvioId());
														mensajesConservados = mensajesConservados + listaMensajesHist.size();
													} else {
														numLotesEnvioFallo++;
														lotesIncorrectos.add(loteEnvio.getLoteEnvioId());
														mensajesNoConservados = mensajesNoConservados + listaMensajesHist.size();
														logger.info("Se ha producido un error al historificar el lote "+loteEnvio.getLoteEnvioId()+". ");
													}

												} catch (Exception e) {
													numLotesEnvioFallo++;
													lotesIncorrectos.add(loteEnvio.getLoteEnvioId());
													mensajesNoConservados = mensajesNoConservados + listaMensajesHist.size();
													logger.info("Se ha producido un error al historificar el lote "+loteEnvio.getLoteEnvioId()+". ");
												}
											}
										}
										listaMensajesHist.clear();listaMensajesHist=null;
									}
								}

								if(numLotesEnvioFallo==0){
									if(numLotesEnvioExito==0){
										descripcionEstado.append(CARACTER_TAB).append("Historico del servicio "+servicio.getNombre().toUpperCase()+" ("+servicio.getServicioId()+"):"
												+ " No existen lotes para historificar. ").
												append(CARACTER_SEPARADOR_LINEAS);
									} else {
										descripcionEstado.append(CARACTER_TAB).append("Historico del servicio "+servicio.getNombre().toUpperCase()+" ("+servicio.getServicioId()+"):"
												+ " realizado correctamente. Se han procesado Correctamente "+lotesCorrectos.size()+" lotes (" + mensajesConservados + " mensajes). ")
												.append(CARACTER_SEPARADOR_LINEAS);
									}
									
								} else {
									exito = false;
									if(numLotesEnvioExito==0){
										descripcionEstado.append(CARACTER_TAB).append("Historico del servicio "+servicio.getNombre().toUpperCase()+" ("+servicio.getServicioId()+"): se han producido fallos."
												+ "No existen lotes para historificar. Para mas informacion, consulte logs. ").append(CARACTER_SEPARADOR_LINEAS);
									} else {
										descripcionEstado.append(CARACTER_TAB).append("Historico del servicio "+servicio.getNombre().toUpperCase()+" ("+servicio.getServicioId()+"):"
												+ " realizado correctamente. Se han procesado Correctamente "+lotesCorrectos.size()+" lotes (" + mensajesConservados + " mensajes). ").append("Se han procesado Incorrectamente:  " + lotesIncorrectos.size() + " lotes (" + mensajesNoConservados + " mensajes).")
												.append(CARACTER_SEPARADOR_LINEAS);
									}
									
								}

								
							} else {
								descripcionEstado.append(CARACTER_TAB).append("Historico del servicio "+servicio.getNombre().toUpperCase()+" ("+servicio.getServicioId()+"): No existen lotes para historificar. ").append(CARACTER_SEPARADOR_LINEAS);
								logger.info("No existen lotes en el servicio con ID "+servicio.getServicioId()+" con fecha anterior o igual a "+auxS+".");
							}
							
							logger.info("Lotes procesados Correctamente:" + lotesCorrectos.toString() + ".");
							logger.info("Se han conservado correctamente:" + mensajesConservados + " mensajes.");
							logger.info("Lotes procesados Incorrectamente:" + lotesIncorrectos.toString() + ".");
							logger.info("Lotes NO HISTORIFICADOS:"+lotesNoHistorificados.size()+" :" + lotesNoHistorificados.toString() + ".");
							logger.info("NO Se han conservado correctamente:" + mensajesNoConservados + " mensajes.");
							
							listaLotesEnvios.clear();listaLotesEnvios=null;
							lotesCorrectos.clear();
							lotesCorrectos = null;
							lotesIncorrectos.clear();
							lotesIncorrectos = null;
							mensajesConservados = 0;
							mensajesNoConservados = 0;
						
						//Se realiza la busqueda de aquellos lotes de envio cuya fecha de modificacion es igual o mayor a la definida en el servicio


						
					} else {
						if(null != servicio){
							descripcionEstado.append(CARACTER_TAB).append("Historico del servicio "+servicio.getNombre().toUpperCase()+" ("+servicio.getServicioId()+"): No existen servicios con historificacion programada. ").append(CARACTER_SEPARADOR_LINEAS);
							logger.info("No existen servicios con historificacion programada.");
						}
					}

				}

				if(exito){
					procesoHistBean.setCodigoEstado(ESTADO_PROCESO_OK);
					procesoHistBean.setDescripcionEstado("La historificacion se ha realizado correctamente.");
				} else {
					procesoHistBean.setCodigoEstado(ESTADO_PROCESO_KO);
					procesoHistBean.setDescripcionEstado("Se han producido fallos en la historificacion. Para mas informacion, consulte logs.");
				}

				procesoHistBean.setFechaFin(new Date());
				servicioProcesoHistoricos.newServicioProcesoHistoricos(procesoHistBean);
			}

		}catch (Exception e) {
			logger.info("execute - FIN  Recuperar Historificacion - exception");
			procesoHistBean.setCodigoEstado(ESTADO_PROCESO_KO);
			descripcionEstado.append(CARACTER_TAB).append("Historico del servicio "+servicioAnalizado+" ("+idServicioAnalizado+"):"
					+ " realizado correctamente. Se han procesado Correctamente "+lotesCorrectos.size()+" lotes (" + mensajesConservados + " mensajes). ").append("Se han procesado Incorrectamente:  " + lotesIncorrectos.size() + " lotes (" + mensajesNoConservados + " mensajes).")
					.append(CARACTER_SEPARADOR_LINEAS);
			
			logger.info("Lotes procesados Correctamente:" + lotesCorrectos.toString() + ".");
			logger.info("Se han conservado correctamente:" + mensajesConservados + " mensajes.");
			logger.info("Lotes procesados Incorrectamente:" + lotesIncorrectos.toString() + ".");
			logger.info("NO Se han conservado correctamente:" + mensajesNoConservados + " mensajes.");
			
			descripcionEstado.append(CARACTER_TAB).append("Se ha producido una excepcion. Para mas informacion, consulte logs. ").append(CARACTER_SEPARADOR_LINEAS);
			procesoHistBean.setDescripcionEstado("Se han producido fallos en la historificacion. Para mas informacion, consulte logs.");
			procesoHistBean.setFechaFin(new Date());
			servicioProcesoHistoricos.newServicioProcesoHistoricos(procesoHistBean);
			e.printStackTrace();
		}
		finally{
				//Se envia un correo informando del resultado de la ejecucion del JOB
				SendMailService sendMailService = new SendMailService();
				try {
					sendMailService.initJob(NOMBRE_JOB, procesoHistBean.getCodigoEstado(), descripcionEstado.toString());
				} catch (ServletException e) {
					e.printStackTrace();
				}
		
				Calendar fechaFin = Calendar.getInstance();
				logger.info("execute - Fecha fin: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(fechaFin.getTime()));
				long tiempo2 = fechaFin.getTimeInMillis();
				long tiempo = tiempo2 - tiempo1;
				logger.info("execute - Duracion del Proceso de historificacion: " + tiempo + " milisegundos");
		}
	}

	private void inicializarVariables(JobExecutionContext context) throws JobExecutionException {
		try {
			SchedulerContext schedulerContext = context.getScheduler().getContext();
			ApplicationContext applicationContext = (ApplicationContext) schedulerContext.get("applicationContext");
			servicioProcesoHistoricos = (ServicioProcesoHistoricos) applicationContext.getBean("servicioProcesoHistoricos");
			servicioServicios = (ServicioServicio) applicationContext.getBean("servicioServicios");
			servicioLotesEnvios = (ServicioLotesEnvios) applicationContext.getBean("servicioLotesEnvios");
			servicioMensajes = (ServicioMensajes) applicationContext.getBean("servicioMensajes");
			servicioMensajesAdjuntos = (ServicioMensajesAdjuntos) applicationContext.getBean("servicioMensajesAdjuntos");
			servicioAdjuntoEmail = (ServicioAdjuntoEmail) applicationContext.getBean("servicioAdjuntoEmail");
			servicioDestinatario = (ServicioDestinatario) applicationContext.getBean("servicioDestinatario");
			servicioHistorico = (ServicioHistorico) applicationContext.getBean("servicioHistorico");
			servicioGestionEnvios = (ServicioGestionEnvios) applicationContext.getBean("servicioGestionesEnvios");


		} catch (Exception objException) {
			logger.error("HistorificacionJob - InicializarVariables - Error: " + objException.getMessage());
			objException.printStackTrace();
			throw new JobExecutionException("Un error en el planificador");
		}
	}

}
