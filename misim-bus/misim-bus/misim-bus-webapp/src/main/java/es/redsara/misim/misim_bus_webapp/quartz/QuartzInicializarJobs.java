package es.redsara.misim.misim_bus_webapp.quartz;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mule.api.MuleContext;


public class QuartzInicializarJobs extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(QuartzInicializarJobs.class);
	
	private ServletContext ctx = null;

	public void init(ServletConfig config) throws ServletException{
		logger.debug(" QuartzInicializarJobs - Inicializamos el contexto de los jobs");
		ctx = config.getServletContext();
		MuleContext muleContext = (MuleContext) ctx.getAttribute("mule.context");
		Planificador p = new Planificador(muleContext);
		try {
			p.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug(" QuartzInicializarJobs - Salimos del servlet");
	}
	
	public QuartzInicializarJobs() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
