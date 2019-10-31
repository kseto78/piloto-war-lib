//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.05.27 a las 09:32:13 AM CEST 
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
 *         &lt;element name="IdPeticion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Estado" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}estado"/&gt;
 *         &lt;element name="TransmisionDerack" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}transmisionDerack"/&gt;
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
    "idPeticion",
    "estado",
    "transmisionDerdack"
})
@XmlRootElement(name = "EnvioResponse", namespace=Respuesta.R_CONST_1)
public class Respuesta {

    protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/respuesta";
	@XmlElement(name = "IdPeticion", required = true, namespace=R_CONST_1)
    protected String idPeticion;
    @XmlElement(name = "Estado", required = true, namespace=R_CONST_1)
    protected Estado estado;
    @XmlElement(name = "TransmisionDerdack", required = true, namespace=R_CONST_1)
    protected TransmisionDerdack transmisionDerdack;

    /**
     * Obtiene el valor de la propiedad idPeticion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPeticion() {
        return idPeticion;
    }

    /**
     * Define el valor de la propiedad idPeticion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPeticion(String value) {
        this.idPeticion = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link Estado }
     *     
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link Estado }
     *     
     */
    public void setEstado(Estado value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad transmisionDerack.
     * 
     * @return
     *     possible object is
     *     {@link TransmisionDerdack }
     *     
     */
    public TransmisionDerdack getTransmisionDerdack() {
        return transmisionDerdack;
    }

    /**
     * Define el valor de la propiedad transmisionDerack.
     * 
     * @param value
     *     allowed object is
     *     {@link TransmisionDerdack }
     *     
     */
    public void setTransmisionDerdack(TransmisionDerdack value) {
        this.transmisionDerdack = value;
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
    		
    		
    		} catch (JAXBException e) {
    			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
    		}
    }
}
