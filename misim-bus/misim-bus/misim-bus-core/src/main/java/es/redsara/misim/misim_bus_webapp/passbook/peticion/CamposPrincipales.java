
package es.redsara.misim.misim_bus_webapp.passbook.peticion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para camposPrincipales complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="camposPrincipales">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PkFields" type="{http://misim.redsara.es/misim-bus-webapp/peticion}pkFields"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "camposPrincipales", propOrder = {
    "pkFields"
})
public class CamposPrincipales {

    @XmlElement(name = "PkFields", required = true)
    protected PkFields pkFields;

    /**
     * Obtiene el valor de la propiedad pkFields.
     * 
     * @return
     *     possible object is
     *     {@link PkFields }
     *     
     */
    public PkFields getPkFields() {
        return pkFields;
    }

    /**
     * Define el valor de la propiedad pkFields.
     * 
     * @param value
     *     allowed object is
     *     {@link PkFields }
     *     
     */
    public void setPkFields(PkFields value) {
        this.pkFields = value;
    }

}
