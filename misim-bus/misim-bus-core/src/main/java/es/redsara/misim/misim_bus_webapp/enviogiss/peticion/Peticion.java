
package es.redsara.misim.misim_bus_webapp.enviogiss.peticion;

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
 *       &lt;sequence>
 *         &lt;element name="idPeticion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usuSistemaEnvio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="passSistemaEnvio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="aplicaci�n" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroTelefonoDestino" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contenidoMsj" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idPeticion",
    "usuSistemaEnvio",
    "passSistemaEnvio",
    "aplicacion",
    "numeroTelefonoDestino",
    "contenidoMsj"
})
@XmlRootElement(name = "Peticion")
public class Peticion {

    @XmlElement(required = true)
    protected String idPeticion;
    @XmlElement(required = true)
    protected String usuSistemaEnvio;
    @XmlElement(required = true)
    protected String passSistemaEnvio;
    @XmlElement(required = true)
    protected String aplicacion;
    @XmlElement(required = true)
    protected String numeroTelefonoDestino;
    @XmlElement(required = true)
    protected String contenidoMsj;

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
     * Obtiene el valor de la propiedad usuSistemaEnvio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuSistemaEnvio() {
        return usuSistemaEnvio;
    }

    /**
     * Define el valor de la propiedad usuSistemaEnvio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuSistemaEnvio(String value) {
        this.usuSistemaEnvio = value;
    }

    /**
     * Obtiene el valor de la propiedad passSistemaEnvio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassSistemaEnvio() {
        return passSistemaEnvio;
    }

    /**
     * Define el valor de la propiedad passSistemaEnvio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassSistemaEnvio(String value) {
        this.passSistemaEnvio = value;
    }

    /**
     * Obtiene el valor de la propiedad aplicaci�n.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAplicacion() {
        return aplicacion;
    }

    /**
     * Define el valor de la propiedad aplicaci�n.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAplicacion(String value) {
        this.aplicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroTelefonoDestino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroTelefonoDestino() {
        return numeroTelefonoDestino;
    }

    /**
     * Define el valor de la propiedad numeroTelefonoDestino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroTelefonoDestino(String value) {
        this.numeroTelefonoDestino = value;
    }

    /**
     * Obtiene el valor de la propiedad contenidoMsj.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContenidoMsj() {
        return contenidoMsj;
    }

    /**
     * Define el valor de la propiedad contenidoMsj.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContenidoMsj(String value) {
        this.contenidoMsj = value;
    }

}
