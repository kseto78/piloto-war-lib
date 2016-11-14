
package com.i3g.schema.siroccopushstatusservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PushStatusMessageHeaderType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PushStatusMessageHeaderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="globalStatusCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="globalStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="timestampFirst" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="timestampLastUpdate" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="detail" type="{http://i3g.com/schema/SiroccoPushStatusService}PushStatusMessageDetailArrayType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PushStatusMessageHeaderType", propOrder = {
    "guid",
    "globalStatusCode",
    "globalStatus",
    "timestampFirst",
    "timestampLastUpdate",
    "detail"
})
public class PushStatusMessageHeaderType {

    @XmlElement(required = true)
    protected String guid;
    protected int globalStatusCode;
    protected int globalStatus;
    protected long timestampFirst;
    protected long timestampLastUpdate;
    @XmlElement(required = true)
    protected PushStatusMessageDetailArrayType detail;

    /**
     * Obtiene el valor de la propiedad guid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Define el valor de la propiedad guid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuid(String value) {
        this.guid = value;
    }

    /**
     * Obtiene el valor de la propiedad globalStatusCode.
     * 
     */
    public int getGlobalStatusCode() {
        return globalStatusCode;
    }

    /**
     * Define el valor de la propiedad globalStatusCode.
     * 
     */
    public void setGlobalStatusCode(int value) {
        this.globalStatusCode = value;
    }

    /**
     * Obtiene el valor de la propiedad globalStatus.
     * 
     */
    public int getGlobalStatus() {
        return globalStatus;
    }

    /**
     * Define el valor de la propiedad globalStatus.
     * 
     */
    public void setGlobalStatus(int value) {
        this.globalStatus = value;
    }

    /**
     * Obtiene el valor de la propiedad timestampFirst.
     * 
     */
    public long getTimestampFirst() {
        return timestampFirst;
    }

    /**
     * Define el valor de la propiedad timestampFirst.
     * 
     */
    public void setTimestampFirst(long value) {
        this.timestampFirst = value;
    }

    /**
     * Obtiene el valor de la propiedad timestampLastUpdate.
     * 
     */
    public long getTimestampLastUpdate() {
        return timestampLastUpdate;
    }

    /**
     * Define el valor de la propiedad timestampLastUpdate.
     * 
     */
    public void setTimestampLastUpdate(long value) {
        this.timestampLastUpdate = value;
    }

    /**
     * Obtiene el valor de la propiedad detail.
     * 
     * @return
     *     possible object is
     *     {@link PushStatusMessageDetailArrayType }
     *     
     */
    public PushStatusMessageDetailArrayType getDetail() {
        return detail;
    }

    /**
     * Define el valor de la propiedad detail.
     * 
     * @param value
     *     allowed object is
     *     {@link PushStatusMessageDetailArrayType }
     *     
     */
    public void setDetail(PushStatusMessageDetailArrayType value) {
        this.detail = value;
    }

}
