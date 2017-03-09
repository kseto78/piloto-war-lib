package es.minhap.misim.bus.webapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class SevletSAML
 */
@WebServlet("/ServletSAML")
public class ServletSAML extends HttpServlet {

	private static Logger LOG = LoggerFactory.getLogger(ServletSAML.class);

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletSAML() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String SAMLResponse = request.getParameter("SAMLResponse");

		try {
			PrintWriter out = response.getWriter();

			if(LOG.isInfoEnabled()){
				LOG.info("-- START SAMLResponse --");
				LOG.info(SAMLResponse);
				LOG.info("-- END SAMLResponse --");
			}
			out.println("<html>");
			out.println("<body style='width:900px;background-color:white;'>");
			out.println("<div style='width:800px;color:white;'>");
			out.println("<p>" + SAMLResponse + "</p><br>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");

		} catch (Exception e) {
			LOG.error("[ServletSAML] generando printWriter", e);
		}
	}

}
