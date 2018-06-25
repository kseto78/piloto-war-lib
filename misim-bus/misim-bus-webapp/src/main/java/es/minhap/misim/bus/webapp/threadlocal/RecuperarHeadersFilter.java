package es.minhap.misim.bus.webapp.threadlocal;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase java que implementas filtros para la cabecera
 * @author ralberoc
 *
 */
public class RecuperarHeadersFilter implements Filter{

	private static final Logger LOG = LoggerFactory.getLogger(RecuperarHeadersFilter.class);
	
	/**
	 * {@inheritDoc}
	 */
	public RecuperarHeadersFilter() {
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			
		LOG.info("RecuperarHeadersFilter - doFilter - start");
		HttpServletRequest requestHttp = (HttpServletRequest) request;		
		HttpServletResponse responseHttp = (HttpServletResponse) response;
		
		String header = requestHttp.getHeader("X-Client-Serial");
		LOG.info("RecuperarHeadersFilter header:" + header);
		if (header!=null && !("(null)").equals(header)){
			RecuperarHeaders.setHeader(header);
		}
		
		LOG.info("RecuperarHeadersFilter - doFilter - end");
		
		chain.doFilter(requestHttp, responseHttp);
		return;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroy() {
		
		LOG.debug("RecuperarHeadersFilter - destroy - start");
		
		RecuperarHeaders.removeHeader();
		
		LOG.debug("RecuperarHeadersFilter - destroy - end");
	}
	
}
