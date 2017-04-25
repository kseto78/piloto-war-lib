
package es.minhap.plataformamensajeria.iop.business.beans.enviarmensaje;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
 *         &lt;element name="Status" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}responseStatusType"/>
 *         &lt;element name="MessageId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "messageId"
})
@XmlRootElement(name = "Respuesta", namespace ="http://misim.redsara.es/misim-bus-webapp/respuesta")
public class Respuesta {

    @XmlElement(name = "Status", required = true, namespace ="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected ResponseStatusType status;
    @XmlElement(name = "MessageId", namespace ="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected String messageId;

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link ResponseStatusType }
     *     
     */
    public ResponseStatusType getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseStatusType }
     *     
     */
    public void setStatus(ResponseStatusType value) {
        this.status = value;
    }

    /**
     * Obtiene el valor de la propiedad messageId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Define el valor de la propiedad messageId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageId(String value) {
        this.messageId = value;
    }

	@Override
	public String toString() {
		return "Respuesta [status=" + status + ", messageId=" + messageId + "]";
	}

public void loadObjectFromXML (String xmlRespuesta)throws PlataformaBusinessException {
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Respuesta.class);
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlRespuesta);
		Respuesta respuestaEnvio = (Respuesta) unmarshaller.unmarshal(reader);
		
		org.apache.commons.beanutils.BeanUtils.copyProperties(this, respuestaEnvio);
	
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlRespuesta);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlRespuesta);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlRespuesta);
		}
	}
	
}
