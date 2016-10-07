//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.07.12 a las 01:29:53 PM CEST 
//


package es.minhap.plataformamensaferia.iop.beans.envioPremium;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="StatusCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="StatusText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Details" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "statusCode",
    "statusText",
    "details"
})
@XmlRootElement(name = "RespuestaNotificacionEstadoSMS", namespace="https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aduanas/es/aeat/dit/adu/itea/server/AcCLEV1Sal.xsd")
public class RespuestaNotificacionEstadoSMS {

    @XmlElement(name = "StatusCode", namespace="https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aduanas/es/aeat/dit/adu/itea/server/AcCLEV1Sal.xsd", required = true)
    protected String statusCode;
    @XmlElement(name = "StatusText", namespace="https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aduanas/es/aeat/dit/adu/itea/server/AcCLEV1Sal.xsd", required = true)
    protected String statusText;
    @XmlElement(name = "Details", namespace="https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aduanas/es/aeat/dit/adu/itea/server/AcCLEV1Sal.xsd", required = true)
    protected String details;

    /**
     * Obtiene el valor de la propiedad statusCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Define el valor de la propiedad statusCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    /**
     * Obtiene el valor de la propiedad statusText.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusText() {
        return statusText;
    }

    /**
     * Define el valor de la propiedad statusText.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusText(String value) {
        this.statusText = value;
    }

    /**
     * Obtiene el valor de la propiedad details.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetails() {
        return details;
    }

    /**
     * Define el valor de la propiedad details.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetails(String value) {
        this.details = value;
    }
    public String toXML() throws PlataformaBusinessException{
    	
    	RespuestaNotificacionEstadoSMS envioXML = this;
    	
    	try {
        JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaNotificacionEstadoSMS.class);
    	Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    	
    	jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    	
    	StringWriter writer = new StringWriter();
    	jaxbMarshaller.marshal(envioXML, writer);
    	jaxbMarshaller.marshal(envioXML, System.out);
    	
    	return writer.toString();
    	} catch (PropertyException e) {
    		throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
    	} catch (JAXBException e) {
    		throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
    	}
    	
    }
}
