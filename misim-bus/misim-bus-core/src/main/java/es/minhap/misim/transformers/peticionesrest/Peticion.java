
package es.minhap.misim.transformers.peticionesrest;

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
 *       &lt;all>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Atributos"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Solicitudes"/>
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
		"basicAuthorization",
		"keyAuthorization",
		"parameters",
		"message"
})
@XmlRootElement(name = "Peticion", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
public class Peticion {

    @XmlElement(name = "BasicAuthorization", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected BasicAuthorization basicAuthorization;
    @XmlElement(name = "KeyAuthorization", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected KeyAuthorization keyAuthorization;
    @XmlElement(name = "Parameters", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected Parameters parameters;
    @XmlElement(name = "Message", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String message;
    
    
	public BasicAuthorization getBasicAuthorization() {
		return basicAuthorization;
	}
	public KeyAuthorization getKeyAuthorization() {
		return keyAuthorization;
	}
	public Parameters getParameters() {
		return parameters;
	}
	public String getMessage() {
		return message;
	}
	public void setBasicAuthorization(BasicAuthorization basicAuthorization) {
		this.basicAuthorization = basicAuthorization;
	}
	public void setKeyAuthorization(KeyAuthorization keyAuthorization) {
		this.keyAuthorization = keyAuthorization;
	}
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
	public String toXMLSMS(Peticion resp) throws Exception {
		Peticion respuesta = this;

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Peticion.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(respuesta, writer);

			return writer.toString();

		} catch (PropertyException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage());
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage());
		}
	}
}
