package es.minhap.misim.bus.webapp.threadlocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase java para el elemento header
 * @author ralberoc
 *
 */
public class RecuperarHeaders {
	
	private static final Logger LOG = LoggerFactory.getLogger(RecuperarHeaders.class);
	private static ThreadLocal<String> header = new ThreadLocal<String>(){};
	
	/**
	 * Modifica la cadena header
	 * @param string
	 */
	public static void setHeader(String string){	
		LOG.debug("SET HEADER: " + string);
		header.set(string);
	}
	
	/**
	 * Obtiene la cadena header
	 * @return
	 */
	public static String getHeader(){	
		LOG.debug("GET HEADER: " + header.get());
		return header.get();
	}
	
	/**
	 * Elimina la cadena header
	 */
	public static void removeHeader(){	
		LOG.debug("REMOVE HEADER: " + header.get());
		header.remove();
	}
}
