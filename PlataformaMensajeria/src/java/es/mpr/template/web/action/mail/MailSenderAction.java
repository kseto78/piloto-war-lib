package es.mpr.template.web.action.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.security.context.SecurityContextHolder;

import com.map.j2ee.mail.MailManager;
import com.map.j2ee.mail.MailMessageVO;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * <p>
 * <a href="MailSenderAction.java.html"> <i>Ver c&oacute;digo fuente</i> </a>
 * </p>
 *
 * @author Altran
 * 
 * @version $Revision: 1.1 $ $Date: 2006/07/03 13:23:27 $
 */
public class MailSenderAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	private static final long serialVersionUID = 1L;

	/**
	 * Definicion del logger
	 */
	protected Log logger = LogFactory.getLog(this.getClass());

    private MailManager mailManager;
    
    protected MailSenderForm f = new MailSenderForm();

	protected HttpServletRequest request;
	
	protected HttpServletResponse response;
	
	protected List<KeyValueObject> listaFormatosCorreo = new ArrayList<KeyValueObject>();
	
    private List<File> adjuntos;//La lista de adjuntos
    private List<String> adjuntoContentType; //El content type de los adjuntos
    private List<String> adjuntoFileName; //El nombre de los ficheros adjuntos

	/**
	 * 
	 * <p>M&eacute;todo que genera los datos para presentar el formulario de correo</p>
	 * 
	 * @return
	 */
    public String newMail() {
    	if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
    	KeyValueObject aOption = new KeyValueObject();
    	aOption.setCodigo("text/plain");
    	aOption.setDescripcion(getText("mail.content.type.texto"));
    	listaFormatosCorreo.add(aOption);
    	aOption = new KeyValueObject();
    	aOption.setCodigo("text/html");
    	aOption.setDescripcion(getText("mail.content.type.html"));
    	listaFormatosCorreo.add(aOption);
    	f = new MailSenderForm();
    	
    	return SUCCESS;
    }

    /**
     * 
     * <p>M&eacute;todo que realiza el envío de un email</p>
     * 
     * @return
     * @throws Exception
     */
    public String send() throws Exception {
    	if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 

        MailMessageVO message= new MailMessageVO();
        message.setFrom(f.getFrom());
        message.setCc(f.getCc());
        message.setBcc(f.getBcc());
        message.setTo(f.getTo());
        message.setSubject(f.getSubject());
        message.setHtmlContents(f.getHtmlContents());
        message.setContentType(f.getContentType());        
        message.setAttachments(getAttatchments());
        mailManager.sendEmail(message);
        
        return SUCCESS;
    }
    
    /**
     * @return Returns the mailManager.
     */
    public MailManager getMailManager() {
        return mailManager;
    }
    /**
     * @param mailManager The mailManager to set.
     */
    public void setMailManager(MailManager mailManager) {
        this.mailManager = mailManager;
    }

	public MailSenderForm getF() {
		return f;
	}

	public void setF(MailSenderForm f) {
		this.f = f;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * @return the request
	 */
	public HttpServletRequest getServletRequest() {
		return request;
	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getServletResponse() {
		return response;
	}

	/**
	 * @return the listaFormatosCorreo
	 */
	public List<KeyValueObject> getListaFormatosCorreo() {
		return listaFormatosCorreo;
	}

	/**
	 * @param listaFormatosCorreo the listaFormatosCorreo to set
	 */
	public void setListaFormatosCorreo(List<KeyValueObject> listaFormatosCorreo) {
		this.listaFormatosCorreo = listaFormatosCorreo;
	}

    /**
	 * @return the adjuntos
	 */
	public List<File> getAdjunto() {
		return adjuntos;
	}

	/**
	 * @param adjuntos the adjuntos to set
	 */
	public void setAdjunto(List<File> adjuntos) {
		this.adjuntos = adjuntos;
	}

	/**
	 * @return the adjuntoContentType
	 */
	public List<String> getAdjuntoContentType() {
		return adjuntoContentType;
	}

	/**
	 * @param adjuntoContentType the adjuntoContentType to set
	 */
	public void setAdjuntoContentType(List<String> adjuntoContentType) {
		this.adjuntoContentType = adjuntoContentType;
	}

	/**
	 * @return the adjuntoFileName
	 */
	public List<String> getAdjuntoFileName() {
		return adjuntoFileName;
	}

	/**
	 * @param adjuntoFileName the adjuntoFileName to set
	 */
	public void setAdjuntoFileName(List<String> adjuntoFileName) {
		this.adjuntoFileName = adjuntoFileName;
	}

    /**
     * 
     */
    private ArrayList<File> getAttatchments() throws Exception {
    	ArrayList<File> listaAttachments = new ArrayList<File>();
    	
    	if (getAdjunto() != null) {
	    	for (int i=0; i<getAdjunto().size();i++) {
	    		File unAdjunto = getAdjunto().get(i);
	    		String nombreAdjunto = getAdjuntoFileName().get(i);
	        	File destFile = new File(nombreAdjunto);
	        	FileUtils.copyFile(unAdjunto, destFile);
	        	listaAttachments.add(destFile);
	    	}  	
    		return listaAttachments;
    	} else {
    		return null;
    	}
    }
    /**
	 * <p>M&eacute;todo que realiza la preparaci&oacute;n de datos previos a la ejecución de cualquier acci&oacute;n</p>
	 */

	public void prepare() throws Exception {
		newMail();
	}
    
    
	

}
