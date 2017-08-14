
package es.minhap.misim.transformers.peticionesrest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *       &lt;all>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}IdPeticion"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}NumElementos"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}TimeStamp"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Estado" minOccurs="0"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}CodigoCertificado"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"key"
})
@XmlRootElement(name = "KeyAuthorization", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
public class KeyAuthorization {

    @XmlElement(name = "Key", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String key;

    /**
     * Obtiene el valor de la propiedad key.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Define el valor de la propiedad key.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

}
