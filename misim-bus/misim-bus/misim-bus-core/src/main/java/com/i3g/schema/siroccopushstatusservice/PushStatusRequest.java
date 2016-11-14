
package com.i3g.schema.siroccopushstatusservice;

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
 *         &lt;element name="principal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="siroccoTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="status" type="{http://i3g.com/schema/SiroccoPushStatusService}PushStatusMessageHeaderArrayType"/>
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
    "principal",
    "siroccoTime",
    "status"
})
@XmlRootElement(name = "PushStatusRequest")
public class PushStatusRequest {

    @XmlElement(required = true)
    protected String principal;
    protected long siroccoTime;
    @XmlElement(required = true)
    protected PushStatusMessageHeaderArrayType status;

    /**
     * Obtiene el valor de la propiedad principal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * Define el valor de la propiedad principal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrincipal(String value) {
        this.principal = value;
    }

    /**
     * Obtiene el valor de la propiedad siroccoTime.
     * 
     */
    public long getSiroccoTime() {
        return siroccoTime;
    }

    /**
     * Define el valor de la propiedad siroccoTime.
     * 
     */
    public void setSiroccoTime(long value) {
        this.siroccoTime = value;
    }

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link PushStatusMessageHeaderArrayType }
     *     
     */
    public PushStatusMessageHeaderArrayType getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link PushStatusMessageHeaderArrayType }
     *     
     */
    public void setStatus(PushStatusMessageHeaderArrayType value) {
        this.status = value;
    }

}
