package es.map.sim.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.map.sim.jms.receiver.SIMMessageReceiver;
import es.map.sim.jms.sender.SIMMessageSender;
import es.map.sim.negocio.modelo.MensajeJMS;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:jms-context.xml"})
public class MockListeningTest {
	
	@Autowired
	@Qualifier("enviarMensajeMessageReceiver")
	private SIMMessageReceiver receiver;
	
	@Autowired
	@Qualifier("messageSender")
	private SIMMessageSender sender;
	//@Test
	public void testEjb() throws InterruptedException {
		System.out.println("Starting ejb test");
		MensajeJMS mensaje = new MensajeJMS();
//		mensaje.setIdExterno("TestExtEjb1");
//		mensaje.setCodSia("TestEjb1");
		sender.send(mensaje, 2l, "test", true);
		Thread.sleep(2000);
	}
	//@Test
	public void test() throws InterruptedException {
		System.out.println("Starting synchronous test");
		MensajeJMS mensaje = new MensajeJMS();
//		mensaje.setIdExterno("TestExt1");
//		mensaje.setCodSia("Test1");
		sender.send(mensaje, 2l, "test", false);
		boolean received=true;
		Thread.sleep(2000l);
		while(received){
			try{
				received=receiver.receiveByServiceName("test");
			}catch(RuntimeException e){
				//e.printStackTrace();
			}
		}
	}
	
	//@Test
	public void testPremiumListener() throws InterruptedException{
		System.out.println("Starting Listener Test");
		MensajeJMS mensaje = new MensajeJMS();
//		mensaje.setIdExterno("TestExt2");
//		mensaje.setCodSia("Test2");
		sender.send(mensaje, 3l, "test", true);
		Thread.sleep(20000l);
	}
	@Test
	public void testRefreshListener() throws InterruptedException{
		System.out.println("Starting Refresh Listener Test");
		MensajeJMS mensaje = new MensajeJMS();
//		mensaje.setIdExterno("TestExt2");
//		mensaje.setCodSia("Test2");
		sender.sendRefresh(mensaje, 6l);
		Thread.sleep(20000l);
	}

}
