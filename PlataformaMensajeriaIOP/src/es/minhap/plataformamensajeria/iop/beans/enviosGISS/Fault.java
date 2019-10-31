//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.05.27 a las 01:47:59 PM CEST 
//


package es.minhap.plataformamensajeria.iop.beans.enviosGISS;

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
 *         &lt;element name="Faultcode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Faultstring" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Detail" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}detail"/&gt;
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
    "faultcode",
    "faultstring",
    "detail"
})
@XmlRootElement(name = "Fault")
public class Fault {

    protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/respuesta";
	@XmlElement(name = "Faultcode", required = true, namespace=R_CONST_1)
    protected String faultcode;
    @XmlElement(name = "Faultstring", required = true, namespace=R_CONST_1)
    protected String faultstring;
    @XmlElement(name = "Detail", required = true, namespace=R_CONST_1)
    protected Detail detail;

    /**
     * Obtiene el valor de la propiedad faultcode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaultcode() {
        return faultcode;
    }

    /**
     * Define el valor de la propiedad faultcode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaultcode(String value) {
        this.faultcode = value;
    }

    /**
     * Obtiene el valor de la propiedad faultstring.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaultstring() {
        return faultstring;
    }

    /**
     * Define el valor de la propiedad faultstring.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaultstring(String value) {
        this.faultstring = value;
    }

    /**
     * Obtiene el valor de la propiedad detail.
     * 
     * @return
     *     possible object is
     *     {@link Detail }
     *     
     */
    public Detail getDetail() {
        return detail;
    }

    /**
     * Define el valor de la propiedad detail.
     * 
     * @param value
     *     allowed object is
     *     {@link Detail }
     *     
     */
    public void setDetail(Detail value) {
        this.detail = value;
    }

    
    public String toXMLSMS(Fault resp)throws PlataformaBusinessException{
    	Fault respuesta = this;
    	
    	try {
					
            JAXBContext jaxbContext = JAXBContext.newInstance(Fault.class);
    		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    		
    		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    		
    		StringWriter writer = new StringWriter();
    		jaxbMarshaller.marshal(respuesta, writer);
    				
    		return writer.toString();
    		   		
    		} catch (JAXBException e) {
    			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
    		}
    }
}
