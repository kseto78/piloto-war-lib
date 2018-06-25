package es.redsara.misim.misim_bus_webapp.respuesta.rest;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;


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
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="1"/>
 *         &lt;element name="urlClave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="1"/>
 *         &lt;element name="parameters" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="1"/>
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
    "urlClave",
    "parameters",
    "urlReturn"
})
@XmlRootElement(name = "Respuesta",namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaSAMLResponse")
public class RespuestaSAMLRequest {

    @XmlElement(name = "Status", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaSAMLResponse")
	private ResponseSAMLStatusType status;
    @XmlElement(name = "Parameters", required = false, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaSAMLResponse")
	private Parametros parameters;
    @XmlElement(name = "UrlClave", required = false, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaSAMLResponse")
   	private String urlClave;
    @XmlElement(name = "UrlReturn", required = false, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaSAMLResponse")
   	private String urlReturn;
	
    /**
	 * @return the status
	 */
	public ResponseSAMLStatusType getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(ResponseSAMLStatusType status) {
		this.status = status;
	}


	  /**
	 * @return the parameters
	 */
	public Parametros getParameters() {
		return parameters;
	}
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Parametros parameters) {
		this.parameters = parameters;
	}
	/**
	 * @return the urlClave
	 */
	public String getUrlClave() {
		return urlClave;
	}
	/**
	 * @param urlClave the urlClave to set
	 */
	public void setUrlClave(String urlClave) {
		this.urlClave = urlClave;
	}
	
	
	/**
	 * @return the urlReturn
	 */
	public String getUrlReturn() {
		return urlReturn;
	}
	/**
	 * @param urlReturn the urlReturn to set
	 */
	public void setUrlReturn(String urlReturn) {
		this.urlReturn = urlReturn;
	}
	public String toXMLSMS(RespuestaSAMLRequest resp)throws PlataformaBusinessException{
		  RespuestaSAMLRequest respuesta = this;
	    	
	    	try {
				
				
	            JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaSAMLRequest.class);
	    		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

	    		
	    		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    		
	    		StringWriter writer = new StringWriter();
	    		jaxbMarshaller.marshal(respuesta, writer);
	    		
	    		
	    		return writer.toString();
	    		
	    		
	    		} catch (PropertyException e) {
	    			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
	    		} catch (JAXBException e) {
	    			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
	    		}
	    }
}
