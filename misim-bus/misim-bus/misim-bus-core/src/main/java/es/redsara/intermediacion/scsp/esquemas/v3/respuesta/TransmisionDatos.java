
package es.redsara.intermediacion.scsp.esquemas.v3.respuesta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import es.redsara.intermediacion.scsp.esquemas.datosespecificos.DatosEspecificos;


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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}DatosGenericos"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}DatosEspecificos" minOccurs="0"/>
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

})
@XmlRootElement(name = "TransmisionDatos")
public class TransmisionDatos {

    @XmlElement(name = "DatosGenericos", required = true)
    protected DatosGenericos datosGenericos;
    @XmlElement(name = "DatosEspecificos", namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos")
    protected DatosEspecificos datosEspecificos;

    /**
     * Obtiene el valor de la propiedad datosGenericos.
     * 
     * @return
     *     possible object is
     *     {@link DatosGenericos }
     *     
     */
    public DatosGenericos getDatosGenericos() {
        return datosGenericos;
    }

    /**
     * Define el valor de la propiedad datosGenericos.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosGenericos }
     *     
     */
    public void setDatosGenericos(DatosGenericos value) {
        this.datosGenericos = value;
    }

    /**
     * Obtiene el valor de la propiedad datosEspecificos.
     * 
     * @return
     *     possible object is
     *     {@link DatosEspecificos }
     *     
     */
    public DatosEspecificos getDatosEspecificos() {
        return datosEspecificos;
    }

    /**
     * Define el valor de la propiedad datosEspecificos.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosEspecificos }
     *     
     */
    public void setDatosEspecificos(DatosEspecificos value) {
        this.datosEspecificos = value;
    }

}
