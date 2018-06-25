
package es.redsara.misim.misim_bus_webapp.respuesta.envio.aplicaciones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para lote complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="lote">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idLote" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ErrorLote" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}responseStatusType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lote", propOrder = {
    "idLote",
    "errorLote"
})
public class Lote {

    @XmlElement(required = true)
    protected String idLote;
    @XmlElement(name = "ErrorLote")
    protected ResponseStatusType errorLote;

    /**
     * Obtiene el valor de la propiedad idLote.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdLote() {
        return idLote;
    }

    /**
     * Define el valor de la propiedad idLote.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdLote(String value) {
        this.idLote = value;
    }

    /**
     * Obtiene el valor de la propiedad errorLote.
     * 
     * @return
     *     possible object is
     *     {@link ResponseStatusType }
     *     
     */
    public ResponseStatusType getErrorLote() {
        return errorLote;
    }

    /**
     * Define el valor de la propiedad errorLote.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseStatusType }
     *     
     */
    public void setErrorLote(ResponseStatusType value) {
        this.errorLote = value;
    }

}
