//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.05.27 a las 09:32:13 AM CEST 
//


package es.minhap.plataformamensajeria.iop.beans.enviosGISS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para transmisionDerack complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="transmisionDerack"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Return" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="NIndex" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="StrErrorDescription" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transmisionDerdack", propOrder = {
    "_return",
    "nIndex",
    "strErrorDescription"
})
public class TransmisionDerdack {

    @XmlElement(name = "Return", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected String _return;
    @XmlElement(name = "NIndex", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected String nIndex;
    @XmlElement(name = "StrErrorDescription", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
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
