
package es.minhap.misim.transformers.respuestasrest;

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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}IdentificadorSolicitante"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}NombreSolicitante"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}UnidadTramitadora" minOccurs="0"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Procedimiento" minOccurs="0"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Finalidad"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Consentimiento"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Funcionario" minOccurs="0"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}IdExpediente" minOccurs="0"/>
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
		"statusCode",
		"statusText",
		"statusDetails"
})
@XmlRootElement(name = "Parameter",namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
public class Status {

    @XmlElement(name = "StatusCode", required = true,namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected String statusCode;
    @XmlElement(name = "StatusText", required = true,namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected String statusText;
    @XmlElement(name = "StatusDetails",namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected String statusDetails;
	
    
    public String getStatusCode() {
		return statusCode;
	}
	public String getStatusText() {
		return statusText;
	}
	public String getStatusDetails() {
		return statusDetails;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public void setStatusDetails(String statusDetails) {
		this.statusDetails = statusDetails;
	}
   
 
}
