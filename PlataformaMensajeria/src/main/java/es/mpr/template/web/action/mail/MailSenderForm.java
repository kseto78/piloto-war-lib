package es.mpr.template.web.action.mail;

import java.io.File;

/**
 * <p>Formulario para el env&iacute;o de un correo elect&oacute;nico</p>
 *
 * @author Altran
 * 
 */
public class MailSenderForm {
    private String from;
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String htmlContents;
    private String contentType="text/html";
    private File[] adjuntos=new File[3];

    /**
     * @return {@link String}
     */
    public String getBcc() {
        return bcc;
    }
    
    /**
     * 
     * @param bcc 
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }
    
    /**
     * @return {@link String}
     * 
     */
    public String getCc() {
        return cc;
    }
    
    /**
     * @param cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }
    
    /**
     * @return {@link String}
     */
    public String getFrom() {
        return from;
    }
    
    /**
     * @param from
     */
    public void setFrom(String from) {
        this.from = from;
    }
    
    /**
     * @return {@link String}
     */
    public String getHtmlContents() {
        return htmlContents;
    }
    
    /**
     * @param htmlContents
     */
    public void setHtmlContents(String htmlContents) {
        this.htmlContents = htmlContents;
    }
    
    /**
     * @return {@link String}
     */
    public String getSubject() {
        return subject;
    }
    
    /**
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    /**
     * @return {@link String}
     */
    public String getTo() {
        return to;
    }
    
    /**
     * @param to
     */
    public void setTo(String to) {
        this.to = to;
    }
    
    /**
     * @return {@link File[]}
     */
    public File[] getAdjuntos() {
        return adjuntos;
    }
    
    /**
     * @param adjuntos
     */
    public void setAdjuntos(File[] adjuntos) {
        this.adjuntos = adjuntos;
    }
    
    /**
     * @return {@link String}
     */
    public String getContentType() {
        return contentType;
    }
    
    /**
     * @param contentType
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
