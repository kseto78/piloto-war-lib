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
 * <p>Clase Java para mensajeEmail complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="mensajeEmail"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Asunto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Cuerpo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Origen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Modo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Adjuntos" type="{http://misim.redsara.es/misim-bus-webapp/peticion}adjuntos" minOccurs="0"/&gt;
 *         &lt;element name="DestinatariosMail" type="{http://misim.redsara.es/misim-bus-webapp/peticion}DestinatariosMail" maxOccurs="unbounded"/&gt;
 *         &lt;element name="Passbook" type="{http://misim.redsara.es/misim-bus-webapp/peticion}passbook" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mensajeEmail", propOrder = {
    "asunto",
    "cuerpo",
    "origen",
    "modo",
    "adjuntos",
    "destinatariosMail",
    "passbook"
})
@XmlRootElement(name = "MensajeEmail", namespace=MensajePeticionLotesEmailXMLBean.R_CONST_1)
public class MensajePeticionLotesEmailXMLBean {

    protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/peticion";
	@XmlElement(name = "Asunto", required = true, namespace=R_CONST_1)
    protected String asunto;
    @XmlElement(name = "Cuerpo", required = true, namespace=R_CONST_1)
    protected String cuerpo;
    @XmlElement(name = "Origen", namespace=R_CONST_1)
    protected String origen;
    @XmlElement(name = "Modo", namespace=R_CONST_1)
    protected String modo;
    @XmlElement(name = "Adjuntos", namespace=R_CONST_1)
    protected AdjuntosPeticionLotesXMLBean adjuntos;
    @XmlElement(name = "DestinatariosMail", required = true, namespace=R_CONST_1)
    protected DestinatariosPeticionLotesMailXMLBean destinatariosMail;
    @XmlElement(name = "PassBook",namespace=R_CONST_1)
	protected PassbookXMLBean passbook;

    /**
     * Obtiene el valor de la propiedad asunto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * Define el valor de la propiedad asunto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsunto(String value) {
        this.asunto = value;
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
     * Obtiene el valor de la propiedad origen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Define el valor de la propiedad origen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigen(String value) {
        this.origen = value;
    }

    /**
     * Obtiene el valor de la propiedad modo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModo() {
        return modo;
    }

    /**
     * Define el valor de la propiedad modo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModo(String value) {
        this.modo = value;
    }

    /**
     * Obtiene el valor de la propiedad adjuntos.
     * 
     * @return
     *     possible object is
     *     {@link AdjuntosPeticionLotesXMLBean }
     *     
     */
    public AdjuntosPeticionLotesXMLBean getAdjuntos() {
        return adjuntos;
    }

    /**
     * Define el valor de la propiedad adjuntos.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjuntosPeticionLotesXMLBean }
     *     
     */
    public void setAdjuntos(AdjuntosPeticionLotesXMLBean value) {
        this.adjuntos = value;
    }
    
    /**
     * Obtiene el valor de la propiedad destinatariosMail.
     * 
     * @return
     *     possible object is
     *     {@link DestinatariosPeticionLotesMailXMLBean }
     *     
     */
    public DestinatariosPeticionLotesMailXMLBean getDestinatariosMail() {
        return destinatariosMail;
    }

    /**
     * Define el valor de la propiedad destinatariosMail.
     * 
     * @param value
     *     allowed object is
     *     {@link DestinatariosPeticionLotesMailXMLBean }
     *     
     */
    public void setDestinatariosMail(DestinatariosPeticionLotesMailXMLBean value) {
        this.destinatariosMail = value;
    }

	/**
	 * @return the passbook
	 */
	public PassbookXMLBean getPassbook() {
		return passbook;
	}

	/**
	 * @param passbook the passbook to set
	 */
	public void setPassbook(PassbookXMLBean passbook) {
		this.passbook = passbook;
	}
}
