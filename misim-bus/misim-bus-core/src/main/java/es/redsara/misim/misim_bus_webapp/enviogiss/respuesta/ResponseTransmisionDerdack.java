
package es.redsara.misim.misim_bus_webapp.enviogiss.respuesta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para responseTransmisionDerdack complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="responseTransmisionDerdack">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nIndex" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strErrorDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseTransmisionDerdack", propOrder = {
    "_return",
    "nIndex",
    "strErrorDescription"
})
public class ResponseTransmisionDerdack {

    @XmlElement(name = "return", required = true)
    protected String _return;
    @XmlElement(required = true)
    protected String nIndex;
    protected String strErrorDescription;

    /**
     * Obtiene el valor de la propiedad return.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturn() {
        return _return;
    }

    /**
     * Define el valor de la propiedad return.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturn(String value) {
        this._return = value;
    }

    /**
     * Obtiene el valor de la propiedad nIndex.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNIndex() {
        return nIndex;
    }

    /**
     * Define el valor de la propiedad nIndex.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNIndex(String value) {
        this.nIndex = value;
    }

    /**
     * Obtiene el valor de la propiedad strErrorDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrErrorDescription() {
        return strErrorDescription;
    }

    /**
     * Define el valor de la propiedad strErrorDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrErrorDescription(String value) {
        this.strErrorDescription = value;
    }

}
