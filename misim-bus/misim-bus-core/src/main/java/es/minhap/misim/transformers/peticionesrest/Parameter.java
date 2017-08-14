
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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}IdentificadorSolicitante"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}NombreSolicitante"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}UnidadTramitadora" minOccurs="0"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Procedimiento" minOccurs="0"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Finalidad"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Consentimiento"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Funcionario" minOccurs="0"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}IdExpediente" minOccurs="0"/>
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
		"label",
		"value"
})
@XmlRootElement(name = "Parameter", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
public class Parameter {

    @XmlElement(name = "Label", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String label;
    @XmlElement(name = "Value", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String value;
   
    /**
     * Obtiene el valor de la propiedad label.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Define el valor de la propiedad label.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificadorSolicitante(String value) {
        this.label = value;
    }

    /**
     * Obtiene el valor de la propiedad value.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Define el valor de la propiedad value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

}
