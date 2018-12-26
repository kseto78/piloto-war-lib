
package es.redsara.misim.misim_bus_webapp.peticion.envio.aplicaciones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import es.redsara.misim.misim_bus_webapp.passbook.peticion.Passbook;


/**
 * <p>Clase Java para MensajeEmail complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="MensajeEmail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Asunto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Cuerpo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Origen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Modo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Adjuntos" type="{http://misim.redsara.es/misim-bus-webapp/peticion}Adjuntos" minOccurs="0"/>
 *         &lt;element name="DestinatariosMail" type="{http://misim.redsara.es/misim-bus-webapp/peticion}DestinatariosMail"/>
 *         &lt;element name="PassBook" type="{http://misim.redsara.es/misim-bus-webapp/peticion}passbook" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MensajeEmail", propOrder = {
    "asunto",
    "cuerpo",
    "origen",
    "modo",
    "adjuntos",
    "destinatariosMail",
    "passBook"
})
public class MensajeEmail {

    @XmlElement(name = "Asunto", required = true)
    protected String asunto;
    @XmlElement(name = "Cuerpo", required = true)
    protected String cuerpo;
    @XmlElement(name = "Origen")
    protected String origen;
    @XmlElement(name = "Modo")
    protected String modo;
    @XmlElement(name = "Adjuntos")
    protected Adjuntos adjuntos;
    @XmlElement(name = "DestinatariosMail", required = true)
    protected DestinatariosMail destinatariosMail;
    @XmlElement(name = "PassBook")
    protected Passbook passBook;

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
     *     {@link Adjuntos }
     *     
     */
    public Adjuntos getAdjuntos() {
        return adjuntos;
    }

    /**
     * Define el valor de la propiedad adjuntos.
     * 
     * @param value
     *     allowed object is
     *     {@link Adjuntos }
     *     
     */
    public void setAdjuntos(Adjuntos value) {
        this.adjuntos = value;
    }

    /**
     * Obtiene el valor de la propiedad destinatariosMail.
     * 
     * @return
     *     possible object is
     *     {@link DestinatariosMail }
     *     
     */
    public DestinatariosMail getDestinatariosMail() {
        return destinatariosMail;
    }

    /**
     * Define el valor de la propiedad destinatariosMail.
     * 
     * @param value
     *     allowed object is
     *     {@link DestinatariosMail }
     *     
     */
    public void setDestinatariosMail(DestinatariosMail value) {
        this.destinatariosMail = value;
    }
    /**
     * Obtiene el valor de la propiedad passBook.
     * 
     * @return
     *     possible object is
     *     {@link Passbook }
     *     
     */
    public Passbook getPassBook() {
        return passBook;
    }

    /**
     * Define el valor de la propiedad passBook.
     * 
     * @param value
     *     allowed object is
     *     {@link Passbook }
     *     
     */
    public void setPassBook(Passbook value) {
        this.passBook = value;
    }
}
