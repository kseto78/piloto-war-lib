
package es.mpr.plataformamensajeria.ws.envionotificacionespush;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import es.mpr.plataformamensajeria.ws.envionotificacionespush.ResponseStatusType;


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
 *         &lt;element name="IdLoteEnvio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StatusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StatusText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Details" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mensajes" type="{http://envionotificacionpush.ws.plataformamensajeria.minhap.es/}mensajesType" minOccurs="0"/>
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
    "idLoteEnvio",
    "statusCode",
    "statusText",
    "details",
    "mensajes"
})
@XmlRootElement(name = "envioNotificacionPushResponse")
public class EnvioNotificacionPushResponse {

    @XmlElement(name = "IdLoteEnvio", required = true)
    protected String idLoteEnvio;
    @XmlElement(name = "StatusCode", required = true)
    protected String statusCode;
    @XmlElement(name = "StatusText", required = true)
    protected String statusText;
    @XmlElement(name = "Details")
    protected String details;
    @XmlElement(name = "Mensajes")
    protected MensajesType mensajes;

    /**
     * Obtiene el valor de la propiedad idLoteEnvio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdLoteEnvio() {
        return idLoteEnvio;
    }

    /**
     * Define el valor de la propiedad idLoteEnvio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdLoteEnvio(String value) {
        this.idLoteEnvio = value;
    }

    /**
     * Obtiene el valor de la propiedad statusCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Define el valor de la propiedad statusCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    /**
     * Obtiene el valor de la propiedad statusText.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusText() {
        return statusText;
    }

    /**
     * Define el valor de la propiedad statusText.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusText(String value) {
        this.statusText = value;
    }

    /**
     * Obtiene el valor de la propiedad details.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetails() {
        return details;
    }

    /**
     * Define el valor de la propiedad details.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetails(String value) {
        this.details = value;
    }

    /**
     * Obtiene el valor de la propiedad mensajes.
     * 
     * @return
     *     possible object is
     *     {@link MensajesType }
     *     
     */
    public MensajesType getMensajes() {
        return mensajes;
    }

    /**
     * Define el valor de la propiedad mensajes.
     * 
     * @param value
     *     allowed object is
     *     {@link MensajesType }
     *     
     */
    public void setMensajes(MensajesType value) {
        this.mensajes = value;
    }

}
