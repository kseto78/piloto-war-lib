
package es.minhap.plataformamensajeria.iop.beans;

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
 *         &lt;element name="Status" type="{http://registrousuario.ws.plataformamensajeria.minhap.es/}responseStatusType"/>
 *         &lt;element name="CodConfirmacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "codConfirmacion"
})
@XmlRootElement(name = "RespuestaRegistroMovil",namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaRegistroMovil")
public class RespuestaRegistroMovil {

    @XmlElement(name = "Status", required = true,namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaRegistroMovil")
    protected ResponseStatusTypeRegistroMovil status;
    
    @XmlElement(name = "CodConfirmacion", namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaRegistroMovil")
    protected String codConfirmacion;

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link ResponseStatusType }
     *     
     */
    public ResponseStatusTypeRegistroMovil getStatus() {
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
    public void setStatus(ResponseStatusTypeRegistroMovil value) {
        this.status = value;
    }

	/**
	 * @return the codConfirmacion
	 */
	public String getCodConfirmacion() {
		return codConfirmacion;
	}

	/**
	 * @param codConfirmacion the codConfirmacion to set
	 */
	public void setCodConfirmacion(String codConfirmacion) {
		this.codConfirmacion = codConfirmacion;
	}
    
	public String toXML(RespuestaRegistroMovil resp) throws PlataformaBusinessException {
		RespuestaRegistroMovil respuesta = this;

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaRegistroMovil.class);
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
	
	public void loadObjectFromXML(String xmlPeticion) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RespuestaRegistroMovil.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlPeticion);
			RespuestaRegistroMovil peticion = (RespuestaRegistroMovil) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this, peticion);

		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage() + "\nXML:\n" + xmlPeticion);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage() + "\nXML:\n" + xmlPeticion);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: "
					+ e.getMessage() + "\nXML:\n" + xmlPeticion);
		}
	}

}
