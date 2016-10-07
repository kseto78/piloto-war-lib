package es.mpr.plataformamensajeria.quartz.jobs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.springframework.context.ApplicationContext;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.HistoricoDTO;
import es.mpr.plataformamensajeria.beans.ProcesoConsBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.model.AdjuntoEmailHistoricosJPA;
import es.mpr.plataformamensajeria.model.DestinatarioHistoricosJPA;
import es.mpr.plataformamensajeria.model.EstadisticasConsolidadasJPA;
import es.mpr.plataformamensajeria.model.GestionEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.HistoricoHistJPA;
import es.mpr.plataformamensajeria.model.LotesEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.MensajesAdjuntosHistoricosJPA;
import es.mpr.plataformamensajeria.model.MensajesHistoricosJPA;
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
import es.mpr.plataformamensajeria.web.action.servicios.SendMailService;

public class EstadisticasConsolidadasJob implements Job {

	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EstadisticasConsolidadasJob.class);

	private static String NOMBRE_JOB = "Consolidacion estadisticas";
	private static String ESTADO_PROCESO_OK = "OK";
	private static String ESTADO_PROCESO_KO = "KO";
	private static String CARACTER_SEPARADOR_LINEAS = "<br>"; // El salto de linea esta preparado para el correo en formato HTML
	private static String CARACTER_TAB = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; // El tab esta preparado para el correo en formato HTML

	private ServicioProcesoConsolidadas servicioProcesoConsolidadas;
	private ServicioServicio servicioServicios;
	private ServicioLotesEnviosHistoricos servicioLotesEnviosHistoricos;
	private ServicioMensajesHistoricos servicioMensajesHistoricos;
	private ServicioMensajesAdjuntosHistoricos servicioMensajesAdjuntosHistoricos;
	private ServicioAdjuntoEmailHistoricos servicioAdjuntoEmailHistoricos;
	private ServicioDestinatarioHistoricos servicioDestinatarioHistoricos;
	private ServicioHistoricoHist servicioHistoricoHist;
	private ServicioGestionEnviosHistoricos servicioGestionEnviosHistoricos;
	private ServicioServidor servicioServidor;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		this.inicializarVariables(context);
		logger.info("execute - INICIO Estadisticas consolidadas");
		Calendar fechaIni = Calendar.getInstance();
		logger.info("execute - Fecha comienzo: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(fechaIni.getTime()));
		long tiempo1 = fechaIni.getTimeInMillis();
		StringBuilder descripcionEstado = new StringBuilder();

		ProcesoConsBean procesoConsBean = new ProcesoConsBean();
		procesoConsBean.setFechaInicio(new Date());
		ArrayList<Integer> lotesCorrectos = new ArrayList<Integer>();
		ArrayList<Integer> lotesIncorrectos = new ArrayList<Integer>();
		ArrayList<Integer> lotesSinMensajes = new ArrayList<Integer>();
		ArrayList<Integer> lotesNoCoincideSuma = new ArrayList<Integer>();
		String servicioAnalizado = "";
		Integer idServicioAnalizado = 0;
		Integer mensajesConservados = 0;
		Integer mensajesNoConservados = 0;
	

		try {

			// Buscamos los servicios que tienen fecha definido fecha consolidada
			List<ServicioBean> listaServicios = servicioServicios.getServiciosCons();

			if (null != listaServicios) {
				 for(ServicioBean servicio : listaServicios){
//					 for (int i = 8; i<9;i++){
//						 ServicioBean servicio = listaServicios.get(i);
					 Map<LotesEnviosHistoricosJPA,HistoricoDTO> mapLotes = new HashMap<LotesEnviosHistoricosJPA, HistoricoDTO>();
					lotesCorrectos = new ArrayList<Integer>();
					lotesIncorrectos = new ArrayList<Integer>();
					lotesSinMensajes = new ArrayList<Integer>();
					lotesNoCoincideSuma = new ArrayList<Integer>();
					mensajesConservados = 0;
					mensajesNoConservados = 0;
					int contadorLote = 0;
					
					servicioAnalizado = servicio.getNombre();
					idServicioAnalizado = servicio.getServicioId();
					Calendar calendar = Calendar.getInstance();

					if (null != servicio && null != servicio.getConservacion() && null != servicio.getServicioId()) {
						// Calculamos la fecha a partir de la cual se realiza el historico de mensajes
						Integer conservacion = servicio.getConservacion();

						calendar.add(Calendar.YEAR, -conservacion); // Le restamos a la fecha actual los anios marcados en el atributo de conservacion del servicio

						String auxS = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());

						logger.info("Consulta Lotes envios: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));

						// Se realiza la busqueda de aquellos lotes de envio historicos cuya fecha de modificacion es igual o mayor a la definida en el servicio
//						List<LotesEnviosHistoricosJPA> listaLotesEnviosHist = servicioLotesEnviosHistoricos.getLotesEnviosJPAHist(servicio.getServicioId(), calendar.getTime());
						mapLotes = servicioLotesEnviosHistoricos.getLotesEnviosJPAHistMap(servicio.getServicioId(), calendar.getTime(),
								servicioAdjuntoEmailHistoricos,servicioDestinatarioHistoricos,servicioHistoricoHist,servicioGestionEnviosHistoricos,
								servicioMensajesAdjuntosHistoricos);

						
						if (null != mapLotes && !mapLotes.isEmpty()) {
//						if (null != listaLotesEnviosHist && !listaLotesEnviosHist.isEmpty()) {

							/*
							 * Se comprueba para cada lote historico que los mensajes cuya fecha de 'ultimoenvio' es igual o mayor a la definida en el servicio. Si es asi, se consolidan los mensajes historicos del lote historico.
							 */
							for (Map.Entry<LotesEnviosHistoricosJPA,HistoricoDTO> entry : mapLotes.entrySet()){
								LotesEnviosHistoricosJPA loteEnvio = entry.getKey();
								HistoricoDTO his = entry.getValue();
//							for (LotesEnviosHistoricosJPA loteEnvio : listaLotesEnviosHist) {
								logger.info("ANALIZANDO LOTE:" + loteEnvio.getId()+ " Numero: " + contadorLote +" de "+mapLotes.size());
								List<LotesEnviosHistoricosJPA> listaLotesEnviosHistoricosCons = new ArrayList<LotesEnviosHistoricosJPA>();
								List<MensajesHistoricosJPA> listaMensajesHistoritosCons = new ArrayList<MensajesHistoricosJPA>();

								List<MensajesAdjuntosHistoricosJPA> listaMensajesAdjuntosHist = his.getListaMensajesAdjuntosHist();
								List<DestinatarioHistoricosJPA> listaDestinatariosHist = his.getListaDestinatariosHist();
								List<HistoricoHistJPA> listaHistoricoHist = his.getListaHistoricoHist();
								List<GestionEnviosHistoricosJPA> listaGestionEnviosHist = his.getListaGestionEnviosHist();
								List<AdjuntoEmailHistoricosJPA> listaAdjuntosEmailHist = his.getListaAdjuntosEmailHist();

								logger.info("Consulta Mensajes: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));
								// Se obtienen todos los mensajes historicos del lote
								List<MensajesHistoricosJPA> listaMensajesHistoricos = servicioMensajesHistoricos.getTodosMensajeJPACons(servicio.getServicioId(), loteEnvio.getLoteEnvioId());
								logger.info("TOTAL: "+listaMensajesHistoricos.size()+" Mensajes.");
								// Variable para almacenar los mensajes del lote de envio
								List<MensajesHistoricosJPA> listaMensajesAux = new ArrayList<MensajesHistoricosJPA>();

								for (MensajesHistoricosJPA mensaje : listaMensajesHistoricos) {
									if (null != mensaje && null != mensaje.getLoteEnvioId() && mensaje.getLoteEnvioId().equals(loteEnvio.getLoteEnvioId())) {
										listaMensajesAux.add(mensaje);
									}
								}
								listaMensajesHistoricos.clear();
								listaMensajesHistoricos = null;

								if (null != listaMensajesAux && !listaMensajesAux.isEmpty()) {
									int numMensajesTotal = listaMensajesAux.size();
									int numMensajesReq = 0;
									for (MensajesHistoricosJPA mensaje : listaMensajesAux) {
										// Si la fecha de ultimo envio es anterior o igual a la fecha de conservacion
										if (mensaje.getUltimoEnvio().compareTo(calendar.getTime()) <= 0) {
											numMensajesReq++;
										}
									}
									if (numMensajesReq > 1) {
										logger.info("El lote de envio historico " + loteEnvio.getLoteEnvioId() + " tiene " + numMensajesReq + " mensajes. ");
									}

									// Se consolida el lote junto a todos sus mensajes
									if (numMensajesTotal == numMensajesReq) {
										listaLotesEnviosHistoricosCons.add(loteEnvio);
										for (MensajesHistoricosJPA mensaje : listaMensajesAux) {
											listaMensajesHistoritosCons.add(mensaje);
										}

										logger.info("Consulta Adjuntos/Mensaje: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));

										// Se obtienen todos los adjuntos de los mensajes historicos del lote
//										listaMensajesAdjuntosHist.addAll(servicioMensajesAdjuntosHistoricos.getTodosMensajesAdjuntosJPACons(servicio.getServicioId(), loteEnvio.getLoteEnvioId()));

//										// Se agrega la informacion de los envios
//										logger.info("Consulta Adjuntos: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));
//										listaAdjuntosEmailHist.addAll(servicioAdjuntoEmailHistoricos.getTodosAdjuntosJPACons(servicio.getServicioId(), loteEnvio.getLoteEnvioId()));
//										logger.info("Consulta Destinatarios: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));
//										listaDestinatariosHist.addAll(servicioDestinatarioHistoricos.getTodosDestinatarioJPACons(servicio.getServicioId(), loteEnvio.getLoteEnvioId()));
//										logger.info("Consulta Historico: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));
//										listaHistoricoHist.addAll(servicioHistoricoHist.getTodosHistoricosJPACons(servicio.getServicioId(), loteEnvio.getLoteEnvioId()));
//										logger.info("Consulta Gestion Envios: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));
//										listaGestionEnviosHist.addAll(servicioGestionEnviosHistoricos.getTodosGestionEnviosJPACons(servicio.getServicioId(), loteEnvio.getLoteEnvioId()));

									} else {
										logger.info("No se puede consolidas el lote de envios con ID " + loteEnvio.getLoteEnvioId() + " con fecha anterior o igual a " + auxS + ". ");
									}

								} else {
									// Se consolida el lote de envio historico
									listaLotesEnviosHistoricosCons.add(loteEnvio);
									logger.info("No existen mensajes en el lote de envio historico con ID " + loteEnvio.getLoteEnvioId() + " con fecha anterior o igual a " + auxS + ". ");
								}
								

								// Si existen Lotes con mensajes para consolidar, se procede a obtener toda la informacion de dichos lotes
								if (null != listaLotesEnviosHistoricosCons && !listaLotesEnviosHistoricosCons.isEmpty()) {

									logger.info("Se van a consolidar " + listaLotesEnviosHistoricosCons.size() + " lotes de envios del servicio con ID " + servicio.getServicioId() + " " + "con fecha anterior o igual a " + auxS + ". ");

									if (null != listaMensajesHistoritosCons && !listaMensajesHistoritosCons.isEmpty()) {
										logger.info("Se van a consolidar " + listaMensajesHistoritosCons.size() + " mensajes historicos del servicio con ID " + servicio.getServicioId() + " " + "con fecha anterior o igual a " + auxS + ".");

										// Se van calculando la informacion de las estadisticas consolidadas
										if (null != listaGestionEnviosHist && !listaGestionEnviosHist.isEmpty()) {

											logger.info("Proceso generar estadisticas consolidadas: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss.SSS").format(new Date()));

											/*
											 * En el caso de que un lote disponga de mas de un mensaje y que estos tengas los mismos valores de servicio, servidor, estado, anio y mes, documento usuario, codigo SIA, codigo organismo y codigo organismo pagador, lo que hay que hacer
											 * es aumentar el valor de 'numTotal' del nuevo registro de estadistica consolidada y 'eliminar' dicho registro para evitar que el valor de 'numTotal' sea incorrecto
											 */
											List<EstadisticasConsolidadasJPA> listaEstadisticasConsolidadas = obtenterEstadisticasConsolidadas(listaGestionEnviosHist);

											/*
											 * Evaluamos que el numTotal de todas las estadisticas coincida con el numero de registros de Gestion de Envios historicos
											 */
											int numTotalConsolidados = 0;

											if (null != listaEstadisticasConsolidadas && !listaEstadisticasConsolidadas.isEmpty()) {
												for (EstadisticasConsolidadasJPA estCon : listaEstadisticasConsolidadas) {
													numTotalConsolidados = numTotalConsolidados + estCon.getNumTotal();
												}
											}

											if (listaGestionEnviosHist.size() == numTotalConsolidados) {

												boolean exitoLE = false;

												try {// TODO: descomentar lo de abajo
													exitoLE = servicioProcesoConsolidadas.procesoConsJPA(listaEstadisticasConsolidadas, listaLotesEnviosHistoricosCons, listaMensajesHistoritosCons, listaAdjuntosEmailHist, listaMensajesAdjuntosHist, listaDestinatariosHist,
															listaHistoricoHist, listaGestionEnviosHist);

													if (exitoLE) {
														lotesCorrectos.add(loteEnvio.getLoteEnvioId());
														mensajesConservados = mensajesConservados + listaMensajesHistoritosCons.size();
														
													} else {
														
														lotesIncorrectos.add(loteEnvio.getLoteEnvioId());
														mensajesNoConservados = mensajesNoConservados + listaMensajesHistoritosCons.size();
														logger.info("Procesada lote: " + loteEnvio.getLoteEnvioId());
													}

												} catch (Exception e) {
													
													lotesIncorrectos.add(loteEnvio.getLoteEnvioId());
												}

											} else {
												lotesNoCoincideSuma.add(loteEnvio.getLoteEnvioId());
												
												logger.info("Consolidacion de estadisticas del servicio " + servicio.getServicioId() + ": y lote:" + loteEnvio.getLoteEnvioId() + " se han producido fallos. El numero de registros de gestion de envios historicos no coincide con la suma del numero total de registros de las estadisticas consolidadas.");
											}

										}
									}
								} else {
									
									lotesSinMensajes.add(loteEnvio.getLoteEnvioId());
									logger.info("No existen lotes historicos en el servicio con ID " + servicio.getServicioId() + " con fecha anterior o igual a " + auxS + ".");
								}

								listaLotesEnviosHistoricosCons.clear();
								listaLotesEnviosHistoricosCons = null;
								listaMensajesHistoritosCons.clear();
								listaMensajesHistoritosCons = null;

								listaMensajesAdjuntosHist.clear();
								listaMensajesAdjuntosHist = null;
								listaDestinatariosHist.clear();
								listaDestinatariosHist = null;
								listaHistoricoHist.clear();
								listaHistoricoHist = null;
								listaGestionEnviosHist.clear();
								listaGestionEnviosHist = null;
								listaAdjuntosEmailHist.clear();
								listaAdjuntosEmailHist = null;

							} // del for de procesar 1 lote

						}

					} // dil if de todos los servicios

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
			}

		
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

			e.printStackTrace();
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
				sendMailService.initJob(NOMBRE_JOB, procesoConsBean.getCodigoEstado(), descripcionEstado.toString());
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}

	}

	private void inicializarVariables(JobExecutionContext context) throws JobExecutionException {
		try {
			SchedulerContext schedulerContext = context.getScheduler().getContext();
			ApplicationContext applicationContext = (ApplicationContext) schedulerContext.get("applicationContext");
			servicioProcesoConsolidadas = (ServicioProcesoConsolidadas) applicationContext.getBean("servicioProcesoConsolidadas");
			servicioServicios = (ServicioServicio) applicationContext.getBean("servicioServicios");
			servicioLotesEnviosHistoricos = (ServicioLotesEnviosHistoricos) applicationContext.getBean("servicioLotesEnviosHistoricos");
			servicioMensajesHistoricos = (ServicioMensajesHistoricos) applicationContext.getBean("servicioMensajesHistoricos");
			servicioMensajesAdjuntosHistoricos = (ServicioMensajesAdjuntosHistoricos) applicationContext.getBean("servicioMensajesAdjuntosHistoricos");
			servicioAdjuntoEmailHistoricos = (ServicioAdjuntoEmailHistoricos) applicationContext.getBean("servicioAdjuntoEmailHistoricos");
			servicioDestinatarioHistoricos = (ServicioDestinatarioHistoricos) applicationContext.getBean("servicioDestinatarioHistoricos");
			servicioHistoricoHist = (ServicioHistoricoHist) applicationContext.getBean("servicioHistoricoHist");
			servicioGestionEnviosHistoricos = (ServicioGestionEnviosHistoricos) applicationContext.getBean("servicioGestionEnviosHistoricos");
			servicioServidor = (ServicioServidor) applicationContext.getBean("servicioServidores");
		} catch (Exception objException) {
			logger.error("HistorificacionJob - InicializarVariables - Error: " + objException.getMessage());
			objException.printStackTrace();
			throw new JobExecutionException("Un error en el planificador");
		}
	}

	private List<EstadisticasConsolidadasJPA> obtenterEstadisticasConsolidadas(List<GestionEnviosHistoricosJPA> listaGestionEnviosHist) throws BusinessException {

		// Recuperamos los servidores para despues agregar su nombre a las estadisticas
		List<ServidorBean> listaServidores = servicioServidor.getAllServidoresBBDD();

		List<EstadisticasConsolidadasJPA> listaEstadisticasConsolidadas = new ArrayList<EstadisticasConsolidadasJPA>();

		List<Integer> listaGestionEnvioIdsAgregados = new ArrayList<Integer>();

		for (GestionEnviosHistoricosJPA geh : listaGestionEnviosHist) {
			EstadisticasConsolidadasJPA estadisticasConsolidadas = new EstadisticasConsolidadasJPA();
			int numTotal = 1;
			boolean agregado = false;

			// Evaluamos que el registro que queremos comprobar no haya sido 'agregado'
			// a un registro previo en su valor de 'numTotal'
			for (Integer gestionEnvioId : listaGestionEnvioIdsAgregados) {
				if (gestionEnvioId.equals(geh.getMensajeId())) {
					agregado = true;
					break;
				}
			}

			// Si el registro no ha sido agregado, realizamos la evaluacion de condiciones
			if (!agregado) {

				for (GestionEnviosHistoricosJPA gehAux : listaGestionEnviosHist) {
					if (!geh.getMensajeId().equals(gehAux.getMensajeId())) {
						if (geh.getServicioId().equals(gehAux.getServicioId()) && geh.getEstadoId().equals(gehAux.getEstadoId()) && geh.getAnio().equals(gehAux.getAnio()) && geh.getMes().equals(gehAux.getMes()) && geh.getServidorId().equals(gehAux.getServidorId()) && ((null == geh
								.getDocUsuario() && null == gehAux.getDocUsuario()) || (null != geh.getDocUsuario() && null != gehAux.getDocUsuario() && geh.getDocUsuario().equals(gehAux.getDocUsuario()))) && ((null == geh.getCodSIA() && null == gehAux.getCodSIA()) || (null != geh
								.getCodSIA() && null != gehAux.getCodSIA() && geh.getCodSIA().equals(gehAux.getCodSIA()))) && ((null == geh.getCodOrganismo() && null == gehAux.getCodOrganismo()) || (null != geh.getCodOrganismo() && null != gehAux.getCodOrganismo() && geh
								.getCodOrganismo().equals(gehAux.getCodOrganismo()))) && ((null == geh.getCodOrganismoPagador() && null == gehAux.getCodOrganismoPagador()) || (null != geh.getCodOrganismoPagador() && null != gehAux.getCodOrganismoPagador() && geh
								.getCodOrganismoPagador().equals(gehAux.getCodOrganismoPagador())))) {

							numTotal++;
							listaGestionEnvioIdsAgregados.add(gehAux.getMensajeId());
						}
					}
				}

				estadisticasConsolidadas.setServidorId(geh.getServidorId());

				// Agregamos al nuevo registro el nombre del servidor
				for (ServidorBean servidor : listaServidores) {
					if (null != servidor && null != servidor.getServidorId() && servidor.getServidorId().equals(Long.valueOf(geh.getServidorId()))) {
						estadisticasConsolidadas.setServidorNombre(servidor.getNombre());
						break;
					}
				}

				estadisticasConsolidadas.setAplicacionId(geh.getAplicacionId());
				estadisticasConsolidadas.setAplicacionNombre(geh.getAplicacion());
				estadisticasConsolidadas.setServicioId(geh.getServicioId());
				estadisticasConsolidadas.setServicioNombre(geh.getServicio());
				estadisticasConsolidadas.setCanalId(geh.getCanalId());
				estadisticasConsolidadas.setCanalNombre(geh.getCanal());
				estadisticasConsolidadas.setEstadoId(geh.getEstadoId());
				estadisticasConsolidadas.setEstadoNombre(geh.getEstado());
				estadisticasConsolidadas.setAnno(geh.getAnio());
				estadisticasConsolidadas.setMes(geh.getMes() + "_" + geh.getAnio());
				estadisticasConsolidadas.setNumTotal(numTotal);
				estadisticasConsolidadas.setDocUsuario(geh.getDocUsuario());
				estadisticasConsolidadas.setCodSIA(geh.getCodSIA());
				estadisticasConsolidadas.setCodOrganismo(geh.getCodOrganismo());
				estadisticasConsolidadas.setCodOrganismoPagador(geh.getCodOrganismoPagador());

				listaEstadisticasConsolidadas.add(estadisticasConsolidadas);
			}

		}

		return listaEstadisticasConsolidadas;
	}

}
