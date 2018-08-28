
package es.redsara.misim.misim_bus_webapp.peticion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para MensajePush complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="MensajePush">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Titulo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Cuerpo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DestinatariosWebPush" type="{http://misim.redsara.es/misim-bus-webapp/peticion}DestinatariosWebPush"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MensajeWebPush", propOrder = {
    "titulo",
    "cuerpo",
    "destinatariosWebPush"
})
public class MensajeWebPush {

    @XmlElement(name = "Titulo", required = true)
    protected String titulo;
    @XmlElement(name = "Cuerpo", required = true)
    protected String cuerpo;
    @XmlElement(name = "DestinatariosWebPush", required = true)
    protected DestinatariosWebPush destinatariosWebPush;

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
     * Obtiene el valor de la propiedad destinatariosWebPush.
     * 
     * @return
     *     possible object is
     *     {@link DestinatariosWebPush }
     *     
     */
    public DestinatariosWebPush getDestinatariosWebPush() {
        return destinatariosWebPush;
    }

    /**
     * Define el valor de la propiedad destinatarioWebsPush.
     * 
     * @param value
     *     allowed object is
     *     {@link DestinatariosWebPush }
     *     
     */
    public void setDestinatariosWebPush(DestinatariosWebPush value) {
        this.destinatariosWebPush = value;
    }

}
