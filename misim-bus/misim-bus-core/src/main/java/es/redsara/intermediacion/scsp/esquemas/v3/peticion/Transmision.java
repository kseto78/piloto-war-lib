
package es.redsara.intermediacion.scsp.esquemas.v3.peticion;

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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}CodigoCertificado"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}IdSolicitud"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}IdTransmision" minOccurs="0"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}FechaGeneracion" minOccurs="0"/>
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
@XmlRootElement(name = "Transmision")
public class Transmision {

    @XmlElement(name = "CodigoCertificado", required = true)
    protected String codigoCertificado;
    @XmlElement(name = "IdSolicitud", required = true)
    protected String idSolicitud;
    @XmlElement(name = "IdTransmision")
    protected String idTransmision;
    @XmlElement(name = "FechaGeneracion")
    protected String fechaGeneracion;

    /**
     * Obtiene el valor de la propiedad codigoCertificado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCertificado() {
        return codigoCertificado;
    }

    /**
     * Define el valor de la propiedad codigoCertificado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCertificado(String value) {
        this.codigoCertificado = value;
    }

    /**
     * Obtiene el valor de la propiedad idSolicitud.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSolicitud() {
        return idSolicitud;
    }

    /**
     * Define el valor de la propiedad idSolicitud.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSolicitud(String value) {
        this.idSolicitud = value;
    }

    /**
     * Obtiene el valor de la propiedad idTransmision.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTransmision() {
        return idTransmision;
    }

    /**
     * Define el valor de la propiedad idTransmision.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTransmision(String value) {
        this.idTransmision = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaGeneracion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    /**
     * Define el valor de la propiedad fechaGeneracion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaGeneracion(String value) {
        this.fechaGeneracion = value;
    }

}
