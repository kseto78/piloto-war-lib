
package es.redsara.misim.misim_bus_webapp.enviogiss.respuesta;

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
 *         &lt;element name="IdPeticion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Estado" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}responseStatusType"/>
 *         &lt;element name="TransmisionDerdack" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}responseTransmisionDerdack"/>
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
    "estado",
    "transmisionDerdack"
})
@XmlRootElement(name = "EnvioResponse", namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
public class Respuesta {

    @XmlElement(name = "IdPeticion", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected String idPeticion;
    @XmlElement(name = "Estado", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected ResponseStatusType estado;
    @XmlElement(name = "TransmisionDerdack", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected ResponseTransmisionDerdack transmisionDerdack;

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
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link ResponseStatusType }
     *     
     */
    public ResponseStatusType getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseStatusType }
     *     
     */
    public void setEstado(ResponseStatusType value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad transmisionDerdack.
     * 
     * @return
     *     possible object is
     *     {@link ResponseTransmisionDerdack }
     *     
     */
    public ResponseTransmisionDerdack getTransmisionDerdack() {
        return transmisionDerdack;
    }

    /**
     * Define el valor de la propiedad transmisionDerdack.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseTransmisionDerdack }
     *     
     */
    public void setTransmisionDerdack(ResponseTransmisionDerdack value) {
        this.transmisionDerdack = value;
    }

}
