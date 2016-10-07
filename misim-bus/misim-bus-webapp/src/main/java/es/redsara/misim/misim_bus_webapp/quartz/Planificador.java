package es.redsara.misim.misim_bus_webapp.quartz;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.mule.api.MuleContext;
import org.quartz.CronExpression;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import es.minhap.common.properties.PropertiesServices;

public class Planificador {

	private static Logger logger = Logger.getLogger(Planificador.class);
	private PropertiesServices ps;
	private static final String GISS_JOB_NAME = "REINTENTOS_GISS";
	private static final String GISS_TRIGGER_NAME = "GISS_TRIGGER";
	private MuleContext muleContext;
	
	public Planificador(){		
	}
	
	
	public Planificador(MuleContext muleContext){
		this.muleContext = muleContext;
		ps = new PropertiesServices((ReloadableResourceBundleMessageSource) muleContext.getRegistry().lookupObject("reloadableResourceBundleMessageSource"));
	}
	

	public void run(){
		logger.info("run - start");
		this.planificarJobs();
    	logger.info("run - end");
	}
	
	public void planificarJobs(){
		try{
			String activarJobGISS = ps.getMessage("jobGISS.activacion", null, null, null);
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
			logger.debug("planificar - Error: " + se.getMessage());
			se.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void planificarJobGISS(Scheduler scheduler){
		try{
			logger.info("planificarJobGISS - Definimos la tarea");
			
	        // Definimos la tarea (nombre de la tarea, nombre del grupo de tareas, Clase que implementa la tarea)
			JobDetailImpl job = new JobDetailImpl();
			
			job.setName(GISS_JOB_NAME);
			job.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
			Class<? extends Job> clase = (Class <? extends Job>)Class.forName("es.redsara.misim.misim_bus_webapp.quartz.jobs.ReintentosGISSJob");
			
			job.setJobClass(clase);
			String cronExpression = ps.getMessage("cron.jobGISS.expression", null, null, null);
			logger.info("planificarJobGISS - La cronExpression es: " + cronExpression);
			CronExpression cronExp = new CronExpression(cronExpression);
			
			 // Creación del trigger
	        logger.info("planificarJobGISS - Configuramos el trigger que avisara al planificador");
	        
	        // Configuramos el Trigger que avisará al planificador de cuando debe ejecutar la tarea 
			CronTriggerImpl trigger = new CronTriggerImpl();
			trigger.setCronExpression(cronExp);
			trigger.setDescription(GISS_TRIGGER_NAME);
			trigger.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
			trigger.setName(GISS_TRIGGER_NAME);
			
			// Creación del planificador con el job y trigger creados anteriormente y arranque
			// La tarea definida en JobDetail será ejecutada en los instantes especificados por el Trigger.
			
			logger.info("planificarJobGISS - Iniciamos la tarea");
			scheduler.scheduleJob(job, trigger);
		}catch (SchedulerException se){
			logger.error("planificarJobGISS - Error: " + se.getMessage());
			se.printStackTrace();
		}catch(ParseException pe){
			logger.error("planificarJobGISS - Error: " + pe.getMessage());
			pe.printStackTrace();
		}catch (ClassNotFoundException cnfe){
			logger.error("planificarJobGISS - Error: " + cnfe.getMessage());
			cnfe.printStackTrace();
		}
	}
}
