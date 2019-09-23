package es.mpr.plataformamensajeria.quartz;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.plataformamensajeria.iop.beans.ProcesosBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesos;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;


/**
 * Clase QuartzInicializarJobs.
 */
public class QuartzInicializarJobs extends HttpServlet  {

		
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**  logger. */
	private static Logger logger = Logger.getLogger(QuartzInicializarJobs.class);
	
	/**  application context. */
	private static ApplicationContext applicationContext;
	
	/**  config prop. */
	private static PlataformaMensajeriaProperties configProp;
	
	/** Constante HIST_JOB_NAME. */
	private static final String HIST_JOB_NAME = "HISTORIFICACION";
	
	/** Constante HIST_TRIGGER_NAME. */
	private static final String HIST_TRIGGER_NAME = "HISTORIFICACION_TRIGGER";
	
	/** Constante CONS_JOB_NAME. */
	private static final String CONS_JOB_NAME = "CONSERVACION";
	
	/** Constante CONS_TRIGGER_NAME. */
	private static final String CONS_TRIGGER_NAME = "CONSERVACION_TRIGGER";
	
	/** Constante INFORMES_SERVICIOS_JOB_NAME. */
	private static final String INFORMES_SERVICIOS_JOB_NAME = "INFORMES_SERVICIOS";
	
	/** Constante INFORMES_SERVICIOS_TRIGGER_NAME. */
	private static final String INFORMES_SERVICIOS_TRIGGER_NAME = "INFORMES_SERVICIOS_TRIGGER";

	/** Constante DIR3_JOB_NAME. */
	private static final String DIR3_JOB_NAME = "DIR3";
	
	/** Constante DIR3_TRIGGER_NAME. */
	private static final String DIR3_TRIGGER_NAME = "DIR3_TRIGGER";
	
	private static final String TRIGGER = "_TRIGGER";
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	////MIGRADO
	@Override
	public void init() throws ServletException{
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		configProp  = (PlataformaMensajeriaProperties) applicationContext.getBean("plataformaMensajeriaProperties");
		String nodoPrincipal = configProp.getProperty("jobNodoActivos.activacion", null);
		
		try {
           this.planificarJobs();
           if(nodoPrincipal != null && nodoPrincipal.equals("S")){
        	   Planificador p = new Planificador(applicationContext);
               p.run();
           }         
		} catch (Exception e) {
			logger.error("[QuartzInicializarJobs - init] ",e);
		}
	}
	
