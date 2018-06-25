package es.minhap.misim.components.envio.exceptions;

import java.io.Serializable;
/**
 * 
 * @author i-nercya
 *
 */
public class IncompleteMessageException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IncompleteMessageException(String info) {
		super(info);
	}
}
