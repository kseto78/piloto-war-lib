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

import com.map.j2ee.mail.MailManager;
import com.map.j2ee.mail.MailMessageVO;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.ActionSupport;

// TODO: Auto-generated Javadoc
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

	protected static final String INFOUSER = "infoUser";

	protected static final String NOUSER = "noUser";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Definicion del logger. */
	protected Log logger = LogFactory.getLog(this.getClass());

    /**  mail manager. */
    private MailManager mailManager;
    
    /**  f. */
    protected MailSenderForm f = new MailSenderForm();

	/**  request. */
	protected HttpServletRequest request;
	
	/**  response. */
	protected HttpServletResponse response;
	
	/**  lista formatos correo. */
	protected List<KeyValueObject> listaFormatosCorreo = new ArrayList<>();
	
    /**  adjuntos. */
    private List<File> adjuntos;
    //La lista de adjuntos
    
    /**  adjunto content type. */
    private List<String> adjuntoContentType; 
    //El content type de los adjuntos
    
    /**  adjunto file name. */
    private List<String> adjuntoFileName; 
    //El nombre de los ficheros adjuntos

	/**
	 * <p>M&eacute;todo que genera los datos para presentar el formulario de correo</p>.
	 *
	 * @return the string
	 */
    public String newMail() {
    	if(request.getSession().getAttribute(INFOUSER)==null) {
			return NOUSER;
		} 
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
     * <p>M&eacute;todo que realiza el envío de un email</p>.
     *
     * @return the string
     * @throws Exception the exception
     */
    public String send() throws Exception {
    	if(request.getSession().getAttribute(INFOUSER)==null) {
			return NOUSER;
		} 

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
     * Obtener mail manager.
     *
     * @return Returns the mailManager.
     */
    public MailManager getMailManager() {
        return mailManager;
    }
    
    /**
     * Modificar mail manager.
     *
     * @param mailManager The mailManager to set.
     */
    public void setMailManager(MailManager mailManager) {
        this.mailManager = mailManager;
    }

	/**
	 * Obtener f.
	 *
	 * @return f
	 */
	public MailSenderForm getF() {
		return f;
	}

	/**
	 * Modificar f.
	 *
	 * @param f new f
	 */
	public void setF(MailSenderForm f) {
		this.f = f;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * Obtener servlet request.
	 *
	 * @return the request
	 */
	public HttpServletRequest getServletRequest() {
		return request;
	}

	/**
	 * Obtener servlet response.
	 *
	 * @return the response
	 */
	public HttpServletResponse getServletResponse() {
		return response;
	}

	/**
	 * Obtener lista formatos correo.
	 *
	 * @return the listaFormatosCorreo
	 */
	public List<KeyValueObject> getListaFormatosCorreo() {
		return listaFormatosCorreo;
	}

	/**
	 * Modificar lista formatos correo.
	 *
	 * @param listaFormatosCorreo the listaFormatosCorreo to set
	 */
	public void setListaFormatosCorreo(List<KeyValueObject> listaFormatosCorreo) {
		this.listaFormatosCorreo = listaFormatosCorreo;
	}

    /**
     * Obtener adjunto.
     *
     * @return the adjuntos
     */
	public List<File> getAdjunto() {
		return adjuntos;
	}

	/**
	 * Modificar adjunto.
	 *
	 * @param adjuntos the adjuntos to set
	 */
	public void setAdjunto(List<File> adjuntos) {
		this.adjuntos = adjuntos;
	}

	/**
	 * Obtener adjunto content type.
	 *
	 * @return the adjuntoContentType
	 */
	public List<String> getAdjuntoContentType() {
		return adjuntoContentType;
	}

	/**
	 * Modificar adjunto content type.
	 *
	 * @param adjuntoContentType the adjuntoContentType to set
	 */
	public void setAdjuntoContentType(List<String> adjuntoContentType) {
		this.adjuntoContentType = adjuntoContentType;
	}

	/**
	 * Obtener adjunto file name.
	 *
	 * @return the adjuntoFileName
	 */
	public List<String> getAdjuntoFileName() {
		return adjuntoFileName;
	}

	/**
	 * Modificar adjunto file name.
	 *
	 * @param adjuntoFileName the adjuntoFileName to set
	 */
	public void setAdjuntoFileName(List<String> adjuntoFileName) {
		this.adjuntoFileName = adjuntoFileName;
	}

    /**
     * Obtener attatchments.
     *
     * @return attatchments
     * @throws Exception the exception
     */
    private ArrayList<File> getAttatchments() throws Exception {
    	ArrayList<File> listaAttachments = new ArrayList<>();
    	
    	if (getAdjunto() != null) {
	    	for (int i=0, s = getAdjunto().size(); i<s;i++) {
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
     * <p>M&eacute;todo que realiza la preparaci&oacute;n de datos previos a la ejecución de cualquier acci&oacute;n</p>.
     *
     * @throws Exception the exception
     */

	public void prepare() throws Exception {
		newMail();
	}
    
    
	

}
