package es.mpr.template.pool;

import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.pool.impl.StackObjectPool;

import com.map.j2ee.util.Constants;

/**
 * El servlet, en el arranque de la aplicaci�n, levanta el pool Se crea el pool
 * y se deja en una propiedad estatica de tal forma que sea accesible por las
 * aplicaciones podria haberse utilizado un bean de Spring pero por
 * accesibilidad se ha elegido esta opci�n
 */
public class servletPool extends HttpServlet {


	
	private static final long serialVersionUID = 5210693274053558468L;
	

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		staticPool.setPool(new StackObjectPool(new factoryPoolParser()));
		Locale locale = new Locale(Constants.FWKDEFAULTLOCALELANG,
				Constants.FWKDEFAULTLOCALECOUNT);
		ServletContext scontext = getServletContext();
		scontext.setAttribute(Constants.STRUTS2SESSIONLOCALEKEY, locale);

	}
}
