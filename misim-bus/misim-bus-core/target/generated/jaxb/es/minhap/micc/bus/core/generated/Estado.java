//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2018.06.25 a las 02:30:20 PM CEST 
//


package es.minhap.micc.bus.core.generated;

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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/soapfaultatributos}CodigoEstado" minOccurs="0"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/soapfaultatributos}CodigoEstadoSecundario" minOccurs="0"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/soapfaultatributos}LiteralError" minOccurs="0"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/soapfaultatributos}LiteralErrorSec" minOccurs="0"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/soapfaultatributos}TiempoEstimadoRespuesta" minOccurs="0"/>
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
@XmlRootElement(name = "Estado")
public class Estado {

    @XmlElement(name = "CodigoEstado")
    protected String codigoEstado;
    @XmlElement(name = "CodigoEstadoSecundario")
    protected String codigoEstadoSecundario;
    @XmlElement(name = "LiteralError")
    protected String literalError;
    @XmlElement(name = "LiteralErrorSec")
    protected String literalErrorSec;
    @XmlElement(name = "TiempoEstimadoRespuesta")
    protected Integer tiempoEstimadoRespuesta;

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
     * Obtiene el valor de la propiedad codigoEstadoSecundario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEstadoSecundario() {
        return codigoEstadoSecundario;
    }

    /**
     * Define el valor de la propiedad codigoEstadoSecundario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEstadoSecundario(String value) {
        this.codigoEstadoSecundario = value;
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
     * Obtiene el valor de la propiedad literalErrorSec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLiteralErrorSec() {
        return literalErrorSec;
    }

    /**
     * Define el valor de la propiedad literalErrorSec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLiteralErrorSec(String value) {
        this.literalErrorSec = value;
    }

    /**
     * Obtiene el valor de la propiedad tiempoEstimadoRespuesta.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTiempoEstimadoRespuesta() {
        return tiempoEstimadoRespuesta;
    }

    /**
     * Define el valor de la propiedad tiempoEstimadoRespuesta.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTiempoEstimadoRespuesta(Integer value) {
        this.tiempoEstimadoRespuesta = value;
    }

}
