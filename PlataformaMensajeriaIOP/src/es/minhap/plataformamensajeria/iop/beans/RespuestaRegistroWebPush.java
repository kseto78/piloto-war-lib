
package es.minhap.plataformamensajeria.iop.beans;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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
})
@XmlRootElement(name = "RespuestaRegistroWebPush",namespace = RespuestaRegistroWebPush.R_CONST_1)
public class RespuestaRegistroWebPush {

    protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/respuestaRegistroWebPush";
	protected static final String R_CONST_2 = "\\nMensaje: ";
	@XmlElement(name = "Status", required = true,namespace = R_CONST_1)
    protected ResponseStatusTypeRegistroWebPush status;

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link ResponseStatusType }
     *     
     */
    public ResponseStatusTypeRegistroWebPush getStatus() {
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
    public void setStatus(ResponseStatusTypeRegistroWebPush value) {
        this.status = value;
    }

	
	public String toXML() throws PlataformaBusinessException {
		RespuestaRegistroWebPush respuesta = this;

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaRegistroWebPush.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(respuesta, writer);

			return writer.toString();

		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + R_CONST_2
					+ e.getMessage());
		} 
	}
	
	public void loadObjectFromXML(String xmlPeticion) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RespuestaRegistroWebPush.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlPeticion);
			RespuestaRegistroWebPush peticion = (RespuestaRegistroWebPush) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this, peticion);

		} catch (JAXBException | IllegalAccessException | InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + R_CONST_2
					+ e.getMessage() + "\nXML:\n" + xmlPeticion);
		} 
	}

}
