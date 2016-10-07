package es.mpr.plataformamensajeria.ws.exceptions;

import java.io.Serializable;
/**
 * 
 * @author i-nercya
 *
 */
public class PlataformaBusinessException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PlataformaBusinessException(String info) {
		super(info);
	}
}
