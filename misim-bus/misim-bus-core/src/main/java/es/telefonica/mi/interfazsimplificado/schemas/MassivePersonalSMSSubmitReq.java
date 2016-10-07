
package es.telefonica.mi.interfazsimplificado.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="Version" type="{http://www.telefonica.es/MI/InterfazSimplificado/schemas}versionType"/>
 *         &lt;element name="Authorization" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="Sender" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Recipients" type="{http://www.telefonica.es/MI/InterfazSimplificado/schemas}massivePersonalRecipientsType"/>
 *         &lt;element name="SMSText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EarliestDeliveryTime" type="{http://www.telefonica.es/MI/InterfazSimplificado/schemas}relativeOrAbsoluteDateType" minOccurs="0"/>
 *         &lt;element name="ExpiryDate" type="{http://www.telefonica.es/MI/InterfazSimplificado/schemas}relativeOrAbsoluteDateType" minOccurs="0"/>
 *         &lt;element name="DeliveryReport" type="{http://www.telefonica.es/MI/InterfazSimplificado/schemas}deliveryReportType" minOccurs="0"/>
 *         &lt;element name="DeliveryReportURL" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="SMSClass" type="{http://www.telefonica.es/MI/InterfazSimplificado/schemas}smsClassType" minOccurs="0"/>
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
    "version",
    "authorization",
    "sender",
    "recipients",
    "smsText",
    "earliestDeliveryTime",
    "expiryDate",
    "deliveryReport",
    "deliveryReportURL",
    "smsClass"
})
@XmlRootElement(name = "massivePersonalSMSSubmitReq")
public class MassivePersonalSMSSubmitReq {

    @XmlElement(name = "Version", required = true)
    protected String version;
    @XmlElement(name = "Authorization", required = true)
    protected byte[] authorization;
    @XmlElement(name = "Sender", required = true)
    protected String sender;
    @XmlElement(name = "Recipients", required = true)
    protected MassivePersonalRecipientsType recipients;
    @XmlElement(name = "SMSText", required = true)
    protected String smsText;
    @XmlElement(name = "EarliestDeliveryTime")
    protected String earliestDeliveryTime;
    @XmlElement(name = "ExpiryDate")
    protected String expiryDate;
    @XmlElement(name = "DeliveryReport")
    protected DeliveryReportType deliveryReport;
    @XmlElement(name = "DeliveryReportURL")
    @XmlSchemaType(name = "anyURI")
    protected String deliveryReportURL;
    @XmlElement(name = "SMSClass")
    protected String smsClass;

    /**
     * Obtiene el valor de la propiedad version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Define el valor de la propiedad version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Obtiene el valor de la propiedad authorization.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getAuthorization() {
        return authorization;
    }

    /**
     * Define el valor de la propiedad authorization.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setAuthorization(byte[] value) {
        this.authorization = value;
    }

    /**
     * Obtiene el valor de la propiedad sender.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSender() {
        return sender;
    }

    /**
     * Define el valor de la propiedad sender.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSender(String value) {
        this.sender = value;
    }

    /**
     * Obtiene el valor de la propiedad recipients.
     * 
     * @return
     *     possible object is
     *     {@link MassivePersonalRecipientsType }
     *     
     */
    public MassivePersonalRecipientsType getRecipients() {
        return recipients;
    }

    /**
     * Define el valor de la propiedad recipients.
     * 
     * @param value
     *     allowed object is
     *     {@link MassivePersonalRecipientsType }
     *     
     */
    public void setRecipients(MassivePersonalRecipientsType value) {
        this.recipients = value;
    }

    /**
     * Obtiene el valor de la propiedad smsText.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSMSText() {
        return smsText;
    }

    /**
     * Define el valor de la propiedad smsText.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSMSText(String value) {
        this.smsText = value;
    }

    /**
     * Obtiene el valor de la propiedad earliestDeliveryTime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEarliestDeliveryTime() {
        return earliestDeliveryTime;
    }

    /**
     * Define el valor de la propiedad earliestDeliveryTime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEarliestDeliveryTime(String value) {
        this.earliestDeliveryTime = value;
    }

    /**
     * Obtiene el valor de la propiedad expiryDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Define el valor de la propiedad expiryDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpiryDate(String value) {
        this.expiryDate = value;
    }

    /**
     * Obtiene el valor de la propiedad deliveryReport.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryReportType }
     *     
     */
    public DeliveryReportType getDeliveryReport() {
        return deliveryReport;
    }

    /**
     * Define el valor de la propiedad deliveryReport.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryReportType }
     *     
     */
    public void setDeliveryReport(DeliveryReportType value) {
        this.deliveryReport = value;
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

    /**
     * Obtiene el valor de la propiedad smsClass.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSMSClass() {
        return smsClass;
    }

    /**
     * Define el valor de la propiedad smsClass.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSMSClass(String value) {
        this.smsClass = value;
    }

}
