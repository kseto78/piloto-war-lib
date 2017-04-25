
package es.minhap.plataformamensajeria.iop.beans.estadoUsuarios;

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
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idServicioMovil" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estadoServicio" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "usuario",
    "password",
    "idUsuario",
    "idServicioMovil",
    "estadoServicio"
})
@XmlRootElement(name = "estadoUsuarioRequest")
public class EstadoUsuarioRequest {

    @XmlElement(required = true)
    protected String usuario;
    @XmlElement(required = true)
    protected String password;
    @XmlElement(required = true)
    protected String idUsuario;
    @XmlElement(required = true)
    protected String idServicioMovil;
    @XmlElement(required = true)
    protected String estadoServicio;

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
     * Obtiene el valor de la propiedad idUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Define el valor de la propiedad idUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdUsuario(String value) {
        this.idUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad idServicioMovil.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdServicioMovil() {
        return idServicioMovil;
    }

    /**
     * Define el valor de la propiedad idServicioMovil.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdServicioMovil(String value) {
        this.idServicioMovil = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoServicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoServicio() {
        return estadoServicio;
    }

    /**
     * Define el valor de la propiedad estadoServicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoServicio(String value) {
        this.estadoServicio = value;
    }

    
    public String toXML() throws PlataformaBusinessException{
    	
    	EstadoUsuarioRequest estadoUsuarioRequestXML = this;
    	
    	try {
        JAXBContext jaxbContext = JAXBContext.newInstance(EstadoUsuarioRequest.class);
    	Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    	
    	jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    	
    	StringWriter writer = new StringWriter();
    	jaxbMarshaller.marshal(estadoUsuarioRequestXML, writer);
//    	jaxbMarshaller.marshal(estadoUsuarioRequestXML, System.out);
    	
    	return writer.toString();
    	} catch (PropertyException e) {
    		throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
    	} catch (JAXBException e) {
    		throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
    	}
    	
    }
}
