package es.mpr.plataformamensajeria.quartz;

import java.text.ParseException;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.map.j2ee.exceptions.BusinessException;

import org.quartz.CronExpression;
import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import es.minhap.plataformamensajeria.iop.beans.ProcesosBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProcesos;



public class Planificador {

	protected static final String PLANIFICAR_PROC = "planificar proceso ";

	protected static final String RUN_PROCESOS_REF = "run - Procesos planificados";

	protected static final String RUN___TAM_SIZE = "run - Tam size:";

	protected static final String RUN___PLANIFICA = "run - Planificando proceso:";

	private static Logger logger = Logger.getLogger(Planificador.class);
	
	private static final String TRIGGER = "_TRIGGER";
		
//	@Resource(name = "servicioProcesosImpl")
	private static ServicioProcesos servicioProcesos;
	
	/**  application context. */
	private static ApplicationContext applicationContext;
	
	
	public Planificador(ApplicationContext applicationContext){
		this.applicationContext = applicationContext;		
		servicioProcesos = (ServicioProcesos) applicationContext.getBean("servicioProcesosImpl");
	
	}
	
	public void run() throws BusinessException{
		logger.info("run - start");
		
    	
    		logger.info(RUN_PROCESOS_REF);
			List<ProcesosBean> listaProcesos = servicioProcesos.getAllProcesos();
			logger.info(RUN___TAM_SIZE + listaProcesos.size());
			
			for (ProcesosBean proceso : listaProcesos){
				if (proceso==null || (proceso.getActivo() != null && !proceso.getActivo())){
					continue;
				}
				logger.info(RUN___PLANIFICA + proceso.getNombre());
				this.planificarProcesos(proceso);
			}
    	
    	logger.info("run - end");
	}
	
	public boolean planificarUnProceso(String nombreProceso) throws BusinessException{
		
		boolean planificado = false;
		logger.info(RUN_PROCESOS_REF);
		List<ProcesosBean> listaProcesos = servicioProcesos.getAllProcesos();
		logger.info(RUN___TAM_SIZE + listaProcesos.size());
		
		for (ProcesosBean proceso : listaProcesos){
			if (proceso!=null && proceso.getNombreClase().equals(nombreProceso) ){
				logger.info(RUN___PLANIFICA + proceso.getNombre());
				this.planificarProcesos(proceso);			
				planificado = true;
			}			
		}
		return planificado; 
	}
	/**
	 * Planificar jobs.
	 * @throws BusinessException 
	 */
	////MIGRADO
		public void planificarProcesos(ProcesosBean proceso) throws BusinessException{
			try{
				SchedulerFactory sf = new StdSchedulerFactory();
				Scheduler scheduler = sf.getScheduler();
				scheduler.getContext().put("applicationContext",applicationContext);
							
				
				if(proceso.getActivo()){
					// Obtenemos la referencia del planificador
									
					try{
						TriggerKey tk = new TriggerKey(proceso.getNombreClase()+TRIGGER);
						Trigger triggerProceso = scheduler.getTrigger(tk);
						
						// Creación del job
						logger.debug("planificar Jobs - Definimos la tarea");
				        // Definimos la tarea (nombre de la tarea, nombre del grupo de tareas, Clase que implementa la tarea)
						JobDetailImpl job = new JobDetailImpl();
						job.setName(proceso.getNombre());
						job.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
						@SuppressWarnings("unchecked")
						Class<? extends Job> clase = (Class <? extends Job>)Class.forName("es.mpr.plataformamensajeria.quartz.jobs."+proceso.getNombreClase());
						job.setJobClass(clase);
						
						logger.debug(PLANIFICAR_PROC+proceso.getNombre()+"- La cronExpression es: " + proceso.getProximaEjecucion());
						CronExpression cronExp = new CronExpression(proceso.getProximaEjecucion());
						// Creación del trigger
				        logger.debug(PLANIFICAR_PROC+proceso.getNombre()+" - Configuramos el trigger que avisara al planificador");
				        // Configuramos el Trigger que avisará al planificador de cuando debe ejecutar la tarea 
						CronTriggerImpl trigger = new CronTriggerImpl();
						
						
						trigger.setCronExpression(cronExp);
						trigger.setDescription(proceso.getNombreClase()+TRIGGER);
						trigger.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
						trigger.setName(proceso.getNombreClase()+TRIGGER);
						// Creación del planificador con el job y trigger creados anteriormente y arranque
						// La tarea definida en JobDetail será ejecutada en los instantes especificados por el Trigger.
						logger.debug("planificar +"+proceso.getNombre()+" - Iniciamos la tarea");
						
						if (triggerProceso == null){
							scheduler.scheduleJob(job, trigger);
						}else{
							scheduler.rescheduleJob(tk, trigger);						
						}
						
						
					}catch (SchedulerException | ParseException | ClassNotFoundException se){
						logger.error("[QuartzInicializarJobs - planificarJobCons] ",se);
						logger.debug("planificarJobDIR3 - Error: " + se.getMessage());
					}
					
					
					scheduler.start();
				}else{
					scheduler.deleteJob(new JobKey(proceso.getNombre()+TRIGGER));
					logger.debug("proceso +"+proceso.getNombre()+" - Desactivado");
				}
					
			        
				
			}catch (SchedulerException se){
				logger.error("[QuartzInicializarJobs - planificarJobs] ",se);
				logger.debug("planificar - Error: " + se.getMessage());
			}
		}	
	
}
