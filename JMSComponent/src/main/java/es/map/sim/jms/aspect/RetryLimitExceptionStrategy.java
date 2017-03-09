package es.map.sim.jms.aspect;

import java.lang.reflect.Method;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQMessage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import es.map.sim.jms.strategy.ExceptionStrategy;



public class RetryLimitExceptionStrategy implements ExceptionStrategy{
	private String limitQueue;
	@Autowired
	private JmsOperations template;
	private String retriesProperty;
	private long fixedLimit=0;
	
	public void manageException(final Message message, Throwable t) throws Throwable {
		int numRetries = 0;
		if (message instanceof ActiveMQMessage) {
			numRetries = ((ActiveMQMessage) message).getRedeliveryCounter();
		} else {
			Method method = sun.reflect.misc.MethodUtil.getMethod(message.getClass(),"getRedeliveryCounter", new Class[0]);
			if(method!=null){
				numRetries=(Integer) sun.reflect.misc.MethodUtil.invoke(method, message, new Object[0]);
			}
		}
		long limit = fixedLimit;
		try {
			limit = message.getLongProperty(retriesProperty);
		} catch (NumberFormatException e) {
			// ignored
		} catch (JMSException e) {
			//ignored
		}
		if (numRetries < limit) {
			if(!TransactionAspectSupport.currentTransactionStatus().isNewTransaction()){
				//No EJB
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
			
			//EJB
			throw t;
		} else {
			template.send(limitQueue, new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					return message;
				}
			});
		}
	}

	/**
	 * @return the limitQueue
	 */
	public String getLimitQueue() {
		return limitQueue;
	}

	/**
	 * @param limitQueue the limitQueue to set
	 */
	public void setLimitQueue(String limitQueue) {
		this.limitQueue = limitQueue;
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
	 * @return the retriesProperty
	 */
	public String getRetriesProperty() {
		return retriesProperty;
	}

	/**
	 * @param retriesProperty the retriesProperty to set
	 */
	public void setRetriesProperty(String retriesProperty) {
		this.retriesProperty = retriesProperty;
	}

	/**
	 * @return the fixedLimit
	 */
	public long getFixedLimit() {
		return fixedLimit;
	}

	/**
	 * @param fixedLimit the fixedLimit to set
	 */
	public void setFixedLimit(long fixedLimit) {
		this.fixedLimit = fixedLimit;
	}

}
