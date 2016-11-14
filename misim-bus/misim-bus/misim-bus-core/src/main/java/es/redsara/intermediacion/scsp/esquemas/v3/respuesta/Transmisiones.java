
package es.redsara.intermediacion.scsp.esquemas.v3.respuesta;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}TransmisionDatos" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "transmisionDatos"
})
@XmlRootElement(name = "Transmisiones")
public class Transmisiones {

    @XmlElement(name = "TransmisionDatos", required = true)
    protected List<TransmisionDatos> transmisionDatos;
    @XmlAttribute(name = "Id")
    protected String id;

    /**
     * Gets the value of the transmisionDatos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transmisionDatos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransmisionDatos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransmisionDatos }
     * 
     * 
     */
    public List<TransmisionDatos> getTransmisionDatos() {
        if (transmisionDatos == null) {
            transmisionDatos = new ArrayList<TransmisionDatos>();
        }
        return this.transmisionDatos;
    }

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
