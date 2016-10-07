
package es.mpr.template.ws.saludo.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase java para el tipo complejo responseType.
 * 
 * <p>El siguiente XML schema especifica el contenido esperado dentro de esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responseType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @author Altran
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "responseType"
})
@XmlRootElement(name = "saludoReturn")
public class SaludoReturn {

    @XmlElement(required = true)
    protected String responseType;

    /**
     * @return Devuelve el valor de la propiedad responseType
     */
    public String getResponseType() {
        return responseType;
    }

    /**
     * Establece el valor de la propiedad responseType
     * 
     * @param value 
     *     
     */
    public void setResponseType(String value) {
        this.responseType = value;
    }

}
