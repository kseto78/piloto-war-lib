
package es.redsara.misim.misim_bus_webapp.peticion.envio.aplicaciones;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Adjuntos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Adjuntos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Adjunto" type="{http://misim.redsara.es/misim-bus-webapp/peticion}Adjunto" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Adjuntos", propOrder = {
    "adjunto"
})
public class Adjuntos {

    @XmlElement(name = "Adjunto", required = true)
    protected List<Adjunto> adjunto;

    /**
     * Gets the value of the adjunto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adjunto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdjunto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Adjunto }
     * 
     * 
     */
    public List<Adjunto> getAdjunto() {
        if (adjunto == null) {
            adjunto = new ArrayList<Adjunto>();
        }
        return this.adjunto;
    }

}
