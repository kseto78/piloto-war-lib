package es.minhap.plataformamensajeria.iop.beans;

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
 *         &lt;element name="NIF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="1"/>
 *         &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="1"/>
 *         &lt;element name="Apellido1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="1"/>
 *         &lt;element name="Apellido2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="1"/>
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
    "nif",
    "nombre",
    "apellido1",
    "apellido2"
})
@XmlRootElement(name = "Respuesta",namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaSAMLResponse")
public class RespuestaSAMLResponse {

    @XmlElement(name = "Status", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaSAMLResponse")
	private ResponseSAMLStatusType status;
    @XmlElement(name = "NIF", required = false, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaSAMLResponse")
	private String nif;
    @XmlElement(name = "Nombre", required = false, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaSAMLResponse")
	private String nombre;
    @XmlElement(name = "Apellido1", required = false, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaSAMLResponse")
	private String apellido1;
    @XmlElement(name = "Apellido2", required = false, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaSAMLResponse")
	private String apellido2;
	
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
	 * @return the nif
	 */
	public String getNif() {
		return nif;
	}
	/**
	 * @param nif the nif to set
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}
	/**
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}
	/**
	 * @param apellido2 the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	public String toXMLSMS(RespuestaSAMLResponse resp)throws PlataformaBusinessException{
		RespuestaSAMLResponse respuesta = this;
	    	
	    	try {
				
				
	            JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaSAMLResponse.class);
	    		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

	    		
	    		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    		
	    		StringWriter writer = new StringWriter();
	    		jaxbMarshaller.marshal(respuesta, writer);
	    		
	    		
	    		return writer.toString();
	    		
	    		
	    		}  catch (PropertyException e) {
	    			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
	    		} catch (JAXBException e) {
	    			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
	    		}
	    }
}
