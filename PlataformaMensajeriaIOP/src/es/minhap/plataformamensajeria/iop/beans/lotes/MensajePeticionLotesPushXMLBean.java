//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.02.18 a las 12:19:53 PM CET 
//


package es.minhap.plataformamensajeria.iop.beans.lotes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para mensajePush complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="mensajePush"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Titulo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Cuerpo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Icono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Sonido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DestinatariosPush" type="{http://misim.redsara.es/misim-bus-webapp/peticion}DestinatariosPush" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mensajePush", propOrder = {
    "titulo",
    "cuerpo",
    "icono",
    "sonido",
    "notificacionesSilenciosas",
    "destinatariosPush"
})
@XmlRootElement(name = "MensajePush", namespace=MensajePeticionLotesPushXMLBean.R_CONST_1)
public class MensajePeticionLotesPushXMLBean {

    protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/peticion";
	@XmlElement(name = "Titulo", required = true, namespace=R_CONST_1)
    protected String titulo;
    @XmlElement(name = "Cuerpo", required = true, namespace=R_CONST_1)
    protected String cuerpo;
    @XmlElement(name = "Icono", namespace=R_CONST_1)
    protected String icono;
    @XmlElement(name = "Sonido", namespace=R_CONST_1)
    protected String sonido;
    @XmlElement(name = "NotificacionesSilenciosas", namespace=R_CONST_1)
    protected Boolean notificacionesSilenciosas;    
    @XmlElement(name = "DestinatariosPush", namespace=R_CONST_1)
    protected DestinatariosPeticionLotesPushXMLBean destinatariosPush;

    /**
     * Obtiene el valor de la propiedad titulo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define el valor de la propiedad titulo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo(String value) {
        this.titulo = value;
    }

    /**
     * Obtiene el valor de la propiedad cuerpo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuerpo() {
        return cuerpo;
    }

    /**
     * Define el valor de la propiedad cuerpo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuerpo(String value) {
        this.cuerpo = value;
    }

    /**
     * Obtiene el valor de la propiedad icono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIcono() {
        return icono;
    }

    /**
     * Define el valor de la propiedad icono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIcono(String value) {
        this.icono = value;
    }

    /**
     * Obtiene el valor de la propiedad sonido.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSonido() {
        return sonido;
    }

    /**
     * Define el valor de la propiedad sonido.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSonido(String value) {
        this.sonido = value;
    }

    /**
     * Obtiene el valor de la propiedad destinatariosSMS.
     * 
     * @return
     *     possible object is
     *     {@link DestinatariosPeticionLotesPushXMLBean }
     *     
     */
    public DestinatariosPeticionLotesPushXMLBean getDestinatariosPush() {
        return destinatariosPush;
    }

    /**
     * Define el valor de la propiedad destinatariosSMS.
     * 
     * @param value
     *     allowed object is
     *     {@link DestinatariosPeticionLotesPushXMLBean }
     *     
     */
    public void setDestinatariosPush(DestinatariosPeticionLotesPushXMLBean value) {
        this.destinatariosPush = value;
    }

	/**
	 * @return the notificacionesSilenciosas
	 */
	public Boolean getNotificacionesSilenciosas() {
		return notificacionesSilenciosas;
	}

	/**
	 * @param notificacionesSilenciosas the notificacionesSilenciosas to set
	 */
	public void setNotificacionesSilenciosas(Boolean notificacionesSilenciosas) {
		this.notificacionesSilenciosas = notificacionesSilenciosas;
	}    

}
