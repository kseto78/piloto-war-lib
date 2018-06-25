package es.map.sim.jms.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.interceptor.Interceptors;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;
import org.springframework.ejb.support.AbstractStatelessSessionBean;

@Interceptors(SpringBeanAutowiringInterceptor.class)
public class SIMMessageListenerMDB implements MessageDrivenBean, MessageListener {
	private static final Logger LOG =Logger.getLogger(SIMMessageListenerMDB.class);
	private String listenerBean;
	@Autowired
	private ApplicationContext appContext;
	
	
	
	public void onMessage(Message message) {
		MessageListener listener=appContext.getBean(listenerBean,MessageListener.class);
		try{
			listener.onMessage(message);
		}catch(Throwable t){
			LOG.error("Error on MessageDrivenBean",t);
			throw new RuntimeException(t.getMessage());
		}
	}

	public void ejbRemove() throws EJBException {
		// TODO Auto-generated method stub
		
	}

	public void setMessageDrivenContext(MessageDrivenContext arg0)
			throws EJBException {
		
	}

	@PostConstruct
	protected void onEjbCreate() throws CreateException {
		
	}

}