	/**
	 * Constructor de quartz inicializar jobs.
	 */
	public QuartzInicializarJobs() {
		super();
		
	}
	
	
	/**
	 * Planificar jobs.
	 */
	////MIGRADO
		public void planificarJobs(){
			try{
				String activarJobHist = configProp.getProperty("jobHist.activacion", null);
				String activarJobCons = configProp.getProperty("jobCons.activacion", null);
				String activarJobInformesServicios = configProp.getProperty("jobInformesServicios.activacion", null);
				String activarJobDIR3 = configProp.getProperty("jobDIR3.activacion", null);
				
				if((activarJobHist!=null && activarJobHist.equals("S")) ||
						(activarJobCons!=null && activarJobCons.equals("S")) ||
						(activarJobInformesServicios!=null && activarJobInformesServicios.equals("S")) ||
						(activarJobDIR3!=null && activarJobDIR3.equals("S"))){
					
			        // Obtenemos la referencia del planificador
					SchedulerFactory sf = new StdSchedulerFactory();
					Scheduler scheduler = sf.getScheduler();
					scheduler.getContext().put("applicationContext",applicationContext);
					
					if(activarJobHist!=null && "S".equals(activarJobHist)){
						this.planificarJobHist(scheduler);
					}
					if(activarJobCons!=null && "S".equals(activarJobCons)){
						this.planificarJobCons(scheduler);
					}
					if(activarJobInformesServicios!=null && "S".equals(activarJobInformesServicios)){
						this.planificarJobInformesServicios(scheduler);
					}
					
					if(activarJobDIR3!= null && "S".equals(activarJobDIR3)){
						this.planificarJobDIR3(scheduler);
					}
					scheduler.start();
				}
			}catch (SchedulerException se){
				logger.error("[QuartzInicializarJobs - planificarJobs] ",se);
				logger.debug("planificar - Error: " + se.getMessage());
			}
		}
	
/**
 * Planificar job hist.
 *
 * @param scheduler the scheduler
 */
////MIGRADO
	private void planificarJobHist(Scheduler scheduler){
		try{
			// Creación del job
			logger.debug("planificarJobHist - Definimos la tarea");
	        // Definimos la tarea (nombre de la tarea, nombre del grupo de tareas, Clase que implementa la tarea)
			JobDetailImpl job = new JobDetailImpl();
			
			job.setName(HIST_JOB_NAME);
			job.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
			@SuppressWarnings("unchecked")
			Class<? extends Job> clase = (Class <? extends Job>)Class.forName("es.mpr.plataformamensajeria.quartz.jobs.HistorificacionJob");
			job.setJobClass(clase);
			String cronExpression = configProp.getProperty("cron.jobHist.expression", null);
			logger.debug("planificarJobHist - La cronExpression es: " + cronExpression);
			CronExpression cronExp = new CronExpression(cronExpression);
			 // Creación del trigger
	        logger.debug("planificarJobHist - Configuramos el trigger que avisara al planificador");
	        // Configuramos el Trigger que avisará al planificador de cuando debe ejecutar la tarea 
			CronTriggerImpl trigger = new CronTriggerImpl();
			trigger.setCronExpression(cronExp);
			trigger.setDescription(HIST_TRIGGER_NAME);
			trigger.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
			trigger.setName(HIST_TRIGGER_NAME);
			// Creación del planificador con el job y trigger creados anteriormente y arranque
			// La tarea definida en JobDetail será ejecutada en los instantes especificados por el Trigger.
			logger.debug("planificarJobHist - Iniciamos la tarea");
			scheduler.scheduleJob(job, trigger);
			
		}catch (SchedulerException | ParseException | ClassNotFoundException se){
			logger.error("[QuartzInicializarJobs - planificarJobHist] ",se);
			logger.debug("planificarJobHist - Error: " + se.getMessage());
		}
	}
	
/**
 * Planificar job cons.
 *
 * @param scheduler the scheduler
 */
////MIGRADO
	private void planificarJobCons(Scheduler scheduler){
		try{
			// Creación del job
			logger.debug("planificarJobCons - Definimos la tarea");
	        // Definimos la tarea (nombre de la tarea, nombre del grupo de tareas, Clase que implementa la tarea)
			JobDetailImpl job = new JobDetailImpl();
			job.setName(CONS_JOB_NAME);
			job.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
			@SuppressWarnings("unchecked")
			Class<? extends Job> clase = (Class <? extends Job>)Class.forName("es.mpr.plataformamensajeria.quartz.jobs.EstadisticasConsolidadasJob");
			job.setJobClass(clase);
			String cronExpression = configProp.getProperty("cron.jobCons.expression", null);
			logger.debug("planificarJobCons - La cronExpression es: " + cronExpression);
			CronExpression cronExp = new CronExpression(cronExpression);
			// Creación del trigger
	        logger.debug("planificarJobCons - Configuramos el trigger que avisara al planificador");
	        // Configuramos el Trigger que avisará al planificador de cuando debe ejecutar la tarea 
			CronTriggerImpl trigger = new CronTriggerImpl();
			trigger.setCronExpression(cronExp);
			trigger.setDescription(CONS_TRIGGER_NAME);
			trigger.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
			trigger.setName(CONS_TRIGGER_NAME);
			// Creación del planificador con el job y trigger creados anteriormente y arranque
			// La tarea definida en JobDetail será ejecutada en los instantes especificados por el Trigger.
			logger.debug("planificarJobCons - Iniciamos la tarea");
			scheduler.scheduleJob(job, trigger);
			
		}catch (SchedulerException | ParseException | ClassNotFoundException se){
			logger.error("[QuartzInicializarJobs - planificarJobCons] ",se);
			logger.debug("planificarJobCons - Error: " + se.getMessage());
		}
	}
	
/**
 * Planificar job informes servicios.
 *
 * @param scheduler the scheduler
 */
////MIGRADO
	private void planificarJobInformesServicios(Scheduler scheduler){
		try{
			// Creación del job
			logger.debug("planificarJobInformesServicios - Definimos la tarea");
	        // Definimos la tarea (nombre de la tarea, nombre del grupo de tareas, Clase que implementa la tarea)
			JobDetailImpl job = new JobDetailImpl();
			job.setName(INFORMES_SERVICIOS_JOB_NAME);
			job.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
			@SuppressWarnings("unchecked")
			Class<? extends Job> clase = (Class <? extends Job>)Class.forName("es.mpr.plataformamensajeria.quartz.jobs.InformesServiciosJob");
			job.setJobClass(clase);
			String cronExpression = configProp.getProperty("cron.jobInformesServicios.expression", null);
			logger.debug("planificarJobInformesServicios - La cronExpression es: " + cronExpression);
			CronExpression cronExp = new CronExpression(cronExpression);
			// Creación del trigger
	        logger.debug("planificarJobInformesServicios - Configuramos el trigger que avisara al planificador");
	        // Configuramos el Trigger que avisará al planificador de cuando debe ejecutar la tarea 
			CronTriggerImpl trigger = new CronTriggerImpl();
			trigger.setCronExpression(cronExp);
			trigger.setDescription(INFORMES_SERVICIOS_TRIGGER_NAME);
			trigger.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
			trigger.setName(INFORMES_SERVICIOS_TRIGGER_NAME);
			// Creación del planificador con el job y trigger creados anteriormente y arranque
			// La tarea definida en JobDetail será ejecutada en los instantes especificados por el Trigger.
			logger.debug("planificarJobInformesServicios - Iniciamos la tarea");
			scheduler.scheduleJob(job, trigger);
			
		}catch (SchedulerException | ParseException | ClassNotFoundException se){
			logger.error("[QuartzInicializarJobs - planificarJobInformesServicios] ",se);
			logger.debug("planificarJobInformes - Error: " + se.getMessage());
		}
	}
		
	
	private void planificarJobDIR3(Scheduler scheduler){
		try{
			// Creación del job
			logger.debug("planificarJobDIR3 - Definimos la tarea");
	        // Definimos la tarea (nombre de la tarea, nombre del grupo de tareas, Clase que implementa la tarea)
			JobDetailImpl job = new JobDetailImpl();
			job.setName(DIR3_JOB_NAME);
			job.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
			@SuppressWarnings("unchecked")
			Class<? extends Job> clase = (Class <? extends Job>)Class.forName("es.mpr.plataformamensajeria.quartz.jobs.RecuperarInforDIRJob");
			job.setJobClass(clase);
			String cronExpression = configProp.getProperty("cron.jobDIR3.expression", null);
			logger.debug("planificarJobDIR3 - La cronExpression es: " + cronExpression);
			CronExpression cronExp = new CronExpression(cronExpression);
			// Creación del trigger
	        logger.debug("planificarJobDIR3 - Configuramos el trigger que avisara al planificador");
	        // Configuramos el Trigger que avisará al planificador de cuando debe ejecutar la tarea 
			CronTriggerImpl trigger = new CronTriggerImpl();
			trigger.setCronExpression(cronExp);
			trigger.setDescription(DIR3_TRIGGER_NAME);
			trigger.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
			trigger.setName(DIR3_TRIGGER_NAME);
			// Creación del planificador con el job y trigger creados anteriormente y arranque
			// La tarea definida en JobDetail será ejecutada en los instantes especificados por el Trigger.
			logger.debug("planificarJobDIR3 - Iniciamos la tarea");
			scheduler.scheduleJob(job, trigger);
			
		}catch (SchedulerException | ParseException | ClassNotFoundException se){
			logger.error("[QuartzInicializarJobs - planificarJobCons] ",se);
			logger.debug("planificarJobDIR3 - Error: " + se.getMessage());
		}
	}
}
