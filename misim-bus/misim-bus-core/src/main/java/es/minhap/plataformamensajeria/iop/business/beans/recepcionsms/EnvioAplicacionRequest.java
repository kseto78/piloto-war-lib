
package es.minhap.plataformamensajeria.iop.business.beans.recepcionsms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosEspecificos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Sender" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Recipient" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SMSText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LoteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MessageId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "envioAplicacionRequest", propOrder = {
    "user",
    "password",
    "sender",
    "recipient",
    "smsText",
    "loteId",
    "messageId"
})

public class EnvioAplicacionRequest {

    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "Password", required = true)
    protected String password;
    @XmlElement(name = "Sender", required = true)
    protected String sender;
    @XmlElement(name = "Recipient", required = true)
    protected String recipient;
    @XmlElement(name = "SMSText", required = true)
    protected String smsText;
    @XmlElement(name = "LoteId", required = true)
    protected String loteId;
    @XmlElement(name = "MessageId", required = true)
    protected String messageId;

    /**
     * Obtiene el valor de la propiedad user.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Define el valor de la propiedad user.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Obtiene el valor de la propiedad password.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define el valor de la propiedad password.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
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
     * Obtiene el valor de la propiedad recipient.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Define el valor de la propiedad recipient.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipient(String value) {
        this.recipient = value;
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
     * Obtiene el valor de la propiedad loteId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoteId() {
        return loteId;
    }

    /**
     * Define el valor de la propiedad loteId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoteId(String value) {
        this.loteId = value;
    }

    /**
     * Obtiene el valor de la propiedad messageId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Define el valor de la propiedad messageId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageId(String value) {
        this.messageId = value;
    }

	public String getSmsText() {
		return smsText;
	}

	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}

	@Override
	public String toString() {
		return "EnvioAplicacionRequest [user=" + user + ", password=" + password + ", sender=" + sender + ", recipient=" + recipient + ", smsText=" + smsText + ", loteId=" + loteId + ", messageId=" + messageId + "]";
	}

}
