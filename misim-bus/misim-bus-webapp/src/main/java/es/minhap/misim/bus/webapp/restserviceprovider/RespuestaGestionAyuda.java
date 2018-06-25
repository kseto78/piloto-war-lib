package es.minhap.misim.bus.webapp.restserviceprovider;

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
 *         &lt;element name="Status" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}responseStatusType"/>
 *         &lt;element name="ayuda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="1"/>
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
    "status",
    "ayuda"
})
@XmlRootElement(name = "Respuesta",namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaGestionAyuda")
public class RespuestaGestionAyuda {

    @XmlElement(name = "Status", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaGestionAyuda")
    protected ResponseAyudaStatusType status;
    @XmlElement(name = "Ayuda", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaGestionAyuda")
	private String ayuda;

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link ResponseAyudaStatusType }
     *     
     */
    public ResponseAyudaStatusType getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseAyudaStatusType }
     *     
     */
    public void setStatus(ResponseAyudaStatusType value) {
        this.status = value;
    }

	/**
	 * @return the ayuda
	 */
	public String getAyuda() {
		return ayuda;
	}

	/**
	 * @param ayuda the ayuda to set
	 */
	public void setAyuda(String ayuda) {
		this.ayuda = ayuda;
	}

}
