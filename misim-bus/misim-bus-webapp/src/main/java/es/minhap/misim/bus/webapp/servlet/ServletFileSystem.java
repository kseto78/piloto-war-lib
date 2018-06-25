package es.minhap.misim.bus.webapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mule.api.MuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.services.filesystem.IMigracionFilesystemService;

/**
 * Servlet implementation class SevletSAML
 */
@WebServlet("/ServletFilesystem")
public class ServletFileSystem extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(ServletFileSystem.class);

	
	private static final String MIGRACIONOK = "Proceso de migración a FileSystem terminado correctamente";
	private static final String MIGRACIONKO = "Proceso de migración a FileSystem terminado con errores";
	private static final String MIGRACIONNOAUTORIZADA = "No está autorizado para ejecutar el proceso de migración de ficheros";
	private static final String OFFSETINICIO = " 00:00:00";
	private static final String OFFSETFIN = " 23:59:59";

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletFileSystem() {
		super();
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");

		// cargamos el properties para constanstes.
		ServletContext context = getServletContext();
		MuleContext muleContext = (MuleContext) context.getAttribute("mule.context");
		PropertiesServices ps = new PropertiesServices((ReloadableResourceBundleMessageSource) muleContext
				.getRegistry().lookupObject("reloadableResourceBundleMessageSource"));
		IMigracionFilesystemService migracionFilesystem = (IMigracionFilesystemService) muleContext.getRegistry().lookupObject("MigracionFilesystemServiceImpl");
		String activo = ps.getMessage("filesystem.activo", null);
		try {
			PrintWriter out = response.getWriter();
			if ("S".equals(activo)) {
				Long mensajeId = checkParameterMensajeId(request);
				Date fechaInicio = checkParameterFechaInicio(request, true);
				Date fechaFin = checkParameterFechaInicio(request, false);
				boolean historicos = checkParameterHistoricos(request);
								
				String txt = migracionFilesystem.modificarMensajes(mensajeId, fechaInicio, fechaFin, historicos)? MIGRACIONOK : MIGRACIONKO ;
				
				out.println("<html>");
				out.println("<body style='width:900px;background-color:white;'>");
				out.println("<div style='width:800px;color:black;'>");
				out.println("<p>" + txt + "</p><br>");
				out.println("</div>");
				out.println("</body>");
				out.println("</html>");
			} else {
				out.println("<html>");
				out.println("<body style='width:900px;background-color:white;'>");
				out.println("<div style='width:800px;color:black;'>");
				out.println("<p>" + MIGRACIONNOAUTORIZADA + "</p><br>");
				out.println("</div>");
				out.println("</body>");
				out.println("</html>");
			}
		} catch (IOException e) {
			log.error("ServletFileSystem.doGet IOException", e);
		} catch (Exception e) {
			log.error("ServletFileSystem.doGet Exception", e);
		}

	}

	/**
	 * Metodo que comprueba los parametros del historico del fileSystem
	 * @param request
	 * @return
	 */
	private boolean checkParameterHistoricos(HttpServletRequest request) {
		boolean res = false;
		try {
			if (request.getParameterMap().containsKey("historico")) {
				String h = request.getParameter("historico");
				return "true".equals(h)? true : false;
			}
		}catch (Exception e) {
			log.error("ServletFileSystem.checkParameterHistoricos ", e);
			return res;
		}
		return res;
	}

	/**
	 * Metodo que comprueba el parametro mensaje
	 * @param request
	 * @return
	 */
	private Long checkParameterMensajeId(HttpServletRequest request) {
		if (request.getParameterMap().containsKey("mensaje")) {
			try {
				return Long.parseLong(request.getParameter("mensaje"));
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * Metodo que comprueba el formato de las fechas
	 * @param request
	 * @param inicio
	 * @return
	 */
	private Date checkParameterFechaInicio(HttpServletRequest request, boolean inicio) {
		SimpleDateFormat formatInicio = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		SimpleDateFormat formatFin = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			if (inicio) {
				if (request.getParameterMap().containsKey("inicio")) {
					return formatInicio.parse(request.getParameter("inicio") + OFFSETINICIO);

				}
			} else {
				if (request.getParameterMap().containsKey("fin")) {

					return formatFin.parse(request.getParameter("fin") + OFFSETFIN);

				}

			}
		} catch (Exception e) {
			log.error("ServletFileSystem.checkParameterFechaInicio ", e);
			return null;
		}
		return null;
	}
}
