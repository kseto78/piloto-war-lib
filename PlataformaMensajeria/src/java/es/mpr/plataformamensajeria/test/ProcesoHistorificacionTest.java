package es.mpr.plataformamensajeria.test;

import java.util.Iterator;

import javax.servlet.http.HttpServlet;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ProcesoHistorificacionTest extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ProcesoHistorificacionTest.class);
	
	private ApplicationContext applicationContext;
	
	public void test(){
		
		logger.info("INICIO Test Historificacion");
		
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		
		JobDetailImpl job = new JobDetailImpl();
		job.setName("HIST_JOB_NAME");
		job.setGroup(org.quartz.Scheduler.DEFAULT_GROUP);
		try {
			Class<? extends Job> clase = (Class <? extends Job>)Class.forName("es.mpr.plataformamensajeria.quartz.jobs.HistorificacionJob");
			job.setJobClass(clase);
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")
				    .forJob("HIST_JOB_NAME", org.quartz.Scheduler.DEFAULT_GROUP) // identify job with name, group strings
				    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")) // cada 1 minuto
				    .build();
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler scheduler = sf.getScheduler();
			scheduler.scheduleJob(job, trigger);
			scheduler.getContext().put("applicationContext", applicationContext);
			scheduler.start();
			
			// Recorro todos los grupos para obtener todos los jobkeys
			for (String groupName : scheduler.getJobGroupNames()) {
			    // Obtento el jobkey cuyo Name es igual al IdJob del proceso recibido como parametro
				boolean encontrado = false;
				Iterator<JobKey> iteratorJobs = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName)).iterator();
				while(iteratorJobs.hasNext() && !encontrado){
					JobKey jobkey = iteratorJobs.next();
					if(StringUtils.equals(jobkey.getName(), "HIST_JOB_NAME")){
						scheduler.triggerJob(jobkey);
			    	}
				}
			}
			
			Thread.sleep(50000000);
			
		} catch (SchedulerException e) {
			e.printStackTrace();
			logger.info("FIN Test Historificacion - exception");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.info("FIN Test Historificacion - exception");
		} catch (InterruptedException e) {
			e.printStackTrace();
			logger.info("FIN Test Historificacion - exception");
		}		
	}

}
