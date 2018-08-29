package es.mpr.template.web.action.mail;

import java.io.File;

/**
 * <p>Formulario para el env&iacute;o de un correo elect&oacute;nico</p>.
 *
 * @author Altran
 */
public class MailSenderForm {
    
    /**  from. */
    private String from;
    
    /**  to. */
    private String to;
    
    /**  cc. */
    private String cc;
    
    /**  bcc. */
    private String bcc;
    
    /**  subject. */
    private String subject;
    
    /**  html contents. */
    private String htmlContents;
    
    /**  content type. */
    private String contentType="text/html";
    
    /**  adjuntos. */
    private File[] adjuntos=new File[3];

    /**
     * Obtener bcc.
     *
     * @return {@link String}
     */
    public String getBcc() {
        return bcc;
    }
    
    /**
     * Modificar bcc.
     *
     * @param bcc new bcc
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }
    
    /**
     * Obtener cc.
     *
     * @return {@link String}
     */
    public String getCc() {
        return cc;
    }
    
    /**
     * Modificar cc.
     *
     * @param cc new cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }
    
    /**
     * Obtener from.
     *
     * @return {@link String}
     */
    public String getFrom() {
        return from;
    }
    
    /**
     * Modificar from.
     *
     * @param from new from
     */
    public void setFrom(String from) {
        this.from = from;
    }
    
    /**
     * Obtener html contents.
     *
     * @return {@link String}
     */
    public String getHtmlContents() {
        return htmlContents;
    }
    
    /**
     * Modificar html contents.
     *
     * @param htmlContents new html contents
     */
    public void setHtmlContents(String htmlContents) {
        this.htmlContents = htmlContents;
    }
    
    /**
     * Obtener subject.
     *
     * @return {@link String}
     */
    public String getSubject() {
        return subject;
    }
    
    /**
     * Modificar subject.
     *
     * @param subject new subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    /**
     * Obtener to.
     *
     * @return {@link String}
     */
    public String getTo() {
        return to;
    }
    
    /**
     * Modificar to.
     *
     * @param to new to
     */
    public void setTo(String to) {
        this.to = to;
    }
    
    /**
     * Obtener adjuntos.
     *
     * @return {@link File[]}
     */
    public File[] getAdjuntos() {
        return adjuntos;
    }
    
    /**
     * Modificar adjuntos.
     *
     * @param adjuntos new adjuntos
     */
    public void setAdjuntos(File[] adjuntos) {
        this.adjuntos = adjuntos;
    }
    
    /**
     * Obtener content type.
     *
     * @return {@link String}
     */
    public String getContentType() {
        return contentType;
    }
    
    /**
     * Modificar content type.
     *
     * @param contentType new content type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
