
package es.redsara.misim.misim_bus_webapp.passbook.respuesta;

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
 *         &lt;element name="Status" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}responseStatusType"/>
 *         &lt;element name="Lote" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}lote" minOccurs="0"/>
 *         &lt;element name="Mensajes" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}mensajes" minOccurs="0"/>
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
    "status",
    "lote",
    "mensajes"
})
@XmlRootElement(name = "Respuesta")
public class Respuesta {

    @XmlElement(name = "Status", required = true)
    protected ResponseStatusType status;
    @XmlElement(name = "Lote")
    protected Lote lote;
    @XmlElement(name = "Mensajes")
    protected Mensajes mensajes;

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link ResponseStatusType }
     *     
     */
    public ResponseStatusType getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseStatusType }
     *     
     */
    public void setStatus(ResponseStatusType value) {
        this.status = value;
    }

    /**
     * Obtiene el valor de la propiedad lote.
     * 
     * @return
     *     possible object is
     *     {@link Lote }
     *     
     */
    public Lote getLote() {
        return lote;
    }

    /**
     * Define el valor de la propiedad lote.
     * 
     * @param value
     *     allowed object is
     *     {@link Lote }
     *     
     */
    public void setLote(Lote value) {
        this.lote = value;
    }

    /**
     * Obtiene el valor de la propiedad mensajes.
     * 
     * @return
     *     possible object is
     *     {@link Mensajes }
     *     
     */
    public Mensajes getMensajes() {
        return mensajes;
    }

    /**
     * Define el valor de la propiedad mensajes.
     * 
     * @param value
     *     allowed object is
     *     {@link Mensajes }
     *     
     */
    public void setMensajes(Mensajes value) {
        this.mensajes = value;
    }

}
