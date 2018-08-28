package es.mpr.plataformamensajeria.servlet;

import java.io.File;
import java.io.IOException;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;


/**
 * The Class DownloadFile.
 */
public class DownloadFile extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5558728030792820295L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(DownloadFile.class);

	/** Constante PARAM_PATH_CONTENIDO. */
	public static final String PARAM_PATH_CONTENIDO = "pathContenido";
	
	
	/**
	 * Constructor de download file.
	 */
	public DownloadFile() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("DownloadFile - doGet - start");
		
		String pathContenido = request.getParameter(PARAM_PATH_CONTENIDO);
		if (pathContenido==null || "".equals(pathContenido)){
			pathContenido = request.getPathInfo().substring(1);
		}
		if (pathContenido==null || "".equals(pathContenido)){
			logger.error("DownloadFile - doGet - Error: la ruta del contenido esta vacia");
			return;
		}
		
		String titulo = "";

		String[] tituloArray = pathContenido.split("/");
		titulo = tituloArray[tituloArray.length-1];

		try {
			
			File fichero = new File(pathContenido);
			
				if(fichero.exists()){
					ServletOutputStream stream = null;
					String contentDisposition = "attachment; filename=\"" + titulo + "\"";
					response.setHeader("Content-Disposition", contentDisposition);
						response.setContentType(getMimeType(fichero));
						stream = response.getOutputStream();
						stream.write(FileUtils.readFileToByteArray(fichero));
						stream.flush();
				}
			
		
			} 
		catch (Exception e) {
			logger.error("[DownloadFile] - doGet - ERROR: ", e);
		}
		
		logger.debug("DownloadFile - doGet - end");
	}
	
	/**
	 * Obtener mime type.
	 *
	 * @param file the file
	 * @return mime type
	 */
	private String getMimeType(File file) {
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		return mimeTypesMap.getContentType(file);
	}
}
