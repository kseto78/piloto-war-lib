
package es.mpr.plataformamensajeria.ws.envionotificacionespush;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="NombreLote" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Servicio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MensajesNotificacion" type="{http://envionotificacionpush.ws.plataformamensajeria.minhap.es/}mensajesNotificacionType"/>
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
    "nombreLote",
    "servicio",
    "usuario",
    "password",
    "mensajesNotificacion"
})
@XmlRootElement(name = "envioNotificacionPushRequest")
public class EnvioNotificacionPushRequest {

    @XmlElement(name = "NombreLote", required = true)
    protected String nombreLote;
    @XmlElement(name = "Servicio", required = true)
    protected String servicio;
    @XmlElement(name = "Usuario", required = true)
    protected String usuario;
    @XmlElement(name = "Password", required = true)
    protected String password;
    @XmlElement(name = "MensajesNotificacion", required = true)
    protected MensajesNotificacionType mensajesNotificacion;

    /**
     * Obtiene el valor de la propiedad nombreLote.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreLote() {
        return nombreLote;
    }

    /**
     * Define el valor de la propiedad nombreLote.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreLote(String value) {
        this.nombreLote = value;
    }

    /**
     * Obtiene el valor de la propiedad servicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * Define el valor de la propiedad servicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicio(String value) {
        this.servicio = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
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
     * Obtiene el valor de la propiedad mensajesNotificacion.
     * 
     * @return
     *     possible object is
     *     {@link MensajesNotificacionType }
     *     
     */
    public MensajesNotificacionType getMensajesNotificacion() {
        return mensajesNotificacion;
    }

    /**
     * Define el valor de la propiedad mensajesNotificacion.
     * 
     * @param value
     *     allowed object is
     *     {@link MensajesNotificacionType }
     *     
     */
    public void setMensajesNotificacion(MensajesNotificacionType value) {
        this.mensajesNotificacion = value;
    }

}
