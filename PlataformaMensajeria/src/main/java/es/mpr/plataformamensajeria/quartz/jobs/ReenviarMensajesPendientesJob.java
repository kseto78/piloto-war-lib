package es.mpr.plataformamensajeria.quartz.jobs;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import es.map.sim.jms.sender.SIMMessageSender;
import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblProcesosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.managerimpl.TblParametrosServidorManagerImpl;
import es.minhap.plataformamensajeria.iop.managerimpl.TblServiciosManagerImpl;
import es.minhap.plataformamensajeria.iop.misim.manager.ErroresManager;
import es.minhap.sim.model.TblProcesos;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.query.TblProcesosQuery;
import es.mpr.plataformamensajeria.beans.JobBean;
import es.mpr.plataformamensajeria.beans.ProcesoHistBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesoHistoricos;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.web.action.servicios.SendMailService;

/**
 * Clase para el Job de Reenvios de mensajes pendientes
 * 
 * @author everis
 * 
 */
@Service("reenviarMensajesPendientesJob")
public class ReenviarMensajesPendientesJob implements Job{
	private static final Logger LOG = Logger.getLogger(ReenviarMensajesPendientesJob.class);

	@Autowired
	private TblServiciosManager serviciosManager;
	
	@Autowired
	private ErroresManager erroresManager;

	@Resource(name = "TblMensajesManagerImpl")
	private TblMensajesManager tblMensajesManager;
	
	@Resource(name = "tblParametrosServidorManagerImpl")
	private TblParametrosServidorManagerImpl tblParametrosServidorManager;

	@Resource(name = "TblDestinatariosMensajesManagerImpl")
	private TblDestinatariosMensajesManager tblDestinatariosMensajesManager;
	
	@Resource(name = "TblProcesosManagerImpl")
	private TblProcesosManager tblProcesosManager;
	
	/**  servicio proceso historicos. */
	private ServicioProcesoHistoricos servicioProcesoHistoricos;

	@Resource
	ConnectionFactory pooledConnectionFactory;
	
	@Resource(name = "messageSender")
	private SIMMessageSender sender;
	
	/**  estado proceso ok. */
	private static String ESTADO_PROCESO_OK = "OK";
	
	/**  estado proceso ko. */
	private static String ESTADO_PROCESO_KO = "KO";
	
	/**  nombre job. */
	private static String NOMBRE_JOB = "Reenvio";
	
	/**  job bean. */
	private JobBean jobBean = null;
	
	/**  datos Servicios */
	private String datosServicios = null;

	private static String caracterSeparadorLineas = "<br>";
	
	/**  logger. */
	private static Logger logger = Logger.getLogger(ReenviarMensajesPendientesJob.class);
	
	/**  properties. */
	private PlataformaMensajeriaProperties properties;

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	// /MIGRADO
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SchedulerContext schedulerContext;
		List<TblProcesos> proceso = new ArrayList();
		String accion = "";
		Long accionId = null;
		String source = "";		
		
