package es.minhap.misim.bus.webapp.threadlocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RecuperarHeaders {
	
	private static final Logger LOG = LoggerFactory.getLogger(RecuperarHeaders.class);
	private static ThreadLocal<String> header = new ThreadLocal<String>(){};
		
	public static void setHeader(String string){	
		LOG.debug("SET HEADER: " + string);
		header.set(string);
	}
	
	public static String getHeader(){	
		LOG.debug("GET HEADER: " + header.get());
		return header.get();
	}
	
	public static void removeHeader(){	
		LOG.debug("REMOVE HEADER: " + header.get());
		header.remove();
	}
}
