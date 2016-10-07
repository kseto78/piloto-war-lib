package es.mpr.plataformamensajeria.ws.exceptions;

import java.io.Serializable;
/**
 * 
 * @author i-nercya
 *
 */
public class DatabaseException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String info) {
		super(info);
	}
}
