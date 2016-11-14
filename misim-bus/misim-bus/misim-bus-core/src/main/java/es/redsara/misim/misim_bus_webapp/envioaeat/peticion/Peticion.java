
package es.redsara.misim.misim_bus_webapp.envioaeat.peticion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idExterno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodOrganismoPagadorSMS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Cuerpo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Destinatario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deliveryReportURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idExterno",
    "codOrganismoPagadorSMS",
    "cuerpo",
    "destinatario",
    "deliveryReportURL"
})
@XmlRootElement(name = "Peticion")
public class Peticion {

    @XmlElement(name = "IdExterno",required = true)
    protected String idExterno;
    @XmlElement(name = "CodOrganismoPagadorSMS", required = true)
    protected String codOrganismoPagadorSMS;
    @XmlElement(name = "Cuerpo", required = true)
    protected String cuerpo;
    @XmlElement(name = "Destinatario", required = true)
    protected String destinatario;
    @XmlElement(name = "DeliveryReportURL",required = true)
    protected String deliveryReportURL;

    /**
     * Obtiene el valor de la propiedad idExterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdExterno() {
        return idExterno;
    }

    /**
     * Define el valor de la propiedad idExterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdExterno(String value) {
        this.idExterno = value;
    }

    /**
     * Obtiene el valor de la propiedad codOrganismoPagadorSMS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodOrganismoPagadorSMS() {
        return codOrganismoPagadorSMS;
    }

    /**
     * Define el valor de la propiedad codOrganismoPagadorSMS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodOrganismoPagadorSMS(String value) {
        this.codOrganismoPagadorSMS = value;
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
     * Obtiene el valor de la propiedad destinatario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * Define el valor de la propiedad destinatario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinatario(String value) {
        this.destinatario = value;
    }

    /**
     * Obtiene el valor de la propiedad deliveryReportURL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryReportURL() {
        return deliveryReportURL;
    }

    /**
     * Define el valor de la propiedad deliveryReportURL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryReportURL(String value) {
        this.deliveryReportURL = value;
    }

}
