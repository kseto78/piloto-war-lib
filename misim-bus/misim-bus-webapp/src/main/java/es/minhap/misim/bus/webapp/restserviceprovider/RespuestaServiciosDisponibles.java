package es.minhap.misim.bus.webapp.restserviceprovider;

import java.util.List;

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
 *         &lt;element name="serviciosMoviles" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="1"/>
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
    "serviciosMoviles"
})
@XmlRootElement(name = "Respuesta",namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaServiciosDisponibles")
public class RespuestaServiciosDisponibles {

    @XmlElement(name = "Status", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaServiciosDisponibles")
	private ResponseServDispStatusType status;
    @XmlElement(name = "ServiciosMoviles", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaServiciosDisponibles")
	private List<ServicioMovil> serviciosMoviles;
	/**
	 * @return the status
	 */
	public ResponseServDispStatusType getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(ResponseServDispStatusType status) {
		this.status = status;
	}
	/**
	 * @return the serviciosMoviles
	 */
	public List<ServicioMovil> getServiciosMoviles() {
		return serviciosMoviles;
	}
	/**
	 * @param serviciosMoviles the serviciosMoviles to set
	 */
	public void setServiciosMoviles(List<ServicioMovil> serviciosMoviles) {
		this.serviciosMoviles = serviciosMoviles;
	}
}
