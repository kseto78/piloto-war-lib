/**
 * 
 */
package es.mpr.template.web.action.files;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.FileUtil;
import org.apache.commons.vfs.VFS;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.map.j2ee.exceptions.BaseException;
import com.opensymphony.xwork2.ActionSupport;

/**
 * The Class DescargaFicherosAction.
 *
 * @author carlos.ruiz
 */
public class DescargaFicherosAction extends ActionSupport implements ServletResponseAware{

	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Variable que almacena la ruta del fichero a descargar. */
	private String fichero = "";
	
	/** Objeto response. */
	private HttpServletResponse response;
	
	/**
	 * Método que obtiene el listado de ficheros disponibles para descarga.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String descargarFichero() throws BaseException{
		//if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		FileSystemManager fileManager = null;
		String sFichero = this.getFichero();
		FileObject ficheroDescargar = null;
		try {
			fileManager = VFS.getManager();
			ficheroDescargar = fileManager.resolveFile(sFichero);
			//Prepara la respuesta para permitir guardar el fichero
			//Definir el content type como application/download
			response.setContentType("application/download");
			
			//Definir el header para indicar el nombre del fichero a descargar.
			String contentDisposition = "attachment; filename=\"" + 
			ficheroDescargar.getName().getBaseName() + "\"";
			response.setHeader("Content-Disposition", contentDisposition);
			   
			// guarda en la respuesta la información del fichero
			byte bFichero[] = FileUtil.getContent(ficheroDescargar);
		
            ServletOutputStream sOutStream = response.getOutputStream();
			sOutStream.write(bFichero);
            response.flushBuffer();
           
		} catch (Exception e) {
			throw new BaseException(e);
		} finally {
			try {
				ficheroDescargar.close();
			}catch (FileSystemException fse) {}
			fileManager.closeFileSystem(ficheroDescargar.getFileSystem());			
		}
		return NONE;
	}

	/**
	 * Obtener fichero.
	 *
	 * @return the fichero
	 */
	public String getFichero() {
		return fichero;
	}

	/**
	 * Modificar fichero.
	 *
	 * @param fichero the fichero to set
	 */
	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setServletResponse(HttpServletResponse aResponse) {
		this.response = aResponse;
	}

	/**
	 * Obtener servlet response.
	 *
	 * @return servlet response
	 */
	public HttpServletResponse getServletResponse() {
		return this.response;
	}

}
