/**
 * 
 */
package es.map.sim.jms.listener.adapter;

import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import es.map.sim.jms.aspect.RetryLimitExceptionStrategy;
import es.map.sim.jms.strategy.ExceptionStrategy;

/**
 * @author vbazagad
 *
 */
public class ExceptionStrategyMessageListenerAdapter extends MessageListenerAdapter {
	
	private static final Logger LOG = Logger.getLogger(ExceptionStrategyMessageListenerAdapter.class);
	private ExceptionStrategy excStrategy;
	/* (non-Javadoc)
	 * @see org.springframework.jms.listener.adapter.MessageListenerAdapter#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message, Session session) {
		try{
			super.onMessage(message, session);
		}catch(Throwable t){
			try {
				excStrategy.manageException(message, t);
			} catch (Throwable e) {
//				LOG.info("Error on exception Strategy",e);
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 
	 */
	public ExceptionStrategyMessageListenerAdapter() {
	}

	/**
	 * @param delegate
	 */
	public ExceptionStrategyMessageListenerAdapter(Object delegate) {
		super(delegate);

	}

	/* (non-Javadoc)
	 * @see org.springframework.jms.listener.adapter.MessageListenerAdapter#handleListenerException(java.lang.Throwable)
	 */
	@Override
	protected void handleListenerException(Throwable ex) {
		throw new RuntimeException("Error on listener execution", ex);

	}

	/**
	 * @return the excStrategy
	 */
	public ExceptionStrategy getExcStrategy() {
		return excStrategy;
	}

	/**
	 * @param excStrategy the excStrategy to set
	 */
	public void setExcStrategy(ExceptionStrategy excStrategy) {
		this.excStrategy = excStrategy;
	}


}
