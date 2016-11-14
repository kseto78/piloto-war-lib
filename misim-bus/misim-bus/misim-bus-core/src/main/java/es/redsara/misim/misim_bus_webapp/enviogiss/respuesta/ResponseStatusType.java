
package es.redsara.misim.misim_bus_webapp.enviogiss.respuesta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para responseStatusType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="responseStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoEstado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LiteralError" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DescripcionError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseStatusType", propOrder = {
    "codigoEstado",
    "literalError",
    "descripcionError"
})
public class ResponseStatusType {

    @XmlElement(name = "CodigoEstado", required = true)
    protected String codigoEstado;
    @XmlElement(name = "LiteralError", required = true)
    protected String literalError;
    @XmlElement(name = "DescripcionError")
    protected String descripcionError;

    /**
     * Obtiene el valor de la propiedad codigoEstado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEstado() {
        return codigoEstado;
    }

    /**
     * Define el valor de la propiedad codigoEstado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEstado(String value) {
        this.codigoEstado = value;
    }

    /**
     * Obtiene el valor de la propiedad literalError.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLiteralError() {
        return literalError;
    }

    /**
     * Define el valor de la propiedad literalError.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLiteralError(String value) {
        this.literalError = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionError.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionError() {
        return descripcionError;
    }

    /**
     * Define el valor de la propiedad descripcionError.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionError(String value) {
        this.descripcionError = value;
    }

}
