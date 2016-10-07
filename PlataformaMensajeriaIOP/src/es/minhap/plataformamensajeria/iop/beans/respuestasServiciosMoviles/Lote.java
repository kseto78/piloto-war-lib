//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.02.24 a las 09:09:37 PM CET 
//


package es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles;

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
 * &lt;complexType name="lote"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idLote" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ErrorLote" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}responseStatusType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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

    @XmlElement(required = true, namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected String idLote;
    @XmlElement(name = "ErrorLote", namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
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
