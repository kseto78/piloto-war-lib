
package es.redsara.misim.misim_bus_webapp.peticion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DestinatariosPush complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DestinatariosWebPush">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DestinatarioWebPush" type="{http://misim.redsara.es/misim-bus-webapp/peticion}DestinatarioWebPush" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DestinatariosWebPush", propOrder = {
    "destinatarioWebPush"
})
public class DestinatariosWebPush {

    @XmlElement(name = "DestinatarioWebPush", required = true)
    protected List<DestinatarioWebPush> destinatarioWebPush;

    /**
     * Gets the value of the destinatarioWebPush property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the destinatarioWebPush property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDestinatarioWebPush().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DestinatarioWebPush }
     * 
     * 
     */
    public List<DestinatarioWebPush> getDestinatarioWebPush() {
        if (destinatarioWebPush == null) {
            destinatarioWebPush = new ArrayList<DestinatarioWebPush>();
        }
        return this.destinatarioWebPush;
    }

}
