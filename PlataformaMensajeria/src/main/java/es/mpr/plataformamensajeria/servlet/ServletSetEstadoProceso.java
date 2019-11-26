package es.mpr.plataformamensajeria.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.mpr.plataformamensajeria.quartz.Planificador;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;

/**
 * Servlet implementation class ServletEjecutarProceso
 */

public class ServletSetEstadoProceso extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private static final Logger logger = LoggerFactory.getLogger(ServletSetEstadoProceso.class);
	
	/**  properties. */
	private PlataformaMensajeriaProperties properties;
	
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
		 properties = (PlataformaMensajeriaProperties) applicationContext.getBean("plataformaMensajeriaProperties");
		 String nodoPrincipal = properties.getProperty("jobNodoActivos.activacion", null);
		 
		 try {
			
			if(!nodoPrincipal.equals("S")){
				 respuesta = "Nodo no principal, no se planifica ningun job.";
				 stream = response.getOutputStream();
				 stream.write(respuesta.getBytes());
			     stream.flush();
				 stream.close();
				 return;
			 }
			
			Planificador p = new Planificador(applicationContext);
			boolean planificado = p.planificarUnProceso(proceso);
			
			if(planificado){
				respuesta = "Planificacion automatica del proceso " + proceso + " correcta.";
			}
			else{
				respuesta="No se ha planificado ningun proceso";
			}
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
