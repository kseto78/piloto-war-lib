/**
 * 
 */
package es.map.sim.jms.strategy;

import javax.jms.Message;

/**
 * @author vbazagad
 *
 */
public interface ExceptionStrategy {

	void manageException(Message message, Throwable t) throws Throwable;

}
