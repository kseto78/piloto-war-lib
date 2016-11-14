
package es.redsara.misim.misim_bus_webapp.passbook.peticion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DestinatariosMail complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DestinatariosMail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DestinatarioMail" type="{http://misim.redsara.es/misim-bus-webapp/peticion}DestinatarioMail" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DestinatariosMail", propOrder = {
    "destinatarioMail"
})
public class DestinatariosMail {

    @XmlElement(name = "DestinatarioMail", required = true)
    protected List<DestinatarioMail> destinatarioMail;

    /**
     * Gets the value of the destinatarioMail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the destinatarioMail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDestinatarioMail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DestinatarioMail }
     * 
     * 
     */
    public List<DestinatarioMail> getDestinatarioMail() {
        if (destinatarioMail == null) {
            destinatarioMail = new ArrayList<DestinatarioMail>();
        }
        return this.destinatarioMail;
    }

}
