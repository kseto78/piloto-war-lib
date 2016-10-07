package es.minhap.misim.components.envio.exceptions;

import java.io.Serializable;
/**
 * 
 * @author i-nercya
 *
 */
public class IncorrectAttachmentException extends Exception implements Serializable{
	
	public IncorrectAttachmentException(String info) {
		super(info);
	}
}
