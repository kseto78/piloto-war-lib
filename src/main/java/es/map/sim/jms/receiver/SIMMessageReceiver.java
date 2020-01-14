package es.map.sim.jms.receiver;



import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;

import es.map.sim.jms.QueueResolver;

@Component("messageReceiver")
public class SIMMessageReceiver {
	
	private static final Logger LOG = Logger.getLogger(SIMMessageReceiver.class);
	
	@Autowired
	private QueueResolver qResolver; 
	
	private MessageListener listener;
	
	@Autowired
	private JmsOperations template;
	
	private String defaultQueueName;
	
	public boolean receiveByServiceName(String servicio){
		return receiveByQueue(qResolver.resolveQueueName(servicio, false));
	}
	public boolean receive(){
		if(defaultQueueName==null){
			throw new IllegalArgumentException("defaultQueueName property must be set to use default receive method");
		}
		return receiveByQueue(defaultQueueName);
	}
	private boolean receiveByQueue(String queueName){
		Message msg = null;
		try{
			msg = template.receive(queueName);
		}catch(Throwable t){
			LOG.error("Error receiving message on queue "+queueName,t);
		}
		if(msg!=null){
			listener.onMessage(msg);
			return true;
		}
		return false;
	}
	/**
	 * @return the qResolver
	 */
	public QueueResolver getqResolver() {
		return qResolver;
	}
	/**
	 * @param qResolver the qResolver to set
	 */
	public void setqResolver(QueueResolver qResolver) {
		this.qResolver = qResolver;
	}
	/**
	 * @return the listener
	 */
	public MessageListener getListener() {
		return listener;
	}
	/**
	 * @param listener the listener to set
	 */
	public void setListener(MessageListener listener) {
		this.listener = listener;
	}
	/**
	 * @return the template
	 */
	public JmsOperations getTemplate() {
		return template;
	}
	/**
	 * @param template the template to set
	 */
	public void setTemplate(JmsOperations template) {
		this.template = template;
	}
	/**
	 * @return the defaultQueueName
	 */
	public String getDefaultQueueName() {
		return defaultQueueName;
	}
	/**
	 * @param defaultQueueName the defaultQueueName to set
	 */
	public void setDefaultQueueName(String defaultQueueName) {
		this.defaultQueueName = defaultQueueName;
	}
}
