package es.mpr.plataformamensajeria.servlet;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.quartz.Planificador;

/**
 * Servlet implementation class ServletEjecutarProceso
 */

public class ServletSetEstadoProceso extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private static final Logger logger = LoggerFactory.getLogger(ServletSetEstadoProceso.class);
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSetEstadoProceso() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// cargamos el properties para constanstes.

		ServletOutputStream stream = null;
		String respuesta = "";
		String proceso = request.getParameter("job");
		if(proceso == null || proceso.equals("")){
			return;
		}
		 ApplicationContext applicationContext =
				 WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		try {
			Planificador p = new Planificador(applicationContext);
			p.planificarUnProceso(proceso);
			
			respuesta = "Planificacion automatica del proceso " + proceso + " correcta.";
			stream = response.getOutputStream();
			stream.write(respuesta.getBytes());
			stream.flush();
			stream.close();		

		} catch (Exception e1) {
			logger.error("Ha ocurrido un error en la planificaci�n del proceso: ", e1);
			respuesta = "Ha ocurrido un error realizando la planificaci�n sobre el proceso " + proceso;
			stream = response.getOutputStream();
			stream.write(respuesta.getBytes());
			stream.flush();
			stream.close();
		}
	}

}
