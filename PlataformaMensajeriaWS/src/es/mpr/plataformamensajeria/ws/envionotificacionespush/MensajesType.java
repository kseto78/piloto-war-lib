
package es.mpr.plataformamensajeria.ws.envionotificacionespush;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para mensajesType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="mensajesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Mensaje" type="{http://envionotificacionpush.ws.plataformamensajeria.minhap.es/}responseStatusType" minOccurs="0" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mensajesType", propOrder = {
    "mensaje"
})
public class MensajesType {

    @XmlElement(name = "Mensaje")
    protected List<ResponseStatusType> mensaje;

    /**
     * Gets the value of the mensaje property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mensaje property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMensaje().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResponseStatusType }
     * 
     * 
     */
    public List<ResponseStatusType> getMensaje() {
        if (mensaje == null) {
            mensaje = new ArrayList<ResponseStatusType>();
        }
        return this.mensaje;
    }
    
    public void setMensaje(List<ResponseStatusType> mensaje) {
        this.mensaje = mensaje;
    }

}
