package es.redsara.misim.misim_bus_webapp.quartz;

import java.text.ParseException;

import org.mule.api.MuleContext;
import org.quartz.CronExpression;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import es.minhap.common.properties.PropertiesServices;

/**
 * Clase que contiene metodos para las planificaciones
 * @author ralberoc
 *
 */
public class Planificador {

	private static Logger logger = LoggerFactory.getLogger(Planificador.class);
	private PropertiesServices ps;
	private static final String ACTIVEMQ_JOB = "DESENCOLAR_MENSAJES_ACTIVEMQ";
	private static final String ACTIVEMQ_TRIGGER_NAME = "DESENCOLAR_MENSAJES_ACTIVEMQ_TRIGGER";
	private MuleContext muleContext;
	
	
	/**
	 * Modifica el objeto MuleContext
	 * @param muleContext
	 */
	public Planificador(MuleContext muleContext){
		this.muleContext = muleContext;
		ps = new PropertiesServices((ReloadableResourceBundleMessageSource) muleContext.getRegistry().lookupObject("reloadableResourceBundleMessageSource"));
	}
	
	/**
	 * Ejecuta el metodo planificarJobs
	 */
	public void run(){
		logger.info("run - start");
		this.planificarJobs();
    	logger.info("run - end");
	}
	
	/**
	 * Proceso para planificar el job
	 */
	public void planificarJobs(){
		try{
			String activarJobGISS = ps.getMessage("jobDesencolarMensajesActiveMQ.activacion", null, null, null);
			if((activarJobGISS!=null && activarJobGISS.equals("S"))){
				
				// Creacion de una instacia de Scheduler
				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
				scheduler.getContext().put("muleContext", muleContext);

				if(activarJobGISS!=null && activarJobGISS.equals("S")){
					this.planificarJobGISS(scheduler);
				}
				scheduler.start();
			}
		}catch (SchedulerException se){
			logger.error("planificar - Error: " + se.getMessage(),se);
		}
	}
	
	/**
	 * Metodo para planificar el job de GISS
	 * @param scheduler
	 */
	@SuppressWarnings("unchecked")
	private void planificarJobGISS(Scheduler scheduler){
		try{
			logger.info("planificarDesencolamiento de mensajes de Active MQ - Definimos la tarea");
			
	        // Definimos la tarea (nombre de la tarea, nombre del grupo de tareas, Clase que implementa la tarea)
			JobDetailImpl job = new JobDetailImpl();
			
			job.setName(ACTIVEMQ_JOB);
			job.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
			Class<? extends Job> clase = (Class <? extends Job>)Class.forName("es.redsara.misim.misim_bus_webapp.quartz.jobs.EnviarMensajeJob");
			
			job.setJobClass(clase);
			String cronExpression = ps.getMessage("cron.jobDesencolarMensajesActiveMQ.expression", null, null, null);
			logger.info("planificar desencolamientos de la Active MQ de los mensajes - La cronExpression es: " + cronExpression);
			CronExpression cronExp = new CronExpression(cronExpression);
			
			 // Creación del trigger
	        logger.info("planificar desencolamientos de la Active MQ de los mensajes - Configuramos el trigger que avisara al planificador");
	        
	        // Configuramos el Trigger que avisará al planificador de cuando debe ejecutar la tarea 
			CronTriggerImpl trigger = new CronTriggerImpl();
			trigger.setCronExpression(cronExp);
			trigger.setDescription(ACTIVEMQ_TRIGGER_NAME);
			trigger.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
			trigger.setName(ACTIVEMQ_TRIGGER_NAME);
			
			// Creación del planificador con el job y trigger creados anteriormente y arranque
			// La tarea definida en JobDetail será ejecutada en los instantes especificados por el Trigger.
			
			logger.info("Desencolar de la Active MQ los mensajes - Iniciamos la tarea");
			scheduler.scheduleJob(job, trigger);
		}catch (SchedulerException se){
			logger.error("planificarJobGISS - Error: " + se.getMessage(),se);
		}catch(ParseException pe){
			logger.error("planificarJobGISS - Error: " + pe.getMessage(),pe);
		}catch (ClassNotFoundException cnfe){
			logger.error("planificarJobGISS - Error: " + cnfe.getMessage(),cnfe);
		}
	}
}
