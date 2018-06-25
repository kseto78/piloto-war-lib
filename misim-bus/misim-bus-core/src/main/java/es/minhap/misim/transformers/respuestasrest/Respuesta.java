
package es.minhap.misim.transformers.respuestasrest;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.redsara.misim.misim_bus_webapp.respuesta.ResponseStatusType;


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
		"status",
		"message"
})
@XmlRootElement(name = "Respuesta", namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
public class Respuesta {

    @XmlElement(name = "Status", namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected ResponseStatusType status;
    @XmlElement(name = "Message", namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected String message;
	
    public ResponseStatusType getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public void setStatus(ResponseStatusType status) {
		this.status = status;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
	public String toXMLSMS(Respuesta resp) throws Exception {
		Respuesta respuesta = this;

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Respuesta.class);
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
	
	public void loadObjectFromXML(String xml)
			throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Respuesta.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xml);
			Respuesta respuesta = (Respuesta) unmarshaller
					.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this,
					respuesta);

			
		} catch (JAXBException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xml);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xml);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xml);
		}
	}
}
