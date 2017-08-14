
package es.mpr.template.ws.saludo.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase java para el tipo complejo saludoIn.
 * 
 * <p>El siguiente XML schema especifica el contenido esperado dentro de esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestType" type="{http://ws.mpr.es/saludo_soap_http/types}miTipoString"/>
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
    "requestType"
})
@XmlRootElement(name = "saludoIn")
public class SaludoIn {

    @XmlElement(required = true)
    protected String requestType;

    /**
     * @return Devuelve el valor de la propiedad requestType
     *     
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * Establece el valor de la propiedad requestType.
     * 
     * @param value
     */
    public void setRequestType(String value) {
        this.requestType = value;
    }

}