		try {
			schedulerContext = context.getScheduler().getContext();			
			ApplicationContext applicationContext = (ApplicationContext) schedulerContext.get("applicationContext");
			this.inicializarVariables(applicationContext);
			
			accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
			accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
			source = properties.getProperty("log.SOURCE_REENVIOJOBS", null);
			

			TblProcesosQuery query = new TblProcesosQuery();
			query.setNombreClase("ReenviarMensajesPendientesJob");
			
			proceso = tblProcesosManager.getProcesosByQuery(query);
				
			if(proceso.get(0).getEstado().equals("En ejecucion...")){
				return;
			}
			
			proceso.get(0).setInicioUltimaEjecucion(new Date());
			proceso.get(0).setEstado("En ejecucion...");
			tblProcesosManager.update(proceso.get(0), source, accion, accionId);
			
			
			
		} catch (SchedulerException e) {
			logger.error("Error ScheduleContext en ejecucion Job Informes Servicios", e);
		
		}
		try{
			ejecutar();
		}catch (Exception ex){
			LOG.error("[ReenviarMensajesPendientesJob] Error ReenviarMensajesPendientes" , ex);
			
		}finally{
			proceso.get(0).setFinUltimaEjecucion(new Date());
			proceso.get(0).setEstado("Finalizado");
			tblProcesosManager.update(proceso.get(0), source, accion, accionId);
		}
	}
	
	/**
	 * Lanzar job.
	 *
	 * @param servletContext the servlet context
	 * @param bean the bean
	 * @throws JobExecutionException 
	 */
	public void lanzarJob(ServletContext servletContext, JobBean bean, String servicios) throws JobExecutionException {
		List<TblProcesos> proceso = new ArrayList();
		String accion = "";
		Long accionId = null;
		String source = "";
		
		try {
			ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			this.inicializarVariables(applicationContext);
			jobBean = bean;			
			datosServicios = servicios;
			
			accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
			accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
			source = properties.getProperty("log.SOURCE_REENVIOJOBS", null);
			
			TblProcesosQuery query = new TblProcesosQuery();
			query.setNombreClase("ReenviarMensajesPendientesJob");
			
			proceso = tblProcesosManager.getProcesosByQuery(query);
				
			if(proceso.get(0).getEstado().equals("En ejecucion...")){
				return;
			}		
			proceso.get(0).setInicioUltimaEjecucion(new Date());
			proceso.get(0).setEstado("En ejecucion...");
			tblProcesosManager.update(proceso.get(0), source, accion, accionId);
						
		} catch (SchedulerException e) {
			logger.error("Error ejecucion Job manual Historificacion: ", e);		
		}
		
		try{
			ejecutar();
		}catch (Exception ex){
			LOG.error("[ReenviarMensajesPendientesJob] Error ReenviarMensajesPendientes" , ex);
			
		}finally{
			proceso.get(0).setFinUltimaEjecucion(new Date());
			proceso.get(0).setEstado("Finalizado");
			tblProcesosManager.update(proceso.get(0), source, accion, accionId);
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
			erroresManager = (ErroresManager) applicationContext.getBean("ErroresManagerImpl");
			pooledConnectionFactory = (ConnectionFactory) applicationContext.getBean("amqConnectionFactory");
			sender = (SIMMessageSender) applicationContext.getBean("messageSender");
			tblDestinatariosMensajesManager = (TblDestinatariosMensajesManager) applicationContext.getBean("TblDestinatariosMensajesManagerImpl");
			tblMensajesManager = (TblMensajesManager) applicationContext.getBean("TblMensajesManagerImpl");
			tblProcesosManager = (TblProcesosManager) applicationContext.getBean("TblProcesosManagerImpl");
			serviciosManager = (TblServiciosManagerImpl) applicationContext.getBean("TblServiciosManagerImpl");
			servicioProcesoHistoricos = (ServicioProcesoHistoricos) applicationContext.getBean("servicioProcesoHistoricosImpl");
			tblParametrosServidorManager = (TblParametrosServidorManagerImpl) applicationContext.getBean("tblParametrosServidorManagerImpl");
			properties = (PlataformaMensajeriaProperties) applicationContext.getBean("plataformaMensajeriaProperties");
		} catch (Exception objException) {
			logger.error("RecuperarInforDIRJob - InicializarVariables - Error: " + objException);
			throw new JobExecutionException("Un error en el planificador");
		}
	}
	/**
	 * Metodo que ejecute el Job
	 * @throws JobExecutionException
	 */
	@Transactional(noRollbackFor = Throwable.class)
	public void ejecutar() throws JobExecutionException {
		StringBuilder descripcionEstado = new StringBuilder();
		
		ProcesoHistBean procesoHistBean = new ProcesoHistBean();
		procesoHistBean.setFechaInicio(new Date());		
		
		TblProcesosQuery query = new TblProcesosQuery();
		query.setNombreClase("ReenviarMensajesPendientesJob");
		
		List<TblProcesos> proceso = tblProcesosManager.getProcesosByQuery(query);

		String errorActiveMq = properties.getProperty("conexion.ERRORACTIVEMQ","[ERROR-ACTIVEMQ]");
		String serviciosExcluidos = "";
		if(proceso.get(0).getParametro2() != null) serviciosExcluidos = proceso.get(0).getParametro2();
		Date fechaInicio = null;
		Date fechaFin = null;

		if (null != jobBean && null != jobBean.getFecha()) {			
			fechaInicio = jobBean.getFecha();			
		}else{
			int numeroDiasAtras;
			if(null != jobBean && jobBean.getParametro1() != null){
				numeroDiasAtras = Integer.valueOf(jobBean.getParametro1());
			}else if(tblProcesosManager.getProcesosByQuery(query).get(0).getParametro1() != null){
				numeroDiasAtras = Integer.valueOf(tblProcesosManager.getProcesosByQuery(query).get(0).getParametro1());
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
		
			String prefijoNormal = properties.getProperty("activemq.queueNamePrefix", null);
			String prefijoPremium = properties.getProperty("activemq.premiumQueueName", null);
			List<String> mensajesConError = new ArrayList<>();
			HashMap<Long, String> mensajesEnviados = new HashMap<Long, String>();
			List<String> destinatariosConError = new ArrayList<>();
			
			LOG.info("---Inicio JOB ReenviarMensajes---");
			checkDependenciesPresent();
			
			// Obtenemos mensaje pendientes de envío.Hay que mirar el estado en
			// destinatarios-mensaje
			Map<Long, List<MensajeJMS>> mapMensajes = tblMensajesManager.getMensajesReenviar(serviciosExcluidos,fechaInicio,fechaFin,datosServicios);
 			for (Map.Entry<Long, List<MensajeJMS>> entry : mapMensajes.entrySet()) {
				Long servicioId = entry.getKey();
				List<MensajeJMS> listaMensajesPendientes = entry.getValue();
				infoMensajes(servicioId, listaMensajesPendientes);
				
				TblServicios servicio = serviciosManager.getServicio(servicioId);
				List<MensajeJMS> listaMensajesEncolados = new ArrayList<>();
				try {
					if (null != servicio && null != servicio.getPremium() && servicio.getPremium()) {
						listaMensajesEncolados = getListaMensajesEncolados(servicioId, prefijoPremium);
					} else {
						listaMensajesEncolados = getListaMensajesEncolados(servicioId, prefijoNormal);
					}
				} catch (Exception e) {
					for (MensajeJMS mensajeJMS : listaMensajesPendientes) {
						completarMensajesConError(mensajesConError, destinatariosConError, mensajeJMS);
					}
				}
				for (MensajeJMS mensajeJMSPendiente : listaMensajesPendientes) {
					
					int activeMQ = 2;
					
					try {
						boolean encolado = false;
						for (MensajeJMS mensajeJMSEncolado : listaMensajesEncolados) {
							if(mensajeJMSPendiente.getIdMensaje().equals(mensajeJMSEncolado.getIdMensaje()) 
									&& (mensajeJMSPendiente.getDestinatarioMensajeId()!=null &&
											mensajeJMSPendiente.getDestinatarioMensajeId().equals(mensajeJMSEncolado.getDestinatarioMensajeId())
											|| (mensajeJMSPendiente.getDestinatarioMensajeId() == null 
												&& mensajeJMSEncolado.getDestinatarioMensajeId() == null))) {
								encolado=true;
								break;
							}
						}
						if (!encolado) {
							LOG.info("Mensaje " + mensajeJMSPendiente.getIdMensaje() + " - " + mensajeJMSPendiente.getDestinatarioMensajeId() + " no ha sido encolado previamente.");
							encolarMensaje(servicioId, servicio, mensajeJMSPendiente);
							if(mensajesEnviados.containsKey(servicioId)){
								if(!mensajesEnviados.get(servicioId).contains(mensajeJMSPendiente.getIdMensaje())){
									mensajesEnviados.put(servicioId,mensajesEnviados.get(servicioId) + ","+mensajeJMSPendiente.getIdMensaje());
								}
								
							}else{
								mensajesEnviados.put(servicioId,mensajeJMSPendiente.getIdMensaje());
							}							
							//Comprobamos que si ya se ha actualizado la tabla de errores a true
							activeMQ = 1;//true
						} else {
							LOG.info("Mensaje " + mensajeJMSPendiente.getIdMensaje() + " - " + mensajeJMSPendiente.getDestinatarioMensajeId() + " ya ha sido encolado previamente.");
						} 				
					}catch (CannotCreateTransactionException e) {
						//Comprobamos que si ya se ha actualizado la tabla de errores a false
						activeMQ = 0;//false
					} catch (Exception e) {
						LOG.error("[ReenviarMensajesPendientesJob] Error al encolarMensaje: " , e);
						completarMensajesConError(mensajesConError, destinatariosConError, mensajeJMSPendiente);
					}finally{
//						Comprobamos que si ya se ha actualizado la tabla de errores
						LOG.debug("Estamos en ReenviarMensajesPendientesJob-execute");					
						if (activeMQ == 0){
							if (erroresManager.comprobarActiveMqActivo(false)) {
								LOG.error(errorActiveMq+" HiloEnviarMensajesPremium.run --Error ActiveMq-- Mensaje: " + mensajeJMSPendiente.getIdMensaje());
							}
						}else if (activeMQ == 1){
							erroresManager.comprobarActiveMqActivo(true);
						}
					}
				}
	
			}// del for del map
 			
 			
			
			if (null != mensajesConError && mensajesConError.size() > 0) {								
				procesoHistBean.setCodigoEstado(ESTADO_PROCESO_KO);
				procesoHistBean.setDescripcionEstado("Se han producido fallos en el reenvio job. Para mas informacion, consulte logs.");
				
			}else{
				procesoHistBean.setCodigoEstado(ESTADO_PROCESO_OK);
				procesoHistBean.setDescripcionEstado("Reenvio job se ha realizado correctamente.");
			}
			procesoHistBean.setFechaFin(new Date());
			servicioProcesoHistoricos.newServicioProcesoHistoricos(procesoHistBean);
			
			if("S".equals(properties.getProperty("job.mail.avisar", null))) {
				LOG.info("--- Envio email Job ReenviarMensajesPendientes ---");
				SendMailService sendMailService = new SendMailService();
				try {
					if(mensajesEnviados.size() == 0){
						descripcionEstado.append("No se ha reenviado ningun mensaje");
					}
					if(mensajesEnviados.size() > 0){
						descripcionEstado.append("Se han enviado los siguientes mensajes: ");
						for (Map.Entry<Long, String> entry : mensajesEnviados.entrySet()) {
							descripcionEstado.append("<br>");
							descripcionEstado.append("-Servicio: ["+entry.getKey()+"] ID Mensajes: ["+entry.getValue()+"]");
						}
					}
					if(!mensajesConError.isEmpty()){
						descripcionEstado.append("No se han enviado los siguientes mensajes: ");
						for(String mensaje:mensajesConError){
							descripcionEstado.append(mensaje);
						}
					}					
					sendMailService.initJob(NOMBRE_JOB, procesoHistBean.getCodigoEstado(), descripcionEstado.toString(), properties, tblParametrosServidorManager);
				} catch (ServletException e) {
					logger.error("HistorificacionJob.Execute " , e);
				}
			}
			
			
			LOG.info("--- Fin Job ReenvioMensajesPendientes ---");
		
	}

	/**
	 * metodo para encolar los mensajes
	 * @param ps
	 * @param servicioId
	 * @param servicio
	 * @param mensajeJMS
	 */
	private void encolarMensaje(Long servicioId, TblServicios servicio, MensajeJMS mensajeJMS) {
		Long maxRetries = null;
		if (servicio.getNumeroMaxReenvios() != null && servicio.getNumeroMaxReenvios() >= 0) {
			maxRetries = servicio.getNumeroMaxReenvios().longValue();
		} else {
			maxRetries = Long.parseLong(properties.getProperty("constantes.servicio.numMaxReenvios", null));
		}
		boolean premium = false;
		if (servicio.getPremium() != null && servicio.getPremium()) {
			premium = true;
		}
//		throw new CannotCreateTransactionException("Error manual");
		LOG.info("Se encola Mensaje:  ---->" + mensajeJMS.getIdMensaje() + " -- DestinatarioMensajeId: " + mensajeJMS.getDestinatarioMensajeId());
		sender.send(mensajeJMS, maxRetries, servicioId.toString(), premium);
		
	}

	/**
	 * Metodo que muestra en los log el id de servicio y la cantidad de mensajes a procesar
	 * @param servicioId
	 * @param listaMensajes
	 */
	private void infoMensajes(Long servicioId, List<MensajeJMS> listaMensajes) {
		LOG.info("------------------");
		LOG.info("SERVICIO ---->" + servicioId);
		LOG.info("MENSAJES ---->" + listaMensajes.size());
		LOG.info("------------------");
	}
	

	/**
	 * Encola los mensajes con error
	 * @param mensajesConError
	 * @param destinatariosConError
	 * @param mensajeJMS
	 * @param ps
	 */
	private void completarMensajesConError(List<String> mensajesConError,
			List<String> destinatariosConError, MensajeJMS mensajeJMS) {
		Integer maxIntentosReenvio = Integer.parseInt(properties.getProperty("constantes.servicio.maxIntentosReenvio", null));
		
		List<String> listaDestinatarios = new ArrayList<String>(Arrays.asList(mensajeJMS.getDestinatarioMensajeId()
				.split(";")));
		for (String ds : listaDestinatarios) {
			Integer intentos = tblDestinatariosMensajesManager.updateNumIntentosEncolar(Long.parseLong(ds));
			if (intentos >= maxIntentosReenvio) {
				if (!mensajesConError.contains(mensajeJMS.getIdMensaje()))
					mensajesConError.add(mensajeJMS.getIdMensaje());
				if (!destinatariosConError.contains(ds))
					destinatariosConError.add(ds);
			}
			
		}
	}

	/**
	 * Obtiene la lista de mensajes encolados
	 * @param servicioId
	 * @param prefijo
	 * @return
	 * @throws Exception
	 */
	private List<MensajeJMS> getListaMensajesEncolados(Long servicioId, String prefijo) throws Exception {
		QueueBrowser browser = null;
		Session session = null;
		Connection connection = null;
		List<MensajeJMS> listaMensajesEncolados = new ArrayList<>();
		try {
			connection = pooledConnectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue(prefijo + servicioId);
			connection.start();
			browser = session.createBrowser(queue);
			Enumeration<?> e = browser.getEnumeration();

			while (e.hasMoreElements()) {
				Message tempMsg = (Message) e.nextElement();
				ActiveMQBytesMessage bytesMessage = (ActiveMQBytesMessage) tempMsg;
				String messageText = new String(bytesMessage.getContent().data);

				MensajeJMS data = new Gson().fromJson(messageText, MensajeJMS.class);
				listaMensajesEncolados.add(data);
			}
		} catch (Exception e) {
			LOG.error("ReenviarMensajesPendientesJob.getListaMensajesEncolados", e);
			throw new Exception(e);
		} finally {
			if (null != browser)
				browser.close();
			if (null != session)
				session.close();
			if (null != connection)
				connection.close();
		}
		return listaMensajesEncolados;
	}

	/**
	 * METODO QUE RECUPERA LOS DESTINATARIOS POR EL TIPO (CC,TO,BCC)
	 * @param address
	 * @return
	 * @throws AddressException
	 */
	private ArrayList<String> recuperarInternetAddress(String address) throws AddressException {

		ArrayList<String> recipients = new ArrayList<String>();
		int contador = 0;

		if (null != address && !address.isEmpty()) {
			// Si existe solo una direccion de correo
			if (address.indexOf(";") == -1) {
				recipients.add(address);
			} else {
				// Recuperamos todos las direcciones de correo
				while (address.indexOf(";", contador) > -1) {
					recipients.add(address.substring(contador, address.indexOf(";", contador)));
					contador = address.indexOf(";", contador) + 1;
				}
				recipients.add(address.substring(contador, address.length()));
			}
		}

		return recipients;
	}

	/**
	 * METODO QUE ANADE LOS DESTINATARIOS POR EL TIPO (CC,TO,BCC)
	 * @param recipients
	 * @return
	 * @throws AddressException
	 */
	private InternetAddress[] generateInternetAddress(ArrayList<String> recipients) throws AddressException {

		InternetAddress[] address = new InternetAddress[recipients.size()];

		for (int i = 0; i < recipients.size(); i++) {
			address[i] = new InternetAddress(recipients.get(i));
		}
		return address;
	}

	/**
	 * Comprueba que el manager serviciosManager no es nulo
	 */
	private void checkDependenciesPresent() {
		if (this.serviciosManager == null) {
			throw new IllegalStateException("ServiciosManager is required for this job to execute");
		}

	}

	/**
	 * @return the serviciosManager
	 */
	public TblServiciosManager getServiciosManager() {
		return serviciosManager;
	}

	/**
	 * @param serviciosManager
	 *            the serviciosManager to set
	 */
	public void setServiciosManager(TblServiciosManager serviciosManager) {
		this.serviciosManager = serviciosManager;
	}
	
}
