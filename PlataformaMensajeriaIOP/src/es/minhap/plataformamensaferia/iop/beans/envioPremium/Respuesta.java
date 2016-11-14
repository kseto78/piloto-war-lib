
package es.minhap.plataformamensaferia.iop.beans.envioPremium;

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
 *         &lt;element name="StatusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StatusText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Details" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idExterno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idMensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "statusCode",
    "statusText",
    "details",
    "idExterno",
    "idMensaje"
})
@XmlRootElement(name = "Respuesta" , namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
public class Respuesta {

    @XmlElement(name = "StatusCode", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected String statusCode;
    @XmlElement(name = "StatusText", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected String statusText;
    @XmlElement(name = "Details", namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected String details;
    @XmlElement(name = "IdExterno", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected String idExterno;
    @XmlElement(name = "IdMensaje", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/respuesta")
    protected String idMensaje;

    /**
     * Obtiene el valor de la propiedad idExterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdExterno() {
        return idExterno;
    }

    /**
     * Define el valor de la propiedad idExterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdExterno(String value) {
        this.idExterno = value;
    }

    /**
     * Obtiene el valor de la propiedad idMensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMensaje() {
        return idMensaje;
    }

    /**
     * Define el valor de la propiedad idMensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMensaje(String value) {
        this.idMensaje = value;
    }
    
    public String toXMLSMS(Respuesta resp)throws PlataformaBusinessException{
    	Respuesta respuesta = this;
    	
    	try {
			
			
            JAXBContext jaxbContext = JAXBContext.newInstance(Respuesta.class);
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
    
    public void loadObjectFromXML(String xmlRespuesta)
			throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Respuesta.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlRespuesta);
			Respuesta respuesta = (Respuesta) unmarshaller
					.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this,
					respuesta);

			
		} catch (JAXBException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xmlRespuesta);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xmlRespuesta);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException(
					"Error procesando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
							+ xmlRespuesta);
		}
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusText
	 */
	public String getStatusText() {
		return statusText;
	}

	/**
	 * @param statusText the statusText to set
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

}
