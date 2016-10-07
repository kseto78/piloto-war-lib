
package es.mpr.plataformamensajeria.ws.envionotificacionespush;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para mensajesNotificacionType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="mensajesNotificacionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MensajeNotificacion" type="{http://envionotificacionpush.ws.plataformamensajeria.minhap.es/}mensajeNotificacionType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mensajesNotificacionType", propOrder = {
    "mensajeNotificacion"
})
public class MensajesNotificacionType {

    @XmlElement(name = "MensajeNotificacion", required = true)
    protected List<MensajeNotificacionType> mensajeNotificacion;

    /**
     * Gets the value of the mensajeNotificacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mensajeNotificacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMensajeNotificacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MensajeNotificacionType }
     * 
     * 
     */
    public List<MensajeNotificacionType> getMensajeNotificacion() {
        if (mensajeNotificacion == null) {
            mensajeNotificacion = new ArrayList<MensajeNotificacionType>();
        }
        return this.mensajeNotificacion;
    }

}
