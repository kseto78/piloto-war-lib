
package es.minhap.misim.transformers.peticionesrest;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}SolicitudTransmision" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "parameters"
})
@XmlRootElement(name = "Parameters", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
public class Parameters {

	@XmlElement(name = "Parameter", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected List<Parameter> parameters;

    /**
     * Gets the value of the parameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Parameter }
     * 
     * 
     */
    public List<Parameter> getParameters() {
        if (parameters == null) {
        	parameters = new ArrayList<Parameter>();
        }
        return this.parameters;
    }

    public String toXMLSMS(Parameters resp) throws Exception {
    	Parameters respuesta = this;

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Parameters.class);
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
    
    public Parameters loadObjectFromXML(String xml)
			throws PlataformaBusinessException {
    	Parameters respuesta = null;
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Parameters.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xml);
			respuesta = (Parameters) unmarshaller
					.unmarshal(reader);

			
			
		} catch (JAXBException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xml);
		}
		return respuesta;
	}

}
