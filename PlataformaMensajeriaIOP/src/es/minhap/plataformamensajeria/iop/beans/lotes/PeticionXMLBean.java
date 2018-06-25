//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.02.18 a las 12:19:53 PM CET 
//


package es.minhap.plataformamensajeria.iop.beans.lotes;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="NombreLote" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Servicio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CodSia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodOrganismo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodOrganismoPagadorSMS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Mensajes" type="{http://misim.redsara.es/misim-bus-webapp/peticion}Mensajes"/&gt;
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
    "usuario",
    "password",
    "nombreLote",
    "servicio",
    "codSia",
    "codOrganismo",
    "codOrganismoPagadorSMS",
    "mensajes"
})
@XmlRootElement(name = "Peticion", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
public class PeticionXMLBean {

    @XmlElement(name = "Usuario", namespace="http://misim.redsara.es/misim-bus-webapp/peticion", required = true)
    protected String usuario;
    @XmlElement(name = "Password", namespace="http://misim.redsara.es/misim-bus-webapp/peticion",required = true)
    protected String password;
    @XmlElement(name = "NombreLote", namespace="http://misim.redsara.es/misim-bus-webapp/peticion", required = true)
    protected String nombreLote;
    @XmlElement(name = "Servicio", namespace="http://misim.redsara.es/misim-bus-webapp/peticion",required = true)
    protected String servicio;
    @XmlElement(name = "CodSia",namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String codSia;
    @XmlElement(name = "CodOrganismo",namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String codOrganismo;
    @XmlElement(name = "CodOrganismoPagadorSMS",namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String codOrganismoPagadorSMS;
    @XmlElement(name = "Mensajes", namespace="http://misim.redsara.es/misim-bus-webapp/peticion", required = true)
    protected MensajesPeticionLotesXMLBean mensajes;

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Obtiene el valor de la propiedad password.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define el valor de la propiedad password.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreLote.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreLote() {
        return nombreLote;
    }

    /**
     * Define el valor de la propiedad nombreLote.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreLote(String value) {
        this.nombreLote = value;
    }

    /**
     * Obtiene el valor de la propiedad servicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * Define el valor de la propiedad servicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicio(String value) {
        this.servicio = value;
    }

    /**
     * Obtiene el valor de la propiedad codSia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodSia() {
        return codSia;
    }

    /**
     * Define el valor de la propiedad codSia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodSia(String value) {
        this.codSia = value;
    }

    /**
     * Obtiene el valor de la propiedad codOrganismo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodOrganismo() {
        return codOrganismo;
    }

    /**
     * Define el valor de la propiedad codOrganismo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodOrganismo(String value) {
        this.codOrganismo = value;
    }

    /**
     * Obtiene el valor de la propiedad codOrganismoPagadorSMS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodOrganismoPagadorSMS() {
        return codOrganismoPagadorSMS;
    }

    /**
     * Define el valor de la propiedad codOrganismoPagadorSMS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodOrganismoPagadorSMS(String value) {
        this.codOrganismoPagadorSMS = value;
    }

    /**
     * Obtiene el valor de la propiedad mensajes.
     * 
     * @return
     *     possible object is
     *     {@link MensajesPeticionLotesXMLBean }
     *     
     */
    public MensajesPeticionLotesXMLBean getMensajes() {
        return mensajes;
    }

    /**
     * Define el valor de la propiedad mensajes.
     * 
     * @param value
     *     allowed object is
     *     {@link MensajesPeticionLotesXMLBean }
     *     
     */
    public void setMensajes(MensajesPeticionLotesXMLBean value) {
        this.mensajes = value;
    }

public void loadObjectFromXML (String xmlPeticionXML)throws PlataformaBusinessException {
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(PeticionXMLBean.class);
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlPeticionXML);
		PeticionXMLBean peticionXML = (PeticionXMLBean) unmarshaller.unmarshal(reader);
		
		org.apache.commons.beanutils.BeanUtils.copyProperties(this, peticionXML);
		
		
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlPeticionXML);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlPeticionXML);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlPeticionXML);
		}
	}

public String toXML(PeticionXMLBean resp) throws PlataformaBusinessException {
	PeticionXMLBean respuesta = this;

	try {

		JAXBContext jaxbContext = JAXBContext.newInstance(PeticionXMLBean.class);
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
