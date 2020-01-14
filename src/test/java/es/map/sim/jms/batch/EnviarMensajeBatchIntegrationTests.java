package es.map.sim.jms.batch;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/batch-context.xml"})
public class EnviarMensajeBatchIntegrationTests {
	
	//@Autowired
	private Scheduler scheduler;

	

	@Test
	public void test() throws SchedulerException{
		//getScheduler().start();
		System.out.println("Test executed");
	}



	/**
	 * @return the scheduler
	 */
	public Scheduler getScheduler() {
		return scheduler;
	}



	/**
	 * @param scheduler the scheduler to set
	 */
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}





	

}
