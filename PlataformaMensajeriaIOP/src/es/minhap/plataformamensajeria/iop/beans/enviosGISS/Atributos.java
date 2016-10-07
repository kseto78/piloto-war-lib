//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.05.27 a las 01:47:59 PM CEST 
//


package es.minhap.plataformamensajeria.iop.beans.enviosGISS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para atributos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="atributos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IdPeticion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="LiteralError" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="DescripcionError" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "atributos", propOrder = {
    "idPeticion",
    "codigoError",
    "literalError",
    "descripcionError"
})
public class Atributos {

    @XmlElement(name = "IdPeticion", required = true)
    protected String idPeticion;
    @XmlElement(name = "CodigoError", required = true)
    protected String codigoError;
    @XmlElement(name = "LiteralError", required = true)
    protected String literalError;
    @XmlElement(name = "DescripcionError", required = true)
    protected String descripcionError;

    /**
     * Obtiene el valor de la propiedad idPeticion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPeticion() {
        return idPeticion;
    }

    /**
     * Define el valor de la propiedad idPeticion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPeticion(String value) {
        this.idPeticion = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoError.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoError() {
        return codigoError;
    }

    /**
     * Define el valor de la propiedad codigoError.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoError(String value) {
        this.codigoError = value;
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
