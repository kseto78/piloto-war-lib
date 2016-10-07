package es.mpr.plataformamensajeria.quartz;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class QuartzInicializarJobs extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(QuartzInicializarJobs.class);
	private static Properties configProp = new Properties();
	private ApplicationContext applicationContext;
	
	private static final String HIST_JOB_NAME = "HISTORIFICACION";
	private static final String HIST_TRIGGER_NAME = "HISTORIFICACION_TRIGGER";
	private static final String CONS_JOB_NAME = "CONSERVACION";
	private static final String CONS_TRIGGER_NAME = "CONSERVACION_TRIGGER";
	private static final String INFORMES_SERVICIOS_JOB_NAME = "INFORMES_SERVICIOS";
	private static final String INFORMES_SERVICIOS_TRIGGER_NAME = "INFORMES_SERVICIOS_TRIGGER";
	
	
	public void init() throws ServletException{
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		InputStream in = this.getClass().getResourceAsStream("/es/mpr/plataformamensajeria/util/conf.properties");
		try {
           configProp.load(in);
           this.planificarJobs();
		} catch (IOException e) {
           e.printStackTrace();
		}
	}
	
	public QuartzInicializarJobs() {
		super();
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
	public void planificarJobs(){
		try{
			String activarJobHist = configProp.getProperty("jobHist.activacion");
			String activarJobCons = configProp.getProperty("jobCons.activacion");
			String activarJobInformesServicios = configProp.getProperty("jobInformesServicios.activacion");
			
			if((activarJobHist!=null && activarJobHist.equals("S")) ||
					(activarJobCons!=null && activarJobCons.equals("S")) ||
					(activarJobInformesServicios!=null && activarJobInformesServicios.equals("S"))){
				
		        // Obtenemos la referencia del planificador
				SchedulerFactory sf = new StdSchedulerFactory();
				Scheduler scheduler = sf.getScheduler();
				scheduler.getContext().put("applicationContext",applicationContext);
				
				if(activarJobHist!=null && activarJobHist.equals("S")){
					this.planificarJobHist(scheduler);
				}
				if(activarJobCons!=null && activarJobCons.equals("S")){
					this.planificarJobCons(scheduler);
				}
				if(activarJobInformesServicios!=null && activarJobInformesServicios.equals("S")){
					this.planificarJobInformesServicios(scheduler);
				}
				scheduler.start();
			}
		}catch (SchedulerException se){
			logger.debug("planificar - Error: " + se.getMessage());
			se.printStackTrace();
		}
	}
	
	private void planificarJobHist(Scheduler scheduler){
		try{
			// Creación del job
			logger.debug("planificarJobHist - Definimos la tarea");
	        // Definimos la tarea (nombre de la tarea, nombre del grupo de tareas, Clase que implementa la tarea)
			JobDetailImpl job = new JobDetailImpl();
			
			job.setName(HIST_JOB_NAME);
			job.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
			Class<? extends Job> clase = (Class <? extends Job>)Class.forName("es.mpr.plataformamensajeria.quartz.jobs.HistorificacionJob");
			job.setJobClass(clase);
			String cronExpression = configProp.getProperty("cron.jobHist.expression");
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
			
		}catch (SchedulerException se){
			logger.debug("planificarJobHist - Error: " + se.getMessage());
			se.printStackTrace();
		}catch(ParseException pe){
			logger.debug("planificarJobHist - Error: " + pe.getMessage());
			pe.printStackTrace();
		}catch (ClassNotFoundException cnfe){
			logger.debug("planificarJobHist - Error: " + cnfe.getMessage());
			cnfe.printStackTrace();
		}
	}
	
	private void planificarJobCons(Scheduler scheduler){
		try{
			// Creación del job
			logger.debug("planificarJobCons - Definimos la tarea");
	        // Definimos la tarea (nombre de la tarea, nombre del grupo de tareas, Clase que implementa la tarea)
			JobDetailImpl job = new JobDetailImpl();
			job.setName(CONS_JOB_NAME);
			job.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
			Class<? extends Job> clase = (Class <? extends Job>)Class.forName("es.mpr.plataformamensajeria.quartz.jobs.EstadisticasConsolidadasJob");
			job.setJobClass(clase);
			String cronExpression = configProp.getProperty("cron.jobCons.expression");
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
			
		}catch (SchedulerException se){
			logger.debug("planificarJobCons - Error: " + se.getMessage());
			se.printStackTrace();
		}catch(ParseException pe){
			logger.debug("planificarJobCons - Error: " + pe.getMessage());
			pe.printStackTrace();
		}catch (ClassNotFoundException cnfe){
			logger.debug("planificarJobCons - Error: " + cnfe.getMessage());
			cnfe.printStackTrace();
		}
	}
	
	private void planificarJobInformesServicios(Scheduler scheduler){
		try{
			// Creación del job
			logger.debug("planificarJobInformesServicios - Definimos la tarea");
	        // Definimos la tarea (nombre de la tarea, nombre del grupo de tareas, Clase que implementa la tarea)
			JobDetailImpl job = new JobDetailImpl();
			job.setName(INFORMES_SERVICIOS_JOB_NAME);
			job.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
			Class<? extends Job> clase = (Class <? extends Job>)Class.forName("es.mpr.plataformamensajeria.quartz.jobs.InformesServiciosJob");
			job.setJobClass(clase);
			String cronExpression = configProp.getProperty("cron.jobInformesServicios.expression");
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
			
		}catch (SchedulerException se){
			logger.debug("planificarJobInformesServicios - Error: " + se.getMessage());
			se.printStackTrace();
		}catch(ParseException pe){
			logger.debug("planificarJobInformesServicios - Error: " + pe.getMessage());
			pe.printStackTrace();
		}catch (ClassNotFoundException cnfe){
			logger.debug("planificarJobInformesServicios - Error: " + cnfe.getMessage());
			cnfe.printStackTrace();
		}
	}
	
}
